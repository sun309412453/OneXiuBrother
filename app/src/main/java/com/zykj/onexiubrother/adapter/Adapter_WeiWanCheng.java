package com.zykj.onexiubrother.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.zykj.onexiubrother.bean.WeiWanChengBean;

import java.util.List;

/**
 * Created by zykj on 2017/4/13.
 */

public class Adapter_WeiWanCheng extends RecyclerView.Adapter<Adapter_WeiWanCheng.WeiWanChengHolder> {
    private List<WeiWanChengBean> list;private Context context;
    @Override
    public WeiWanChengHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(WeiWanChengHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class WeiWanChengHolder extends RecyclerView.ViewHolder {
        public WeiWanChengHolder(View itemView) {
            super(itemView);
        }
    }
}
