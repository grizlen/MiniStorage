package ru.griz.msfxclient;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import ru.griz.msfxclient.domain.documents.buy.JournalBuyView;
import ru.griz.msfxclient.presentation.views.MainView;

@Component
public class StageInitializer implements ApplicationListener<FxClientApp.StageReadyEvent> {

    private final ApplicationContext applicationContext;
    private final String applicationTitle;

    public StageInitializer(ApplicationContext applicationContext, @Value("${spring.application.ui.title}") String applicationTitle) {
        this.applicationContext = applicationContext;
        this.applicationTitle = applicationTitle;
    }

    @Override
    public void onApplicationEvent(FxClientApp.StageReadyEvent stageReadyEvent) {
        Stage stage = stageReadyEvent.getStage();
        MainView.currentView(JournalBuyView.class);
        stage.setScene(new Scene(MainView.instance(), 800, 600));
        stage.setTitle(applicationTitle);
        stage.show();
    }
}
