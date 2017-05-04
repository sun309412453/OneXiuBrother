package com.zykj.onexiubrother.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zykj.onexiubrother.R;
import com.zykj.onexiubrother.adapter.Adapter_WeiWanCheng;
import com.zykj.onexiubrother.bean.WeiWanChengBean;
import com.zykj.onexiubrother.widget.MyTitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/14.
 */

public class Activity_YiWanCheng extends Activity {
    @Bind(R.id.title)
    MyTitleBar title;
    @Bind(R.id.btweiwancheng)
    TextView btweiwancheng;
    @Bind(R.id.btyiwancheng)
    TextView btyiwancheng;
    @Bind(R.id.btyiquxiao)
    TextView btyiquxiao;
    @Bind(R.id.weiwancheng_line)
    ImageView weiwanchengLine;
    @Bind(R.id.yiwancheng_line)
    ImageView yiwanchengLine;
    @Bind(R.id.yiquxiao_line)
    ImageView yiquxiaoLine;
    @Bind(R.id.dingdanrv)
    RecyclerView dingdanrv;
    private List<WeiWanChengBean> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuangtai);
        ButterKnife.bind(this);
        weiwanchengLine.setVisibility(View.INVISIBLE);
        yiquxiaoLine.setVisibility(View.INVISIBLE);
        list = new ArrayList<WeiWanChengBean>();
        WeiWanChengBean bean = new WeiWanChengBean();
//        bean.setAdd("哈尔滨卓亚科技有限公司");
//        bean.setDate("2017-04-14 10:00");
//        bean.setZhonglei("手机");
        list.add(bean);
        Adapter_WeiWanCheng weiWanCheng = new Adapter_WeiWanCheng(list,this);
        weiWanCheng.setDianJi(new Adapter_WeiWanCheng.DianJi() {
            @Override
            public void OnClick(View v) {
                Intent xiangQingIntent = new Intent(Activity_YiWanCheng.this,Activity_YiWanCheng_XiangQing.class);
                startActivity(xiangQingIntent);
            }
        });
        dingdanrv.setItemAnimator(new DefaultItemAnimator());
        dingdanrv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        dingdanrv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        dingdanrv.setAdapter(weiWanCheng);
    }
}
