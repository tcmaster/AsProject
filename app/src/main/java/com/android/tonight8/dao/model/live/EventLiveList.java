package com.android.tonight8.dao.model.live;

import com.android.tonight8.dao.entity.AwardHit;
import com.android.tonight8.dao.entity.Comment;
import com.android.tonight8.dao.entity.CouponProvide;
import com.android.tonight8.dao.entity.Live;
import com.android.tonight8.dao.entity.Org;
import com.android.tonight8.dao.entity.Photo;
import com.android.tonight8.dao.entity.PopPic;
import com.android.tonight8.dao.entity.Seller;
import com.android.tonight8.dao.entity.User;
import com.android.tonight8.dao.entity.Waiter;

import java.util.List;

/**
 * Created by LiXiaoSong
 * Date: 2015/9/6 0006
 */
public class EventLiveList {
    private Live live;
    private Comment comment;
    private CouponProvide couponProvide;
    private AwardHit awardHit;
    private User user;
    private Seller seller;
    private Org org;
    private Waiter waiter;
    private List<Photo> photos;
    private PopPic popPic;

    public Live getLive() {
        return live;
    }

    public void setLive(Live live) {
        this.live = live;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Org getOrg() {
        return org;
    }

    public void setOrg(Org org) {
        this.org = org;
    }

    public Waiter getWaiter() {
        return waiter;
    }

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public PopPic getPopPic() {
        return popPic;
    }

    public void setPopPic(PopPic popPic) {
        this.popPic = popPic;
    }

    @Override
    public String toString() {
        return "EventLiveList{" +
                "live=" + live +
                ", comment=" + comment +
                ", couponProvide=" + couponProvide +
                ", awardHit=" + awardHit +
                ", user=" + user +
                ", seller=" + seller +
                ", org=" + org +
                ", waiter=" + waiter +
                ", photos=" + photos +
                ", popPic=" + popPic +
                '}';
    }
}
