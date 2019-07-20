package com.hibernate.demo;

import com.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {
    public static void main(String[] args) {

        // Create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        // use session object to save Java object
        try {
            int studentId = 1;
            // Start a transaction
            session = factory.getCurrentSession();
            session.beginTransaction();
            // retrieve student based on the id: primary key
            System.out.println("Getting student with id: " + studentId);
            Student myStudent = session.get(Student.class, studentId);

            System.out.println("Updating student... ");
            myStudent.setFirstName("Scooby");

            // Once we do the commit, it's updated in the database.
            session.getTransaction().commit();

            // get a new session
            session = factory.getCurrentSession();
            session.beginTransaction();

            // update email for all students
            System.out.println("Updating email for all students");
            session.createQuery("update Student set email='foo@gmail.com'").executeUpdate();

            session.getTransaction().commit();
        }
        finally {
            factory.close();
        }
    }
}
