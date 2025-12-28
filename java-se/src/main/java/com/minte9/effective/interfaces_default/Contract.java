/**
 * Default methods for existing interfaces ...
 * should be avoided unless the need is critical. 
 * 
 * A default method added to an interface break main scope ...
 * which is to unsure a valid contract betwween classes.
 */

package com.minte9.effective.interfaces_default;

public class Contract {
    public static void main(String[] args) {

        new Subscribe();
    }
}

interface A {
    public default void getCommand() {
    }
    public default void subscribe() {        
    }
}

class B implements A {
    
    // there is no need to override subscribe() so ... 
    // the contract is no longer valid!
}