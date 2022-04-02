package ru.griz.msfxclient.domain.documents.buy;

import ru.griz.msfxclient.presentation.commands.Commands;
import ru.griz.msfxclient.presentation.views.DocumentView;

public class DocumentBuyView extends DocumentView<DocBuy, DocBuy.DocBuyItem> {
    @Override
    public String title() {
        return "Поступление";
    }

    @Override
    public Commands.Command[] commands() {
        return new Commands.Command[] {
                Commands.create("Сохранить", this::save),
                Commands.CMD_JOURNAL_BUY
        };
    }

    @Override
    public void open() {
        document = new DocBuy();
        updateModel();
    }

    private void updateModel() {
        System.out.println("updateModel " + document);
        headerView.setId(document.getId());
        headerView.setDate(document.getDateStr());
        tableView.LoadItems(document.getItems());
    }

    @Override
    protected void Load(DocBuy doc) {
        super.Load(doc);
        updateModel();
    }

    private void save() {
        System.out.println("SAVE");
        Commands.CMD_JOURNAL_BUY.exec();
    }
}
