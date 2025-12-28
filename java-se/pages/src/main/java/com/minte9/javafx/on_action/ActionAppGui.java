/**
 * Gui class with button.xml loader
 */

package com.minte9.javafx.on_action;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ActionAppGui extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("button.fxml"));

        stage.setTitle("Button Event FXML");
        stage.setScene(new Scene(root));
        stage.show();
    }
}