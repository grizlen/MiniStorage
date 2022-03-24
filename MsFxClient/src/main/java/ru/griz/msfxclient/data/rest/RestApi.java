package ru.griz.msfxclient.data.rest;

import ru.griz.msfxclient.data.entities.BuyHeaderEntity;
import ru.griz.msfxclient.data.entities.BuyItemEntity;
import ru.griz.msfxclient.data.entities.DocumentsEntity;
import ru.griz.msfxclient.data.entities.ProductEntity;

import java.util.List;

public class RestApi extends RestClient {

    private static RestApi self;

//    private static final String CLIENT_PATH = "https://my-simplecrm.herokuapp.com/api";
    private static final String CLIENT_PATH = "http://localhost/api";
    private static final String API_GET_ALL_PRODUCTS = "/products/";
    private static final String API_GET_ALL_DOCUMENTS = "/doc/";
    private static final String API_GET_DOC_BUY = "/doc/buy/";
    private static final String API_GET_DOC_BUY_ITEMS = "/doc/buy/items/";
    private static final String API_POST_PRODUCT = "/products/";
    private static final String API_POST_DOCUMENT = "/doc/";


    public static RestApi instance() {
        return self == null ? (self = new RestApi()) : self;
    }

    private RestApi() {
        super(CLIENT_PATH);
    }

    public List<ProductEntity> getAllProducts() {
        return getList(API_GET_ALL_PRODUCTS, ProductEntity.class);
    }

    public List<DocumentsEntity> getAllDocs() {
        return getList(API_GET_ALL_DOCUMENTS, DocumentsEntity.class);
    }

    public BuyHeaderEntity getDocBuy(Long id) {
        return get(API_GET_DOC_BUY + id, BuyHeaderEntity.class);
    }

    public List<BuyItemEntity> getDocBuyItems(Long docId) {
        return getList(API_GET_DOC_BUY_ITEMS + docId, BuyItemEntity.class);
    }

    public ProductEntity postProduct(ProductEntity entity) {
        return post(API_POST_PRODUCT, entity, ProductEntity.class);
    }

    public DocumentsEntity postDocument(DocumentsEntity entity) {
        return post(API_POST_DOCUMENT, entity, DocumentsEntity.class);
    }
}
