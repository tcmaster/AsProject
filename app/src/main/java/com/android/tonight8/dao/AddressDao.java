package com.android.tonight8.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.android.tonight8.dao.entity.Address;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table ADDRESS.
*/
public class AddressDao extends AbstractDao<Address, Long> {

    public static final String TABLENAME = "ADDRESS";

    /**
     * Properties of entity Address.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "ID");
        public final static Property RelateType = new Property(1, Integer.class, "relateType", false, "RELATE_TYPE");
        public final static Property RelateId = new Property(2, Long.class, "relateId", false, "RELATE_ID");
        public final static Property Name = new Property(3, String.class, "name", false, "NAME");
        public final static Property ProvinceCode = new Property(4, String.class, "provinceCode", false, "PROVINCE_CODE");
        public final static Property CityCode = new Property(5, String.class, "cityCode", false, "CITY_CODE");
        public final static Property AreaCode = new Property(6, String.class, "areaCode", false, "AREA_CODE");
        public final static Property ZipCode = new Property(7, String.class, "zipCode", false, "ZIP_CODE");
        public final static Property ContactPerson = new Property(8, String.class, "contactPerson", false, "CONTACT_PERSON");
        public final static Property ContactMobilePhone = new Property(9, String.class, "contactMobilePhone", false, "CONTACT_MOBILE_PHONE");
        public final static Property IsDefault = new Property(10, Boolean.class, "isDefault", false, "IS_DEFAULT");
    };


    public AddressDao(DaoConfig config) {
        super(config);
    }
    
    public AddressDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'ADDRESS' (" + //
                "'ID' INTEGER PRIMARY KEY NOT NULL ," + // 0: id
                "'RELATE_TYPE' INTEGER," + // 1: relateType
                "'RELATE_ID' INTEGER," + // 2: relateId
                "'NAME' TEXT," + // 3: name
                "'PROVINCE_CODE' TEXT," + // 4: provinceCode
                "'CITY_CODE' TEXT," + // 5: cityCode
                "'AREA_CODE' TEXT," + // 6: areaCode
                "'ZIP_CODE' TEXT," + // 7: zipCode
                "'CONTACT_PERSON' TEXT," + // 8: contactPerson
                "'CONTACT_MOBILE_PHONE' TEXT," + // 9: contactMobilePhone
                "'IS_DEFAULT' INTEGER);"); // 10: isDefault
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'ADDRESS'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Address entity) {
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
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(4, name);
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
 
        String zipCode = entity.getZipCode();
        if (zipCode != null) {
            stmt.bindString(8, zipCode);
        }
 
        String contactPerson = entity.getContactPerson();
        if (contactPerson != null) {
            stmt.bindString(9, contactPerson);
        }
 
        String contactMobilePhone = entity.getContactMobilePhone();
        if (contactMobilePhone != null) {
            stmt.bindString(10, contactMobilePhone);
        }
 
        Boolean isDefault = entity.getIsDefault();
        if (isDefault != null) {
            stmt.bindLong(11, isDefault ? 1l: 0l);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Address readEntity(Cursor cursor, int offset) {
        Address entity = new Address( //
            cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // relateType
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // relateId
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // name
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // provinceCode
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // cityCode
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // areaCode
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // zipCode
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // contactPerson
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // contactMobilePhone
            cursor.isNull(offset + 10) ? null : cursor.getShort(offset + 10) != 0 // isDefault
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Address entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setRelateType(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setRelateId(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setProvinceCode(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setCityCode(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setAreaCode(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setZipCode(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setContactPerson(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setContactMobilePhone(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setIsDefault(cursor.isNull(offset + 10) ? null : cursor.getShort(offset + 10) != 0);
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Address entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Address entity) {
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
