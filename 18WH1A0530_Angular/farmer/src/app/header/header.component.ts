import { Component, OnInit } from '@angular/core';
import { FarmService } from '../farm.service';
import { Router } from '@angular/router';
declare var jQuery: any;
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  admin: any;
    user:any;
    authInstance:any;
    gapiSetup:any;
    error:any;
  constructor(private service: FarmService, private router:Router) { 
    this.admin = false;
    this.user = false;
    this.admin = this.service.getAdmin();
    
  }

   ngOnInit() {
    
      this.user = this.service.getAdmin();

  }
  logout(){
    this.service.setAdminOut();
    this.service.setUserLoggedOut();
    console.log("Hi");
    this.router.navigate(['login']);
  }
  trans(){
    console.log("HI");
    jQuery('#myModal').modal('show');
  }
  userck():boolean {
    return this.service.getAdmin()
  }
  empck():boolean{
    return this.service.getUserLogged();

}
}
    

