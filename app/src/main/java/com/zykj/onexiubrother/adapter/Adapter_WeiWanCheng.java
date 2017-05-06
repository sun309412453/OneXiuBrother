package com.zykj.onexiubrother.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hss01248.dialog.StyledDialog;
import com.zykj.onexiubrother.R;
import com.zykj.onexiubrother.bean.WeiWanChengBean;
import com.zykj.onexiubrother.utils.Y;
import com.zykj.onexiubrother.utils.YURL;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zykj on 2017/4/13.
 */

public class Adapter_WeiWanCheng extends RecyclerView.Adapter<Adapter_WeiWanCheng.WeiWanChengHolder> {
    private List<WeiWanChengBean> list;
    private Context context;
    private int Index = -1;

    public Adapter_WeiWanCheng(List<WeiWanChengBean> list, Context context, int i) {
        this.list = list;
        this.context = context;
        this.Index = i;
    }

    @Override
    public WeiWanChengHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.zhuangtai_weiwancheng_item, parent, false);
        WeiWanChengHolder holder = new WeiWanChengHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final WeiWanChengHolder holder, final int position) {
        final WeiWanChengBean weiWanChengBean = list.get(position);
        holder.date.setText(weiWanChengBean.getAddtime());
        holder.add_item.setText(weiWanChengBean.getService_address());
        if (Index == 0) {
            holder.shanchu.setText("取消订单");
        } else if (Index == 1) {
            holder.shanchu.setText("删除订单");
        }
        holder.chakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dianJi.OnClick(v, position);
            }
        });
        //删除订单

        holder.shanchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map map = new HashMap();
                map.put("custom_id", Y.USER.getUser_id() + "");
                map.put("id", weiWanChengBean.getId() + "");
                if (Index == 0) {
                    Y.get(YURL.CANCEL_ORDER, map, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
                            StyledDialog.dismissLoading();
                            if (Y.getRespCode(result)) {
                                Y.t("取消订单成功");
                                list.remove(position);
                                notifyDataSetChanged();//刷新列表

                            }
                        }
                    });
                } else if (Index == 1) {
                    Y.get(YURL.DEL_ORDER, map, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
                            StyledDialog.dismissLoading();
                            if (Y.getRespCode(result)) {
                                Y.t("删除订单成功");
                                list.remove(position);
                                notifyDataSetChanged();//刷新列表
                            }
                        }
                    });
                }

            }
        });
        x.image().bind(holder.item_iv_img, YURL.HOST + weiWanChengBean.getImage1());
        int order_type = weiWanChengBean.getOrder_type();
        //判断类型状态
        switch (order_type) {
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
        //订单状态
        int order_state = weiWanChengBean.getOrder_state();
        switch (order_state) {
            case 1:
                holder.zhuangtai.setText("刚发布的订单");
                break;
            case 2:
                holder.zhuangtai.setText("已完成");
                break;
            case 3:
                holder.zhuangtai.setText("已取消");
                break;
            case 4:
                holder.zhuangtai.setText("确认订单");
                break;
            case 5:
                holder.zhuangtai.setText("已支付");
                break;
            case 6:
                holder.zhuangtai.setText("已接单");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class WeiWanChengHolder extends RecyclerView.ViewHolder {
        TextView zhonglei, date, zhuangtai, add_item;
        Button chakan, shanchu;
        ImageView item_iv_img;

        public WeiWanChengHolder(View itemView) {
            super(itemView);
            zhonglei = (TextView) itemView.findViewById(R.id.zhonglei);
            date = (TextView) itemView.findViewById(R.id.date);
            zhuangtai = (TextView) itemView.findViewById(R.id.zhuangtai);
            add_item = (TextView) itemView.findViewById(R.id.add_item);
            chakan = (Button) itemView.findViewById(R.id.chakan);
            item_iv_img = (ImageView) itemView.findViewById(R.id.item_iv_img);
            shanchu = (Button) itemView.findViewById(R.id.item_bt_shanchu);
        }
    }

    private DianJi dianJi;

    public void setDianJi(DianJi dianJi) {
        this.dianJi = dianJi;
    }

    public interface DianJi {
        void OnClick(View v, int pos);
    }
}
