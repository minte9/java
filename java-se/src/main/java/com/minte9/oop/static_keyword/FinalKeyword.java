/**
 * Final classes cannot be extended into different kind.
 * Final methods cannot be changed by subclasses.
 * Final static fields are constants (it can never change).
 */

package com.minte9.oop.static_keyword;

public class FinalKeyword {
    public static void main(String[] args) {
        
        IDCard id = new IDCard();
        id.showInfo();  // Official ID Card

        // ❌ Not allowed
        // IDCard.LABEL = "New ID Card";
    }
}

final class IDCard {  // Look Here
    final static String LABEL = "Official ID Card"; // Look Here
    
    public void showInfo() {
        System.out.println(LABEL);
    }
}

// ❌ Not allowed
// class StudentCard extends IDCard() {}