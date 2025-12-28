/**
 * Calling a default method in lambas without object reference ...
 * doesn't work!
 */

package com.minte9.streams.default_methods;

import static org.junit.Assert.assertEquals;

public class Object {
  public static void main(String[] args) {
      
    Formula a = x -> x * 2; 
    assertEquals(4, a.calculate(2)); 
      // pass

    Formula b = x -> a.sqrt(x); // Look Here
    assertEquals(4, b.calculate(16)); 
      // pass

    //Formula c = x -> sqrt(x); // Not working
  }
}

@FunctionalInterface interface Formula {

  abstract int calculate(int x);

  default int sqrt(int x) {
    return (int) Math.sqrt(x);
  }
}