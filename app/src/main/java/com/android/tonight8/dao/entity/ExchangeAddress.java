package com.android.tonight8.dao.entity;

import com.android.tonight8.dao.DaoSession;
import de.greenrobot.dao.DaoException;

import com.android.tonight8.dao.ExchangeAddressDao;
import com.android.tonight8.dao.ExchangeCityDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table EXCHANGE_ADDRESS.
 */
public class ExchangeAddress {

    private long id;
    private Long cityId;
    private String address;
    private String coordinate;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient ExchangeAddressDao myDao;

    private ExchangeCity exchangeCity;
    private Long exchangeCity__resolvedKey;


    public ExchangeAddress() {
    }

    public ExchangeAddress(long id) {
        this.id = id;
    }

    public ExchangeAddress(long id, Long cityId, String address, String coordinate) {
        this.id = id;
        this.cityId = cityId;
        this.address = address;
        this.coordinate = coordinate;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getExchangeAddressDao() : null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    /** To-one relationship, resolved on first access. */
    public ExchangeCity getExchangeCity() {
        Long __key = this.cityId;
        if (exchangeCity__resolvedKey == null || !exchangeCity__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ExchangeCityDao targetDao = daoSession.getExchangeCityDao();
            ExchangeCity exchangeCityNew = targetDao.load(__key);
            synchronized (this) {
                exchangeCity = exchangeCityNew;
            	exchangeCity__resolvedKey = __key;
            }
        }
        return exchangeCity;
    }

    public void setExchangeCity(ExchangeCity exchangeCity) {
        synchronized (this) {
            this.exchangeCity = exchangeCity;
            cityId = exchangeCity == null ? null : exchangeCity.getId();
            exchangeCity__resolvedKey = cityId;
        }
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

}
