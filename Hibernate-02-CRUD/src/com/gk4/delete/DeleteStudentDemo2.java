package com.gk4.delete;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gk.repository.Student;

public class DeleteStudentDemo2 {

	public static void main(String[] args) {

		// Step 1: Create a SessionFactory.
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		// Step 2: Create Session.
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			
			// Appraoch 2: Delete the Student
			System.out.println("Appraoch 2: Deleting the Student");
			session.createQuery("delete from Student where lastName='Gopi'").executeUpdate();
			
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
