package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class AddCoursesForStudentDemo {

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
			
			// get student from db
			int studentId = 1;
			Student tmpStudent = session.get(Student.class, studentId);
			
			System.out.println("The student: " + tmpStudent);
			System.out.println("Courses of student: " + tmpStudent.getCourses());
			
			// Create the course
			Course tmpCourse1 = new Course("Java Basic");
			Course tmpCourse2 = new Course("C++ Basic");
			
			// Add student to course
			tmpCourse1.addStudent(tmpStudent);
			tmpCourse2.addStudent(tmpStudent);
			
			// save the courses
			System.out.println("Saving the courses");
			session.save(tmpCourse1);
			session.save(tmpCourse2);
			
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			session.close();
			factory.close();
		}

	}

}
