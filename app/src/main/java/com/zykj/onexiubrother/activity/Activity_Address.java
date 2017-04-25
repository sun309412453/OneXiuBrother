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

public class Activity_Address extends Activity {
    @Bind(R.id.title)
    MyTitleBar title;
    @Bind(R.id.newadd)
    LinearLayout newadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dizhi);
        ButterKnife.bind(this);
        title.setLeftClick(new MyTitleBar.leftClick() {
            @Override
            public void Click(View view) {
                finish();
            }
        });
    }

    @OnClick(R.id.newadd)
    public void onViewClicked() {
        Intent addIntent = new Intent(this,Activity_EditAdd.class);
        startActivityForResult(addIntent,521);
    }
}
