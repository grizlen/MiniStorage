package ru.griz.msfxclient.domain.controllers;

import ru.griz.msfxclient.data.cache.DbContext;
import ru.griz.msfxclient.data.cache.db.tables.DocumentsTable;
import ru.griz.msfxclient.data.cache.repositories.DocumentsRepository;
import ru.griz.msfxclient.data.entities.BuyHeaderEntity;
import ru.griz.msfxclient.data.cache.repositories.BuysHeaderRepository;
import ru.griz.msfxclient.data.entities.DocumentsEntity;
import ru.griz.msfxclient.domain.models.DocBuyModel;
import ru.griz.msfxclient.domain.models.ProductItem;

import java.util.List;

public class BuyController {

    private final DocumentsRepository documentsRepository = DbContext.repository(DocumentsRepository.class);
    private final BuysHeaderRepository headerRepository = DbContext.repository(BuysHeaderRepository.class);

    private static final Converter<DocBuyModel, BuyHeaderEntity> fromEntity =
            header -> new DocBuyModel(header.getId(), header.getDate());

    private static final ListConverter<DocBuyModel, BuyHeaderEntity> fromListEntity =
            headers -> headers.stream().map(fromEntity::convert).toList();

    private static final Converter<BuyHeaderEntity, DocBuyModel> fromModel =
            doc -> new BuyHeaderEntity(doc.getId(), doc.getDate());

    public List<DocBuyModel> getAll() {
        return fromListEntity.convert(headerRepository.findAll());
    }

    public void save(DocBuyModel model) {
        if (model.getId() == null) {
            DocumentsEntity documentsEntity = new DocumentsEntity(null, DocumentsTable.TYPE_BUY);
            documentsRepository.save(documentsEntity);
            model.setId(documentsEntity.getId());
            headerRepository.add(fromModel.convert(model));
        } else {
            headerRepository.save(fromModel.convert(model));
        }
    }

    public List<DocBuyModel.BuyItem> getItems(DocBuyModel model) {
        DocBuyModel.BuyItem item;
        item = new DocBuyModel.BuyItem();
        item.setProduct(new ProductItem("product_1"));
        model.setItems(List.of(item));
        return model.getItems();
    }
}
