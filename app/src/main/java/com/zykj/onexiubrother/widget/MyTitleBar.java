package com.zykj.onexiubrother.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zykj.onexiubrother.R;

/**
 * 自定义控件
 * Created by zykj on 2017/4/11.
 */

public class MyTitleBar extends RelativeLayout {
    //创建自定义控件
    private ImageView lift;
    private TextView right;
    private TextView title;
    //创建初始化控件变量
    //      <!--左侧控件 开始-->
    private Drawable LiftBackground;
    //       <!--左侧控件 结束-->
    //       <!--标题  开始-->
    private String TitleText;
    private float TitleTextSize;
    private int TitleTextColor;
    //    <!--标题  结束-->
    //    <!--右侧控件  开始-->
    private String RightText;
    private float RightTextSize;
    private int RightTextColor;
    private Drawable RightBackground;
    //    <!--右侧控件  结束-->
    public MyTitleBar(Context context) {
        super(context);
    }

    public MyTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取资源位置
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTitleBar);
        //把左侧控件信息对接上
        LiftBackground=typedArray.getDrawable(R.styleable.MyTitleBar_LiftBackground);
        //把右侧控件信息对接上
        RightText=typedArray.getString(R.styleable.MyTitleBar_RightText);
        RightTextSize=typedArray.getDimension(R.styleable.MyTitleBar_RightTextSize,0);
        RightTextColor=typedArray.getInt(R.styleable.MyTitleBar_RightTextColor,0);
        RightBackground=typedArray.getDrawable(R.styleable.MyTitleBar_RightBackground);
        //把标题控件信息对接上
        TitleText=typedArray.getString(R.styleable.MyTitleBar_TitleText);
        TitleTextSize=typedArray.getDimension(R.styleable.MyTitleBar_TitleTextSize,0);
        TitleTextColor=typedArray.getInt(R.styleable.MyTitleBar_TitleTextColor,0);
        //创建控件并绑定设置信息
        lift=new ImageView(context);
        right = new TextView(context);
        title = new TextView(context);
        //把左侧控件设置上信息
        lift.setBackgroundDrawable(LiftBackground);
        //把右侧控件设置上信息
        right.setText(RightText);
        right.setTextSize(RightTextSize);
        right.setTextColor(RightTextColor);
        right.setBackgroundDrawable(RightBackground);
        right.setGravity(Gravity.RIGHT);
        //把标题控件设置上信息
        title.setText(TitleText);
        title.setTextSize(TitleTextSize);
        title.setTextColor(TitleTextColor);
        title.setGravity(Gravity.CENTER);
        //把标题控件绑定到RL设置上
        LayoutParams titleParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        this.addView(title,titleParams);
        //把左侧控件绑定到RL设置上
        LayoutParams liftParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        liftParams.width=30;
        liftParams.height=40;
        liftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT|RelativeLayout.CENTER_VERTICAL);
        this.addView(lift,liftParams);
        //把右侧控件绑定到RL设置上
        LayoutParams rightParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        rightParams.topMargin=40;
        this.addView(right,rightParams);
        //把left与right设置点击事件
        lift.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                leftClick.Click(view);
            }
        });
        right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                rightClick.Click(view);
            }
        });
    }
    //设置点击事件的成员变量
    private leftClick leftClick;
    private rightClick rightClick;
    //添加点击事件的set函数
    public void setLeftClick(MyTitleBar.leftClick leftClick) {
        this.leftClick = leftClick;
    }

    public void setRightClick(MyTitleBar.rightClick rightClick) {
        this.rightClick = rightClick;
    }
    //添加点击事件的接口
    public interface leftClick{
        void Click(View view);
    }
    public interface rightClick{
        void Click(View view);
    }
    public MyTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
