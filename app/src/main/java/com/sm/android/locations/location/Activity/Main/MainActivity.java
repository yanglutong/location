package com.sm.android.locations.location.Activity.Main;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.speech.tts.TextToSpeech;


import android.os.Bundle;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.sm.android.locations.location.Activity.AddParam.AddParamActivity;
import com.sm.android.locations.location.Activity.AddParam.ParamActivity;
import com.sm.android.locations.location.Activity.Communit.CommunitViewPagerActivity;
import com.sm.android.locations.location.Activity.Device.DeviceInfoActivity;
import com.sm.android.locations.location.Activity.Main.Adapter.RyImsiAdapter;
import com.sm.android.locations.location.Activity.Main.Adapter.RyZmAdapter;
import com.sm.android.locations.location.Activity.Main.IT.SaoPinCallback;
import com.sm.android.locations.location.Activity.Main.IT.ZmDataCallBack;
import com.sm.android.locations.location.Activity.Main.Objects.States;
import com.sm.android.locations.location.Activity.PinConfig.PinConfigViewPagerActivity;
import com.sm.android.locations.location.Activity.SaoPin.SaoPinActivity.SaoPinActivity;
import com.sm.android.locations.location.Activity.SaoPin.SaopinList.SaoPinSetingActivity;
import com.sm.android.locations.location.Base.BaseActivity;
import com.sm.android.locations.location.Constant.Constant;
import com.sm.android.locations.location.R;
import com.sm.android.locations.location.Test.ReceiveTask;
import com.sm.android.locations.location.Test.Setzy;
import com.sm.android.locations.location.Test.setxq;
import com.sm.android.locations.location.Utils.DialogUtils;
import com.sm.android.locations.location.Utils.LoginUtils.LoginUtils;
import com.sm.android.locations.location.Utils.MainUtils.AddMenuUtils;
import com.sm.android.locations.location.Utils.MainUtils.DbUtils;
import com.sm.android.locations.location.Utils.MainUtils.Hex;
import com.sm.android.locations.location.Utils.MainUtils.MainUtils;
import com.sm.android.locations.location.Utils.MainUtils.MainUtils2;
import com.sm.android.locations.location.Utils.MainUtils.MainUtilsThread;
import com.sm.android.locations.location.Utils.MainUtils.StringConvertUtils;
import com.sm.android.locations.location.Utils.MyUtils;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.AddPararBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.Conmmunit01Bean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.LogBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.PararBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.PinConfigBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.SaopinBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.SpBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.ZmBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManager01;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerAddParam;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerLog;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerPinConfig;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerZM;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerZY;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagersaopin;
import com.sm.android.locations.location.Utils.ToastUtils;
import com.sm.android.locations.location.Utils.UtilsView;
import com.sm.android.locations.location.Utils.View.LineChartView;
import com.sm.android.locations.location.Utils.pop.DLPopItem;
import com.sm.android.locations.location.Utils.pop.DLPopupWindow;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import static com.sm.android.locations.location.App.App.context;
import static com.sm.android.locations.location.Constant.Constant.BLACKLOCATION1;
import static com.sm.android.locations.location.Constant.Constant.BLACKLOCATION2;
import static com.sm.android.locations.location.Constant.Constant.BLACKOPENSET1;
import static com.sm.android.locations.location.Constant.Constant.BLACKOPENSET2;
import static com.sm.android.locations.location.Constant.Constant.BLACKTIME1;
import static com.sm.android.locations.location.Constant.Constant.BLACKTIME2;
import static com.sm.android.locations.location.Constant.Constant.BLACKTIMESET1;
import static com.sm.android.locations.location.Constant.Constant.BLACKTIMESET2;
import static com.sm.android.locations.location.Constant.Constant.BOARDTEMPERATURE1;
import static com.sm.android.locations.location.Constant.Constant.CALLBLACKFLAG1;
import static com.sm.android.locations.location.Constant.Constant.CALLBLACKFLAG2;
import static com.sm.android.locations.location.Constant.Constant.CALLBLACKFLAGSET1;
import static com.sm.android.locations.location.Constant.Constant.CALLBLACKFLAGSET2;
import static com.sm.android.locations.location.Constant.Constant.CALLBLACKLOCATION1;
import static com.sm.android.locations.location.Constant.Constant.CALLBLACKLOCATION2;
import static com.sm.android.locations.location.Constant.Constant.CALLBLACKOPEN1;
import static com.sm.android.locations.location.Constant.Constant.CALLBLACKOPEN2;
import static com.sm.android.locations.location.Constant.Constant.CLEAR;
import static com.sm.android.locations.location.Constant.Constant.DOWNPIN1;
import static com.sm.android.locations.location.Constant.Constant.DOWNPIN2;
import static com.sm.android.locations.location.Constant.Constant.GFFLAG1;
import static com.sm.android.locations.location.Constant.Constant.GFFLAG2;
import static com.sm.android.locations.location.Constant.Constant.IP1;
import static com.sm.android.locations.location.Constant.Constant.IP2;
import static com.sm.android.locations.location.Constant.Constant.TITLESD;
import static com.sm.android.locations.location.Constant.Constant.TITLESDZM;
import static com.sm.android.locations.location.Constant.Constant.TITLEZD;
import static com.sm.android.locations.location.Constant.Constant.TITLEZDZM;
import static com.sm.android.locations.location.Constant.Constant.ZYCHAXYN;
import static com.sm.android.locations.location.Utils.MainUtils.MainUtils.DS;
import static com.sm.android.locations.location.Utils.MainUtils.MainUtils.i;
import static com.sm.android.locations.location.Utils.MainUtils.MainUtils.location;
import static com.sm.android.locations.location.Utils.MainUtils.MainUtils2.toIMSI;
import static com.sm.android.locations.location.Utils.MyUtils.removeDuplicate;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    public int blackNUM1 = 1;
    public int blackNUM2 = 1;
    public String imsi11 = "";
    public String imsi12 = "";
    public String imsi21 = "";
    public String imsi22 = "";
    public String DOWN11 = "";
    public String DOWN12 = "";
    public String DOWN21 = "";
    public String DOWN22 = "";
    @SuppressLint("NewApi")
    DecimalFormat dfBaoshu = new DecimalFormat("###");
    String itemtype1 = "", itemtype2 = "";//??????????????????????????????
    public static List<SpBean> SPBEANLIST1 = new ArrayList<>();
    public static List<SpBean> SPBEANLIST2 = new ArrayList<>();
    private boolean phone1sp = false;//????????????1?????????????????????  ????????????
    private boolean phone2sp = false;//????????????2?????????????????????
    private boolean SaoPinB1 = false;
    private boolean SaoPinB2 = false;
    private boolean FengShanFlag = false;//???????????? true ??????,false???
    private int SAOPIN = 1;//?????????type??????1
    private int SAOPIN2 = 1;//?????????type??????1
    DBManagerZM dbManagerZM;
    Dialog dialog;
    View inflate;
    private boolean sb1locationgFlag1 = false;
    private String sp1MAX1value = "";//??????1?????????1?????????
    private String sp1MAX2value = "";//??????1?????????2?????????
    private String sp2MAX1value = "";//??????2?????????1?????????
    private String sp2MAX2value = "";//??????2?????????2?????????
    private SpBean spBean1 = new SpBean();
    private SpBean spBean2 = new SpBean();
    private SpBean spBean154 = new SpBean();
    private SpBean spBean254 = new SpBean();
    //id
    CheckBox cbzt1_d, cbzt1_z, cbzt1_g, cbzt2_d, cbzt2_z, cbzt2_g;// ??????????????? ??????
    TextView tv_r1, tv_r2, tv_searchNum;
    TextView tv_wendu;
    ImageView iv_wendu;
    ImageView iv_fengshan;
    EditText et_zhenmasearch;
    HandlerThread mHandlerThread = new HandlerThread("JavenThread");

    ZmDataCallBack zmDataCallBack = new ZmDataCallBack() {
        @Override
        public void sucess(String dir, String datetime) {

        }

        @Override
        public void deleall() {
            try {
                dbManagerZM = new DBManagerZM(MainActivity.this);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //?????????????????????imsi??????
            List<ZmBean> zmBeans = null;
            try {
                zmBeans = dbManagerZM.getDataAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ToastUtils.showToast("????????????????????????");
            Log.d("dbManagerZM", "handleMessage: " + i);
            Log.d("dbManagerZM", "handleMessage:size " + zmBeans.toString());
            List<Integer> listsize = new ArrayList<>();
            for (int i = 0; i < zmBeans.size(); i++) {
                listsize.add(i + 1);
            }
            if (zmBeans == null) {
                zmBeans = new ArrayList<>();
                ryZmAdapter = new RyZmAdapter(Basecontext, zmBeans, listsize);//list???imsi?????????????????????
                ry_zhenma.setAdapter(ryZmAdapter);
                tv_searchNum.setText("(" + "0" + ")");
            } else {
                ryZmAdapter = new RyZmAdapter(Basecontext, zmBeans, listsize);//list???imsi?????????????????????
                ry_zhenma.setAdapter(ryZmAdapter);
                tv_searchNum.setText("(" + listsize.size() + ")");
            }
        }
    };
    RyImsiAdapter.IDialogPinConfig config = new RyImsiAdapter.IDialogPinConfig() {
        @Override
        public void call(final String imsi, String sb) {
            if (sb.equals("1")) {
//                new CircleDialog.Builder((FragmentActivity) mContext)
//                        .setTitle("")
//                        .setText("???????????????" + imsi + "????")
//                        .setTitleColor(Color.parseColor("#00acff"))
//                        .setNegative("??????", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                new Thread(new Runnable() {
//
//                                    @Override
//                                    public void run() {
//                                        String sa = "";
//                                        if (tf1.equals("TDD")) {
//                                            sa = "01";
//                                        }
//                                        if (tf1.equals("FDD")) {
//                                            sa = "00";
//                                        }
//                                        DatagramSocket socket = null;
//                                        InetAddress address = null;//????????????ip??????
//                                        Log.e("nzq", "run: nzqsend");
//
////                                        Log.d("nzqsendimsi", "run: " + sendListBlack.get(0).getImsi());
////                                        String location = location(sendListBlack.get(0).getImsi(), "04", sa);
////                                        Log.d(TAG, "run: " + location);
//                                        byte[] outputData = MainUtilsThread.hexStringToByteArray(location(imsi, "04", sa));
//                                        try {
//                                            socket = new DatagramSocket();
//                                            address = InetAddress.getByName(IP1);
//                                        } catch (UnknownHostException e) {
//                                            e.printStackTrace();
//                                        } catch (SocketException e) {
//                                            e.printStackTrace();
//                                        }
//                                        ;
//                                        DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, address, 3345);
////                        DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, reIP, Integer.parseInt(et_port.getText().toString()));
//                                        Log.e("nzqsendstart1", "run: sendsocket?????????" + outputPacket.getPort() + "Ip??????:" + outputPacket.getAddress().toString() + "??????:" + outputPacket.getData());
//                                        try {
//                                            sb1locationgFlag = true;
//                                            socket.send(outputPacket);
////
//                                        } catch (Exception e) {
//                                            e.printStackTrace();
//                                        }
//                                        LocationFlag1 = true;
//                                        long l = System.currentTimeMillis();
////
//                                    }
//                                }).start();
//                            }
//                        })
//                        .setPositive("??????", null)
//                        .show();


                dialog = new Dialog(MainActivity.this, R.style.menuDialogStyleDialogStyle);
                inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_item_title, null);
                TextView tv_title = inflate.findViewById(R.id.tv_title);
                tv_title.setText("???????????????" + imsi + "????");
                Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
                bt_confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
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

//                                        Log.d("nzqsendimsi", "run: " + sendListBlack.get(0).getImsi());
//                                        String location = location(sendListBlack.get(0).getImsi(), "04", sa);
//                                        Log.d(TAG, "run: " + location);
                                byte[] outputData = MainUtilsThread.hexStringToByteArray(location(imsi, "04", sa, MainActivity.this, 1));
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
                                Log.e("nzqsendstart1", "run: sendsocket?????????" + outputPacket.getPort() + "Ip??????:" + outputPacket.getAddress().toString() + "??????:" + outputPacket.getData());
                                try {
                                    sb1locationgFlag = true;
                                    //                                    socket.send(outputPacket);
                                    DS.send(outputPacket);
//
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                LocationFlag1 = true;
                                long l = System.currentTimeMillis();
//
                            }
                        }).start();
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

            } else if (sb.equals("2")) {

                dialog = new Dialog(MainActivity.this, R.style.menuDialogStyleDialogStyle);
                inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_item_title, null);
                TextView tv_title = inflate.findViewById(R.id.tv_title);
                tv_title.setText("???????????????" + imsi + "????");
                Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
                bt_confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                String sa = "";
                                if (tf2.equals("TDD")) {
                                    sa = "01";
                                }
                                if (tf2.equals("FDD")) {
                                    sa = "00";
                                }
                                DatagramSocket socket = null;
                                InetAddress address = null;//????????????ip??????
//                                        Log.e("nzq", "run: nzqsend");
//                                        Log.d("nzqsendimsi", "run: " + sendListBlack.get(0).getImsi());
//                                        String location = location(sendListBlack.get(0).getImsi(), "04", sa);
//                                        Log.d(TAG, "run: " + location);
                                byte[] outputData = MainUtilsThread.hexStringToByteArray(location(imsi, "04", sa, MainActivity.this, 2));
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
                                Log.e("nzqsendstart1", "run: sendsocket?????????" + outputPacket.getPort() + "Ip??????:" + outputPacket.getAddress().toString() + "??????:" + outputPacket.getData());
                                try {
                                    sb2locationgFlag = true;
                                    //                                    socket.send(outputPacket);
                                    DS.send(outputPacket);
//
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                LocationFlag2 = true;
                                long l = System.currentTimeMillis();
//
                            }
                        }).start();
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


            } else {
                ToastUtils.showToast("?????????");

            }
        }
    };
    //    aaa
    int ii = 0;
    SaoPinCallback saoPinCallback = new SaoPinCallback() {
        @Override
        public void sucess(int sb, int i) {
//            ToastUtils.showToast("?????????i==" + i + "?????????:" + sb);
            Log.d("saoPinCallback", "sucess: ??????" + sb + "i===" + i);
            if (sb == 1) {//??????1
                SAOPIN = i;
                if (tf1.equals("TDD")) {
                    if (i == 1) {//??????TDD
//                        ToastUtils.showToast("????????????TDD");
                        sp1MAX1value = "";
                        sp1MAX2value = "";
//                        SaoPinB1 = false;
                        if (SaoPinB1 == false) {

                        } else {
                            tv_r1.setText("");
                        }
                        MainUtils.start1SNF(IP1, Constant.SNFTDD);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                MainUtils.OpenGF1(1, 1, handler);
                                GFFLAG1 = 3;

                            }
                        }, 3000);

//                      sb1zhishiFlag = true;

                        if (itemtype1.equals(TITLEZD)) {//?????????????????????
//                            Set1StatusBar("??????????????????");
                            CALLBLACKOPEN1 = false;
                            sb1zhishiFlag = true;
                            try {
                                saopinBeanList = dbManagersaopin.getStudent(); //?????????????????????
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("??????????????????");
                                    saopinSend1(saopinBeanList, tf1, SAOPIN);
//                                            MainUtils.start1SNF(IP1);
//                                            ToastUtils.showToast("snf?????????");
                                    GFFLAG1 = 1;
                                } else {
                                    ToastUtils.showToast("??????????????????");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else {
//                            Set1StatusBar("??????????????????");
                            CALLBLACKOPEN1 = false;
                            sb1zhishiFlag = true;
                            try {
                                saopinBeanList = dbManagersaopin.getStudent(); //?????????????????????
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("??????????????????");
                                    saopinSend1(saopinBeanList, tf1, SAOPIN);
//                                            MainUtils.start1SNF(IP1);
//                                            ToastUtils.showToast("snf?????????");
                                    GFFLAG1 = 1;
                                } else {
                                    ToastUtils.showToast("??????????????????");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                        return;
                    }
                    if (i == 2) {//??????FDD   ????????????
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                String titles = "";
                                if (tf1.equals("TDD")) {
                                    titles = "FDD";
//                                    MainUtils.start1SNF(IP1, Constant.SNFFDD);
                                }
                                if (tf1.equals("FDD")) {
                                    titles = "TDD";
                                    MainUtils.start1SNF(IP1, Constant.SNFTDD);
                                }
                                MainUtils.Qiehuanzs(titles, IP1);

//                                if (title.getText().toString().equals(getString(R.string.activity_saopintitle))) {//?????????????????????
//                                    sp1MAX1value = "";
//                                    sp1MAX2value = "";
//                                    tv_r1.setText("");
//                                }
                                if (itemtype1.equals(TITLEZD)) {
                                    sp1MAX1value = "";
                                    sp1MAX2value = "";
                                    tv_r1.setText("");
                                }

                            }
                        }, 3000);
                        return;
                    }
                    if (i == 3) {//??????FDD
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                String titles = "";
                                if (tf1.equals("TDD")) {
                                    titles = "FDD";
                                    MainUtils.start1SNF(IP1, Constant.SNFFDD);
                                }
                                if (tf1.equals("FDD")) {
                                    titles = "TDD";
                                    MainUtils.start1SNF(IP1, Constant.SNFTDD);
                                }
                                MainUtils.Qiehuanzs(titles, IP1);

//                                if (title.getText().toString().equals(getString(R.string.activity_saopintitle))) {//?????????????????????
//                                    sp1MAX1value = "";
//                                    sp1MAX2value = "";
//                                    tv_r1.setText("");
//                                }
                                if (itemtype1.equals(TITLEZD)) {
                                    sp1MAX1value = "";
                                    sp1MAX2value = "";
                                    tv_r1.setText("");
                                }

                            }
                        }, 3000);
                        return;
                    }
                    if (i == 4) {//??????FDD
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                String titles = "";
                                if (tf1.equals("TDD")) {
                                    titles = "FDD";
                                    MainUtils.start1SNF(IP1, Constant.SNFFDD);
                                }
                                if (tf1.equals("FDD")) {
                                    titles = "TDD";
                                    MainUtils.start1SNF(IP1, Constant.SNFTDD);
                                }
                                MainUtils.Qiehuanzs(titles, IP1);

//                                if (title.getText().toString().equals(getString(R.string.activity_saopintitle))) {//?????????????????????
//                                    sp1MAX1value = "";
//                                    sp1MAX2value = "";
//                                    tv_r1.setText("");
//                                }
                                if (itemtype1.equals(TITLEZD)) {
                                    sp1MAX1value = "";
                                    sp1MAX2value = "";
                                    tv_r1.setText("");
                                }

                            }
                        }, 3000);
                        return;
                    }
                }
                if (tf1.equals("FDD")) {
                    if (i == 1) {//??????TDD
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                String titles = "";
                                if (tf1.equals("TDD")) {
                                    titles = "FDD";
                                    MainUtils.start1SNF(IP1, Constant.SNFFDD);
                                }
                                if (tf1.equals("FDD")) {
                                    titles = "TDD";
                                    MainUtils.start1SNF(IP1, Constant.SNFTDD);
                                }
                                MainUtils.Qiehuanzs(titles, IP1);
//                                if (title.getText().toString().equals(getString(R.string.activity_saopintitle))) {//?????????????????????
//                                    sp1MAX1value = "";
//                                    sp1MAX2value = "";
//                                    tv_r1.setText("");
//                                }
                                if (itemtype1.equals(TITLEZD)) {
                                    sp1MAX1value = "";
                                    sp1MAX2value = "";
                                    tv_r1.setText("");
                                }

                            }
                        }, 3000);
                        return;
                    }
                    if (i == 2) {//??????FDD
//                        ToastUtils.showToast("????????????FDD---");
                        sb1zhishiFlag = true;
//                                    MainUtils.start1SNF(IP1);
                        MainUtils.start1SNF(IP1, Constant.SNFFDD);

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                saopinSend1(saopinBeanList, tf1, SAOPIN);

//                                if (title.getText().toString().equals(getString(R.string.activity_saopintitle))) {//?????????????????????
//                                    sp1MAX1value = "";
//                                    sp1MAX2value = "";
//                                    tv_r1.setText("");
//                                }
                                if (itemtype1.equals(TITLEZD)) {
                                    sp1MAX1value = "";
                                    sp1MAX2value = "";
                                    tv_r1.setText("");
                                }

                            }
                        }, 3000);
                        return;
                    }
                    if (i == 3) {//??????FDD
//                        ToastUtils.showToast("????????????FDD---");
                        sb1zhishiFlag = true;
//                                    MainUtils.start1SNF(IP1);
                        MainUtils.start1SNF(IP1, Constant.SNFFDD);

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                saopinSend1(saopinBeanList, tf1, SAOPIN);

//                                if (title.getText().toString().equals(getString(R.string.activity_saopintitle))) {//?????????????????????
//                                    sp1MAX1value = "";
//                                    sp1MAX2value = "";
//                                    tv_r1.setText("");
//                                }
                                if (itemtype1.equals(TITLEZD)) {
                                    sp1MAX1value = "";
                                    sp1MAX2value = "";
                                    tv_r1.setText("");
                                }

                            }
                        }, 3000);
                        return;
                    }
                    if (i == 4) {//??????FDD
//                        ToastUtils.showToast("????????????FDD---");
                        sb1zhishiFlag = true;
//                                    MainUtils.start1SNF(IP1);
                        MainUtils.start1SNF(IP1, Constant.SNFFDD);

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                saopinSend1(saopinBeanList, tf1, SAOPIN);

//                                if (title.getText().toString().equals(getString(R.string.activity_saopintitle))) {//?????????????????????
//                                    sp1MAX1value = "";
//                                    sp1MAX2value = "";
//                                    tv_r1.setText("");
//                                }
                                if (itemtype1.equals(TITLEZD)) {
                                    sp1MAX1value = "";
                                    sp1MAX2value = "";
                                    tv_r1.setText("");
                                }

                            }
                        }, 3000);
                        return;
                    }
                }
            }
            //??????2
            if (sb == 2) {
                SAOPIN2 = i;
                if (tf2.equals("TDD")) {
                    if (i == 1) {//??????TDD
//                        ToastUtils.showToast("1????????????TDD");
                        sp2MAX1value = "";
                        sp2MAX2value = "";

                        if (SaoPinB1 == false) {

                        } else {
                            tv_r2.setText("");
                        }
                        MainUtils.start1SNF(IP2, Constant.SNFTDD);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                GFFLAG2 = 3;
                                MainUtils.OpenGF2(2, 1, handler);

                                Log.d("nzqGFFLAG2", "run: " + 2);
                                if (itemtype2.equals(TITLEZD)) {//?????????????????????
//                                    Set2StatusBar("??????????????????");
                                    Log.d("200142", "handleMessage: " + 4);
                                    CALLBLACKOPEN2 = false;
                                    sb2zhishiFlag = true;
                                    try {
                                        saopinBeanList = dbManagersaopin.getStudent(); //?????????????????????
                                        if (saopinBeanList != null && saopinBeanList.size() > 0) {
                                            saopinSend2(saopinBeanList, tf2, SAOPIN2);
//                                            MainUtils.start1SNF(IP1);
//                                            ToastUtils.showToast("snf?????????");
                                            GFFLAG2 = 1;
                                        } else {
                                            ToastUtils.showToast("??????????????????");
                                        }

                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    Log.d("200142", "handleMessage: " + 5);
//                                    Set2StatusBar("??????????????????");
                                    CALLBLACKOPEN2 = false;
                                    sb2zhishiFlag = true;
                                    try {
                                        saopinBeanList = dbManagersaopin.getStudent(); //?????????????????????
                                        if (saopinBeanList != null && saopinBeanList.size() > 0) {
                                            saopinSend2(saopinBeanList, tf2, SAOPIN2);
//                                            MainUtils.start1SNF(IP1);
//                                            ToastUtils.showToast("snf?????????");
                                            GFFLAG2 = 1;
                                        } else {
                                            ToastUtils.showToast("??????????????????");
                                        }

                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }, 3000);

//                                    sb1zhishiFlag = true;
                        return;
                    }
                    if (i == 2) {//??????FDD   ????????????
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                String titles = "";
                                if (tf2.equals("TDD")) {
                                    titles = "FDD";
                                    MainUtils.start1SNF(IP2, Constant.SNFFDD);
                                }
                                if (tf2.equals("FDD")) {
                                    titles = "TDD";
                                    MainUtils.start1SNF(IP2, Constant.SNFTDD);
                                }
                                MainUtils.Qiehuanzs(titles, IP2);

//                                if (title.getText().toString().equals(getString(R.string.activity_saopintitle))) {//?????????????????????
//                                    sp2MAX1value = "";
//                                    sp2MAX2value = "";
//                                    tv_r2.setText("");
//                                }
                                if (itemtype2.equals(TITLEZD)) {
                                    sp2MAX1value = "";
                                    sp2MAX2value = "";
                                    tv_r2.setText("");
                                }

                            }
                        }, 3000);
                        return;
                    }
                    if (i == 3) {//??????FDD
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                String titles = "";
                                if (tf2.equals("TDD")) {
                                    titles = "FDD";
                                    MainUtils.start1SNF(IP2, Constant.SNFFDD);
                                }
                                if (tf2.equals("FDD")) {
                                    titles = "TDD";
                                    MainUtils.start1SNF(IP2, Constant.SNFTDD);
                                }
                                MainUtils.Qiehuanzs(titles, IP2);

//                                if (title.getText().toString().equals(getString(R.string.activity_saopintitle))) {//?????????????????????
//                                    sp2MAX1value = "";
//                                    sp2MAX2value = "";
//                                    tv_r2.setText("");
//                                }
                                if (itemtype2.equals(TITLEZD)) {
                                    sp2MAX1value = "";
                                    sp2MAX2value = "";
                                    tv_r2.setText("");
                                }

                            }
                        }, 3000);
                        return;
                    }
                    if (i == 4) {//??????FDD
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                String titles = "";
                                if (tf2.equals("TDD")) {
                                    titles = "FDD";
                                    MainUtils.start1SNF(IP2, Constant.SNFFDD);
                                }
                                if (tf2.equals("FDD")) {
                                    titles = "TDD";
                                    MainUtils.start1SNF(IP2, Constant.SNFTDD);
                                }
                                MainUtils.Qiehuanzs(titles, IP2);

//                                if (title.getText().toString().equals(getString(R.string.activity_saopintitle))) {//?????????????????????
//                                    sp2MAX1value = "";
//                                    sp2MAX2value = "";
//                                    tv_r2.setText("");
//                                }
                                if (itemtype2.equals(TITLEZD)) {
                                    sp2MAX1value = "";
                                    sp2MAX2value = "";
                                    tv_r2.setText("");
                                }

                            }
                        }, 3000);
                        return;
                    }
                }
                if (tf2.equals("FDD")) {
                    if (i == 1) {//??????TDD
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                String titles = "";
                                if (tf2.equals("TDD")) {
                                    titles = "FDD";
                                    MainUtils.start1SNF(IP2, Constant.SNFFDD);
                                }
                                if (tf2.equals("FDD")) {
                                    titles = "TDD";
                                    MainUtils.start1SNF(IP2, Constant.SNFTDD);
                                }
                                MainUtils.Qiehuanzs(titles, IP2);

//                                if (title.getText().toString().equals(getString(R.string.activity_saopintitle))) {//?????????????????????
//                                    sp2MAX1value = "";
//                                    sp2MAX2value = "";
//                                    tv_r2.setText("");
//                                }
                                if (itemtype2.equals(TITLEZD)) {
                                    sp2MAX1value = "";
                                    sp2MAX2value = "";
                                    tv_r2.setText("");
                                }

                            }
                        }, 3000);
                        return;
                    }
                    if (i == 2) {//??????FDD
//                        ToastUtils.showToast("????????????FDD---");
                        sb2zhishiFlag = true;
//                                    MainUtils.start1SNF(IP1);
                        MainUtils.start1SNF(IP1, Constant.SNFFDD);

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                saopinSend2(saopinBeanList, tf2, SAOPIN2);

//                                if (title.getText().toString().equals(getString(R.string.activity_saopintitle))) {//?????????????????????
//                                    sp2MAX1value = "";
//                                    sp2MAX2value = "";
//                                    tv_r2.setText("");
//                                }
                                if (itemtype2.equals(TITLEZD)) {
                                    sp2MAX1value = "";
                                    sp2MAX2value = "";
                                    tv_r2.setText("");
                                }

                            }
                        }, 3000);
                        return;
                    }
                    if (i == 3) {//??????FDD
//                        ToastUtils.showToast("????????????FDD---");
                        sb2zhishiFlag = true;
//                                    MainUtils.start1SNF(IP1);
                        MainUtils.start1SNF(IP1, Constant.SNFFDD);

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                saopinSend2(saopinBeanList, tf2, SAOPIN2);

//                                if (title.getText().toString().equals(getString(R.string.activity_saopintitle))) {//?????????????????????
//                                    sp2MAX1value = "";
//                                    sp2MAX2value = "";
//                                    tv_r2.setText("");
//                                }
                                if (itemtype2.equals(TITLEZD)) {
                                    sp2MAX1value = "";
                                    sp2MAX2value = "";
                                    tv_r2.setText("");
                                }

                            }
                        }, 3000);
                        return;
                    }
                    if (i == 4) {//??????FDD
//                        ToastUtils.showToast("????????????FDD---");
                        sb2zhishiFlag = true;
//                                    MainUtils.start1SNF(IP1);
                        MainUtils.start1SNF(IP1, Constant.SNFFDD);

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                saopinSend2(saopinBeanList, tf2, SAOPIN2);

//                                if (title.getText().toString().equals(getString(R.string.activity_saopintitle))) {//?????????????????????
//                                    sp2MAX1value = "";
//                                    sp2MAX2value = "";
//                                    tv_r2.setText("");
//                                }
                                if (itemtype2.equals(TITLEZD)) {
                                    sp2MAX1value = "";
                                    sp2MAX2value = "";
                                    tv_r2.setText("");
                                }

                            }
                        }, 3000);
                        return;
                    }
                }
            }
        }

        @Override
        public void sucessphone(int sb, String down, SpBean spBean, boolean phonesp) {
            Log.d("sucessphonespBean", "sucessphone: " + spBean.toString());
            if (sb == 1) {
                phone1sp = phonesp;
                sp1MAX1value = down;
                spBean1 = spBean;

                if (MainUtils2.YY(spBean1.getPlmn()).equals("??????")) {
                    SAOPIN = 1;
                    Log.d("aaaplmnsucessphone", "sucessphone: +");
                    if (tf1.equals("TDD")) {
                        if (bts_start1.getText().equals(TITLEZDZM)) {//????????????
                            MainUtils.sbzmLocation(IP1, MainActivity.this);  //??????????????????
                        } else {//????????????
                            sb1Clear();
                        }
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
                        MainUtils.Qiehuanzs(titles, IP1);
                    }
                } else if (spBean.getBand().equals("1") || spBean.getBand().equals("3") || spBean.getBand().equals("5") || spBean.getBand().equals("8") || MainUtils2.YY(spBean1.getPlmn()).equals("??????")) {//??????FDD
                    SAOPIN = 2;
                    if (tf1.equals("FDD")) {
                        if (bts_start1.getText().equals(TITLEZDZM)) {//????????????
                            MainUtils.sbzmLocation(IP1, MainActivity.this);  //??????????????????
                        } else {//????????????
                            sb1Clear();
                        }


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
                        MainUtils.Qiehuanzs(titles, IP1);
                    }
                } else if (MainUtils2.YY(spBean1.getDown()).equals("??????")) {
                    SAOPIN = 3;
                    if (tf1.equals("FDD")) {
                        if (bts_start1.getText().equals(TITLEZDZM)) {//????????????
                            MainUtils.sbzmLocation(IP1, MainActivity.this);  //??????????????????
                        } else {//????????????
                            sb1Clear();
                        }
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
                        MainUtils.Qiehuanzs(titles, IP1);
                    }
                } else if (MainUtils2.YY(spBean1.getDown()).equals("??????")) {
                    SAOPIN = 4;
                    if (tf1.equals("FDD")) {
                        if (bts_start1.getText().equals(TITLEZDZM)) {//????????????
                            MainUtils.sbzmLocation(IP1, MainActivity.this);  //??????????????????
                        } else {//????????????
                            sb1Clear();
                        }
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
                        MainUtils.Qiehuanzs(titles, IP1);
                    }
                }

            }
            if (sb == 2) {
                sp2MAX1value = down;
                spBean154 = spBean;
                phone2sp = phonesp;
                Log.d("spBean154a", "sucessphone: " + spBean154.toString());
                if (MainUtils2.YY(spBean154.getPlmn()).equals("??????") && !spBean154.getBand().equals("1") && !spBean154.getBand().equals("3") && !spBean154.getBand().equals("5") && !spBean154.getBand().equals("8")) {
                    SAOPIN2 = 1;
                    if (tf2.equals("TDD")) {
                        if (bts_start2.getText().equals(TITLEZDZM)) {//????????????
                            MainUtils.sbzmLocation(IP2, MainActivity.this);  //??????????????????
                        } else {//????????????
                            sb2Clear();
                        }
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
                        MainUtils.Qiehuanzs(titles, IP2);
                    }
                } else if (spBean154.getBand().equals("1") || spBean154.getBand().equals("3") || spBean154.getBand().equals("5") || spBean154.getBand().equals("8")) {//??????FDD
                    SAOPIN2 = 2;
                    if (tf2.equals("FDD")) {
                        if (bts_start2.getText().equals(TITLEZDZM)) {//????????????
                            MainUtils.sbzmLocation(IP2, MainActivity.this);  //??????????????????
                        } else {//????????????
                            sb2Clear();
                        }
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
                        MainUtils.Qiehuanzs(titles, IP2);
                    }
                } else if (MainUtils2.YY(spBean154.getDown()).equals("??????")) {
                    SAOPIN2 = 3;
                    if (tf2.equals("FDD")) {
                        if (bts_start2.getText().equals(TITLEZDZM)) {//????????????
                            MainUtils.sbzmLocation(IP2, MainActivity.this);  //??????????????????
                        } else {//????????????
                            sb2Clear();
                        }
                    } else {

                        String titles = "";
                        if (tf2.equals("TDD")) {
                            titles = "FDD";
                            MainUtils.start1SNF(IP2, Constant.SNFFDD);
                        }
                        if (tf1.equals("FDD")) {
                            titles = "TDD";
                            MainUtils.start1SNF(IP2, Constant.SNFTDD);
                        }
                        MainUtils.Qiehuanzs(titles, IP2);
                    }
                } else if (MainUtils2.YY(spBean154.getDown()).equals("??????")) {
                    SAOPIN2 = 4;
                    if (tf2.equals("FDD")) {
                        if (bts_start2.getText().equals(TITLEZDZM)) {//????????????
                            MainUtils.sbzmLocation(IP2, MainActivity.this);  //??????????????????
                        } else {//????????????
                            sb2Clear();
                        }
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
                        MainUtils.Qiehuanzs(titles, IP2);
                    }
                } else {
//                    ToastUtils.showToast("mmmmm");
                }
            }
        }

        @Override
        public void sucessphoneShow(int sb, String down, SpBean spBean, boolean phonesp, boolean showFlag) {
            if (sb == 1) {
                Intent intent = new Intent(MainActivity.this, SaoPinActivity.class);
                intent.putExtra("type", "1");
//                SPBEANLIST1.add(spBean);
                startActivity(intent);
            } else {
                Intent intent = new Intent(MainActivity.this, SaoPinActivity.class);
                intent.putExtra("type", "2");
//                SPBEANLIST2.add(spBean);
                startActivity(intent);
            }
        }

        @Override
        public void error() {
            ToastUtils.showToast("????????????????????????");
        }
    };
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //????????????
            if (msg.what == 100001) {//????????????
                String s = msg.getData().getString("msgWifi");
                Log.d(TAG, "handleMessa4age: " + s);
                if (s.equals("true")) {//????????????
                    if (wifitrue == 0) {
//                        LoadingFalse();
                        tv1_wifi.setText("WIFI??????: ??????");
                        tv2_wifi.setText("WIFI??????: ??????");
                        Log.d(TAG, "handleMessage: ??????true");
                        wifitrue = 1;
                        wififalse = 0;
                    } else if (wifitrue == 1) {

                    }


                }
                if (s.equals("false")) {//????????????
                    if (wififalse == 0) {
                        tv1_wifi.setText("WIFI??????: ??????");
                        tv2_wifi.setText("WIFI??????: ??????");
//                        LoadingTrue("?????????????????????");
                        Log.d(TAG, "handleMessage: zoulealese");
                        wififalse = 1;
                        wifitrue = 0;
                    }

                }
            }
            if (msg.what == 100110) {//??????1??????
                tf1 = msg.getData().getString("tf1");
                String a = "";
                if (!TextUtils.isEmpty(spinnerS1)) {
                    try {
                        pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(spinnerS1)); //?????????????????????
                        a = pinConfigBeans.get(0).getTf();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "start1sb1zhishi: " + a);
                    if (!TextUtils.isEmpty(tf1)) {
                        if (a.equals(tf1)) {//????????????
                            sb1zhishiFlag = true;
                        } else {//???????????????
                            sb1zhishiFlag = false;
//                                ToastUtils.showToast("???????????????");
                        }
                    } else {

                    }

                }


                if (!TextUtils.isEmpty(tf1)) {
                    tv1_td.setText("????????????: " + tf1);
                    Log.d(TAG, "handleMessage????????????1: " + tf1);
                } else {
                    tv1_td.setText("????????????: ");
                }

            }
            if (msg.what == 99) {//??????1??????
                String s = msg.getData().getString("timer1");
                tv1_td.setText("????????????: " + s + "");
            }

            if (msg.what == 200110) {//??????2??????
                tf2 = msg.getData().getString("tf2");
                String a = "";
                if (!TextUtils.isEmpty(spinnerS2)) {
                    try {
                        pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(spinnerS2)); //?????????????????????
                        a = pinConfigBeans.get(0).getTf();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "start1sb1zhishi: " + a);
                    if (!TextUtils.isEmpty(tf2)) {
                        if (a.equals(tf2)) {//????????????
                            sb2zhishiFlag = true;
                        } else {//???????????????
                            sb2zhishiFlag = false;
//                            ToastUtils.showToast("???????????????");

                        }
                    } else {

                    }

                }
                if (!TextUtils.isEmpty(tf1)) {
                    tv2_tf.setText("????????????: " + tf2);
                    Log.d(TAG, "handleMessage????????????1: " + tf2);
                } else {
                    tv2_tf.setText("????????????: ");
                }
            }
            if (msg.what == 99) {//??????1??????
                String s = msg.getData().getString("timer1");
                tv1_td.setText("????????????: " + s + "");
            }
            if (msg.what == 30000) {//?????????????????????????????????


//                if (bts_start1.getText().equals(TITLEZDZM)) {//????????????
                //????????????????????????
//                    String down = msg.getData().getString("zmdown");
                String imsi = msg.getData().getString("imsi");
                Log.d("Qdown1", "handleMessage: " + "imsi===" + imsi + "----");

//                Log.d("Qdown1", "handleMessage: " + "imsi===" + imsi + "----" + DOWNPIN1);
                try {
                    dbManagerZM = new DBManagerZM(MainActivity.this);
                    ZmBean zmBean = new ZmBean();
                    zmBean.setImsi(imsi);
                    if (msg.getData().getString("sb").equals("1")) {
                        zmBean.setDown(DOWNPIN1);
                        zmBean.setSb(msg.getData().getString("sb"));
                        zmBean.setTime(msg.getData().getString("time"));
                        zmBean.setDatatime(msg.getData().getString("datetime"));
                        int i = dbManagerZM.insertAddZmBean(zmBean);
                    }
                    if (msg.getData().getString("sb").equals("2")) {
                        zmBean.setDown(DOWNPIN2);
                        zmBean.setSb(msg.getData().getString("sb"));
                        zmBean.setTime(msg.getData().getString("time"));
                        zmBean.setDatatime(msg.getData().getString("datetime"));
                        int i = dbManagerZM.insertAddZmBean(zmBean);
                    }

                    //?????????????????????imsi??????
                    List<ZmBean> zmBeans = dbManagerZM.getDataAll();
                    List<Integer> listsize = new ArrayList<>();
                    for (int i = 0; i < zmBeans.size(); i++) {
                        listsize.add(i + 1);

                    }
                    Log.d("dbManagerZM", "handleMessage: " + i);
                    Log.d("dbManagerZM", "handleMessage:size " + zmBeans.toString());
                    ryZmAdapter = new RyZmAdapter(Basecontext, zmBeans, listsize);//list???imsi?????????????????????
                    if (et_zhenmasearch.getText().length() == 0) {


//                        ry_zhenma.setAdapter(ryZmAdapter);
                        if (zmBeans.size() > 6) {
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                            linearLayoutManager.setStackFromEnd(true);//recyview?????????????????????
                            ry_zhenma.setLayoutManager(linearLayoutManager);
                            tv_searchNum.setText("(" + zmBeans.size() + ")");
                        } else {
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                            linearLayoutManager.setStackFromEnd(false);//recyview?????????????????????
                            ry_zhenma.setLayoutManager(linearLayoutManager);
                            tv_searchNum.setText("(" + zmBeans.size() + ")");
                        }
                        ryZmAdapter = new RyZmAdapter(Basecontext, zmBeans, listsize);//list???imsi?????????????????????
                        ry_zhenma.setAdapter(ryZmAdapter);
                    }

//                        ry_zhenma.scrollToPosition(zmBeans.size());
                    if (msg.getData().getString("zb").equals("1")) {
                        //???????????????????????????
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
//
//                } else {
                sbImsiType(msg);
//                }

            }

            if (msg.what == 300001) {
                //??????
                List<States> listremoveDuplicate = removeDuplicate(listStates);
                Log.d(TAG, "handleMessagesslistStates: " + listremoveDuplicate.size() + listremoveDuplicate.toString());
                for (int i = 0; i < pararBeansList1.size(); i++) {
                    pararBeansList1.get(i).setSb("");
//                    pararBeansList1.get(i).setZb("");
                }
                for (int i = 0; i < pararBeansList1.size(); i++) {
                    for (int j = 0; j < listremoveDuplicate.size(); j++) {
                        if (listremoveDuplicate.get(j).getImsi().equals(pararBeansList1.get(i).getImsi())) {
                            pararBeansList1.get(i).setSb(listremoveDuplicate.get(j).getSb());
                            pararBeansList1.get(i).setZb(listremoveDuplicate.get(j).getZb());
                        }
                    }
                }
                List<Integer> size = new ArrayList<>();
                for (int i = 1; i < pararBeansList1.size() + 1; i++) {
                    size.add(i);
                }
                final List<String> listFirstIMSI1 = new ArrayList<>();
                final List<String> listFirstIMSI2 = new ArrayList<>();
                if (pararBeansList1.size() > 0) {
                    for (int i = 0; i < pararBeansList1.size(); i++) {
                        if (pararBeansList1.get(i).getSb().equals("1")) {
                            listFirstIMSI1.add(pararBeansList1.get(i).getImsi());
                        }
                        if (pararBeansList1.get(i).getSb().equals("2")) {
                            listFirstIMSI2.add(pararBeansList1.get(i).getImsi());
                        }
                    }
                }
                if (listFirstIMSI1.size() > 0) {
                    if (sb1FirstFlag == true) {
                        if (sb1.equals("?????????")) {
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
//                                        Log.e("nzq", "run: nzqsend");
//                                        Log.d("nzqsendimsi", "run: " + sendListBlack.get(0).getImsi());
//                                        String location = location(sendListBlack.get(0).getImsi(), "04", sa);
//                                        Log.d(TAG, "run: " + location);
                                    byte[] outputData = MainUtilsThread.hexStringToByteArray(location(listFirstIMSI1.get(0), "04", sa, MainActivity.this, 1));
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
                                    Log.e("nzqsendstart1", "run: sendsocket?????????" + outputPacket.getPort() + "Ip??????:" + outputPacket.getAddress().toString() + "??????:" + outputPacket.getData());
                                    try {
                                        sb1locationgFlag = true;
                                        //                                    socket.send(outputPacket);
                                        DS.send(outputPacket);
//
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    listFirstIMSI1.clear();
                                    LocationFlag1 = true;
                                    sb1FirstFlag = false;
                                    sb1locationgFlag = true;
                                    long l = System.currentTimeMillis();
//                                        while (LocationFlag1) {
//                                            long l1 = System.currentTimeMillis();
//                                            if (LocationFlags1 == true) {
//                                                if (l1 - l > 8000) {
//                                                    LocationFlag1 = false;
//                                                    Message message = new Message();
//                                                    bundle.putString("test", "");
//                                                    message.setData(bundle);
//                                                    handler.sendMessage(message);
//                                                    message.what = 100137;
//                                                } else if (LocationFlags1 == false) {
//                                                    LocationFlag1 = false;
//                                                }
//                                            }
//                                        }
                                }
                            }).start();
                        }

                    }

                }
                if (listFirstIMSI2.size() > 0) {
                    if (sb2FirstFlag == true) {
                        if (sb1.equals("?????????")) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    String sa = "";
                                    if (tf2.equals("TDD")) {
                                        sa = "01";
                                    }
                                    if (tf2.equals("FDD")) {
                                        sa = "00";
                                    }
                                    DatagramSocket socket = null;
                                    InetAddress address = null;//????????????ip??????
//                                        Log.e("nzq", "run: nzqsend");
//                                        Log.d("nzqsendimsi", "run: " + sendListBlack.get(0).getImsi());
//                                        String location = location(sendListBlack.get(0).getImsi(), "04", sa);
//                                        Log.d(TAG, "run: " + location);
                                    byte[] outputData = MainUtilsThread.hexStringToByteArray(location(listFirstIMSI2.get(0), "04", sa, MainActivity.this, 2));
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
                                    Log.e("nzqsendstart1", "run: sendsocket?????????" + outputPacket.getPort() + "Ip??????:" + outputPacket.getAddress().toString() + "??????:" + outputPacket.getData());
                                    try {
                                        sb2locationgFlag = true;
                                        //                                    socket.send(outputPacket);
                                        DS.send(outputPacket);
//
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    listFirstIMSI2.clear();
                                    sb2FirstFlag = false;
                                    LocationFlag2 = true;
                                    sb1locationgFlag = true;
                                    long l = System.currentTimeMillis();
//                                        while (LocationFlag1) {
//                                            long l1 = System.currentTimeMillis();
//                                            if (LocationFlags1 == true) {
//                                                if (l1 - l > 8000) {
//                                                    LocationFlag1 = false;
//                                                    Message message = new Message();
//                                                    bundle.putString("test", "");
//                                                    message.setData(bundle);
//                                                    handler.sendMessage(message);
//                                                    message.what = 100137;
//                                                } else if (LocationFlags1 == false) {
//                                                    LocationFlag1 = false;
//                                                }
//                                            }
//                                        }
                                }
                            }).start();
                        }

                    }

                }
                List<AddPararBean> pararBeansListA = new ArrayList<>();
                Log.d(TAG, "handleMessage: " + pararBeansList1);
