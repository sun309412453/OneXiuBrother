package com.zykj.onexiubrother.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zykj.onexiubrother.R;
import com.zykj.onexiubrother.bean.WeiWanChengBean;
import com.zykj.onexiubrother.utils.YURL;
import com.zykj.onexiubrother.widget.MyTitleBar;

import org.xutils.x;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/14.
 */

public class Activity_YiWanCheng_XiangQing extends Activity {
    @Bind(R.id.title)
    MyTitleBar title;
    @Bind(R.id.yiwancheng_zhonglei)
    TextView yiwanchengZhonglei;
    @Bind(R.id.yiwancheng_date)
    TextView yiwanchengDate;
    @Bind(R.id.yiwancheng_add)
    TextView yiwanchengAdd;
    @Bind(R.id.yiwancheng_img)
    ImageView yiwanchengImg;
    @Bind(R.id.yiwancheng_pinpai)
    TextView yiwanchengPinpai;
    @Bind(R.id.yiwancheng_xinghao)
    TextView yiwanchengXinghao;
    @Bind(R.id.yiwancheng_guzhang)
    TextView yiwanchengGuzhang;
    @Bind(R.id.yiwancheng_guzhangxiangqing)
    TextView yiwanchengGuzhangxiangqing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yiwancheng_xiangqing);
        ButterKnife.bind(this);
        title.setLeftClick(new MyTitleBar.leftClick() {
            @Override
            public void Click(View view) {
                finish();
            }
        });
        Intent intentYiWanCheng = getIntent();
        if (intentYiWanCheng != null) {
            WeiWanChengBean yiwancheng = (WeiWanChengBean) intentYiWanCheng.getSerializableExtra("zhuangtai");
            //设置数据到控件上
            //判断接口种类
            if (yiwancheng.getOrder_type()==1){
                yiwanchengZhonglei.setText("手机");
            }else if (yiwancheng.getOrder_type()==2){
                yiwanchengZhonglei.setText("电脑");
            }else if (yiwancheng.getOrder_type()==3){
                yiwanchengZhonglei.setText("家电");
            }
            //设置时间
            yiwanchengDate.setText(yiwancheng.getAddtime());
            //设置地址
            yiwanchengAdd.setText(yiwancheng.getService_address());
            //设置型号
            yiwanchengXinghao.setText(yiwancheng.getModel());
            //设置品牌
            yiwanchengPinpai.setText(yiwancheng.getBrand());
            //设置故障
            yiwanchengGuzhang.setText(yiwancheng.getFault());
            //设置故障信息
            yiwanchengGuzhangxiangqing.setText(yiwancheng.getFault_desc());
            //设置照片
            String image1 = yiwancheng.getImage1();
            x.image().bind(yiwanchengImg, YURL.HOST+image1);
        }
    }
}
