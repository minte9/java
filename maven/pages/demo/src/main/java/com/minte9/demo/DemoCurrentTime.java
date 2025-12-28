/**
 * Maven import library example (joda.time)
 */

package com.minte9.demo;
import org.joda.time.LocalTime;

public class DemoCurrentTime {
    public static void main(String[] args) {
        
        LocalTime currentTime = new LocalTime();
        System.out.println(
            "The current local time is: " + currentTime
        );
  }
}