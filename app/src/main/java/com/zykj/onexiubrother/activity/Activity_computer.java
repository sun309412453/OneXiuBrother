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
import com.zykj.onexiubrother.bean.ComputerBean;
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

public class Activity_computer extends Activity {
    @Bind(R.id.title)
    MyTitleBar title;
    @Bind(R.id.computer_ok)
    Button computerOk;
    @Bind(R.id.diannao_tv_pinpai)
    TextView diannaoTvPinpai;
    @Bind(R.id.diannao_ll_pinpai)
    LinearLayout diannaoLlPinpai;
    @Bind(R.id.diannao_tv_leixing)
    TextView diannaoTvLeixing;
    @Bind(R.id.diannao_ll_leixing)
    LinearLayout diannaoLlLeixing;
    @Bind(R.id.diannao_tv_xinghao)
    TextView diannaoTvXinghao;
    @Bind(R.id.diannao_ll_xinghao)
    LinearLayout diannaoLlXinghao;
    @Bind(R.id.diannao_tv_guzhang)
    TextView diannaoTvGuzhang;
    @Bind(R.id.diannao_ll_guzhang)
    LinearLayout diannaoLlGuzhang;
    private List<ComputerBean> list; //品牌的数据源
    private List<ComputerBean> lists; //类型的数据源
    private List<ComputerBean> listss; //型号的数据源
    private List<ComputerBean> listsss; //故障的数据源
    private int computerIndexid = -1;  //用于检测是否选择了类型
    private int computerIndexidd = -1;  //用于检测是否选择了型号
    private int computerIndexiddd = -1;  //用于检测是否选择了故障
    private int computerIndex = -1;  //用于检测是否选择了品牌
    private OptionsPickerView opv,opv1,opv2,opv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_computer);
        ButterKnife.bind(this);
        title.setLeftClick(new MyTitleBar.leftClick() {
            @Override
            public void Click(View view) {
                finish();
            }
        });
    }

    @OnClick({R.id.computer_ok, R.id.diannao_ll_pinpai, R.id.diannao_ll_leixing, R.id.diannao_ll_xinghao, R.id.diannao_ll_guzhang})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.computer_ok:
                Intent computerOkIntent = new Intent(this, Activity_Call_Service.class);
                startActivity(computerOkIntent);
                break;
            case R.id.diannao_ll_pinpai:
                Y.get(YURL.FIND_COMPUTER_BRAND,null, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {
                            list = JSON.parseArray(Y.getData(result), ComputerBean.class);
                            if (opv == null)
                                opv = new OptionsPickerView.Builder(Activity_computer.this, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                        //选择后的监听器
                                        diannaoTvPinpai.setText(list.get(options1).getName());
                                        diannaoTvPinpai.setTextColor(Color.parseColor("#00cccc"));
                                        diannaoTvPinpai.setGravity(Gravity.RIGHT|Gravity.CENTER_VERTICAL);
                                        if (computerIndex != options1) {
                                            diannaoTvXinghao.setHint("请选择您的电脑型号");
                                            diannaoTvXinghao.setHintTextColor(Color.parseColor("#c9c9c9"));
                                            diannaoTvXinghao.setText("");
                                            diannaoTvLeixing.setHint("请选择您的电脑类型");
                                            diannaoTvLeixing.setHintTextColor(Color.parseColor("#c9c9c9"));
                                            diannaoTvLeixing.setText("");
                                            computerIndex = options1;
                                        }
                                    }
                                }).build();
                            //把list 进行转换
                            List<String> strs = new ArrayList<String>();
                            for (ComputerBean cb : list) {
                                strs.add(cb.getName());
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
            case R.id.diannao_ll_leixing:
                if (computerIndex==-1){
                    Y.t("请您先选择品牌");
                }else {
                    Map<String,String> map = new HashMap<>();
                    map.put("pid", list.get(computerIndex).getId() + "");
                    Y.get(YURL.FIND_COMPUTER_CATEGORY,map, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
                            StyledDialog.dismissLoading();
                            if (Y.getRespCode(result)) {
                                lists = JSON.parseArray(Y.getData(result), ComputerBean.class);
                                if (opv1 == null)
                                    opv1 = new OptionsPickerView.Builder(Activity_computer.this, new OptionsPickerView.OnOptionsSelectListener() {
                                        @Override
                                        public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                            //选择后的监听器
                                            diannaoTvLeixing.setText(lists.get(options1).getName());
                                            diannaoTvLeixing.setTextColor(Color.parseColor("#00cccc"));
                                            diannaoTvLeixing.setGravity(Gravity.RIGHT|Gravity.CENTER_VERTICAL);
                                            if (computerIndex != options1) {
                                                diannaoTvLeixing.setHint("请选择您的电脑类型");
                                                diannaoTvLeixing.setHintTextColor(Color.parseColor("#c9c9c9"));
                                                diannaoTvLeixing.setText("");
                                            }
                                            computerIndexid=options1;
                                        }
                                    }).build();
                                //把list 进行转换
                                List<String> strs = new ArrayList<String>();
                                for (ComputerBean cb : lists) {
                                    strs.add(cb.getName());
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
            case R.id.diannao_ll_xinghao:
                if (computerIndex==-1){
                    Y.t("请您先选择类型");
                }else {
                    Map<String,String> map = new HashMap<>();
                    map.put("pid", list.get(computerIndex).getId() + "");
                    map.put("category",lists.get(computerIndexid).getId()+"");
                    Y.get(YURL.FIND_BY_COMPUTER_MODEL,map, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
                            StyledDialog.dismissLoading();
                            if (Y.getRespCode(result)) {
                                listss = JSON.parseArray(Y.getData(result), ComputerBean.class);
                                if (opv2 == null)
                                    opv2 = new OptionsPickerView.Builder(Activity_computer.this, new OptionsPickerView.OnOptionsSelectListener() {
                                        @Override
                                        public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                            //选择后的监听器
                                            diannaoTvXinghao.setText(listss.get(options1).getName());
                                            diannaoTvXinghao.setTextColor(Color.parseColor("#00cccc"));
                                            diannaoTvXinghao.setGravity(Gravity.RIGHT|Gravity.CENTER_VERTICAL);
                                            computerIndexidd=options1;
                                        }
                                    }).build();
                                //把list 进行转换
                                List<String> strs = new ArrayList<String>();
                                for (ComputerBean cb : listss) {
                                    strs.add(cb.getName());
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
            case R.id.diannao_ll_guzhang:
                Y.get(YURL.FIND_PHONE_FAULT,null, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {
                            listsss = JSON.parseArray(Y.getData(result), ComputerBean.class);
                            if (opv3 == null)
                                opv3 = new OptionsPickerView.Builder(Activity_computer.this, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                        //选择后的监听器
                                        diannaoTvGuzhang.setText(listsss.get(options1).getName());
                                        diannaoTvGuzhang.setTextColor(Color.parseColor("#00cccc"));
                                        diannaoTvGuzhang.setGravity(Gravity.RIGHT|Gravity.CENTER_VERTICAL);
                                        computerIndexiddd=options1;
                                    }
                                }).build();
                            //把list 进行转换
                            List<String> strs = new ArrayList<String>();
                            for (ComputerBean cb : listsss) {
                                strs.add(cb.getName());
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
        }
    }
}
