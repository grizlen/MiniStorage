package ru.griz.msfxclient.presentation.dialogs;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import ru.griz.msfxclient.domain.models.ProductItem;
import ru.griz.msfxclient.domain.services.ProductService;
import ru.griz.msfxclient.domain.services.Services;

import java.util.List;

public class SelectProductDialog extends ItemDialog<ProductItem>{

    private ListView<ProductItem> lvProducts;

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
        lvProducts.setOnMouseClicked(event -> {
            if (event.getClickCount() != 2) {
                return;
            }
            ProductItem item = lvProducts.getSelectionModel().getSelectedItem();
            if (item != null) {
                dialog.setResult(item);
                dialog.close();
            }
        });

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
        ProductService service = Services.get(ProductService.class);
        List<ProductItem> list = service.getAll();
        lvProducts.getItems().addAll(list);
    }
}
