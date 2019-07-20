package com.hibernate.demo;

import com.hibernate.entity.Instructor;
import com.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorDemo {
    public static void main(String[] args) {

        // Create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        // use session object to save Java object
        try {
            session.beginTransaction();

            // Get the instructor detail object
            InstructorDetail instructorDetail = session.get(InstructorDetail.class, 70);
            // Print the instructor detail
            System.out.println("InstructorDetail: " + instructorDetail);

            // Print the associated instructor
            System.out.println("Instructor: " + instructorDetail.getInstructor());
            session.getTransaction().commit();
        } catch (Exception err) {
            err.printStackTrace();
        }
        finally {
            session.close();
            factory.close();
        }
    }
}
