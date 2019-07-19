package com.hibernate.demo;

import com.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {
    public static void main(String[] args) {

        // Create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        // use session object to save Java object
        try {

            // create 3 student objects
            System.out.println("Creating 3 student objects...");
            Student tempStudent = new Student("Paul", "Wall", "paul@luv2code.com");
            Student tempStudent2 = new Student("John", "Wall", "pau2l@luv2code.com");
            Student tempStudent3 = new Student("Mary", "Kay", "pau3l@luv2code.com");
            Student tempStudent4 = new Student("Mary", "Ellsworth", "pau3l@luv2code.com");
            Student tempStudent5 = new Student("Tom", "Phillips", "pau3l@luv2code.com");

            // Start a transaction
            session.beginTransaction();

            // save the student object
            System.out.println("Saving the student");
            session.save(tempStudent);
            session.save(tempStudent2);
            session.save(tempStudent3);
            session.save(tempStudent4);
            session.save(tempStudent5);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        }
        finally {
            factory.close();
        }
    }
}
