package com.zykj.onexiubrother.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.zykj.onexiubrother.bean.DiZhiGuanLiBean;

import java.util.List;

/**
 * Created by zykj on 2017/4/15.
 */

public class Adapter_DiZhiGuanLi extends RecyclerView.Adapter<Adapter_DiZhiGuanLi.DiZhiHolder> {
    private List<DiZhiGuanLiBean> list;  private Context context;

    @Override
    public DiZhiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(DiZhiHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class DiZhiHolder extends RecyclerView.ViewHolder {

        public DiZhiHolder(View itemView) {
            super(itemView);
        }
    }
}
