package ru.griz.msfxclient.domain.controllers;

import ru.griz.msfxclient.data.entities.BuyHeader;
import ru.griz.msfxclient.data.repositories.BuyHeaderRepository;
import ru.griz.msfxclient.domain.models.DocumentBuy;
import ru.griz.msfxclient.domain.models.DocumentBuyItem;
import ru.griz.msfxclient.domain.models.ProductItem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BuyController {
    private static BuyController instance;
    private long lastId;

    private BuyHeaderRepository headerRepository;
    private ArrayList<DocumentBuyItem> items;

    public static BuyController instance() {
        return instance == null ? (instance = new BuyController()) : instance;
    }

    public BuyController() {
        headerRepository = new BuyHeaderRepository();
        items = new ArrayList<>();
    }

    public List<DocumentBuy> getAll() {
        List<DocumentBuy> result = new ArrayList<>();
        result.addAll(headerRepository.findAll().stream()
                .map(this::toModel)
                .toList());
        return result;
    }

    public void save(DocumentBuy model) {
        headerRepository.save(toEntity(model));
    }

    private BuyHeader toEntity(DocumentBuy model) {
        return new BuyHeader(
                model.getId(),
                model.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
    }

    private DocumentBuy toModel(BuyHeader entity) {
        return new DocumentBuy(entity.getId(), LocalDateTime.parse(entity.getDate()));
    }

    public List<DocumentBuyItem> getItems(DocumentBuy model) {
        items.clear();
        DocumentBuyItem item;
        item = new DocumentBuyItem();
        item.setDocId(model.getId());
        item.setProduct(new ProductItem("product_1"));
        items.add(item);
        return items;
    }
}
