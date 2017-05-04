package com.zykj.onexiubrother.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zykj.onexiubrother.R;
import com.zykj.onexiubrother.bean.WeiWanChengBean;
import com.zykj.onexiubrother.utils.YURL;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by zykj on 2017/4/13.
 */

public class Adapter_WeiWanCheng extends RecyclerView.Adapter<Adapter_WeiWanCheng.WeiWanChengHolder> {
    private List<WeiWanChengBean> list;
    private Context context;

    public Adapter_WeiWanCheng(List<WeiWanChengBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public WeiWanChengHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.zhuangtai_weiwancheng_item, parent, false);
        WeiWanChengHolder holder = new WeiWanChengHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(WeiWanChengHolder holder, int position) {
        WeiWanChengBean weiWanChengBean = list.get(position);
        holder.zhuangtai.setText("处理中...");
        holder.date.setText(weiWanChengBean.getAddtime());
        holder.add_item.setText(weiWanChengBean.getService_address());
        holder.chakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dianJi.OnClick(view);
            }
        });
        x.image().bind(holder.item_iv_img, YURL.HOST+weiWanChengBean.getImage1());
        int order_type = weiWanChengBean.getOrder_type();
        switch (order_type){
            case 1:
                holder.zhonglei.setText("手机");
                break;
            case 2:
                holder.zhonglei.setText("电脑");
                break;
            case 3:
                holder.zhonglei.setText("家电");
                break;

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class WeiWanChengHolder extends RecyclerView.ViewHolder {
        TextView zhonglei, date, zhuangtai, add_item;
        Button chakan;
        ImageView item_iv_img;
        public WeiWanChengHolder(View itemView) {
            super(itemView);
            zhonglei = (TextView) itemView.findViewById(R.id.zhonglei);
            date = (TextView) itemView.findViewById(R.id.date);
            zhuangtai = (TextView) itemView.findViewById(R.id.zhuangtai);
            add_item = (TextView) itemView.findViewById(R.id.add_item);
            chakan = (Button) itemView.findViewById(R.id.chakan);
            item_iv_img= (ImageView) itemView.findViewById(R.id.item_iv_img);
        }
    }
    private DianJi dianJi;

    public void setDianJi(DianJi dianJi) {
        this.dianJi = dianJi;
    }

    public interface DianJi{
        void OnClick(View v);
    }
}
