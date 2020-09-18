package com.ts;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.dao.AnswerDAO;
import com.dao.BookingDAO;
import com.dao.FarmerDAO;
import com.dao.QuestionDAO;
import com.dao.VehicleDAO;
import com.dto.Answer;
import com.dto.Booking;
import com.dto.Farmer;
import com.dto.Question;
import com.dto.Vehicle;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    @Path("registerFarmer")
    @POST
	@Consumes(MediaType.APPLICATION_JSON)
    public void registerFarmer(Farmer farmer){
    	System.out.println("Data recieved..." + farmer);
    	FarmerDAO farmerdao = new FarmerDAO();
    	farmerdao.register(farmer);
    }
    @Path("AddAnswer")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addAnswer(Answer answer){
    	System.out.println("In add answer");
    	System.out.println("Data recieved..." + answer);
    	new AnswerDAO().addAnswer(answer);
    }
    @Path("AddQuestion")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addQuestion(Question question){
    	System.out.println("Data recieved ....." + question);
    	new QuestionDAO().addQuestion(question);
    }
    @Path("AddVehDrive")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addVehDrive(Vehicle vehicle){
    	System.out.println("Data recieved ....." + vehicle);
    	new VehicleDAO().register(vehicle);
    }
    @Path("getFarmerByUser/{loginId}/{password}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Farmer getEmployeeById(@PathParam("loginId") String loginId, @PathParam("password") String password){
		System.out.println("Recieved is : " + loginId +" " + password); 
		FarmerDAO farmerdao = new FarmerDAO();
		Farmer farmer = farmerdao.getFarmerByUserPass(loginId, password);
		System.out.println(farmer);
		if(farmer != null) return farmer;
		return null;
	}
    
    @Path("updatePassword/{number}/{password}")
   	@GET
   	@Produces(MediaType.APPLICATION_JSON)
   	public void updatePass(@PathParam("number") String number, @PathParam("password") String password){
   		System.out.println("Data Recieved in updatepassword : " + number + password); 
   		new FarmerDAO().updatepassword(number, password);
   		
   	}
    
    @Path("getBookingsById/{farmerId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Booking> getBookingsById(@PathParam("farmerId") String farmerId){
    	System.out.println("Recieved is : " + farmerId);
    	int farmId = Integer.parseInt(farmerId);
    	List<Booking> bookList =  new BookingDAO().getBookings(farmId);
    	System.out.println(bookList);
    	Collections.reverse(bookList);
    	return bookList;
    }
    @Path("bookingDetails")
    @POST
	@Consumes(MediaType.APPLICATION_JSON)
    public void bookingDetails(Booking booking){
    	System.out.println("Data recieved..." + booking);
    	BookingDAO bookingdao = new BookingDAO();
    	booking.setBookingStatus("pending");
    	booking.setVehicle(new VehicleDAO().getVehicle("xxx"));
    	bookingdao.addBookingData(booking);
    }
    @Path("GetALLQuestions")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Question> getAllQuestions(){
    	QuestionDAO queDao = new QuestionDAO();
    	List<Question> queList = queDao.getAllQuestions();
    	Collections.reverse(queList);
    	return queList;
    }
    @Path("singlebookings")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Booking> singlebookings(){
    	BookingDAO queDao = new BookingDAO();
    	List<Booking> booklist = queDao.getSingleBookings();
    	Collections.reverse(booklist);
    	return booklist;
    }
    @Path("poolingbookings")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Booking> poolingbookings(){
    	BookingDAO queDao = new BookingDAO();
    	List<Booking> booklist = queDao.getPoolingBookings();
    	Collections.reverse(booklist);
    	return booklist;
    }
    @Path("acceptedBookings")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Booking> acceptedBookings(){
    	BookingDAO queDao = new BookingDAO();
    	List<Booking> booklist = queDao.getBookingsByStatus("accepted");
    	Collections.reverse(booklist);
    	return booklist;
    }
    @Path("rejectedbookings")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Booking> rejectedbookings(){
    	BookingDAO queDao = new BookingDAO();
    	List<Booking> booklist = queDao.getBookingsByStatus("rejected");
    	Collections.reverse(booklist);
    	return booklist;
    }
    
    @Path("getAllTitles")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getAllTitles(){
    	return new QuestionDAO().getTitles();
    }
    
    @Path("getNumbers")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getAllNumbers(){
    	return new FarmerDAO().getNumbers();
    }
    
    @Path("getAllVehicleNumbers")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getAllVehicleNumbers(){
    	return new VehicleDAO().getVehicleNo();
    }
    
    @Path("getQuestionsByFarmerId/{farmerId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Question> getQuestionsByFarmerId(@PathParam("farmerId") int farmerId){
    	return new QuestionDAO().getAllQuestionsByFarmerId(farmerId);
    }
    
    @Path("getQuestionsByTitle/{title}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Question> getQuestionsByFarmerId(@PathParam("title") String title){
    	return new QuestionDAO().getQuestionsByTitle(title);
    }
    
    @Path("getQuestionById/{questionId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Question getQuestionById(@PathParam("questionId") String questionId){
    	System.out.println(questionId);
    	int x = Integer.parseInt(questionId);
    	return new QuestionDAO().getQuestion(x);
    }
    @Path("getAnswerList/{questionId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Answer> getAnswerList(@PathParam("questionId") String questionId){
    	System.out.println(questionId);
    	int x = Integer.parseInt(questionId);
    	return new AnswerDAO().getAnsByQId(x);
    }
    
    @Path("deleteAns/{ansId}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteEmp(@PathParam("ansId") int ansId){
		System.out.println("Data Recieved in delete : " + ansId);
		AnswerDAO ansDAO = new AnswerDAO();
		Answer answer = ansDAO.getAnswerById(ansId);
		ansDAO.deleteAns(answer);
	}
    @Path("deleteQue/{queId}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteQue(@PathParam("queId") int queId){
		System.out.println("Data Recieved in delete : " + queId);
		new QuestionDAO().deleteQuestion(queId);
	}
    @Path("deleteBooking/{bookingId}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteBooking(@PathParam("bookingId") int bookingId){
		System.out.println("Data Recieved in delete : " + bookingId);
		new BookingDAO().deleteBooking(bookingId);
	}
    
    @Path("getFarmerById/{farmerId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Farmer getFarmerById(@PathParam("farmerId") int farmerId){
    	return new FarmerDAO().getFarmer(farmerId);
    }
    
    @Path("getVehDrive")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vehicle> getVehDrive(){
    	return new VehicleDAO().getAllVehicle();
    }
    @Path("deletevehDrive/{vehicle}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public void deletevehDrive(@PathParam("vehicle") String vehicle){
    	System.out.println("Data recieved...." + vehicle);
    	new VehicleDAO().deleteVehicle(vehicle);
    }
    
    @Path("updateEmp")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateEmp(Vehicle vehicle){
		System.out.println("Data Recieved in update : " + vehicle); 
		new VehicleDAO().updateVehicle(vehicle);
		
	}
    @Path("updateBook")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateBook(Booking booking){
		System.out.println("Data Recieved in update : " + booking); 
		new BookingDAO().updateBooking(booking);
		
	}
    @Path("updateFarm")
   	@PUT
   	@Consumes(MediaType.APPLICATION_JSON)
   	public void updateFarm(Farmer farmer){
   		System.out.println("Data Recieved in update : " + farmer); 
   		new FarmerDAO().update(farmer);
   		
   	}
    
    
    
    
    @Path("OtpSend/{number}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public int SMSSending(@PathParam("number") String mobile ) {
    	int otp = (int) (Math.random()*9000)+1000;
    	System.out.println(mobile);
		Twilio.init("AC2d7cb43faf4b771b97a1588b50233448", "cb0f2d867dfbcfefb6822c3b20b9bc2a");
		Message message = Message.creator(new PhoneNumber("+91"+mobile), new PhoneNumber("+18138561208"), "\n\nHello, Your otp to reset password - " + otp).create();

		System.out.println(message.getSid() +"" + otp);
		return otp;
	}
    
    
    
}
