package com.zykj.onexiubrother.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
    @Bind(R.id.radioButton)
    RadioButton radioButton;
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
    @Bind(R.id.wodeziliao_tv_diqu)
    TextView wodeziliaoTvDiqu;
    @Bind(R.id.wodeziliao_bt_ok)
    Button wodeziliaoBtOK;
    @Bind(R.id.wodeziliao_iv_iconbg)
    ImageView wodeziliaoIvIconbg;
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
            x.image().bind(wodeziliaoIvIconbg, Y.USER.getIcon(), options);
            wodeziliaoIvIcon.setVisibility(View.INVISIBLE);
        }
        sexRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.radioButton_nan){
                    Y.USER.setSex("男");
                }else if (checkedId==R.id.radioButton_nv){
                    Y.USER.setSex("女");
                }
            }
        });
    }

    @OnClick({R.id.wodeziliao_diqu, R.id.wodeziliao_ll_icon, R.id.wodeziliao_bt_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wodeziliao_diqu:
                Intent diQuIntent = new Intent(this, Activity_WoDeZiLiao_DiQu.class);
                startActivity(diQuIntent);
                break;
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
            case R.id.wodeziliao_bt_ok:
                String name = wodeziliaoEtName.getText().toString().trim();
                String phone = wodeziliaoEtPhone.getText().toString().trim();
                String sex = Y.USER.getSex().toString().trim();
                int user_id = Y.USER.getUser_id();
                String city = Y.USER.getCity().toString().trim();
                String sheng = Y.USER.getProvince().toString().trim();

                break;

        }
    }
}
