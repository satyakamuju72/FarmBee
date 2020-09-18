package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.jvnet.hk2.annotations.Service;

import com.db.HibernateTemplate;
import com.dto.Answer;
import com.dto.Farmer;
import com.dto.Vehicle;

@Service
public class AnswerDAO {
	public int addAnswer(Answer answer){
		return HibernateTemplate.addObject(answer);
	}
	public List<Answer> getAnsByQId(int questionId){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Query q1 = session.createQuery("from Answer a where a.question.questionId=:questionId");
		q1.setParameter("questionId", questionId);
		List<Answer> ansList = q1.list();
		session.close();
		return ansList;
	}
	public Answer getAnswerById(int answerId){
		return (Answer)HibernateTemplate.getObject(Answer.class,answerId);
	}
	public void deleteAns(Answer answer){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		session.delete(answer); //insert query
		Transaction tx = session.beginTransaction();
		tx.commit(); //permanent save
		session.close();
	}
	public void updateAns(Answer answer){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		session.saveOrUpdate(answer);
		Transaction tx = session.beginTransaction();
		tx.commit(); //permanent save
		session.close();
	}
	public void deleteEmp(Answer answer) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		session.delete(answer); //insert query
		Transaction tx = session.beginTransaction();
		tx.commit(); //permanent save
		session.close();
	}
}
