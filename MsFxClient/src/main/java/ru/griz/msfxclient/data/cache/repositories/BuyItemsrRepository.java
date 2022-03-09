package ru.griz.msfxclient.data.cache.repositories;

import ru.griz.msfxclient.data.cache.db.EntityMapper;
import ru.griz.msfxclient.data.cache.db.FieldValues;
import ru.griz.msfxclient.data.cache.db.Repository;
import ru.griz.msfxclient.data.cache.db.tables.DocBuyItemsTable;
import ru.griz.msfxclient.data.cache.db.tables.DocBuysTable;
import ru.griz.msfxclient.data.entities.BuyHeaderEntity;
import ru.griz.msfxclient.data.entities.BuyItemEntity;

import java.sql.ResultSet;

public class BuyItemsrRepository extends Repository<BuyItemEntity> implements DocBuyItemsTable {

    private EntityMapper<BuyItemEntity> mapper = new EntityMapper<>() {
        @Override
        public BuyItemEntity toEntity(ResultSet rs) {
            BuyItemEntity result = new BuyItemEntity();
            result.setId(getLong(rs, COLUMN_ID));
            result.setDocId(getLong(rs, COLUMN_DOC_ID));
            result.setProductId(getLong(rs, COLUMN_PRODUCT_ID));
            result.setCount(getInt(rs, COLUMN_COUNT));
            return result;
        }

        @Override
        public FieldValues toFields(BuyItemEntity entity) {
            FieldValues fv = new FieldValues();
            fv.put(COLUMN_DOC_ID, entity.getDocId());
            fv.put(COLUMN_PRODUCT_ID, entity.getProductId());
            fv.put(COLUMN_COUNT, entity.getCount());
            return fv;
        }
    };

    @Override
    protected String table() {
        return TABLE;
    }

    @Override
    protected EntityMapper<BuyItemEntity> mapper() {
        return mapper;
    }
}
