package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;
import java.util.List;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
				
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			int studentId = 2;
			// start the transaction
			session.beginTransaction();
			
			// query students's id: primary key
			System.out.println("Getting student with id: " + studentId);
			Student myStudent = session.get(Student.class, studentId);
			
			System.out.println("Updating student...");
			myStudent.setFirstName("Ngoc");
			myStudent.setLastName("Duong");

			// commit transaction
			session.getTransaction().commit();

			// Update many student
			session = factory.getCurrentSession();
			session.beginTransaction();
			System.out.println("update email for all students");
			session.createQuery("update Student set email='test@gmail.com'").executeUpdate();
			
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
