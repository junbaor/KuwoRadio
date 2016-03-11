package com.junbaor.kuworadio;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.junbaor.kuworadio.model.Kuwo;
import com.junbaor.kuworadio.model.Music;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpStatus;

public class MainActivity extends AppCompatActivity {


    private final String url = "http://album.kuwo.cn/album/servlet/commkdtpage?flag=2&listid=2";

    private List<Map<String, Object>> list1 = new ArrayList<>();
    private List<Map<String, Object>> list2 = new ArrayList<>();
    private List<Map<String, Object>> list3 = new ArrayList<>();
    private List<Map<String, Object>> list4 = new ArrayList<>();
    private List<Map<String, Object>> list5 = new ArrayList<>();

    private SimpleAdapter kuwoAdapter1;
    private SimpleAdapter kuwoAdapter2;
    private SimpleAdapter kuwoAdapter3;
    private SimpleAdapter kuwoAdapter4;
    private SimpleAdapter kuwoAdapter5;

    private SwipeRefreshLayout swipeRefreshLayout1;
    private SwipeRefreshLayout swipeRefreshLayout2;
    private SwipeRefreshLayout swipeRefreshLayout3;
    private SwipeRefreshLayout swipeRefreshLayout4;
    private SwipeRefreshLayout swipeRefreshLayout5;

    private ViewPager pager;
    private ListView listView;
    private TabLayout tabLayout;

    private String getUrl(int i) {
        return "http://album.kuwo.cn/album/servlet/commkdtpage?flag=2&listid=" + String.valueOf(i);
    }

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
        ArrayList<FrameLayout> frameLayouts = new ArrayList<>();
        MyOnItemClickListener onItemClickListener = new MyOnItemClickListener();
        FrameLayout frameLayout1 = (FrameLayout) getLayoutInflater().inflate(R.layout.fragment_my, null);
        kuwoAdapter1 = new SimpleAdapter(MainActivity.this, list1, R.layout.listview_item, new String[]{"id", "name", "artist"},
                new int[]{R.id.txtMusicId, R.id.txtMusicName, R.id.txtMusicArtist});
        ((ListView) frameLayout1.findViewById(R.id.lvShow)).setAdapter(kuwoAdapter1);
        ((ListView) frameLayout1.findViewById(R.id.lvShow)).setOnItemClickListener(onItemClickListener);
        swipeRefreshLayout1 = setSwipeRefreshLayout((SwipeRefreshLayout) frameLayout1.findViewById(R.id.swipeLayout));
        getDate(1);
        frameLayouts.add(frameLayout1);

        FrameLayout frameLayout2 = (FrameLayout) getLayoutInflater().inflate(R.layout.fragment_my, null);
        kuwoAdapter2 = new SimpleAdapter(MainActivity.this, list2, R.layout.listview_item, new String[]{"id", "name", "artist"},
                new int[]{R.id.txtMusicId, R.id.txtMusicName, R.id.txtMusicArtist});
        ((ListView) frameLayout2.findViewById(R.id.lvShow)).setAdapter(kuwoAdapter2);
        ((ListView) frameLayout2.findViewById(R.id.lvShow)).setOnItemClickListener(onItemClickListener);
        swipeRefreshLayout2 = setSwipeRefreshLayout((SwipeRefreshLayout) frameLayout2.findViewById(R.id.swipeLayout));
        getDate(2);
        frameLayouts.add(frameLayout2);

        FrameLayout frameLayout3 = (FrameLayout) getLayoutInflater().inflate(R.layout.fragment_my, null);
        kuwoAdapter3 = new SimpleAdapter(MainActivity.this, list3, R.layout.listview_item, new String[]{"id", "name", "artist"}, new int[]{R.id.txtMusicId, R.id.txtMusicName, R.id.txtMusicArtist});
        ((ListView) frameLayout3.findViewById(R.id.lvShow)).setAdapter(kuwoAdapter3);
        ((ListView) frameLayout3.findViewById(R.id.lvShow)).setOnItemClickListener(onItemClickListener);
        swipeRefreshLayout3 = setSwipeRefreshLayout((SwipeRefreshLayout) frameLayout3.findViewById(R.id.swipeLayout));
        getDate(3);
        frameLayouts.add(frameLayout3);

        FrameLayout frameLayout4 = (FrameLayout) getLayoutInflater().inflate(R.layout.fragment_my, null);
        kuwoAdapter4 = new SimpleAdapter(MainActivity.this, list4, R.layout.listview_item, new String[]{"id", "name", "artist"}, new int[]{R.id.txtMusicId, R.id.txtMusicName, R.id.txtMusicArtist});
        ((ListView) frameLayout4.findViewById(R.id.lvShow)).setAdapter(kuwoAdapter4);
        ((ListView) frameLayout4.findViewById(R.id.lvShow)).setOnItemClickListener(onItemClickListener);
        swipeRefreshLayout4 = setSwipeRefreshLayout((SwipeRefreshLayout) frameLayout4.findViewById(R.id.swipeLayout));
        getDate(4);
        frameLayouts.add(frameLayout4);

        FrameLayout frameLayout5 = (FrameLayout) getLayoutInflater().inflate(R.layout.fragment_my, null);
        kuwoAdapter5 = new SimpleAdapter(MainActivity.this, list5, R.layout.listview_item, new String[]{"id", "name", "artist"}, new int[]{R.id.txtMusicId, R.id.txtMusicName, R.id.txtMusicArtist});
        ((ListView) frameLayout5.findViewById(R.id.lvShow)).setAdapter(kuwoAdapter5);
        ((ListView) frameLayout5.findViewById(R.id.lvShow)).setOnItemClickListener(onItemClickListener);
        swipeRefreshLayout5 = setSwipeRefreshLayout((SwipeRefreshLayout) frameLayout5.findViewById(R.id.swipeLayout));
        getDate(5);
        frameLayouts.add(frameLayout5);

