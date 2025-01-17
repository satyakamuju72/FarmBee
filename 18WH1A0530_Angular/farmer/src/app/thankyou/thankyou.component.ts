import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NotificationService } from '../notification.service';
import { FarmService } from '../farm.service';

@Component({
  selector: 'app-thankyou',
  templateUrl: './thankyou.component.html',
  styleUrls: ['./thankyou.component.css']
})
export class ThankyouComponent implements OnInit {
    feedbackrate: any
    submitfeedback: any;
    constructor(private router: Router, private notifyService: NotificationService,private service: FarmService) { 
                this.feedbackrate = 0;
    }

    ngOnInit(): void {

    }
    submitFarmer(){
         this.service.sendMail(this.submitfeedback).subscribe();
        this.notifyService.showSuccess("Thank you for your feedback💜", "Feedback Recorded!");

        this.router.navigate(['home']);

    }

}
