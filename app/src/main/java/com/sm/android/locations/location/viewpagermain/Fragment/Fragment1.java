package com.sm.android.locations.location.viewpagermain.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import android.os.Message;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.sm.android.locations.location.Activity.Communit.CommunitViewPagerActivity;
import com.sm.android.locations.location.Activity.Device.DeviceInfoActivity;
import com.sm.android.locations.location.Activity.Main.Adapter.RyZmAdapter;
import com.sm.android.locations.location.Activity.PinConfig.PinConfigViewPagerActivity;
import com.sm.android.locations.location.Activity.SaoPin.SaopinList.SaoPinSetingActivity;
import com.sm.android.locations.location.App.App;
import com.sm.android.locations.location.Base.BaseFragment2;
import com.sm.android.locations.location.Lunxun.SaopinList.LunxunSetingActivity;
import com.sm.android.locations.location.R;
import com.sm.android.locations.location.Utils.MainUtils.AddMenuUtils;
import com.sm.android.locations.location.Utils.MainUtils.DbUtils;
import com.sm.android.locations.location.Utils.MainUtils.MainUtils;
import com.sm.android.locations.location.Utils.MyUtils;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.LunxunBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.ZmBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerLunxun;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerZM;
import com.sm.android.locations.location.Utils.ToastUtils;
import com.sm.android.locations.location.Utils.pop.DLPopItem;
import com.sm.android.locations.location.Utils.pop.DLPopupWindow;
import com.sm.android.locations.location.zhenma.ZhenmaFenxiActivity;

import java.net.DatagramPacket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;
import static com.sm.android.locations.location.Constant.Constant.BOARDTEMPERATURE1;
import static com.sm.android.locations.location.Constant.Constant.DOWNPIN1;
import static com.sm.android.locations.location.Constant.Constant.DOWNPIN2;
import static com.sm.android.locations.location.Constant.Constant.IP1;
import static com.sm.android.locations.location.Utils.MainUtils.MainUtils.i;
import static com.sm.android.locations.location.viewpagermain.Fragment.frament1Packet.Fragment0.FENGSHANFLAG;
import static com.sm.android.locations.location.viewpagermain.ViewPagerMainActivity.TYPE_VIEWPAGERMAIN;


