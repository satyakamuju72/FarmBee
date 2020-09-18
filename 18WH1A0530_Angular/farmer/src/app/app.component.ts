import { Component } from '@angular/core';
import { FarmService } from './farm.service';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
    title = 'farmbee';
    constructor(private toastr: ToastrService) { }

  ngOnInit() {
      
  }
 
  showToaster(){
      this.toastr.success("Hello, I'm the toastr message.")
  }
}