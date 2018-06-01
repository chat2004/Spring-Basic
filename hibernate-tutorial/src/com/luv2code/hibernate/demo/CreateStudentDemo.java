package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
				
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// Create student
			System.out.println("Create new student");
			Student tmpStudent = new Student("Vu", "Huynh", "huynhminhvu@gmail.com");
			
			// start the transaction
			session.beginTransaction();
			// save student
			System.out.println("Saving the student");
			session.save(tmpStudent);
			
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
