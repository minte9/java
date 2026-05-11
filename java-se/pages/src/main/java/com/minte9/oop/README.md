# Object Oriented Programming (OOP)

1. Inheritance
2. Composition
3. Polymorphism



## 1. Inheritance 

"Prefer composition/interfaces over inheritance" is a good advice most of the time.  
But inheritance is still the right tool when there's a true "is-a" relationship + shared state + shared base behavior.

### 1.1 Payment Processing System

We can have different payment methods:

- Credit card
- PayPal
- Bank transfer

They all:

- Have an amout
- Have a common processing flow
- But differ in how the payment is executed

<details>
<summary>PaymentApp.java</summary>
~~~java
package com.minte9.oop.inheritance;

public class PaymentApp {
    public static void main(String[] args) {
        Payment p1 = new CreditCardPayment(100);
        Payment p2 = new PayPalPayment(200);

        p1.processPayment();
        p2.processPayment();

        /*
            Validating payment of $100.0
            Processing credit card payment...
            Sending receipt...

            Validating payment of $200.0
            Processing PayPal payment...
            Sending receipt...
        */
    }
}

// Superclass
abstract class Payment {
    protected double amount;

    public Payment(double amount) {
        this.amount = amount;
    }

    public void processPayment() {
        validate();
        execute();  // subclass-specific
        sendReceipt();
    }

    public void validate() {
        System.out.println("Validating payment of $" + amount);
    }

    protected abstract void execute();  // Look Here (must be implemented)

    public void sendReceipt() {
        System.out.println("Sending receipt...\n");
    }
}

// Subclass 1
class CreditCardPayment extends Payment {

    public CreditCardPayment(double amount) {
        super(amount);
    }

    @Override
    protected void execute() {
        System.out.println("Processing credit card payment...");
    }
}

// Subclass 2
class PayPalPayment extends Payment {

    public PayPalPayment(double amount) {
        super(amount);
    }

    @Override
    protected void execute() {
        System.out.println("Processing PayPal payment...");
    }
}
~~~
</details>

### 1.1 Why inheritance is the right choise here?

a) Strong "is-a" relationship:

- CreditCardPayment IS-a payment
- PayPalPayment IS-a payment

b) Shared logic that should NOT be duplicated.

You don't want every class reimplementing this.  

~~~java
processPayment()

// Validation
// Receipt sending
// Flow control
~~~

c) Sublcasses only customize:

~~~java
execute()
~~~

### 1.2 Why not use interfaces?

You could do:

~~~java
interface Payment() {
    void process();
}
~~~

But then:

- You lose shared state (amount)
- You duplicate workflow logic
- You can't enforce the sequence (validate -> execute -> receipt)

### 1.3 Rule of thumb

Use inheritance when:

- There is a true hierachy (is-a)
- You need shared state
- You want to enforce a common algorithm/flow
- Subclasses only tweak specific steps

Use interfaces when:

- You want capabilities/roles (Serializable, Runnable)
- Behavior varies wildly
- No shared implementation is needed

### 1.4 Payment system (done wrong)

A developer might try to be "modern" and do everything with interfaces. 

~~~java
package com.minte9.oop.inheritance.wrong_implementation;

public class PaymentWrongApp {
    public static void main(String[] args) {
        Payment p1 = new CreditCardPayment(100);
        Payment p2 = new PayPalPayment(200);

        p1.processPayment();
        p2.processPayment();

        /*
            Validating $100.0
            Processing credit card...
            Sending receipt...

            Validating $200.0
            Processing PayPal...
            Sending receipt...
        */
    }
}

interface Payment  {
    void processPayment();    
}

class CreditCardPayment implements Payment {
    private double amount;

    public CreditCardPayment(double amount) {
        this.amount = amount;
    }

    @Override
    public void processPayment() {
        System.out.println("Validating $" + amount);
        System.out.println("Processing credit card...");
        System.out.println("Sending receipt...\n");
    }
}

class PayPalPayment implements Payment {
    private double amount;

    public PayPalPayment(double amount) {
        this.amount = amount;
    }

    @Override
    public void processPayment() {
        System.out.println("Validating $" + amount);
        System.out.println("Processing PayPal...");
        System.out.println("Sending receipt...\n");
    }
}
~~~

### 1.5 What's wrong here?

a) Massive duplication, every class repeats:

~~~java
System.out.println("Validating $" + amount);
System.out.println("Sending receipt...\n");
~~~

If you change validation logic, you must update every class.

b) No shared state

Every class defines amount, but that's cleary common.

c) No control over workflow

Nothing enforces:

~~~sh
validate -> execute -> sendRecipt
~~~

A developer could accidentally do:

~~~java
sendReceipt();
execute();
~~~

### 1.6 Abstract Keyword

An abstract class CANNOT be instantiated directly.

What exactly is a Payment?

- Is it a credit card payment? A PayPal payment? A bank transfer?
- We don't know, so Payment should be abstract.

An abstract class is meant to be extended by subclasses.  
An abstract method defines behavior that subclasses must implement.  

~~~java
package com.minte9.oop.inheritance.abstract_note;

