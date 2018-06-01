package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;
import java.util.List;

public class ReadStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
				
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// start the transaction
			session.beginTransaction();
			
			// query students
			List<Student> theStudents = session.createQuery("from Student").list();
			
			// display student
			displayStudents(theStudents);
			
			// query students: lastName='Huynh'
			theStudents = session.createQuery("from Student s where s.lastName='Huynh'").list();
			System.out.println("Students who has last name of Huynh: ");
			
			displayStudents(theStudents);
			
			// commit transaction
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

	private static void displayStudents(List<Student> theStudents) {
		for (Student tmpStudent : theStudents) {
			System.out.println(tmpStudent);
		}
	}

}
