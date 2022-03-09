package ru.griz.msfxclient.presentation.common;

public abstract class DocView<M, I> extends SimpleView{

    protected final DocHeaderView header;
    protected final DocTableView<I> table;
    protected M model;

    public DocView() {
        header = createHeader();
        table = createTable();
        container.getChildren().addAll(header.getContainer(), table.getItemList());
    }

    protected abstract DocHeaderView createHeader();
    protected abstract DocTableView<I> createTable();

    protected abstract void fillHeader();
    protected abstract void fillTable();

    public void loadModel(M model) {
        this.model = model;
    }
}
