package com.minte9.oop.composition;

public class NotificationServiceApp {
    public static void main(String[] args) {
        
        NotificationService service = 
            new NotificationService(new EmailSender());
            
        service.notifyUser("Your order was shipped.");  
            // Sending email: Your order was shipped.

    }
}

interface MessageSender {
    void send(String message);    
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

// Composition: NotificationService HAS-A MessageSender
class NotificationService {

    // Dependency Inversion
    private MessageSender sender;  // service depends on the abstraction

    // Dependency Injection
    public NotificationService(MessageSender sender) {
        this.sender = sender;
    }

    public void notifyUser(String message) {
        sender.send(message);
    }
}
