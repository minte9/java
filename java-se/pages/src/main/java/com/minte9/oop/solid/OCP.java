/**
 * O - OPEN/CLOSED PRINCIPLE (OCP)
 * -------------------------------
 * Open for extension.
 * Closed for modification. 
 * 
 * We can add new notification types WITHOUT
 * changing existing classes.
 * 
 * Example: Notification Service
 * 
 * If tommorrow you need a SlackNotification, just add a new class.
 * This DO NOT change:
 *  - NotificationService
 *  - EmailService
 *  - Any existing code
 */

package com.minte9.oop.solid;

public class OCP {
    public static void main(String[] args) {
        NotificationService service = new NotificationService();

        service.send(new EmailNotification(), "Welcome!");
        service.send(new SmsNotification(), "Your code is 1234");
        service.send(new PushNotification(), "New message received");

            // Sending EMAIL: Welcome!
            // Sending SMS: Your code is 1234
            // Sending PUSH: New message received
    }
}

// ==== Contract ====

interface Notification   {
    void send(String message);    
}

// ==== Serice ====

class NotificationService {
    public void send(Notification notification, String message) {
        notification.send(message);
    }
} 

// ==== Extensions =====

class EmailNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Sending EMAIL: " + message);
    }
}

class SmsNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

class PushNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Sending PUSH: " + message);
    }
}
