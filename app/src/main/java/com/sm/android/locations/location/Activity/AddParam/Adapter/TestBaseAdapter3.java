package com.sm.android.locations.location.Activity.AddParam.Adapter;

import android.app.Dialog;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.diycoder.library.adapter.BaseAdapter;
import com.sm.android.locations.location.R;
import com.sm.android.locations.location.Utils.LoginUtils.LoginUtils;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.AdminBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerAdmin;
import com.sm.android.locations.location.Utils.ToastUtils;

import java.sql.SQLException;

/**
 * Created by lq on 16/6/29.
 */
public class TestBaseAdapter3 extends BaseAdapter<TestData3, TestBaseAdapter3.ItemViewHolder> {

    private ITTestBaseAdapterCallBack3 callBack;
    private Context mContext;
    private final LayoutInflater mLayoutInflater;

    public TestBaseAdapter3(Context context) {
        super(context);
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public TestBaseAdapter3(Context context, ITTestBaseAdapterCallBack3 callBack) {
        super(context);
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.callBack = callBack;
    }

    @Override
    public ItemViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(mLayoutInflater.inflate(R.layout.recycler_row3, parent, false));
    }

    @Override
    public void onBindItemViewHolder(final ItemViewHolder holder, final int position) {
        final TestData3 data = getItemData(position);
        if (data != null) {
            holder.xh.setText(data.xh + "");
            holder.datetv.setText(LoginUtils.getBase64(data.datetime));
            holder.name.setText(data.person);
            holder.event.setText(LoginUtils.getBase64(data.event));
            holder.nr.setText("");
            if (LoginUtils.getBase64(data.event).equals("????????????")) {
                holder.nr.setText("?????????"+LoginUtils.getBase64(data.sb));
            }
            if (LoginUtils.getBase64(data.event).equals("????????????")) {
                holder.nr.setText("?????????"+LoginUtils.getBase64(data.sb));
            }
            if (LoginUtils.getBase64(data.event).equals("????????????")||LoginUtils.getBase64(data.event).equals("????????????")) {
                holder.nr.setText("?????????"+LoginUtils.getBase64(data.sb)+"  "+"?????????"+LoginUtils.getBase64(data.pd)+"  "+"IMSI:"+LoginUtils.getBase64(data.sucessIMSI));
            }
            if (LoginUtils.getBase64(data.event).equals("??????")) {
                holder.nr.setText("?????????"+LoginUtils.getBase64(data.sb)+"  "+"?????????"+LoginUtils.getBase64(data.pd)+"  ");
            }
        }
    }


    //?????? ViewHolder
    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView name, datetv, event, xh, nr;


        public ItemViewHolder(View itemView) {
            super(itemView);
            xh = (TextView) itemView.findViewById(R.id.xh);
            name = (TextView) itemView.findViewById(R.id.name);

            datetv = (TextView) itemView.findViewById(R.id.datetv);
            event = (TextView) itemView.findViewById(R.id.event);
            nr = (TextView) itemView.findViewById(R.id.nr);
        }
    }

    public void remove(int posion) {
        notifyItemRemoved(posion);
    }

    class Positiv implements View.OnClickListener {
        private int position;
        private int id;
        private Dialog dialog;

        public Positiv(int id, int position, Dialog dialog) {
            this.id = id;
            this.position = position;
            this.dialog = dialog;
        }

        @Override
        public void onClick(View view) {
            DBManagerAdmin dbManagerAdmin = null;
            try {
                dbManagerAdmin = new DBManagerAdmin(mContext);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                AdminBean forid = dbManagerAdmin.forid(id);
                int i = dbManagerAdmin.deleteAddPararBean(forid);
                if (i == 1) {
                    ToastUtils.showToast("????????????");
//                    data.remove(position);
//                    mAdapter.remove(position);
//                    mAdapter.notifyItemRemoved(position);
////
////                    recyclerView.setAdapter(mAdapter);
//                    callBack.CallDele(id, position);
                    dialog.dismiss();
                    dialog.cancel();
                } else {
                    ToastUtils.showToast("????????????");
                    dialog.dismiss();
                    dialog.cancel();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public interface ITTestBaseAdapterCallBack3 {
        void Call(int position, boolean checkFlag, CheckBox checkBox);

        void CallDele(int id, int position);
    }
}
