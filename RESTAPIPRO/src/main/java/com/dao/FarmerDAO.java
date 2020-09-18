package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.jvnet.hk2.annotations.Service;

import com.db.HibernateTemplate;
import com.dto.Farmer;
@Service
public class FarmerDAO {
	public int register(Farmer farmer) {
		return HibernateTemplate.addObject(farmer);
	}
	
	public List<Farmer> getAllFarmers() {
		List<Farmer> farms=(List)HibernateTemplate.getObjectListByQuery("From Farmer");
		return farms;	
	}
	public int update(Farmer farmer){
		return HibernateTemplate.updateObject(farmer);
	}
	public Farmer getFarmer(int farmerId) {
		return (Farmer)HibernateTemplate.getObject(Farmer.class,farmerId);
	}
	public Farmer getFarmerByPhone(String farmerNumber, Session session) {
		return (Farmer)HibernateTemplate.getObjectByPhone(farmerNumber, session);
	}
	public Farmer getFarmerByUserPass(String loginId,String password) {

		return (Farmer)HibernateTemplate.getObjectByUserPass(loginId,password);
	}
	public List<String> getNumbers(){
		Session session = getSession();
		Query q1 = session.createQuery("select f.farmerMobile from Farmer f");
		System.out.println(q1.list());
		List<String> res = q1.list();
		session.close();
		return res;
	}
	public void updatepassword(String number, String password){
		
		Session session = getSession();
		Farmer farmer = new FarmerDAO().getFarmerByPhone(number, session);
		farmer.setPassword(password);
		session.saveOrUpdate(farmer);
		Transaction tx = session.beginTransaction();
		tx.commit(); //permanent save
		session.close();
	}
	public static Session getSession(){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		return session;
	}
	
	
	
}
