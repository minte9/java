/**
 * Setter and getter JavaBeans specifications:
 * 
 * The setter method for foo must be called setFoo()
 * The gettter method for xIndex must be called getxIndex()
 */

package com.minte9.basics.variables;

public class Setter {           
    public static void main(String[] args) {
        
        Dog dog = new Dog();  
        dog.setName("Rex");
        
        System.out.println(dog.getName()); // Rex
    }
}

class Dog {
    private String name;

    public void setName(String x) {
        name = x;
    }
    public String getName() {
        return name;
    }
}