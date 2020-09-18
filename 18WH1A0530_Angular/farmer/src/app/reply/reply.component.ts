import { Component, OnInit, NgZone } from '@angular/core';
import { FarmService } from '../farm.service';
import { Router } from '@angular/router';
import { element } from 'protractor';
import { NotificationService } from '../notification.service';
declare const annyang: any;
@Component({
  selector: 'app-reply',
  templateUrl: './reply.component.html',
  styleUrls: ['./reply.component.css']
})
export class ReplyComponent implements OnInit {
  voiceActiveSectionDisabled: boolean = true;
	voiceActiveSectionError: boolean = false;
	voiceActiveSectionSuccess: boolean = false;
	voiceActiveSectionListening: boolean = false;
	voiceText: any;
  question : any;
  id: any;
  answer : any;
  ansList: any;
  constructor(private ngZone: NgZone, private service:  FarmService, private router: Router, private notify: NotificationService) { 
    this.answer = {suggestion:'', question:{questionId:''}, farmer: {farmerId: '', farmerName:'',farmerMobile:'',emailId:'',loginId:'',password:''}};
  }

  ngOnInit(): void {
    this.id = this.service.getQueId();
    console.log(this.id);
    this.service.getQuestionById(this.id).subscribe((result:any) => {console.log(result); this.question = result;});
    this.service.getAllAnswers(this.id).subscribe((result: any) =>{console.log(result); this.ansList = result;})
  }
  addAnswer(comment: any){
    this.answer.farmer.farmerId = this.service.getFarmerId();
    this.answer.question.questionId = this.service.getQueId();
    if(this.answer.suggestion == ""){
      this.answer.suggestion = this.voiceText;
    }
    if(this.answer.suggestion){
      this.service.addAnswer(this.answer).subscribe((result : any) => {console.log(result);})
      console.log(this.answer);
      this.notify.showInfo("Thanks for your reply", "Posted");
      this.ngOnInit();
    }

  }
  delete(farId: any, answer : any){
    console.log(farId);
    console.log(this.question.farmer.farmerId);
    if(farId == this.service.getFarmerId() || this.service.getFarmerId() == this.question.farmer.farmerId){
      this.service.deleteAnswer(answer).subscribe((result : any)=> {
        const i = this.ansList.findIndex((element) => {return element.ansId === answer.ansId
      });
      this.ansList.splice(i, 1);
    });
      //alert("Message deleted successfully!!");
      this.notify.showSuccess("Reply deleted successfully!!", "Deleted");
    }
    else{
      //alert("You cannot delete this message!!");
      this.notify.showFailure("You cannot delete this reply!!", "Alert");
    }
  }
  initializeVoiceRecognitionCallback(): void {
		annyang.addCallback('error', (err) => {
      if(err.error === 'network'){
        this.voiceText = "Internet is require";
        annyang.abort();
        this.ngZone.run(() => this.voiceActiveSectionSuccess = true);
      } else if (this.voiceText === undefined) {
				this.ngZone.run(() => this.voiceActiveSectionError = true);
				annyang.abort();
			}
		});

		annyang.addCallback('soundstart', (res) => {
      this.ngZone.run(() => this.voiceActiveSectionListening = true);
		});

		annyang.addCallback('end', () => {
      if (this.voiceText === undefined) {
        this.ngZone.run(() => this.voiceActiveSectionError = true);
				annyang.abort();
			}
		});

		annyang.addCallback('result', (userSaid) => {
			this.ngZone.run(() => this.voiceActiveSectionError = false);

			let queryText: any = userSaid[0];

			annyang.abort();

      this.voiceText = queryText;

			this.ngZone.run(() => this.voiceActiveSectionListening = false);
      this.ngZone.run(() => this.voiceActiveSectionSuccess = true);
		});
	}

	startVoiceRecognition(): void {
    this.voiceActiveSectionDisabled = false;
		this.voiceActiveSectionError = false;
		this.voiceActiveSectionSuccess = false;
    this.voiceText = undefined;

		if (annyang) {
			let commands = {
				'demo-annyang': () => { }
			};

			annyang.addCommands(commands);

      this.initializeVoiceRecognitionCallback();

			annyang.start({ autoRestart: false });
		}
	}

	closeVoiceRecognition(): void {
    this.voiceActiveSectionDisabled = true;
		this.voiceActiveSectionError = false;
		this.voiceActiveSectionSuccess = false;
		this.voiceActiveSectionListening = false;
		this.voiceText = undefined;

		if(annyang){
      annyang.abort();
    }
	}
}
