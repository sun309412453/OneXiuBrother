package com.zykj.onexiubrother.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zykj.onexiubrother.R;
import com.zykj.onexiubrother.adapter.Adapter_DiZhiGuanLi;
import com.zykj.onexiubrother.bean.DiZhiGuanLiBean;
import com.zykj.onexiubrother.widget.MyTitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zykj on 2017/4/15.
 */

public class Activity_DiZhiGuanLi extends Activity {
    @Bind(R.id.title)
    MyTitleBar title;
    @Bind(R.id.dizhiguanlirv)
    RecyclerView dizhiguanlirv;
    List<DiZhiGuanLiBean> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dizhiguanli);
        ButterKnife.bind(this);
        list=new ArrayList<DiZhiGuanLiBean>();
        title.setLeftClick(new MyTitleBar.leftClick() {
            @Override
            public void Click(View view) {
                finish();
            }
        });
        DiZhiGuanLiBean bean = new DiZhiGuanLiBean();
        bean.setName("孙书博");
        bean.setAdd("哈尔滨卓亚科技有限公司，黑龙江省银行街2号");
        bean.setPhone("15004666284");
        bean.setZhuangtai("默认");
        list.add(bean);
        Adapter_DiZhiGuanLi guanLi = new Adapter_DiZhiGuanLi(list,this);
        dizhiguanlirv.setItemAnimator(new DefaultItemAnimator());
        dizhiguanlirv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        dizhiguanlirv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        dizhiguanlirv.setAdapter(guanLi);

    }
}
