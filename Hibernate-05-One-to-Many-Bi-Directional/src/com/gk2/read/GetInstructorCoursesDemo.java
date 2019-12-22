package com.gk2.read;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gk.repository.Course;
import com.gk.repository.Instructor;


public class GetInstructorCoursesDemo {

	public static void main(String[] args) {

		// Step 1: Create a SessionFactory.
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(Course.class)
									.buildSessionFactory();

		// Step 2: Create Session.
		Session session=factory.getCurrentSession();

		try {
			// Start Transaction
			session.beginTransaction();
			
			// Get the instructor from db
			int id=1;
			Instructor theInstructor=session.get(Instructor.class, id);
			System.out.println("Instructor: "+theInstructor);
			
			// Get course for the instructor
			System.out.println("Instructor Courses: "+theInstructor.getTheCourse());
			
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
