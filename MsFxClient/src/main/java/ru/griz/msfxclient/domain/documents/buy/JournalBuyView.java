package ru.griz.msfxclient.domain.documents.buy;

import ru.griz.msfxclient.presentation.commands.Commands;
import ru.griz.msfxclient.presentation.views.JournalView;
import ru.griz.msfxclient.presentation.views.MainView;

public class JournalBuyView extends JournalView<DocBuy> {

    @Override
    public String title() {
        return "Поступления";
    }

    @Override
    public Commands.Command[] commands() {
        return new Commands.Command[] {
                Commands.create("+", () ->
                        MainView.currentView(DocumentBuyView.class))
        };
    }

    @Override
    public void open() {
        DocBuy docBuy = new DocBuy();
        docBuy.setId(1L);
        DocBuy.DocBuyItem buyItem = new DocBuy.DocBuyItem();
        buyItem.setProductName("product 1");
        docBuy.addItem(buyItem);
        items.add(docBuy);
    }

    @Override
    protected void onClickItem(DocBuy item) {
        super.onClickItem(item);
        DocumentBuyView documentBuyView = MainView.currentView(DocumentBuyView.class);
        documentBuyView.Load(item);
    }
}
