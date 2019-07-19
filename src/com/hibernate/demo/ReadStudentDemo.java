package com.hibernate.demo;

import com.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {
    public static void main(String[] args) {

        // Create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        // use session object to save Java object
        try {
            // create a student object
            System.out.println("Creating a new student object...");
            Student tempStudent = new Student("Daffy", "Duck", "paul@luv2code.com");
            // Start a transaction
            session.beginTransaction();

            // save the student object
            System.out.println("Saving the student");
            System.out.println(tempStudent);
            session.save(tempStudent);

            // commit transaction
            session.getTransaction().commit();


            System.out.println("Saved student. generated id: " + tempStudent.getId());
            System.out.println("Done!");



            // now get a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();
            // retrieve student based on the id: primary key
            System.out.println("Getting student with id: " + tempStudent.getId());
            Student myStudent = session.get(Student.class, tempStudent.getId());

            System.out.println("Get complete: " + myStudent);
            session.getTransaction().commit();

            // commit the transaction
        }
        finally {
            factory.close();
        }
    }
}
