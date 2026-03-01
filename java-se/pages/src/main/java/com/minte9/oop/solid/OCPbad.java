/**
 * NOT OCP - BAD DESIGN
 * --------------------
 * 
 * Every time you add a new type:
 *  - You modify the service class
 *  - You risk breaking existing code
 *  - You recompile and retest
 * 
 * This violetes OCP.
 * 
 * Why interfaces are perfect for OCP?
 * 
 * Interfaces allow:
 *  - Polymorphism
 *  - Loose coupling
 *  - Independent implementations
 *  - Runtime substitution
 * 
 * Whenever you see if (type == ...)
 *  - That's usually a smell that OCP can improve the design.
 */
package com.minte9.oop.solid;

public class OCPbad {
    public static void main(String[] args) {
        BadNotificationService service = new BadNotificationService();

        service.send("email", "Welcome!");
        service.send("sms", "Your code is 1234");
        service.send("push", "New message received");
    }
}

class BadNotificationService {
    public void send(String type, String message) {

        if (type.equals("email")) {
            System.out.println("Sending EMAIL: " + message);
        } else
        if (type.equals("sms")) {
            System.out.println("Sending SMS: " + message);
        } else
        if (type.equals("push")) {
            System.out.println("Sending PUSH: " + message);
        }

    }
}