        MyPagerAdapter adapter = new MyPagerAdapter(frameLayouts);
        pager.setAdapter(adapter);
        //绑定viewPager
        tabLayout.setupWithViewPager(pager);

        //设置tab标题
        tabLayout.getTabAt(0).setText("吐小槽扒新闻");
        tabLayout.getTabAt(1).setText("莫萱日记");
        tabLayout.getTabAt(2).setText("爆笑糗事段子");
        tabLayout.getTabAt(3).setText("柜子开了");
        tabLayout.getTabAt(4).setText("酷我音乐调频");

    }

    private SwipeRefreshLayout setSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout) {
        //swipeRefreshLayout = (SwipeRefreshLayout) getLayoutInflater().inflate(R.layout.fragment_my, null).findViewById(R.id.swipeLayout);

        swipeRefreshLayout.setColorSchemeResources(R.color.swipe_color_1,
                R.color.swipe_color_2,
                R.color.swipe_color_3,
                R.color.swipe_color_4);

        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        swipeRefreshLayout.setBackgroundColor(R.color.swipe_background_color);
        /*swipeRefreshLayout.setProgressViewEndTarget(true, 50);  //指定开始位置距离顶部的距离
        swipeRefreshLayout.setProgressViewOffset(true, 500, 800);  //指定起始结束距顶部距离*/

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mHandler.sendEmptyMessage(tabLayout.getSelectedTabPosition() + 1);
                    }
                }).start();
            }
        });
        return swipeRefreshLayout;
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            getDate(msg.what);
            Log.d("tag", "mHandler正在执行" + String.valueOf(msg.what));
            if (msg.what == 1) {
                swipeRefreshLayout1.setRefreshing(false);
            } else if (msg.what == 2) {
                swipeRefreshLayout2.setRefreshing(false);
            } else if (msg.what == 3) {
                swipeRefreshLayout3.setRefreshing(false);
            } else if (msg.what == 4) {
                swipeRefreshLayout4.setRefreshing(false);
            } else if (msg.what == 5) {
                swipeRefreshLayout5.setRefreshing(false);
            }
        }
    };

    private void getDate(final int i) {
        AsyncHttpClient client = new AsyncHttpClient();
        Log.d("tag", "getDate准备发起请求" + String.valueOf(i));
        client.get(getUrl(i), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == HttpStatus.SC_OK) {
                    Gson gson = new Gson();
                    try {
                        if (i == 1) {
                            list1.clear();
                        } else if (i == 2) {
                            list2.clear();
                        } else if (i == 3) {
                            list3.clear();
                        } else if (i == 4) {
                            list4.clear();
                        } else {
                            list5.clear();
                        }
                        Kuwo kuwo = gson.fromJson(new String(responseBody), Kuwo.class);
                        for (Music music : kuwo.getMusiclist()) {
                            String name = music.getName();
                            if (name.indexOf("(") > -1) {
                                name = name.substring(0, name.indexOf("("));
                            } else if (name.indexOf("（") > -1) {
                                name = name.substring(0, name.indexOf("（"));
                            }
                            Map<String, Object> map = new HashMap<String, Object>();
                            map.put("id", music.getMusicrid());
                            map.put("name", name);
                            map.put("artist", music.getArtist());
                            if (i == 1) {
                                list1.add(map);
                            } else if (i == 2) {
                                list2.add(map);
                            } else if (i == 3) {
                                list3.add(map);
                            } else if (i == 4) {
                                list4.add(map);
                            } else {
                                list5.add(map);
                            }
                            Log.d("tag", "完成");
                        }
                        if (i == 1) {
                            kuwoAdapter1.notifyDataSetChanged();
                        } else if (i == 2) {
                            kuwoAdapter2.notifyDataSetChanged();
                        } else if (i == 3) {
                            kuwoAdapter3.notifyDataSetChanged();
                        } else if (i == 4) {
                            kuwoAdapter4.notifyDataSetChanged();
                        } else {
                            kuwoAdapter5.notifyDataSetChanged();
                        }

                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("失败", error.getMessage());
            }
        });
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
        }else if(id == R.id.action_openPlay){
            Intent intent = new Intent(this, PlayActivity.class);
            startActivity(intent);
            return true;
        }
        return true;
    }

    class MyPagerAdapter extends PagerAdapter {
        ArrayList<FrameLayout> frameLayouts;

        public MyPagerAdapter(ArrayList<FrameLayout> frameLayouts) {
            this.frameLayouts = frameLayouts;
        }

        @Override
        public int getCount() {
            return frameLayouts.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            FrameLayout frameLayout = frameLayouts.get(position);
            container.addView(frameLayout);
            return frameLayout;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(frameLayouts.get(position));
        }

    }

    class MyOnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String ids = (String) ((TextView) view.findViewById(R.id.txtMusicId)).getText();
            final String name = (String) ((TextView) view.findViewById(R.id.txtMusicName)).getText();
            /*String artist = (String) ((TextView) view.findViewById(R.id.txtMusicArtist)).getText();*/
            /*Toast.makeText(MainActivity.this, ids + "," + name + "," + artist, Toast.LENGTH_SHORT).show();*/
            Log.d("tag", "onItemClick");
            Intent intent = new Intent(MainActivity.this,PlayActivity.class);
            intent.putExtra("id",ids);
            intent.putExtra("name",name);
            startActivity(intent);
        }
    }
}
