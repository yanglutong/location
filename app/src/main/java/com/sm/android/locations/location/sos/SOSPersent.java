package com.sm.android.locations.location.sos;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.icu.text.DecimalFormat;
import android.os.Build;
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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManager01;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerAddParam;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerAddParamWhite;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerLog;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerPinConfig;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerZM;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerZMGK;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagersaopin;
import com.sm.android.locations.location.Utils.ToastUtils;
import com.sm.android.locations.location.initData.CallBackSetState;
import com.sm.android.locations.location.initData.CommandUtils;
import com.sm.android.locations.location.initData.MyLog;
import com.sm.android.locations.location.initData.PLMN;
import com.sm.android.locations.location.initData.SocketSend;
import com.sm.android.locations.location.initData.TCPServer;
import com.sm.android.locations.location.initData.dao.DBManagerDevice;
import com.sm.android.locations.location.initData.dao.DeviceBean;
import com.sm.android.locations.location.viewpagermain.NewMainPager.SendUtilsNew;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.sm.android.locations.location.Constant.Constant.CHONGDINGXIANGSET;
import static com.sm.android.locations.location.Constant.Constant.DOWNPIN1;
import static com.sm.android.locations.location.Constant.Constant.DOWNPIN2;
import static com.sm.android.locations.location.Constant.Constant.IP1;
import static com.sm.android.locations.location.Constant.Constant.IP2;
import static com.sm.android.locations.location.Utils.MainUtils.MainUtils.DS;
import static com.sm.android.locations.location.Utils.MainUtils.MainUtils.i;
import static com.sm.android.locations.location.Utils.MainUtils.MainUtils.location;
import static com.sm.android.locations.location.Utils.MainUtils.MainUtils2.toIMSI;
import static com.sm.android.locations.location.sos.MubiaopSOS.ZMBEANGKTONGJILISTCALL;

public class SOSPersent implements SOSVIEW.MainPresenter {
    public SOSVIEW.View viewS;
    private String TAG = "SOSPersent";

    public SOSPersent(@NonNull SOSVIEW.View view) {
        this.viewS = view;
        view.setPresenter(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void fengshanSet(boolean b, ImageView imageView, Context context) {
        Log.d("setFS", "setFS: " + b);
        if (b) {
            b = false;
            viewS.fengshanUp(b);
            SendUtilsNew.fs(b);
            Drawable drawable = context.getResources().getDrawable(R.mipmap.fengshan_toming);
            imageView.setBackground(drawable);
        } else {
            b = true;
            viewS.fengshanUp(b);
            SendUtilsNew.fs(b);
            Drawable drawable = context.getResources().getDrawable(R.mipmap.fengshan);
            imageView.setBackground(drawable);
        }
    }

    Dialog dialog;
    View inflate;

    @Override
    public void startSD(int device, String tf, Context context, String spinnerDown, String sbzhuangtai, String tv, TCPServer tcpServer) {
        Log.d(TAG, "startSD: " + device + "tf==" + tf + "---" + spinnerDown);
        if (device == 1) {
            if (tf.equals("")) {
                ToastUtils.showToast("设备未连接");
                return;
            }
            if (TextUtils.isEmpty(spinnerDown)) {
                ToastUtils.showToast("设备1下行频点不能为空");
                return;
            }
            if(!"WIFI连接:正常".equals(tv)){
                ToastUtils.showToast("请检查当前wifi连接");
                return;
            }


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
                pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(spinnerDown)); //查询对应的频点
                yy = pinConfigBeans.get(0).getYy();
                sb1zhishi = pinConfigBeans.get(0).getTf();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DBManagerAddParam dbManagerAddParam = null;
            List<AddPararBean> dataAll = null;//首页IMSI列表的数据
            List<AddPararBean> listImsiListTrue = null;//装载已经被选中的imsi
           /* if (sb1zhishi.equals(tf)) {//判断是tdd还是fdd 是同一个制式的情况下*/
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
//                            viewS.setpararBeansList1(listImsiListTrue);
                        }
                    }
                    if(listImsiListTrue.size()>0){
                        MyLog.e("uuu", "imsi :"+listImsiListTrue.get(0).getImsi());
                    }
                    List<AddPararBean> sendListBlack = null;
                    sendListBlack = new ArrayList<>();
//                        Log.d(TAG, "sendBlackList:移动 ");
                    if (listImsiListTrue.size() > 0 && listImsiListTrue != null) {
                        for (int i = 0; i < listImsiListTrue.size(); i++) {
                            MyLog.e("uuu", "选中的imsi运营商："+listImsiListTrue.get(i).getYy());
                            if (listImsiListTrue.get(i).getYy().equals(yy)) {
                                sendListBlack.add(listImsiListTrue.get(i));
                            }
                        }
                    }

                    if (sendListBlack.size() > 0 && sendListBlack != null) {
//                                Log.d(TAG, "sendBlackList: " + sendListBlack);
                        if (sendListBlack.size() > 20) {
                            ToastUtils.showToast("符合条件的黑名单列表大于20");
                        } else {
                            dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
                            inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
                            TextView tv_title = inflate.findViewById(R.id.tv_title);
//            String ip=IP1;

                            tv_title.setText("确定要启动设备1吗?");
//                ip=IP1;

                            Button bt_confirm = inflate.findViewById(R.id.bt_confirm);

                            final List<AddPararBean> finalSendListBlack = sendListBlack;
                            final List<AddPararBean> finalListImsiListTrue = listImsiListTrue;
                            bt_confirm.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    sendBlackListRun(finalSendListBlack, tf, spinnerDown, context, finalListImsiListTrue,tcpServer);
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
//
                    } else {
                        ToastUtils.showToast("没有符合条件的IMSI");
                    }

                } catch (Exception e) {
                }
            /*}*/
        }
    }

    @Override
    public void startSaoPin(int device, String tf, Context context, String spinnerDown, String sbzhuangtai, String tv, TCPServer tcpServer) {
        Log.d(TAG, "startSD: " + device + "tf==" + tf + "---" + spinnerDown);
        if (device == 1) {
            if (tf.equals("")) {
                ToastUtils.showToast("设备未连接");
                return;
            }
//            if(!"WIFI连接:正常".equals(tv)){
//                ToastUtils.showToast("请检查当前wifi连接");
//                return;
//            }


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
                pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(spinnerDown)); //查询对应的频点
                yy = pinConfigBeans.get(0).getYy();
                sb1zhishi = pinConfigBeans.get(0).getTf();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DBManagerAddParam dbManagerAddParam = null;
            List<AddPararBean> dataAll = null;//首页IMSI列表的数据
            List<AddPararBean> listImsiListTrue = null;//装载已经被选中的imsi
            /* if (sb1zhishi.equals(tf)) {//判断是tdd还是fdd 是同一个制式的情况下*/
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
//                            viewS.setpararBeansList1(listImsiListTrue);
                    }
                }
                if(listImsiListTrue.size()>0){
                    MyLog.e("uuu", "imsi :"+listImsiListTrue.get(0).getImsi());
                }
                List<AddPararBean> sendListBlack = null;
                sendListBlack = new ArrayList<>();
