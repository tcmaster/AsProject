package com.android.tonight8.dao.entity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table MESSAGE_CONFIG.
 */
public class MessageConfig {

    private long id;
    private Boolean followMe;
    private Boolean supportMe;
    private Boolean sponsorMe;
    private Boolean commentMe;
    private Boolean receiveOrg;
    private Boolean reciveSeller;
    private Boolean reciveUser;
    private Boolean reciveFollower;
    private Boolean reciveFollowing;

    public MessageConfig() {
    }

    public MessageConfig(long id) {
        this.id = id;
    }

    public MessageConfig(long id, Boolean followMe, Boolean supportMe, Boolean sponsorMe, Boolean commentMe, Boolean receiveOrg, Boolean reciveSeller, Boolean reciveUser, Boolean reciveFollower, Boolean reciveFollowing) {
        this.id = id;
        this.followMe = followMe;
        this.supportMe = supportMe;
        this.sponsorMe = sponsorMe;
        this.commentMe = commentMe;
        this.receiveOrg = receiveOrg;
        this.reciveSeller = reciveSeller;
        this.reciveUser = reciveUser;
        this.reciveFollower = reciveFollower;
        this.reciveFollowing = reciveFollowing;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Boolean getFollowMe() {
        return followMe;
    }

    public void setFollowMe(Boolean followMe) {
        this.followMe = followMe;
    }

    public Boolean getSupportMe() {
        return supportMe;
    }

    public void setSupportMe(Boolean supportMe) {
        this.supportMe = supportMe;
    }

    public Boolean getSponsorMe() {
        return sponsorMe;
    }

    public void setSponsorMe(Boolean sponsorMe) {
        this.sponsorMe = sponsorMe;
    }

    public Boolean getCommentMe() {
        return commentMe;
    }

    public void setCommentMe(Boolean commentMe) {
        this.commentMe = commentMe;
    }

    public Boolean getReceiveOrg() {
        return receiveOrg;
    }

    public void setReceiveOrg(Boolean receiveOrg) {
        this.receiveOrg = receiveOrg;
    }

    public Boolean getReciveSeller() {
        return reciveSeller;
    }

    public void setReciveSeller(Boolean reciveSeller) {
        this.reciveSeller = reciveSeller;
    }

    public Boolean getReciveUser() {
        return reciveUser;
    }

    public void setReciveUser(Boolean reciveUser) {
        this.reciveUser = reciveUser;
    }

    public Boolean getReciveFollower() {
        return reciveFollower;
    }

    public void setReciveFollower(Boolean reciveFollower) {
        this.reciveFollower = reciveFollower;
    }

    public Boolean getReciveFollowing() {
        return reciveFollowing;
    }

    public void setReciveFollowing(Boolean reciveFollowing) {
        this.reciveFollowing = reciveFollowing;
    }

}
