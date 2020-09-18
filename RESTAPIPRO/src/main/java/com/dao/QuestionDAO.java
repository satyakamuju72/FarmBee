package com.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.jvnet.hk2.annotations.Service;

import com.db.HibernateTemplate;
import com.dto.Answer;
import com.dto.Farmer;
import com.dto.Question;

@Service
public class QuestionDAO {
	public int addQuestion(Question question){
		return HibernateTemplate.addObject(question);
	}
	
	public List<Question> getAllQuestions(){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		List<Question> queList = session.createQuery("from Question").list();
		
		return queList;
	
	}
	public Question getQuestion(int questionId) {
		return (Question)HibernateTemplate.getObject(Question.class,questionId);
	}
	
	public void deleteQuestion(int questionId){
		HibernateTemplate.deleteObject(Question.class, questionId);
	}
	public void updateQuestion(Question question){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		session.saveOrUpdate(question);
		Transaction tx = session.beginTransaction();
		tx.commit(); //permanent save
		
	}
	public List<String> getTitles(){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Query q1 = session.createQuery("select distinct q.title from Question q");
		System.out.println(q1.list());
		List<String> res = q1.list();
		
		return res;
		
	}
	public List<Question> getAllQuestionsByFarmerId(int farmerId){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Query q1 = session.createQuery("from Question q where q.farmer.farmerId=:farmerId");
		q1.setParameter("farmerId", farmerId);
		List<Question> queList = q1.list();
		
		return queList;
	
	}
	
	public List<Question> getQuestionsByTitle(String title){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Query q1 = session.createQuery("from Question q where q.title=:title");
		q1.setParameter("title", title);
		List<Question> queList = q1.list();
		
		return queList;
	
	}

}
