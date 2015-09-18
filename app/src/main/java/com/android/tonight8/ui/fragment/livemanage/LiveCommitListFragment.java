package com.android.tonight8.ui.fragment.livemanage;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.tonight8.R;
import com.android.tonight8.base.BaseFragment;
import com.android.tonight8.dao.entity.AwardHit;
import com.android.tonight8.dao.entity.CouponProvide;
import com.android.tonight8.dao.entity.Live;
import com.android.tonight8.dao.entity.Org;
import com.android.tonight8.dao.entity.Photo;
import com.android.tonight8.dao.entity.PopPic;
import com.android.tonight8.dao.entity.Seller;
import com.android.tonight8.dao.entity.Subject;
import com.android.tonight8.dao.entity.User;
import com.android.tonight8.dao.model.live.EventLiveList;
import com.android.tonight8.function.ScrollTopOrBottomListener;
import com.android.tonight8.io.HandlerConstants;
import com.android.tonight8.io.live.LiveIOController;
import com.android.tonight8.ui.activity.live.EventLivePlayActivity;
import com.android.tonight8.ui.adapter.live.EventLiveCommitAdapter;
import com.android.tonight8.ui.view.xlistview.XListView;
import com.android.tonight8.utils.JsonUtils;
import com.android.tonight8.utils.Utils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LiXiaoSong
 * @Descripton 用于现场直播的fragment（现场评论）
 * @2015-6-10
 * @Tonight8
 */
public class LiveCommitListFragment extends BaseFragment {
    /**
     * 该界面的listView
     */
    @ViewInject(R.id.lv_only_list)
    private XListView lv_only_list;
    /**
     * listView尾部文字
     */
    private TextView tv_foot;
    /**
     * listView尾部布局
     */
    private LinearLayout ll_foot;
    /**
     * 父View
     */
    private View view;
    private EventLiveCommitAdapter adapter;
    private MyHandler handler;
    private View myHeader;//activity的头部

    public LiveCommitListFragment() {
        handler = new MyHandler(this);
    }

    public static LiveCommitListFragment newInstance() {
        return new LiveCommitListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.activity_only_list,
                    null);
            ViewUtils.inject(this, view);
        }
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        LiveIOController.readEventLiveCommit(handler);
        myHeader = ((EventLivePlayActivity) getActivity()).getHeader();
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.header_main_live, null);
        v.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, Utils.dip2px(getActivity(), 350)));
        lv_only_list.addExtraHeaderView(v);
        //滑动的处理
        lv_only_list.setOnScrollListener(new ScrollTopOrBottomListener(lv_only_list, myHeader));
    }

    private static class MyHandler extends Handler {
        private WeakReference<LiveCommitListFragment> ref;

        public MyHandler(LiveCommitListFragment lclf) {
            ref = new WeakReference<>(lclf);
        }

        @SuppressWarnings("unchecked")
        @Override
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case HandlerConstants.Live.LIVE_COMMIT:
                    switch (msg.arg1) {
                        case HandlerConstants.RESULT_OK:
                            if (ref.get().adapter == null) {
                                List<EventLiveList> list = new ArrayList<>();
                                for (int i = 0; i < 10; i++) {//模拟数据
                                    EventLiveList model = new EventLiveList();
                                    Live live = new Live();
                                    live.setId(i);
                                    live.setType((int) (4 * Math.random()));
                                    Subject subject = new Subject();
                                    subject.setId(i);
                                    subject.setHasPhoto(true);
                                    subject.setHasAudio(false);
                                    subject.setHasSource(false);
                                    subject.setContent("我的中英文话题，没有蛀牙" + i);
                                    subject.setDate("2015-01-0" + i);
                                    subject.setTime("15:0" + i);
                                    live.setSubject(subject);
                                    CouponProvide provide = new CouponProvide();
                                    provide.setId(i);
                                    provide.setEventId((long) i);
                                    provide.setType(0);
                                    provide.setValue(100.0f);
                                    provide.setContent("我们的活动券是十分专业的，接口很明确");
                                    provide.setProvideNumber(1200);
                                    provide.setDispatchNumber(500);
                                    provide.setDate("2015-01-0" + i);
                                    provide.setTime("15:0" + i);
                                    AwardHit awardHit = new AwardHit();
                                    awardHit.setCount(150);
                                    awardHit.setDate("2015-01-0" + i);
                                    awardHit.setTime("15:0" + i);
                                    double k = Math.random() * 10;
                                    if (k >= 0 && k <= 5) {
                                        User user = new User();
                                        user.setName("用户" + i);
                                        user.setPic(JsonUtils.imgs[(int) (Math.random() * 8)]);
                                        model.setUser(user);
                                    } else if (k > 5 && k < 8) {
                                        Seller seller = new Seller();
                                        seller.setName("卖手" + i);
                                        seller.setPic(JsonUtils.imgs[(int) (Math.random() * 8)]);
                                        model.setSeller(seller);
                                    } else if (k >= 8 && k < 10) {
                                        Org org = new Org();
                                        org.setName("商家" + i);
                                        org.setPic(JsonUtils.imgs[(int) (Math.random() * 8)]);
                                        model.setOrg(org);
                                    }
                                    List<Photo> photos = new ArrayList<>();
                                    Photo photo = new Photo();
                                    photo.setUrl(JsonUtils.imgs[(int) (Math.random() * 8)]);
                                    photo.setSize("480,320");
                                    photos.add(photo);
                                    PopPic popPic = new PopPic();
                                    popPic.setUrl(JsonUtils.imgs[(int) (Math.random() * 8)]);
                                    model.setLive(live);
                                    model.setCouponProvide(provide);
                                    model.setAwardHit(awardHit);
                                    model.setPhotos(photos);
                                    model.setPopPic(popPic);
                                    list.add(model);
                                }
                                ref.get().adapter = new EventLiveCommitAdapter(ref.get().getActivity(),
                                        list);
                                ref.get().lv_only_list.setAdapter(ref.get().adapter);
                            } else
                                ref.get().adapter.notifyDataSetChanged();
                            break;

                        default:
                            break;
                    }
                    break;

                default:
                    break;
            }
        }
    }
}
