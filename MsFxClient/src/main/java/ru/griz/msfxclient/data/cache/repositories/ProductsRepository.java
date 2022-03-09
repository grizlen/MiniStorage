package ru.griz.msfxclient.data.cache.repositories;

import ru.griz.msfxclient.data.cache.db.EntityMapper;
import ru.griz.msfxclient.data.cache.db.FieldValues;
import ru.griz.msfxclient.data.cache.db.Repository;
import ru.griz.msfxclient.data.cache.db.tables.DocumentsTable;
import ru.griz.msfxclient.data.cache.db.tables.ProductsTable;
import ru.griz.msfxclient.data.entities.DocumentsEntity;
import ru.griz.msfxclient.data.entities.ProductEntity;

import java.sql.ResultSet;

public class ProductsRepository extends Repository<ProductEntity> implements ProductsTable {

    private EntityMapper<ProductEntity> mapper = new EntityMapper<>() {
        @Override
        public ProductEntity toEntity(ResultSet rs) {
            ProductEntity result = new ProductEntity();
            result.setId(getLong(rs, COLUMN_ID));
            result.setServerId(getLong(rs, COLUMN_SERVER_ID));
            result.setName(getString(rs, COLUMN_NAME));
            return result;
        }

        @Override
        public FieldValues toFields(ProductEntity entity) {
            FieldValues fv = new FieldValues();
            fv.put(COLUMN_SERVER_ID, entity.getServerId());
            fv.put(COLUMN_NAME, entity.getName());
            return fv;
        }
    };

    @Override
    protected String table() {
        return TABLE;
    }

    @Override
    protected EntityMapper<ProductEntity> mapper() {
        return mapper;
    }
}
