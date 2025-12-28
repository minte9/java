/**
 * Phone Setup (multiple constructors example):
 * 
 * When you buy a phone, it must be set up in one of several ways:
 *   - Default setup
 *   - Setup with owner name
 *   - Setup with owner + storage
 */

package examples.oop.constructors;

public class PhoneSetup {
    public static void main(String[] args) {

        Phone phone1 = new Phone();
        Phone phone2 = new Phone("Alice");
        Phone phone3 = new Phone("Bob");

        phone1.showInfo();  // Owner: Unknown, Storage: 64
        phone2.showInfo();  // Owner: Alice, Storage: 64
        phone3.showInfo();  // Owner: Bob, Storage: 64
    }
}

class Phone {

    String owner;
    int storage;

    // Default constructor
    public Phone() {
        this.owner = "Unknown";
        this.storage = 64;
    }

    // Constructor with name
    public Phone(String owner) {
        this.owner = owner;
        this.storage = 64;
    }

    // Constructor with name and storage
    public Phone(String owner, int storage) {
        this.owner = owner;
        this.storage = storage;
    }

    public void showInfo() {
        System.out.println("Owner: " + owner + ", Storage: " + storage);
    }
}
