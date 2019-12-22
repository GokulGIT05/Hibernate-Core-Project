package com.gk5.hql;

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
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			// Display students
			displayStudents(theStudents);
			
			// Query Students: lastname = gopi
			theStudents=session.createQuery("from Student s where s.lastName ='Gopi'").getResultList();
			System.out.println("\n\nLast Name: Gopi");
			displayStudents(theStudents);

			// Query Students lastname: gopi1 and where firstname Gokul1
			theStudents=session.createQuery("from Student s where s.firstName='Gokul1' OR s.lastName='Gopi3'").getResultList();
			System.out.println("\n\nLast name: gopi1 and where first name Gokul1");
			displayStudents(theStudents);
			
			
			// Query Students: emails ends with %1@gmail.com
			theStudents=session.createQuery("from Student s where s.email LIKE '%1@gmail.com'").getResultList();
			System.out.println("\n\nQuery Students: emails ends with %1@gmail.com");
			displayStudents(theStudents);
			
			// Commit Transaction
			session.getTransaction().commit();

			System.out.println("\n\nDone");

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
