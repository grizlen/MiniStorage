package ru.griz.msfxclient.presentation.common;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DocHeaderView {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    private final Label lblDate;
    private final HBox container;

    public DocHeaderView() {
        container = new HBox();
        lblDate = new Label();
        container.getChildren().add(lblDate);
    }

    public HBox getContainer() {
        return container;
    }

    public void setDate(Date date) {
        lblDate.setText(formatter.format(date));
    }
}
