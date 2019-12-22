package com.gk1.create;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gk.repository.Course;
import com.gk.repository.Student;


public class CreateCoursesForStudent {

	public static void main(String[] args) {

		// Step 1: Create a SessionFactory.
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Course.class)
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();

		// Step 2: Create Session.
		Session session=factory.getCurrentSession();

		try {

			// Start Transaction
			session.beginTransaction();

			// Get the student Gokul from database
			int theStudentId=1;
			Student theStudent=session.get(Student.class, theStudentId);
			
	//		System.out.println("The Loaded Student Course: "+theStudent.getCourses());
			
			// Create  a more courses
			Course theCourses1=new Course("Java Course 100");
			Course theCourses2=new Course("Python Course 200");
			Course theCourses3=new Course("JSP & Servlet Course 300");
			
			
			// Add Student to the courses	
			theCourses1.addStudentInCourse(theStudent);
			theCourses2.addStudentInCourse(theStudent);
			theCourses3.addStudentInCourse(theStudent);
			
			// save the courses.
			System.out.println("\n Saving the Student and Courses...");
			session.save(theCourses1);
			session.save(theCourses2);
			session.save(theCourses3);
			
			
			// Commit transaction
			session.getTransaction().commit();
			session.close();

			System.out.println("Done");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}

	}

}
