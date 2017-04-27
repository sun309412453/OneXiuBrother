package com.zykj.onexiubrother.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    @Bind(R.id.dizhi_name)
    EditText dizhiName;
    private String phone,dizhi;

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
        if (!TextUtils.isEmpty(Y.USER.getPhone())){
            editTvPhone.setText(Y.USER.getPhone());
        }
        if (!TextUtils.isEmpty(Y.USER.getUsername())){
            dizhiName.setText(Y.USER.getUsername());
        }
    }

    @OnClick({R.id.edit_phone, R.id.add_ok, R.id.ll_xiangxidizhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edit_phone:
                Intent intent = new Intent(this, Activity_EditPhone.class);
                startActivityForResult(intent, 100);
                break;
            case R.id.add_ok:
                String name = dizhiName.getText().toString().trim();
                if (TextUtils.isEmpty(name)){
                    Y.t("姓名不能为空");
                    return;
                }
//                name: 姓名
//                address: 地址
//                phone: 电话号码
//                user_id:用户ID
//                region:区
//                lat:纬度
//                lon:经度
//                city_name:城市名
//                city_code:城市编码
//                isdefault: 是否是默认 0 不默认  1 默认
                final Map<String,String> map = new HashMap<String, String>();
                map.put("name",name);
                map.put("address",dizhi);
                map.put("phone",editTvPhone.getText().toString());
                map.put("user_id",Y.USER.getUser_id()+"");
                map.put("region",Y.ADDRESS.getRegion());
                map.put("lat",Y.ADDRESS.getLat());
                map.put("lon",Y.ADDRESS.getLon());
                map.put("city_name",Y.ADDRESS.getCity_name());
                map.put("city_code",Y.ADDRESS.getCity_code());
                map.put("isdefault",0+"");//是否默认
                Y.get(YURL.ADD_ADDRESS, map, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)){
                            Y.t("上传成功");
                            Y.ADDRESS.setName(map.get("name"));
                            Y.ADDRESS.setPhone(phone);
                            Y.ADDRESS.setAddress(dizhi);
                            finish();
                        }else {
                            Y.t("上传失败");
                        }
                    }
                });
                break;
            case R.id.ll_xiangxidizhi:
                    Intent intent1 = new Intent(this, Activity_DiZhiMap.class);
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
        if (requestCode == 0 && resultCode == 1) {
            Bundle bundle = data.getExtras();
            dizhi = bundle.getString("dizhi");
            etXiangxidizhi.setText(dizhi);
            Y.i(Y.ADDRESS.getAddress());
        }
    }
}