//                for (int i = 0; i < pararBeansList1.size(); i++) {
//                    if (pararBeansList1.get(i).getSb() != null) {
//                        if (pararBeansList1.get(i).getSb().equals("1")) {
//                            pararBeansListA.add(pararBeansList1.get(i));
//                        }
//                    }
//                }
//                for (int i = 0; i < pararBeansList1.size(); i++) {
//                    if (pararBeansList1.get(i).getSb() != null) {
//                        if (pararBeansList1.get(i).getSb().equals("2")) {
//                            pararBeansListA.add(pararBeansList1.get(i));
//                        }
//                    }
//                }
//                for (int i = 0; i < pararBeansList1.size(); i++) {
//                    if (pararBeansList1.get(i).getSb() == null) {
////                        if (pararBeansList1.get(i).getSb().equals("1")) {
//                            pararBeansListA.add(pararBeansList1.get(i));
////                        }
//                    }
//                }

                ryImsiAdapter = new RyImsiAdapter(Basecontext, pararBeansList1, size, config, tv_imsi1, tv_imsi2);//list???imsi?????????????????????
                ryIMSI.setAdapter(ryImsiAdapter);
                listStates.clear();

            }
            if (msg.what == 300002) {
                if (TextUtils.isEmpty(imsi1)) {
                    tv_imsi1.setText("");
                    sb1_jl.setText("");
                    sb1_jl_pj1.setText("");
                }
                if (TextUtils.isEmpty(imsi2)) {
                    tv_imsi2.setText("");
                    sb1_j2.setText("");
                    sb1_jl_pj2.setText("");
                }
            }
            switch (msg.what) {
                //????????????
                case 100: //??????????????????
                    Log.d(TAG, "handleMessage: LoadingFalse");
//
//                    Set1StatusBar("??????????????????");
                    break;
                case 8121://?????????????????? ????????????????????????1
                    //???????????????????????????
                    Log.d(TAG, "handleMessage120: ");

                    String callblackflag1 = msg.getData().getString("CALLBLACKFLAG1");//?????????1
                    if (CALLBLACKFLAG1 == true) {
                        if (callblackflag1.equals("1")) {
//                            CALLBLACKFLAG1 = false;
                            Set1StatusBar("????????????????????????");
                            if (blackNUM1 <= 4) {
                                Set1StatusBar("???????????????????????????");
                                sb1Clear();
                                BLACKTIME1 = System.currentTimeMillis();
                                CALLBLACKFLAG1 = false;
                                blackNUM1++;
                            } else {
                                Set1StatusBar("???????????????????????????????????????");
                            }

                        }
                    }
                    break;

                case 8122://?????????????????? ????????????????????????2
                    //???????????????????????????
                    Log.d(TAG, "handleMessage120: ");

                    String callblackflag2 = msg.getData().getString("CALLBLACKFLAG2");//?????????1
                    if (CALLBLACKFLAG2 == true) {
                        if (callblackflag2.equals("1")) {
                            CALLBLACKFLAG2 = false;
                            Set2StatusBar("????????????????????????");
                            if (blackNUM2 <= 4) {
                                Set2StatusBar("???????????????????????????");
                                sb2Clear();
                                BLACKTIME2 = System.currentTimeMillis();
                                CALLBLACKFLAG2 = false;
                            } else {
                                Set2StatusBar("???????????????????????????????????????");
                            }

                        }
                    }
                    break;
                case 8131://?????????????????? ????????????????????????1
                    //???????????????????????????
                    Log.d(TAG, "handleMessage120: ");

                    String callblackflagset1 = msg.getData().getString("CALLBLACKFLAGSET1");//?????????1
                    if (CALLBLACKFLAGSET1 == true) {
                        if (callblackflagset1.equals("1")) {
                            CALLBLACKFLAGSET1 = false;
                            Set1StatusBar("????????????????????????");
                        }
                    }
                    break;

                case 8132://?????????????????? ????????????????????????2
                    //???????????????????????????
                    Log.d(TAG, "handleMessage120: ");

                    String callblackflagset2 = msg.getData().getString("CALLBLACKFLAGSET2");//?????????1
                    if (CALLBLACKFLAGSET2 == true) {
                        if (callblackflagset2.equals("1")) {
                            CALLBLACKFLAGSET2 = false;
                            Set2StatusBar("????????????????????????");
                        }
                    }
                    break;

                case 8141://?????????????????? ????????????????????????2
                    //???????????????????????????
                    Log.d(TAG, "handleMessage120: ");

                    String callblackopen1 = msg.getData().getString("CALLBLACKOPEN1");//?????????1
                    if (CALLBLACKOPEN1 == true) {
                        if (callblackopen1.equals("1")) {
                            CALLBLACKOPEN1 = false;
                            Set1StatusBar("?????????????????????");
                        }
                    }
                    break;
                case 8142://?????????????????? ????????????????????????2
                    //???????????????????????????
                    Log.d(TAG, "handleMessage120: ");

                    String callblackopen2 = msg.getData().getString("CALLBLACKOPEN2");//?????????1
                    if (CALLBLACKOPEN2 == true) {
                        if (callblackopen2.equals("1")) {
                            CALLBLACKOPEN2 = false;
                            Set2StatusBar("?????????????????????");
                        }
                    }
                    break;
                case 8151://?????????????????? ??????????????????2
                    //???????????????????????????
                    Log.d(TAG, "handleMessage120: ");

                    String callblacklocation1 = msg.getData().getString("CALLBLACKLOCATION1");//?????????1
                    if (CALLBLACKLOCATION1 == true) {
                        if (callblacklocation1.equals("1")) {
                            CALLBLACKLOCATION1 = false;
                            Set1StatusBar("???????????????????????????");
                        }
                    }
                    break;

                case 8152://?????????????????? ??????????????????2
                    //???????????????????????????
                    Log.d(TAG, "handleMessage120: ");

                    String callblacklocation2 = msg.getData().getString("CALLBLACKLOCATION2");//?????????1
                    if (CALLBLACKLOCATION2 == true) {
                        if (callblacklocation2.equals("1")) {
                            CALLBLACKLOCATION2 = false;
                            Set2StatusBar("???????????????????????????");
                        }
                    }
                    break;

                case 8153://?????????????????????????????????53 ??????????????????
//                tv_wendu.setText(BOARDTEMPERATURE2+"??C");
                    DecimalFormat df2;
                    if (!TextUtils.isEmpty(BOARDTEMPERATURE1)) {

                        Double i = Double.parseDouble(BOARDTEMPERATURE1);
                        df2 = new DecimalFormat("####");
                        if (i >= 60) {
                            iv_wendu.setBackground(getResources().getDrawable(R.mipmap.wendu_hong));
                            tv_wendu.setTextColor(getResources().getColor(R.color.redTextwendu));
                            tv_wendu.setText(df2.format(i) + "??C");
                            if (!FengShanFlag) {
                                FengShanFlag = true;
                                off_on(1);
                                iv_fengshan.setBackground(getResources().getDrawable(R.mipmap.fengshan));
                            }
                        } else {
                            iv_wendu.setBackground(getResources().getDrawable(R.mipmap.wendu));
                            tv_wendu.setTextColor(getResources().getColor(R.color.white));
                            tv_wendu.setText(df2.format(i) + "??C");
                        }

                    }
                    break;


                case 8154:
                    String callblacklocation21 = msg.getData().getString("SpinnersSetData");
                    if (callblacklocation21.equals("1")) {
                        SpinnersSetData(1);
                    } else {
                        SpinnersSetData(2);
                    }
                    break;
//                ??????1

                case 100120: //??????1?????????????????????
                    String zt1 = msg.getData().getString("zt1");
                    if (!TextUtils.isEmpty(zt1)) {
                        if (zt1.equals("0")) {
                            tv1_type.setText("????????????: " + "?????????...");
                            tv1_td.setText("????????????:");
                            sb1 = "?????????...";
                            Set1StatusBar("?????????...");
                            mysp1.setEnabled(true);
                            break;
                        }
                        if (zt1.equals("1")) {
                            tv1_type.setText("????????????: " + "????????????");
                            tv1_td.setText("????????????:");
                            sb1 = "????????????";
                            Set1StatusBar("????????????");
                            mysp1.setEnabled(true);
                            break;
                        }
                        if (zt1.equals("2")) {
                            tv1_type.setText("????????????: " + "??????");//????????????
                            //
//                            ??????1??????
                            sb1 = "??????";
//                            Set1StatusBar("??????");
//                            if (itemtype1.equals())
                            mysp1.setEnabled(true);
                            tv_imsi1.setText("");
//                            DOWNPIN1="";//????????????IMSI ?????????????????????
                            if (itemtype1.equals(TITLEZD)) {//?????????????????????
                                mysp1.setVisibility(View.GONE);
                                tv_r1.setText("????????????");

                            } else {
                                mysp1.setVisibility(View.VISIBLE);
                                tv_r1.setText("????????????");
                            }

                            //????????????1???????????????????????????????????????
                            if (sb1zy1 == false) {
//                                zy(1);//??????????????????
//                                ToastUtils.showToast(sb1pdStr);
                                DBManagerZY dbManagerZY = null;
                                try {
                                    dbManagerZY = new DBManagerZY(MainActivity.this);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                cbzt1_g.setChecked(true);
                                if (tf1.equals("TDD")) {
                                    int data = 0;
                                    try {
                                        data = dbManagerZY.foriddata(1, 1, 3);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    setzy(data, 1);
                                    sb1zy1 = true;
                                }
                                if (tf1.equals("FDD")) {
                                    int data = 0;
                                    try {
                                        data = dbManagerZY.foriddata(1, 2, 3);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    setzy(data, 1);
                                    sb1zy1 = true;
                                }
                            }
//                            ToastUtils.showToast(sb1pdStr);
                            if (timerRestartFlag1 == true) {
                                rRestart(1001388);
                                timerRestartFlag1 = false;
                            }
                            break;
                        }
                        if (zt1.equals("3")) {
//                            tv1_type.setText("????????????: " + "?????????????????????");//????????????
                            sb1 = "?????????????????????";
                            Set1StatusBar("?????????????????????");
                            mysp1.setEnabled(true);
                            break;
                        }
                        if (zt1.equals("4")) {
//                            tv1_type.setText("????????????: " + "???????????????");//????????????
                            sb1 = "???????????????";
                            Set1StatusBar("???????????????");
                            mysp1.setEnabled(true);
                            break;
                        }
                        if (zt1.equals("5")) {

                            if (bts_start1.getText().equals(TITLEZDZM)) {//????????????
                                Set1StatusBar("?????????");
                                tv1_type.setText("????????????: " + "?????????");//????????????
                                sb1 = "?????????";
                                mysp1.setEnabled(false);
                                sb1types = "?????????";
                                CALLBLACKOPEN1 = false;
                                DOWN12 = DOWNPIN1;
                                if (DOWN12.equals(DOWN11)) {

                                } else {
                                    DOWN11 = DOWN12;
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    DBManagerLog dbManagerLog = null;
                                    //????????????
                                    try {
                                        dbManagerLog = new DBManagerLog(MainActivity.this);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    LogBean logBean = new LogBean();
                                    logBean.setAssociated(LoginUtils.getId(MainActivity.this) + "");//??????ID
                                    logBean.setEvent(LoginUtils.setBase64("??????"));//????????????
                                    logBean.setSb(LoginUtils.setBase64("1"));
                                    logBean.setPd(LoginUtils.setBase64(DOWN11));
                                    String format = sdf.format(new Date());//????????????
                                    logBean.setDatetime(LoginUtils.setBase64(format));
                                    try {
                                        dbManagerLog.insertConmmunit01Bean(logBean);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }


                            } else if (bts_start1.getText().equals(TITLESDZM)) {
                                Set1StatusBar("?????????");
                                tv1_type.setText("????????????: " + "?????????");//????????????
                                sb1 = "?????????";
                                mysp1.setEnabled(false);
                                sb1types = "?????????";
                                CALLBLACKOPEN1 = false;

                                DOWN12 = DOWNPIN1;
                                if (DOWN12.equals(DOWN11)) {

                                } else {
                                    DOWN11 = DOWN12;
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    DBManagerLog dbManagerLog = null;
                                    //????????????
                                    try {
                                        dbManagerLog = new DBManagerLog(MainActivity.this);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    LogBean logBean = new LogBean();
                                    logBean.setAssociated(LoginUtils.getId(MainActivity.this) + "");//??????ID
                                    logBean.setEvent(LoginUtils.setBase64("??????"));//????????????
                                    logBean.setSb(LoginUtils.setBase64("1"));
                                    logBean.setPd(LoginUtils.setBase64(DOWN11));
                                    String format = sdf.format(new Date());//????????????
                                    logBean.setDatetime(LoginUtils.setBase64(format));
                                    try {
                                        dbManagerLog.insertConmmunit01Bean(logBean);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else {
                                Set1StatusBar("?????????");
                                tv1_type.setText("????????????: " + "?????????");//????????????
                                sb1 = "?????????";
                                Set1StatusBar("?????????");
                                mysp1.setEnabled(false);
                                sb1types = "?????????";
                                sb1type = "?????????";
                                CALLBLACKOPEN1 = false;
                                if (sb1zy1 == false) {
                                    zy(1);//??????????????????
//                                ToastUtils.showToast(sb1pdStr);
                                }
                            }


                            break;
                        }
                    }
                case 1001200:
                    String test = msg.getData().getString("test");
                    if (!TextUtils.isEmpty(test)) {
                        if (test.equals("1")) {
                            Set1StatusBar("??????????????????");
                            break;
                        }
                        if (test.equals("2")) {
                            Set1StatusBar("??????????????????");
                            break;
                        }
                        if (test.equals("3")) {
                            Set1StatusBar("GPS????????????");
                            break;
                        }
                        if (test.equals("4")) {
                            Set1StatusBar("GPS????????????");
                            break;
                        }
                        if (test.equals("5")) {
                            Set1StatusBar("????????????");
                            break;
                        }
                        if (test.equals("6")) {
                            Set1StatusBar("????????????");
                            break;
                        }
                        if (test.equals("7")) {
                            Set1StatusBar("??????????????????");
                            break;
                        }
                        if (test.equals("8")) {
                            Set1StatusBar("??????????????????");
                            break;
                        }
                        if (test.equals("9")) {
                            Set1StatusBar("???????????????");
                            if (itemtype1.equals(TITLEZD)) {//?????????????????????
                                mysp1.setVisibility(View.GONE);
                                tv_r1.setText("????????????");

                            } else {
                                mysp1.setVisibility(View.VISIBLE);
                                tv_r1.setText("????????????");
                            }
                            break;
                        }
                        if (test.equals("10")) {
                            Set1StatusBar("???????????????");
                            break;
                        }
                        if (test.equals("11")) {
                            Set1StatusBar("GPS?????????");
                            break;
                        }
                        if (test.equals("12")) {
                            Set1StatusBar("?????????");
                            break;
                        }
                        if (test.equals("13")) {
                            Set1StatusBar("???????????????");
                            sb1type = "???????????????";
                            break;
                        }
                        if (test.equals("14")) {
                            Set1StatusBar("??????????????????");
                            sb1type = "??????????????????";
                            break;
                        }
                    }

                case 100130://????????????????????????????????????
//                    ToastUtils.showToast("????????????");//???????????????-?????????????????????

                    if (itemtype1.equals(TITLEZD)) {//?????????????????????

                        ClearFlag1 = false;
                        ClearFlags1 = false;

                        Set1StatusBar("?????????????????????");
                        sb1type = "?????????????????????";
                        CALLBLACKFLAG1 = false;
                        BLACKTIMESET1 = System.currentTimeMillis();
                        CALLBLACKFLAGSET1 = true;
                        sendBlackListsp();
                    }
                    if (itemtype1.equals(TITLESD)) {//?????????????????????
                        Log.d(TAG, "handleMessage2????????????: ");
                        ClearFlag1 = false;
                        ClearFlags1 = false;

                        Set1StatusBar("?????????????????????");
                        sb1type = "?????????????????????";
                        CALLBLACKFLAG1 = false;
                        BLACKTIMESET1 = System.currentTimeMillis();
                        CALLBLACKFLAGSET1 = true;
                        sendBlackList();
                    }
                    break;
                case 100132:
                    //??????????????????
//                    ToastUtils.showToast("??????????????????");

                    Log.d(TAG, "handleMessage:?????????????????? ");
//                    Set1StatusBar("??????????????????");
                    break;
                case 100131://?????????????????????????????????
//
                    if (itemtype1.equals(TITLEZD)) {
//                        ToastUtils.showToast("???????????????????????????");
                        sb1type = "???????????????????????????";
                        Log.d(TAG, "handleMessage2???????????????????????????: ");
//                    ????????????????????????-????????? ????????????
                        BlackFlags1 = false;
                        sb1LocationsSP();//??????1????????????
                        Set1StatusBar("???????????????????????????");
                        CALLBLACKFLAGSET1 = false;
                        BLACKLOCATION1 = System.currentTimeMillis();
                        CALLBLACKLOCATION1 = true;
                        CALLBLACKFLAG1 = false;
                    }//?????????????????????

                    if (itemtype1.equals(TITLESD)) {//??????
//                        ToastUtils.showToast("???????????????????????????");
                        Log.d(TAG, "handleMessage2???????????????????????????: ");
//                    ????????????????????????-????????? ????????????
                        BlackFlags1 = false;
                        sb1type = "???????????????????????????";
                        sb1Locations();//??????1????????????
                        Set1StatusBar("???????????????????????????");
                        CALLBLACKFLAGSET1 = false;
                        BLACKLOCATION1 = System.currentTimeMillis();
                        CALLBLACKLOCATION1 = true;
                        CALLBLACKFLAG1 = false;
                    }//
                    break;
                case 100133:
//                    ToastUtils.showToast("???????????????");
                    Set1StatusBar("???????????????");
                    BlackFlag1 = true;
                    break;
                case 100134:
//                    ToastUtils.showToast("????????????");
                    Set1StatusBar("?????????????????????");
                    sb1type = "?????????????????????";
                    BlackFlag1 = true;
                    break;
                case 100135:
//                    ToastUtils.showToast("???????????????");
                    Set1StatusBar("?????????????????????");
                    sb1type = "?????????????????????";
                    BlackFlag1 = true;
                    break;
                case 100136: //????????????????????????

                    if (bts_start1.getText().equals(TITLEZDZM)) {//????????????
                        Set1StatusBar("????????????????????????");
                    } else if (bts_start1.getText().equals(TITLESDZM)) {
                        Set1StatusBar("????????????????????????");
                    } else {
                        Set1StatusBar("????????????????????????");
                    }

                    sb1type = "????????????????????????";
                    CALLBLACKFLAG1 = false;
                    CALLBLACKFLAGSET1 = false;
                    CALLBLACKOPEN1 = false;
                    CALLBLACKLOCATION1 = false;
//                    if (sb1locationgFlag == true) {
//                        sb1locationgFlag = false;
//                    } else if (sb1locationgFlag == false) {
                    GFFLAG1 = 1;
//                    MainUtils.OpenGF2(1, 1, handler);// sb1: ???????????? 1??????53,2 ??????54 .
                    BLACKOPENSET1 = System.currentTimeMillis();
                    CALLBLACKOPEN1 = true;
//                    }
                    // switchs :????????????, 1????????? 2?????????.
                    LocationFlag2 = false;
//                    if (sb1zhishiFlag == true) {
//                        Log.d(TAG, "handleMessage:sb1zhishiFlag " + true);
//                    } else {
//                        //????????????
//                        MainUtils.Restart();
//                        Set1StatusBar("??????????????????");
//                        Log.d(TAG, "handleMessage:sb1zhishiFlag " + false);
//                    }


                    CALLBLACKOPEN1 = false;
                    if (GFFLAG1 == 1) {
                        //????????????
                        if (itemtype1.equals(TITLEZD)) {//?????????????????????
                            if (sb1.equals("?????????")) {
                                break;
                            } else {
                                EstablishVillageSS(sp1MAX1value);
//                                Set1StatusBar("??????????????????");
                                CALLBLACKOPEN1 = false;
                            }

                        }
                        if (itemtype1.equals(TITLESD)) {//?????????????????????
                            Log.d("nzqgf1", "handleMessage: ");
                            if (!sb1.equals("?????????")) {
                                EstablishVillage();
//                                Set1StatusBar("??????????????????");
                                CALLBLACKOPEN1 = false;
                            }

                        }

                        if (bts_start1.getText().equals(TITLEZDZM)) {//????????????
                            EstablishVillageSS(sp1MAX1value);
                            break;
                        }
                    }
                    if (GFFLAG1 == 3) {  // ??????3??????TDD????????????
                        if (itemtype1.equals(TITLEZD)) {//?????????????????????
//                            Set1StatusBar("??????????????????");
                            CALLBLACKOPEN1 = false;
                            sb1zhishiFlag = true;
                            try {
                                saopinBeanList = dbManagersaopin.getStudent(); //?????????????????????
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("??????????????????");
                                    saopinSend1(saopinBeanList, tf1, SAOPIN);
//                                            MainUtils.start1SNF(IP1);
//                                            ToastUtils.showToast("snf?????????");
                                    GFFLAG1 = 1;
                                } else {
                                    ToastUtils.showToast("??????????????????");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else {
//                            Set1StatusBar("??????????????????");
                            CALLBLACKOPEN1 = false;
                            sb1zhishiFlag = true;
                            try {
                                saopinBeanList = dbManagersaopin.getStudent(); //?????????????????????
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("??????????????????");
                                    saopinSend1(saopinBeanList, tf1, SAOPIN);
//                                            MainUtils.start1SNF(IP1);
//                                            ToastUtils.showToast("snf?????????");
                                    GFFLAG1 = 1;
                                } else {
                                    ToastUtils.showToast("??????????????????");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }


                    break;
                case 100137: //????????????????????????
//                    ToastUtils.showToast("??????????????????");
                    Log.d(TAG, "handleMessagea: " + "??????????????????");
//                    LocationFlag1 = false;
                    Set1StatusBar("??????????????????");
                    sb1type = "??????????????????";
                    break;

                case 100138: //??????????????????
//                    ToastUtils.showToast("??????????????????");
                    Log.d(TAG, "handleMessagea: " + "??????????????????");
                    Set1StatusBar("?????????...");
//                    if (itemtype1.equals(TITLEZD)) {//?????????????????????
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("??????????????????", "run:788888888888");
//                                saopinSend1(saopinBeanList, tf1, SAOPIN);
                            timerRestartFlag1 = true;
                        }
                    }, 30000);
//                    }


                    break;
                case 1001388: //??????????????????
//                    ToastUtils.showToast("??????????????????");
                    Log.d(TAG, "handleMessagea: " + "??????????????????");
//                    Set1StatusBar("?????????...");
                    if (bts_start1.getText().equals("????????????")) {
                        MainUtils.sbzmLocation(IP1, MainActivity.this);
                        return;
                    }
                    if (bts_start1.getText().equals("????????????")) {

                        return;
                    }
                    if (itemtype1.equals(TITLEZD)) {//?????????????????????
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("??????????????????", "run:788888888888");
                                if (phone1sp) {
                                    sb1Clear();
                                } else {
                                    saopinSend1(saopinBeanList, tf1, SAOPIN);

                                }

//                            }
//                        }, 180000);//??? 180????????????
                            }
                        }, 1000);
                    }
                    if (itemtype1.equals(TITLESD)) {//?????????????????????
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Log.d(TAG, "run:788888888888");
//                            sb1Clear();//??????1????????????
                                if ("??????".equals(sb1) || "?????????...".equals(sb1) || "?????????".equals(sb1) || "????????????".equals(sb1) || "?????????????????????".equals(sb1) || "???????????????".equals(sb1)) {
                                    String sb1zhishi = "";
                                    if (!TextUtils.isEmpty(spinnerS1)) {
                                        try {
                                            pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(spinnerS1)); //?????????????????????
                                            sb1zhishi = pinConfigBeans.get(0).getTf();
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                        Log.d(TAG, "start1sb1zhishi: " + sb1zhishi);
//                  String s = tv1_td.getText().toString();
                                        if (!TextUtils.isEmpty(tf1)) {
                                            if (sb1zhishi.equals(tf1)) {//????????????
                                                sb1zhishiFlag = true;
//                                            ToastUtils.showToast("????????????");
                                                sb1Clear();//??????1????????????

//                        sb1Locations();//??????1????????????
                                            } else {//???????????????
                                                sb1zhishiFlag = false;
//                                                ToastUtils.showToast("???????????????");

//                                                new CircleDialog.Builder((FragmentActivity) mContext)
//                                                        .setTitle("")
//                                                        .setText("??????????????????????????????????")
//                                                        .setTitleColor(Color.parseColor("#00acff"))
//                                                        .setNegative("??????", new MyOclck(sb1zhishi, 11))
//                                                        .setPositive("??????", null)
//                                                        .show();

                                                dialog = new Dialog(MainActivity.this, R.style.menuDialogStyleDialogStyle);
                                                inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_item_title, null);
                                                TextView tv_title = inflate.findViewById(R.id.tv_title);
                                                tv_title.setText("??????????????????????????????????");
                                                Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
                                                bt_confirm.setOnClickListener(new MyOclck(sb1zhishi, 11));
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
                                        } else {
                                            ToastUtils.showToast("???????????????1");
                                        }

                                    }
                                } else {
                                    ToastUtils.showToast("???????????????1");
                                }

                            }
//                        }, 180000);//??? 180????????????
                        }, 1000);//
                    }


                    break;
                case 100139: //????????????
//                    ToastUtils.showToast("????????????");
                    Log.d(TAG, "handleMessagea: " + "????????????");
                    Set1StatusBar("????????????");
                    break;


                case 100140:
//                    ToastUtils.showToast("??????????????????");
                    Set1StatusBar("??????????????????");
                    MainUtils.Restart();
                    break;

                case 100141:
//                    ToastUtils.showToast("??????????????????");
                    Set1StatusBar("??????????????????");
                    break;


                case 100142:
                    CALLBLACKOPEN1 = false;
                    if (GFFLAG1 == 1) {
                        //????????????
                        if (itemtype1.equals(TITLEZD)) {//?????????????????????
                            if (sb1.equals("?????????")) {
                                break;
                            } else {
                                EstablishVillageSS(sp1MAX1value);
//                                Set1StatusBar("??????????????????");
                                CALLBLACKOPEN1 = false;
                            }

                        }
                        if (itemtype1.equals(TITLESD)) {//?????????????????????
                            Log.d("nzqgf1", "handleMessage: ");
                            if (!sb1.equals("?????????")) {
                                EstablishVillage();
//                                Set1StatusBar("??????????????????");
                                CALLBLACKOPEN1 = false;
                            }

                        }
                    }
                    if (GFFLAG1 == 3) {  // ??????3??????TDD????????????
                        if (itemtype1.equals(TITLEZD)) {//?????????????????????
//                            Set1StatusBar("??????????????????");
                            CALLBLACKOPEN1 = false;
                            sb1zhishiFlag = true;
                            try {
                                saopinBeanList = dbManagersaopin.getStudent(); //?????????????????????
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("??????????????????");
                                    saopinSend1(saopinBeanList, tf1, SAOPIN);
//                                            MainUtils.start1SNF(IP1);
//                                            ToastUtils.showToast("snf?????????");
                                    GFFLAG1 = 1;
                                } else {
                                    ToastUtils.showToast("??????????????????");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else {
//                            Set1StatusBar("??????????????????");
                            CALLBLACKOPEN1 = false;
                            sb1zhishiFlag = true;
                            try {
                                saopinBeanList = dbManagersaopin.getStudent(); //?????????????????????
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("??????????????????");
                                    saopinSend1(saopinBeanList, tf1, SAOPIN);
//                                            MainUtils.start1SNF(IP1);
//                                            ToastUtils.showToast("snf?????????");
                                    GFFLAG1 = 1;
                                } else {
                                    ToastUtils.showToast("??????????????????");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    break;

                case 100143:
//                    ToastUtils.showToast("??????????????????");
//                    Set1StatusBar("??????????????????");
                    break;

                case 100145:
//                    ToastUtils.showToast("??????????????????");
                    Log.d("nzq", "handleMessage??????????????????: ");
                    sb1type = "??????????????????";
                    if (title.getText().equals(getString(R.string.activity_saopintitle))) {
//                        tv_r1.setText("");
//                        Set1StatusBar("????????????????????????");
                    }

                    if (title.getText().equals(getString(R.string.activity_title))) {
//                        Set1StatusBar("??????????????????????????????");
                        xiaoquFlag1 = false;
                    }

                    break;
                case 100146:
//                    ToastUtils.showToast("??????????????????");
                    Set1StatusBar("??????????????????????????????");
                    xiaoquFlags1 = false;
                    Log.d("nzq", "handleMessage??????????????????: ");
                    break;
                case 100147:  //?????????
                    ii++;
                    String sb1jl = msg.getData().getString("sb1jl");
                    Log.d(TAG, "sb1jlhandleMessage: " + sb1jl + "---");
                    if (!TextUtils.isEmpty(sb1jl)) {
                        String format = "";
                        if (tf1.equals("TDD")) {
                            double v = Double.parseDouble(sb1jl);
                            double s = v / 110 * 1000;
                            format = dfBaoshu.format(s);
                            Log.d(TAG, "dfBaoshuhandleMessage: " + format);
                        } else {
                            double v = Double.parseDouble(sb1jl);
                            double s = v / 164 * 1000;
                            format = dfBaoshu.format(s);
                            Log.d(TAG, "dfBaoshuhandleMessage: " + format);
                        }


                        sb1_jl.setText(format);
                        if (labaFlag1 == true) {
                            startAuto(format, true);
                        }

//
                        if (list1quxian.size() > 0) {
                            list1quxian.remove(0);
                            list1quxian.add(Integer.parseInt(format));
                            double total = 0;
                            for (int i = 0; i < list1quxian.size(); i++) {
                                total += list1quxian.get(i);
                            }
                            double a = total / list1quxian.size();
                            DecimalFormat df = new DecimalFormat("###");
                            sb1_jl_pj1.setText(df.format(a));
                            setqxData(list1quxian, list2quxian);
                        }
                    }
                    break;
                case 100148://??????1??????????????????imsi
                    imsi1 = msg.getData().getString("imsi1");
                    tv_imsi1.setText(imsi1);
                    imsi2 = imsi1;
                    if (imsi11.equals(imsi2)) {

                    } else {
                        imsi11 = imsi2;
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        DBManagerLog dbManagerLog = null;
                        //????????????
                        try {
                            dbManagerLog = new DBManagerLog(MainActivity.this);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        LogBean logBean = new LogBean();
                        logBean.setAssociated(LoginUtils.getId(MainActivity.this) + "");//??????ID
                        logBean.setEvent(LoginUtils.setBase64("??????"));//????????????
                        logBean.setPd(LoginUtils.setBase64(DOWNPIN1));
                        logBean.setSb(LoginUtils.setBase64(1 + ""));
                        logBean.setSucessIMSI(LoginUtils.setBase64(imsi11));
                        String formatD = sdf.format(new Date());//????????????
                        logBean.setDatetime(LoginUtils.setBase64(formatD));
                        try {
                            dbManagerLog.insertConmmunit01Bean(logBean);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                    }


                    imsi1 = "";
                    break;
                case 100149:
                    String zy1 = msg.getData().getString("zy1");
//                    tv_imsi1.setText(zy1);
                    sb1zy1 = true;
                    seekbar1.setProgress(Integer.parseInt(zy1));
                    tv_zy1.setText("???????????????:" + zy1 + "dB");
//                    Set1StatusBar(zy1);
                    break;

                case 100150:
                    String zyset1 = msg.getData().getString("zyset1");
                    if (zyset1.equals("?????????????????????")) {
//                        zy(1);//
//                        ToastUtils.showToast("?????????????????????1");
//                        Set1StatusBar("?????????????????????1");
                    }
                    if (zyset1.equals("?????????????????????")) {
//                        ToastUtils.showToast("?????????????????????1");
//                        Set1StatusBar("?????????????????????1");
                    }
                    break;

                case 100151:
                    String down = msg.getData().getString("down");
                    Log.d(TAG, "151handleMessage: " + down);
                    sb1pdStr = down;

                    if (itemtype1.equals(TITLEZD)) {//?????????????????????
                        if (sb1.equals("?????????")) {
                            mysp1.setVisibility(View.GONE);
                            tv_r1.setText("????????????" + sb1pdStr);
                        }

                    } else {
                        if (sb1.equals("?????????")) {
                            mysp1.setVisibility(View.GONE);
                            tv_r1.setText("????????????" + sb1pdStr);
                        }
                    }
                    break;
//                case 100152:
//
//                    String dwon1sa = msg.getData().getString("sp1MAX1value");//???????????????
//                    Log.d("nzqh1", "handleMessage: "+dwon1sa);
//                    ToastUtils.showToast(""+dwon1sa);
//                    break;
//                case 1001523333:
                case 100152:
                    Log.d("nzqh1", "handleMessage: ");
                    if (itemtype1.equals(TITLEZD)) {//?????????????????????
                        if (sb1.equals("?????????")) {
                            break;
                        }

                        String dwon1s = msg.getData().getString("sp1MAX1value");//???????????????
                        Log.d(TAG, "dwon1shan: " + dwon1s);
//                        ArrayList<SpBean> list = msg.getData().getParcelableArrayList("List");
//                        Log.d("listHand", "handleMessage: " + list);

                        String sp1up = msg.getData().getString("sp1up");
                        String sp1pci = msg.getData().getString("sp1pci");
                        String sp1plmn = msg.getData().getString("sp1plmn");
                        String sp1band = msg.getData().getString("sp1band");
                        String sp1tac = msg.getData().getString("sp1tac");

                        sp1MAX2value = msg.getData().getString("sp1MAX2value");//???????????????
                        if (!TextUtils.isEmpty(sp1MAX2value)) {
                            String sp2up = msg.getData().getString("sp2up");
                            String sp2pci = msg.getData().getString("sp2pci");
                            String sp2plmn = msg.getData().getString("sp2plmn");
                            String sp2band = msg.getData().getString("sp2band");
                            String sp2tac = msg.getData().getString("sp2tac");
                            spBean2.setUp(sp2up);
                            if (!TextUtils.isEmpty(sp2pci)) {
                                spBean2.setPci(Integer.parseInt(sp2pci));
                            } else {
                                spBean2.setPci(0);
                            }
                            if (!TextUtils.isEmpty(sp2tac)) {
                                spBean2.setTac(Integer.parseInt(sp2tac));
                            } else {
                                spBean2.setTac(0);
                            }
//                            spBean2.setTac(Integer.parseInt(sp2tac));
                            spBean2.setPlmn(sp2plmn);
                            spBean2.setDown(sp1MAX2value);
                            spBean2.setBand(sp2band);
                        }
                        Log.d(TAG, "100152handleMessage: " + dwon1s + "sp1MAX2value" + sp1MAX2value);
                        if (dwon1s.equals(sp1MAX1value)) {

                            break;
                        } else {
                            sp1MAX1value = dwon1s;

                        }
                        if (sb2.equals("?????????")) {//????????????2 ????????????
                            if (tf1.equals(tf2)) {
                                if (sp1MAX1value.equals(sp2MAX1value)) {
//                                    Set1StatusBar("????????????100152 ??????1???????????????2????????????");
                                    if (!TextUtils.isEmpty(sp1MAX2value)) {
                                        Set1StatusBar("sp1MAX2value?????????");
                                        spBean1 = spBean2;
//                                        ToastUtils.showToast("??????1??????" + "" + sp1MAX1value);
//
                                        if (sb1locationgFlag1 == false) {
                                            if (!sp1MAX2value.equals("0") && !TextUtils.isEmpty(sp1MAX2value)) {

//                                                sb1Clear();//??????1????????????


                                                if (bts_start1.getText().equals(TITLEZDZM)) {//????????????
                                                    MainUtils.sbzmLocation(IP1, MainActivity.this);
                                                } else {
                                                    sb1Clear();//??????1????????????
                                                }
                                                Log.d("100152spBean1aaa", "100152handleMessage: " + spBean1);
//                                aaaaaa
//                                                tv_r1.setText("????????????" + sp1MAX2value + "");
                                                Set1StatusBar("????????????" + sp1MAX2value);

                                            } else {
//                                                ToastUtils.showToast("?????????0?????????");
                                                Set1StatusBar("?????????????????????");
                                            }

                                        } else {
                                            ToastUtils.showToast("??????????????????");
                                        }
//
                                    } else {
                                        ToastUtils.showToast("?????????");
//                                        Set1StatusBar("sp1MAX2value???????????????");
                                        Set1StatusBar("?????????????????????");
                                    }
                                } else {
//                                    Set1StatusBar("sp1MAX1value???sp2MAX1value??????");
                                    if (!TextUtils.isEmpty(sp1MAX1value)) {
                                        Set1StatusBar("sp1MAX1value?????????");
//                        spBean1.setUp(sp1up);
                                        SpBean spBean = new SpBean();
                                        spBean.setUp(sp1up);
                                        spBean.setDown(sp1MAX1value);
                                        spBean.setBand(sp1band);
                                        spBean.setPlmn(sp1plmn);
                                        spBean.setTac(Integer.parseInt(sp1tac));
                                        spBean.setPci(Integer.parseInt(sp1pci));
                                        spBean1 = spBean;


//                                        ToastUtils.showToast("??????333" + "" + sp1MAX1value);
//
                                        if (sb1locationgFlag1 == false) {
                                            if (!sp1MAX1value.equals("0") && !TextUtils.isEmpty(sp1MAX1value)) {
                                                if (!sp1MAX1value.equals(sp2MAX1value)) {
//                                                    sb1Clear();//??????1????????????

                                                    if (bts_start1.getText().equals(TITLEZDZM)) {//????????????
                                                        MainUtils.sbzmLocation(IP1, MainActivity.this);
                                                    } else {
                                                        sb1Clear();//??????1????????????
                                                    }

                                                    Log.d("spBean1aaa", "handleMessage: " + spBean1);
//                                aaaaaa
//                                                    tv_r1.setText("????????????" + sp1MAX1value + "");
                                                    Set1StatusBar("????????????" + sp1MAX1value);
                                                } else {

                                                    Set1StatusBar("?????????????????????");
                                                }

                                            } else {
//                                                ToastUtils.showToast("?????????0?????????");
                                                Set1StatusBar("?????????????????????");
                                            }

                                        } else {
                                            ToastUtils.showToast("??????????????????");
                                        }
//
                                    } else {
                                        ToastUtils.showToast("?????????");
                                        Set1StatusBar("?????????????????????");
                                    }
                                }
                            } else {
                                if (!TextUtils.isEmpty(sp1MAX1value)) {
//                                    Set1StatusBar("????????????????????????sp1MAX1value");
//                              spBean1.setUp(sp1up);
                                    SpBean spBean = new SpBean();
                                    spBean.setUp(sp1up);
                                    spBean.setDown(sp1MAX1value);
                                    spBean.setBand(sp1band);
                                    spBean.setPlmn(sp1plmn);
                                    spBean.setTac(Integer.parseInt(sp1tac));
                                    spBean.setPci(Integer.parseInt(sp1pci));
                                    spBean1 = spBean;

//                                    ToastUtils.showToast("??????2221" + "" + sp1MAX1value);
//
                                    if (sb1locationgFlag1 == false) {
                                        if (!sp1MAX1value.equals("0") && !TextUtils.isEmpty(sp1MAX1value)) {
                                            if (!sp1MAX1value.equals(sp2MAX1value)) {
//                                                sb1Clear();//??????1????????????
                                                if (bts_start1.getText().equals(TITLEZDZM)) {//????????????
                                                    MainUtils.sbzmLocation(IP1, MainActivity.this);
                                                } else {
                                                    sb1Clear();//??????1????????????
                                                }
                                                Log.d("spBean1aaa", "handleMessage: " + spBean1);
//                                aaaaaa
//                                                tv_r1.setText("????????????" + sp1MAX1value + "");
                                                Set1StatusBar("????????????" + sp1MAX1value);
                                            } else {
                                                Set1StatusBar("?????????????????????");

                                            }

                                        } else {
//                                            ToastUtils.showToast("?????????0?????????");
                                            Set1StatusBar("?????????????????????");
                                        }

                                    } else {
                                        ToastUtils.showToast("??????????????????");
                                    }
//
                                } else {
                                    ToastUtils.showToast("?????????");
                                    Set1StatusBar("?????????????????????");
                                }
                            }
                        } else {//?????????????????????
                            if (!TextUtils.isEmpty(sp1MAX1value)) {
//                                Set1StatusBar("??????2????????????????????????sp1MAX1value?????????");
//                              spBean1.setUp(sp1up);
                                SpBean spBean = new SpBean();
                                spBean.setUp(sp1up);
                                spBean.setDown(sp1MAX1value);
                                spBean.setBand(sp1band);
                                spBean.setPlmn(sp1plmn);
                                spBean.setTac(Integer.parseInt(sp1tac));
                                spBean.setPci(Integer.parseInt(sp1pci));
                                spBean1 = spBean;


//                                ToastUtils.showToast("??????222" + "" + sp1MAX1value);
//
                                if (sb1locationgFlag1 == false) {
                                    if (!sp1MAX1value.equals("0") && !TextUtils.isEmpty(sp1MAX1value)) {
                                        if (!sp1MAX1value.equals(sp2MAX1value)) {
                                            if (bts_start1.getText().equals(TITLEZDZM)) {//????????????
                                                MainUtils.sbzmLocation(IP1, MainActivity.this);
                                            } else {
                                                sb1Clear();//??????1????????????
                                            }

                                            Log.d("spBean1aaaA", "handleMessage: " + spBean1);
//                                aaaaaa
//                                            tv_r1.setText("????????????" + sp1MAX1value + "");
                                            Set1StatusBar("????????????" + sp1MAX1value);
                                        } else {

                                            Set1StatusBar("?????????????????????");
                                        }

                                    } else {
//                                        ToastUtils.showToast("?????????0?????????");
                                        Set1StatusBar("?????????????????????");
                                    }

                                } else {
                                    ToastUtils.showToast("??????????????????");
                                }
//
                            } else {
                                ToastUtils.showToast("?????????");
//                                Set1StatusBar("??????2????????????????????????sp1MAX1value??????");
                                Set1StatusBar("?????????????????????");
                            }
                        }


                    }
                    if (itemtype1.equals(TITLESD)) {//?????????????????????
                        String dwon1s = msg.getData().getString("sp1MAX1value");//???????????????
                        Log.d("dwon1shan", "dwon1shan: " + dwon1s);

                        if (SaoPinB1 == false) {//?????????????????????  false ???,  true ????????????????????????
                            if (SPBEANLIST1.size() > 0) {

//                                Set1StatusBar("????????????????????????");
                                Intent intent = new Intent(MainActivity.this, SaoPinActivity.class);
                                intent.putExtra("type", "1");
                                startActivity(intent);
//                                dialog = new Dialog(MainActivity.this, R.style.menuDialogStyleDialogStyle);
//                                inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_item_spbeanlist, null);
//                                RecyclerView ry_saopinlist = inflate.findViewById(R.id.ry_saopinlist);//??????????????????
//                                ry_saopinlist.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
//                                SaopinListAdapter saopinListAdapter = new SaopinListAdapter(MainActivity.this, SPBEANLIST1);
//                                ry_saopinlist.setAdapter(saopinListAdapter);
//                                TextView tv_title = inflate.findViewById(R.id.tv_title);
//                                tv_title.setText("????????????");
//                                Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
//                                bt_confirm.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View view) {
//
//                                        dialog.dismiss();
//                                        dialog.cancel();
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
//                                //????????????Activity???????????????
//                                Window dialogWindow = dialog.getWindow();
//                                //??????Dialog?????????????????????
//                                dialogWindow.setGravity(Gravity.CENTER);
//                                //?????????????????????
//                                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
////                           lp.y = 20;//??????Dialog?????????????????????
////                          ????????????????????????
//                                dialogWindow.setAttributes(lp);
//                                dialog.show();//???????????????

                            } else {
                                Set1StatusBar("???????????????????????????");
                            }

                        }
                    }
                    if (itemtype1.equals(TITLEZD)) {//?????????????????????
                        String dwon1s = msg.getData().getString("sp1MAX1value");//???????????????
                        Log.d("dwon1shan", "dwon1shan: " + dwon1s);

                        if (SaoPinB1 == false) {//?????????????????????  false ???,  true ????????????????????????
                            if (SPBEANLIST1.size() > 0) {

//                                Set1StatusBar("????????????????????????");
                                Intent intent = new Intent(MainActivity.this, SaoPinActivity.class);
                                intent.putExtra("type", "1");
                                startActivity(intent);
//                                dialog = new Dialog(MainActivity.this, R.style.menuDialogStyleDialogStyle);
//                                inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_item_spbeanlist, null);
//                                RecyclerView ry_saopinlist = inflate.findViewById(R.id.ry_saopinlist);//??????????????????
//                                ry_saopinlist.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
//                                SaopinListAdapter saopinListAdapter = new SaopinListAdapter(MainActivity.this, SPBEANLIST1);
//                                ry_saopinlist.setAdapter(saopinListAdapter);
//                                TextView tv_title = inflate.findViewById(R.id.tv_title);
//                                tv_title.setText("????????????");
//                                Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
//                                bt_confirm.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View view) {
//
//                                        dialog.dismiss();
//                                        dialog.cancel();
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
//                                //????????????Activity???????????????
//                                Window dialogWindow = dialog.getWindow();
//                                //??????Dialog?????????????????????
//                                dialogWindow.setGravity(Gravity.CENTER);
//                                //?????????????????????
//                                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
////                           lp.y = 20;//??????Dialog?????????????????????
////                          ????????????????????????
//                                dialogWindow.setAttributes(lp);
//                                dialog.show();//???????????????

                            } else {
                                Set1StatusBar("???????????????????????????");
                            }

                        }
                    }

                    break;


                case 100153:
                    textViews.setText(stringBuffer1 + "");
                    scrollView.fullScroll(ScrollView.FOCUS_DOWN);//???????????????
                    break;

                case 100154:
//                    ToastUtils.showToast("100154");
//                    saopinSend1(pinConfigBeans, tf1);
                    break;
                case 100155:
                    SpinnersSetData(1);
                    break;
                ////?????????
                case 200120: //??????1?????????????????????
                    String zt2 = msg.getData().getString("zt2");
                    if (!TextUtils.isEmpty(zt2)) {
                        if (zt2.equals("0")) {
                            tv2_type.setText("????????????: " + "?????????...");
                            tv2_tf.setText("????????????:");
                            sb2 = "?????????...";
                            Set2StatusBar("?????????...");
                            mysp2.setEnabled(true);
                            break;
                        }
                        if (zt2.equals("1")) {
                            tv2_type.setText("????????????: " + "????????????");
                            tv2_tf.setText("????????????:");
                            sb2 = "????????????";
                            Set2StatusBar("????????????");
                            mysp2.setEnabled(true);
                            break;
                        }
                        if (zt2.equals("2")) {
                            tv2_type.setText("????????????: " + "??????");//????????????
                            //
//                            ??????1??????
                            sb2 = "??????";
//                            Set2StatusBar("??????");
                            mysp2.setEnabled(true);
                            tv_imsi2.setText("");
                            if (itemtype2.equals(TITLEZD)) {//?????????????????????
                                mysp2.setVisibility(View.GONE);
                                tv_r2.setText("????????????");

                            } else {
                                mysp2.setVisibility(View.VISIBLE);
                                tv_r2.setText("????????????");
                            }
                            //????????????2???????????????????????????????????????
                            if (sb1zy2 == false) {
//                                zy(2);//??????????????????
                                DBManagerZY dbManagerZY = null;
                                try {
                                    dbManagerZY = new DBManagerZY(MainActivity.this);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                cbzt2_g.setChecked(true);
                                if (tf2.equals("TDD")) {
                                    int data = 0;
                                    try {
                                        data = dbManagerZY.foriddata(2, 1, 3);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    setzy(data, 2);
                                    sb1zy2 = true;
                                }
                                if (tf2.equals("FDD")) {
                                    int data = 0;
                                    try {
                                        data = dbManagerZY.foriddata(2, 2, 3);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    setzy(data, 2);
                                    sb1zy2 = true;
                                }
                            }


                            if (timerRestartFlag2 == true) {

//                                timerRestartFlag1
                                rRestart(2001388);
                                timerRestartFlag2 = false;
                            }
                            break;
                        }
                        if (zt2.equals("3")) {
//                            tv1_type.setText("????????????: " + "?????????????????????");//????????????
                            sb2 = "?????????????????????";
                            Set2StatusBar("?????????????????????");
                            mysp2.setEnabled(true);

                            break;
                        }
                        if (zt2.equals("4")) {
//                            tv1_type.setText("????????????: " + "???????????????");//????????????
                            sb2 = "???????????????";
                            Set2StatusBar("???????????????");
                            mysp2.setEnabled(true);
                            break;
                        }
                        if (zt2.equals("5")) {
                            if (bts_start2.getText().equals(TITLEZDZM)) {//????????????
                                Set2StatusBar("?????????");
                                tv2_type.setText("????????????: " + "?????????");//????????????

                                sb2 = "?????????";
                                CALLBLACKOPEN2 = false;
                                Set2StatusBar("?????????");
                                mysp2.setEnabled(false);
                                sb2types = "?????????";
                                sb2type = "?????????";
                                if (sb1zy2 == false) {
                                    zy(2);//??????????????????
//                                ToastUtils.showToast(sb1pdStr);
                                }


                                DOWN22 = DOWNPIN2;
                                if (DOWN22.equals(DOWN21)) {

                                } else {
                                    DOWN21 = DOWN22;
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    DBManagerLog dbManagerLog = null;
                                    //????????????
                                    try {
                                        dbManagerLog = new DBManagerLog(MainActivity.this);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    LogBean logBean = new LogBean();
                                    logBean.setAssociated(LoginUtils.getId(MainActivity.this) + "");//??????ID
                                    logBean.setEvent(LoginUtils.setBase64("??????"));//????????????
                                    logBean.setSb(LoginUtils.setBase64("1"));
                                    logBean.setPd(LoginUtils.setBase64(DOWN21));
                                    String format = sdf.format(new Date());//????????????
                                    logBean.setDatetime(LoginUtils.setBase64(format));
                                    try {
                                        dbManagerLog.insertConmmunit01Bean(logBean);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else if (bts_start2.getText().equals(TITLESDZM)) {
                                Set2StatusBar("?????????");
                                sb2 = "?????????";
                                CALLBLACKOPEN2 = false;
                                Set2StatusBar("?????????");
                                mysp2.setEnabled(false);
                                sb2types = "?????????";
                                sb2type = "?????????";
                                tv2_type.setText("????????????: " + "?????????");//????????????
                                DOWN22 = DOWNPIN2;
                                if (DOWN22.equals(DOWN21)) {

                                } else {
                                    DOWN21 = DOWN22;
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    DBManagerLog dbManagerLog = null;
                                    //????????????2
                                    try {
                                        dbManagerLog = new DBManagerLog(MainActivity.this);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    LogBean logBean = new LogBean();
                                    logBean.setAssociated(LoginUtils.getId(MainActivity.this) + "");//??????ID
                                    logBean.setEvent(LoginUtils.setBase64("??????"));//????????????
                                    logBean.setSb(LoginUtils.setBase64("1"));
                                    logBean.setPd(LoginUtils.setBase64(DOWN21));
                                    String format = sdf.format(new Date());//????????????
                                    logBean.setDatetime(LoginUtils.setBase64(format));
                                    try {
                                        dbManagerLog.insertConmmunit01Bean(logBean);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else {
                                Set2StatusBar("?????????");
                                tv2_type.setText("????????????: " + "?????????");//????????????
                                sb2 = "?????????";
                                CALLBLACKOPEN2 = false;
                                Set2StatusBar("?????????");
                                mysp2.setEnabled(false);
                                sb2types = "?????????";
                                sb2type = "?????????";
                                if (sb1zy2 == false) {
                                    zy(2);//??????????????????
//                                ToastUtils.showToast(sb1pdStr);
                                }
                            }
                            break;
                        }
                    }
                case 2001200:
                    String testa = msg.getData().getString("test");
                    if (!TextUtils.isEmpty(testa)) {
                        if (testa.equals("1")) {
                            Set2StatusBar("??????????????????");
                            break;
                        }
                        if (testa.equals("2")) {
                            Set2StatusBar("??????????????????");
                            break;
                        }
                        if (testa.equals("3")) {
                            Set2StatusBar("GPS????????????");
                            break;
                        }
                        if (testa.equals("4")) {
                            Set2StatusBar("GPS????????????");
                            break;
                        }
                        if (testa.equals("5")) {
                            Set2StatusBar("????????????");
                            break;
                        }
                        if (testa.equals("6")) {
                            Set2StatusBar("????????????");
                            break;
                        }
                        if (testa.equals("7")) {
                            Set2StatusBar("??????????????????");
                            break;
                        }
                        if (testa.equals("8")) {
                            Set2StatusBar("??????????????????");
                            break;
                        }
                        if (testa.equals("9")) {
                            Set2StatusBar("???????????????");

                            if (itemtype2.equals(TITLEZD)) {
                                mysp2.setVisibility(View.GONE);
                                tv_r2.setText("????????????");
                            } else {
                                mysp2.setVisibility(View.VISIBLE);
                                tv_r2.setText("????????????");
                            }

                            break;
                        }
                        if (testa.equals("10")) {
                            Set2StatusBar("???????????????");
                            break;
                        }
                        if (testa.equals("11")) {
                            Set2StatusBar("GPS?????????");
                            break;
                        }
                        if (testa.equals("12")) {
                            Set2StatusBar("?????????");
                            break;
                        }
                        if (testa.equals("13")) {
                            Set2StatusBar("???????????????");
                            sb2type = "???????????????";
                            break;
                        }
                        if (testa.equals("14")) {
                            Set2StatusBar("??????????????????");
                            sb2type = "??????????????????";
                            break;
                        }

                    }
                case 200130://????????????????????????????????????
//                    ToastUtils.showToast("????????????");//???????????????-?????????????????????
//                    Log.d(TAG, "handleMessage2????????????: ");
//                    ClearFlag2 = false;
//                    ClearFlags2 = false;
//                    sendBlackList2();
//                    Set2StatusBar("?????????????????????");
                    CALLBLACKFLAG2 = false;
                    if (itemtype2.equals(TITLEZD)) {//?????????????????????

                        ClearFlag2 = false;
                        ClearFlags2 = false;

                        Set2StatusBar("?????????????????????");
                        sb2type = "?????????????????????";
                        BLACKTIMESET2 = System.currentTimeMillis();
                        CALLBLACKFLAGSET2 = true;
                        sendBlackListsp2();
                    }
                    if (itemtype2.equals(TITLESD)) {//?????????????????????
                        Log.d(TAG, "handleMessage2????????????: ");
                        ClearFlag2 = false;
                        ClearFlags2 = false;

                        Set2StatusBar("?????????????????????");
                        sb2type = "?????????????????????";
                        BLACKTIMESET2 = System.currentTimeMillis();
                        CALLBLACKFLAGSET2 = true;
                        sendBlackList2();
                    }
                    break;

                case 200132:
                    //??????????????????
//                    ToastUtils.showToast("??????????????????");

                    Log.d(TAG, "handleMessage:?????????????????? ");
//                    Set2StatusBar("??????????????????");
                    break;
                case 200131://?????????????????????????????????
//

                    if (itemtype2.equals(TITLEZD)) {//??????
                        ToastUtils.showToast("???????????????????????????");
                        Log.d("tagsaopin", "handleMessage2???????????????????????????: ");
//                    ????????????????????????-????????? ????????????
                        BlackFlags2 = false;
                        sb2LocationsSP();//??????2????????????
                        BLACKLOCATION2 = System.currentTimeMillis();
                        CALLBLACKLOCATION2 = true;
                        Set2StatusBar("???????????????????????????");
                        sb2type = "???????????????????????????";
                        CALLBLACKFLAGSET2 = false;
                        CALLBLACKFLAG2 = false;
                    } else {
                        ToastUtils.showToast("???????????????????????????");
                        Log.d("tagsd", "handleMessage2???????????????????????????: ");
//                    ????????????????????????-????????? ????????????
                        BlackFlags2 = false;
                        sb2Locations();//??????2????????????
                        BLACKLOCATION2 = System.currentTimeMillis();
                        CALLBLACKLOCATION2 = true;
                        Set2StatusBar("???????????????????????????");
                        sb2type = "???????????????????????????";
                        CALLBLACKFLAGSET2 = false;
                        CALLBLACKFLAG2 = false;
                    }
                    break;
                case 200133:
//                    ToastUtils.showToast("???????????????");
//                    Set2StatusBar("???????????????");
                    BlackFlag2 = true;
                    break;
                case 200134:
//                    ToastUtils.showToast("????????????");
                    Set2StatusBar("?????????????????????");
                    BlackFlag2 = true;
                    break;
                case 200135:
//                    ToastUtils.showToast("???????????????");
                    Set2StatusBar("?????????????????????");
                    BlackFlag2 = true;
                    break;

                case 200136: //????????????????????????
//                    ToastUtils.showToast("????????????????????????");


                    if (bts_start2.getText().equals(TITLEZDZM)) {//????????????
                        Set2StatusBar("????????????????????????");
                    } else if (bts_start2.getText().equals(TITLESDZM)) {
                        Set2StatusBar("????????????????????????");
                    } else {
                        Set2StatusBar("????????????????????????");
                    }
//                        Set2StatusBar("????????????????????????");
                    sb2type = "????????????????????????";
                    CALLBLACKFLAG2 = false;
                    CALLBLACKFLAGSET2 = false;
                    CALLBLACKLOCATION2 = false;

                    CALLBLACKOPEN2 = false;
//                    if (sb2locationgFlag == true) {
//                        sb2locationgFlag = false;
//                    } else if (sb2locationgFlag == false) {
                    GFFLAG2 = 1;
                    MainUtils.OpenGF2(2, 1, handler);// sb1: ???????????? 1??????53,2 ??????54 .
                    BLACKOPENSET2 = System.currentTimeMillis();
                    CALLBLACKOPEN2 = true;

//                    }
                    // switchs :????????????, 1????????? 2?????????.
                    LocationFlag2 = false;
//                    if (sb2zhishiFlag == true) {
//                        Log.d(TAG, "handleMessage:sb2zhishiFlag " + true);
//                    } else {
//                        //????????????
//                        MainUtils.Restart2();
//
//                        Set2StatusBar("??????????????????");
//                        Log.d(TAG, "handleMessage:sb2zhishiFlag " + false);
//                    }
                    Log.d("200142", "handleMessage: " + 1);
                    if (GFFLAG2 == 1) {
                        //????????????
                        if (itemtype2.equals(TITLEZD)) {//?????????????????????
                            if (sb2.equals("?????????")) {
                                break;
                            } else {
                                EstablishVillageSS2(sp2MAX1value);
//                                Set2StatusBar("??????????????????");
                                CALLBLACKOPEN2 = false;
                                Log.d("200142", "handleMessage: " + 2);
                            }

                        }
                        if (itemtype2.equals(TITLESD)) {//?????????????????????
                            EstablishVillage2();
//                            Set2StatusBar("??????????????????");
                            CALLBLACKOPEN2 = false;
                            Log.d("200142", "handleMessage: " + 3);
                        }
                    }
                    if (GFFLAG2 == 3) {  // ??????3??????TDD????????????
                        if (itemtype2.equals(TITLEZD)) {//?????????????????????
//                            Set2StatusBar("??????????????????");
                            Log.d("200142", "handleMessage: " + 4);
                            CALLBLACKOPEN2 = false;
                            sb2zhishiFlag = true;
                            try {
                                saopinBeanList = dbManagersaopin.getStudent(); //?????????????????????
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
                                    saopinSend2(saopinBeanList, tf2, SAOPIN2);
//                                            MainUtils.start1SNF(IP1);
//                                            ToastUtils.showToast("snf?????????");
                                    GFFLAG2 = 1;
                                } else {
                                    ToastUtils.showToast("??????????????????");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.d("200142", "handleMessage: " + 5);
//                            Set2StatusBar("??????????????????");
                            CALLBLACKOPEN2 = false;
                            sb2zhishiFlag = true;
                            try {
                                saopinBeanList = dbManagersaopin.getStudent(); //?????????????????????
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
                                    saopinSend2(saopinBeanList, tf2, SAOPIN2);
//                                            MainUtils.start1SNF(IP1);
//                                            ToastUtils.showToast("snf?????????");
                                    GFFLAG2 = 1;
                                } else {
                                    ToastUtils.showToast("??????????????????");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (bts_start2.getText().equals(TITLEZDZM)) {//????????????
                        EstablishVillageSS2(sp2MAX1value);
                        break;
                    }

                    break;
                case 200137: //????????????????????????
//                    ToastUtils.showToast("??????????????????");
                    Log.d(TAG, "handleMessagea: " + "??????????????????");
//                    LocationFlag1 = false;
                    Set2StatusBar("??????????????????");
                    break;

                case 200138: //??????????????????
//                    ToastUtils.showToast("??????????????????");
                    Log.d(TAG, "handleMessagea: " + "??????????????????");
                    Set2StatusBar("?????????...");

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("??????????????????", "run:788888888888");
//                                saopinSend2(saopinBeanList, tf2, SAOPIN2);
                            timerRestartFlag2 = true;
                        }
                    }, 30000);

                    break;
                case 2001388: //??????????????????
//                    ToastUtils.showToast("??????????????????");
                    Log.d(TAG, "handleMessagea: " + "??????????????????");
//                    Set2StatusBar("?????????...");


                    if (bts_start2.getText().equals("????????????")) {
                        MainUtils.sbzmLocation(IP2, MainActivity.this);
                        return;
                    }
                    if (bts_start2.getText().equals("????????????")) {

                        return;
                    }
                    if (itemtype2.equals(TITLEZD)) {//?????????????????????
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("??????????????????", "run:788888888888");
                                if (phone2sp) {
                                    sb2Clear();
                                } else {
                                    saopinSend2(saopinBeanList, tf2, SAOPIN2);

                                }

                            }
                        }, 1000);
                    }
                    if (itemtype2.equals(TITLESD)) {//?????????????????????
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Log.d(TAG, "run:788888888888");
//                            sb1Clear();//??????1????????????
                                if ("??????".equals(sb2) || "?????????...".equals(sb2) || "?????????".equals(sb2) || "????????????".equals(sb2) || "?????????????????????".equals(sb2) || "???????????????".equals(sb2)) {
                                    String sb2zhishi = "";
                                    if (!TextUtils.isEmpty(spinnerS2)) {
                                        try {
                                            pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(spinnerS2)); //?????????????????????
                                            sb2zhishi = pinConfigBeans.get(0).getTf();
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                        Log.d(TAG, "start1sb1zhishi: " + sb2zhishi);
//                String s = tv1_td.getText().toString();
                                        if (!TextUtils.isEmpty(tf2)) {
                                            if (sb2zhishi.equals(tf2)) {//????????????
                                                sb2zhishiFlag = true;
//                                            ToastUtils.showToast("????????????");
                                                sb2Clear();//??????1????????????

//                        sb1Locations();//??????1????????????
                                            } else {//???????????????
                                                sb2zhishiFlag = false;
                                                ToastUtils.showToast("???????????????");
//                                                new CircleDialog.Builder((FragmentActivity) mContext)
//                                                        .setTitle("")
//                                                        .setText("??????????????????????????????????")
//                                                        .setTitleColor(Color.parseColor("#00acff"))
//                                                        .setNegative("??????", new MyOclck2(sb2zhishi, 11))
//                                                        .setPositive("??????", null)
//                                                        .show();
                                                dialog = new Dialog(MainActivity.this, R.style.menuDialogStyleDialogStyle);
                                                inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_item_title, null);
                                                TextView tv_title = inflate.findViewById(R.id.tv_title);
                                                tv_title.setText("?????????????????????????????????");
                                                Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
                                                bt_confirm.setOnClickListener(new MyOclck2(sb2zhishi, 11, dialog));
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
                                        } else {
                                            ToastUtils.showToast("???????????????2");
                                        }

                                    }
                                } else {
                                    ToastUtils.showToast("???????????????2");
                                }

                            }
                        }, 1000);
                    }
                    break;
                case 200139: //????????????
//                    ToastUtils.showToast("????????????2");
//                    Log.d(TAG, "handleMessagea: " + "????????????");
                    Set2StatusBar("????????????");
                    break;


                case 200140:
//                    ToastUtils.showToast("??????????????????2");
                    Set2StatusBar("??????????????????");
                    MainUtils.Restart2();
                    break;

                case 200141:
//                    ToastUtils.showToast("??????????????????2");
                    Set2StatusBar("??????????????????2");
                    break;


                case 200142:
//                    ToastUtils.showToast("??????????????????2");
//                    //????????????
//                    if (GFFLAG2 == 1) {
//                        EstablishVillage2();
//                        Set2StatusBar("??????????????????");
//                    }
                    Log.d("200142", "handleMessage: " + 1);
                    if (GFFLAG2 == 1) {
                        //????????????
                        if (itemtype2.equals(TITLEZD)) {//?????????????????????
                            if (sb2.equals("?????????")) {
                                break;
                            } else {
                                EstablishVillageSS2(sp2MAX1value);
//                                Set2StatusBar("??????????????????");
                                CALLBLACKOPEN2 = false;
                                Log.d("200142", "handleMessage: " + 2);
                            }

                        }
                        if (itemtype2.equals(TITLESD)) {//?????????????????????
                            EstablishVillage2();
//                            Set2StatusBar("??????????????????");
                            CALLBLACKOPEN2 = false;
                            Log.d("200142", "handleMessage: " + 3);
                        }
                    }
                    if (GFFLAG2 == 3) {  // ??????3??????TDD????????????
                        if (itemtype2.equals(TITLEZD)) {//?????????????????????
//                            Set2StatusBar("??????????????????");
                            Log.d("200142", "handleMessage: " + 4);
                            CALLBLACKOPEN2 = false;
                            sb2zhishiFlag = true;
                            try {
                                saopinBeanList = dbManagersaopin.getStudent(); //?????????????????????
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
                                    saopinSend2(saopinBeanList, tf2, SAOPIN2);
//                                            MainUtils.start1SNF(IP1);
//                                            ToastUtils.showToast("snf?????????");
                                    GFFLAG2 = 1;
                                } else {
                                    ToastUtils.showToast("??????????????????");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.d("200142", "handleMessage: " + 5);
//                            Set2StatusBar("??????????????????");
                            CALLBLACKOPEN2 = false;
                            sb2zhishiFlag = true;
                            try {
                                saopinBeanList = dbManagersaopin.getStudent(); //?????????????????????
                                if (saopinBeanList != null && saopinBeanList.size() > 0) {
                                    saopinSend2(saopinBeanList, tf2, SAOPIN2);
//                                            MainUtils.start1SNF(IP1);
//                                            ToastUtils.showToast("snf?????????");
                                    GFFLAG2 = 1;
                                } else {
                                    ToastUtils.showToast("??????????????????");
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                    break;

                case 200143:
//                    ToastUtils.showToast("??????????????????2");
//                    Set2StatusBar("??????????????????2");
                    break;

                case 200145:
//                    ToastUtils.showToast("??????????????????2");
                    Log.d("nzq", "handleMessage??????????????????: ");
                    sb2type = "??????????????????";
                    if (title.getText().equals(getString(R.string.activity_saopintitle))) {
//                        Set2StatusBar("????????????????????????");
                    }
                    if (title.getText().equals(getString(R.string.activity_title))) {
//                        Set2StatusBar("??????????????????????????????");
                        xiaoquFlag2 = false;
                    }
                    break;
                case 200146:
//                    ToastUtils.showToast("??????????????????2");
                    Set2StatusBar("??????????????????");
                    xiaoquFlags2 = false;
                    Log.d("nzq", "handleMessage??????????????????2: ");
                    break;

                case 200147:
                    String sb1j2 = msg.getData().getString("sb1j2");
                    Log.d(TAG, "sb1jlhandleMessage: " + sb1j2 + "---");
                    if (!TextUtils.isEmpty(sb1j2)) {
                        String format = "";
                        if (tf2.equals("TDD")) {
                            double v = Double.parseDouble(sb1j2);
                            double s = v / 110 * 1000;
                            format = dfBaoshu.format(s);
                            Log.d(TAG, "dfBaoshuhandleMessage: " + format);
                        } else {
                            double v = Double.parseDouble(sb1j2);
                            double s = v / 164 * 1000;
                            format = dfBaoshu.format(s);
                            Log.d(TAG, "dfBaoshuhandleMessage: " + format);
                        }


                        sb1_j2.setText(format);
                        if (labaFlag2 == true) {
                            startAuto(format, true);
                        }
                        if (list2quxian.size() > 0) {
                            list2quxian.remove(0);
                            list2quxian.add(Integer.parseInt(format));
                            double total = 0;
                            for (int i = 0; i < list2quxian.size(); i++) {
                                total += list2quxian.get(i);
                            }
                            double a = total / list2quxian.size();
                            DecimalFormat df = new DecimalFormat("###");
                            sb1_jl_pj2.setText(df.format(a));
                            setqxData(list1quxian, list2quxian);
                        }
                    }

                    break;
                case 200148://??????1??????????????????imsi
                    imsi2 = msg.getData().getString("imsi2");
                    tv_imsi2.setText(imsi2);
                    Log.e("tv_imsi2", "tv_imsi2handleMessage: " + imsi2);
                    imsi22 = imsi2;
                    if (imsi21.equals(imsi22)) {

                    } else {
                        imsi21 = imsi22;

                        SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        DBManagerLog dbManagerLog2 = null;
                        //????????????
                        try {
                            dbManagerLog2 = new DBManagerLog(MainActivity.this);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        LogBean logBean2 = new LogBean();
                        logBean2.setAssociated(LoginUtils.getId(MainActivity.this) + "");//??????ID
                        logBean2.setEvent(LoginUtils.setBase64("??????"));//????????????
                        logBean2.setPd(LoginUtils.setBase64(DOWNPIN2));
                        logBean2.setSb(LoginUtils.setBase64(2 + ""));
                        logBean2.setSucessIMSI(LoginUtils.setBase64(imsi21));
                        String formatD2 = sdfs.format(new Date());//????????????
                        logBean2.setDatetime(LoginUtils.setBase64(formatD2));
                        try {
                            dbManagerLog2.insertConmmunit01Bean(logBean2);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }


                    imsi2 = "";
//                    String sb1j2s = msg.getData().getString("sb1j2");
//                    startAuto(sb1j2s, false);
                    break;

//

                case 200149:
                    String zy2 = msg.getData().getString("zy2");
//                    tv_imsi2.setText(zy2);
                    sb1zy2 = true;
                    seekbar2.setProgress(Integer.parseInt(zy2));
//                    Set2StatusBar(zy2);
                    tv_zy2.setText("???????????????:" + zy2 + "dB");
                    break;

                case 200150:
                    String zyset2 = msg.getData().getString("zyset2");
                    if (zyset2.equals("?????????????????????")) {
//                        zy(2);//
//                        ToastUtils.showToast("?????????????????????2");
//                        Set2StatusBar("?????????????????????1");
                    }
                    if (zyset2.equals("?????????????????????")) {
                        ToastUtils.showToast("?????????????????????2");
//                        Set2StatusBar("?????????????????????2");
                    }

                    break;

                case 200151:
                    String downww = msg.getData().getString("down");

                    Log.d(TAG, "151handleMessage: " + downww);
                    sb2pdStr = downww;

//
                    if (itemtype2.equals(TITLEZD)) {//?????????????????????
                        if (sb2.equals("?????????")) {
                            mysp2.setVisibility(View.GONE);
                            tv_r2.setText("????????????" + sb2pdStr);
                        }

                    } else {
                        if (sb2.equals("?????????")) {
                            mysp2.setVisibility(View.GONE);
                            tv_r2.setText("????????????" + sb2pdStr);
                        }

                    }
//                        String sr = "";
//                        try {
//                            List<PinConfigBean> pinConfigBeans = dbManagerPinConfig.getStudent();
//                            for (int i = 0; i < pinConfigBeans.size(); i++) {
//                                if (sb2pdStr.equals(pinConfigBeans.get(i).getDown() + "")) {
//                                    sr = "1";
//                                }
//
//                            }
//                            if (!TextUtils.isEmpty(sr)) {//    ????????????  ??????
//                                for (int i = 0; i < listsSp.size(); i++) {
//                                    if (listsSp.get(i).equals(sb2pdStr)) {
//                                        mysp2.setSelection(i);
////                                        sb1pd = true;
//
//                                    }
//                                }
//                            } else {//???????????????
//
//                                tv_r2.setText("????????????" + sb2pdStr);
//                            }
//
//                        } catch (SQLException e) {
//                            e.printStackTrace();
//                        }
//                    } else {//?????????????????????
//                    tv_r2.setText("????????????" + sb2pdStr);
//                    }
                    break;

                case 200152:
//                    Set2StatusBar("200152---" + msg.getData().getString("sp1MAX1value54"));
                    if (itemtype2.equals(TITLEZD)) {//?????????????????????
                        if (sb2.equals("?????????")) {
                            break;
                        }
                        String dwon1s54 = msg.getData().getString("sp1MAX1value54");//???????????????

                        String sp1up54 = msg.getData().getString("sp1up54");
                        String sp1pci54 = msg.getData().getString("sp1pci54");
                        String sp1plmn54 = msg.getData().getString("sp1plmn54");
                        String sp1band54 = msg.getData().getString("sp1band54");
                        String sp1tac54 = msg.getData().getString("sp1tac54");
                        sp2MAX2value = msg.getData().getString("sp1MAX2value54");//???????????????
                        String sp2up54 = msg.getData().getString("sp2up54");
                        String sp2pci54 = msg.getData().getString("sp2pci54");
                        String sp2plmn54 = msg.getData().getString("sp2plmn54");
                        String sp2band54 = msg.getData().getString("sp2band54");
                        String sp2tac54 = msg.getData().getString("sp2tac54");

                        spBean254.setUp(sp2up54);
                        spBean254.setDown(sp2MAX2value);
                        spBean254.setBand(sp2band54);
                        spBean254.setPlmn(sp2plmn54);
                        if (!TextUtils.isEmpty(sp2tac54)) {
                            spBean254.setTac(Integer.parseInt(sp2tac54));
                        } else {
                            spBean254.setTac(0);
                        }
                        if (!TextUtils.isEmpty(sp2tac54)) {
                            spBean254.setPci(Integer.parseInt(sp2pci54));
                        } else {
                            spBean254.setPci(0);
                        }
//                        spBean254.setPci(Integer.parseInt(sp2pci54));

                        if (TextUtils.isEmpty(dwon1s54)) {
                            Set2StatusBar("?????????????????????");
                            break;
                        }
                        if (dwon1s54.equals(sp2MAX1value)) {
                            break;
                        } else {
                            sp2MAX1value = dwon1s54;

                        }
                        if (sb1.equals("?????????")) {
                            if (tf1.equals(tf2)) {//?????????????????????
//                                Set2StatusBar("????????????");
                                if (sp2MAX1value.equals(sp1MAX1value)) {//??????2???????????????1???????????????????????????????????????????????????
//                                    Set2StatusBar("sp2MAX1value???sp1MAX1value??????");
                                    if (!TextUtils.isEmpty(sp2MAX2value)) {
//                                        Set2StatusBar("???sp2MAX2value????????????sp2MAX2value");
//                        spBean1.setUp(sp1up);
                                        spBean254.setUp(sp2up54);
                                        spBean254.setDown(sp2MAX2value);
                                        spBean254.setBand(sp2band54);
                                        spBean254.setPlmn(sp2plmn54);
                                        spBean254.setTac(Integer.parseInt(sp2tac54));
                                        spBean254.setPci(Integer.parseInt(sp2pci54));
                                        spBean154 = spBean254;


//                                        ToastUtils.showToast("??????" + "" + sp2MAX2value);
                                        Log.d("spBean154", "handleMessage: " + spBean254);
//
                                        if (sb2locationgFlag == false) {
                                            if (!sp2MAX2value.equals("0") && !TextUtils.isEmpty(sp2MAX2value)) {
//                                                sb2Clear();//??????1????????????

                                                if (bts_start2.getText().equals(TITLEZDZM)) {//????????????
                                                    MainUtils.sbzmLocation(IP2, MainActivity.this);
                                                } else {
                                                    sb2Clear();//??????1????????????
                                                }
                                                Log.d("spBean1aaa", "handleMessage: " + spBean254);
//                                                tv_r2.setText("????????????" + sp2MAX2value);
                                                Set2StatusBar("????????????" + sp2MAX2value);
                                            } else {
//                                                ToastUtils.showToast("?????????0?????????");
                                                Set2StatusBar("?????????????????????");
                                            }
                                        } else {
                                            ToastUtils.showToast("??????????????????");
                                        }

                                    } else {
//                                        ToastUtils.showToast("1?????????");
//                                        Set2StatusBar("sp2MAX1value???sp1MAX1value?????? sp2MAX2value??????,?????????");
                                        Set2StatusBar("?????????????????????");
                                    }
                                } else {
//                                    Set2StatusBar("sp2MAX1value ???sp1MAX1value??????");
                                    if (!TextUtils.isEmpty(sp2MAX1value)) {
//                                        Set2StatusBar("sp2MAX1value????????? ???sp2MAX1value");
//                        spBean1.setUp(sp1up);
                                        SpBean spBean = new SpBean();
                                        spBean.setUp(sp1up54);
                                        spBean.setDown(sp2MAX1value);
                                        spBean.setBand(sp1band54);
                                        spBean.setPlmn(sp1plmn54);
                                        spBean.setTac(Integer.parseInt(sp1tac54));
                                        spBean.setPci(Integer.parseInt(sp1pci54));
                                        spBean154 = spBean;


                                        ToastUtils.showToast("??????" + "" + sp2MAX1value);
                                        Log.d("spBean154", "handleMessage: " + spBean154);
//
                                        if (sb2locationgFlag == false) {
                                            if (!sp2MAX1value.equals("0") && !TextUtils.isEmpty(sp2MAX1value)) {
//                                                sb2Clear();//??????1????????????
                                                if (bts_start2.getText().equals(TITLEZDZM)) {//????????????
                                                    MainUtils.sbzmLocation(IP2, MainActivity.this);
                                                } else {
                                                    sb2Clear();//??????1????????????
                                                }
                                                Log.d("spBean1aaa", "handleMessage: " + spBean154);
//                                                tv_r2.setText("????????????" + sp2MAX1value + "");
                                                Set2StatusBar("????????????" + sp2MAX1value);
                                            } else {
//                                                ToastUtils.showToast("?????????0?????????");
                                                Set2StatusBar("?????????????????????");
                                            }
                                        } else {
                                            ToastUtils.showToast("??????????????????");
                                        }

                                    } else {
//                                        ToastUtils.showToast("2?????????");
//                                        Set2StatusBar("sp2MAX1value?????? ?????????");
                                        Set2StatusBar("?????????????????????");
                                    }
                                }
                            } else {//???????????????????????????
                                Set2StatusBar("????????????");
                                if (!TextUtils.isEmpty(sp2MAX1value)) {
//                                    Set2StatusBar("????????????sp2MAX1value?????????");
//                        spBean1.setUp(sp1up);
                                    SpBean spBean = new SpBean();
                                    spBean.setUp(sp1up54);
                                    spBean.setDown(sp2MAX1value);
                                    spBean.setBand(sp1band54);
                                    spBean.setPlmn(sp1plmn54);
                                    spBean.setTac(Integer.parseInt(sp1tac54));
                                    spBean.setPci(Integer.parseInt(sp1pci54));
                                    spBean154 = spBean;


                                    ToastUtils.showToast("??????" + "" + sp2MAX1value);
                                    Log.d("spBean154", "handleMessage: " + spBean154);
//
                                    if (sb2locationgFlag == false) {
                                        if (!sp2MAX1value.equals("0") && !TextUtils.isEmpty(sp2MAX1value)) {
//                                            sb2Clear();//??????1????????????
                                            if (bts_start2.getText().equals(TITLEZDZM)) {//????????????
                                                MainUtils.sbzmLocation(IP2, MainActivity.this);
                                            } else {
                                                sb2Clear();//??????1????????????
                                            }
                                            Log.d("spBean1aaa", "handleMessage: " + spBean154);
//                                            tv_r2.setText("????????????" + sp2MAX1value);
                                            Set2StatusBar("????????????" + sp2MAX1value);
                                        } else {
//                                            ToastUtils.showToast("?????????0?????????");
                                            Set2StatusBar("?????????????????????");
                                        }
                                    } else {
                                        ToastUtils.showToast("??????????????????");
                                    }

                                } else {
//                                    ToastUtils.showToast("3?????????");
                                    Set2StatusBar("?????????????????????");
                                }
                            }
                        } else {
                            if (!TextUtils.isEmpty(sp2MAX1value)) {
//                                Set2StatusBar("??????1?????????????????? sp2MAX1value????????? ??????sp2MAX1value");
//                        spBean1.setUp(sp1up);
                                SpBean spBean = new SpBean();
                                spBean.setUp(sp1up54);
                                spBean.setDown(sp2MAX1value);
                                spBean.setBand(sp1band54);
                                spBean.setPlmn(sp1plmn54);
                                spBean.setTac(Integer.parseInt(sp1tac54));
                                spBean.setPci(Integer.parseInt(sp1pci54));
                                spBean154 = spBean;


                                ToastUtils.showToast("??????" + "" + sp2MAX1value);
                                Log.d("spBean154", "handleMessage: " + spBean154);
//
                                if (sb2locationgFlag == false) {
                                    if (!sp2MAX1value.equals("0") && !TextUtils.isEmpty(sp2MAX1value)) {
//                                        sb2Clear();//??????1????????????

                                        if (bts_start2.getText().equals(TITLEZDZM)) {//????????????
                                            MainUtils.sbzmLocation(IP2, MainActivity.this);
                                        } else {
                                            sb2Clear();//??????1????????????
                                        }
                                        Log.d("spBean1aaa", "handleMessage: " + spBean154);
//                                        tv_r2.setText("????????????" + sp2MAX1value);
                                        Set2StatusBar("????????????" + sp2MAX1value);
                                    } else {
//                                        ToastUtils.showToast("?????????0?????????");
                                        Set2StatusBar("?????????????????????");
                                    }
                                } else {
                                    ToastUtils.showToast("??????????????????");
                                }

                            } else {
                                ToastUtils.showToast("4?????????");
                                Set2StatusBar("?????????????????????");
                            }
                        }


                    }
                    if (itemtype2.equals(TITLESD)) {//????????????
                        String dwon1s = msg.getData().getString("sp1MAX1value");//???????????????
                        Log.d("dwon1shan", "dwon1shan: " + dwon1s);

                        if (SaoPinB2 == false) {//?????????????????????  false ???,  true ????????????????????????
                            if (SPBEANLIST2.size() > 0) {
                                Set2StatusBar("????????????????????????");
                                Intent intent = new Intent(MainActivity.this, SaoPinActivity.class);
                                intent.putExtra("type", "2");
                                startActivity(intent);
//
                            } else {
                                Set2StatusBar("???????????????????????????");
                            }

                        }
                    }
                    if (itemtype2.equals(TITLEZD)) {//????????????
                        String dwon1s = msg.getData().getString("sp1MAX1value");//???????????????
                        Log.d("dwon1shan", "dwon1shan: " + dwon1s);

                        if (SaoPinB2 == false) {//?????????????????????  false ???,  true ????????????????????????
                            if (SPBEANLIST2.size() > 0) {
                                Set2StatusBar("????????????????????????");
                                Intent intent = new Intent(MainActivity.this, SaoPinActivity.class);
                                intent.putExtra("type", "2");
                                startActivity(intent);
//
                            } else {
                                Set2StatusBar("???????????????????????????");
                            }

                        }
                    }
                    break;

                case 200155:
                    SpinnersSetData(2);
                    break;
            }
        }
    };

    //?????????????????????
    private void rRestart(final int i) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                handler.sendMessage(message);
                message.what = i;
            }
        }).start();
    }

    //???????????????IMSI
    private void sendBlackListsp() {
        Log.d(TAG, "sendBlackList:?????? " + spinnerS1);
        sendListBlack = new ArrayList<>();

        if (listImsiListTrue.size() > 0 && listImsiListTrue != null) {
            for (int i = 0; i < listImsiListTrue.size(); i++) {
//                    if (listImsiListTrue.get(i).getYy().equals(yy)) {
                sendListBlack.add(listImsiListTrue.get(i));
//                    }
            }
            sendBlackListRun(sendListBlack);
        } else {
            ToastUtils.showToast("?????????IMSI??????");
            return;
        }
    }

    //???????????????IMSI
    private void sendBlackListsp2() {
        Log.d(TAG, "sendBlackList:?????? " + spinnerS1);
        sendListBlack2 = new ArrayList<>();

        if (listImsiListTrue.size() > 0 && listImsiListTrue != null) {
            for (int i = 0; i < listImsiListTrue.size(); i++) {
//                    if (listImsiListTrue.get(i).getYy().equals(yy)) {
                sendListBlack2.add(listImsiListTrue.get(i));
//                    }
            }
            sendBlackListRun2(sendListBlack2);
        } else {
            ToastUtils.showToast("?????????IMSI??????");
            return;
        }
    }

    /**
     * ??????????????????
     *
     * @param i ????????????
     */
    private void zy(final int i) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String ip = "";
                if (i == 1) {
                    ip = IP1;
                }
                if (i == 2) {
                    ip = IP2;

                }
                DatagramSocket socket = null;
                InetAddress address = null;//????????????ip??????
                Log.e("znzq", "run: nzqsend");
                byte[] outputData = MainUtilsThread.hexStringToByteArray(ZYCHAXYN);
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
                Log.e("startLocation1s", "run: sendsocket?????????" + outputPacket.getPort() + "Ip??????:" + outputPacket.getAddress().toString() + "??????:" + outputPacket.getData());
                try {
                    //                                    socket.send(outputPacket);
                    DS.send(outputPacket);
//                    interrupted();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void initTTS() {
        //???????????????????????????
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == textToSpeech.SUCCESS) {
                    // Toast.makeText(MainActivity.this,"??????????????????",
                    // Toast.LENGTH_SHORT).show();
                    // Locale loc1=new Locale("us");
                    // Locale loc2=new Locale("china");

                    textToSpeech.setPitch(0.5f);//????????????????????????
                    textToSpeech.setSpeechRate(0.01f);//??????????????????

                    //????????????????????????????????????
                    int result1 = textToSpeech.setLanguage(Locale.US);
                    int result2 = textToSpeech.setLanguage(Locale.
                            SIMPLIFIED_CHINESE);
                    boolean a = (result1 == TextToSpeech.LANG_MISSING_DATA || result1 == TextToSpeech.LANG_NOT_SUPPORTED);
                    boolean b = (result2 == TextToSpeech.LANG_MISSING_DATA || result2 == TextToSpeech.LANG_NOT_SUPPORTED);

//                    Log.i("zhh_tts", "US????????????--???" + a +
//                            "\nzh-CN????????????--???" + b);
                } else {
//                    MyToast.showToast("????????????????????????");
//                    Toast.makeText(MainActivity.this, "????????????????????????", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void startAuto(String data, boolean b) {
        // ???????????????????????????????????????????????????????????????????????????,1.0?????????

        textToSpeech.setPitch(1.f);
        Log.d("wpnqq", "startAuto: " + b);

        // ????????????
        textToSpeech.setSpeechRate(8.01f);
        textToSpeech.speak(data,//??????????????????????????????????????????????????????
                TextToSpeech.QUEUE_FLUSH, null);
    }

    /**
     * ????????????imsi?????????
     *
     * @param msg
     */
    private void sbImsiType(Message msg) {
        String imsi = msg.getData().getString("imsi");
        String sb = msg.getData().getString("sb");
        String zb = msg.getData().getString("zb");
        String datetime = msg.getData().getString("datetime");
        String time = msg.getData().getString("time");
        Log.d(TAG, "handl?????????: " + imsi + "---" + sb + "zb--" + "datatime--" + datetime + "time--" + time);
        States states = new States();
        states.setImsi(imsi);
        states.setSb(sb);
        states.setZb(zb + "");
        states.setDatatime(datetime);
        states.setTime(time);
        listStates.add(states);

        Log.d("statessbImsiType", "statessbImsiType: " + states.toString());
        //?????????????????????
        if (timer == null) {
            timer = new Timer();
            //schedule??????????????????????????????????????????
            timer.schedule(new TimerTask() {

                //run?????????????????????????????????????????????
                @Override
                public void run() {

                    Message message = new Message();
                    handler.sendMessage(message);
                    message.what = 300001;
                    Log.d(TAG, "handlerrun: " + 1);
                    Log.d(TAG, "handlerrun: " + 1);
                }
            }, 0, 11000);//IMSI
        } else {
            Log.d(TAG, "ahandlerrun: " + 1);

//            timer.schedule(new TimerTask() {
//
//                //run?????????????????????????????????????????????
//                @Override
//                public void run() {
//                    Message message = new Message();
//                    handler.sendMessage(message);
//                    message.what = 300001;
//                    Log.d(TAG, "handlerrun: " + 1);
//                }
//            }, 0, 4000);
        }
    }

    //????????????1
    private void EstablishVillage() {
        new Thread(new Runnable() {
            @SuppressLint("LongLogTag")
            @Override
            public void run() {
                List<PinConfigBean> pinConfigBeans = null;
                try {
                    pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(spinnerS1)); //?????????????????????

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Conmmunit01Bean forid = null;
                try {
                    forid = dbManager01.forid(1);  //????????????1

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (pinConfigBeans == null) {
//                    ToastUtils.showToast("??????????????????");
                    Set1StatusBar("??????????????????");
                }
                if (forid == null) {
//                    ToastUtils.showToast("??????1????????????");
                    Set1StatusBar("??????1????????????");
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
                Log.d(TAG, "run: " + s);
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
                long l = System.currentTimeMillis();
                xiaoquFlag1 = true;
                xiaoquFlags1 = true;
//                while (xiaoquFlag1) {
//                    long l1 = System.currentTimeMillis();
//                    if (xiaoquFlags1 = true) {
//                        if (l1 - l > 8000) {
//                            xiaoquFlag1 = false;
//                            Message message = new Message();
//                            bundle.putString("zt1", "1");
//                            message.setData(bundle);
//                            handler.sendMessage(message);
//                            message.what = 100146;
//
//                        } else if (xiaoquFlags1 == false) {
//                            xiaoquFlag1 = false;
//                        }
//                    }
//
//                }
            }
        }).start();
    }

    //????????????1
    private void EstablishVillageSS(final String sp1MAX1value) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<PinConfigBean> pinConfigBeans = null;
                try {
                    pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(sp1MAX1value)); //?????????????????????

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Conmmunit01Bean forid = null;
                try {
                    forid = dbManager01.forid(1);  //????????????1

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (forid == null) {
                    ToastUtils.showToast("??????1????????????");
                    Set1StatusBar("??????1????????????");
                    return;
                }
                if (TextUtils.isEmpty(forid.getTac())) {
                    ToastUtils.showToast("??????1????????????TAC?????????");
                    return;
                }
                if (TextUtils.isEmpty(forid.getCid())) {
                    ToastUtils.showToast("??????1????????????CID?????????");
                    return;
                }
                if (TextUtils.isEmpty(forid.getPci())) {
                    ToastUtils.showToast("??????1????????????pci?????????");
                    return;
                }
//
                DBManagerPinConfig dbManagerA = null;
                try {
                    dbManagerA = new DBManagerPinConfig(MainActivity.this);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                DatagramSocket socket = null;
                InetAddress address = null;//????????????ip??????
                Log.e("znzq", "run: nzqsend");

//               Log.d(TAG, "run: " + s);
                byte[] outputData = null;
                String plmn = "";
                if (bts_start1.getText().equals(TITLEZDZM)) {//???????????????
                    plmn = spBean1.getPlmn();
                    DOWNPIN1 = spBean1.getDown();//????????????????????????imsi ???????????????
                } else {
                    plmn = MyUtils.PLMN(SAOPIN);
                }


                String band = MainUtils.getBand(Integer.parseInt(spBean1.getDown()));
                Log.d("aaaplmn", "run: " + plmn + "band" + band + "SAOPIN===" + SAOPIN);
                if (spBean1.getUp().equals("255")) {
                    outputData = MainUtilsThread.hexStringToByteArray(setxq.setXq(
                            Integer.parseInt(spBean1.getUp()),
                            Integer.parseInt(spBean1.getDown()),
                            plmn,
                            Integer.parseInt(band),
                            Integer.parseInt(forid.getPci()),
                            Integer.parseInt(forid.getTac()),
                            Integer.parseInt(forid.getCid())));
                    Log.d("nzq255", "run: ");
                    if (spBean1.getDown() != null) {
                        int i1 = Integer.parseInt(spBean1.getDown());
//                    aaaaaaaaaaaaaaa
                        try {
                            String s = "";
                            List<PinConfigBean> pinConfigBeanList = dbManagerA.getStudent();
                            boolean contains = pinConfigBeanList.contains(spBean1.getDown());
                            Log.d(TAG, "containsrun: " + contains);
                            Log.d(TAG, "containsrunrun:logas " + pinConfigBeanList.toString() + "\n" + spBean1.getDown());
                            if (pinConfigBeanList != null && pinConfigBeanList.size() > 0) {
                                for (int i = 0; i < pinConfigBeanList.size(); i++) {
                                    if (spBean1.getDown().equals(pinConfigBeanList.get(i).getDown() + "")) {
                                        s = "1";
                                        Log.d(TAG, "scontainsrunrun:logas " + s);
                                    }
                                }
                                if (TextUtils.isEmpty(s)) {
                                    PinConfigBean pinConfigBean = new PinConfigBean();
                                    pinConfigBean.setBand(Integer.parseInt(band));
                                    pinConfigBean.setDown(Integer.parseInt(spBean1.getDown()));
                                    pinConfigBean.setPlmn(plmn);
                                    pinConfigBean.setType(0);
                                    pinConfigBean.setUp(Integer.parseInt(spBean1.getUp()));
                                    pinConfigBean.setTf(tf1);
                                    String YY = MyUtils.YYname(SAOPIN);
                                    pinConfigBean.setYy(YY);

                                    try {
                                        int i = dbManagerA.insertStudent(pinConfigBean);
                                        if (i == 1) {
//                Toast.makeText(this, "????????????", Toast.LENGTH_SHORT).show();
                                            try {
                                                dbManagerPinConfig = new DBManagerPinConfig(MainActivity.this);
                                                pinConfigBeans = dbManagerPinConfig.getStudent();
                                                listsSp.clear();
                                                listsSp.add("");
                                                if (pinConfigBeans.size() > 0) {
                                                    for (int j = 0; j < pinConfigBeans.size(); j++) {
                                                        listsSp.add(pinConfigBeans.get(j).getDown() + "");
                                                    }
                                                    if (listsSp.size() > 0) {

                                                        for (int j = 0; j < listsSp.size(); j++) {
                                                            if (listsSp.get(j).equals(i1)) {
                                                                mysp1.setSelection(j);
                                                                sb1pd = true;
                                                            }
                                                        }
//                                                    SpinnersSetData(1);//????????????spinner???????????????
                                                        message = new Message();
                                                        Bundle bundle = new Bundle();
                                                        bundle.putString("SpinnersSetData", "1");
                                                        message.setData(bundle);
                                                        handler.sendMessage(message);
                                                        message.what = 8154;
                                                    } else {
                                                        ToastUtils.showToast("???????????????");
                                                    }
                                                } else {
                                                    ToastUtils.showToast("???????????????");
                                                }
                                                Log.d(TAG, "pinConfigBeansinit: " + pinConfigBeans);
                                            } catch (SQLException e) {
                                                e.printStackTrace();
                                            }
                                            //??????????????????
                                        } else {
//                Toast.makeText(this, "????????????", Toast.LENGTH_SHORT).show();

                                        }
//
                                    } catch (SQLException e) {

                                        e.printStackTrace();
                                    }
                                } else {
                                    try {
                                        dbManagerPinConfig = new DBManagerPinConfig(MainActivity.this);
                                        pinConfigBeans = dbManagerPinConfig.getStudent();
                                        listsSp.clear();
                                        listsSp.add("");
                                        if (pinConfigBeans.size() > 0) {
                                            for (int j = 0; j < pinConfigBeans.size(); j++) {
                                                listsSp.add(pinConfigBeans.get(j).getDown() + "");
                                            }
                                            if (listsSp.size() > 0) {

                                                for (int j = 0; j < listsSp.size(); j++) {
                                                    if (listsSp.get(j).equals(i1)) {
                                                        mysp1.setSelection(j);
                                                        sb1pd = true;
                                                    }
                                                }
//                                            SpinnersSetData(1);//????????????spinner???????????????
//

                                                message = new Message();
                                                Bundle bundle = new Bundle();
                                                bundle.putString("SpinnersSetData", "1");
                                                message.setData(bundle);
                                                handler.sendMessage(message);
                                                message.what = 8154;
                                            } else {
                                                ToastUtils.showToast("???????????????");
                                            }
                                        } else {
                                            ToastUtils.showToast("???????????????");
                                        }
                                        Log.d(TAG, "pinConfigBeansinit: " + pinConfigBeans);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    Log.d("7nzqspBean1255-", "run: " + spBean1.toString());
                    outputData = MainUtilsThread.hexStringToByteArray(setxq.setXq(
                            Integer.parseInt(spBean1.getUp()),
                            Integer.parseInt(spBean1.getDown()),
                            plmn,
                            Integer.parseInt(band),
                            Integer.parseInt(forid.getPci()),
                            Integer.parseInt(forid.getTac()),
                            Integer.parseInt(forid.getCid())));
                    Log.d(TAG, "run: ");
                    try {
                        String s = "";
                        int i1 = Integer.parseInt(spBean1.getDown());
                        List<PinConfigBean> pinConfigBeanList = dbManagerA.getStudent();
                        if (pinConfigBeanList != null && pinConfigBeanList.size() > 0) {
                            for (int i = 0; i < pinConfigBeanList.size(); i++) {
                                if (spBean1.getDown().equals(pinConfigBeanList.get(i).getDown() + "")) {
                                    s = "1";
                                }
                            }
                            if (TextUtils.isEmpty(s)) {
                                PinConfigBean pinConfigBean = new PinConfigBean();
                                pinConfigBean.setBand(Integer.parseInt(band));
                                pinConfigBean.setDown(Integer.parseInt(spBean1.getDown()));
                                pinConfigBean.setPlmn(plmn);
                                pinConfigBean.setType(0);
                                pinConfigBean.setUp(Integer.parseInt(spBean1.getUp()));
                                pinConfigBean.setTf(tf1);
                                String YY = MyUtils.YYname(SAOPIN);
                                pinConfigBean.setYy(YY);

                                try {
                                    int i = dbManagerA.insertStudent(pinConfigBean);
                                    if (i == 1) {
//                Toast.makeText(this, "????????????", Toast.LENGTH_SHORT).show();
                                        try {
                                            dbManagerPinConfig = new DBManagerPinConfig(MainActivity.this);
                                            pinConfigBeans = dbManagerPinConfig.getStudent();
                                            listsSp.clear();
                                            listsSp.add("");
                                            if (pinConfigBeans.size() > 0) {
                                                for (int j = 0; j < pinConfigBeans.size(); j++) {
                                                    listsSp.add(pinConfigBeans.get(j).getDown() + "");
                                                }
                                                if (listsSp.size() > 0) {

                                                    for (int j = 0; j < listsSp.size(); j++) {
                                                        if (listsSp.get(j).equals(i1)) {
                                                            mysp1.setSelection(j);
                                                            sb1pd = true;
                                                        }
                                                    }
//                                                    SpinnersSetData(1);//????????????spinner???????????????
                                                    message = new Message();
                                                    Bundle bundle = new Bundle();
                                                    bundle.putString("SpinnersSetData", "1");
                                                    message.setData(bundle);
                                                    handler.sendMessage(message);
                                                    message.what = 8154;
                                                } else {
                                                    ToastUtils.showToast("???????????????");
                                                }
                                            } else {
                                                ToastUtils.showToast("???????????????");
                                            }
                                            Log.d(TAG, "pinConfigBeansinit: " + pinConfigBeans);
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                        //??????????????????
                                    } else {
//                Toast.makeText(this, "????????????", Toast.LENGTH_SHORT).show();

                                    }
//
                                } catch (SQLException e) {

                                    e.printStackTrace();
                                }
                            } else {
                                try {
                                    dbManagerPinConfig = new DBManagerPinConfig(MainActivity.this);
                                    pinConfigBeans = dbManagerPinConfig.getStudent();
                                    listsSp.clear();
                                    listsSp.add("");
                                    if (pinConfigBeans.size() > 0) {
                                        for (int j = 0; j < pinConfigBeans.size(); j++) {
                                            listsSp.add(pinConfigBeans.get(j).getDown() + "");
                                        }
                                        if (listsSp.size() > 0) {

                                            for (int j = 0; j < listsSp.size(); j++) {
                                                if (listsSp.get(j).equals(i1)) {
                                                    mysp1.setSelection(j);
                                                    sb1pd = true;
                                                }
                                            }
//                                            SpinnersSetData(1);//????????????spinner???????????????
                                            message = new Message();
                                            Bundle bundle = new Bundle();
                                            bundle.putString("SpinnersSetData", "1");
                                            message.setData(bundle);
                                            handler.sendMessage(message);
                                            message.what = 8154;
                                        } else {
                                            ToastUtils.showToast("???????????????");
                                        }
                                    } else {
                                        ToastUtils.showToast("???????????????");
                                    }
                                    Log.d(TAG, "pinConfigBeansinit: " + pinConfigBeans);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    socket = new DatagramSocket();
                    address = InetAddress.getByName(IP1);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (SocketException e) {
                    e.printStackTrace();
                }
                ;
                Log.d("outputDataoutputDatas", "run: " + outputData.toString());
                DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, address, 3345);
                Log.e("startLocation1s", "run: sendsocket?????????" + outputPacket.getPort() + "Ip??????:" + outputPacket.getAddress().toString() + "??????:" + outputPacket.getData());

                try {
                    if (sb1.equals("?????????")) {

                    } else {
                        //                                    socket.send(outputPacket);
                        DS.send(outputPacket);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                long l = System.currentTimeMillis();
                xiaoquFlag1 = true;
                xiaoquFlags1 = true;
            }
        }).start();


    }

    //????????????2
    private void EstablishVillageSS2(final String sp2MAX1value) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<PinConfigBean> pinConfigBeans = null;
                try {
                    pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(sp2MAX1value)); //?????????????????????

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Conmmunit01Bean forid = null;
                DBManagerPinConfig dbManagerA = null;
                try {
                    dbManagerA = new DBManagerPinConfig(MainActivity.this);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    forid = dbManager01.forid(2);  //????????????2

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (forid == null) {
                    ToastUtils.showToast("??????2????????????");
                    Set1StatusBar("??????1????????????");
                    return;
                }

                if (TextUtils.isEmpty(forid.getTac())) {
                    ToastUtils.showToast("??????2????????????TAC?????????");
                    return;
                }
                if (TextUtils.isEmpty(forid.getCid())) {
                    ToastUtils.showToast("??????2????????????CID?????????");
                    return;
                }
                if (TextUtils.isEmpty(forid.getPci())) {
                    ToastUtils.showToast("??????2????????????pci?????????");
                    return;
                }
                DatagramSocket socket = null;
                InetAddress address = null;//????????????ip??????
                Log.e("znzq", "run: nzqsend");
//                String plmn = MyUtils.PLMN(SAOPIN2);

                String plmn = "";
                if (bts_start2.getText().equals(TITLEZDZM)) {//???????????????
                    plmn = spBean154.getPlmn();
                    DOWNPIN2 = spBean154.getDown();
                } else {
                    plmn = MyUtils.PLMN(SAOPIN2);
                }
                String band = MainUtils.getBand(Integer.parseInt(spBean154.getDown()));
                byte[] outputData = null;
                if (spBean154.getUp().equals("255")) {//?????????TDD
                    outputData = MainUtilsThread.hexStringToByteArray(setxq.setXq(
                            Integer.parseInt(spBean154.getUp()),
                            Integer.parseInt(spBean154.getDown()),
                            plmn,
                            Integer.parseInt(band),
                            Integer.parseInt(forid.getPci()),
                            Integer.parseInt(forid.getTac()),
                            Integer.parseInt(forid.getCid())));

                    Log.d("nzq255", "run: ");

                    try {
                        String s = "";
                        List<PinConfigBean> pinConfigBeanList = dbManagerA.getStudent();
                        if (spBean1.getDown() != null) {
                            int i1 = Integer.parseInt(spBean1.getDown());
                            if (pinConfigBeanList != null && pinConfigBeanList.size() > 0) {
                                for (int i = 0; i < pinConfigBeanList.size(); i++) {
                                    if (spBean154.getDown().equals(pinConfigBeanList.get(i).getDown() + "")) {
                                        s = "1";
                                    }
                                }
                                if (TextUtils.isEmpty(s)) {
                                    PinConfigBean pinConfigBean = new PinConfigBean();
                                    pinConfigBean.setBand(Integer.parseInt(band));
                                    pinConfigBean.setDown(Integer.parseInt(spBean154.getDown()));
                                    pinConfigBean.setPlmn(plmn);
                                    pinConfigBean.setType(0);
                                    pinConfigBean.setUp(Integer.parseInt(spBean154.getUp()));
                                    pinConfigBean.setTf(tf2);
                                    String YY = MyUtils.YYname(SAOPIN2);
                                    pinConfigBean.setYy(YY);

                                    try {
                                        int i = dbManagerA.insertStudent(pinConfigBean);
                                        if (i == 1) {
//                Toast.makeText(this, "????????????", Toast.LENGTH_SHORT).show();
                                            try {
                                                dbManagerPinConfig = new DBManagerPinConfig(MainActivity.this);
                                                pinConfigBeans = dbManagerPinConfig.getStudent();
                                                listsSp.clear();
                                                listsSp.add("");
                                                if (pinConfigBeans.size() > 0) {
                                                    for (int j = 0; j < pinConfigBeans.size(); j++) {
                                                        listsSp.add(pinConfigBeans.get(j).getDown() + "");
                                                    }
                                                    if (listsSp.size() > 0) {

                                                        for (int j = 0; j < listsSp.size(); j++) {
                                                            if (listsSp.get(j).equals(i1)) {
                                                                mysp2.setSelection(j);
                                                                sb2pd = true;
                                                            }
                                                        }
//                                                    SpinnersSetData(2);//????????????spinner???????????????
                                                        message = new Message();
                                                        Bundle bundle = new Bundle();
                                                        bundle.putString("SpinnersSetData", "2");
                                                        message.setData(bundle);
                                                        handler.sendMessage(message);
                                                        message.what = 8154;
                                                    } else {
                                                        ToastUtils.showToast("???????????????");
                                                    }
                                                } else {
                                                    ToastUtils.showToast("???????????????");
                                                }
                                                Log.d(TAG, "pinConfigBeansinit: " + pinConfigBeans);
                                            } catch (SQLException e) {
                                                e.printStackTrace();
                                            }
                                            //??????????????????
                                        } else {
//                Toast.makeText(this, "????????????", Toast.LENGTH_SHORT).show();

                                        }
//
                                    } catch (SQLException e) {

                                        e.printStackTrace();
                                    }
                                } else {
                                    try {
                                        dbManagerPinConfig = new DBManagerPinConfig(MainActivity.this);
                                        pinConfigBeans = dbManagerPinConfig.getStudent();
                                        listsSp.clear();
                                        listsSp.add("");
                                        if (pinConfigBeans.size() > 0) {
                                            for (int j = 0; j < pinConfigBeans.size(); j++) {
                                                listsSp.add(pinConfigBeans.get(j).getDown() + "");
                                            }
                                            if (listsSp.size() > 0) {

                                                for (int j = 0; j < listsSp.size(); j++) {
                                                    if (listsSp.get(j).equals(i1)) {
                                                        mysp2.setSelection(j);
                                                        sb2pd = true;
                                                    }
                                                }
//                                            SpinnersSetData(2);//????????????spinner???????????????
                                                message = new Message();
                                                Bundle bundle = new Bundle();
                                                bundle.putString("SpinnersSetData", "2");
                                                message.setData(bundle);
                                                handler.sendMessage(message);
                                                message.what = 8154;
                                            } else {
                                                ToastUtils.showToast("???????????????");
                                            }
                                        } else {
                                            ToastUtils.showToast("???????????????");
                                        }
                                        Log.d(TAG, "pinConfigBeansinit: " + pinConfigBeans);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        } else {

                        }


                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {//?????????FDD
                    Log.d("nzq255-", "run: ");
                    outputData = MainUtilsThread.hexStringToByteArray(setxq.setXq(
                            Integer.parseInt(spBean154.getUp()),
                            Integer.parseInt(spBean154.getDown()),
                            plmn,
                            Integer.parseInt(band),
                            Integer.parseInt(forid.getPci()),
                            Integer.parseInt(forid.getTac()),
                            Integer.parseInt(forid.getCid())));
                    try {
                        String s = "";
                        List<PinConfigBean> pinConfigBeanList = dbManagerA.getStudent();
                        int i1 = Integer.parseInt(spBean154.getDown());
                        if (pinConfigBeanList != null && pinConfigBeanList.size() > 0) {
                            for (int i = 0; i < pinConfigBeanList.size(); i++) {
                                if (spBean154.getDown().equals(pinConfigBeanList.get(i).getDown() + "")) {
                                    s = "1";
                                }
                            }
                            if (TextUtils.isEmpty(s)) {
                                PinConfigBean pinConfigBean = new PinConfigBean();
                                pinConfigBean.setBand(Integer.parseInt(band));
                                pinConfigBean.setDown(Integer.parseInt(spBean154.getDown()));
                                pinConfigBean.setPlmn(plmn);
                                pinConfigBean.setType(0);
                                pinConfigBean.setUp(Integer.parseInt(spBean154.getUp()));
                                pinConfigBean.setTf(tf2);
                                String YY = MyUtils.YYname(SAOPIN2);
                                pinConfigBean.setYy(YY);

                                try {
                                    int i = dbManagerA.insertStudent(pinConfigBean);
                                    if (i == 1) {
//                Toast.makeText(this, "????????????", Toast.LENGTH_SHORT).show();
                                        try {
                                            dbManagerPinConfig = new DBManagerPinConfig(MainActivity.this);
                                            pinConfigBeans = dbManagerPinConfig.getStudent();
                                            listsSp.clear();
                                            listsSp.add("");
                                            if (pinConfigBeans.size() > 0) {
                                                for (int j = 0; j < pinConfigBeans.size(); j++) {
                                                    listsSp.add(pinConfigBeans.get(j).getDown() + "");
                                                }
                                                if (listsSp.size() > 0) {

                                                    for (int j = 0; j < listsSp.size(); j++) {
                                                        if (listsSp.get(j).equals(i1)) {
                                                            mysp2.setSelection(j);
                                                            sb2pd = true;
                                                        }
                                                    }
//                                                    SpinnersSetData(2);//????????????spinner???????????????


                                                    message = new Message();
                                                    Bundle bundle = new Bundle();
                                                    bundle.putString("SpinnersSetData", "2");
                                                    message.setData(bundle);
                                                    handler.sendMessage(message);
                                                    message.what = 8154;
//                                                    message = new Message();
//                                                    bundle.putString("SpinnersSetData", "1");//????????????
//                                                    message.setData(bundle);
//                                                    handler.sendMessage(message);
//                                                    message.what = 200151;
                                                } else {
                                                    ToastUtils.showToast("???????????????");
                                                }
                                            } else {
                                                ToastUtils.showToast("???????????????");
                                            }
                                            Log.d(TAG, "pinConfigBeansinit: " + pinConfigBeans);
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                        //??????????????????
                                    } else {
//                Toast.makeText(this, "????????????", Toast.LENGTH_SHORT).show();

                                    }
//
                                } catch (SQLException e) {

                                    e.printStackTrace();
                                }
                            } else {
                                try {
                                    dbManagerPinConfig = new DBManagerPinConfig(MainActivity.this);
                                    pinConfigBeans = dbManagerPinConfig.getStudent();
                                    listsSp.clear();
                                    listsSp.add("");
                                    if (pinConfigBeans.size() > 0) {
                                        for (int j = 0; j < pinConfigBeans.size(); j++) {
                                            listsSp.add(pinConfigBeans.get(j).getDown() + "");
                                        }
                                        if (listsSp.size() > 0) {

                                            for (int j = 0; j < listsSp.size(); j++) {
                                                if (listsSp.get(j).equals(i1)) {
                                                    mysp2.setSelection(j);
                                                    sb2pd = true;
                                                }
                                            }
//                                            SpinnersSetData(2);//????????????spinner???????????????
                                            message = new Message();
                                            Bundle bundle = new Bundle();
                                            bundle.putString("SpinnersSetData", "2");
                                            message.setData(bundle);
                                            handler.sendMessage(message);
                                            message.what = 8154;
                                        } else {
                                            ToastUtils.showToast("???????????????");
                                        }
                                    } else {
                                        ToastUtils.showToast("???????????????");
                                    }
                                    Log.d(TAG, "pinConfigBeansinit: " + pinConfigBeans);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
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
                Log.e("startLocation1s", "run: sendsocket?????????" + outputPacket.getPort() + "Ip??????:" + outputPacket.getAddress().toString() + "??????:" + outputPacket.getData());

                try {
                    if (sb2.equals("?????????")) {

                    } else {
                        //                                    socket.send(outputPacket);
                        DS.send(outputPacket);
                    }
//                    socket.send(outputPacket);
//                    interrupted();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                long l = System.currentTimeMillis();
                xiaoquFlag2 = true;
                xiaoquFlags2 = true;
//                while (xiaoquFlag2) {
//                    long l1 = System.currentTimeMillis();
//                    if (xiaoquFlags2 = true) {
//                        if (l1 - l > 5000) {
//                            xiaoquFlag2 = false;
//                            Message message = new Message();
//                            bundle.putString("zt1", "1");
//                            message.setData(bundle);
//                            handler.sendMessage(message);
//                            message.what = 200146;
//
//                        } else if (xiaoquFlags2 == false) {
//                            xiaoquFlag2 = false;
//                        }
//                    }
//
//                }

            }
        }).start();


    }

    //????????????2
    private void EstablishVillage2() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<PinConfigBean> pinConfigBeans = null;
                try {
                    if (!TextUtils.isEmpty(spinnerS2)) {
                        pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(spinnerS2)); //?????????????????????
                    } else {
                        return;
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Conmmunit01Bean forid = null;
                try {
                    forid = dbManager01.forid(2);  //????????????2

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (pinConfigBeans == null) {
//                    ToastUtils.showToast("??????????????????");
                    Set2StatusBar("??????????????????");
                }
                if (forid == null) {
//                    ToastUtils.showToast("??????2????????????");
                    Set2StatusBar("??????2????????????");
                    return;
                }
                DatagramSocket socket = null;
                InetAddress address = null;//????????????ip??????
                Log.e("foridgBeansznzq", "run: nzqsend" + forid.toString());

//        int ulEarfcn,int dlEarfcn,String PLMN, int band,int PCI,int TAC
                byte[] outputData = MainUtilsThread.hexStringToByteArray(setxq.setXq(
                        pinConfigBeans.get(0).getUp(),
                        pinConfigBeans.get(0).getDown(),
                        pinConfigBeans.get(0).getPlmn(),
                        pinConfigBeans.get(0).getBand(),
                        Integer.parseInt(forid.getPci()),
                        Integer.parseInt(forid.getTac()), Integer.parseInt(forid.getCid())));
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
//                        DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, reIP, Integer.parseInt(et_port.getText().toString()));
                Log.e("startLocation1s", "run: sendsocket?????????" + outputPacket.getPort() + "Ip??????:" + outputPacket.getAddress().toString() + "??????:" + outputPacket.getData());

                try {
                    if (sb2.equals("?????????")) {

                    } else {
                        //                                    socket.send(outputPacket);
                        DS.send(outputPacket);
                    }
//                    socket.send(outputPacket);
//                    interrupted();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                long l = System.currentTimeMillis();
                xiaoquFlag2 = true;
                xiaoquFlags2 = true;
//                while (xiaoquFlag2) {
//                    long l1 = System.currentTimeMillis();
//                    if (xiaoquFlags2 = true) {
//                        if (l1 - l > 5000) {
//                            xiaoquFlag2 = false;
//                            Message message = new Message();
//                            bundle.putString("zt1", "1");
//                            message.setData(bundle);
//                            handler.sendMessage(message);
//                            message.what = 200146;
//
//                        } else if (xiaoquFlags2 == false) {
//                            xiaoquFlag2 = false;
//                        }
//                    }
//
//                }

            }
        }).start();
    }

    private String sb1pdStr = "";      //????????????
    private String sb2pdStr = "";     //????????????
    private boolean sb1pd = false;   //???????????????????????????????????????
    private boolean sb2pd = false;   //???????????????????????????????????????
    private boolean sb1zy1 = false;//???????????????????????????????????????
    private boolean sb1zy2 = false;//???????????????????????????????????????
    private boolean sb1locationgFlag = false;
    private boolean sb2locationgFlag = false;
    private boolean sb1FirstFlag = false;
    private boolean sb2FirstFlag = false;
    private TextToSpeech textToSpeech = null;//????????????????????????
    private boolean ClearFlag1 = true;//??????
    private boolean ClearFlags1 = true;//????????????
    private boolean BlackFlag1 = true;//?????????
    private boolean BlackFlags1 = true;//???????????????
    private boolean LocationFlag1 = true;//??????
    private boolean LocationFlags1 = true;//????????????

    private boolean ClearFlag2 = true;//??????
    private boolean ClearFlags2 = true;//????????????
    private boolean BlackFlag2 = true;//?????????
    private boolean BlackFlags2 = true;//???????????????
    private boolean LocationFlag2 = true;//??????
    private boolean LocationFlags2 = true;//????????????
    //    public static boolean GFFLAG1 = true;
//    public static boolean GFFLAGS1 = true;
    private boolean xiaoquFlag1 = true;//??????

    private boolean xiaoquFlags1 = true;//????????????
    private boolean xiaoquFlag2 = true;//??????

    private boolean xiaoquFlags2 = true;//????????????

    private void sendBlackList() {//?????????????????????  1.????????????????????? imsi
        Log.d(TAG, "sendBlackList:?????? " + spinnerS1);
        sendListBlack = new ArrayList<>();
        if (!TextUtils.isEmpty(spinnerS1)) {
//            String sb1zhishi = "";
            String yy = "";
            try {
                pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(spinnerS1)); //?????????????????????
                yy = pinConfigBeans.get(0).getYy();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "sendBlackList:?????? ");
            if (listImsiListTrue.size() > 0 && listImsiListTrue != null) {
                for (int i = 0; i < listImsiListTrue.size(); i++) {
                    if (listImsiListTrue.get(i).getYy().equals(yy)) {
                        sendListBlack.add(listImsiListTrue.get(i));
                    }
                }
            }

            if (sendListBlack.size() > 0 && sendListBlack != null) {
                Log.d(TAG, "sendBlackList: " + sendListBlack);
                if (sendListBlack.size() > 20) {
                    ToastUtils.showToast("????????????????????????????????????20");
                } else {
                    sendBlackListRun(sendListBlack);
                }
//
            } else {
                CALLBLACKFLAGSET1 = false;
                ToastUtils.showToast("?????????????????????IMSI");
            }

        } else {
            ToastUtils.showToast("????????????????????????");
        }

    }

    private void sendBlackListinit() {//?????????????????????  1.????????????????????? imsi
        Log.d(TAG, "sendBlackList:?????? " + spinnerS1);
        sendListBlack = new ArrayList<>();
        if (!TextUtils.isEmpty(spinnerS1)) {
//            String sb1zhishi = "";
            String yy = "";
            try {
                pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(spinnerS1)); //?????????????????????
                yy = pinConfigBeans.get(0).getYy();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "sendBlackList:?????? ");
            if (listImsiListTrue.size() > 0 && listImsiListTrue != null) {
                for (int i = 0; i < listImsiListTrue.size(); i++) {
                    if (listImsiListTrue.get(i).getYy().equals(yy)) {
                        sendListBlack.add(listImsiListTrue.get(i));
                    }
                }
            }

            if (sendListBlack.size() > 0 && sendListBlack != null) {
                Log.d(TAG, "sendBlackList: " + sendListBlack);
                if (sendListBlack.size() > 20) {
                    ToastUtils.showToast("????????????????????????????????????20");
                } else {
                    sendBlackListRun(sendListBlack);
                }
//
            } else {

                ToastUtils.showToast("?????????????????????IMSI");
            }

        } else {
            ToastUtils.showToast("????????????????????????");
        }

    }

    //
    private void sendBlackList2() {//?????????????????????  1.????????????????????? imsi
        Log.d(TAG, "sendBlackList:?????? " + spinnerS2);
        sendListBlack2 = new ArrayList<>();
        if (!TextUtils.isEmpty(spinnerS2)) {
//            String sb1zhishi = "";
            String yy = "";
            try {
                pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(spinnerS2)); //?????????????????????
                yy = pinConfigBeans.get(0).getYy();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "sendBlackList:pinConfigBeans?????? " + pinConfigBeans);
            if (listImsiListTrue.size() > 0 && listImsiListTrue != null) {
                for (int i = 0; i < listImsiListTrue.size(); i++) {
                    if (listImsiListTrue.get(i).getYy().equals(yy)) {
                        sendListBlack2.add(listImsiListTrue.get(i));
                    }
                }
            }
            if (sendListBlack2.size() > 0 && sendListBlack2 != null) {
                Log.d(TAG, "sendBlackList: " + sendListBlack2);
                if (sendListBlack2.size() > 20) {
                    ToastUtils.showToast("????????????????????????????????????20");
                } else {
                    sendBlackListRun2(sendListBlack2);
                }

            } else {
                CALLBLACKFLAGSET2 = false;
                ToastUtils.showToast("?????????????????????IMSI");
            }
        } else {
            ToastUtils.showToast("????????????????????????");
        }

    }

    private void sendBlackList2init() {//?????????????????????  1.????????????????????? imsi
        Log.d(TAG, "sendBlackList:?????? " + spinnerS2);
        sendListBlack2 = new ArrayList<>();
        if (!TextUtils.isEmpty(spinnerS2)) {
//            String sb1zhishi = "";
            String yy = "";
            try {
                pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(spinnerS2)); //?????????????????????
                yy = pinConfigBeans.get(0).getYy();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "sendBlackList:?????? ");
            if (listImsiListTrue.size() > 0 && listImsiListTrue != null) {
                for (int i = 0; i < listImsiListTrue.size(); i++) {
                    if (listImsiListTrue.get(i).getYy().equals(yy)) {
                        sendListBlack2.add(listImsiListTrue.get(i));
                    }
                }
            }
            if (sendListBlack2.size() > 0 && sendListBlack2 != null) {
                Log.d(TAG, "sendBlackList: " + sendListBlack2);
                if (sendListBlack2.size() > 20) {
                    ToastUtils.showToast("????????????????????????????????????20");
                } else {
                    sendBlackListRun2(sendListBlack2);
                }

            } else {
                ToastUtils.showToast("?????????????????????IMSI");
            }
        } else {
            ToastUtils.showToast("????????????????????????");
        }

    }

    List<States> listStates = new ArrayList<>();// ???????????????????????????

    //???????????????
    private void sendBlackListRun(List<AddPararBean> sendListBlack) {
        List<String> list = new ArrayList<>();
        Log.d(TAG, "sendBlacListRun:list" + list);
        for (int i = 0; i < sendListBlack.size(); i++) {
            list.add(sendListBlack.get(i).getImsi());
        }
        int totalMy = list.size();
        Log.d(TAG, "sendBlackListRun:totalMy" + totalMy);
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
        str.append("000000");
        if (!TextUtils.isEmpty(str)) {
            sendrun(str);//????????????
            Log.d(TAG, "sendBlackListRun:???????????? ");
        }
    }

    //???????????????2
    private void sendBlackListRun2(List<AddPararBean> sendListBlack2) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < sendListBlack2.size(); i++) {
            list.add(sendListBlack2.get(i).getImsi());
        }
        int totalMy = list.size();

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

        for (int i = 0; i < 20 - list2.size(); i++) {
            str.append("0000000000000000000000000000000000");
        }
        str.append("000000");
        if (!TextUtils.isEmpty(str)) {
            sendrun2(str);//????????????
            Log.d(TAG, "sendBlackListRun2:???????????? ");
        }
    }


    //??????1???????????????
    private void sendrun(final StringBuffer strData) {
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
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
                BlackFlag1 = true;
                BlackFlags1 = true;
                while (BlackFlag1) {
                    long ls = System.currentTimeMillis();
                    if (BlackFlags1 == true) {
                        if (ls - startTime1 > 5000) {
                            Log.d(TAG, "run:?????????1 ");
//                        interrupted();
                            Message message = new Message();
                            bundle.putString("zt1", "1");
                            message.setData(bundle);
                            handler.sendMessage(message);
//                            message.what = 100133;
                            BlackFlag1 = false;
//                        ToastUtils.showToast("?????????");
                        } else if (BlackFlags1 == false) {
                            BlackFlag1 = false;

                        }
                    }

                }

            }
        }).start();
    }

    //??????2???????????????
    private void sendrun2(final StringBuffer strData) {
//
        new Thread(new Runnable() {
            @Override
            public void run() {
                DatagramSocket socket = null;
                InetAddress address = null;//????????????ip??????
                Log.e("nzq", "run: nzqsend");
                byte[] outputData = MainUtilsThread.hexStringToByteArray(strData.toString());
                try {
                    socket = new DatagramSocket();
                    address = InetAddress.getByName(IP2);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
                BlackFlag2 = true;
                BlackFlags2 = true;
                while (BlackFlag2) {
                    long ls = System.currentTimeMillis();
                    if (BlackFlags2 == true) {
                        if (ls - startTime2 > 5000) {
                            Log.d(TAG, "run:?????????1 ");
//                        interrupted();
                            Message message = new Message();
                            bundle.putString("test", "");
                            message.setData(bundle);
                            handler.sendMessage(message);
                            message.what = 200133;
                            BlackFlag2 = false;
//                        ToastUtils.showToast("?????????");
                        } else if (BlackFlags2 == false) {
                            BlackFlag2 = false;
                        }
                    }

                }

            }
        }).start();
    }

    //    Runnable runnable1;//??????????????????????????????
    String imsi2 = "";
    String imsi1 = "";
    private String sb1types = "", sb2types = "";
    private Timer timer = null;//????????????imsi????????????
    private Timer timerLocation = null;//????????????imsi????????????
    private Timer timerRestart1 = null;//???????????? 1
    private Timer timerRestart2 = null;//???????????? 2
    private boolean timerRestartFlag1 = false;  //??????????????????1  ???????????????
    // ???????????????????????????
    private boolean timerRestartFlag2 = false;//??????????????????2  ???????????????????????????????????????????????? true ?????? false ?????????
    private StringBuffer stringBuffer1 = null;
    private StringBuffer stringBuffer2 = null;
    List<AddPararBean> sendListBlack = null;
    List<AddPararBean> sendListBlack2 = null;
    String tf1 = "";
    String tf2 = "";
    boolean sb1zhishiFlag = false;
    boolean sb2zhishiFlag = false;
    public String sb1 = "";
    public String sb2 = "";
    public String sb1type = "";//???????????????????????????????????????????????????,??????????????????????????????
    public String sb2type = "";
    public static long startTime1 = 0;
    public static long startTime2 = 0;
    private String TAG = MainActivity.ACTIVITY_SERVICE;
    private Context mContext = this;
    private DLPopupWindow popupWindow;
    List<AddPararBean> dataAll = null;//??????IMSI???????????????
    private List<DLPopItem> mList = new ArrayList<>();
    private View view;
    private boolean zdFlag = false;//???????????????
    private boolean zdSearchFlag = false;//???????????????

    private long timerDate = 1000 * 60 * 3;
    private static boolean WIFIflag = false;
    private int wifitrue = 0;
    private int wififalse = 0;
    private int ztint = 0;
    private boolean runThread = false;
    private String spinnerS1 = "", spinnerS2 = "";
    ArrayAdapter<String> adapter1, adapter2, adapter3;
    private DBManagerAddParam dbManagerAddParam = null;//imsi
    DBManager01 dbManager01 = null;//??????1??????

    DBManagerPinConfig dbManagerPinConfig = null;//????????????
    DBManagersaopin dbManagersaopin = null;
    private static Timer timer1, timer2, timer_wendu;
    Message message;
    Bundle bundle;
    //    int[] dataArr = null;//??????1???????????????
//    int[] dataArr2 = null;//??????2???????????????
    List<Integer> list1quxian = null;//??????1???????????????
    List<Integer> list2quxian = null;//??????2???????????????
    SeekBar seekbar1, seekbar2;
    TextView tv1_wifi, tv1_td, tv1_type, sb1_jl, sb1_jl_pj1, tv_imsi1, tv_zy1;//??????1 ?????????,??????, ?????? ,?????????,??????imsi ?????????
    TextView tv2_type, tv2_tf, tv2_wifi, sb1_j2, sb1_jl_pj2, tv_imsi2, tv_zy2;//??????2??? ?????? ??????, ?????? ,?????????,??????imsi ?????????
    ImageView laba2, laba1;
    Button bts_start2, bts_start1, btsstop1, btsstop2;
    private boolean labaFlag1 = true;
    private boolean labaFlag2 = true;
    Spinner mysp1, mysp2;
    Spinner typeSp1, typeSp2;
    RecyclerView ryIMSI, ry_zhenma;
    RyImsiAdapter ryImsiAdapter;
    RyZmAdapter ryZmAdapter;
    DatagramPacket dp;
    byte[] buf;
    private static String WIFINAME = "SMLOCATIONAP";
    List<PinConfigBean> pinConfigBeans = null;
    List<SaopinBean> saopinBeanList = null;
    List<String> listsSp = new ArrayList<>();
    List<AddPararBean> listImsiListTrue = null;//????????????????????????imsi
    List<AddPararBean> pararBeansList1 = new ArrayList<>();
    //id
    LinearLayout layout, ll_zhenma_search;
    TextView title;
    ImageView imageView;
    ImageView iv_findish;
    ImageButton ib_zhedie, ib_zhedie_zhenma;//????????????
    LineChartView lineChartView;
    ScrollView scrollView;//??????????????????????????????
    TextView textViews;//?????????1
    ScrollView scrollView2;//??????????????????????????????2
    TextView text2Views;//?????????2
    HandlerThread mBackThread;
    // ???????????????Handler
    Handler mBackHandler;

    @Override
    protected void initQx() {

    }

    @SuppressLint("MissingPermission")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void init() {
        try {//????????? ???????????????????????????
            dbManagerZM = new DBManagerZM(this);
            List<ZmBean> zmBeans = dbManagerZM.getDataAll();
            if (zmBeans.size() > 0) {
                dbManagerZM.deleteall();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        test1();


        SpinnersSet();
//        String str = "460002360893648";
//        boolean contains = str.contains("146000");
//        Log.d(TAG, "strinit: "+contains);
        mBackThread = new HandlerThread("mybackthread");
        mBackThread.start();
        // ???????????????Handler
        mBackHandler = new Handler(mBackThread.getLooper());
//        mBackHandler.post(new Runnable() {
//            @Override
//            public void run() {
//                // ???????????????????????????????????????
//
//                // mHandler?????????????????????????????????UI
//                Message message=new Message();
//                message.what=1;
//                handler.sendMessage(message);
//            }
//        });

        if (timerLocation == null) {
            timerLocation = new Timer();
            //schedule??????????????????????????????????????????
            timerLocation.schedule(new TimerTask() {
                //run?????????????????????????????????????????????
                @Override
                public void run() {
                    Message message = new Message();
                    handler.sendMessage(message);
                    message.what = 300002;
                    Log.d(TAG, "handlerrun: " + 1);
                }
//            }, 0, 10000);
            }, 0, 8000);
        }

        timerRestart1 = new Timer();
        timerRestart2 = new Timer();
        initTTS();
        try {
            dbManager01 = new DBManager01(MainActivity.this);
            dbManagerPinConfig = new DBManagerPinConfig(MainActivity.this);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            dbManagersaopin = new DBManagersaopin(MainActivity.this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getPermissions();
        AddITemMenu();//?????????????????????
        message = new Message();
        bundle = new Bundle();
        timer1 = new Timer();
        timer2 = new Timer();
        timer_wendu = new Timer();
        buf = new byte[1024];
        dp = new DatagramPacket(buf, buf.length);
        MainUtils.ReceiveMain(handler, message, bundle, timer1, timer2, dp, buf, MainActivity.this, runThread);//??????????????????
        MainUtils.WifiMain(handler, message, bundle, timer1, timer2, dp, buf, MainActivity.this);//??????wifi??????
        MainUtils.ReceiveMainWD(handler, message, bundle, timer_wendu);
        MainUtils.TYPES(handler);
        stringBuffer1 = new StringBuffer();
        stringBuffer2 = new StringBuffer();
//        initTimers();
        //DBManagerPinConfig ?????????
        listsSp.clear();
        try {
            dbManagerPinConfig = new DBManagerPinConfig(this);
            pinConfigBeans = dbManagerPinConfig.getStudent();
            listsSp.add("");
            if (pinConfigBeans.size() > 0) {
                for (int j = 0; j < pinConfigBeans.size(); j++) {
                    listsSp.add(pinConfigBeans.get(j).getDown() + "");
                }
                if (listsSp.size() > 0) {
                    SpinnersSetData(1);//????????????spinner???????????????
                    SpinnersSetData(2);//????????????spinner???????????????

                } else {
//                    ToastUtils.showToast("???????????????");
                }
            } else {
//                ToastUtils.showToast("???????????????");
            }
            Log.d(TAG, "pinConfigBeansinit: " + pinConfigBeans);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        ImslList();
        Log.d(TAG, "init: ");
        initSeek();
        sendBlackListinit();
        sendBlackList2init();


//        isFirstStart(MainActivity.this);
//        SpinnersSet();
        //??????????????????

//        FengShanFlag = true;
//        off_on(1);
        iv_fengshan.setBackground(getResources().getDrawable(R.mipmap.fengshan));

        setLocationType();

        try {
            dbManagerZM = new DBManagerZM(MainActivity.this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //?????????????????????imsi??????
        List<ZmBean> zmBeans = null;
        try {
            zmBeans = dbManagerZM.getDataAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Log.d("dbManagerZM", "handleMessage: " + i);
        Log.d("dbManagerZM", "handleMessage:size " + zmBeans.toString());
        List<Integer> listsize = new ArrayList<>();
        for (int i = 0; i < zmBeans.size(); i++) {
            listsize.add(i + 1);
        }
        if (zmBeans == null) {

        } else {
            ryZmAdapter = new RyZmAdapter(Basecontext, zmBeans, listsize);//list???imsi?????????????????????
            ry_zhenma.setAdapter(ryZmAdapter);
        }

        if (listsize.size() > 6) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
            linearLayoutManager.setStackFromEnd(true);//recyview?????????????????????
            ry_zhenma.setLayoutManager(linearLayoutManager);
            tv_searchNum.setText("(" + zmBeans.size() + ")");
        } else {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
            linearLayoutManager.setStackFromEnd(false);//recyview?????????????????????
            ry_zhenma.setLayoutManager(linearLayoutManager);
            tv_searchNum.setText("(" + zmBeans.size() + ")");
        }
        ryZmAdapter = new RyZmAdapter(Basecontext, zmBeans, listsize);//list???imsi?????????????????????
        ry_zhenma.setAdapter(ryZmAdapter);
    }

    private void test1() {
        int a = 18547;
        byte[] bytes = StringConvertUtils.toHH(a);
        String s2 = Hex.byte2HexStr(bytes);
        String str = ReceiveTask.toHexString1(bytes);
        String substring = str.substring(4);
        Log.d("str2HexStr", "init: " + substring);

        String substring1 = substring.substring(0, 1);
        String substring2 = substring.substring(1, 2);
        String substring3 = substring.substring(2, 3);
        String substring4 = substring.substring(3, 4);
        Log.d("str2HexStr", "test1: " + substring3 + substring4 + substring1 + substring2);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void initSeek() {
        seekbar1.getThumb().setColorFilter(Color.parseColor("#10a146"), PorterDuff.Mode.SRC_ATOP);
        seekbar2.getThumb().setColorFilter(Color.parseColor("#ff6367"), PorterDuff.Mode.SRC_ATOP);
        seekbar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //            ??????????????????????????????
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            //            ??????SeekBar????????????
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            //            ??????SeekBar?????????
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
//                ToastUtils.showToast("" + progress);
                setzy(progress, 1);
            }
        });
        seekbar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //            ??????????????????????????????
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            //            ??????SeekBar????????????
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            //            ??????SeekBar?????????
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                ToastUtils.showToast("" + progress);

                setzy(progress, 2);
            }
        });
    }

    /**
     * ????????????
     *
     * @param i  ??????????????????
     * @param sb ????????????
     */
    private void setzy(final int i, final int sb) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String ip = "";
                if (sb == 1) {
                    ip = IP1;
                }
                if (sb == 2) {
                    ip = IP2;
                }
                DatagramSocket socket = null;
                InetAddress address = null;//????????????ip??????
                Log.e("znzq", "run: nzqsend" + i);
                String s = Setzy.acceptGain(i);
                Log.d(TAG, "run: " + s);
                byte[] outputData = MainUtilsThread.hexStringToByteArray(Setzy.acceptGain(i));
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
                Log.e("startLocation1s", "run: sendsocket?????????" + outputPacket.getPort() + "Ip??????:" + outputPacket.getAddress().toString() + "??????:" + outputPacket.getData());
                try {
                    //                                    socket.send(outputPacket);
                    DS.send(outputPacket);
//                    interrupted();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onResume() {
        super.onResume();
        setLocationType();//????????????????????????
        ImslList();
        if ("?????????".equals(sb1type) || "???????????????".equals(sb1type) || "??????????????????".equals(sb1type) || "??????????????????".equals(sb1type) || "?????????????????????".equals(sb1type) || "???????????????????????????".equals(sb1type) || "????????????????????????".equals(sb1type)) {

        } else {
            sb1pd = false;
            sb2pd = false;
            listsSp.clear();
            try {
                dbManagerPinConfig = new DBManagerPinConfig(this);
                pinConfigBeans = dbManagerPinConfig.getStudent();
                listsSp.add("");
                if (pinConfigBeans.size() > 0) {
                    for (int j = 0; j < pinConfigBeans.size(); j++) {
                        listsSp.add(pinConfigBeans.get(j).getDown() + "");
                    }
                    if (listsSp.size() > 0) {
                        SpinnersSetData(1);//????????????spinner???????????????
                        Log.d(TAG, "onResumeSpinnersSetData" + "t");
                    } else {
                        ToastUtils.showToast("???????????????");
                    }
                } else {
                    ToastUtils.showToast("???????????????");
                }
                Log.d(TAG, "pinConfigBeansinit: " + pinConfigBeans);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if ("?????????".equals(sb1type) || "???????????????".equals(sb1type) || "??????????????????".equals(sb1type) || "??????????????????".equals(sb1type) || "?????????????????????".equals(sb1type) || "???????????????????????????".equals(sb1type) || "????????????????????????".equals(sb1type)) {

        } else {
            sb1pd = false;
            sb2pd = false;
            listsSp.clear();
            try {
                dbManagerPinConfig = new DBManagerPinConfig(this);
                pinConfigBeans = dbManagerPinConfig.getStudent();
                listsSp.add("");
                if (pinConfigBeans.size() > 0) {
                    for (int j = 0; j < pinConfigBeans.size(); j++) {
                        listsSp.add(pinConfigBeans.get(j).getDown() + "");
                    }
                    if (listsSp.size() > 0) {
                        SpinnersSetData(1);//????????????spinner???????????????
                        Log.d(TAG, "onResumeSpinnersSetData" + "y");
                    } else {
                        ToastUtils.showToast("???????????????");
                    }
                } else {
                    ToastUtils.showToast("???????????????");
                }
                Log.d(TAG, "pinConfigBeansinit: " + pinConfigBeans);
            } catch (SQLException e) {
                e.printStackTrace();
            }
//            ImslList();
        }
        if ("?????????".equals(sb2type) || "???????????????".equals(sb2type) || "??????????????????".equals(sb2type) || "??????????????????".equals(sb2type) || "?????????????????????".equals(sb2type) || "???????????????????????????".equals(sb2type) || "????????????????????????".equals(sb2type)) {

        } else {
            sb1pd = false;
            sb2pd = false;
            listsSp.clear();
            try {
                dbManagerPinConfig = new DBManagerPinConfig(this);
                pinConfigBeans = dbManagerPinConfig.getStudent();
                listsSp.add("");
                if (pinConfigBeans.size() > 0) {
                    for (int j = 0; j < pinConfigBeans.size(); j++) {
                        listsSp.add(pinConfigBeans.get(j).getDown() + "");
                    }
                    if (listsSp.size() > 0) {
                        SpinnersSetData(2);//????????????spinner???????????????
                        Log.d(TAG, "onResumeSpinnersSetData" + "u");
                    } else {
                        ToastUtils.showToast("???????????????");
                    }
                } else {
                    ToastUtils.showToast("???????????????");
                }
                Log.d(TAG, "pinConfigBeansinit: " + pinConfigBeans);
            } catch (SQLException e) {
                e.printStackTrace();
            }
//            ImslList();
        }
    }

    /**
     * ?????????????????????????????????
     */
    private void setLocationType() {


//                Timer timer = new Timer();// ?????????Timer???
//                timer.schedule(new TimerTask() {
//                    public void run() {
        //???????????????????????? ??????1
        DBManager01 dbManager01 = null;
        try {
            dbManager01 = new DBManager01(MainActivity.this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Conmmunit01Bean forid1 = null;
        try {
            forid1 = dbManager01.forid(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String type1 = forid1.getType();
        if (type1.equals("0")) {  // ????????????
//            SpinnersSet();
            bts_start1.setText("????????????");
            btsstop1.setText("????????????");

        } else {//????????????
//            SpinnersSet2();
            bts_start1.setText("????????????");
            btsstop1.setText("????????????");

        }

        //???????????????????????? ??????2
        try {
            dbManager01 = new DBManager01(MainActivity.this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Conmmunit01Bean forid2 = null;
        try {
            forid2 = dbManager01.forid(2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String type2 = forid2.getType();
//                        ToastUtils.showToast("????????????2"+type2);
        if (type2.equals("0")) {//????????????
            bts_start2.setText("????????????");
            btsstop2.setText("????????????");
        } else {//????????????
            bts_start2.setText("????????????");
            btsstop2.setText("????????????");
        }
//                        this.cancel();
//                    }
//                }, 500);// ???????????????


    }

    //???????????????1?????????
    public String upStr = "";

    //??????????????? ????????????
    private void Set1StatusBar(String str) {
        if (!upStr.equals(str)) {
            stringBuffer1.append(str + "" + "\n");
            textViews.setText(stringBuffer1);
            upStr = str;
            scrollView.fullScroll(ScrollView.FOCUS_DOWN);//???????????????
        }
    }

    //???????????????1?????????
    public String upStr2 = "";

    //??????????????? ????????????
    private void Set2StatusBar(String str) {
        if (!upStr2.equals(str)) {
            stringBuffer2.append(str + "" + "\n");
            text2Views.setText(stringBuffer2);
            upStr2 = str;
            scrollView2.fullScroll(ScrollView.FOCUS_DOWN);//???????????????
        }
    }

    //imsi??????
    private void ImslList() {
        try {
            dbManagerAddParam = new DBManagerAddParam(this);
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

                    ryImsiAdapter = new RyImsiAdapter(Basecontext, listImsiListTrue, list1size, config, tv_imsi1, tv_imsi2);//list???imsi?????????????????????
                    ryIMSI.setAdapter(ryImsiAdapter);
                }

            }
            Log.d("addPararBeans", "init: " + dataAll);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //????????????1
    public void start1() {
//        Log.d(TAG, "sendBlackList:?????? " + spinnerS1);
        sendListBlack = new ArrayList<>();
        if (!TextUtils.isEmpty(spinnerS1)) {
//            String sb1zhishi = "";
            String yy = "";
            try {
                pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(spinnerS1)); //?????????????????????
                yy = pinConfigBeans.get(0).getYy();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "sendBlackList:?????? ");
            if (listImsiListTrue.size() > 0 && listImsiListTrue != null) {
                for (int i = 0; i < listImsiListTrue.size(); i++) {
                    if (listImsiListTrue.get(i).getYy().equals(yy)) {
                        sendListBlack.add(listImsiListTrue.get(i));
                    }
                }
            }

            if (sendListBlack.size() > 0 && sendListBlack != null) {
                Log.d(TAG, "sendBlackList: " + sendListBlack);
                if (sendListBlack.size() > 20) {
                    ToastUtils.showToast("????????????????????????????????????20");
                    return;
                } else {
//                    sendBlackListRun(sendListBlack);
                }
//
            } else {
//                CALLBLACKFLAGSET1 = false;
                ToastUtils.showToast("?????????????????????IMSI");
                return;
            }

        } else {
            ToastUtils.showToast("????????????????????????");
            return;
        }

        if ("??????".equals(sb1) || "?????????...".equals(sb1) || "?????????".equals(sb1) || "????????????".equals(sb1) || "?????????????????????".equals(sb1) || "???????????????".equals(sb1)) {
            String sb1zhishi = "";
            if (!TextUtils.isEmpty(spinnerS1)) {
                try {
                    pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(spinnerS1)); //?????????????????????
                    sb1zhishi = pinConfigBeans.get(0).getTf();
                } catch (SQLException e) {
                    e.printStackTrace();
                }


                Log.d(TAG, "start1sb1zhishi: " + sb1zhishi);
//                String s = tv1_td.getText().toString();
                if (!TextUtils.isEmpty(tf1)) {
                    if (sb1zhishi.equals(tf1)) {//????????????
                        if (TextUtils.isEmpty(spinnerS1)) {
                            ToastUtils.showToast("??????1??????????????????");
                            return;
                        }
//                        if (TextUtils.isEmpty(spinnerS2)) {
//                            ToastUtils.showToast("??????2??????????????????");
//                            return;
//                        }
                        if (spinnerS1.equals(spinnerS2)) {
                            ToastUtils.showToast("??????1?????????2????????????????????????");
                        } else {
                            sb1zhishiFlag = true;
//                                            ToastUtils.showToast("????????????");
//                            new CircleDialog.Builder((FragmentActivity) mContext)
//                                    .setTitle("")
//                                    .setText("?????????????????????1????")
//                                    .setTitleColor(Color.parseColor("#00acff"))
//                                    .setNegative("??????", new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View view) {
//                                            sb1Clear();//??????1????????????
//                                            BLACKTIME1 = System.currentTimeMillis();
//                                            CALLBLACKFLAG1 = true;
//                                        }
//                                    })
//                                    .setPositive("??????", null)
//                                    .show();
//
                            dialog = new Dialog(MainActivity.this, R.style.menuDialogStyleDialogStyle);
                            inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_item_title, null);
                            TextView tv_title = inflate.findViewById(R.id.tv_title);
                            tv_title.setText("?????????????????????1????");
                            Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
                            bt_confirm.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    sb1Clear();//??????1????????????
                                    Log.d("sb1Clear", "onClicksb1Clear: ");
                                    BLACKTIME1 = System.currentTimeMillis();
                                    CALLBLACKFLAG1 = true;
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
//                        sb1Locations();//??????1????????????
                    } else {//???????????????
                        if (TextUtils.isEmpty(spinnerS1)) {
                            ToastUtils.showToast("??????1??????????????????");
                            return;
                        }
//                        if (TextUtils.isEmpty(spinnerS2)) {
//                            ToastUtils.showToast("??????2??????????????????");
//                            return;
//                        }
                        if (spinnerS1.equals(spinnerS2)) {
                            ToastUtils.showToast("??????1?????????2????????????????????????");
                            return;
                        }

                        sb1zhishiFlag = false;
//                        ToastUtils.showToast("???????????????");
//                        new CircleDialog.Builder((FragmentActivity) mContext)
//                                .setTitle("")
//                                .setText("??????????????????,????????????????????????1")
//                                .setTitleColor(Color.parseColor("#00acff"))
//                                .setNegative("??????", new MyOclck(sb1zhishi, 11))
//                                .setPositive("??????", null)
//                                .show();
                        dialog = new Dialog(MainActivity.this, R.style.menuDialogStyleDialogStyle);
                        inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_item_title, null);
                        TextView tv_title = inflate.findViewById(R.id.tv_title);
                        tv_title.setText("??????????????????,????????????????????????1");
                        Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
                        bt_confirm.setOnClickListener(new MyOclck(sb1zhishi, 11));

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
                } else {
                    ToastUtils.showToast("???????????????1");
                }
            } else {
                ToastUtils.showToast("?????????????????????");
            }
        } else {
            ToastUtils.showToast("???????????????1");
        }

    }

    //??????1??????
    public void start1sp(boolean phonesp) {//?????????????????????

        if (phonesp) {//??????????????????
            if ("??????".equals(sb1) || "?????????...".equals(sb1) || "?????????".equals(sb1) || "????????????".equals(sb1) || "?????????????????????".equals(sb1) || "???????????????".equals(sb1) || "?????????".equals(sb1)) {
//
                DialogUtils.SaoPinDialog(1, MainActivity.this, inflate, saoPinCallback, tf1, true);
            } else {
                ToastUtils.showToast("???????????????");
            }
        } else {//???????????? ???????????????
            if ("??????".equals(sb1) || "?????????...".equals(sb1) || "?????????".equals(sb1) || "????????????".equals(sb1) || "?????????????????????".equals(sb1) || "???????????????".equals(sb1) || "?????????".equals(sb1)) {
//
                DialogUtils.SaoPinDialog2(1, MainActivity.this, inflate, saoPinCallback, tf1, false, sb1);
            } else {
                ToastUtils.showToast("???????????????");
            }
        }
    }

    //??????1??????
    public void start1zm() {// ????????????
        String sb1zhishi = "";
        if ("??????".equals(sb1) || "?????????...".equals(sb1) || "?????????".equals(sb1) || "????????????".equals(sb1) || "?????????????????????".equals(sb1) || "???????????????".equals(sb1)) {
            if (!TextUtils.isEmpty(spinnerS1)) {
                try {
                    pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(spinnerS1)); //?????????????????????
                    sb1zhishi = pinConfigBeans.get(0).getTf();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                Log.d(TAG, "start1sb1zhishi: " + sb1zhishi);
//                String s = tv1_td.getText().toString();
                if (!TextUtils.isEmpty(tf1)) {
                    if (sb1zhishi.equals(tf1)) {//????????????
                        if (TextUtils.isEmpty(spinnerS1)) {
                            ToastUtils.showToast("??????1??????????????????");
                            return;
                        }
//                        if (TextUtils.isEmpty(spinnerS2)) {
//                            ToastUtils.showToast("??????2??????????????????");
//                            return;
//                        }
                        if (spinnerS1.equals(spinnerS2)) {
                            ToastUtils.showToast("??????1?????????2????????????????????????");
                        } else {
                            sb1zhishiFlag = true;
//                                            ToastUtils.showToast("????????????");
//
                            dialog = new Dialog(MainActivity.this, R.style.menuDialogStyleDialogStyle);
                            inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_item_title, null);
                            TextView tv_title = inflate.findViewById(R.id.tv_title);
                            tv_title.setText("?????????????????????1????");
                            Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
                            final String finalSb1zhishi = sb1zhishi;
                            bt_confirm.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
//                                    if (tf1.equals("FDD")) {//????????????
                                    MainUtils.sbzmLocation(IP1, MainActivity.this);
//                                    ToastUtils.showToast("MainUtils.sbzmLocation(IP1)");
                                    dialog.dismiss();
                                    dialog.cancel();
//                                    } else {// TDD
////                                        dialog.dismiss();
////                                        dialog.cancel();
//                                        sb1zhishiFlag = false;
//                                        dialog = new Dialog(MainActivity.this, R.style.menuDialogStyleDialogStyle);
//                                        inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_item_title, null);
//                                        TextView tv_title = inflate.findViewById(R.id.tv_title);
//                                        tv_title.setText("TDD??????????????????");
//                                        Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
//
//                                        bt_confirm.setOnClickListener(new MyOclck(finalSb1zhishi, 11));
//
//                                        Button bt_cancel = inflate.findViewById(R.id.bt_cancel);
//                                        bt_cancel.setOnClickListener(new View.OnClickListener() {
//                                            @Override
//                                            public void onClick(View view) {
//                                                dialog.dismiss();
//                                                dialog.cancel();
//                                            }
//                                        });
//                                        dialog.setCanceledOnTouchOutside(false);
//                                        dialog.setContentView(inflate);
//                                        //????????????Activity???????????????
//                                        Window dialogWindow = dialog.getWindow();
//                                        //??????Dialog?????????????????????
//                                        dialogWindow.setGravity(Gravity.CENTER);
//                                        //?????????????????????
//                                        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
////                           lp.y = 20;//??????Dialog?????????????????????
////                          ????????????????????????
//                                        dialogWindow.setAttributes(lp);
////                        dialog.show();//???????????????
//                                        dialog.show();//???????????????
//                                    }
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
//                        sb1Locations();//??????1????????????
                    } else {//???????????????
                        if (TextUtils.isEmpty(spinnerS1)) {
                            ToastUtils.showToast("??????1??????????????????");
                            return;
                        }
//                        if (TextUtils.isEmpty(spinnerS2)) {
//                            ToastUtils.showToast("??????2??????????????????");
//                            return;
//                        }
                        if (spinnerS1.equals(spinnerS2)) {
                            ToastUtils.showToast("??????1?????????2????????????????????????");
                            return;
                        }

                        sb1zhishiFlag = false;


                        dialog = new Dialog(MainActivity.this, R.style.menuDialogStyleDialogStyle);
                        inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_item_title, null);
                        TextView tv_title = inflate.findViewById(R.id.tv_title);
                        tv_title.setText("??????????????????,????????????????????????1");
                        Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
                        bt_confirm.setOnClickListener(new MyOclck(sb1zhishi, 11));

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
                } else {
                    ToastUtils.showToast("???????????????1");
                }
            } else {
                ToastUtils.showToast("?????????????????????");
            }
        } else {
            ToastUtils.showToast("???????????????1");
        }

    }

    //????????????2
    public void start2() {
        Log.d(TAG, "sendBlackList:?????? " + spinnerS2);
        sendListBlack2 = new ArrayList<>();
        if (!TextUtils.isEmpty(spinnerS2)) {
//            String sb1zhishi = "";
            String yy = "";
            try {
                pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(spinnerS2)); //?????????????????????
                yy = pinConfigBeans.get(0).getYy();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "sendBlackList:pinConfigBeans?????? " + pinConfigBeans);
            if (listImsiListTrue.size() > 0 && listImsiListTrue != null) {
                for (int i = 0; i < listImsiListTrue.size(); i++) {
                    if (listImsiListTrue.get(i).getYy().equals(yy)) {
                        sendListBlack2.add(listImsiListTrue.get(i));
                    }
                }
            }
            if (sendListBlack2.size() > 0 && sendListBlack2 != null) {
                Log.d(TAG, "sendBlackList: " + sendListBlack2);
                if (sendListBlack2.size() > 20) {
                    ToastUtils.showToast("????????????????????????????????????20");
                    return;
                } else {
//                    sendBlackListRun2(sendListBlack2);
                }

            } else {
//                CALLBLACKFLAGSET2 = false;
                ToastUtils.showToast("?????????????????????IMSI");
                return;
            }
        } else {
            ToastUtils.showToast("????????????????????????");
            return;
        }

        if ("??????".equals(sb2) || "?????????...".equals(sb2) || "?????????".equals(sb2) || "????????????".equals(sb2) || "?????????????????????".equals(sb2) || "???????????????".equals(sb2)) {
            String sb2zhishi = "";
            if (!TextUtils.isEmpty(spinnerS2)) {
                try {
                    pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(spinnerS2)); //?????????????????????
                    sb2zhishi = pinConfigBeans.get(0).getTf();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                Log.d(TAG, "start1sb1zhishi: " + sb2zhishi);
//                String s = tv1_td.getText().toString();
                if (!TextUtils.isEmpty(tf2)) {
                    if (sb2zhishi.equals(tf2)) {//????????????

                        if (TextUtils.isEmpty(spinnerS2)) {
                            ToastUtils.showToast("??????2??????????????????");
                            return;
                        }
                        if (spinnerS1.equals(spinnerS2)) {
                            ToastUtils.showToast("??????1?????????2????????????????????????");
                        } else {
                            sb2zhishiFlag = true;
//                            ToastUtils.showToast("????????????");
//                            new CircleDialog.Builder((FragmentActivity) mContext)
//                                    .setTitle("")
//                                    .setText("?????????????????????2????")
//                                    .setTitleColor(Color.parseColor("#00acff"))
//                                    .setNegative("??????", new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View view) {
//                                            sb2Clear();//??????2????????????
//                                            BLACKTIME2 = System.currentTimeMillis();
//                                            CALLBLACKFLAG2 = true;
//
//                                        }
//                                    })
//                                    .setPositive("??????", null)
//                                    .show();
//
                            dialog = new Dialog(MainActivity.this, R.style.menuDialogStyleDialogStyle);
                            inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_item_title, null);
                            TextView tv_title = inflate.findViewById(R.id.tv_title);
                            tv_title.setText("?????????????????????2????");
                            Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
                            bt_confirm.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    sb2Clear();//??????2????????????
                                    BLACKTIME2 = System.currentTimeMillis();
                                    CALLBLACKFLAG2 = true;
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
//                        sb1Locations();//??????1????????????
                    } else {//???????????????
//                    if (TextUtils.isEmpty(spinnerS1)) {
//                        ToastUtils.showToast("??????1??????????????????");
//                        return;
//                    }
                        if (TextUtils.isEmpty(spinnerS2)) {
                            ToastUtils.showToast("??????2??????????????????");
                            return;
                        }
                        if (spinnerS1.equals(spinnerS2)) {
                            ToastUtils.showToast("??????1?????????2????????????????????????");
                            return;
                        }

                        sb2zhishiFlag = false;
                        ToastUtils.showToast("???????????????");
//                        new CircleDialog.Builder((FragmentActivity) mContext)
//                                .setTitle("")
//                                .setText("??????????????????,????????????????????????2")
//                                .setTitleColor(Color.parseColor("#00acff"))
//                                .setNegative("??????", new MyOclck2(sb2zhishi, 11))
//                                .setPositive("??????", null)
//                                .show();
                        dialog = new Dialog(MainActivity.this, R.style.menuDialogStyleDialogStyle);
                        inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_item_title, null);
                        TextView tv_title = inflate.findViewById(R.id.tv_title);
                        tv_title.setText("??????????????????,????????????????????????2");
                        Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
                        bt_confirm.setOnClickListener(new MyOclck2(sb2zhishi, 11, dialog));
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
                } else {
                    ToastUtils.showToast("???????????????1");
                }
            } else {
                ToastUtils.showToast("?????????????????????");
            }
        } else {
            ToastUtils.showToast("???????????????1");
        }
    }

    //??????2??????
    public void start2sp(boolean phonesp) {
        if (phonesp) {

            if ("??????".equals(sb2) || "?????????...".equals(sb2) || "?????????".equals(sb2) || "????????????".equals(sb2) || "?????????????????????".equals(sb2) || "???????????????".equals(sb2) || "?????????".equals(sb2)) {
//
                DialogUtils.SaoPinDialog(2, MainActivity.this, inflate, saoPinCallback, tf2, true);
            } else {
                ToastUtils.showToast("???????????????");
            }
        } else {

            if ("??????".equals(sb2) || "?????????...".equals(sb2) || "?????????".equals(sb2) || "????????????".equals(sb2) || "?????????????????????".equals(sb2) || "???????????????".equals(sb2) || "?????????".equals(sb2)) {
//
                DialogUtils.SaoPinDialog2(2, MainActivity.this, inflate, saoPinCallback, tf2, false, sb2);
            } else {
                ToastUtils.showToast("???????????????");
            }
        }

    }

    //??????2??????
    public void start2zm() {// ????????????
        String sb1zhishi = "";
        if ("??????".equals(sb2) || "?????????...".equals(sb2) || "?????????".equals(sb2) || "????????????".equals(sb2) || "?????????????????????".equals(sb2) || "???????????????".equals(sb2)) {
            if (!TextUtils.isEmpty(spinnerS2)) {
                try {
                    pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(spinnerS2)); //?????????????????????
                    sb1zhishi = pinConfigBeans.get(0).getTf();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                Log.d(TAG, "start1sb1zhishi: " + sb1zhishi);
//                String s = tv1_td.getText().toString();
                if (!TextUtils.isEmpty(tf2)) {
                    if (sb1zhishi.equals(tf2)) {//????????????
                        if (TextUtils.isEmpty(spinnerS2)) {
                            ToastUtils.showToast("??????2??????????????????");
                            return;
                        }
//                        if (TextUtils.isEmpty(spinnerS2)) {
//                            ToastUtils.showToast("??????2??????????????????");
//                            return;
//                        }
                        if (spinnerS1.equals(spinnerS2)) {
                            ToastUtils.showToast("??????1?????????2????????????????????????");
                        } else {
                            sb2zhishiFlag = true;
//                                            ToastUtils.showToast("????????????");
//
                            dialog = new Dialog(MainActivity.this, R.style.menuDialogStyleDialogStyle);
                            inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_item_title, null);
                            TextView tv_title = inflate.findViewById(R.id.tv_title);
                            tv_title.setText("?????????????????????2????");
                            Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
                            final String finalSb1zhishi = sb1zhishi;
                            bt_confirm.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
//                                    if (tf1.equals("FDD")) {//????????????
                                    MainUtils.sbzmLocation(IP2, MainActivity.this);
//                                    ToastUtils.showToast("MainUtils.sbzmLocation(IP2)");
                                    dialog.dismiss();
                                    dialog.cancel();
//                                    } else {// TDD
////                                        dialog.dismiss();
////                                        dialog.cancel();
//                                        sb1zhishiFlag = false;
//                                        dialog = new Dialog(MainActivity.this, R.style.menuDialogStyleDialogStyle);
//                                        inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_item_title, null);
//                                        TextView tv_title = inflate.findViewById(R.id.tv_title);
//                                        tv_title.setText("TDD??????????????????");
//                                        Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
//
//                                        bt_confirm.setOnClickListener(new MyOclck(finalSb1zhishi, 11));
//
//                                        Button bt_cancel = inflate.findViewById(R.id.bt_cancel);
//                                        bt_cancel.setOnClickListener(new View.OnClickListener() {
//                                            @Override
//                                            public void onClick(View view) {
//                                                dialog.dismiss();
//                                                dialog.cancel();
//                                            }
//                                        });
//                                        dialog.setCanceledOnTouchOutside(false);
//                                        dialog.setContentView(inflate);
//                                        //????????????Activity???????????????
//                                        Window dialogWindow = dialog.getWindow();
//                                        //??????Dialog?????????????????????
//                                        dialogWindow.setGravity(Gravity.CENTER);
//                                        //?????????????????????
//                                        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
////                           lp.y = 20;//??????Dialog?????????????????????
////                          ????????????????????????
//                                        dialogWindow.setAttributes(lp);
////                        dialog.show();//???????????????
//                                        dialog.show();//???????????????
//                                    }
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
//                        sb1Locations();//??????1????????????
                    } else {//???????????????
                        if (TextUtils.isEmpty(spinnerS2)) {
                            ToastUtils.showToast("??????1??????????????????");
                            return;
                        }
//                        if (TextUtils.isEmpty(spinnerS2)) {
//                            ToastUtils.showToast("??????2??????????????????");
//                            return;
//                        }
                        if (spinnerS1.equals(spinnerS2)) {
                            ToastUtils.showToast("??????1?????????2????????????????????????");
                            return;
                        }

                        sb2zhishiFlag = false;


                        dialog = new Dialog(MainActivity.this, R.style.menuDialogStyleDialogStyle);
                        inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_item_title, null);
                        TextView tv_title = inflate.findViewById(R.id.tv_title);
                        tv_title.setText("??????????????????,????????????????????????2");
                        Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
                        bt_confirm.setOnClickListener(new MyOclck2(sb1zhishi, 11, dialog));

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
                } else {
                    ToastUtils.showToast("???????????????2");
                }
            } else {
                ToastUtils.showToast("?????????????????????");
            }
        } else {
            ToastUtils.showToast("???????????????2");
        }

    }

    //??????1???????????? ??????
    private void sb1Locations() {
        SaoPinB1 = true;
        sendListBlack = new ArrayList<>();
        if (!sb1.equals("?????????")) {
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
                        try {
                            pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(spinnerS1)); //?????????????????????
                            yy = pinConfigBeans.get(0).getYy();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        Log.d(TAG, "sendBlackList:?????? ");
                        if (listImsiListTrue.size() > 0 && listImsiListTrue != null) {
                            for (int i = 0; i < listImsiListTrue.size(); i++) {
                                if (listImsiListTrue.get(i).getYy().equals(yy)) {
                                    sendListBlack.add(listImsiListTrue.get(i));
                                }
                            }
                        }

                        if (sendListBlack.size() > 0 && sendListBlack != null) {
                            Log.d(TAG, "sendBlackList: " + sendListBlack);
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
                        byte[] outputData = MainUtilsThread.hexStringToByteArray(location(sendListBlack.get(0).getImsi(), "04", sa, MainActivity.this, 1));
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
                        LocationFlag1 = true;
                        LocationFlags1 = true;
                        long l = System.currentTimeMillis();
                        while (LocationFlag1) {
                            long l1 = System.currentTimeMillis();
                            if (LocationFlags1 == true) {
                                if (l1 - l > 5000) {
                                    LocationFlag1 = false;
                                    Message message = new Message();
                                    bundle.putString("test", "");
                                    message.setData(bundle);
                                    handler.sendMessage(message);
//                            message.what = 100137;

                                } else if (LocationFlags1 == false) {
                                    LocationFlag1 = false;
                                }
                            }
                        }
                    }


                }
            }).start();
        } else {
        }
    }

    //??????1???????????? ??????
    private void sb1LocationsSP() {
//        sendListBlack = new ArrayList<>();
        if (!sb1.equals("?????????")) {
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

                    if (sendListBlack.size() == 0) {
//                        Set1StatusBar("???????????????????????????IMSI");
//                        ToastUtils.showToast("???????????????????????????IMSI");
                        Log.d(TAG, "Arun: ");
                    } else {
                        Log.d(TAG, "Brun: ");
                        byte[] outputData = MainUtilsThread.hexStringToByteArray(location(sendListBlack.get(0).getImsi(), "04", sa, MainActivity.this, 1));
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
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        LocationFlag1 = true;
                        LocationFlags1 = true;
                        long l = System.currentTimeMillis();
                        while (LocationFlag1) {
                            long l1 = System.currentTimeMillis();
                            if (LocationFlags1 == true) {
                                if (l1 - l > 5000) {
                                    LocationFlag1 = false;
                                    Message message = new Message();
                                    bundle.putString("test", "");
                                    message.setData(bundle);
                                    handler.sendMessage(message);
//                            message.what = 100137;

                                } else if (LocationFlags1 == false) {
                                    LocationFlag1 = false;
                                }
                            }
                        }
                    }


                }
            }).start();
        } else {

        }

    }

    //??????2????????????
    private void sb2Locations() {
//
        SaoPinB2 = true;
        if (!sb2.equals("?????????")) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String sa = "";
                    if (tf2.equals("TDD")) {
                        sa = "01";
                    }
                    if (tf2.equals("FDD")) {
                        sa = "00";
                    }
                    DatagramSocket socket = null;
                    InetAddress address = null;//????????????ip??????
                    Log.e("nzq", "run: nzqsend");

                    sendListBlack2 = new ArrayList<>();
                    if (!TextUtils.isEmpty(spinnerS2)) {
//            String sb1zhishi = "";
                        String yy = "";
                        try {
                            pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(spinnerS2)); //?????????????????????
                            yy = pinConfigBeans.get(0).getYy();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        Log.d(TAG, "sendBlackList:pinConfigBeans?????? " + pinConfigBeans);
                        if (listImsiListTrue.size() > 0 && listImsiListTrue != null) {
                            for (int i = 0; i < listImsiListTrue.size(); i++) {
                                if (listImsiListTrue.get(i).getYy().equals(yy)) {
                                    sendListBlack2.add(listImsiListTrue.get(i));
                                }
                            }
                        }
                        if (sendListBlack2.size() > 0 && sendListBlack2 != null) {
                            Log.d(TAG, "sendBlackList: " + sendListBlack2);
                            if (sendListBlack2.size() > 20) {
                                ToastUtils.showToast("????????????????????????????????????20");
                            } else {
//                            sendBlackListRun2(sendListBlack2);
                            }

                        } else {
                            ToastUtils.showToast("?????????????????????IMSI");
                        }
                    } else {
                        ToastUtils.showToast("????????????????????????");
                    }
                    if (sendListBlack2.size() == 0) {
                        Set2StatusBar("?????????????????????IMSI");
                    } else {
                        byte[] outputData = MainUtilsThread.hexStringToByteArray(location(sendListBlack2.get(0).getImsi(), "04", sa, MainActivity.this, 2));
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
                        Log.e("nzqsendstart1", "run: sendsocket?????????" + outputPacket.getPort() + "Ip??????:" + outputPacket.getAddress().toString() + "??????:" + outputPacket.getData());
                        try {
                            //                                    socket.send(outputPacket);
                            DS.send(outputPacket);
//
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        LocationFlag2 = true;
                        LocationFlags2 = true;
                        long l = System.currentTimeMillis();
                        while (LocationFlag2) {
                            long l1 = System.currentTimeMillis();
                            if (LocationFlags2 == true) {
                                if (l1 - l > 5000) {
                                    LocationFlag2 = false;
                                    Message message = new Message();
                                    bundle.putString("test", "");
                                    message.setData(bundle);
                                    handler.sendMessage(message);
//                            message.what = 200137;

                                } else if (LocationFlags2 == false) {
                                    LocationFlag2 = false;
                                }
                            }

                        }
                    }


                }
            }).start();
        } else {

        }

    }

    //??????1???????????? ??????
    private void sb2LocationsSP() {
//        sendListBlack = new ArrayList<>();
        if (!sb2.equals("?????????")) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String sa = "";
                    if (tf2.equals("TDD")) {
                        sa = "01";
                    }
                    if (tf2.equals("FDD")) {
                        sa = "00";
                    }
                    DatagramSocket socket = null;
                    InetAddress address = null;//????????????ip??????
                    Log.e("nzq", "run: nzqsend");

                    if (sendListBlack2.size() == 0) {
//                        Set1StatusBar("???????????????????????????IMSI");
//                        ToastUtils.showToast("???????????????????????????IMSI");
                        Log.d(TAG, "Arun: ");
                    } else {
                        Log.d(TAG, "Brun: ");
                        byte[] outputData = MainUtilsThread.hexStringToByteArray(location(sendListBlack2.get(0).getImsi(), "04", sa, MainActivity.this, 2));
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
                        LocationFlag2 = true;
                        LocationFlags2 = true;
                        long l = System.currentTimeMillis();
                        while (LocationFlag2) {
                            long l1 = System.currentTimeMillis();
                            if (LocationFlags2 == true) {
                                if (l1 - l > 5000) {
                                    LocationFlag2 = false;
                                    Message message = new Message();
                                    bundle.putString("test", "");
                                    message.setData(bundle);
                                    handler.sendMessage(message);
//                            message.what = 200137;
                                } else if (LocationFlags2 == false) {
                                    LocationFlag2 = false;
                                }
                            }
                        }
                    }
                }
            }).start();
        } else {

        }

    }

    //??????1???????????????
    private void sb1Clear() {
        if (itemtype1.equals(TITLEZD)) {//?????????????????????
            new Thread(new Runnable() {
                @Override
                public void run() {
                    sb1FirstFlag = true;
                    startTime1 = System.currentTimeMillis();
                    DatagramSocket socket = null;
                    InetAddress address = null;//????????????ip??????
                    Log.e("nzq", "run: nzqsend");
                    byte[] outputData = MainUtilsThread.hexStringToByteArray(CLEAR);
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
                    Log.e("nzqsendstart1", "run: sendsocket?????????" + outputPacket.getPort() + "Ip??????:" + outputPacket.getAddress().toString() + "??????:" + outputPacket.getData());

                    try {
                        //                                    socket.send(outputPacket);
                        DS.send(outputPacket);
//
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    ClearFlag1 = true;
                    ClearFlags1 = true;
                }
            }).start();
//
        } else {
            Log.d(TAG, "sendBlackList:?????? " + spinnerS1);
            sendListBlack = new ArrayList<>();
            if (!TextUtils.isEmpty(spinnerS1)) {
//            String sb1zhishi = "";
                String yy = "";
                try {
                    pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(spinnerS1)); //?????????????????????
                    yy = pinConfigBeans.get(0).getYy();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "sendBlackList:?????? ");
                if (listImsiListTrue.size() > 0 && listImsiListTrue != null) {
                    for (int i = 0; i < listImsiListTrue.size(); i++) {
                        if (listImsiListTrue.get(i).getYy().equals(yy)) {
                            sendListBlack.add(listImsiListTrue.get(i));
                        }
                    }
                }

                if (sendListBlack.size() > 0 && sendListBlack != null) {
                    Log.d(TAG, "sendBlackList: " + sendListBlack);
                    if (sendListBlack.size() > 20) {
                        ToastUtils.showToast("????????????????????????????????????20");
                    } else {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                sb1FirstFlag = true;
                                startTime1 = System.currentTimeMillis();
                                DatagramSocket socket = null;
                                InetAddress address = null;//????????????ip??????
                                Log.e("nzq", "run: nzqsend");
                                byte[] outputData = MainUtilsThread.hexStringToByteArray(CLEAR);
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
                                Log.e("nzqsendstart1", "run: sendsocket?????????" + outputPacket.getPort() + "Ip??????:" + outputPacket.getAddress().toString() + "??????:" + outputPacket.getData());

                                try {
                                    //                                    socket.send(outputPacket);
                                    DS.send(outputPacket);
//
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                ClearFlag1 = true;
                                ClearFlags1 = true;

                            }
                        }).start();
                    }
//
                } else {
                    CALLBLACKFLAGSET1 = false;
                    ToastUtils.showToast("?????????????????????IMSI");
                }

            } else {
                ToastUtils.showToast("????????????????????????");
            }
        }

    }

    //??????2???????????????
    private void sb2Clear() {
        if (itemtype2.equals(TITLEZD)) {//?????????????????????
            new Thread(new Runnable() {
                @Override
                public void run() {
                    sb2FirstFlag = true;
                    startTime2 = System.currentTimeMillis();
                    DatagramSocket socket = null;
                    InetAddress address = null;//????????????ip??????
                    Log.e("nzq", "run: nzqsend");
                    byte[] outputData = MainUtilsThread.hexStringToByteArray(CLEAR);
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
                    Log.e("nzqsendstart1", "run: sendsocket?????????" + outputPacket.getPort() + "Ip??????:" + outputPacket.getAddress().toString() + "??????:" + outputPacket.getData());
                    try {
                        //                                    socket.send(outputPacket);
                        DS.send(outputPacket);
//
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    ClearFlag2 = true;
                    ClearFlags2 = true;

                }
            }).start();
        } else {
            sendListBlack2 = new ArrayList<>();
            if (!TextUtils.isEmpty(spinnerS2)) {
//            String sb1zhishi = "";
                String yy = "";
                try {
                    pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(spinnerS2)); //?????????????????????
                    yy = pinConfigBeans.get(0).getYy();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "sendBlackList:pinConfigBeans?????? " + pinConfigBeans);
                if (listImsiListTrue.size() > 0 && listImsiListTrue != null) {
                    for (int i = 0; i < listImsiListTrue.size(); i++) {
                        if (listImsiListTrue.get(i).getYy().equals(yy)) {
                            sendListBlack2.add(listImsiListTrue.get(i));
                        }
                    }
                }
                if (sendListBlack2.size() > 0 && sendListBlack2 != null) {
                    Log.d(TAG, "sendBlackList: " + sendListBlack2);
                    if (sendListBlack2.size() > 20) {
                        ToastUtils.showToast("????????????????????????????????????20");
                    } else {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                sb2FirstFlag = true;
                                startTime2 = System.currentTimeMillis();
                                DatagramSocket socket = null;
                                InetAddress address = null;//????????????ip??????
                                Log.e("nzq", "run: nzqsend");
                                byte[] outputData = MainUtilsThread.hexStringToByteArray(CLEAR);
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
                                Log.e("nzqsendstart1", "run: sendsocket?????????" + outputPacket.getPort() + "Ip??????:" + outputPacket.getAddress().toString() + "??????:" + outputPacket.getData());
                                try {
                                    //                                    socket.send(outputPacket);
                                    DS.send(outputPacket);
//
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                ClearFlag2 = true;
                                ClearFlags2 = true;
//                while (ClearFlag2) {
//                    long ls = System.currentTimeMillis();
//                    if (ls - startTime2 > 5000) {
//                        Log.d(TAG, "run:?????????1 ");
//                        ClearFlag2 = false;
//                        interrupted();
//                        Message message = new Message();
//                        bundle.putString("zt2", "1");
//                        message.setData(bundle);
//                        handler.sendMessage(message);
//                        message.what = 200132;
////                        ToastUtils.showToast("?????????");
//                    } else if (ClearFlags2 == false) {
//                        ClearFlag2 = false;
//
//                    }
//                }
                            }
                        }).start();
                    }

                } else {
                    CALLBLACKFLAGSET2 = false;
                    ToastUtils.showToast("?????????????????????IMSI");
                }
            } else {
                ToastUtils.showToast("????????????????????????");
            }
        }
// Log.d(TAG, "sendBlackList:?????? " + spinnerS2);


    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.laba1:
                if (labaFlag1 == true) {
                    labaFlag1 = false;
                    laba1.setBackground(getResources().getDrawable(R.mipmap.laba_gray));
                    break;
                } else if (labaFlag1 == false) {
                    labaFlag1 = true;
                    laba1.setBackground(getResources().getDrawable(R.mipmap.laba_green));
                    break;
                }
            case R.id.laba2:
                if (labaFlag2 == true) {
                    labaFlag2 = false;
                    laba2.setBackground(getResources().getDrawable(R.mipmap.laba_gray));

                    break;
                }
                if (labaFlag2 == false) {
                    labaFlag2 = true;

                    laba2.setBackground(getResources().getDrawable(R.mipmap.laba_red));
                    break;
                }

            case R.id.bts_start1:
                if (itemtype1.equals(TITLESD)) {
                    if (sb1.equals("?????????")) {
                        ToastUtils.showToast("??????????????????");
                    } else {
                        if (bts_start1.getText().equals(TITLESDZM)) {//????????????
                            ToastUtils.showToast(TITLESDZM);
                            start1zm();
                            SaoPinB1 = true;
                        } else {//????????????
                            SaoPinB1 = true;
                            start1();
                        }
                    }
                }

                if (itemtype1.equals(TITLEZD)) {//????????????
                    if (sb1.equals("?????????")) {
                        ToastUtils.showToast("??????????????????");
                    } else {
                        if (bts_start1.getText().equals(TITLEZDZM)) {//????????????
                            ToastUtils.showToast(TITLEZDZM);
                            SaoPinB1 = true;
                            start1sp(true);
                        } else {//????????????
                            SaoPinB1 = true;
                            start1sp(true);
                        }
                    }
                }
                break;
            case R.id.bts_start2:
                if (itemtype2.equals(TITLESD)) {
                    if (sb2.equals("?????????")) {
                        ToastUtils.showToast("??????????????????");

                    } else {
                        if (bts_start2.getText().equals(TITLESDZM)) {//????????????
                            ToastUtils.showToast(TITLESDZM);
                            start2zm();
                            SaoPinB2 = true;
                            LogUtils.a("889900" + "start2zm()");
                        } else {//????????????
                            SaoPinB2 = true;
                            start2();
                            LogUtils.a("889900" + "??????stat2()");
                        }

//                        EstablishVillage();
                    }

                }
                if (itemtype2.equals(TITLEZD)) {
                    if (sb2.equals("?????????")) {
                        ToastUtils.showToast("??????????????????");
                    } else {

                        if (bts_start2.getText().equals(TITLEZDZM)) {//????????????
                            ToastUtils.showToast(TITLEZDZM);
                            SaoPinB2 = true;
                            start2sp(true);
                        } else {
                            SaoPinB2 = true;
                            start2sp(true);
                        }
                    }

                }
                break;
            case R.id.btsstop1://????????????
                stopLocation1();
                break;

            case R.id.btsstop2://????????????
                stopLocation2();
                break;
            case R.id.iv_fengshan:
                if (FengShanFlag == true) {
                    FengShanFlag = false;
                    off_on(2);
                    iv_fengshan.setBackground(getResources().getDrawable(R.mipmap.fengshan));
                } else if (FengShanFlag == false) {
                    FengShanFlag = true;
                    off_on(1);
                    iv_fengshan.setBackground(getResources().getDrawable(R.mipmap.fengshan_toming));
                }

                break;

            case R.id.iv_menu:

                break;
        }
    }

    private void off_on(final int switchs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                DatagramSocket socket = null;
                InetAddress address = null;//????????????ip??????
                byte[] outputData = null;
                if (switchs == 1) {
                    outputData = MainUtilsThread.hexStringToByteArray(Constant.OPENGF);
                }
                if (switchs == 2) {
                    outputData = MainUtilsThread.hexStringToByteArray(Constant.CLOSEGF);

                }
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
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

//    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.laba1:
//                if (labaFlag1 == true) {
//                    labaFlag1 = false;
//                    laba1.setBackground(getResources().getDrawable(R.mipmap.laba_gray));
//                } else if (labaFlag1 == false) {
//                    labaFlag1 = true;
//                    laba1.setBackground(getResources().getDrawable(R.mipmap.laba_green));
//
//                }
//
//                break;
//            case R.id.laba2:
//                if (labaFlag2 == true) {
//                    labaFlag2 = false;
//
//                    laba2.setBackground(getResources().getDrawable(R.mipmap.laba_gray));
//                }
//                if (labaFlag2 == false) {
//                    labaFlag2 = true;
//
//                    laba2.setBackground(getResources().getDrawable(R.mipmap.laba_red));
//                }
//                break;
//        }
//    }
//}


    //??????1???????????????
    public class MyOclck implements View.OnClickListener {
        private int i;
        private String zs;

        public MyOclck(String zs) {
            this.zs = zs;
        }

        public MyOclck(String zs, int i) {
            this.i = i;
            this.zs = zs;
        }

        @Override
        public void onClick(View view) {
            //????????????
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.d("awwjjnzq", "run: " + zs);
                    String Sa = "";
                    byte[] outputData = null;
                    outputData = MainUtilsThread.hexStringToByteArray(location("460010619201205", "04", Sa, MainActivity.this, 1));
                    if (zs.equals("FDD")) {
                        Sa = "00";
                    }
                    if (zs.equals("TDD")) {
                        Sa = "01";
                    }
                    if (i == 1) {
                        outputData = MainUtilsThread.hexStringToByteArray(Constant.FDD);//??????FDD
                    }
                    if (i == 2) {
                        outputData = MainUtilsThread.hexStringToByteArray(Constant.TDD);//??????TDD
                    }
                    if (i == 3) {
                        outputData = MainUtilsThread.hexStringToByteArray(Constant.REBOOT);//?????? ???????????????
                    }
                    if (i == 4) {                                                           //????????????
                        outputData = MainUtilsThread.hexStringToByteArray(location(sendListBlack.get(0).getImsi(), "04", Sa, MainActivity.this, 1));
                    }
                    if (i == 11) {//?????????????????????
                        if (zs.equals("FDD")) {
                            outputData = MainUtilsThread.hexStringToByteArray(Constant.FDD);//??????FDD
                        }
                        if (zs.equals("TDD")) {
                            outputData = MainUtilsThread.hexStringToByteArray(Constant.TDD);//??????TDD
                        }
                    }
                    DatagramSocket socket = null;
                    InetAddress address = null;//????????????ip??????
                    Log.e("nzq", "run: nzqsend");
                    Log.d("awwjjnzq", "run: " + Sa);
//                    byte[] outputData = MainUtilsThread.hexStringToByteArray(location("460010619201205", "04", Sa));
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
                    Log.e("nzqsendstart1", "run: sendsocket?????????" + outputPacket.getPort() + "Ip??????:" + outputPacket.getAddress().toString() + "??????:" + outputPacket.getData());
                    try {
                        //                                    socket.send(outputPacket);
                        DS.send(outputPacket);
                        mysp1.setEnabled(false);
                        Log.d("nzqsocket", "socketrun: " + "??????");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (dialog != null) {
                        dialog.dismiss();
                        dialog.cancel();
                    }
                }
            }).start();
        }
    }

    //??????2???????????????
    class MyOclck2 implements View.OnClickListener {
        private int i;
        private String zs;
        private Dialog dialog;

        public MyOclck2(String zs) {
            this.zs = zs;
        }

        public MyOclck2(String zs, int i, Dialog dialog) {
            this.i = i;
            this.zs = zs;
            this.dialog = dialog;
        }

        @Override
        public void onClick(View view) {
            //????????????
//             * @param power ??????  00???enable-FDD(????????????) 01???disable-TDD(????????????)
//            mBackHandler.post(new Runnable() {
//                @Override
//                public void run() {
//                    // ???????????????????????????????????????
//                    Log.d("awwjjnzq", "run: " + zs);
//                    String Sa = "";
//                    byte[] outputData = null;
//                    outputData = MainUtilsThread.hexStringToByteArray(location("460010619201205", "04", Sa));
//                    if (zs.equals("FDD")) {
//                        Sa = "00";
//                    }
//                    if (zs.equals("TDD")) {
//                        Sa = "01";
//                    }
//                    if (i == 1) {
//                        outputData = MainUtilsThread.hexStringToByteArray(Constant.FDD);//??????FDD
//                    }
//                    if (i == 2) {
//                        outputData = MainUtilsThread.hexStringToByteArray(Constant.TDD);//??????TDD
//                    }
//                    if (i == 3) {
//                        outputData = MainUtilsThread.hexStringToByteArray(Constant.REBOOT);//?????? ???????????????
//                    }
//                    if (i == 4) {                                                           //????????????
//                        outputData = MainUtilsThread.hexStringToByteArray(location(sendListBlack2.get(0).getImsi(), "04", Sa));
//
//                    }
//
//                    if (i == 11) {//?????????????????????
//                        if (zs.equals("FDD")) {
//                            outputData = MainUtilsThread.hexStringToByteArray(Constant.FDD);//??????FDD
//                        }
//                        if (zs.equals("TDD")) {
//                            outputData = MainUtilsThread.hexStringToByteArray(Constant.TDD);//??????TDD
//                        }
//                    }
//                    DatagramSocket socket = null;
//                    InetAddress address = null;//????????????ip??????
//                    Log.e("nzq", "run: nzqsend");
//                    Log.d("awwjjnzq", "run: " + Sa);
////                    byte[] outputData = MainUtilsThread.hexStringToByteArray(location("460010619201205", "04", Sa));
//                    try {
//                        socket = new DatagramSocket();
//                        address = InetAddress.getByName(IP2);
//                    } catch (UnknownHostException e) {
//                        e.printStackTrace();
//                    } catch (SocketException e) {
//                        e.printStackTrace();
//                    }
//                    ;
//                    DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, address, 3345);
////                        DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, reIP, Integer.parseInt(et_port.getText().toString()));
//                    Log.e("nzqsendstart1", "run: sendsocket?????????" + outputPacket.getPort() + "Ip??????:" + outputPacket.getAddress().toString() + "??????:" + outputPacket.getData());
//
//                    try {
//                        socket.send(outputPacket);
////
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.d("awwjjnzq", "run: " + zs);
                    String Sa = "";
                    byte[] outputData = null;
                    outputData = MainUtilsThread.hexStringToByteArray(location("460010619201205", "04", Sa, MainActivity.this, 2));
                    if (zs.equals("FDD")) {
                        Sa = "00";
                    }
                    if (zs.equals("TDD")) {
                        Sa = "01";
                    }
                    if (i == 1) {
                        outputData = MainUtilsThread.hexStringToByteArray(Constant.FDD);//??????FDD
                    }
                    if (i == 2) {
                        outputData = MainUtilsThread.hexStringToByteArray(Constant.TDD);//??????TDD
                    }
                    if (i == 3) {
                        outputData = MainUtilsThread.hexStringToByteArray(Constant.REBOOT);//?????? ???????????????
                    }
                    if (i == 4) {                                                           //????????????
                        outputData = MainUtilsThread.hexStringToByteArray(location(sendListBlack2.get(0).getImsi(), "04", Sa, MainActivity.this, 2));

                    }

                    if (i == 11) {//?????????????????????
                        if (zs.equals("FDD")) {
                            outputData = MainUtilsThread.hexStringToByteArray(Constant.FDD);//??????FDD
                        }
                        if (zs.equals("TDD")) {
                            outputData = MainUtilsThread.hexStringToByteArray(Constant.TDD);//??????TDD
                        }
                    }
                    DatagramSocket socket = null;
                    InetAddress address = null;//????????????ip??????
                    Log.e("nzq", "run: nzqsend");
                    Log.d("awwjjnzq", "run: " + Sa);
//                    byte[] outputData = MainUtilsThread.hexStringToByteArray(location("460010619201205", "04", Sa));
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
                    Log.e("nzqsendstart1", "run: sendsocket?????????" + outputPacket.getPort() + "Ip??????:" + outputPacket.getAddress().toString() + "??????:" + outputPacket.getData());

                    try {
                        socket.send(outputPacket);
                        mysp2.setEnabled(false);
//
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (dialog != null) {
                        dialog.dismiss();
                        dialog.cancel();
                    }
                }
            }).start();

        }

    }
//    //????????????IMSI????????????????????????IMSI???
//    public static StringBuffer toIMSI(String str) {
//
//        char[] chars = "0123456789ABCDEF".toCharArray();
//        StringBuffer sb = new StringBuffer("");
//        byte[] bs = str.getBytes();
//        int bit;
//        for (int i = 0; i < bs.length; i++) {
//            bit = (bs[i] & 0x0f0) >> 4;
//            sb.append(chars[bit]);
//            bit = bs[i] & 0x0f;
//            sb.append(chars[bit]);
//
//        }
//        return sb;
//    }

    //?????????????????????
    private void SpinnersSetData(int posion) {
//        mysp1.setClickable(true);
        if (posion == 1) {
            adapter1 = new ArrayAdapter<String>(context, R.layout.spinner_item
                    , listsSp);
            mysp1.setAdapter(adapter1);
            mysp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    spinnerS1 = adapter1.getItem(i);//?????????1???????????????
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
        if (posion == 2) {
            adapter2 = new ArrayAdapter<String>(context, R.layout.spinner_item
                    , listsSp);
            mysp2.setAdapter(adapter2);
            mysp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    spinnerS2 = adapter2.getItem(i);//?????????2???????????????
                    Log.d(TAG, "2onItemSelected: " + spinnerS2);

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }

    }

    /**
     * ????????????-???????????????
     */
    private void SpinnersSet() {
        List<String> list = new ArrayList<>();
        list.add("??????");
        list.add("??????");
        adapter3 = new ArrayAdapter<String>(context, R.layout.spinner_itemtypesp
                , list);
        typeSp1.setAdapter(adapter3);
        typeSp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                itemtype1 = adapter3.getItem(i);
                Log.d(TAG, "typeSp1onItemSelected: " + itemtype1);
                if (itemtype1.equals(TITLESD)) {//????????????
                    if (MainUtils.dblocation(1, MainActivity.this)) {//????????????
                        bts_start1.setText("????????????");
                        mysp1.setVisibility(View.VISIBLE);
                        tv_r1.setText("????????????");
                    } else {
                        bts_start1.setText("????????????");
                        mysp1.setVisibility(View.VISIBLE);
                        tv_r1.setText("????????????");
                    }
                } else {//????????????
                    if (MainUtils.dblocation(1, MainActivity.this)) {//????????????
                        bts_start1.setText("????????????");
                        mysp1.setVisibility(View.GONE);
                        tv_r1.setText("????????????" + sp1MAX1value);
                    } else {
                        bts_start1.setText("????????????");
                        mysp1.setVisibility(View.GONE);
                        tv_r1.setText("????????????" + sp1MAX1value);
                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        typeSp2.setAdapter(adapter3);
        typeSp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                itemtype2 = adapter3.getItem(i);
                Log.d(TAG, "typeSp2onItemSelected: " + itemtype2);
                if (itemtype2.equals(TITLESD)) {//????????????
                    if (MainUtils.dblocation(2, MainActivity.this)) {//????????????
                        bts_start2.setText("????????????");
                        mysp2.setVisibility(View.VISIBLE);
                        tv_r2.setText("????????????");
                    } else {
                        bts_start2.setText("????????????");
                        mysp2.setVisibility(View.VISIBLE);
                        tv_r2.setText("????????????");
                    }
                } else {//????????????
                    if (MainUtils.dblocation(1, MainActivity.this)) {//????????????
                        bts_start2.setText("????????????");
                        mysp2.setVisibility(View.GONE);
                        tv_r2.setText("????????????" + sp2MAX1value);
                    } else {

                        bts_start2.setText("????????????");
                        mysp2.setVisibility(View.GONE);
                        tv_r2.setText("????????????" + sp2MAX1value);
                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    /**
     * ????????????-???????????????
     */
    private void SpinnersSet2() {
        List<String> list = new ArrayList<>();
        list.add("??????");
        list.add("??????");
        adapter3 = new ArrayAdapter<String>(context, R.layout.spinner_itemtypesp
                , list);
        typeSp1.setAdapter(adapter3);
        typeSp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                itemtype1 = adapter3.getItem(i);
                Log.d(TAG, "typeSp1onItemSelected: " + itemtype1);
                if (itemtype1.equals(TITLESD)) {//????????????
                    bts_start1.setText("????????????");
                    mysp1.setVisibility(View.VISIBLE);
                    tv_r1.setText("????????????");
                } else {//????????????
                    bts_start1.setText("????????????");
                    mysp1.setVisibility(View.GONE);
                    tv_r1.setText("????????????" + sp1MAX1value);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        typeSp2.setAdapter(adapter3);
        typeSp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                itemtype2 = adapter3.getItem(i);
                Log.d(TAG, "typeSp2onItemSelected: " + itemtype2);
                if (itemtype2.equals(TITLESD)) {//????????????
                    bts_start2.setText("????????????");
                    mysp2.setVisibility(View.VISIBLE);
                    tv_r2.setText("????????????");
                } else {//????????????
                    bts_start2.setText("????????????");
                    mysp2.setVisibility(View.GONE);
                    tv_r2.setText("????????????" + sp2MAX1value);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void UpView() {
        ll_zhenma_search = findViewById(R.id.ll_zhenma_search);

        et_zhenmasearch = findViewById(R.id.et_zhenmasearch);
        //??????1
        tv_r1 = findViewById(R.id.tv_r1);
        bts_start1 = findViewById(R.id.bts_start1);
        bts_start1.setOnClickListener(this);
        btsstop1 = findViewById(R.id.btsstop1);
        btsstop1.setOnClickListener(this);//????????????
        tv_zy1 = findViewById(R.id.tv_zy1);
        seekbar1 = findViewById(R.id.seekbar1);//???????????????
        tv1_wifi = findViewById(R.id.tv1_wifi);//????????????1
        tv1_td = findViewById(R.id.tv1_td);//??????
        tv1_type = findViewById(R.id.tv1_type);//??????
        sb1_jl = findViewById(R.id.sb1_jl);//??????1??????
        sb1_jl_pj1 = findViewById(R.id.sb1_jl_pj1);//??????1????????????
        tv_imsi1 = findViewById(R.id.tv_imsi1);//????????????1 imsi
        laba1 = findViewById(R.id.laba1);
        laba1.setOnClickListener(this);

        cbzt1_d = findViewById(R.id.cbzt1_d);
        cbzt1_z = findViewById(R.id.cbzt1_z);
        cbzt1_g = findViewById(R.id.cbzt1_g);
        //??????2
        tv_r2 = findViewById(R.id.tv_r2);
        bts_start2 = findViewById(R.id.bts_start2);
        bts_start2.setOnClickListener(this);//????????????
        btsstop2 = findViewById(R.id.btsstop2);
        btsstop2.setOnClickListener(this);//????????????
        tv_zy2 = findViewById(R.id.tv_zy2);
        seekbar2 = findViewById(R.id.seekbar2);//???????????????
        tv2_type = findViewById(R.id.tv2_type);//??????2 ??????
        tv2_tf = findViewById(R.id.tv2_tf);//??????2??????
        tv2_wifi = findViewById(R.id.tv2_wifi);
        sb1_j2 = findViewById(R.id.sb1_j2);//??????2??????
        sb1_jl_pj2 = findViewById(R.id.sb1_jl_pj2);//??????2????????????
        tv_imsi2 = findViewById(R.id.tv_imsi2);//????????????2 imsi
        laba2 = findViewById(R.id.laba2);
        laba2.setOnClickListener(this);
        cbzt2_d = findViewById(R.id.cbzt2_d);
        cbzt2_z = findViewById(R.id.cbzt2_z);
        cbzt2_g = findViewById(R.id.cbzt2_g);
        //

        mysp1 = findViewById(R.id.mysp1);//??????1?????????
        mysp2 = findViewById(R.id.mysp2);
        typeSp1 = findViewById(R.id.typeSp1);//??????1?????????
        typeSp2 = findViewById(R.id.typeSp2);
        //
        tv_searchNum = findViewById(R.id.tv_searchNum);//??????????????????
    }


    private void AddITemMenu() {//?????????????????????
        popupWindow = new DLPopupWindow(mContext, mList, DLPopupWindow.STYLE_WEIXIN);
        AddMenuUtils.addmenu(this, popupWindow, mList);
        popupWindow.setOnItemClickListener(new DLPopupWindow.OnItemClickListener() {
            @Override
            public void OnClick(int position) {
//                ToastUtils.showToast(mList.get(position).getText());
                if (mList.get(position).getText().equals("????????????")) {
//                    new CircleDialog.Builder((FragmentActivity) mContext)
//                            .setTitle("")
//                            .setText("??????????????????????")
//                            .setTitleColor(Color.parseColor("#00acff"))
//                            .setNegative("????????????", new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                    //????????????
//                                    UtilsView.setViewVisibility(MainActivity.this, layout, title, imageView, getResources().getString(R.string.activity_title), true, iv_findish, false);
//                                    bts_start1.setText("????????????");
//                                    bts_start2.setText("????????????");
//                                    mysp1.setVisibility(View.VISIBLE);
//                                    mysp2.setVisibility(View.VISIBLE);
//                                    tv_r1.setText("????????????");
//                                    tv_r2.setText("????????????");
//                                }
//                            })
//                            .setPositive("????????????", new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                    //????????????
//                                    UtilsView.setViewVisibility(MainActivity.this, layout, title, imageView, getResources().getString(R.string.activity_saopintitle), true, iv_findish, false);
//                                    bts_start1.setText("????????????");
//                                    bts_start2.setText("????????????");
//                                    mysp1.setVisibility(View.GONE);
//                                    mysp2.setVisibility(View.GONE);
//                                    tv_r1.setText("");
//                                    tv_r2.setText("");
//
//                                }
//                            })
//                            .show();
                    String titles = getTitles();
                    dialog = new Dialog(MainActivity.this, R.style.menuDialogStyleDialogStyle);
                    inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_setmenu, null);

                    final CheckBox cb_sp = inflate.findViewById(R.id.cb_sp);
                    final CheckBox cb_sd = inflate.findViewById(R.id.cb_sd);
                    int i;
                    if (titles.equals(TITLEZD)) {
                        cb_sp.setChecked(true);
                        i = 2;
                    }
                    if (titles.equals(TITLEZD)) {
                        cb_sd.setChecked(true);
                        i = 1;
                    }
                    cb_sp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            if (b) {
                                cb_sd.setChecked(false);
                            } else {
                                cb_sp.setChecked(false);
                                cb_sd.setChecked(true);
                            }
                        }
                    });
                    cb_sd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            if (b) {
                                cb_sp.setChecked(false);
                            } else {
                                cb_sd.setChecked(false);
                                cb_sp.setChecked(true);
                            }
                        }
                    });
                    Button bt_cancel = inflate.findViewById(R.id.bt_cancel);
                    Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
                    bt_cancel.setOnClickListener(new View.OnClickListener() {//??????
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                            dialog.cancel();
                        }
                    });
                    bt_confirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            boolean checked = cb_sp.isChecked();
                            boolean checked1 = cb_sd.isChecked();
                            Log.d("bt_confirm", "onClick??????: " + checked1 + "??????" + checked);
                            if (checked) {
                                //????????????
                                UtilsView.setViewVisibility(MainActivity.this, layout, title, imageView, getResources().getString(R.string.activity_saopintitle), true, iv_findish, false);
                                bts_start1.setText("????????????");
                                bts_start2.setText("????????????");
                                mysp1.setVisibility(View.GONE);
                                mysp2.setVisibility(View.GONE);
                                tv_r1.setText("????????????" + sp1MAX1value);
                                tv_r2.setText("????????????" + sp2MAX1value);
                                dialog.dismiss();
                                dialog.cancel();
                            } else {
                                //????????????
                                UtilsView.setViewVisibility(MainActivity.this, layout, title, imageView, getResources().getString(R.string.activity_title), true, iv_findish, false);
                                bts_start1.setText("????????????");
                                bts_start2.setText("????????????");
                                mysp1.setVisibility(View.VISIBLE);
                                mysp2.setVisibility(View.VISIBLE);
                                tv_r1.setText("????????????");
                                tv_r2.setText("????????????");
                                dialog.dismiss();
                                dialog.cancel();
                            }
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
//                    lp.y = 20;//??????Dialog?????????????????????
//       ????????????????????????
                    dialogWindow.setAttributes(lp);
                    dialog.show();//???????????????
                }
                if (mList.get(position).getText().equals("??????IMSI")) {
                    startActivity(new Intent(MainActivity.this, AddParamActivity.class));
                }
                if (mList.get(position).getText().equals("??????IMSI")) {
                    startActivity(new Intent(MainActivity.this, ParamActivity.class));
//                    startActivity(new Intent(MainActivity.this, Param2Activity.class));
                }
                if (mList.get(position).getText().equals("????????????")) {
                    startActivity(new Intent(MainActivity.this, SaoPinSetingActivity.class));
                }

                if (mList.get(position).getText().equals("????????????")) {
//                    startActivity(new Intent(MainActivity.this, PinConfigActivity.class));//?????????
                    startActivity(new Intent(MainActivity.this, PinConfigViewPagerActivity.class));
                }
                if (mList.get(position).getText().equals("????????????")) {
//                    startActivity(new Intent(MainActivity.this, Community01Activity.class));
                    startActivity(new Intent(MainActivity.this, CommunitViewPagerActivity.class));
                }

                if (mList.get(position).getText().equals("????????????")) {
                    startActivity(new Intent(MainActivity.this, DeviceInfoActivity.class));
                }
                if (mList.get(position).getText().equals("????????????")) {
//                    startActivity(new Intent(MainActivity.this, DeviceInfoActivity.class));
//                    ToastUtils.showToast("????????????");
                    DialogUtils.DataExport(MainActivity.this, inflate, zmDataCallBack);
                }

            }
        });
    }

    private String getTitles() {
        String str = "";
        if (itemtype1.equals(TITLEZD)) {//?????????????????????
            str = getString(R.string.activity_saopintitle);
        }
        if (itemtype1.equals(TITLESD)) {//?????????????????????
            str = getString(R.string.activity_title);
        }
        return str;
    }


    @Override
    protected void findViews() {
        UpView();
        tv_wendu = findViewById(R.id.tv_wendu);
        tv_wendu.setVisibility(View.VISIBLE);
        iv_wendu = findViewById(R.id.iv_wendu);
        iv_wendu.setVisibility(View.VISIBLE);
        iv_fengshan = findViewById(R.id.iv_fengshan);
        iv_fengshan.setVisibility(View.VISIBLE);
        iv_fengshan.setOnClickListener(this);
        ryIMSI = findViewById(R.id.ryIMSI);//IMSI??????
        ry_zhenma = findViewById(R.id.ry_zhenma);//IMSI??????

        ryIMSI.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);//recyview?????????????????????
        ry_zhenma.setLayoutManager(linearLayoutManager);
        layout = findViewById(R.id.ll_tab);
        title = findViewById(R.id.titles);
        imageView = findViewById(R.id.iv_menu);
        imageView.setOnClickListener(this);
        iv_findish = findViewById(R.id.iv_finish);
        ib_zhedie = findViewById(R.id.ib_zhedie);
        ib_zhedie_zhenma = findViewById(R.id.ib_zhedie_zhenma);
        lineChartView = (LineChartView) findViewById(R.id.line_chart_view);
        scrollView = findViewById(R.id.scrollView1);
        scrollView.fullScroll(ScrollView.FOCUS_DOWN);//???????????????
        textViews = findViewById(R.id.textViews);

        scrollView2 = findViewById(R.id.scrollView2);
        scrollView2.fullScroll(ScrollView.FOCUS_DOWN);//???????????????
        text2Views = findViewById(R.id.textViews2);
        // ????????????textView?????????????????????????????????
//        textViews.setMovementMethod(ScrollingMovementMethod.getInstance());
        //???????????????
        ib_zhedie.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                if (zdFlag == false) {
                    lineChartView.setVisibility(View.GONE);
                    ib_zhedie.setBackground(getResources().getDrawable(R.mipmap.zheide));
                    zdFlag = true;
                } else if (zdFlag == true) {
                    lineChartView.setVisibility(View.VISIBLE);
                    ib_zhedie.setBackground(getResources().getDrawable(R.mipmap.zheidedown));
                    zdFlag = false;
                }
            }
        });

        //?????? ??????
        ib_zhedie_zhenma.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                if (zdSearchFlag == false) {
                    ll_zhenma_search.setVisibility(View.VISIBLE);
                    ry_zhenma.setVisibility(View.VISIBLE);
                    ib_zhedie_zhenma.setBackground(getResources().getDrawable(R.mipmap.zheidedown));
                    zdSearchFlag = true;

                } else if (zdSearchFlag == true) {
                    ll_zhenma_search.setVisibility(View.GONE);
                    ry_zhenma.setVisibility(View.GONE);
                    ib_zhedie_zhenma.setBackground(getResources().getDrawable(R.mipmap.zheide));
                    zdSearchFlag = false;
                }
            }
        });

        et_zhenmasearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d("et_zhenmasearch", "beforeTextChanged: ");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d("et_zhenmasearch", "onTextChanged: ");
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String str = editable.toString();
                if (!TextUtils.isEmpty(str) && str.length() > 0) {
                    try {
                        dbManagerZM = new DBManagerZM(MainActivity.this);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    //?????????????????????imsi??????
                    List<ZmBean> zmBeans = null;
                    try {
                        zmBeans = dbManagerZM.getDataAll();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    List<ZmBean> zmBeanscontains = new ArrayList<>();
                    if (zmBeans != null) {
                        for (int i = 0; i < zmBeans.size(); i++) {
                            if (zmBeans.get(i).getImsi().contains(str)) {
                                zmBeanscontains.add(zmBeans.get(i));
                            }

                        }
                    } else {
                        return;
                    }
                    List<Integer> listsize = new ArrayList<>();
                    for (int i = 0; i < zmBeanscontains.size(); i++) {
                        listsize.add(i + 1);
                    }
                    if (zmBeans == null) {
                        tv_searchNum.setText("(" + "0" + ")");
                    } else {
                        if (zmBeanscontains.size() > 6) {
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                            linearLayoutManager.setStackFromEnd(true);//recyview?????????????????????
                            ry_zhenma.setLayoutManager(linearLayoutManager);
                            tv_searchNum.setText("(" + listsize.size() + ")");
                        } else {
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                            linearLayoutManager.setStackFromEnd(false);//recyview?????????????????????
                            ry_zhenma.setLayoutManager(linearLayoutManager);
                            tv_searchNum.setText("(" + listsize.size() + ")");
                        }
                        ryZmAdapter = new RyZmAdapter(Basecontext, zmBeanscontains, listsize);//list???imsi?????????????????????
                        ry_zhenma.setAdapter(ryZmAdapter);
                    }
                } else {

                }

            }
        });
        //????????????
//        getResources().getString(R.string.activity_title)
        UtilsView.setViewVisibility(this, layout, title, imageView, "????????????", true, iv_findish, false);

        list1quxian = new ArrayList<>();
        list2quxian = new ArrayList<>();
//        list1quxian.add(0);
        list1quxian.add(0);
        list1quxian.add(0);
        list1quxian.add(0);
        list1quxian.add(0);
        list1quxian.add(0);
        list1quxian.add(0);
        list1quxian.add(0);
        list1quxian.add(0);
        list1quxian.add(0);
        list1quxian.add(0);

//        list2quxian.add(0);
        list2quxian.add(0);
        list2quxian.add(0);
        list2quxian.add(0);
        list2quxian.add(0);
        list2quxian.add(0);
        list2quxian.add(0);
        list2quxian.add(0);
        list2quxian.add(0);
        list2quxian.add(0);
        list2quxian.add(0);

//        dataArr = new int[]{0, 20, 40, 60, 80, 100, 0, 60, 80, 100, 12
//        };
//        dataArr2 = new int[]{0, 14, 0, 22, 89, 10, 12, 20, 40, 60, 135
//        };

        setqxData(list1quxian, list2quxian);
        //CheckBox  ??????
        CheckBoxOnclickSet();
    }

    private void CheckBoxOnclickSet() {

        //??????1
        cbzt1_d.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cbzt1_z.setChecked(false);
                    cbzt1_g.setChecked(false);
                    try {
                        DBManagerZY dbManagerZY = new DBManagerZY(MainActivity.this);
                        if (tf1.equals("TDD")) {
                            int data = dbManagerZY.foriddata(1, 1, 1);
                            setzy(data, 1);
                        }
                        if (tf1.equals("FDD")) {
                            int data = dbManagerZY.foriddata(1, 2, 1);
                            setzy(data, 1);
                            Log.d("zydata", "onCheckedChanged: " + data);
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
//
                } else {
//                    cbzt1_d.setChecked(true);
//                    cbzt1_z.setChecked(false);
//                    cbzt1_g.setChecked(false);
                    if (!cbzt1_z.isChecked() && !cbzt1_g.isChecked()) {
                        cbzt1_d.setChecked(true);
                        Log.d("cbzt1_da", "1onCheckedChanged: ");
                    } else {
                        Log.d("cbzt1_da", "2onCheckedChanged: ");
                    }
                }

            }
        });
        cbzt1_z.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cbzt1_d.setChecked(false);
                    cbzt1_g.setChecked(false);
                    try {
                        DBManagerZY dbManagerZY = new DBManagerZY(MainActivity.this);
                        if (tf1.equals("TDD")) {
                            int data = dbManagerZY.foriddata(1, 1, 2);
                            setzy(data, 1);
                        }
                        if (tf1.equals("FDD")) {
                            int data = dbManagerZY.foriddata(1, 2, 2);
                            setzy(data, 1);
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    if (!cbzt1_d.isChecked() && !cbzt1_g.isChecked()) {
                        cbzt1_z.setChecked(true);
                        Log.d("cbzt1_da", "1onCheckedChanged: ");
                    } else {
                        Log.d("cbzt1_da", "2onCheckedChanged: ");
                    }
                }
            }
        });
        cbzt1_g.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cbzt1_z.setChecked(false);
                    cbzt1_d.setChecked(false);
                    try {
                        DBManagerZY dbManagerZY = new DBManagerZY(MainActivity.this);
                        if (tf1.equals("TDD")) {
                            int data = dbManagerZY.foriddata(1, 1, 3);
                            setzy(data, 1);
                        }
                        if (tf1.equals("FDD")) {
                            int data = dbManagerZY.foriddata(1, 2, 3);
                            setzy(data, 1);
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    if (!cbzt1_z.isChecked() && !cbzt1_d.isChecked()) {
                        cbzt1_g.setChecked(true);
                        Log.d("cbzt1_da", "1onCheckedChanged: ");
                    } else {
                        Log.d("cbzt1_da", "2onCheckedChanged: ");
                    }
                }
            }
        });

        //??????2
        cbzt2_d.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cbzt2_z.setChecked(false);
                    cbzt2_g.setChecked(false);
                    try {
                        DBManagerZY dbManagerZY = new DBManagerZY(MainActivity.this);
                        if (tf2.equals("TDD")) {
                            int data = dbManagerZY.foriddata(2, 1, 1);
                            setzy(data, 2);
                        }
                        if (tf2.equals("FDD")) {
                            int data = dbManagerZY.foriddata(2, 2, 1);
                            setzy(data, 2);
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    if (!cbzt2_z.isChecked() && !cbzt2_g.isChecked()) {
                        cbzt2_d.setChecked(true);
                        Log.d("cbzt1_da", "1onCheckedChanged: ");
                    } else {
                        Log.d("cbzt1_da", "2onCheckedChanged: ");
                    }
                }
            }
        });
        cbzt2_z.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cbzt2_d.setChecked(false);
                    cbzt2_g.setChecked(false);
                    try {
                        DBManagerZY dbManagerZY = new DBManagerZY(MainActivity.this);
                        if (tf2.equals("TDD")) {
                            int data = dbManagerZY.foriddata(2, 1, 2);
                            setzy(data, 2);
                        }
                        if (tf2.equals("FDD")) {
                            int data = dbManagerZY.foriddata(2, 2, 2);
                            setzy(data, 2);
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    if (!cbzt2_d.isChecked() && !cbzt2_g.isChecked()) {
                        cbzt2_z.setChecked(true);
                        Log.d("cbzt1_da", "1onCheckedChanged: ");
                    } else {
                        Log.d("cbzt1_da", "2onCheckedChanged: ");
                    }
                }
            }
        });
        cbzt2_g.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cbzt2_z.setChecked(false);
                    cbzt2_d.setChecked(false);
                    try {
                        DBManagerZY dbManagerZY = new DBManagerZY(MainActivity.this);
                        if (tf2.equals("TDD")) {
                            int data = dbManagerZY.foriddata(2, 1, 3);
                            setzy(data, 2);
                        }
                        if (tf2.equals("FDD")) {
                            int data = dbManagerZY.foriddata(2, 2, 3);
                            setzy(data, 2);
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    if (!cbzt2_z.isChecked() && !cbzt2_d.isChecked()) {
                        cbzt2_g.setChecked(true);
                        Log.d("cbzt1_da", "1onCheckedChanged: ");
                    } else {
                        Log.d("cbzt1_da", "2onCheckedChanged: ");
                    }
                }
            }
        });
    }

    /**
     * ?????????????????????
     *
     * @param list1quxian
     * @param list2quxian
     */
    private void setqxData(List<Integer> list1quxian, List<Integer> list2quxian) {
        lineChartView.setShowTable(true);
        List<LineChartView.Data> datas = new ArrayList<>();
        for (int value : list1quxian) {
            LineChartView.Data data = new LineChartView.Data(value);
            datas.add(data);
        }

        List<LineChartView.Data> datas2 = new ArrayList<>();
        for (int value2 : list2quxian) {
            LineChartView.Data data2 = new LineChartView.Data(value2);
            datas2.add(data2);
        }
        lineChartView.setData(datas, datas2);
        lineChartView.setBezierLine(true);
        lineChartView.setRulerYSpace(200);
        lineChartView.setStepSpace(30);
        scrollView.fullScroll(ScrollView.FOCUS_DOWN);//???????????????
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                //???????????????
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                //???????????????
                //scrollview.fullScroll(ScrollView.FOCUS_UP);
            }
        });
    }


    @Override
    protected int getView() {
        return R.layout.activity_main;

    }

    /**
     * ???????????????
     *
     * @param view
     */
    public void open(View view) {
        popupWindow.showAsDropDown(view, 0, 0);
    }

    public void saopinList(View view) { //????????????
        if (sb1.equals("?????????")) {
//            ToastUtils.showToast("??????????????????????????????");
            start1sp(false);//??????????????????
        } else {
            SaoPinB1 = true;
            start1sp(false);//??????????????????
        }

    }

    public void lishi1(View view) {//????????????1
        if (SPBEANLIST1.size() > 0) {
//      Set1StatusBar("????????????????????????");
            Intent intent = new Intent(MainActivity.this, SaoPinActivity.class);
            intent.putExtra("type", "1");
            startActivity(intent);
        } else {
//            Set1StatusBar("???????????????????????????");
            ToastUtils.showToast("?????????????????????");
        }
    }

    public void lishi2(View view) {//????????????2
        if (SPBEANLIST2.size() > 0) {
            Set1StatusBar("????????????????????????");
            Intent intent = new Intent(MainActivity.this, SaoPinActivity.class);
            intent.putExtra("type", "2");
            startActivity(intent);
        } else {
//            Set2StatusBar("???????????????????????????");
            ToastUtils.showToast("?????????????????????");
        }
    }

    public void saopinList2(View view) { //????????????
        if (sb2.equals("?????????")) {
//            ToastUtils.showToast("??????????????????????????????");
            start2sp(false);//??????????????????

        } else {
            SaoPinB2 = false;
            start2sp(false);//??????????????????
        }

//
    }

    /**
     * ????????????
     *
     * @param
     */
    public void stopLocation1() {
//        new CircleDialog.Builder((FragmentActivity) mContext)
//                .setTitle("")
//                .setText("?????????????????????1????")
//                .setTitleColor(Color.parseColor("#00acff"))
//                .setNegative("??????", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        MainUtils.StopLocation(IP1);
//                        GFFLAG1 = 2;
//                        MainUtils.OpenGF1(1, 2, handler);
//
//
//                        if (title.getText().toString().equals(getString(R.string.activity_saopintitle))) {//?????????????????????
//                            sp1MAX1value = "";
//                            sp1MAX2value = "";
//                            tv_r1.setText("");
//                            sb1locationgFlag1 = false;
//                        }
//                        if (title.getText().toString().equals(getString(R.string.activity_title))) {//?????????????????????
//                            sp1MAX1value = "";
//                            sp1MAX2value = "";
//                            tv_r1.setText("????????????");
//                            sb1locationgFlag1 = false;
//                        }
//                    }
//                })
//                .setPositive("??????", null)
//                .show();

        dialog = new Dialog(MainActivity.this, R.style.menuDialogStyleDialogStyle);
        inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_item_title, null);
        TextView tv_title = inflate.findViewById(R.id.tv_title);
        tv_title.setText("?????????????????????????");
        Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //??????????????????????????????