public class AbstractKeyword {

    public static void main(String[] args) {

        CreditCardPayment payment = new CreditCardPayment();
        
        payment.setAmount(250);
        payment.processPayment();  // Processing credit card payment of $250.0
    }
}

abstract class Payment {
    protected double amount;

    public abstract void processPayment();  // abstract (must be implemented)

    public void setAmount(double amount) {  // non-abstract method (shared behavior)
        this.amount = amount;
    }
}

class CreditCardPayment extends Payment {

    @Override
    public void processPayment() {
        System.out.println("Processing credit card payment of $" + amount);
    }
}
~~~

### 1.7 Override Annotation

The @Override annotation acts as a compile-time safeguard.  

It tells the compiler:  
This method must override a method from the parent class.  

Without @Override, a typo creates a new method instead of overriding,  
and the error may go unnoticed until runtime behavior is wrong.  

~~~java
package com.minte9.oop.inheritance.override_note;

public class OverrideAnnotation {

    public static void main(String[] args) {
        
        CreditCardPayment payment = new CreditCardPayment();
        payment.processPayment();  // Wrong method called!
    }
}

abstract class Payment {
    public abstract void processPayment();
}

class CreditCardPayment extends Payment {

    // Typo: processsPayment instead of processPayment
    // No @Override annotation to protect us
    public void processsPayment() {
        System.out.println("Processing credit card payment...");
    }

    // This method was auto-generated later just to satisfy compilation
    @Override
    public void processPayment() {
        System.out.println("Wrong method called!");
    }
}
~~~



## 2. Composition

When a class implements an interface, it agrees to a contract.  

### 2.1. Notification Service

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

### 2.2 Dependency Inversion

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

### 2.3 Default Method

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


## 3. Polymorphism

Polymorphism means "many forms".  



### 3.1 Storage Service

A storage service can work with different storage providers  
through the same interface.  

The application does not care if files are stored:  

- locally
- in Amazon S3
- in Google GCS

It only knows: store(fileName)

At runtime, Java decides which implementation to execute.  

~~~java
package com.minte9.oop.polymorphism;

public class StorageServiceApp {
    public static void main(String[] args) {
        
        // Same interface reference
        StorageProvider storage = new LocalStorage();

        FileService service = new FileService(storage);
        service.uploadFile("photo.jpg");  // Saving file locally: photo.jpg

        // Runtime behavior changes
        storage = new CloudStorage();
        service = new FileService(storage);
        service.uploadFile("video.mp4");  // Uploading file to cloud: video.mp4
    }
}

interface StorageProvider {
    void storage(String fileName);    
}

// First implementation
class LocalStorage implements StorageProvider {
    @Override
    public void storage(String fileName) {
        System.out.println("Saving file locally: " + fileName);
    }
}

// Second implementation
class CloudStorage implements StorageProvider {
    @Override
    public void storage(String fileName) {
        System.out.println("Uploading file to cloud: " + fileName);
    }
}

// Composition
class FileService {

    // Dependency Inversion:
    private StorageProvider storage;  // Business logic depends on abstraction

    // Dependency Injection:
    public FileService(StorageProvider storage) {  // Dependency injectected from outside
        this.storage = storage;
    }

    // Polymorphism: 
    public void uploadFile(String fileName) {
        storage.storage(fileName);  // Same method call, different runtime behavior
    }
}
~~~

### 3.2 Dependency Injection

A beginner ofthen writes this:

~~~java
class FileService {

    private CloudStorage storage = new CloudStorage();
}
~~~

Problem:

- tightly coupled
- impossible to switch implementation
- difficult to test
- business logic controls low-level details

With Dependency Injection:

- implementation is external
- behavior is configurable
- dependencies are replaceable

~~~java
class FileService {

    private StorageProvider storage;  // Business logic depends on abstraction

    public FileService(StorageProvider storage) {  // Dependency injectected from outside
        this.storage = storage;
    }
}
~~~

Dependency Injection is everywhere in:

- Spring Framework
- Hibernate
- JUnit

### 3.3 Final Keyword

Suppose storaget provider should never change after service creation.  
That is a perfect use of final (cannot be reasigned, overriden, extended).    

~~~java
class FileService {
    private final StorageProvider storage;  // Look Here

    public FileService(StorageProvider storage) {
        this.storage = storage;
    }
}
~~~

Usefull when:

- security matters
- workflow must not change
- algorithm must stay consistent

Example:

- authentication flow
- transcation handling
- framework lifecycle methods

Extremely common, because dependencies should not mutate:

- Spring Framework
- Dependency Injection
- Clean arhitecture
- Immutable desing

### 3.4 Static Keyword

Suppose your application wants to count uploaded files globally.  
That is perfect use of static (belongs to the class/service).  

That count belongs to: 

- the whole application
- not to one FileService object

~~~java
class FileService {

    public static int totalUploads = 0;  // shared by all instances
    private StorageProvider storage;

    public FileService(StorageProvider storage) {
        this.storage = storage;
    }

    public void uploadFile(String fileName) {
        storage.store(fileName);
        totalUploads++;  // Look Here
    }
}
~~~