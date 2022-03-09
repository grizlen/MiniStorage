package ru.griz.msfxclient.data.cache.repositories;

import ru.griz.msfxclient.data.cache.db.EntityMapper;
import ru.griz.msfxclient.data.cache.db.FieldValues;
import ru.griz.msfxclient.data.cache.db.Repository;
import ru.griz.msfxclient.data.cache.db.tables.DocumentsTable;
import ru.griz.msfxclient.data.entities.DocumentsEntity;

import java.sql.ResultSet;

public class DocumentsRepository extends Repository<DocumentsEntity> implements DocumentsTable {

    private EntityMapper<DocumentsEntity> mapper = new EntityMapper<>() {
        @Override
        public DocumentsEntity toEntity(ResultSet rs) {
            DocumentsEntity result = new DocumentsEntity();
            result.setId(getLong(rs, COLUMN_ID));
            result.setServerId(getLong(rs, COLUMN_SERVER_ID));
            result.setType(getString(rs, COLUMN_TYPE));
            return result;
        }

        @Override
        public FieldValues toFields(DocumentsEntity entity) {
            FieldValues fv = new FieldValues();
            fv.put(COLUMN_SERVER_ID, entity.getServerId());
            fv.put(COLUMN_TYPE, entity.getType());
            return fv;
        }
    };

    @Override
    protected String table() {
        return TABLE;
    }

    @Override
    protected EntityMapper<DocumentsEntity> mapper() {
        return mapper;
    }
}
