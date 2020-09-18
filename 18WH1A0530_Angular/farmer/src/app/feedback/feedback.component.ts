import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css']
})
export class FeedbackComponent implements OnInit {
   submitfeedback:any
  constructor() { }

  ngOnInit(): void {
  }
    submit(){
      console.log(this.submitfeedback)
    }

}
