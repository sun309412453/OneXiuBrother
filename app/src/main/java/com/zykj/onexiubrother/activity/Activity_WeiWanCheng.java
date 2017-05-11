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

public class Activity_WeiWanCheng extends Activity {
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
    private RequestParams params;
    private Adapter_WeiWanCheng weiWanCheng;
    private String zhuangtai;
    private int index;
    private Intent xiangQingIntent;
    private WeiWanChengBean weiWanChengBean;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuangtai);
        ButterKnife.bind(this);
        list = new ArrayList<WeiWanChengBean>();
        Intent intent = getIntent();
        if (intent!=null){
            zhuangtai = intent.getStringExtra("zhuangtai");
            index = intent.getIntExtra("index", 0);
        }
        params = new RequestParams(YURL.FIND_ORDER_BY_STATE);
        params.addBodyParameter("custom_id",Y.USER.getUser_id()+"");
        params.addBodyParameter("order_state",zhuangtai);
        Y.post(params, new Y.MyCommonCall<String>() {
            @Override
            public void onSuccess(String result) {
                StyledDialog.dismissLoading();
                if (Y.getRespCode(result)){
                    Y.i(Y.getData(result));
                    list = JSON.parseArray(Y.getData(result), WeiWanChengBean.class);
                    weiWanCheng = new Adapter_WeiWanCheng(list,Activity_WeiWanCheng.this,index);
                    weiWanCheng.setDianJi(new Adapter_WeiWanCheng.DianJi() {
                        @Override
                        public void OnClick(View v,int pos) {
                            weiWanChengBean = list.get(pos);

                            if ("1".equals(zhuangtai)){
                                xiangQingIntent = new Intent(Activity_WeiWanCheng.this,Activity_WeiWanCheng_XiangQing.class);
                                xiangQingIntent.putExtra("zhuangtai", weiWanChengBean);
                            }else if ("2".equals(zhuangtai)){
                                xiangQingIntent = new Intent(Activity_WeiWanCheng.this,Activity_YiWanCheng_XiangQing.class);
                                xiangQingIntent.putExtra("zhuangtai", weiWanChengBean);
                            }else if ("3".equals(zhuangtai)){
                                xiangQingIntent = new Intent(Activity_WeiWanCheng.this,Activity_YiQuXiao_XiangQing.class);
                                xiangQingIntent.putExtra("zhuangtai", weiWanChengBean);
                            }
                            startActivity(xiangQingIntent);
                        }
                    });
                    dingdanrv.setItemAnimator(new DefaultItemAnimator());
                    dingdanrv.setLayoutManager(new LinearLayoutManager(Activity_WeiWanCheng.this,LinearLayoutManager.VERTICAL,false));
                    dingdanrv.addItemDecoration(new DividerItemDecoration(Activity_WeiWanCheng.this,DividerItemDecoration.VERTICAL));
                    dingdanrv.setAdapter(weiWanCheng);
                }else {
                    Y.t("失败");
                }
            }
        });
        if ("1".equals(zhuangtai)){
            yiquxiaoLine.setVisibility(View.INVISIBLE);
            yiwanchengLine.setVisibility(View.INVISIBLE);
            weiwanchengLine.setVisibility(View.VISIBLE);

        }else if ("2".equals(zhuangtai)){
            weiwanchengLine.setVisibility(View.INVISIBLE);
            yiquxiaoLine.setVisibility(View.INVISIBLE);
            yiwanchengLine.setVisibility(View.VISIBLE);
        }else if ("3".equals(zhuangtai)){
            weiwanchengLine.setVisibility(View.INVISIBLE);
            yiwanchengLine.setVisibility(View.INVISIBLE);
            yiquxiaoLine.setVisibility(View.VISIBLE);
        }

    }

    @OnClick({R.id.btweiwancheng, R.id.btyiwancheng, R.id.btyiquxiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btweiwancheng:
                yiquxiaoLine.setVisibility(View.INVISIBLE);
                yiwanchengLine.setVisibility(View.INVISIBLE);
                weiwanchengLine.setVisibility(View.VISIBLE);
                params = new RequestParams(YURL.FIND_ORDER_BY_STATE);
                params.addBodyParameter("custom_id", Y.USER.getUser_id() + "");
                params.addBodyParameter("order_state","1");
                Y.post(params, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {
                            Y.i(Y.getData(result));
                            list = JSON.parseArray(Y.getData(result), WeiWanChengBean.class);
                            Adapter_WeiWanCheng weiWanCheng = new Adapter_WeiWanCheng(list, Activity_WeiWanCheng.this, 0);

                            weiWanCheng.setDianJi(new Adapter_WeiWanCheng.DianJi() {
                                @Override
                                public void OnClick(View v, int pos) {
                                    WeiWanChengBean weiWanChengBean = list.get(pos);
                                    Intent xiangQingIntent = new Intent(Activity_WeiWanCheng.this, Activity_WeiWanCheng_XiangQing.class);
                                    xiangQingIntent.putExtra("zhuangtai", weiWanChengBean);
                                    startActivity(xiangQingIntent);
                                    Y.t(weiWanChengBean.getId() + "");
                                }
                            });
                            dingdanrv.setItemAnimator(new DefaultItemAnimator());
                            dingdanrv.setLayoutManager(new LinearLayoutManager(Activity_WeiWanCheng.this, LinearLayoutManager.VERTICAL, false));
                            dingdanrv.addItemDecoration(new DividerItemDecoration(Activity_WeiWanCheng.this, DividerItemDecoration.VERTICAL));
                            dingdanrv.setAdapter(weiWanCheng);
                        }
                    }
                });
                break;
            case R.id.btyiwancheng:
                weiwanchengLine.setVisibility(View.INVISIBLE);
                yiquxiaoLine.setVisibility(View.INVISIBLE);
                yiwanchengLine.setVisibility(View.VISIBLE);
                RequestParams params = new RequestParams(YURL.FIND_ORDER_BY_STATE);
                params.addBodyParameter("custom_id",Y.USER.getUser_id()+"");
                params.addBodyParameter("order_state","2");
                Y.post(params, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)){
                            Y.i(Y.getData(result));
                            list = JSON.parseArray(Y.getData(result), WeiWanChengBean.class);
                            Adapter_WeiWanCheng weiWanCheng = new Adapter_WeiWanCheng(list,Activity_WeiWanCheng.this,1);
                            weiWanCheng.setDianJi(new Adapter_WeiWanCheng.DianJi() {
                                @Override
                                public void OnClick(View v,int pos) {
                                    WeiWanChengBean weiWanChengBean = list.get(pos);
                                    Intent xiangQingIntent = new Intent(Activity_WeiWanCheng.this,Activity_YiWanCheng_XiangQing.class);
                                    xiangQingIntent.putExtra("zhuangtai",weiWanChengBean);
                                    startActivity(xiangQingIntent);
                                }
                            });
                            dingdanrv.setItemAnimator(new DefaultItemAnimator());
                            dingdanrv.setLayoutManager(new LinearLayoutManager(Activity_WeiWanCheng.this,LinearLayoutManager.VERTICAL,false));
                            dingdanrv.addItemDecoration(new DividerItemDecoration(Activity_WeiWanCheng.this,DividerItemDecoration.VERTICAL));
                            dingdanrv.setAdapter(weiWanCheng);
                        }
                    }
                });

                break;
            case R.id.btyiquxiao:
                weiwanchengLine.setVisibility(View.INVISIBLE);
                yiwanchengLine.setVisibility(View.INVISIBLE);
                yiquxiaoLine.setVisibility(View.VISIBLE);
                RequestParams params1 = new RequestParams(YURL.FIND_ORDER_BY_STATE);
                params1.addBodyParameter("custom_id", Y.USER.getUser_id()+"");
                params1.addBodyParameter("order_state","3");
                Y.post(params1, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)){
                            Y.i(Y.getData(result));
                            list = JSON.parseArray(Y.getData(result), WeiWanChengBean.class);
                            Adapter_WeiWanCheng yiquxiao = new Adapter_WeiWanCheng(list,Activity_WeiWanCheng.this,1);
                            yiquxiao.setDianJi(new Adapter_WeiWanCheng.DianJi() {
                                @Override
                                public void OnClick(View v, int pos) {
                                    WeiWanChengBean weiWanChengBean = list.get(pos);
                                    Intent intentQuXiao = new Intent(Activity_WeiWanCheng.this,Activity_YiQuXiao_XiangQing.class);
                                    intentQuXiao.putExtra("zhuangtai",weiWanChengBean);
                                    startActivity(intentQuXiao);
                                }
                            });
                            dingdanrv.setItemAnimator(new DefaultItemAnimator());
                            dingdanrv.setLayoutManager(new LinearLayoutManager(Activity_WeiWanCheng.this,LinearLayoutManager.VERTICAL,false));
                            dingdanrv.addItemDecoration(new DividerItemDecoration(Activity_WeiWanCheng.this,DividerItemDecoration.VERTICAL));
                            dingdanrv.setAdapter(yiquxiao);
                        }
                    }
                });
                break;
        }
    }
}
