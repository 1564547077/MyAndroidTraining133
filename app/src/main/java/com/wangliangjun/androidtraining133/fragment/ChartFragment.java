package com.wangliangjun.androidtraining133.fragment;

import android.view.View;

import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.wangliangjun.androidtraining133.R;

public class ChartFragment extends BaseFragment {


    @Override
    protected int setLayoutResourceId() {
        return R.layout.chart_layout;
    }

    @Override
    protected void initView(View view) {
        BoomMenuButton bmb = view.findViewById(R.id.bmb);
        String[] texts = {
                "Android","Java","PHP","黑马程序员.Python","黑马程序员.C/C++","黑马程序员.IOS"
                ,"黑马程序员.前端与移动开发","黑马程序员.","黑马程序员.UI设计","黑马程序员.网站营销"
        };
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            TextInsideCircleButton.Builder builder = new TextInsideCircleButton.Builder()
                    .normalImageRes(R.drawable.android_icon)
                    .normalText(texts[i]);
            bmb.addBuilder(builder);
        }
    }


    @Override
    protected void initData() {
    }
}
