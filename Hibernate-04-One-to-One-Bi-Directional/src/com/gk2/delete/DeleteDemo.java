package com.gk2.delete;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gk.repository.Instructor;
import com.gk.repository.InstructorDetail;

public class DeleteDemo {

	public static void main(String[] args) {

		// Step 1: Create a SessionFactory.
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.buildSessionFactory();

		// Step 2: Create Session.
		Session session = factory.getCurrentSession();

		try {

			// Start Transaction
			session.beginTransaction();
			
			/*// Deleting Instructor Details so now Instructor associate with the same Id have to delete.
			int insDetailKey=4;
			InstructorDetail insDetail = session.get(InstructorDetail.class, insDetailKey);
			System.out.println("The instructor Detail: "+insDetail);

			if(insDetail != null) {
				session.delete(insDetail);
			}else {
				System.out.println("No Record Found");
			}*/
			
			/// Deleting Instructor which shared foreign key, , will see what happens,  Many to One kind of.
			Instructor instructor = session.get(Instructor.class, 3);
			System.out.println("The Instructor is : "+instructor);
			
			if(instructor != null) {
				session.delete(instructor);
			}else {
				System.out.println("No Record Found for the given record!!!!");
			}
			// Commit transaction
			session.getTransaction().commit();

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}

	}

}
