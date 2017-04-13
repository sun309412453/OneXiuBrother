package com.zykj.onexiubrother.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zykj.onexiubrother.R;
import com.zykj.onexiubrother.widget.MyTitleBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/13.
 */

public class Activity_Mobile extends Activity {
    @Bind(R.id.title)
    MyTitleBar title;
    @Bind(R.id.mobile_ok)
    Button mobileOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_mobile);
        ButterKnife.bind(this);
        title.setLeftClick(new MyTitleBar.leftClick() {
            @Override
            public void Click(View view) {
                finish();
            }
        });
    }

    @OnClick(R.id.appliance_ok)
    public void onViewClicked() {
        Intent applianceOkintent = new Intent();
        startActivity(applianceOkintent);
    }

    @OnClick(R.id.mobile_ok)
    public void onViewClicked(View view) {
        Intent mobileOkIntent = new Intent(this,Activity_Call_Service.class);
        startActivity(mobileOkIntent);
        }
    }
