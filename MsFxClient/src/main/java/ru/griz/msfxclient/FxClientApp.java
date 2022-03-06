package ru.griz.msfxclient;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.griz.msfxclient.presentation.common.ViewManager;
import ru.griz.msfxclient.presentation.views.BuysView;

public class FxClientApp extends Application {
    @Override
    public void start(Stage stage) {
        ViewManager.init();
        ViewManager.currentView(BuysView.class);
        Scene scene = new Scene(ViewManager.mainView(), 320, 240);
        stage.setTitle("Mini storage");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}