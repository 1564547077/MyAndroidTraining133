package com.wangliangjun.androidtraining133.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Toast;
import com.gyf.immersionbar.ImmersionBar;

public abstract class BaseActivity extends AppCompatActivity {
    protected String TAG;
    private long exitTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //隐藏顶部系统自带标题
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        ImmersionBar.with(this).statusBarAlpha(0.2f).init();
        TAG = getClass().getSimpleName();
        super.onCreate(savedInstanceState);
    }

    //判断是否快速点击返回键退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            //判断上次点击返回距离这次是否在两秒内，是的话退出否则弹出提示
            if(System.currentTimeMillis()-exitTime>2000){
                Toast.makeText(this, "再按一次退出当前App",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            }else{
                //结束当前app并回收Java资源
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
