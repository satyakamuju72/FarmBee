package com.db;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.mindrot.jbcrypt.BCrypt;

import com.dto.Farmer;
import com.dto.Vehicle;


public class HibernateTemplate {

	private static SessionFactory sessionFactory;
	
	static {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	
	public static int addObject(Object obj){
		System.out.println("Inside Template...");
		int result=0;
		Transaction tx=null;
		try {
			Session session=sessionFactory.openSession();
			tx=session.beginTransaction();
			
			session.save(obj);
			
			tx.commit();
			
			result=1;
			
		} catch (Exception e) {
		
			tx.rollback();
			
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static Object getObject(Class c,Serializable serializable)
	{
		Object obj=null;
		
		try {			
			return sessionFactory.openSession().get(c,serializable);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return obj;
	}
	
	public static Object getObjectByUserPass(String loginId,String password) {
	
	/*String queryString = "from Farmer where loginId = :loginId and password =:password";
	
	  Query query = sessionFactory.openSession().createQuery(queryString);
	  query.setString("loginId", loginId);
	  query.setString("password", password);
	  Object queryResult = query.uniqueResult();
	  Farmer farmer = (Farmer)queryResult;
	  return farmer; */
		String queryString = "from Farmer where loginId = :loginId";
		  Query query = sessionFactory.openSession().createQuery(queryString);
		  query.setString("loginId", loginId);
		  Object queryResult = query.uniqueResult();
		  Farmer farmer = (Farmer)queryResult;
		  String hashedPassword = farmer.getPassword();
		if (BCrypt.checkpw(password, hashedPassword)){
			System.out.println("The password matches.");
		}else{
			System.out.println("The password does not match.");
			farmer = null;
	}
		return farmer;
	}
	public static Object getObjectByPhone(String email, Session session) {
		String queryString = "from Farmer where FarmerMobile = :email";
		  Query query = session.createQuery(queryString);
		  query.setString("email", email);
		  Object queryResult = query.uniqueResult();
		  Farmer farmer = (Farmer)queryResult;
		  return farmer; 
		}
	
	public static List<Object> getObjectListByQuery(String query)
	{
		return sessionFactory.openSession().createQuery(query).list();
	}
	
	public static int updateObject(Object obj)
	{
		int result=0;
		
		Transaction tx=null;
		
		try {
			
			Session session=sessionFactory.openSession();
			tx=session.beginTransaction();
			
			session.saveOrUpdate(obj);
			
			tx.commit();
			
			result=1;
			
		} catch (Exception e) {
		
			tx.rollback();
			
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static int deleteObject(Class c,Serializable serializable)
	{
		int result=0;
		
		Session session=sessionFactory.openSession();
		
		Transaction tx=session.beginTransaction();
		
		try {
			
			Object obj=session.get(c,serializable);
			
			session.delete(obj);
			
			tx.commit();
			
			result=1;
						
		} catch (Exception e) {
			
			e.printStackTrace();
			
			tx.rollback();
		}
		
		return result;
	}

	public static List<Object> getObjectListByName(Class c, String columName, String value) {
		Session session=sessionFactory.openSession();
		  Criteria criteria = session.createCriteria(c);
			Criterion nameCriterion = Restrictions.eq(columName, value);
			criteria.add(nameCriterion);
			return criteria.list();
	}
	
}
