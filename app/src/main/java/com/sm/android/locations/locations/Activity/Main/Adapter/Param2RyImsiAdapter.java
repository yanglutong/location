package com.sm.android.locations.locations.Activity.Main.Adapter;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sm.android.locations.locations.Activity.AddParam.Param2Activity;
import com.sm.android.locations.locations.R;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.AddPararBean;
import com.sm.android.locations.locations.Utils.OrmSqlLite.DBManagerAddParam;
import com.sm.android.locations.locations.Utils.OrmSqlLite.DBManagerGuijiView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Param2RyImsiAdapter extends RecyclerView.Adapter<Param2RyImsiAdapter.ViewHolder> {
    private List<AddPararBean> dataAll;
    private Context basecontext;
    public ITParamAdapterCallBack callBack;


    public Param2RyImsiAdapter(Param2Activity basecontext, List<AddPararBean> dataAll, ITParamAdapterCallBack callBack) {
        this.basecontext = basecontext;
        this.dataAll = dataAll;
        this.callBack = callBack;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_row, viewGroup, false);
        Param2RyImsiAdapter.ViewHolder viewHolder = new Param2RyImsiAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.tv_name.setText(dataAll.get(position).getName() + "");
        holder.tv_imsi.setText(dataAll.get(position).getImsi() + "");
        holder.tv_phone.setText(dataAll.get(position).getPhone() + "");
        holder.tv_yy.setText(dataAll.get(position).getYy() + "");
        if (dataAll.get(position).isCheckbox() == true) {
            holder.cb.setChecked(true);
        }
        if (dataAll.get(position).isCheckbox() == false) {
            holder.cb.setChecked(false);
        }

        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.d("nzqcb", "onCheckedChanged: " + b);
                callBack.Call(dataAll.get(position).getId(), b, holder.cb, position);
//
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataAll.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_imsi, tv_phone, tv_yy;
        CheckBox cb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.name);
            tv_imsi = itemView.findViewById(R.id.imsi);
            tv_phone = itemView.findViewById(R.id.phone);
            tv_yy = itemView.findViewById(R.id.yy);
            cb = itemView.findViewById(R.id.cb);

        }
    }

    public interface ITParamAdapterCallBack {
        void Call(int id, boolean checkFlag, CheckBox checkBox, int position);

        void CallDele(int id, int position);
    }
}
