package com.sm.android.locations.location.sos;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.sm.android.locations.location.Utils.MainUtils.MainUtils2;
import com.sm.android.locations.location.Utils.MainUtils.MainUtilsThread;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.AddPararBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.PararBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.PinConfigBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.ZmBeanGKTongji;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerPinConfig;
import com.sm.android.locations.location.Utils.ToastUtils;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.sm.android.locations.location.Constant.Constant.IP1;
import static com.sm.android.locations.location.Constant.Constant.IP2;
import static com.sm.android.locations.location.Utils.MainUtils.MainUtils.DS;
import static com.sm.android.locations.location.Utils.MainUtils.MainUtils.location;
import static com.sm.android.locations.location.Utils.MainUtils.MainUtils2.toIMSI;

public class SOSUtils {
    public static String[] permissions = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.CHANGE_WIFI_STATE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.FOREGROUND_SERVICE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.GET_TASKS,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_FINE_LOCATION

    };//申请的权限
    public static List<String> mPermissionList = new ArrayList<>();
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private static final int MY_PERMISSIONS_REQUEST_CALL_CAMERA = 2;

    /**
     * 申请权限
     */
    public static void getPermissions(Activity context) {//获取手机权限
        mPermissionList.clear();
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(context, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permissions[i]);
            }
        }
        if (mPermissionList.isEmpty()) {//未授予的权限为空，表示都授予了
//            Toast.makeText(LoginActivity.this,"已经授权",Toast.LENGTH_LONG).show();
        } else {//请求权限方法
            String[] permissions = mPermissionList.toArray(new String[mPermissionList.size()]);//将List转为数组
            ActivityCompat.requestPermissions(context, permissions, MY_PERMISSIONS_REQUEST_CALL_CAMERA);
        }
    }

    public static void setTITle(TextView tiTle) {
        tiTle.setText("单频便携");
    }

    public static List<ZmBeanGKTongji> zmBeanGKTongjiListCALL = new ArrayList<>();

    public static void mubibiao(Context context, String device, SOSActivity.SOSxuanzemubiao soSxuanzemubiao) {
//        Dialog dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
//        View inflate = LayoutInflater.from(context).inflate(R.layout.sos_mubiaobiaoxuanze, null);
////        TextView tv_title = inflate.findViewById(R.id.tv_title);
////        tv_title.setText("确定要停止管控吗?");
//        Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
//        RecyclerView ry_sos_mubiao = inflate.findViewById(R.id.ry_sos_mubiao);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
////                linearLayoutManager.setStackFromEnd(true);//recyview显示最底部一条
//        ry_sos_mubiao.setLayoutManager(linearLayoutManager);
//
//        DBManagerZMGK dbManagerZM = null;
//        try {
//            dbManagerZM = new DBManagerZMGK(context);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        List<ZmBeanGK> zmBeanss = null;
//        try {
//            zmBeanss = dbManagerZM.getDataAll();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        List<ZmBeanGK> zmBeans = new ArrayList<>();
//        for (int j = 0; j < zmBeanss.size(); j++) {
//            if (zmBeanss.get(j).getMaintype().equals("0") && zmBeanss.get(j).getSb().equals(device)) {
//                zmBeans.add(zmBeanss.get(j));
//            }
//        }
//
//        Log.d("dbManagerZM", "handleMessage: " + i);
//        Log.d("dbManagerZM", "handleMessage:size " + zmBeans.toString());
////            ryZmAdapterGk = new RyZmAdapterGk(context, zmBeans, listsize);//list是imsi列表选中的数据
////            if (et_zhenmasearchdw.getText().length() == 0) {
////                        ry_zhenma.setAdapter(ryZmAdapter);
//
////            ryZmAdapterGk = new RyZmAdapterGk(context, zmBeans, listsize);//list是imsi列表选中的数据
////            recyclerView.setAdapter(ryZmAdapterGk);
//
//        //通过 split 方法拆分数据并放入数组中
//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < zmBeanss.size(); i++) {
//            list.add(zmBeanss.get(i).getImsi());
//        }
//        Log.e("inittongjiIMsi", "init: " + list.toString());
//        List<Map<String, Integer>> list1 = new ArrayList<>();
//        Map<String, Integer> map = new HashMap<>();
//        for (int i = 0; i < list.size(); i++) {
//            Integer integer = map.get(list.get(i));
//            map.put(list.get(i), integer == null ? 1 : integer + 1);
//        }
//        // 方式一
//        Set<String> res = map.keySet();
//        for (String im : res) {
//            System.out.println("a数据：" + im + "共有" + map.get(im));
//        }
//        List<ZmBeanGKTongji> list2 = new ArrayList<>();
//        ZmBeanGKTongji zmBeanGKTongji;
//        Set<Map.Entry<String, Integer>> set = map.entrySet();
//        for (Map.Entry<String, Integer> entry : set) {
//            System.out.println("输出结果重复" + entry.getKey() + "---" + entry.getValue());
//            if (entry.getValue() > 1) {
//                zmBeanGKTongji = new ZmBeanGKTongji();
//                zmBeanGKTongji.setImsi(entry.getKey());
//                zmBeanGKTongji.setNum(entry.getValue() + "");
////                zmBeanGKTongji.setTime(time);
//                list2.add(zmBeanGKTongji);
//
//            }
//
//
//        }
//
//        Log.d("TAGlisttongji", "init: " + list2.toString());
//        for (int i = 0; i < zmBeanss.size(); i++) {
//            for (int j = 0; j < list2.size(); j++) {
//                if (zmBeanss.get(i).getImsi().equals(list2.get(j).getImsi())) {
//                    list2.get(j).setDown(zmBeanss.get(i).getDown());
//                    list2.get(j).setSb(zmBeanss.get(i).getSb());
//                    list2.get(j).setTime(zmBeanss.get(i).getTime());
//                    list2.get(j).setDatatime(zmBeanss.get(i).getDatatime());
//                }
//            }
//        }
//        Log.d("sosutilsTAGlistton", "init: " + list2.toString());
//        //时间倒叙
//
//
//        List<Integer> listsize = new ArrayList<>();
//        for (int i = 0; i < list2.size(); i++) {
//            listsize.add(i + 1);
//
//        }
//
//        XuanzeCallback callback = new XuanzeCallback() {
//            @Override
//            public void Call(List<ZmBeanGKTongji> zmBeanGKTongjiList) {
//                Log.d("XuanzeCallback", "Call: " + zmBeanGKTongjiList.toString());
//                zmBeanGKTongjiListCALL = zmBeanGKTongjiList;
//            }
//        };
////        RyZmAdapterGkMUBIAO ryZmAdapterGk = new RyZmAdapterGkMUBIAO(context, list2, listsize, callback);//list是imsi列表选中的数据
//        ry_sos_mubiao.setAdapter(ryZmAdapterGk);
//        LinearLayoutManager linearLayoutManagera;
//        if (list2.size() > 10) {
//            linearLayoutManagera = new LinearLayoutManager(context);
////                linearLayoutManager.setStackFromEnd(true);//recyview显示最底部一条
//            ry_sos_mubiao.setLayoutManager(linearLayoutManagera);
////                tv_searchNumdw.setText("(" + zmBeans.size() + ")");
//        } else {
//            linearLayoutManagera = new LinearLayoutManager(context);
////                linearLayoutManager.setStackFromEnd(false);//recyview显示最底部一条
//            ry_sos_mubiao.setLayoutManager(linearLayoutManagera);
////                tv_searchNumdw.setText("(" + zmBeans.size() + ")");
//        }
//        //默认 IMSI 名单不选中
//        bt_confirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                List<ZmBeanGKTongji> list = new ArrayList<>();
//                for (int j = 0; j < zmBeanGKTongjiListCALL.size(); j++) {
//                    if (zmBeanGKTongjiListCALL.get(j).isCheck()) {
//                        list.add(zmBeanGKTongjiListCALL.get(j));
//                    }
//
//                }
//                if (list.size() > 10) {
//                    ToastUtils.showToast("定位选择IMSI不得大于10个");
//                    return;
//                }
//                if (list.size() == 0) {
//                    ToastUtils.showToast("没有选中定位选择IMSI");
//                    return;
//                }
//                soSxuanzemubiao.Callback(list, device);
//            }
//        });
//        Button bt_cancel = inflate.findViewById(R.id.bt_cancel);
//        bt_cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//                dialog.cancel();
//            }
//        });
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.setContentView(inflate);
//        //获取当前Activity所在的窗体
//        Window dialogWindow = dialog.getWindow();
//        //设置Dialog从窗体底部弹出
//        dialogWindow.setGravity(Gravity.CENTER);
//        //获得窗体的属性
//        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
////                           lp.y = 20;//设置Dialog距离底部的距离
////                          将属性设置给窗体
//        dialogWindow.setAttributes(lp);
//        dialog.show();//显示对话框
    }


    //发送黑名单
    public static void sendBlackListRun(List<AddPararBean> sendListBlack, final String tf1, final String spinnerS1, final Context context, List<AddPararBean> listImsiListTrue) {
        if (sendListBlack.size() == 0) {
            ToastUtils.showToast("没有符合条件的IMSI");
            return;
        }
        List<String> list = new ArrayList<>();
//        Log.d(TAG, "sendBlacListRun:list" + list);
        for (int i = 0; i < sendListBlack.size(); i++) {
            list.add(sendListBlack.get(i).getImsi());
        }
        int totalMy = list.size();
//        Log.d(TAG, "sendBlackListRun:totalMy" + totalMy);
        //消息头
        StringBuffer str = new StringBuffer("aaaa555553f06401000000ff");
        //黑名单数量
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
            sendrun(str, sendListBlack, tf1, spinnerS1, context, listImsiListTrue);//开始发送

        }
    }

    //设备1已开始发送
    public static void sendrun(final StringBuffer strData, final List<AddPararBean> sendListBlack, final String tf1, final String spinnerS1, final Context context, final List<AddPararBean> listImsiListTrue) {
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
                    address = InetAddress.getByName(IP1);

                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (SocketException e) {
                    e.printStackTrace();
                }
                ;
                DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, address, 3345);
