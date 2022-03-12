package ru.griz.msfxclient.presentation.common;

public abstract class DocView<M, I> extends SimpleView{

    protected final DocHeaderView header;
    protected final DocTableView<I> table;
    protected final DocFooterView footer;
    protected M model;

    public DocView() {
        header = new DocHeaderView(); setUpHeader(header);
        table = new DocTableView<>(); setUpTable(table);
        footer = new DocFooterView(); setUpFooter(footer);
        container.getChildren().addAll(header.getContainer(), table.getItemList(), footer.container());
    }

    @Override
    public void open() {
        loadModel(null);
    }

    protected void setUpHeader(DocHeaderView header) { }
    protected void setUpTable(DocTableView<I> table) { }
    protected void setUpFooter(DocFooterView footer) { }

    protected abstract void fillHeader();
    protected abstract void fillTable();

    public void loadModel(M model) {
        this.model = model;
    }
}
