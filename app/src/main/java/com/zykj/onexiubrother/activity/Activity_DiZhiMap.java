package com.zykj.onexiubrother.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.baidu.location.LocationClient;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.zykj.onexiubrother.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/25.
 */

public class Activity_DiZhiMap extends Activity {
    @Bind(R.id.et_map)
    EditText etMap;
    @Bind(R.id.map_ok)
    Button mapOk;
    @Bind(R.id.bmapView)
    MapView bmapView;
    private BaiduMap baiduMap;
    private LocationClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.dizhi_map);
        ButterKnife.bind(this);
        Intent intent = getIntent();
    }

    @OnClick(R.id.map_ok)
    public void onViewClicked() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        bmapView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bmapView.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        bmapView.onPause();
    }
}
