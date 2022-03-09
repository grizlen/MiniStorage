package ru.griz.msfxclient.presentation.common;

import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.util.List;

public class DocTableView<T> {

    private ListView<T> lvItems;

    public DocTableView() {
        lvItems = new ListView<>();
        HBox.setHgrow(lvItems, Priority.ALWAYS);
    }

    public ListView<T> getItemList() {
        return lvItems;
    }

    public void setItems(List<T> items) {
        lvItems.getItems().clear();
        lvItems.getItems().addAll(items);
    }
}
