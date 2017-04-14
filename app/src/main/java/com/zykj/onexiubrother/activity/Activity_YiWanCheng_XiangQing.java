package com.zykj.onexiubrother.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.zykj.onexiubrother.R;
import com.zykj.onexiubrother.widget.MyTitleBar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/14.
 */

public class Activity_YiWanCheng_XiangQing extends Activity {
    @Bind(R.id.title)
    MyTitleBar title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yiwancheng_xiangqing);
        ButterKnife.bind(this);
        title.setLeftClick(new MyTitleBar.leftClick() {
            @Override
            public void Click(View view) {
                finish();
            }
        });

    }
}
