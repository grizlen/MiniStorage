package ru.griz.msfxclient.presentation.common;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DocumentHeader {

    private final Label lblDate;
    private final DateTimeFormatter formatter;
    private final HBox container;

    public DocumentHeader() {
        container = new HBox();
        lblDate = new Label();
        container.getChildren().add(lblDate);
        formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    }

    public HBox getContainer() {
        return container;
    }

    public void setDate(LocalDateTime date) {
        lblDate.setText(formatter.format(date));
    }
}
