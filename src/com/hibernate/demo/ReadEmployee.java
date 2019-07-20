package com.hibernate.demo;

import com.hibernate.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ReadEmployee {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            System.out.println("Getting employee with id of 1");
            Employee employee = session.get(Employee.class, 1);

            System.out.println("Employee gotten: " + employee);

            System.out.println("\nStarting new Query of all employees:");
            List<Employee> employees = session.createQuery("from Employee").getResultList();
            displayEmployees(employees);

            System.out.println("\nStarting new Query that work at Apple");
            employees = session.createQuery("from Employee e where e.company='Apple'").getResultList();
            displayEmployees(employees);

            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }

    private static void displayEmployees(List<Employee> employees) {
        for (Employee e : employees) {
            System.out.println(e);
        }
    }
}
