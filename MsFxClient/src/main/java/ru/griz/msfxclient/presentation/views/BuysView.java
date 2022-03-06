package ru.griz.msfxclient.presentation.views;

import javafx.scene.input.MouseEvent;
import ru.griz.msfxclient.domain.controllers.BuyController;
import ru.griz.msfxclient.domain.models.DocumentBuy;
import ru.griz.msfxclient.presentation.commands.Commands;
import ru.griz.msfxclient.presentation.common.JournalView;
import ru.griz.msfxclient.presentation.common.ViewManager;

public class BuysView extends JournalView<DocumentBuy> {

    private final BuyController controller;

    public BuysView() {
        lvData.setOnMouseClicked(this::onLwDataClick);
        controller = BuyController.instance();
    }

    private void onLwDataClick(MouseEvent mouseEvent) {
        DocumentBuy item = selectedItem();
        if ((mouseEvent.getClickCount() == 2) && (item != null)) {
            BuyView view = ViewManager.currentView(BuyView.class);
            view.loadModel(item);
        }
    }

    @Override
    public void open() {
        ViewManager.setNavCommands(Commands.get(Commands.CMD_BUY_NEW));
        ViewManager.setNavTitle("Поставки");
        loadData();
    }

    private void loadData() {
        docList.clear();
        docList.addAll(controller.getAll());
    }
}
