package ru.griz.msfxclient.presentation.common;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

public abstract class SimpleView implements View{

    protected final VBox container;

    public SimpleView() {
        container = new VBox();
        container.setPadding(new Insets(4));
    }

    @Override
    public VBox container() {
        return container;
    }
}
