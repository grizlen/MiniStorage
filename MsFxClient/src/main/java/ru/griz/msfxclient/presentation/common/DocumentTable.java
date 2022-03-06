package ru.griz.msfxclient.presentation.common;

import javafx.scene.control.ListView;

import java.util.List;

public class DocumentTable<T> {

    private ListView<T> lvItems;

    public DocumentTable() {
        lvItems = new ListView<>();
    }

    public ListView<T> getItemList() {
        return lvItems;
    }

    public void setItems(List<T> items) {
        lvItems.getItems().clear();
        lvItems.getItems().addAll(items);
    }
}
