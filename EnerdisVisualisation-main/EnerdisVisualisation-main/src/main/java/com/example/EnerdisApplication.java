package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class EnerdisApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(new URL("file:D:/donneEnerdis-main/" +
//                "donneEnerdis-main/target/classes/com/example/main-view.fxml"));

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/main-view.fxml"));


        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Enerdis Visual");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}