package com.dto;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
@ManagedBean  
@Entity
@XmlRootElement
public class Question {
	@Id@GeneratedValue
	private int questionId;
	private String title;
	private String query;
	
	@OneToMany (mappedBy = "question", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	List<Answer> answerList = new ArrayList<Answer>();
	
	@ManyToOne
	@JoinColumn(name="farmerId")
	Farmer farmer;

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public List<Answer> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<Answer> answerList) {
		this.answerList = answerList;
	}

	public Farmer getFarmer() {
		return farmer;
	}

	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}

	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", query=" + query + ", answerList=" + answerList + ", farmer="
				+ farmer + "]";
	}
	
	
}
