package ru.griz.msfxclient.data.cache.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface EntityMapper<T> {
    T toEntity(ResultSet rs);
    FieldValues toFields(T entity);

    default Long getLong(ResultSet rs, String name) {
        try {
            return rs.getLong(name);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    default Integer getInt(ResultSet rs, String name) {
        try {
            return rs.getInt(name);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    default String getString(ResultSet rs, String name) {
        try {
            return rs.getString(name);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
