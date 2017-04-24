package com.zykj.onexiubrother.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

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

public class Activity_RenZhengXinXi extends Activity {
    @Bind(R.id.title)
    MyTitleBar title;
    @Bind(R.id.zheng_iv)
    ImageView zhengIv;
    @Bind(R.id.zheng_jiahao)
    ImageView zhengJiahao;
    @Bind(R.id.renzheng_zheng)
    FrameLayout renzhengZheng;
    @Bind(R.id.fan_iv)
    ImageView fanIv;
    @Bind(R.id.fan_jiahao)
    ImageView fanJiahao;
    @Bind(R.id.renzheng_fan)
    FrameLayout renzhengFan;
    @Bind(R.id.renzheng_bt_tijiao)
    Button renzhengBtTijiao;
    private int REQUEST_CODE_GALLERY = 1;
    private String photoPathzheng, photoPathfan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.renzhengxinxi);
        ButterKnife.bind(this);
        title.setLeftClick(new MyTitleBar.leftClick() {
            @Override
            public void Click(View view) {
                finish();
            }
        });
    }

    @OnClick({R.id.renzheng_zheng, R.id.renzheng_fan, R.id.renzheng_bt_tijiao})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.renzheng_zheng:
                GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, new GalleryFinal.OnHanlderResultCallback() {
                    @Override   //成功
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                        if (REQUEST_CODE_GALLERY == reqeustCode) {
                            if (resultList != null) {
                                for (PhotoInfo info : resultList) {
                                    ImageOptions options = new ImageOptions.Builder().build();
                                    x.image().bind(zhengIv, info.getPhotoPath(), options);
                                    zhengJiahao.setVisibility(View.INVISIBLE);
                                    photoPathzheng = info.getPhotoPath();
                                }
                            }
                        }
                    }

                    @Override
                    public void onHanlderFailure(int requestCode, String errorMsg) {

                    }
                });
                break;
            case R.id.renzheng_fan:
                GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, new GalleryFinal.OnHanlderResultCallback() {
                    @Override   //成功
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                        if (REQUEST_CODE_GALLERY == reqeustCode) {
                            if (resultList != null) {
                                for (PhotoInfo info : resultList) {
                                    ImageOptions options = new ImageOptions.Builder().build();
                                    x.image().bind(fanIv, info.getPhotoPath(), options);
                                    fanJiahao.setVisibility(View.INVISIBLE);
                                    photoPathfan = info.getPhotoPath();
                                }
                            }
                        }
                    }

                    @Override
                    public void onHanlderFailure(int requestCode, String errorMsg) {

                    }
                });
                break;
            case R.id.renzheng_bt_tijiao:
                Map<String, String> map = new HashMap<String, String>();
                map.put("idcard_image1", photoPathzheng);
                map.put("idcard_image2", photoPathfan);
                map.put("token", Y.TOKEN);
                Y.post(YURL.UP_LOAD_ID_CARD, map, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                    StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)){
                            Y.t("上传成功");
                            String data = Y.getData(result);
                            finish();
                        }else {
                            Y.t("上传失败");
                        }
                    }
                });
                break;
        }
    }
}