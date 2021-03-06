package com.android.tonight8.dao.entity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table TMESSAGE.
 */
public class TMessage {

    private Long id;
    private String userPic;
    private String userName;
    private String userLastMessage;
    private Long lastTime;
    private Long userId;

    public TMessage() {
    }

    public TMessage(Long id) {
        this.id = id;
    }

    public TMessage(Long id, String userPic, String userName, String userLastMessage, Long lastTime, Long userId) {
        this.id = id;
        this.userPic = userPic;
        this.userName = userName;
        this.userLastMessage = userLastMessage;
        this.lastTime = lastTime;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLastMessage() {
        return userLastMessage;
    }

    public void setUserLastMessage(String userLastMessage) {
        this.userLastMessage = userLastMessage;
    }

    public Long getLastTime() {
        return lastTime;
    }

    public void setLastTime(Long lastTime) {
        this.lastTime = lastTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
