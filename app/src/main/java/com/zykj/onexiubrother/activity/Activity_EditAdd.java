package com.zykj.onexiubrother.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.zykj.onexiubrother.R;
import com.zykj.onexiubrother.widget.MyTitleBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/13.
 */

public class Activity_EditAdd extends Activity {
    @Bind(R.id.title)
    MyTitleBar title;
    @Bind(R.id.edit_phone)
    LinearLayout editPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editadd);
        ButterKnife.bind(this);
        title.setLeftClick(new MyTitleBar.leftClick() {
            @Override
            public void Click(View view) {
                finish();
            }
        });
    }

    @OnClick(R.id.edit_phone)
    public void onViewClicked() {
        Intent editPhoneIntent = new Intent(this,Activity_EditPhone.class);
        startActivity(editPhoneIntent);
    }
}
