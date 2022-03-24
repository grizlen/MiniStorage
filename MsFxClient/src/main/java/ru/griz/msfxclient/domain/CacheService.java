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
import ru.griz.msfxclient.data.rest.RestApi;

import java.util.List;

public class CacheService {

    private static CacheService self;

    private static CacheService self() {
        return self == null ? (self = new CacheService()) : self;
    }

    public static void checkUpdates() {
        System.out.println("Synchronization start...");
        try {
            self().exportProducts();
            self.exportDocuments();
            self.importProducts();
            self.importDocuments();
        } catch (Exception e) {
            System.out.println("Synchronization failed.");
            e.printStackTrace();
        }
    }

    private final RestApi restApi = RestApi.instance();

    private void exportProducts() {
        System.out.println("export products...");
        ProductsRepository productsRepository = DbContext.repository(ProductsRepository.class);
        List<ProductEntity> dbList = productsRepository.findAll().stream()
                .filter(p -> (p.getServerId() == null) || (p.getServerId() == 0))
                .toList();
        dbList.forEach(p -> {
            ProductEntity entity = restApi.postProduct(p);
            entity.setServerId(entity.getId());
            entity.setId(p.getId());
            productsRepository.save(entity);
        });
    }

    private void exportDocuments() {
        System.out.println("export documents...");
        DocumentsRepository documentsRepository = DbContext.repository(DocumentsRepository.class);
        List<DocumentsEntity> dbList = documentsRepository.findAll().stream()
                .filter(d -> (d.getServerId() == null) || (d.getServerId() == 0))
                .toList();
        dbList.forEach(d -> {
            DocumentsEntity entity = restApi.postDocument(d);
            entity.setServerId(entity.getId());
            entity.setId(d.getId());
            documentsRepository.save(entity);
            if (entity.getType().equals(DocumentsTable.TYPE_BUY)) {
                exportBuyDoc(d.getId(), entity.getServerId());
            }
        });
    }

    private void exportBuyDoc(Long id, Long serverId) {
        BuysHeaderRepository headerRepository = DbContext.repository(BuysHeaderRepository.class);
        BuyHeaderEntity headerEntity = headerRepository.findById(id);
    }

    private void importProducts() {
        System.out.println("import products...");
        ProductsRepository productsRepository = DbContext.repository(ProductsRepository.class);

        List<ProductEntity> dbList = productsRepository.findAll();
        List<ProductEntity> serverList = restApi.getAllProducts();

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

    private void importDocuments() {
        System.out.println("import documents...");
        DocumentsRepository documentsRepository = DbContext.repository(DocumentsRepository.class);

        List<DocumentsEntity> dbList = documentsRepository.findAll();
        List<DocumentsEntity> serverList = restApi.getAllDocs();

        for (DocumentsEntity entity: serverList) {
            Long serverId = entity.getId();
            String type = entity.getType();
            if (dbList.stream().noneMatch(item -> serverId.equals(item.getServerId()))) {
                if (type.equals(DocumentsTable.TYPE_BUY)) {
                    entity.setId(null);
                    entity.setServerId(serverId);
                    documentsRepository.save(entity);
                    importBuyDoc(serverId, entity.getId());
                }
            }
        }
    }

    private void importBuyDoc(Long serverId, Long newDocId) {
        BuyHeaderEntity buyHeaderEntity = restApi.getDocBuy(serverId);
        buyHeaderEntity.setId(newDocId);
        buyHeaderEntity.setServerId(serverId);
        BuysHeaderRepository headerRepository = DbContext.repository(BuysHeaderRepository.class);
        headerRepository.add(buyHeaderEntity);

        BuyItemsRepository itemsRepository = DbContext.repository(BuyItemsRepository.class);
        List<BuyItemEntity> dbList = itemsRepository.findAll();
        List<BuyItemEntity> serverList = restApi.getDocBuyItems(serverId);
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
