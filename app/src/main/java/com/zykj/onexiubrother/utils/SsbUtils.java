package com.zykj.onexiubrother.utils;

import android.content.Context;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * 工具类
 * Created by zykj on 2017/4/8.
 */

public  class SsbUtils {
    public static Context context;
    //LOG日志工具类
    public static void i(String str){
        Logger.i(str);
    }
    //Toast工具类
    public static void t(String str){
        Toast.makeText(context,str, Toast.LENGTH_SHORT).show();
    }
    //get请求 工具类
    public static void get(RequestParams params, Callback.CommonCallback<Object> callback){
        x.http().get(params,callback);
    }
    //post请求 工具类
    public static void post(RequestParams params, Callback.CommonCallback<Object> callback){
        x.http().get(params,callback);
    }
}
