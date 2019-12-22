package com.gk1.create;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gk.repository.Course;
import com.gk.repository.Review;


public class CreateCourseAndReviewDemo {

	public static void main(String[] args) {

		// Step 1: Create a SessionFactory.
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Course.class)
									.addAnnotatedClass(Review.class)
									.buildSessionFactory();

		// Step 2: Create Session.
		Session session=factory.getCurrentSession();

		try {

			// Start Transaction
			session.beginTransaction();

			// Create a course
			Course tempCourse = new Course("How to Score One Million 12");

			// Add Some Reviews
			tempCourse.addReveiws(new Review("Great 01!!!"));
			tempCourse.addReveiws(new Review("Great 11 !!!"));
			tempCourse.addReveiws(new Review("Great 21 !!!"));
			tempCourse.addReveiws(new Review("Great 31 !!!"));
			tempCourse.addReveiws(new Review("Great 41 !!!"));

			// Save the Course: check the cascade
			System.out.println("Saving the Course... ");
			System.out.println(tempCourse.getReviews());
			session.save(tempCourse);

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
