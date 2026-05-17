package examples.junit;

import org.junit.Test;

public class ExpectedExceptions {

    @Test(expected = ArithmeticException.class)
    public void divide_by_zero_should_throw_exception() {

        Calculator calculator = new Calculator();
        calculator.divide(10, 0);
            // test passes only if ArithmeticException is thrown
    }
}

class Calculator {

    public int divide(int a, int b) {
        return a / b;
    }
}