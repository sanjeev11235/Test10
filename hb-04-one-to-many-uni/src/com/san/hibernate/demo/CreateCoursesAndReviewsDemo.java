package com.san.hibernate.demo;

import java.text.ParseException;

import javax.persistence.CascadeType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.san.hibernate.demo.entity.Course;
import com.san.hibernate.demo.entity.Instructor;
import com.san.hibernate.demo.entity.InstructorDetail;
import com.san.hibernate.demo.entity.Review;

public class CreateCoursesAndReviewsDemo {

	public static void main(String[] args) throws ParseException {
		
		// create session factory 
		SessionFactory factory = new Configuration()
												.configure("hibernate.cfg.xml")
												.addAnnotatedClass(Instructor.class)
												.addAnnotatedClass(InstructorDetail.class)
												.addAnnotatedClass(Course.class)
												.addAnnotatedClass(Review.class)
												.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();
			
			// create a course
			Course tempCourse = new Course("pacman - How to score one million points");
			
			// add some reviews
			tempCourse.addReview(new Review("Great course ... loved it!"));
			tempCourse.addReview(new Review("Cool course, job well done"));
			tempCourse.addReview(new Review("What a dumb course, you are an idiot!"));
			
			// save the course .. and leverage the cascade all :)
			System.out.println("Saving the course");
			System.out.println(tempCourse);
			System.out.println(tempCourse.getReviews());
			
			session.save(tempCourse);
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done! ");
		}
		finally {
			session.close();
			factory.close();
			
		}

	}

}
