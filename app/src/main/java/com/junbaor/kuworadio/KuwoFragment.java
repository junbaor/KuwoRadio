package com.junbaor.kuworadio;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
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

/**
 * Created by Administrator on 2016/3/11.
 */
@SuppressLint("ValidFragment")
public class KuwoFragment extends Fragment {
    private List<Map<String, Object>> listDate = new ArrayList<>();
    private String dataUrl;
    private Context context;
    private ListView listView;
    private SimpleAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String id;
    private boolean isInit = false;
    private View view;

    public KuwoFragment() {
        Log.d("DUBUG", "KuwoFragment 无参构造方法执行...");
    }

    public KuwoFragment(Context context, String id) {
        Log.d("DUBUG", "KuwoFragment" + id + " 构造方法执行...");
        this.id = id;
        this.context = context;
        dataUrl = "http://album.kuwo.cn/album/servlet/commkdtpage?flag=2&listid=" + id;
    }

    @Override
    public void onStart() {
        Log.d("DUBUG", "onStart()" + id + "触发...");
        super.onStart();
    }

    @Override
    public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState) {
        Log.d("DUBUG", "onInflate()" + id + "触发...");
        super.onInflate(context, attrs, savedInstanceState);
    }

/*  @Nullable
    @Override
    public View getView() {
        Log.d("DUBUG", "getView()触发...");
        return super.getView();
    }*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("DUBUG", "onCreateView" + id + "方法执行...");
        if (isInit) {
            Log.d("DUBUG", "准备返回缓存的 View...");
            return view;
        }
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        listView = (ListView) view.findViewById(R.id.lvShow);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeLayout);
        setSwipeRefreshLayout(swipeRefreshLayout);
        getDate();
        adapter = new SimpleAdapter(context, listDate, R.layout.listview_item, new String[]{"id", "name", "artist"},
                new int[]{R.id.txtMusicId, R.id.txtMusicName, R.id.txtMusicArtist});
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new MyOnItemClickListener());
        Log.d("DUBUG", "View " + id + "创建完成...");
        isInit = true;
        this.view = view;
        return view;
    }


    private SwipeRefreshLayout setSwipeRefreshLayout(final SwipeRefreshLayout swipeRefreshLayout) {
        swipeRefreshLayout.setColorSchemeResources(R.color.swipe_color_1,
                R.color.swipe_color_2,
                R.color.swipe_color_3,
                R.color.swipe_color_4);

        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        /*swipeRefreshLayout.setBackgroundColor(R.color.swipe_background_color);
         swipeRefreshLayout.setProgressViewEndTarget(true, 50);  //指定开始位置距离顶部的距离
        swipeRefreshLayout.setProgressViewOffset(true, 500, 800);  //指定起始结束距顶部距离*/

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d("DEBUG", "下拉刷新事件触发,即将请求数据...");
                getDate();
            }
        });
        return swipeRefreshLayout;
    }

    private void getDate() {
        swipeRefreshLayout.setRefreshing(true);
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(dataUrl, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == HttpStatus.SC_OK) {
                    Gson gson = new Gson();
                    String res = new String(responseBody);
                    Log.d("api响应", res);
                    Kuwo kuwo = gson.fromJson(res, Kuwo.class);
                    listDate.clear();
                    for (Music music : kuwo.getMusiclist()) {
                        if (music == null) continue;
                        String name = music.getName();
                        if (name.indexOf("(") > -1) {
                            name = name.substring(0, name.indexOf("("));
                        } else if (name.indexOf("（") > -1) {
                            name = name.substring(0, name.indexOf("（"));
                        }
                        Map<String, Object> map = new HashMap<>();
                        map.put("id", music.getMusicrid());
                        map.put("name", name);
                        map.put("artist", music.getArtist());
                        listDate.add(map);
                    }
                    Log.d("DEBUG", id + "数据加载完成...");
                    adapter.notifyDataSetChanged();
                    Log.d("DEBUG", id + "已通知UI更新...");
                    swipeRefreshLayout.setRefreshing(false);
                    Log.d("DEBUG", id + "已取消刷新状态...");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("失败", error.getMessage());
            }
        });
    }

    class MyOnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String ids = (String) ((TextView) view.findViewById(R.id.txtMusicId)).getText();
            final String name = (String) ((TextView) view.findViewById(R.id.txtMusicName)).getText();
            Log.d("tag", "onItemClick");
            Intent intent = new Intent(context, PlayActivity.class);
            intent.putExtra("id", ids);
            intent.putExtra("name", name);
            startActivity(intent);
        }
    }

}
