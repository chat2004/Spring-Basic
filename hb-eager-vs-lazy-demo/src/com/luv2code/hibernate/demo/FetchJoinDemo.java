package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
				
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start transaction
			session.beginTransaction();
			
			// option 2: Hibernate query with HQL
			// get instructor from db
			int theId = 1;
			Query<Instructor> query = session.createQuery("select i from Instructor i "
					+ "JOIN FETCH i.courses "
					+ "where i.id=:theInstructorId", 
					Instructor.class);
			
			// set parameter on query
			query.setParameter("theInstructorId", theId);
			
			// execute the query
			Instructor tmpInstructor = query.getSingleResult();
			
			System.out.println("The instructor: " + tmpInstructor);
			
			// transaction commit
			session.getTransaction().commit();
						
			// close the session
			System.out.println("session is now closed");
			session.close();
			
			// this should fail because of lazy loading
			//get course of instructor
			System.out.println("Courses of instructor: " + tmpInstructor.getCourses());
			
			
			System.out.println("Done!");
		}
		
		finally {
			session.close();
			factory.close();
		}

	}

}
