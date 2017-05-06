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
 * Created by zykj on 2017/4/15.
 */

public class Activity_PingTaiFuWu extends Activity {
    @Bind(R.id.title)
    MyTitleBar title;
    @Bind(R.id.yixiugezixun)
    LinearLayout yixiugezixun;
    @Bind(R.id.changjianwenti)
    LinearLayout changjianwenti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pingtaifuwu);
        ButterKnife.bind(this);
        title.setLeftClick(new MyTitleBar.leftClick() {
            @Override
            public void Click(View view) {
                finish();
            }
        });
    }

    @OnClick({R.id.yixiugezixun, R.id.changjianwenti})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.yixiugezixun:
                Intent intentZiXun = new Intent(this,Activity_YiXiuGeZiXun.class);
                startActivity(intentZiXun);
                break;
            case R.id.changjianwenti:
                Intent intentWenTi  = new Intent(this,Activity_ChangJianWenTi.class);
                startActivity(intentWenTi);
                break;
        }
    }
}
