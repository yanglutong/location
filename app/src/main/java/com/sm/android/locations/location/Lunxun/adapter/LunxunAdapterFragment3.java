package com.sm.android.locations.location.Lunxun.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;

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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sm.android.locations.location.Lunxun.SaopinList.LunxunSetingActivity;
import com.sm.android.locations.location.R;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.LunxunBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerLunxun;
import com.sm.android.locations.location.Utils.ToastUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LunxunAdapterFragment3 extends RecyclerView.Adapter<LunxunAdapterFragment3.ViewHolder> {
    private  String name;
    //    private IDialogSaopin dialogPinConfig;
    private List<LunxunBean> spBeanList;
    private Context context;
    DBManagerLunxun dbManager = null;
    int add;
    LunxunAdapterFragment3.IDialogSaopin3 dialogSaopin;
    List<Integer> numlist;
    public LunxunAdapterFragment3(LunxunSetingActivity lunxunSetingActivity) {
        this.context = lunxunSetingActivity;
    }

    public LunxunAdapterFragment3(Context saoPinSetingActivity, List<LunxunBean> spBeanList, LunxunAdapterFragment3.IDialogSaopin3 dialogSaopin, String name, List<Integer> numlist) {
        this.context = saoPinSetingActivity;
        this.spBeanList = spBeanList;
        this.dialogSaopin = dialogSaopin;
        this.name = name;
        this.numlist = numlist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.saopin_c_list, viewGroup, false);
        LunxunAdapterFragment3.ViewHolder viewHolder = new LunxunAdapterFragment3.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        add++;
        holder.tv_pin.setText(spBeanList.get(position).getUp() + "");
        holder.tv_down.setText(spBeanList.get(position).getDown() + "");
        holder.tv_plmn.setText(spBeanList.get(position).getPlmn() + "");
        holder.tv_tf.setText(spBeanList.get(position).getTf() + "");
//        holder.tv_xulie.setText(add + "");
//        if (spBeanList.get(position).getId() == 3) {
//            holder.cb_deful.setChecked(true);
//        }
        holder.tv_xulie.setText(numlist.get(position) + 1 + "");
        holder.tv_band.setText(spBeanList.get(position).getBand() + "");
        holder.ll_dele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Dialog dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
                View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
                TextView tv_title = inflate.findViewById(R.id.tv_title);
                tv_title.setText("????????????????????????????");
                Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
                bt_confirm.setOnClickListener(new Positiv(spBeanList.get(position).getId(), position, dialog));
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
                //????????????Activity???????????????
                Window dialogWindow = dialog.getWindow();
                //??????Dialog?????????????????????
                dialogWindow.setGravity(Gravity.CENTER);
                //?????????????????????
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//                           lp.y = 20;//??????Dialog?????????????????????
//                          ????????????????????????
                dialogWindow.setAttributes(lp);
                dialog.show();//???????????????
            }
        });
        holder.tv_yy.setText(spBeanList.get(position).getYy());

        if (spBeanList.get(position).getType() == 1) {
            holder.cb_deful.setChecked(true);
        } else {
            holder.cb_deful.setChecked(false);
        }
        holder.cb_deful.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean cheecked) {
                if (cheecked) {
                    Log.d("nzq", "onCheckedChanged: " + "??????");
                    List<Integer> list = new ArrayList<>();
                    for (int i = 0; i < spBeanList.size(); i++) {
                        if (spBeanList.get(i).getYy().equals(name)) {
                            list.add(i);
                        }

                    }
                    List<Integer>trues=new ArrayList<>();
                    for (int i = 0; i < spBeanList.size(); i++) {
                        if (spBeanList.get(i).getType() == 1) {
                            trues.add(i);
                        }

                    }
                    if (list.size() > 9&&trues.size()>9) {
                        ToastUtils.showToast("???????????????10?????????");
                        holder.cb_deful.setChecked(false);
                        return;
                    }
                    try {
                        dbManager = new DBManagerLunxun(context);
                        LunxunBean saopinBean = spBeanList.get(position);
                        dbManager.updateStudenttrue(saopinBean);
                        List<LunxunBean> student = dbManager.getStudent();
                        List<Integer> lists = new ArrayList<>();//?????????
                        List<Integer> listtrue = new ArrayList<>();//?????????????????????

                        if (student.size() > 0 && student != null) {
                            for (int i = 0; i < student.size(); i++) {
                                if (student.get(i).getYy().equals(name)) {
                                    lists.add(i);
                                }
                            }
                            for (int i = 0; i < student.size(); i++) {
                                if (student.get(i).getYy().equals(name) && student.get(i).getType() == 1) {
                                    listtrue.add(i);
                                }

                            }
                        }
                        Log.d("nzq", "onCheckedChanged:lists " + lists.size());
                        Log.d("nzq", "onCheckedChanged:student " + student.size());
                        if (listtrue.size() == lists.size()) {//????????????
                            dialogSaopin.update(1);
                        } else if (lists.size() > 0 && listtrue.size() < lists.size()) {
                            dialogSaopin.update(2);////?????????????????? ?????????????????????????????????
                        }


                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.d("nzq", "onCheckedChanged: " + "wei??????");
                    try {
//                        dbManager = new DBManagersaopin(context);
//                        dbManager.updateStudentfalse(spBeanList.get(position));
//                        dialogPinConfig.update();

                        dbManager = new DBManagerLunxun(context);
                        LunxunBean saopinBean = spBeanList.get(position);
                        dbManager.updateStudentfalse(saopinBean);
                        dialogSaopin.update(3);

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        holder.ll_bianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LunxunBean saopinBean = spBeanList.get(position);
                dialogSaopin.upbianji(saopinBean);
            }
        });


    }

    @Override
    public int getItemCount() {
        return spBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_pin, tv_down, tv_plmn, tv_tf, tv_xulie, tv_band, tv_yy;
        CheckBox cb_deful;
        LinearLayout ll_dele, ll_bianji;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_pin = itemView.findViewById(R.id.tv_pin);
            tv_down = itemView.findViewById(R.id.tv_down);
            tv_plmn = itemView.findViewById(R.id.tv_plmn);
            tv_tf = itemView.findViewById(R.id.tv_tf);
            tv_xulie = itemView.findViewById(R.id.tv_xulie);
            tv_band = itemView.findViewById(R.id.tv_band);
//            tv_pin=itemView.findViewById(R.id.tv_pin);

            tv_yy = itemView.findViewById(R.id.tv_yy);
            cb_deful = itemView.findViewById(R.id.cb_deful);
            ll_bianji = itemView.findViewById(R.id.ll_bianji);
            ll_dele = itemView.findViewById(R.id.ll_dele);

        }
    }

