/**
 * Default methods can be used in lambda expresions.
 * Functional interface must contain only one abstract method.
 */

package com.minte9.streams.default_methods;
import java.util.Arrays;
import java.util.List;

public class Functional {
  public static void main(String[] args) {
      
    List<MyValue> myList = Arrays.asList(
      () -> 1, 
      () -> 3,
      () -> 2 // lambda: get()
    );

    int maxDoubled = myList.stream()
      .mapToInt(x -> x.getDouble()) // lambda: default method
      .max()
      .orElse(0)
    ;

    System.out.println(maxDoubled); // 6
  }
}

@FunctionalInterface interface MyValue {

  int get(); // abstract
  
  default int getDouble() {
    return get() * 2;
  }
}