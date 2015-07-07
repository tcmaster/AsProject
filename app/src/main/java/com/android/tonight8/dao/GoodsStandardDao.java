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

import com.android.tonight8.dao.entity.Goods;

import com.android.tonight8.dao.entity.GoodsStandard;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table GOODS_STANDARD.
*/
public class GoodsStandardDao extends AbstractDao<GoodsStandard, Long> {

    public static final String TABLENAME = "GOODS_STANDARD";

    /**
     * Properties of entity GoodsStandard.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "ID");
        public final static Property GoodsId = new Property(1, Long.class, "goodsId", false, "GOODS_ID");
        public final static Property Content = new Property(2, String.class, "content", false, "CONTENT");
    };

    private DaoSession daoSession;


    public GoodsStandardDao(DaoConfig config) {
        super(config);
    }
    
    public GoodsStandardDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'GOODS_STANDARD' (" + //
                "'ID' INTEGER PRIMARY KEY NOT NULL ," + // 0: id
                "'GOODS_ID' INTEGER," + // 1: goodsId
                "'CONTENT' TEXT);"); // 2: content
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'GOODS_STANDARD'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, GoodsStandard entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        Long goodsId = entity.getGoodsId();
        if (goodsId != null) {
            stmt.bindLong(2, goodsId);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(3, content);
        }
    }

    @Override
    protected void attachEntity(GoodsStandard entity) {
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
    public GoodsStandard readEntity(Cursor cursor, int offset) {
        GoodsStandard entity = new GoodsStandard( //
            cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // goodsId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // content
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, GoodsStandard entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setGoodsId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setContent(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(GoodsStandard entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(GoodsStandard entity) {
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
            SqlUtils.appendColumns(builder, "T0", daoSession.getGoodsDao().getAllColumns());
            builder.append(" FROM GOODS_STANDARD T");
            builder.append(" LEFT JOIN GOODS T0 ON T.'GOODS_ID'=T0.'ID'");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected GoodsStandard loadCurrentDeep(Cursor cursor, boolean lock) {
        GoodsStandard entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Goods goods = loadCurrentOther(daoSession.getGoodsDao(), cursor, offset);
        entity.setGoods(goods);

        return entity;    
    }

    public GoodsStandard loadDeep(Long key) {
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
    public List<GoodsStandard> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<GoodsStandard> list = new ArrayList<GoodsStandard>(count);
        
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
    
    protected List<GoodsStandard> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<GoodsStandard> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
