package ru.griz.msfxclient.data.cache.db.tables;

public interface DocBuysTable extends TableInfo{
    String TABLE = "doc_buys";

    String COLUMN_DATE = "date";

    String SQL_CREATE =
            "CREATE TABLE " + TABLE + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_DATE + " TEXT NOT NULL);";

    String SQL_DROP =
            "DROP TABLE IF EXISTS " + TABLE + ";";

    String SQL_INIT =
            "INSERT INTO " + TABLE + "(" +
                    COLUMN_ID + ", " + COLUMN_DATE + ") " +
                    "VALUES " +
                    "( 1, '2022-01-01 08:00:00');";
}
