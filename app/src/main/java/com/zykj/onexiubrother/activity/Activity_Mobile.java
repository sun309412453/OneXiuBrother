package com.zykj.onexiubrother.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.OptionsPickerView;
import com.hss01248.dialog.StyledDialog;
import com.zykj.onexiubrother.R;
import com.zykj.onexiubrother.bean.MobileBean;
import com.zykj.onexiubrother.utils.Y;
import com.zykj.onexiubrother.utils.YURL;
import com.zykj.onexiubrother.widget.MyTitleBar;

import org.xutils.http.RequestParams;

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

public class Activity_Mobile extends Activity {
    @Bind(R.id.title)
    MyTitleBar title;
    @Bind(R.id.mobile_ok)
    Button mobileOk;
    @Bind(R.id.mobile_tv_brand)
    TextView mobileTvBrand;
    @Bind(R.id.mobile_tv_model)
    TextView mobileTvModel;
    @Bind(R.id.mobile_tv_fault)
    TextView mobileTvFault;
    private OptionsPickerView opv, opv1, opv2;
    private List<MobileBean> lists; //品牌的数据源
    private int mobileIndex = -1;  //用于检测是否选择了品牌
    @Bind(R.id.mobileTvBrand_ll)
    LinearLayout mobileTvBrandLl;
    @Bind(R.id.mobileTvModel_ll)
    LinearLayout mobileTvModelLl;
    @Bind(R.id.mobileTvFault_ll)
    LinearLayout mobileTvFaultLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_mobile);
        ButterKnife.bind(this);
        title.setLeftClick(new MyTitleBar.leftClick() {
            @Override
            public void Click(View view) {
                finish();
            }
        });
    }

    @OnClick({R.id.mobileTvBrand_ll, R.id.mobileTvModel_ll, R.id.mobileTvFault_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mobileTvBrand_ll: //选择品牌
                StyledDialog.buildLoading().show();
                //发起请求
                Y.get(YURL.FIND_PHONE_BRAND,null, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {
                            //成功
                            lists = JSON.parseArray(Y.getData(result), MobileBean.class);
                            //创建选择器
                            if (opv == null)
                                opv = new OptionsPickerView.Builder(Activity_Mobile.this, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                        //选择后的监听器
                                        mobileTvBrand.setText(lists.get(options1).getName());
                                        if (mobileIndex != options1) {
                                            mobileTvModel.setHint("请选择您的手机型号");
                                            mobileTvModel.setHintTextColor(Color.parseColor("#c9c9c9"));
                                            mobileTvModel.setText("");
                                            mobileIndex = options1;
                                        }
                                    }
                                }).build();
                            //把lists 进行转换
                            List<String> strs = new ArrayList<String>();
                            for (MobileBean mb : lists) {
                                strs.add(mb.getName());
                            }
                            //添加数据
                            opv.setPicker(strs, null, null);
                            //显示选择器
                            if (!opv.isShowing()) {
                                opv.show();
                            }

                        } else {
                            //失败
                            Y.t("数据解析失败");
                        }
                    }
                });

                break;
            case R.id.mobileTvModel_ll: //选择型号
                //检测是否选择了品牌
                if (mobileIndex == -1) {
                    Y.t("请您先选择品牌");
                } else {
                    //开始获取型号数据
                    //发起请求
                    Map<String,String> map = new HashMap<String, String>();
                    map.put("pid", lists.get(mobileIndex).getId() + "");
                    Y.get(YURL.FIND_PHONE_MODEL,map, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
                            StyledDialog.dismissLoading();
                            if (Y.getRespCode(result)) {
                                //成功
                                lists = JSON.parseArray(Y.getData(result), MobileBean.class);
                                //创建选择器
                                if (opv1 == null)
                                    opv1 = new OptionsPickerView.Builder(Activity_Mobile.this, new OptionsPickerView.OnOptionsSelectListener() {
                                        @Override
                                        public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                            //选择后的监听器
                                            mobileTvModel.setText(lists.get(options1).getName());
                                        }
                                    }).build();
                                //把lists 进行转换
                                List<String> strs = new ArrayList<String>();
                                for (MobileBean mb : lists) {
                                    strs.add(mb.getName());
                                }
                                //添加数据
                                opv1.setPicker(strs, null, null);
                                //显示选择器
                                if (!opv1.isShowing()) {
                                    opv1.show();
                                }
                            } else {
                                //失败
                                Y.t("数据解析失败");
                            }
                        }
                    });
                }
                break;
            case R.id.mobileTvFault_ll: //选择故障
                //发起请求
                Y.get(YURL.FIND_PHONE_FAULT,null, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {
                            //成功
                            lists = JSON.parseArray(Y.getData(result), MobileBean.class);
                            //创建选择器
                            if (opv2 == null)
                                opv2 = new OptionsPickerView.Builder(Activity_Mobile.this, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                        //选择后的监听器
                                        mobileTvFault.setText(lists.get(options1).getName());
                                    }
                                }).build();
                            //把lists 进行转换
                            List<String> strs = new ArrayList<String>();
                            for (MobileBean mb : lists) {
                                strs.add(mb.getName());
                            }
                            //添加数据
                            opv2.setPicker(strs, null, null);
                            //显示选择器
                            if (!opv2.isShowing()) {
                                opv2.show();
                            }
                        } else {
                            //失败
                            Y.t("数据解析失败");
                        }
                    }
                });
                break;
            case R.id.mobile_ok:
                Intent mobileOkIntent = new Intent(this, Activity_Call_Service.class);
                startActivity(mobileOkIntent);
                break;
        }
    }
}