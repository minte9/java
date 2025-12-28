/** 
 * In Java polymorphism is the ability of an object to take many forms.
 * You can access a subtype method from a super type object,
 * but you must cast the object with subtype. 
 */

package com.minte9.oop.polymorphism;

public class Polymorphism {
    public static void main(String[] args) {

        User user = new Client(); // reference is User type
        user.setName("John");

        // superclass method call
        System.out.println(user.reading());  // John is reading

        // subclass method call
        System.out.println( ((Client) user).buying() );  // John is buying
    }
}

// Abstract superclass
abstract class User {
    protected String name;
    abstract void setName(String s);
    public String reading() { 
        return name + " is reading."; 
    }
}

// Subclass
class Client extends User {
    public void setName(String s) {
        name = s;
    }
    public String buying() { 
        return name + " is buying."; 
    }
}
