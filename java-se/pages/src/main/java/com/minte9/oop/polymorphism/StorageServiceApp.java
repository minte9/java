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