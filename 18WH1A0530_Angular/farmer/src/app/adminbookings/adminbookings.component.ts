import { Component, OnInit } from '@angular/core';
import { FarmService } from '../farm.service';
import { analyzeAndValidateNgModules } from '@angular/compiler';
import { NotificationService } from '../notification.service';
import { Router } from '@angular/router';
declare var jQuery: any;
@Component({
  selector: 'app-adminbookings',
  templateUrl: './adminbookings.component.html',
  styleUrls: ['./adminbookings.component.css']
})
export class AdminbookingsComponent implements OnInit {
  bookings: any;
  editObject: any;
  vehidrive: any;
  acceptedList:any;
  rejectedList:any;
  constructor(private service : FarmService, private notify: NotificationService, private router: Router) {
    this.editObject ={bookingId: '', source:'', destination:'', cropType:'', loadSize:'', pool:'', wantDate:'',price: '', status:'', vehicle:{vehicleNo:'', vehicleType:''}, farmer:{farmerId:''}};
   }

  ngOnInit(): void {
    this.service.getAllVehicleNumbers().subscribe((result: any) => {console.log(result); this.vehidrive = result});
    this.service.getSingleBookings().subscribe((result : any) => {console.log(result); this.bookings = result});
  }
  showEditPopup(data:any){
    this.editObject = data;
    jQuery('#empModel').modal('show');
  }
  updateBooking(){
    console.log(this.editObject);
    if((this.editObject.bookingStatus == "rejected" && this.editObject.vehicle.vehicleNo == "xxx") ||  (this.editObject.bookingStatus == "accepted" && this.editObject.vehicle.vehicleNo != "xxx")){
      this.service.updateBook(this.editObject).subscribe();
    }
    else{
      this.notify.showFailure("Invalid Vehicle Number","Alert");
    }
    
  }
  acceptedBookings(){
      console.log("hi");
    this.service.getAcceptedBookings().subscribe((result: any) => {console.log(result); this.acceptedList = result});
      
    jQuery('#accept').modal('show');
  }
    rejectedBookings(){
    this.service.getRejectedBookings().subscribe((result: any) => {console.log(result); this.rejectedList = result});
        
    jQuery('#reject').modal('show');
  }
  pooling(){
    this.router.navigate(['adminpool']);
  }
}
