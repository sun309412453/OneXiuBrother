package com.zykj.onexiubrother.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hss01248.dialog.StyledDialog;
import com.zykj.onexiubrother.R;
import com.zykj.onexiubrother.bean.AddressBean;
import com.zykj.onexiubrother.bean.DiZhiGuanLiBean;
import com.zykj.onexiubrother.utils.Y;
import com.zykj.onexiubrother.utils.YURL;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zykj on 2017/4/15.
 */

public class Adapter_DiZhiGuanLi extends RecyclerView.Adapter<Adapter_DiZhiGuanLi.DiZhiHolder> {
    private List<AddressBean> list;
    private Context context;

    @Override
    public DiZhiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dizhiguanli_item, parent, false);
        DiZhiHolder holder = new DiZhiHolder(view);
        return holder;
    }

    public Adapter_DiZhiGuanLi(List<AddressBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(final DiZhiHolder holder, final int position) {
        holder.add_item.setText(list.get(position).getAddress());
        holder.name_item.setText(list.get(position).getName());
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
        AddressBean addressBean = list.get(position);
        holder.dizhishanchu.setTag(addressBean.getAddress_id());
        //删除数据
        holder.dizhishanchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map map = new HashMap();
                map.put("user_id",Y.USER.getUser_id()+"");
                map.put("address_id",v.getTag()+"");
                Y.get(YURL.DEL_ADDRESS, map, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)){
                            Y.t("删除成功");
                            list.remove(position);
                            notifyDataSetChanged();//刷新列表
                        }
                    }
                });
            }
        });
        //默认数据
        if (addressBean.getIsdefault()==1){
            //默认
            holder.tv_moren.setText("默认");
            holder.tv_moren.setTextColor(0xff00cccc);
            holder.iv_moren.setImageResource(R.mipmap.zhifuchenggong);
        }else {
            //非默认
            holder.tv_moren.setText("设置");
            holder.tv_moren.setTextColor(0xffcacaca);
            holder.iv_moren.setImageResource(R.mipmap.u1112);
        }
        //选择默认事件
        holder.iv_moren.setTag(addressBean.getAddress_id());//把ID 捆绑到itemAddressBtShanchu 控件上
        holder.dizhixuanze_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map map =new HashMap();
                map.put("user_id",Y.USER.getUser_id()+"");
                map.put("address_id",v.getTag()+"");
                //发送删除请求
                Y.get(YURL.DEF_ADDRESS, map, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        Y.t("设置成功");
                        if(Y.getRespCode(result)){
                            for (int i = 0; i <list.size() ; i++) {
                                if(i==position){  //需要勾选的位置
                                    list.get(i).setIsdefault(1);
                                }else{  //取消勾选
                                    list.get(i).setIsdefault(0);
                                }
                            }
                            notifyDataSetChanged();//刷新列表
                        }
                    }
                });
            }
        });
        //Item点击事件
        holder.dizhi_item_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diZhiItem.ItemClick(v,position);
            }
        });
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
    public interface DiZhiBianJi {
        void Click(View v);
    }

    private DiZhiBianJi bianJi;

    public interface DiZhiDianJi {
        void Click(View v);
    }
    private DiZhiDianJi dianJi;
    public interface DiZhi_item{
        void ItemClick(View view,int pos);
    }
    private DiZhi_item diZhiItem;

    public List<AddressBean> getList() {
        return list;
    }

    public void setList(List<AddressBean> list) {
        this.list = list;
    }

    public void setDiZhiItem(DiZhi_item diZhiItem) {

        this.diZhiItem = diZhiItem;
    }

    public class DiZhiHolder extends RecyclerView.ViewHolder {
        TextView name_item, phone_item, add_item ,tv_moren;
        LinearLayout dizhixuanze_item ,dizhi_item_ll;
        ImageView iv_moren;
        Button dizhibianji, dizhishanchu;
        public DiZhiHolder(View itemView) {
            super(itemView);
            name_item = (TextView) itemView.findViewById(R.id.name_item);
            phone_item = (TextView) itemView.findViewById(R.id.phone_item);
            add_item = (TextView) itemView.findViewById(R.id.add_item);
            dizhixuanze_item = (LinearLayout) itemView.findViewById(R.id.dizhixuanze_item);
            dizhibianji = (Button) itemView.findViewById(R.id.dizhibianji);
            dizhishanchu = (Button) itemView.findViewById(R.id.dizhishanchu);
            tv_moren = (TextView) itemView.findViewById(R.id.item_tv_moren);
            iv_moren = (ImageView) itemView.findViewById(R.id.iv_moren);
            dizhi_item_ll = (LinearLayout) itemView.findViewById(R.id.dizhi_item_ll);
        }
    }
}
