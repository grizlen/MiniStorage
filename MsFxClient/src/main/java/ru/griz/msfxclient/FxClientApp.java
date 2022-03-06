package ru.griz.msfxclient;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.griz.msfxclient.presentation.RestView;

public class FxClientApp extends Application {
    @Override
    public void start(Stage stage) {
        Parent restView = new RestView();
        Scene scene = new Scene(restView, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}