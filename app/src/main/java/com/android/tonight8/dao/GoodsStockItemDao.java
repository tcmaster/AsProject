package com.android.tonight8.dao;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.internal.DaoConfig;

import com.android.tonight8.dao.entity.GoodsSpecification;

import com.android.tonight8.dao.entity.GoodsStockItem;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table GOODS_STOCK_ITEM.
*/
public class GoodsStockItemDao extends AbstractDao<GoodsStockItem, Long> {

    public static final String TABLENAME = "GOODS_STOCK_ITEM";

    /**
     * Properties of entity GoodsStockItem.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "ID");
        public final static Property GoodsSpecificationId = new Property(1, Long.class, "goodsSpecificationId", false, "GOODS_SPECIFICATION_ID");
        public final static Property Code = new Property(2, String.class, "code", false, "CODE");
        public final static Property Name = new Property(3, String.class, "name", false, "NAME");
    };

    private DaoSession daoSession;


    public GoodsStockItemDao(DaoConfig config) {
        super(config);
    }
    
    public GoodsStockItemDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'GOODS_STOCK_ITEM' (" + //
                "'ID' INTEGER PRIMARY KEY NOT NULL ," + // 0: id
                "'GOODS_SPECIFICATION_ID' INTEGER," + // 1: goodsSpecificationId
                "'CODE' TEXT," + // 2: code
                "'NAME' TEXT);"); // 3: name
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'GOODS_STOCK_ITEM'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, GoodsStockItem entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        Long goodsSpecificationId = entity.getGoodsSpecificationId();
        if (goodsSpecificationId != null) {
            stmt.bindLong(2, goodsSpecificationId);
        }
 
        String code = entity.getCode();
        if (code != null) {
            stmt.bindString(3, code);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(4, name);
        }
    }

    @Override
    protected void attachEntity(GoodsStockItem entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public GoodsStockItem readEntity(Cursor cursor, int offset) {
        GoodsStockItem entity = new GoodsStockItem( //
            cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // goodsSpecificationId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // code
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // name
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, GoodsStockItem entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setGoodsSpecificationId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setCode(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(GoodsStockItem entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(GoodsStockItem entity) {
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
    
    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getGoodsSpecificationDao().getAllColumns());
            builder.append(" FROM GOODS_STOCK_ITEM T");
            builder.append(" LEFT JOIN GOODS_SPECIFICATION T0 ON T.'GOODS_SPECIFICATION_ID'=T0.'ID'");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected GoodsStockItem loadCurrentDeep(Cursor cursor, boolean lock) {
        GoodsStockItem entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        GoodsSpecification goodsSpecification = loadCurrentOther(daoSession.getGoodsSpecificationDao(), cursor, offset);
        entity.setGoodsSpecification(goodsSpecification);

        return entity;    
    }

    public GoodsStockItem loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<GoodsStockItem> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<GoodsStockItem> list = new ArrayList<GoodsStockItem>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<GoodsStockItem> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<GoodsStockItem> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
