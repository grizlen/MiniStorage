package ru.griz.msfxclient.presentation.views;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import ru.griz.msfxclient.domain.controllers.BuyController;
import ru.griz.msfxclient.domain.controllers.Controllers;
import ru.griz.msfxclient.domain.models.DocBuyModel;
import ru.griz.msfxclient.presentation.commands.Command;
import ru.griz.msfxclient.presentation.commands.Commands;
import ru.griz.msfxclient.presentation.common.DocHeaderView;
import ru.griz.msfxclient.presentation.common.DocTableView;
import ru.griz.msfxclient.presentation.common.DocView;
import ru.griz.msfxclient.presentation.common.ViewManager;
import ru.griz.msfxclient.presentation.dialogs.SelectProductDialog;

public class DocBuyView extends DocView<DocBuyModel, DocBuyModel.BuyItem> {

    private final Command cmdSave;
    private final BuyController controller;

    public DocBuyView() {
        cmdSave = Command.builder("save", "Сохранить")
                .action(this::cmdSaveAction)
                .build();
        controller = Controllers.get(BuyController.class);
        Button btnSelect = new Button("+");
        btnSelect.setOnAction(this::onBtnSelectAction);
        HBox footer = new HBox(btnSelect);
        footer.setAlignment(Pos.CENTER_RIGHT);
        footer.setPadding(new Insets(4));
        container.getChildren().add(footer);
    }

    private void onBtnSelectAction(ActionEvent actionEvent) {
        SelectProductDialog dialog = new SelectProductDialog();
        if (dialog.execute()) {
            DocBuyModel.BuyItem item = new DocBuyModel.BuyItem();
            item.setProductId(dialog.result().getId());
            item.setCount(1);
            addItem(item);
        }
    }

    private void addItem(DocBuyModel.BuyItem buyItem) {
        model.addItem(buyItem);
        table.setItems(model.getItems());
    }

    @Override
    public String title() {
        return "Поставка";
    }

    @Override
    public void open() {
        ViewManager.setNavCommands(cmdSave, Commands.get(Commands.CMD_BUY_JOURNAL));
        loadModel(null);
    }

    @Override
    public void loadModel(DocBuyModel model) {
        if (model == null) {
            model = new DocBuyModel();
        }
        super.loadModel(model);
        fillHeader();
        fillTable();
    }

    @Override
    protected DocHeaderView createHeader() {
        return new DocHeaderView();
    }

    @Override
    protected DocTableView<DocBuyModel.BuyItem> createTable() {
        return new DocTableView<>();
    }

    @Override
    protected void fillHeader() {
        if (model != null) {
            header.setDate(model.getDate());
        }
    }

    @Override
    protected void fillTable() {
        if (model != null && model.getId() != null) {
            table.setItems(controller.getItems(model));
        }
    }

    private void cmdSaveAction() {
        System.out.println("Save: " + model);
        controller.save(model);
        ViewManager.currentView(JournalBuyView.class);
    }
}
