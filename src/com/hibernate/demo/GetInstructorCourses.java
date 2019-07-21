package com.hibernate.demo;

import com.hibernate.entity.Course;
import com.hibernate.entity.Instructor;
import com.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorCourses {
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
            Instructor tempInstructor = session.get(Instructor.class, 1);

            System.out.println("Instructor: "+ tempInstructor);
            System.out.println("Courses: " + tempInstructor.getCourses());

            session.getTransaction().commit();
        }
        finally {
            session.close();
            factory.close();
        }
    }
}
