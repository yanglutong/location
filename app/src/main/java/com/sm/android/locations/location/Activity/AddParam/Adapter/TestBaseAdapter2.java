package com.sm.android.locations.location.Activity.AddParam.Adapter;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.diycoder.library.adapter.BaseAdapter;
import com.sm.android.locations.location.Activity.LogsActivity;
import com.sm.android.locations.location.R;
import com.sm.android.locations.location.UpdateAdminActivity;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.AdminBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerAdmin;
import com.sm.android.locations.location.Utils.ToastUtils;

import java.sql.SQLException;

/**
 * Created by lq on 16/6/29.
 */
public class TestBaseAdapter2 extends BaseAdapter<TestData2, TestBaseAdapter2.ItemViewHolder> {

    private ITTestBaseAdapterCallBack2 callBack;
    private Context mContext;
    private final LayoutInflater mLayoutInflater;

    public TestBaseAdapter2(Context context) {
        super(context);
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public TestBaseAdapter2(Context context, ITTestBaseAdapterCallBack2 callBack) {
        super(context);
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.callBack = callBack;
    }

    @Override
    public ItemViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(mLayoutInflater.inflate(R.layout.recycler_row2, parent, false));
    }

    @Override
    public void onBindItemViewHolder(final ItemViewHolder holder, final int position) {
        final TestData2 data = getItemData(position);
        if (data != null) {
//            String url = data.icon;
////            Glide.with(mContext).load(url).centerCrop().into(holder.userIcon);
//            holder.mainText.setText(data.nick);
//            holder.subText.setText(data.msg);
//            Date date = new Date();
//            int i = new Random().nextInt(2);
//            long hours = date.getHours() + i;
//            int minutes = date.getMinutes() + i;
//
//            holder.rowButton.setText(hours + ":" + minutes);
//            holder.imsi.setText(data.imsi);
            holder.name.setText(data.name);
            holder.pwd.setText(data.pwd);
            holder.note.setText(data.note);
            holder.xh.setText(data.xh);
            if (data.type.equals("0")) {
                holder.cb.setChecked(false);
            } else {
                holder.cb.setChecked(true);
            }

            holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    Log.d("nzqcb", "onCheckedChanged: " + b);

                    callBack.Call(data.id, b, holder.cb);
                }
            });
            holder.ll_dele.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("favorite", "onClick:favorite ");
//                    new CircleDialog.Builder((FragmentActivity) mContext)
//                            .setTitle("")
//                            .setText("确定要删除IMSI吗")
//                            .setTitleColor(Color.parseColor("#00acff"))
//                            .setNegative("确定", new Positiv(data.id, position))
//                            .setPositive("取消", null)
//                            .show();

                    final Dialog dialog = new Dialog(mContext, R.style.menuDialogStyleDialogStyle);
                    View inflate = LayoutInflater.from(mContext).inflate(R.layout.dialog_item_title, null);
                    TextView tv_title = inflate.findViewById(R.id.tv_title);
                    tv_title.setText("确定要删除" + data.name + "吗？");
                    Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
                    bt_confirm.setOnClickListener(new Positiv(data.id, position, dialog));
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
            holder.ll_bianji.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, UpdateAdminActivity.class);
                    intent.putExtra("id", data.id + "");
                    mContext.startActivity(intent);
                }
            });
            holder.ll_log.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    ToastUtils.showToast("你点击了日志");
//
                    Intent intent = new Intent(mContext, LogsActivity.class);
                    intent.putExtra("id", data.id + "");
                    intent.putExtra("name", data.name + "");
                    mContext.startActivity(intent);
                }
            });
        }
    }


    //内容 ViewHolder
    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView mainText;
        private final TextView subText;
        private final ImageView userIcon;
        private final Button rowButton;
        private final TextView name, note, pwd, xh;
        private final CheckBox cb;
        private RelativeLayout thumb, favorite;
        private LinearLayout ll_bianji, ll_dele, ll_log;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ll_bianji = itemView.findViewById(R.id.ll_bianji);//编辑
            ll_dele = itemView.findViewById(R.id.ll_dele);//删除
            ll_log = itemView.findViewById(R.id.ll_log);//日志
            mainText = (TextView) itemView.findViewById(R.id.mainText);
            subText = (TextView) itemView.findViewById(R.id.subText);
            userIcon = (ImageView) itemView.findViewById(R.id.userIcon);
            rowButton = (Button) itemView.findViewById(R.id.rowButton);

            name = (TextView) itemView.findViewById(R.id.name);
            note = (TextView) itemView.findViewById(R.id.note);
            pwd = (TextView) itemView.findViewById(R.id.pwd);
            xh = (TextView) itemView.findViewById(R.id.xh);
            cb = (CheckBox) itemView.findViewById(R.id.cb);

            thumb = itemView.findViewById(R.id.thumb);//编辑
            favorite = itemView.findViewById(R.id.favorite);//删除
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
                    ToastUtils.showToast("删除成功");
//                    data.remove(position);
//                    mAdapter.remove(position);
//                    mAdapter.notifyItemRemoved(position);
////
////                    recyclerView.setAdapter(mAdapter);
                    callBack.CallDele(id, position);
                    dialog.dismiss();
                    dialog.cancel();
                } else {
                    ToastUtils.showToast("删除失败");
                    dialog.dismiss();
                    dialog.cancel();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public interface ITTestBaseAdapterCallBack2 {
        void Call(int position, boolean checkFlag, CheckBox checkBox);

        void CallDele(int id, int position);
    }
}
