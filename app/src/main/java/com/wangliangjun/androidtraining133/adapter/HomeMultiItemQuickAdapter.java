package com.wangliangjun.androidtraining133.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.wangliangjun.androidtraining133.bean.NewsList;
import com.wangliangjun.androidtraining133.viewholder.HomeViewHolder;

import java.util.List;

public class HomeMultiItemQuickAdapter extends BaseMultiItemQuickAdapter<NewsList, HomeViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public HomeMultiItemQuickAdapter(List<NewsList> data) {
        super(data);
    }

    @Override
    protected void convert(HomeViewHolder helper, NewsList item) {

    }
}
