import { Component, OnInit } from '@angular/core';
import { FarmService } from '../farm.service';
import { NotificationService } from '../notification.service';
import { Router } from '@angular/router';
declare var jQuery: any;
@Component({
  selector: 'app-adminpooling',
  templateUrl: './adminpooling.component.html',
  styleUrls: ['./adminpooling.component.css']
})
export class AdminpoolingComponent implements OnInit {
  bookings:any;
  editObject:any;
  vehidrive: any;
  constructor(private service : FarmService, private notify: NotificationService, private router: Router) {
    this.editObject ={bookingId: '', source:'', destination:'', cropType:'', loadSize:'', pool:'', wantDate:'',price: '', status:'', vehicle:{vehicleNo:'', vehicleType:''}, farmer:{farmerId:''}};
   }

  ngOnInit(): void {
    this.service.getAllVehicleNumbers().subscribe((result: any) => {console.log(result); this.vehidrive = result});
    this.service.getPoolingBookings().subscribe((result : any) => {console.log(result); this.bookings = result});
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
      this.ngOnInit();
    }

    
  }

}
