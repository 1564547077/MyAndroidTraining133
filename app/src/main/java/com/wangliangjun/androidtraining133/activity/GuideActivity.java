package com.wangliangjun.androidtraining133.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wangliangjun.androidtraining133.R;
import com.wangliangjun.androidtraining133.adapter.GuidePagerAdapter;
import com.wangliangjun.androidtraining133.utils.PreFUtils;

import java.util.ArrayList;

public class GuideActivity extends BaseActivity {

    ViewPager guideViewPager;
    LinearLayout guideIndicateContainer;
    //前一个圆
    int mNum = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        guideViewPager = findViewById(R.id.guideViewPager);
        guideIndicateContainer = findViewById(R.id.guideIndicateContainer);
        //调用设置适配器的方法
        initAdapter();
        guideIndicateContainer.getChildAt(0).setEnabled(true);
        guideViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                guideIndicateContainer.getChildAt(mNum).setEnabled(false);
                guideIndicateContainer.getChildAt(position).setEnabled(true);
                mNum = position;
            }
        });
    }

    //初始化引导页面数据
    private ArrayList<ImageView> getImageViews(){
        ArrayList<ImageView> imageViews = new ArrayList<>();
        ImageView imageView1 = new ImageView(this);
        ImageView imageView2 = new ImageView(this);
        ImageView imageView3 = new ImageView(this);
        ImageView imageView4 = new ImageView(this);
        imageView1.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView2.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView3.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView4.setScaleType(ImageView.ScaleType.FIT_XY);
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
        guideViewPager.setAdapter(new GuidePagerAdapter(getImageViews()));
        setIndicator();
    }
    private void setIndicator(){
        View view;
        for (int i = 0; i < getImageViews().size(); i++){
            view = new View(GuideActivity.this);
            view.setBackgroundResource(R.drawable.indicator_selector);
            view.setEnabled(false);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(30,30);
            if (i!=0){
                layoutParams.leftMargin = 15;
            }
            guideIndicateContainer.addView(view,layoutParams);
        }

    }

    public void setGuideImage(View view) {
        PreFUtils.setInt(GuideActivity.this,"splashImage",mNum);
        PreFUtils.setBoolean(GuideActivity.this,"firstEnter",false);
        Intent intent = new Intent(GuideActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void setDefault(View view) {
        PreFUtils.setInt(GuideActivity.this,"splashImage",0);
        PreFUtils.setBoolean(GuideActivity.this,"firstEnter",false);
        Intent intent = new Intent(GuideActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
