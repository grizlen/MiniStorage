package ru.griz.msfxclient.data.cache.db;

import ru.griz.msfxclient.data.entities.Entity;

import java.util.List;

public class SqlQueries {
    private final SqLiteDb db;

    public SqlQueries(SqLiteDb db) {
        this.db = db;
    }

    public Select select(String table) {
        return new Select(table);
    }

    public <T extends Entity> void insert(String table, T entity, EntityMapper<T> mapper) {
        FieldValues fv = mapper.toFields(entity);
        String fields = fv.fields();
        String values = fv.values();
        String sql = "INSERT INTO " + table + " (" + fields + ") VALUES (" + values + ");";
        int id = db.executeUpdate(sql);
        entity.setId(id == 0 ? null : (long) id);
    }

    public <T extends Entity> void update(String table, T entity, EntityMapper<T> mapper) {
        FieldValues fv = mapper.toFields(entity);
        String values = fv.fieldsAndValues();
        String sql = "UPDATE " + table + " SET " + values +
                " WHERE _id = " + entity.getId() + ";";
        db.executeUpdate(sql);
    }

    public void delete(String table, Long id) {
        String sql = "DELETE FROM " + table + " WHERE _id = " + id + ";";
        db.executeUpdate(sql);
    }

    public class Select {

        private final String table;
        private String where;

        private Select(String table) {
            this.table = table;
        }

        private String sql() {
            StringBuilder result = new StringBuilder("SELECT * FROM ")
                    .append(table);
            if (where != null) {
                result.append(where);
            }
            result.append(";");
            return result.toString();
        }

        public Select whereId(Long id) {
            where = "_id = " + id;
            return this;
        }

        public <T> List<T> run(EntityMapper<T> mapper) {
            return db.executeQuery(sql(), mapper);
        }

        public <T> T runSingle(EntityMapper<T> mapper) {
            List<T> list = db.executeQuery(sql(), mapper);
            if (list.size() == 1) {
                return list.get(0);
            }
            return null;
        }
    }
}
