package com.hibernate.demo;

import com.hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesForStudent {
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

            // Get the student mary from database
            Student mary = session.get(Student.class, 2);

            // Create more courses
            Course newCourse1 = new Course("Social Studies");
            Course newCourse2 = new Course("Science");


            // Add mary to those courses
            newCourse1.addStudent(mary);
            newCourse2.addStudent(mary);

            session.save(newCourse1);
            session.save(newCourse2);

            session.getTransaction().commit();
        }
        finally {
            session.close();
            factory.close();
        }
    }
}
