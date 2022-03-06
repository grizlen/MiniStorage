package ru.griz.msfxclient.presentation.views;

import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import ru.griz.msfxclient.domain.controllers.BuyController;
import ru.griz.msfxclient.domain.models.DocumentBuy;
import ru.griz.msfxclient.domain.models.DocumentBuyItem;
import ru.griz.msfxclient.presentation.commands.Command;
import ru.griz.msfxclient.presentation.commands.Commands;
import ru.griz.msfxclient.presentation.common.*;

public class BuyView extends DocumentView<DocumentBuy> {

    private final Command cmdSave;
    private final BuyController controller;
    private final DocumentTable<DocumentBuyItem> table;

    public BuyView() {
        cmdSave = Command.builder("save", "Сохранить")
                .action(this::cmdSaveAction)
                .build();
        table = new DocumentTable<>();
        ListView<DocumentBuyItem> lwItems = table.getItemList();
        HBox.setHgrow(lwItems, Priority.ALWAYS);
        container.getChildren().add(lwItems);
        controller = BuyController.instance();
    }

    private void cmdSaveAction() {
        System.out.println("Save: " + model);
        controller.save(model);
        ViewManager.currentView(BuysView.class);
    }

    @Override
    public void open() {
        ViewManager.setNavCommands(cmdSave, Commands.get(Commands.CMD_BUY_JOURNAL));
        ViewManager.setNavTitle("Поставка");
        loadModel(null);
    }

    @Override
    protected DocumentHeader createHeader() {
        return new DocumentHeader();
    }

    @Override
    public void loadModel(DocumentBuy model) {
        if (model == null) {
            model = new DocumentBuy();
        }
        super.loadModel(model);
        fillHeader();
        fillTable();
    }

    private void fillTable() {
        if (model != null) {
            table.setItems(controller.getItems(model));
        }
    }

    @Override
    protected void fillHeader() {
        if (model != null) {
            header.setDate(model.getDate());
        }
    }
}
