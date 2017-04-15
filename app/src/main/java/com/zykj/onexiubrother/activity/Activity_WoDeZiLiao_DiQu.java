package com.zykj.onexiubrother.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zykj.onexiubrother.R;
import com.zykj.onexiubrother.adapter.Adapter_DiQu;
import com.zykj.onexiubrother.bean.DiQuBean;
import com.zykj.onexiubrother.widget.MyTitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zykj on 2017/4/15.
 */

public class Activity_WoDeZiLiao_DiQu extends Activity {
    @Bind(R.id.title)
    MyTitleBar title;
    @Bind(R.id.diqu_rv)
    RecyclerView diquRv;
    private String []diqus ={"哈尔滨","吉林","三亚","青岛","北京","天津","日本","非洲","广州","东京"};
    private List<DiQuBean> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wodeziliao_diqu);
        ButterKnife.bind(this);
        list = new ArrayList<DiQuBean>();
        title.setLeftClick(new MyTitleBar.leftClick() {
            @Override
            public void Click(View view) {
                finish();
            }
        });
        for (int i = 0; i <diqus.length ; i++) {
            DiQuBean diQuBean = new DiQuBean();
            diQuBean.setDiQu(diqus[i]);
            list.add(diQuBean);
        }
        Adapter_DiQu adapter_diQu = new Adapter_DiQu(this,list);
        diquRv.setItemAnimator(new DefaultItemAnimator());
        diquRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        diquRv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        diquRv.setAdapter(adapter_diQu);
    }
}
