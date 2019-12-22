package com.gk2.get;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gk.repository.Course;
import com.gk.repository.Student;

public class GetCoursesForGokul1Demo {

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
			
			System.out.println("The Loaded Student Course: "+theStudent.getCoursesList());
			
			
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
