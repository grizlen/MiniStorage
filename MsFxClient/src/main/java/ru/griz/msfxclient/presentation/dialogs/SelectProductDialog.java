package ru.griz.msfxclient.presentation.dialogs;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import ru.griz.msfxclient.domain.controllers.Controllers;
import ru.griz.msfxclient.domain.controllers.ProductController;
import ru.griz.msfxclient.domain.models.ProductItem;

import java.util.List;

public class SelectProductDialog extends ItemDialog<ProductItem>{

    private ListView<ProductItem> lvProducts;

    public SelectProductDialog() {
        dialog.getDialogPane().setContent(createContent());
    }

    @Override
    protected ButtonType[] buttonTypes() {
        return new ButtonType[] {ButtonType.CANCEL};
    }

    @Override
    protected ProductItem resultConverter(ButtonType buttonType) {
        return null;
    }

    @Override
    protected Node createContent() {
        VBox result = new VBox();
        result.setSpacing(4);
        lvProducts = new ListView<>();
        loadItems();
        lvProducts.setOnMouseClicked(this::onSelectProduct);

        Button btnNew = new Button("New");
        btnNew.setOnAction(event -> {
            NewProductDialog dialogNew = new NewProductDialog();
            if (dialogNew.execute()) {
                lvProducts.getItems().add(dialogNew.result);
            }
        });

        result.getChildren().addAll(btnNew, lvProducts);
        return result;
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
        ProductItem item = lvProducts.getSelectionModel().getSelectedItem();
        if (item != null) {
            dialog.setResult(item);
            dialog.close();
        }
    }
}
