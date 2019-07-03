package com.wangliangjun.androidtraining133.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.gyf.immersionbar.ImmersionBar;
import com.wangliangjun.androidtraining133.R;
import com.wangliangjun.androidtraining133.adapter.AppViewPagerAdapter;
import com.wangliangjun.androidtraining133.fragment.BaseFragment;
import com.wangliangjun.androidtraining133.fragment.ChartFragment;
import com.wangliangjun.androidtraining133.fragment.HomeFragment;
import com.wangliangjun.androidtraining133.fragment.PersonFragment;
import com.wangliangjun.androidtraining133.fragment.VideoFragment;
import com.wangliangjun.androidtraining133.utils.PreFUtils;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    private TextView mTextMessage;
    private ViewPager appViewPager;
    private TextView title;
    private Toolbar toolbar;

    private ArrayList<BaseFragment> pages = new ArrayList<>();
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    appViewPager.setCurrentItem(0);
                    title.setText("首页");
                    return true;
                case R.id.navigation_chart:
                    appViewPager.setCurrentItem(1);
                    title.setText("统计");
                    return true;
                case R.id.navigation_video:
                    appViewPager.setCurrentItem(2);
                    title.setText("视频");
                    return true;
                case R.id.navigation_person:
                    appViewPager.setCurrentItem(3);
                    title.setText("个人");
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        toolbar = findViewById(R.id.appToolbar);
        title = findViewById(R.id.toolbarTitle);//获取顶部标题
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        initToolbarStyle();
        initViewPager();
        //设置viewPage页面改变时底部导航栏选中状态也跟着改变
        appViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                navView.getMenu().getItem(position).setChecked(true);
                //根据页面改变toolbar的标题
                switch (position){
                    case 0:
                        title.setText("首页");
                        break;
                    case 1:
                        title.setText("统计");
                        break;
                    case 2:
                        title.setText("视频");
                        break;
                    case 3:
                        title.setText("个人");
                        break;
                }
            }
        });
        Button button = findViewById(R.id.test);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(MainActivity.this)
                        .title("更改状态栏颜色")
                        .titleGravity(GravityEnum.CENTER)
                        .items("白色","绿色","红色","蓝色","黑色")
                        .itemsGravity(GravityEnum.CENTER)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                Toast.makeText(MainActivity.this, which+"", Toast.LENGTH_SHORT).show();
                                switch (which){
                                    case 0:
                                        toolbar.setBackgroundColor(Color.parseColor("#FFFFFF"));
                                        title.setTextColor(Color.parseColor("#000000"));
                                        PreFUtils.setInt(MainActivity.this,"toolbarColor",Color.parseColor("#FFFFFF"));
                                        break;
                                    case 1:
                                        toolbar.setBackgroundColor(Color.parseColor("#008577"));
                                        setTitleWhite();
                                        PreFUtils.setInt(MainActivity.this,"toolbarColor",Color.parseColor("#008577"));
                                        break;
                                    case 2:
                                        toolbar.setBackgroundColor(Color.parseColor("#F00000"));
                                        setTitleWhite();
                                        PreFUtils.setInt(MainActivity.this,"toolbarColor",Color.parseColor("#F00000"));
                                        break;
                                    case 3:
                                        toolbar.setBackgroundColor(Color.parseColor("#0C63CD"));
                                        setTitleWhite();
                                        PreFUtils.setInt(MainActivity.this,"toolbarColor",Color.parseColor("#0C63CD"));
                                        break;
                                    case 4:
                                        toolbar.setBackgroundColor(Color.parseColor("#000000"));
                                        setTitleWhite();
                                        PreFUtils.setInt(MainActivity.this,"toolbarColor",Color.parseColor("#000000"));
                                        break;

                                }
                            }
                        })
                        .show();
            }
        });
    }
    protected void setTitleWhite(){
        title.setTextColor(Color.parseColor("#FFFFFF"));
    }
    protected void initToolbarStyle(){
        int color = PreFUtils.getInt(MainActivity.this,"toolbarColor",Color.parseColor("#FFFFFF"));
        int titleColor;
        title.setText("首页");
        if (color!=Color.parseColor("#FFFFFF")){
            titleColor = Color.parseColor("#FFFFFF");
        }else{
            titleColor = Color.parseColor("#000000");
        }
        toolbar.setBackgroundColor(color);
        title.setTextColor(titleColor);
    }
    private void initViewPager(){
        appViewPager = findViewById(R.id.AppViewPager);
        //将主页面添加入集合中
        pages.add(new HomeFragment());
        pages.add(new ChartFragment());
        pages.add(new VideoFragment());
        pages.add(new PersonFragment());
        appViewPager.setAdapter(new AppViewPagerAdapter(getSupportFragmentManager(),pages));
    }
}
