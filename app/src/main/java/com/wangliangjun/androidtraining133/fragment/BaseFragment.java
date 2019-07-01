package com.wangliangjun.androidtraining133.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public abstract class BaseFragment extends Fragment {
    protected Activity mActivity;
    protected String TAG;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
        TAG = getClass().getSimpleName();
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
            view = inflater.inflate(setLayoutResourceId(),container);
        }
        return view;
    }
    protected int setLayoutResourceId(){
        return 0;
    }
}
