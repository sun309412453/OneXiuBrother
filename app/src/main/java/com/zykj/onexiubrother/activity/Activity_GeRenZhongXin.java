package com.zykj.onexiubrother.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hss01248.dialog.StyledDialog;
import com.zykj.onexiubrother.R;
import com.zykj.onexiubrother.utils.Y;
import com.zykj.onexiubrother.utils.YURL;
import com.zykj.onexiubrother.widget.MyTitleBar;

import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.File;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by zykj on 2017/4/13.
 */

public class Activity_GeRenZhongXin extends Activity {
    @Bind(R.id.title)
    MyTitleBar title;
    @Bind(R.id.weiwancheng)
    LinearLayout weiwancheng;
    @Bind(R.id.yiwancheng)
    LinearLayout yiwancheng;
    @Bind(R.id.yiquxiao)
    LinearLayout yiquxiao;
    @Bind(R.id.tishi)
    TextView tishi;
    @Bind(R.id.wodeziliao)
    LinearLayout wodeziliao;
    @Bind(R.id.dizhiguanli)
    LinearLayout dizhiguanli;
    @Bind(R.id.wodeqianbao)
    LinearLayout wodeqianbao;
    @Bind(R.id.renzhengxinxi)
    LinearLayout renzhengxinxi;
    @Bind(R.id.pingtaifuwu)
    LinearLayout pingtaifuwu;
    @Bind(R.id.guanyuwomen)
    LinearLayout guanyuwomen;
    @Bind(R.id.shezhi)
    LinearLayout shezhi;
    @Bind(R.id.geren_iv_she)
    ImageView gerenIvShe;
    @Bind(R.id.renzheng)
    TextView renzheng;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.wodeziliao_tv_xinxi)
    TextView wodeziliaoTvXinxi;
    private int REQUEST_CODE_GALLERY = 1;

    protected void onResume() {
        super.onResume();
        //把USER数据设置到UI上
        if (!TextUtils.isEmpty(Y.USER.getIcon())) {
            ImageOptions options = new ImageOptions.Builder().setCircular(true).build();
            x.image().bind(gerenIvShe, YURL.HOST + Y.USER.getIcon(), options);
            wodeziliaoTvXinxi.setText("");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_center);
        ButterKnife.bind(this);
        title.setLeftClick(new MyTitleBar.leftClick() {
            @Override
            public void Click(View view) {
                finish();
            }
        });
    }

    @OnClick({R.id.geren_iv_she, R.id.weiwancheng, R.id.yiwancheng, R.id.yiquxiao, R.id.wodeziliao, R.id.dizhiguanli, R.id.wodeqianbao, R.id.renzhengxinxi, R.id.pingtaifuwu, R.id.guanyuwomen, R.id.shezhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.weiwancheng:
                Intent weiWanChengIntent = new Intent(this, Activity_WeiWanCheng.class);
                startActivity(weiWanChengIntent);
                tishi.setVisibility(View.INVISIBLE);
                break;
            case R.id.yiwancheng:
                Intent yiWanChengIntent = new Intent(this, Activity_YiWanCheng.class);
                startActivity(yiWanChengIntent);
                break;
            case R.id.yiquxiao:
                Intent yiQuXiaoIntent = new Intent(this, Activity_YiQuXiao.class);
                startActivity(yiQuXiaoIntent);
                break;
            case R.id.wodeziliao:
                Intent ziLiaoIntent = new Intent(this, Activity_WoDeZiLiao.class);
                ziLiaoIntent.putExtra("name", Y.USER.getUsername());
                ziLiaoIntent.putExtra("phone", Y.USER.getPhone());
                ziLiaoIntent.putExtra("city", Y.USER.getCity());
                ziLiaoIntent.putExtra("shengfen", Y.USER.getProvince());
                startActivity(ziLiaoIntent);
                break;
            case R.id.dizhiguanli:
                Intent diZhiIntent = new Intent(this, Activity_DiZhiGuanLi.class);
                startActivity(diZhiIntent);
                break;
            case R.id.wodeqianbao:
                break;
            case R.id.renzhengxinxi:
                Intent intentRenZheng = new Intent(Activity_GeRenZhongXin.this, Activity_RenZhengXinXi.class);
                startActivity(intentRenZheng);

                break;
            case R.id.pingtaifuwu:
                break;
            case R.id.guanyuwomen:
                break;
            case R.id.shezhi:
                break;
            case R.id.geren_iv_she:
                GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, new GalleryFinal.OnHanlderResultCallback() {
                    @Override   //成功
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                        if (REQUEST_CODE_GALLERY == reqeustCode) {
                            if (resultList != null) {
                                for (final PhotoInfo info : resultList) {
                                    RequestParams requestParams = new RequestParams(YURL.UP_LOAD_ICON);
                                    Y.i(info.getPhotoPath());
                                    File file = new File(info.getPhotoPath());
                                    requestParams.addBodyParameter("icon",file);
                                    requestParams.addBodyParameter("token", Y.TOKEN);
                                    requestParams.setMultipart(true);
                                    Y.postFile(requestParams, new Y.MyCommonCall<String>() {
                                        @Override
                                        public void onSuccess(String result) {
                                            Y.i(result);
                                            //关闭对话框
                                            StyledDialog.dismissLoading();
                                            if (Y.getRespCode(result)) {
                                                ImageOptions options = new ImageOptions.Builder().setCircular(true).build();
                                                x.image().bind(gerenIvShe, info.getPhotoPath(), options);
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
        }
    }
}
