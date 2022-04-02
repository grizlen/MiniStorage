package ru.griz.msfxclient.presentation.views;


import javafx.geometry.Insets;

public abstract class DocumentView<T, I> extends ContentView {

    protected T document;
    protected DocHeaderView headerView;
    protected DocTableView<I> tableView;

    public DocumentView() {
        setPadding(new Insets(4));
        headerView = createHeader();
        tableView = createTable();
        getChildren().addAll(headerView, tableView);
    }

    protected DocHeaderView createHeader() {
        return new DocHeaderView();
    }

    protected DocTableView<I> createTable() {
        return new DocTableView<>();
    }

    protected void Load(T doc) {
        document = doc;
    }
}
