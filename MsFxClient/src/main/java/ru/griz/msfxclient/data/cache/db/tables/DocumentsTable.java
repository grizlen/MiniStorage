package ru.griz.msfxclient.data.cache.db.tables;

public interface DocumentsTable extends TableInfo{
    String TABLE = "documents";

    String COLUMN_TYPE = "type";

    String TYPE_BUY = "BUY";

    String SQL_CREATE =
            "CREATE TABLE " + TABLE + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TYPE + " TEXT NOT NULL);";

    String SQL_DROP =
            "DROP TABLE IF EXISTS " + TABLE + ";";

    String SQL_INIT =
            "INSERT INTO " + TABLE + "(" +
                    COLUMN_TYPE + ") " +
                    "VALUES " +
                    "( '" + TYPE_BUY + "');";
}
