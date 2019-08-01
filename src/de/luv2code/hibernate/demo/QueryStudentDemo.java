package de.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import de.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();
		try {
			
			// start a transaction
			session.beginTransaction();
			
			// query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			// display the students
			displayStudents(theStudents);
			
			// query students: lastName='Duck'
			theStudents = session.createQuery("from Student s where s.lastName='Duck'").getResultList();
			
			// display students
			System.out.println("\n\nStudents who have last name of Duck");
			displayStudents(theStudents);
			
			// query students: lastName='Duck' OR firstName='Paul'
			theStudents =
						session.createQuery("from Student s where"
						+ " s.lastName='Duck' OR s.firstName='Paul'").getResultList();
			
			System.out.println("\n\nStudents who have last name of Duck OR first name Paul");
			displayStudents(theStudents);
			
			// query students where email LIKE '%luv2code.de'
			theStudents = session.createQuery("from Student s where"
										+ " s.email LIKE '%luv2code.de'").getResultList();
			
			System.out.println("\n\nStudents whose have email ends with luv2code.de");
			displayStudents(theStudents);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}finally{
			session.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