@SuppressLint("ValidFragment")
public class Fragment1 extends BaseFragment2 implements View.OnClickListener {
    private StringBuffer stringBuffer1 = null;
    private StringBuffer stringBuffer2 = null;
    private ScrollView scrollView1;//??????????????????????????????
    private TextView textViews1;//?????????1
    private ScrollView scrollView2;//??????????????????????????????2
    private TextView textViews2;//?????????2
    private Timer timerSB1 = null;//????????????imsi????????????
    private Timer timerSB2 = null;//????????????imsi????????????
    List<String> listDown1 = new ArrayList<>();
    List<String> listDown2 = new ArrayList<>();
    private boolean start1 = false;
    private boolean start2 = false;
    private String Mydwon1 = "";//??????????????????
    private String Mydwon2 = "";//??????????????????
    protected Context mContext;
    private static Timer timer1, timer2, timer_wendu, timerwifi;
    private View rootview;
    private String name;
    //     Handler mHandler ;
    private View rootView;//??????Fragment view
    boolean runThread = false;
    Message message;
    Bundle bundle;
    DatagramPacket dp;
    byte[] buf;
    private int wifitrue = 0;
    private int wififalse = 0;
    TextView tv1_wifi, tv2_wifi, titles, tv_wendu, tv_searchNum;
    ImageView iv_menu, iv_fengshan, iv_wendu;
    Button bts_start1, bts_start2;
    RyZmAdapter ryZmAdapter;
    EditText et_zhenmasearch;
    private RecyclerView ry_zhenma;
    private DBManagerZM dbManagerZM;
    DBManagerLunxun dbManagerLunxun = null;
    private DLPopupWindow popupWindow;//????????????
    private List<DLPopItem> mList = new ArrayList<>();
    public String startdatazm1;
    public String startdatazm2;
    public java.text.SimpleDateFormat DateFormat = new java.text.SimpleDateFormat("" + "yyyy-MM-dd HH:mm:ss");
    private TextView Tv_type1, Tv_type2;
    public String sb1 = "";
    public String sb2 = "";
    private boolean sb1zy1 = false;//???????????????????????????????????????
    private boolean sb1zy2 = false;//???????????????????????????????????????
    private Handler mHandler = new Handler() {
        @SuppressLint("NewApi")
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String s = bundle.getString("hehe");
//            Log.d("TAGhehe1", "handleMessage: ." + s + "TYPE_VIEWPAGERMAIN" + TYPE_VIEWPAGERMAIN);
            if (TYPE_VIEWPAGERMAIN == 1) {
                switch (msg.what) {
                    case 100001:
                        String wifi = msg.getData().getString("msgWifi");
//                        Log.d(TAG, "handleMessa4age: " + wifi);
                        if (wifi.equals("true")) {//????????????
                            if (wifitrue == 0) {
//                        LoadingFalse();
                                tv1_wifi.setText("WIFI??????: ??????");
                                tv2_wifi.setText("WIFI??????: ??????");
//                                Log.d(TAG, "handleMessage: ??????true");
                                wifitrue = 1;
                                wififalse = 0;
                            } else if (wifitrue == 1) {

                            }
                        }
                        if (wifi.equals("false")) {//????????????
                            if (wififalse == 0) {
                                tv1_wifi.setText("WIFI??????: ??????");
                                tv2_wifi.setText("WIFI??????: ??????");
//                        LoadingTrue("?????????????????????");
//                                Log.d(TAG, "handleMessage: zoulealese");
                                wififalse = 1;
                                wifitrue = 0;
                            }

                        }
                        break;
                    case 8153:
                        DecimalFormat df2;
                        if (!TextUtils.isEmpty(BOARDTEMPERATURE1)) {
                            Double i = Double.parseDouble(BOARDTEMPERATURE1);
                            df2 = new DecimalFormat("####");
                            if (i >= 60) {
                                iv_wendu.setBackground(getResources().getDrawable(R.mipmap.wendu_hong));
                                tv_wendu.setTextColor(getResources().getColor(R.color.redTextwendu));
                                tv_wendu.setText(df2.format(i) + "??C");
                                if (!FENGSHANFLAG) {
                                    FENGSHANFLAG = true;
                                    MainUtils.offOn(1);//????????????
                                    iv_fengshan.setBackground(getResources().getDrawable(R.mipmap.fengshan));
                                }
                            } else {
                                iv_wendu.setBackground(getResources().getDrawable(R.mipmap.wendu));
                                tv_wendu.setTextColor(getResources().getColor(R.color.white));
                                tv_wendu.setText(df2.format(i) + "??C");
                            }

                        }
//                        Log.d("Fragment0nzq", msg.obj.toString());
                        break;

                    case 30000:


//                if (bts_start1.getText().equals(TITLEZDZM)) {//????????????
                        //????????????????????????
//                    String down = msg.getData().getString("zmdown");
                        String imsi = msg.getData().getString("imsi");
                        Log.d("Qdown1", "handleMessage: " + "imsi===" + imsi + "----");

//                Log.d("Qdown1", "
//                handleMessage: " + "imsi===" + imsi + "----" + DOWNPIN1);
                        DBManagerZM dbManagerZM = null;
                        try {
                            dbManagerZM = new DBManagerZM(mContext);
                            ZmBean zmBean = new ZmBean();
                            zmBean.setImsi(imsi);
                            if (msg.getData().getString("sb").equals("1")) {
                                if (!TextUtils.isEmpty(DOWNPIN1)) {
                                    zmBean.setDown(DOWNPIN1);
                                    zmBean.setSb(msg.getData().getString("sb"));
                                    zmBean.setTime(msg.getData().getString("time"));
                                    zmBean.setDatatime(msg.getData().getString("datetime"));
                                    int i = dbManagerZM.insertAddZmBean(zmBean);
                                }

                            }
                            if (msg.getData().getString("sb").equals("2")) {
                                if (!TextUtils.isEmpty(DOWNPIN2)) {
                                    zmBean.setDown(DOWNPIN2);
                                    zmBean.setSb(msg.getData().getString("sb"));
                                    zmBean.setTime(msg.getData().getString("time"));
                                    zmBean.setDatatime(msg.getData().getString("datetime"));
                                    int i = dbManagerZM.insertAddZmBean(zmBean);
                                }

                            }

                            //?????????????????????imsi??????
                            List<ZmBean> zmBeans = dbManagerZM.getDataAll();
                            List<Integer> listsize = new ArrayList<>();
                            for (int i = 0; i < zmBeans.size(); i++) {
                                listsize.add(i + 1);

                            }
                            Log.d("dbManagerZM", "handleMessage: " + i);
                            Log.d("dbManagerZM", "handleMessage:size " + zmBeans.toString());
                            ryZmAdapter = new RyZmAdapter(mContext, zmBeans, listsize);//list???imsi?????????????????????
                            if (et_zhenmasearch.getText().length() == 0) {


//                        ry_zhenma.setAdapter(ryZmAdapter);
                                if (zmBeans.size() > 6) {
                                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                                    linearLayoutManager.setStackFromEnd(true);//recyview?????????????????????
                                    ry_zhenma.setLayoutManager(linearLayoutManager);
                                    tv_searchNum.setText("(" + zmBeans.size() + ")");
                                } else {
                                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                                    linearLayoutManager.setStackFromEnd(false);//recyview?????????????????????
                                    ry_zhenma.setLayoutManager(linearLayoutManager);
                                    tv_searchNum.setText("(" + zmBeans.size() + ")");
                                }
                                ryZmAdapter = new RyZmAdapter(mContext, zmBeans, listsize);//list???imsi?????????????????????
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
//                        sbImsiType(msg);
//                }

                        break;

                    case 9001://??????????????????1
                        timeSB1SD_Start1();
                        break;

                    case 100120:
                        Log.d("Fragmentnzq1", "??????" + msg.getData().getString("zt1"));
                        String zt1 = msg.getData().getString("zt1");
                        if (!TextUtils.isEmpty(zt1)) {
                            if (zt1.equals("0")) {
                                Tv_type1.setText("????????????:?????????");
                            }
                            if (zt1.equals("1")) {
                                Tv_type1.setText("????????????");
                            }
                            if (zt1.equals("2")) {//??????
                                Tv_type1.setText("????????????: ??????");
//                                SetStatusBar("??????", 1);
//                                tv_r1.setText("??????:");
                                sb1 = "??????";
                                //????????????1???????????????????????????????????????
//                                tv_imsi1.setText("");

//                                slideButton1Flag = isChecked;
//                                if (slideButton1Flag) {
//                                    slideButton1.setChecked(true);
//                                    slideButton2.setChecked(true);
//                                    mysp1.setVisibility(View.GONE);
//
//                                    mysp2.setVisibility(View.GONE);
//                                    mysp1.setEnabled(false);
//                                    mysp2.setEnabled(false);
//                                } else {
//                                    slideButton1.setChecked(false);
//                                    slideButton2.setChecked(false);
//                                    mysp1.setVisibility(View.VISIBLE);
//                                    mysp2.setVisibility(View.VISIBLE);
//                                    mysp1.setEnabled(true);
//                                    mysp2.setEnabled(true);
//                                }
//                                mysp1.setVisibility(View.VISIBLE);
//                                if (sb1zy1 == false) {
////                                zy(1);//??????????????????
////                                ToastUtils.showToast(sb1pdStr);
//                                    DBManagerZY dbManagerZY = null;
//                                    try {
//                                        dbManagerZY = new DBManagerZY(mContext);
//                                    } catch (SQLException e) {
//                                        e.printStackTrace();
//                                    }
//                                    cbzt1_g.setChecked(true);
////                                if (tf1.equals("TDD")) {
//                                    int data = 0;
//                                    try {
//                                        data = dbManagerZY.foriddata(1, 1, 3);
//                                    } catch (SQLException e) {
//                                        e.printStackTrace();
//                                    }
//                                    setzy(data, 1);
//                                    sb1zy1 = true;
////                                }
//
//                                }
//                                if (timerRestartFlag1 == true) {
//                                    rRestart(1001388);
//                                    timerRestartFlag1 = false;
//                                }
                            }
                            if (zt1.equals("3")) {//?????????????????????
//                            Tv_type1.setText("????????????");
                                SetStatusBar("?????????????????????", 1);
                                sb1 = "?????????????????????";
                            }
                            if (zt1.equals("4")) {//???????????????
                                Tv_type1.setText("????????????:????????????");
                                SetStatusBar("???????????????", 1);
                            }
                            if (zt1.equals("5")) {//????????? /?????????  ?????? ??????.
                                Tv_type1.setText("????????????:?????????");
                                SetStatusBar("?????????", 1);
//                                tv_r1.setText("??????:" + DOWNPIN1);
                                sb1 = "?????????";
//                                mysp1.setVisibility(View.GONE);
                            }

                        }
                    case 100136:
                        SetStatusBar("????????????????????????", 1);
                        break;
                    default:
                        break;
                }
            }

        }

        ;
    };

    private void timeSB1SD_Start1() {

        ToastUtils.showToast("??????????????????");

        start1 = true;
        new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                if (listDown1.size() == 0) {
                    LogUtils.a("???????????????????????????????????????");
                    return;
                }
                if (listDown1.size() == 1) {
                    if (start1) {
                        Mydwon1 = listDown1.get(0);
                        MainUtils.sbzmLocation(IP1, mContext);  //??????????????????
                        if (timerSB1 != null) {
                            timerSB1.cancel();
//                                onStop();
                        }

                        onStop();
                    } else {
                        return;
                    }

                    LogUtils.a("???????????? ??????1???????????????");


                }
                // ???????????? : ??????  ???????????? ?????? ??????  ??????     ??????   ?????? ?????? ?????????????????? ,     ?????????????????? ??????   ??????????????? ??????    ???????????? ?????????   ??????  ?????? ??????    ,  ???????????? ??? ??????????????????
//                SendSocket.start(1);//??????1????????????
//                ToastUtils.showToast("?????????");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if (listDown1.get(0).equals(Mydwon1)) {
                    if (start1) {
                        listDown1.remove(0);
                        listDown1.add(Mydwon1);
                        Mydwon1 = listDown1.get(0);
                        MainUtils.sbzmLocation(IP1, mContext);  //??????????????????

                        LogUtils.a("???????????????" + Mydwon1);
                    } else {
                        return;
                    }

                } else {
                    if (start1) {
                        Mydwon1 = listDown1.get(0);
                        listDown1.remove(0);
                        listDown1.add(Mydwon1);
                        MainUtils.sbzmLocation(IP1, mContext);  //??????????????????

                        LogUtils.a("???????????????" + Mydwon1);
                    } else {

                    }

                }


            }
        }).start();
    }


