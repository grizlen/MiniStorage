package ru.griz.msfxclient.domain.controllers;

import ru.griz.msfxclient.data.cache.DbContext;
import ru.griz.msfxclient.data.cache.db.tables.DocumentsTable;
import ru.griz.msfxclient.data.cache.repositories.BuyItemsRepository;
import ru.griz.msfxclient.data.cache.repositories.BuysHeaderRepository;
import ru.griz.msfxclient.data.cache.repositories.DocumentsRepository;
import ru.griz.msfxclient.data.entities.BuyHeaderEntity;
import ru.griz.msfxclient.data.entities.BuyItemEntity;
import ru.griz.msfxclient.data.entities.DocumentsEntity;
import ru.griz.msfxclient.domain.models.DocBuyModel;

import java.util.List;

public class BuyController {

    private final DocumentsRepository documentsRepository = DbContext.repository(DocumentsRepository.class);
    private final BuysHeaderRepository headerRepository = DbContext.repository(BuysHeaderRepository.class);
    private final BuyItemsRepository itemsRepository = DbContext.repository(BuyItemsRepository.class);

    private static final Converter<DocBuyModel, BuyHeaderEntity> fromEntity =
            header -> new DocBuyModel(header.getId(), header.getDate());

    private static final ListConverter<DocBuyModel, BuyHeaderEntity> fromListEntity =
            headers -> headers.stream().map(fromEntity::convert).toList();

    private static final Converter<BuyHeaderEntity, DocBuyModel> fromModel =
            doc -> new BuyHeaderEntity(doc.getId(), doc.getDate());

    private static final Converter<DocBuyModel.BuyItem, BuyItemEntity> fromItemEntity =
            entity -> {
                DocBuyModel.BuyItem item = new DocBuyModel.BuyItem();
                item.setId(entity.getId());
                item.setProductId(entity.getProductId());
                item.setCount(entity.getCount());
                return item;
            };

    private static final ListConverter<DocBuyModel.BuyItem, BuyItemEntity> fromListItemEntities =
            list -> list.stream().map(fromItemEntity::convert).toList();

    private static final Converter<BuyItemEntity, DocBuyModel.BuyItem> fromBuyItem =
            item -> {
                BuyItemEntity entity = new BuyItemEntity();
                entity.setId(item.getId());
                entity.setDocId(item.getDocId());
                entity.setProductId(item.getProductId());
                entity.setCount(item.getCount());
                return entity;
            };

    public List<DocBuyModel> getAll() {
        System.out.println("BuyController getAll");
        return fromListEntity.convert(headerRepository.findAll());
    }

    public void save(DocBuyModel model) {
        System.out.println("BuyController save: " + model);
        if (model.getId() == null) {
            DocumentsEntity documentsEntity = new DocumentsEntity(null, DocumentsTable.TYPE_BUY);
            documentsRepository.save(documentsEntity);
            model.setId(documentsEntity.getId());
            headerRepository.add(fromModel.convert(model));
        } else {
            headerRepository.save(fromModel.convert(model));
        }
        model.getItems().forEach(item -> itemsRepository.save(fromBuyItem.convert(item)));
    }

    public List<DocBuyModel.BuyItem> getItems(DocBuyModel model) {
        System.out.println("BuyController getItems of: " + model);
        List<DocBuyModel.BuyItem> items = fromListItemEntities.convert(itemsRepository.findByDocId(model.getId()));
        model.setItems(items);
        return model.getItems();
    }
}
