package com.gk2.retrieve;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gk.repository.Student;

public class RetrieveStudentDemo {
	
	public static void main(String[] args) {

		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			
			// Saving the Object then retrieving the same object.
			System.out.println("Create a Student Object");
			Student tempStudent = new Student("Rahul-19", "Gopi-19", "Rk@gmail.com");
			// Start Transaction
			session.beginTransaction();
			// Save student object
			System.out.println("The Student Object is Saving to DB is: " + tempStudent);
			session.save(tempStudent);
			// Commit transaction
			session.getTransaction().commit();
			System.out.println("Transaction Done to Save the Object");

			System.out.println("**************************************************************************************************");
			
			// 1. By using Primary Key New Code to Retrieve
			// Find out the Student id primary key.
			System.out.println("Saved Student Generate ID:" + tempStudent.getId());
			// Now get a new session and Transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			// Retrieve the student 
			Student myStudent = session.get(Student.class, tempStudent.getId()); // Without Transaction Object we cant perfrom session.get Operations.
			System.out.println("Retrival Complete: " + myStudent.getFirstName() + " " + myStudent.getLastName() + " "
					+ myStudent.getEmail());
			// Commit transaction
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
		}
	}
}
