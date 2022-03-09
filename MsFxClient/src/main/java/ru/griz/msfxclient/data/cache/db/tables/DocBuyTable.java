package ru.griz.msfxclient.data.cache.db.tables;

public interface DocBuyTable extends TableInfo{
    String TABLE = "doc_buy";

    String COLUMN_DATE = "date";

    String SQL_CREATE =
            "CREATE TABLE " + TABLE + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_DATE + " TEXT NOT NULL);";

    String SQL_DROP =
            "DROP TABLE IF EXISTS " + TABLE + ";";

    String SQL_INIT =
            "INSERT INTO " + TABLE + "(" +
                    COLUMN_DATE + ") " +
                    "VALUES " +
                    "( '2022-01-01 08:00:00');";
}
