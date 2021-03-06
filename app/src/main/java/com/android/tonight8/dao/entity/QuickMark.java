package com.android.tonight8.dao.entity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table QUICK_MARK.
 */
public class QuickMark {

    private long id;
    private Integer type;
    private Long relateId;
    private String code;
    private String link;
    private String pic;

    public QuickMark() {
    }

    public QuickMark(long id) {
        this.id = id;
    }

    public QuickMark(long id, Integer type, Long relateId, String code, String link, String pic) {
        this.id = id;
        this.type = type;
        this.relateId = relateId;
        this.code = code;
        this.link = link;
        this.pic = pic;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getRelateId() {
        return relateId;
    }

    public void setRelateId(Long relateId) {
        this.relateId = relateId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

}
