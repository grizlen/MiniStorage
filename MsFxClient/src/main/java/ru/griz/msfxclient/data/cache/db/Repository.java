package ru.griz.msfxclient.data.cache.db;

import ru.griz.msfxclient.data.cache.DbContext;
import ru.griz.msfxclient.data.entities.Entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public abstract class Repository<T extends Entity> {

    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    protected final SqlQueries queries;

    public Repository() {
        queries = DbContext.queries();
    }

    protected Date parseDate(String date) {
        try {
            return FORMATTER.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    protected String formatDate(Date date) {
        return FORMATTER.format(date);
    }

    protected abstract String table();
    protected abstract EntityMapper<T> mapper();

    public List<T> findAll() {
        return queries.select(table()).run(mapper());
    }

    public T findById(Long id) {
        if (id == null) {
            return null;
        }
        return queries.select(table()).whereId(id).runSingle(mapper());
    }

    public void save(T entity) {
        if (entity.getId() == null) {
            queries.insert(table(), entity, mapper());
        } else {
            queries.update(table(), entity, mapper());
        }
    }

    public void delete(Long id) {
        if (id != null) {
            queries.delete(table(), id);
        }
    }
}
