package ru.griz.msfxclient.data.cache.db;

import ru.griz.msfxclient.data.cache.db.tables.DocBuyItemsTable;
import ru.griz.msfxclient.data.cache.db.tables.DocBuysTable;
import ru.griz.msfxclient.data.cache.db.tables.DocumentsTable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqLiteDb {
    private static final String DB_NAME = "jdbc:sqlite:cache.db";

    private final SqliteConnection sqliteConnection;

    public SqLiteDb() {
        try {
            Class.forName("org.sqlite.JDBC");
            sqliteConnection = new SqliteConnection();
            init();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void init() {
        String[] drop = new String[] {
                DocumentsTable.SQL_DROP, DocBuysTable.SQL_DROP, DocBuyItemsTable.SQL_DROP};
        String[] create = new String[] {
                DocumentsTable.SQL_CREATE, DocBuysTable.SQL_CREATE, DocBuyItemsTable.SQL_CREATE};
        String[] init = new String[] {
                DocumentsTable.SQL_INIT, DocBuysTable.SQL_INIT, DocBuyItemsTable.SQL_INIT};
        for (String sql : drop) {
            sqliteConnection.execute(sql);
        }
        for (String sql : create) {
            sqliteConnection.execute(sql);
        }
        for (String sql : init) {
            sqliteConnection.execute(sql);
        }
    }

    public void Close() {
        if (sqliteConnection != null) {
            sqliteConnection.close();
        }
    }

    public <T> List<T> executeQuery(String sql, EntityMapper<T> mapper) {
        return sqliteConnection.executeQuery(sql, mapper);
    }

    public Long executeUpdate(String sql) {
        return sqliteConnection.executeUpdate(sql);
    }

    private class SqliteConnection {

        private final Connection connection;

        public SqliteConnection() throws SQLException {
            connection = DriverManager.getConnection(DB_NAME);
        }

        public void close() {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        private void execute(String sql) {
            System.out.println(sql);
            try (Statement statement = connection.createStatement()){
                statement.execute(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public <T> List<T> executeQuery(String sql, EntityMapper<T> mapper) {
            System.out.println(sql);
            ArrayList<T> result = new ArrayList<>();
            try (Statement statement = connection.createStatement()) {
                ResultSet rs = statement.executeQuery(sql);
                while (rs.next()) {
                    result.add(mapper.toEntity(rs));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return result;
        }

        public Long executeUpdate(String sql) {
            System.out.println(sql);
            Long key = null;
            try (Statement statement = connection.createStatement()) {
                if (statement.executeUpdate(sql) == 1) {
                    ResultSet rs = statement.getGeneratedKeys();
                    if (rs.next()) {
                        key = rs.getLong(1);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return key;
        }
    }
}
