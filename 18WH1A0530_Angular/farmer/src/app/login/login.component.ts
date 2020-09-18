import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FarmService } from '../farm.service';
declare var jQuery: any;
import { NotificationService } from '../notification.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  login: any;
  farmer: any;
  detail: any
  user: any = false;
  admin: any = false;
  number: any;
  numList: any;
  temp: any;
  otpd:any;
  otpsent:any;
  newpwd: any;
  constructor(private router: Router, private service: FarmService, private notifyService: NotificationService) {
    this.login = { loginId: '', password: '' };
    this.farmer = { farmerId: '', farmerName: '', farmerMobile: '', emailId: '', loginId: '', password: '' };
  }

  ngOnInit(): void {
    this.service.getNumbers().subscribe((result: any) => { console.log(result); this.numList = result; });
    this.user = this.service.getUserLogged();
    this.admin = this.service.getAdmin();
    console.log(this.farmer);
    if (this.user && !this.admin) {
      this.service.getFarmerById(this.service.getFarmerId()).subscribe((result: any) => { console.log(result); this.farmer = result });
      console.log(this.service.getFarmerId());
      console.log(this.detail);
    }
  }
  loginSubmit(loginForm: any): void {
    console.log(loginForm.value);
    if (this.login.loginId == "admin" && this.login.password == "admin") {
      this.service.setAdmin();
      this.notifyService.showSuccess("Welcome back, Admin", "Notification");
      this.router.navigate(['home']);
    }

    else {
      this.service.loginFarmer(this.login.loginId, this.login.password).subscribe((result: any) => {
        this.farmer = result;
        if (result != null) {
          this.service.setUserLoggedIn(this.farmer.farmerName);
          this.service.setFarmerId(this.farmer.farmerId);
          this.notifyService.showSuccess("Welcome " + this.farmer.farmerName + "!!!", "Notification");
          this.router.navigate(['home']);
        }
        else {
          //  alert('Invalid credentials..');
          this.notifyService.showFailure("Invalid cerdentials!!", "Notification");
        }
      },
        (error) => { })

    }
  }


  updateFarmer() {
    this.service.updateFarmer(this.farmer).subscribe();
    console.log(this.farmer);
    this.notifyService.showSuccess("Updated Sucessfully", "Sucess");
  }

  emailgather() {

    jQuery('#myModal').modal('show');
  }

  validate() {
    console.log(this.number)
    this.temp = String(this.number);
    if (this.numList) {
      if ((this.numList).indexOf(this.temp) > -1) {
        this.service.setNumber(this.number);
        this.otpverify();
      }
      else {
        this.notifyService.showFailure("Enter valid number", "Failure");
      }
    }
    this.number = "";
  }

  otpverify(){
    this.service.getOtp(this.number).subscribe((result: any) => {console.log(result); this.otpsent = result;});
    console.log("HI");
    jQuery('#myModal2').modal('show');
  }

  otp(){
    if(this.otpd != this.otpsent){
      this.errorModal();
    }
    else{
      this.notifyService.showSuccess("Success", "");
  this.resetpassword();
      
    }
    this.otpd="";
  }

  errorModal(){
     jQuery('#errorModal').modal('show');
  }


  resetpassword(){
    jQuery('#resetpw').modal('show');
  }

  

  updatepassword(){
    console.log(this.newpwd);
    this.service.updatePassword(this.newpwd).subscribe((result:any)=>{console.log(result)});
    this.newpwd="";
  }
}
