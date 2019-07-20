package com.hibernate.demo;

import com.hibernate.entity.Instructor;
import com.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {
    public static void main(String[] args) {

        // Create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        // use session object to save Java object
        try {

            // Create the objects
            Instructor tempInstructor = new Instructor("Jonas", "Rawles", "hello@hello2.com");
            InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.youtube.com/hello2", "Coding");

            Instructor tempInstructor2 = new Instructor("Sophie", "Hawes", "hello23@hello2.com");
            InstructorDetail tempInstructorDetail2 = new InstructorDetail("http://www.youtube.com/23o2", "Music");


            // Associate the objects
            tempInstructor.setInstructorDetail(tempInstructorDetail);
            tempInstructor2.setInstructorDetail(tempInstructorDetail2);

            // Start a transaction
            session.beginTransaction();

            // Save the instructor: Because of the CascadeType.ALL, this will also save the details object.
            session.save(tempInstructor);
            session.save(tempInstructor2);

            // Commit transaction
            session.getTransaction().commit();
        }
        finally {
            factory.close();
        }
    }
}
