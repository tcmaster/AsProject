package com.android.tonight8.io.live;

import android.content.Entity;
import android.os.DropBoxManager;
import android.os.Handler;

import com.android.tonight8.io.HandlerConstants;
import com.android.tonight8.io.live.entity.EventGoodsOrderNetEntity;
import com.android.tonight8.io.live.entity.EventGoodsServiceNetEntity;
import com.android.tonight8.io.live.entity.EventLiveCommitNetEntity;
import com.android.tonight8.io.live.entity.EventLiveNetEntity;
import com.android.tonight8.io.live.entity.EventLiveVoteNetEntity;
import com.android.tonight8.io.live.entity.EventLiveWinnerListNetEntity;
import com.android.tonight8.io.live.entity.GoodsDetailNetEntity;
import com.android.tonight8.io.net.NetEntityBase;
import com.android.tonight8.io.net.NetRequest;
import com.android.tonight8.io.net.NetRequest.RequestResult;
import com.lidroid.xutils.exception.HttpException;

import java.security.KeyStore;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @Description:读取活动现场列表数据
 * @author:LiuZhao
 * @Date:2015年2月5日
 */
public class LiveIOController {
    private static final String EVENT_LIVE_TITLE = NetRequest.BASE_URL + "";
    private static final String EVENT_LIVE_COMMIT = NetRequest.BASE_URL + "";
    private static final String EVENT_LIVE_WINNER_LIST = NetRequest.BASE_URL
            + "";
    private static final String EVENT_LIVE_VOTE_SHOW = NetRequest.BASE_URL + "";
    private static final String EVENT_GOODS_SHOW = NetRequest.BASE_URL + "";
    private static final String EVENT_GOODS_COMMENT = NetRequest.BASE_URL + "";
    private static final String EVENT_GOODS_ORDER_SHOW = NetRequest.BASE_URL
            + "";
    private static final String EVENT_LIVE_VOTE_COMMIT = NetRequest.BASE_URL + "";

    public static void readLiveTitle(final Handler handler) {
        Map<String, String> param = new HashMap<String, String>();
        param.put(NetRequest.REQUEST_URL, EVENT_LIVE_TITLE);
        HandlerConstants.sendMessage(handler, null,
                HandlerConstants.Live.LIVE_TITLE,
                HandlerConstants.NETWORK_BEGIN, -1);
        NetRequest.doGetRequest(param, new RequestResult<EventLiveNetEntity>(
                EventLiveNetEntity.class, handler) {

            @Override
            public void getData(NetEntityBase netEntityBase,
                                EventLiveNetEntity t, Handler handler) {
                HandlerConstants.sendMessage(handler, t.getEventLive(),
                        HandlerConstants.Live.LIVE_TITLE,
                        HandlerConstants.RESULT_OK, -1);
            }

            @Override
            public void onFailure(HttpException error, String msg) {
            }
        });
    }

    public static void readEventLiveCommit(final Handler handler) {
        Map<String, String> param = new HashMap<String, String>();
        param.put(NetRequest.REQUEST_URL, EVENT_LIVE_COMMIT);
        HandlerConstants.sendMessage(handler, null,
                HandlerConstants.Live.LIVE_COMMIT,
                HandlerConstants.NETWORK_BEGIN, -1);
        NetRequest.doGetRequest(param,
                new RequestResult<EventLiveCommitNetEntity>(
                        EventLiveCommitNetEntity.class, handler) {

                    @Override
                    public void getData(NetEntityBase netEntityBase,
                                        EventLiveCommitNetEntity t, Handler handler) {
                        HandlerConstants.sendMessage(handler,
                                t.getSubjectList(),
                                HandlerConstants.Live.LIVE_COMMIT,
                                HandlerConstants.RESULT_OK, -1);
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                    }
                });
    }

    public static void readLiveWinnerList(Handler handler) {
        Map<String, String> param = new HashMap<String, String>();
        param.put(NetRequest.REQUEST_URL, EVENT_LIVE_WINNER_LIST);
        HandlerConstants.sendMessage(handler, null,
                HandlerConstants.Live.LIVE_WINNER_LIST,
                HandlerConstants.NETWORK_BEGIN, -1);
        NetRequest.doGetRequest(param,
                new RequestResult<EventLiveWinnerListNetEntity>(
                        EventLiveWinnerListNetEntity.class, handler) {

                    @Override
                    public void getData(NetEntityBase netEntityBase,
                                        EventLiveWinnerListNetEntity t, Handler handler) {
                        HandlerConstants.sendMessage(handler,
                                t.getEventAwards(),
                                HandlerConstants.Live.LIVE_WINNER_LIST,
                                HandlerConstants.RESULT_OK, -1);
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                    }
                });
    }

