package ru.griz.msfxclient.domain.documents.buy;

import lombok.Getter;
import lombok.Setter;
import ru.griz.msfxclient.domain.common.DocHeader;

import java.util.ArrayList;
import java.util.List;

public class DocBuy extends DocHeader {
    private List<DocBuyItem> items;

    public DocBuy() {
        super();
        items = new ArrayList<>();
    }

    public List<DocBuyItem> getItems() {
        return items;
    }


    public void addItem(DocBuyItem item) {
        DocBuyItem found = items.stream().filter(i -> i.productId.equals(item.productId)).findAny().orElse(null);
        if (found != null) {
            found.count += item.count;
        } else {
            items.add(item);
        }
    }

    @Override
    public String toString() {
        return "id: " + id + " date: " + getDateStr();
    }

    @Getter
    @Setter
    public static class DocBuyItem {
        private Long id;
        private Long productId;
        private String productName;
        private int count;

        public DocBuyItem() {
            count = 1;
        }

        @Override
        public String toString() {
            return productName + " count: " + count;
        }
    }
}
