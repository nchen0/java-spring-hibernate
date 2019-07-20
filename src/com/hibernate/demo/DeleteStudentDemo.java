package com.hibernate.demo;

import com.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {
    public static void main(String[] args) {

        // Create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        // use session object to save Java object
        try {
            int studentId = 1;
            session = factory.getCurrentSession();
            session.beginTransaction();

            System.out.println("\nGetting studnet with id: " + studentId);
            Student myStudent = session.get(Student.class, studentId);

            // delete the student
            System.out.println("Deleting student: " + myStudent);
            session.delete(myStudent);


            // Deleting using an alternate approach
            System.out.println("Deleting student id=2");
            session.createQuery("delete from Student where id=2").executeUpdate();

            session.getTransaction().commit();


        }
        finally {
            factory.close();
        }
    }
}
