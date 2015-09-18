package com.android.tonight8.dao.entity;

/**
 * Created by LiXiaoSong
 * Date: 2015/8/21 0021
 */
public class Live {
    private long id;
    private int type;
    private Subject subject;
    private Comment comment;
    private CouponProvide couponProvide;
    private AwardHit awardHit;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public CouponProvide getCouponProvide() {
        return couponProvide;
    }

    public void setCouponProvide(CouponProvide couponProvide) {
        this.couponProvide = couponProvide;
    }

    public AwardHit getAwardHit() {
        return awardHit;
    }

    public void setAwardHit(AwardHit awardHit) {
        this.awardHit = awardHit;
    }

    @Override
    public String toString() {
        return "Live{" +
                "id=" + id +
                ", type=" + type +
                ", subject=" + subject +
                ", comment=" + comment +
                ", couponProvide=" + couponProvide +
                ", awardHit=" + awardHit +
                '}';
    }
}
