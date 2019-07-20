package com.hibernate.demo;

import com.hibernate.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateEmployee {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            Employee employee = session.get(Employee.class, 1);
            employee.setFirstName("Sam");

            System.out.println("\nUpdating multiple fields");
            session.createQuery("update Employee set company='Facebook'").executeUpdate();

            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }
}
