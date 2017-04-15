package com.zykj.onexiubrother.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zykj.onexiubrother.R;
import com.zykj.onexiubrother.bean.DiQuBean;

import java.util.List;

/**
 * Created by zykj on 2017/4/15.
 */

public class Adapter_DiQu extends RecyclerView.Adapter<Adapter_DiQu.DiQuHolder> {
    private Context context; private List<DiQuBean> list;

    public Adapter_DiQu(Context context, List<DiQuBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public DiQuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.diqu_item,parent,false);
        DiQuHolder holder = new DiQuHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DiQuHolder holder, int position) {
        holder.tv_diqu.setText(list.get(position).getDiQu());
        holder.diqu_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dianJi.Click(v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public interface DianJi{
        void Click(View v);
    }
    private DianJi dianJi;

    public void setDianJi(DianJi dianJi) {
        this.dianJi = dianJi;
    }

    public class DiQuHolder extends RecyclerView.ViewHolder {
        TextView tv_diqu;LinearLayout diqu_ll;
        public DiQuHolder(View itemView) {
            super(itemView);
            tv_diqu = (TextView) itemView.findViewById(R.id.tv_diqu);
            diqu_ll= (LinearLayout) itemView.findViewById(R.id.diqu_ll);
        }
    }
}
