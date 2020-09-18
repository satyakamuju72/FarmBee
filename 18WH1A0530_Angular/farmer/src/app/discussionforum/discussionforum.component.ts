
import { Component, OnInit } from '@angular/core';
import { FarmService } from '../farm.service';
import { Router } from '@angular/router';
import { NotificationService } from '../notification.service';
declare var jQuery: any;
@Component({
  selector: 'app-discussionforum',
  templateUrl: './discussionforum.component.html',
  styleUrls: ['./discussionforum.component.css']
})
export class DiscussionforumComponent implements OnInit {
  queList : any;
  Question:any;
  data:any;
  constructor(private service: FarmService, private router: Router,  private notify : NotificationService) {
    this.Question = {title:'', query: '', farmer: {farmerId: '', farmerName:'',farmerMobile:'',emailId:'',loginId:'',password:''}};
    this.data={caption :''};
   }

  ngOnInit(): void {
    this.Question.farmer.farmerId = this.service.getFarmerId();
    this.service.getAllQuestions().subscribe((result:any) => {console.log(result); this.queList = result;});
  }

  viewReplies(queId: any){
    this.service.setQueId(queId);
    this.router.navigate(['reply']);
  }
  addQuestion(){
    // this.router.navigate(['question']);
    jQuery('#myModal').modal('show');
  }
  delete(farmId: any, Question:any){
    if(farmId == this.service.getFarmerId()){
      this.service.deleteQuestion(Question).subscribe((result:any) => {
        const i = this.queList.findIndex((element) => {return element.empId === Question.empId;
        });
        this.queList.splice(i, 1);
      });
      this.notify.showSuccess("Topic deleted!!", "Success");
      console.log(Question);
    }
    else{
      //alert("You cannot delete this topic");
      this.notify.showFailure("You cannot delete this topic", "Alert")
    }
  }
  addQues(){
    console.log(this.Question);
    this.service.addQuestion(this.Question).subscribe((result : any) => {console.log(result);});
    //alert("Your topic added!!");
    this.notify.showSuccess("Your topic added!!", "Success");
    this.ngOnInit();
  }
  yourfeed(){
    this.router.navigate(['feed']);
  }
  byTitle(){
    this.service.setTitle(this.data.caption);
    console.log(this.data);
    this.router.navigate(['title']);
  }
}
