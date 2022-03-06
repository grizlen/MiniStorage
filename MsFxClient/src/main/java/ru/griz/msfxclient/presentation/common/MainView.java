package ru.griz.msfxclient.presentation.common;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class MainView extends VBox {

    private final NavPanel navPanel;
    private final VBox container;

    public MainView() {
        navPanel = new NavPanel();
        container = new VBox();
        container.setAlignment(Pos.CENTER);
        container.getChildren().add(new Label("Main view"));
        VBox.setVgrow(container, Priority.ALWAYS);
        getChildren().addAll(navPanel, container);
    }

    NavPanel getNavPanel() {
        return navPanel;
    }

    public Parent getContainer() {
        return container;
    }

    public void setContent(View view) {
        container.getChildren().clear();
        if (view == null) {
            return;
        }
        Parent content = view.getContainer();
        if (content != null) {
            container.getChildren().add(content);
            view.open();
        }
    }
}
