package com.zykj.onexiubrother.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zykj.onexiubrother.R;
import com.zykj.onexiubrother.bean.WeiWanChengBean;
import com.zykj.onexiubrother.utils.YURL;
import com.zykj.onexiubrother.widget.MyTitleBar;

import org.xutils.x;

import java.io.Serializable;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/14.
 */

public class Activity_WeiWanCheng_XiangQing extends Activity {
    @Bind(R.id.title)
    MyTitleBar title;
    @Bind(R.id.weiwancheng_zhuangtai1)
    ImageView weiwanchengZhuangtai1;
    @Bind(R.id.weiwancheng_zhuangtai2)
    LinearLayout weiwanchengZhuangtai2;
    @Bind(R.id.weiwancheng_zhuangtai3)
    LinearLayout weiwanchengZhuangtai3;
    @Bind(R.id.weiwancheng_zhonglei)
    TextView weiwanchengZhonglei;
    @Bind(R.id.weiwancheng_date)
    TextView weiwanchengDate;
    @Bind(R.id.weiwancheng_add)
    TextView weiwanchengAdd;
    @Bind(R.id.weiwancheng_img)
    ImageView weiwanchengImg;
    @Bind(R.id.weiwancheng_pinpai)
    TextView weiwanchengPinpai;
    @Bind(R.id.weiwancheng_xinghao)
    TextView weiwanchengXinghao;
    @Bind(R.id.weiwancheng_guzhang)
    TextView weiwanchengGuzhang;
    @Bind(R.id.weiwancheng_guzhangxinxi)
    TextView weiwanchengGuzhangxinxi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weiwancheng_xiangqing);
        ButterKnife.bind(this);
        title.setLeftClick(new MyTitleBar.leftClick() {
            @Override
            public void Click(View view) {
                finish();
            }
        });
        Intent intent = getIntent();
        if (intent!=null){
            WeiWanChengBean weiwancheng = (WeiWanChengBean) intent.getSerializableExtra("zhuangtai");
            //判断接口种类
            if (weiwancheng.getOrder_type()==1){
                weiwanchengZhonglei.setText("手机");
            }else if (weiwancheng.getOrder_type()==2){
                weiwanchengZhonglei.setText("电脑");
            }else if (weiwancheng.getOrder_type()==3){
                weiwanchengZhonglei.setText("家电");
            }
            //设置时间
            weiwanchengDate.setText(weiwancheng.getAddtime());
            //设置地址
            weiwanchengAdd.setText(weiwancheng.getService_address());
            //设置型号
            weiwanchengXinghao.setText(weiwancheng.getModel());
            //设置品牌
            weiwanchengPinpai.setText(weiwancheng.getBrand());
            //设置故障
            weiwanchengGuzhang.setText(weiwancheng.getFault());
            //设置故障信息
            weiwanchengGuzhangxinxi.setText(weiwancheng.getFault_desc());
            //设置照片
            String image1 = weiwancheng.getImage1();
            x.image().bind(weiwanchengImg, YURL.HOST+image1);
        }
    }
}
