package ru.griz.msfxclient.presentation.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

<<<<<<< HEAD
import java.util.List;

=======
>>>>>>> origin/master
public abstract class JournalView<T> extends ContentView {

    private final ListView<T> lvItems = new ListView<>();
    protected final ObservableList<T> items = FXCollections.observableArrayList();

    public JournalView() {
        lvItems.setItems(items);
        lvItems.setOnMouseClicked(this::lvItemsOnMouseClicked);
        setContent(lvItems);
    }

    private void lvItemsOnMouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            T item = lvItems.getSelectionModel().getSelectedItem();
            if (item != null) {
                onClickItem(item);
            }
        }
    }

    protected void onClickItem(T item) {
        System.out.println("onClickItem");
    }
<<<<<<< HEAD

    protected void loadItems(List<T> list) {
        items.clear();
        items.addAll(list);
    }
=======
>>>>>>> origin/master
}