    /**
     * 活动现场主题商品展示接口
     */
    public static void readGoodsInfo(final Handler handler) {
        Map<String, String> param = new HashMap<String, String>();
        param.put(NetRequest.REQUEST_URL, EVENT_GOODS_SHOW);
        HandlerConstants.sendMessage(handler, null,
                HandlerConstants.Live.GOODS_DETAIL,
                HandlerConstants.NETWORK_BEGIN, -1);
        NetRequest.doGetRequest(param, new RequestResult<GoodsDetailNetEntity>(
                GoodsDetailNetEntity.class, handler) {

            @Override
            public void getData(NetEntityBase netEntityBase,
                                GoodsDetailNetEntity t, Handler handler) {
                HandlerConstants.sendMessage(handler, t.getEventGoods(),
                        HandlerConstants.Live.GOODS_DETAIL,
                        HandlerConstants.RESULT_OK, -1);
            }

            @Override
            public void onFailure(HttpException error, String msg) {
            }
        });
    }

    public static void readGoodsComment(final Handler handler) {
        Map<String, String> param = new HashMap<String, String>();
        param.put(NetRequest.REQUEST_URL, EVENT_GOODS_COMMENT);
        HandlerConstants.sendMessage(handler, null,
                HandlerConstants.Live.GOODS_COMMENT,
                HandlerConstants.NETWORK_BEGIN, -1);
        NetRequest.doGetRequest(param,
                new RequestResult<EventGoodsServiceNetEntity>(
                        EventGoodsServiceNetEntity.class, handler) {

                    @Override
                    public void getData(NetEntityBase netEntityBase,
                                        EventGoodsServiceNetEntity t, Handler handler) {
                        HandlerConstants.sendMessage(handler,
                                t.getEventGoodsServiceMarkList(),
                                HandlerConstants.Live.GOODS_COMMENT,
                                HandlerConstants.RESULT_OK, -1);
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                    }
                });
    }

    public static void readOrderShow(final Handler handler) {
        Map<String, String> param = new HashMap<String, String>();
        param.put(NetRequest.REQUEST_URL, EVENT_GOODS_ORDER_SHOW);
        HandlerConstants.sendMessage(handler, null,
                HandlerConstants.Live.GOODS_ORDER,
                HandlerConstants.NETWORK_BEGIN, -1);
        NetRequest.doGetRequest(param,
                new RequestResult<EventGoodsOrderNetEntity>(
                        EventGoodsOrderNetEntity.class, handler) {

                    @Override
                    public void getData(NetEntityBase netEntityBase,
                                        EventGoodsOrderNetEntity t, Handler handler) {
                        HandlerConstants.sendMessage(handler,
                                t.getEventGoodsOrder(),
                                HandlerConstants.Live.GOODS_ORDER,
                                HandlerConstants.RESULT_OK, -1);
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                    }
                });
    }

    public static void readVoteShow(final Handler handler) {
        Map<String, String> params = new HashMap<>();
        params.put(NetRequest.REQUEST_URL, EVENT_LIVE_VOTE_SHOW);
        HandlerConstants.sendMessage(handler, null, HandlerConstants.Live.LIVE_VOTE, HandlerConstants.NETWORK_BEGIN, -1);
        NetRequest.doGetRequest(params, new RequestResult<EventLiveVoteNetEntity>(EventLiveVoteNetEntity.class, handler) {
            @Override
            public void onFailure(HttpException e, String s) {
                HandlerConstants.sendMessage(handler, null, HandlerConstants.Live.LIVE_VOTE, HandlerConstants.RESULT_FAIL, -1);
            }

            @Override
            public void getData(NetEntityBase netEntityBase, EventLiveVoteNetEntity eventLiveVoteNetEntity, Handler handler) {
                HandlerConstants.sendMessage(handler, eventLiveVoteNetEntity.getVoteShow(), HandlerConstants.Live.LIVE_VOTE, HandlerConstants.RESULT_OK, -1);
            }
        });
    }

    public static void writeVoteCommit(Map<Long, Long> result, RequestResult<NetEntityBase> request) {
        Map<String, String> params = new HashMap<>();
        params.put(NetRequest.REQUEST_URL, EVENT_LIVE_VOTE_COMMIT);
        Set<Map.Entry<Long, Long>> set = result.entrySet();
        Iterator<Map.Entry<Long, Long>> it = set.iterator();
        StringBuilder voteItems = new StringBuilder();
        StringBuilder voteItemOptions = new StringBuilder();
        while (it.hasNext()) {
            Map.Entry<Long, Long> entry = it.next();
            voteItems.append(entry.getKey() + ",");
            voteItemOptions.append(entry.getValue() + ",");
        }
        voteItems.setLength(voteItems.length() - 1);
        voteItemOptions.setLength(voteItemOptions.length() - 1);
        params.put("voteItems", voteItems.toString());
        params.put("voteItemOptions", voteItemOptions.toString());
        NetRequest.doGetRequest(params, request);
    }
}
