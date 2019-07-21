package com.hibernate.demo;

import com.hibernate.entity.Course;
import com.hibernate.entity.Instructor;
import com.hibernate.entity.InstructorDetail;
import com.hibernate.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourse {
    public static void main(String[] args) {

        // Create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        // use session object to save Java object
        try {
            session.beginTransaction();

            Course course = session.get(Course.class, 10);
            session.delete(course);

            session.getTransaction().commit();
        }
        finally {
            session.close();
            factory.close();
        }
    }
}
