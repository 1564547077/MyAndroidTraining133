package com.wangliangjun.androidtraining133.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.wangliangjun.androidtraining133.R;

public abstract class BaseFragment extends Fragment {
    protected Activity mActivity;
    protected String TAG;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
        TAG = getClass().getSimpleName();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view;
        if(setLayoutResourceId()==0){
            view = new TextView(mActivity);
            ((TextView) view).setText(TAG);

        }else{
            view = inflater.inflate(setLayoutResourceId(),container,false);
        }
        ImmersionBar.with(this).init();
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected int setLayoutResourceId(){
        return 0;
    }

    protected abstract void initView(View view);

    protected abstract void initData();

}
