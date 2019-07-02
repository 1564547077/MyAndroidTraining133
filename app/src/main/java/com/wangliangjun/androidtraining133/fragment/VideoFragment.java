package com.wangliangjun.androidtraining133.fragment;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.wangliangjun.androidtraining133.R;

public class VideoFragment extends BaseFragment {

    private Toolbar toolbar;
    private TextView title;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.video_layout;
    }

    @Override
    protected void initView(View view) {
        toolbar = view.findViewById(R.id.toolbar);
        title = toolbar.findViewById(R.id.toolbarTitle);
    }


    @Override
    protected void initData() {
        title.setText("视频页");
    }
}
