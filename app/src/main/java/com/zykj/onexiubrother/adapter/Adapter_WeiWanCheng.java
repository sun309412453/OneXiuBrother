package com.zykj.onexiubrother.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zykj.onexiubrother.R;
import com.zykj.onexiubrother.bean.WeiWanChengBean;

import java.util.List;

/**
 * Created by zykj on 2017/4/13.
 */

public class Adapter_WeiWanCheng extends RecyclerView.Adapter<Adapter_WeiWanCheng.WeiWanChengHolder> {
    private List<WeiWanChengBean> list;private Context context;
    @Override
    public WeiWanChengHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.zhuangtai_weiwancheng_item,parent,false);
        WeiWanChengHolder holder = new WeiWanChengHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(WeiWanChengHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class WeiWanChengHolder extends RecyclerView.ViewHolder {
        TextView zhonglei,date,zhuangtai,add_item;Button chakan;
        public WeiWanChengHolder(View itemView) {
            super(itemView);
            zhonglei= (TextView) itemView.findViewById(R.id.zhonglei);
            date= (TextView) itemView.findViewById(R.id.date);
            zhuangtai= (TextView) itemView.findViewById(R.id.zhuangtai);
            add_item= (TextView) itemView.findViewById(R.id.add_item);
            chakan = (Button) itemView.findViewById(R.id.chakan);
        }
    }
}
