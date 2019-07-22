package com.hibernate.demo;

import com.hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudents {
    public static void main(String[] args) {

        // Create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        // use session object to save Java object
        try {
            session.beginTransaction();
            Course tempCourse = new Course("Math");

            // Save the course
            System.out.println("\nSaving the course....");
            session.save(tempCourse);
            System.out.println("Saved the course: " + tempCourse);

            // Create the students
            Student tempStudent1 = new Student("John", "Doe", "john2@code.com");
            Student tempStudent2 = new Student("Mary", "Jane", "mary@code.com");

            // Add students to the course
            tempCourse.addStudent(tempStudent1);
            tempCourse.addStudent(tempStudent2);

            // Save the students
            System.out.println("\nSaving the students");
            session.save(tempStudent1);
            session.save(tempStudent2);

            session.getTransaction().commit();
        }
        finally {
            session.close();
            factory.close();
        }
    }
}
