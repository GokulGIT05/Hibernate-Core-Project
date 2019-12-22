package com.gk3.update;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gk.repository.Student;


public class UpdateStudentDemo2 {

	public static void main(String[] args) {

		// Step 1: Create a SessionFactory.
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();

		// Step 2: Create Session.
		Session session = factory.getCurrentSession();

		try {
			// Update Email for all Students Using Create Query
			session = factory.getCurrentSession();
			session.beginTransaction();
			System.out.println("Update Emails for all Students");
			session.createQuery("update Student set email='gk@infosys.com'").executeUpdate();
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
