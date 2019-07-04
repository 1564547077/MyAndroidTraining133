package com.wangliangjun.androidtraining133.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.wangliangjun.androidtraining133.R;

public abstract class HomeAsUpBaseActivity extends BaseActivity {
    @Override
    protected void onStart() {

        Toolbar toolbar = findViewById(R.id.appToolbar);
        setSupportActionBar(toolbar);//使用toolbar替换原有actionbar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayShowTitleEnabled(false);
        }
        super.onStart();
    }
}
