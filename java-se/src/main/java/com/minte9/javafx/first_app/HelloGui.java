/**
 * Gui launcher class ...
 * Loads and display hello.xml file generated with Scene Builder
 */

package com.minte9.javafx.first_app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloGui extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("hello.fxml"));

        stage.setTitle("Hello World");
        stage.setScene(new Scene(root, 300, 300));
        stage.show();
    }
}