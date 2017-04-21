package com.zykj.onexiubrother.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zykj.onexiubrother.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/21.
 */

public class Activity_DengLu extends Activity {
    @Bind(R.id.denglu_iv_touxiang)
    ImageView dengluIvTouxiang;
    @Bind(R.id.denglu_et_name)
    EditText dengluEtName;
    @Bind(R.id.denglu_et_pwd)
    EditText dengluEtPwd;
    @Bind(R.id.denglu_bt_denglu)
    Button dengluBtDenglu;
    @Bind(R.id.denglu_tv_zhuce)
    TextView dengluTvZhuce;
    @Bind(R.id.denglu_tv_wangjimima)
    TextView dengluTvWangjimima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.denglu);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.denglu_bt_denglu, R.id.denglu_tv_zhuce,R.id.denglu_tv_wangjimima})
    public void onClick(View view) {
        switch (view.getId()) {
            //登录
            case R.id.denglu_bt_denglu:
                break;
            //注册
            case R.id.denglu_tv_zhuce:
                Intent zhuCeIntent = new Intent(this,Activity_ZhuCe.class);
                startActivity(zhuCeIntent);
                break;
            case R.id.denglu_tv_wangjimima:
                break;
        }
    }
}
