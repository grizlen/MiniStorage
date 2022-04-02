package ru.griz.msfxclient.domain.documents.buy;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.griz.msfxclient.presentation.commands.Commands;
import ru.griz.msfxclient.presentation.views.JournalView;
import ru.griz.msfxclient.presentation.views.MainView;

@Component
public class JournalBuyView extends JournalView<DocBuy> {

    @Autowired
    private DocBuyService docBuyService = new DocBuyService();

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
        loadItems(docBuyService.getAll());
//        DocBuy docBuy = new DocBuy();
//        docBuy.setId(1L);
//        DocBuy.DocBuyItem buyItem = new DocBuy.DocBuyItem();
//        buyItem.setProductName("product 1");
//        docBuy.addItem(buyItem);
//        items.add(docBuy);
    }

    @Override
    protected void onClickItem(DocBuy item) {
        super.onClickItem(item);
        DocumentBuyView documentBuyView = MainView.currentView(DocumentBuyView.class);
        documentBuyView.Load(item);
    }
}
