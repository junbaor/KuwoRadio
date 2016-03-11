package com.junbaor.kuworadio;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager pager;

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#303F9F"));
        }


        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        //设置mode 滚动模式动态改变tabLayout宽度
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        pager = (ViewPager) findViewById(R.id.view_pager);
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new KuwoFragment(MainActivity.this, "1"));
        fragments.add(new KuwoFragment(MainActivity.this, "2"));
        fragments.add(new KuwoFragment(MainActivity.this, "3"));
        fragments.add(new KuwoFragment(MainActivity.this, "4"));
        fragments.add(new KuwoFragment(MainActivity.this, "5"));
        fragments.add(new KuwoFragment(MainActivity.this, "6"));
        fragments.add(new KuwoFragment(MainActivity.this, "15"));
        fragments.add(new KuwoFragment(MainActivity.this, "22"));

        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), fragments);
        pager.setAdapter(adapter);
        //绑定viewPager
        tabLayout.setupWithViewPager(pager);

        //设置tab标题
        tabLayout.getTabAt(0).setText("吐小槽扒新闻");
        tabLayout.getTabAt(1).setText("莫萱日记");
        tabLayout.getTabAt(2).setText("爆笑糗事段子");
        tabLayout.getTabAt(3).setText("柜子开了");
        tabLayout.getTabAt(4).setText("酷我音乐调频");
        tabLayout.getTabAt(5).setText("一路向北");
        tabLayout.getTabAt(6).setText("请给我一首歌的时间");
        tabLayout.getTabAt(7).setText("小曹胡咧咧");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_downManager) {
            Intent intent = new Intent(this, DownManagerActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_openPlay) {
            Intent intent = new Intent(this, PlayActivity.class);
            startActivity(intent);
            return true;
        }
        return true;
    }

    class MyPagerAdapter extends FragmentPagerAdapter {
        List<Fragment> fragments;

        public MyPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragments = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
