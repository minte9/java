### Composition

When a class implements an interface, it agrees to a contract.  

### 1. Notification Service

A notification chanel cand send a message.  
Email, SMS, and push notifications are different channels,  
but they all must implement send().  

Interfaces are recommended here because "sendable" is a capability.  

~~~java
package com.minte9.oop.composition;

public class NotificationServiceApp {
    public static void main(String[] args) {
        
        NotificationService service = 
            new NotificationService(new EmailSender());  // polimorphism
            
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

    // Dependecny Injection
    public NotificationService(MessageSender sender) {
        this.sender = sender;
    }

    public void notifyUser(String message) {
        sender.send(message);
    }
}
~~~

### 2. Dependency Inversion

Before Dependency Inversion high-level business code depends directly  
on low-level details.  

~~~java
class NotificationService {
    private EmailSender sender = new EmailSender();
}
~~~

Dependency direction:

~~~sh
NotificationService  ...>  EmailSender
(high level)               (low level)
~~~

Dependency inversion:

~~~sh
High-level ...> Abstraction <... Low-level
~~~

Originally:

~~~sh
NotificationService depends on EmailSender
~~~

After inversion:

~~~sh
EmailSender depends on MessageSender
~~~

The low-level class now depends on the abstraction defined by the high-level policy.  
That is the inversion.  

### 3. Default Method

Java 8 introduced default methods in interfaces.  

A default method allows us to add behavior to an interface  
without breaking all existing classes.  

Classes can use the default behavior or override it.  

~~~java
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
~~~