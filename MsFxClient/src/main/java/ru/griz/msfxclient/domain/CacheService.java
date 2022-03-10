package ru.griz.msfxclient.domain;

import ru.griz.msfxclient.data.cache.DbContext;
import ru.griz.msfxclient.data.cache.db.tables.DocumentsTable;
import ru.griz.msfxclient.data.cache.repositories.BuyItemsRepository;
import ru.griz.msfxclient.data.cache.repositories.BuysHeaderRepository;
import ru.griz.msfxclient.data.cache.repositories.DocumentsRepository;
import ru.griz.msfxclient.data.cache.repositories.ProductsRepository;
import ru.griz.msfxclient.data.entities.BuyHeaderEntity;
import ru.griz.msfxclient.data.entities.BuyItemEntity;
import ru.griz.msfxclient.data.entities.DocumentsEntity;
import ru.griz.msfxclient.data.entities.ProductEntity;
import ru.griz.msfxclient.data.rest.RestClient;

import java.util.List;

public class CacheService {

    private static CacheService self;

    private static CacheService self() {
        return self == null ? (self = new CacheService()) : self;
    }

    public static void checkUpdates() {
        self().loadProducts();
        self.loadDocuments();
    }

    private final RestClient restClient = RestClient.instance();

    private void loadProducts() {
        ProductsRepository productsRepository = DbContext.repository(ProductsRepository.class);

        List<ProductEntity> dbList = productsRepository.findAll();
        List<ProductEntity> serverList = restClient.getList("/products/", ProductEntity.class);

        for (ProductEntity entity: serverList) {
            Long serverId = entity.getId();
            if (dbList.stream().noneMatch(item -> serverId.equals(item.getServerId()))) {
                ProductEntity newEntity = new ProductEntity();
                newEntity.setServerId(serverId);
                newEntity.setName(entity.getName());
                productsRepository.save(newEntity);
            }
        }
    }

    private void loadDocuments() {
        DocumentsRepository documentsRepository = DbContext.repository(DocumentsRepository.class);

        List<DocumentsEntity> dbList = documentsRepository.findAll();
        List<DocumentsEntity> serverList = restClient.getList("/documents/", DocumentsEntity.class);

        for (DocumentsEntity entity: serverList) {
            Long serverId = entity.getId();
            String type = entity.getType();
            if (dbList.stream().noneMatch(item -> serverId.equals(item.getServerId()))) {
                if (type.equals(DocumentsTable.TYPE_BUY)) {
                    entity.setId(null);
                    entity.setServerId(serverId);
                    documentsRepository.save(entity);
                    loadBuyDoc(serverId, entity.getId());
                }
            }
        }
    }

    private void loadBuyDoc(Long serverId, Long newDocId) {
        BuyHeaderEntity buyHeaderEntity = restClient.get("/buy/" + serverId, BuyHeaderEntity.class);
        buyHeaderEntity.setId(newDocId);
        buyHeaderEntity.setServerId(serverId);
        BuysHeaderRepository headerRepository = DbContext.repository(BuysHeaderRepository.class);
        headerRepository.add(buyHeaderEntity);

        BuyItemsRepository itemsRepository = DbContext.repository(BuyItemsRepository.class);
        List<BuyItemEntity> dbList = itemsRepository.findAll();
        List<BuyItemEntity> serverList = restClient.getList("/buy/items/" + serverId, BuyItemEntity.class);
        for (BuyItemEntity item: serverList) {
            Long itemServerId = item.getId();
            if (dbList.stream().noneMatch(i -> itemServerId.equals(i.getServerId()))) {
                item.setId(null);
                item.setServerId(serverId);
                item.setDocId(buyHeaderEntity.getId());
                itemsRepository.save(item);
            }
        }
    }
}
