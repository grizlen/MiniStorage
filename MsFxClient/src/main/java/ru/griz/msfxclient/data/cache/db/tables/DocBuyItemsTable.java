package ru.griz.msfxclient.data.cache.db.tables;

public interface DocBuyItemsTable extends TableInfo{
    String TABLE = "doc_buy_items";

    String COLUMN_DOC_ID = "doc_id";
    String COLUMN_PRODUCT_ID = "product_id";
    String COLUMN_COUNT = "count";

    String SQL_CREATE =
            "CREATE TABLE " + TABLE + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_DOC_ID + " INTEGER NOT NULL, " +
                    COLUMN_PRODUCT_ID + " INTEGER NOT NULL, " +
                    COLUMN_COUNT + " INTEGER NOT NULL);";

    String SQL_DROP =
            "DROP TABLE IF EXISTS " + TABLE + ";";

    String SQL_INIT =
            "INSERT INTO " + TABLE + "(" +
                    COLUMN_DOC_ID + ", " + COLUMN_PRODUCT_ID + ", " + COLUMN_COUNT + ") " +
                    "VALUES " +
                    "( 1, 1, 10);";
}
