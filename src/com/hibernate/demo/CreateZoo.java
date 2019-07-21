package com.hibernate.demo;

import com.hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateZoo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Zoo.class)
                .addAnnotatedClass(Animal.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        try {
//            Zoo zoo1 = new Zoo("Budapest Zoo", "Rainy");
//            Animal animal = new Animal("Ziggy", "Koala");
//            animal.setZoo(zoo1);
//            zoo1.add(animal);

            session.beginTransaction();

//            Zoo zoo2 = new Zoo("Houston Zoo", "Large");
            Zoo zoo2 = session.get(Zoo.class, 5);
            Animal animal2 = new Animal("Reina", "Rhino");
            zoo2.add(animal2);
            session.save(animal2);


//            session.save(animal2);
//            session.save(zoo1);

            // Commit transaction
            session.getTransaction().commit();
        }
        finally {
            session.close();
            factory.close();
        }
    }
}
