package com.android.tonight8.dao.entity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table NOTICE.
 */
public class Notice {

    private long id;
    private Integer relateType;
    private Integer relateId;
    private String name;
    private String content;
    private String date;
    private String time;

    public Notice() {
    }

    public Notice(long id) {
        this.id = id;
    }

    public Notice(long id, Integer relateType, Integer relateId, String name, String content, String date, String time) {
        this.id = id;
        this.relateType = relateType;
        this.relateId = relateId;
        this.name = name;
        this.content = content;
        this.date = date;
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getRelateType() {
        return relateType;
    }

    public void setRelateType(Integer relateType) {
        this.relateType = relateType;
    }

    public Integer getRelateId() {
        return relateId;
    }

    public void setRelateId(Integer relateId) {
        this.relateId = relateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
