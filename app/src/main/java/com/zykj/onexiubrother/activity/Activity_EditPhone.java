package com.zykj.onexiubrother.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zykj.onexiubrother.R;
import com.zykj.onexiubrother.widget.MyTitleBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/13.
 */
//WprQSsIdo28xvCQGOy4ifoHBayVhIzho
public class Activity_EditPhone extends Activity {
    @Bind(R.id.title)
    MyTitleBar title;
    @Bind(R.id.edit_et_phone)
    EditText editEtPhone;
    @Bind(R.id.edit_bt_ok)
    Button editBtOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editphone);
        ButterKnife.bind(this);
        title.setLeftClick(new MyTitleBar.leftClick() {
            @Override
            public void Click(View view) {
                finish();
            }
        });

    }

    @OnClick(R.id.edit_bt_ok)
    public void onViewClicked() {
        String s = editEtPhone.getText().toString().trim();
        Intent intent = getIntent();
        intent.putExtra("phone",s);
       this.setResult(101,intent);
        this.finish();
    }
}
