package ru.griz.msfxclient.presentation.dialogs;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import ru.griz.msfxclient.domain.controllers.Controllers;
import ru.griz.msfxclient.domain.controllers.ProductController;
import ru.griz.msfxclient.domain.models.ProductItem;

import java.util.List;

public class SelectProductDialog {

    private final Dialog<ProductItem> dialog;
    private final ListView<ProductItem> lvProducts;
    private ProductItem result;

    public SelectProductDialog() {
        dialog = new Dialog<>();
        lvProducts = new ListView<>();
        loadItems();
        lvProducts.setOnMouseClicked(this::onSelectProduct);
        dialog.getDialogPane().setContent(lvProducts);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        dialog.setResultConverter(buttonType -> null);
    }

    private void loadItems() {
        ProductController controller = Controllers.get(ProductController.class);
        List<ProductItem> list = controller.getAll();
        lvProducts.getItems().addAll(list);
    }

    private void onSelectProduct(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() != 2) {
            return;
        }
        ProductItem result = lvProducts.getSelectionModel().getSelectedItem();
        if (result != null) {
            dialog.setResult(result);
            dialog.close();
        }
    }

    public boolean execute() {
        result = dialog.showAndWait().orElse(null);
        return result != null;
    }

    public ProductItem result() {
        return result;
    }
}
