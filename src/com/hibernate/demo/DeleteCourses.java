package com.hibernate.demo;

import com.hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourses {
    public static void main(String[] args) {

        // Create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        // use session object to save Java object
        try {
            session.beginTransaction();

            // get the course
            Course course = session.get(Course.class, 10);
            System.out.println("Deleteing course: " + course);
            session.delete(course);

            // Delete the course

            session.getTransaction().commit();
        }
        finally {
            session.close();
            factory.close();
        }
    }
}
