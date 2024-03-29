package com.wangliangjun.androidtraining133.adapter;

import android.animation.Animator;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.wangliangjun.androidtraining133.R;
import com.wangliangjun.androidtraining133.bean.NewsBean;
import com.wangliangjun.androidtraining133.global.GlobalConstants;
import com.wangliangjun.androidtraining133.utils.ImageUtil;
import com.wangliangjun.androidtraining133.utils.JsonParseUtils;
import com.wangliangjun.androidtraining133.viewholder.HomeViewHolder;

import java.util.ArrayList;
import java.util.List;

public class HomeMultiItemQuickAdapter extends BaseMultiItemQuickAdapter<NewsBean, HomeViewHolder> {
    ArrayList<NewsBean> data;
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public HomeMultiItemQuickAdapter(List<NewsBean> data) {
        super(data);
        //根据不同的新闻类型选择不同的布局
        addItemType(1, R.layout.item_news1);
        addItemType(2, R.layout.item_news2);
    }

    @Override
    protected void convert(HomeViewHolder helper, NewsBean item) {
        switch (helper.getItemViewType()){
            case 1:
                helper.setText(R.id.newsTitle1,item.getNewsName());
                helper.setText(R.id.newsType1,item.getNewsTypeName());
                ImageUtil.setImage(mContext,GlobalConstants.SERVER_URL + item.getImg1(),(ImageView) helper.getView(R.id.newsImg1));
                break;
            case 2:
                helper.setText(R.id.newsTitle2,item.getNewsName());
                helper.setText(R.id.newsType2,item.getNewsTypeName());
                ImageUtil.setImage(mContext,GlobalConstants.SERVER_URL + item.getImg1(),(ImageView) helper.getView(R.id.news2img1));
                ImageUtil.setImage(mContext,GlobalConstants.SERVER_URL + item.getImg2(),(ImageView) helper.getView(R.id.news2img2));
                ImageUtil.setImage(mContext,GlobalConstants.SERVER_URL + item.getImg3(),(ImageView) helper.getView(R.id.news2img3));
                break;
        }
    }
}
