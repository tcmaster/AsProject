package com.android.tonight8.io.live.entity;

import com.android.tonight8.dao.model.live.VoteShow;
import com.android.tonight8.io.net.NetEntityBase;

/**
 * Created by LiXiaoSong
 * Date: 2015/8/21 0021
 */
public class EventLiveVoteNetEntity extends NetEntityBase {
    private VoteShow voteShow;

    public VoteShow getVoteShow() {
        return voteShow;
    }

    public void setVoteShow(VoteShow voteShow) {
        this.voteShow = voteShow;
    }

    @Override
    public String toString() {
        return "EventLiveVoteNetEntity{" +
                "voteShow=" + voteShow +
                '}';
    }
}