//    @Override
//    public View initView() {
//        mContext = getActivity();
//        view = LayoutInflater.from(mContext).inflate(R.layout.activity_main, null);
//        return view;
//    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        if (rootView == null) {
//            new FragmentPersent1(this);

            rootView = LayoutInflater.from(mContext).inflate(R.layout.activity_mainview_pagermain_f1, null);
            buf = new byte[1024];
            dp = new DatagramPacket(buf, buf.length);
            message = new Message();
            bundle = new Bundle();
            timer1 = new Timer();
            timer2 = new Timer();
            timer_wendu = new Timer();
            buf = new byte[1024];
            dp = new DatagramPacket(buf, buf.length);
            MainUtils.ReceiveMainWD1(mHandler, message, bundle, timer_wendu);//????????????
            MainUtils.TYPES(mHandler);
            WifiMain(mHandler, message, bundle, timer1, timer2, dp, buf, mContext);//??????wifi??????
//            MainUtils.ReceiveMain1(mHandler, message, bundle, timer1, timer2, dp, buf, mContext, runThread);//??????????????????
            findViewS();
            initData();
            AddITemMenu();


//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    MainUtils.getInstance().ReceiveMain0(mHandler, message, bundle, timer1, timer2, dp, buf, mContext, runThread);//??????????????????
//                }
//            }).start();
//            App.setOnHandlerListener(new App.HandlerListener() {
//                @Override
//                public void heandleMessage(Message msg) {
//                    System.out.println("MainActivity_msg1==== " + msg.what);
//                    System.out.println("??????1==== " + msg.what);
//                }
//            });
        }
        //?????????rootView?????????????????????????????????parent??? ?????????parent?????????parent?????????????????????????????????rootview?????????parent????????????
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
            rootView = LayoutInflater.from(mContext).inflate(R.layout.activity_mainview_pagermain_f1, null);
            buf = new byte[1024];
            dp = new DatagramPacket(buf, buf.length);
            message = new Message();
            bundle = new Bundle();
            timer1 = new Timer();
            timer2 = new Timer();
            timer_wendu = new Timer();
            buf = new byte[1024];
            dp = new DatagramPacket(buf, buf.length);
            MainUtils.ReceiveMainWD1(mHandler, message, bundle, timer_wendu);//????????????
            MainUtils.TYPES(mHandler);
            WifiMain(mHandler, message, bundle, timer1, timer2, dp, buf, mContext);//??????wifi??????
