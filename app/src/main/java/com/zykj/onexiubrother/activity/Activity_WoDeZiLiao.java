package com.zykj.onexiubrother.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.hss01248.dialog.StyledDialog;
import com.zykj.onexiubrother.R;
import com.zykj.onexiubrother.utils.OptionsPicke;
import com.zykj.onexiubrother.utils.Y;
import com.zykj.onexiubrother.utils.YURL;
import com.zykj.onexiubrother.widget.MyTitleBar;

import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by zykj on 2017/4/15.
 */

public class Activity_WoDeZiLiao extends Activity {
    @Bind(R.id.title)
    MyTitleBar title;
    @Bind(R.id.wodeziliao_diqu)
    LinearLayout wodeziliaoDiqu;
    @Bind(R.id.wodeziliao_iv_icon)
    ImageView wodeziliaoIvIcon;
    @Bind(R.id.wodeziliao_ll_icon)
    LinearLayout wodeziliaoLlIcon;
    @Bind(R.id.wodeziliao_et_name)
    EditText wodeziliaoEtName;
    @Bind(R.id.radioButton_nan)
    RadioButton radioButtonNan;
    @Bind(R.id.radioButton_nv)
    RadioButton radioButtonNv;
    @Bind(R.id.sex_rg)
    RadioGroup sexRg;
    @Bind(R.id.wodeziliao_et_phone)
    EditText wodeziliaoEtPhone;
    @Bind(R.id.wodeziliao_bt_ok)
    Button wodeziliaoBtOK;
    @Bind(R.id.wodeziliao_iv_iconbg)
    ImageView wodeziliaoIvIconbg;
    @Bind(R.id.wodeziliao_tv_p)
    TextView wodeziliaoTvP;
    @Bind(R.id.wodeziliao_tv_c)
    TextView wodeziliaoTvC;
    private int REQUEST_CODE_GALLERY = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wodeziliao);
        ButterKnife.bind(this);
        title.setLeftClick(new MyTitleBar.leftClick() {
            @Override
            public void Click(View view) {
                finish();
            }
        });
        if (!TextUtils.isEmpty(Y.USER.getIcon())) {
            ImageOptions options = new ImageOptions.Builder().setCircular(true).build();
            x.image().bind(wodeziliaoIvIconbg,YURL.HOST+Y.USER.getIcon(), options);
            wodeziliaoIvIcon.setVisibility(View.GONE);  }
        //把USER数据设置到UI上
        if (!TextUtils.isEmpty(Y.USER.getPhone())) {
            wodeziliaoEtPhone.setText(Y.USER.getPhone());
        }
        //把USER数据设置到UI上
        if (!TextUtils.isEmpty(Y.USER.getSex())) {
            if ("男".equals(Y.USER.getSex())) {
                radioButtonNan.setChecked(true);
                radioButtonNv.setChecked(false);
            } else if ("女".equals(Y.USER.getSex())) {
                radioButtonNv.setChecked(true);
                radioButtonNan.setChecked(false);
            }
        }
        //把USER数据设置到UI上
        if (!TextUtils.isEmpty(Y.USER.getProvince())) {
            wodeziliaoTvP.setText(Y.USER.getProvince());
        }
        if (!TextUtils.isEmpty(Y.USER.getCity())) {
            wodeziliaoTvC.setText(Y.USER.getCity());
        }

        //把USER数据设置到UI上
        if (!TextUtils.isEmpty(Y.USER.getUsername())) {
            wodeziliaoEtName.setText(Y.USER.getUsername());
            wodeziliaoEtName.setGravity(Gravity.RIGHT|Gravity.CENTER_VERTICAL);
            wodeziliaoEtName.setTextColor(Color.parseColor("#c9c9c9"));
        }
    }

    @OnClick({R.id.wodeziliao_diqu, R.id.wodeziliao_ll_icon, R.id.wodeziliao_bt_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            //地区选择
            case R.id.wodeziliao_diqu:
                OptionsPicke optionsPicke = new OptionsPicke();
                optionsPicke.showOptionsPicke(this, new OptionsPicke.OptionsSelectListener() {
                    @Override
                    public void selectListener(String province, String city, String district) {
                        wodeziliaoTvP.setText(province);
                        wodeziliaoTvC.setText(city);
                        wodeziliaoTvP.setTextColor(Color.parseColor("#c9c9c9"));
                        wodeziliaoTvC.setTextColor(Color.parseColor("#c9c9c9"));
                    }
                });
                break;
            //设置头像
            case R.id.wodeziliao_ll_icon:
                GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, new GalleryFinal.OnHanlderResultCallback() {
                    @Override   //成功
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                        if (REQUEST_CODE_GALLERY == reqeustCode) {
                            if (resultList != null) {
                                for (PhotoInfo info : resultList) {
                                    ImageOptions options = new ImageOptions.Builder().setCircular(true).build();
                                    x.image().bind(wodeziliaoIvIconbg, info.getPhotoPath(), options);
                                    wodeziliaoIvIcon.setVisibility(View.INVISIBLE);
                                    File file = new File(info.getPhotoPath());
                                    RequestParams requestParams = new RequestParams(YURL.UP_LOAD_ICON);
                                    requestParams.setMultipart(true);
                                    requestParams.addBodyParameter("icon",file);
                                    requestParams.addBodyParameter("token", Y.TOKEN);
                                    Y.postFile(requestParams, new Y.MyCommonCall<String>() {
                                        @Override
                                        public void onSuccess(String result) {
                                            Y.i(result);
                                            //关闭对话框
                                            StyledDialog.dismissLoading();
                                            if (Y.getRespCode(result)) {
                                                Y.t("上传成功");
                                                Y.USER.setIcon(Y.getData(result));
                                                Y.i(Y.getData(result));
                                            } else {
                                                Y.t("上传失败");
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    }

                    @Override   //失败
                    public void onHanlderFailure(int requestCode, String errorMsg) {
                    }
                });
                break;
            //提交数据并上传
            case R.id.wodeziliao_bt_ok:
                String name = wodeziliaoEtName.getText().toString().trim();
                 String cheng = wodeziliaoTvP.getText().toString().trim();
                 String shi = wodeziliaoTvC.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    Y.t("用户名不能为空");
                    return;
                }
                if (radioButtonNan.isChecked()){
                    Y.USER.setSex(radioButtonNan.getText().toString());
                }else if (radioButtonNv.isChecked()){
                    Y.USER.setSex(radioButtonNv.getText().toString());
                }
                //上传用户资料
                final Map<String, String> map = new HashMap<String, String>();
                map.put("username", name);
                map.put("sex",Y.USER.getSex());
                map.put("province", cheng);
                map.put("city", shi);
                map.put("user_id",Y.USER.getUser_id()+"");
                map.put("token", Y.TOKEN);
                Y.post(YURL.SET_USER_INFO, map, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        //关闭对话框
                        StyledDialog.dismissLoading();

                        if (Y.getRespCode(result)) {
                            String name = map.get("username");
                            String sex = map.get("sex");
                            String province = map.get("province");
                            String city = map.get("city");
                            Y.USER.setUsername(name);
                            Y.USER.setSex(sex);
                            Y.USER.setProvince(province);
                            Y.USER.setCity(city);
                            Y.t("上传成功");
                            Y.i(result);
                            finish();
                        } else {
                            Y.t("上传失败");
                        }
                    }
                });
                break;

        }
    }
}
