package com.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {

        java.util.logging.LogManager.getLogManager().reset();
        
        SessionFactory factory = new Configuration()
            .configure()
            .buildSessionFactory();

    
        // First Run
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Message message = new Message("Hello Database!");
            session.save(message);
            session.getTransaction().commit();

            System.out.println("Saved ID: " + message.getId());
            session.close();
        }

        // Second Run
        try (Session session = factory.openSession()) {
            
            Message item = session.get(Message.class, 1L);

            if (item != null) {
                System.out.println("Found: " + item.getText());
            } else {
                System.out.println("Not found");
            }
            session.close();
        }
        
        factory.close();
    }    
}
