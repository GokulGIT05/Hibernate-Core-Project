package com.gk2.read;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.gk.repository.Course;
import com.gk.repository.Instructor;

public class TestHQLFetch {

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
			
			// Option 2: Hibernate 	Query with HQL to resolve Lazy fetch Exception.	
			// Get the instructor from db
			int id=1;
			// It will load instructor and courses all at once
			 Query<Instructor> query=session.createQuery("select i from Instructor i "
					 										+"JOIN FETCH i.theCourse where i.id=:theInstructorId",
					 										Instructor.class);
			 
			 
			 // Set parameter on query
			 query.setParameter("theInstructorId", id);
			 
			 // Execute query and get instructor all at once
			 Instructor theInstructor=query.getSingleResult();
			
			System.out.println("luv2code: Instructor: "+theInstructor);
			
			
			session.getTransaction().commit();
			
			// Close the session
			session.close();
			System.out.println("\n\nThe Session is Closed Now\n\n");
			// After session is close we can able to access the courses.
			System.out.println("luv2code: Instructor Courses: "+theInstructor.getTheCourse());
			System.out.println("luv2code: Done");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		//	session.close();
			factory.close();
		}

	}

}
