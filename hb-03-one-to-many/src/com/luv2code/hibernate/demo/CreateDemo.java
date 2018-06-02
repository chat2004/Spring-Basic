package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
				
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// create the object
			Instructor tmpInstructor = new Instructor("Vu1","Huynh1","huynhminhvu1@gmail.com");
			InstructorDetail tmpDetail = new InstructorDetail("Vu Huynh 1", "sdflskjf film");
			
			// associate objects
			tmpInstructor.setInstructorDetail(tmpDetail);
			
			// start the transaction
			session.beginTransaction();
			
			// save the objects, also save the detail object
			System.out.println("Saving the instructor: " + tmpInstructor);
			session.save(tmpInstructor);
			
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		finally {
			factory.close();
		}

	}

}
