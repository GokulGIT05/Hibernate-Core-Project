package com.gk2.queryCache;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gk.repository.Student;

public class QueryStudentDemo {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		// Step 1: Create a SessionFactory.
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// Step 2: Create Session.
		Session session = factory.getCurrentSession();

		try {
			// Start Transaction
			session.beginTransaction();
			// Query Student
			List<Student> theStudents =	 session.createQuery("from Student s where s.lastName ='Gopi'")
												.setCacheable(true)
												.getResultList();
			// Display students
			displayStudents(theStudents);
			// Commit Transaction
			session.getTransaction().commit();

			System.out.println("\n *****By Using Second level Query Cache.... \n");

			session = factory.getCurrentSession();
			// Start Transaction
			session.beginTransaction();
			// Query Student
			List<Student> theStudents1 = session.createQuery("from Student s where s.lastName ='Gopi'")
												.setCacheable(true)
												.getResultList();
			// Display students
			displayStudents(theStudents1);
			// Commit Transaction
			session.getTransaction().commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
		}

	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			System.out.println("The Student Details: " + tempStudent);
		}
	}

}
