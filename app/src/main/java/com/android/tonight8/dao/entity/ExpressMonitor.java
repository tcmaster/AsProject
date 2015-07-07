package com.android.tonight8.dao.entity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table EXPRESS_MONITOR.
 */
public class ExpressMonitor {

    private long id;
    private Integer status;
    private Integer node;
    private String content;
    private String time;

    public ExpressMonitor() {
    }

    public ExpressMonitor(long id) {
        this.id = id;
    }

    public ExpressMonitor(long id, Integer status, Integer node, String content, String time) {
        this.id = id;
        this.status = status;
        this.node = node;
        this.content = content;
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNode() {
        return node;
    }

    public void setNode(Integer node) {
        this.node = node;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
