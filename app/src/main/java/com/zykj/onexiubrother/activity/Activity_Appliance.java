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
 * Created by Administrator on 2017/4/12.
 */

public class Activity_Appliance extends Activity {
    @Bind(R.id.title)
    MyTitleBar title;
    @Bind(R.id.appliance_ok)
    Button applianceOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_appliance);
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
        Intent applianceOkIntent = new Intent(this,Activity_Call_Service.class);
        startActivity(applianceOkIntent);
    }
}
