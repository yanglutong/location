package com.sm.android.locations.location.viewpagermain.NewMainPager;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Message;
import android.speech.tts.TextToSpeech;

import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.sm.android.locations.location.Activity.Main.Adapter.RyZmAdapter;
import com.sm.android.locations.location.Activity.Main.Adapter.RyZmAdapterGk;
import com.sm.android.locations.location.Activity.Main.Adapter.RyZmAdapterdw;
import com.sm.android.locations.location.Constant.Constant;
import com.sm.android.locations.location.R;
import com.sm.android.locations.location.Test.setxq;
import com.sm.android.locations.location.Utils.MainUtils.MainUtils;
import com.sm.android.locations.location.Utils.MainUtils.MainUtils2;
import com.sm.android.locations.location.Utils.MainUtils.MainUtilsThread;
import com.sm.android.locations.location.Utils.MyUtils;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.AddPararBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.AddPararBeanWhite;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.Conmmunit01Bean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.PararBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.PinConfigBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.SaopinBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.ZmBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.ZmBeanGK;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.ZmBeanGKTongji;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.ZmBeanlinshi;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManager01;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerAddParam;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerAddParamWhite;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerLog;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerLunxun;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerPinConfig;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerZM;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerZMGK;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerZMlinshi;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagersaopin;
import com.sm.android.locations.location.Utils.ToastUtils;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;

import static com.sm.android.locations.location.Constant.Constant.CHONGDINGXIANGSET;
import static com.sm.android.locations.location.Constant.Constant.DOWNPIN1;
import static com.sm.android.locations.location.Constant.Constant.DOWNPIN2;


import static com.sm.android.locations.location.Constant.Constant.IP1;
import static com.sm.android.locations.location.Constant.Constant.IP2;

import static com.sm.android.locations.location.Constant.Constant.OPENCHONGDINGXIANG;
import static com.sm.android.locations.location.Utils.MainUtils.MainUtils.DS;
import static com.sm.android.locations.location.Utils.MainUtils.MainUtils.i;
import static com.sm.android.locations.location.Utils.MainUtils.MainUtils.location;
import static com.sm.android.locations.location.Utils.MainUtils.MainUtils2.toIMSI;

public class NewPersent implements NewView.MainPresenter {
    public NewView.View viewS;

    public NewPersent(@NonNull NewView.View view) {
        this.viewS = view;
        view.setPresenter(this);
    }


