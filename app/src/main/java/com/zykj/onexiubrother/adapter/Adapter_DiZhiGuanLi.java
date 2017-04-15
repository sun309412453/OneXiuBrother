package com.zykj.onexiubrother.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zykj.onexiubrother.R;
import com.zykj.onexiubrother.bean.DiZhiGuanLiBean;

import java.util.List;

/**
 * Created by zykj on 2017/4/15.
 */

public class Adapter_DiZhiGuanLi extends RecyclerView.Adapter<Adapter_DiZhiGuanLi.DiZhiHolder> {
    private List<DiZhiGuanLiBean> list;
    private Context context;

    @Override
    public DiZhiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dizhiguanli_item, parent, false);
        DiZhiHolder holder = new DiZhiHolder(view);
        return holder;
    }

    public Adapter_DiZhiGuanLi(List<DiZhiGuanLiBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(DiZhiHolder holder, int position) {
        holder.add_item.setText(list.get(position).getAdd());
        holder.name_item.setText(list.get(position).getName());
        holder.add_item.setText(list.get(position).getAdd());
        holder.phone_item.setText(list.get(position).getPhone());
        holder.dizhixuanze_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dianJi.Click(v);
            }
        });
        holder.dizhibianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bianJi.Click(v);
            }
        });
        holder.dizhishanchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shanChu.Click(v);
            }
        });
    }

    public void setShanChu(DiZhiShanChu shanChu) {
        this.shanChu = shanChu;
    }

    public void setBianJi(DiZhiBianJi bianJi) {
        this.bianJi = bianJi;
    }

    public void setDianJi(DiZhiDianJi dianJi) {
        this.dianJi = dianJi;
    }

    @Override
    public int getItemCount() {
        return list.size();

    }

    public interface DiZhiShanChu {
        void Click(View v);
    }

    private DiZhiShanChu shanChu;

    public interface DiZhiBianJi {
        void Click(View v);
    }

    private DiZhiBianJi bianJi;

    public interface DiZhiDianJi {
        void Click(View v);
    }

    private DiZhiDianJi dianJi;

    public class DiZhiHolder extends RecyclerView.ViewHolder {
        TextView name_item, phone_item, add_item;
        LinearLayout dizhixuanze_item;
        Button dizhibianji, dizhishanchu;

        public DiZhiHolder(View itemView) {
            super(itemView);
            name_item = (TextView) itemView.findViewById(R.id.name_item);
            phone_item = (TextView) itemView.findViewById(R.id.phone_item);
            add_item = (TextView) itemView.findViewById(R.id.add_item);
            dizhixuanze_item = (LinearLayout) itemView.findViewById(R.id.dizhixuanze_item);
            dizhibianji = (Button) itemView.findViewById(R.id.dizhibianji);
            dizhishanchu = (Button) itemView.findViewById(R.id.dizhishanchu);
        }
    }
}
