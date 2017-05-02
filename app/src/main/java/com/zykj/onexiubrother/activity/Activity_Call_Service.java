package com.zykj.onexiubrother.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.hss01248.dialog.StyledDialog;
import com.zykj.onexiubrother.R;
import com.zykj.onexiubrother.bean.AddressBean;
import com.zykj.onexiubrother.utils.Y;
import com.zykj.onexiubrother.utils.YURL;
import com.zykj.onexiubrother.widget.MyTitleBar;

import org.xutils.http.RequestParams;

import java.io.File;
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
    private String shijian;
    private String dizhi1;

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

    @OnClick({R.id.dizhi, R.id.hujiaofuwu_shijian, R.id.hujiaofuwu_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dizhi:
                Intent diZhiIntent = new Intent(this, Activity_Address.class);
                startActivityForResult(diZhiIntent, 1);
                break;
            //用时间选择器获取时间
            case R.id.hujiaofuwu_shijian:
                TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss");//利用占位符来格式化时间
                        hujiaofuwuTvShijian.setText(format.format(date).toString());
                    }
                }).setType(TimePickerView.Type.ALL)//默认全部显示
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
            case R.id.hujiaofuwu_ok:
                shijian = hujiaofuwuTvShijian.getText().toString().trim();
                dizhi1 = hujiaofuwuTvDizhi.getText().toString().trim();
                //手机上传位置
                if ("1".equals(getIntent().getStringExtra("order_type"))){
                    RequestParams params = new RequestParams(YURL.ADD_ORDER);
                    params.addBodyParameter("order_type",getIntent().getStringExtra("order_type"));
                    params.addBodyParameter("brand",getIntent().getStringExtra("pinpai"));
                    params.addBodyParameter("model",getIntent().getStringExtra("xinghao"));
                    params.addBodyParameter("fault",getIntent().getStringExtra("huzhang"));
                    params.addBodyParameter("fault_desc",getIntent().getStringExtra("miaoshu"));
                    params.addBodyParameter("category","");
                    params.addBodyParameter("image1",new File(getIntent().getStringExtra("imgpath")));
                    params.addBodyParameter("service_time",shijian);
                    params.addBodyParameter("service_address",dizhi1);
                    params.addBodyParameter("custom_phone",addbean.getPhone());
                    params.addBodyParameter("custom_name",addbean.getName());
                    params.addBodyParameter("custom_id",Y.USER.getUser_id()+"");
                    params.addBodyParameter("address_id",addbean.getAddress_id()+"");
                    Y.post(params, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
                            //关闭对话框
                            StyledDialog.dismissLoading();
                            if (Y.getRespCode(result)){
                                Y.t("手机下单成功");
                                Intent intent1 = new Intent(Activity_Call_Service.this,MainActivity.class);
                                startActivity(intent1);
                            }else {
                                Y.t("手机下单失败");
                            }
                        }
                    });
                }else if ("2".equals(getIntent().getStringExtra("order_type"))){
                    //电脑上传位置
                    RequestParams params = new RequestParams(YURL.ADD_ORDER);
                    params.addBodyParameter("order_type",getIntent().getStringExtra("order_type"));
                    params.addBodyParameter("brand",getIntent().getStringExtra("pinpai"));
                    params.addBodyParameter("model",getIntent().getStringExtra("xinghao"));
                    params.addBodyParameter("category",getIntent().getStringExtra("leixing"));
                    params.addBodyParameter("fault",getIntent().getStringExtra("guzhang"));
                    params.addBodyParameter("fault_desc",getIntent().getStringExtra("miaoshu"));
                    params.addBodyParameter("image1",new File(getIntent().getStringExtra("imgpath")));
                    params.addBodyParameter("service_time",shijian);
                    params.addBodyParameter("service_address",dizhi1);
                    params.addBodyParameter("custom_phone",addbean.getPhone());
                    params.addBodyParameter("custom_name",addbean.getName());
                    params.addBodyParameter("custom_id",Y.USER.getUser_id()+"");
                    params.addBodyParameter("address_id",addbean.getAddress_id()+"");
                    Y.post(params, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
                            //关闭对话框
                            StyledDialog.dismissLoading();
                            if (Y.getRespCode(result)){
                                Y.t("电脑下单成功");
                                Intent intent2 = new Intent(Activity_Call_Service.this,MainActivity.class);
                                startActivity(intent2);
                            }else {
                                Y.t("电脑下单失败");
                            }
                        }
                    });
            }else if ("3".equals(getIntent().getStringExtra("order_type"))){
                    //家电上传位置
                    //电脑上传位置
                    RequestParams params = new RequestParams(YURL.ADD_ORDER);
                    params.addBodyParameter("order_type",getIntent().getStringExtra("order_type"));
                    params.addBodyParameter("brand",getIntent().getStringExtra("pinpai"));
                    params.addBodyParameter("model",getIntent().getStringExtra("xinghao"));
                    params.addBodyParameter("category",getIntent().getStringExtra("leixing"));
                    params.addBodyParameter("fault",getIntent().getStringExtra("guzhang"));
                    params.addBodyParameter("fault_desc",getIntent().getStringExtra("miaoshu"));
                    params.addBodyParameter("image1",new File(getIntent().getStringExtra("imgpath")));
                    params.addBodyParameter("service_time",shijian);
                    params.addBodyParameter("service_address",dizhi1);
                    params.addBodyParameter("custom_phone",addbean.getPhone());
                    params.addBodyParameter("custom_name",addbean.getName());
                    params.addBodyParameter("custom_id",Y.USER.getUser_id()+"");
                    params.addBodyParameter("address_id",addbean.getAddress_id()+"");
                    Y.post(params, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
                            //关闭对话框
                            StyledDialog.dismissLoading();
                            if (Y.getRespCode(result)){
                                Y.t("家电下单成功");
                                Intent intent = new Intent(Activity_Call_Service.this,MainActivity.class);
                                startActivity(intent);
                            }else {
                                Y.t("家电下单失败");
                            }
                        }
                    });
                }
//                order_type: 订单类型,1手机,2电脑,3家电
//                brand: 品牌
//                model:型号
//                fault:故障点
//                fault_desc:故障描述
//                category:类别 例如电脑(一体机,笔记本,台式机) 手机此参数为空
//                image1:图片一  必选 必须有一张图片
//                image2:图片二  可选
//                image3:图片一   可选
//                service_time:上门服务时间
//                service_address:服务地址
//                custom_phone:客户电话
//                custom_name:客户姓名
//                custom_id:客户ID
//                address_id:客户关联的地址ID

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 2) {
            addbean = (AddressBean) data.getSerializableExtra("addbean");
            hujiaofuwuTvDizhi.setText(addbean.getAddress());
        }
    }
}
