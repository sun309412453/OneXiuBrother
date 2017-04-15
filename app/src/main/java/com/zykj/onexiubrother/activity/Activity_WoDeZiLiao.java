package com.zykj.onexiubrother.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.zykj.onexiubrother.R;
import com.zykj.onexiubrother.widget.MyTitleBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/15.
 */

public class Activity_WoDeZiLiao extends Activity {
    @Bind(R.id.title)
    MyTitleBar title;
    @Bind(R.id.radioButton)
    RadioButton radioButton;
    @Bind(R.id.wodeziliao_diqu)
    LinearLayout wodeziliaoDiqu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wodeziliao);
        ButterKnife.bind(this);
        title.setLeftClick(new MyTitleBar.leftClick() {
            @Override
            public void Click(View view) {
                finish();
            }
        });
    }

    @OnClick(R.id.wodeziliao_diqu)
    public void onViewClicked() {
        Intent diQuIntent = new Intent(this,Activity_WoDeZiLiao_DiQu.class);
        startActivity(diQuIntent);
    }
}
