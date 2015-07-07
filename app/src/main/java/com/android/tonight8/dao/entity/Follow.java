package com.android.tonight8.dao.entity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table FOLLOW.
 */
public class Follow {

    private long id;
    private Integer followerRole;
    private Integer followingRole;
    private Long followerId;
    private Long followingId;

    public Follow() {
    }

    public Follow(long id) {
        this.id = id;
    }

    public Follow(long id, Integer followerRole, Integer followingRole, Long followerId, Long followingId) {
        this.id = id;
        this.followerRole = followerRole;
        this.followingRole = followingRole;
        this.followerId = followerId;
        this.followingId = followingId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getFollowerRole() {
        return followerRole;
    }

    public void setFollowerRole(Integer followerRole) {
        this.followerRole = followerRole;
    }

    public Integer getFollowingRole() {
        return followingRole;
    }

    public void setFollowingRole(Integer followingRole) {
        this.followingRole = followingRole;
    }

    public Long getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Long followerId) {
        this.followerId = followerId;
    }

    public Long getFollowingId() {
        return followingId;
    }

    public void setFollowingId(Long followingId) {
        this.followingId = followingId;
    }

}
