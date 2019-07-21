package com.hibernate.demo;

import com.hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetZoo {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Zoo.class)
                .addAnnotatedClass(Animal.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            Zoo tempZoo = session.get(Zoo.class, 5);

            System.out.println("Zoo: "+ tempZoo);
            System.out.println("Animals: " + tempZoo.getAnimals());

            session.getTransaction().commit();
        }
        finally {
            session.close();
            factory.close();
        }
    }
}
