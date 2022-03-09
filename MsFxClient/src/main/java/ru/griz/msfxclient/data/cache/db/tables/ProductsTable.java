package ru.griz.msfxclient.data.cache.db.tables;

public interface ProductsTable extends TableInfo{
    String TABLE = "products";

    String COLUMN_NAME = "name";

    String SQL_CREATE =
            "CREATE TABLE " + TABLE + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_SERVER_ID + " INTEGER, " +
                    COLUMN_NAME + " TEXT NOT NULL);";

    String SQL_DROP =
            "DROP TABLE IF EXISTS " + TABLE + ";";

    String SQL_INIT =
            "INSERT INTO " + TABLE + "(" +
                    COLUMN_NAME + ") " +
                    "VALUES " +
                    "('Стойка 1М (бн)');";
}
