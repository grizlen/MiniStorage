package ru.griz.msfxclient.presentation.dialogs;

import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import ru.griz.msfxclient.data.entities.ProductEntity;
import ru.griz.msfxclient.domain.common.ProductItem;

public class NewProductDialog extends ItemDialog<ProductItem> {

    private TextField tfName;

    @Override
    protected Node createContent() {
        HBox result = new HBox();
        result.setSpacing(4);
        tfName = new TextField();
        result.getChildren().addAll(new Label("name:"), tfName);
        return result;
    }

    @Override
    protected ButtonType[] buttonTypes() {
        return new ButtonType[] {ButtonType.OK, ButtonType.CANCEL};
    }

    @Override
    protected ProductItem resultConverter(ButtonType buttonType) {
        if (buttonType.equals(ButtonType.OK) && !tfName.getText().isBlank()) {
            ProductEntity entity = new ProductEntity();
            entity.setName(tfName.getText());
//            DbContext.repository(ProductsRepository.class).save(entity);
            ProductItem item = new ProductItem();
            item.setId(entity.getId());
            item.setName(entity.getName());
            return item;
        }
        return null;
    }
}
