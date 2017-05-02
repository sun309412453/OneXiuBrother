package com.zykj.onexiubrother.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.zykj.onexiubrother.R;
import com.zykj.onexiubrother.bean.AddressBean;
import com.zykj.onexiubrother.widget.MyTitleBar;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/13.
 */

public class Activity_Call_Service extends Activity {
    @Bind(R.id.title)
    MyTitleBar title;
    @Bind(R.id.dizhi)
    LinearLayout dizhi;
    @Bind(R.id.hujiaofuwu_tv_shijian)
    TextView hujiaofuwuTvShijian;
    @Bind(R.id.hujiaofuwu_shijian)
    LinearLayout hujiaofuwuShijian;
    @Bind(R.id.hujiaofuwu_tv_dizhi)
    TextView hujiaofuwuTvDizhi;
    private AddressBean addbean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_service);
        ButterKnife.bind(this);
        title.setLeftClick(new MyTitleBar.leftClick() {
            @Override
            public void Click(View view) {
                finish();
            }
        });

    }

    @OnClick({R.id.dizhi,R.id.hujiaofuwu_shijian})
    public void onViewClicked(View view) {
            switch (view.getId()){
                case R.id.dizhi:
                Intent diZhiIntent = new Intent(this, Activity_Address.class);
                startActivityForResult(diZhiIntent,1);
                break;
                //用时间选择器获取时间
                case R.id.hujiaofuwu_shijian:
                    TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date, View v) {//选中事件回调
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss");//利用占位符来格式化时间
                            hujiaofuwuTvShijian.setText(format.format(date).toString());
                        }
                    })    .setType(TimePickerView.Type.ALL)//默认全部显示
                            .setCancelText("取消")//取消按钮文字
                            .setSubmitText("确定")//确认按钮文字
                            .setContentSize(18)//滚轮文字大小
                            .setTitleSize(20)//标题文字大小
                            .setTitleText("时间选择")//标题文字
                            .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                            .isCyclic(true)//是否循环滚动
                            .setTitleColor(Color.parseColor("#00cccc"))//标题文字颜色
                            .setSubmitColor(Color.parseColor("#00cccc"))//确定按钮文字颜色
                            .setCancelColor(Color.parseColor("#00cccc"))//取消按钮文字颜色
                            .setTitleBgColor(0xFFffffff)//标题背景颜色 Night mode
                            .setBgColor(0xFFffffff)//滚轮背景颜色 Night mode
                            .build();
                    pvTime.setDate(Calendar.getInstance());
                    pvTime.show();

                break;
            }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1&&resultCode==2){
            addbean = (AddressBean) data.getSerializableExtra("addbean");
            hujiaofuwuTvDizhi.setText(addbean.getAddress());
        }
    }
}
