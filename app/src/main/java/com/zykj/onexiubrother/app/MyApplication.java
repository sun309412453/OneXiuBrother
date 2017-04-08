package com.zykj.onexiubrother.app;

import android.app.Application;

import com.zykj.onexiubrother.utils.SsbUtils;

import org.xutils.x;

/**
 * Created by zykj on 2017/4/8.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        SsbUtils.context=this;
    }
}
