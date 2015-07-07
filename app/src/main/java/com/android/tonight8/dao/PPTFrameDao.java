package com.android.tonight8.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.android.tonight8.dao.entity.PPTFrame;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table PPTFRAME.
*/
public class PPTFrameDao extends AbstractDao<PPTFrame, Long> {

    public static final String TABLENAME = "PPTFRAME";

    /**
     * Properties of entity PPTFrame.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "ID");
        public final static Property RelateType = new Property(1, Integer.class, "relateType", false, "RELATE_TYPE");
        public final static Property RelateId = new Property(2, Long.class, "relateId", false, "RELATE_ID");
        public final static Property FrameNumber = new Property(3, Integer.class, "frameNumber", false, "FRAME_NUMBER");
        public final static Property SecondNumber = new Property(4, Integer.class, "secondNumber", false, "SECOND_NUMBER");
        public final static Property Content = new Property(5, String.class, "content", false, "CONTENT");
    };


    public PPTFrameDao(DaoConfig config) {
        super(config);
    }
    
    public PPTFrameDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'PPTFRAME' (" + //
                "'ID' INTEGER PRIMARY KEY NOT NULL ," + // 0: id
                "'RELATE_TYPE' INTEGER," + // 1: relateType
                "'RELATE_ID' INTEGER," + // 2: relateId
                "'FRAME_NUMBER' INTEGER," + // 3: frameNumber
                "'SECOND_NUMBER' INTEGER," + // 4: secondNumber
                "'CONTENT' TEXT);"); // 5: content
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'PPTFRAME'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, PPTFrame entity) {
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
 
        Integer frameNumber = entity.getFrameNumber();
        if (frameNumber != null) {
            stmt.bindLong(4, frameNumber);
        }
 
        Integer secondNumber = entity.getSecondNumber();
        if (secondNumber != null) {
            stmt.bindLong(5, secondNumber);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(6, content);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public PPTFrame readEntity(Cursor cursor, int offset) {
        PPTFrame entity = new PPTFrame( //
            cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // relateType
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // relateId
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3), // frameNumber
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4), // secondNumber
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5) // content
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, PPTFrame entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setRelateType(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setRelateId(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setFrameNumber(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
        entity.setSecondNumber(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
        entity.setContent(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(PPTFrame entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(PPTFrame entity) {
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
