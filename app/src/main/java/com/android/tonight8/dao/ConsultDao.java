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
import com.android.tonight8.dao.entity.User;

import com.android.tonight8.dao.entity.Consult;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table CONSULT.
*/
public class ConsultDao extends AbstractDao<Consult, Long> {

    public static final String TABLENAME = "CONSULT";

    /**
     * Properties of entity Consult.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "ID");
        public final static Property EventId = new Property(1, Long.class, "eventId", false, "EVENT_ID");
        public final static Property UserId = new Property(2, Long.class, "userId", false, "USER_ID");
        public final static Property Type = new Property(3, Integer.class, "type", false, "TYPE");
        public final static Property Code = new Property(4, String.class, "code", false, "CODE");
        public final static Property QuoteId = new Property(5, Long.class, "quoteId", false, "QUOTE_ID");
        public final static Property Content = new Property(6, String.class, "content", false, "CONTENT");
        public final static Property Date = new Property(7, String.class, "date", false, "DATE");
        public final static Property Time = new Property(8, String.class, "time", false, "TIME");
        public final static Property ReplyTo = new Property(9, String.class, "replyTo", false, "REPLY_TO");
    };

    private DaoSession daoSession;


    public ConsultDao(DaoConfig config) {
        super(config);
    }
    
    public ConsultDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'CONSULT' (" + //
                "'ID' INTEGER PRIMARY KEY NOT NULL ," + // 0: id
                "'EVENT_ID' INTEGER," + // 1: eventId
                "'USER_ID' INTEGER," + // 2: userId
                "'TYPE' INTEGER," + // 3: type
                "'CODE' TEXT," + // 4: code
                "'QUOTE_ID' INTEGER," + // 5: quoteId
                "'CONTENT' TEXT," + // 6: content
                "'DATE' TEXT," + // 7: date
                "'TIME' TEXT," + // 8: time
                "'REPLY_TO' TEXT);"); // 9: replyTo
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'CONSULT'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Consult entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        Long eventId = entity.getEventId();
        if (eventId != null) {
            stmt.bindLong(2, eventId);
        }
 
        Long userId = entity.getUserId();
        if (userId != null) {
            stmt.bindLong(3, userId);
        }
 
        Integer type = entity.getType();
        if (type != null) {
            stmt.bindLong(4, type);
        }
 
        String code = entity.getCode();
        if (code != null) {
            stmt.bindString(5, code);
        }
 
        Long quoteId = entity.getQuoteId();
        if (quoteId != null) {
            stmt.bindLong(6, quoteId);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(7, content);
        }
 
        String date = entity.getDate();
        if (date != null) {
            stmt.bindString(8, date);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(9, time);
        }
 
        String replyTo = entity.getReplyTo();
        if (replyTo != null) {
            stmt.bindString(10, replyTo);
        }
    }

    @Override
    protected void attachEntity(Consult entity) {
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
    public Consult readEntity(Cursor cursor, int offset) {
        Consult entity = new Consult( //
            cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // eventId
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // userId
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3), // type
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // code
            cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5), // quoteId
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // content
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // date
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // time
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9) // replyTo
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Consult entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setEventId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setUserId(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setType(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
        entity.setCode(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setQuoteId(cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5));
        entity.setContent(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setDate(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setTime(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setReplyTo(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Consult entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Consult entity) {
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
            SqlUtils.appendColumns(builder, "T1", daoSession.getUserDao().getAllColumns());
            builder.append(" FROM CONSULT T");
            builder.append(" LEFT JOIN EVENT T0 ON T.'EVENT_ID'=T0.'ID'");
            builder.append(" LEFT JOIN USER T1 ON T.'USER_ID'=T1.'ID'");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected Consult loadCurrentDeep(Cursor cursor, boolean lock) {
        Consult entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Event event = loadCurrentOther(daoSession.getEventDao(), cursor, offset);
        entity.setEvent(event);
        offset += daoSession.getEventDao().getAllColumns().length;

        User user = loadCurrentOther(daoSession.getUserDao(), cursor, offset);
        entity.setUser(user);

        return entity;    
    }

    public Consult loadDeep(Long key) {
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
    public List<Consult> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Consult> list = new ArrayList<Consult>(count);
        
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
    
    protected List<Consult> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Consult> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
