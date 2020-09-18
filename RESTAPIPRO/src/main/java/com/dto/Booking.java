package com.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@XmlRootElement
public class Booking {
	@Id@GeneratedValue
	private int bookingId;
	private String source;
	private String destination;
	private String cropType;
	private float loadSize;
	private String pool;
	private String bookingStatus; 
	private float price;
	private java.util.Date wantDate;

	
	@ManyToOne
	@JoinColumn(name="farmerId")
	Farmer farmer;
	@ManyToOne
	@JoinColumn(name="vehicleNo")
	Vehicle vehicle;
	
	
	public java.util.Date getWantDate() {
		return wantDate;
	}
	public void setWantDate(java.util.Date date) {
		this.wantDate = date;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getCropType() {
		return cropType;
	}
	public void setCropType(String cropType) {
		this.cropType = cropType;
	}
	public float getLoadSize() {
		return loadSize;
	}
	public void setLoadSize(float loadSize) {
		this.loadSize = loadSize;
	}
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Farmer getFarmer() {
		return farmer;
	}
	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", source=" + source + ", destination=" + destination + ", cropType="
				+ cropType + ", loadSize=" + loadSize + ", pool=" + pool + ", bookingStatus=" + bookingStatus
				+ ", price=" + price + "]";
	}
	public String getPool() {
		return pool;
	}
	public void setPool(String pool) {
		this.pool = pool;
	}
	
	
}
