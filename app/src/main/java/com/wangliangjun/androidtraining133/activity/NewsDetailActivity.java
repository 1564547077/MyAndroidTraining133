package com.wangliangjun.androidtraining133.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.just.agentweb.WebChromeClient;
import com.wangliangjun.androidtraining133.R;

public class NewsDetailActivity extends HomeAsUpBaseActivity {
    private AgentWeb mAgentWeb;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsdetail);
        LinearLayout newDetailLinearLayout = findViewById(R.id.newDetailLinearLayout);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        final TextView toolbarTitle = findViewById(R.id.toolbarTitle);
        Toolbar toolbar = findViewById(R.id.appToolbar);
        toolbarTitle.setText("加载中....");
        toolbarTitle.setTextSize(15);
        toolbar.setBackgroundColor(Color.parseColor("#000000"));
        toolbarTitle.setTextColor(Color.parseColor("#FFFFFF"));
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(newDetailLinearLayout,
                        new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT))
                .useDefaultIndicator(-1, 3)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.DISALLOW)
                .interceptUnkownUrl()
                .setWebChromeClient(new WebChromeClient(){
                    @Override
                    public void onReceivedTitle(WebView view, String title) {
                        super.onReceivedTitle(view, title);
                        toolbarTitle.setText(title);
                    }
                })
                .createAgentWeb()
                .ready()
                .go(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mAgentWeb.handleKeyEvent(keyCode,event)){
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
