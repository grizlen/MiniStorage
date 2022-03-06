package ru.griz.msfxclient.presentation.common;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public abstract class JournalView<T> extends SimpleView {

    protected final ListView<T> lvData;
    protected final ObservableList<T> docList;

    public JournalView() {
        lvData = new ListView<>();
        VBox.setVgrow(lvData, Priority.ALWAYS);
        container.getChildren().add(lvData);
        docList = FXCollections.observableArrayList();
        lvData.setItems(docList);
    }

    protected T selectedItem() {
        return lvData.getSelectionModel().getSelectedItem();
    }
}
