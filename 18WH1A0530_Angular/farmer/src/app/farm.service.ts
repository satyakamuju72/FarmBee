import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FarmService {
  private isUserLogged: any;
  private farmerId: any;
  private userName: any;
  private queId : any;
  private isAdminLogged: any;
  private title:any;
  private number: any;
  setTitle(title : any){
    this.title = title;
  }
  setNumber(number: any){
    this.number = number;
  }

  constructor(private httpClient: HttpClient) {
    this.isUserLogged = false;
    this.farmerId = null;
    this.userName = null;
    this.isAdminLogged = false;
    this.title = null;
   }
   setAdminOut(){
    this.isAdminLogged = false;

   }
   setAdmin(){
     this.isAdminLogged = true;
   }
   getAdmin(){
     return this.isAdminLogged
   }
   setQueId(queId: any){
     this.queId = queId;
   }
   getQueId(){
     return this.queId;
   }
   setUserLoggedIn(userName: any): void{
     this.isUserLogged = true;
     this.userName = userName;
   }
   setFarmerId(farmerId: any):void{
    this.farmerId = farmerId;
   }
   getUserName():any{
     return this.userName;
   }
   setUserLoggedOut(): void{
    this.isUserLogged = false;
    this.farmerId = null;
    this.userName = null;
   }
   getFarmerId(){
     return this.farmerId;
   }
   getUserLogged(): any{
     return this.isUserLogged;
   }
   registerfarm(farmer: any){
     return this.httpClient.post('RESTAPIPRO/webapi/myresource/registerFarmer', farmer);
   }
   getFarmerById(farmId: any){
     if(farmId != null){
       return this.httpClient.get('RESTAPIPRO/webapi/myresource/getFarmerById/' + farmId);
     }
     return null;
   }
   loginFarmer(loginId: any, password: any){
     return this.httpClient.get('RESTAPIPRO/webapi/myresource/getFarmerByUser/' + loginId +'/' + password);
   }
   bookingDetails(booking: any){
     return this.httpClient.post('RESTAPIPRO/webapi/myresource/bookingDetails/',booking);
   }
   getBookingsById(){
     return this.httpClient.get('RESTAPIPRO/webapi/myresource/getBookingsById/'+this.farmerId);
   }
   getAllQuestions(){
     return this.httpClient.get('RESTAPIPRO/webapi/myresource/GetALLQuestions');
   }
   getQuestionById(quesId: any){
     console.log(quesId);
     return this.httpClient.get('RESTAPIPRO/webapi/myresource/getQuestionById/' + quesId);
   }
   addAnswer(answer: any){
     console.log(answer);
     return this.httpClient.post('RESTAPIPRO/webapi/myresource/AddAnswer', answer);
   }
   addQuestion(question: any){

    return this.httpClient.post('RESTAPIPRO/webapi/myresource/AddQuestion/', question);
  }
   getAllAnswers(quesId:any){
     return this.httpClient.get('RESTAPIPRO/webapi/myresource/getAnswerList/'+ quesId);
   }
   deleteAnswer(answer: any){
     return this.httpClient.delete('RESTAPIPRO/webapi/myresource/deleteAns/' + answer.answerId);
   }
   deleteBook(booking: any){
    return this.httpClient.delete('RESTAPIPRO/webapi/myresource/deleteBooking/' + booking.bookingId);
  }
   deleteQuestion(question: any){
    return this.httpClient.delete('RESTAPIPRO/webapi/myresource/deleteQue/' + question.questionId);
  }
  getAllQuestionsByTitle(){
    console.log(this.title);
    return this.httpClient.get('RESTAPIPRO/webapi/myresource/getQuestionsByTitle/'+this.title);
  }

  getAllQuestionsByFarmerId(){
    return this.httpClient.get('RESTAPIPRO/webapi/myresource/getQuestionsByFarmerId/'+this.farmerId);
  }
  getAllTitles(){
    return this.httpClient.get('RESTAPIPRO/webapi/myresource/getAllTitles');
  }
  getAllVehicleNumbers(){
    return this.httpClient.get('RESTAPIPRO/webapi/myresource/getAllVehicleNumbers');
  }
  addVehdrive(vehicle:any){
    return this.httpClient.post('RESTAPIPRO/webapi/myresource/AddVehDrive/', vehicle);
  }
  getAllVehDrive(){
    return this.httpClient.get('RESTAPIPRO/webapi/myresource/getVehDrive');
  }
  deleteVehDrive(vehicle: any){
    return this.httpClient.delete('RESTAPIPRO/webapi/myresource/deletevehDrive/' + vehicle.vehicleNo);
  }
  updateVehDrive(editObject: any){
    return this.httpClient.put('RESTAPIPRO/webapi/myresource/updateEmp/',editObject)
  }
  getSingleBookings(){
    return this.httpClient.get('RESTAPIPRO/webapi/myresource/singlebookings');
  }
  updateBook(editObject: any){
    return this.httpClient.put('RESTAPIPRO/webapi/myresource/updateBook/',editObject)
  }
  getAcceptedBookings(){
    return this.httpClient.get('RESTAPIPRO/webapi/myresource/acceptedBookings');
  }
  getRejectedBookings(){
    return this.httpClient.get('RESTAPIPRO/webapi/myresource/rejectedbookings');
  }
  getPoolingBookings(){
    return this.httpClient.get('RESTAPIPRO/webapi/myresource/poolingbookings');
  }
  updateFarmer(farmer: any){
    return this.httpClient.put('RESTAPIPRO/webapi/myresource/updateFarm/',farmer);
  }
  updatePassword(password: any){
    return this.httpClient.get('RESTAPIPRO/webapi/myresource/updatePassword/'+ this.number + '/' + password);
  }

  getNumbers(){
    return this.httpClient.get('RESTAPIPRO/webapi/myresource/getNumbers');
  }

  getOtp(number:any){
    return this.httpClient.get('RESTAPIPRO/webapi/myresource/OtpSend/'+number);
  }

}

