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

import org.xutils.image.ImageOptions;
import org.xutils.x;

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
        //把USER数据设置到UI上
        if (!TextUtils.isEmpty(Y.USER.getIcon())) {
            ImageOptions options = new ImageOptions.Builder().setCircular(true).build();
            x.image().bind(wodeziliaoIvIconbg, Y.USER.getIcon(), options);
            wodeziliaoIvIcon.setVisibility(View.INVISIBLE);
            return;
        }
        //把USER数据设置到UI上
        if (!TextUtils.isEmpty(Y.USER.getPhone())) {
            wodeziliaoEtPhone.setText(Y.USER.getPhone());
            return;
        }
        //把USER数据设置到UI上
        if (!TextUtils.isEmpty(Y.USER.getSex())) {
            if ("男".equals(Y.USER.getSex())) {
                radioButtonNan.setChecked(true);
                return;
            } else if ("女".equals(Y.USER.getSex())) {
                radioButtonNv.setChecked(true);
                return;
            }
        }
        //把USER数据设置到UI上
        if (!TextUtils.isEmpty(Y.USER.getProvince())) {
            wodeziliaoTvP.setText(Y.USER.getProvince());
            return;
        }
        if (!TextUtils.isEmpty(Y.USER.getCity())) {
            wodeziliaoTvC.setText(Y.USER.getCity());
            return;
        }
        //把USER数据设置到UI上
        if (!TextUtils.isEmpty(Y.USER.getUsername())) {
            wodeziliaoEtName.setText(Y.USER.getUsername());
            wodeziliaoEtName.setGravity(Gravity.RIGHT|Gravity.CENTER_VERTICAL);
            wodeziliaoEtName.setTextColor(Color.parseColor("#c9c9c9"));
            return;
        }
        sexRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButton_nan) {
                    Y.USER.setSex("男");
                } else if (checkedId == R.id.radioButton_nv) {
                    Y.USER.setSex("女");
                }
            }
        });
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
                                    Map<String, String> map = new HashMap<String, String>();
                                    map.put("icon", info.getPhotoPath());
                                    map.put("token", Y.TOKEN);
                                    Y.post(YURL.UP_LOAD_ICON, map, new Y.MyCommonCall<String>() {
                                        @Override
                                        public void onSuccess(String result) {
                                            //关闭对话框
                                            StyledDialog.dismissLoading();
                                            if (Y.getRespCode(result)) {
                                                Y.t("上传成功");
                                                Y.USER.setIcon(Y.getData(result));
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
                final String cheng = wodeziliaoTvP.getText().toString().trim();
                final String shi = wodeziliaoTvC.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    Y.t("用户名不能为空");
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
                            Y.t("上传成功");
                            Y.USER.setProvince(cheng);
                            String city1 = map.get("city");

                            Y.USER.setCity(city1);
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
