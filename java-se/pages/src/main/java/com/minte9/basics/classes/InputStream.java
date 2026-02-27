/**
 * INPUT STREAM - Scanner
 * ----------------------------------------
 * Read input from stdin (standard input)
 * Write output to stdout (standard output)
 * ----------------------------------------
 */

package com.minte9.basics.classes;

import java.util.Scanner;

public class InputStream {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int a = scanner.nextInt();  // 10
        int b = scanner.nextInt();  // 20

        System.out.println("b: " + b);  // b: 20
        System.out.println("a: " + a);  // a: 10
        // ---------------------------
        // a: 20
        // a: 10

        int n = scanner.nextInt();                          // 2147483647
        double d = Double.parseDouble(scanner.next());      // 235345345345.234534

        scanner.nextLine();
        String s = scanner.nextLine();                      // Hello World

        System.out.println("String: " + s);                 
        System.out.println("Double: " + d);
        System.out.println("Int: " + n);
        // --------------------------------
        // String: Hello World
        // Double: 2.3534534534523453E11
        // Int: 2147483647

        scanner.close();
    }
}