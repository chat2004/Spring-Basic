package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
				
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// Create 3 students object
			System.out.println("Create 3 students");
			Student tmpStudent1 = new Student("Vu1", "Huynh1", "huynhminhvu1@gmail.com");
			Student tmpStudent2 = new Student("Vu2", "Huynh2", "huynhminhvu2@gmail.com");
			Student tmpStudent3 = new Student("Vu3", "Huynh3", "huynhminhvu3@gmail.com");
			
			// start the transaction
			session.beginTransaction();
			// save student
			System.out.println("Saving the student");
			session.save(tmpStudent1);
			session.save(tmpStudent2);
			session.save(tmpStudent3);
			
			System.out.println("Commit session");
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
