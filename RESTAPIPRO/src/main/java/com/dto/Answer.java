package com.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;
@Entity
@XmlRootElement
public class Answer {
	@Id@GeneratedValue
	private int answerId;
	private String suggestion;
	@ManyToOne
	@JoinColumn(name="questionId")
	Question question;
	@ManyToOne
	@JoinColumn(name="farmerId")
	Farmer farmer;
	public String getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
//	public Question getQuestion() {
//		return question;
//	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public Farmer getFarmer() {
		return farmer;
	}
	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}
	@Override
	public String toString() {
		return "Answer [suggestion=" + suggestion + ", question=" + question + ", farmer=" + farmer + "]";
	}
	public int getAnswerId() {
		return answerId;
	}
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}
}
