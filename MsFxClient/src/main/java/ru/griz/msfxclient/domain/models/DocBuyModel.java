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
        items.add(item);
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
        private DocBuyModel document;
        private ProductItem product;

        public Long getDocId() {
            return document == null? null : document.id;
        }

        public void setProduct(ProductItem product) {
            this.product = product;
        }

        public ProductItem getProduct() {
            return product;
        }

        @Override
        public String toString() {
            return "doc = " + getDocId() + ", product = " + product;
        }
    }
}
