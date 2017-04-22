package com.zykj.onexiubrother.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.hss01248.dialog.StyledDialog;
import com.zykj.onexiubrother.R;
import com.zykj.onexiubrother.utils.Y;
import com.zykj.onexiubrother.utils.YURL;
import com.zykj.onexiubrother.widget.MyTitleBar;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/21.
 */

public class Activity_MiMa extends Activity {
    @Bind(R.id.title)
    MyTitleBar title;
    @Bind(R.id.mima_et_mima)
    EditText mimaEtMima;
    @Bind(R.id.mima_et_querenmima)
    EditText mimaEtQuerenmima;
    @Bind(R.id.mima_bt_queren)
    Button mimaBtQueren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mima);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.mima_bt_queren)
    public void onClick() {
        String mima = mimaEtMima.getText().toString().trim();
        String queren = mimaEtQuerenmima.getText().toString().trim();
        if (TextUtils.isEmpty(mima)){
            Y.t("密码不能为空");
            return;
        }
        if (TextUtils.isEmpty(queren)){
            Y.t("确认密码不能为空");
            return;
        }
        if (!mima.equals(queren)){
            Y.t("密码要与确认密码一致");
            return;
        }
        Map<String,String> map = new HashMap<String, String>();
        map.put("password",mima);
        map.put("token",getIntent().getStringExtra("token"));
        Y.get(YURL.SETPASSWORD, map, new Y.MyCommonCall<String>() {
            @Override
            public void onSuccess(String result) {
                //关闭对话框
                StyledDialog.dismissLoading();
                if (Y.getRespCode(result)){
                    Y.t("注册成功");
                    Intent miMaIntent = new Intent(Activity_MiMa.this,Activity_DengLu.class);
                    miMaIntent.putExtra("token",getIntent().getStringExtra("token"));
                    startActivity(miMaIntent);
                }
            }
        });
    }
}
