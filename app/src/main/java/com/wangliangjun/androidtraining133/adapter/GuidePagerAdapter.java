package com.wangliangjun.androidtraining133.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class GuidePagerAdapter extends PagerAdapter {
    ArrayList<ImageView> imageViews;

    //通过构造方法获取viewPage要显示的数据
    public GuidePagerAdapter(ArrayList<ImageView> imageViews) {
        this.imageViews = imageViews;
    }

    //获取ViewPager的页面数
    @Override
    public int getCount() {
        return imageViews.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }


    //每次滑动的时候把视图添加到ViewPager
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(imageViews.get(position));
        return imageViews.get(position);
    }

    //要移除当前位置的View否则会报错
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(imageViews.get(position));

    }
}
