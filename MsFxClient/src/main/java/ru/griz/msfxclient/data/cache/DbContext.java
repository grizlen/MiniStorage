package ru.griz.msfxclient.data.cache;

import ru.griz.msfxclient.data.cache.db.Repository;
import ru.griz.msfxclient.data.cache.db.SqLiteDb;
import ru.griz.msfxclient.data.cache.db.SqlQueries;

import java.util.HashMap;

public class DbContext {

    private static DbContext self;

    private static DbContext self() {
        return self == null ? self = new DbContext() : self;
    }

    public static <T extends Repository<?>> T repository(Class<T> tClass) {
        T result = self().findRepository(tClass.getName());
        return result == null ? self.createRepository(tClass) : result;
    }

    public static SqlQueries queries() {
        return self().queries;
    }

    private final HashMap<String, Repository<?>> repositories;
    private final SqlQueries queries;

    private DbContext() {
        SqLiteDb db = new SqLiteDb();
        repositories = new HashMap<>();
        queries = new SqlQueries(db);
    }

    private <T extends Repository<?>> T findRepository(String name) {
        return (T) repositories.get(name);
    }

    private <T extends Repository<?>> T createRepository(Class<T> tClass) {
        T result = null;
        try {
            result = tClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result != null) {
            repositories.put(tClass.getName(), result);
        }
        return result;
    }
}
