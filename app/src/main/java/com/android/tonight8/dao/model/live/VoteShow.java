package com.android.tonight8.dao.model.live;

import com.android.tonight8.dao.entity.Vote;
import com.android.tonight8.dao.entity.VoteItem;
import com.android.tonight8.dao.entity.VoteItemOption;

import java.util.List;

/**
 * Created by LiXiaoSong
 * Date: 2015/8/21 0021
 */
public class VoteShow {
    private Vote vote;
    private List<VoteItem> voteItems;
    private List<VoteItemOption> voteItemOptions;

    public Vote getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }

    public List<VoteItem> getVoteItems() {
        return voteItems;
    }

    public void setVoteItems(List<VoteItem> voteItems) {
        this.voteItems = voteItems;
    }

    public List<VoteItemOption> getVoteItemOptions() {
        return voteItemOptions;
    }

    public void setVoteItemOptions(List<VoteItemOption> voteItemOptions) {
        this.voteItemOptions = voteItemOptions;
    }

    @Override
    public String toString() {
        return "VoteShow{" +
                "vote=" + vote +
                ", voteItems=" + voteItems +
                ", voteItemOptions=" + voteItemOptions +
                '}';
    }
}
