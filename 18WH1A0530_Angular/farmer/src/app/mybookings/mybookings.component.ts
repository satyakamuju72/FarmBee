import { Component, OnInit } from '@angular/core';
import { FarmService } from '../farm.service';
import { DatePipe } from '@angular/common';
import { NotificationService } from '../notification.service';
@Component({
  selector: 'app-mybookings',
  templateUrl: './mybookings.component.html',
  styleUrls: ['./mybookings.component.css']
})
export class MybookingsComponent implements OnInit {
  bookings: any;
  _date: string;
  myDate = new Date();
  current : string;
  constructor(private services: FarmService, private datePipe: DatePipe, private notify: NotificationService) {
    this.current = this.datePipe.transform(this.myDate, 'yyyy-MM-dd');
  }

  ngOnInit(): void {
    this.services.getBookingsById().subscribe((result: any) => { console.log(result); this.bookings = result });
  }
  deleteBooking(b: any) {
    this._date= this.datePipe.transform(b.wantDate, 'yyyy-MM-dd');
    if(this.current < this._date){
      this.services.deleteBook(b).subscribe((result: any) => {
        const i = this.bookings.findIndex((element) => {
          return element.empId === b.empId;
        });
        this.notify.showSuccess("Succesfully cancelled","Success");
        this.bookings.splice(i, 1);
      });
    }
    else{
      this.notify.showFailure("Booking cannot be cancelled","Alert");
    }
    console.log(b)
  }

}
