package ru.griz.msfxclient.presentation.views;

import javafx.scene.input.MouseEvent;
import ru.griz.msfxclient.domain.controllers.BuyController;
import ru.griz.msfxclient.domain.controllers.Controllers;
import ru.griz.msfxclient.domain.models.DocBuyModel;
import ru.griz.msfxclient.presentation.commands.Commands;
import ru.griz.msfxclient.presentation.common.JournalView;
import ru.griz.msfxclient.presentation.common.ViewManager;

public class JournalBuyView extends JournalView<DocBuyModel> {

    private final BuyController controller;

    public JournalBuyView() {
        lvData.setOnMouseClicked(this::onLwDataClick);
        controller = Controllers.get(BuyController.class);
    }

    private void onLwDataClick(MouseEvent mouseEvent) {
        DocBuyModel item = selectedItem();
        if ((mouseEvent.getClickCount() == 2) && (item != null)) {
            DocBuyView view = ViewManager.currentView(DocBuyView.class);
            view.loadModel(item);
        }
    }

    @Override
    public String title() {
        return "Поставки";
    }

    @Override
    public void open() {
        ViewManager.setNavCommands(Commands.get(Commands.CMD_BUY_NEW));
        loadData();
    }

    private void loadData() {
        docList.clear();
        docList.addAll(controller.getAll());
    }
}
