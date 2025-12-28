/**
 * Class instance variables always have default value.
 * Local variable does not have a default value, they must initialized.
 * 
 * Local variables must be initialized before use.
 * Null is allowed anywhere.
 */
package com.minte9.basics.variables;

public class DefaultValues {
    public static void main(String[] args) {
        new Values().showValues();
    }
}

class MyObj {}

class Values {
    int a;  // 0
    float b;  // 0.0
    boolean c;  // false
    MyObj v;  // null

    public void showValues() {
        String local_variable = "a";  // no default value

        System.out.println("int a = " + a);
        System.out.println("float b = " + b);
        System.out.println("boolean c = " + c);
        System.out.println("object v = " + v);
        System.out.println("String local_var = " + local_variable);

        /**
            int a = 0
            float b = 0.0
            boolean c = false
            object v = null
            String local_var = a
         */
    }
}

