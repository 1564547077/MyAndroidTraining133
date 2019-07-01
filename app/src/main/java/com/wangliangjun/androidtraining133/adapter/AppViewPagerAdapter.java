package com.wangliangjun.androidtraining133.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.wangliangjun.androidtraining133.fragment.BaseFragment;

import java.util.ArrayList;

public class AppViewPagerAdapter extends FragmentStatePagerAdapter {
    ArrayList<BaseFragment> data;
    public AppViewPagerAdapter(FragmentManager fm,ArrayList<BaseFragment> data) {
        super(fm);
        this.data = data;
    }

    @Override
    public Fragment getItem(int i) {
        return data.get(i);
    }

    @Override
    public int getCount() {
        return data.size();
    }
}
