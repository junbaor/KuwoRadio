package com.junbaor.kuworadio.ext;

import android.app.Application;

import im.fir.sdk.FIR;

/**
 * Created by junbaor on 2016/9/22.
 */
public class BugApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FIR.init(this);
    }

}
