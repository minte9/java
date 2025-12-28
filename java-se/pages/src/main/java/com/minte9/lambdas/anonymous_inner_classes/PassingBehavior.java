/**
 * LAMBDAS - PASSING BEHAVIOR, NOT OBJECTS
 * ---------------------------------------
 * Before Java 8, anonymous inner classes were the primary way to
 * pass 'behavior' (code) as a parameter.
 * 
 * Lambdas allow us to pass 'behavior' (a block of code) insteed of 
 * passing an object that wraps that behavior.
 * 
 * A lambdas expression:
 *  - has no name
 *  - implements a functional interface
 */

package com.minte9.lambdas.anonymous_inner_classes;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PassingBehavior {
    public static void main (String[] args) {
        
        JFrame frame = new JFrame("Inner Class vs Lambda");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        // ---------------------------------------------
        // Button 1 - Annonymous Inner Class (old style)
        // ---------------------------------------------
        JButton buttonInner = new JButton("Inner Class");
        buttonInner.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Clicked via INNER CLASS");
                }
            }
        );

        // ---------------------------------------------------
        // Button 2 - Lambda Expressions (modern style)
        // ---------------------------------------------------
        JButton buttonLambda = new JButton("Lambda");
        buttonLambda.addActionListener(
            event -> System.out.println("Clicked via LAMBDA")
        );

        // ---------------------------------------------------
        // Layout 
        // ---------------------------------------------------
        frame.getContentPane().setLayout(new java.awt.FlowLayout());
        frame.getContentPane().add(buttonInner);
        frame.getContentPane().add(buttonLambda);        
        frame.setVisible(true);

    }
}