//            MainUtils.ReceiveMain1(mHandler, message, bundle, timer1, timer2, dp, buf, mContext, runThread);//??????????????????
            findViewS();
            initData();
            AddITemMenu();
//            App.setOnHandlerListener(new App.HandlerListener() {
//                @Override
//                public void heandleMessage(Message msg) {
//                    System.out.println("MainActivity_msg1==== " + msg.what);
//                    System.out.println("??????1==== " + msg.what);
//                }
//            });
        }
        return rootView;
    }

    private void AddITemMenu() {
        popupWindow = new DLPopupWindow(mContext, mList, DLPopupWindow.STYLE_WEIXIN);
        AddMenuUtils.addmenuZhenma(mContext, popupWindow, mList);
        popupWindow.setOnItemClickListener(new DLPopupWindow.OnItemClickListener() {
            @Override
            public void OnClick(int position) {
                if (mList.get(position).getText().equals("????????????")) {
                    startActivity(new Intent(mContext, DeviceInfoActivity.class));
                }
                if (mList.get(position).getText().equals("????????????")) {
//                    startActivity(new Intent(MainActivity.this, PinConfigActivity.class));//?????????
                    startActivity(new Intent(mContext, PinConfigViewPagerActivity.class));
                }
                if (mList.get(position).getText().equals("????????????")) {
                    startActivity(new Intent(mContext, SaoPinSetingActivity.class));
                }
                if (mList.get(position).getText().equals("????????????")) {
                    startActivity(new Intent(mContext, ZhenmaFenxiActivity.class));
                }
//                if (mList.get(position).getText().equals("??????IMSI")) {
//                    startActivity(new Intent(mContext, ParamActivity.class));
////                    startActivity(new Intent(MainActivity.this, Param2Activity.class));
//                }

                if (mList.get(position).getText().equals("????????????")) {
                    startActivity(new Intent(mContext, LunxunSetingActivity.class));
//                    ToastUtils.showToast("aaa");
                }


                if (mList.get(position).getText().equals("????????????")) {
//                    startActivity(new Intent(MainActivity.this, Community01Activity.class));
                    startActivity(new Intent(mContext, CommunitViewPagerActivity.class));
                }


//                              if (mList.get(position).getText().equals("????????????")) {
////                    startActivity(new Intent(MainActivity.this, DeviceInfoActivity.class));
////                    ToastUtils.showToast("????????????");
//                    DialogUtils.DataExport(context, inflate, zmDataCallBack);
//                }

            }
        });
    }

    private void findViewS() {
        tv_searchNum = rootView.findViewById(R.id.tv_searchNum);
        tv1_wifi = rootView.findViewById(R.id.tv1_wifi);//????????????1
        tv2_wifi = rootView.findViewById(R.id.tv2_wifi);//????????????1
        titles = rootView.findViewById(R.id.titles);
        tv_wendu = rootView.findViewById(R.id.tv_wendu);
        iv_menu = rootView.findViewById(R.id.iv_menu);
        iv_fengshan = rootView.findViewById(R.id.iv_fengshan);
        iv_wendu = rootView.findViewById(R.id.iv_wendu);
        //???????????????
        titles.setText("??????");
        titles.setVisibility(View.VISIBLE);
        tv_wendu.setVisibility(View.VISIBLE);
        iv_menu.setVisibility(View.VISIBLE);
        iv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.showAsDropDown(view, 0, 0);
            }
        });
        iv_fengshan.setVisibility(View.VISIBLE);
        iv_wendu.setVisibility(View.VISIBLE);
        ry_zhenma = rootView.findViewById(R.id.ry_zhenma);//IMSI??????
        et_zhenmasearch = rootView.findViewById(R.id.et_zhenmasearch);
        scrollView1 = rootView.findViewById(R.id.scrollView1);//??????
        scrollView2 = rootView.findViewById(R.id.scrollView2);//??????
        textViews1 = rootView.findViewById(R.id.textViews1);
        textViews2 = rootView.findViewById(R.id.textViews2);
        Tv_type1 = rootView.findViewById(R.id.tv1_type);
        Tv_type2 = rootView.findViewById(R.id.tv2_type);
        bts_start1 = rootView.findViewById(R.id.bts_start1);
        bts_start1.setOnClickListener(this);
        bts_start2 = rootView.findViewById(R.id.bts_start2);
        bts_start2.setOnClickListener(this);

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
                    DBManagerZM dbManagerZM = null;
                    try {
                        dbManagerZM = new DBManagerZM(mContext);
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
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                            linearLayoutManager.setStackFromEnd(true);//recyview?????????????????????
                            linearLayoutManager.setStackFromEnd(true);//recyview?????????????????????
                            ry_zhenma.setLayoutManager(linearLayoutManager);
                            tv_searchNum.setText("(" + listsize.size() + ")");
                        } else {
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                            linearLayoutManager.setStackFromEnd(false);//recyview?????????????????????
                            ry_zhenma.setLayoutManager(linearLayoutManager);
                            tv_searchNum.setText("(" + listsize.size() + ")");
                        }
                        ryZmAdapter = new RyZmAdapter(mContext, zmBeanscontains, listsize);//list???imsi?????????????????????
                        ry_zhenma.setAdapter(ryZmAdapter);
                    }
                } else {

                }

            }
        });

        try {
            dbManagerZM = new DBManagerZM(mContext);
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
            ryZmAdapter = new RyZmAdapter(mContext, zmBeans, listsize);//list???imsi?????????????????????
            ry_zhenma.setAdapter(ryZmAdapter);
        }

        if (listsize.size() > 6) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            linearLayoutManager.setStackFromEnd(true);//recyview?????????????????????
            ry_zhenma.setLayoutManager(linearLayoutManager);
            tv_searchNum.setText("(" + zmBeans.size() + ")");
        } else {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            linearLayoutManager.setStackFromEnd(false);//recyview?????????????????????
            ry_zhenma.setLayoutManager(linearLayoutManager);
            tv_searchNum.setText("(" + zmBeans.size() + ")");
        }
        ryZmAdapter = new RyZmAdapter(mContext, zmBeans, listsize);//list???imsi?????????????????????
        ry_zhenma.setAdapter(ryZmAdapter);

        xunhuanSetDataList();
        stringBuffer1 = new StringBuffer();//????????????
        stringBuffer2 = new StringBuffer();
    }

    @Override
    public void initData() {
        super.initData();
        findViewS();
        Log.d("tagFragment1", "initData: .");
    }

    /**
     * wifi???????????????
     *
     * @param handler
     * @param messages
     * @param bundle
     * @param timer1
     * @param timer2
     * @param dp
     * @param buf
     * @param context
     */
    private static boolean wifiFlag = false;

    /**
     * ??????wifi??????????????????
     *
     * @param handler
     * @param messages
     * @param bundle
     * @param timer1
     * @param timer2
     * @param dp
     * @param buf
     * @param context
     */
    public static void WifiMain(final Handler handler, Message messages, final Bundle bundle, final Timer timer1, final Timer timer2, final DatagramPacket dp, final byte[] buf, final Context context) {
        //wifi??????????????? ??????1wifi????????????
        final String WIFINAME = "\"" + "SMLOCATIONAP" + "\"";
        String sql = "exec p_skill '" + 1 + "'," + "\"" + 1 + "\"";
        Log.d("wifisql", sql);
        timerwifi = new Timer();
        timerwifi.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                String wifiName = MyUtils.getWifiName((Activity) context);
                Log.d("WIFINAMR", "wifiNamerun: " + wifiName);
                String wifi = "";
                if (WIFINAME.equals(wifiName)) {
                    wifiFlag = true;
                    wifi = "??????";
//                        Log.d(TAG, "run:??????");
                    bundle.putString("msgWifi", "true");
                    Message message = new Message();
                    message.setData(bundle);
                    handler.sendMessage(message);
                    message.what = 100001;
                    Log.d("nzqmsgWifi", "run: true");
                } else {
//                        ToastUtils.showToast("??????wifi??????");
                    wifi = "??????";
                    wifiFlag = false;
//                    Log.d(TAG, "run:??????");
                    bundle.putString("msgWifi", "false");
                    Message message = new Message();
                    message.setData(bundle);
                    handler.sendMessage(message);
                    message.what = 100001;
                    Log.d("nzqmsgWifi", "run: false");
                }
            }
        }, 0, 2000);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bts_start1:
                startdatazm1 = DateFormat.format(new Date());
                if (timerSB1 == null) {
                    timerSB1 = new Timer();
                    //schedule??????????????????????????????????????????
                    timerSB1.schedule(new TimerTask() {

                        //run?????????????????????????????????????????????
                        @Override
                        public void run() {
                            Message message = new Message();
                            mHandler.sendMessage(message);
                            message.what = 9001;
                            Log.d("fragment1", "handlerrunaaaa: " + 1);
                        }
//            }, 0, 10000);
                    }, 0, DbUtils.getTime(mContext, 1) * 1000);
                } else {
                    timerSB1.cancel();
                    timerSB1 = new Timer();
                    timerSB1.schedule(new TimerTask() {

                        //run?????????????????????????????????????????????
                        @Override
                        public void run() {
                            Message message = new Message();
                            mHandler.sendMessage(message);
                            message.what = 9001;
                            Log.d("fragment1", "handlerrunaaaa: " + 1);
                        }
//            }, 0, 10000);
                    }, 0, DbUtils.getTime(mContext, 1) * 1000);
                }
                break;

            case R.id.bts_start2:


                break;
        }

    }

    private void xunhuanSetDataList() {
        try {
            dbManagerLunxun = new DBManagerLunxun(mContext);
            List<LunxunBean> listall = dbManagerLunxun.getStudent();
            for (int j = 0; j < listall.size(); j++) {
                if (listall.get(j).getYy().equals("??????TDD") && listall.get(j).getType() == 1) {
                    listDown1.add(listall.get(j).getDown() + "");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //??????2
        try {
            dbManagerLunxun = new DBManagerLunxun(mContext);
            List<LunxunBean> listall = dbManagerLunxun.getStudent();
            for (int j = 0; j < listall.size(); j++) {
                if (listall.get(j).getYy().equals("??????FDD") && listall.get(j).getType() == 1) {
                    listDown2.add(listall.get(j).getDown() + "");
                }
            }
            for (int j = 0; j < listall.size(); j++) {
                if (listall.get(j).getYy().equals("??????") && listall.get(j).getType() == 1) {
                    listDown2.add(listall.get(j).getDown() + "");
                }
            }
            for (int j = 0; j < listall.size(); j++) {
                if (listall.get(j).getYy().equals("??????") && listall.get(j).getType() == 1) {
                    listDown2.add(listall.get(j).getDown() + "");
                }
            }
            LogUtils.a("??????2 listDown2" + listDown2.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //???????????????1?????????
    public String upStr1 = "";
    //???????????????2?????????
    public String upStr2 = "";
    public String upStr3 = "";
    public String upStr4 = "";

    private void SetStatusBar(String str, int i) {
        if (i == 1) {
            if (!upStr1.equals(str)) {
                stringBuffer1.append(str + "" + "\n");
                textViews1.setText(stringBuffer1);
                upStr1 = str;
                scrollView1.fullScroll(scrollView1.FOCUS_DOWN);//???????????????
            }
        }

        if (i == 2) {
            if (!upStr2.equals(str)) {
                stringBuffer2.append(str + "" + "\n");
                textViews2.setText(stringBuffer2);
                upStr2 = str;
                scrollView2.fullScroll(scrollView2.FOCUS_DOWN);//???????????????
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        initData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            App.setOnHandlerListener(new App.HandlerListener() {
                @Override
                public void heandleMessage(Message msg) {
                    System.out.println("Fragment_msg1==== " + msg.what);
                    System.out.println("??????1==== " + msg.what);

                    switch (msg.what) {
                        case 100110:
                            String tf1 = msg.getData().getString("tf1");

                            Log.d("fragment1-tf1", "heandleMessage: " + tf1);
                            break;
                    }
                }
            });
            if (timer_wendu != null) {  //???????????????????????????
                timer_wendu.cancel();
                timer_wendu = new Timer();
                bundle = new Bundle();
                MainUtils.ReceiveMainWD1(mHandler, message, bundle, timer_wendu);//????????????
                MainUtils.TYPES(mHandler);
            } else {
                bundle = new Bundle();
                MainUtils.ReceiveMainWD1(mHandler, message, bundle, timer_wendu);//????????????
                MainUtils.TYPES(mHandler);
            }


            //wifi
            if (timerwifi != null) {  //???????????????????????????
                timerwifi.cancel();
                timerwifi = new Timer();
                bundle = new Bundle();
                WifiMain(mHandler, message, bundle, timer1, timer2, dp, buf, mContext);//??????wifi??????

            } else {

            }

            iv_wendu.postDelayed(new Runnable() {
                @Override
                public void run() {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
//                            MainUtils.getInstance().ReceiveMain0(mHandler, message, bundle, timer1, timer2, dp, buf, mContext, runThread);//??????????????????
                        }
                    }).start();
                }
            },1000);


        } else {

            MainUtils.getInstance().closeSocket();
            if (timer_wendu != null) {
                timer_wendu.cancel();
            }
            if (timerwifi != null) {
                timerwifi.cancel();
            }
        }
    }
}
