package com.wangliangjun.androidtraining133.fragment;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wangliangjun.androidtraining133.R;
import com.wangliangjun.androidtraining133.adapter.HomeMultiItemQuickAdapter;
import com.wangliangjun.androidtraining133.bean.NewsBean;
import com.wangliangjun.androidtraining133.global.GlobalConstants;
import com.wangliangjun.androidtraining133.utils.JsonParseUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeFragment extends BaseFragment {
    private Toolbar toolbar;
    private TextView title;
    private RecyclerView newsList;
    static private Handler handler;
    private FloatingActionButton floatingRefreshButton;
    private final static int SUCCESS = 0x001;
    private final static int FAILURE = 0x002;
    SmartRefreshLayout refreshLayout;
    private HomeMultiItemQuickAdapter homeMultiItemQuickAdapter;
    private ArrayList<NewsBean> newsListData;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.home_layout;
    }

    @Override
    protected void initView(View view) {
        newsList = view.findViewById(R.id.newsList);
        floatingRefreshButton = view.findViewById(R.id.floatingRefreshButton);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        floatingRefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshLayout.autoRefresh();
            }
        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initData(true);
            }
        });
    }

    @SuppressLint("HandlerLeak")
    @Override
    protected void initData(final boolean refresh) {
        //使用okhttp获取服务器数据
        post(GlobalConstants.REQUEST_NEWS_URL);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case SUCCESS:
                        String json = (String) msg.obj;
                        newsListData = (ArrayList<NewsBean>) JsonParseUtils.getNewsList(json);
                        homeMultiItemQuickAdapter = new HomeMultiItemQuickAdapter(newsListData);
                        homeMultiItemQuickAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN  );
                        homeMultiItemQuickAdapter.isFirstOnly(false);
                        newsList.setAdapter(homeMultiItemQuickAdapter);
                        newsList.setLayoutManager(new LinearLayoutManager(getContext()));
                        if (refresh){
                            Toasty.success(mActivity, "刷新成功!", Toast.LENGTH_SHORT, true).show();
                        }
                        break;
                    case FAILURE:
                        if (refresh){
                            Toasty.error(mActivity,"刷新失败，请检查网络连接", Toast.LENGTH_SHORT, true).show();
                        }else{
                            Toasty.error(mActivity,"加载失败，请检查网络连接", Toast.LENGTH_SHORT, true).show();
                        }
                        break;
                }
            }
        };
    }
    void post(String url){
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder().url(
                url).get().build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            Message message = new Message();
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d(TAG,"加载失败；"+e.getLocalizedMessage());
                refreshLayout.finishRefresh(false);
                message.what = FAILURE;
                handler.sendMessage(message);
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String json = response.body().string();
                Log.i(TAG, "onResponse: "+json);
                message.obj = json;
                message.what = SUCCESS;
                handler.sendMessage(message);
                refreshLayout.finishRefresh(true);
            }
        });
    }
}
