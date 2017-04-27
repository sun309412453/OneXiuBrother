package com.zykj.onexiubrother.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.hss01248.dialog.StyledDialog;
import com.zykj.onexiubrother.R;
import com.zykj.onexiubrother.adapter.Adapter_DiZhiGuanLi;
import com.zykj.onexiubrother.bean.AddressBean;
import com.zykj.onexiubrother.bean.DiZhiGuanLiBean;
import com.zykj.onexiubrother.utils.Y;
import com.zykj.onexiubrother.utils.YURL;
import com.zykj.onexiubrother.widget.MyTitleBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/13.
 */

public class Activity_Address extends Activity {
    @Bind(R.id.title)
    MyTitleBar title;
    @Bind(R.id.newadd)
    LinearLayout newadd;
    @Bind(R.id.addrv)
    RecyclerView addrv;
    private List<AddressBean> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dizhi);
        ButterKnife.bind(this);
        title.setLeftClick(new MyTitleBar.leftClick() {
            @Override
            public void Click(View view) {
                finish();
            }
        });
        list = new ArrayList<AddressBean>();
        title.setLeftClick(new MyTitleBar.leftClick() {
            @Override
            public void Click(View view) {
                finish();
            }
        });

    }

    @OnClick(R.id.newadd)
    public void onViewClicked() {
        Intent addIntent = new Intent(this, Activity_EditAdd.class);
        startActivity(addIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Map<String,String> map = new HashMap<String, String>();
        map.put("user_id", Y.USER.getUser_id()+"");
        Y.get(YURL.SELECT_ADDRESS, map, new Y.MyCommonCall<String>() {
            @Override
            public void onSuccess(String result) {
                StyledDialog.dismissLoading();
                if (Y.getRespCode(result)){
                    list = JSON.parseArray(Y.getData(result), AddressBean.class);
                    Adapter_DiZhiGuanLi guanLi = new Adapter_DiZhiGuanLi(list, Activity_Address.this);

                    addrv.setItemAnimator(new DefaultItemAnimator());
                    addrv.setLayoutManager(new LinearLayoutManager(Activity_Address.this, LinearLayoutManager.VERTICAL, false));
                    addrv.addItemDecoration(new DividerItemDecoration(Activity_Address.this, DividerItemDecoration.VERTICAL));
                    addrv.setAdapter(guanLi);
                }
            }
        });

    }
}
