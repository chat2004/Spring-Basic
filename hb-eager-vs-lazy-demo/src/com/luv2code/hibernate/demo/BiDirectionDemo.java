package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class BiDirectionDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
				
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// start the transaction
			session.beginTransaction();
			
			// get the object by id
			int theId = 22;
			
			// get the instructor detail
			InstructorDetail tmpInstructorDetail = session.get(InstructorDetail.class, theId);
			
			System.out.println("The instructor detail: " + tmpInstructorDetail);
			
			// print the instructor
			System.out.println("The instructor: " + tmpInstructorDetail.getInstructor());
		
			
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			session.close();
			factory.close();
		}

	}

}
