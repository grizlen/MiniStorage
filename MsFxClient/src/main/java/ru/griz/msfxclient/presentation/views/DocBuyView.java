package ru.griz.msfxclient.presentation.views;

import javafx.scene.control.Button;
import ru.griz.msfxclient.domain.models.DocBuyModel;
import ru.griz.msfxclient.domain.services.BuyService;
import ru.griz.msfxclient.domain.services.Services;
import ru.griz.msfxclient.presentation.commands.Command;
import ru.griz.msfxclient.presentation.commands.Commands;
import ru.griz.msfxclient.presentation.common.*;
import ru.griz.msfxclient.presentation.dialogs.SelectProductDialog;

public class DocBuyView extends DocView<DocBuyModel, DocBuyModel.BuyItem> {

    private final Command cmdSave;
    private final BuyService service;

    public DocBuyView() {
        service = Services.get(BuyService.class);
        cmdSave = Command.builder("save", "Сохранить")
                .action(() -> {
                    service.save(model);
                    ViewManager.currentView(JournalBuyView.class);
                })
                .build();
    }

    @Override
    public String title() {
        return "Поставка";
    }

    @Override
    public Command[] navCommands() {
        return new Command[] {cmdSave, Commands.get(Commands.CMD_BUY_JOURNAL)};
    }

    @Override
    protected void setUpFooter(DocFooterView footer) {
        Button btnSelect = new Button("+");
        btnSelect.setOnAction(event -> {
            SelectProductDialog dialog = new SelectProductDialog();
            if (dialog.execute()) {
                DocBuyModel.BuyItem item = new DocBuyModel.BuyItem();
                item.setProductId(dialog.result().getId());
                item.setCount(1);
                model.addItem(item);
                table.setItems(model.getItems());
            }
        });
        footer.container().getChildren().add(btnSelect);
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
            table.setItems(service.getItems(model));
        } else {
            table.clearItems();
        }
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
}
