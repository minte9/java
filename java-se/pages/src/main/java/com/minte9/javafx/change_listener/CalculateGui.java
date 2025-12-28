/**
 * CalculateGui launcher which loads the fxml file
 */

package com.minte9.javafx.change_listener;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CalculateGui extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override public void start(Stage stage) throws Exception {
        Parent root = 
            FXMLLoader.load(getClass().getResource("tipcalculator.fxml"));

        stage.setTitle("Button Event FXML");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
