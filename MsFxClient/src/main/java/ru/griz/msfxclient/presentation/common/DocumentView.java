package ru.griz.msfxclient.presentation.common;

public abstract class DocumentView<T> extends SimpleView{

    protected final DocumentHeader header;
    protected T model;

    public DocumentView() {
        header = createHeader();
        container.getChildren().add(header.getContainer());
    }

    protected abstract DocumentHeader createHeader();
    protected abstract void fillHeader();

    public void loadModel(T model) {
        this.model = model;
    }
}
