package com.gk1.create;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gk.repository.Course;
import com.gk.repository.Instructor;

public class CreateCourseDemo {
	
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
			// Create courses: Line no 35 to 45 is used to create a courses
			Course theCourse1=new Course("Air Guitor123");
			Course theCourse2=new Course("Bike Riding1");
			
			// Add courses to the instructor
			theInstructor.addCourseThorughInstructor(theCourse1);
			theInstructor.addCourseThorughInstructor(theCourse2);
			
			// Save the session 
			session.save(theCourse1);
			session.save(theCourse2);
			
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
