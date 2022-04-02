package ru.griz.msfxclient.presentation.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class DocTableView<T> extends ListView<T> {

    private final ObservableList<T> items = FXCollections.observableArrayList();
    private Consumer<T> onItemClick = i -> System.out.println("onItemClick");

    public DocTableView() {
        VBox.setVgrow(this, Priority.ALWAYS);
        setItems(items);
        setOnMouseClicked(this::onMouseClicked);
    }

    private void onMouseClicked(MouseEvent mouseEvent) {
        Optional<T> current = getCurrent();
        if (mouseEvent.getClickCount() == 2 && current.isPresent()) {
            if (onItemClick != null) {
                onItemClick.accept(current.get());
            }
        }
    }

    public void LoadItems(List<T> list) {
        items.clear();
        items.addAll(list);
    }

    public Optional<T> getCurrent() {
        return Optional.ofNullable(getSelectionModel().getSelectedItem());
    }

    public void setOnItemClick(Consumer<T> onItemClick) {
        this.onItemClick = onItemClick;
    }
}
