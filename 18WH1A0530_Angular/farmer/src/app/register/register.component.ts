import { Component, OnInit } from '@angular/core';
import { Router } from '../../../node_modules/@angular/router';
import { FarmService } from '../farm.service';
import { NotificationService } from '../notification.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  farmer:any;
    constructor(private router: Router, private service : FarmService, private notify: NotificationService) {
        this.farmer = {farmerName:'',farmerMobile:'',emailId:'',loginId:'',password:''};
    }
  
  ngOnInit(): void {
  }
    registerSubmit(registerForm:any):void{
        this.service.registerfarm(this.farmer).subscribe((result : any) => {console.log(result);})
        console.log(registerForm);
        this.notify.showInfo("Registered successfully", "Notification");
        this.router.navigate(['login']);
    }

}
