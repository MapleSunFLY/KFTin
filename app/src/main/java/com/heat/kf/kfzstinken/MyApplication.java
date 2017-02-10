package com.heat.kf.kfzstinken;

import android.app.Application;
import android.content.Context;

import heat.kf.com.tinkenlibrary.service.Judgment;

/**
 * Created by "sinlov" on 2017/2/3.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        Judgment judgment = new Judgment();
//        judgment.fileDex(this);
    }
}
