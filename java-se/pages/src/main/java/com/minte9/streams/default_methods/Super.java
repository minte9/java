/**
 * Interfaces are subject to multiple inheritance.
 * Java compiler might not know which method to choose.
 * 
 * In this case you must override the defaul method ...
 * and provide the choosen interface method
 */

package com.minte9.streams.default_methods;

public class Super {
    public static void main(String[] args) {
  
        String msg = new myClass().hello();
        System.out.println(msg); 
            // Hello A
    }
  }
  
  interface AI {
    public default String hello() { return "Hello A"; }
  }
  
  interface BI {
    public default String hello() { return "Hello B"; }
  }
  
  class myClass implements AI, BI {

    @Override public String hello() {
        
        return AI.super.hello(); // Look Here
    }
  }
  