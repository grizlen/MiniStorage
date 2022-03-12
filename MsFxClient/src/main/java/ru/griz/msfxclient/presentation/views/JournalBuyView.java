package ru.griz.msfxclient.presentation.views;

import ru.griz.msfxclient.domain.models.DocBuyModel;
import ru.griz.msfxclient.domain.services.BuyService;
import ru.griz.msfxclient.domain.services.Services;
import ru.griz.msfxclient.presentation.commands.Command;
import ru.griz.msfxclient.presentation.commands.Commands;
import ru.griz.msfxclient.presentation.common.JournalView;
import ru.griz.msfxclient.presentation.common.ViewManager;

public class JournalBuyView extends JournalView<DocBuyModel> {

    private final BuyService controller;

    public JournalBuyView() {
        lvData.setOnMouseClicked(event -> {
            DocBuyModel item = selectedItem();
            if ((event.getClickCount() == 2) && (item != null)) {
                DocBuyView view = ViewManager.currentView(DocBuyView.class);
                view.loadModel(item);
            }
        });
        controller = Services.get(BuyService.class);
    }

    @Override
    public String title() {
        return "Поставки";
    }

    @Override
    public Command[] navCommands() {
        return new Command[] {Commands.get(Commands.CMD_BUY_NEW)};
    }

    @Override
    public void open() {
        loadData();
    }

    private void loadData() {
        docList.clear();
        docList.addAll(controller.getAll());
    }
}
