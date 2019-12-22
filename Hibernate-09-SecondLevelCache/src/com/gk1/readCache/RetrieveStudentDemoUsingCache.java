package com.gk1.readCache;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gk.repository.Student;

public class RetrieveStudentDemoUsingCache {

	// https://www.boraji.com/hibernate-5-query-cache-entity-cache-and-collection-cache-example
	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {

			int idToTest = 20;
			session = factory.getCurrentSession();
			session.beginTransaction();
			// Retrieve the student
			Student myStudent = session.get(Student.class, idToTest); // Without Transaction Object we cant
																		// perfrom session.get Operations.
			System.out.println("Retrival Complete: " + myStudent.getFirstName() + " " + myStudent.getLastName() + " "
					+ myStudent.getEmail());
			
			Student myStudentFirstLevelCache = session.get(Student.class, idToTest); 
			System.out.println("myStudentFirstLevelCache Complete: " + myStudentFirstLevelCache.getFirstName() + " " + myStudent.getLastName() + " "
					+ myStudentFirstLevelCache.getEmail());
			// Commit transaction
			session.getTransaction().commit();
			session.close();
			System.out.println("*********************************************\n");

			// Check by Commenting @Cache in Student entity.
			session = factory.getCurrentSession();
			session.beginTransaction();
			// Retrieve the student
			Student myStudent1 = session.get(Student.class, idToTest);
			System.out.println("Retrival Complete Using Second Level Cache: " + myStudent1.getFirstName() + " " + myStudent1.getLastName() + " "
					+ myStudent1.getEmail());
			// Commit transaction
			session.getTransaction().commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
		}
	}
}
