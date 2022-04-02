package ru.griz.msfxclient.presentation.views;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class DocHeaderView extends HBox {
    private final Label lblId = new Label();
    private final Label lblDate = new Label();

    public DocHeaderView() {
        setSpacing(4);
        getChildren().addAll(new Label("№"), lblId, new Label("от"), lblDate);
    }

    public void setId(Long id) {
        lblId.setText(String.valueOf(id));
    }

    public void setDate(String date) {
        lblDate.setText(date);
    }
}
