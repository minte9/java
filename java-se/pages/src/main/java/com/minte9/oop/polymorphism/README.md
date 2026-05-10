### Polymorphism

Polymorphism means "many forms".  

### 1. Storage Service

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

### 2. Dependency Injection

FileOpener receives an Item through its constructor insteed of creating it itself.  
This is Dependency Injectdion.

The same method call (open) behaves differently depending on the concreate type (Csv or XML) 
provided at runtime (PO).

~~~java
package com.minte9.oop.polymorphism.di_note;

public class DependencyInjection {
    public static void main(String[] args) {
        Item csv = new Csv();
        Item xml = new Xml();

        FileOpener csvOpener = new FileOpener(csv);
        FileOpener xmlOpener = new FileOpener(xml);

        csvOpener.open();  // CSV opened
        xmlOpener.open();  // XML opened
       
    }
}

abstract class Item {
    public abstract void open();
}

class Csv extends Item {
    @Override
    public void open() {
        System.out.println("CSV opened");
    }
}

class Xml extends Item {
    @Override
    public void open() {
        System.out.println("XML opened");
    }
}

class FileOpener {
    private final Item item;  // dependency

    // Dependency Injection via constructor
    public FileOpener(Item item) {
        this.item = item;
    }

    public void open() {
        item.open();
    }
}
~~~