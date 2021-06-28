package com.sm.android.locations.locations.zhenma.adapter;

import android.annotation.SuppressLint;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sm.android.locations.locations.R;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.AddPararBean;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.ZmBeanlinshi;

import java.text.SimpleDateFormat;
import java.util.List;

public class ZhenmafenxiImsiListAdapter extends RecyclerView.Adapter<ZhenmafenxiImsiListAdapter.ViewHolder> {
    private TextView tv_imsi2;
    private TextView tv_imsi1;
    private ZhenmaCallBack zhenmaCallBack;
    private List<Integer> list1size;
    private List<AddPararBean> dataAll;
    private Context basecontext;

    private List<ZmBeanlinshi> list;
    private Context context;
    List<Integer> listsize;

    public ZhenmafenxiImsiListAdapter(Context basecontext, List<ZmBeanlinshi> list) {
        this.basecontext = basecontext;
        this.list = list;
        this.listsize = listsize;
        this.zhenmaCallBack = zhenmaCallBack;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.zhenmafenxilieimsi3, viewGroup, false);
        ZhenmafenxiImsiListAdapter.ViewHolder viewHolder = new ZhenmafenxiImsiListAdapter.ViewHolder(view);
        return viewHolder;
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        int a = position + 1;
        holder.tv_num.setText(a + "");
        holder.tv_imsi.setText(list.get(position).getImsi());
        holder.tv_starttime.setText(list.get(position).getTime()+"");
        holder.tv_stoptime.setText(list.get(position).getStoptime());
        holder.tv_dele.setText(list.get(position).getSb());
//        holder.tv_sum.setText(list.get(position).getSum());

//        holder.tv_starttime.setText(list.get(0).get(position).getStartdatatime() + "");


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_starttime, tv_num, tv_stoptime, tv_imsi, tv_dele;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_starttime = itemView.findViewById(R.id.tv_starttime);
            tv_num = itemView.findViewById(R.id.tv_num);
            tv_stoptime = itemView.findViewById(R.id.tv_stoptime);
            tv_imsi = itemView.findViewById(R.id.tv_imsi);
            tv_dele = itemView.findViewById(R.id.tv_dele);


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
//            try {
//                DBManagerPinConfig dbManager = new DBManagerPinConfig(context);
//                int i1 = dbManager.deleteStudent(pinConfigBeanList.get(position));
//                notifyDataSetChanged();
//                Log.e("nzq", "deleteStudentonClick: " + "执行了" + i1);
//                if (i1 == 1) {
//                    //删除成功
//                    dialogPinConfig.deleID(i1);
//                    pinConfigBeanList.remove(position);
//                    notifyItemRemoved(position);
//                    notifyDataSetChanged();
//                    Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
//
//                } else {
//                    Toast.makeText(context, "删除失败", Toast.LENGTH_SHORT).show();
//                }
//            } catch (Exception e) {
//
//            }
        }
    }

    public interface ZhenmaCallBack {
        void call(List<List<ZmBeanlinshi>> lists);

        void callID(int i);
    }
}
