package com.gk3.delete;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gk.repository.Course;
import com.gk.repository.Instructor;


public class DeleteCoursesDemo {

	public static void main(String[] args) {

		// Step 1: Create a SessionFactory.
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(Course.class)
									.buildSessionFactory();

		// Step 2: Create Session.
		Session session = factory.getCurrentSession();

		try {
			// Start Transaction
			session.beginTransaction();

			// Get a course
			int theId = 11;
			System.out.println("The ID: " + theId);
			Course tempCourse = session.get(Course.class, theId);
			// Delete a course
			System.out.println("The Course to be deleted: " + tempCourse);
			session.delete(tempCourse);

			// Commit transaction
			session.getTransaction().commit();
			System.out.println("Done");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}

	}

}
