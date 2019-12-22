package com.gk4.delete;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gk.repository.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {

		// Step 1: Create a SessionFactory.
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		// Step 2: Create Session.
		Session session = factory.getCurrentSession();

		try {
			
			int studentId=3;
			// Start Transaction
			session.beginTransaction();
			System.out.println("Deleting Student");
			//Retrieving Student
			Student theStudent=session.get(Student.class, studentId);
			System.out.println("The Student: "+theStudent);
			
			//Delete the Student: Approach 1
			System.out.println("Approach 1 : Delting the Student...");
			session.delete(theStudent);
			System.out.println("Done");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
		}

	} 	

}
