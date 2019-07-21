package com.hibernate.demo;

import com.hibernate.entity.Course;
import com.hibernate.entity.Instructor;
import com.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class EagerLazyDemo {
    public static void main(String[] args) {

        // Create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            // Option 1: Call getter method before session is closed.
//            Instructor tempInstructor = session.get(Instructor.class, 1);
//            System.out.println("Printing Instructor: "+ tempInstructor);
//            System.out.println("Printing Courses: " + tempInstructor.getCourses());

            // Option 2: Use HQL
            Query<Instructor> query = session.createQuery("select i from Instructor i JOIN FETCH i.courses where i.id=:theInstructorId", Instructor.class);

            // Set parameter on query
            query.setParameter("theInstructorId", 1);

            // Execute query
            Instructor tempInstructor = query.getSingleResult();
            session.getTransaction().commit();
            System.out.println("Instructor: " + tempInstructor);

            // Close the session
            session.close();
            System.out.println("Printing Courses: " + tempInstructor.getCourses());

        }
        finally {
            session.close();
            factory.close();
        }
    }
}
