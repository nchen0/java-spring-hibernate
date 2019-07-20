package com.hibernate.demo;

import com.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {
    public static void main(String[] args) {

        // Create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        // use session object to save Java object
        try {


            session.beginTransaction();

            // Query all students
            List<Student> theStudents = session.createQuery("from Student").getResultList();

            // Display students
            displayStudents(theStudents);

            System.out.println("\nQuerying Student with first name Mary:");
            // Querying: Student with first name Mary
            theStudents = session.createQuery("from Student s where s.firstName='Mary'").getResultList();
            displayStudents(theStudents);
            // save the student object

            System.out.println("\nQuerying astName='Wall' OR firstName='Mary':");
            // Query students: lastName='Wall' OR firstName='Mary'
            theStudents = session.createQuery("from Student s where s.lastName='Wall' OR s.firstName='Mary'").getResultList();
            displayStudents(theStudents);

            System.out.println("\nQuerying Students with email ending in hello.com:");
            theStudents = session.createQuery("from Student s where s.email LIKE '%hello.com'").getResultList();
            displayStudents(theStudents);



            // commit transaction
            session.getTransaction().commit();
        }
        finally {
            factory.close();
        }
    }

    private static void displayStudents(List<Student> theStudents) {
        for (Student tempStudent : theStudents) {
            System.out.println(tempStudent);
        }
    }
}
