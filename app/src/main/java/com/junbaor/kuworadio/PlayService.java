package com.junbaor.kuworadio;


import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;

/**
 * Created by Administrator on 2016/2/26.
 */
public class PlayService extends Service {
    private MediaPlayer mediaPlayer;
    String currentPlay = null; //当前播放的文件

    @Override
    public void onCreate() {
        Log.d("info：", "MyService.onCreate...");
        super.onCreate();
        mediaPlayer = new MediaPlayer();
    }

    @Override
    public void onDestroy() {
        Log.d("info：", "MyService.onDestroy...");
        mediaPlayer.stop();
        mediaPlayer.release();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("info：", "MyService.onBind...");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("info：", "MyService.onStartCommand...");
        String URI = intent.getStringExtra("URI");
        if (URI == null ||
                URI.isEmpty() ||
                URI.equals(currentPlay)) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            } else {
                mediaPlayer.start();
            }
        } else {
            mediaPlayer.reset();
            try {
                mediaPlayer.setDataSource(URI);
                mediaPlayer.prepare();
                mediaPlayer.start();
                currentPlay = URI;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

}
