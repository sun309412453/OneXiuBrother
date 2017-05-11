package com.zykj.onexiubrother.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.zykj.onexiubrother.R;
import com.zykj.onexiubrother.utils.BannerUtil;
import com.zykj.onexiubrother.utils.OptionsPicke;
import com.zykj.onexiubrother.utils.Y;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 主页面
 * Created by zykj on 2017/4/8.
 */

public class MainActivity extends Activity {
    @Bind(R.id.home_title_city)
    LinearLayout homeTitleCity;
    @Bind(R.id.home_tv_city)
    TextView home_tv_city;
    @Bind(R.id.personal_information)
    ImageView personalInformation;
    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.home_mobile)
    LinearLayout homeMobile;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.home_computer)
    LinearLayout homeComputer;
    @Bind(R.id.home_appliance)
    LinearLayout homeAppliance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButterKnife.bind(this);
        //设置图片加载器
        banner.setImageLoader(new BannerUtil());
        List<Integer> images = new ArrayList<Integer>();
        images.add(R.mipmap.lufei0);
        images.add(R.mipmap.lufei1);
        images.add(R.mipmap.logo);
        //设置图片集合
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

    }

    @OnClick({R.id.home_title_city, R.id.personal_information, R.id.home_mobile, R.id.home_computer, R.id.home_appliance})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_title_city:
                OptionsPicke optionsPicke = new OptionsPicke();
                optionsPicke.showOptionsPicke(this, new OptionsPicke.OptionsSelectListener() {
                    @Override
                    public void selectListener(String province, String city, String district) {
                        home_tv_city.setText(district);
                    }
                });
                break;
            case R.id.personal_information:
                Intent personalInformationIntent = new Intent(this,Activity_GeRenZhongXin.class);
                startActivity(personalInformationIntent);
                break;
            case R.id.home_mobile:
                Intent mobileIntent = new Intent(this,Activity_Mobile.class);
                startActivity(mobileIntent);
                break;
            case R.id.home_computer:
                Intent computerIntent = new Intent(this,Activity_computer.class);
                computerIntent.putExtra("icon", Y.USER.getIcon());
                startActivity(computerIntent);
                break;
            case R.id.home_appliance:
                Intent applianceIntent = new Intent(this,Activity_Appliance.class);
                startActivity(applianceIntent);
                break;
        }
    }
}
