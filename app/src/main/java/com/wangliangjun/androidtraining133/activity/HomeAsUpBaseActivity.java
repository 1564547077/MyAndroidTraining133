package com.wangliangjun.androidtraining133.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.wangliangjun.androidtraining133.R;

public class HomeAsUpBaseActivity extends BaseActivity {
    @Override
    protected void onStart() {

        Toolbar toolbar = findViewById(R.id.appToolbar);
        setSupportActionBar(toolbar);//使用toolbar替换原有actionbar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);//给左上角图标的左边加上一个返回的图标
            actionBar.setDisplayShowTitleEnabled(false);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//点击左上角返回图标结束当前窗口
            }
        });
        super.onStart();
    }
}
