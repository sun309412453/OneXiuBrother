package com.zykj.onexiubrother.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Activity_EditAdd extends Activity {
    @Bind(R.id.title)
    MyTitleBar title;
    @Bind(R.id.edit_phone)
    LinearLayout editPhone;
    @Bind(R.id.add_ok)
    Button addOk;
    @Bind(R.id.edit_tv_phone)
    TextView editTvPhone;
    @Bind(R.id.et_xiangxidizhi)
    TextView etXiangxidizhi;
    @Bind(R.id.ll_xiangxidizhi)
    LinearLayout llXiangxidizhi;
    private String phone;

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

    @OnClick({R.id.edit_phone, R.id.add_ok,R.id.ll_xiangxidizhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edit_phone:
                Intent intent = new Intent(this, Activity_EditPhone.class);
                startActivityForResult(intent, 100);
                break;
            case R.id.add_ok:

                break;
            case R.id.ll_xiangxidizhi:
                Intent intent1 = new Intent(this,Activity_DiZhiMap.class);
                startActivityForResult(intent1,0);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 101) {
            Bundle bundle = data.getExtras();
            phone = bundle.getString("phone");
            editTvPhone.setText(phone);
        }
    }
}
