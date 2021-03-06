package com.android.tonight8.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.android.tonight8.dao.entity.GoodsStock;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table GOODS_STOCK.
*/
public class GoodsStockDao extends AbstractDao<GoodsStock, Long> {

    public static final String TABLENAME = "GOODS_STOCK";

    /**
     * Properties of entity GoodsStock.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "ID");
        public final static Property Code = new Property(1, String.class, "code", false, "CODE");
        public final static Property KeepNumber = new Property(2, Integer.class, "keepNumber", false, "KEEP_NUMBER");
    };


    public GoodsStockDao(DaoConfig config) {
        super(config);
    }
    
    public GoodsStockDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'GOODS_STOCK' (" + //
                "'ID' INTEGER PRIMARY KEY NOT NULL ," + // 0: id
                "'CODE' TEXT," + // 1: code
                "'KEEP_NUMBER' INTEGER);"); // 2: keepNumber
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'GOODS_STOCK'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, GoodsStock entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String code = entity.getCode();
        if (code != null) {
            stmt.bindString(2, code);
        }
 
        Integer keepNumber = entity.getKeepNumber();
        if (keepNumber != null) {
            stmt.bindLong(3, keepNumber);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public GoodsStock readEntity(Cursor cursor, int offset) {
        GoodsStock entity = new GoodsStock( //
            cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // code
            cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2) // keepNumber
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, GoodsStock entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setCode(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setKeepNumber(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(GoodsStock entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(GoodsStock entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