    @Override
    public void set(String str) {
        Log.d("nzq", str);
        viewS.Up("???????????????2", "2");
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void setFS(boolean b, ImageView imageView, Context context) {
        Log.d("setFS", "setFS: " + b);
        if (b) {
            b = false;
            viewS.FsUp(b);
            SendUtilsNew.fs(b);
            Drawable drawable = context.getResources().getDrawable(R.mipmap.fengshan_toming);
            imageView.setBackground(drawable);
        } else {
            b = true;
            viewS.FsUp(b);
            SendUtilsNew.fs(b);
            Drawable drawable = context.getResources().getDrawable(R.mipmap.fengshan);
            imageView.setBackground(drawable);
        }
    }

    //restart   1???????????? 0???????????????
    @Override
    public void setStart(int device, final boolean b, final int maintype, final String sb1, String sb2, final String sp1, final String sp2, final Context context, final String tf1, final String tf2, int restart, boolean phoneFalg) {
        if (device == 1) {
            if (restart == 1) {
                if (maintype == 0) {
                    if (TextUtils.isEmpty(sb1)) {
                        ToastUtils.showToast("??????1?????????");
                        return;
                    }
                    if (sb1.equals("?????????")) {
                        ToastUtils.showToast("??????1????????????????????????");
                        return;
                    }

                    if (!b) {//??????
                        if (TextUtils.isEmpty(sp1)) {
                            ToastUtils.showToast("??????1????????????????????????");
                            return;
                        }
                        if (sb1.equals("??????")) {
                            String yy = "";
                            String sb1zhishi = "";
                            List<PinConfigBean> pinConfigBeans = null;
                            DBManagerPinConfig dbManagerPinConfig = null;
                            try {
                                dbManagerPinConfig = new DBManagerPinConfig(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(sp1)); //?????????????????????
                                yy = pinConfigBeans.get(0).getYy();
                                sb1zhishi = pinConfigBeans.get(0).getTf();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            if (sb1zhishi.equals(tf1)) {
                                DBManagerAddParam dbManagerAddParam = null;
                                List<AddPararBean> dataAll = null;//??????IMSI???????????????
                                List<AddPararBean> listImsiListTrue = null;//????????????????????????imsi
                                try {
                                    try {
                                        dbManagerAddParam = new DBManagerAddParam(context);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        dataAll = dbManagerAddParam.getDataAll();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    listImsiListTrue = new ArrayList<>();
                                    if (dataAll.size() > 0) {
                                        for (int i = 0; i < dataAll.size(); i++) {
                                            if (dataAll.get(i).isCheckbox() == true) {
                                                dataAll.get(i).setSb("");
                                                listImsiListTrue.add(dataAll.get(i));
                                            }
                                        }
                                        List<Integer> list1size = new ArrayList<>();
                                        if (listImsiListTrue.size() > 0) {
                                            for (int i = 1; i < listImsiListTrue.size() + 1; i++) {

                                                list1size.add(i);

                                            }
                                            viewS.setpararBeansList1(listImsiListTrue);
                                        }

                                    }
                                    List<AddPararBean> sendListBlack = null;
                                    sendListBlack = new ArrayList<>();
//                        Log.d(TAG, "sendBlackList:?????? ");
                                    if (listImsiListTrue.size() > 0 && listImsiListTrue != null) {
                                        for (int i = 0; i < listImsiListTrue.size(); i++) {
                                            if (listImsiListTrue.get(i).getYy().equals(yy)) {
                                                sendListBlack.add(listImsiListTrue.get(i));
                                            }
                                        }
                                    }

                                    if (sendListBlack.size() > 0 && sendListBlack != null) {
//                                Log.d(TAG, "sendBlackList: " + sendListBlack);
                                        if (sendListBlack.size() > 20) {
                                            ToastUtils.showToast("????????????????????????????????????20");
                                        } else {

                                            dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
                                            inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
                                            TextView tv_title = inflate.findViewById(R.id.tv_title);
//            String ip=IP1;

                                            tv_title.setText("?????????????????????1????");
//                ip=IP1;

                                            Button bt_confirm = inflate.findViewById(R.id.bt_confirm);

                                            final List<AddPararBean> finalSendListBlack = sendListBlack;
                                            final List<AddPararBean> finalListImsiListTrue = listImsiListTrue;
                                            bt_confirm.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    sendBlackListRun(finalSendListBlack, tf1, sp1, context, finalListImsiListTrue);


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
//
                                    } else {

                                        ToastUtils.showToast("?????????????????????IMSI");
                                    }

                                } catch (Exception e) {
                                }
                            } else {//???????????????
//                                ToastUtils.showToast("??????1???????????????");

                                dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
                                inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
                                TextView tv_title = inflate.findViewById(R.id.tv_title);
//            String ip=IP1;

                                tv_title.setText("??????1????????????????????????????");
//                ip=IP1;

                                Button bt_confirm = inflate.findViewById(R.id.bt_confirm);

                                bt_confirm.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        viewS.zhishiqiehuan(1, tf1);

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
                                return;
                            }

                        } else {
                            ToastUtils.showToast("??????1??????????????????");
                            return;
                        }
                    } else {//??????
                        if (phoneFalg == false) {
//                            ToastUtils.showToast("??????");
                            Log.d("aaaaa", "setStart: 1");

                            viewS.zidongsaopinjianlixiaoqu(device);
                        } else {
                            Log.d("aaaaa", "setStart: 2");
                            String yy = "";
                            List<PinConfigBean> pinConfigBeans = null;
                            DBManagerPinConfig dbManagerPinConfig = null;
                            try {
                                dbManagerPinConfig = new DBManagerPinConfig(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(sp1)); //?????????????????????
                                yy = pinConfigBeans.get(0).getYy();

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            List<AddPararBean> listImsiListTrue = null;
                            List<AddPararBean> dataAll = null;
                            try {
                                DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                                dataAll = dbManagerAddParam.getDataAll();
                                listImsiListTrue = new ArrayList<>();
                                if (dataAll.size() > 0) {
                                    for (int i = 0; i < dataAll.size(); i++) {
                                        if (dataAll.get(i).isCheckbox() == true) {
                                            dataAll.get(i).setSb("");
                                            listImsiListTrue.add(dataAll.get(i));
                                        }
                                    }
                                    List<Integer> list1size = new ArrayList<>();
                                    if (listImsiListTrue.size() > 0) {
                                        for (int i = 1; i < listImsiListTrue.size() + 1; i++) {

                                            list1size.add(i);

                                        }

                                    }
                                    List<AddPararBean> sendListBlack = null;
                                    sendListBlack = new ArrayList<>();
//                        Log.d(TAG, "sendBlackList:?????? ");
                                    if (listImsiListTrue.size() > 0 && listImsiListTrue != null) {
                                        for (int i = 0; i < listImsiListTrue.size(); i++) {
                                            if (listImsiListTrue.get(i).getYy().equals(yy)) {
                                                sendListBlack.add(listImsiListTrue.get(i));
                                            }
                                        }
                                    }
                                    if (sendListBlack.size() == 0 && sendListBlack == null) {
                                        ToastUtils.showToast("????????????????????????????????????20");
                                    } else {
                                        if (sendListBlack.size() > 20) {
                                            ToastUtils.showToast("?????????????????????IMSI");
                                        } else {
                                            sendBlackListRun(sendListBlack, tf1, sp1, context, listImsiListTrue);

                                        }
                                    }

                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                        }

                    }
                } else {
                    return;
                }
            }


            if (restart == 0) {
                if (sb1.equals("??????")) {
                    String yy = "";
                    String sb1zhishi = "";
                    List<PinConfigBean> pinConfigBeans = null;
                    DBManagerPinConfig dbManagerPinConfig = null;
                    try {
                        dbManagerPinConfig = new DBManagerPinConfig(context);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    try {
                        pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(sp1)); //?????????????????????
                        yy = pinConfigBeans.get(0).getYy();
                        sb1zhishi = pinConfigBeans.get(0).getTf();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if (sb1zhishi.equals(tf1)) {
                        DBManagerAddParam dbManagerAddParam = null;
                        List<AddPararBean> dataAll = null;//??????IMSI???????????????
                        List<AddPararBean> listImsiListTrue = null;//????????????????????????imsi
                        try {
                            try {
                                dbManagerAddParam = new DBManagerAddParam(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                dataAll = dbManagerAddParam.getDataAll();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            listImsiListTrue = new ArrayList<>();
                            if (dataAll.size() > 0) {
                                for (int i = 0; i < dataAll.size(); i++) {
                                    if (dataAll.get(i).isCheckbox() == true) {
                                        dataAll.get(i).setSb("");
                                        listImsiListTrue.add(dataAll.get(i));
                                    }
                                }
                                List<Integer> list1size = new ArrayList<>();
                                if (listImsiListTrue.size() > 0) {
                                    for (int i = 1; i < listImsiListTrue.size() + 1; i++) {

                                        list1size.add(i);

                                    }
                                    viewS.setpararBeansList1(listImsiListTrue);
                                }

                            }
                            List<AddPararBean> sendListBlack = null;
                            sendListBlack = new ArrayList<>();
//                        Log.d(TAG, "sendBlackList:?????? ");
                            if (listImsiListTrue.size() > 0 && listImsiListTrue != null) {
                                for (int i = 0; i < listImsiListTrue.size(); i++) {
                                    if (listImsiListTrue.get(i).getYy().equals(yy)) {
                                        sendListBlack.add(listImsiListTrue.get(i));
                                    }
                                }
                            }

                            if (sendListBlack.size() > 0 && sendListBlack != null) {
//                                Log.d(TAG, "sendBlackList: " + sendListBlack);
                                if (sendListBlack.size() > 20) {
                                    ToastUtils.showToast("????????????????????????????????????20");
                                } else {
                                    final List<AddPararBean> finalSendListBlack = sendListBlack;
                                    final List<AddPararBean> finalListImsiListTrue = listImsiListTrue;

                                    sendBlackListRun(finalSendListBlack, tf1, sp1, context, finalListImsiListTrue);


                                }
//
                            } else {

                                ToastUtils.showToast("?????????????????????IMSI");
                            }

                        } catch (Exception e) {
                        }
                    } else {//???????????????
//                        ToastUtils.showToast("??????1???????????????");
                        dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
                        inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
                        TextView tv_title = inflate.findViewById(R.id.tv_title);
//            String ip=IP1;

                        tv_title.setText("??????1????????????????????????????");
//                ip=IP1;

                        Button bt_confirm = inflate.findViewById(R.id.bt_confirm);

                        bt_confirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                viewS.zhishiqiehuan(1, tf1);

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

                        return;
                    }

                } else {
                    ToastUtils.showToast("??????1??????????????????");
                    return;
                }
            }

        }

        //??????2
        if (device == 2) {
            if (restart == 1) {
                if (maintype == 0) {
                    if (TextUtils.isEmpty(sb2)) {
                        ToastUtils.showToast("??????2?????????");
                        return;
                    }
                    if (sb2.equals("?????????")) {
                        ToastUtils.showToast("??????2????????????????????????");
                        return;
                    }

                    if (!b) {//??????
                        if (TextUtils.isEmpty(sp2)) {
                            ToastUtils.showToast("??????2????????????????????????");
                            return;
                        }
                        if (sb2.equals("??????")) {
                            String yy = "";
                            String sb2zhishi = "";
                            List<PinConfigBean> pinConfigBeans = null;
                            DBManagerPinConfig dbManagerPinConfig = null;
                            try {
                                dbManagerPinConfig = new DBManagerPinConfig(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(sp2)); //?????????????????????
                                yy = pinConfigBeans.get(0).getYy();
                                sb2zhishi = pinConfigBeans.get(0).getTf();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            if (sb2zhishi.equals(tf2)) {
                                DBManagerAddParam dbManagerAddParam = null;
                                List<AddPararBean> dataAll = null;//??????IMSI???????????????
                                List<AddPararBean> listImsiListTrue = null;//????????????????????????imsi
                                try {
                                    try {
                                        dbManagerAddParam = new DBManagerAddParam(context);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        dataAll = dbManagerAddParam.getDataAll();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    listImsiListTrue = new ArrayList<>();
                                    if (dataAll.size() > 0) {
                                        for (int i = 0; i < dataAll.size(); i++) {
                                            if (dataAll.get(i).isCheckbox() == true) {
                                                dataAll.get(i).setSb("");
                                                listImsiListTrue.add(dataAll.get(i));
                                            }
                                        }
                                        List<Integer> list1size = new ArrayList<>();
                                        if (listImsiListTrue.size() > 0) {
                                            for (int i = 1; i < listImsiListTrue.size() + 1; i++) {

                                                list1size.add(i);

                                            }
                                            viewS.setpararBeansList1(listImsiListTrue);
                                        }

                                    }
                                    List<AddPararBean> sendListBlack = null;
                                    sendListBlack = new ArrayList<>();
//                        Log.d(TAG, "sendBlackList:?????? ");
                                    if (listImsiListTrue.size() > 0 && listImsiListTrue != null) {
                                        for (int i = 0; i < listImsiListTrue.size(); i++) {
                                            if (listImsiListTrue.get(i).getYy().equals(yy)) {
                                                sendListBlack.add(listImsiListTrue.get(i));
                                            }
                                        }
                                    }

                                    if (sendListBlack.size() > 0 && sendListBlack != null) {
//                                Log.d(TAG, "sendBlackList: " + sendListBlack);
                                        if (sendListBlack.size() > 20) {
                                            ToastUtils.showToast("????????????????????????????????????20");
                                        } else {

                                            dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
                                            inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
                                            TextView tv_title = inflate.findViewById(R.id.tv_title);
//            String ip=IP1;

                                            tv_title.setText("?????????????????????2????");
//                ip=IP1;

                                            Button bt_confirm = inflate.findViewById(R.id.bt_confirm);

                                            final List<AddPararBean> finalSendListBlack = sendListBlack;
                                            final List<AddPararBean> finalListImsiListTrue = listImsiListTrue;
                                            bt_confirm.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    sendBlackListRun2(finalSendListBlack, tf2, sp2, context, finalListImsiListTrue);


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
//
                                    } else {

                                        ToastUtils.showToast("?????????????????????IMSI");
                                    }

                                } catch (Exception e) {
                                }
                            } else {//???????????????
//                                ToastUtils.showToast("??????1???????????????");

                                dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
                                inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
                                TextView tv_title = inflate.findViewById(R.id.tv_title);
//            String ip=IP1;

                                tv_title.setText("??????2????????????????????????????");
//                ip=IP1;

                                Button bt_confirm = inflate.findViewById(R.id.bt_confirm);

                                bt_confirm.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        viewS.zhishiqiehuan(2, tf2);

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
                                return;
                            }

                        } else {
                            ToastUtils.showToast("??????2??????????????????");
                            return;
                        }
                    } else {//??????
//                        ToastUtils.showToast("??????");
//                        viewS.zidongsaopinjianlixiaoqu(device);

                        if (phoneFalg == false) {
//                            ToastUtils.showToast("??????");
                            viewS.zidongsaopinjianlixiaoqu(device);
                        } else {
                            String yy = "";
                            List<PinConfigBean> pinConfigBeans = null;
                            DBManagerPinConfig dbManagerPinConfig = null;
                            try {
                                dbManagerPinConfig = new DBManagerPinConfig(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(sp2)); //?????????????????????
                                yy = pinConfigBeans.get(0).getYy();

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            List<AddPararBean> listImsiListTrue = null;
                            List<AddPararBean> dataAll = null;
                            try {
                                DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                                dataAll = dbManagerAddParam.getDataAll();
                                listImsiListTrue = new ArrayList<>();
                                if (dataAll.size() > 0) {
                                    for (int i = 0; i < dataAll.size(); i++) {
                                        if (dataAll.get(i).isCheckbox() == true) {
                                            dataAll.get(i).setSb("");
                                            listImsiListTrue.add(dataAll.get(i));
                                        }
                                    }
                                    List<Integer> list1size = new ArrayList<>();
                                    if (listImsiListTrue.size() > 0) {
                                        for (int i = 1; i < listImsiListTrue.size() + 1; i++) {

                                            list1size.add(i);

                                        }

                                    }
                                    List<AddPararBean> sendListBlack = null;
                                    sendListBlack = new ArrayList<>();
//                        Log.d(TAG, "sendBlackList:?????? ");
                                    if (listImsiListTrue.size() > 0 && listImsiListTrue != null) {
                                        for (int i = 0; i < listImsiListTrue.size(); i++) {
                                            if (listImsiListTrue.get(i).getYy().equals(yy)) {
                                                sendListBlack.add(listImsiListTrue.get(i));
                                            }
                                        }
                                    }
                                    if (sendListBlack.size() == 0 && sendListBlack == null) {
                                        ToastUtils.showToast("????????????????????????????????????20");
                                    } else {
                                        if (sendListBlack.size() > 20) {
                                            ToastUtils.showToast("?????????????????????IMSI");
                                        } else {
                                            sendBlackListRun2(sendListBlack, tf2, sp2, context, listImsiListTrue);

                                        }
                                    }

                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }


                        }
                    }
                } else {
                    return;
                }
            }


            if (restart == 0) {
                if (sb2.equals("??????")) {
                    String yy = "";
                    String sb2zhishi = "";
                    List<PinConfigBean> pinConfigBeans = null;
                    DBManagerPinConfig dbManagerPinConfig = null;
                    try {
                        dbManagerPinConfig = new DBManagerPinConfig(context);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    try {
                        pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(sp2)); //?????????????????????
                        yy = pinConfigBeans.get(0).getYy();
                        sb2zhishi = pinConfigBeans.get(0).getTf();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if (sb2zhishi.equals(tf2)) {
                        DBManagerAddParam dbManagerAddParam = null;
                        List<AddPararBean> dataAll = null;//??????IMSI???????????????
                        List<AddPararBean> listImsiListTrue = null;//????????????????????????imsi
                        try {
                            try {
                                dbManagerAddParam = new DBManagerAddParam(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                dataAll = dbManagerAddParam.getDataAll();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            listImsiListTrue = new ArrayList<>();
                            if (dataAll.size() > 0) {
                                for (int i = 0; i < dataAll.size(); i++) {
                                    if (dataAll.get(i).isCheckbox() == true) {
                                        dataAll.get(i).setSb("");
                                        listImsiListTrue.add(dataAll.get(i));
                                    }
                                }
                                List<Integer> list1size = new ArrayList<>();
                                if (listImsiListTrue.size() > 0) {
                                    for (int i = 1; i < listImsiListTrue.size() + 1; i++) {

                                        list1size.add(i);

                                    }
                                    viewS.setpararBeansList1(listImsiListTrue);
                                }

                            }
                            List<AddPararBean> sendListBlack = null;
                            sendListBlack = new ArrayList<>();
//                        Log.d(TAG, "sendBlackList:?????? ");
                            if (listImsiListTrue.size() > 0 && listImsiListTrue != null) {
                                for (int i = 0; i < listImsiListTrue.size(); i++) {
                                    if (listImsiListTrue.get(i).getYy().equals(yy)) {
                                        sendListBlack.add(listImsiListTrue.get(i));
                                    }
                                }
                            }

                            if (sendListBlack.size() > 0 && sendListBlack != null) {
//                                Log.d(TAG, "sendBlackList: " + sendListBlack);
                                if (sendListBlack.size() > 20) {
                                    ToastUtils.showToast("????????????????????????????????????20");
                                } else {
                                    final List<AddPararBean> finalSendListBlack = sendListBlack;
                                    final List<AddPararBean> finalListImsiListTrue = listImsiListTrue;

                                    sendBlackListRun2(finalSendListBlack, tf2, sp2, context, finalListImsiListTrue);


                                }
//
                            } else {

                                ToastUtils.showToast("?????????????????????IMSI");
                            }

                        } catch (Exception e) {
                        }
                    } else {//???????????????
//                        ToastUtils.showToast("??????2???????????????");
                        dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
                        inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
                        TextView tv_title = inflate.findViewById(R.id.tv_title);
//            String ip=IP1;

                        tv_title.setText("??????1????????????????????????????");
//                ip=IP1;

                        Button bt_confirm = inflate.findViewById(R.id.bt_confirm);

                        bt_confirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                viewS.zhishiqiehuan(2, tf2);

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

                        return;
                    }

                } else {
                    ToastUtils.showToast("??????2??????????????????");
                    return;
                }
            }

        }
    }

    @Override
    public void buildSD(final String spinnerS1, final int i, final String sb1, final Context context) {
        if (i == 1) {
            new Thread(new Runnable() {
                @SuppressLint("LongLogTag")
                @Override
                public void run() {
                    List<PinConfigBean> pinConfigBeans = null;
                    DBManagerPinConfig dbManagerPinConfig = null;
                    try {
                        dbManagerPinConfig = new DBManagerPinConfig(context);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if (TextUtils.isEmpty(spinnerS1)) {
                        ToastUtils.showToast("??????1??????????????????");
                        return;
                    }
                    try {
                        pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(spinnerS1)); //?????????????????????

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    Conmmunit01Bean forid = null;
                    DBManager01 dbManager01 = null;
                    try {
                        dbManager01 = new DBManager01(context);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    try {
                        forid = dbManager01.forid(1);  //????????????1

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if (pinConfigBeans == null) {
//                    ToastUtils.showToast("??????????????????");
//                    Set1StatusBar("??????????????????");
                        viewS.buildSdError("??????????????????", 1);
                    }
                    if (forid == null) {
//                    ToastUtils.showToast("??????1????????????");
//                    Set1StatusBar("??????1????????????");
                        viewS.buildSdError("??????1????????????", 1);
                        return;
                    }
                    DatagramSocket socket = null;
                    InetAddress address = null;//????????????ip??????
                    Log.e("znzq", "run: nzqsend");

//        int ulEarfcn,int dlEarfcn,String PLMN, int band,int PCI,int TAC
                    String s = setxq.setXq(
                            pinConfigBeans.get(0).getUp(),
                            pinConfigBeans.get(0).getDown(),
                            pinConfigBeans.get(0).getPlmn(),
                            pinConfigBeans.get(0).getBand(),
                            Integer.parseInt(forid.getPci()),
                            Integer.parseInt(forid.getTac()), Integer.parseInt(forid.getCid()));
//                Log.d(TAG, "run: " + s);
//                byte[] outputData = MainUtilsThread.hexStringToByteArray(setxq.setXq(
//                        pinConfigBeans.get(0).getUp(),
//                        pinConfigBeans.get(0).getDown(),
//                        pinConfigBeans.get(0).getPlmn(),
//                        pinConfigBeans.get(0).getBand(),
//                        Integer.parseInt(forid.getPci()),
//                        Integer.parseInt(forid.getTac()), Integer.parseInt(forid.getCid())));

                    byte[] outputData = MainUtilsThread.hexStringToByteArray(setxq.setXq(
                            pinConfigBeans.get(0).getUp(),
                            pinConfigBeans.get(0).getDown(),
                            pinConfigBeans.get(0).getPlmn(),
                            pinConfigBeans.get(0).getBand(),
                            Integer.parseInt(forid.getPci()),
                            Integer.parseInt(forid.getTac()), Integer.parseInt(forid.getCid())));//4294967295   //222222222l   //  268435455
                    DOWNPIN1 = pinConfigBeans.get(0).getDown() + "";
                    try {
                        socket = new DatagramSocket();
                        address = InetAddress.getByName(IP1);
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    } catch (SocketException e) {
                        e.printStackTrace();
                    }
                    ;
                    DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, address, 3345);
                    Log.e("startLocation1s????????????1", "run: sendsocket?????????" + outputPacket.getPort() + "Ip??????:" + outputPacket.getAddress().toString() + "??????:" + outputPacket.getData());

                    try {
                        if (sb1.equals("?????????")) {

                        } else {
                            //                                    socket.send(outputPacket);
                            DS.send(outputPacket);
                            Log.e("socketstartLocation1s????????????1", "run: sendsocket?????????" + outputPacket.getPort() + "Ip??????:" + outputPacket.getAddress().toString() + "??????:" + outputPacket.getData());

                        }

//                    interrupted();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }
        if (i == 2) {
            new Thread(new Runnable() {
                @SuppressLint("LongLogTag")
                @Override
                public void run() {
                    List<PinConfigBean> pinConfigBeans = null;
                    DBManagerPinConfig dbManagerPinConfig = null;
                    try {
                        dbManagerPinConfig = new DBManagerPinConfig(context);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if (TextUtils.isEmpty(spinnerS1)) {
                        ToastUtils.showToast("??????2??????????????????");
                        return;
                    }
                    try {
                        pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(spinnerS1)); //?????????????????????

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    Conmmunit01Bean forid = null;
                    DBManager01 dbManager01 = null;
                    try {
                        dbManager01 = new DBManager01(context);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    try {
                        forid = dbManager01.forid(2);  //????????????1

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if (pinConfigBeans == null) {
//                    ToastUtils.showToast("??????????????????");
//                    Set1StatusBar("??????????????????");
                        viewS.buildSdError("??????????????????", 1);
                    }
                    if (forid == null) {
//                    ToastUtils.showToast("??????1????????????");
//                    Set1StatusBar("??????1????????????");
                        viewS.buildSdError("??????1????????????", 1);
                        return;
                    }
                    DatagramSocket socket = null;
                    InetAddress address = null;//????????????ip??????
                    Log.e("znzq", "run: nzqsend");

//        int ulEarfcn,int dlEarfcn,String PLMN, int band,int PCI,int TAC
                    String s = setxq.setXq(
                            pinConfigBeans.get(0).getUp(),
                            pinConfigBeans.get(0).getDown(),
                            pinConfigBeans.get(0).getPlmn(),
                            pinConfigBeans.get(0).getBand(),
                            Integer.parseInt(forid.getPci()),
                            Integer.parseInt(forid.getTac()), Integer.parseInt(forid.getCid()));
//                Log.d(TAG, "run: " + s);
//                byte[] outputData = MainUtilsThread.hexStringToByteArray(setxq.setXq(
//                        pinConfigBeans.get(0).getUp(),
//                        pinConfigBeans.get(0).getDown(),
//                        pinConfigBeans.get(0).getPlmn(),
//                        pinConfigBeans.get(0).getBand(),
//                        Integer.parseInt(forid.getPci()),
//                        Integer.parseInt(forid.getTac()), Integer.parseInt(forid.getCid())));

                    byte[] outputData = MainUtilsThread.hexStringToByteArray(setxq.setXq(
                            pinConfigBeans.get(0).getUp(),
                            pinConfigBeans.get(0).getDown(),
                            pinConfigBeans.get(0).getPlmn(),
                            pinConfigBeans.get(0).getBand(),
                            Integer.parseInt(forid.getPci()),
                            Integer.parseInt(forid.getTac()), Integer.parseInt(forid.getCid())));//4294967295   //222222222l   //  268435455
                    DOWNPIN2 = pinConfigBeans.get(0).getDown() + "";
                    try {
                        socket = new DatagramSocket();
                        address = InetAddress.getByName(IP2);
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    } catch (SocketException e) {
                        e.printStackTrace();
                    }
                    ;
                    DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, address, 3345);
                    Log.e("startLocation1s????????????1", "run: sendsocket?????????" + outputPacket.getPort() + "Ip??????:" + outputPacket.getAddress().toString() + "??????:" + outputPacket.getData());

                    try {
                        if (sb1.equals("?????????")) {

                        } else {
                            //                                    socket.send(outputPacket);
                            DS.send(outputPacket);
                            Log.e("socketstartLocation1s????????????1", "run: sendsocket?????????" + outputPacket.getPort() + "Ip??????:" + outputPacket.getAddress().toString() + "??????:" + outputPacket.getData());

                        }

//                    interrupted();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }

    }

    Dialog dialog;
    View inflate;

    @Override
    public void stopdw(final int i, final Context context) {
        dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
        inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
        TextView tv_title = inflate.findViewById(R.id.tv_title);
        String ip = IP1;
        if (i == 1) {
            tv_title.setText("?????????????????????1????");
            ip = IP1;
        }
        if (i == 2) {
            tv_title.setText("?????????????????????2????");
            ip = IP2;
        }
        Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
        final String finalIp = ip;
        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainUtils.StopLocation(finalIp);
//                GFFLAG1 = 2;
//                MainUtils.OpenGF1(1, 2, handler);
                viewS.stopdwup(i);
                dialog.dismiss();
                dialog.cancel();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                DBManagerLog dbManagerLog = null;
                String string = "";

                string = "????????????";

//                //????????????
//                try {
//                    dbManagerLog = new DBManagerLog(context);
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//                LogBean logBean = new LogBean();
//                logBean.setAssociated(LoginUtils.getId(context) + "");//??????ID
//                logBean.setEvent(LoginUtils.setBase64(string));//????????????
//                logBean.setSb(LoginUtils.setBase64("1"));
//                String format = sdf.format(new Date());//????????????
//                logBean.setDatetime(LoginUtils.setBase64(format));
//                try {
//                    dbManagerLog.insertConmmunit01Bean(logBean);
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
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

    @Override
    public void stopZM(int i, final Context context, final Timer timerSB1, final Timer timerSB2, final CountDownTimer countDownTimer1, final CountDownTimer countDownTimer2, final TextView tv_datashengyu1, final TextView tv_datashengyu2, final TextView tv_xunhuanNum1_zm, final TextView tv_xunhuanNum2_zm, final int lunxunNum1, final int lunxunNum2) {//??????????????????

        dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
        inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
        TextView tv_title = inflate.findViewById(R.id.tv_title);
        String ip = IP1;
        if (i == 1) {
            tv_title.setText("???????????????????????????????");
            ip = IP1;
        }
        if (i == 2) {
            tv_title.setText("?????????????????????2????");
            ip = IP2;
        }

        Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
        final String finalIp = ip;
        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainUtils.StopLocation(IP2);

//                GFFLAG1 = 2;
//                MainUtils.OpenGF1(1, 2, handler);
                if (timerSB1 != null) {
                    timerSB1.cancel();
                }
                if (timerSB2 != null) {
                    timerSB2.cancel();
                }
                if (countDownTimer1 != null) {
                    countDownTimer1.cancel();
                }
                if (countDownTimer2 != null) {
                    countDownTimer2.cancel();
                }

                tv_datashengyu1.setText("??????????????????:");
                tv_datashengyu2.setText("??????????????????:");
                tv_xunhuanNum1_zm.setText("??????????????????:");
                tv_xunhuanNum2_zm.setText("??????????????????:");
                viewS.zmstop(lunxunNum1, lunxunNum2);
                SimpleDateFormat aDateFormat = new java.text.SimpleDateFormat("" + "yyyy-MM-dd HH:mm:ss");
                DBManagerZMlinshi dbManagerZMlinshi = null;
                try {
                    dbManagerZMlinshi = new DBManagerZMlinshi(context);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    List<ZmBeanlinshi> dataAllA = dbManagerZMlinshi.getDataAll();
                    if (dataAllA.size() > 0) {
                        for (int i = 0; i < dataAllA.size(); i++) {
                            if (dataAllA.get(i).getSb().equals("1") && TextUtils.isEmpty(dataAllA.get(i).getStoptime())) {
                                ZmBeanlinshi zmBeanlinshi = dataAllA.get(i);
                                zmBeanlinshi.setStoptime(aDateFormat.format(new Date()));
                                dbManagerZMlinshi.updateCheck(zmBeanlinshi);//
                            }

                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    dbManagerZMlinshi = new DBManagerZMlinshi(context);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    List<ZmBeanlinshi> dataAllA = dbManagerZMlinshi.getDataAll();
                    if (dataAllA.size() > 0) {
                        for (int i = 0; i < dataAllA.size(); i++) {
                            if (dataAllA.get(i).getSb().equals("2") && TextUtils.isEmpty(dataAllA.get(i).getStoptime())) {
                                ZmBeanlinshi zmBeanlinshi = dataAllA.get(i);
                                zmBeanlinshi.setStoptime(aDateFormat.format(new Date()));
                                dbManagerZMlinshi.updateCheck(zmBeanlinshi);//
                            }

                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                MainUtils.StopLocation(IP1);
                dialog.dismiss();
                dialog.cancel();
                viewS.stopZmV();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                DBManagerLog dbManagerLog = null;
                String string = "";

                string = "????????????";

//                //????????????
//                try {
//                    dbManagerLog = new DBManagerLog(context);
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//                LogBean logBean = new LogBean();
//                logBean.setAssociated(LoginUtils.getId(context) + "");//??????ID
//                logBean.setEvent(LoginUtils.setBase64(string));//????????????
//                logBean.setSb(LoginUtils.setBase64("1"));
//                String format = sdf.format(new Date());//????????????
//                logBean.setDatetime(LoginUtils.setBase64(format));
//                try {
//                    dbManagerLog.insertConmmunit01Bean(logBean);
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
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

    @Override
    public void stop(int i, Context context) {
        String finalIp = "";
        if (i == 1) {
            finalIp = IP1;
            MainUtils.StopLocation(finalIp);
        }
        if (i == 2) {
            finalIp = IP2;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MainUtils.StopLocation(finalIp);
        }


    }

    @Override
    public void stopGK(final Context context) {

        dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
        inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
        TextView tv_title = inflate.findViewById(R.id.tv_title);
        tv_title.setText("?????????????????????????");


        Button bt_confirm = inflate.findViewById(R.id.bt_confirm);

        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainUtils.StopLocation(IP1);
                MainUtils.StopLocation(IP2);
//                GFFLAG1 = 2;
//                MainUtils.OpenGF1(1, 2, handler);

                dialog.dismiss();
                dialog.cancel();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                DBManagerLog dbManagerLog = null;
                String string = "";

                string = "????????????";
                viewS.gkstop();

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

    @Override
    public void spbuilsGK(Context context, int device, int yy, String tf1, String tf2) {
        if (device == 1) {
            if (yy == 1) {//???????????????
                if (tf1.equals("TDD")) {

                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
                        if (listmun.size() > 0) {
                            MainUtils.start1SNF(IP1, Constant.SNFTDD);
                            DBManagersaopin dbManagersaopin = null;
                            try {
                                dbManagersaopin = new DBManagersaopin(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//?????????????????????
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("??????????????????");
                                    saopinSend1(saopinBeanList, tf1, yy, context);
                                } else {
                                    ToastUtils.showToast("??????????????????");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else {
//                            ToastUtils.showToast("?????????????????????IMSI7");
                            MainUtils.start1SNF(IP1, Constant.SNFTDD);
                            DBManagersaopin dbManagersaopin = null;
                            try {
                                dbManagersaopin = new DBManagersaopin(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//?????????????????????
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("??????????????????");
                                    saopinSend1(saopinBeanList, tf1, yy, context);
                                } else {
                                    ToastUtils.showToast("??????????????????");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }


                }

                if (tf1.equals("FDD")) {


                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
                        if (listmun.size() > 0) {
                            String titles = "";
                            if (tf1.equals("TDD")) {
                                titles = "FDD";
                                MainUtils.start1SNF(IP1, Constant.SNFFDD);
                            }
                            if (tf1.equals("FDD")) {
                                titles = "TDD";
                                MainUtils.start1SNF(IP1, Constant.SNFTDD);
                            }
                            viewS.zhishiqiehuan(1, titles);
                        } else {
//                            ToastUtils.showToast("?????????????????????IMSI");
                            String titles = "";
                            if (tf1.equals("TDD")) {
                                titles = "FDD";
                                MainUtils.start1SNF(IP1, Constant.SNFFDD);
                            }
                            if (tf1.equals("FDD")) {
                                titles = "TDD";
                                MainUtils.start1SNF(IP1, Constant.SNFTDD);
                            }
                            viewS.zhishiqiehuan(1, titles);
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }

            }
            if (yy == 2) {
                if (tf1.equals("TDD")) {
                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
                        if (listmun.size() > 0) {
                            String titles = "";
                            if (tf1.equals("TDD")) {
                                titles = "FDD";
                                MainUtils.start1SNF(IP1, Constant.SNFFDD);
                            }
                            if (tf1.equals("FDD")) {
                                titles = "TDD";
                                MainUtils.start1SNF(IP1, Constant.SNFTDD);
                            }
                            viewS.zhishiqiehuan(1, titles);
                        } else {
//                            ToastUtils.showToast("?????????????????????IMSI");

                            String titles = "";
                            if (tf1.equals("TDD")) {
                                titles = "FDD";
                                MainUtils.start1SNF(IP1, Constant.SNFFDD);
                            }
                            if (tf1.equals("FDD")) {
                                titles = "TDD";
                                MainUtils.start1SNF(IP1, Constant.SNFTDD);
                            }
                            viewS.zhishiqiehuan(1, titles);
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
                if (tf1.equals("FDD")) {

                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
                        if (listmun.size() > 0) {
                            MainUtils.start1SNF(IP1, Constant.SNFFDD);
                            DBManagersaopin dbManagersaopin = null;
                            try {
                                dbManagersaopin = new DBManagersaopin(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//?????????????????????
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("??????????????????");
                                    saopinSend1(saopinBeanList, tf1, yy, context);
                                } else {
                                    ToastUtils.showToast("??????????????????");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else {
                            MainUtils.start1SNF(IP1, Constant.SNFFDD);
                            DBManagersaopin dbManagersaopin = null;
                            try {
                                dbManagersaopin = new DBManagersaopin(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//?????????????????????
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("??????????????????");
                                    saopinSend1(saopinBeanList, tf1, yy, context);
                                } else {
                                    ToastUtils.showToast("??????????????????");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            }
            if (yy == 3) {
                try {
                    DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                    List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                    List<String> listmun = new ArrayList<>();
                    for (int j = 0; j < dataAll.size(); j++) {
                        if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                            listmun.add(dataAll.get(j).getImsi());
                        }

                    }
                    if (tf1.equals("TDD")) {
                        if (listmun.size() > 0) {

                            String titles = "";
                            if (tf1.equals("TDD")) {
                                titles = "FDD";
                                MainUtils.start1SNF(IP1, Constant.SNFFDD);
                            }
                            if (tf1.equals("FDD")) {
                                titles = "TDD";
                                MainUtils.start1SNF(IP1, Constant.SNFTDD);
                            }
                            viewS.zhishiqiehuan(1, titles);
                        } else {
                            String titles = "";
                            if (tf1.equals("TDD")) {
                                titles = "FDD";
                                MainUtils.start1SNF(IP1, Constant.SNFFDD);
                            }
                            if (tf1.equals("FDD")) {
                                titles = "TDD";
                                MainUtils.start1SNF(IP1, Constant.SNFTDD);
                            }
                            viewS.zhishiqiehuan(1, titles);
                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                if (tf1.equals("FDD")) {
                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
                        if (listmun.size() > 0) {
                            MainUtils.start1SNF(IP1, Constant.SNFFDD);
                            DBManagersaopin dbManagersaopin = null;
                            try {
                                dbManagersaopin = new DBManagersaopin(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//?????????????????????
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("??????????????????");
                                    saopinSend1(saopinBeanList, tf1, yy, context);
                                } else {
                                    ToastUtils.showToast("??????????????????");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else {
                            MainUtils.start1SNF(IP1, Constant.SNFFDD);
                            DBManagersaopin dbManagersaopin = null;
                            try {
                                dbManagersaopin = new DBManagersaopin(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//?????????????????????
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("??????????????????");
                                    saopinSend1(saopinBeanList, tf1, yy, context);
                                } else {
                                    ToastUtils.showToast("??????????????????");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            }
            if (yy == 4) {
                if (tf1.equals("TDD")) {
                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
                        if (listmun.size() > 0) {
                            String titles = "";
                            if (tf1.equals("TDD")) {
                                titles = "FDD";
                                MainUtils.start1SNF(IP1, Constant.SNFFDD);
                            }
                            if (tf1.equals("FDD")) {
                                titles = "TDD";
                                MainUtils.start1SNF(IP1, Constant.SNFTDD);
                            }
                            viewS.zhishiqiehuan(1, titles);
                        } else {
                            String titles = "";
                            if (tf1.equals("TDD")) {
                                titles = "FDD";
                                MainUtils.start1SNF(IP1, Constant.SNFFDD);
                            }
                            if (tf1.equals("FDD")) {
                                titles = "TDD";
                                MainUtils.start1SNF(IP1, Constant.SNFTDD);
                            }
                            viewS.zhishiqiehuan(1, titles);
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
                if (tf1.equals("FDD")) {
                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
                        if (listmun.size() > 0) {
                            MainUtils.start1SNF(IP1, Constant.SNFFDD);
                            DBManagersaopin dbManagersaopin = null;
                            try {
                                dbManagersaopin = new DBManagersaopin(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//?????????????????????
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("??????????????????");
                                    saopinSend1(saopinBeanList, tf1, yy, context);
                                } else {
                                    ToastUtils.showToast("??????????????????");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else {
                            MainUtils.start1SNF(IP1, Constant.SNFFDD);
                            DBManagersaopin dbManagersaopin = null;
                            try {
                                dbManagersaopin = new DBManagersaopin(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//?????????????????????
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("??????????????????");
                                    saopinSend1(saopinBeanList, tf1, yy, context);
                                } else {
                                    ToastUtils.showToast("??????????????????");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            }
        }
        //??????2
        if (device == 2) {
            if (yy == 1) {//???????????????
                if (tf2.equals("TDD")) {
                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
                        if (listmun.size() > 0) {
                            MainUtils.start1SNF(IP2, Constant.SNFTDD);
                            DBManagersaopin dbManagersaopin = null;
                            try {
                                dbManagersaopin = new DBManagersaopin(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//?????????????????????
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("??????????????????");
                                    saopinSend2(saopinBeanList, tf2, yy, context);
                                } else {
                                    ToastUtils.showToast("??????????????????");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else {
                            MainUtils.start1SNF(IP2, Constant.SNFTDD);
                            DBManagersaopin dbManagersaopin = null;
                            try {
                                dbManagersaopin = new DBManagersaopin(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//?????????????????????
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("??????????????????");
                                    saopinSend2(saopinBeanList, tf2, yy, context);
                                } else {
                                    ToastUtils.showToast("??????????????????");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }

                if (tf2.equals("FDD")) {
                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
                        if (listmun.size() > 0) {
                            String titles = "";
                            if (tf2.equals("TDD")) {
                                titles = "FDD";
                                MainUtils.start1SNF(IP2, Constant.SNFFDD);
                            }
                            if (tf2.equals("FDD")) {
                                titles = "TDD";
                                MainUtils.start1SNF(IP2, Constant.SNFTDD);
                            }
                            viewS.zhishiqiehuan(2, titles);
                        } else {
                            String titles = "";
                            if (tf2.equals("TDD")) {
                                titles = "FDD";
                                MainUtils.start1SNF(IP2, Constant.SNFFDD);
                            }
                            if (tf2.equals("FDD")) {
                                titles = "TDD";
                                MainUtils.start1SNF(IP2, Constant.SNFTDD);
                            }
                            viewS.zhishiqiehuan(2, titles);
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }

            }
            if (yy == 2) {
                if (tf2.equals("TDD")) {
                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
                        if (listmun.size() > 0) {
                            String titles = "";
                            if (tf2.equals("TDD")) {
                                titles = "FDD";
                                MainUtils.start1SNF(IP2, Constant.SNFFDD);
                            }
                            if (tf2.equals("FDD")) {
                                titles = "TDD";
                                MainUtils.start1SNF(IP2, Constant.SNFTDD);
                            }
                            viewS.zhishiqiehuan(2, titles);
                        } else {
                            String titles = "";
                            if (tf2.equals("TDD")) {
                                titles = "FDD";
                                MainUtils.start1SNF(IP2, Constant.SNFFDD);
                            }
                            if (tf2.equals("FDD")) {
                                titles = "TDD";
                                MainUtils.start1SNF(IP2, Constant.SNFTDD);
                            }
                            viewS.zhishiqiehuan(2, titles);
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
                if (tf2.equals("FDD")) {
                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
                        if (listmun.size() > 0) {
                            MainUtils.start1SNF(IP2, Constant.SNFFDD);
                            DBManagersaopin dbManagersaopin = null;
                            try {
                                dbManagersaopin = new DBManagersaopin(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//?????????????????????
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("??????????????????");
                                    saopinSend2(saopinBeanList, tf2, yy, context);
                                } else {
                                    ToastUtils.showToast("??????????????????");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else {
                            MainUtils.start1SNF(IP2, Constant.SNFFDD);
                            DBManagersaopin dbManagersaopin = null;
                            try {
                                dbManagersaopin = new DBManagersaopin(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//?????????????????????
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("??????????????????");
                                    saopinSend2(saopinBeanList, tf2, yy, context);
                                } else {
                                    ToastUtils.showToast("??????????????????");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            }
            if (yy == 3) {

                try {
                    DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                    List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                    List<String> listmun = new ArrayList<>();
                    for (int j = 0; j < dataAll.size(); j++) {
                        if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                            listmun.add(dataAll.get(j).getImsi());
                        }

                    }
                    if (tf2.equals("TDD")) {
                        if (listmun.size() > 0) {

                            String titles = "";
                            if (tf2.equals("TDD")) {
                                titles = "FDD";
                                MainUtils.start1SNF(IP2, Constant.SNFFDD);
                            }
                            if (tf2.equals("FDD")) {
                                titles = "TDD";
                                MainUtils.start1SNF(IP2, Constant.SNFTDD);
                            }
                            viewS.zhishiqiehuan(2, titles);
                        } else {
//                            ToastUtils.showToast("?????????????????????IMSI");
                            String titles = "";
                            if (tf2.equals("TDD")) {
                                titles = "FDD";
                                MainUtils.start1SNF(IP2, Constant.SNFFDD);
                            }
                            if (tf2.equals("FDD")) {
                                titles = "TDD";
                                MainUtils.start1SNF(IP2, Constant.SNFTDD);
                            }
                            viewS.zhishiqiehuan(2, titles);
                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                    List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                    List<String> listmun = new ArrayList<>();
                    for (int j = 0; j < dataAll.size(); j++) {
                        if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                            listmun.add(dataAll.get(j).getImsi());
                        }

                    }
                    if (tf2.equals("FDD")) {
                        if (listmun.size() > 0) {

                            MainUtils.start1SNF(IP2, Constant.SNFFDD);
                            DBManagersaopin dbManagersaopin = null;
                            try {
                                dbManagersaopin = new DBManagersaopin(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//?????????????????????
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("??????????????????");
                                    saopinSend2(saopinBeanList, tf2, yy, context);
                                } else {
                                    ToastUtils.showToast("??????????????????");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else {
                            MainUtils.start1SNF(IP2, Constant.SNFFDD);
                            DBManagersaopin dbManagersaopin = null;
                            try {
                                dbManagersaopin = new DBManagersaopin(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//?????????????????????
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("??????????????????");
                                    saopinSend2(saopinBeanList, tf2, yy, context);
                                } else {
                                    ToastUtils.showToast("??????????????????");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
            if (yy == 4) {
                try {
                    DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                    List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                    List<String> listmun = new ArrayList<>();
                    for (int j = 0; j < dataAll.size(); j++) {
                        if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                            listmun.add(dataAll.get(j).getImsi());
                        }

                    }
                    if (tf2.equals("TDD")) {
                        if (listmun.size() > 0) {

                            String titles = "";
                            if (tf2.equals("TDD")) {
                                titles = "FDD";
                                MainUtils.start1SNF(IP2, Constant.SNFFDD);
                            }
                            if (tf2.equals("FDD")) {
                                titles = "TDD";
                                MainUtils.start1SNF(IP2, Constant.SNFTDD);
                            }
                            viewS.zhishiqiehuan(2, titles);
                        } else {
//                            ToastUtils.showToast("?????????????????????IMSI");
                            String titles = "";
                            if (tf2.equals("TDD")) {
                                titles = "FDD";
                                MainUtils.start1SNF(IP2, Constant.SNFFDD);
                            }
                            if (tf2.equals("FDD")) {
                                titles = "TDD";
                                MainUtils.start1SNF(IP2, Constant.SNFTDD);
                            }
                            viewS.zhishiqiehuan(2, titles);
                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                    List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                    List<String> listmun = new ArrayList<>();
                    for (int j = 0; j < dataAll.size(); j++) {
                        if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                            listmun.add(dataAll.get(j).getImsi());
                        }

                    }
                    if (tf2.equals("FDD")) {
                        if (listmun.size() > 0) {

                            MainUtils.start1SNF(IP2, Constant.SNFFDD);
                            DBManagersaopin dbManagersaopin = null;
                            try {
                                dbManagersaopin = new DBManagersaopin(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//?????????????????????
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("??????????????????");
                                    saopinSend2(saopinBeanList, tf2, yy, context);
                                } else {
                                    ToastUtils.showToast("??????????????????");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else {

                            MainUtils.start1SNF(IP2, Constant.SNFFDD);
                            DBManagersaopin dbManagersaopin = null;
                            try {
                                dbManagersaopin = new DBManagersaopin(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//?????????????????????
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("??????????????????");
                                    saopinSend2(saopinBeanList, tf2, yy, context);
                                } else {
                                    ToastUtils.showToast("??????????????????");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        }
    }

    @Override
    public void spbuils(Context context, int device, int yy, String tf1, String tf2) {
        if (device == 1) {
            if (yy == 1) {//???????????????
                if (tf1.equals("TDD")) {

                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
                        if (listmun.size() > 0) {
                            MainUtils.start1SNF(IP1, Constant.SNFTDD);
                            DBManagersaopin dbManagersaopin = null;
                            try {
                                dbManagersaopin = new DBManagersaopin(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//?????????????????????
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("??????????????????");
                                    saopinSend1(saopinBeanList, tf1, yy, context);
                                } else {
                                    ToastUtils.showToast("??????????????????");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else {
                            ToastUtils.showToast("?????????????????????IMSI");
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }


                }

                if (tf1.equals("FDD")) {


                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
                        if (listmun.size() > 0) {
                            String titles = "";
                            if (tf1.equals("TDD")) {
                                titles = "FDD";
                                MainUtils.start1SNF(IP1, Constant.SNFFDD);
                            }
                            if (tf1.equals("FDD")) {
                                titles = "TDD";
                                MainUtils.start1SNF(IP1, Constant.SNFTDD);
                            }
                            viewS.zhishiqiehuan(1, titles);
                        } else {
                            ToastUtils.showToast("?????????????????????IMSI");
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }

            }
            if (yy == 2) {
                if (tf1.equals("TDD")) {
                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
                        if (listmun.size() > 0) {
                            String titles = "";
                            if (tf1.equals("TDD")) {
                                titles = "FDD";
                                MainUtils.start1SNF(IP1, Constant.SNFFDD);
                            }
                            if (tf1.equals("FDD")) {
                                titles = "TDD";
                                MainUtils.start1SNF(IP1, Constant.SNFTDD);
                            }
                            viewS.zhishiqiehuan(1, titles);
                        } else {
                            ToastUtils.showToast("?????????????????????IMSI");
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
                if (tf1.equals("FDD")) {

                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
                        if (listmun.size() > 0) {
                            MainUtils.start1SNF(IP1, Constant.SNFFDD);
                            DBManagersaopin dbManagersaopin = null;
                            try {
                                dbManagersaopin = new DBManagersaopin(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//?????????????????????
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("??????????????????");
                                    saopinSend1(saopinBeanList, tf1, yy, context);
                                } else {
                                    ToastUtils.showToast("??????????????????");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else {
                            ToastUtils.showToast("?????????????????????IMSI");
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            }
            if (yy == 3) {
                try {
                    DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                    List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                    List<String> listmun = new ArrayList<>();
                    for (int j = 0; j < dataAll.size(); j++) {
                        if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                            listmun.add(dataAll.get(j).getImsi());
                        }

                    }
                    if (tf1.equals("TDD")) {
                        if (listmun.size() > 0) {

                            String titles = "";
                            if (tf1.equals("TDD")) {
                                titles = "FDD";
                                MainUtils.start1SNF(IP1, Constant.SNFFDD);
                            }
                            if (tf1.equals("FDD")) {
                                titles = "TDD";
                                MainUtils.start1SNF(IP1, Constant.SNFTDD);
                            }
                            viewS.zhishiqiehuan(1, titles);
                        } else {
                            ToastUtils.showToast("?????????????????????IMSI");
                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                if (tf1.equals("FDD")) {
                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
                        if (listmun.size() > 0) {
                            MainUtils.start1SNF(IP1, Constant.SNFFDD);
                            DBManagersaopin dbManagersaopin = null;
                            try {
                                dbManagersaopin = new DBManagersaopin(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//?????????????????????
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("??????????????????");
                                    saopinSend1(saopinBeanList, tf1, yy, context);
                                } else {
                                    ToastUtils.showToast("??????????????????");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else {
                            ToastUtils.showToast("?????????????????????IMSI");
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            }
            if (yy == 4) {
                if (tf1.equals("TDD")) {
                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
                        if (listmun.size() > 0) {
                            String titles = "";
                            if (tf1.equals("TDD")) {
                                titles = "FDD";
                                MainUtils.start1SNF(IP1, Constant.SNFFDD);
                            }
                            if (tf1.equals("FDD")) {
                                titles = "TDD";
                                MainUtils.start1SNF(IP1, Constant.SNFTDD);
                            }
                            viewS.zhishiqiehuan(1, titles);
                        } else {
                            ToastUtils.showToast("?????????????????????IMSI");
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
                if (tf1.equals("FDD")) {
                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
                        if (listmun.size() > 0) {
                            MainUtils.start1SNF(IP1, Constant.SNFFDD);
                            DBManagersaopin dbManagersaopin = null;
                            try {
                                dbManagersaopin = new DBManagersaopin(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//?????????????????????
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("??????????????????");
                                    saopinSend1(saopinBeanList, tf1, yy, context);
                                } else {
                                    ToastUtils.showToast("??????????????????");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else {
                            ToastUtils.showToast("?????????????????????IMSI");
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            }
        }
        //??????2
        if (device == 2) {
            if (yy == 1) {//???????????????
                if (tf2.equals("TDD")) {
                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
                        if (listmun.size() > 0) {
                            MainUtils.start1SNF(IP2, Constant.SNFTDD);
                            DBManagersaopin dbManagersaopin = null;
                            try {
                                dbManagersaopin = new DBManagersaopin(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//?????????????????????
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("??????????????????");
                                    saopinSend2(saopinBeanList, tf2, yy, context);
                                } else {
                                    ToastUtils.showToast("??????????????????");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else {
                            ToastUtils.showToast("?????????????????????IMSI");
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }

                if (tf2.equals("FDD")) {
                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
                        if (listmun.size() > 0) {
                            String titles = "";
                            if (tf2.equals("TDD")) {
                                titles = "FDD";
                                MainUtils.start1SNF(IP2, Constant.SNFFDD);
                            }
                            if (tf2.equals("FDD")) {
                                titles = "TDD";
                                MainUtils.start1SNF(IP2, Constant.SNFTDD);
                            }
                            viewS.zhishiqiehuan(2, titles);
                        } else {
                            ToastUtils.showToast("?????????????????????IMSI");
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }

            }
            if (yy == 2) {
                if (tf2.equals("TDD")) {
                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
                        if (listmun.size() > 0) {
                            String titles = "";
                            if (tf2.equals("TDD")) {
                                titles = "FDD";
                                MainUtils.start1SNF(IP2, Constant.SNFFDD);
                            }
                            if (tf2.equals("FDD")) {
                                titles = "TDD";
                                MainUtils.start1SNF(IP2, Constant.SNFTDD);
                            }
                            viewS.zhishiqiehuan(2, titles);
                        } else {
                            ToastUtils.showToast("?????????????????????IMSI");
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
                if (tf2.equals("FDD")) {
                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
                        if (listmun.size() > 0) {
                            MainUtils.start1SNF(IP2, Constant.SNFFDD);
                            DBManagersaopin dbManagersaopin = null;
                            try {
                                dbManagersaopin = new DBManagersaopin(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//?????????????????????
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("??????????????????");
                                    saopinSend2(saopinBeanList, tf2, yy, context);
                                } else {
                                    ToastUtils.showToast("??????????????????");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else {
                            ToastUtils.showToast("?????????????????????IMSI");
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            }
            if (yy == 3) {

                try {
                    DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                    List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                    List<String> listmun = new ArrayList<>();
                    for (int j = 0; j < dataAll.size(); j++) {
                        if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                            listmun.add(dataAll.get(j).getImsi());
                        }

                    }
                    if (tf2.equals("TDD")) {
                        if (listmun.size() > 0) {

                            String titles = "";
                            if (tf2.equals("TDD")) {
                                titles = "FDD";
                                MainUtils.start1SNF(IP2, Constant.SNFFDD);
                            }
                            if (tf2.equals("FDD")) {
                                titles = "TDD";
                                MainUtils.start1SNF(IP2, Constant.SNFTDD);
                            }
                            viewS.zhishiqiehuan(2, titles);
                        } else {
                            ToastUtils.showToast("?????????????????????IMSI");
                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                    List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                    List<String> listmun = new ArrayList<>();
                    for (int j = 0; j < dataAll.size(); j++) {
                        if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                            listmun.add(dataAll.get(j).getImsi());
                        }

                    }
                    if (tf2.equals("FDD")) {
                        if (listmun.size() > 0) {

                            MainUtils.start1SNF(IP2, Constant.SNFFDD);
                            DBManagersaopin dbManagersaopin = null;
                            try {
                                dbManagersaopin = new DBManagersaopin(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//?????????????????????
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("??????????????????");
                                    saopinSend2(saopinBeanList, tf2, yy, context);
                                } else {
                                    ToastUtils.showToast("??????????????????");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else {
                            ToastUtils.showToast("?????????????????????IMSI");
                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
            if (yy == 4) {
                try {
                    DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                    List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                    List<String> listmun = new ArrayList<>();
                    for (int j = 0; j < dataAll.size(); j++) {
                        if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                            listmun.add(dataAll.get(j).getImsi());
                        }

                    }
                    if (tf2.equals("TDD")) {
                        if (listmun.size() > 0) {

                            String titles = "";
                            if (tf2.equals("TDD")) {
                                titles = "FDD";
                                MainUtils.start1SNF(IP2, Constant.SNFFDD);
                            }
                            if (tf2.equals("FDD")) {
                                titles = "TDD";
                                MainUtils.start1SNF(IP2, Constant.SNFTDD);
                            }
                            viewS.zhishiqiehuan(2, titles);
                        } else {
                            ToastUtils.showToast("?????????????????????IMSI");
                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                    List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                    List<String> listmun = new ArrayList<>();
                    for (int j = 0; j < dataAll.size(); j++) {
                        if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                            listmun.add(dataAll.get(j).getImsi());
                        }

                    }
                    if (tf2.equals("FDD")) {
                        if (listmun.size() > 0) {

                            MainUtils.start1SNF(IP2, Constant.SNFFDD);
                            DBManagersaopin dbManagersaopin = null;
                            try {
                                dbManagersaopin = new DBManagersaopin(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//?????????????????????
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("??????????????????");
                                    saopinSend2(saopinBeanList, tf2, yy, context);
                                } else {
                                    ToastUtils.showToast("??????????????????");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else {
                            ToastUtils.showToast("?????????????????????IMSI");
                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        }
    }

    @Override
    public void spbuilsshow(Context context, int device, int yy, String tf1, String tf2) {
        if (device == 1) {
            if (yy == 1) {//???????????????
                if (tf1.equals("TDD")) {

                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
//                        if (listmun.size() > 0) {
                        MainUtils.start1SNF(IP1, Constant.SNFTDD);
                        DBManagersaopin dbManagersaopin = null;
                        try {
                            dbManagersaopin = new DBManagersaopin(context);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        try {
                            List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//?????????????????????
                            if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("??????????????????");
                                saopinSend1(saopinBeanList, tf1, yy, context);
                            } else {
                                ToastUtils.showToast("??????????????????");
                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
//                        } else {
//                            ToastUtils.showToast("?????????????????????IMSI");
//                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }


                }

                if (tf1.equals("FDD")) {


                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
//                        if (listmun.size() > 0) {
                        String titles = "";
                        if (tf1.equals("TDD")) {
                            titles = "FDD";
                            MainUtils.start1SNF(IP1, Constant.SNFFDD);
                        }
                        if (tf1.equals("FDD")) {
                            titles = "TDD";
                            MainUtils.start1SNF(IP1, Constant.SNFTDD);
                        }
                        viewS.zhishiqiehuan(1, titles);
//                        } else {
//                            ToastUtils.showToast("?????????????????????IMSI");
//                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }

            }
            if (yy == 2) {
                if (tf1.equals("TDD")) {
                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
//                        if (listmun.size() > 0) {
                        String titles = "";
                        if (tf1.equals("TDD")) {
                            titles = "FDD";
                            MainUtils.start1SNF(IP1, Constant.SNFFDD);
                        }
                        if (tf1.equals("FDD")) {
                            titles = "TDD";
                            MainUtils.start1SNF(IP1, Constant.SNFTDD);
                        }
                        viewS.zhishiqiehuan(1, titles);
//                        } else {
//                            ToastUtils.showToast("?????????????????????IMSI");
//                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
                if (tf1.equals("FDD")) {

                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
//                        if (listmun.size() > 0) {
                        MainUtils.start1SNF(IP1, Constant.SNFFDD);
                        DBManagersaopin dbManagersaopin = null;
                        try {
                            dbManagersaopin = new DBManagersaopin(context);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        try {
                            List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//?????????????????????
                            if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("??????????????????");
                                saopinSend1(saopinBeanList, tf1, yy, context);
                            } else {
                                ToastUtils.showToast("??????????????????");
                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
//                        } else {
//                            ToastUtils.showToast("?????????????????????IMSI");
//                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            }
            if (yy == 3) {
                try {
                    DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                    List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                    List<String> listmun = new ArrayList<>();
                    for (int j = 0; j < dataAll.size(); j++) {
                        if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                            listmun.add(dataAll.get(j).getImsi());
                        }

                    }
                    if (tf1.equals("TDD")) {
//                        if (listmun.size() > 0) {

                        String titles = "";
                        if (tf1.equals("TDD")) {
                            titles = "FDD";
                            MainUtils.start1SNF(IP1, Constant.SNFFDD);
                        }
                        if (tf1.equals("FDD")) {
                            titles = "TDD";
                            MainUtils.start1SNF(IP1, Constant.SNFTDD);
                        }
                        viewS.zhishiqiehuan(1, titles);
//                        } else {
//                            ToastUtils.showToast("?????????????????????IMSI");
//                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                if (tf1.equals("FDD")) {
                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
//                        if (listmun.size() > 0) {
                        MainUtils.start1SNF(IP1, Constant.SNFFDD);
                        DBManagersaopin dbManagersaopin = null;
                        try {
                            dbManagersaopin = new DBManagersaopin(context);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        try {
                            List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//?????????????????????
                            if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("??????????????????");
                                saopinSend1(saopinBeanList, tf1, yy, context);
                            } else {
                                ToastUtils.showToast("??????????????????");
                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
//                        } else {
//                            ToastUtils.showToast("?????????????????????IMSI");
//                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            }
            if (yy == 4) {
                if (tf1.equals("TDD")) {
                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
//                        if (listmun.size() > 0) {
                        String titles = "";
                        if (tf1.equals("TDD")) {
                            titles = "FDD";
                            MainUtils.start1SNF(IP1, Constant.SNFFDD);
                        }
                        if (tf1.equals("FDD")) {
                            titles = "TDD";
                            MainUtils.start1SNF(IP1, Constant.SNFTDD);
                        }
                        viewS.zhishiqiehuan(1, titles);
//                        } else {
//                            ToastUtils.showToast("?????????????????????IMSI");
//                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
                if (tf1.equals("FDD")) {
                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
//                        if (listmun.size() > 0) {
                        MainUtils.start1SNF(IP1, Constant.SNFFDD);
                        DBManagersaopin dbManagersaopin = null;
                        try {
                            dbManagersaopin = new DBManagersaopin(context);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        try {
                            List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//?????????????????????
                            if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("??????????????????");
                                saopinSend1(saopinBeanList, tf1, yy, context);
                            } else {
                                ToastUtils.showToast("??????????????????");
                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
//                        } else {
//                            ToastUtils.showToast("?????????????????????IMSI");
//                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            }
        }
        //??????2
        if (device == 2) {
            if (yy == 1) {//???????????????
                if (tf2.equals("TDD")) {
                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
//                        if (listmun.size() > 0) {
                        MainUtils.start1SNF(IP2, Constant.SNFTDD);
                        DBManagersaopin dbManagersaopin = null;
                        try {
                            dbManagersaopin = new DBManagersaopin(context);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        try {
                            List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//?????????????????????
                            if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("??????????????????");
                                saopinSend2(saopinBeanList, tf2, yy, context);
                            } else {
                                ToastUtils.showToast("??????????????????");
                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
//                        } else {
//                            ToastUtils.showToast("?????????????????????IMSI");
//                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }

                if (tf2.equals("FDD")) {
                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
//                        if (listmun.size() > 0) {
                        String titles = "";
                        if (tf2.equals("TDD")) {
                            titles = "FDD";
                            MainUtils.start1SNF(IP2, Constant.SNFFDD);
                        }
                        if (tf2.equals("FDD")) {
                            titles = "TDD";
                            MainUtils.start1SNF(IP2, Constant.SNFTDD);
                        }
                        viewS.zhishiqiehuan(2, titles);
//                        } else {
//                            ToastUtils.showToast("?????????????????????IMSI");
//                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }

            }
            if (yy == 2) {
                if (tf2.equals("TDD")) {
                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
//                        if (listmun.size() > 0) {
                        String titles = "";
                        if (tf2.equals("TDD")) {
                            titles = "FDD";
                            MainUtils.start1SNF(IP2, Constant.SNFFDD);
                        }
                        if (tf2.equals("FDD")) {
                            titles = "TDD";
                            MainUtils.start1SNF(IP2, Constant.SNFTDD);
                        }
                        viewS.zhishiqiehuan(2, titles);
//                        } else {
//                            ToastUtils.showToast("?????????????????????IMSI");
//                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
                if (tf2.equals("FDD")) {
                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
//                        if (listmun.size() > 0) {
                        MainUtils.start1SNF(IP2, Constant.SNFFDD);
                        DBManagersaopin dbManagersaopin = null;
                        try {
                            dbManagersaopin = new DBManagersaopin(context);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        try {
                            List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//?????????????????????
                            if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("??????????????????");
                                saopinSend2(saopinBeanList, tf2, yy, context);
                            } else {
                                ToastUtils.showToast("??????????????????");
                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
//                        } else {
//                            ToastUtils.showToast("?????????????????????IMSI");
//                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            }
            if (yy == 3) {

                try {
                    DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                    List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                    List<String> listmun = new ArrayList<>();
                    for (int j = 0; j < dataAll.size(); j++) {
                        if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                            listmun.add(dataAll.get(j).getImsi());
                        }

                    }
//                    if (tf2.equals("TDD")) {
                    if (listmun.size() > 0) {

                        String titles = "";
                        if (tf2.equals("TDD")) {
                            titles = "FDD";
                            MainUtils.start1SNF(IP2, Constant.SNFFDD);
                        }
                        if (tf2.equals("FDD")) {
                            titles = "TDD";
                            MainUtils.start1SNF(IP2, Constant.SNFTDD);
                        }
                        viewS.zhishiqiehuan(2, titles);
//                        } else {
//                            ToastUtils.showToast("?????????????????????IMSI");
//                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                    List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                    List<String> listmun = new ArrayList<>();
                    for (int j = 0; j < dataAll.size(); j++) {
                        if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                            listmun.add(dataAll.get(j).getImsi());
                        }

                    }
                    if (tf2.equals("FDD")) {
//                        if (listmun.size() > 0) {

                        MainUtils.start1SNF(IP2, Constant.SNFFDD);
                        DBManagersaopin dbManagersaopin = null;
                        try {
                            dbManagersaopin = new DBManagersaopin(context);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        try {
                            List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//?????????????????????
                            if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("??????????????????");
                                saopinSend2(saopinBeanList, tf2, yy, context);
                            } else {
                                ToastUtils.showToast("??????????????????");
                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
//                        } else {
//                            ToastUtils.showToast("?????????????????????IMSI");
//                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
            if (yy == 4) {
                try {
                    DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                    List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                    List<String> listmun = new ArrayList<>();
                    for (int j = 0; j < dataAll.size(); j++) {
                        if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                            listmun.add(dataAll.get(j).getImsi());
                        }

                    }
                    if (tf2.equals("TDD")) {
//                        if (listmun.size() > 0) {

                        String titles = "";
                        if (tf2.equals("TDD")) {
                            titles = "FDD";
                            MainUtils.start1SNF(IP2, Constant.SNFFDD);
                        }
                        if (tf2.equals("FDD")) {
                            titles = "TDD";
                            MainUtils.start1SNF(IP2, Constant.SNFTDD);
                        }
                        viewS.zhishiqiehuan(2, titles);
//                        } else {
//                            ToastUtils.showToast("?????????????????????IMSI");
//                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                    List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                    List<String> listmun = new ArrayList<>();
                    for (int j = 0; j < dataAll.size(); j++) {
                        if (dataAll.get(j).getYy().equals("??????") && dataAll.get(j).isCheckbox() == true) {
                            listmun.add(dataAll.get(j).getImsi());
                        }

                    }
                    if (tf2.equals("FDD")) {
//                        if (listmun.size() > 0) {

                        MainUtils.start1SNF(IP2, Constant.SNFFDD);
                        DBManagersaopin dbManagersaopin = null;
                        try {
                            dbManagersaopin = new DBManagersaopin(context);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        try {
                            List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//?????????????????????
                            if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("??????????????????");
                                saopinSend2(saopinBeanList, tf2, yy, context);
                            } else {
                                ToastUtils.showToast("??????????????????");
                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
//                        } else {
//                            ToastUtils.showToast("?????????????????????IMSI");
//                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        }
    }

    /**
     * ??????????????????
     *
     * @param context
     * @param device
     * @param tf
     * @param down
     */
    @Override
    public void saopinjianlixiaoqu(Context context, int device, String tf, String down) {
        if (device == 1) {
            if (tf.equals("?????????")) {

                return;
            }
            String yy = "";
            List<AddPararBean> sendListBlack = null;
            List<PinConfigBean> pinConfigBeans = null;
            sendListBlack = new ArrayList<>();
            DBManagerPinConfig dbManagerPinConfig = null;
            try {
                dbManagerPinConfig = new DBManagerPinConfig(context);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(down)); //?????????????????????
                yy = pinConfigBeans.get(0).getYy();
                Log.d("qwqea", "saopinjianlixiaoqu: " + yy);
            } catch (SQLException e) {
                e.printStackTrace();
            }
//            Log.d(TAG, "sendBlackList:?????? ");
            List<AddPararBean> dataAll = null;
            try {
                DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                dataAll = dbManagerAddParam.getDataAll();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            List<AddPararBean> listImsiListTrue = new ArrayList<>();
            if (dataAll.size() > 0) {
                for (int i = 0; i < dataAll.size(); i++) {
                    if (dataAll.get(i).isCheckbox() == true) {
                        listImsiListTrue.add(dataAll.get(i));
                    }

                }
            }
            if (listImsiListTrue.size() > 0 && listImsiListTrue != null) {
                for (int i = 0; i < listImsiListTrue.size(); i++) {
                    if (listImsiListTrue.get(i).getYy().equals(yy)) {
                        sendListBlack.add(listImsiListTrue.get(i));
                    }
                }
            }

            if (sendListBlack.size() > 0 && sendListBlack != null) {
//                Log.d(TAG, "sendBlackList: " + sendListBlack);
                if (sendListBlack.size() > 20) {
                    ToastUtils.showToast("????????????????????????????????????20");
                } else {
                    sendBlackListRun(sendListBlack, tf, down, context, listImsiListTrue);
                }
//
            } else {
                viewS.MesageV(1);
                ToastUtils.showToast("?????????????????????IMSI");
            }
        }

        //??????2
        if (device == 2) {
            if (tf.equals("?????????")) {

                return;
            }
            String yy = "";
            List<AddPararBean> sendListBlack = null;
            List<PinConfigBean> pinConfigBeans = null;
            sendListBlack = new ArrayList<>();
            DBManagerPinConfig dbManagerPinConfig = null;
            try {
                dbManagerPinConfig = new DBManagerPinConfig(context);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(down)); //?????????????????????
                yy = pinConfigBeans.get(0).getYy();
            } catch (SQLException e) {
                e.printStackTrace();
            }
//            Log.d(TAG, "sendBlackList:?????? ");
            List<AddPararBean> dataAll = null;
            try {
                DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                dataAll = dbManagerAddParam.getDataAll();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            List<AddPararBean> listImsiListTrue = new ArrayList<>();
            if (dataAll.size() > 0) {
                for (int i = 0; i < dataAll.size(); i++) {
                    if (dataAll.get(i).isCheckbox() == true) {
                        listImsiListTrue.add(dataAll.get(i));
                    }

                }
            }
            if (listImsiListTrue.size() > 0 && listImsiListTrue != null) {
                for (int i = 0; i < listImsiListTrue.size(); i++) {
                    if (listImsiListTrue.get(i).getYy().equals(yy)) {
                        sendListBlack.add(listImsiListTrue.get(i));
                    }
                }
            }

            if (sendListBlack.size() > 0 && sendListBlack != null) {
//                Log.d(TAG, "sendBlackList: " + sendListBlack);
                if (sendListBlack.size() > 20) {
                    ToastUtils.showToast("????????????????????????????????????20");
                } else {
                    sendBlackListRun2(sendListBlack, tf, down, context, listImsiListTrue);
                }
//
            } else {
                viewS.MesageV(2);
                ToastUtils.showToast("?????????????????????IMSI");
            }
        }
    }

    @Override
    public void sendsaopin(Context context, int device, int yy) {
        if (device == 1) {
            List<Integer> list = new ArrayList<>();
//        Log.d(TAG, "AsaopinSend1: " + saopinBeanList + "??????" + zs);
            DBManagersaopin dbManagersaopin = null;
            try {
                dbManagersaopin = new DBManagersaopin(context);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            List<SaopinBean> saopinBeanList = null;
            try {
                saopinBeanList = dbManagersaopin.getStudent();
//            Log.d(TAG, "AsaopinSend1: " + saopinBeanList + "??????" + zs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (saopinBeanList.size() > 0) {

                for (int i = 0; i < saopinBeanList.size(); i++) {
                    if (saopinBeanList.get(i).getYy().equals(MyUtils.YYname(yy)) && saopinBeanList.get(i).getType() == 1) {
                        list.add(saopinBeanList.get(i).getDown());
                    }
                }

                if (list.size() > 0) {
                    if (list.size() > 10) {
                        ToastUtils.showToast("??????????????????10???");
                    } else {
                        MainUtils.sendspSocket(list, IP1);
//                    Log.d(TAG, "saopinSend1: " + list);
                    }

                } else {
//                ToastUtils.showToast("????????????" + zs + "?????????");

                }

            } else {
                ToastUtils.showToast("??????????????????");
            }
        }
        if (device == 2) {
            List<Integer> list = new ArrayList<>();
//        Log.d(TAG, "AsaopinSend1: " + saopinBeanList + "??????" + zs);
            DBManagersaopin dbManagersaopin = null;
            try {
                dbManagersaopin = new DBManagersaopin(context);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            List<SaopinBean> saopinBeanList = null;
            try {
                saopinBeanList = dbManagersaopin.getStudent();
//            Log.d(TAG, "AsaopinSend1: " + saopinBeanList + "??????" + zs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (saopinBeanList.size() > 0) {

                for (int i = 0; i < saopinBeanList.size(); i++) {
                    if (saopinBeanList.get(i).getYy().equals(MyUtils.YYname(yy)) && saopinBeanList.get(i).getType() == 1) {
                        list.add(saopinBeanList.get(i).getDown());
                    }
                }
                Log.d("listaaa", "sendsaopin: " + list.toString());
                if (list.size() > 0) {
                    if (list.size() > 10) {
                        ToastUtils.showToast("??????????????????10???");
                    } else {
                        MainUtils.sendspSocket(list, IP2);
//                    Log.d(TAG, "saopinSend1: " + list);
                    }

                } else {
//                ToastUtils.showToast("????????????" + zs + "?????????");

                }

            } else {
                ToastUtils.showToast("??????????????????");
            }
        }
    }

    @Override
    public void setRyimsidw(Context context, RecyclerView ry_zhenma, Message msg, RyZmAdapterdw ryZmAdapter, EditText et_zhenmasearchdw, TextView tv_searchNumdw) {
        String imsi = msg.getData().getString("imsi");
        Log.d("Qdown1", "handleMessage: " + "imsi===" + imsi + "----");

//                Log.d("Qdown1", "handleMessage: " + "imsi===" + imsi + "----" + DOWNPIN1);
        DBManagerZM dbManagerZM = null;
        try {
            dbManagerZM = new DBManagerZM(context);
            ZmBean zmBean = new ZmBean();
            zmBean.setImsi(imsi);
            if (msg.getData().getString("sb").equals("1")) {
                if (!TextUtils.isEmpty(DOWNPIN1)) {
                    zmBean.setDown(DOWNPIN1);
                    zmBean.setMaintype("0");
                    zmBean.setSb(msg.getData().getString("sb"));
                    zmBean.setTime(msg.getData().getString("time"));
                    zmBean.setDatatime(msg.getData().getString("datetime"));
                    int i = dbManagerZM.insertAddZmBean(zmBean);
                    Log.d("DOWNPIN1TAG", "setRyimsidw:fkong .");
                } else {
                    Log.d("DOWNPIN1TAG", "setRyimsidw:kong .");
                }

            }
            if (msg.getData().getString("sb").equals("2")) {
                if (!TextUtils.isEmpty(DOWNPIN2)) {
                    zmBean.setDown(DOWNPIN2);
                    zmBean.setMaintype("0");
                    zmBean.setSb(msg.getData().getString("sb"));
                    zmBean.setTime(msg.getData().getString("time"));
                    zmBean.setDatatime(msg.getData().getString("datetime"));
                    int i = dbManagerZM.insertAddZmBean(zmBean);
                    Log.d("DOWNPIN1TAG", "setRyimsidw:fkong .");

                } else {
                    Log.d("DOWNPIN1TAG", "setRyimsidw:kong .");
                }

            }

            //?????????????????????imsi??????
            List<ZmBean> zmBeanss = dbManagerZM.getDataAll();
            List<ZmBean> zmBeans = new ArrayList<>();
            for (int j = 0; j < zmBeanss.size(); j++) {
                if (zmBeanss.get(j).getMaintype().equals("0")) {
                    zmBeans.add(zmBeanss.get(j));
                }
            }
            List<Integer> listsize = new ArrayList<>();
            for (int i = 0; i < zmBeans.size(); i++) {
                listsize.add(i + 1);

            }
            Log.d("dbManagerZM", "handleMessage: " + i);
            Log.d("dbManagerZM", "handleMessage:size " + zmBeans.toString());
            ryZmAdapter = new RyZmAdapterdw(context, zmBeans, listsize);//list???imsi?????????????????????
//            if (et_zhenmasearchdw.getText().length() == 0) {
//                        ry_zhenma.setAdapter(ryZmAdapter);
            if (zmBeans.size() > 6) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                linearLayoutManager.setStackFromEnd(true);//recyview?????????????????????
                ry_zhenma.setLayoutManager(linearLayoutManager);
                tv_searchNumdw.setText("(" + zmBeans.size() + ")");
            } else {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                linearLayoutManager.setStackFromEnd(false);//recyview?????????????????????
                ry_zhenma.setLayoutManager(linearLayoutManager);
                tv_searchNumdw.setText("(" + zmBeans.size() + ")");
            }
            ryZmAdapter = new RyZmAdapterdw(context, zmBeans, listsize);//list???imsi?????????????????????
            ry_zhenma.setAdapter(ryZmAdapter);
//            }

//                        ry_zhenma.scrollToPosition(zmBeans.size());
            if (msg.getData().getString("zb").equals("1")) {
                //???????????????????????????
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setryGK(Context context, RecyclerView recyclerView, String down1, String down2, RyZmAdapterGk ryZmAdapterGk, Message msg) {
        String imsi = msg.getData().getString("imsi");
        Log.d("Qdown1", "handleMessage: " + "imsi===" + imsi + "----");

//                Log.d("Qdown1", "handleMessage: " + "imsi===" + imsi + "----" + DOWNPIN1);
        DBManagerZMGK dbManagerZM = null;
        try {
            dbManagerZM = new DBManagerZMGK(context);
            ZmBeanGK zmBean = new ZmBeanGK();
            zmBean.setImsi(imsi);
            if (msg.getData().getString("sb").equals("1")) {
                if (!TextUtils.isEmpty(DOWNPIN1)) {
                    zmBean.setDown(DOWNPIN1);
                    zmBean.setMaintype("0");
                    zmBean.setSb(msg.getData().getString("sb"));
                    zmBean.setTime(msg.getData().getString("time"));
                    zmBean.setDatatime(msg.getData().getString("datetime"));
                    int i = dbManagerZM.insertAddZmBean(zmBean);
                    Log.d("DOWNPIN1TAG", "setRyimsidw:fkong .");
                } else {
                    Log.d("DOWNPIN1TAG", "setRyimsidw:kong .");
                }

            }
            if (msg.getData().getString("sb").equals("2")) {
                if (!TextUtils.isEmpty(DOWNPIN2)) {
                    zmBean.setDown(DOWNPIN2);
                    zmBean.setMaintype("0");
                    zmBean.setSb(msg.getData().getString("sb"));
                    zmBean.setTime(msg.getData().getString("time"));
                    zmBean.setDatatime(msg.getData().getString("datetime"));
                    int i = dbManagerZM.insertAddZmBean(zmBean);
                    Log.d("DOWNPIN1TAG", "setRyimsidw:fkong .");

                } else {
                    Log.d("DOWNPIN1TAG", "setRyimsidw:kong .");
                }
            }

            //?????????????????????imsi??????
            List<ZmBeanGK> zmBeanss = dbManagerZM.getDataAll();
            List<ZmBeanGK> zmBeans = new ArrayList<>();
            for (int j = 0; j < zmBeanss.size(); j++) {
                if (zmBeanss.get(j).getMaintype().equals("0")) {
                    zmBeans.add(zmBeanss.get(j));
                }
            }
            List<Integer> listsize = new ArrayList<>();
            for (int i = 0; i < zmBeans.size(); i++) {
                listsize.add(i + 1);

            }
            Log.d("dbManagerZM", "handleMessage: " + i);
            Log.d("dbManagerZM", "handleMessage:size " + zmBeans.toString());
//            ryZmAdapterGk = new RyZmAdapterGk(context, zmBeans, listsize);//list???imsi?????????????????????
//            if (et_zhenmasearchdw.getText().length() == 0) {
//                        ry_zhenma.setAdapter(ryZmAdapter);

//            ryZmAdapterGk = new RyZmAdapterGk(context, zmBeans, listsize);//list???imsi?????????????????????
//            recyclerView.setAdapter(ryZmAdapterGk);

            //?????? split ????????????????????????????????????
            List<String> list = new ArrayList<>();
            for (int i = 0; i < zmBeanss.size(); i++) {
                list.add(zmBeanss.get(i).getImsi());
            }
            Log.e("inittongjiIMsi", "init: " + list.toString());
            List<Map<String, Integer>> list1 = new ArrayList<>();
            Map<String, Integer> map = new HashMap<>();
            for (int i = 0; i < list.size(); i++) {
                Integer integer = map.get(list.get(i));
                map.put(list.get(i), integer == null ? 1 : integer + 1);
            }
            // ?????????
            Set<String> res = map.keySet();
            for (String im : res) {
                System.out.println("a?????????" + im + "??????" + map.get(im));
            }
            List<ZmBeanGKTongji> list2 = new ArrayList<>();
            ZmBeanGKTongji zmBeanGKTongji;
            Set<Map.Entry<String, Integer>> set = map.entrySet();
            for (Map.Entry<String, Integer> entry : set) {
                System.out.println("??????????????????" + entry.getKey() + "---" + entry.getValue());
                if (entry.getValue() > 1) {
                    zmBeanGKTongji = new ZmBeanGKTongji();
                    zmBeanGKTongji.setImsi(entry.getKey());
                    zmBeanGKTongji.setNum(entry.getValue() + "");
//                zmBeanGKTongji.setTime(time);
                    list2.add(zmBeanGKTongji);

                }


            }

            Log.d("TAGlisttongji", "init: " + list2.toString());
            for (int i = 0; i < zmBeanss.size(); i++) {
                for (int j = 0; j < list2.size(); j++) {
                    if (zmBeanss.get(i).getImsi().equals(list2.get(j).getImsi())) {
                        list2.get(j).setDown(zmBeanss.get(i).getDown());
                        list2.get(j).setSb(zmBeanss.get(i).getSb());
                        list2.get(j).setTime(zmBeanss.get(i).getTime());
                        list2.get(j).setDatatime(zmBeanss.get(i).getDatatime());
                    }
                }
            }
            Log.d("TAGlisttongjichuili", "init: " + list2.toString());

            ryZmAdapterGk = new RyZmAdapterGk(context, list2, listsize,null);//list???imsi?????????????????????
            recyclerView.setAdapter(ryZmAdapterGk);
            if (list2.size() > 10) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
//                linearLayoutManager.setStackFromEnd(true);//recyview?????????????????????
                recyclerView.setLayoutManager(linearLayoutManager);
//                tv_searchNumdw.setText("(" + zmBeans.size() + ")");
            } else {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
//                linearLayoutManager.setStackFromEnd(false);//recyview?????????????????????
                recyclerView.setLayoutManager(linearLayoutManager);
//                tv_searchNumdw.setText("(" + zmBeans.size() + ")");
            }
            //?????? IMSI ???????????????
        } catch (SQLException e) {
            e.printStackTrace();

            Log.d("errorsetryGK", "setryGK: " + e.getMessage());
        }
    }

    @Override
    public void setRyimsizm(Context context, RecyclerView recyclerView, Message msg, RyZmAdapter ryZmAdapter, EditText et_zhenmasearchdw, TextView tv_searchNumdw, String DOWN1, String DOWN2, String startdatazm1, String startdatazm2, String stopdata) {

    }

    @SuppressLint("NewApi")
    @Override
    public void setIMSInengliangzhi(Context context, int device, String tf, DecimalFormat dfBaoshu, TextView sb1_jl, boolean laba, Message msg, TextToSpeech textToSpeech) {
        if (device == 1) {
            String sb1jl = msg.getData().getString("sb1jl");
//        Log.d(TAG, "sb1jlhandleMessage: " + sb1jl + "---");
            if (!TextUtils.isEmpty(sb1jl)) {
                String format = "";
                if (TextUtils.isEmpty(tf)) {
                    return;
                }
                if (tf.equals("TDD")) {
                    double v = Double.parseDouble(sb1jl);
                    double s = v / 110 * 1000;
                    format = dfBaoshu.format(s);
//                Log.d(TAG, "dfBaoshuhandleMessage: " + format);
                } else {
                    double v = Double.parseDouble(sb1jl);
                    double s = v / 164 * 1000;
                    format = dfBaoshu.format(s);
//                Log.d(TAG, "dfBaoshuhandleMessage: " + format);
                }


                sb1_jl.setText(format);
                if (laba == true) {
                    startAuto(format, true, textToSpeech);
                }
                viewS.quxian(format, 1);

            }
        }
        if (device == 2) {
            String sb2jl = msg.getData().getString("sb1j2");
//        Log.d(TAG, "sb1jlhandleMessage: " + sb1jl + "---");
            if (!TextUtils.isEmpty(sb2jl)) {
                String format = "";
                if (TextUtils.isEmpty(tf)) {
                    return;
                }
                if (tf.equals("TDD")) {
                    double v = Double.parseDouble(sb2jl);
                    double s = v / 110 * 1000;
                    format = dfBaoshu.format(s);
//                Log.d(TAG, "dfBaoshuhandleMessage: " + format);
                } else {
                    double v = Double.parseDouble(sb2jl);
                    double s = v / 164 * 1000;
                    format = dfBaoshu.format(s);
//                Log.d(TAG, "dfBaoshuhandleMessage: " + format);
                }


                sb1_jl.setText(format);
                if (laba == true) {
                    startAuto(format, true, textToSpeech);
                }
                viewS.quxian(format, 2);

            }
        }
    }

    @Override
    public void setIMSIshow2(Message message, TextView tv_imsi1_dw) {

        String imsi1 = message.getData().getString("imsi2");
        tv_imsi1_dw.setText(imsi1);

    }

    private void startAuto(String data, boolean b, TextToSpeech textToSpeech) {
        textToSpeech.setPitch(1.f);
        Log.d("wpnqq", "startAuto: " + b);

        // ????????????
        textToSpeech.setSpeechRate(8.01f);
        textToSpeech.speak(data,//??????????????????????????????????????????????????????
                TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void setIMSIshow(Message msg, TextView tv_imsi1_dw) {
        String imsi1 = msg.getData().getString("imsi1");
        tv_imsi1_dw.setText(imsi1);
    }

    @SuppressLint("NewApi")
    @Override
    public void setlaba(Context context, ImageView imageView, int device, boolean laba) {
        if (device == 1) {
            if (laba) {
                laba = false;
                imageView.setBackground(context.getResources().getDrawable(R.mipmap.laba_gray));
                viewS.labaup(device, laba);
            } else {
                laba = true;
                imageView.setBackground(context.getResources().getDrawable(R.mipmap.laba_green));
                viewS.labaup(device, laba);
            }
        }
        if (device == 2) {
            if (laba) {
                laba = false;
                imageView.setBackground(context.getResources().getDrawable(R.mipmap.laba_gray));
                viewS.labaup(device, laba);
            } else {
                laba = true;
                imageView.setBackground(context.getResources().getDrawable(R.mipmap.laba_red));
                viewS.labaup(device, laba);
            }
        }
    }

    /**
     * @param context
     * @param qx
     * @param zm
     * @param type
     * @param nengliangquxiantu
     * @param zhenmajilu
     */
    @Override
    public void setnengliangAndzhenma(Context context, ImageButton qx, ImageButton zm, int type, boolean nengliangquxiantu, boolean zhenmajilu) {

    }

    /**
     * ??????????????????
     *
     * @param context
     * @param tf1
     * @param tf2
     * @param sb1
     * @param sb2
     */
    @Override
    public void setZMStart(final Context context, String tf1, String tf2, String sb1, String sb2, int type) {
        DBManagerLunxun dbManagerLunxun = null;
        if (type == 1) {
            if (!sb1.equals("??????")) {
                ToastUtils.showToast("??????1??????????????????");
                return;
            }
            if (!sb2.equals("??????")) {
                ToastUtils.showToast("??????2??????????????????");
                return;
            }
            if (TextUtils.isEmpty(tf1)) {
                ToastUtils.showToast("??????1?????????");

                return;
            }
            if (TextUtils.isEmpty(tf2)) {
                ToastUtils.showToast("??????2?????????");

                return;
            }
            Log.d("nzq", "setZMStart: " + tf1 + "--" + tf2);
            if (tf1.equals("TDD") && tf2.equals("TDD")) {
                setzmlistStartTDD(context);
            }

            if (tf1.equals("TDD") && tf2.equals("TDD")) {
                Log.d("qqqqsadvaa", "setZMStart: ");
                return;
            }
            if (tf1.equals("FDD") && tf2.equals("FDD")) {
                setzmlistStartFDD(context);
            }

            if (tf1.equals("FDD") && tf2.equals("FDD")) {
                Log.d("qqqqsadvaa", "setZMStart: ");
                return;
            }
            if (tf1.equals("FDD") && tf2.equals("TDD")) {
                dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
                inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
                TextView tv_title = inflate.findViewById(R.id.tv_title);
                Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
                tv_title.setText("??????1?????????2????????????????????????????");
                bt_confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        dialog.cancel();
                        viewS.zhishiqiehuanZm(3);
                        setzmlistStart(context);

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
                //????????????Activity???????????????
                Window dialogWindow = dialog.getWindow();
                //??????Dialog?????????????????????
                dialogWindow.setGravity(Gravity.CENTER);
                //?????????????????????
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//                           lp.y = 20;//??????Dialog?????????????????????
//                          ????????????????????????
                dialogWindow.setAttributes(lp);
                return;
            }
            Log.d("qqqqsadvaa", "setZMStart: ");
            if (!tf1.equals("TDD") || !tf2.equals("FDD")) {
                if (!tf1.equals("TDD") && !tf2.equals("FDD")) {//???????????????????????????

                    dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
                    inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
                    TextView tv_title = inflate.findViewById(R.id.tv_title);
                    Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
                    tv_title.setText("??????1?????????2????????????????????????????");
                    bt_confirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                            dialog.cancel();
                            viewS.zhishiqiehuanZm(3);
                            setzmlistStart(context);

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
                    //????????????Activity???????????????
                    Window dialogWindow = dialog.getWindow();
                    //??????Dialog?????????????????????
                    dialogWindow.setGravity(Gravity.CENTER);
                    //?????????????????????
                    WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//                           lp.y = 20;//??????Dialog?????????????????????
//                          ????????????????????????
                    dialogWindow.setAttributes(lp);
//                    dialog.show();//???????????????

                    return;
                }
                if (!tf1.equals("TDD")) {//???????????????????????????
                    dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
                    inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
                    TextView tv_title = inflate.findViewById(R.id.tv_title);
                    Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
                    tv_title.setText("??????1????????????????????????????");
                    bt_confirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                            dialog.cancel();
                            viewS.zhishiqiehuanZm(1);
                            setzmlistStart(context);

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
                    //????????????Activity???????????????
                    Window dialogWindow = dialog.getWindow();
                    //??????Dialog?????????????????????
                    dialogWindow.setGravity(Gravity.CENTER);
                    //?????????????????????
                    WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//                           lp.y = 20;//??????Dialog?????????????????????
//                          ????????????????????????
                    dialogWindow.setAttributes(lp);
//                    dialog.show();//???????????????
                    return;
                }
                if (!tf2.equals("FDD")) {//???????????????????????????
                    dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
                    inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
                    TextView tv_title = inflate.findViewById(R.id.tv_title);
                    Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
                    tv_title.setText("??????2????????????????????????????");
                    bt_confirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            dialog.dismiss();
                            dialog.cancel();
                            viewS.zhishiqiehuanZm(2);
                            setzmlistStart(context);
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
                    //????????????Activity???????????????
                    Window dialogWindow = dialog.getWindow();
                    //??????Dialog?????????????????????
                    dialogWindow.setGravity(Gravity.CENTER);
                    //?????????????????????
                    WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//                           lp.y = 20;//??????Dialog?????????????????????
//                          ????????????????????????
                    dialogWindow.setAttributes(lp);
//                    dialog.show();//???????????????
                    return;
                } else {

                }
            } else {

                setzmlistStart(context);
            }
        }
//
    }

    @Override
    public void setGKtart(final Context context, final String tf1, final String tf2, final String sb1, final String sb2, int GuankongType) {
        if (GuankongType == 1) {//?????????1
            if (TextUtils.isEmpty(tf1)) {
                ToastUtils.showToast("??????1?????????");
                return;
            }
            if (TextUtils.isEmpty(tf2)) {
                ToastUtils.showToast("??????2?????????");
                return;
            }

            if (!sb1.equals("??????")) {
                ToastUtils.showToast("??????1??????????????????");
                return;
            }
            if (!sb2.equals("??????")) {
                ToastUtils.showToast("??????2??????????????????");
                return;
            }
            Log.d("setGKtart", "setGKtart: ");
            //---------------------------------???????????????????????????
            dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
            inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
            TextView tv_title = inflate.findViewById(R.id.tv_title);
            Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
            tv_title.setText("??????????????????????");
            bt_confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("setGKtart", "setGKtart:?????????????????????? ");
                    try {
                        DBManagerAddParamWhite dbManagerAddParamA = new DBManagerAddParamWhite(context);
                        List<AddPararBeanWhite> dataAlla = dbManagerAddParamA.getDataAll();
                        List<AddPararBeanWhite> listImsiListTrue = new ArrayList<>();
                        Log.d("setGKtartaddPararBeans", "???????????????--init: " + dataAlla.toString());

                        if (dataAlla.size() > 0) {
                            for (int i = 0; i < dataAlla.size(); i++) {
                                if ( dataAlla.get(i).isCheckbox() == true) {
                                    dataAlla.get(i).setSb("");
                                    listImsiListTrue.add(dataAlla.get(i));
                                }
                            }
                            List<Integer> list1size = new ArrayList<>();
                            if (listImsiListTrue.size() > 0) {
                                for (int i = 1; i < listImsiListTrue.size() + 1; i++) {
                                    list1size.add(i);
                                }

                                List<AddPararBeanWhite> sendwhitelist = new ArrayList<>();
                                for (int j = 0; j < listImsiListTrue.size(); j++) {
                                    if (listImsiListTrue.get(j).getYy().equals("??????")) {
                                        sendwhitelist.add(listImsiListTrue.get(j));
                                    }
                                }
                                Log.d("+sendwhitelist", "onClick: " + sendwhitelist);
                                if (sendwhitelist.size() > 0) {
                                    if (!tf1.equals("TDD")) {
                                        ToastUtils.showToast("??????1???????????????FDD");
                                        viewS.gkqiehuan(tf1, 1);
//                return;
                                    }
                                    if (!tf2.equals("FDD")) {
                                        ToastUtils.showToast("??????2???????????????TDD");
                                        viewS.gkqiehuan(tf2, 2);
//                return;
                                    }
//                                    if (!sb1.equals("??????")) {
////                ToastUtils.showToast("??????1??????????????????");
//                                        return;
//                                    }
//                                    if (!sb2.equals("??????")) {
////                ToastUtils.showToast("??????2??????????????????");
//                                        return;
//                                    }
                                    sendwhiteListRun(sendwhitelist, tf1, tf2, context, listImsiListTrue);//???????????????  2???

                                } else {
                                    if (!tf1.equals("TDD")) {
                                        ToastUtils.showToast("??????1???????????????FDD");
                                        viewS.gkqiehuan(tf1, 1);
//                return;
                                    }
                                    if (!tf2.equals("FDD")) {
                                        ToastUtils.showToast("??????2???????????????TDD");
                                        viewS.gkqiehuan(tf2, 2);
//                return;
                                    }
//                                    if (!sb1.equals("??????")) {
////                ToastUtils.showToast("??????1??????????????????");
//                                        return;
//                                    }
//                                    if (!sb2.equals("??????")) {
////                ToastUtils.showToast("??????2??????????????????");
//                                        return;
//                                    }
                                    sendwhiteListRunDELE(sendwhitelist, tf1, tf2, context, listImsiListTrue);//???????????????  2???

                                }


                            } else {
//                                ToastUtils.showToast("?????????????????????IMSI");

                                List<AddPararBeanWhite> sendwhitelist = new ArrayList<>();
                                sendwhiteListRunDELE(sendwhitelist, tf1, tf2, context, listImsiListTrue);//???????????????  2???

                            }
                        } else {//???????????????
                            if (tf1.equals("TDD")&&tf2.equals("FDD")){
                                List<AddPararBeanWhite> sendwhitelist = new ArrayList<>();
                                sendwhiteListRunDELE(sendwhitelist, tf1, tf2, context, listImsiListTrue);//???????????????  2???
                            }
                            if (!tf1.equals("TDD")) {
                                ToastUtils.showToast("??????1???????????????FDD");
                                viewS.gkqiehuan(tf1, 1);
//                return;
                            }else {

                            }
                            if (!tf2.equals("FDD")) {
                                ToastUtils.showToast("??????2???????????????TDD");
                                viewS.gkqiehuan(tf2, 2);
//                return;
                            }else {

                            }
                        }
                        Log.d("setGKtartaddPararBeans", "?????????init: " + listImsiListTrue.toString());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
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
        if (GuankongType == 2) {//?????????1
            if (TextUtils.isEmpty(tf1)) {
                ToastUtils.showToast("??????1?????????");
                return;
            }
            if (TextUtils.isEmpty(tf2)) {
                ToastUtils.showToast("??????2?????????");
                return;
            }

            if (!sb1.equals("??????")) {
                ToastUtils.showToast("??????1??????????????????");
                return;
            }
            if (!sb2.equals("??????")) {
                ToastUtils.showToast("??????2??????????????????");
                return;
            }
            Log.d("setGKtart", "setGKtart: ");
            //---------------------------------???????????????????????????
            dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
            inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
            TextView tv_title = inflate.findViewById(R.id.tv_title);
            Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
            tv_title.setText("??????????????????????");
            bt_confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("setGKtart", "setGKtart:????????????????????? ");
                    try {
                        DBManagerAddParamWhite dbManagerAddParamA = new DBManagerAddParamWhite(context);
                        List<AddPararBeanWhite> dataAlla = dbManagerAddParamA.getDataAll();
                        List<AddPararBeanWhite> listImsiListTrue = new ArrayList<>();
                        Log.d("setGKtartaddPararBeans", "???????????????--init: " + dataAlla.toString());

                        if (dataAlla.size() > 0) {
                            for (int i = 0; i < dataAlla.size(); i++) {
                                if (dataAlla.get(i).isCheckbox() == true) {
                                    dataAlla.get(i).setSb("");
                                    listImsiListTrue.add(dataAlla.get(i));
                                }
                            }
                            List<Integer> list1size = new ArrayList<>();
                            if (listImsiListTrue.size() > 0) {
                                for (int i = 1; i < listImsiListTrue.size() + 1; i++) {
                                    list1size.add(i);
                                }

                                List<AddPararBeanWhite> sendwhitelistLT = new ArrayList<>();
                                for (int j = 0; j < listImsiListTrue.size(); j++) {
                                    if (listImsiListTrue.get(j).getYy().equals("??????")) {
                                        sendwhitelistLT.add(listImsiListTrue.get(j));
                                    }
                                }
                                List<AddPararBeanWhite> sendwhitelistDX = new ArrayList<>();
                                for (int j = 0; j < listImsiListTrue.size(); j++) {
                                    if (listImsiListTrue.get(j).getYy().equals("??????")) {
                                        sendwhitelistDX.add(listImsiListTrue.get(j));
                                    }
                                }
                                if (!tf1.equals("FDD")) {
                                    ToastUtils.showToast("??????1???????????????TDD");
                                    viewS.gkqiehuan(tf1, 1);
//                                    return;
                                }
                                if (!tf2.equals("FDD")) {
                                    ToastUtils.showToast("??????2???????????????TDD");
                                    viewS.gkqiehuan(tf2, 2);
//                                    return;
                                }
                                if (sendwhitelistLT.size() > 0) {
                                    sendwhiteListRunLT(sendwhitelistLT, tf1, tf2, context, listImsiListTrue);//???????????????  2???
                                } else {
                                    sendwhiteListRunLT(sendwhitelistLT, tf1, tf2, context, listImsiListTrue);//???????????????  2???
                                }
                                if (sendwhitelistDX.size() > 0) {
                                    sendwhiteListRunDX(sendwhitelistDX, tf1, tf2, context, listImsiListTrue);//???????????????  2???
                                } else {
                                    sendwhiteListRunDX(sendwhitelistDX, tf1, tf2, context, listImsiListTrue);//???????????????  2???

                                }


                            } else {
//                                ToastUtils.showToast("?????????????????????IMSI");
                                List<AddPararBeanWhite> sendwhitelistLT = new ArrayList<>();
                                List<AddPararBeanWhite> sendwhitelistDX = new ArrayList<>();
//                                ToastUtils.showToast("111");

                                sendwhiteListRunLT(sendwhitelistLT, tf1, tf2, context, listImsiListTrue);//???????????????  2???


                                sendwhiteListRunDX(sendwhitelistDX, tf1, tf2, context, listImsiListTrue);//???????????????  2???


                            }
                        }else {
                            List<AddPararBeanWhite> sendwhitelistLT = new ArrayList<>();
                            List<AddPararBeanWhite> sendwhitelistDX = new ArrayList<>();
                            if (!tf1.equals("FDD")) {
                                ToastUtils.showToast("??????1???????????????TDD");
                                viewS.gkqiehuan(tf1, 1);
//                                    return;
                            }else {
                                sendwhiteListRunLT(sendwhitelistLT, tf1, tf2, context, listImsiListTrue);//???????????????  2???
                            }
                            if (!tf2.equals("FDD")) {
                                ToastUtils.showToast("??????2???????????????TDD");
                                viewS.gkqiehuan(tf2, 2);
//                                    return;
                            }else {
                                sendwhiteListRunDX(sendwhitelistDX, tf1, tf2, context, listImsiListTrue);//???????????????  2???
                            }
                        }
                        Log.d("setGKtartaddPararBeans", "?????????init: " + listImsiListTrue.toString());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }


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
    }

    private void chongdingxiang(final String ip) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                DatagramSocket socket = null;
                InetAddress address = null;//????????????ip??????
                Log.e("nzq", "run: nzqsend");
                byte[] outputData = MainUtilsThread.hexStringToByteArray(OPENCHONGDINGXIANG);
                try {
                    socket = new DatagramSocket();
                    address = InetAddress.getByName(ip);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (SocketException e) {
                    e.printStackTrace();
                }
                ;

                DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, address, 3345);
//                        DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, reIP, Integer.parseInt(et_port.getText().toString()));
                Log.e("nzqsendstart1Clear", "run: sendsocket?????????" + outputPacket.getPort() + "Ip??????:" + outputPacket.getAddress().toString() + "??????:" + outputPacket.getData());

                try {
                    Thread.sleep(500);
                    //                                    socket.send(outputPacket);
                    DS.send(outputPacket);

//
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }

    private void chongdingxiangSet(final String ip) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                DatagramSocket socket = null;
                InetAddress address = null;//????????????ip??????
                Log.e("nzq", "run: nzqsend");
                byte[] outputData = MainUtilsThread.hexStringToByteArray(CHONGDINGXIANGSET);
                try {
                    socket = new DatagramSocket();
                    address = InetAddress.getByName(ip);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (SocketException e) {
                    e.printStackTrace();
                }
                ;

                DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, address, 3345);
//                        DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, reIP, Integer.parseInt(et_port.getText().toString()));
                Log.e("nzqsendstart1??????", "run: sendsocket?????????" + outputPacket.getPort() + "Ip??????:" + outputPacket.getAddress().toString() + "??????:" + outputPacket.getData());

                try {
//                    Thread.sleep(500);
                    //                                    socket.send(outputPacket);
                    DS.send(outputPacket);

//
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }

    private void setzmlistStart(Context context) {
        Log.d("aaasdqwe", "setzmlistStart: ");
        DBManagersaopin dbManagerLunxun;//??????1???????????????
        final List<Integer> listDown1 = new ArrayList<>();
        final List<Integer> listDown2 = new ArrayList<>();
        try {
            dbManagerLunxun = new DBManagersaopin(context);
            List<SaopinBean> listall = dbManagerLunxun.getStudent();
            for (int j = 0; j < listall.size(); j++) {
                if (listall.get(j).getYy().equals("??????TDD") && listall.get(j).getType() == 1) {
                    listDown1.add(listall.get(j).getDown());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d("aaasdqwe", "setzmlistStart1: " + e.getMessage());
        }
        //??????2
        try {
            dbManagerLunxun = new DBManagersaopin(context);
            List<SaopinBean> listall = dbManagerLunxun.getStudent();
            for (int j = 0; j < listall.size(); j++) {
                if (listall.get(j).getYy().equals("??????FDD") && listall.get(j).getType() == 1) {
                    listDown2.add(listall.get(j).getDown());
                }
            }
            for (int j = 0; j < listall.size(); j++) {
                if (listall.get(j).getYy().equals("??????") && listall.get(j).getType() == 1) {
                    listDown2.add(listall.get(j).getDown());
                }
            }
            for (int j = 0; j < listall.size(); j++) {
                if (listall.get(j).getYy().equals("??????") && listall.get(j).getType() == 1) {
                    listDown2.add(listall.get(j).getDown());
                }
            }
            LogUtils.a("??????2 listDown2" + listDown2.toString());

        } catch (SQLException e) {
            e.printStackTrace();
            Log.d("aaasdqwe", "setzmlistStart: ");
        }
        Log.d("saopinlunxunlist1-2", "setZMStart: 1:" + listDown1.toString() + "---\n" + listDown2);
        Log.d("aaasdqwe", "setzmlistStart: " + listDown1.toString() + "---\n" + listDown2);
        if (listDown1.size() > 10) {
            ToastUtils.showToast("??????1????????????????????????10???");
            return;
        }
        if (listDown2.size() > 10) {
            ToastUtils.showToast("??????2????????????????????????10???");
            return;
        }
        if (listDown1.size() == 0) {
            ToastUtils.showToast("??????1????????????0???");
            return;
        }
        if (listDown2.size() == 0) {
            ToastUtils.showToast("??????2????????????0???");
            return;
        }
        //

        //---------------------------------???????????????????????????
        dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
        inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
        TextView tv_title = inflate.findViewById(R.id.tv_title);
        Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
        tv_title.setText("??????????????????????");
        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("aaasdqwe", "setzmlistStart1: ");
                viewS.zmlunxunlist(listDown1, listDown2);
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

    private void setzmlistStartFDD(Context context) {
        Log.d("aaasdqwe", "setzmlistStart: ");
        DBManagersaopin dbManagerLunxun;//??????1???????????????
        final List<Integer> listDown1 = new ArrayList<>();
        final List<Integer> listDown2 = new ArrayList<>();
        try {
            dbManagerLunxun = new DBManagersaopin(context);
            List<SaopinBean> listall = dbManagerLunxun.getStudent();
            for (int j = 0; j < listall.size(); j++) {
                if (listall.get(j).getYy().equals("??????TDD") && listall.get(j).getType() == 1) {
                    listDown1.add(listall.get(j).getDown());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d("aaasdqwe", "setzmlistStart1: " + e.getMessage());
        }
        //??????2
        try {
            dbManagerLunxun = new DBManagersaopin(context);
            List<SaopinBean> listall = dbManagerLunxun.getStudent();
            for (int j = 0; j < listall.size(); j++) {
                if (listall.get(j).getYy().equals("??????FDD") && listall.get(j).getType() == 1) {
                    listDown2.add(listall.get(j).getDown());
                }
            }
            for (int j = 0; j < listall.size(); j++) {
                if (listall.get(j).getYy().equals("??????") && listall.get(j).getType() == 1) {
                    listDown2.add(listall.get(j).getDown());
                }
            }
            for (int j = 0; j < listall.size(); j++) {
                if (listall.get(j).getYy().equals("??????") && listall.get(j).getType() == 1) {
                    listDown2.add(listall.get(j).getDown());
                }
            }
            LogUtils.a("??????2 listDown2" + listDown2.toString());

        } catch (SQLException e) {
            e.printStackTrace();
            Log.d("aaasdqwe", "setzmlistStart: ");
        }
        Log.d("saopinlunxunlist1-2", "setZMStart: 1:" + listDown1.toString() + "---\n" + listDown2);
        Log.d("aaasdqwe", "setzmlistStart: " + listDown1.toString() + "---\n" + listDown2);
        if (listDown1.size() > 10) {
            ToastUtils.showToast("??????1????????????????????????10???");
            return;
        }
        if (listDown2.size() > 10) {
            ToastUtils.showToast("??????2????????????????????????10???");
            return;
        }
        if (listDown1.size() == 0) {
            ToastUtils.showToast("??????1????????????0???");
            return;
        }
        if (listDown2.size() == 0) {
            ToastUtils.showToast("??????2????????????0???");
            return;
        }
        //

        //---------------------------------???????????????????????????
        dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
        inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
        TextView tv_title = inflate.findViewById(R.id.tv_title);
        Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
        tv_title.setText("??????????????????????");
        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("aaasdqwe", "setzmlistStart1: ");
                viewS.zmlunxunlist(listDown1, listDown2);
                viewS.zhishiqiehuanZm(1);
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

    private void setzmlistStartTDD(Context context) {
        Log.d("aaasdqwe", "setzmlistStart: ");
        DBManagersaopin dbManagerLunxun;//??????1???????????????
        final List<Integer> listDown1 = new ArrayList<>();
        final List<Integer> listDown2 = new ArrayList<>();
        try {
            dbManagerLunxun = new DBManagersaopin(context);
            List<SaopinBean> listall = dbManagerLunxun.getStudent();
            for (int j = 0; j < listall.size(); j++) {
                if (listall.get(j).getYy().equals("??????TDD") && listall.get(j).getType() == 1) {
                    listDown1.add(listall.get(j).getDown());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d("aaasdqwe", "setzmlistStart1: " + e.getMessage());
        }
        //??????2
        try {
            dbManagerLunxun = new DBManagersaopin(context);
            List<SaopinBean> listall = dbManagerLunxun.getStudent();
            for (int j = 0; j < listall.size(); j++) {
                if (listall.get(j).getYy().equals("??????FDD") && listall.get(j).getType() == 1) {
                    listDown2.add(listall.get(j).getDown());
                }
            }
            for (int j = 0; j < listall.size(); j++) {
                if (listall.get(j).getYy().equals("??????") && listall.get(j).getType() == 1) {
                    listDown2.add(listall.get(j).getDown());
                }
            }
            for (int j = 0; j < listall.size(); j++) {
                if (listall.get(j).getYy().equals("??????") && listall.get(j).getType() == 1) {
                    listDown2.add(listall.get(j).getDown());
                }
            }
            LogUtils.a("??????2 listDown2" + listDown2.toString());

        } catch (SQLException e) {
            e.printStackTrace();
            Log.d("aaasdqwe", "setzmlistStart: ");
        }
        Log.d("saopinlunxunlist1-2", "setZMStart: 1:" + listDown1.toString() + "---\n" + listDown2);
        Log.d("aaasdqwe", "setzmlistStart: " + listDown1.toString() + "---\n" + listDown2);
        if (listDown1.size() > 10) {
            ToastUtils.showToast("??????1????????????????????????10???");
            return;
        }
        if (listDown2.size() > 10) {
            ToastUtils.showToast("??????2????????????????????????10???");
            return;
        }
        if (listDown1.size() == 0) {
            ToastUtils.showToast("??????1????????????0???");
            return;
        }
        if (listDown2.size() == 0) {
            ToastUtils.showToast("??????2????????????0???");
            return;
        }
        //

        //---------------------------------???????????????????????????
        dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
        inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
        TextView tv_title = inflate.findViewById(R.id.tv_title);
        Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
        tv_title.setText("??????????????????????");
        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("aaasdqwe", "setzmlistStart1: ");
                viewS.zmlunxunlist(listDown1, listDown2);
                viewS.zhishiqiehuanZm(2);
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

    @Override
    public void setLunxunJianLixiaoqu(final Context context, final int device, final String down, final String type) {
        if (device == 1) {
            new Thread(new Runnable() {
                @SuppressLint("LongLogTag")
                @Override
                public void run() {
                    List<PinConfigBean> pinConfigBeans = null;
                    DBManagerPinConfig dbManagerPinConfig = null;
                    try {
                        dbManagerPinConfig = new DBManagerPinConfig(context);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    try {
                        pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(down)); //?????????????????????
                        LogUtils.a("pinConfigBeans--" + pinConfigBeans.toString() + "??????" + down);

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if (pinConfigBeans.size() == 0) {
                        ToastUtils.showToast("???????????????" + down + "??????");
                        return;
                    }
                    Conmmunit01Bean forid = null;
                    DBManager01 dbManager01 = null;
                    try {
                        dbManager01 = new DBManager01(context);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    try {
                        forid = dbManager01.forid(device);  //????????????1

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if (pinConfigBeans == null) {
//                    ToastUtils.showToast("??????????????????");
//                    Set1StatusBar("??????????????????");
                        String band = MainUtils.getBand(Integer.parseInt(down));
                        pinConfigBeans = new ArrayList<>();
                        PinConfigBean pinConfigBean = new PinConfigBean();
                        pinConfigBean.setDown(Integer.parseInt(down));
                        pinConfigBean.setUp(255);
                        pinConfigBean.setBand(Integer.parseInt(band));
                        pinConfigBean.setPlmn("46000");
                        pinConfigBeans.add(pinConfigBean);

                    }
                    if (forid == null) {
//                    DBManager01   dbManager01 = null;
                        try {
                            dbManager01 = new DBManager01(context);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        Conmmunit01Bean conmmunit01Bean = new Conmmunit01Bean();
                        conmmunit01Bean.setCid("");
                        conmmunit01Bean.setEnodebpmax("");
                        conmmunit01Bean.setUepmax("");
                        conmmunit01Bean.setTac("1111");
                        conmmunit01Bean.setPci("111");
                        conmmunit01Bean.setCid("1111");
                        conmmunit01Bean.setType("0");
                        conmmunit01Bean.setCycle("5");
                        conmmunit01Bean.setId(1);
                        try {
                            dbManager01.insertConmmunit01Bean(conmmunit01Bean);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        return;
                    }
                    DatagramSocket socket = null;
                    InetAddress address = null;//????????????ip??????
                    Log.e("znzq", "run: nzqsend");

                    String s = setxq.setXq(
                            pinConfigBeans.get(0).getUp(),
                            pinConfigBeans.get(0).getDown(),
                            pinConfigBeans.get(0).getPlmn(),
                            pinConfigBeans.get(0).getBand(),
                            Integer.parseInt(forid.getPci()),
                            Integer.parseInt(forid.getTac()), Integer.parseInt(forid.getCid()));
//                Log.d(TAG, "run: " + s);
//
                    String ip = "";
                    byte[] outputData = MainUtilsThread.hexStringToByteArray(setxq.setXq(
                            pinConfigBeans.get(0).getUp(),
                            pinConfigBeans.get(0).getDown(),
                            pinConfigBeans.get(0).getPlmn(),
                            pinConfigBeans.get(0).getBand(),
                            Integer.parseInt(forid.getPci()),
                            Integer.parseInt(forid.getTac()), Integer.parseInt(forid.getCid())));//4294967295   //222222222l   //  268435455
                    if (device == 1) {
                        DOWNPIN1 = pinConfigBeans.get(0).getDown() + "";
                        ip = IP1;
                    } else if (device == 2) {
                        DOWNPIN2 = pinConfigBeans.get(0).getDown() + "";
                        ip = IP2;
                    }

                    try {
                        socket = new DatagramSocket();
                        address = InetAddress.getByName(ip);
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    } catch (SocketException e) {
                        e.printStackTrace();
                    }
                    ;
                    DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, address, 3345);
                    Log.e("startLocation1s????????????1", "run: sendsocket?????????" + outputPacket.getPort() + "Ip??????:" + outputPacket.getAddress().toString() + "??????:" + outputPacket.getData());

                    try {
                        if (type.equals("?????????")) {
//                        view.UpEstablish(device, "?????????");
                        } else {
//                                    socket.send(outputPacket);
                            DS.send(outputPacket);                            Log.e("socketstartLocation1s????????????1", "run: sendsocket?????????" + outputPacket.getPort() + "Ip??????:" + outputPacket.getAddress().toString() + "??????:" + outputPacket.getData());

                        }

//                    interrupted();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }
        if (device == 2) {
            new Thread(new Runnable() {
                @SuppressLint("LongLogTag")
                @Override
                public void run() {
                    List<PinConfigBean> pinConfigBeans = null;
                    DBManagerPinConfig dbManagerPinConfig = null;
                    try {
                        dbManagerPinConfig = new DBManagerPinConfig(context);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    try {
                        pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(down)); //?????????????????????
                        LogUtils.a("pinConfigBeans--" + pinConfigBeans.toString() + "??????" + down);

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if (pinConfigBeans.size() == 0) {
                        ToastUtils.showToast("???????????????" + down + "??????");
                        return;
                    }
                    Conmmunit01Bean forid = null;
                    DBManager01 dbManager01 = null;
                    try {
                        dbManager01 = new DBManager01(context);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    try {
                        forid = dbManager01.forid(device);  //????????????1

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if (pinConfigBeans == null) {
//                    ToastUtils.showToast("??????????????????");
//                    Set1StatusBar("??????????????????");
                        String band = MainUtils.getBand(Integer.parseInt(down));
                        pinConfigBeans = new ArrayList<>();
                        PinConfigBean pinConfigBean = new PinConfigBean();
                        pinConfigBean.setDown(Integer.parseInt(down));
                        pinConfigBean.setUp(Integer.parseInt(down) + 18000);
                        pinConfigBean.setBand(Integer.parseInt(band));
                        pinConfigBean.setPlmn("46001");
                        pinConfigBeans.add(pinConfigBean);

                    }
                    if (forid == null) {
//                    DBManager01   dbManager01 = null;
                        try {
                            dbManager01 = new DBManager01(context);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        Conmmunit01Bean conmmunit01Bean = new Conmmunit01Bean();
                        conmmunit01Bean.setCid("");
                        conmmunit01Bean.setEnodebpmax("");
                        conmmunit01Bean.setUepmax("");
                        conmmunit01Bean.setTac("1111");
                        conmmunit01Bean.setPci("111");
                        conmmunit01Bean.setCid("1111");
                        conmmunit01Bean.setType("0");
                        conmmunit01Bean.setCycle("5");
                        conmmunit01Bean.setId(2);
                        try {
                            dbManager01.insertConmmunit01Bean(conmmunit01Bean);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        return;
                    }
                    DatagramSocket socket = null;
                    InetAddress address = null;//????????????ip??????
                    Log.e("znzq", "run: nzqsend");

                    String s = setxq.setXq(
                            pinConfigBeans.get(0).getUp(),
                            pinConfigBeans.get(0).getDown(),
                            pinConfigBeans.get(0).getPlmn(),
                            pinConfigBeans.get(0).getBand(),
                            Integer.parseInt(forid.getPci()),
                            Integer.parseInt(forid.getTac()), Integer.parseInt(forid.getCid()));
//                Log.d(TAG, "run: " + s);
//
                    String ip = "";
                    byte[] outputData = MainUtilsThread.hexStringToByteArray(setxq.setXq(
                            pinConfigBeans.get(0).getUp(),
                            pinConfigBeans.get(0).getDown(),
                            pinConfigBeans.get(0).getPlmn(),
                            pinConfigBeans.get(0).getBand(),
                            Integer.parseInt(forid.getPci()),
                            Integer.parseInt(forid.getTac()), Integer.parseInt(forid.getCid())));//4294967295   //222222222l   //  268435455
                    if (device == 1) {
                        DOWNPIN1 = pinConfigBeans.get(0).getDown() + "";
                        ip = IP1;
                    } else if (device == 2) {
                        DOWNPIN2 = pinConfigBeans.get(0).getDown() + "";
                        ip = IP2;
                    }

                    try {
                        socket = new DatagramSocket();
                        address = InetAddress.getByName(ip);
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    } catch (SocketException e) {
                        e.printStackTrace();
                    }
                    ;
                    DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, address, 3345);
                    Log.e("startLocation1s????????????1", "run: sendsocket?????????" + outputPacket.getPort() + "Ip??????:" + outputPacket.getAddress().toString() + "??????:" + outputPacket.getData());

                    try {
                        if (type.equals("?????????")) {
//                        view.UpEstablish(device, "?????????");
                        } else {
                            //                                    socket.send(outputPacket);
                            DS.send(outputPacket);
                            Log.e("socketstartLocation1s????????????1", "run: sendsocket?????????" + outputPacket.getPort() + "Ip??????:" + outputPacket.getAddress().toString() + "??????:" + outputPacket.getData());

                        }

//                    interrupted();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }
    }
//aaaaaaaa

    /**
     * ???????????????
     */
    @Override
    public void chongdingxiang1(final String down) {
//        chongdingxiang(IP1);
        new Thread(new Runnable() {
            @Override
            public void run() {

                DatagramSocket socket = null;
                InetAddress address = null;//????????????ip??????
                Log.e("nzq", "run: nzqsend");

                StringBuffer buffer = new StringBuffer("aaaa555517f01800000000ff00000000");

                int as = Integer.parseInt(down);
                String s = Integer.toHexString(as);

                StringBuilder sb = new StringBuilder(s);
                if (s.length() >= 4) {
                    char x1 = sb.charAt(0);
                    char x2 = sb.charAt(1);
                    char x3 = sb.charAt(2);
                    char x4 = sb.charAt(3);
                    sb.setCharAt(0, x3);
                    sb.setCharAt(1, x4);
                    sb.setCharAt(2, x1);
                    sb.setCharAt(3, x2);
                }
                if (s.length() == 3) {
                    if (s.length() == 3) {


                        StringBuilder buffer2 = new StringBuilder();

                        char x1 = sb.charAt(0);
                        char x2 = sb.charAt(1);
                        char x3 = '0';
                        char x4 = sb.charAt(2);
                        buffer2.append("0");
                        buffer2.append(x4);
                        buffer2.append(x1);
                        buffer2.append(x2);
//                    sb.setCharAt(0, x3);
//                    sb.setCharAt(1, x4);
//                    sb.setCharAt(2, x1);
//                    sb.setCharAt(3, x2);
                        sb = buffer2;
                    }
                }
                Log.d("TAGzhuanhuan", "run: ." + s + "===zhuanhuanhou+" + sb.toString());
                buffer.append(sb);
                buffer.append("00000000");

//                byte[] outputData = MainUtilsThread.hexStringToByteArray(OPENCHONGDINGXIANG);
                byte[] outputData = MainUtilsThread.hexStringToByteArray(buffer.toString());
                try {
                    socket = new DatagramSocket();
                    address = InetAddress.getByName(IP1);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (SocketException e) {
                    e.printStackTrace();
                }
                ;

                DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, address, 3345);
//                        DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, reIP, Integer.parseInt(et_port.getText().toString()));
                Log.e("nzqsendstart1Clear", "run: sendsocket?????????" + outputPacket.getPort() + "Ip??????:" + outputPacket.getAddress().toString() + "??????:" + outputPacket.getData());

                try {
                    Thread.sleep(500);
                    //                                    socket.send(outputPacket);
                    DS.send(outputPacket);
                    Log.d("aaaaaasad", "run: " + buffer.toString());
//
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }

    @Override
    public void chongdingxiang2(final String down) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                DatagramSocket socket = null;
                InetAddress address = null;//????????????ip??????
                Log.e("nzq", "run: nzqsend");

                StringBuffer buffer = new StringBuffer("aaaa555517f01800000000ff00000000");

                int as = Integer.parseInt(down);
                String s = Integer.toHexString(as);

                StringBuilder sb = new StringBuilder(s);
                if (s.length() >= 4) {
                    char x1 = sb.charAt(0);
                    char x2 = sb.charAt(1);
                    char x3 = sb.charAt(2);
                    char x4 = sb.charAt(3);
                    sb.setCharAt(0, x3);
                    sb.setCharAt(1, x4);
                    sb.setCharAt(2, x1);
                    sb.setCharAt(3, x2);
                }
                if (s.length() == 3) {


                    StringBuilder buffer2 = new StringBuilder();

                    char x1 = sb.charAt(0);
                    char x2 = sb.charAt(1);
                    char x3 = '0';
                    char x4 = sb.charAt(2);
                    buffer2.append("0");
                    buffer2.append(x4);
                    buffer2.append(x1);
                    buffer2.append(x2);
//                    sb.setCharAt(0, x3);
//                    sb.setCharAt(1, x4);
//                    sb.setCharAt(2, x1);
//                    sb.setCharAt(3, x2);
                    sb = buffer2;
                }
                Log.d("TAGzhuanhuan", "run: ." + s + "===zhuanhuanhou+" + sb.toString());
                buffer.append(sb);
                buffer.append("00000000");

//                byte[] outputData = MainUtilsThread.hexStringToByteArray(OPENCHONGDINGXIANG);
                byte[] outputData = MainUtilsThread.hexStringToByteArray(buffer.toString());
                try {
                    socket = new DatagramSocket();
                    address = InetAddress.getByName(IP2);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (SocketException e) {
                    e.printStackTrace();
                }
                ;

                DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, address, 3345);
//                        DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, reIP, Integer.parseInt(et_port.getText().toString()));
                Log.e("nzqsendstart1Clear", "run: sendsocket?????????" + outputPacket.getPort() + "Ip??????:" + outputPacket.getAddress().toString() + "??????:" + outputPacket.getData());

                try {
                    Thread.sleep(500);
                    //                                    socket.send(outputPacket);
                    DS.send(outputPacket);
                    Log.d("aaaaaasad", "run: " + buffer.toString());
//
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }

    @Override
    public void chongdingxiangSET1() {
        chongdingxiangSet(IP1);
    }

    @Override
    public void chongdingxiangSET2() {
        chongdingxiangSet(IP2);
    }

    private void sendBlackListRunSp(List<AddPararBean> sendListBlack) {

    }

    private void saopinSend1(List<SaopinBean> saopinBeanList, String tf1, int yy, Context context) {
//        Log.d(TAG, "listImsiListTruesad1: " + listImsiListTrue);
        List<Integer> list = new ArrayList<>();
//        Log.d(TAG, "AsaopinSend1: " + saopinBeanList + "??????" + zs);
        DBManagersaopin dbManagersaopin = null;
        try {
            dbManagersaopin = new DBManagersaopin(context);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            saopinBeanList = dbManagersaopin.getStudent();
//            Log.d(TAG, "AsaopinSend1: " + saopinBeanList + "??????" + zs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (saopinBeanList.size() > 0) {

            for (int i = 0; i < saopinBeanList.size(); i++) {
                if (saopinBeanList.get(i).getYy().equals(MyUtils.YYname(yy)) && saopinBeanList.get(i).getType() == 1) {
                    list.add(saopinBeanList.get(i).getDown());
                }
            }

            if (list.size() > 0) {
                if (list.size() > 10) {
                    ToastUtils.showToast("??????????????????10???");
                } else {
                    MainUtils.sendspSocket(list, IP1);
//                    Log.d(TAG, "saopinSend1: " + list);
                }

            } else {
//                ToastUtils.showToast("????????????" + zs + "?????????");

            }

        } else {
            ToastUtils.showToast("??????????????????");
        }
    }

    private void saopinSend2(List<SaopinBean> saopinBeanList, String tf1, int yy, Context context) {
//        Log.d(TAG, "listImsiListTruesad1: " + listImsiListTrue);
        List<Integer> list = new ArrayList<>();
//        Log.d(TAG, "AsaopinSend1: " + saopinBeanList + "??????" + zs);
        DBManagersaopin dbManagersaopin = null;
        try {
            dbManagersaopin = new DBManagersaopin(context);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            saopinBeanList = dbManagersaopin.getStudent();
//            Log.d(TAG, "AsaopinSend1: " + saopinBeanList + "??????" + zs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (saopinBeanList.size() > 0) {

            for (int i = 0; i < saopinBeanList.size(); i++) {
                if (saopinBeanList.get(i).getYy().equals(MyUtils.YYname(yy)) && saopinBeanList.get(i).getType() == 1) {
                    list.add(saopinBeanList.get(i).getDown());
                }
            }

            if (list.size() > 0) {
                if (list.size() > 10) {
                    ToastUtils.showToast("??????????????????10???");
                } else {
                    MainUtils.sendspSocket(list, IP2);
//                    Log.d(TAG, "saopinSend1: " + list);
                }

            } else {
//                ToastUtils.showToast("????????????" + zs + "?????????");

            }

        } else {
            ToastUtils.showToast("??????????????????");
        }
    }

    //???????????????
    private void sendwhiteListRun(List<AddPararBeanWhite> sendListBlack, final String tf1, final String spinnerS1, final Context context, List<AddPararBeanWhite> listImsiListTrue) {

        List<String> list = new ArrayList<>();
//        Log.d(TAG, "sendBlacListRun:list" + list);
        for (int i = 0; i < sendListBlack.size(); i++) {
            list.add(sendListBlack.get(i).getImsi());
        }
        Log.d("aaaaalist", "sendwhiteListRun: " + list);
        int totalMy = list.size();
//        Log.d(TAG, "sendBlackListRun:totalMy" + totalMy);
        //?????????
        StringBuffer str = new StringBuffer("aaaa555539f0b900000000ff01");

        //???????????????
        String s = "";
        if (totalMy == 10) {
            s = "10";
        } else {
            s = "0" + String.valueOf(totalMy);
        }
        str.append(s);
//        str.append(MainUtils2.StringPin(MainUtils2.blacklistCount(Integer.toString(totalMy, 16))));
        Log.d("aaTAG", "sendwhiteListRun: " + str.toString());
        str.append("00");
        List<String> list2 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).equals("") && list.get(i) != null) {
                StringBuffer imsi = toIMSI(list.get(i));
                str.append(imsi).append("0000");
                list2.add(list.get(i));
            }
        }
        for (int i = 0; i < list2.size(); i++) {
            PararBean pararBean = new PararBean();
            pararBean.setImsi(list2.get(i));
        }
        for (int i = 0; i < 10 - list2.size(); i++) {
            str.append("0000000000000000000000000000000000");
        }
        Log.d("uuew", "sendwhiteListRun: " + str.toString());
        str.append("03ff00");
        if (!TextUtils.isEmpty(str)) {
            sendrunwhite(str, sendListBlack, tf1, spinnerS1, context, listImsiListTrue);//????????????
            sendrunwhite2(str, sendListBlack, tf1, spinnerS1, context, listImsiListTrue);//????????????

        }
    }

    //???????????????
    private void sendwhiteListRunDELE(List<AddPararBeanWhite> sendListBlack, final String tf1, final String spinnerS1, final Context context, List<AddPararBeanWhite> listImsiListTrue) {

        List<String> list = new ArrayList<>();
//        Log.d(TAG, "sendBlacListRun:list" + list);
//        for (int i = 0; i < sendListBlack.size(); i++) {
//            list.add(sendListBlack.get(i).getImsi());
//        }
        Log.d("aaaaalist", "sendwhiteListRun: " + list);
        int totalMy = list.size();
//        Log.d(TAG, "sendBlackListRun:totalMy" + totalMy);
        //?????????
        StringBuffer str = new StringBuffer("aaaa555539f0b900000000ff00");

//        //???????????????
//        String s = "";
//        if (totalMy == 10) {
//            s = "10";
//        } else {
//            s = "0" + String.valueOf(totalMy);
//        }
        str.append("01");
        str.append("00");
//        str.append(MainUtils2.StringPin(MainUtils2.blacklistCount(Integer.toString(totalMy, 16))));
        Log.d("aaTAG", "sendwhiteListRun: " + str.toString());
//        str.append("00");

        ///????????????15??? 1

        str.append("3131313131313131313131313131310000");
//        List<String> list2 = new ArrayList<>();
//        for (int i = 0; i < list.size(); i++) {
//            if (!list.get(i).equals("") && list.get(i) != null) {
//                StringBuffer imsi = toIMSI(list.get(i));
//                str.append(imsi).append("0000");
//                list2.add(list.get(i));
//            }
//        }
//        for (int i = 0; i < list2.size(); i++) {
//            PararBean pararBean = new PararBean();
//            pararBean.setImsi(list2.get(i));
//        }
        for (int i = 0; i < 10 - 1; i++) {
            str.append("0000000000000000000000000000000000");
        }
        Log.d("uuew", "sendwhiteListRun: " + str.toString());
        str.append("03ff00");
        StringBuffer str2 = new StringBuffer("aa aa 55 55 39 f0 b9 00 00 00 00 ff 00 01 00 " +
                "31 31 31 31 31 31 31 31 31 31 31 31 31 31 31 00 00" +
                "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 " +
                "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 " +
                "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 " +
                "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 " +
                "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 " +
                "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 " +
                "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 " +
                "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 " +
                "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 " +
                "03 ff 00");
        if (!TextUtils.isEmpty(str)) {
            sendrunwhite(str, sendListBlack, tf1, spinnerS1, context, listImsiListTrue);//????????????
            sendrunwhite2(str, sendListBlack, tf1, spinnerS1, context, listImsiListTrue);//????????????

        }
    }

    //???????????????
    private void sendwhiteListRunLT(List<AddPararBeanWhite> sendListBlack, final String tf1, final String spinnerS1, final Context context, List<AddPararBeanWhite> listImsiListTrue) {

        List<String> list = new ArrayList<>();
//        Log.d(TAG, "sendBlacListRun:list" + list);
        for (int i = 0; i < sendListBlack.size(); i++) {
            list.add(sendListBlack.get(i).getImsi());
        }
        Log.d("aaaaalist", "sendwhiteListRun: " + list);
        int totalMy = list.size();
//        Log.d(TAG, "sendBlackListRun:totalMy" + totalMy);
        //?????????
        StringBuffer str = new StringBuffer("aaaa555539f0b900000000ff01");
        //???????????????
        String s = "";
        if (totalMy == 10) {
            s = "10";
        } else {
            s = "0" + String.valueOf(totalMy);
        }
        str.append(s);
//        str.append(MainUtils2.StringPin(MainUtils2.blacklistCount(Integer.toString(totalMy, 16))));
        Log.d("aaTAG", "sendwhiteListRun: " + str.toString());
        str.append("00");
        List<String> list2 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).equals("") && list.get(i) != null) {
                StringBuffer imsi = toIMSI(list.get(i));
                str.append(imsi).append("0000");
                list2.add(list.get(i));
            }
        }
        for (int i = 0; i < list2.size(); i++) {
            PararBean pararBean = new PararBean();
            pararBean.setImsi(list2.get(i));
        }
        for (int i = 0; i < 10 - list2.size(); i++) {
            str.append("0000000000000000000000000000000000");
        }
        Log.d("uuew", "sendwhiteListRun: " + str.toString());
        str.append("03ff00");
        if (!TextUtils.isEmpty(str)) {
            sendrunwhite(str, sendListBlack, tf1, spinnerS1, context, listImsiListTrue);//????????????
//            sendrunwhite2(str, sendListBlack, tf1, spinnerS1, context, listImsiListTrue);//????????????

        }
    }

    //???????????????
    private void sendwhiteListRunDX(List<AddPararBeanWhite> sendListBlack, final String tf1, final String spinnerS1, final Context context, List<AddPararBeanWhite> listImsiListTrue) {

        List<String> list = new ArrayList<>();
//        Log.d(TAG, "sendBlacListRun:list" + list);
        for (int i = 0; i < sendListBlack.size(); i++) {
            list.add(sendListBlack.get(i).getImsi());
        }
        Log.d("aaaaalist", "sendwhiteListRun: " + list);
        int totalMy = list.size();
//        Log.d(TAG, "sendBlackListRun:totalMy" + totalMy);
        //?????????
        StringBuffer str = new StringBuffer("aaaa555539f0b900000000ff01");
        //???????????????
        String s = "";
        if (totalMy == 10) {
            s = "10";
        } else {
            s = "0" + String.valueOf(totalMy);
        }
        str.append(s);
//        str.append(MainUtils2.StringPin(MainUtils2.blacklistCount(Integer.toString(totalMy, 16))));
        Log.d("aaTAG", "sendwhiteListRun: " + str.toString());
        str.append("00");
        List<String> list2 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).equals("") && list.get(i) != null) {
                StringBuffer imsi = toIMSI(list.get(i));
                str.append(imsi).append("0000");
                list2.add(list.get(i));
            }
        }
        for (int i = 0; i < list2.size(); i++) {
            PararBean pararBean = new PararBean();
            pararBean.setImsi(list2.get(i));
        }
        for (int i = 0; i < 10 - list2.size(); i++) {
            str.append("0000000000000000000000000000000000");
        }
        Log.d("uuew", "sendwhiteListRun: " + str.toString());
        str.append("03ff00");
        if (!TextUtils.isEmpty(str)) {
//            sendrunwhite(str, sendListBlack, tf1, spinnerS1, context, listImsiListTrue);//????????????
            sendrunwhite2(str, sendListBlack, tf1, spinnerS1, context, listImsiListTrue);//????????????

        }
    }

    //???????????????
    private void sendBlackListRun(List<AddPararBean> sendListBlack, final String tf1, final String spinnerS1, final Context context, List<AddPararBean> listImsiListTrue) {
        if (sendListBlack.size() == 0) {
            ToastUtils.showToast("?????????????????????IMSI");
            return;
        }
        List<String> list = new ArrayList<>();
//        Log.d(TAG, "sendBlacListRun:list" + list);
        for (int i = 0; i < sendListBlack.size(); i++) {
            list.add(sendListBlack.get(i).getImsi());
        }
        int totalMy = list.size();
//        Log.d(TAG, "sendBlackListRun:totalMy" + totalMy);
        //?????????
        StringBuffer str = new StringBuffer("aaaa555553f06401000000ff");
        //???????????????
        str.append(MainUtils2.StringPin(MainUtils2.blacklistCount(Integer.toString(totalMy, 16))));
        List<String> list2 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).equals("") && list.get(i) != null) {
                StringBuffer imsi = toIMSI(list.get(i));
                str.append(imsi).append("0000");
                list2.add(list.get(i));

            }
        }
        for (int i = 0; i < list2.size(); i++) {
            PararBean pararBean = new PararBean();
            pararBean.setImsi(list2.get(i));


        }
        for (int i = 0; i < 20 - list2.size(); i++) {
            str.append("0000000000000000000000000000000000");
        }
        str.append("010000");
        if (!TextUtils.isEmpty(str)) {
            sendrun(str, sendListBlack, tf1, spinnerS1, context, listImsiListTrue);//????????????

        }
    }

    //???????????????
    private void sendBlackListRun2(List<AddPararBean> sendListBlack, final String tf1, final String spinnerS1, final Context context, List<AddPararBean> listImsiListTrue) {
        if (sendListBlack.size() == 0) {
            ToastUtils.showToast("?????????????????????IMSI");
            return;
        }
        List<String> list = new ArrayList<>();
//        Log.d(TAG, "sendBlacListRun:list" + list);
        for (int i = 0; i < sendListBlack.size(); i++) {
            list.add(sendListBlack.get(i).getImsi());
        }
        int totalMy = list.size();
//        Log.d(TAG, "sendBlackListRun:totalMy" + totalMy);
        //?????????
        StringBuffer str = new StringBuffer("aaaa555553f06401000000ff");
        //???????????????
        str.append(MainUtils2.StringPin(MainUtils2.blacklistCount(Integer.toString(totalMy, 16))));
        List<String> list2 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).equals("") && list.get(i) != null) {
                StringBuffer imsi = toIMSI(list.get(i));
                str.append(imsi).append("0000");
                list2.add(list.get(i));

            }
        }
        for (int i = 0; i < list2.size(); i++) {
            PararBean pararBean = new PararBean();
            pararBean.setImsi(list2.get(i));


        }
        for (int i = 0; i < 20 - list2.size(); i++) {
            str.append("0000000000000000000000000000000000");
        }
        str.append("010000");
        if (!TextUtils.isEmpty(str)) {
            sendrun2(str, sendListBlack, tf1, spinnerS1, context, listImsiListTrue);//????????????

        }
    }

    //??????1???????????????
    private void sendrun(final StringBuffer strData, final List<AddPararBean> sendListBlack, final String tf1, final String spinnerS1, final Context context, final List<AddPararBean> listImsiListTrue) {
//
        new Thread(new Runnable() {
            @Override
            public void run() {
                DatagramSocket socket = null;
                InetAddress address = null;//????????????ip??????
                Log.e("strDatanzq", "run: nzqsend" + strData);
                byte[] outputData = MainUtilsThread.hexStringToByteArray(strData.toString());
                try {
                    socket = new DatagramSocket();
                    address = InetAddress.getByName(IP1);

                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (SocketException e) {
                    e.printStackTrace();
                }
                ;
                DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, address, 3345);
//                        DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, reIP, Integer.parseInt(et_port.getText().toString()));
                Log.e("10nzqsendstaart1Black", "run: sendsocket?????????" + outputPacket.getPort() + "Ip??????:" + outputPacket.getAddress().toString() + "??????:" + outputPacket.getData());
                try {
                    //                                    socket.send(outputPacket);
                    DS.send(outputPacket);
//                    interrupted();
                    try {
                        Thread.sleep(10000);   //?????????????????????10??? ????????????
                        sb1Locations(sendListBlack, tf1, spinnerS1, context, listImsiListTrue);
                        Log.d("tag", "??????10???run: ");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }

    //??????1???????????????
    private void sendrunwhite(final StringBuffer strData, final List<AddPararBeanWhite> sendListBlack, final String tf1, final String spinnerS1, final Context context, final List<AddPararBeanWhite> listImsiListTrue) {
//
        new Thread(new Runnable() {
            @Override
            public void run() {
                DatagramSocket socket = null;
                InetAddress address = null;//????????????ip??????
//                String aa="";
//                aa ="aaaa555539f0b900000000ff010301" +
//                        "3436303030323336303839333634380000" +
//                        "3131313131313131313131313131310000" +
//                        "0000000000000000000000000000000000" +
//                        "0000000000000000000000000000000000" +
//                        "0000000000000000000000000000000000" +
//                        "0000000000000000000000000000000000" +
//                        "0000000000000000000000000000000000" +
//                        "0000000000000000000000000000000000" +
//                        "0000000000000000000000000000000000" +
//                        "0000000000000000000000000000000000" +
//                        "03ff00";
                Log.e("delestrDatanzq", "run: nzqsend" + strData);
//                Log.e("strDatanaazq", "run: nzqsend" + aa.toString());
                byte[] outputData = MainUtilsThread.hexStringToByteArray(strData.toString());
                try {
                    socket = new DatagramSocket();
                    address = InetAddress.getByName(IP1);

                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (SocketException e) {
                    e.printStackTrace();
                }
                ;
                DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, address, 3345);
//                        DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, reIP, Integer.parseInt(et_port.getText().toString()));
                Log.e("nzqsendstart1Black", "run: sendsocket?????????" + outputPacket.getPort() + "Ip??????:" + outputPacket.getAddress().toString() + "??????:" + outputPacket.getData());
                try {
                    //                                    socket.send(outputPacket);
                    DS.send(outputPacket);
//                    interrupted();
//                    try {
////                        Thread.sleep(2000);
////                        sb1Locations(sendListBlack, tf1, spinnerS1, context, listImsiListTrue);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }

    //??????1???????????????
    private void sendrunwhite2(final StringBuffer strData, final List<AddPararBeanWhite> sendListBlack, final String tf1, final String spinnerS1, final Context context, final List<AddPararBeanWhite> listImsiListTrue) {
//
        new Thread(new Runnable() {
            @Override
            public void run() {
                DatagramSocket socket = null;
                InetAddress address = null;//????????????ip??????
                Log.e("strDatanzq", "run: nzqsend" + strData);
                byte[] outputData = MainUtilsThread.hexStringToByteArray(strData.toString());
                try {
                    socket = new DatagramSocket();
                    address = InetAddress.getByName(IP2);

                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (SocketException e) {
                    e.printStackTrace();
                }
                ;
                DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, address, 3345);
//                        DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, reIP, Integer.parseInt(et_port.getText().toString()));
                Log.e("nzqsendstart1Black", "run: sendsocket?????????" + outputPacket.getPort() + "Ip??????:" + outputPacket.getAddress().toString() + "??????:" + outputPacket.getData());
                try {
                    //                                    socket.send(outputPacket);
                    DS.send(outputPacket);
//                    interrupted();
//                    try {
////                        Thread.sleep(2000);
////                        sb1Locations(sendListBlack, tf1, spinnerS1, context, listImsiListTrue);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }

    //??????1???????????????
    private void sendrun2(final StringBuffer strData, final List<AddPararBean> sendListBlack, final String tf1, final String spinnerS1, final Context context, final List<AddPararBean> listImsiListTrue) {
//
        new Thread(new Runnable() {
            @Override
            public void run() {
                DatagramSocket socket = null;
                InetAddress address = null;//????????????ip??????
                Log.e("strDatanzq", "run: nzqsend" + strData);
                byte[] outputData = MainUtilsThread.hexStringToByteArray(strData.toString());
                try {
                    socket = new DatagramSocket();
                    address = InetAddress.getByName(IP2);

                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (SocketException e) {
                    e.printStackTrace();
                }
                ;
                DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, address, 3345);
//                        DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, reIP, Integer.parseInt(et_port.getText().toString()));
                Log.e("nzqsendstart1Black", "run: sendsocket?????????" + outputPacket.getPort() + "Ip??????:" + outputPacket.getAddress().toString() + "??????:" + outputPacket.getData());
                try {
                    //                                    socket.send(outputPacket);
                    DS.send(outputPacket);
//                    interrupted();
                    try {
                        Thread.sleep(2000);
                        sb2Locations(sendListBlack, tf1, spinnerS1, context, listImsiListTrue);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }

    //??????1???????????? ??????
    private void sb1Locations(final List<AddPararBean> sendListBlack, final String tf1, final String spinnerS1, final Context context, final List<AddPararBean> listImsiListTrue) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                String sa = "";
                if (tf1.equals("TDD")) {
                    sa = "01";
                }
                if (tf1.equals("FDD")) {
                    sa = "00";
                }
                DatagramSocket socket = null;
                InetAddress address = null;//????????????ip??????
                Log.e("nzq", "run: nzqsend");

                if (!TextUtils.isEmpty(spinnerS1)) {
                    String yy = "";
                    DBManagerPinConfig dbManagerPinConfig = null;
                    List<PinConfigBean> pinConfigBeans;
                    try {
                        dbManagerPinConfig = new DBManagerPinConfig(context);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    try {
                        pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(spinnerS1));//?????????????????????
                        yy = pinConfigBeans.get(0).getYy();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
//                        Log.d(TAG, "sendBlackList:?????? ");
                    if (listImsiListTrue.size() > 0 && listImsiListTrue != null) {
                        for (int i = 0; i < listImsiListTrue.size(); i++) {
                            if (listImsiListTrue.get(i).getYy().equals(yy)) {
                                sendListBlack.add(listImsiListTrue.get(i));
                            }
                        }
                    }

                    if (sendListBlack.size() > 0 && sendListBlack != null) {
//                            Log.d(TAG, "sendBlackList: " + sendListBlack);
                        if (sendListBlack.size() > 20) {
                            ToastUtils.showToast("????????????????????????????????????20");
                        } else {
//                                sendBlackListRun(sendListBlack);
                        }
//
                    } else {
                        ToastUtils.showToast("?????????????????????IMSI");
                    }

                } else {
                    ToastUtils.showToast("????????????????????????");
                }
                if (sendListBlack.size() == 0) {
//                        Set1StatusBar("???????????????????????????IMSI");
                    ToastUtils.showToast("?????????????????????IMSI");
                } else {
                    byte[] outputData = MainUtilsThread.hexStringToByteArray(location(sendListBlack.get(0).getImsi(), "04", sa, context, 1));
                    try {
                        socket = new DatagramSocket();
                        address = InetAddress.getByName(IP1);
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    } catch (SocketException e) {
                        e.printStackTrace();
                    }
                    ;
                    DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, address, 3345);
//                        DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, reIP, Integer.parseInt(et_port.getText().toString()));
                    Log.e("nzqsendstart1??????????????????", "run: sendsocket?????????" + outputPacket.getPort() + "Ip??????:" + outputPacket.getAddress().toString() + "??????:" + outputPacket.getData());
                    try {
                        //                                    socket.send(outputPacket);
                        DS.send(outputPacket);
//
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


            }
        }).start();

    }

    //??????1???????????? ??????
    private void sb2Locations(final List<AddPararBean> sendListBlack, final String tf1, final String spinnerS1, final Context context, final List<AddPararBean> listImsiListTrue) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                String sa = "";
                if (tf1.equals("TDD")) {
                    sa = "01";
                }
                if (tf1.equals("FDD")) {
                    sa = "00";
                }
                DatagramSocket socket = null;
                InetAddress address = null;//????????????ip??????
                Log.e("nzq", "run: nzqsend");

                if (!TextUtils.isEmpty(spinnerS1)) {
                    String yy = "";
                    DBManagerPinConfig dbManagerPinConfig = null;
                    List<PinConfigBean> pinConfigBeans;
                    try {
                        dbManagerPinConfig = new DBManagerPinConfig(context);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    try {
                        pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(spinnerS1));//?????????????????????
                        yy = pinConfigBeans.get(0).getYy();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
//                        Log.d(TAG, "sendBlackList:?????? ");
                    if (listImsiListTrue.size() > 0 && listImsiListTrue != null) {
                        for (int i = 0; i < listImsiListTrue.size(); i++) {
                            if (listImsiListTrue.get(i).getYy().equals(yy)) {
                                sendListBlack.add(listImsiListTrue.get(i));
                            }
                        }
                    }

                    if (sendListBlack.size() > 0 && sendListBlack != null) {
//                            Log.d(TAG, "sendBlackList: " + sendListBlack);
                        if (sendListBlack.size() > 20) {
                            ToastUtils.showToast("????????????????????????????????????20");
                        } else {
//                                sendBlackListRun(sendListBlack);
                        }
//
                    } else {
                        ToastUtils.showToast("?????????????????????IMSI");
                    }

                } else {
                    ToastUtils.showToast("????????????????????????");
                }
                if (sendListBlack.size() == 0) {
//                        Set1StatusBar("???????????????????????????IMSI");
                    ToastUtils.showToast("?????????????????????IMSI");
                } else {
                    byte[] outputData = MainUtilsThread.hexStringToByteArray(location(sendListBlack.get(0).getImsi(), "04", sa, context, 1));
                    try {
                        socket = new DatagramSocket();
                        address = InetAddress.getByName(IP2);
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    } catch (SocketException e) {
                        e.printStackTrace();
                    }
                    ;
                    DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, address, 3345);
//                        DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, reIP, Integer.parseInt(et_port.getText().toString()));
                    Log.e("nzqsendstart1??????????????????", "run: sendsocket?????????" + outputPacket.getPort() + "Ip??????:" + outputPacket.getAddress().toString() + "??????:" + outputPacket.getData());
                    try {
                        //                                    socket.send(outputPacket);
                        DS.send(outputPacket);
//
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


            }
        }).start();

    }
}
