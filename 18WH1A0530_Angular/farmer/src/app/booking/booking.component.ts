import { Component, OnInit } from '@angular/core';
import { Router } from '../../../node_modules/@angular/router';
import { FarmService } from '../farm.service';
import { NotificationService } from '../notification.service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent implements OnInit {
  booking: any;
  myDate = new Date();
  current : string;
  constructor(private router: Router, private service : FarmService, private notify : NotificationService, private datePipe: DatePipe) {
    this.booking = {source:'',destination:'',cropType:'',loadSize:'',pool:'',wantDate:'', farmer: {farmerId: '', farmerName:'',farmerMobile:'',emailId:'',loginId:'',password:''}};
    this.current = this.datePipe.transform(this.myDate, 'yyyy-MM-dd');
   }
  
  ngOnInit(): void {

  }
  bookingSubmit(bookingForm:any):void{
    if(this.current > this.booking.wantDate){
      this.notify.showFailure("Previous dates cannot book","Failure")
    }
    else{
      this.booking.farmer.farmerId = this.service.getFarmerId();
      this.service.bookingDetails(this.booking).subscribe((result : any) => {console.log(result);})
      console.log(this.service.getFarmerId());
    //console.log(this.service.getFarmerById());
      this.notify.showInfo("Request sent!! Will be updated soon..","Info");
    }
    //this.router.navigate(['mybookings']);
  }
  myBookings():void{
    this.router.navigate(['mybookings'])
  }
  

}
