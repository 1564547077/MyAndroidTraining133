package com.wangliangjun.androidtraining133.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.wangliangjun.androidtraining133.R;
import com.wangliangjun.androidtraining133.adapter.GuidePagerAdapter;

import java.util.ArrayList;

public class GuideActivity extends BaseActivity {

    ViewPager guideViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        //调用设置适配器的方法
        initAdapter();
    }

    //初始化引导页面数据
    private ArrayList<ImageView> getImageViews(){
        ArrayList<ImageView> imageViews = new ArrayList<>();
        ImageView imageView1 = new ImageView(this);
        ImageView imageView2 = new ImageView(this);
        ImageView imageView3 = new ImageView(this);
        ImageView imageView4 = new ImageView(this);
        imageView1.setImageResource(R.drawable.logo);
        imageView2.setImageResource(R.drawable.logo1);
        imageView3.setImageResource(R.drawable.logo2);
        imageView4.setImageResource(R.drawable.logo3);
        imageViews.add(imageView1);
        imageViews.add(imageView2);
        imageViews.add(imageView3);
        imageViews.add(imageView4);
        return imageViews;
    }

    //设置ViewPager的适配器
    private void initAdapter(){
        guideViewPager = findViewById(R.id.guideViewPager);
        guideViewPager.setAdapter(new GuidePagerAdapter(getImageViews()));
    }
}
