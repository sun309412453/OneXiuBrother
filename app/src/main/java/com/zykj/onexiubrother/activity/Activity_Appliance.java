package com.zykj.onexiubrother.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.OptionsPickerView;
import com.hss01248.dialog.StyledDialog;
import com.zykj.onexiubrother.R;
import com.zykj.onexiubrother.bean.ApplianceBean;
import com.zykj.onexiubrother.bean.ComputerBean;
import com.zykj.onexiubrother.utils.Y;
import com.zykj.onexiubrother.utils.YURL;
import com.zykj.onexiubrother.widget.MyTitleBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/12.
 */

public class Activity_Appliance extends Activity {
    @Bind(R.id.title)
    MyTitleBar title;
    @Bind(R.id.appliance_ok)
    Button applianceOk;
    @Bind(R.id.jiadian_tv_pinpai)
    TextView jiadianTvPinpai;
    @Bind(R.id.jiadian_ll_pinpai)
    LinearLayout jiadianLlPinpai;
    @Bind(R.id.jiadian_tv_leixing)
    TextView jiadianTvLeixing;
    @Bind(R.id.jiadian_ll_leixing)
    LinearLayout jiadianLlLeixing;
    @Bind(R.id.jiadian_tv_xinghao)
    TextView jiadianTvXinghao;
    @Bind(R.id.jiadian_ll_xinghao)
    LinearLayout jiadianLlXinghao;
    @Bind(R.id.jiadian_tv_guzhang)
    TextView jiadianTvGuzhang;
    @Bind(R.id.jiadian_ll_guzhang)
    LinearLayout jiadianLlGuzhang;
    private List<ApplianceBean> list; //品牌的数据源
    private List<ApplianceBean> lists; //类型的数据源
    private List<ApplianceBean> listss; //型号的数据源
    private List<ApplianceBean> listsss; //故障的数据源
    private int applianceIndexid = -1;  //用于检测是否选择了类型
    private int applianceIndexidd = -1;  //用于检测是否选择了型号
    private int applianceIndexiddd = -1;  //用于检测是否选择了故障
    private int applianceIndex = -1;  //用于检测是否选择了品牌
    private OptionsPickerView opv,opv1,opv2,opv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_appliance);
        ButterKnife.bind(this);
        title.setLeftClick(new MyTitleBar.leftClick() {
            @Override
            public void Click(View view) {
                finish();
            }
        });
    }
    @OnClick({R.id.appliance_ok,R.id.jiadian_ll_pinpai, R.id.jiadian_ll_leixing, R.id.jiadian_ll_xinghao, R.id.jiadian_ll_guzhang})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.jiadian_ll_pinpai:
                Y.get(YURL.FIND_BY_APPLIANCE_BRAND,null, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {
                            list = JSON.parseArray(Y.getData(result), ApplianceBean.class);
                            if (opv == null)
                                opv = new OptionsPickerView.Builder(Activity_Appliance.this, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                        //选择后的监听器
                                        jiadianTvPinpai.setText(list.get(options1).getName());
                                        jiadianTvPinpai.setTextColor(Color.parseColor("#00cccc"));
                                        jiadianTvPinpai.setGravity(Gravity.RIGHT|Gravity.CENTER_VERTICAL);
                                        if (applianceIndex != options1) {
                                            jiadianTvXinghao.setHint("请选择您的家电型号");
                                            jiadianTvXinghao.setHintTextColor(Color.parseColor("#c9c9c9"));
                                            jiadianTvXinghao.setText("");
                                            jiadianTvLeixing.setHint("请选择您的家电类型");
                                            jiadianTvLeixing.setHintTextColor(Color.parseColor("#c9c9c9"));
                                            jiadianTvLeixing.setText("");
                                            applianceIndex = options1;
                                        }
                                    }
                                }).build();
                            //把list 进行转换
                            List<String> strs = new ArrayList<String>();
                            for (ApplianceBean ab : list) {
                                strs.add(ab.getName());
                            }
                            //添加数据
                            opv.setPicker(strs, null, null);
                            //显示选择器
                            if (!opv.isShowing()) {
                                opv.show();
                            }
                        }
                    }
                });
                break;
            case R.id.jiadian_ll_leixing:
                if (applianceIndex==-1){
                    Y.t("请您先选择品牌");
                }else {
                    Map<String,String> map = new HashMap<>();
                    map.put("pid", list.get(applianceIndex).getId() + "");
                    Y.get(YURL.FIND_APPLIANCE_CATEGORY,map, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
                            StyledDialog.dismissLoading();
                            if (Y.getRespCode(result)) {
                                lists = JSON.parseArray(Y.getData(result), ApplianceBean.class);
                                if (opv1 == null)
                                    opv1 = new OptionsPickerView.Builder(Activity_Appliance.this, new OptionsPickerView.OnOptionsSelectListener() {
                                        @Override
                                        public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                            //选择后的监听器
                                            jiadianTvLeixing.setText(lists.get(options1).getName());
                                            jiadianTvLeixing.setTextColor(Color.parseColor("#00cccc"));
                                            jiadianTvLeixing.setGravity(Gravity.RIGHT|Gravity.CENTER_VERTICAL);
                                            if (applianceIndex != options1) {
                                                jiadianTvLeixing.setHint("请选择您的家电型号");
                                                jiadianTvLeixing.setHintTextColor(Color.parseColor("#c9c9c9"));
                                                jiadianTvLeixing.setText("");
                                            }
                                            applianceIndexid=options1;
                                        }
                                    }).build();
                                //把list 进行转换
                                List<String> strs = new ArrayList<String>();
                                for (ApplianceBean ab : lists) {
                                    strs.add(ab.getName());
                                }
                                //添加数据
                                opv1.setPicker(strs, null, null);
                                //显示选择器
                                if (!opv1.isShowing()) {
                                    opv1.show();
                                }
                            }
                        }
                    });
                }
                break;
            case R.id.jiadian_ll_xinghao:
                if (applianceIndex==-1){
                    Y.t("请您先选择类型");
                }else {
                    Map<String,String> map = new HashMap<>();
                    map.put("pid", list.get(applianceIndex).getId() + "");
                    map.put("category",lists.get(applianceIndexid).getId()+"");
                    Y.get(YURL.FIND_BY_APPLIANCE_MODEL,map, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
                            StyledDialog.dismissLoading();
                            if (Y.getRespCode(result)) {
                                listss = JSON.parseArray(Y.getData(result), ApplianceBean.class);
                                if (opv2 == null)
                                    opv2 = new OptionsPickerView.Builder(Activity_Appliance.this, new OptionsPickerView.OnOptionsSelectListener() {
                                        @Override
                                        public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                            //选择后的监听器
                                            jiadianTvXinghao.setText(listss.get(options1).getName());
                                            jiadianTvXinghao.setTextColor(Color.parseColor("#00cccc"));
                                            jiadianTvXinghao.setGravity(Gravity.RIGHT|Gravity.CENTER_VERTICAL);
                                            applianceIndexidd=options1;
                                        }
                                    }).build();
                                //把list 进行转换
                                List<String> strs = new ArrayList<String>();
                                for (ApplianceBean ab : listss) {
                                    strs.add(ab.getName());
                                }
                                //添加数据
                                opv2.setPicker(strs, null, null);
                                //显示选择器
                                if (!opv2.isShowing()) {
                                    opv2.show();
                                }
                            }
                        }
                    });
                }

                break;
            case R.id.jiadian_ll_guzhang:
                Y.get(YURL.FIND_PHONE_FAULT,null, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {
                            listsss = JSON.parseArray(Y.getData(result), ApplianceBean.class);
                            if (opv3 == null)
                                opv3 = new OptionsPickerView.Builder(Activity_Appliance.this, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                        //选择后的监听器
                                        jiadianTvGuzhang.setText(listsss.get(options1).getName());
                                        jiadianTvGuzhang.setTextColor(Color.parseColor("#00cccc"));
                                        jiadianTvGuzhang.setGravity(Gravity.RIGHT|Gravity.CENTER_VERTICAL);
                                        applianceIndexiddd=options1;
                                    }
                                }).build();
                            //把list 进行转换
                            List<String> strs = new ArrayList<String>();
                            for (ApplianceBean ab : listsss) {
                                strs.add(ab.getName());
                            }
                            //添加数据
                            opv3.setPicker(strs, null, null);
                            //显示选择器
                            if (!opv3.isShowing()) {
                                opv3.show();
                            }
                        }
                    }
                });
                break;
            case R.id.appliance_ok:
                Intent applianceOkIntent = new Intent(this, Activity_Call_Service.class);
                startActivity(applianceOkIntent);
                break;
        }
    }
}
