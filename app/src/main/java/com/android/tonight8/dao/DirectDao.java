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

import com.android.tonight8.dao.entity.Event;
import com.android.tonight8.dao.entity.Org;
import com.android.tonight8.dao.entity.Seller;

import com.android.tonight8.dao.entity.Direct;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table DIRECT.
*/
public class DirectDao extends AbstractDao<Direct, Long> {

    public static final String TABLENAME = "DIRECT";

    /**
     * Properties of entity Direct.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "ID");
        public final static Property EventId = new Property(1, Long.class, "eventId", false, "EVENT_ID");
        public final static Property OrgId = new Property(2, Long.class, "orgId", false, "ORG_ID");
        public final static Property SellerId = new Property(3, Long.class, "sellerId", false, "SELLER_ID");
        public final static Property CommissionType = new Property(4, Integer.class, "commissionType", false, "COMMISSION_TYPE");
        public final static Property CommissionNumber = new Property(5, Float.class, "commissionNumber", false, "COMMISSION_NUMBER");
        public final static Property Status = new Property(6, Integer.class, "status", false, "STATUS");
        public final static Property InviteTime = new Property(7, String.class, "inviteTime", false, "INVITE_TIME");
        public final static Property AnswerTime = new Property(8, String.class, "answerTime", false, "ANSWER_TIME");
    };

    private DaoSession daoSession;


    public DirectDao(DaoConfig config) {
        super(config);
    }
    
    public DirectDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'DIRECT' (" + //
                "'ID' INTEGER PRIMARY KEY NOT NULL ," + // 0: id
                "'EVENT_ID' INTEGER," + // 1: eventId
                "'ORG_ID' INTEGER," + // 2: orgId
                "'SELLER_ID' INTEGER," + // 3: sellerId
                "'COMMISSION_TYPE' INTEGER," + // 4: commissionType
                "'COMMISSION_NUMBER' REAL," + // 5: commissionNumber
                "'STATUS' INTEGER," + // 6: status
                "'INVITE_TIME' TEXT," + // 7: inviteTime
                "'ANSWER_TIME' TEXT);"); // 8: answerTime
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'DIRECT'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Direct entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        Long eventId = entity.getEventId();
        if (eventId != null) {
            stmt.bindLong(2, eventId);
        }
 
        Long orgId = entity.getOrgId();
        if (orgId != null) {
            stmt.bindLong(3, orgId);
        }
 
        Long sellerId = entity.getSellerId();
        if (sellerId != null) {
            stmt.bindLong(4, sellerId);
        }
 
        Integer commissionType = entity.getCommissionType();
        if (commissionType != null) {
            stmt.bindLong(5, commissionType);
        }
 
        Float commissionNumber = entity.getCommissionNumber();
        if (commissionNumber != null) {
            stmt.bindDouble(6, commissionNumber);
        }
 
        Integer status = entity.getStatus();
        if (status != null) {
            stmt.bindLong(7, status);
        }
 
        String inviteTime = entity.getInviteTime();
        if (inviteTime != null) {
            stmt.bindString(8, inviteTime);
        }
 
        String answerTime = entity.getAnswerTime();
        if (answerTime != null) {
            stmt.bindString(9, answerTime);
        }
    }

    @Override
    protected void attachEntity(Direct entity) {
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
    public Direct readEntity(Cursor cursor, int offset) {
        Direct entity = new Direct( //
            cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // eventId
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // orgId
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3), // sellerId
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4), // commissionType
            cursor.isNull(offset + 5) ? null : cursor.getFloat(offset + 5), // commissionNumber
            cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6), // status
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // inviteTime
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8) // answerTime
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Direct entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setEventId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setOrgId(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setSellerId(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.setCommissionType(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
        entity.setCommissionNumber(cursor.isNull(offset + 5) ? null : cursor.getFloat(offset + 5));
        entity.setStatus(cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6));
        entity.setInviteTime(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setAnswerTime(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Direct entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Direct entity) {
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
            SqlUtils.appendColumns(builder, "T1", daoSession.getOrgDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T2", daoSession.getSellerDao().getAllColumns());
            builder.append(" FROM DIRECT T");
            builder.append(" LEFT JOIN EVENT T0 ON T.'EVENT_ID'=T0.'ID'");
            builder.append(" LEFT JOIN ORG T1 ON T.'ORG_ID'=T1.'ID'");
            builder.append(" LEFT JOIN SELLER T2 ON T.'SELLER_ID'=T2.'ID'");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected Direct loadCurrentDeep(Cursor cursor, boolean lock) {
        Direct entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Event event = loadCurrentOther(daoSession.getEventDao(), cursor, offset);
        entity.setEvent(event);
        offset += daoSession.getEventDao().getAllColumns().length;

        Org org = loadCurrentOther(daoSession.getOrgDao(), cursor, offset);
        entity.setOrg(org);
        offset += daoSession.getOrgDao().getAllColumns().length;

        Seller seller = loadCurrentOther(daoSession.getSellerDao(), cursor, offset);
        entity.setSeller(seller);

        return entity;    
    }

    public Direct loadDeep(Long key) {
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
    public List<Direct> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Direct> list = new ArrayList<Direct>(count);
        
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
    
    protected List<Direct> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Direct> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
