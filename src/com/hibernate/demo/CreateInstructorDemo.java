package com.hibernate.demo;

import com.hibernate.entity.Course;
import com.hibernate.entity.Instructor;
import com.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {
    public static void main(String[] args) {

        // Create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        // use session object to save Java object
        try {

            // Create the objects
            Instructor tempInstructor = new Instructor("Susan", "Wiley", "hello@hello2.com");
            InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.youtube.com/hello2", "Gaming");



            // Associate the objects
            tempInstructor.setInstructorDetail(tempInstructorDetail);

            // Start a transaction
            session.beginTransaction();

            // Save the instructor: Because of the CascadeType.ALL, this will also save the details object.
            session.save(tempInstructor);

            // Commit transaction
            session.getTransaction().commit();
        }
        finally {
            session.close();
            factory.close();
        }
    }
}