//
    class Positiv implements View.OnClickListener {
        private int position;
        int id;
        Dialog dialog;

        public Positiv(int id, int position, Dialog dialog) {
            this.id = id;
            this.position = position;
            this.dialog = dialog;
        }

        @Override
        public void onClick(View v) {
            try {
                dbManager = new DBManagerLunxun(context);
                int i1 = dbManager.deleteStudent(spBeanList.get(position));
                notifyDataSetChanged();
                Log.e("nzq", "deleteStudentonClick: " + "?????????" + i1);
                if (i1 == 1) {
                    //????????????
//                    dialogPinConfig.deleID(i1);
                    spBeanList.remove(position);
                    notifyItemRemoved(position);
                    notifyDataSetChanged();
                    Toast.makeText(context, "????????????", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    dialog.cancel();
//                    dialogPinConfig.update();
                } else {
                    Toast.makeText(context, "????????????", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    dialog.cancel();
//                    dialogPinConfig.update();
                }
            } catch (Exception e) {

            }
        }
    }

    /**
     * //     * @param check ????????????
     */
    public void allCheck(CompoundButton compoundButton) {


    }

    // ????????????
    public interface IDialogSaopin3 {
        public void getPosition(String position, int index, String linename);

        public void deleID(int deleid);

        public void up(LunxunBean saopinBean);

        public void update(int type);

        public void update2();

        public void upbianji(LunxunBean saopinBean);//???????????????
    }
}
