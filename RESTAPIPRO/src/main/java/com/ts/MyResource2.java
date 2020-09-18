package com.ts;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

/**
 * Root resource (exposed at "myresource2" path)
 */
@Path("myresource2")
public class MyResource2 {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
	@Path("addFarmer")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String addFarmer(){
		Farmer far = new Farmer();
		FarmerDAO fdao = new FarmerDAO();
		far.setFarmerName("Farmer 1");
		far.setFarmerMobile("7410258963");
		far.setEmailId("f1@gmail.com");
		far.setLoginId("Far123");
		far.setPassword("1234");
		int x = fdao.register(far);
		if(x == 0) return "Unsuccesful";
		return "Success";
	}
	
//	@Path("AddVehicle")
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public String addVehicle(){
//		Vehicle v = new Vehicle();
//		v.setVehicleNo("TS0258");
//		v.setOccupiedArea(123);
//		v.setPricePerKm(20.0f);
//		v.setVehicleStatus("Booked");
//		v.setVehicleType("Truck");
//		v.setDriver(null);
//		return null;
//	}
	@Path("getallvehicle")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Vehicle> getAllFarm(){
		VehicleDAO vehDao = new VehicleDAO();
		List<Vehicle> vehList = vehDao.getAllVehicle();	
		return vehList;
	}
	
	
	
	@Path("addAnswer")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String addAns(){
		Answer q = new Answer();
		q.setQuestion(new QuestionDAO().getQuestion(1));
		q.setFarmer(new FarmerDAO().getFarmer(1));
		q.setSuggestion("Dont know");
		AnswerDAO ansdao = new AnswerDAO();
		int x = ansdao.addAnswer(q);
		if (x == 0) return "UNSUCCESS";
		return "SUCCESS";
		
	}
	 @Path("getBookingsById/{farmerId}")
	    @GET
	    @Produces(MediaType.APPLICATION_JSON)
	    public List<Booking> getBookingsById(@PathParam("farmerId") String farmerId){
	    	System.out.println("Recieved is : " + farmerId);
	    	int farmId = Integer.parseInt(farmerId);
	    	List<Booking> bookList =  new BookingDAO().getBookings(farmId);
	    	System.out.println(bookList);
	    	return bookList;
	    }
	 @Path("addq")
	 @GET
	 @Produces(MediaType.TEXT_PLAIN)
	 public String addQuestion(){
		 Question q = new Question();
	
		 q.setQuery("What are organic pesticides");
		 q.setFarmer(new FarmerDAO().getFarmer(2));
		 QuestionDAO qdao = new QuestionDAO();
		 int x = qdao.addQuestion(q);
		 if(x == 0) return "Not accepted";
		 return "Success";
	 }
	 @Path("addanswer")
	 @GET
	 @Produces(MediaType.TEXT_PLAIN)
	 public String addAnswer(){
		 Answer ans = new Answer();
		 ans.setFarmer(new FarmerDAO().getFarmer(4));
		 ans.setSuggestion("Organic fertilizers are fertilizers derived from animal matter, animal excreta, human excreta, and vegetable matter. Naturally occurring organic fertilizers include animal wastes from meat processing, peat, manure, slurry, and guano.");
		 AnswerDAO ansDao = new AnswerDAO();
		 int x = ansDao.addAnswer(ans);
		 if(x == 0) return "Not accepted";
		 return "Success";
	 }

}
