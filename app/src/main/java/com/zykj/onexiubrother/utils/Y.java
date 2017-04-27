package com.zykj.onexiubrother.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.hss01248.dialog.StyledDialog;
import com.orhanobut.logger.Logger;
import com.zykj.onexiubrother.bean.AddressBean;
import com.zykj.onexiubrother.bean.UserBean;

import org.xutils.common.Callback;
import org.xutils.common.util.KeyValue;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.List;
import java.util.Map;

import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * 工具类
 * Created by zykj on 2017/4/8.
 */

public class Y {
    public static Context context; //全局上下文

    public static boolean isLog = true; //控制日志打印的开关

    public static UserBean USER;//保存所有User
    public static String TOKEN;
    public static AddressBean ADDRESS= new AddressBean();
    //手机正则表达式
    public static boolean checkCallphone(String cellphone) {
        String regex = "[1][358]\\d{9}";
        if (TextUtils.isEmpty(cellphone)) {
            return false;
        } else {
            return cellphone.matches(regex);
        }
    }
    //CallBack

    /**
     * 吐司功能只需要传入一个 字符串
     *
     * @param str
     */
    public static void t(String str) {
        Toast.makeText(context, str, Toast.LENGTH_LONG).show();
    }

    /**
     * 输出log日志
     *
     * @param str
     */
    public static void i(String str) {
        if (isLog)
            Logger.i(str);
    }

    /**
     * 检测请求返回的数据是否正确
     */
    public static boolean getRespCode(String result) {
        if ("0".equals(JSON.parseObject(result).getString("resp_code"))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 如果成功获取数据
     */
    public static String getData(String result) {
        return JSON.parseObject(result).getString("data");
    }


    /**
     * get请求  返回成功回调
     *
     * @param params
     * @param call
     * @return
     */
    public static Callback.Cancelable get(String url, Map<String, String> params, MyCommonCall<String> call) {
        if (params == null)
            i(url);
        //请求的对象
        RequestParams rp = new RequestParams(url);

        //检测外部是否传入了参数
        if (params != null) {
            //把参数取出来这是到rp
            for (Map.Entry<String, String> entry : params.entrySet()) {
                rp.addBodyParameter(entry.getKey(), entry.getValue());
            }
        }
        // 只要发起Get请求就开启对话框
        i(rp.toString());
        return x.http().get(rp, call);
    }

    //Post上传文件的函数//内部实现鲁班压缩
    public static void postFile(final RequestParams params, final MyCommonCall<String> call) {
        //获取params内的所有文件
        List<KeyValue> fileParams = params.getFileParams();
        //迭代，把文件的值迭代出来
        for (final KeyValue kv : fileParams) {
            File file = (File) kv.value;
            //启动鲁班
            Luban.get(context).load(file).putGear(Luban.THIRD_GEAR).setCompressListener(new OnCompressListener() {
                @Override
                public void onStart() {
                    i("开始压缩文件");
                }//压缩开始

                @Override
                public void onSuccess(File file) {//压缩成功
                    params.addBodyParameter(kv.key, file);
                    params.setMultipart(true);
                    i(params.toString());
                    x.http().post(params, call);
                }

                @Override
                public void onError(Throwable e) {
                    t("压缩失败");
                }//压缩失败
            }).launch();
        }
    }

    /**
     * post请求  返回成功回调
     *
     * @param params
     * @param call
     * @return
     */
    public static Callback.Cancelable post(String url, Map<String, String> params, MyCommonCall<String> call) {
        if (params == null)
            i(url);
        //请求的对象
        RequestParams rp = new RequestParams(url);

        //检测外部是否传入了参数
        if (params != null) {
            //把参数取出来这是到rp
            i(rp.toString());
            for (Map.Entry<String, String> entry : params.entrySet()) {
                rp.addBodyParameter(entry.getKey(), entry.getValue());
            }
        }
        return x.http().post(rp, call);
    }

    public static Callback.Cancelable post(RequestParams params, MyCommonCall<String> call) {
        i(params.toString());
        return x.http().post(params, call);
    }

    /**
     * 实现不需要外部完成的两个函数
     */
    public abstract static class MyCommonCall<String> implements Callback.CommonCallback<String> {
        @Override
        public void onFinished() {
        }

        @Override
        public void onCancelled(CancelledException cex) {
        }

        @Override
        public void onError(Throwable ex, boolean isOnCallback) {
            t("服务器异常");
            StyledDialog.dismissLoading(); //失败的时候关闭对话框
            ex.printStackTrace();
        }
    }


}
