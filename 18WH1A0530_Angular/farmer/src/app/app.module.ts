import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule} from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; 
import { ToastrModule } from 'ngx-toastr';
import {RouterModule, Routes} from '@angular/router'; 
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HttpClientModule } from '@angular/common/http';
import { AuthGuard } from './auth.guard';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { HomepageComponent } from './homepage/homepage.component';
import { BookingComponent } from './booking/booking.component';
import { MybookingsComponent } from './mybookings/mybookings.component';
import { DiscussionforumComponent } from './discussionforum/discussionforum.component';
import { ReplyComponent } from './reply/reply.component';
import { YourfeedComponent } from './yourfeed/yourfeed.component';
import { DatePipe } from '@angular/common';
import { UnifilterPipe } from './unifilter.pipe';
import { SearchbytitleComponent } from './searchbytitle/searchbytitle.component';
import { VehicledriverComponent } from './vehicledriver/vehicledriver.component';
import { AdminbookingsComponent } from './adminbookings/adminbookings.component';
import { AdminpoolingComponent } from './adminpooling/adminpooling.component';
import { FooterComponent } from './footer/footer.component';
import { ResetpasswordComponent } from './resetpassword/resetpassword.component';
import { FeedbackComponent } from './feedback/feedback.component';
import { ThankyouComponent } from './thankyou/thankyou.component';

const appRoot: Routes = [{path: 'login',  component:LoginComponent},
{path: 'register',component:RegisterComponent},
{path: '', component:HomepageComponent},
{path: 'pooling',canActivate:[AuthGuard], component:BookingComponent},
{path: 'forum',canActivate:[AuthGuard], component:DiscussionforumComponent},
{path: 'home',component:HomepageComponent},
{path: 'feed',component:YourfeedComponent},
{path: 'reply',component:ReplyComponent},
{path: 'adminpool',component:AdminpoolingComponent},
{path: 'title',component:SearchbytitleComponent},
{path: 'vehicledriver',component:VehicledriverComponent},
{path: 'abookings',component:AdminbookingsComponent},
{path: 'mybookings',component:MybookingsComponent},
{path: 'feedback',component:FeedbackComponent},
{path: 'thankyou',component:ThankyouComponent}
    ]

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginComponent,
    RegisterComponent,
    HomepageComponent,
    BookingComponent,
    MybookingsComponent,
    DiscussionforumComponent,
    ReplyComponent,
    YourfeedComponent,
    UnifilterPipe,
    SearchbytitleComponent,
    VehicledriverComponent,
    AdminbookingsComponent,
    AdminpoolingComponent,
    FooterComponent,
    ResetpasswordComponent,
    FeedbackComponent,
    ThankyouComponent,

    
  ],
  imports: [
      BrowserModule, FormsModule,HttpClientModule,RouterModule.forRoot(appRoot),
    AppRoutingModule,ToastrModule.forRoot(),  BrowserAnimationsModule
  ],
  providers: [AuthGuard, DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }