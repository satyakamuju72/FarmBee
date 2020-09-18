package com.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@XmlRootElement
public class Farmer {
	@Id@GeneratedValue
	private int farmerId;
	private String farmerName;
	private String farmerMobile;
	private String emailId;
	private String loginId;
	private String password;


	@OneToMany (mappedBy = "farmer", fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	List<Answer> ansList = new ArrayList<Answer>();
	
	@OneToMany (mappedBy = "farmer", fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	List<Question> queList = new ArrayList<Question>();
	
	@OneToMany (mappedBy = "farmer", fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)

	List<Booking> bookList = new ArrayList<Booking>();

	public int getFarmerId() {
		return farmerId;
	}

	public void setFarmerId(int farmerId) {
		this.farmerId = farmerId;
	}

	public String getFarmerName() {
		return farmerName;
	}

	public void setFarmerName(String farmerName) {
		this.farmerName = farmerName;
	}

	public String getFarmerMobile() {
		return farmerMobile;
	}

	public void setFarmerMobile(String farmerMobile) {
		this.farmerMobile = farmerMobile;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
//	public List<Answer> getAnsList() {
//		return ansList;
//	}
//
//	public void setAnsList(List<Answer> ansList) {
//		this.ansList = ansList;
//	}

//	public List<Question> getQueList() {
//		return queList;
//	}

//	public void setQueList(List<Question> queList) {
//		this.queList = queList;
//	}

//	public List<Booking> getBookList() {
//		return bookList;
//	}

	public void setBookList(List<Booking> bookList) {
		this.bookList = bookList;
	}

	@Override
	public String toString() {
		return "Farmer [farmerId=" + farmerId + ", farmerName=" + farmerName + ", farmerMobile=" + farmerMobile
				+ ", emailId=" + emailId + ", loginId=" + loginId + ", password=" + password + "]";
	}

	
	

	
}
