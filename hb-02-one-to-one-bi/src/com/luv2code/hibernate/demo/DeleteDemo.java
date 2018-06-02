package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class DeleteDemo {

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
			int theId = 1;
			Instructor tmpInstructor = session.get(Instructor.class, theId);
			System.out.println("The instructor found: " + tmpInstructor);
			
			if (tmpInstructor != null) {
				System.out.println("Deleting instructor: " + tmpInstructor);
				// also delete the details
				session.delete(tmpInstructor);
			}
			
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
