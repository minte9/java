/**
 * Method reference
 * 
 * Use a lambdas that call methods on its parameter
 * Standard form of method reference is ClassName::methodName
 * The code is not only more shorter, also a lot easier to read
 */
package com.minte9.streams.static_methods;

import java.util.Arrays;
import java.util.List;

public class MethodReference {
  public static void main(String[] args) {
      
    List<String> myList = Arrays.asList("a", "b", "c");

    myList.stream()
      .map(x -> x + x)
      .map(String::toUpperCase)
      .forEach(System.out::println); // AA BB CC
  }
}