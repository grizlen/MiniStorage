package ru.griz.msfxclient.data.cache.db.tables;

public interface DbConfig {
    String[] DROP = new String[] {};
    String[] CREATE = new String[] {};
    String[] INIT = new String[] {};

//    String[] DROP = new String[] {
//            ProductsTable.SQL_DROP, DocumentsTable.SQL_DROP, DocBuysTable.SQL_DROP, DocBuyItemsTable.SQL_DROP};
//    String[] CREATE = new String[] {
//            ProductsTable.SQL_CREATE, DocumentsTable.SQL_CREATE, DocBuysTable.SQL_CREATE, DocBuyItemsTable.SQL_CREATE};
//    String[] INIT = new String[] {
//            ProductsTable.SQL_INIT, DocumentsTable.SQL_INIT, DocBuysTable.SQL_INIT, DocBuyItemsTable.SQL_INIT};
}
