package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.jvnet.hk2.annotations.Service;

import com.db.HibernateTemplate;
import com.dto.Booking;
import com.dto.Question;

@Service
public class BookingDAO {
	public int addBookingData(Booking booking){
		return HibernateTemplate.addObject(booking);
	}
	public List<Booking> getBookings(int farmerId){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Query q1 = session.createQuery("from Booking b where b.farmer.farmerId=:id");
		q1.setParameter("id", farmerId);
		List<Booking> bookList = q1.list();
		System.out.println(bookList);
		session.close();
		return bookList;	
	}
	public void updateBooking(Booking booking){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		session.saveOrUpdate(booking);
		Transaction tx = session.beginTransaction();
		tx.commit(); //permanent save
		session.close();
	}
	public List<Booking> getSingleBookings(){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Query q1 = session.createQuery("from Booking b where b.pool=:status and b.bookingStatus=:pool");
		q1.setParameter("status", "single");
		q1.setParameter("pool", "pending");
		List<Booking> bookList = q1.list();
		System.out.println(bookList);
		session.close();
		return bookList;	
	}
	public List<Booking> getPoolingBookings(){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Query q1 = session.createQuery("from Booking b where b.pool=:status and b.bookingStatus=:pool order by b.wantDate");
		q1.setParameter("status", "pooling");
		q1.setParameter("pool", "pending");
		List<Booking> bookList = q1.list();
		System.out.println(bookList);
		session.close();
		return bookList;	
	}
	public void deleteBooking(int bookingId){
		HibernateTemplate.deleteObject(Booking.class, bookingId);
	}
	public List<Booking> getBookingsByStatus(String status){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Query q1 = session.createQuery("from Booking b where b.bookingStatus=:status");
		q1.setParameter("status", status);
		List<Booking> bookList = q1.list();
		System.out.println(bookList);
		session.close();
		return bookList;	
	}
	
}
