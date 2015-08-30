package com.android.tonight8.ui.fragment.spots;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.tonight8.R;
import com.android.tonight8.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveSpotsFragment extends BaseFragment {


    public LiveSpotsFragment() {
        // Required empty public constructor
    }
    public static final LiveSpotsFragment newInstance() {
        LiveSpotsFragment fragment = new LiveSpotsFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_spots, container, false);
        return view;
    }


}
