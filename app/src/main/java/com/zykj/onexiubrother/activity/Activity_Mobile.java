package com.zykj.onexiubrother.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.OptionsPickerView;
import com.zykj.onexiubrother.R;
import com.zykj.onexiubrother.bean.MobileBean;
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


    List<MobileBean> lists; //品牌的数据源
    int mobileIndex = -1;  //用于检测是否选择了品牌
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
                //发起请求
                Y.get(new RequestParams(YURL.FIND_PHONE_BRAND), new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        if (Y.getRespCode(result)) {
                            //成功
                            lists = JSON.parseArray(Y.getData(result), MobileBean.class);
                            //创建选择器
                            OptionsPickerView opv = new OptionsPickerView.Builder(Activity_Mobile.this, new OptionsPickerView.OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                    //选择后的监听器
                                    mobileTvBrand.setText(lists.get(options1).getName());
                                    mobileIndex = options1; // 当前选择的索引
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
                            opv.show();
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
                    RequestParams rp = new RequestParams(YURL.FIND_PHONE_MODEL);
                    rp.addBodyParameter("pid", lists.get(mobileIndex).getId() + "");
                    Y.get(rp, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
                            if (Y.getRespCode(result)) {
                                //成功
                                lists = JSON.parseArray(Y.getData(result), MobileBean.class);
                                //创建选择器
                                OptionsPickerView opv = new OptionsPickerView.Builder(Activity_Mobile.this, new OptionsPickerView.OnOptionsSelectListener() {
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
                                opv.setPicker(strs, null, null);
                                //显示选择器
                                opv.show();
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
                Y.get(new RequestParams(YURL.FIND_PHONE_FAULT), new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        if (Y.getRespCode(result)) {
                            //成功
                            lists = JSON.parseArray(Y.getData(result), MobileBean.class);
                            //创建选择器
                            OptionsPickerView opv = new OptionsPickerView.Builder(Activity_Mobile.this, new OptionsPickerView.OnOptionsSelectListener() {
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
                            opv.setPicker(strs, null, null);
                            //显示选择器
                            opv.show();
                        } else {
                            //失败
                            Y.t("数据解析失败");
                        }
                    }
                });
                break;
        }
    }
}