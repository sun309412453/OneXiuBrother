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

public class Activity_SheZhi extends Activity {
    @Bind(R.id.title)
    MyTitleBar title;
    @Bind(R.id.shezhi_xiugai)
    LinearLayout shezhiXiugai;
    @Bind(R.id.shezhi_tongzhi)
    LinearLayout shezhiTongzhi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shezhi);
        ButterKnife.bind(this);
        title.setLeftClick(new MyTitleBar.leftClick() {
            @Override
            public void Click(View view) {
                finish();
            }
        });
    }

    @OnClick({R.id.shezhi_xiugai, R.id.shezhi_tongzhi,R.id.tuichu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shezhi_xiugai:
                Intent intentXiuGai = new Intent(this,Activity_XiuGaiMiMa.class);
                startActivity(intentXiuGai);
                finish();
                break;
            case R.id.shezhi_tongzhi:
                Intent intentTongZhi = new Intent(this,Activity_XiaoXiTongZhi.class);
                startActivity(intentTongZhi);
                break;
            case R.id.tuichu:
                Intent intentTuiChu = new Intent(this,Activity_DengLu.class);
                startActivity(intentTuiChu);
                finish();
                break;
        }
    }
}
