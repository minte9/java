package com.minte9.oop.composition.default_note;

public class DefaultMethods {
    public static void main(String[] args) {
        
        NotificationService emailService = new NotificationService(new EmailSender());
        NotificationService smsService = new NotificationService(new SmsSender());

        emailService.send("Your order was shipped."); 
        emailService.log("Your order was shipped."); 

        smsService.send("Your code is 1234.");
        smsService.log("Your code is 1234.");  

        /*
            Sending email: Your order was shipped.
            Logging message: Your order was shipped.
            Sending sms: Your code is 1234.
            Logging message: Your code is 1234.
        */
    }
}

interface MessageSender {
    void send(String message); 
    
    default void log(String message) {  // Look Here
        System.out.println("Logging message: " + message);
    }
}

class EmailSender implements MessageSender {
    @Override
    public void send(String message) {
        System.out.println("Sending email: " + message);
    }
}

class SmsSender implements MessageSender {
    @Override
    public void send(String message) {
        System.out.println("Sending sms: " + message);
    }
}

class NotificationService {
    private MessageSender sender;
    public NotificationService(MessageSender sender) {
        this.sender = sender;
    }

    public void send(String message) {
        sender.send(message);
    }

    public void log(String message) {
        sender.log(message);
    }
}