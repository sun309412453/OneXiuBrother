package com.zykj.onexiubrother.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.zykj.onexiubrother.R;
import com.zykj.onexiubrother.widget.MyTitleBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/21.
 */

public class Activity_MiMa extends Activity {
    @Bind(R.id.title)
    MyTitleBar title;
    @Bind(R.id.mima_et_mima)
    EditText mimaEtMima;
    @Bind(R.id.mima_et_querenmima)
    EditText mimaEtQuerenmima;
    @Bind(R.id.mima_bt_queren)
    Button mimaBtQueren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mima);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.mima_bt_queren)
    public void onClick() {

    }
}
