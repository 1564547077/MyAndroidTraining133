package com.wangliangjun.androidtraining133.activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.wangliangjun.androidtraining133.R;
import com.wangliangjun.androidtraining133.adapter.AppViewPagerAdapter;
import com.wangliangjun.androidtraining133.fragment.BaseFragment;
import com.wangliangjun.androidtraining133.fragment.ChartFragment;
import com.wangliangjun.androidtraining133.fragment.HomeFragment;
import com.wangliangjun.androidtraining133.fragment.PersonFragment;
import com.wangliangjun.androidtraining133.fragment.VideoFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    private TextView mTextMessage;
    private ViewPager appViewPager;
    private TextView tile;

    private ArrayList<BaseFragment> pages = new ArrayList<>();
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    appViewPager.setCurrentItem(0);
                    tile.setText("新闻");
                    return true;
                case R.id.navigation_chart:
                    appViewPager.setCurrentItem(1);
                    tile.setText("统计");
                    return true;
                case R.id.navigation_video:
                    appViewPager.setCurrentItem(2);
                    tile.setText("视频");
                    return true;
                case R.id.navigation_person:
                    appViewPager.setCurrentItem(3);
                    tile.setText("个人");
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
        tile = findViewById(R.id.toolbarTitle);//获取顶部标题
        tile.setText("新闻");
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        appViewPager = findViewById(R.id.AppViewPager);
        pages.add(new HomeFragment());
        pages.add(new ChartFragment());
        pages.add(new VideoFragment());
        pages.add(new PersonFragment());
        appViewPager.setAdapter(new AppViewPagerAdapter(getSupportFragmentManager(),pages));
        //设置viewPage页面改变时底部导航栏选中状态也跟着改变
        appViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                navView.getMenu().getItem(position).setChecked(true);
                switch (position){
                    case 0:
                        tile.setText("新闻");
                        break;
                    case 1:
                        tile.setText("统计");
                        break;
                    case 2:
                        tile.setText("视频");
                        break;
                    case 3:
                        tile.setText("个人");
                        break;
                }
            }
        });
    }

}