//                MainUtils.StopLocation(IP1);
                GFFLAG1 = 2;
//                MainUtils.OpenGF1(1, 2, handler);


                if (itemtype1.equals(TITLEZD)) {//?????????????????????
                    sp1MAX1value = "";
                    sp1MAX2value = "";
                    tv_r1.setText("");
                    sb1locationgFlag1 = false;
                    sb1type = "";
                }
                if (itemtype1.equals(TITLESD)) {//?????????????????????
                    sp1MAX1value = "";
                    sp1MAX2value = "";
                    tv_r1.setText("????????????");
                    sb1locationgFlag1 = false;
                    sb1type = "";
                    mysp1.setEnabled(true);
                }
                dialog.dismiss();
                dialog.cancel();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                DBManagerLog dbManagerLog = null;
                String string = "";
                if (btsstop1.getText().toString().equals("????????????")) {
                    string = "????????????";
                }
                if (btsstop1.getText().toString().equals("????????????")) {
                    string = "????????????";

                }
                //????????????
                try {
                    dbManagerLog = new DBManagerLog(MainActivity.this);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                LogBean logBean = new LogBean();
                logBean.setAssociated(LoginUtils.getId(MainActivity.this) + "");//??????ID
                logBean.setEvent(LoginUtils.setBase64(string));//????????????
                logBean.setSb(LoginUtils.setBase64("1"));
                String format = sdf.format(new Date());//????????????
                logBean.setDatetime(LoginUtils.setBase64(format));
                try {
                    dbManagerLog.insertConmmunit01Bean(logBean);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
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

    /**
     * ????????????
     *
     * @param
     */
    public void stopLocation2() {
//        new CircleDialog.Builder((FragmentActivity) mContext)
//                .setTitle("")
//                .setText("?????????????????????2????")
//                .setTitleColor(Color.parseColor("#00acff"))
//                .setNegative("??????", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        MainUtils.StopLocation(IP2);
//                        GFFLAG2 = 2;
//                        MainUtils.OpenGF2(2, 2, handler);
//                        if (title.getText().toString().equals(getString(R.string.activity_saopintitle))) {//?????????????????????
//                            sp2MAX1value = "";
//                            sp2MAX2value = "";
//                            tv_r2.setText("");
//                            sb2locationgFlag = false;
//                        }
//                        if (title.getText().toString().equals(getString(R.string.activity_title))) {//?????????????????????
//                            sp2MAX1value = "";
//                            sp2MAX2value = "";
//                            tv_r2.setText("????????????");
//                            sb2locationgFlag = false;
//                        }
//                    }
//                })
//                .setPositive("??????", null)
//                .show();


        dialog = new Dialog(MainActivity.this, R.style.menuDialogStyleDialogStyle);
        inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_item_title, null);
        TextView tv_title = inflate.findViewById(R.id.tv_title);
        tv_title.setText("?????????????????????2????");
        Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainUtils.StopLocation(IP2);
                GFFLAG2 = 2;
                MainUtils.OpenGF2(2, 2, handler);
                if (itemtype2.equals(TITLEZD)) {//?????????????????????
                    sp2MAX1value = "";
                    sp2MAX2value = "";
                    tv_r2.setText("");
                    sb2locationgFlag = false;
                    sb2type = "";
                }
                if (itemtype1.equals(TITLESD)) {//?????????????????????
                    sp2MAX1value = "";
                    sp2MAX2value = "";
                    tv_r2.setText("????????????");
                    sb2locationgFlag = false;
                    sb2type = "";
                    mysp2.setEnabled(true);

                }
                dialog.dismiss();
                dialog.cancel();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                DBManagerLog dbManagerLog = null;
                String string = "";
                if (btsstop2.getText().toString().equals("????????????")) {
                    string = "????????????";
                }
                if (btsstop2.getText().toString().equals("????????????")) {
                    string = "????????????";

                }
                //????????????
                try {
                    dbManagerLog = new DBManagerLog(MainActivity.this);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                LogBean logBean = new LogBean();
                logBean.setAssociated(LoginUtils.getId(MainActivity.this) + "");//??????ID
                logBean.setEvent(LoginUtils.setBase64(string));//????????????
                logBean.setSb(LoginUtils.setBase64("2"));
                String format = sdf.format(new Date());//????????????
                logBean.setDatetime(LoginUtils.setBase64(format));
                try {
                    dbManagerLog.insertConmmunit01Bean(logBean);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onDestroy() {
//        ActivityUtil.getInstance().removeActivity(this);
        System.exit(0);//????????????App
        super.onDestroy();
        if (dialog != null) {
            dialog.cancel();
        }
        if (mBackThread != null) {
            mBackThread.quitSafely();
            try {
                mBackThread.join();
                mBackThread = null;
                mBackHandler = null;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        handler.removeCallbacksAndMessages(null);
        timer1.cancel();
        timerLocation.cancel();
        timer2.cancel();
//        ActivityUtil.getInstance().exitSystem();
//        finish();
//        handler.removeCallbacks((Runnable) this);
    }


    /**
     * ????????????????????? zs ??????
     *
     * @param saopinBeanList
     */
    private void saopinSend1(List<SaopinBean> saopinBeanList, String zs, int SAOPIN) {
        Log.d(TAG, "listImsiListTruesad1: " + listImsiListTrue);
        List<Integer> list = new ArrayList<>();
        Log.d(TAG, "AsaopinSend1: " + saopinBeanList + "??????" + zs);
        try {
            saopinBeanList = dbManagersaopin.getStudent();
            Log.d(TAG, "AsaopinSend1: " + saopinBeanList + "??????" + zs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (saopinBeanList.size() > 0) {

            for (int i = 0; i < saopinBeanList.size(); i++) {
                if (saopinBeanList.get(i).getYy().equals(MyUtils.YYname(SAOPIN)) && saopinBeanList.get(i).getType() == 1) {
                    list.add(saopinBeanList.get(i).getDown());
                }
            }

            if (list.size() > 0) {
                if (list.size() > 10) {
                    ToastUtils.showToast("??????????????????10???");
                } else {
                    MainUtils.sendspSocket(list, IP1);
                    Log.d(TAG, "saopinSend1: " + list);
                }

            } else {
                ToastUtils.showToast("????????????" + zs + "?????????");

            }

        } else {
            ToastUtils.showToast("??????????????????");
        }

    }

    /**
     * ????????????????????? zs ??????
     *
     * @param saopinBeanList
     */
    private void saopinSend2(List<SaopinBean> saopinBeanList, String zs, int SAOPIN) {
        Log.d(TAG, "listImsiListTruesad1: " + listImsiListTrue);
        List<Integer> list = new ArrayList<>();
        Log.d(TAG, "AsaopinSend1: " + saopinBeanList + "??????" + zs);
        try {
            saopinBeanList = dbManagersaopin.getStudent();
            Log.d(TAG, "AsaopinSend1: " + saopinBeanList + "??????" + zs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (saopinBeanList.size() > 0) {

            for (int i = 0; i < saopinBeanList.size(); i++) {
                if (saopinBeanList.get(i).getYy().equals(MyUtils.YYname(SAOPIN)) && saopinBeanList.get(i).getType() == 1) {
                    list.add(saopinBeanList.get(i).getDown());
                }
            }

            if (list.size() > 0) {
                if (list.size() > 10) {
                    ToastUtils.showToast("??????????????????10???");
                } else {
                    MainUtils.sendspSocket(list, IP2);
                    Log.d(TAG, "saopinSend1: " + list);
                }

            } else {
                ToastUtils.showToast("????????????" + zs + "?????????");

            }

        } else {
            ToastUtils.showToast("??????????????????");
        }

    }


    //?????????????????????????????????????????????????????? ??????????????????????????????
    public boolean isFirstStart(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(
                "SHARE_APP_TAG", 0);
        Boolean isFirst = preferences.getBoolean("FIRSTStart", true);
        if (isFirst) {// ?????????
            preferences.edit().putBoolean("FIRSTStart", false).commit();
//            Log.i("GFA", "??????");
//            int band, int down, String plmn, int type, int up, String tf, String yy
            //??????????????????????????? ??????
            //??????TDD
            DbUtils.insertDB(MainActivity.this, 38, 37900, "46000", 0, 255, "TDD", "??????");
            DbUtils.insertDB(MainActivity.this, 38, 38098, "46000", 0, 255, "TDD", "??????");
            DbUtils.insertDB(MainActivity.this, 39, 38400, "46000", 0, 255, "TDD", "??????");
            DbUtils.insertDB(MainActivity.this, 39, 38544, "46000", 0, 255, "TDD", "??????");
            DbUtils.insertDB(MainActivity.this, 40, 38950, "46000", 0, 255, "TDD", "??????");
            DbUtils.insertDB(MainActivity.this, 41, 40936, "46000", 0, 255, "TDD", "??????");
            //??????FDD
            DbUtils.insertDB(MainActivity.this, 3, 1300, "46000", 0, 19300, "FDD", "??????");
            DbUtils.insertDB(MainActivity.this, 8, 3683, "46000", 0, 21683, "FDD", "??????");
            //??????FDD
            DbUtils.insertDB(MainActivity.this, 1, 375, "46001", 0, 18375, "FDD", "??????");
            DbUtils.insertDB(MainActivity.this, 3, 1650, "46001", 0, 19650, "FDD", "??????");
            //??????
            DbUtils.insertDB(MainActivity.this, 1, 100, "46011", 0, 18100, "FDD", "??????");
            DbUtils.insertDB(MainActivity.this, 3, 1825, "46011", 0, 19825, "FDD", "??????");
            DbUtils.insertDB(MainActivity.this, 3, 1850, "46011", 0, 19850, "FDD", "??????");
            DbUtils.insertDB(MainActivity.this, 5, 2452, "46011", 0, 20452, "FDD", "??????");
            //???????????????????????????
            //??????TDD
            DbUtils.insertDBSaopin(MainActivity.this, 37900, 255, 1);
            DbUtils.insertDBSaopin(MainActivity.this, 38098, 255, 1);
            DbUtils.insertDBSaopin(MainActivity.this, 38400, 255, 1);
            DbUtils.insertDBSaopin(MainActivity.this, 38544, 255, 1);
            DbUtils.insertDBSaopin(MainActivity.this, 38950, 255, 1);
            DbUtils.insertDBSaopin(MainActivity.this, 40936, 255, 1);
            //??????FDD
            DbUtils.insertDBSaopin(MainActivity.this, 1300, 19300, 2);
            DbUtils.insertDBSaopin(MainActivity.this, 3683, 21683, 2);
            //??????FDD
            DbUtils.insertDBSaopin(MainActivity.this, 375, 18375, 3);
            DbUtils.insertDBSaopin(MainActivity.this, 1650, 19650, 3);
            //??????FDD
            DbUtils.insertDBSaopin(MainActivity.this, 100, 18100, 4);
            DbUtils.insertDBSaopin(MainActivity.this, 1825, 19825, 4);
            DbUtils.insertDBSaopin(MainActivity.this, 1850, 19850, 4);
            DbUtils.insertDBSaopin(MainActivity.this, 2452, 20452, 4);
            //???????????????????????????
            DbUtils.xiaoqu(MainActivity.this);
            return true;
        } else {
//            startActivity(new Intent(MainActivity.this, MainActivity.class));
//            finish();
//            Log.i("GFA", "N???");
//            Toast.makeText(getApplicationContext(),"N???",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }


    private long mPressedTime = 0;

    @Override
    public void onBackPressed() {//?????????????????????
        long mNowTime = System.currentTimeMillis();//???????????????????????????
        Log.d("nzq", "onBackPressed: " + mNowTime);
        if ((mNowTime - mPressedTime) > 2000) {//???????????????????????????
            ToastUtils.showToast("????????????????????????");
            mPressedTime = mNowTime;
        } else {//????????????
            //????????????
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            DBManagerLog dbManagerLog = null;
            //????????????
            try {
                dbManagerLog = new DBManagerLog(MainActivity.this);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            LogBean logBean = new LogBean();
            logBean.setAssociated(LoginUtils.getId(MainActivity.this) + "");//??????ID
            logBean.setEvent(LoginUtils.setBase64("??????"));//????????????
            String format = sdf.format(new Date());//????????????
            logBean.setDatetime(LoginUtils.setBase64(format));
            try {
                dbManagerLog.insertConmmunit01Bean(logBean);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            this.finish();
//            System.exit(0);
        }
    }
}
