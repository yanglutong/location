package com.sm.android.locations.location.zhenma.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sm.android.locations.location.R;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.AddPararBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.ZmBeanlinshi;
import com.sm.android.locations.location.zhenma.ZhenmaSHowListdata;

import java.util.List;

public class ZhenmaFenxiAdapter extends RecyclerView.Adapter<ZhenmaFenxiAdapter.ViewHolder> {
    private TextView tv_imsi2;
    private TextView tv_imsi1;
    private ZhenmaCallBack zhenmaCallBack;
    private List<Integer> list1size;
    private List<AddPararBean> dataAll;
    private Context basecontext;

    private List<ZmBeanlinshi> list;
    private Context context;
    List<Integer> listsize;

    public ZhenmaFenxiAdapter(Context basecontext, List<ZmBeanlinshi> list, ZhenmaCallBack zhenmaCallBack) {
        this.basecontext = basecontext;
        this.list = list;
        this.listsize = listsize;
        this.zhenmaCallBack = zhenmaCallBack;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.zhenmafenxilie2, viewGroup, false);
        ZhenmaFenxiAdapter.ViewHolder viewHolder = new ZhenmaFenxiAdapter.ViewHolder(view);
        return viewHolder;
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Integer integer = position;
        holder.tv_num.setText(integer + 1 + "");
        holder.tv_starttime.setText(list.get(position).getStartdatatime() + "");
        holder.tv_stoptime.setText(list.get(position).getStoptime() + "");
//        holder.tv_starttime.setText(list.get(0).get(position).getStartdatatime() + "");
        holder.tv_imsishow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("nzqa", "onClick: ");
                Intent intent = new Intent(basecontext, ZhenmaSHowListdata.class);
                intent.putExtra("starttime", list.get(position).getStartdatatime());
                intent.putExtra("stoptime", list.get(position).getStoptime());
                basecontext.startActivity(intent);
            }
        });
        holder.cb_location.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                zhenmaCallBack.callID(position);
                if (compoundButton.isChecked()) {
//                    List<ZmBeanlinshi> listAs = ZhenmaFenxiAdapter.this.list.get(position);
                    for (int i = 0; i < ZhenmaFenxiAdapter.this.list.size(); i++) {
                        ZhenmaFenxiAdapter.this.list.get(position).setCheck("1");
                    }

                    zhenmaCallBack.call(list);
                } else {
                    for (int i = 0; i < ZhenmaFenxiAdapter.this.list.size(); i++) {
                        ZhenmaFenxiAdapter.this.list.get(position).setCheck("0");
                    }

                    zhenmaCallBack.call(list);

                }
                Log.d("cb_location", "" + compoundButton.isChecked());

            }
        });
        holder.tv_dele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(basecontext, R.style.menuDialogStyleDialogStyle);
                View inflate = LayoutInflater.from(basecontext).inflate(R.layout.dialog_item_title, null);
                TextView tv_title = inflate.findViewById(R.id.tv_title);
                tv_title.setText("确定要删除侦码分析数据?");
                Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
                bt_confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        zhenmaCallBack.callStartTime(list.get(position).getStartdatatime(), list.get(position).getStoptime(), position);


                        dialog.dismiss();
                        dialog.cancel();


                    }
                });
                Button bt_cancel = inflate.findViewById(R.id.bt_cancel);
                bt_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        dialog.cancel();
                    }
                });
                dialog.setCanceledOnTouchOutside(false);
                dialog.setContentView(inflate);
                //获取当前Activity所在的窗体
                Window dialogWindow = dialog.getWindow();
                //设置Dialog从窗体底部弹出
                dialogWindow.setGravity(Gravity.CENTER);
                //获得窗体的属性
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//                           lp.y = 20;//设置Dialog距离底部的距离
//                          将属性设置给窗体
                dialogWindow.setAttributes(lp);
                dialog.show();//显示对话框
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_num, tv_starttime, tv_stoptime, tv_imsishow, tv_dele;
        CheckBox cb_location;
        RelativeLayout ll_tab;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_num = itemView.findViewById(R.id.tv_num);
            tv_starttime = itemView.findViewById(R.id.tv_starttime);
            tv_stoptime = itemView.findViewById(R.id.tv_stoptime);
            tv_imsishow = itemView.findViewById(R.id.tv_imsi);
            cb_location = itemView.findViewById(R.id.cb_location);
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
        void call(List<ZmBeanlinshi> lists);

        void callID(int i);

        void callStartTime(String i, String i2, int
                postion);
    }
}
