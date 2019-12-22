package com.gk1.create;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gk.repository.Course;
import com.gk.repository.Student;

public class CreateCourseAndStudentDemo {

	public static void main(String[] args) {

		// Step 1: Create a SessionFactory.
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Course.class)
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();

		// Step 2: Create Session.
		Session session=factory.getCurrentSession();

		try {

			// Start Transaction
			session.beginTransaction();

			// Create a course
			Course tempCourse = new Course("How to Score One Million 1 day");
			
			// Saving the Course
			System.out.println("\n Saving the Course..");
			session.save(tempCourse);
			System.out.println("\n Course Saved: "+tempCourse);
			
			// Create the students
			Student std=new Student("Gokul", "Gopi", "Gk@infy.com");
			Student std1=new Student("Gokul1", "Gopi1", "Gk1@infy.com");
			
			// Add Student to the course
			tempCourse.addStudentInCourse(std);
			tempCourse.addStudentInCourse(std1);
			
			// Save the Students
			System.out.println("\n Saving the Students..");
			session.save(std);
			session.save(std1);
			
			System.out.println("The Students added to the Course: "+tempCourse.getStudentsList());
			
			// Commit transaction
			session.getTransaction().commit();

			System.out.println("Done");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}

	}

}
