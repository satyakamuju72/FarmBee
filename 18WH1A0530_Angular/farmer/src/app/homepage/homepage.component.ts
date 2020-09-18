import { Component, OnInit } from '@angular/core';
import { FarmService } from '../farm.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  constructor(private service : FarmService, private router : Router) { }

  ngOnInit(): void {
  }
  home(){
    this.router.navigate(['home']);
  }
  
  
}
