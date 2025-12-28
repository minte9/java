/**
 * FXML on action method is defined in ...
 * Scene Builer 'on Action'
 */

package com.minte9.javafx.on_action;
import javafx.fxml.FXML;

public class ActionApp {
    public static void main(String[] args) {
        ActionAppGui.main(args);
    }

    @FXML void buttonPressed() { // Look Here
        System.out.println("button pressed");
    }
}