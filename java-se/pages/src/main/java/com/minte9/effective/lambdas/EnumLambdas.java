/**
 * Enum Example - With lambdas
 * 
 * The constructor stores the lambda in an instanced field
 * Later, the method run() invokes the lambda expression
 */

package com.minte9.effective.lambdas;
import java.util.function.IntBinaryOperator;

public class EnumLambdas {

    public enum Operation {

        PLUS  ('+', (x, y) ->  x + y) {},
        MINUS ('-', (x, y) ->  x - y) {};

        IntBinaryOperator op;
        private Operation(char ch, IntBinaryOperator op) { this.op = op; }
        public int run(int x, int y) { return op.applyAsInt(x, y); }
    }

    public static void main(String[] args) {

        int a = Operation.PLUS.run(1, 2);
        int b = Operation.MINUS.run(20, 10);

        System.out.println(a); // 3
        System.out.println(b); // 10
    }
}