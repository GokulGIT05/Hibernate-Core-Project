package com.gk3.update;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gk.repository.Student;


public class UpdateStudentDemo {

	public static void main(String[] args) {

		// Step 1: Create a SessionFactory.
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();

		// Step 2: Create Session.
		Session session = factory.getCurrentSession();

		try {

			int studentId = 3;
			// Start Transaction
			session.beginTransaction();

			System.out.println("Updating Student");
			// Retrieving Student by Id
			Student theStudent = session.get(Student.class, studentId);
			System.out.println("The Student: " + theStudent);

			// Updating Student
			theStudent.setEmail("gokul456@infosys.com"); // After commit only this will reflect in database.

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
