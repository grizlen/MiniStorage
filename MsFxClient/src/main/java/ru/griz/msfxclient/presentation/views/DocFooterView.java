package ru.griz.msfxclient.presentation.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class DocFooterView {

    private final HBox container;

    DocFooterView() {
        container = new HBox();
        container.setAlignment(Pos.CENTER_RIGHT);
        container.setPadding(new Insets(4));
    }

    public HBox container() {
        return container;
    }
}
