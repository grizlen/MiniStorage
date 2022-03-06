package ru.griz.msfxclient.domain.models;

public class DocumentBuyItem {
    private Long docId;
    private ProductItem product;

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    public Long getDocId() {
        return docId;
    }

    public void setProduct(ProductItem product) {
        this.product = product;
    }

    public ProductItem getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return "doc = " + docId + ", product = " + product;
    }
}
