package com.zykj.onexiubrother.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
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
 * 注册
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
                String phone = zhuceEtPhone.getText().toString().trim();
                String code = zhuceEtYanzhengma.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    Y.t("手机号不能为空");
                    return;
                }
                if (!Y.checkCallphone(phone)) {
                    Y.t("手机号码输入有误");
                    return;
                }
                if (TextUtils.isEmpty(code)) {
                    Y.t("验证码不能为空");
                    return;
                }
                if (code.length() != 4) {
                    Y.t("验证码输入有误");
                    return;
                }
                Map<String, String> map = new HashMap<String, String>();
                map.put("phone", phone);
                map.put("vcode", code);
                map.put("type", 0 + "");
                Y.get(YURL.REGISTER, map, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {
                            String data = Y.getData(result);
                            Intent zhuCeIntent = new Intent(Activity_ZhuCe.this, Activity_MiMa.class);
                            zhuCeIntent.putExtra("token", data);
                            startActivity(zhuCeIntent);
                        } else {
                            Y.t(JSON.parseObject(result).getString("message"));
                        }

                    }
                });
                break;
        }
    }
}