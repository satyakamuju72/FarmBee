import { Component, OnInit } from '@angular/core';
import { FarmService } from '../farm.service';
import { NotificationService } from '../notification.service';
import { NgForm } from '@angular/forms'; 
declare var jQuery: any;
@Component({
  selector: 'app-vehicledriver',
  templateUrl: './vehicledriver.component.html',
  styleUrls: ['./vehicledriver.component.css']
})
export class VehicledriverComponent implements OnInit {

  vehicleDriver:any;
  vehidrive: any;
  editObject:any;
  isValidFormSubmitted= false;
  mobNumberPattern = "^((\\+91-?)|0)?[0-9]{10}$";
  constructor(private service : FarmService, private notify: NotificationService) { 
    this.editObject = {vehicleNo:'', vehicleType:'', driverName:'', driverNumber:''};
    this.vehicleDriver = {vehicleNo:'', vehicleType:'', driverName:'', driverNumber:''};
  }

  ngOnInit(): void {
    this.service.getAllVehDrive().subscribe((result: any) => {console.log(result); this.vehidrive = result});
  }
  deleteVehDrive(data: any){
    if(data.vehicleNo === 'xxx'){
        this.notify.showFailure("Admin, This is default data!!", "")
        this.ngOnInit();
    }
    else{this.service.deleteVehDrive(data).subscribe((result:any) => {
      const i = this.vehidrive.findIndex((element) => {return element.vehicleNo === data.vehicleNo;
      });
      this.vehidrive.splice(i, 1);
    });
        }
    console.log(data)    
  }
  showEditPopup(data:any){
    this.editObject = data;
    jQuery('#empModel').modal('show');
  }
  updateVehDrive(){
    this.service.updateVehDrive(this.editObject).subscribe();
  }
  showAddPopup(){
    jQuery('#addModel').modal('show');
  }
  addVehDrive(){
    
    this.service.addVehdrive(this.vehicleDriver).subscribe((result : any) => {console.log(result);});
      this.ngOnInit();
      console.log("hi");
  
  }
  
}
