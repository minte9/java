/**
 * Starting with Java 8 ...
 * interfaces can have default method construct
 */

package com.minte9.effective.interfaces_default;

public class DefaultMethods {
    public static void main(String[] args) {

        new Login().subscribe();
            // Default subscribe method

        new Subscribe().subscribe();
            // Default subscribe method - overwritten
    }
}

interface Commandable {
    public abstract void getCommand();

    public default void subscribe() {  // Look Here 
        System.out.println("Default subscribe method");
    }
}

class Subscribe implements Commandable {
    @Override public void getCommand() {
    }

    @Override public void subscribe() {
        System.out.println("Default subscribe method - overwritten");
    }
}

/**
 * Old class - don't need to imnplement subscribe()
 */
class Login implements Commandable { 
    @Override public void getCommand() {
    }

    //subscribe() overwitten - not needed!
}