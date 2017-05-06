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

import com.alibaba.fastjson.JSON;
import com.hss01248.dialog.StyledDialog;
import com.zykj.onexiubrother.R;
import com.zykj.onexiubrother.adapter.Adapter_WeiWanCheng;
import com.zykj.onexiubrother.bean.WeiWanChengBean;
import com.zykj.onexiubrother.utils.Y;
import com.zykj.onexiubrother.utils.YURL;
import com.zykj.onexiubrother.widget.MyTitleBar;

import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/14.
 */

public class Activity_YiQuXiao extends Activity {
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
        yiwanchengLine.setVisibility(View.INVISIBLE);
        list = new ArrayList<WeiWanChengBean>();
        RequestParams params = new RequestParams(YURL.FIND_ORDER_BY_STATE);
        params.addBodyParameter("custom_id", Y.USER.getUser_id()+"");
        params.addBodyParameter("order_state",3+"");
        Y.post(params, new Y.MyCommonCall<String>() {
            @Override
            public void onSuccess(String result) {
                StyledDialog.dismissLoading();
                if (Y.getRespCode(result)){
                    Y.i(Y.getData(result));
                    list = JSON.parseArray(Y.getData(result), WeiWanChengBean.class);
                    Adapter_WeiWanCheng yiquxiao = new Adapter_WeiWanCheng(list,Activity_YiQuXiao.this,1);
                    yiquxiao.setDianJi(new Adapter_WeiWanCheng.DianJi() {
                        @Override
                        public void OnClick(View v, int pos) {
                            WeiWanChengBean weiWanChengBean = list.get(pos);
                            Intent intentQuXiao = new Intent(Activity_YiQuXiao.this,Activity_YiQuXiao_XiangQing.class);
                            intentQuXiao.putExtra("yiquxiao",weiWanChengBean);
                            startActivity(intentQuXiao);
                        }
                    });
                    dingdanrv.setItemAnimator(new DefaultItemAnimator());
                    dingdanrv.setLayoutManager(new LinearLayoutManager(Activity_YiQuXiao.this,LinearLayoutManager.VERTICAL,false));
                    dingdanrv.addItemDecoration(new DividerItemDecoration(Activity_YiQuXiao.this,DividerItemDecoration.VERTICAL));
                    dingdanrv.setAdapter(yiquxiao);
                }
            }
        });
    }
    @OnClick({R.id.btweiwancheng, R.id.btyiwancheng, R.id.btyiquxiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btweiwancheng:
                Intent intentwei = new Intent(this,Activity_WeiWanCheng.class);
                startActivity(intentwei);
                break;
            case R.id.btyiwancheng:
                Intent intentyi = new Intent(this,Activity_YiWanCheng.class);
                startActivity(intentyi);
                break;
            case R.id.btyiquxiao:
                Intent intent = new Intent(this,Activity_YiQuXiao.class);
                startActivity(intent);
                break;
        }
    }
}
