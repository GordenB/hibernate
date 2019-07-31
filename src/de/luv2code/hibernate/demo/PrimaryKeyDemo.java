package de.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import de.luv2code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// create 3 new students
			System.out.println("Creating 3 new student objects...");
			Student tempStudent1 = new Student("Hanna", "Ming","Hannibunny@freenet.de");
			Student tempStudent2 = new Student("Gregor", "Fitz","hartWieBeton@muscle.org");
			Student tempStudent3 = new Student("Aley", "Wall","WaAley@aol.com");
			
			// start a transaction
			session.beginTransaction();
			
			// save the student object
			System.out.println("Saving the students...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);
			
			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
		}finally{
			session.close();
		}
	}

}
