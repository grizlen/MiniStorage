package ru.griz.msfxclient.presentation.views;

import javafx.scene.Node;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import ru.griz.msfxclient.presentation.commands.Commands;

public abstract class ContentView extends VBox {
    public abstract String title();
    public abstract Commands.Command[] commands();
    public abstract void open();

    protected void setContent(Node node) {
        VBox.setVgrow(node, Priority.ALWAYS);
        getChildren().add(node);
    }
}
