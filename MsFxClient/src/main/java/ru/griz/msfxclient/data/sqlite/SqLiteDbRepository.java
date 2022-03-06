package ru.griz.msfxclient.data.sqlite;

import java.util.List;

public abstract class SqLiteDbRepository<T> {
    public abstract List<T> findAll();
    public abstract void save(T model);
}
