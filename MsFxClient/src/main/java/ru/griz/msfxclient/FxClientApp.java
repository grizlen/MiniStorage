package ru.griz.msfxclient;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.griz.msfxclient.domain.CacheService;
import ru.griz.msfxclient.presentation.common.ViewManager;
import ru.griz.msfxclient.buy.JournalBuyView;

public class FxClientApp extends Application {
    @Override
    public void start(Stage stage) {
        CacheService.checkUpdates();
        ViewManager.currentView(JournalBuyView.class);
        Scene scene = new Scene(ViewManager.mainView(), 320, 240);
        stage.setTitle("Mini storage");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}