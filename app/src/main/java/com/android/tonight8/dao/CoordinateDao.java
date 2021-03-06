package com.android.tonight8.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.android.tonight8.dao.entity.Coordinate;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table COORDINATE.
*/
public class CoordinateDao extends AbstractDao<Coordinate, Integer> {

    public static final String TABLENAME = "COORDINATE";

    /**
     * Properties of entity Coordinate.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, int.class, "id", true, "ID");
        public final static Property RelateType = new Property(1, Integer.class, "relateType", false, "RELATE_TYPE");
        public final static Property RelateId = new Property(2, Integer.class, "relateId", false, "RELATE_ID");
        public final static Property Address = new Property(3, String.class, "address", false, "ADDRESS");
        public final static Property ProvinceCode = new Property(4, String.class, "provinceCode", false, "PROVINCE_CODE");
        public final static Property CityCode = new Property(5, String.class, "cityCode", false, "CITY_CODE");
        public final static Property AreaCode = new Property(6, String.class, "areaCode", false, "AREA_CODE");
    };


    public CoordinateDao(DaoConfig config) {
        super(config);
    }
    
    public CoordinateDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'COORDINATE' (" + //
                "'ID' INTEGER PRIMARY KEY NOT NULL ," + // 0: id
                "'RELATE_TYPE' INTEGER," + // 1: relateType
                "'RELATE_ID' INTEGER," + // 2: relateId
                "'ADDRESS' TEXT," + // 3: address
                "'PROVINCE_CODE' TEXT," + // 4: provinceCode
                "'CITY_CODE' TEXT," + // 5: cityCode
                "'AREA_CODE' TEXT);"); // 6: areaCode
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'COORDINATE'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Coordinate entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        Integer relateType = entity.getRelateType();
        if (relateType != null) {
            stmt.bindLong(2, relateType);
        }
 
        Integer relateId = entity.getRelateId();
        if (relateId != null) {
            stmt.bindLong(3, relateId);
        }
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(4, address);
        }
 
        String provinceCode = entity.getProvinceCode();
        if (provinceCode != null) {
            stmt.bindString(5, provinceCode);
        }
 
        String cityCode = entity.getCityCode();
        if (cityCode != null) {
            stmt.bindString(6, cityCode);
        }
 
        String areaCode = entity.getAreaCode();
        if (areaCode != null) {
            stmt.bindString(7, areaCode);
        }
    }

    /** @inheritdoc */
    @Override
    public Integer readKey(Cursor cursor, int offset) {
        return cursor.getInt(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Coordinate readEntity(Cursor cursor, int offset) {
        Coordinate entity = new Coordinate( //
            cursor.getInt(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // relateType
            cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2), // relateId
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // address
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // provinceCode
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // cityCode
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6) // areaCode
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Coordinate entity, int offset) {
        entity.setId(cursor.getInt(offset + 0));
        entity.setRelateType(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setRelateId(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
        entity.setAddress(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setProvinceCode(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setCityCode(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setAreaCode(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
     }
    
    /** @inheritdoc */
    @Override
    protected Integer updateKeyAfterInsert(Coordinate entity, long rowId) {
        return entity.getId();
    }
    
    /** @inheritdoc */
    @Override
    public Integer getKey(Coordinate entity) {
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
