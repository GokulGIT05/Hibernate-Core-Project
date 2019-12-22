package com.gk2.get;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gk.repository.Course;
import com.gk.repository.Review;


public class GetCourseAndReviewDemo {

	public static void main(String[] args) {

		// Step 1: Create a SessionFactory.
		SessionFactory factory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Course.class)
										.addAnnotatedClass(Review.class)
										.buildSessionFactory();

		// Step 2: Create Session.
		Session session = factory.getCurrentSession();

		try {

			// Start Transaction
			session.beginTransaction();

			// Get the Course
			int theId = 11;
			Course theCourse = session.get(Course.class, theId);

			// Print the course
			System.out.println("The Course Details: " + theCourse);

			// Print the course reviews
			System.out.println("The Course Reviews: " + theCourse.getReviews());

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
