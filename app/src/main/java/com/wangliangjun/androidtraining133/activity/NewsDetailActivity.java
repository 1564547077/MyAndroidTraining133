package com.wangliangjun.androidtraining133.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.just.agentweb.IWebLayout;
import com.just.agentweb.WebChromeClient;
import com.just.agentweb.WebCreator;
import com.wangliangjun.androidtraining133.R;

import es.dmoral.toasty.Toasty;

public class NewsDetailActivity extends HomeAsUpBaseActivity {
    private AgentWeb mAgentWeb;
    private WebView webView;
    private Toolbar toolbar;
    private LinearLayout newDetailLinearLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsdetail);
        newDetailLinearLayout = findViewById(R.id.newDetailLinearLayout);
        Button backButton = findViewById(R.id.backButton);
        Button closeButton = findViewById(R.id.closeButton);
        Button browserButton = findViewById(R.id.browserButton);
        browserButton.setVisibility(View.VISIBLE);
        backButton.setVisibility(View.VISIBLE);
        closeButton.setVisibility(View.VISIBLE);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        final TextView toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbar = findViewById(R.id.appToolbar);
        //这里是设置toolbar的各种布局和样式
        toolbarTitle.setText("加载中....");
        toolbarTitle.setTextSize(17);
        toolbar.setBackgroundColor(Color.parseColor("#000000"));
        toolbarTitle.setTextColor(Color.parseColor("#FFFFFF"));
        //添加第三方AgentWeb到本Activity的布局中
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(newDetailLinearLayout,
                        new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT))
                .useDefaultIndicator(-1, 5)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.DERECT)
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
        //获取webView
        WebCreator webCreator = mAgentWeb.getWebCreator();
        webView = webCreator.getWebView();
        changeTextSize();//调用改变字体大小的方法(给button设置对应时间显示字体改变框)
    }

    //改变字体大小
    private void changeTextSize(){
        final WebSettings webSettings = webView.getSettings();
        Button textSize = findViewById(R.id.textSize);
        textSize.setVisibility(View.VISIBLE);
        textSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(NewsDetailActivity.this)
                .title("更改字体大小")
                .titleGravity(GravityEnum.CENTER)
                .items("超大号字体","大号字体","正常字体","小号字体","超小号字体")
                .itemsGravity(GravityEnum.CENTER)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        switch (which){
                            case 0:
                                webSettings.setTextZoom(200);
                                break;
                            case 1:
                                webSettings.setTextZoom(150);
                                break;
                            case 2:
                                webSettings.setTextZoom(100);
                                break;
                            case 3:
                                webSettings.setTextZoom(75);
                                break;
                            case 4:
                                webSettings.setTextZoom(50);
                                break;
                        }
                    }
                }).show();
            }
        });
    }

    //手机返回按钮事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!mAgentWeb.back()){
            this.finish();
        }
        return false;
    }

    //回到上一个页面
    public void back(View view) {
        if (!mAgentWeb.back()){
            this.finish();
        }
    }

    //关闭当前页面
    public void closePage(View view) {
        new MaterialDialog.Builder(this)
                .content("是否要退出当前页面？")
                .positiveText("是")
                .negativeText("否")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        finish();
                        Toasty.info(NewsDetailActivity.this,"已退出", Toast.LENGTH_SHORT, true).show();
                    }
                })
                .show();
    }
    public void openBrowser(View view){
        Uri content_url = Uri.parse(webView.getUrl());
        if (content_url!=null){
            Intent intent= new Intent("android.intent.action.VIEW",content_url);
            startActivity(intent);
        }
    }

    //下面都是为了节省资源
    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        newDetailLinearLayout.removeView(webView);
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }

}
