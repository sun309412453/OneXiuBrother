package com.zykj.onexiubrother.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zykj.onexiubrother.R;
import com.zykj.onexiubrother.widget.MyTitleBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/13.
 */

public class Activity_GeRenZhongXin extends Activity {
    @Bind(R.id.title)
    MyTitleBar title;
    @Bind(R.id.weiwancheng)
    LinearLayout weiwancheng;
    @Bind(R.id.yiwancheng)
    LinearLayout yiwancheng;
    @Bind(R.id.yiquxiao)
    LinearLayout yiquxiao;
    @Bind(R.id.tishi)
    TextView tishi;
    @Bind(R.id.wodeziliao)
    LinearLayout wodeziliao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_center);
        ButterKnife.bind(this);
        title.setLeftClick(new MyTitleBar.leftClick() {
            @Override
            public void Click(View view) {
                finish();
            }
        });
    }

    @OnClick({R.id.weiwancheng, R.id.yiwancheng, R.id.yiquxiao,R.id.wodeziliao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.weiwancheng:
                Intent weiWanChengIntent = new Intent(this, Activity_WeiWanCheng.class);
                startActivity(weiWanChengIntent);
                tishi.setVisibility(View.INVISIBLE);
                break;
            case R.id.yiwancheng:
                Intent yiWanChengIntent = new Intent(this, Activity_YiWanCheng.class);
                startActivity(yiWanChengIntent);
                break;
            case R.id.yiquxiao:
                Intent yiQuXiaoIntent = new Intent(this, Activity_YiQuXiao.class);
                startActivity(yiQuXiaoIntent);
                break;
            case R.id.wodeziliao:
                Intent ziLiaoIntent = new Intent(this, Activity_WoDeZiLiao.class);
                startActivity(ziLiaoIntent);
                break;
        }
    }
}