//                        Log.d(TAG, "sendBlackList:移动 ");
                if (listImsiListTrue.size() > 0 && listImsiListTrue != null) {
                    for (int i = 0; i < listImsiListTrue.size(); i++) {
                        MyLog.e("uuu", "选中的imsi运营商："+listImsiListTrue.get(i).getYy());
                        if (listImsiListTrue.get(i).getYy().equals(yy)) {
                            sendListBlack.add(listImsiListTrue.get(i));
                        }
                    }
                }
                if (sendListBlack.size() > 0 && sendListBlack != null) {
//                                Log.d(TAG, "sendBlackList: " + sendListBlack);
                    if (sendListBlack.size() > 20) {
                        ToastUtils.showToast("符合条件的黑名单列表大于20");
                    } else {
                        final List<AddPararBean> finalSendListBlack = sendListBlack;
                        final List<AddPararBean> finalListImsiListTrue = listImsiListTrue;
                                sendBlackListRunSaoPin(finalSendListBlack, tf, spinnerDown, context, finalListImsiListTrue,tcpServer);
                    }
//
                } else {
                    ToastUtils.showToast("没有符合条件的IMSI");
                }

            } catch (Exception e) {
            }
            /*}*/
        }
    }

    @Override
    public void setZy(TCPServer tcpServer,String zy) {
        if(tcpServer!=null){
            tcpServer.sendPost(CommandUtils.setZy(zy));
        }
    }


    @Override
    public void buildSD(final String spinnerS1, final int i, final String sb1, final Context context,TCPServer tcpServer,List<AddPararBean> sendListBlack) {
        if (i == 1) {
                    List<PinConfigBean> pinConfigBeans = null;//查询频点的集合
                    DBManagerPinConfig dbManagerPinConfig = null;//频点配置类
                    try {
                        dbManagerPinConfig = new DBManagerPinConfig(context);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if (TextUtils.isEmpty(spinnerS1)) {
                        ToastUtils.showToast("设备1频点不能为空");
                        return;
                    }
                    try {
                        pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(spinnerS1)); //查询对应的频点
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
                        forid = dbManager01.forid(1);  //查询小区1
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if (pinConfigBeans == null) {
                      ToastUtils.showToast("频点配置错误");
                        return;
                    }
                    if (forid == null) {
                    ToastUtils.showToast("小区1配置错误");
                        return;
                    }
                    String s = setxq.setXq(
                            pinConfigBeans.get(0).getUp(),
                            pinConfigBeans.get(0).getDown(),
                            pinConfigBeans.get(0).getPlmn(),
                            pinConfigBeans.get(0).getBand(),
                            Integer.parseInt(forid.getPci()),
                            Integer.parseInt(forid.getTac()), Integer.parseInt(forid.getCid()));
                    MyLog.e("uuu", "sos   "+s);
                    MyLog.e("uuu", ""+pinConfigBeans.get(0).getUp()+
                            pinConfigBeans.get(0).getDown()+
                            pinConfigBeans.get(0).getPlmn()+
                            "band"+pinConfigBeans.get(0).getBand()+"pci"+
                            Integer.parseInt(forid.getPci())+
                            Integer.parseInt(forid.getTac())+ Integer.parseInt(forid.getCid()));
                    DOWNPIN1 = pinConfigBeans.get(0).getDown() + "";//将下行频点赋值给成员变量

            try {
                DBManagerDevice device = new DBManagerDevice(context);
                List<DeviceBean> list = device.getDeviceBeans();
                if(list.size()>0){//用户设置了接受增益就用用户的




                    String zy="35";//默认35增益
//                    if(!CommandUtils.zyType.equals("")){
//                        zy=CommandUtils.zyType;
//                    }
                    MyLog.e("SOSPersentdeviceBean", zy);
                    DeviceBean deviceBean = list.get(list.size()-1);
                    MyLog.e("SOSPersentdeviceBean", deviceBean.toString());


                    PLMN.setPlmns(new PLMN(pinConfigBeans.get(0).getPlmn(), pinConfigBeans.get(0).getDown() + "", pinConfigBeans.get(0).getUp() + "",
                            deviceBean.getTac(), deviceBean.getPci(), pinConfigBeans.get(0).getBand() + "", deviceBean.getCi()));//将要发送的小区参数保存起来用于判断是否设置成功





                    StringBuffer xq = CommandUtils.setXq(pinConfigBeans.get(0).getPlmn()+"", deviceBean.getTac()+"", pinConfigBeans.get(0).getDown()+"", pinConfigBeans.get(0).getUp()+"", deviceBean.getPci()+"", pinConfigBeans.get(0).getBand()+"", zy,deviceBean.getCi()+"");
                    String header = com.sm.android.locations.location.initData.MyUtils.getSocketHeader(com.sm.android.locations.location.initData.MyUtils.getToHexString(CommandUtils.xqType,xq.toString()));
                    //            //设置黑名单
                    List<AddPararBean> beans = com.sm.android.locations.location.initData.MyUtils.removeDuplicate2(sendListBlack);
                    StringBuffer stringBuffer = CommandUtils.setBlackList(beans);
                    CommandUtils.setBlackList= com.sm.android.locations.location.initData.MyUtils.getSocketHeader(com.sm.android.locations.location.initData.MyUtils.getToHexString(CommandUtils.blackType, stringBuffer.toString()));

                    MyLog.send(CommandUtils.xqType, header);//将发送的小区指令打log
//                    new SOSActivity().Set1StatusBar("小区参数下发");//提示语
                    tcpServer.sendPost(header);//设置工作参数
                }else{
                    String zy="35";//默认使用最高增益
//                    if(!CommandUtils.zyType.equals("")){
//                        zy=CommandUtils.zyType;
//                    }
                    MyLog.e("SOSPersentdeviceBean", zy);
                    PLMN.setPlmns(new PLMN(pinConfigBeans.get(0).getPlmn(), pinConfigBeans.get(0).getDown() + "", pinConfigBeans.get(0).getUp() + "",
                            forid.getTac(), forid.getPci(), pinConfigBeans.get(0).getBand() + "", forid.getCid()+""));//将要发送的小区参数保存起来用于判断是否设置成功


                    StringBuffer xq = CommandUtils.setXq(pinConfigBeans.get(0).getPlmn()+"", forid.getTac()+"", pinConfigBeans.get(0).getDown()+"", pinConfigBeans.get(0).getUp()+"", forid.getPci()+"", pinConfigBeans.get(0).getBand()+"", zy,forid.getCid()+"");
                    String header = com.sm.android.locations.location.initData.MyUtils.getSocketHeader(com.sm.android.locations.location.initData.MyUtils.getToHexString(CommandUtils.xqType,xq.toString()));
                    //            //设置黑名单
                    List<AddPararBean> beans = com.sm.android.locations.location.initData.MyUtils.removeDuplicate2(sendListBlack);
                    StringBuffer stringBuffer = CommandUtils.setBlackList(beans);
                    CommandUtils.setBlackList= com.sm.android.locations.location.initData.MyUtils.getSocketHeader(com.sm.android.locations.location.initData.MyUtils.getToHexString(CommandUtils.blackType, stringBuffer.toString()));

                    MyLog.send(CommandUtils.xqType, header);//将发送的小区指令打log
                    tcpServer.sendPost(header);//设置工作参数
//                    new SOSActivity().Set1StatusBar("小区参数下发");//提示语

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

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
                        ToastUtils.showToast("设备2频点不能为空");
                        return;
                    }
                    try {
                        pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(spinnerS1)); //查询对应的频点

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
                        forid = dbManager01.forid(2);  //查询小区1

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if (pinConfigBeans == null) {
//                    ToastUtils.showToast("频点配置错误");
//                    Set1StatusBar("频点配置错误");
//                        viewS.buildSdError("频点配置错误", 1);
                    }
                    if (forid == null) {
//                    ToastUtils.showToast("小区1配置错误");
//                    Set1StatusBar("小区1配置错误");
//                        viewS.buildSdError("小区1配置错误", 1);
                        return;
                    }
                    DatagramSocket socket = null;
                    InetAddress address = null;//你本机的ip地址
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
                    Log.e("startLocation1s建立小区1", "run: sendsocket端口号" + outputPacket.getPort() + "Ip地址:" + outputPacket.getAddress().toString() + "数据:" + outputPacket.getData());

                    try {
                        if (sb1.equals("定位中")) {

                        } else {
                            //                                    socket.send(outputPacket);
                            DS.send(outputPacket);
                            Log.e("socketstartLocation1s建立小区1", "run: sendsocket端口号" + outputPacket.getPort() + "Ip地址:" + outputPacket.getAddress().toString() + "数据:" + outputPacket.getData());

                        }

//                    interrupted();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }

    }
 @Override
    public void buildSDSaoPin(final String spinnerS1, final int i, final String sb1, final Context context,TCPServer tcpServer,List<AddPararBean> sendListBlack) {
        if (i == 1) {
                    List<PinConfigBean> pinConfigBeans = null;//查询频点的集合
                    DBManagerPinConfig dbManagerPinConfig = null;//频点配置类
                    try {
                        dbManagerPinConfig = new DBManagerPinConfig(context);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if (TextUtils.isEmpty(spinnerS1)) {
                        ToastUtils.showToast("设备1频点不能为空");
                        return;
                    }
                    try {
                        pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(spinnerS1)); //查询对应的频点
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
                        forid = dbManager01.forid(1);  //查询小区1
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if (pinConfigBeans == null) {
                      ToastUtils.showToast("频点配置错误");
                        return;
                    }
                    if (forid == null) {
                    ToastUtils.showToast("小区1配置错误");
                        return;
                    }
                    String s = setxq.setXq(
                            pinConfigBeans.get(0).getUp(),
                            pinConfigBeans.get(0).getDown(),
                            pinConfigBeans.get(0).getPlmn(),
                            pinConfigBeans.get(0).getBand(),
                            Integer.parseInt(forid.getPci()),
                            Integer.parseInt(forid.getTac()), Integer.parseInt(forid.getCid()));
                    MyLog.e("uuu", "sos   "+s);
                    MyLog.e("uuu", ""+pinConfigBeans.get(0).getUp()+
                            pinConfigBeans.get(0).getDown()+
                            pinConfigBeans.get(0).getPlmn()+
                            "band"+pinConfigBeans.get(0).getBand()+"pci"+
                            Integer.parseInt(forid.getPci())+
                            Integer.parseInt(forid.getTac())+ Integer.parseInt(forid.getCid()));
                    DOWNPIN1 = pinConfigBeans.get(0).getDown() + "";//将下行频点赋值给成员变量
            try {
                DBManagerDevice device = new DBManagerDevice(context);
                List<DeviceBean> list = device.getDeviceBeans();
                if(list.size()>0){//用户设置了接受增益就用用户的




                    String zy="35";//默认35增益
//                    if(!CommandUtils.zyType.equals("")){
//                        zy=CommandUtils.zyType;
//                    }
                    MyLog.e("SOSPersentdeviceBean", zy);
                    DeviceBean deviceBean = list.get(list.size()-1);
                    MyLog.e("SOSPersentdeviceBean", deviceBean.toString());


                    PLMN.setPlmns(new PLMN(pinConfigBeans.get(0).getPlmn(), pinConfigBeans.get(0).getDown() + "", pinConfigBeans.get(0).getUp() + "",
                            deviceBean.getTac(), deviceBean.getPci(), pinConfigBeans.get(0).getBand() + "", deviceBean.getCi()));//将要发送的小区参数保存起来用于判断是否设置成功





                    StringBuffer xq = CommandUtils.setXq(pinConfigBeans.get(0).getPlmn()+"", deviceBean.getTac()+"", pinConfigBeans.get(0).getDown()+"", pinConfigBeans.get(0).getUp()+"", deviceBean.getPci()+"", pinConfigBeans.get(0).getBand()+"", zy,deviceBean.getCi()+"");
                    String header = com.sm.android.locations.location.initData.MyUtils.getSocketHeader(com.sm.android.locations.location.initData.MyUtils.getToHexString(CommandUtils.xqType,xq.toString()));
                    //            //设置黑名单
                    List<AddPararBean> beans = com.sm.android.locations.location.initData.MyUtils.removeDuplicate2(sendListBlack);
                    StringBuffer stringBuffer = CommandUtils.setBlackList(beans);
                    CommandUtils.setBlackList= com.sm.android.locations.location.initData.MyUtils.getSocketHeader(com.sm.android.locations.location.initData.MyUtils.getToHexString(CommandUtils.blackType, stringBuffer.toString()));

                    MyLog.send(CommandUtils.xqType, header);//将发送的小区指令打log
//                    new SOSActivity().Set1StatusBar("小区参数下发");//提示语

                    tcpServer.sendPost(header);//设置工作参数
                }else{
                    String zy="35";//默认使用最高增益
//                    if(!CommandUtils.zyType.equals("")){
//                        zy=CommandUtils.zyType;
//                    }
                    MyLog.e("SOSPersentdeviceBean", zy);
                    PLMN.setPlmns(new PLMN(pinConfigBeans.get(0).getPlmn(), pinConfigBeans.get(0).getDown() + "", pinConfigBeans.get(0).getUp() + "",
                            forid.getTac(), forid.getPci(), pinConfigBeans.get(0).getBand() + "", forid.getCid()+""));//将要发送的小区参数保存起来用于判断是否设置成功


                    StringBuffer xq = CommandUtils.setXq(pinConfigBeans.get(0).getPlmn()+"", forid.getTac()+"", pinConfigBeans.get(0).getDown()+"", pinConfigBeans.get(0).getUp()+"", forid.getPci()+"", pinConfigBeans.get(0).getBand()+"", zy,forid.getCid()+"");
                    String header = com.sm.android.locations.location.initData.MyUtils.getSocketHeader(com.sm.android.locations.location.initData.MyUtils.getToHexString(CommandUtils.xqType,xq.toString()));
                    //            //设置黑名单
                    List<AddPararBean> beans = com.sm.android.locations.location.initData.MyUtils.removeDuplicate2(sendListBlack);
                    StringBuffer stringBuffer = CommandUtils.setBlackList(beans);
                    CommandUtils.setBlackList= com.sm.android.locations.location.initData.MyUtils.getSocketHeader(com.sm.android.locations.location.initData.MyUtils.getToHexString(CommandUtils.blackType, stringBuffer.toString()));

                    MyLog.send(CommandUtils.xqType, header);//将发送的小区指令打log
                    tcpServer.sendPost(header);//设置工作参数
//                    new SOSActivity().Set1StatusBar("小区参数下发");//提示语

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
                        ToastUtils.showToast("设备2频点不能为空");
                        return;
                    }
                    try {
                        pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(spinnerS1)); //查询对应的频点

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
                        forid = dbManager01.forid(2);  //查询小区1

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if (pinConfigBeans == null) {
//                    ToastUtils.showToast("频点配置错误");
//                    Set1StatusBar("频点配置错误");
//                        viewS.buildSdError("频点配置错误", 1);
                    }
                    if (forid == null) {
//                    ToastUtils.showToast("小区1配置错误");
//                    Set1StatusBar("小区1配置错误");
//                        viewS.buildSdError("小区1配置错误", 1);
                        return;
                    }
                    DatagramSocket socket = null;
                    InetAddress address = null;//你本机的ip地址
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
                    Log.e("startLocation1s建立小区1", "run: sendsocket端口号" + outputPacket.getPort() + "Ip地址:" + outputPacket.getAddress().toString() + "数据:" + outputPacket.getData());

                    try {
                        if (sb1.equals("定位中")) {

                        } else {
                            //                                    socket.send(outputPacket);
                            DS.send(outputPacket);
                            Log.e("socketstartLocation1s建立小区1", "run: sendsocket端口号" + outputPacket.getPort() + "Ip地址:" + outputPacket.getAddress().toString() + "数据:" + outputPacket.getData());

                        }

//                    interrupted();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }

    }

    @Override
    public void stopdw(int i, Context context, String sbzhuangtai,TCPServer tcpServer) {
//        if (TextUtils.isEmpty(sbzhuangtai)) {
//            ToastUtils.showToast("设备未连接");
//            return;
//        }
//        if (sbzhuangtai.equals("就绪")) {
//            ToastUtils.showToast("设备已在就绪状态");
//            return;
//        }
        dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
        inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
        TextView tv_title = inflate.findViewById(R.id.tv_title);
        String ip = IP1;
        if (i == 1) {
            tv_title.setText("确定要停止定位1吗?");
            ip = IP1;
        }
        if (i == 2) {
            tv_title.setText("确定要停止定位2吗?");
            ip = IP2;
        }
        Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
        final String finalIp = ip;
        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*开启射频 关*/
                tcpServer.sendPost(CommandUtils.getRF(0));//关闭射频
//                MainUtils.StopLocation(finalIp);
//                GFFLAG1 = 2;
//                MainUtils.OpenGF1(1, 2, handler);

                viewS.stopdwup(i);
                dialog.dismiss();
                dialog.cancel();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                DBManagerLog dbManagerLog = null;
                String string = "";

                string = "停止定位";

//                //退出日志
//                try {
//                    dbManagerLog = new DBManagerLog(context);
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//                LogBean logBean = new LogBean();
//                logBean.setAssociated(LoginUtils.getId(context) + "");//关联ID
//                logBean.setEvent(LoginUtils.setBase64(string));//登录事件
//                logBean.setSb(LoginUtils.setBase64("1"));
//                String format = sdf.format(new Date());//登录时间
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

            //此处显示侦码的imsi列表
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
//            ryZmAdapter = new RyZmAdapterdw(context, zmBeans, listsize);//list是imsi列表选中的数据
//            if (et_zhenmasearchdw.getText().length() == 0) {
//                        ry_zhenma.setAdapter(ryZmAdapter);
            if (zmBeans.size() > 6) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                linearLayoutManager.setStackFromEnd(true);//recyview显示最底部一条
                ry_zhenma.setLayoutManager(linearLayoutManager);
                tv_searchNumdw.setText("(" + zmBeans.size() + ")");
                ryZmAdapter = new RyZmAdapterdw(context, zmBeans, listsize);//list是imsi列表选中的数据
                ry_zhenma.setAdapter(ryZmAdapter);
            } else {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                linearLayoutManager.setStackFromEnd(false);//recyview显示最底部一条
                ry_zhenma.setLayoutManager(linearLayoutManager);
                tv_searchNumdw.setText("(" + zmBeans.size() + ")");
                ryZmAdapter = new RyZmAdapterdw(context, zmBeans, listsize);//list是imsi列表选中的数据
                ry_zhenma.setAdapter(ryZmAdapter);
            }

//            }

//                        ry_zhenma.scrollToPosition(zmBeans.size());
            if (msg.getData().getString("zb").equals("1")) {
                //如果是中标的不插入
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            Log.d(TAG, "sb1jlhandleMessage: " + sb2jl + "---");
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

    private void startAuto(String data, boolean b, TextToSpeech textToSpeech) {
        textToSpeech.setPitch(1.f);
        Log.d("wpnqq", "startAuto: " + b);

        // 设置语速
        textToSpeech.setSpeechRate(8.01f);
        textToSpeech.speak(data,//输入中文，若不支持的设备则不会读出来
                TextToSpeech.QUEUE_FLUSH, null);
    }

    //发送工作参数 设置小区
    private void sendBlackListRun(List<AddPararBean> sendListBlack, final String tf1, final String spinnerS1, final Context context, List<AddPararBean> listImsiListTrue,TCPServer tcpServer) {
        if (sendListBlack.size() == 0) {
            ToastUtils.showToast("没有符合条件的IMSI");
            return;
        }
        sendrun(new StringBuffer(), sendListBlack, tf1, spinnerS1, context, listImsiListTrue,tcpServer/*第一个参数是发送的黑名单数量*/);//开始发送
    }
    //发送工作参数 设置小区扫频模式
    private void sendBlackListRunSaoPin(List<AddPararBean> sendListBlack, final String tf1, final String spinnerS1, final Context context, List<AddPararBean> listImsiListTrue,TCPServer tcpServer) {
        if (sendListBlack.size() == 0) {
            ToastUtils.showToast("没有符合条件的IMSI");
            return;
        }
        sendrunSaoPin(new StringBuffer(), sendListBlack, tf1, spinnerS1, context, listImsiListTrue,tcpServer/*第一个参数是发送的黑名单数量*/);//开始发送
    }

    //设备1已开始发送
    private void sendrun2(final StringBuffer strData, final List<AddPararBean> sendListBlack, final String tf1, final String spinnerS1, final Context context, final List<AddPararBean> listImsiListTrue) {
//
        new Thread(new Runnable() {
            @Override
            public void run() {
                DatagramSocket socket = null;
                InetAddress address = null;//你本机的ip地址
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
                Log.e("nzqsendstart1Black", "run: sendsocket端口号" + outputPacket.getPort() + "Ip地址:" + outputPacket.getAddress().toString() + "数据:" + outputPacket.getData());
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

    //设备1定位模式 手动
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
                InetAddress address = null;//你本机的ip地址
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
                        pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(spinnerS1));//查询对应的频点
                        yy = pinConfigBeans.get(0).getYy();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
//                        Log.d(TAG, "sendBlackList:移动 ");
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
                            ToastUtils.showToast("符合条件的黑名单列表大于20");
                        } else {
//                                sendBlackListRun(sendListBlack);
                        }
//
                    } else {
                        ToastUtils.showToast("没有符合条件的IMSI");
                    }

                } else {
                    ToastUtils.showToast("请先设置下行频点");
                }
                if (sendListBlack.size() == 0) {
//                        Set1StatusBar("没有符合下行频点的IMSI");
                    ToastUtils.showToast("没有符合条件的IMSI");
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
                    Log.e("nzqsendstart1设置定位模式", "run: sendsocket端口号" + outputPacket.getPort() + "Ip地址:" + outputPacket.getAddress().toString() + "数据:" + outputPacket.getData());
                    try {
                        //                                    socket.send(outputPacket);
                        DS.send(outputPacket);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


            }
        }).start();

    }

    //设备1已开始发送
    private void sendrun(final StringBuffer strData, final List<AddPararBean> sendListBlack, final String tf1, final String spinnerS1, final Context context, final List<AddPararBean> listImsiListTrue,TCPServer tcpServer) {
        sb1Locations(sendListBlack, tf1, spinnerS1, context, listImsiListTrue,tcpServer);
    }
    //设备1扫频已开始发送
    private void sendrunSaoPin(final StringBuffer strData, final List<AddPararBean> sendListBlack, final String tf1, final String spinnerS1, final Context context, final List<AddPararBean> listImsiListTrue,TCPServer tcpServer) {
        sb1LocationSaopin(sendListBlack, tf1, spinnerS1, context, listImsiListTrue,tcpServer);
    }

    //设备1定位模式 手动
    private void sb1Locations(final List<AddPararBean> sendListBlack, final String tf1, final String spinnerS1, final Context context, final List<AddPararBean> listImsiListTrue,TCPServer tcpServer) {

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
                    pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(spinnerS1));//查询对应的频点
                    yy = pinConfigBeans.get(0).getYy();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
//                        Log.d(TAG, "sendBlackList:移动 ");
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
                        ToastUtils.showToast("符合条件的黑名单列表大于20");
                    } else {
//                                sendBlackListRun(sendListBlack);
                    }
//
                } else {
                    ToastUtils.showToast("没有符合条件的IMSI");
                }

            } else {
                ToastUtils.showToast("请先设置下行频点");
            }
            if (sendListBlack.size() == 0) {
//                        Set1StatusBar("没有符合下行频点的IMSI");
                ToastUtils.showToast("没有符合条件的IMSI");
            } else {//有和频点相符合的imsi就发送定位黑名单
//                byte[] outputData = MainUtilsThread.hexStringToByteArray(location(sendListBlack.get(0).getImsi(), "04", sa, context, 1));
//                try {
//                    socket = new DatagramSocket();
//                    address = InetAddress.getByName(IP1);
//                } catch (UnknownHostException e) {
//                    e.printStackTrace();
//                } catch (SocketException e) {
//                    e.printStackTrace();
//                }
//                ;
//                DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, address, 3345);
////                        DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, reIP, Integer.parseInt(et_port.getText().toString()));
//                Log.e("nzqsendstart1设置定位模式", "run: sendsocket端口号" + outputPacket.getPort() + "Ip地址:" + outputPacket.getAddress().toString() + "数据:" + outputPacket.getData());
//                try {
//                    //                                    socket.send(outputPacket);
//                    DS.send(outputPacket);
//                    Thread.sleep(4000);
                    buildSD(spinnerS1,1,"sb",context,tcpServer,sendListBlack);
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

            }


//        }).start();

    }

    //设备1自动扫频模式
    private void sb1LocationSaopin(final List<AddPararBean> sendListBlack, final String tf1, final String spinnerS1, final Context context, final List<AddPararBean> listImsiListTrue,TCPServer tcpServer) {

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
                    pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(spinnerS1));//查询对应的频点
                    yy = pinConfigBeans.get(0).getYy();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
//                        Log.d(TAG, "sendBlackList:移动 ");
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
                        ToastUtils.showToast("符合条件的黑名单列表大于20");
                    } else {
//                                sendBlackListRun(sendListBlack);
                    }
//
                } else {
                    ToastUtils.showToast("没有符合条件的IMSI");
                }

            } else {
                ToastUtils.showToast("请先设置下行频点");
            }
            if (sendListBlack.size() == 0) {
//                        Set1StatusBar("没有符合下行频点的IMSI");
                ToastUtils.showToast("没有符合条件的IMSI");
            } else {//有和频点相符合的imsi就发送定位黑名单


                    buildSDSaoPin(spinnerS1,1,"sb",context,tcpServer,sendListBlack);
            }



    }

    @Override
    public void setIMSIshow(Message msg, TextView tv_imsi1_dw) {
        String imsi1 = msg.getData().getString("imsi1");
        tv_imsi1_dw.setText(imsi1);
    }

    @Override
    public void setIMSIshow2(Message msg, TextView tv_imsi1_dw) {
        String imsi1 = msg.getData().getString("imsi2");
        tv_imsi1_dw.setText(imsi1);
    }

