package ru.griz.msfxclient.domain.models;

public class ProductItem {
    private String name;

    public ProductItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
