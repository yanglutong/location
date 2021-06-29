package com.sm.android.locations.location.Activity.Main.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sm.android.locations.location.R;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.AddPararBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.ZmBeanGKTongji;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RyZmAdapterGk extends RecyclerView.Adapter<RyZmAdapterGk.ViewHolder> {
    private List<Integer> listsize;
    private List<ZmBeanGKTongji> list;

    private List<Integer> list1size;
    private List<AddPararBean> dataAll;
    private Context basecontext;
    int add;
    private GKCallBack gkCallBack;

    @SuppressLint("NewApi")
    public RyZmAdapterGk(Context basecontext, List<ZmBeanGKTongji> list, List<Integer> listsize, GKCallBack gkCallBack) {
        this.list = list;
        this.basecontext = basecontext;
        this.listsize = listsize;
        this.gkCallBack = gkCallBack;
//        rewardModelList  是我本地的 一个list ==List<实体>
//                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        List<String> l = new ArrayList<String>();
//        l.add("2020-12-22 15:20:32");
//        l.add("2020-12-22 15:20:31");
//        l.add("2020-12-22 15:20:33");
//
//        Collections.sort(l, new Comparator<String>() {
//
//            @Override
//            public int compare(String o1, String o2) {
//                try {
//                    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(o2).compareTo(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(o1));
//                } catch (ParseException e) {
//                    return 0;
//                }
//            }
//        });
//        Collections.sort(l, new Comparator<String>() {
//
//            @Override
//            public int compare(String o1, String o2) {
//                try {
//                    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(o2).compareTo(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(o1));
//                } catch (ParseException e) {
//                    return 0;
//                }
//            }
//        });
//        list.sort(new Comparator<ZmBeanGKTongji>() {
//            @Override
//            public int compare(ZmBeanGKTongji zmBeanGKTongji, ZmBeanGKTongji t1) {
//
//                try {
//                    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(t1.getDatatime()).compareTo(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(zmBeanGKTongji.getDatatime()));
//                } catch (ParseException e) {
//                    return 0;
//                }
//            }
//        });
//        Log.d("listnzq", "RyZmAdapterGk: "+list.toString());
//        System.out.println(l);
//        Log.d("nsaqsjbj", "RyZmAdapterGk: " + l.toString());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_liesgk22, viewGroup, false);
        RyZmAdapterGk.ViewHolder viewHolder = new RyZmAdapterGk.ViewHolder(view);
        return viewHolder;
    }


    @SuppressLint({"ResourceAsColor", "NewApi"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {


        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateTimes = new Date();
        String datatime = list.get(position).getDatatime();
        Date date = null;
        try {
            date = df.parse(datatime);
            long between = (dateTimes.getTime() - date.getTime()) / 1000;//除以1000是为了转换成秒
            long second1 = between % 60 / 60;

            Log.d("naaaa", "onBindViewHolder: " + second1 + "----between" + between);
            if (between < 30) {
                if (list.get(position).getSb().equals("2")) {
                    holder.tv_num.setTextColor(basecontext.getColor(R.color.colorAccent));
                    holder.tv_imsi.setTextColor(basecontext.getColor(R.color.colorAccent));
                    holder.tv_datatime.setTextColor(basecontext.getColor(R.color.colorAccent));
                    holder.tv_down.setTextColor(basecontext.getColor(R.color.colorAccent));
                    holder.tv_sb.setTextColor(basecontext.getColor(R.color.colorAccent));
                    holder.tv_sum.setTextColor(basecontext.getColor(R.color.colorAccent));
                }
                if (list.get(position).getSb().equals("1")) {
                    holder.tv_num.setTextColor(basecontext.getColor(R.color.main_green));
                    holder.tv_imsi.setTextColor(basecontext.getColor(R.color.main_green));
                    holder.tv_datatime.setTextColor(basecontext.getColor(R.color.main_green));
                    holder.tv_down.setTextColor(basecontext.getColor(R.color.main_green));
                    holder.tv_sb.setTextColor(basecontext.getColor(R.color.main_green));
                    holder.tv_sum.setTextColor(basecontext.getColor(R.color.main_green));
                }


                //设置数据
                holder.tv_num.setText(listsize.get(position) + "");
                holder.tv_imsi.setText(list.get(position).getImsi() + "");
//        holder.tv_datatime.setText(list.get(position).getDatatime()+"");
                holder.tv_datatime.setText(list.get(position).getTime() + "");
                holder.tv_down.setText(list.get(position).getDown() + "");
                holder.tv_sb.setText(list.get(position).getSb() + "");
                holder.tv_sum.setText(list.get(position).getNum());
            } else {
                holder.tv_num.setText(listsize.get(position) + "");
                holder.tv_imsi.setText(list.get(position).getImsi() + "");
//        holder.tv_datatime.setText(list.get(position).getDatatime()+"");
                holder.tv_datatime.setText(list.get(position).getTime() + "");
                holder.tv_down.setText(list.get(position).getDown() + "");
                holder.tv_sb.setText(list.get(position).getSb() + "");
                holder.tv_sum.setText(list.get(position).getNum());

                holder.tv_num.setTextColor(basecontext.getColor(R.color.gray_black));
                holder.tv_imsi.setTextColor(basecontext.getColor(R.color.gray_black));
                holder.tv_datatime.setTextColor(basecontext.getColor(R.color.gray_black));
                holder.tv_down.setTextColor(basecontext.getColor(R.color.gray_black));
                holder.tv_sb.setTextColor(basecontext.getColor(R.color.gray_black));
                holder.tv_sum.setTextColor(basecontext.getColor(R.color.gray_black));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (gkCallBack != null) {
            holder.ll_tab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gkCallBack.call(list.get(position).getImsi(), list.get(position).getSb());
                }
            });
        }
//        if (ZMBEANGKTONGJILISTCALL.size() > 0) {
//            //隐藏不需要的
//            if (list.get(position).isCheck()==true) {
//                holder.ll_tab.setVisibility(View.VISIBLE);
//            } else {
//                holder.ll_tab.setVisibility(View.GONE);
//            }
//        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_num, tv_datatime, tv_imsi, tv_down, tv_sb, tv_sum;
        LinearLayout ll_tab;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_num = itemView.findViewById(R.id.tv_num);
            tv_imsi = itemView.findViewById(R.id.tv_imsi);
            tv_datatime = itemView.findViewById(R.id.tv_datatime);
            tv_down = itemView.findViewById(R.id.tv_down);
            tv_sb = itemView.findViewById(R.id.tv_sb);
            tv_sum = itemView.findViewById(R.id.tv_sum);
            ll_tab = itemView.findViewById(R.id.ll_tab);
        }
    }


    class Positiv implements View.OnClickListener {
        private int position;
        int id;

        public Positiv(int id, int position) {
            this.id = id;
            this.position = position;
        }

        @Override
        public void onClick(View v) {
//
        }
    }

    public interface IDialogPinConfig {
        void call(String imsi, String sb);
    }

    public interface GKCallBack {
        void call(String imsi, String sb);
    }
}
