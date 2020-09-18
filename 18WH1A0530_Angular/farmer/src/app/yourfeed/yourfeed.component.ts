import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FarmService } from '../farm.service';
import { NotificationService } from '../notification.service';

@Component({
  selector: 'app-yourfeed',
  templateUrl: './yourfeed.component.html',
  styleUrls: ['./yourfeed.component.css']
})
export class YourfeedComponent implements OnInit {
  queList: any;
  Question: any;
  constructor(private router: Router, private service: FarmService, private notify: NotificationService) { 
    this.Question = {query: '', farmer: {farmerId: '', farmerName:'',farmerMobile:'',emailId:'',loginId:'',password:''}}

  }

  ngOnInit(): void {
    this.Question.farmer.farmerId = this.service.getFarmerId();
    this.service.getAllQuestionsByFarmerId().subscribe((result : any) => {console.log(result); this.queList = result;})
  }
  delete(farmId: any, Question:any){
    if(farmId == this.service.getFarmerId()){
      this.service.deleteQuestion(Question).subscribe((result:any) => {
        const i = this.queList.findIndex((element) => {return element.empId === Question.empId;
        });
        this.queList.splice(i, 1);
      });
      console.log(Question);
    }
    else{
      this.notify.showFailure("You cannot delete this topic", "Alert");
    }
  }
  addQues(){
    console.log(this.Question);
    this.service.addQuestion(this.Question).subscribe((result : any) => {console.log(result);});
    this.notify.showSuccess("Topic added!!", "Success");
    this.ngOnInit();
  }
  globalfeed(){
    this.router.navigate(['forum']);
  }
  viewReplies(queId: any){
    this.service.setQueId(queId);
    this.router.navigate(['reply']);
  }

}