//                        DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, reIP, Integer.parseInt(et_port.getText().toString()));
                Log.e("nzqsendstaart1Black", "run: sendsocket端口号" + outputPacket.getPort() + "Ip地址:" + outputPacket.getAddress().toString() + "数据:" + outputPacket.getData());
                try {
                    //                                    socket.send(outputPacket);
                    DS.send(outputPacket);
//                    interrupted();
                    try {
                        Thread.sleep(2000);
                        sb1Locations(sendListBlack, tf1, spinnerS1, context, listImsiListTrue);
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
    public static void sb1Locations(final List<AddPararBean> sendListBlack, final String tf1, final String spinnerS1, final Context context, final List<AddPararBean> listImsiListTrue) {


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
//                    if (listImsiListTrue.size() > 0 && listImsiListTrue != null) {
//                        for (int i = 0; i < listImsiListTrue.size(); i++) {
////                            if (listImsiListTrue.get(i).getYy().equals(yy)) {
//                                sendListBlack.add(listImsiListTrue.get(i));
////                            }
//                        }
//                    }
//
//                    if (sendListBlack.size() > 0 && sendListBlack != null) {
////                            Log.d(TAG, "sendBlackList: " + sendListBlack);
//                        if (sendListBlack.size() > 20) {
//                            ToastUtils.showToast("符合条件的黑名单列表大于20");
//                        } else {
////                                sendBlackListRun(sendListBlack);
//                        }
////
//                    } else {
//                        ToastUtils.showToast("没有符合条件的IMSI");
//                    }

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
                        address = InetAddress.getByName(IP1);
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
//
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


            }
        }).start();

    }
    //发送黑名单
    public static void sendBlackListRun2(List<AddPararBean> sendListBlack, final String tf1, final String spinnerS1, final Context context, List<AddPararBean> listImsiListTrue) {
        if (sendListBlack.size() == 0) {
            ToastUtils.showToast("没有符合条件的IMSI");
            return;
        }
        List<String> list = new ArrayList<>();
//        Log.d(TAG, "sendBlacListRun:list" + list);
        for (int i = 0; i < sendListBlack.size(); i++) {
            list.add(sendListBlack.get(i).getImsi());
        }
        int totalMy = list.size();
//        Log.d(TAG, "sendBlackListRun:totalMy" + totalMy);
        //消息头
        StringBuffer str = new StringBuffer("aaaa555553f06401000000ff");
        //黑名单数量
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
            sendrun2(str, sendListBlack, tf1, spinnerS1, context, listImsiListTrue);//开始发送

        }
    }

    //设备1已开始发送
    public static void sendrun2(final StringBuffer strData, final List<AddPararBean> sendListBlack, final String tf1, final String spinnerS1, final Context context, final List<AddPararBean> listImsiListTrue) {
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
    public static void sb2Locations(final List<AddPararBean> sendListBlack, final String tf1, final String spinnerS1, final Context context, final List<AddPararBean> listImsiListTrue) {

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
//                    if (listImsiListTrue.size() > 0 && listImsiListTrue != null) {
//                        for (int i = 0; i < listImsiListTrue.size(); i++) {
////                            if (listImsiListTrue.get(i).getYy().equals(yy)) {
//                                sendListBlack.add(listImsiListTrue.get(i));
////                            }
//                        }
//                    }

//                    if (sendListBlack.size() > 0 && sendListBlack != null) {
////                            Log.d(TAG, "sendBlackList: " + sendListBlack);
//                        if (sendListBlack.size() > 20) {
//                            ToastUtils.showToast("符合条件的黑名单列表大于20");
//                        } else {
////                                sendBlackListRun(sendListBlack);
//                        }
////
//                    } else {
//                        ToastUtils.showToast("没有符合条件的IMSI");
//                    }

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
//
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


            }
        }).start();

    }

}
