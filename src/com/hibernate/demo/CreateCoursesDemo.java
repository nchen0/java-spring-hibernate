package com.hibernate.demo;

import com.hibernate.entity.Course;
import com.hibernate.entity.Instructor;
import com.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesDemo {
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

            // Get the instructor from database
            session.beginTransaction();
            Instructor firstInstructor = session.get(Instructor.class, 1);

            // Create some courses
            Course firstCourse = new Course("Math");
            Course secondCourse = new Course("Science");
            firstInstructor.add(firstCourse);
            firstInstructor.add(secondCourse);

            // save the courses

            // Start a transaction

            // Save the instructor: Because of the CascadeType.ALL, this will also save the details object.

            // Commit transaction
            session.getTransaction().commit();
        }
        finally {
            session.close();
            factory.close();
        }
    }
}
