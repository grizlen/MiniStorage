package ru.griz.msfxclient.presentation.dialogs;

import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public abstract class ItemDialog<T> {

    protected final Dialog<T> dialog;
    protected T result;

    public ItemDialog() {
        dialog = new Dialog<>();
        setButtonTypes(buttonTypes());
        Node content = createContent();
        if (content != null) {
            dialog.getDialogPane().setContent(content);
        }
        dialog.setResultConverter(this::resultConverter);
    }

    protected abstract ButtonType[] buttonTypes();
    protected abstract T resultConverter(ButtonType buttonType);
    protected abstract Node createContent();

    private void setButtonTypes(ButtonType ... buttonType) {
        if (buttonType.length == 1) {
            dialog.getDialogPane().getButtonTypes().add(buttonType[0]);
        } else {
            dialog.getDialogPane().getButtonTypes().addAll(buttonType);
        }
    }

    public boolean execute() {
        result = dialog.showAndWait().orElse(null);
        return result != null;
    }

    public T result() {
        return result;
    }
}
