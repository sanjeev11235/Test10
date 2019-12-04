package com.san.hibernate.demo;

import java.text.ParseException;

import javax.persistence.CascadeType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.san.hibernate.demo.entity.Instructor;
import com.san.hibernate.demo.entity.InstructorDetail;

public class CreateDemo {

	public static void main(String[] args) throws ParseException {
		
		// create session factory 
		SessionFactory factory = new Configuration()
												.configure("hibernate.cfg.xml")
												.addAnnotatedClass(Instructor.class)
												.addAnnotatedClass(InstructorDetail.class)
												.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// create the objects
			
//			  Instructor tempInstructor = new Instructor("Chad", "Darby", "darby@luv2code.com");
//			  
//			  InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.luv2code.com/youtube", "luv2code !!");
			 
			
			Instructor tempInstructor = new Instructor("Madhu", "Patel", "Madhu@luv2code.com");
			
			InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.youtube.com", "Guitar");
			
			// associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			// start a transaction
			session.beginTransaction();
			
			// save the instructor
			/* Note: this will also save the details object because of CascadeType.ALL */
			System.out.println("Saving instructor: "+tempInstructor);
			session.save(tempInstructor);
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done! ");
		}
		finally {
			factory.close();
		}

	}

}
