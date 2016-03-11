package com.junbaor.kuworadio;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpStatus;

/**
 * Created by Administrator on 2016/2/25.
 */
public class PlayActivity extends AppCompatActivity {

    private Button btn_playNetAudio;
    private Button btn_stopNetAudio;

    private TextView txt_name;

    private String id;
    private String name;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#303F9F"));
        }
        setTitle("播放界面");
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        txt_name = (TextView) findViewById(R.id.txt_name);
        txt_name.setText(name);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_playNetAudio = (Button) findViewById(R.id.btn_playNetAudio);
        btn_stopNetAudio = (Button) findViewById(R.id.btn_stopNetAudio);
        btn_playNetAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncHttpClient client = new AsyncHttpClient();
                client.get("http://antiserver.kuwo.cn/anti.s?type=convert_url&rid=MUSIC_" + id + "&format=mp3&response=url", new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        if (statusCode == HttpStatus.SC_OK) {
                            url = new String(responseBody);
                            Intent intent = new Intent(PlayActivity.this, PlayService.class);
                            intent.putExtra("URI", url);
                            startService(intent);
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Toast.makeText(PlayActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("获取音频地址失败", error.getMessage());
                    }
                });

            }
        });
        btn_stopNetAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlayActivity.this, PlayService.class);
                stopService(intent);
            }
        });
    }


}
