package com.zykj.onexiubrother.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hss01248.dialog.StyledDialog;
import com.zykj.onexiubrother.R;
import com.zykj.onexiubrother.utils.Y;
import com.zykj.onexiubrother.utils.YURL;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/21.
 */

public class Activity_DengLu extends Activity {
    @Bind(R.id.denglu_iv_touxiang)
    ImageView dengluIvTouxiang;
    @Bind(R.id.denglu_et_name)
    EditText dengluEtName;
    @Bind(R.id.denglu_et_pwd)
    EditText dengluEtPwd;
    @Bind(R.id.denglu_bt_denglu)
    Button dengluBtDenglu;
    @Bind(R.id.denglu_tv_zhuce)
    TextView dengluTvZhuce;
    @Bind(R.id.denglu_tv_wangjimima)
    TextView dengluTvWangjimima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.denglu);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.denglu_bt_denglu, R.id.denglu_tv_zhuce,R.id.denglu_tv_wangjimima})
    public void onClick(View view) {
        switch (view.getId()) {
            //登录
            case R.id.denglu_bt_denglu:
                String name = dengluEtName.getText().toString().trim();
                String pwd = dengluEtPwd.getText().toString().trim();
                if (TextUtils.isEmpty(name)){
                    Y.t("用户名不能为空");
                    return;
                }
                if (TextUtils.isEmpty(pwd)){
                    Y.t("密码不能为空");
                    return;
                }
                Map<String,String> map = new HashMap<String, String>();
                map.put("phone",name);
                map.put("password",pwd);
                Y.get(YURL.LOGIN, map, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        //关闭对话框
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)){
                            Y.t("登录成功");
                            String data = Y.getData(result);
                            Intent dengLuIntent = new Intent(Activity_DengLu.this,MainActivity.class);
                            dengLuIntent.putExtra("data",data);
                            startActivity(dengLuIntent);
                        }else {
                            Y.t("用户名或密码不正确");
                        }

                    }
                });
                break;
            //注册
            case R.id.denglu_tv_zhuce:
                Intent zhuCeIntent = new Intent(this,Activity_ZhuCe.class);
                startActivity(zhuCeIntent);
                break;
            case R.id.denglu_tv_wangjimima:
                break;
        }
    }
}