//    @Override
//    public void spbuilsshow(Context context, int device, int yy, String tf1, String tf2) {
//        if (device == 1) {
//            if (yy == 1) {//移动运营商
//                if (tf1.equals("TDD")) {
//
//                    try {
//                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
//                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
//                        List<String> listmun = new ArrayList<>();
//                        for (int j = 0; j < dataAll.size(); j++) {
//                            if (dataAll.get(j).getYy().equals("移动") && dataAll.get(j).isCheckbox() == true) {
//                                listmun.add(dataAll.get(j).getImsi());
//                            }
//
//                        }
////                        if (listmun.size() > 0) {
//                        MainUtils.start1SNF(IP1, Constant.SNFTDD);
//                        DBManagersaopin dbManagersaopin = null;
//                        try {
//                            dbManagersaopin = new DBManagersaopin(context);
//                        } catch (SQLException e) {
//                            e.printStackTrace();
//                        }
//                        try {
//                            List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
//                            if (saopinBeanList != null && saopinBeanList.size() > 0) {
////                                    Set1StatusBar("功放开启成功");
//                                saopinSend1(saopinBeanList, tf1, yy, context);
//                            } else {
//                                ToastUtils.showToast("当前没有频点");
//                            }
//
//                        } catch (SQLException e) {
//                            e.printStackTrace();
//                        }
////                        } else {
////                            ToastUtils.showToast("没有符合条件的IMSI");
////                        }
//                    } catch (SQLException throwables) {
//                        throwables.printStackTrace();
//                    }
//
//
//                }
//
//                if (tf1.equals("FDD")) {
//
//
//                    try {
//                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
//                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
//                        List<String> listmun = new ArrayList<>();
//                        for (int j = 0; j < dataAll.size(); j++) {
//                            if (dataAll.get(j).getYy().equals("移动") && dataAll.get(j).isCheckbox() == true) {
//                                listmun.add(dataAll.get(j).getImsi());
//                            }
//
//                        }
////                        if (listmun.size() > 0) {
//                        String titles = "";
//                        if (tf1.equals("TDD")) {
//                            titles = "FDD";
//                            MainUtils.start1SNF(IP1, Constant.SNFFDD);
//                        }
//                        if (tf1.equals("FDD")) {
//                            titles = "TDD";
//                            MainUtils.start1SNF(IP1, Constant.SNFTDD);
//                        }
//                        viewS.zhishiqiehuan(1, titles);
////                        } else {
////                            ToastUtils.showToast("没有符合条件的IMSI");
////                        }
//                    } catch (SQLException throwables) {
//                        throwables.printStackTrace();
//                    }
//
//                }
//
//            }
//            if (yy == 2) {
//                if (tf1.equals("TDD")) {
//                    try {
//                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
//                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
//                        List<String> listmun = new ArrayList<>();
//                        for (int j = 0; j < dataAll.size(); j++) {
//                            if (dataAll.get(j).getYy().equals("移动") && dataAll.get(j).isCheckbox() == true) {
//                                listmun.add(dataAll.get(j).getImsi());
//                            }
//
//                        }
////                        if (listmun.size() > 0) {
//                        String titles = "";
//                        if (tf1.equals("TDD")) {
//                            titles = "FDD";
//                            MainUtils.start1SNF(IP1, Constant.SNFFDD);
//                        }
//                        if (tf1.equals("FDD")) {
//                            titles = "TDD";
//                            MainUtils.start1SNF(IP1, Constant.SNFTDD);
//                        }
//                        viewS.zhishiqiehuan(1, titles);
////                        } else {
////                            ToastUtils.showToast("没有符合条件的IMSI");
////                        }
//                    } catch (SQLException throwables) {
//                        throwables.printStackTrace();
//                    }
//
//                }
//                if (tf1.equals("FDD")) {
//
//                    try {
//                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
//                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
//                        List<String> listmun = new ArrayList<>();
//                        for (int j = 0; j < dataAll.size(); j++) {
//                            if (dataAll.get(j).getYy().equals("移动") && dataAll.get(j).isCheckbox() == true) {
//                                listmun.add(dataAll.get(j).getImsi());
//                            }
//
//                        }
////                        if (listmun.size() > 0) {
//                        MainUtils.start1SNF(IP1, Constant.SNFFDD);
//                        DBManagersaopin dbManagersaopin = null;
//                        try {
//                            dbManagersaopin = new DBManagersaopin(context);
//                        } catch (SQLException e) {
//                            e.printStackTrace();
//                        }
//                        try {
//                            List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
//                            if (saopinBeanList != null && saopinBeanList.size() > 0) {
////                                    Set1StatusBar("功放开启成功");
//                                saopinSend1(saopinBeanList, tf1, yy, context);
//                            } else {
//                                ToastUtils.showToast("当前没有频点");
//                            }
//
//                        } catch (SQLException e) {
//                            e.printStackTrace();
//                        }
////                        } else {
////                            ToastUtils.showToast("没有符合条件的IMSI");
////                        }
//                    } catch (SQLException throwables) {
//                        throwables.printStackTrace();
//                    }
//
//                }
//            }
//            if (yy == 3) {
//                try {
//                    DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
//                    List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
//                    List<String> listmun = new ArrayList<>();
//                    for (int j = 0; j < dataAll.size(); j++) {
//                        if (dataAll.get(j).getYy().equals("联通") && dataAll.get(j).isCheckbox() == true) {
//                            listmun.add(dataAll.get(j).getImsi());
//                        }
//
//                    }
//                    if (tf1.equals("TDD")) {
////                        if (listmun.size() > 0) {
//
//                        String titles = "";
//                        if (tf1.equals("TDD")) {
//                            titles = "FDD";
//                            MainUtils.start1SNF(IP1, Constant.SNFFDD);
//                        }
//                        if (tf1.equals("FDD")) {
//                            titles = "TDD";
//                            MainUtils.start1SNF(IP1, Constant.SNFTDD);
//                        }
//                        viewS.zhishiqiehuan(1, titles);
////                        } else {
////                            ToastUtils.showToast("没有符合条件的IMSI");
////                        }
//                    }
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//
//                if (tf1.equals("FDD")) {
//                    try {
//                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
//                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
//                        List<String> listmun = new ArrayList<>();
//                        for (int j = 0; j < dataAll.size(); j++) {
//                            if (dataAll.get(j).getYy().equals("联通") && dataAll.get(j).isCheckbox() == true) {
//                                listmun.add(dataAll.get(j).getImsi());
//                            }
//
//                        }
////                        if (listmun.size() > 0) {
//                        MainUtils.start1SNF(IP1, Constant.SNFFDD);
//                        DBManagersaopin dbManagersaopin = null;
//                        try {
//                            dbManagersaopin = new DBManagersaopin(context);
//                        } catch (SQLException e) {
//                            e.printStackTrace();
//                        }
//                        try {
//                            List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
//                            if (saopinBeanList != null && saopinBeanList.size() > 0) {
////                                    Set1StatusBar("功放开启成功");
//                                saopinSend1(saopinBeanList, tf1, yy, context);
//                            } else {
//                                ToastUtils.showToast("当前没有频点");
//                            }
//
//                        } catch (SQLException e) {
//                            e.printStackTrace();
//                        }
////                        } else {
////                            ToastUtils.showToast("没有符合条件的IMSI");
////                        }
//                    } catch (SQLException throwables) {
//                        throwables.printStackTrace();
//                    }
//
//                }
//            }
//            if (yy == 4) {
//                if (tf1.equals("TDD")) {
//                    try {
//                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
//                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
//                        List<String> listmun = new ArrayList<>();
//                        for (int j = 0; j < dataAll.size(); j++) {
//                            if (dataAll.get(j).getYy().equals("电信") && dataAll.get(j).isCheckbox() == true) {
//                                listmun.add(dataAll.get(j).getImsi());
//                            }
//
//                        }
////                        if (listmun.size() > 0) {
//                        String titles = "";
//                        if (tf1.equals("TDD")) {
//                            titles = "FDD";
//                            MainUtils.start1SNF(IP1, Constant.SNFFDD);
//                        }
//                        if (tf1.equals("FDD")) {
//                            titles = "TDD";
//                            MainUtils.start1SNF(IP1, Constant.SNFTDD);
//                        }
//                        viewS.zhishiqiehuan(1, titles);
////                        } else {
////                            ToastUtils.showToast("没有符合条件的IMSI");
////                        }
//                    } catch (SQLException throwables) {
//                        throwables.printStackTrace();
//                    }
//
//                }
//                if (tf1.equals("FDD")) {
//                    try {
//                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
//                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
//                        List<String> listmun = new ArrayList<>();
//                        for (int j = 0; j < dataAll.size(); j++) {
//                            if (dataAll.get(j).getYy().equals("电信") && dataAll.get(j).isCheckbox() == true) {
//                                listmun.add(dataAll.get(j).getImsi());
//                            }
//
//                        }
////                        if (listmun.size() > 0) {
//                        MainUtils.start1SNF(IP1, Constant.SNFFDD);
//                        DBManagersaopin dbManagersaopin = null;
//                        try {
//                            dbManagersaopin = new DBManagersaopin(context);
//                        } catch (SQLException e) {
//                            e.printStackTrace();
//                        }
//                        try {
//                            List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
//                            if (saopinBeanList != null && saopinBeanList.size() > 0) {
////                                    Set1StatusBar("功放开启成功");
//                                saopinSend1(saopinBeanList, tf1, yy, context);
//                            } else {
//                                ToastUtils.showToast("当前没有频点");
//                            }
//
//                        } catch (SQLException e) {
//                            e.printStackTrace();
//                        }
////                        } else {
////                            ToastUtils.showToast("没有符合条件的IMSI");
////                        }
//                    } catch (SQLException throwables) {
//                        throwables.printStackTrace();
//                    }
//
//                }
//            }
//        }
//        //设备2
//        if (device == 2) {
//            if (yy == 1) {//移动运营商
//                if (tf2.equals("TDD")) {
//                    try {
//                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
//                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
//                        List<String> listmun = new ArrayList<>();
//                        for (int j = 0; j < dataAll.size(); j++) {
//                            if (dataAll.get(j).getYy().equals("移动") && dataAll.get(j).isCheckbox() == true) {
//                                listmun.add(dataAll.get(j).getImsi());
//                            }
//
//                        }
////                        if (listmun.size() > 0) {
//                        MainUtils.start1SNF(IP2, Constant.SNFTDD);
//                        DBManagersaopin dbManagersaopin = null;
//                        try {
//                            dbManagersaopin = new DBManagersaopin(context);
//                        } catch (SQLException e) {
//                            e.printStackTrace();
//                        }
//                        try {
//                            List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
//                            if (saopinBeanList != null && saopinBeanList.size() > 0) {
////                                    Set1StatusBar("功放开启成功");
//                                saopinSend2(saopinBeanList, tf2, yy, context);
//                            } else {
//                                ToastUtils.showToast("当前没有频点");
//                            }
//
//                        } catch (SQLException e) {
//                            e.printStackTrace();
//                        }
////                        } else {
////                            ToastUtils.showToast("没有符合条件的IMSI");
////                        }
//                    } catch (SQLException throwables) {
//                        throwables.printStackTrace();
//                    }
//
//                }
//
//                if (tf2.equals("FDD")) {
//                    try {
//                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
//                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
//                        List<String> listmun = new ArrayList<>();
//                        for (int j = 0; j < dataAll.size(); j++) {
//                            if (dataAll.get(j).getYy().equals("移动") && dataAll.get(j).isCheckbox() == true) {
//                                listmun.add(dataAll.get(j).getImsi());
//                            }
//
//                        }
////                        if (listmun.size() > 0) {
//                        String titles = "";
//                        if (tf2.equals("TDD")) {
//                            titles = "FDD";
//                            MainUtils.start1SNF(IP2, Constant.SNFFDD);
//                        }
//                        if (tf2.equals("FDD")) {
//                            titles = "TDD";
//                            MainUtils.start1SNF(IP2, Constant.SNFTDD);
//                        }
//                        viewS.zhishiqiehuan(2, titles);
////                        } else {
////                            ToastUtils.showToast("没有符合条件的IMSI");
////                        }
//                    } catch (SQLException throwables) {
//                        throwables.printStackTrace();
//                    }
//
//                }
//
//            }
//            if (yy == 2) {
//                if (tf2.equals("TDD")) {
//                    try {
//                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
//                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
//                        List<String> listmun = new ArrayList<>();
//                        for (int j = 0; j < dataAll.size(); j++) {
//                            if (dataAll.get(j).getYy().equals("移动") && dataAll.get(j).isCheckbox() == true) {
//                                listmun.add(dataAll.get(j).getImsi());
//                            }
//
//                        }
////                        if (listmun.size() > 0) {
//                        String titles = "";
//                        if (tf2.equals("TDD")) {
//                            titles = "FDD";
//                            MainUtils.start1SNF(IP2, Constant.SNFFDD);
//                        }
//                        if (tf2.equals("FDD")) {
//                            titles = "TDD";
//                            MainUtils.start1SNF(IP2, Constant.SNFTDD);
//                        }
//                        viewS.zhishiqiehuan(2, titles);
////                        } else {
////                            ToastUtils.showToast("没有符合条件的IMSI");
////                        }
//                    } catch (SQLException throwables) {
//                        throwables.printStackTrace();
//                    }
//
//                }
//                if (tf2.equals("FDD")) {
//                    try {
//                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
//                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
//                        List<String> listmun = new ArrayList<>();
//                        for (int j = 0; j < dataAll.size(); j++) {
//                            if (dataAll.get(j).getYy().equals("移动") && dataAll.get(j).isCheckbox() == true) {
//                                listmun.add(dataAll.get(j).getImsi());
//                            }
//
//                        }
////                        if (listmun.size() > 0) {
//                        MainUtils.start1SNF(IP2, Constant.SNFFDD);
//                        DBManagersaopin dbManagersaopin = null;
//                        try {
//                            dbManagersaopin = new DBManagersaopin(context);
//                        } catch (SQLException e) {
//                            e.printStackTrace();
//                        }
//                        try {
//                            List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
//                            if (saopinBeanList != null && saopinBeanList.size() > 0) {
////                                    Set1StatusBar("功放开启成功");
//                                saopinSend2(saopinBeanList, tf2, yy, context);
//                            } else {
//                                ToastUtils.showToast("当前没有频点");
//                            }
//
//                        } catch (SQLException e) {
//                            e.printStackTrace();
//                        }
////                        } else {
////                            ToastUtils.showToast("没有符合条件的IMSI");
////                        }
//                    } catch (SQLException throwables) {
//                        throwables.printStackTrace();
//                    }
//
//                }
//            }
//            if (yy == 3) {
//                if (tf2.equals("TDD")){//当前是TDD
//
//                }else {//当前是FDD
//
//                }
//                try {
//                    DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
//                    List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
//                    List<String> listmun = new ArrayList<>();
//                    for (int j = 0; j < dataAll.size(); j++) {
//                        if (dataAll.get(j).getYy().equals("联通") && dataAll.get(j).isCheckbox() == true) {
//                            listmun.add(dataAll.get(j).getImsi());
//                        }
//
//                    }
////                    if (tf2.equals("TDD")) {
//                    if (listmun.size() > 0) {
//
//                        String titles = "";
//                        if (tf2.equals("TDD")) {
//                            titles = "FDD";
//                            MainUtils.start1SNF(IP2, Constant.SNFFDD);
//                        }
////                        if (tf2.equals("FDD")) {
////                            titles = "TDD";
////                            MainUtils.start1SNF(IP2, Constant.SNFTDD);
////                        }
//                        viewS.zhishiqiehuan(2, titles);
////                        } else {
////                            ToastUtils.showToast("没有符合条件的IMSI");
////                        }
//                    }
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//                try {
//                    DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
//                    List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
//                    List<String> listmun = new ArrayList<>();
//                    for (int j = 0; j < dataAll.size(); j++) {
//                        if (dataAll.get(j).getYy().equals("联通") && dataAll.get(j).isCheckbox() == true) {
//                            listmun.add(dataAll.get(j).getImsi());
//                        }
//
//                    }
//                    if (tf2.equals("FDD")) {
////                        if (listmun.size() > 0) {
//
//                        MainUtils.start1SNF(IP2, Constant.SNFFDD);
//                        DBManagersaopin dbManagersaopin = null;
//                        try {
//                            dbManagersaopin = new DBManagersaopin(context);
//                        } catch (SQLException e) {
//                            e.printStackTrace();
//                        }
//                        try {
//                            List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
//                            if (saopinBeanList != null && saopinBeanList.size() > 0) {
////                                    Set1StatusBar("功放开启成功");
//                                saopinSend2(saopinBeanList, tf2, yy, context);
//                            } else {
//                                ToastUtils.showToast("当前没有频点");
//                            }
//
//                        } catch (SQLException e) {
//                            e.printStackTrace();
//                        }
////                        } else {
////                            ToastUtils.showToast("没有符合条件的IMSI");
////                        }
//                    }
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//
//            }
//            if (yy == 4) {
//                try {
//                    DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
//                    List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
//                    List<String> listmun = new ArrayList<>();
//                    for (int j = 0; j < dataAll.size(); j++) {
//                        if (dataAll.get(j).getYy().equals("电信") && dataAll.get(j).isCheckbox() == true) {
//                            listmun.add(dataAll.get(j).getImsi());
//                        }
//
//                    }
//                    if (tf2.equals("TDD")) {
////                        if (listmun.size() > 0) {
//
//                        String titles = "";
//                        if (tf2.equals("TDD")) {
//                            titles = "FDD";
//                            MainUtils.start1SNF(IP2, Constant.SNFFDD);
//                        }
//                        if (tf2.equals("FDD")) {
//                            titles = "TDD";
//                            MainUtils.start1SNF(IP2, Constant.SNFTDD);
//                        }
//                        viewS.zhishiqiehuan(2, titles);
////                        } else {
////                            ToastUtils.showToast("没有符合条件的IMSI");
////                        }
//                    }
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//                try {
//                    DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
//                    List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
//                    List<String> listmun = new ArrayList<>();
//                    for (int j = 0; j < dataAll.size(); j++) {
//                        if (dataAll.get(j).getYy().equals("电信") && dataAll.get(j).isCheckbox() == true) {
//                            listmun.add(dataAll.get(j).getImsi());
//                        }
//
//                    }
//                    if (tf2.equals("FDD")) {
////                        if (listmun.size() > 0) {
//
//                        MainUtils.start1SNF(IP2, Constant.SNFFDD);
//                        DBManagersaopin dbManagersaopin = null;
//                        try {
//                            dbManagersaopin = new DBManagersaopin(context);
//                        } catch (SQLException e) {
//                            e.printStackTrace();
//                        }
//                        try {
//                            List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
//                            if (saopinBeanList != null && saopinBeanList.size() > 0) {
////                                    Set1StatusBar("功放开启成功");
//                                saopinSend2(saopinBeanList, tf2, yy, context);
//                            } else {
//                                ToastUtils.showToast("当前没有频点");
//                            }
//
//                        } catch (SQLException e) {
//                            e.printStackTrace();
//                        }
////                        } else {
////                            ToastUtils.showToast("没有符合条件的IMSI");
////                        }
//                    }
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//
//            }
//        }
//    }

    @Override
    public void spbuilsshow(Context context, int device, int yy, String tf1, String tf2, CallBackSetState callBackSetState) {//如果是设备查看的话就不创建小区
        if (device == 1) {
            if (yy == 1) {//移动运营商
                if (tf1.equals("TDD")) {
                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("移动") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }
                        }

                        DBManagersaopin dbManagersaopin = null;
                        try {
                            dbManagersaopin = new DBManagersaopin(context);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        try {
                            List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
                            if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("功放开启成功");

                                CommandUtils.spbuilsshow=true;
                                saopinSend1(saopinBeanList, tf1, yy, context,callBackSetState);
                            } else {
                                ToastUtils.showToast("当前没有频点");
                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
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
                            if (dataAll.get(j).getYy().equals("移动") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
                        DBManagersaopin dbManagersaopin = null;
                        try {
                            dbManagersaopin = new DBManagersaopin(context);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        try {
                            List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
                            if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("功放开启成功");
                                CommandUtils.spbuilsshow=true;
                                saopinSend1(saopinBeanList, tf1, yy, context,null);
                            } else {
                                ToastUtils.showToast("当前没有频点");
                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
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
                            if (dataAll.get(j).getYy().equals("移动") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
                        DBManagersaopin dbManagersaopin = null;
                        try {
                            dbManagersaopin = new DBManagersaopin(context);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        try {
                            List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
                            if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("功放开启成功");
                                CommandUtils.spbuilsshow=true;
                                saopinSend1(saopinBeanList, tf1, yy, context,callBackSetState);
                            } else {
                                ToastUtils.showToast("当前没有频点");
                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
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
                            if (dataAll.get(j).getYy().equals("移动") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }

                        DBManagersaopin dbManagersaopin = null;
                        try {
                            dbManagersaopin = new DBManagersaopin(context);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        try {
                            List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
                            if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("功放开启成功");
                                CommandUtils.spbuilsshow=true;

                                saopinSend1(saopinBeanList, tf1, yy, context,callBackSetState);
                            } else {
                                ToastUtils.showToast("当前没有频点");
                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            }
            if (yy == 3) {
                MyLog.e("saopinBeanlist", "ff");

                if (tf1.equals("TDD")) {
                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("联通") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
                        DBManagersaopin dbManagersaopin = null;
                        try {
                            dbManagersaopin = new DBManagersaopin(context);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        try {
                            List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
                            MyLog.e("saopinBeanlist", saopinBeanList.size()+"  \r\n"+saopinBeanList.toString());
                            if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("功放开启成功");
                                CommandUtils.spbuilsshow=true;
                                saopinSend1(saopinBeanList, tf1, yy, context,callBackSetState);
                            } else {
                                ToastUtils.showToast("当前没有频点");
                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
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
                            if (dataAll.get(j).getYy().equals("联通") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
//
                        DBManagersaopin dbManagersaopin = null;
                        try {
                            dbManagersaopin = new DBManagersaopin(context);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        try {
                            List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
                            if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("功放开启成功");
                                CommandUtils.spbuilsshow=true;
                                saopinSend1(saopinBeanList, tf1, yy, context,callBackSetState);
                            } else {
                                ToastUtils.showToast("当前没有频点");
                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
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
                            if (dataAll.get(j).getYy().equals("电信") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
                        DBManagersaopin dbManagersaopin = null;
                        try {
                            dbManagersaopin = new DBManagersaopin(context);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        try {
                            List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
                            if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("功放开启成功");
                                CommandUtils.spbuilsshow=true;

                                saopinSend1(saopinBeanList, tf1, yy, context,callBackSetState);
                            } else {
                                ToastUtils.showToast("当前没有频点");
                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
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
                            if (dataAll.get(j).getYy().equals("电信") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }

                        DBManagersaopin dbManagersaopin = null;
                        try {
                            dbManagersaopin = new DBManagersaopin(context);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        try {
                            List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
                            if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("功放开启成功");
                                CommandUtils.spbuilsshow=true;
                                saopinSend1(saopinBeanList, tf1, yy, context,callBackSetState);
                            } else {
                                ToastUtils.showToast("当前没有频点");
                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            }
        }
        //设备2
        if (device == 2) {
            if (yy == 1) {//移动运营商
                if (tf2.equals("TDD")) {
                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("移动") && dataAll.get(j).isCheckbox() == true) {
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
                            List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
                            if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("功放开启成功");
                                saopinSend2(saopinBeanList, tf2, yy, context);
                            } else {
                                ToastUtils.showToast("当前没有频点");
                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
//                        } else {
//                            ToastUtils.showToast("没有符合条件的IMSI");
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
                            if (dataAll.get(j).getYy().equals("移动") && dataAll.get(j).isCheckbox() == true) {
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
//                            ToastUtils.showToast("没有符合条件的IMSI");
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
                            if (dataAll.get(j).getYy().equals("移动") && dataAll.get(j).isCheckbox() == true) {
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
//                            ToastUtils.showToast("没有符合条件的IMSI");
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
                            if (dataAll.get(j).getYy().equals("移动") && dataAll.get(j).isCheckbox() == true) {
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
                            List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
                            if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("功放开启成功");
                                saopinSend2(saopinBeanList, tf2, yy, context);
                            } else {
                                ToastUtils.showToast("当前没有频点");
                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
//                        } else {
//                            ToastUtils.showToast("没有符合条件的IMSI");
//                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            }
            if (yy == 3) {
                if (tf2.equals("TDD")) {
                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("联通") && dataAll.get(j).isCheckbox() == true) {
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
//                            ToastUtils.showToast("没有符合条件的IMSI");
//                        }
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
                            if (dataAll.get(j).getYy().equals("联通") && dataAll.get(j).isCheckbox() == true) {
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
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("功放开启成功");
                                    saopinSend2(saopinBeanList, tf2, yy, context);
                                } else {
                                    ToastUtils.showToast("当前没有频点");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
//                        } else {
//                            ToastUtils.showToast("没有符合条件的IMSI");
//                        }
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }


            }
            if (yy == 4) {
                if (tf2.equals("TDD")) {
                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("电信") && dataAll.get(j).isCheckbox() == true) {
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
//                            ToastUtils.showToast("没有符合条件的IMSI");
//                        }
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
                            if (dataAll.get(j).getYy().equals("电信") && dataAll.get(j).isCheckbox() == true) {
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
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("功放开启成功");
                                    saopinSend2(saopinBeanList, tf2, yy, context);
                                } else {
                                    ToastUtils.showToast("当前没有频点");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
//                        } else {
//                            ToastUtils.showToast("没有符合条件的IMSI");
//                        }
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }

            }
        }
    }

    private void saopinSend1(List<SaopinBean> saopinBeanList, String tf1, int yy, Context context,CallBackSetState c) {
//        Log.d(TAG, "listImsiListTruesad1: " + listImsiListTrue);
        List<Integer> list = new ArrayList<>();
        Log.d(TAG, "AsaopinSend1: " + saopinBeanList );
        DBManagersaopin dbManagersaopin = null;
        try {
            dbManagersaopin = new DBManagersaopin(context);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            saopinBeanList = dbManagersaopin.getStudent();
//            Log.d(TAG, "AsaopinSend1: " + saopinBeanList + "制式" + zs);
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
                    ToastUtils.showToast("扫频列表大于10条");
                } else {
                    ToastUtils.showToast("开始扫频");
                    //先发送最优频点
                    MyLog.e("SOSPresent", ""+list);
//                    MainUtils.sendspSocket(list, IP1);
                     SocketSend.getInstance().setSaoPin(list,c);
                    //0305最优频点 创建集合将最高并且相同的频点存储 在看0201里是否有想符合的频点
                    //有根据场强值来判断，如果有好几个相同的场强值就默认选择第一个
                    Log.d(TAG, "saopinSend1gk: " + list);
                }
            } else {
//                ToastUtils.showToast("当前没有" + zs + "的制式");
                ToastUtils.showToast("当前没有频点");
            }

        } else {
            ToastUtils.showToast("当前没有频点");
        }
    }

    private void saopinSend2(List<SaopinBean> saopinBeanList, String tf1, int yy, Context context) {
//        Log.d(TAG, "listImsiListTruesad1: " + listImsiListTrue);
        List<Integer> list = new ArrayList<>();
//        Log.d(TAG, "AsaopinSend1: " + saopinBeanList + "制式" + zs);
        DBManagersaopin dbManagersaopin = null;
        try {
            dbManagersaopin = new DBManagersaopin(context);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            saopinBeanList = dbManagersaopin.getStudent();
//            Log.d(TAG, "AsaopinSend1: " + saopinBeanList + "制式" + zs);
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
                    ToastUtils.showToast("扫频列表大于10条");
                } else {
                    MainUtils.sendspSocket(list, IP2);
//                    Log.d(TAG, "saopinSend1: " + list);
                }

            } else {
//                ToastUtils.showToast("当前没有" + zs + "的制式");

            }

        } else {
            ToastUtils.showToast("当前没有频点");
        }
    }

    @Override
    public void spbuils(Context context, int device, int yy, String tf1, String tf2,CallBackSetState callBackSetState) {
        if (device == 1) {
            if (yy == 1) {
                if (tf1.equals("TDD")) {//移动TDD

                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("移动") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
                        if (listmun.size() > 0) {
//                            MainUtils.start1SNF(IP1, Constant.SNFTDD);
                            DBManagersaopin dbManagersaopin = null;
                            try {
                                dbManagersaopin = new DBManagersaopin(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("功放开启成功");
                                    CommandUtils.spbuilsshow=false;
                                    saopinSend1(saopinBeanList, tf1, yy, context,callBackSetState);
                                } else {
                                    ToastUtils.showToast("当前没有频点");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else {
                            ToastUtils.showToast("没有符合条件的IMSI");
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }


                }

                if (tf1.equals("FDD")) {//移动FDD
                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("移动") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
                        if (listmun.size() > 0) {

//                            MainUtils.start1SNF(IP1, Constant.SNFTDD);
                            DBManagersaopin dbManagersaopin = null;
                            try {
                                dbManagersaopin = new DBManagersaopin(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("功放开启成功");
                                    CommandUtils.spbuilsshow=false;
                                    saopinSend1(saopinBeanList, tf1, yy, context,callBackSetState);
                                } else {
                                    ToastUtils.showToast("当前没有频点");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

//                            String titles = "";
//                            if (tf1.equals("TDD")) {
//                                titles = "FDD";
//                                MainUtils.start1SNF(IP1, Constant.SNFFDD);
//                            }
//                            if (tf1.equals("FDD")) {
//                                titles = "TDD";
//                                MainUtils.start1SNF(IP1, Constant.SNFTDD);
//                            }
//                            viewS.zhishiqiehuan(1, titles);
                        } else {
                            ToastUtils.showToast("没有符合条件的IMSI");
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
                            if (dataAll.get(j).getYy().equals("移动") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
                        if (listmun.size() > 0) {
//                            MainUtils.start1SNF(IP1, Constant.SNFTDD);
                            DBManagersaopin dbManagersaopin = null;
                            try {
                                dbManagersaopin = new DBManagersaopin(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("功放开启成功");
                                    CommandUtils.spbuilsshow=false;

                                    saopinSend1(saopinBeanList, tf1, yy, context,callBackSetState);
                                } else {
                                    ToastUtils.showToast("当前没有频点");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
//                            String titles = "";
////                            if (tf1.equals("TDD")) {
////                                titles = "FDD";
////                                MainUtils.start1SNF(IP1, Constant.SNFFDD);
////                            }
////                            if (tf1.equals("FDD")) {
////                                titles = "TDD";
////                                MainUtils.start1SNF(IP1, Constant.SNFTDD);
////                            }
////                            viewS.zhishiqiehuan(1, titles);
                        } else {
                            ToastUtils.showToast("没有符合条件的IMSI");
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
                            if (dataAll.get(j).getYy().equals("移动") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
                        if (listmun.size() > 0) {
//                            MainUtils.start1SNF(IP1, Constant.SNFFDD);
                            DBManagersaopin dbManagersaopin = null;
                            try {
                                dbManagersaopin = new DBManagersaopin(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("功放开启成功");
                                    CommandUtils.spbuilsshow=false;
                                    saopinSend1(saopinBeanList, tf1, yy, context,callBackSetState);
                                } else {
                                    ToastUtils.showToast("当前没有频点");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else {
                            ToastUtils.showToast("没有符合条件的IMSI");
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
                        if (dataAll.get(j).getYy().equals("联通") && dataAll.get(j).isCheckbox() == true) {
                            listmun.add(dataAll.get(j).getImsi());
                        }

                    }
                    if (tf1.equals("TDD")) {
                        if (listmun.size() > 0) {
//                            MainUtils.start1SNF(IP1, Constant.SNFTDD);
                            DBManagersaopin dbManagersaopin = null;
                            try {
                                dbManagersaopin = new DBManagersaopin(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("功放开启成功");
                                    CommandUtils.spbuilsshow=false;

                                    saopinSend1(saopinBeanList, tf1, yy, context,callBackSetState);
                                } else {
                                    ToastUtils.showToast("当前没有频点");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
//
//                            String titles = "";
//                            if (tf1.equals("TDD")) {
//                                titles = "FDD";
//                                MainUtils.start1SNF(IP1, Constant.SNFFDD);
//                            }
//                            if (tf1.equals("FDD")) {
//                                titles = "TDD";
//                                MainUtils.start1SNF(IP1, Constant.SNFTDD);
//                            }
//                            viewS.zhishiqiehuan(1, titles);
                        } else {
                            ToastUtils.showToast("没有符合条件的IMSI");
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
                            if (dataAll.get(j).getYy().equals("联通") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
                        if (listmun.size() > 0) {
//                            MainUtils.start1SNF(IP1, Constant.SNFFDD);
                            DBManagersaopin dbManagersaopin = null;
                            try {
                                dbManagersaopin = new DBManagersaopin(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("功放开启成功");
                                    CommandUtils.spbuilsshow=false;

                                    saopinSend1(saopinBeanList, tf1, yy, context,callBackSetState);
                                } else {
                                    ToastUtils.showToast("当前没有频点");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else {
                            ToastUtils.showToast("没有符合条件的IMSI");
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
                            if (dataAll.get(j).getYy().equals("电信") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
                        if (listmun.size() > 0) {
//                            MainUtils.start1SNF(IP1, Constant.SNFTDD);
                            DBManagersaopin dbManagersaopin = null;
                            try {
                                dbManagersaopin = new DBManagersaopin(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("功放开启成功");
                                    CommandUtils.spbuilsshow=false;

                                    saopinSend1(saopinBeanList, tf1, yy, context,callBackSetState);
                                } else {
                                    ToastUtils.showToast("当前没有频点");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
//                            String titles = "";
//                            if (tf1.equals("TDD")) {
//                                titles = "FDD";
//                                MainUtils.start1SNF(IP1, Constant.SNFFDD);
//                            }
//                            if (tf1.equals("FDD")) {
//                                titles = "TDD";
//                                MainUtils.start1SNF(IP1, Constant.SNFTDD);
//                            }
//                            viewS.zhishiqiehuan(1, titles);
                        } else {
                            ToastUtils.showToast("没有符合条件的IMSI");
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
                            if (dataAll.get(j).getYy().equals("电信") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
                        if (listmun.size() > 0) {
//                            MainUtils.start1SNF(IP1, Constant.SNFFDD);
                            DBManagersaopin dbManagersaopin = null;
                            try {
                                dbManagersaopin = new DBManagersaopin(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("功放开启成功");
                                    CommandUtils.spbuilsshow=false;

                                    saopinSend1(saopinBeanList, tf1, yy, context,callBackSetState);
                                } else {
                                    ToastUtils.showToast("当前没有频点");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else {
                            ToastUtils.showToast("没有符合条件的IMSI");
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            }
        }
        //设备2
        if (device == 2) {
            if (yy == 1) {//移动运营商
                if (tf2.equals("TDD")) {
                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("移动") && dataAll.get(j).isCheckbox() == true) {
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
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("功放开启成功");
                                    saopinSend2(saopinBeanList, tf2, yy, context);
                                } else {
                                    ToastUtils.showToast("当前没有频点");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else {
                            ToastUtils.showToast("没有符合条件的IMSI");
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
                            if (dataAll.get(j).getYy().equals("移动") && dataAll.get(j).isCheckbox() == true) {
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
                            ToastUtils.showToast("没有符合条件的IMSI");
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
                            if (dataAll.get(j).getYy().equals("移动") && dataAll.get(j).isCheckbox() == true) {
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
                            ToastUtils.showToast("没有符合条件的IMSI");
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
                            if (dataAll.get(j).getYy().equals("移动") && dataAll.get(j).isCheckbox() == true) {
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
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("功放开启成功");
                                    saopinSend2(saopinBeanList, tf2, yy, context);
                                } else {
                                    ToastUtils.showToast("当前没有频点");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else {
                            ToastUtils.showToast("没有符合条件的IMSI");
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
                        if (dataAll.get(j).getYy().equals("联通") && dataAll.get(j).isCheckbox() == true) {
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
                            ToastUtils.showToast("没有符合条件的IMSI");
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
                        if (dataAll.get(j).getYy().equals("联通") && dataAll.get(j).isCheckbox() == true) {
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
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("功放开启成功");
                                    saopinSend2(saopinBeanList, tf2, yy, context);
                                } else {
                                    ToastUtils.showToast("当前没有频点");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else {
                            ToastUtils.showToast("没有符合条件的IMSI");
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
                        if (dataAll.get(j).getYy().equals("电信") && dataAll.get(j).isCheckbox() == true) {
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
                            ToastUtils.showToast("没有符合条件的IMSI");
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
                        if (dataAll.get(j).getYy().equals("电信") && dataAll.get(j).isCheckbox() == true) {
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
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("功放开启成功");
                                    saopinSend2(saopinBeanList, tf2, yy, context);
                                } else {
                                    ToastUtils.showToast("当前没有频点");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else {
                            ToastUtils.showToast("没有符合条件的IMSI");
                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        }
    }



    //restart   1表示重启 0表示不重启
    @Override
    public void setStart(int device, final boolean b, final int maintype, final String sb1, String sb2, final String sp1, final String sp2, final Context context, final String tf1, final String tf2, int restart, boolean phoneFalg) {
        if (device == 1) {
            if (restart == 1) {
                if (maintype == 0) {
                    if (TextUtils.isEmpty(sb1)) {
                        ToastUtils.showToast("设备1未连接");
                        return;
                    }
                    if (sb1.equals("定位中")) {
                        ToastUtils.showToast("设备1定位中请停止定位");
                        return;
                    }

                    if (!b) {//手动
                        if (TextUtils.isEmpty(sp1)) {
                            ToastUtils.showToast("设备1下行频点不能为空");
                            return;
                        }
                        if (sb1.equals("就绪")) {
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
                                pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(sp1)); //查询对应的频点
                                yy = pinConfigBeans.get(0).getYy();
                                sb1zhishi = pinConfigBeans.get(0).getTf();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            if (sb1zhishi.equals(tf1)) {
                                DBManagerAddParam dbManagerAddParam = null;
                                List<AddPararBean> dataAll = null;//首页IMSI列表的数据
                                List<AddPararBean> listImsiListTrue = null;//装载已经被选中的imsi
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
//                                            viewS.setpararBeansList1(listImsiListTrue);
                                        }

                                    }
                                    List<AddPararBean> sendListBlack = null;
                                    sendListBlack = new ArrayList<>();
//                        Log.d(TAG, "sendBlackList:移动 ");
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
                                            ToastUtils.showToast("符合条件的黑名单列表大于20");
                                        } else {

                                            dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
                                            inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
                                            TextView tv_title = inflate.findViewById(R.id.tv_title);
//            String ip=IP1;

                                            tv_title.setText("确定要启动设备1吗?");
//                ip=IP1;

                                            Button bt_confirm = inflate.findViewById(R.id.bt_confirm);

                                            final List<AddPararBean> finalSendListBlack = sendListBlack;
                                            final List<AddPararBean> finalListImsiListTrue = listImsiListTrue;
                                            bt_confirm.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    sendBlackListRun(finalSendListBlack, tf1, sp1, context, finalListImsiListTrue,null);


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
//
                                    } else {

                                        ToastUtils.showToast("没有符合条件的IMSI");
                                    }

                                } catch (Exception e) {
                                }
                            } else {//制式不一致
//                                ToastUtils.showToast("设备1制式不一致");

                                dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
                                inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
                                TextView tv_title = inflate.findViewById(R.id.tv_title);
//            String ip=IP1;

                                tv_title.setText("设备1需要切换制式并重启?");
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
                                return;
                            }

                        } else {
                            ToastUtils.showToast("设备1不再就绪状态");
                            return;
                        }
                    } else {//自动
                        if (phoneFalg == false) {
//                            ToastUtils.showToast("自动");
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
                                pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(sp1)); //查询对应的频点
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
//                        Log.d(TAG, "sendBlackList:移动 ");
                                    if (listImsiListTrue.size() > 0 && listImsiListTrue != null) {
                                        for (int i = 0; i < listImsiListTrue.size(); i++) {
                                            if (listImsiListTrue.get(i).getYy().equals(yy)) {
                                                sendListBlack.add(listImsiListTrue.get(i));
                                            }
                                        }
                                    }
                                    if (sendListBlack.size() == 0 && sendListBlack == null) {
                                        ToastUtils.showToast("符合条件的黑名单列表大于20");
                                    } else {
                                        if (sendListBlack.size() > 20) {
                                            ToastUtils.showToast("没有符合条件的IMSI");
                                        } else {
                                            sendBlackListRun(sendListBlack, tf1, sp1, context, listImsiListTrue,null);

                                        }
                                    }

                                } else {
                                    ToastUtils.showToast("没有符合条件的IMSI");
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
                if (sb1.equals("就绪")) {
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
                        pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(sp1)); //查询对应的频点
                        yy = pinConfigBeans.get(0).getYy();
                        sb1zhishi = pinConfigBeans.get(0).getTf();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if (sb1zhishi.equals(tf1)) {
                        DBManagerAddParam dbManagerAddParam = null;
                        List<AddPararBean> dataAll = null;//首页IMSI列表的数据
                        List<AddPararBean> listImsiListTrue = null;//装载已经被选中的imsi
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
//                                    viewS.setpararBeansList1(listImsiListTrue);
                                }

                            }
                            List<AddPararBean> sendListBlack = null;
                            sendListBlack = new ArrayList<>();
//                        Log.d(TAG, "sendBlackList:移动 ");
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
                                    ToastUtils.showToast("符合条件的黑名单列表大于20");
                                } else {
                                    final List<AddPararBean> finalSendListBlack = sendListBlack;
                                    final List<AddPararBean> finalListImsiListTrue = listImsiListTrue;

                                    sendBlackListRun(finalSendListBlack, tf1, sp1, context, finalListImsiListTrue,null);


                                }
//
                            } else {

                                ToastUtils.showToast("没有符合条件的IMSI");
                            }

                        } catch (Exception e) {
                        }
                    } else {//制式不一致
//                        ToastUtils.showToast("设备1制式不一致");
                        dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
                        inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
                        TextView tv_title = inflate.findViewById(R.id.tv_title);
//            String ip=IP1;

                        tv_title.setText("设备1需要切换制式并重启?");
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

                        return;
                    }

                } else {
                    ToastUtils.showToast("设备1不再就绪状态");
                    return;
                }
            }

        }

        //设备2
//        if (device == 2) {
//            if (restart == 1) {
//                if (maintype == 0) {
//                    if (TextUtils.isEmpty(sb2)) {
//                        ToastUtils.showToast("设备2未连接");
//                        return;
//                    }
//                    if (sb2.equals("定位中")) {
//                        ToastUtils.showToast("设备2定位中请停止定位");
//                        return;
//                    }
//
//                    if (!b) {//手动
//                        if (TextUtils.isEmpty(sp2)) {
//                            ToastUtils.showToast("设备2下行频点不能为空");
//                            return;
//                        }
//                        if (sb2.equals("就绪")) {
//                            String yy = "";
//                            String sb2zhishi = "";
//                            List<PinConfigBean> pinConfigBeans = null;
//                            DBManagerPinConfig dbManagerPinConfig = null;
//                            try {
//                                dbManagerPinConfig = new DBManagerPinConfig(context);
//                            } catch (SQLException e) {
//                                e.printStackTrace();
//                            }
//                            try {
//                                pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(sp2)); //查询对应的频点
//                                yy = pinConfigBeans.get(0).getYy();
//                                sb2zhishi = pinConfigBeans.get(0).getTf();
//                            } catch (SQLException e) {
//                                e.printStackTrace();
//                            }
//                            if (sb2zhishi.equals(tf2)) {
//                                DBManagerAddParam dbManagerAddParam = null;
//                                List<AddPararBean> dataAll = null;//首页IMSI列表的数据
//                                List<AddPararBean> listImsiListTrue = null;//装载已经被选中的imsi
//                                try {
//                                    try {
//                                        dbManagerAddParam = new DBManagerAddParam(context);
//                                    } catch (SQLException e) {
//                                        e.printStackTrace();
//                                    }
//                                    try {
//                                        dataAll = dbManagerAddParam.getDataAll();
//                                    } catch (SQLException e) {
//                                        e.printStackTrace();
//                                    }
//                                    listImsiListTrue = new ArrayList<>();
//                                    if (dataAll.size() > 0) {
//                                        for (int i = 0; i < dataAll.size(); i++) {
//                                            if (dataAll.get(i).isCheckbox() == true) {
//                                                dataAll.get(i).setSb("");
//                                                listImsiListTrue.add(dataAll.get(i));
//                                            }
//                                        }
//                                        List<Integer> list1size = new ArrayList<>();
//                                        if (listImsiListTrue.size() > 0) {
//                                            for (int i = 1; i < listImsiListTrue.size() + 1; i++) {
//
//                                                list1size.add(i);
//
//                                            }
////                                            viewS.setpararBeansList1(listImsiListTrue);
//                                        }
//
//                                    }
//                                    List<AddPararBean> sendListBlack = null;
//                                    sendListBlack = new ArrayList<>();
////                        Log.d(TAG, "sendBlackList:移动 ");
//                                    if (listImsiListTrue.size() > 0 && listImsiListTrue != null) {
//                                        for (int i = 0; i < listImsiListTrue.size(); i++) {
//                                            if (listImsiListTrue.get(i).getYy().equals(yy)) {
//                                                sendListBlack.add(listImsiListTrue.get(i));
//                                            }
//                                        }
//                                    }
//
//                                    if (sendListBlack.size() > 0 && sendListBlack != null) {
////                                Log.d(TAG, "sendBlackList: " + sendListBlack);
//                                        if (sendListBlack.size() > 20) {
//                                            ToastUtils.showToast("符合条件的黑名单列表大于20");
//                                        } else {
//
//                                            dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
//                                            inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
//                                            TextView tv_title = inflate.findViewById(R.id.tv_title);
////            String ip=IP1;
//
//                                            tv_title.setText("确定要启动设备2吗?");
////                ip=IP1;
//
//                                            Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
//
//                                            final List<AddPararBean> finalSendListBlack = sendListBlack;
//                                            final List<AddPararBean> finalListImsiListTrue = listImsiListTrue;
//                                            bt_confirm.setOnClickListener(new View.OnClickListener() {
//                                                @Override
//                                                public void onClick(View view) {
//                                                    sendBlackListRun2(finalSendListBlack, tf2, sp2, context, finalListImsiListTrue);
//
//
//                                                    dialog.dismiss();
//                                                    dialog.cancel();
//
//                                                }
//                                            });
//                                            Button bt_cancel = inflate.findViewById(R.id.bt_cancel);
//                                            bt_cancel.setOnClickListener(new View.OnClickListener() {
//                                                @Override
//                                                public void onClick(View view) {
//                                                    dialog.dismiss();
//                                                    dialog.cancel();
//                                                }
//                                            });
//                                            dialog.setCanceledOnTouchOutside(false);
//                                            dialog.setContentView(inflate);
//                                            //获取当前Activity所在的窗体
//                                            Window dialogWindow = dialog.getWindow();
//                                            //设置Dialog从窗体底部弹出
//                                            dialogWindow.setGravity(Gravity.CENTER);
//                                            //获得窗体的属性
//                                            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
////                           lp.y = 20;//设置Dialog距离底部的距离
////                          将属性设置给窗体
//                                            dialogWindow.setAttributes(lp);
//                                            dialog.show();//显示对话框
//                                        }
////
//                                    } else {
//
//                                        ToastUtils.showToast("没有符合条件的IMSI");
//                                    }
//
//                                } catch (Exception e) {
//                                }
//                            } else {//制式不一致
////                                ToastUtils.showToast("设备1制式不一致");
//
//                                dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
//                                inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
//                                TextView tv_title = inflate.findViewById(R.id.tv_title);
////            String ip=IP1;
//
//                                tv_title.setText("设备2需要切换制式并重启?");
////                ip=IP1;
//
//                                Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
//
//                                bt_confirm.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View view) {
//                                        viewS.zhishiqiehuan(2, tf2);
//
//                                        dialog.dismiss();
//                                        dialog.cancel();
//
//                                    }
//                                });
//                                Button bt_cancel = inflate.findViewById(R.id.bt_cancel);
//                                bt_cancel.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View view) {
//                                        dialog.dismiss();
//                                        dialog.cancel();
//                                    }
//                                });
//                                dialog.setCanceledOnTouchOutside(false);
//                                dialog.setContentView(inflate);
//                                //获取当前Activity所在的窗体
//                                Window dialogWindow = dialog.getWindow();
//                                //设置Dialog从窗体底部弹出
//                                dialogWindow.setGravity(Gravity.CENTER);
//                                //获得窗体的属性
//                                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
////                           lp.y = 20;//设置Dialog距离底部的距离
////                          将属性设置给窗体
//                                dialogWindow.setAttributes(lp);
//                                dialog.show();//显示对话框
//                                return;
//                            }
//
//                        } else {
//                            ToastUtils.showToast("设备2不再就绪状态");
//                            return;
//                        }
//                    } else {//自动
////                        ToastUtils.showToast("自动");
////                        viewS.zidongsaopinjianlixiaoqu(device);
//
//                        if (phoneFalg == false) {
////                            ToastUtils.showToast("自动");
//                            viewS.zidongsaopinjianlixiaoqu(device);
//                        } else {
//                            String yy = "";
//                            List<PinConfigBean> pinConfigBeans = null;
//                            DBManagerPinConfig dbManagerPinConfig = null;
//                            try {
//                                dbManagerPinConfig = new DBManagerPinConfig(context);
//                            } catch (SQLException e) {
//                                e.printStackTrace();
//                            }
//                            try {
//                                pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(sp2)); //查询对应的频点
//                                yy = pinConfigBeans.get(0).getYy();
//
//                            } catch (SQLException e) {
//                                e.printStackTrace();
//                            }
//                            List<AddPararBean> listImsiListTrue = null;
//                            List<AddPararBean> dataAll = null;
//                            try {
//                                DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
//                                dataAll = dbManagerAddParam.getDataAll();
//                                listImsiListTrue = new ArrayList<>();
//                                if (dataAll.size() > 0) {
//                                    for (int i = 0; i < dataAll.size(); i++) {
//                                        if (dataAll.get(i).isCheckbox() == true) {
//                                            dataAll.get(i).setSb("");
//                                            listImsiListTrue.add(dataAll.get(i));
//                                        }
//                                    }
//                                    List<Integer> list1size = new ArrayList<>();
//                                    if (listImsiListTrue.size() > 0) {
//                                        for (int i = 1; i < listImsiListTrue.size() + 1; i++) {
//                                            list1size.add(i);
//                                        }
//
//                                    }
//                                    List<AddPararBean> sendListBlack = null;
//                                    sendListBlack = new ArrayList<>();
////                        Log.d(TAG, "sendBlackList:移动 ");
//                                    if (listImsiListTrue.size() > 0 && listImsiListTrue != null) {
//                                        for (int i = 0; i < listImsiListTrue.size(); i++) {
//                                            if (listImsiListTrue.get(i).getYy().equals(yy)) {
//                                                sendListBlack.add(listImsiListTrue.get(i));
//                                            }
//                                        }
//                                    }
//                                    if (sendListBlack.size() == 0 && sendListBlack == null) {
//                                        ToastUtils.showToast("符合条件的黑名单列表大于20");
//                                    } else {
//                                        if (sendListBlack.size() > 20) {
//                                            ToastUtils.showToast("没有符合条件的IMSI");
//                                        } else {
//                                            sendBlackListRun2(sendListBlack, tf2, sp2, context, listImsiListTrue);
//
//                                        }
//                                    }
//
//                                } else {
//                                    ToastUtils.showToast("没有符合条件的IMSI");
//                                }
//
//                            } catch (SQLException e) {
//                                e.printStackTrace();
//                            }
//
//
//                        }
//                    }
//                } else {
//                    return;
//                }
//            }
//
//
//            if (restart == 0) {
//                if (sb2.equals("就绪")) {
//                    String yy = "";
//                    String sb2zhishi = "";
//                    List<PinConfigBean> pinConfigBeans = null;
//                    DBManagerPinConfig dbManagerPinConfig = null;
//                    try {
//                        dbManagerPinConfig = new DBManagerPinConfig(context);
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                    try {
//                        pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(sp2)); //查询对应的频点
//                        yy = pinConfigBeans.get(0).getYy();
//                        sb2zhishi = pinConfigBeans.get(0).getTf();
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                    if (sb2zhishi.equals(tf2)) {
//                        DBManagerAddParam dbManagerAddParam = null;
//                        List<AddPararBean> dataAll = null;//首页IMSI列表的数据
//                        List<AddPararBean> listImsiListTrue = null;//装载已经被选中的imsi
//                        try {
//                            try {
//                                dbManagerAddParam = new DBManagerAddParam(context);
//                            } catch (SQLException e) {
//                                e.printStackTrace();
//                            }
//                            try {
//                                dataAll = dbManagerAddParam.getDataAll();
//                            } catch (SQLException e) {
//                                e.printStackTrace();
//                            }
//                            listImsiListTrue = new ArrayList<>();
//                            if (dataAll.size() > 0) {
//                                for (int i = 0; i < dataAll.size(); i++) {
//                                    if (dataAll.get(i).isCheckbox() == true) {
//                                        dataAll.get(i).setSb("");
//                                        listImsiListTrue.add(dataAll.get(i));
//                                    }
//                                }
//                                List<Integer> list1size = new ArrayList<>();
//                                if (listImsiListTrue.size() > 0) {
//                                    for (int i = 1; i < listImsiListTrue.size() + 1; i++) {
//
//                                        list1size.add(i);
//
//                                    }
////                                    viewS.setpararBeansList1(listImsiListTrue);
//                                }
//
//                            }
//                            List<AddPararBean> sendListBlack = null;
//                            sendListBlack = new ArrayList<>();
////                        Log.d(TAG, "sendBlackList:移动 ");
//                            if (listImsiListTrue.size() > 0 && listImsiListTrue != null) {
//                                for (int i = 0; i < listImsiListTrue.size(); i++) {
//                                    if (listImsiListTrue.get(i).getYy().equals(yy)) {
//                                        sendListBlack.add(listImsiListTrue.get(i));
//                                    }
//                                }
//                            }
//
//                            if (sendListBlack.size() > 0 && sendListBlack != null) {
////                                Log.d(TAG, "sendBlackList: " + sendListBlack);
//                                if (sendListBlack.size() > 20) {
//                                    ToastUtils.showToast("符合条件的黑名单列表大于20");
//                                } else {
//                                    final List<AddPararBean> finalSendListBlack = sendListBlack;
//                                    final List<AddPararBean> finalListImsiListTrue = listImsiListTrue;
//
//                                    sendBlackListRun2(finalSendListBlack, tf2, sp2, context, finalListImsiListTrue);
//
//
//                                }
////
//                            } else {
//
//                                ToastUtils.showToast("没有符合条件的IMSI");
//                            }
//
//                        } catch (Exception e) {
//                        }
//                    } else {//制式不一致
////                        ToastUtils.showToast("设备2制式不一致");
//                        dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
//                        inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
//                        TextView tv_title = inflate.findViewById(R.id.tv_title);
////            String ip=IP1;
//
//                        tv_title.setText("设备1需要切换制式并重启?");
////                ip=IP1;
//
//                        Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
//
//                        bt_confirm.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                viewS.zhishiqiehuan(2, tf2);
//
//                                dialog.dismiss();
//                                dialog.cancel();
//
//                            }
//                        });
//                        Button bt_cancel = inflate.findViewById(R.id.bt_cancel);
//                        bt_cancel.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                dialog.dismiss();
//                                dialog.cancel();
//                            }
//                        });
//                        dialog.setCanceledOnTouchOutside(false);
//                        dialog.setContentView(inflate);
//                        //获取当前Activity所在的窗体
//                        Window dialogWindow = dialog.getWindow();
//                        //设置Dialog从窗体底部弹出
//                        dialogWindow.setGravity(Gravity.CENTER);
//                        //获得窗体的属性
//                        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
////                           lp.y = 20;//设置Dialog距离底部的距离
////                          将属性设置给窗体
//                        dialogWindow.setAttributes(lp);
//                        dialog.show();//显示对话框
//
//                        return;
//                    }
//
//                } else {
//                    ToastUtils.showToast("设备2不再就绪状态");
//                    return;
//                }
//            }
//
//        }
    }

    @Override
    public void setStartYy(TCPServer tcpServer,int device, boolean b, String sb1, String sp1, Context context, String tf1, boolean phoneFalg) {
                    MyLog.e("南志强", "b: "+b);
                    MyLog.e("南志强", "sb1  "+sb1);
                    MyLog.e("南志强", "sp1  "+sp1);
                    MyLog.e("南志强", "tf1  "+tf1);
                    MyLog.e("南志强", "phoneFalg  "+phoneFalg);
                    if (!b) {//如果设备处于未就绪时 手动定位
                        if (TextUtils.isEmpty(sp1)) {
                            ToastUtils.showToast("设备1下行频点不能为空");
                            return;
                        }
                        if (sb1.equals("就绪")) {
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
                                pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(sp1)); //查询对应的频点
                                yy = pinConfigBeans.get(0).getYy();
                                sb1zhishi = pinConfigBeans.get(0).getTf();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            if (sb1zhishi.equals(tf1)) {
                                MyLog.e("制式jjjj", sb1zhishi+"-----------"+tf1);
                                DBManagerAddParam dbManagerAddParam = null;
                                List<AddPararBean> dataAll = null;//首页IMSI列表的数据
                                List<AddPararBean> listImsiListTrue = null;//装载已经被选中的imsi
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
//                                            viewS.setpararBeansList1(listImsiListTrue);
                                        }

                                    }
                                    List<AddPararBean> sendListBlack = null;
                                    sendListBlack = new ArrayList<>();
//                        Log.d(TAG, "sendBlackList:移动 ");
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
                                            ToastUtils.showToast("符合条件的黑名单列表大于20");
                                        } else {
                                            dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
                                            inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
                                            TextView tv_title = inflate.findViewById(R.id.tv_title);
//            String ip=IP1;

                                            tv_title.setText("确定要启动设备1吗?");
//                ip=IP1;

                                            Button bt_confirm = inflate.findViewById(R.id.bt_confirm);

                                            final List<AddPararBean> finalSendListBlack = sendListBlack;
                                            final List<AddPararBean> finalListImsiListTrue = listImsiListTrue;
                                            bt_confirm.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    sendBlackListRun(finalSendListBlack, tf1, sp1, context, finalListImsiListTrue,null);


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
//
                                    } else {
                                        ToastUtils.showToast("没有符合条件的IMSI");
                                    }

                                } catch (Exception e) {
                                }
                            } else {//制式不一致
//                                ToastUtils.showToast("设备1制式不一致");

                                dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
                                inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
                                TextView tv_title = inflate.findViewById(R.id.tv_title);
//            String ip=IP1;

                                tv_title.setText("设备1需要切换制式并重启?");
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
                                return;
                            }
                        } else {
                            ToastUtils.showToast("设备1不再就绪状态");
                            return;
                        }
                    } else {//自动
                        if (phoneFalg == false) {
//                            ToastUtils.showToast("自动");
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
                                pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(sp1)); //查询对应的频点
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
//                        Log.d(TAG, "sendBlackList:移动 ");
                                    if (listImsiListTrue.size() > 0 && listImsiListTrue != null) {
                                        for (int i = 0; i < listImsiListTrue.size(); i++) {
                                            if (listImsiListTrue.get(i).getYy().equals(yy)) {
                                                sendListBlack.add(listImsiListTrue.get(i));
                                            }
                                        }
                                    }
                                    if (sendListBlack.size() == 0 && sendListBlack == null) {
                                        ToastUtils.showToast("符合条件的黑名单列表大于20");
                                    } else {
                                        if (sendListBlack.size() > 20) {
                                            ToastUtils.showToast("没有符合条件的IMSI");
                                        } else {
                                            sendBlackListRun(sendListBlack, tf1, sp1, context, listImsiListTrue,tcpServer);
                                        }
                                    }

                                } else {
                                    ToastUtils.showToast("没有符合条件的IMSI");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                        }

                    }

//                if (sb1.equals("就绪")) {
//                    String yy = "";
//                    String sb1zhishi = "";
//                    List<PinConfigBean> pinConfigBeans = null;
//                    DBManagerPinConfig dbManagerPinConfig = null;
//                    try {
//                        dbManagerPinConfig = new DBManagerPinConfig(context);
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                    try {
//                        pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(sp1)); //查询对应的频点
//                        yy = pinConfigBeans.get(0).getYy();
//                        sb1zhishi = pinConfigBeans.get(0).getTf();
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                    if (sb1zhishi.equals(tf1)) {
//                        DBManagerAddParam dbManagerAddParam = null;
//                        List<AddPararBean> dataAll = null;//首页IMSI列表的数据
//                        List<AddPararBean> listImsiListTrue = null;//装载已经被选中的imsi
//                        try {
//                            try {
//                                dbManagerAddParam = new DBManagerAddParam(context);
//                            } catch (SQLException e) {
//                                e.printStackTrace();
//                            }
//                            try {
//                                dataAll = dbManagerAddParam.getDataAll();
//                            } catch (SQLException e) {
//                                e.printStackTrace();
//                            }
//                            listImsiListTrue = new ArrayList<>();
//                            if (dataAll.size() > 0) {
//                                for (int i = 0; i < dataAll.size(); i++) {
//                                    if (dataAll.get(i).isCheckbox() == true) {
//                                        dataAll.get(i).setSb("");
//                                        listImsiListTrue.add(dataAll.get(i));
//                                    }
//                                }
//                                List<Integer> list1size = new ArrayList<>();
//                                if (listImsiListTrue.size() > 0) {
//                                    for (int i = 1; i < listImsiListTrue.size() + 1; i++) {
//
//                                        list1size.add(i);
//
//                                    }
////                                    viewS.setpararBeansList1(listImsiListTrue);
//                                }
//
//                            }
//                            List<AddPararBean> sendListBlack = null;
//                            sendListBlack = new ArrayList<>();
////                        Log.d(TAG, "sendBlackList:移动 ");
//                            if (listImsiListTrue.size() > 0 && listImsiListTrue != null) {
//                                for (int i = 0; i < listImsiListTrue.size(); i++) {
//                                    if (listImsiListTrue.get(i).getYy().equals(yy)) {
//                                        sendListBlack.add(listImsiListTrue.get(i));
//                                    }
//                                }
//                            }
//
//                            if (sendListBlack.size() > 0 && sendListBlack != null) {
////                                Log.d(TAG, "sendBlackList: " + sendListBlack);
//                                if (sendListBlack.size() > 20) {
//                                    ToastUtils.showToast("符合条件的黑名单列表大于20");
//                                } else {
//                                    final List<AddPararBean> finalSendListBlack = sendListBlack;
//                                    final List<AddPararBean> finalListImsiListTrue = listImsiListTrue;
//
//                                    sendBlackListRun(finalSendListBlack, tf1, sp1, context, finalListImsiListTrue,null);
//
//
//                                }
////
//                            } else {
//
//                                ToastUtils.showToast("没有符合条件的IMSI");
//                            }
//
//                        } catch (Exception e) {
//                        }
//                    } else {//制式不一致
////                        ToastUtils.showToast("设备1制式不一致");
//                        dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
//                        inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
//                        TextView tv_title = inflate.findViewById(R.id.tv_title);
////            String ip=IP1;
//
//                        tv_title.setText("设备1需要切换制式并重启?");
////                ip=IP1;
//
//                        Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
//
//                        bt_confirm.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                viewS.zhishiqiehuan(1, tf1);
//
//                                dialog.dismiss();
//                                dialog.cancel();
//
//                            }
//                        });
//                        Button bt_cancel = inflate.findViewById(R.id.bt_cancel);
//                        bt_cancel.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                dialog.dismiss();
//                                dialog.cancel();
//                            }
//                        });
//                        dialog.setCanceledOnTouchOutside(false);
//                        dialog.setContentView(inflate);
//                        //获取当前Activity所在的窗体
//                        Window dialogWindow = dialog.getWindow();
//                        //设置Dialog从窗体底部弹出
//                        dialogWindow.setGravity(Gravity.CENTER);
//                        //获得窗体的属性
//                        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
////                           lp.y = 20;//设置Dialog距离底部的距离
////                          将属性设置给窗体
//                        dialogWindow.setAttributes(lp);
//                        dialog.show();//显示对话框
//
//                        return;
//                    }
//
//                } else {
//                    ToastUtils.showToast("设备1不再就绪状态");
//                    return;
//                }
    }

    /**
     * 扫频建立小区
     *
     * @param context
     * @param device
     * @param tf
     * @param down
     */
    @Override
    public void saopinjianlixiaoqu(Context context, int device, String tf, String down) {
        if (device == 1) {
            if (tf.equals("定位中")) {

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
                pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(down)); //查询对应的频点
                yy = pinConfigBeans.get(0).getYy();
                Log.d("qwqea", "saopinjianlixiaoqu: " + yy);
            } catch (SQLException e) {
                e.printStackTrace();
            }
//            Log.d(TAG, "sendBlackList:移动 ");
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
                    ToastUtils.showToast("符合条件的黑名单列表大于20");
                } else {
                    sendBlackListRun(sendListBlack, tf, down, context, listImsiListTrue,null);
                }
//
            } else {
//                viewS.MesageV(1);
                ToastUtils.showToast("没有符合条件的IMSI");
            }
        }

        //设备2
//        if (device == 2) {
//            if (tf.equals("定位中")) {
//
//                return;
//            }
//            String yy = "";
//            List<AddPararBean> sendListBlack = null;
//            List<PinConfigBean> pinConfigBeans = null;
//            sendListBlack = new ArrayList<>();
//            DBManagerPinConfig dbManagerPinConfig = null;
//            try {
//                dbManagerPinConfig = new DBManagerPinConfig(context);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            try {
//                pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(down)); //查询对应的频点
//                yy = pinConfigBeans.get(0).getYy();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
////            Log.d(TAG, "sendBlackList:移动 ");
//            List<AddPararBean> dataAll = null;
//            try {
//                DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
//                dataAll = dbManagerAddParam.getDataAll();
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            List<AddPararBean> listImsiListTrue = new ArrayList<>();
//            if (dataAll.size() > 0) {
//                for (int i = 0; i < dataAll.size(); i++) {
//                    if (dataAll.get(i).isCheckbox() == true) {
//                        listImsiListTrue.add(dataAll.get(i));
//                    }
//
//                }
//            }
//            if (listImsiListTrue.size() > 0 && listImsiListTrue != null) {
//                for (int i = 0; i < listImsiListTrue.size(); i++) {
//                    if (listImsiListTrue.get(i).getYy().equals(yy)) {
//                        sendListBlack.add(listImsiListTrue.get(i));
//                    }
//                }
//            }
//
//            if (sendListBlack.size() > 0 && sendListBlack != null) {
////                Log.d(TAG, "sendBlackList: " + sendListBlack);
//                if (sendListBlack.size() > 20) {
//                    ToastUtils.showToast("符合条件的黑名单列表大于20");
//                } else {
//                    sendBlackListRun2(sendListBlack, tf, down, context, listImsiListTrue);
//                }
////
//            } else {
////                viewS.MesageV(2);
//                ToastUtils.showToast("没有符合条件的IMSI");
//            }
//        }
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
        if (device == 11) {
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
        if (device == 22) {
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

    @Override
    public void setsosStart(Context context, String tf1, String tf2, String sb1, String sb2, int GuankongType) {
        if (GuankongType == 1) {//运营商1
            if (TextUtils.isEmpty(tf1)) {
                ToastUtils.showToast("设备1未连接");
                return;
            }
//            if (TextUtils.isEmpty(tf2)) {
//                ToastUtils.showToast("设备2未连接");
//                return;
//            }

            if (!sb1.equals("就绪")) {
                ToastUtils.showToast("设备1不在就绪状态");
                return;
            }
//            if (!sb2.equals("就绪")) {
//                ToastUtils.showToast("设备2不在就绪状态");
//                return;
//            }
            Log.d("setGKtart", "setGKtart: ");
            //---------------------------------确定要启动侦码轮循
            dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
            inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
            TextView tv_title = inflate.findViewById(R.id.tv_title);
            Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
            tv_title.setText("启动研判搜救吗?");
            bt_confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("setGKtart", "setGKtart:启动研判搜救吗? ");
                    try {
                        DBManagerAddParamWhite dbManagerAddParamA = new DBManagerAddParamWhite(context);
                        List<AddPararBeanWhite> dataAlla = dbManagerAddParamA.getDataAll();
                        List<AddPararBeanWhite> listImsiListTrue = new ArrayList<>();
                        Log.d("setGKtartaddPararBeans", "白名单所有--init: " + dataAlla.toString());

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

                                List<AddPararBeanWhite> sendwhitelist = new ArrayList<>();
                                for (int j = 0; j < listImsiListTrue.size(); j++) {
                                    if (listImsiListTrue.get(j).getYy().equals("移动")) {
                                        sendwhitelist.add(listImsiListTrue.get(j));
                                    }
                                }
                                Log.d("+sendwhitelist", "onClick: " + sendwhitelist);
                                if (sendwhitelist.size() > 0) {
                                    if (!tf1.equals("TDD")) {
                                        ToastUtils.showToast("设备1当前制式为FDD");
                                        viewS.gkqiehuan(tf1, 1);
//                return;
                                    }
//                                    if (!tf2.equals("FDD")) {
//                                        ToastUtils.showToast("设备2当前制式为TDD");
//
//                                        viewS.gkqiehuan(tf2, 2);
////                return;
//                                    }
//                                    if (!sb1.equals("就绪")) {
////                ToastUtils.showToast("设备1不在就绪状态");
//                                        return;
//                                    }
//                                    if (!sb2.equals("就绪")) {
////                ToastUtils.showToast("设备2不在就绪状态");
//                                        return;
//                                    }
                                    sendwhiteListRun(sendwhitelist, tf1, tf2, context, listImsiListTrue);//发送白名单  2个

                                } else {
                                    if (!tf1.equals("TDD")) {
                                        ToastUtils.showToast("设备1当前制式为FDD");
                                        viewS.gkqiehuan(tf1, 1);
//                return;
                                    }
//                                    if (!tf2.equals("FDD")) {
//                                        ToastUtils.showToast("设备2当前制式为TDD");
//                                        viewS.gkqiehuan(tf2, 2);
////                return;
//                                    }
                                    if (!sb1.equals("就绪")) {
//                ToastUtils.showToast("设备1不在就绪状态");
                                        return;
                                    }
//                                    if (!sb2.equals("就绪")) {
////                ToastUtils.showToast("设备2不在就绪状态");
//                                        return;
//                                    }
                                    sendwhiteListRunDELE(sendwhitelist, tf1, tf2, context, listImsiListTrue);//发送白名单  2个

                                }


                            } else {
//                                ToastUtils.showToast("没有选中的非控IMSI");

                                List<AddPararBeanWhite> sendwhitelist = new ArrayList<>();
                                sendwhiteListRunDELE(sendwhitelist, tf1, tf2, context, listImsiListTrue);//发送白名单  2个

                            }
                        } else {//白名单为空
                            if (tf1.equals("TDD") && tf2.equals("FDD")) {
                                List<AddPararBeanWhite> sendwhitelist = new ArrayList<>();
                                sendwhiteListRunDELE(sendwhitelist, tf1, tf2, context, listImsiListTrue);//发送白名单  2个
                            }
                            if (!tf1.equals("TDD")) {
                                ToastUtils.showToast("设备1当前制式为FDD");
                                viewS.gkqiehuan(tf1, 1);
//                return;
                            } else {
                                List<AddPararBeanWhite> sendwhitelist = new ArrayList<>();
                            }
//                            if (!tf2.equals("FDD")) {
//                                ToastUtils.showToast("设备2当前制式为TDD");
//                                viewS.gkqiehuan(tf2, 2);
////                return;
//                            } else {
//
//
//                            }
                            List<AddPararBeanWhite> sendwhitelist = new ArrayList<>();
                            sendwhiteListRunDELE(sendwhitelist, tf1, tf2, context, listImsiListTrue);//发送白名单  2个
                        }
                        Log.d("setGKtartaddPararBeans", "白名单init: " + listImsiListTrue.toString());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    viewS.zhuanhuandw(1);
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
//        if (GuankongType == 2) {//运营商1
//            if (TextUtils.isEmpty(tf1)) {
//                ToastUtils.showToast("设备1未连接");
//                return;
//            }
//            if (TextUtils.isEmpty(tf2)) {
//                ToastUtils.showToast("设备2未连接");
//                return;
//            }
//
//            if (!sb1.equals("就绪")) {
//                ToastUtils.showToast("设备1不在就绪状态");
//                return;
//            }
//            if (!sb2.equals("就绪")) {
//                ToastUtils.showToast("设备2不在就绪状态");
//                return;
//            }
//            Log.d("setGKtart", "setGKtart: ");
//            //---------------------------------确定要启动侦码轮循
//            dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
//            inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
//            TextView tv_title = inflate.findViewById(R.id.tv_title);
//            Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
//            tv_title.setText("启动研判搜救吗?");
//            bt_confirm.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Log.d("setGKtart", "setGKtart:启动研判搜救吗 ");
//                    try {
//                        DBManagerAddParamWhite dbManagerAddParamA = new DBManagerAddParamWhite(context);
//                        List<AddPararBeanWhite> dataAlla = dbManagerAddParamA.getDataAll();
//                        List<AddPararBeanWhite> listImsiListTrue = new ArrayList<>();
//                        Log.d("setGKtartaddPararBeans", "白名单所有--init: " + dataAlla.toString());
//
//                        if (dataAlla.size() > 0) {
//                            for (int i = 0; i < dataAlla.size(); i++) {
//                                if (dataAlla.get(i).isCheckbox() == true) {
//                                    dataAlla.get(i).setSb("");
//                                    listImsiListTrue.add(dataAlla.get(i));
//                                }
//                            }
//                            List<Integer> list1size = new ArrayList<>();
//                            if (listImsiListTrue.size() > 0) {
//                                for (int i = 1; i < listImsiListTrue.size() + 1; i++) {
//                                    list1size.add(i);
//                                }
//
//                                List<AddPararBeanWhite> sendwhitelistLT = new ArrayList<>();
//                                for (int j = 0; j < listImsiListTrue.size(); j++) {
//                                    if (listImsiListTrue.get(j).getYy().equals("联通")) {
//                                        sendwhitelistLT.add(listImsiListTrue.get(j));
//                                    }
//                                }
//                                List<AddPararBeanWhite> sendwhitelistDX = new ArrayList<>();
//                                for (int j = 0; j < listImsiListTrue.size(); j++) {
//                                    if (listImsiListTrue.get(j).getYy().equals("电信")) {
//                                        sendwhitelistDX.add(listImsiListTrue.get(j));
//                                    }
//                                }
//                                if (!tf1.equals("FDD")) {
//                                    ToastUtils.showToast("设备1当前制式为TDD");
//                                    viewS.gkqiehuan(tf1, 1);
////                                    return;
//                                }
//                                if (!tf2.equals("FDD")) {
//                                    ToastUtils.showToast("设备2当前制式为TDD");
//                                    viewS.gkqiehuan(tf2, 2);
////                                    return;
//                                }
//                                if (sendwhitelistLT.size() > 0) {
//                                    sendwhiteListRunLT(sendwhitelistLT, tf1, tf2, context, listImsiListTrue);//发送白名单  2个
//                                } else {
//                                    sendwhiteListRunLT(sendwhitelistLT, tf1, tf2, context, listImsiListTrue);//发送白名单  2个
//                                }
//                                if (sendwhitelistDX.size() > 0) {
//                                    sendwhiteListRunDX(sendwhitelistDX, tf1, tf2, context, listImsiListTrue);//发送白名单  2个
//                                } else {
//                                    sendwhiteListRunDX(sendwhitelistDX, tf1, tf2, context, listImsiListTrue);//发送白名单  2个
//
//                                }
//
//
//                            } else {
////                                ToastUtils.showToast("没有选中的非控IMSI");
//                                List<AddPararBeanWhite> sendwhitelistLT = new ArrayList<>();
//                                List<AddPararBeanWhite> sendwhitelistDX = new ArrayList<>();
////                                ToastUtils.showToast("111");
//
//                                sendwhiteListRunLT(sendwhitelistLT, tf1, tf2, context, listImsiListTrue);//发送白名单  2个
//
//
//                                sendwhiteListRunDX(sendwhitelistDX, tf1, tf2, context, listImsiListTrue);//发送白名单  2个
//
//
//                            }
//                        } else {
//                            List<AddPararBeanWhite> sendwhitelistLT = new ArrayList<>();
//                            List<AddPararBeanWhite> sendwhitelistDX = new ArrayList<>();
//                            if (!tf1.equals("FDD")) {
//                                ToastUtils.showToast("设备1当前制式为TDD");
//                                viewS.gkqiehuan(tf1, 1);
////                                    return;
//                            } else {
//                                sendwhiteListRunLT(sendwhitelistLT, tf1, tf2, context, listImsiListTrue);//发送白名单  2个
//                            }
//                            if (!tf2.equals("FDD")) {
//                                ToastUtils.showToast("设备2当前制式为TDD");
//                                viewS.gkqiehuan(tf2, 2);
////                                    return;
//                            } else {
//                                sendwhiteListRunDX(sendwhitelistDX, tf1, tf2, context, listImsiListTrue);//发送白名单  2个
//                            }
//                        }
//                        Log.d("setGKtartaddPararBeans", "白名单init: " + listImsiListTrue.toString());
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//
//
//                    dialog.dismiss();
//                    dialog.cancel();
//                }
//            });
//            Button bt_cancel = inflate.findViewById(R.id.bt_cancel);
//            bt_cancel.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    dialog.dismiss();
//                    dialog.cancel();
//                }
//            });
//            dialog.setCanceledOnTouchOutside(false);
//            dialog.setContentView(inflate);
//            //获取当前Activity所在的窗体
//            Window dialogWindow = dialog.getWindow();
//            //设置Dialog从窗体底部弹出
//            dialogWindow.setGravity(Gravity.CENTER);
//            //获得窗体的属性
//            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
////                           lp.y = 20;//设置Dialog距离底部的距离
////                          将属性设置给窗体
//            dialogWindow.setAttributes(lp);
//            dialog.show();//显示对话框
//
//        }

        if (GuankongType == 2) {//运营商2 移动FDD
            ToastUtils.showToast("等于2");
            if (TextUtils.isEmpty(tf1)) {
                ToastUtils.showToast("设备1未连接");
                return;
            }
//            if (TextUtils.isEmpty(tf2)) {
//                ToastUtils.showToast("设备2未连接");
//                return;
//            }

            if (!sb1.equals("就绪")) {
                ToastUtils.showToast("设备1不在就绪状态");
                return;
            }
//            if (!sb2.equals("就绪")) {
//                ToastUtils.showToast("设备2不在就绪状态");
//                return;
//            }
            Log.d("setGKtart", "setGKtart: ");
            //---------------------------------确定要启动侦码轮循
            dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
            inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
            TextView tv_title = inflate.findViewById(R.id.tv_title);
            Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
            tv_title.setText("启动研判搜救吗?");
            bt_confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("setGKtart", "setGKtart:启动研判搜救吗? ");
                    try {
                        DBManagerAddParamWhite dbManagerAddParamA = new DBManagerAddParamWhite(context);
                        List<AddPararBeanWhite> dataAlla = dbManagerAddParamA.getDataAll();
                        List<AddPararBeanWhite> listImsiListTrue = new ArrayList<>();
                        Log.d("setGKtartaddPararBeans", "白名单所有--init: " + dataAlla.toString());

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

                                List<AddPararBeanWhite> sendwhitelist = new ArrayList<>();
                                for (int j = 0; j < listImsiListTrue.size(); j++) {
                                    if (listImsiListTrue.get(j).getYy().equals("移动")) {
                                        sendwhitelist.add(listImsiListTrue.get(j));
                                    }
                                }
                                Log.d("+sendwhitelist", "onClick: " + sendwhitelist);
                                if (sendwhitelist.size() > 0) {
                                    if (!tf1.equals("FDD")) {
                                        ToastUtils.showToast("设备1当前制式为FDD");
                                        viewS.gkqiehuan(tf1, 1);
//                return;
                                    }
//                                    if (!tf2.equals("FDD")) {
//                                        ToastUtils.showToast("设备2当前制式为TDD");
//
//                                        viewS.gkqiehuan(tf2, 2);
////                return;
//                                    }
//                                    if (!sb1.equals("就绪")) {
////                ToastUtils.showToast("设备1不在就绪状态");
//                                        return;
//                                    }
//                                    if (!sb2.equals("就绪")) {
////                ToastUtils.showToast("设备2不在就绪状态");
//                                        return;
//                                    }
                                    sendwhiteListRun(sendwhitelist, tf1, tf2, context, listImsiListTrue);//发送白名单  2个

                                } else {
                                    if (!tf1.equals("FDD")) {
                                        ToastUtils.showToast("设备1当前制式为FDD");
                                        viewS.gkqiehuan(tf1, 1);
//                return;
                                    }
//                                    if (!tf2.equals("FDD")) {
//                                        ToastUtils.showToast("设备2当前制式为TDD");
//                                        viewS.gkqiehuan(tf2, 2);
////                return;
//                                    }
//                                    if (!sb1.equals("就绪")) {
////                ToastUtils.showToast("设备1不在就绪状态");
//                                        return;
//                                    }
//                                    if (!sb2.equals("就绪")) {
////                ToastUtils.showToast("设备2不在就绪状态");
//                                        return;
//                                    }
                                    sendwhiteListRunDELE(sendwhitelist, tf1, tf2, context, listImsiListTrue);//发送白名单  2个

                                }


                            } else {
//                                ToastUtils.showToast("没有选中的非控IMSI");


                                if (!tf1.equals("FDD")) {
                                    ToastUtils.showToast("设备1当前制式为FDD");
                                    viewS.gkqiehuan(tf1, 1);
//                return;
                                } else {
                                    List<AddPararBeanWhite> sendwhitelist = new ArrayList<>();
                                    sendwhiteListRunDELE(sendwhitelist, tf1, tf2, context, listImsiListTrue);//发送白名单  2个
                                }


                            }
                        } else {//白名单为空
                            if (tf1.equals("FDD")) {
                                List<AddPararBeanWhite> sendwhitelist = new ArrayList<>();
                                sendwhiteListRunDELE(sendwhitelist, tf1, tf2, context, listImsiListTrue);//发送白名单  2个
                            }
                            if (!tf1.equals("FDD")) {
                                ToastUtils.showToast("设备1当前制式为FDD");
                                viewS.gkqiehuan(tf1, 1);
//                return;
                            } else {
                                List<AddPararBeanWhite> sendwhitelist = new ArrayList<>();
                            }
//                            if (!tf2.equals("FDD")) {
//                                ToastUtils.showToast("设备2当前制式为TDD");
//                                viewS.gkqiehuan(tf2, 2);
////                return;
//                            } else {
//
//
//                            }
                            List<AddPararBeanWhite> sendwhitelist = new ArrayList<>();
                            sendwhiteListRunDELE(sendwhitelist, tf1, tf2, context, listImsiListTrue);//发送白名单  2个
                        }
                        Log.d("setGKtartaddPararBeans1", "白名单init: " + listImsiListTrue.toString());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    viewS.zhuanhuandw(1);
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
        if (GuankongType == 3) {//运营商3 联通FDD
            if (TextUtils.isEmpty(tf1)) {
                ToastUtils.showToast("设备1未连接");
                return;
            }
//            if (TextUtils.isEmpty(tf2)) {
//                ToastUtils.showToast("设备2未连接");
//                return;
//            }

            if (!sb1.equals("就绪")) {
                ToastUtils.showToast("设备1不在就绪状态");
                return;
            }
//            if (!sb2.equals("就绪")) {
//                ToastUtils.showToast("设备2不在就绪状态");
//                return;
//            }
            Log.d("setGKtart", "setGKtart: ");
            //---------------------------------确定要启动侦码轮循
            dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
            inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
            TextView tv_title = inflate.findViewById(R.id.tv_title);
            Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
            tv_title.setText("启动研判搜救吗?");
            bt_confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("setGKtart", "setGKtart:启动研判搜救吗? ");
                    try {
                        DBManagerAddParamWhite dbManagerAddParamA = new DBManagerAddParamWhite(context);
                        List<AddPararBeanWhite> dataAlla = dbManagerAddParamA.getDataAll();
                        List<AddPararBeanWhite> listImsiListTrue = new ArrayList<>();
                        Log.d("setGKtartaddPararBeans", "白名单所有--init: " + dataAlla.toString());

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

                                List<AddPararBeanWhite> sendwhitelist = new ArrayList<>();
                                for (int j = 0; j < listImsiListTrue.size(); j++) {
                                    if (listImsiListTrue.get(j).getYy().equals("联通")) {
                                        sendwhitelist.add(listImsiListTrue.get(j));
                                    }
                                }
                                Log.d("+sendwhitelist", "onClick: " + sendwhitelist);
                                if (sendwhitelist.size() > 0) {
                                    if (!tf1.equals("FDD")) {
                                        ToastUtils.showToast("设备1当前制式为FDD");
                                        viewS.gkqiehuan(tf1, 1);
//                return;
                                    }
//                                    if (!tf2.equals("FDD")) {
//                                        ToastUtils.showToast("设备2当前制式为TDD");
//
//                                        viewS.gkqiehuan(tf2, 2);
////                return;
//                                    }
//                                    if (!sb1.equals("就绪")) {
////                ToastUtils.showToast("设备1不在就绪状态");
//                                        return;
//                                    }
//                                    if (!sb2.equals("就绪")) {
////                ToastUtils.showToast("设备2不在就绪状态");
//                                        return;
//                                    }
                                    sendwhiteListRun(sendwhitelist, tf1, tf2, context, listImsiListTrue);//发送白名单  2个

                                } else {
                                    if (!tf1.equals("FDD")) {
                                        ToastUtils.showToast("设备1当前制式为FDD");
                                        viewS.gkqiehuan(tf1, 1);
//                return;
                                    }
//                                    if (!tf2.equals("FDD")) {
//                                        ToastUtils.showToast("设备2当前制式为TDD");
//                                        viewS.gkqiehuan(tf2, 2);
////                return;
//                                    }
//                                    if (!sb1.equals("就绪")) {
////                ToastUtils.showToast("设备1不在就绪状态");
//                                        return;
//                                    }
//                                    if (!sb2.equals("就绪")) {
////                ToastUtils.showToast("设备2不在就绪状态");
//                                        return;
//                                    }
                                    sendwhiteListRunDELE(sendwhitelist, tf1, tf2, context, listImsiListTrue);//发送白名单  2个

                                }


                            } else {
//                                ToastUtils.showToast("没有选中的非控IMSI");

                                List<AddPararBeanWhite> sendwhitelist = new ArrayList<>();
                                sendwhiteListRunDELE(sendwhitelist, tf1, tf2, context, listImsiListTrue);//发送白名单  2个

                            }
                        } else {//白名单为空
                            if (tf1.equals("FDD")) {
                                List<AddPararBeanWhite> sendwhitelist = new ArrayList<>();
                                sendwhiteListRunDELE(sendwhitelist, tf1, tf2, context, listImsiListTrue);//发送白名单  2个
                            }
                            if (!tf1.equals("FDD")) {
                                ToastUtils.showToast("设备1当前制式为FDD");
                                viewS.gkqiehuan(tf1, 1);
//                return;
                            } else {
                                List<AddPararBeanWhite> sendwhitelist = new ArrayList<>();
                            }
//                            if (!tf2.equals("FDD")) {
//                                ToastUtils.showToast("设备2当前制式为TDD");
//                                viewS.gkqiehuan(tf2, 2);
////                return;
//                            } else {
//
//
//                            }
                            List<AddPararBeanWhite> sendwhitelist = new ArrayList<>();
                            sendwhiteListRunDELE(sendwhitelist, tf1, tf2, context, listImsiListTrue);//发送白名单  2个
                        }
                        Log.d("setGKtartaddPararBeans", "白名单init: " + listImsiListTrue.toString());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    viewS.zhuanhuandw(1);
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
        if (GuankongType == 4) {//运营商4 电信FDD
            if (TextUtils.isEmpty(tf1)) {
                ToastUtils.showToast("设备1未连接");
                return;
            }
//            if (TextUtils.isEmpty(tf2)) {
//                ToastUtils.showToast("设备2未连接");
//                return;
//            }

            if (!sb1.equals("就绪")) {
                ToastUtils.showToast("设备1不在就绪状态");
                return;
            }
//            if (!sb2.equals("就绪")) {
//                ToastUtils.showToast("设备2不在就绪状态");
//                return;
//            }
            Log.d("setGKtart", "setGKtart: ");
            //---------------------------------确定要启动侦码轮循
            dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
            inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
            TextView tv_title = inflate.findViewById(R.id.tv_title);
            Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
            tv_title.setText("启动研判搜救吗?");
            bt_confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("setGKtart", "setGKtart:启动研判搜救吗? ");
                    try {
                        DBManagerAddParamWhite dbManagerAddParamA = new DBManagerAddParamWhite(context);
                        List<AddPararBeanWhite> dataAlla = dbManagerAddParamA.getDataAll();
                        List<AddPararBeanWhite> listImsiListTrue = new ArrayList<>();
                        Log.d("setGKtartaddPararBeans", "白名单所有--init: " + dataAlla.toString());

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

                                List<AddPararBeanWhite> sendwhitelist = new ArrayList<>();
                                for (int j = 0; j < listImsiListTrue.size(); j++) {
                                    if (listImsiListTrue.get(j).getYy().equals("电信")) {
                                        sendwhitelist.add(listImsiListTrue.get(j));
                                    }
                                }
                                Log.d("+sendwhitelist", "onClick: " + sendwhitelist);
                                if (sendwhitelist.size() > 0) {
                                    if (!tf1.equals("FDD")) {
                                        ToastUtils.showToast("设备1当前制式为FDD");
                                        viewS.gkqiehuan(tf1, 1);
//                return;
                                    }
//                                    if (!tf2.equals("FDD")) {
//                                        ToastUtils.showToast("设备2当前制式为TDD");
//
//                                        viewS.gkqiehuan(tf2, 2);
////                return;
//                                    }
                                    if (!sb1.equals("就绪")) {
//                ToastUtils.showToast("设备1不在就绪状态");
                                        return;
                                    }
//                                    if (!sb2.equals("就绪")) {
////                ToastUtils.showToast("设备2不在就绪状态");
//                                        return;
//                                    }
                                    sendwhiteListRun(sendwhitelist, tf1, tf2, context, listImsiListTrue);//发送白名单  2个

                                } else {
                                    if (!tf1.equals("FDD")) {
                                        ToastUtils.showToast("设备1当前制式为FDD");
                                        viewS.gkqiehuan(tf1, 1);
//                return;
                                    }
//                                    if (!tf2.equals("FDD")) {
//                                        ToastUtils.showToast("设备2当前制式为TDD");
//                                        viewS.gkqiehuan(tf2, 2);
////                return;
//                                    }
                                    if (!sb1.equals("就绪")) {
//                ToastUtils.showToast("设备1不在就绪状态");
                                        return;
                                    }
//                                    if (!sb2.equals("就绪")) {
////                ToastUtils.showToast("设备2不在就绪状态");
//                                        return;
//                                    }
                                    sendwhiteListRunDELE(sendwhitelist, tf1, tf2, context, listImsiListTrue);//发送白名单  2个

                                }


                            } else {
//                                ToastUtils.showToast("没有选中的非控IMSI");

                                List<AddPararBeanWhite> sendwhitelist = new ArrayList<>();
                                sendwhiteListRunDELE(sendwhitelist, tf1, tf2, context, listImsiListTrue);//发送白名单  2个

                            }
                        } else {//白名单为空
                            if (tf1.equals("FDD")) {
                                List<AddPararBeanWhite> sendwhitelist = new ArrayList<>();
                                sendwhiteListRunDELE(sendwhitelist, tf1, tf2, context, listImsiListTrue);//发送白名单  2个
                            }
                            if (!tf1.equals("FDD")) {
                                ToastUtils.showToast("设备1当前制式为FDD");
                                viewS.gkqiehuan(tf1, 1);
//                return;
                            } else {
                                List<AddPararBeanWhite> sendwhitelist = new ArrayList<>();
                            }
//                            if (!tf2.equals("FDD")) {
//                                ToastUtils.showToast("设备2当前制式为TDD");
//                                viewS.gkqiehuan(tf2, 2);
////                return;
//                            } else {
//
//
//                            }
                            List<AddPararBeanWhite> sendwhitelist = new ArrayList<>();
                            sendwhiteListRunDELE(sendwhitelist, tf1, tf2, context, listImsiListTrue);//发送白名单  2个
                        }
                        Log.d("setGKtartaddPararBeans", "白名单init: " + listImsiListTrue.toString());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    viewS.zhuanhuandw(1);
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
    }

    //发送黑名单
    private void sendwhiteListRunLT(List<AddPararBeanWhite> sendListBlack, final String tf1, final String spinnerS1, final Context context, List<AddPararBeanWhite> listImsiListTrue) {

        List<String> list = new ArrayList<>();
//        Log.d(TAG, "sendBlacListRun:list" + list);
        for (int i = 0; i < sendListBlack.size(); i++) {
            list.add(sendListBlack.get(i).getImsi());
        }
        Log.d("aaaaalist", "sendwhiteListRun: " + list);
        int totalMy = list.size();
//        Log.d(TAG, "sendBlackListRun:totalMy" + totalMy);
        //消息头
        StringBuffer str = new StringBuffer("aaaa555539f0b900000000ff01");
        //黑名单数量
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
            sendrunwhite(str, sendListBlack, tf1, spinnerS1, context, listImsiListTrue);//开始发送
//            sendrunwhite2(str, sendListBlack, tf1, spinnerS1, context, listImsiListTrue);//开始发送

        }
    }

    //发送黑名单
    private void sendwhiteListRunDX(List<AddPararBeanWhite> sendListBlack, final String tf1, final String spinnerS1, final Context context, List<AddPararBeanWhite> listImsiListTrue) {

        List<String> list = new ArrayList<>();
//        Log.d(TAG, "sendBlacListRun:list" + list);
        for (int i = 0; i < sendListBlack.size(); i++) {
            list.add(sendListBlack.get(i).getImsi());
        }
        Log.d("aaaaalist", "sendwhiteListRun: " + list);
        int totalMy = list.size();
//        Log.d(TAG, "sendBlackListRun:totalMy" + totalMy);
        //消息头
        StringBuffer str = new StringBuffer("aaaa555539f0b900000000ff01");
        //黑名单数量
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
//            sendrunwhite(str, sendListBlack, tf1, spinnerS1, context, listImsiListTrue);//开始发送
            sendrunwhite2(str, sendListBlack, tf1, spinnerS1, context, listImsiListTrue);//开始发送

        }
    }

    //设备1已开始发送
    private void sendrunwhite2(final StringBuffer strData, final List<AddPararBeanWhite> sendListBlack, final String tf1, final String spinnerS1, final Context context, final List<AddPararBeanWhite> listImsiListTrue) {
//
        new Thread(new Runnable() {
            @Override
            public void run() {
                DatagramSocket socket = null;
                InetAddress address = null;//你本机的ip地址
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
                Log.e("nzqsendstart1Black", "run: sendsocket端口号" + outputPacket.getPort() + "Ip地址:" + outputPacket.getAddress().toString() + "数据:" + outputPacket.getData());
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

    //发送黑名单
    private void sendwhiteListRunDELE(List<AddPararBeanWhite> sendListBlack, final String tf1, final String spinnerS1, final Context context, List<AddPararBeanWhite> listImsiListTrue) {

        List<String> list = new ArrayList<>();
//        Log.d(TAG, "sendBlacListRun:list" + list);
//        for (int i = 0; i < sendListBlack.size(); i++) {
//            list.add(sendListBlack.get(i).getImsi());
//        }
        Log.d("aaaaalist", "sendwhiteListRun: " + list);
        int totalMy = list.size();
//        Log.d(TAG, "sendBlackListRun:totalMy" + totalMy);
        //消息头
        StringBuffer str = new StringBuffer("aaaa555539f0b900000000ff00");

//        //黑名单数量
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

        ///第一个写15个 1

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
            sendrunwhite(str, sendListBlack, tf1, spinnerS1, context, listImsiListTrue);//开始发送
//            sendrunwhite2(str, sendListBlack, tf1, spinnerS1, context, listImsiListTrue);//开始发送

        }
    }


    //发送黑名单
    private void sendwhiteListRun(List<AddPararBeanWhite> sendListBlack, final String tf1, final String spinnerS1, final Context context, List<AddPararBeanWhite> listImsiListTrue) {

        List<String> list = new ArrayList<>();
//        Log.d(TAG, "sendBlacListRun:list" + list);
        for (int i = 0; i < sendListBlack.size(); i++) {
            list.add(sendListBlack.get(i).getImsi());
        }
        Log.d("aaaaalist", "sendwhiteListRun: " + list);
        int totalMy = list.size();
//        Log.d(TAG, "sendBlackListRun:totalMy" + totalMy);
        //消息头
        StringBuffer str = new StringBuffer("aaaa555539f0b900000000ff01");

        //黑名单数量
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
            sendrunwhite(str, sendListBlack, tf1, spinnerS1, context, listImsiListTrue);//开始发送
//            sendrunwhite2(str, sendListBlack, tf1, spinnerS1, context, listImsiListTrue);//开始发送

        }
    }

    //设备1已开始发送
    private void sendrunwhite(final StringBuffer strData, final List<AddPararBeanWhite> sendListBlack, final String tf1, final String spinnerS1, final Context context, final List<AddPararBeanWhite> listImsiListTrue) {
//
        new Thread(new Runnable() {
            @Override
            public void run() {
                DatagramSocket socket = null;
                InetAddress address = null;//你本机的ip地址
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
                Log.e("nzqsendstart1Black2", "run: sendsocket端口号" + outputPacket.getPort() + "Ip地址:" + outputPacket.getAddress().toString() + "数据:" + outputPacket.getData());
                try {
                    //
                    //                                    socket.send(outputPacket);
                    Thread.sleep(3000);
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

    /**
     * 管控重定向
     */
    @Override
    public void chongdingxiang1(final String down) {
//        chongdingxiang(IP1);
        new Thread(new Runnable() {
            @Override
            public void run() {

                DatagramSocket socket = null;
                InetAddress address = null;//你本机的ip地址
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
                Log.e("nzqsendstart1Clear", "run: sendsocket端口号" + outputPacket.getPort() + "Ip地址:" + outputPacket.getAddress().toString() + "数据:" + outputPacket.getData());

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
                InetAddress address = null;//你本机的ip地址
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
                Log.e("nzqsendstart1Clear", "run: sendsocket端口号" + outputPacket.getPort() + "Ip地址:" + outputPacket.getAddress().toString() + "数据:" + outputPacket.getData());

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

    private void chongdingxiangSet(final String ip) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                DatagramSocket socket = null;
                InetAddress address = null;//你本机的ip地址
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
                Log.e("nzqsendstart1管控", "run: sendsocket端口号" + outputPacket.getPort() + "Ip地址:" + outputPacket.getAddress().toString() + "数据:" + outputPacket.getData());

                try {
//                    Thread.sleep(500);
                    //
                    //                                    socket.send(outputPacket);
                    Thread.sleep(2000);
                    DS.send(outputPacket);

//
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }


    @SuppressLint("NewApi")
    @Override
    public void setryGK(Context context, RecyclerView recyclerView, String down1, String down2, RyZmAdapterGk ryZmAdapterGk, Message msg, TextView sos_tv_searchNum_zm, RyZmAdapterGk.GKCallBack gkCallBack) {
        String imsi = msg.getData().getString("imsi");
        Log.d("Qdown1", "handleMessage: " + "imsi===" + imsi + "----");

//                Log.d("Qdown1", "handleMessage: " + "imsi===" + imsi + "----" + DOWNPIN1);
        DBManagerZMGK dbManagerZM = null;
        DBManagerAddParamWhite dbManagerAddParamA = null;
        List<AddPararBeanWhite> dataAlla = new ArrayList<>();
        try {
            dbManagerAddParamA = new DBManagerAddParamWhite(context);
            dataAlla = dbManagerAddParamA.getDataAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

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

            //此处显示侦码的imsi列表
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
//            ryZmAdapterGk = new RyZmAdapterGk(context, zmBeans, listsize);//list是imsi列表选中的数据
//            if (et_zhenmasearchdw.getText().length() == 0) {
//                        ry_zhenma.setAdapter(ryZmAdapter);

//            ryZmAdapterGk = new RyZmAdapterGk(context, zmBeans, listsize);//list是imsi列表选中的数据
//            recyclerView.setAdapter(ryZmAdapterGk);

            //通过 split 方法拆分数据并放入数组中
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
            // 方式一
            Set<String> res = map.keySet();
            for (String im : res) {
                System.out.println("a数据：" + im + "共有" + map.get(im));
            }
            List<ZmBeanGKTongji> list2 = new ArrayList<>();
            ZmBeanGKTongji zmBeanGKTongji;
            Set<Map.Entry<String, Integer>> set = map.entrySet();
            for (Map.Entry<String, Integer> entry : set) {
                System.out.println("输出结果重复" + entry.getKey() + "---" + entry.getValue());
                if (entry.getValue() > 0) {
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
            //时间倒叙
            list2.sort(new Comparator<ZmBeanGKTongji>() {
                @Override
                public int compare(ZmBeanGKTongji zmBeanGKTongji, ZmBeanGKTongji t1) {

                    try {
                        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(t1.getDatatime()).compareTo(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(zmBeanGKTongji.getDatatime()));
                    } catch (ParseException e) {
                        return 0;
                    }
                }
            });
            //排除白名单IMSI 
            for (int j = 0; j < dataAlla.size(); j++) {
                for (int k = 0; k < list2.size(); k++) {
                    if (dataAlla.get(j).getImsi().equals(list2.get(k).getImsi())) {
                        list2.remove(k);
                    }
                }


            }

            List<ZmBeanGKTongji> list3 = new ArrayList<>();
            List<ZmBeanGKTongji> listZMBEANGKTONGJILISTCALL = new ArrayList<>();
            for (int j = 0; j < ZMBEANGKTONGJILISTCALL.size(); j++) {
                if (ZMBEANGKTONGJILISTCALL.get(j).isCheck() == true) {
                    listZMBEANGKTONGJILISTCALL.add(ZMBEANGKTONGJILISTCALL.get(j));
                }
            }
            if (listZMBEANGKTONGJILISTCALL.size() > 0) {
                Log.d("删除后的集合", "setryGK: " + listZMBEANGKTONGJILISTCALL);

                Log.d("aaaweqea", "onActivityResult: " + ZMBEANGKTONGJILISTCALL);
                for (int j = 0; j < listZMBEANGKTONGJILISTCALL.size(); j++) {
                    for (int li = 0; li < list2.size(); li++) {

                            if (listZMBEANGKTONGJILISTCALL.get(j).getImsi().equals(list2.get(li).getImsi())) {
                                list3.add(list2.get(li));
                            }

                    }

                }
                list2=list3;
            }


//            for (int j = 0; j < ; j++) {
//
//            }

            if (list2.size() == 0) {
                sos_tv_searchNum_zm.setText("(" + 0 + ")");
            } else {
                sos_tv_searchNum_zm.setText("(" + list2.size() + ")");
            }

            viewS.gkList(list2);
            ryZmAdapterGk = new RyZmAdapterGk(context, list2, listsize, gkCallBack);//list是imsi列表选中的数据
            recyclerView.setAdapter(ryZmAdapterGk);
            if (list2.size() > 10) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
//                linearLayoutManager.setStackFromEnd(true);//recyview显示最底部一条
                recyclerView.setLayoutManager(linearLayoutManager);
//                tv_searchNumdw.setText("(" + zmBeans.size() + ")");
            } else {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
//                linearLayoutManager.setStackFromEnd(false);//recyview显示最底部一条
                recyclerView.setLayoutManager(linearLayoutManager);
//                tv_searchNumdw.setText("(" + zmBeans.size() + ")");
            }
            //默认 IMSI 名单不选中
        } catch (SQLException e) {
            e.printStackTrace();

            Log.d("errorsetryGK", "setryGK: " + e.getMessage());
        }
    }


    @Override
    public void spbuilsGK(Context context, int device, int yy, String tf1, String tf2) {
        if (device == 1) {
            if (yy == 1) {//移动运营商  tdd
                if (tf1.equals("TDD")) {

                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("移动") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
                        if (listmun.size() > 0) {
//                            MainUtils.start1SNF(IP1, Constant.SNFTDD);
                            DBManagersaopin dbManagersaopin = null;
                            try {
                                dbManagersaopin = new DBManagersaopin(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("功放开启成功");
                                    saopinSend1(saopinBeanList, tf1, yy, context,null);
                                } else {
                                    ToastUtils.showToast("当前没有频点");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else {
//                            ToastUtils.showToast("没有符合条件的IMSI7");
//                            MainUtils.start1SNF(IP1, Constant.SNFTDD);
                            DBManagersaopin dbManagersaopin = null;
                            try {
                                dbManagersaopin = new DBManagersaopin(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("功放开启成功");
                                    saopinSend1(saopinBeanList, tf1, yy, context,null);
                                } else {
                                    ToastUtils.showToast("当前没有频点");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }


                }

//                if (tf1.equals("FDD")) {
//
//
//                    try {
//                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
//                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
//                        List<String> listmun = new ArrayList<>();
//                        for (int j = 0; j < dataAll.size(); j++) {
//                            if (dataAll.get(j).getYy().equals("移动") && dataAll.get(j).isCheckbox() == true) {
//                                listmun.add(dataAll.get(j).getImsi());
//                            }
//
//                        }
//                        if (listmun.size() > 0) {
//                            String titles = "";
//                            if (tf1.equals("TDD")) {
//                                titles = "FDD";
//                                MainUtils.start1SNF(IP1, Constant.SNFFDD);
//                            }
//                            if (tf1.equals("FDD")) {
//                                titles = "TDD";
//                                MainUtils.start1SNF(IP1, Constant.SNFTDD);
//                            }
//                            viewS.zhishiqiehuan(1, titles);
//                        } else {
////                            ToastUtils.showToast("没有符合条件的IMSI");
//                            String titles = "";
//                            if (tf1.equals("TDD")) {
//                                titles = "FDD";
//                                MainUtils.start1SNF(IP1, Constant.SNFFDD);
//                            }
//                            if (tf1.equals("FDD")) {
//                                titles = "TDD";
//                                MainUtils.start1SNF(IP1, Constant.SNFTDD);
//                            }
//                            viewS.zhishiqiehuan(1, titles);
//                        }
//                    } catch (SQLException throwables) {
//                        throwables.printStackTrace();
//                    }
//
//                }

            }
            if (yy == 2) {
//                if (tf1.equals("TDD")) {
//                    try {
//                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
//                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
//                        List<String> listmun = new ArrayList<>();
//                        for (int j = 0; j < dataAll.size(); j++) {
//                            if (dataAll.get(j).getYy().equals("移动") && dataAll.get(j).isCheckbox() == true) {
//                                listmun.add(dataAll.get(j).getImsi());
//                            }
//
//                        }
//                        if (listmun.size() > 0) {
//                            String titles = "";
//                            if (tf1.equals("TDD")) {
//                                titles = "FDD";
//                                MainUtils.start1SNF(IP1, Constant.SNFFDD);
//                            }
//                            if (tf1.equals("FDD")) {
//                                titles = "TDD";
//                                MainUtils.start1SNF(IP1, Constant.SNFTDD);
//                            }
//                            viewS.zhishiqiehuan(1, titles);
//                        } else {
////                            ToastUtils.showToast("没有符合条件的IMSI");
//
//                            String titles = "";
//                            if (tf1.equals("TDD")) {
//                                titles = "FDD";
//                                MainUtils.start1SNF(IP1, Constant.SNFFDD);
//                            }
//                            if (tf1.equals("FDD")) {
//                                titles = "TDD";
//                                MainUtils.start1SNF(IP1, Constant.SNFTDD);
//                            }
//                            viewS.zhishiqiehuan(1, titles);
//                        }
//                    } catch (SQLException throwables) {
//                        throwables.printStackTrace();
//                    }
//
//                }
                if (tf1.equals("FDD")) {

                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("移动") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
                        if (listmun.size() > 0) {
//                            MainUtils.start1SNF(IP1, Constant.SNFFDD);
                            DBManagersaopin dbManagersaopin = null;
                            try {
                                dbManagersaopin = new DBManagersaopin(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("功放开启成功");
                                    saopinSend1(saopinBeanList, tf1, yy, context,null);
                                } else {
                                    ToastUtils.showToast("当前没有频点");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else {
//                            MainUtils.start1SNF(IP1, Constant.SNFFDD);
                            DBManagersaopin dbManagersaopin = null;
                            try {
                                dbManagersaopin = new DBManagersaopin(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("功放开启成功");
                                    saopinSend1(saopinBeanList, tf1, yy, context,null);
                                } else {
                                    ToastUtils.showToast("当前没有频点");
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
                        if (dataAll.get(j).getYy().equals("联通") && dataAll.get(j).isCheckbox() == true) {
                            listmun.add(dataAll.get(j).getImsi());
                        }

                    }
                    if (tf1.equals("TDD")) {
                        if (listmun.size() > 0) {

                            String titles = "";
                            if (tf1.equals("TDD")) {
                                titles = "FDD";
//                                MainUtils.start1SNF(IP1, Constant.SNFFDD);
                            }
                            if (tf1.equals("FDD")) {
                                titles = "TDD";
//                                MainUtils.start1SNF(IP1, Constant.SNFTDD);
                            }
                            viewS.zhishiqiehuan(1, titles);
                        } else {
                            String titles = "";
                            if (tf1.equals("TDD")) {
                                titles = "FDD";
//                                MainUtils.start1SNF(IP1, Constant.SNFFDD);
                            }
                            if (tf1.equals("FDD")) {
                                titles = "TDD";
//                                MainUtils.start1SNF(IP1, Constant.SNFTDD);
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
                            if (dataAll.get(j).getYy().equals("联通") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
                        if (listmun.size() > 0) {
//                            MainUtils.start1SNF(IP1, Constant.SNFFDD);
                            DBManagersaopin dbManagersaopin = null;
                            try {
                                dbManagersaopin = new DBManagersaopin(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("功放开启成功");
                                    saopinSend1(saopinBeanList, tf1, yy, context,null);
                                } else {
                                    ToastUtils.showToast("当前没有频点");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else {
//                            MainUtils.start1SNF(IP1, Constant.SNFFDD);
                            DBManagersaopin dbManagersaopin = null;
                            try {
                                dbManagersaopin = new DBManagersaopin(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("功放开启成功");
                                    saopinSend1(saopinBeanList, tf1, yy, context,null);
                                } else {
                                    ToastUtils.showToast("当前没有频点");
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
//                if (tf1.equals("TDD")) {
//                    try {
//                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
//                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
//                        List<String> listmun = new ArrayList<>();
//                        for (int j = 0; j < dataAll.size(); j++) {
//                            if (dataAll.get(j).getYy().equals("电信") && dataAll.get(j).isCheckbox() == true) {
//                                listmun.add(dataAll.get(j).getImsi());
//                            }
//
//                        }
//                        if (listmun.size() > 0) {
//                            String titles = "";
//                            if (tf1.equals("TDD")) {
//                                titles = "FDD";
//                                MainUtils.start1SNF(IP1, Constant.SNFFDD);
//                            }
//                            if (tf1.equals("FDD")) {
//                                titles = "TDD";
//                                MainUtils.start1SNF(IP1, Constant.SNFTDD);
//                            }
//                            viewS.zhishiqiehuan(1, titles);
//                        } else {
//                            String titles = "";
//                            if (tf1.equals("TDD")) {
//                                titles = "FDD";
//                                MainUtils.start1SNF(IP1, Constant.SNFFDD);
//                            }
//                            if (tf1.equals("FDD")) {
//                                titles = "TDD";
//                                MainUtils.start1SNF(IP1, Constant.SNFTDD);
//                            }
//                            viewS.zhishiqiehuan(1, titles);
//                        }
//                    } catch (SQLException throwables) {
//                        throwables.printStackTrace();
//                    }
//
//                }
                if (tf1.equals("FDD")) {
                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("电信") && dataAll.get(j).isCheckbox() == true) {
                                listmun.add(dataAll.get(j).getImsi());
                            }

                        }
                        if (listmun.size() > 0) {
//                            MainUtils.start1SNF(IP1, Constant.SNFFDD);
                            DBManagersaopin dbManagersaopin = null;
                            try {
                                dbManagersaopin = new DBManagersaopin(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("功放开启成功");
                                    saopinSend1(saopinBeanList, tf1, yy, context,null);
                                } else {
                                    ToastUtils.showToast("当前没有频点");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else {
//                            MainUtils.start1SNF(IP1, Constant.SNFFDD);
                            DBManagersaopin dbManagersaopin = null;
                            try {
                                dbManagersaopin = new DBManagersaopin(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("功放开启成功");
                                    saopinSend1(saopinBeanList, tf1, yy, context,null);
                                } else {
                                    ToastUtils.showToast("当前没有频点");
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
        //设备2
        if (device == 2) {
            if (yy == 1) {//移动运营商
                if (tf2.equals("TDD")) {
                    try {
                        DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                        List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                        List<String> listmun = new ArrayList<>();
                        for (int j = 0; j < dataAll.size(); j++) {
                            if (dataAll.get(j).getYy().equals("移动") && dataAll.get(j).isCheckbox() == true) {
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
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("功放开启成功");
                                    saopinSend2(saopinBeanList, tf2, yy, context);
                                } else {
                                    ToastUtils.showToast("当前没有频点");
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
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("功放开启成功");
                                    saopinSend2(saopinBeanList, tf2, yy, context);
                                } else {
                                    ToastUtils.showToast("当前没有频点");
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
                            if (dataAll.get(j).getYy().equals("移动") && dataAll.get(j).isCheckbox() == true) {
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
                            if (dataAll.get(j).getYy().equals("移动") && dataAll.get(j).isCheckbox() == true) {
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
                            if (dataAll.get(j).getYy().equals("移动") && dataAll.get(j).isCheckbox() == true) {
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
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("功放开启成功");
                                    saopinSend2(saopinBeanList, tf2, yy, context);
                                } else {
                                    ToastUtils.showToast("当前没有频点");
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
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("功放开启成功");
                                    saopinSend2(saopinBeanList, tf2, yy, context);
                                } else {
                                    ToastUtils.showToast("当前没有频点");
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
                        if (dataAll.get(j).getYy().equals("联通") && dataAll.get(j).isCheckbox() == true) {
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
//                            ToastUtils.showToast("没有符合条件的IMSI");
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
                        if (dataAll.get(j).getYy().equals("联通") && dataAll.get(j).isCheckbox() == true) {
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
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("功放开启成功");
                                    saopinSend2(saopinBeanList, tf2, yy, context);
                                } else {
                                    ToastUtils.showToast("当前没有频点");
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
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("功放开启成功");
                                    saopinSend2(saopinBeanList, tf2, yy, context);
                                } else {
                                    ToastUtils.showToast("当前没有频点");
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
                        if (dataAll.get(j).getYy().equals("电信") && dataAll.get(j).isCheckbox() == true) {
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
//                            ToastUtils.showToast("没有符合条件的IMSI");
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
                        if (dataAll.get(j).getYy().equals("电信") && dataAll.get(j).isCheckbox() == true) {
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
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("功放开启成功");
                                    saopinSend2(saopinBeanList, tf2, yy, context);
                                } else {
                                    ToastUtils.showToast("当前没有频点");
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
                                List<SaopinBean> saopinBeanList = dbManagersaopin.getStudent();//查询对应的频点
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("功放开启成功");
                                    saopinSend2(saopinBeanList, tf2, yy, context);
                                } else {
                                    ToastUtils.showToast("当前没有频点");
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
    public void stopGK(final Context context) {

        dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
        inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
        TextView tv_title = inflate.findViewById(R.id.tv_title);
        tv_title.setText("确定要停止管控吗?");


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

                string = "停止定位";
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
}
