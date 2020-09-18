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
import com.dto.Question;
import com.dto.Vehicle;

@Service
public class VehicleDAO {
	public int register(Vehicle vehicle) {
		return HibernateTemplate.addObject(vehicle);
	}
	public Vehicle getVehicle(String vehicleNo){
		return (Vehicle)HibernateTemplate.getObject(Vehicle.class,vehicleNo);
	}
	public List<Vehicle> getAllVehicle() {
		List<Vehicle> vehicles=(List)HibernateTemplate.getObjectListByQuery("From Vehicle");
		return vehicles;	
	}
	public void updateVehicle(Vehicle vehicle){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		session.saveOrUpdate(vehicle);
		Transaction tx = session.beginTransaction();
		tx.commit(); //permanent save
		session.close();
	}
	public void deleteVehicle(String vehicleNumber){
		HibernateTemplate.deleteObject(Vehicle.class, vehicleNumber);
	}
	
	public List<String> getVehicleNo(){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Query q1 = session.createQuery("select q.vehicleNo from Vehicle q");
		System.out.println(q1.list());
		List<String> res = q1.list();
		session.close();
		return res;
		
	}
}
