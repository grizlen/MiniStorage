package ru.griz.msfxclient.domain.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class DocBuyModel {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    private Long id;
    private Date date;
    private List<BuyItem> items;

    public DocBuyModel() {
        this(null, new Date());
    }

    public DocBuyModel(Long id, Date date) {
        this.id = id;
        this.date = date == null ? new Date() : date;
        items = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void addItem(BuyItem item) {
        item.document = this;
        if (items.stream().noneMatch(i -> i.productId.equals(item.productId))) {
            items.add(item);
        } else {
            items.stream()
                    .filter(i -> i.productId.equals(item.productId))
                    .findAny()
                    .ifPresent(i -> i.setCount(i.count + item.count));
        }
    }
    public void setItems(List<BuyItem> source) {
        items.clear();
        source.forEach(this::addItem);
    }

    public List<BuyItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    @Override
    public String toString() {
        return "Buy id = " + id + ", date = " + formatter.format(date);
    }

    public static class BuyItem {
        private Long id;
        private DocBuyModel document;
        private Long productId;
        private int count;

        public void setId(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }

        public Long getDocId() {
            return document == null? null : document.id;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public Long getProductId() {
            return productId;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getCount() {
            return count;
        }

        @Override
        public String toString() {
            return "doc = " + getDocId() + ", product = " + productId + ", count = " + count;
        }
    }
}
