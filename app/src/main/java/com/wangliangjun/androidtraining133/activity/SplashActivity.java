package com.wangliangjun.androidtraining133.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.wangliangjun.androidtraining133.R;
import com.wangliangjun.androidtraining133.utils.PreFUtils;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends BaseActivity {

    private Timer timer;
    private Button skipButton;
    private ImageView icon;
    ImageView splashImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        skipButton = findViewById(R.id.skipButton);
        splashImageView = findViewById(R.id.splashImageView);
        icon = findViewById(R.id.imageView);
        int i = PreFUtils.getInt(SplashActivity.this, "splashImage", 0);
        splashImageView.setImageResource(getImageId(i));
        if (i!=0){
            icon.setVisibility(View.VISIBLE);
        }else{
            icon.setVisibility(View.INVISIBLE);
        }
        ConstraintLayout splashPager = findViewById(R.id.splashPager);
        //缩放动画
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1
                , Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF
                , 0.5f);
        scaleAnimation.setDuration(1000);
        scaleAnimation.setFillAfter(true);

        //渐变动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(500);
        alphaAnimation.setFillAfter(true);

        //动画集合
        AnimationSet animationSet = new AnimationSet(true);

        //添加动画
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        //启动动画
        splashPager.startAnimation(animationSet);
        splash();
    }

    //闪屏页定时跳转方法
    private void splash() {
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                drump();
            }
        };
        //设置闪屏页在3秒后自动跳转到主页面
        timer.schedule(timerTask, 3000);
    }

    public void skip(View view) {
        drump();
    }

    private void drump() {
        Intent intent;
        boolean firstEnter = PreFUtils.getBoolean(SplashActivity.this, "firstEnter", true);
        if (firstEnter){
            intent = new Intent(SplashActivity.this, GuideActivity.class);
        }else{
            intent = new Intent(SplashActivity.this, MainActivity.class);
        }

        startActivity(intent);
        //结束延时
        timer.cancel();
        //结束当前页面，防止按返回键回到该页面
        finish();
    }
    private int getImageId(int i){
        int[] imageId = {R.drawable.logo,R.drawable.logo1,R.drawable.logo2,R.drawable.logo3};
        return imageId[i];
    }
}
