package com.wangliangjun.androidtraining133.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wangliangjun.androidtraining133.R;
import com.wangliangjun.androidtraining133.activity.NewsDetailActivity;
import com.wangliangjun.androidtraining133.adapter.HomeMultiItemQuickAdapter;
import com.wangliangjun.androidtraining133.bean.ADBean;
import com.wangliangjun.androidtraining133.bean.NewsBean;
import com.wangliangjun.androidtraining133.global.GlobalConstants;
import com.wangliangjun.androidtraining133.utils.ImageUtil;
import com.wangliangjun.androidtraining133.utils.JsonParseUtils;
import com.wangliangjun.androidtraining133.utils.NetUtil;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class HomeFragment extends BaseFragment {
    private RecyclerView newsList;
    private FloatingActionButton floatingRefreshButton;
    SmartRefreshLayout refreshLayout;
    private HomeMultiItemQuickAdapter homeMultiItemQuickAdapter;
    private ArrayList<NewsBean> newsListData = null;
    private ArrayList<ADBean> ADData;

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
                initData();
            }
        });
        homeMultiItemQuickAdapter = new HomeMultiItemQuickAdapter(null);
        View headView = LayoutInflater.from(mActivity).inflate(R.layout.home_head,null);
        homeMultiItemQuickAdapter.addHeaderView(headView);//添加头布局

        final Banner banner = headView.findViewById(R.id.banner);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                if(path instanceof String){
                    ImageUtil.setImage(context,(String)path,imageView);
                }
                if (path instanceof Integer){
                    imageView.setImageResource((Integer) path);
                }
            }
        });
        ArrayList<Integer> imageViews = new ArrayList<>();
        for (int i = 0; i <3; i++) {
            imageViews.add(R.drawable.pic_item_list_default);
        }
        //设置图片集合
        banner.setImages(imageViews);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

        View empty = LayoutInflater.from(mActivity).inflate(R.layout.empty_home,null);
        homeMultiItemQuickAdapter.setEmptyView(empty);
        homeMultiItemQuickAdapter.setHeaderAndEmpty(true);//显示空布局的同时会显示头布局
        newsList.setAdapter(homeMultiItemQuickAdapter);
        newsList.setLayoutManager(new LinearLayoutManager(mActivity));
        homeMultiItemQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mActivity,NewsDetailActivity.class);
                intent.putExtra("url",newsListData.get(position).getNewsUrl());
                startActivity(intent);
            }
        });
    }

    @SuppressLint("HandlerLeak")
    @Override
    protected void initData() {
        getNewsData();
        getADData();
    }


    void getNewsData(){
        NetUtil.getData(GlobalConstants.REQUEST_NEWS_URL, new NetUtil.MyCallBack() {
            @Override
            public void onFailure(IOException e) {
                Log.d(TAG,"加载失败；"+e.getLocalizedMessage());
                refreshLayout.finishRefresh(false);
            }

            @Override
            public void onResponse(final String json) {
                Log.i(TAG, "onResponse: "+json);
                refreshLayout.finishRefresh(true);
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toasty.success(mActivity, "刷新成功", Toast.LENGTH_SHORT, true).show();
                        newsListData = (ArrayList<NewsBean>) JsonParseUtils.getList(NewsBean.class,json);
                        homeMultiItemQuickAdapter.setNewData(newsListData);
                    }
                });
            }
        });
    }
    void getADData(){
        NetUtil.getData(GlobalConstants.REQUEST_AD_URL, new NetUtil.MyCallBack() {
            @Override
            public void onFailure(IOException e) {
                Log.d(TAG,"加载失败；"+e.getLocalizedMessage());
                refreshLayout.finishRefresh(false);
            }

            @Override
            public void onResponse(final String json) {
                Log.i(TAG, "onResponse: "+json);
                refreshLayout.finishRefresh(false);
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ADData = (ArrayList<ADBean>) JsonParseUtils.getList(ADBean.class,json);
                        final Banner banner = homeMultiItemQuickAdapter.getHeaderLayout().findViewById(R.id.banner);
                        banner.setImageLoader(new ImageLoader() {
                            @Override
                            public void displayImage(Context context, Object path, ImageView imageView) {
                                if(path instanceof String){
                                    ImageUtil.setImage(context,(String)path,imageView);
                                }
                                if (path instanceof Integer){
                                    imageView.setImageResource((Integer) path);
                                }
                            }
                        });
                        ArrayList<String> imageViews = new ArrayList<>();
                        for (int i = 0; i <3; i++) {
                            imageViews.add(GlobalConstants.SERVER_URL+ADData.get(i).getImg1());
                        }
                        //设置图片集合
                        banner.setImages(imageViews);
                        //banner设置方法全部调用完毕时最后调用
                        banner.start();
                    }
                });
            }
        });
    }
}
