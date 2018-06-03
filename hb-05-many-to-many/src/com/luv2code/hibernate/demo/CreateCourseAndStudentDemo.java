package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateCourseAndStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
				
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start transaction
			session.beginTransaction();
			
			// Create course
			Course course1 = new Course("C# Basic");
			
			// save the course
			System.out.println("Saving the course: " + course1);
			session.save(course1);		
			
			// create students
			Student student1 = new Student("Vu1", "Huynh1", "huynhminhvu1@gmail.com");
			Student student2 = new Student("Vu2", "Huynh2", "huynhminhvu2@gmail.com");
			Student student3 = new Student("Vu3", "Huynh3", "huynhminhvu3@gmail.com");
			
			course1.addStudent(student1);
			course1.addStudent(student2);
			course1.addStudent(student3);
			// save the student
			System.out.println("Saving student: " + course1.getStudents());
			session.save(student1);
			session.save(student2);
			session.save(student3);
			
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			session.close();
			factory.close();
		}

	}

}
