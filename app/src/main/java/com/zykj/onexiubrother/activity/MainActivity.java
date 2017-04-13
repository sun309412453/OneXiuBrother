package com.zykj.onexiubrother.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.zykj.onexiubrother.R;

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
    @Bind(R.id.personal_information)
    ImageView personalInformation;
    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.home_mobile)
    FrameLayout homeMobile;
    @Bind(R.id.home_computer)
    FrameLayout homeComputer;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.home_appliance)
    FrameLayout homeAppliance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.home_title_city, R.id.personal_information, R.id.home_mobile, R.id.home_computer, R.id.home_appliance})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_title_city:
                break;
            case R.id.personal_information:
                break;
            case R.id.home_mobile:
                Intent mobileIntent = new Intent(this,Activity_Mobile.class);
                startActivity(mobileIntent);
                break;
            case R.id.home_computer:
                Intent computerIntent = new Intent(this,Activity_Computer.class);
                startActivity(computerIntent);
                break;
            case R.id.home_appliance:
                Intent applianceIntent = new Intent(this,Activity_Appliance.class);
                startActivity(applianceIntent);
                break;
        }
    }
}
