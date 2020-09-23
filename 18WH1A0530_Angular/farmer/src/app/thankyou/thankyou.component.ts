import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NotificationService } from '../notification.service';

@Component({
  selector: 'app-thankyou',
  templateUrl: './thankyou.component.html',
  styleUrls: ['./thankyou.component.css']
})
export class ThankyouComponent implements OnInit {
    feedbackrate: any
    submitfeedback: any;
    constructor(private router: Router, private notifyService: NotificationService) { 
                this.feedbackrate = 0;
    }

    ngOnInit(): void {

    }
    submitFarmer(){
        this.notifyService.showSuccess("Thank you for your feedback💜", "Feedback Recorded!");

        this.router.navigate(['home']);

    }

}
