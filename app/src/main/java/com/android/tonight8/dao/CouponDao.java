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

import com.android.tonight8.dao.entity.CouponProvide;
import com.android.tonight8.dao.entity.Event;

import com.android.tonight8.dao.entity.Coupon;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table COUPON.
*/
public class CouponDao extends AbstractDao<Coupon, Long> {

    public static final String TABLENAME = "COUPON";

    /**
     * Properties of entity Coupon.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "ID");
        public final static Property EventId = new Property(1, Long.class, "eventId", false, "EVENT_ID");
        public final static Property CouponProvideId = new Property(2, Long.class, "couponProvideId", false, "COUPON_PROVIDE_ID");
        public final static Property RelateType = new Property(3, Integer.class, "relateType", false, "RELATE_TYPE");
        public final static Property RelateId = new Property(4, Integer.class, "relateId", false, "RELATE_ID");
        public final static Property Type = new Property(5, Integer.class, "type", false, "TYPE");
        public final static Property Code = new Property(6, String.class, "code", false, "CODE");
        public final static Property Status = new Property(7, Integer.class, "status", false, "STATUS");
        public final static Property UsedDateTime = new Property(8, String.class, "usedDateTime", false, "USED_DATE_TIME");
    };

    private DaoSession daoSession;


    public CouponDao(DaoConfig config) {
        super(config);
    }
    
    public CouponDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'COUPON' (" + //
                "'ID' INTEGER PRIMARY KEY NOT NULL ," + // 0: id
                "'EVENT_ID' INTEGER," + // 1: eventId
                "'COUPON_PROVIDE_ID' INTEGER," + // 2: couponProvideId
                "'RELATE_TYPE' INTEGER," + // 3: relateType
                "'RELATE_ID' INTEGER," + // 4: relateId
                "'TYPE' INTEGER," + // 5: type
                "'CODE' TEXT," + // 6: code
                "'STATUS' INTEGER," + // 7: status
                "'USED_DATE_TIME' TEXT);"); // 8: usedDateTime
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'COUPON'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Coupon entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        Long eventId = entity.getEventId();
        if (eventId != null) {
            stmt.bindLong(2, eventId);
        }
 
        Long couponProvideId = entity.getCouponProvideId();
        if (couponProvideId != null) {
            stmt.bindLong(3, couponProvideId);
        }
 
        Integer relateType = entity.getRelateType();
        if (relateType != null) {
            stmt.bindLong(4, relateType);
        }
 
        Integer relateId = entity.getRelateId();
        if (relateId != null) {
            stmt.bindLong(5, relateId);
        }
 
        Integer type = entity.getType();
        if (type != null) {
            stmt.bindLong(6, type);
        }
 
        String code = entity.getCode();
        if (code != null) {
            stmt.bindString(7, code);
        }
 
        Integer status = entity.getStatus();
        if (status != null) {
            stmt.bindLong(8, status);
        }
 
        String usedDateTime = entity.getUsedDateTime();
        if (usedDateTime != null) {
            stmt.bindString(9, usedDateTime);
        }
    }

    @Override
    protected void attachEntity(Coupon entity) {
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
    public Coupon readEntity(Cursor cursor, int offset) {
        Coupon entity = new Coupon( //
            cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // eventId
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // couponProvideId
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3), // relateType
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4), // relateId
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5), // type
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // code
            cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7), // status
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8) // usedDateTime
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Coupon entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setEventId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setCouponProvideId(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setRelateType(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
        entity.setRelateId(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
        entity.setType(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
        entity.setCode(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setStatus(cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7));
        entity.setUsedDateTime(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Coupon entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Coupon entity) {
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
            SqlUtils.appendColumns(builder, "T0", daoSession.getEventDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T1", daoSession.getCouponProvideDao().getAllColumns());
            builder.append(" FROM COUPON T");
            builder.append(" LEFT JOIN EVENT T0 ON T.'EVENT_ID'=T0.'ID'");
            builder.append(" LEFT JOIN COUPON_PROVIDE T1 ON T.'COUPON_PROVIDE_ID'=T1.'ID'");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected Coupon loadCurrentDeep(Cursor cursor, boolean lock) {
        Coupon entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Event event = loadCurrentOther(daoSession.getEventDao(), cursor, offset);
        entity.setEvent(event);
        offset += daoSession.getEventDao().getAllColumns().length;

        CouponProvide couponProvide = loadCurrentOther(daoSession.getCouponProvideDao(), cursor, offset);
        entity.setCouponProvide(couponProvide);

        return entity;    
    }

    public Coupon loadDeep(Long key) {
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
    public List<Coupon> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Coupon> list = new ArrayList<Coupon>(count);
        
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
    
    protected List<Coupon> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Coupon> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
