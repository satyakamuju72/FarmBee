import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FarmService } from '../farm.service';
import { NotificationService } from '../notification.service';

@Component({
  selector: 'app-searchbytitle',
  templateUrl: './searchbytitle.component.html',
  styleUrls: ['./searchbytitle.component.css']
})
export class SearchbytitleComponent implements OnInit {

  queList: any;
  titles:any;
  Question: any;
  data : any;
  constructor(private router: Router, private service: FarmService, private notify: NotificationService) { 
    this.Question = {query: '', farmer: {farmerId: '', farmerName:'',farmerMobile:'',emailId:'',loginId:'',password:''}}
    this.data={caption :''};  
  }

  ngOnInit(): void {
    this.service.getAllTitles().subscribe((result:any)=>{console.log(result); this.titles = result;});
    this.Question.farmer.farmerId = this.service.getFarmerId();
    this.service.getAllQuestionsByTitle().subscribe((result : any) => {console.log(result); this.queList = result;})
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
      //alert("You cannot delete this topic");
      this.notify.showFailure("You cannot delete this topic", "Alert");
    }
  }
  addQues(){
    console.log(this.Question);
    this.service.addQuestion(this.Question).subscribe((result : any) => {console.log(result);});
    //alert("Your topic added!!");
    this.notify.showSuccess("Topic added!!", "Success");
    this.router.navigate(['forum']);
  }
  globalfeed(){
    this.router.navigate(['forum']);
  }
  viewReplies(queId: any){
    this.service.setQueId(queId);
    this.router.navigate(['reply']);
  }
  byTitle(){
    this.service.setTitle(this.data.caption);
    console.log(this.data);
    this.ngOnInit();
  }
}
