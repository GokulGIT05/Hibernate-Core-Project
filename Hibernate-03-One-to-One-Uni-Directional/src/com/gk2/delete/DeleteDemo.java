package com.gk2.delete;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gk.repository.Instructor;
import com.gk.repository.InstructorDetail;

public class DeleteDemo {

	public static void main(String[] args) {

		// Step 1: Create a SessionFactory.
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.buildSessionFactory();

		// Step 2: Create Session.
		Session session = factory.getCurrentSession();

		try {

			// Start Transaction
			session.beginTransaction();

			// Get instructor by primary key
			int theId = 1;

			Instructor theInstrucor = session.get(Instructor.class, theId);
			System.out.println("The Instructor Details: " + theInstrucor);

			// Delete that instructor
			if (theInstrucor != null) {
				System.out.println("Deleting the instructor and associate details: " + theInstrucor);
				// Delete the instructor and its associate instructor details because of
				// cascadeType.All
				session.delete(theInstrucor);
			} else
				System.out.println("No Instructor found");

			// Commit transaction
			session.getTransaction().commit();

			System.out.println("Done");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
		}

	}

}
