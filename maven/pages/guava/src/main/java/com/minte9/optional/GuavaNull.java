/**
 * Guava - Null optional example
 */

package com.minte9.optional;
import com.google.common.base.Optional;

public class GuavaNull {
    public static void main(String[] args) {

      // Optional

      Optional<Integer> possible = Optional.of(5);
      System.out.println(possible.isPresent()); // true
      System.out.println(possible.get()); // 5

      // Without Guava

      try {
        String a = null;
        System.out.println(a.length());
      } catch (NullPointerException e) {}

      // Guava Optional

      String b = null;
      Optional<String> c = Optional.fromNullable(b);
      if (c.isPresent()) {
        System.out.println(c.get());
      }
  }
  
}