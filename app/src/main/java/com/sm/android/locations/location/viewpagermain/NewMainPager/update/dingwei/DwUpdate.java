package com.sm.android.locations.location.viewpagermain.NewMainPager.update.dingwei;

import android.app.Activity;
import android.content.Context;
import android.os.Message;

import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sm.android.locations.location.Activity.Main.Adapter.RyImsiAdapter;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.AddPararBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerAddParam;
import com.sm.android.locations.location.Utils.ToastUtils;
import com.sm.android.locations.location.initData.CallBackSetState;
import com.sm.android.locations.location.initData.CommandUtils;
import com.sm.android.locations.location.initData.MyLog;
import com.sm.android.locations.location.initData.TCPServer;
import com.sm.android.locations.location.sos.SOSActivity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DwUpdate {
    public static void upwifi(Message msg, TextView tv1_wifi, TextView tv1_type_dw, TextView tv1_td_dw, CallBackSetState callBackSetState) {
        String wifi = msg.getData().getString("msgWifi");
//                        Log.d(TAG, "handleMessa4age: " + wifi);
        if (wifi.equals("true")) {//无线正确
            tv1_wifi.setText("WIFI连接: 正常");
            if(!CommandUtils.type.equals("")){//设备连接成功;
                tv1_type_dw.setText("当前状态："+CommandUtils.sbZt);
            }else{//设备断开
                tv1_type_dw.setText("当前状态：未就绪");
            }
        }
        if (wifi.equals("false")) {//无线错误
            tv1_td_dw.setText("双工模式：");
            CommandUtils.type="";
            CommandUtils.type0303="";//wifi错误正在定位的imsi设置离线
            tv1_wifi.setText("WIFI连接:断开");
            tv1_type_dw.setText("当前状态：未就绪");
        }

    }

    // 更新状态
    public static void type(Context context, int Maintype, Message msg, TextView tv1_type_dw, TextView tv1_td_dw, String tf1) {

        if (Maintype == 0) {
            String zt1 = msg.getData().getString("zt1");
            if (zt1.equals("0")) {
                tv1_type_dw.setText("当前状态:" + "连接中...");
                tv1_td_dw.setText("双工模式:");
            }
            if (zt1.equals("1")) {
                tv1_type_dw.setText("当前状态:" + "连接失败");
                tv1_td_dw.setText("双工模式:");
//                sb1 = "连接失败";
//                Set1StatusBar("连接失败");
//                mysp1.setEnabled(true);

            }
            if (zt1.equals("2")) {
                tv1_type_dw.setText("当前状态:" + "就绪");//闲置状态


                //
//                            设备1状态
//                sb1 = "就绪";
////                            Set1StatusBar("就绪");
////                            if (itemtype1.equals())
//                mysp1.setEnabled(true);
//                tv_imsi1.setText("");
////                            DOWNPIN1="";//用于侦码IMSI 显示的下行频点
//                if (itemtype1.equals(TITLEZD)) {//如果是双频模式
//                    mysp1.setVisibility(View.GONE);
//                    tv_r1.setText("下行频点");
//
//                } else {
//                    mysp1.setVisibility(View.VISIBLE);
//                    tv_r1.setText("下行频点");
//                }
//
            }
        }
        if (Maintype == 2) {
            String zt1 = msg.getData().getString("zt1");
            if (zt1.equals("0")) {
                tv1_type_dw.setText("当前状态:" + "连接中...");
                tv1_td_dw.setText("双工模式:");
            }
            if (zt1.equals("1")) {
                tv1_type_dw.setText("当前状态:" + "连接失败");
                tv1_td_dw.setText("双工模式:");
//                sb1 = "连接失败";
//                Set1StatusBar("连接失败");
//                mysp1.setEnabled(true);

            }
            if (zt1.equals("2")) {
                tv1_type_dw.setText("当前状态:" + "就绪");//闲置状态

//
            }
        }
        if (Maintype == 1) {
            String zt1 = msg.getData().getString("zt1");
            if (TextUtils.isEmpty(zt1)){

            }else {
                if (zt1.equals("0")) {
                    tv1_type_dw.setText("当前状态:" + "连接中...");
                    tv1_td_dw.setText("双工模式:");
                }
                if (zt1.equals("1")) {
                    tv1_type_dw.setText("当前状态:" + "连接失败");
                    tv1_td_dw.setText("双工模式:");
//                sb1 = "连接失败";
//                Set1StatusBar("连接失败");
//                mysp1.setEnabled(true);

                }
                if (zt1.equals("2")) {
                    tv1_type_dw.setText("当前状态:" + "就绪");//闲置状态

//
                }
            }

        }
    }
    // 更新状态
    public static void typesos(Context context, int Maintype, Message msg, TextView tv1_type_dw, TextView tv1_td_dw, String tf1) {

        if (Maintype == 2) {
            String zt1 = msg.getData().getString("zt1");
            if (zt1.equals("0")) {
                tv1_type_dw.setText("当前状态:" + "连接中...");
                tv1_td_dw.setText("双工模式:");
            }
            if (zt1.equals("1")) {
                tv1_type_dw.setText("当前状态:" + "连接失败");
                tv1_td_dw.setText("双工模式:");
//                sb1 = "连接失败";
//                Set1StatusBar("连接失败");
//                mysp1.setEnabled(true);

            }
            if (zt1.equals("2")) {
                tv1_type_dw.setText("当前状态:" + "就绪");//闲置状态

//
            }
        }
        if (Maintype == 1) {
            String zt1 = msg.getData().getString("zt1");
            if (TextUtils.isEmpty(zt1)){

            }else {
                if (zt1.equals("0")) {
                    tv1_type_dw.setText("当前状态:" + "连接中...");
                    tv1_td_dw.setText("双工模式:");
                }
                if (zt1.equals("1")) {
                    tv1_type_dw.setText("当前状态:" + "连接失败");
                    tv1_td_dw.setText("双工模式:");
//                sb1 = "连接失败";
//                Set1StatusBar("连接失败");
//                mysp1.setEnabled(true);

                }
                if (zt1.equals("2")) {
                    tv1_type_dw.setText("当前状态:" + "就绪");//闲置状态

//
                }
            }

        }
    }
    // 更新状态
    public static void type2(Context context, int Maintype, Message msg, TextView tv1_type_dw, TextView tv1_td_dw, String tf1) {

        if (Maintype == 0) {
            String zt1 = msg.getData().getString("zt2");
            if (zt1.equals("0")) {
                tv1_type_dw.setText("当前状态:" + "连接中...");
                tv1_td_dw.setText("双工模式:");
            }
            if (zt1.equals("1")) {
                tv1_type_dw.setText("当前状态:" + "连接失败");
                tv1_td_dw.setText("双工模式:");
//                sb1 = "连接失败";
//                Set1StatusBar("连接失败");
//                mysp1.setEnabled(true);

            }
            if (zt1.equals("2")) {
                tv1_type_dw.setText("当前状态:" + "就绪");//闲置状态


//
            }
        }
        if (Maintype == 2) {
            String zt1 = msg.getData().getString("zt2");
            if (zt1.equals("0")) {
                tv1_type_dw.setText("当前状态:" + "连接中...");
                tv1_td_dw.setText("双工模式:");
            }
            if (zt1.equals("1")) {
                tv1_type_dw.setText("当前状态:" + "连接失败");
                tv1_td_dw.setText("双工模式:");
//                sb1 = "连接失败";
//                Set1StatusBar("连接失败");
//                mysp1.setEnabled(true);

            }
            if (zt1.equals("2")) {
                tv1_type_dw.setText("当前状态:" + "就绪");//闲置状态


            }
        }
        if (Maintype == 1) {
            String zt1 = msg.getData().getString("zt2");
            if (zt1.equals("0")) {
                tv1_type_dw.setText("当前状态:" + "连接中...");
                tv1_td_dw.setText("双工模式:");
            }
            if (zt1.equals("1")) {
                tv1_type_dw.setText("当前状态:" + "连接失败");
                tv1_td_dw.setText("双工模式:");
//                sb1 = "连接失败";
//                Set1StatusBar("连接失败");
//                mysp1.setEnabled(true);

            }
            if (zt1.equals("2")) {
                tv1_type_dw.setText("当前状态:" + "就绪");//闲置状态


            }
        }
    }
    // 更新状态
    public static void type2sos(Context context, int Maintype, Message msg, TextView tv1_type_dw, TextView tv1_td_dw, String tf1) {


        if (Maintype == 2) {
            String zt1 = msg.getData().getString("zt2");
            if (zt1.equals("0")) {
                tv1_type_dw.setText("当前状态:" + "连接中...");
                tv1_td_dw.setText("双工模式:");
            }
            if (zt1.equals("1")) {
                tv1_type_dw.setText("当前状态:" + "连接失败");
                tv1_td_dw.setText("双工模式:");
//                sb1 = "连接失败";
//                Set1StatusBar("连接失败");
//                mysp1.setEnabled(true);

            }
            if (zt1.equals("2")) {
                tv1_type_dw.setText("当前状态:" + "就绪");//闲置状态


            }
        }
        if (Maintype == 1) {
            String zt1 = msg.getData().getString("zt2");
            if (zt1.equals("0")) {
                tv1_type_dw.setText("当前状态:" + "连接中...");
                tv1_td_dw.setText("双工模式:");
            }
            if (zt1.equals("1")) {
                tv1_type_dw.setText("当前状态:" + "连接失败");
                tv1_td_dw.setText("双工模式:");
//                sb1 = "连接失败";
//                Set1StatusBar("连接失败");
//                mysp1.setEnabled(true);

            }
            if (zt1.equals("2")) {
                tv1_type_dw.setText("当前状态:" + "就绪");//闲置状态


            }
        }
    }
    /**
     * 更新IMSI
     *
     * @param ryIMSI
     * @param context
     * @param config
     * @param tv_imsi1_dw
     * @param tv_imsi2_dw
     */
    public static void ryIMSIUp(RecyclerView ryIMSI, Context context, RyImsiAdapter.IDialogPinConfig config, TextView tv_imsi1_dw, TextView tv_imsi2_dw) {
        List<AddPararBean> pararBeansList1 = new ArrayList<>();
        RyImsiAdapter ryImsiAdapter;
        DBManagerAddParam dbManagerAddParam = null;
        List<AddPararBean> dataAll;
        List<AddPararBean> listImsiListTrue;
        try {
            dbManagerAddParam = new DBManagerAddParam(context);
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
                    pararBeansList1 = listImsiListTrue;
                    ryImsiAdapter = new RyImsiAdapter(context, listImsiListTrue, list1size, config, tv_imsi1_dw, tv_imsi2_dw);//list是imsi列表选中的数据
                    ryIMSI.setAdapter(ryImsiAdapter);
                }

            }
            Log.d("addPararBeans", "init: " + dataAll);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

