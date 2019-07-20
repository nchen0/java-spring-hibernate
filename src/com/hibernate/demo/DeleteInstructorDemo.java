package com.hibernate.demo;

import com.hibernate.entity.Instructor;
import com.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDemo {
    public static void main(String[] args) {

        // Create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        // use session object to save Java object
        try {

            session.beginTransaction();
            // get instructor by primary key / id
            Instructor tempInstructor = session.get(Instructor.class, 2);
            System.out.println("found Instructor: " + tempInstructor);

            if (tempInstructor != null) {
                session.delete(tempInstructor);
            }

            session.getTransaction().commit();
        }
        finally {
            factory.close();
        }
    }
}
