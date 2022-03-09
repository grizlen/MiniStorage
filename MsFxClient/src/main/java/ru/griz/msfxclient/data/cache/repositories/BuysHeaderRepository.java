package ru.griz.msfxclient.data.cache.repositories;

import ru.griz.msfxclient.data.cache.db.FieldValues;
import ru.griz.msfxclient.data.cache.db.Repository;
import ru.griz.msfxclient.data.cache.db.EntityMapper;
import ru.griz.msfxclient.data.cache.db.tables.DocBuysTable;
import ru.griz.msfxclient.data.entities.BuyHeaderEntity;

import java.sql.ResultSet;

public class BuysHeaderRepository extends Repository<BuyHeaderEntity> implements DocBuysTable {

    private EntityMapper<BuyHeaderEntity> mapper = new EntityMapper<>() {
        @Override
        public BuyHeaderEntity toEntity(ResultSet rs) {
            BuyHeaderEntity result = new BuyHeaderEntity();
            result.setId(getLong(rs, COLUMN_ID));
            result.setDate(parseDate(getString(rs, COLUMN_DATE)));
            return result;
        }

        @Override
        public FieldValues toFields(BuyHeaderEntity entity) {
            FieldValues fv = new FieldValues();
            fv.put(COLUMN_ID, entity.getId());
            fv.put(COLUMN_DATE, formatDate(entity.getDate()));
            return fv;
        }
    };

    @Override
    protected String table() {
        return TABLE;
    }

    @Override
    protected EntityMapper<BuyHeaderEntity> mapper() {
        return mapper;
    }
}
