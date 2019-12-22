package com.gk1.create;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gk.repository.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {

		// Step 1: Create a SessionFactory.
		// This is optional. If we got given then hibernate will look hibernate.cfg.xml in class path.
		// This is default name.
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml") 
													.addAnnotatedClass(Student.class)
													.buildSessionFactory();

		// Step 2: Create Session.
		Session session = factory.getCurrentSession();

		try {
			// Step 3: Use Session object to save the object
			System.out.println("Create a Student Object");

			// Create a student object
			Student tempStudent = new Student("Rahul-19", "Gopi", "gk@gmail.com");
			Student tempStudent1=new Student("Rahul-19", "Gopi1", "gk1@gmail.com");
			Student tempStudent2=new Student("Rahul-19", "Gopi2", "gk2@gmail.com");

			// Start Transaction
			session.beginTransaction();

			// Save student object
			System.out.println("Saving Student");
			session.save(tempStudent);
			session.save(tempStudent1);
			session.save(tempStudent2);

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
