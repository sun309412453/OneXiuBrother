package com.zykj.onexiubrother.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zykj.onexiubrother.R;
import com.zykj.onexiubrother.widget.MyTitleBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/21.
 */

public class Activity_ZhuCe extends Activity {
    @Bind(R.id.title)
    MyTitleBar title;
    @Bind(R.id.zhuce_et_phone)
    EditText zhuceEtPhone;
    @Bind(R.id.zhuce_et_yanzhengma)
    EditText zhuceEtYanzhengma;
    @Bind(R.id.zhuce_bt_huoquyanzhengma)
    TextView zhuceBtHuoquyanzhengma;
    @Bind(R.id.zhuce_bt_zhuce)
    Button zhuceBtZhuce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuce);
        ButterKnife.bind(this);
        title.setLeftClick(new MyTitleBar.leftClick() {
            @Override
            public void Click(View view) {
                finish();
            }
        });
    }

    @OnClick({R.id.zhuce_bt_huoquyanzhengma, R.id.zhuce_bt_zhuce})
    public void onClick(View view) {
        switch (view.getId()) {
            //获取验证码
            case R.id.zhuce_bt_huoquyanzhengma:
                break;
            //注册
            case R.id.zhuce_bt_zhuce:
                break;
        }
    }
}
