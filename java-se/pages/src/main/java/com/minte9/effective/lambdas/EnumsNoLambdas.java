/**
 * Java 8 Lambdas
 * 
 * Enum example with no Lambdas
 */
package com.minte9.effective.lambdas;

public class EnumsNoLambdas {

    public enum Operation {

        PLUS ('+') { public int run(int x, int y) { return x + y; } },
        MINUS('-') { public int run(int x, int y) { return x - y; } },
        ;

        private char ch;
        private Operation(char ch) { this.ch = ch; }
        public String toString() { return ch + ""; }
        public abstract int run(int x, int y);

    }

    public static void main(String[] args) {
        
        int a = Operation.PLUS.run(1, 2);
        int b = Operation.MINUS.run(20, 10);

        System.out.println(a); // 3
        System.out.println(b); // 10

        System.out.println(Operation.PLUS); // +
        System.out.println(Operation.MINUS); // -
    }
}