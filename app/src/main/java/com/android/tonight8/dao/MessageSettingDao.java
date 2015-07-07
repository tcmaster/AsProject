package com.android.tonight8.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.android.tonight8.dao.entity.MessageSetting;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table MESSAGE_SETTING.
*/
public class MessageSettingDao extends AbstractDao<MessageSetting, Long> {

    public static final String TABLENAME = "MESSAGE_SETTING";

    /**
     * Properties of entity MessageSetting.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "ID");
        public final static Property RelateType = new Property(1, Integer.class, "relateType", false, "RELATE_TYPE");
        public final static Property RelateId = new Property(2, Long.class, "relateId", false, "RELATE_ID");
        public final static Property Config = new Property(3, String.class, "config", false, "CONFIG");
    };


    public MessageSettingDao(DaoConfig config) {
        super(config);
    }
    
    public MessageSettingDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'MESSAGE_SETTING' (" + //
                "'ID' INTEGER PRIMARY KEY NOT NULL ," + // 0: id
                "'RELATE_TYPE' INTEGER," + // 1: relateType
                "'RELATE_ID' INTEGER," + // 2: relateId
                "'CONFIG' TEXT);"); // 3: config
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'MESSAGE_SETTING'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, MessageSetting entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        Integer relateType = entity.getRelateType();
        if (relateType != null) {
            stmt.bindLong(2, relateType);
        }
 
        Long relateId = entity.getRelateId();
        if (relateId != null) {
            stmt.bindLong(3, relateId);
        }
 
        String config = entity.getConfig();
        if (config != null) {
            stmt.bindString(4, config);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public MessageSetting readEntity(Cursor cursor, int offset) {
        MessageSetting entity = new MessageSetting( //
            cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // relateType
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // relateId
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // config
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, MessageSetting entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setRelateType(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setRelateId(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setConfig(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(MessageSetting entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(MessageSetting entity) {
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
