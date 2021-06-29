package com.sm.android.locations.location.Activity.Main.Adapter;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sm.android.locations.location.Activity.PinConfig.PinConfigActivity;
import com.sm.android.locations.location.R;
import com.sm.android.locations.location.Utils.MainUtils.MainUtils;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.SpBean;

import java.util.List;

public class SaopinListAdapter extends RecyclerView.Adapter<SaopinListAdapter.ViewHolder> {

    private List<SpBean> spbeanlist1;
    private Context context;
    int add;

    public SaopinListAdapter(Context context, List<SpBean> spbeanlist1) {
        this.context = context;
        this.spbeanlist1 = spbeanlist1;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.saopinlist_item, viewGroup, false);
        SaopinListAdapter.ViewHolder viewHolder = new SaopinListAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        add++;
//        holder.title.setText(add + "频点" + spbeanlist1.get(position).getDown());
        int num = position + 1;
        holder.title.setText(num + "");
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PinConfigActivity.class);
                intent.putExtra("down", spbeanlist1.get(position).getDown() + "");
                intent.putExtra("band", spbeanlist1.get(position).getBand() + "");
                intent.putExtra("pci", spbeanlist1.get(position).getPci() + "");
                intent.putExtra("plmn", spbeanlist1.get(position).getPlmn() + "");
                intent.putExtra("priority", spbeanlist1.get(position).getPriority() + "");
                intent.putExtra("rsrp", spbeanlist1.get(position).getRsrp() + "");
                intent.putExtra("tac", spbeanlist1.get(position).getTac() + "");
                intent.putExtra("up", spbeanlist1.get(position).getUp() + "");
                intent.putExtra("cid", spbeanlist1.get(position).getCid() + "");
                context.startActivity(intent);
            }
        });
//        tv_band, tv_EARFCN, tv_rsrp, tv_rsrq, tv_plmn, tv_tac, tv_cid, tv_pci;
        String band = MainUtils.getBand(Integer.parseInt(spbeanlist1.get(position).getDown()));
        holder.tv_band.setText("" + band);
        holder.tv_EARFCN.setText("" + spbeanlist1.get(position).getDown());
        if (spbeanlist1.get(position).getRsrp() == 0) {
            holder.tv_rsrp.setText("-");
        } else {
            int rsrp = spbeanlist1.get(position).getRsrp();
            int rsrpset = rsrp - 140;
            holder.tv_rsrp.setText("" + rsrpset);
        }
        //
        if (spbeanlist1.get(position).getRsrq() == 0) {
            holder.tv_rsrq.setText("-");
        } else {
            double v = spbeanlist1.get(position).getRsrq() * 0.5;
            double v1 = v - 20;
            holder.tv_rsrq.setText("" + v1);
        }
        //
        if (spbeanlist1.get(position).getPlmn().equals("0")) {
            holder.tv_plmn.setText("-");
        } else {
            holder.tv_plmn.setText("" + spbeanlist1.get(position).getPlmn());
        }
        if (spbeanlist1.get(position).getTac() == 0) {
            holder.tv_tac.setText("-");
        } else {
            if (spbeanlist1.get(position).getTac() == 65535) {
                holder.tv_tac.setText("-");

            } else {
                holder.tv_tac.setText("" + spbeanlist1.get(position).getTac());

            }
        }
        //

        if (spbeanlist1.get(position).getCid().equals("0")) {
            holder.tv_cid.setText("-");
        } else {
            holder.tv_cid.setText("" + spbeanlist1.get(position).getCid());
        }
        if (spbeanlist1.get(position).getPci() == 0) {
            holder.tv_pci.setText("-");
        } else {
            holder.tv_pci.setText("" + spbeanlist1.get(position).getPci());
        }
        if (spbeanlist1.get(position).isZw()) {
            holder.tv_rsrp.setText(spbeanlist1.get(position).getRsrp() + "");
            holder.tv_rsrq.setText(spbeanlist1.get(position).getRsrq() + "");
            holder.tv_plmn.setText(spbeanlist1.get(position).getPlmn() + "");
            if (spbeanlist1.get(position).getTac() == 2147483647) {
                holder.tv_tac.setText("-");
            } else {
                holder.tv_tac.setText(spbeanlist1.get(position).getTac() + "");
            }
//            holder.tv_tac.setText(spbeanlist1.get(position).getTac() + "");
            holder.tv_cid.setText(spbeanlist1.get(position).getCid() + "");
            int cid = Integer.parseInt(spbeanlist1.get(position).getCid());
            if (cid == 2147483647) {
                holder.tv_cid.setText("-");
            } else {
                holder.tv_cid.setText(spbeanlist1.get(position).getCid() + "");
            }
            holder.tv_pci.setText(spbeanlist1.get(position).getPci() + "");
        }

    }

    @Override
    public int getItemCount() {
        return spbeanlist1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView title, tv_band, tv_EARFCN, tv_rsrp, tv_rsrq, tv_plmn, tv_tac, tv_cid, tv_pci;
        LinearLayout ll;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            tv_band = itemView.findViewById(R.id.tv_band);
            tv_EARFCN = itemView.findViewById(R.id.tv_EARFCN);
            tv_rsrp = itemView.findViewById(R.id.tv_rsrp);
            tv_rsrq = itemView.findViewById(R.id.tv_rsrq);
            tv_plmn = itemView.findViewById(R.id.tv_plmn);
            tv_tac = itemView.findViewById(R.id.tv_tac);
            tv_cid = itemView.findViewById(R.id.tv_cid);
            tv_pci = itemView.findViewById(R.id.tv_pci);
            ll = itemView.findViewById(R.id.ll);
        }
    }


}
