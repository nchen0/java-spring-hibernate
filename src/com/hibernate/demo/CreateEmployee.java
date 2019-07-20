package com.hibernate.demo;

import com.hibernate.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateEmployee {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // Create an Employee object
            System.out.println("Creating an employee object");
            Employee employee1 = new Employee("Nick", "Jones", "Apple");

            // start a transaction
            session.beginTransaction();

            // save the employee object in the database
            System.out.println("Saving employees");
            session.save(employee1);
            session.getTransaction().commit();
            System.out.println("\nCreate Done");
        } finally {
            factory.close();
        }
    }
}
