package com.hibernate.demo;

import com.hibernate.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteEmployee {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        try {
            // Deleting 1st employee
            session.beginTransaction();
            session.delete(session.get(Employee.class, 1));


            // Using query:
            session.createQuery("delete from Employee where id=2").executeUpdate();
            session.getTransaction().commit();
        }
        finally {
            factory.close();
        }
    }
}
