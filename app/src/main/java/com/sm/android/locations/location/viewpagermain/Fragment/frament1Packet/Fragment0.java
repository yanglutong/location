package com.sm.android.locations.location.viewpagermain.Fragment.frament1Packet;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import android.os.Message;
import android.speech.tts.TextToSpeech;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.sm.android.locations.location.Activity.Main.Adapter.RyImsiAdapter;
import com.sm.android.locations.location.Activity.Main.Adapter.RyZmAdapter;
import com.sm.android.locations.location.Activity.Main.IT.SaoPinCallback;
import com.sm.android.locations.location.Activity.Main.Objects.States;
import com.sm.android.locations.location.Activity.Main.View.SlideButton;
import com.sm.android.locations.location.Activity.SaoPin.SaoPinActivity.SaoPinActivity;
import com.sm.android.locations.location.Base.BaseFragment2;
import com.sm.android.locations.location.Constant.Constant;
import com.sm.android.locations.location.R;
import com.sm.android.locations.location.Test.setxq;
import com.sm.android.locations.location.Utils.DialogUtils;
import com.sm.android.locations.location.Utils.MainUtils.AddMenuUtils;
import com.sm.android.locations.location.Utils.MainUtils.MainUtils;
import com.sm.android.locations.location.Utils.MainUtils.MainUtils2;
import com.sm.android.locations.location.Utils.MainUtils.MainUtilsThread;
import com.sm.android.locations.location.Utils.MyUtils;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.AddPararBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.Conmmunit01Bean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.PararBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.PinConfigBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.SaopinBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.SpBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.ZmBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManager01;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerAddParam;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerPinConfig;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerZM;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerZY;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagersaopin;
import com.sm.android.locations.location.Utils.ToastUtils;
import com.sm.android.locations.location.Utils.View.LineChartView;
import com.sm.android.locations.location.Utils.pop.DLPopItem;
import com.sm.android.locations.location.Utils.pop.DLPopupWindow;
import com.sm.android.locations.location.viewpagermain.Fragment.SendUtils;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import static android.graphics.Color.parseColor;
import static com.blankj.utilcode.util.Utils.runOnUiThread;
import static com.sm.android.locations.location.App.App.context;
import static com.sm.android.locations.location.Constant.Constant.BLACKLOCATION1;
import static com.sm.android.locations.location.Constant.Constant.BLACKTIME1;
import static com.sm.android.locations.location.Constant.Constant.BLACKTIME2;
import static com.sm.android.locations.location.Constant.Constant.BLACKTIMESET1;
import static com.sm.android.locations.location.Constant.Constant.BOARDTEMPERATURE1;
import static com.sm.android.locations.location.Constant.Constant.CALLBLACKFLAG1;
import static com.sm.android.locations.location.Constant.Constant.CALLBLACKFLAG2;
import static com.sm.android.locations.location.Constant.Constant.CALLBLACKFLAGSET1;
import static com.sm.android.locations.location.Constant.Constant.CALLBLACKLOCATION1;
import static com.sm.android.locations.location.Constant.Constant.CALLBLACKOPEN1;
import static com.sm.android.locations.location.Constant.Constant.CALLBLACKOPEN2;
import static com.sm.android.locations.location.Constant.Constant.CLEAR;
import static com.sm.android.locations.location.Constant.Constant.DOWNPIN1;
import static com.sm.android.locations.location.Constant.Constant.DOWNPIN2;
import static com.sm.android.locations.location.Constant.Constant.GFFLAG1;
import static com.sm.android.locations.location.Constant.Constant.GFFLAG2;
import static com.sm.android.locations.location.Constant.Constant.IP1;
import static com.sm.android.locations.location.Constant.Constant.IP2;
import static com.sm.android.locations.location.Constant.Constant.SPBEANLIST1Fragment;
import static com.sm.android.locations.location.Constant.Constant.SPBEANLIST2Fragment;
import static com.sm.android.locations.location.Constant.Constant.TITLEZDZM;
import static com.sm.android.locations.location.Utils.MainUtils.MainUtils.DS;
import static com.sm.android.locations.location.Utils.MainUtils.MainUtils.i;
import static com.sm.android.locations.location.Utils.MainUtils.MainUtils.location;
import static com.sm.android.locations.location.Utils.MainUtils.MainUtils2.toIMSI;
import static com.sm.android.locations.location.Utils.MyUtils.removeDuplicate;
import static com.sm.android.locations.location.viewpagermain.Fragment.SendUtils.setzy;
import static com.sm.android.locations.location.viewpagermain.ViewPagerMainActivity.TYPE_VIEWPAGERMAIN;


@SuppressLint("ValidFragment")
public class Fragment0 extends BaseFragment2 implements FragmentView0.View {
    Thread thread;
    public int blackNUM1 = 1;
    public int blackNUM2 = 1;
    private boolean sb1pd = false;   //???????????????????????????????????????
    private boolean sb2pd = false;   //???????????????????????????????????????
    protected Context mContext;
    private String TAG = "Fragment0";
    FragmentView0.FragmentPresenter1 presenter1;
    //     Handler mHandler ;
    private static Timer timer1, timer2, timer_wendu, timerwifi;
    private Timer timer = null;//????????????imsi????????????
    private View rootView;//??????Fragment view
    boolean runThread = false;
    List<SaopinBean> saopinBeanList = null;
    Message message;
    Bundle bundle;
    DatagramPacket dp;
    byte[] buf;
    private DLPopupWindow popupWindow;//????????????
    private List<DLPopItem> mList = new ArrayList<>();
    ImageView imageView, iv_fengshan;
    TextView tv_wendu;
    ImageView iv_wendu;//????????????
    ImageButton ib_zhedie, ib_zhedie_zhenma;//???????????? ??????????????????
    private LineChartView lineChartView;
    List<Integer> list1quxian, list2quxian, list3quxian, list4quxian;
    SlideButton slideButton1, slideButton2;//????????????    ??????????????????

    private boolean slideButton1Flag = true;
    public static boolean LABAFLAG1 = true;
    public static boolean LABAFLAG2 = true;
    private RecyclerView ryIMSI, ry_zhenma;
    private ScrollView scrollView1, scrollView2;//??????????????????????????????
    public static boolean FENGSHANFLAG = true;//???????????? ??????????????????
    public static boolean qxzhedieFlag = false;//?????????????????????
    public static boolean zmFlag = false;//????????????????????????
    ImageView laba1, laba2;//??????????????????
    TextView tv_imsi1, tv_imsi2;//?????????IMSI
    CheckBox cbzt1_d, cbzt1_z, cbzt1_g, cbzt2_d, cbzt2_z, cbzt2_g;// ??????????????? ??????
    private boolean sb1zy1 = false;//???????????????????????????????????????
    private boolean sb1zy2 = false;//???????????????????????????????????????
    TextView tv_r1, tv_r2, b1_jl, tv_searchNum;
    TextView tv1_td, tv2_td;
    TextView tv1_wifi, tv2_wifi;
    private TextView textViews1;//?????????1
    private TextView textViews2;//?????????1
    private TextView Tv_type1, Tv_type2;
    EditText et_zhenmasearch;
    private int wifitrue = 0;
    private int wififalse = 0;
    String tf1 = "";
    String tf2 = "";
    public String sb1 = "";
    public String sb2 = "";
    Spinner mysp1, mysp2;
    private String spinnerS1 = "", spinnerS2 = "";//????????????????????????
    List<String> listsSp = new ArrayList<>();//   ?????????????????????
    DBManagerPinConfig dbManagerPinConfig = null;//????????????
    DBManagersaopin dbManagersaopin = null;
    private DBManagerAddParam dbManagerAddParam = null;//imsi
    List<PinConfigBean> pinConfigBeans = null;
    ArrayAdapter<String> adapter1, adapter2;
    Button bts_start1, bts_start2, btsstop1, btsstop2;
    List<AddPararBean> listImsiListTrue = null;//????????????????????????imsi
    List<AddPararBean> pararBeansList1 = new ArrayList<>();
    List<AddPararBean> dataAll = null;//??????IMSI???????????????
    RyImsiAdapter ryImsiAdapter;
    RyZmAdapter ryZmAdapter;
    Dialog dialog;
    View inflate;
    private int SAOPIN = 1;//?????????type??????1
    private int SAOPIN2 = 1;//?????????type??????1
    TextView sb1_jl, sb1_jl_pj1;
    private TextToSpeech textToSpeech = null;//????????????????????????
    private boolean sb1locationgFlag1 = false;
    private boolean LocationFlag1 = true;//??????
    private boolean sb1locationgFlag = false;
    private boolean sb2locationgFlag = false;
    private boolean LocationFlag2 = true;//??????
    private boolean timerRestartFlag1 = false;  //??????????????????1  ???????????????
    private boolean ClearFlag1 = true;//??????
    private boolean ClearFlags1 = true;//????????????
    private boolean ClearFlag2 = true;//??????
    private boolean ClearFlags2 = true;//????????????
    private boolean sb1FirstFlag = false;
    private boolean sb2FirstFlag = false;
    public static long startTime1 = 0;
    public static long startTime2 = 0;
    private boolean BlackFlag1 = true;//?????????
    private boolean BlackFlags1 = true;//???????????????
    private boolean LocationFlags1 = true;//????????????
    private boolean SaoPinB1 = false;
    private boolean SaoPinB2 = false;
    boolean sb1zhishiFlag = false;
    boolean sb2zhishiFlag = false;
    List<AddPararBean> sendListBlack = null;
    List<AddPararBean> sendListBlack2 = null;
    private boolean xiaoquFlag1 = true;//??????

    private boolean xiaoquFlags1 = true;//????????????
    private boolean xiaoquFlag2 = true;//??????

    private boolean xiaoquFlags2 = true;//????????????
    private boolean saopinshow1 = false;
    private boolean saopinshow2 = false;
    String imsi2 = "";
    String imsi1 = "";
    public String imsi11 = "";
    public String imsi12 = "";
    public String imsi21 = "";
    public String imsi22 = "";
    private String sp1MAX1value = "";//??????1?????????1?????????
    private String sp1MAX2value = "";//??????1?????????2?????????
    private String sp2MAX1value = "";//??????2?????????1?????????
    private String sp2MAX2value = "";//??????2?????????2?????????
    private boolean phone1sp = false;//????????????1?????????????????????  ????????????
    private boolean phone2sp = false;//????????????2?????????????????????
    List<States> listStates = new ArrayList<>();// ???????????????????????????
    private SpBean spBean1 = new SpBean();
    private SpBean spBean2 = new SpBean();
    private SpBean spBean154 = new SpBean();
    private SpBean spBean254 = new SpBean();

    @SuppressLint("NewApi")
    DecimalFormat dfBaoshu = new DecimalFormat("###");
    private Timer timerLocation = null;//????????????imsi????????????

    private void findViewS() {
        et_zhenmasearch = rootView.findViewById(R.id.et_zhenmasearch);
        tv_searchNum = rootView.findViewById(R.id.tv_searchNum);
        imageView = rootView.findViewById(R.id.iv_menu);//??????????????????
        imageView.setVisibility(View.VISIBLE);
        tv_wendu = rootView.findViewById(R.id.tv_wendu);//??????
        iv_wendu = rootView.findViewById(R.id.iv_wendu);//????????????
        iv_wendu.setVisibility(View.VISIBLE);
        tv_wendu.setVisibility(View.VISIBLE);
        iv_fengshan = rootView.findViewById(R.id.iv_fengshan);
        iv_fengshan.setVisibility(View.VISIBLE);
        iv_fengshan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter1.setFS(mContext, iv_fengshan, FENGSHANFLAG);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.showAsDropDown(view, 0, 0);
            }
        });
        lineChartView = (LineChartView) rootView.findViewById(R.id.line_chart_view);//?????????
        scrollView1 = rootView.findViewById(R.id.scrollView1);//??????
        scrollView2 = rootView.findViewById(R.id.scrollView2);//??????
        ib_zhedie = rootView.findViewById(R.id.ib_zhedie);//???????????????
        ib_zhedie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter1.setQxzhedie(mContext, ib_zhedie, lineChartView);

            }
        });
        ryIMSI = rootView.findViewById(R.id.ryIMSI);//IMSI??????
        ryIMSI.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        ry_zhenma = rootView.findViewById(R.id.ry_zhenma);//IMSI??????
        ll_zhenma_search = rootView.findViewById(R.id.ll_zhenma_search);//??????  ????????????
        ib_zhedie_zhenma = rootView.findViewById(R.id.ib_zhedie_zhenma);
        ib_zhedie_zhenma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter1.setZmzhedie(mContext, ib_zhedie_zhenma, ll_zhenma_search, ry_zhenma);
            }
        });
        slideButton1 = rootView.findViewById(R.id.slideButton1);
        slideButton2 = rootView.findViewById(R.id.slideButton2);
        slideButton1.setChecked(true);
        slideButton2.setChecked(true);

        slideButton1.setSmallCircleModel(parseColor("#10A146"), parseColor("#00000000"), parseColor("#10A146"), parseColor("#cccccc"));

        slideButton1.setOnCheckedListener(new SlideButton.SlideButtonOnCheckedListener() {
            @Override
            public void onCheckedChangeListener(boolean isChecked) {
//                ToastUtils.showToast(isChecked + "");
                slideButton1Flag = isChecked;
                if (isChecked) {
                    slideButton1.setChecked(true);
                    slideButton2.setChecked(true);
                    mysp1.setVisibility(View.GONE);

                    mysp2.setVisibility(View.GONE);
                    mysp1.setEnabled(false);
                    mysp2.setEnabled(false);
                } else {
                    slideButton1.setChecked(false);
                    slideButton2.setChecked(false);
                    mysp1.setVisibility(View.VISIBLE);
                    mysp2.setVisibility(View.VISIBLE);
                    mysp1.setEnabled(true);
                    mysp2.setEnabled(true);
                }
            }
        });
//        slideButton1.set
        slideButton2.setSmallCircleModel(parseColor("#FF6367"), parseColor("#00000000"), parseColor("#FF6367"), parseColor("#cccccc"));
        slideButton2.setOnCheckedListener(new SlideButton.SlideButtonOnCheckedListener() {
            @Override
            public void onCheckedChangeListener(boolean isChecked) {
//                ToastUtils.showToast(isChecked + "");
                slideButton1Flag = isChecked;
                if (isChecked) {
                    slideButton1.setChecked(true);
                    slideButton2.setChecked(true);
                    mysp1.setVisibility(View.GONE);
                    mysp2.setVisibility(View.GONE);
                    mysp1.setEnabled(false);
                    mysp2.setEnabled(false);
                } else {
                    slideButton1.setChecked(false);
                    slideButton2.setChecked(false);
                    mysp1.setVisibility(View.VISIBLE);
                    mysp2.setVisibility(View.VISIBLE);

                    mysp1.setEnabled(true);
                    mysp2.setEnabled(true);
                }
            }
        });
        sb1_jl = rootView.findViewById(R.id.sb1_jl);//??????1??????
        sb1_jl_pj1 = rootView.findViewById(R.id.sb1_jl_pj1);//??????1????????????
        laba1 = rootView.findViewById(R.id.laba1);//??????1
        laba1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter1.setVoice(mContext, 1, laba1, LABAFLAG1);
            }
        });
        laba2 = rootView.findViewById(R.id.laba2);//??????2
        laba2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter1.setVoice(mContext, 2, laba2, LABAFLAG2);
            }
        });
        tv_imsi1 = rootView.findViewById(R.id.tv_imsi1);//????????????1 imsi
        tv_imsi2 = rootView.findViewById(R.id.tv_imsi2);//????????????2 imsi
        cbzt1_d = rootView.findViewById(R.id.cbzt1_d);//????????????
        cbzt1_z = rootView.findViewById(R.id.cbzt1_z);//????????????
        cbzt1_g = rootView.findViewById(R.id.cbzt1_g);//????????????
        cbzt2_d = rootView.findViewById(R.id.cbzt2_d);
        cbzt2_z = rootView.findViewById(R.id.cbzt2_z);
        cbzt2_g = rootView.findViewById(R.id.cbzt2_g);

        tv1_wifi = rootView.findViewById(R.id.tv1_wifi);//????????????1
        tv2_wifi = rootView.findViewById(R.id.tv2_wifi);//????????????1
        textViews1 = rootView.findViewById(R.id.textViews1);
        textViews2 = rootView.findViewById(R.id.textViews2);
        Tv_type1 = rootView.findViewById(R.id.tv1_type);
        Tv_type2 = rootView.findViewById(R.id.tv2_type);

        tv_r1 = rootView.findViewById(R.id.tv_r1);
        tv_r2 = rootView.findViewById(R.id.tv_r2);
        tv1_td = rootView.findViewById(R.id.tv1_td);
        tv2_td = rootView.findViewById(R.id.tv2_tf);
        mysp1 = rootView.findViewById(R.id.mysp1);//??????1?????????
        mysp2 = rootView.findViewById(R.id.mysp2);//??????2?????????
        mysp1.setVisibility(View.GONE);
        mysp2.setVisibility(View.GONE);
        bts_start1 = rootView.findViewById(R.id.bts_start1);
        bts_start1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (slideButton1Flag) {//??????
                    Start1ZDlocation();
                } else {//??????
                    presenter1.startlocation(mContext, sb1, 1, slideButton1Flag, tf1, spinnerS1, spinnerS2);
                }


            }
        });
        bts_start2 = rootView.findViewById(R.id.bts_start2);
        btsstop1 = rootView.findViewById(R.id.btsstop1);
        btsstop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter1.setStop(mContext, 1, btsstop1);

            }
        });
        btsstop2 = rootView.findViewById(R.id.btsstop2);
        btsstop2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter1.setStop(mContext, 2, btsstop2);

            }
        });

        rootView.findViewById(R.id.bt_xiaoqushow1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saopinList();
            }
        });
        rootView.findViewById(R.id.bt_xiaoqushow2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saopinList2();
            }
        });
        rootView.findViewById(R.id.bt_lishi1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lishi1();
            }
        });
        rootView.findViewById(R.id.bt_lishi2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lishi2();
            }
        });
    }

    //    aaa
    int ii = 0;

    @SuppressLint("HandlerLeak")
//    private Runnable    mRunnable   = new Runnable() {
//
//        public void run() {
//            //???????????? ??????????????????Log????????????
//            Log.e("nzqmRunnable", Thread.currentThread().getName() + "Thread run");
////            MainUtils.ReceiveMain0(mHandler, message, bundle, timer1, timer2, dp, buf, mContext, runThread);//??????????????????
//
//        }
//
//    };
    private Handler mHandler = new Handler() {
        @SuppressLint("NewApi")
        public void handleMessage(final android.os.Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String s = bundle.getString("hehe");
//            Log.d("TAGhehe1", "handleMessage: ." + s + "TYPE_VIEWPAGERMAIN" + TYPE_VIEWPAGERMAIN);
            if (TYPE_VIEWPAGERMAIN == 0) {
                if (rootView == null) {
                    return;
                }
                switch (msg.what) {
                    case 8121:
                        //???????????????????????????
                        Log.d(TAG, "handleMessage120: ");

//                        String callblackflag1 = msg.getData().getString("CALLBLACKFLAG1");//?????????1
//                        if (CALLBLACKFLAG1 == true) {
//                            if (callblackflag1.equals("1")) {
////                            CALLBLACKFLAG1 = false;
//                                SetStatusBar("????????????????????????", 1);
//                                if (blackNUM1 <= 1) {
//                                    SetStatusBar("???????????????????????????", 1);
//                                    sb1clear();
//
//                                    BLACKTIME1 = System.currentTimeMillis();
//                                    CALLBLACKFLAG1 = false;
//                                    blackNUM1++;
//                                } else {
//                                    SetStatusBar("???????????????????????????????????????", 1);
//                                }
//
//                            }
//                        }
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
//                        break;
//                        ZmBean zmBean = null;
//                        DBManagerZM dbManagerZM = null;
//                        try {
//                            dbManagerZM = new DBManagerZM(mContext);
//                             zmBean = new ZmBean();
//                            zmBean.setImsi(imsi);
//
//                        } catch (SQLException e) {
//                            e.printStackTrace();
//                        }
//                        if (msg==null){
//                            Log.d("nzqmsg",msg.what+"");
//                            break;
//                        }
////                            String sb = msg.getData().getString("sb");
//                        if (TextUtils.isEmpty(msg.getData().getString("sb"))){
//                            Log.d("Anzqmsg",msg.what+"");
//                        }
//
//
//                        if (msg.getData().getString("sb").equals("1")) {
//                            if (!TextUtils.isEmpty(DOWNPIN1)) {
//                                zmBean.setDown(DOWNPIN1);
//                                zmBean.setSb(msg.getData().getString("sb"));
//                                zmBean.setTime(msg.getData().getString("time"));
//                                zmBean.setDatatime(msg.getData().getString("datetime"));
//                                try {
//                                    int i = dbManagerZM.insertAddZmBean(zmBean);
//                                } catch (SQLException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//
//                        }
//                        if (msg.getData().getString("sb").equals("2")) {
//                            if (!TextUtils.isEmpty(DOWNPIN2)) {
//                                zmBean.setDown(DOWNPIN2);
//                                zmBean.setSb(msg.getData().getString("sb"));
//                                zmBean.setTime(msg.getData().getString("time"));
//                                zmBean.setDatatime(msg.getData().getString("datetime"));
//                                try {
//                                    int i = dbManagerZM.insertAddZmBean(zmBean);
//                                } catch (SQLException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//
//                        }
                        //?????????????????????imsi??????
                        List<ZmBean> zmBeans = null;
                        DBManagerZM dbManagerZM = null;
                        try {
                            dbManagerZM = new DBManagerZM(context);


                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        try {
                            zmBeans = dbManagerZM.getDataAll();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        final List<Integer> listsize = new ArrayList<>();
                        for (int i = 0; i < zmBeans.size(); i++) {
                            listsize.add(i + 1);

                        }
                        Log.d("dbManagerZM", "handleMessage: " + i);
                        Log.d("dbManagerZM", "handleMessage:size " + zmBeans.toString());
                        final List<ZmBean> finalZmBeans3 = zmBeans;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ryZmAdapter = new RyZmAdapter(mContext, finalZmBeans3, listsize);//list???imsi?????????????????????
                            }
                        });

                        if (et_zhenmasearch.getText().length() == 0) {


//                        ry_zhenma.setAdapter(ryZmAdapter);
                            if (zmBeans.size() > 4) {

                                final List<ZmBean> finalZmBeans = zmBeans;
                                runOnUiThread(new Runnable() {
                                    @SuppressLint("SetTextI18n")
                                    @Override
                                    public void run() {
                                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                                        linearLayoutManager.setStackFromEnd(true);//recyview?????????????????????
                                        ry_zhenma.setLayoutManager(linearLayoutManager);
                                        tv_searchNum.setText("(" + finalZmBeans.size() + ")");
                                    }
                                });
                            } else {
                                final List<ZmBean> finalZmBeans1 = zmBeans;
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                                        linearLayoutManager.setStackFromEnd(false);//recyview?????????????????????
                                        ry_zhenma.setLayoutManager(linearLayoutManager);
                                        tv_searchNum.setText("(" + finalZmBeans1.size() + ")");
                                    }
                                });

                            }
                            final List<ZmBean> finalZmBeans2 = zmBeans;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ryZmAdapter = new RyZmAdapter(mContext, finalZmBeans2, listsize);//list???imsi?????????????????????
                                    ry_zhenma.setAdapter(ryZmAdapter);
                                }
                            });

                        }

//                        ry_zhenma.scrollToPosition(zmBeans.size());
//                        if (msg.getData().getString("zb").equals("1")) {
//                            //???????????????????????????
//                        }

//
//                } else {
                        sbImsiType(msg);
//                }

                        break;
                    case 300001:
//??????
                        Log.d("nzqlistStates", listStates + "");
                        if (listStates.size() == 0 && listStates == null) {
                            break;
                        }
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
                        final List<Integer> size = new ArrayList<>();
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
                                            byte[] outputData = MainUtilsThread.hexStringToByteArray(location(listFirstIMSI1.get(0), "04", sa, context, 1));
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
                                            byte[] outputData = MainUtilsThread.hexStringToByteArray(location(listFirstIMSI2.get(0), "04", sa, context, 2));
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
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ryImsiAdapter = new RyImsiAdapter(mContext, pararBeansList1, size, config, tv_imsi1, tv_imsi2);//list???imsi?????????????????????
                                ryIMSI.setAdapter(ryImsiAdapter);
                            }
                        });

                        listStates.clear();

                        break;
                    case 300002:
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (TextUtils.isEmpty(imsi1)) {
                                    tv_imsi1.setText("");
                                    sb1_jl.setText("");
//                            sb1_jl_pj1.setText("");
                                }
                                if (TextUtils.isEmpty(imsi2)) {
                                    tv_imsi2.setText("");
//                            sb1_j2.setText("");
//                            sb1_jl_pj2.setText("");
                                }
                            }
                        });

                        break;
                    case 100001:
                        String wifi = msg.getData().getString("msgWifi");
//                        Log.d(TAG, "handleMessa4age: " + wifi);
                        if (wifi.equals("true")) {//????????????
                            if (wifitrue == 0) {
//                        LoadingFalse();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv1_wifi.setText("WIFI??????: ??????");
                                        tv2_wifi.setText("WIFI??????: ??????");
                                    }
                                });

//                                Log.d(TAG, "handleMessage: ??????true");
                                wifitrue = 1;
                                wififalse = 0;
                            } else if (wifitrue == 1) {

                            }


                        }
                        if (wifi.equals("false")) {//????????????
                            if (wififalse == 0) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv1_wifi.setText("WIFI??????: ??????");
                                        tv2_wifi.setText("WIFI??????: ??????");
                                    }
                                });

//                        LoadingTrue("?????????????????????");
//                                Log.d(TAG, "handleMessage: zoulealese");
                                wififalse = 1;
                                wifitrue = 0;
                            }

                        }
                        break;
                    case 100110:
                        tf1 = msg.getData().getString("tf1");
                        if (!TextUtils.isEmpty(tf1)) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
//                                    Toast.makeText(getApplicationContext(), "FourActivity2 click button", Toast.LENGTH_SHORT).show();
                                    tv1_td.setText("????????????: " + tf1);
                                }
                            });

//                            Log.d(TAG, "handleMessage????????????1: " + tf1);
                        } else {


                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tv1_td.setText("????????????: ");
                                }
                            });
                        }
                        break;
                    case 100120:
                        Log.d("Fragmentnzq0", "??????" + msg.getData().getString("zt1"));
                        String zt1 = msg.getData().getString("zt1");
                        if (!TextUtils.isEmpty(zt1)) {
                            if (zt1.equals("0")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Tv_type1.setText("????????????:?????????");
                                    }
                                });

//
                            }
                            if (zt1.equals("1")) {

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Tv_type1.setText("????????????");
                                    }
                                });
                            }
                            if (zt1.equals("2")) {//??????
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
//                                        Toast.makeText(getApplicationContext(), "FourActivity2 click button", Toast.LENGTH_SHORT).show();
                                        Tv_type1.setText("????????????: ??????");
//                                SetStatusBar("??????", 1);
                                        tv_r1.setText("??????:");
                                        sb1 = "??????";
                                        //????????????1???????????????????????????????????????
                                        tv_imsi1.setText("");
                                        sb1_jl.setText("");
                                        if (slideButton1Flag) {
                                            slideButton1.setChecked(true);
                                            slideButton2.setChecked(true);
                                            mysp1.setVisibility(View.GONE);

                                            mysp2.setVisibility(View.GONE);
                                            mysp1.setEnabled(false);
                                            mysp2.setEnabled(false);
                                        } else {
                                            slideButton1.setChecked(false);
                                            slideButton2.setChecked(false);
                                            mysp1.setVisibility(View.VISIBLE);
                                            mysp2.setVisibility(View.VISIBLE);
                                            mysp1.setEnabled(true);
                                            mysp2.setEnabled(true);
                                        }
//                                mysp1.setVisibility(View.VISIBLE);
                                        if (sb1zy1 == false) {
//                                zy(1);//??????????????????
//                                ToastUtils.showToast(sb1pdStr);
                                            DBManagerZY dbManagerZY = null;
                                            try {
                                                dbManagerZY = new DBManagerZY(mContext);
                                            } catch (SQLException e) {
                                                e.printStackTrace();
                                            }
                                            cbzt1_g.setChecked(true);
//                                if (tf1.equals("TDD")) {
                                            int data = 0;
                                            try {
                                                data = dbManagerZY.foriddata(1, 1, 3);
                                            } catch (SQLException e) {
                                                e.printStackTrace();
                                            }
                                            setzy(data, 1);
                                            sb1zy1 = true;
//                                }

                                        }
                                        if (timerRestartFlag1 == true) {
                                            rRestart(1001388);
                                            timerRestartFlag1 = false;
                                        }
                                    }

                                });


                            }
                            if (zt1.equals("3")) {//?????????????????????
//                            Tv_type1.setText("????????????");

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                SetStatusBar("?????????????????????", 1);
                                                sb1 = "?????????????????????";
                                            }
                                        });
                                    }
                                });
                            }
                            if (zt1.equals("4")) {//???????????????

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Tv_type1.setText("????????????:????????????");
                                        SetStatusBar("???????????????", 1);
                                    }
                                });
                            }
                            if (zt1.equals("5")) {//????????? /?????????  ?????? ??????.
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Tv_type1.setText("????????????:?????????");
                                        SetStatusBar("?????????", 1);
                                        tv_r1.setText("??????:" + DOWNPIN1);
                                        sb1 = "?????????";
                                        mysp1.setVisibility(View.GONE);
                                    }
                                });

                            }

                        }
                    case 1001200:

                        String test = msg.getData().getString("test");
                        if (!TextUtils.isEmpty(test)) {

                            if (test.equals("1")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        SetStatusBar("??????????????????", 1);
                                    }
                                });

                                break;
                            }
                            if (test.equals("2")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        SetStatusBar("??????????????????", 1);
                                    }
                                });

                                break;
                            }
                            if (test.equals("3")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        SetStatusBar("GPS????????????", 1);
                                    }
                                });

                                break;
                            }
                            if (test.equals("4")) {

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        SetStatusBar("GPS????????????", 1);
                                    }
                                });
                                break;
                            }
                            if (test.equals("5")) {
//                                SetStatusBar("????????????", 1);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                    }
                                });
                                break;
                            }
                            if (test.equals("6")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        SetStatusBar("????????????", 1);
                                    }
                                });

                                break;
                            }
                            if (test.equals("7")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        SetStatusBar("??????????????????", 1);
                                    }
                                });

                                break;
                            }
                            if (test.equals("8")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        SetStatusBar("??????????????????", 1);

                                    }
                                });
                                break;
                            }
                            if (test.equals("9")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        SetStatusBar("???????????????", 1);
                                    }
                                });

//                                if (itemtype1.equals(TITLEZD)) {//?????????????????????
//                                    mysp1.setVisibility(View.GONE);
//                                    tv_r1.setText("????????????");
//
//                                } else {
//                                    mysp1.setVisibility(View.VISIBLE);
//                                    tv_r1.setText("????????????");
//                                }
                                break;
                            }
                            if (test.equals("10")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        SetStatusBar("???????????????", 1);
                                    }
                                });

                                break;
                            }
                            if (test.equals("11")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        SetStatusBar("GPS?????????", 1);
                                    }
                                });

                                break;
                            }
                            if (test.equals("12")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        SetStatusBar("?????????", 1);
                                        Tv_type1.setText("????????????:?????????");
                                    }
                                });


                                break;
                            }
                            if (test.equals("13")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        SetStatusBar("???????????????", 1);
                                        sb1 = "???????????????";
                                        sb1 = "???????????????";
                                    }
                                });

                                break;
                            }
                            if (test.equals("14")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        SetStatusBar("??????????????????", 1);
                                        sb1 = "??????????????????";
                                    }
                                });

                                break;
                            }
                        }
                        break;
                    case 100130://????????????????????????????????????
//                        SetStatusBar("?????????????????????", 1);
//                        if (slideButton1Flag) {//??????
//
//                        } else {//??????
////                            Log.d(TAG, "handleMessage2????????????: ");
//                            ClearFlag1 = false;
//                            ClearFlags1 = false;
//
////                            Set1StatusBar("?????????????????????");
////                            sb1type = "?????????????????????";
//                            CALLBLACKFLAG1 = false;
//                            BLACKTIMESET1 = System.currentTimeMillis();
//                            CALLBLACKFLAGSET1 = true;
//                            sendBlackList();
//                            Log.d("nzqsendstart1Clearset", "?????????????????????");
//                        }
                        break;

                    case 100131:
//                        SetStatusBar("???????????????????????????", 1);
//                        if (slideButton1Flag) {//??????
//                            BlackFlags1 = false;
////                            sb1type = "???????????????????????????";
//                            sb1LocationsSP();//??????1????????????
////                            Set1StatusBar("???????????????????????????");
//                            CALLBLACKFLAGSET1 = false;
//                            BLACKLOCATION1 = System.currentTimeMillis();
//                            CALLBLACKLOCATION1 = true;
//                            CALLBLACKFLAG1 = false;
//                        } else {//??????
//                            BlackFlags1 = false;
////                            sb1type = "???????????????????????????";
//                            sb1Locations();//??????1????????????
////                            Set1StatusBar("???????????????????????????");
//                            CALLBLACKFLAGSET1 = false;
//                            BLACKLOCATION1 = System.currentTimeMillis();
//                            CALLBLACKLOCATION1 = true;
//                            CALLBLACKFLAG1 = false;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//                                SetStatusBar("???????????????????????????", 1);
                            }
                        });
//                        }

                        break;


                    case 100136: //????????????????????????
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                SetStatusBar("????????????????????????", 1);
                                if (slideButton1Flag) {//??????
                                    EstablishVillageSS(sp1MAX1value);
                                } else {//??????

                                    EstablishVillage();
                                }
                            }
                        });


                        break;
                    case 100138: //??????????????????
//                    ToastUtils.showToast("??????????????????");
//                        Log.d(TAG, "handleMessagea: " + "??????????????????");

//                    if (itemtype1.equals(TITLEZD)) {//?????????????????????
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                SetStatusBar("?????????...", 1);
                            }
                        });
                        mHandler.postDelayed(new Runnable() {
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
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                SetStatusBar("??????????????????", 1);
                                if (slideButton1Flag) {//??????
//                            sb1clear();
                                    saopinSend1(saopinBeanList, tf1, SAOPIN);
                                } else {//??????
//                            Log.d(TAG, "sendBlackList:?????? " + spinnerS1);
//                            sb1Locations();
                                    sendBlackList();
                                }
                            }
                        });

                        break;

                    case 100140:
//                    ToastUtils.showToast("??????????????????");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                SetStatusBar("??????????????????", 1);
                                MainUtils.Restart();
                            }
                        });

                        break;
                    case 100141:
//                    ToastUtils.showToast("??????????????????");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                SetStatusBar("??????????????????", 1);
                            }
                        });

                        break;
                    case 100147:  //?????????

                        ii++;
                        String sb1jl = msg.getData().getString("sb1jl");
                        final String imsi1My = msg.getData().getString("imsi1");

                        Log.d(TAG, "sb1jlhandleMessage: " + sb1jl + "---" + imsi1My);
                        if (!TextUtils.isEmpty(sb1jl)) {
                            String format = "";
                            if (TextUtils.isEmpty(tf1)) {
                                break;
                            }
                            if (tf1.equals("TDD")) {
                                double v = Double.parseDouble(sb1jl);
                                double sA = v / 110 * 1000;
                                format = dfBaoshu.format(sA);
                                Log.d(TAG, "dfBaoshuhandleMessage: " + format);
                            } else {
                                double v = Double.parseDouble(sb1jl);
                                double sA = v / 164 * 1000;
                                format = dfBaoshu.format(sA);
                                Log.d(TAG, "dfBaoshuhandleMessage: " + format);
                            }

                            final String finalFormat = format;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    sb1_jl.setText(finalFormat);
                                    tv_imsi1.setText(imsi1My);
                                }
                            });
                            final String finalFormat1 = format;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (LABAFLAG1 == true) {
                                        startAuto(finalFormat1, true);
                                    }
                                }
                            });

                            final String finalFormat2 = format;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (list1quxian.size() > 0) {
                                        list1quxian.remove(0);
                                        list1quxian.add(Integer.parseInt(finalFormat2));
                                        double total = 0;
                                        for (int i = 0; i < list1quxian.size(); i++) {
                                            total += list1quxian.get(i);
                                        }
                                        double a = total / list1quxian.size();
                                        DecimalFormat df = new DecimalFormat("###");
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                            sb1_jl_pj1.setText(df.format(a));
                                        }

                                        setqxData(list1quxian, list2quxian, null, null);
                                    }
                                }
                            });
//

                        }
                        break;

                    case 100148:
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imsi1 = msg.getData().getString("imsi1");
                                Log.d("nzqimsi1", "run: " + imsi1);
                                tv_imsi1.setText(imsi1);
                                imsi2 = imsi1;
//                                if (imsi11.equals(imsi2)) {
//
//                                } else {
//                                    imsi11 = imsi2;
//                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                                    DBManagerLog dbManagerLog = null;
                                //????????????
//                            try {
//                                dbManagerLog = new DBManagerLog(mContext);
//                            } catch (SQLException e) {
//                                e.printStackTrace();
//                            }
//                            LogBean logBean = new LogBean();
//                            logBean.setAssociated(LoginUtils.getId(mContext) + "");//??????ID
//                            logBean.setEvent(LoginUtils.setBase64("??????"));//????????????
//                            logBean.setPd(LoginUtils.setBase64(DOWNPIN1));
//                            logBean.setSb(LoginUtils.setBase64(1 + ""));
//                            logBean.setSucessIMSI(LoginUtils.setBase64(imsi11));
//                            String formatD = sdf.format(new Date());//????????????
//                            logBean.setDatetime(LoginUtils.setBase64(formatD));
//                            try {
//                                dbManagerLog.insertConmmunit01Bean(logBean);
//                            } catch (SQLException e) {
//                                e.printStackTrace();
//                            }

//                                }


                                imsi1 = "";
                            }
                        });

                        break;

                    case 100152:
                        Log.d("nzqh1", "handleMessage: " + msg.getData().getString("sp1MAX1value"));
////                        if (itemtype1.equals(TITLEZD)) {//?????????????????????
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                            }
                        });
                        if (SaoPinB1 == false) {//??????????????????  ?????????
                            if (sb1.equals("?????????")) {
                                break;
                            }

                            if (SPBEANLIST1Fragment.size() > 0) {
//                                Set1StatusBar("????????????????????????");
                                Intent intent = new Intent(mContext, SaoPinActivity.class);
                                intent.putExtra("type", "1");
                                startActivity(intent);
//
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        SetStatusBar("???????????????????????????", 1);
                                    }
                                });

                            }

                        } else {
                            if (sb1.equals("?????????")) {
                                break;
                            }
                            //?????????????????????

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
                            if (TextUtils.isEmpty(dwon1s)) {
                                break;
                            }
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
                                            SetStatusBar("sp1MAX2value?????????", 1);
                                            spBean1 = spBean2;
//                                        ToastUtils.showToast("??????1??????" + "" + sp1MAX1value);
//
                                            if (sb1locationgFlag1 == false) {
                                                if (!sp1MAX2value.equals("0") && !TextUtils.isEmpty(sp1MAX2value)) {

//                                                sb1Clear();//??????1????????????


//                                                if (bts_start1.getText().equals(TITLEZDZM)) {//????????????
//                                                    MainUtils.sbzmLocation(IP1, mContext);
//                                                } else {
//                                                sb1clear();//??????1????????????
//                                                sb1Locations();// ??????????????????
                                                    sendBlackList();
//                                                }
                                                    Log.d("100152spBean1aaa", "100152handleMessage: " + spBean1);
//                                aaaaaa
//                                                tv_r1.setText("????????????" + sp1MAX2value + "");
//                                                        SetStatusBar("????????????" + sp1MAX2value, 1);

                                                } else {
//                                                ToastUtils.showToast("?????????0?????????");

                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            SetStatusBar("?????????????????????", 1);
                                                        }
                                                    });
                                                }

                                            } else {
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        ToastUtils.showToast("??????????????????");
                                                    }
                                                });

                                            }
//
                                        } else {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    ToastUtils.showToast("?????????");
//                                        Set1StatusBar("sp1MAX2value???????????????");
                                                    SetStatusBar("?????????????????????", 1);
                                                }
                                            });

                                        }
                                    } else {
//                                    Set1StatusBar("sp1MAX1value???sp2MAX1value??????");
                                        if (!TextUtils.isEmpty(sp1MAX1value)) {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    SetStatusBar("sp1MAX1value?????????", 1);
                                                }
                                            });

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

//                                                    if (bts_start1.getText().equals(TITLEZDZM)) {//????????????
//                                                        MainUtils.sbzmLocation(IP1, MainActivity.this);
//                                                    } else {
//                                                    sb1clear();//??????1????????????
//                                                    }
//                                                    sb1Locations();// ??????????????????
                                                        runOnUiThread(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                sendBlackList();
                                                            }
                                                        });

                                                        Log.d("spBean1aaa", "handleMessage: " + spBean1);
//                                aaaaaa
//                                                    tv_r1.setText("????????????" + sp1MAX1value + "");
//                                                            SetStatusBar("????????????" + sp1MAX1value, 1);
                                                    } else {
                                                        runOnUiThread(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                SetStatusBar("?????????????????????", 1);
                                                            }
                                                        });

                                                    }

                                                } else {
//                                                ToastUtils.showToast("?????????0?????????");

                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            SetStatusBar("?????????????????????", 1);
                                                        }
                                                    });
                                                }

                                            } else {
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        ToastUtils.showToast("??????????????????");
                                                    }
                                                });

                                            }
//
                                        } else {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    ToastUtils.showToast("?????????");
                                                    SetStatusBar("?????????????????????", 1);
                                                }
                                            });

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
//                                                if (bts_start1.getText().equals(TITLEZDZM)) {//????????????
//                                                    MainUtils.sbzmLocation(IP1, mContext);
//                                                } else {
//                                                sb1clear();//??????1????????????
//                                                sb1Locations();// ??????????????????
//                                                }
                                                    sendBlackList();
                                                    Log.d("spBean1aaa", "handleMessage: " + spBean1);
//                                aaaaaa
//                                                tv_r1.setText("????????????" + sp1MAX1value + "");
//                                                        SetStatusBar("????????????" + sp1MAX1value, 1);
                                                } else {
                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            SetStatusBar("?????????????????????", 1);

                                                        }
                                                    });

                                                }

                                            } else {
//                                            ToastUtils.showToast("?????????0?????????");

                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        SetStatusBar("?????????????????????", 1);
                                                    }
                                                });
                                            }

                                        } else {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    ToastUtils.showToast("??????????????????");
                                                }
                                            });

                                        }
//
                                    } else {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                ToastUtils.showToast("?????????");
                                                SetStatusBar("?????????????????????", 1);
                                            }
                                        });

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
//                                            if (bts_start1.getText().equals(TITLEZDZM)) {//????????????
//                                                MainUtils.sbzmLocation(IP1, mContext);
//                                            } else {
//                                            sb1clear();//??????1????????????
//                                            sb1Locations();// ??????????????????
//                                            }
                                                sendBlackList();
                                                Log.d("spBean1aaaA", "handleMessage: " + spBean1);
//                                aaaaaa
//                                            tv_r1.setText("????????????" + sp1MAX1value + "");
//                                                    SetStatusBar("????????????" + sp1MAX1value, 1);
                                            } else {
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        SetStatusBar("?????????????????????", 1);
                                                    }
                                                });

                                            }

                                        } else {
//                                        ToastUtils.showToast("?????????0?????????");

                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    SetStatusBar("?????????????????????", 1);
                                                }
                                            });
                                        }

                                    } else {

                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                ToastUtils.showToast("??????????????????");
                                            }
                                        });
                                    }
//
                                } else {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            ToastUtils.showToast("?????????");
//                                Set1StatusBar("??????2????????????????????????sp1MAX1value??????");
                                            SetStatusBar("?????????????????????", 1);
                                        }
                                    });

                                }
                            }

                        }


                    default:
                        break;
                }
            }

        }

        ;
    };

    private void sb1clear() {
        if (slideButton1Flag) {
            List<AddPararBean> sendListBlack = null;
            sendListBlack = new ArrayList<>();

//            String sb1zhishi = "";
            String yy = "";
            try {
                pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(sp1MAX1value)); //?????????????????????
                yy = pinConfigBeans.get(0).getYy();
            } catch (SQLException e) {
                e.printStackTrace();
            }
//                                Log.d(TAG, "sendBlackList:?????? ");
            if (listImsiListTrue.size() > 0 && listImsiListTrue != null) {
                for (int i = 0; i < listImsiListTrue.size(); i++) {
                    if (listImsiListTrue.get(i).getYy().equals(yy)) {
                        sendListBlack.add(listImsiListTrue.get(i));
                    }
                }
            }

            if (sendListBlack.size() > 0 && sendListBlack != null) {
//                                    Log.d(TAG, "sendBlackList: " + sendListBlack);
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
                            Log.e("nzqsendstart1Clear", "run: sendsocket?????????" + outputPacket.getPort() + "Ip??????:" + outputPacket.getAddress().toString() + "??????:" + outputPacket.getData());

                            try {
                                //                                    socket.send(outputPacket);
                                DS.send(outputPacket);
                                CALLBLACKFLAG1 = true;
                                BLACKTIME1 = System.currentTimeMillis();
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
            List<AddPararBean> sendListBlack = null;
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
//                                Log.d(TAG, "sendBlackList:?????? ");
                if (listImsiListTrue.size() > 0 && listImsiListTrue != null) {
                    for (int i = 0; i < listImsiListTrue.size(); i++) {
                        if (listImsiListTrue.get(i).getYy().equals(yy)) {
                            sendListBlack.add(listImsiListTrue.get(i));
                        }
                    }
                }

                if (sendListBlack.size() > 0 && sendListBlack != null) {
//                                    Log.d(TAG, "sendBlackList: " + sendListBlack);
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
                                Log.e("nzqsendstart1Clear", "run: sendsocket?????????" + outputPacket.getPort() + "Ip??????:" + outputPacket.getAddress().toString() + "??????:" + outputPacket.getData());

                                try {
                                    //                                    socket.send(outputPacket);
                                    DS.send(outputPacket);
                                    CALLBLACKFLAG1 = true;
                                    BLACKTIME1 = System.currentTimeMillis();
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

    private void sb2clear() {
        List<AddPararBean> sendListBlack = null;
        sendListBlack = new ArrayList<>();
        if (!TextUtils.isEmpty(spinnerS2)) {
//            String sb1zhishi = "";
            String yy = "";
            try {
                pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(spinnerS2)); //?????????????????????
                yy = pinConfigBeans.get(0).getYy();
            } catch (SQLException e) {
                e.printStackTrace();
            }
//                                Log.d(TAG, "sendBlackList:?????? ");
            if (listImsiListTrue.size() > 0 && listImsiListTrue != null) {
                for (int i = 0; i < listImsiListTrue.size(); i++) {
                    if (listImsiListTrue.get(i).getYy().equals(yy)) {
                        sendListBlack.add(listImsiListTrue.get(i));
                    }
                }
            }

            if (sendListBlack.size() > 0 && sendListBlack != null) {
//                                    Log.d(TAG, "sendBlackList: " + sendListBlack);
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
                            Log.e("nzqsendstart1Clear", "run: sendsocket?????????" + outputPacket.getPort() + "Ip??????:" + outputPacket.getAddress().toString() + "??????:" + outputPacket.getData());

                            try {
                                //                                    socket.send(outputPacket);
                                DS.send(outputPacket);
                                CALLBLACKFLAG2 = true;
                                BLACKTIME2 = System.currentTimeMillis();
//
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            ClearFlag2 = true;
                            ClearFlags2 = true;

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

    RyImsiAdapter.IDialogPinConfig config = new RyImsiAdapter.IDialogPinConfig() {
        @Override
        public void call(final String imsi, String sb) {
            if (sb.equals("1")) {
//
                dialog = new Dialog(mContext, R.style.menuDialogStyleDialogStyle);
                inflate = LayoutInflater.from(mContext).inflate(R.layout.dialog_item_title, null);
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
                                byte[] outputData = MainUtilsThread.hexStringToByteArray(location(imsi, "04", sa, mContext, 1));
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

                dialog = new Dialog(mContext, R.style.menuDialogStyleDialogStyle);
                inflate = LayoutInflater.from(mContext).inflate(R.layout.dialog_item_title, null);
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
                                byte[] outputData = MainUtilsThread.hexStringToByteArray(location(imsi, "04", sa, mContext, 2));
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
    LinearLayout layout, ll_zhenma_search;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        if (rootView == null) {
            new FragmentPersent0(this);
            rootView = LayoutInflater.from(mContext).inflate(R.layout.activity_mainview_pagermain, null);
            buf = new byte[1024];
            dp = new DatagramPacket(buf, buf.length);
            message = new Message();
            bundle = new Bundle();
            timer1 = new Timer();
            timer2 = new Timer();
            timer_wendu = new Timer();
            buf = new byte[1024];
            dp = new DatagramPacket(buf, buf.length);
            MainUtils.ReceiveMainWD0(mHandler, message, bundle, timer_wendu);//????????????
            MainUtils.TYPES(mHandler);
//            WifiMain(mHandler, message, bundle, timer1, timer2, dp, buf, mContext);//??????wifi??????
            findViewS();
            initData();
            AddITemMenu();
            new Thread(new Runnable() {
                @Override
                public void run() {
//                    MainUtils.getInstance().ReceiveMain0(mHandler, message, bundle, timer1, timer2, dp, buf, mContext, runThread);//??????????????????
                }
            }).start();
            //??????Handler????????????
//            mHandler.post(mRunnable);
//            App.setOnHandlerListener(new App.HandlerListener() {
//                @Override
//                public void heandleMessage(Message msg) {
//                    System.out.println("Fragment_msg0==== " + msg.what);
//                    System.out.println("??????1==== " + msg.what);
//                    mHandler.handleMessage(msg);
//                }
//            });
        }
        //?????????rootView?????????????????????????????????parent??? ?????????parent?????????parent?????????????????????????????????rootview?????????parent????????????
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
            new FragmentPersent0(this);
            rootView = LayoutInflater.from(mContext).inflate(R.layout.activity_mainview_pagermain, null);
            buf = new byte[1024];
            dp = new DatagramPacket(buf, buf.length);
            message = new Message();
            bundle = new Bundle();
            timer1 = new Timer();
            timer2 = new Timer();
            timer_wendu = new Timer();
            buf = new byte[1024];
            dp = new DatagramPacket(buf, buf.length);
            MainUtils.ReceiveMainWD0(mHandler, message, bundle, timer_wendu);//????????????
            MainUtils.TYPES(mHandler);
            WifiMain(mHandler, message, bundle, timer1, timer2, dp, buf, mContext);//??????wifi??????
//            MainUtils.ReceiveMain0(mHandler, message, bundle, timer1, timer2, dp, buf, mContext, runThread);//??????????????????
            findViewS();
            initData();
            AddITemMenu();

        }
        return rootView;
    }


    @Override
    public void initData() {
        super.initData();

        Log.d("tagFragment2", "initData: .");
        //?????????????????????
        list1quxian = new ArrayList<>();
        list2quxian = new ArrayList<>();
        setPresenter(presenter1);
        presenter1.qxListda(list1quxian, list2quxian, list3quxian, list4quxian);//?????????
        setqxData(list1quxian, list2quxian, list3quxian, list4quxian);
        CheckBoxOnclickSet();//????????????
        ImslList();
        listsSp.clear();
        try {
            dbManagerPinConfig = new DBManagerPinConfig(mContext);
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
//            Log.d(TAG, "pinConfigBeansinit: " + pinConfigBeans);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (timerLocation == null) {
            timerLocation = new Timer();
            //schedule??????????????????????????????????????????
            timerLocation.schedule(new TimerTask() {

                //run?????????????????????????????????????????????
                @Override
                public void run() {
                    Message message = new Message();
                    mHandler.sendMessage(message);
                    message.what = 300002;
                    Log.d(TAG, "handlerrun: " + 1);
                }
//            }, 0, 10000);
            }, 0, 8000);
        }
        initTTS();
        try {
            dbManagersaopin = new DBManagersaopin(mContext);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {//????????? ???????????????????????????
            DBManagerZM dbManagerZM = new DBManagerZM(mContext);
            List<ZmBean> zmBeans = dbManagerZM.getDataAll();
            if (zmBeans.size() > 0) {
                dbManagerZM.deleteall();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
                        if (zmBeanscontains.size() > 4) {
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
    }

    //imsi??????
    private void ImslList() {
        try {
            dbManagerAddParam = new DBManagerAddParam(mContext);
            dataAll = dbManagerAddParam.getDataAll();

            Log.d("nzqdataAll", "ImslList: " + dataAll.toString());
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

                    ryImsiAdapter = new RyImsiAdapter(mContext, listImsiListTrue, list1size, config, tv_imsi1, tv_imsi2);//list???imsi?????????????????????
                    ryIMSI.setAdapter(ryImsiAdapter);
                }

            }
            Log.d("addPararBeans", "init: " + dataAll);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
//                    Log.d(TAG, "2onItemSelected: " + spinnerS2);

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }

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

    /**
     * ???????????????
     */
    private void CheckBoxOnclickSet() {
        //??????1
        cbzt1_d.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cbzt1_z.setChecked(false);
                    cbzt1_g.setChecked(false);
                    try {
                        DBManagerZY dbManagerZY = new DBManagerZY(mContext);

                        int data = dbManagerZY.foriddata(1, 1, 1);
                        SendUtils.setzy(data, 1);
//
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
//
                } else {

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
                        DBManagerZY dbManagerZY = new DBManagerZY(mContext);
                        int data = dbManagerZY.foriddata(1, 1, 2);
                        SendUtils.setzy(data, 1);


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
                        DBManagerZY dbManagerZY = new DBManagerZY(mContext);
//
                        int data = dbManagerZY.foriddata(1, 1, 3);
                        SendUtils.setzy(data, 1);
//

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
                        DBManagerZY dbManagerZY = new DBManagerZY(mContext);
//
                        int data = dbManagerZY.foriddata(2, 2, 1);
                        SendUtils.setzy(data, 2);


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
                        DBManagerZY dbManagerZY = new DBManagerZY(mContext);
//
                        int data = dbManagerZY.foriddata(2, 2, 2);
                        SendUtils.setzy(data, 2);
//                        }

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
                        DBManagerZY dbManagerZY = new DBManagerZY(mContext);
                        int data = dbManagerZY.foriddata(2, 2, 3);
                        SendUtils.setzy(data, 2);


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
     */
    private void AddITemMenu() {
        popupWindow = new DLPopupWindow(mContext, mList, DLPopupWindow.STYLE_WEIXIN);
        AddMenuUtils.addmenu(mContext, popupWindow, mList);
        presenter1.setmenu(mContext, popupWindow, mList);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
//        initData();

//            App.setOnHandlerListener(new App.HandlerListener() {
//                @RequiresApi(api = Build.VERSION_CODES.N)
//                @SuppressLint("NewApi")
//                @Override
//                public void heandleMessage(Message msg) {
//                    System.out.println("Fragment_msg0==== " + msg.what);
//                    System.out.println("??????0==== " + msg.what);
////                    mHandler.sendEmptyMessage(msg);
//                    if (mHandler == null) {
//                        mHandler = new Handler();
//                    } else {
//                        mHandler.handleMessage(msg);
//                    }
//
//
//                }
//            });
            if (timer_wendu != null) {  //???????????????????????????
                timer_wendu.cancel();
                timer_wendu = new Timer();
                bundle = new Bundle();
                MainUtils.ReceiveMainWD0(mHandler, message, bundle, timer_wendu);//????????????
//                MainUtils.TYPES(mHandler);


                buf = new byte[1024];
                dp = new DatagramPacket(buf, buf.length);
                message = new Message();
                bundle = new Bundle();
                timer1 = new Timer();
                timer2 = new Timer();
                timer_wendu = new Timer();
                buf = new byte[1024];
                dp = new DatagramPacket(buf, buf.length);
//                MainUtils.ReceiveMainWD0(mHandler, message, bundle, timer_wendu);//????????????
                MainUtils.TYPES(mHandler);
                WifiMain(mHandler, message, bundle, timer1, timer2, dp, buf, mContext);//??????wifi??????
                initData();
//                if (thread==null){
//                    thread = new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            /**??????????????????*/
////                    Message message=new Message();
////                    message.what=1;
////                    mHandler.sendMessage(message);
//
////                            try {
////                                Thread.sleep(1000);
////                            } catch (InterruptedException e) {
////                                e.printStackTrace();
////                            }
//                            MainUtils.getInstance().ReceiveMain0(mHandler, message, bundle, timer1, timer2, dp, buf, mContext, runThread);//??????????????????
//                            Log.d("qiehuanhuilai", "run: " + "???????????????");
//
//                        }
//                    });
//                    thread.start();
//
//                }else {
//                    thread.start();
//                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        MainUtils.getInstance().ReceiveMain0(mHandler, message, bundle, timer1, timer2, dp, buf, mContext, runThread);//??????????????????
                    }
                }).start();

            } else {
            }
            //wifi
            if (timerwifi != null) {  //???????????????????????????
                timerwifi.cancel();
                timerwifi = new Timer();
                bundle = new Bundle();
//                WifiMain(mHandler, message, bundle, timer1, timer2, dp, buf, mContext);//??????wifi??????

            } else {

            }

        } else {
//            if (thread!=null){
////                thread.interrupt();
////                thread.stop();
//
//            }


            MainUtils.getInstance().closeSocket();

//            Thread thread
            if (timer_wendu != null) {
                timer_wendu.cancel();
            }
            if (timerwifi != null) {
                timerwifi.cancel();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        initData();

    }

    /**
     * ?????????????????????
     *
     * @param list1quxian
     * @param list2quxian
     */
    private void setqxData(List<Integer> list1quxian, List<Integer> list2quxian, List<Integer> list3quxian, List<Integer> list4quxian) {
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
        presenter1.SetStatusBar(mContext, scrollView1);
        presenter1.SetStatusBar(mContext, scrollView2);

    }

    /**
     * ????????????
     * ???: ????????????
     *
     * @param imageView
     * @param drawable
     */
    @Override
    public void WifiUp(ImageView imageView, Drawable drawable) {

    }

    /**
     * ????????????
     *
     * @param drawable
     * @param fs
     */
    @SuppressLint("NewApi")
    @Override
    public void fsup(Drawable drawable, boolean fs) {
        iv_fengshan.setBackground(drawable);
        FENGSHANFLAG = fs;
    }

    /**
     * ???????????????
     *
     * @param drawable
     */
    @SuppressLint("NewApi")
    @Override
    public void qxzhedieUp(Drawable drawable) {
        ib_zhedie.setBackground(drawable);
    }

    /**
     * ??????????????????
     *
     * @param drawable
     */
    @SuppressLint("NewApi")
    @Override
    public void ZmzhedieUp(Drawable drawable) {
        ib_zhedie_zhenma.setBackground(drawable);
    }

    /**
     * ????????????
     *
     * @param imageView
     * @param drawable
     * @param laba
     * @param device
     */
    @SuppressLint("NewApi")
    @Override
    public void VoiceUp(ImageView imageView, Drawable drawable, boolean laba, int device) {
        imageView.setBackground(drawable);
    }

    @Override
    public void MenuUp(Activity activity) {

    }

    @Override
    public void SpinnerUp(int devcice, String spStr, String tf) {

    }

    @Override
    public void UpEstablish(int device, String str) {

    }

    @Override
    public void StartupSDlocation(int device, String down) {

    }

    @Override
    public void StartupSDZM(int device, String down) {

    }

    @Override
    public void StopUp(String s, int i, String ip) {
        if (i == 1) {
            MainUtils.StopLocation(IP1);
        } else {
            MainUtils.StopLocation(IP2);
        }
    }

    @Override
    public void Start1SD() {
//        sb1clear();//?????????????????????
        ClearFlag1 = false;
        ClearFlags1 = false;

//                            Set1StatusBar("?????????????????????");
//                            sb1type = "?????????????????????";
        CALLBLACKFLAG1 = false;
        BLACKTIMESET1 = System.currentTimeMillis();
        CALLBLACKFLAGSET1 = true;
        sendBlackList();
        Log.d("nzqsendstart1Clearset", "?????????????????????");
    }

    @Override
    public void Start2SD() {

    }

    //????????????
    @Override
    public void Start1ZDlocation() {
        SaoPinB1 = true;
        DialogUtils.SaoPinDialog(1, mContext, inflate, saoPinCallback, tf1, true);
    }

    //???????????????1?????????
    public String upStr1 = "";
    //???????????????2?????????
    public String upStr2 = "";
    public String upStr3 = "";
    public String upStr4 = "";
    private StringBuffer stringBuffer1 = new StringBuffer();
    private StringBuffer stringBuffer2 = new StringBuffer();

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
//        if (i == 2) {
//            if (TextUtils.isEmpty(upStr2)){
//                stringBuffer2.append(str + "" + "\n");
//                textViews2.setText(stringBuffer2);
//                upStr2 = str;
//                scrollView2.fullScroll(scrollView2.FOCUS_DOWN);//???????????????
//            }else {
//                if (!upStr2.equals(str)) {
//                    stringBuffer2.append(str + "" + "\n");
//                    textViews2.setText(stringBuffer2);
//                    upStr2 = str;
//                    scrollView2.fullScroll(scrollView2.FOCUS_DOWN);//???????????????
//                }else {
//                    stringBuffer2.append(str + "" + "\n");
//                    textViews2.setText(stringBuffer2);
//                    upStr2 = str;
//                    scrollView2.fullScroll(scrollView2.FOCUS_DOWN);//???????????????
//                }
//            }
//
//        }
        if (i == 3) {
            if (!upStr3.equals(str)) {
//                stringBuffer3.append(str + "" + "\n");
//                textViews3.setText(stringBuffer3);
                upStr3 = str;
//                scrollView3.fullScroll(scrollView3.FOCUS_DOWN);//???????????????
            }
        }
        if (i == 4) {
            if (!upStr4.equals(str)) {
//                stringBuffer4.append(str + "" + "\n");
//                textViews4.setText(stringBuffer4);
                upStr4 = str;
//                scrollView4.fullScroll(scrollView4.FOCUS_DOWN);//???????????????
            }
        }

    }

    //?????????????????????
    private void rRestart(final int i) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                mHandler.sendMessage(message);
                message.what = i;
            }
        }).start();
    }

    private void sendBlackList() {//?????????????????????  1.????????????????????? imsi
//        Log.d(TAG, "sendBlackList:?????? " + spinnerS1);
        if (slideButton1Flag) {
            String yy = "";
            List<AddPararBean> sendListBlack = null;
            sendListBlack = new ArrayList<>();
            try {
                pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(sp1MAX1value)); //?????????????????????
                yy = pinConfigBeans.get(0).getYy();
            } catch (SQLException e) {
                e.printStackTrace();
            }
//            Log.d(TAG, "sendBlackList:?????? ");
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
                    sendBlackListRun(sendListBlack);
                }
//
            } else {
                CALLBLACKFLAGSET1 = false;
                ToastUtils.showToast("?????????????????????IMSI");
            }
        } else {
            List<AddPararBean> sendListBlack = null;
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
//            Log.d(TAG, "sendBlackList:?????? ");
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


    }

    //???????????????
    private void sendBlackListRun(List<AddPararBean> sendListBlack) {
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
            sendrun(str);//????????????
//            Log.d(TAG, "sendBlackListRun:???????????? ");
        }
        Log.d("nzqsendrun", "sendrun: " + str.toString());
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
                Log.e("nzqsendrun", "run: sendsocket?????????" + outputPacket.getPort() + "Ip??????:" + outputPacket.getAddress().toString() + "??????:" + outputPacket.getData());
                LogUtils.a("QsendBlackListRun??????:" + outputPacket.getData());
                try {
                    //                                    socket.send(outputPacket);
                    DS.send(outputPacket);
                    if (!sb1.equals("?????????") || sb1.equals("?????????") || sb1.equals("???????????????") || sb1.equals("?????????????????????"))
                        if (slideButton1Flag) {//??????
                            BlackFlags1 = false;
//                            sb1type = "???????????????????????????";
//                        sb1LocationsSP();//??????1????????????
//                            Set1StatusBar("???????????????????????????");
                            CALLBLACKFLAGSET1 = false;
                            BLACKLOCATION1 = System.currentTimeMillis();
                            CALLBLACKLOCATION1 = true;
                            CALLBLACKFLAG1 = false;
//                            mHandler.postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
                            Log.d("???????????????", "???????????????????????????????????????");

                            sb1LocationsSP();
//                                }
//                            }, 3000);
                        } else {//??????
                            BlackFlags1 = false;
//                            sb1type = "???????????????????????????";
//                        sb1Locations();//??????1????????????
//                            Set1StatusBar("???????????????????????????");
                            CALLBLACKFLAGSET1 = false;
                            BLACKLOCATION1 = System.currentTimeMillis();
                            CALLBLACKLOCATION1 = true;
                            CALLBLACKFLAG1 = false;

//                            mHandler.postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
                            Log.d("???????????????", "???????????????????????????????????????");

                            sb1Locations();
//                                }
//                            }, 3000);
                        }


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
//                            Log.d(TAG, "run:?????????1 ");
//                        interrupted();
                            Message message = new Message();
                            bundle.putString("zt1", "1");
                            message.setData(bundle);
                            mHandler.sendMessage(message);
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
//                        Log.d(TAG, "sendBlackList:?????? ");
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
                                    mHandler.sendMessage(message);
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
                    Log.e("QsendBlackListRu3", "run: nzqsend");
                    sendListBlack = new ArrayList<>();

                    if (listImsiListTrue.size() > 0 && listImsiListTrue != null) {
                        for (int i = 0; i < listImsiListTrue.size(); i++) {
//                    if (listImsiListTrue.get(i).getYy().equals(yy)) {
                            sendListBlack.add(listImsiListTrue.get(i));
//                    }
                        }
                        Log.d("QsendBlackListRun1", "run: ");
//                        sendBlackListRun(sendListBlack);

                    } else {
                        Log.d("QsendBlackListRun2", "run: ");
                        ToastUtils.showToast("?????????IMSI??????");

                        return;
                    }
                    if (sendListBlack.size() == 0) {
//                        Set1StatusBar("???????????????????????????IMSI");
//                        ToastUtils.showToast("???????????????????????????IMSI");
                        Log.d(TAG, "Arun: ");
                    } else {
                        Log.d(TAG, "Brun: ");
                        byte[] outputData = MainUtilsThread.hexStringToByteArray(location(sendListBlack.get(0).getImsi(), "04", sa, mContext, 1));
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
                                    mHandler.sendMessage(message);
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

    @Override
    public void setPresenter(FragmentView0.FragmentPresenter1 presenter) {
        this.presenter1 = presenter;
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
                DBManager01 dbManager01 = null;
                try {
                    dbManager01 = new DBManager01(mContext);
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
                    SetStatusBar("??????????????????", 1);
                }
                if (forid == null) {
//                    ToastUtils.showToast("??????1????????????");
                    SetStatusBar("??????1????????????", 1);
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

    private void startAuto(String data, boolean b) {
        // ???????????????????????????????????????????????????????????????????????????,1.0?????????

        textToSpeech.setPitch(1.f);
        Log.d("wpnqq", "startAuto: " + b);

        // ????????????
        textToSpeech.setSpeechRate(8.01f);
        textToSpeech.speak(data,//??????????????????????????????????????????????????????
                TextToSpeech.QUEUE_FLUSH, null);
    }

    private void initTTS() {
        //???????????????????????????
        textToSpeech = new TextToSpeech(mContext, new TextToSpeech.OnInitListener() {
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
        if (TextUtils.isEmpty(imsi)) {
            return;
        }

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
                    mHandler.sendMessage(message);
                    message.what = 300001;
                    Log.d(TAG, "handlerrun: " + 1);
                    Log.d(TAG, "handlerrun: " + 1);
                }
            }, 0, 11000);//IMSI
        } else {
            Log.d(TAG, "ahandlerrun: " + 1);
        }
    }


    SaoPinCallback saoPinCallback = new SaoPinCallback() {
        @Override
        public void sucess(int sb, int i) {
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
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                MainUtils.OpenGF1(1, 1, mHandler);
                                GFFLAG1 = 3;

                            }
                        }, 3000);

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

                        return;
                    }
                    if (i == 2) {//??????FDD   ????????????
                        mHandler.postDelayed(new Runnable() {
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

                                sp1MAX1value = "";
                                sp1MAX2value = "";
                                tv_r1.setText("");
//                                }

                            }
                        }, 3000);
                        return;
                    }
                    if (i == 3) {//??????FDD
                        mHandler.postDelayed(new Runnable() {
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

                                sp1MAX1value = "";
                                sp1MAX2value = "";
                                tv_r1.setText("");
//                                }

                            }
                        }, 3000);
                        return;
                    }
                    if (i == 4) {//??????FDD
                        mHandler.postDelayed(new Runnable() {
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

                                sp1MAX1value = "";
                                sp1MAX2value = "";
                                tv_r1.setText("");


                            }
                        }, 3000);
                        return;
                    }
                }
                if (tf1.equals("FDD")) {
                    if (i == 1) {//??????TDD
                        mHandler.postDelayed(new Runnable() {
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

                                sp1MAX1value = "";
                                sp1MAX2value = "";
                                tv_r1.setText("");
//                                }

                            }
                        }, 3000);
                        return;
                    }
                    if (i == 2) {//??????FDD

                        sb1zhishiFlag = true;

                        MainUtils.start1SNF(IP1, Constant.SNFFDD);

                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                saopinSend1(saopinBeanList, tf1, SAOPIN);

                                sp1MAX1value = "";
                                sp1MAX2value = "";
                                tv_r1.setText("");
//                                }

                            }
                        }, 3000);
                        return;
                    }
                    if (i == 3) {//??????FDD
//
                        sb1zhishiFlag = true;
//                                    MainUtils.start1SNF(IP1);
                        MainUtils.start1SNF(IP1, Constant.SNFFDD);

                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                saopinSend1(saopinBeanList, tf1, SAOPIN);

                                sp1MAX1value = "";
                                sp1MAX2value = "";
                                tv_r1.setText("");
//                                }

                            }
                        }, 3000);
                        return;
                    }
                    if (i == 4) {//??????FDD

                        sb1zhishiFlag = true;
//                                    MainUtils.start1SNF(IP1);
                        MainUtils.start1SNF(IP1, Constant.SNFFDD);

                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                saopinSend1(saopinBeanList, tf1, SAOPIN);

                                sp1MAX1value = "";
                                sp1MAX2value = "";
                                tv_r1.setText("");

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
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                GFFLAG2 = 3;

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
//
                            }
                        }, 3000);

//                                    sb1zhishiFlag = true;
                        return;
                    }
                    if (i == 2) {//??????FDD   ????????????
                        mHandler.postDelayed(new Runnable() {
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
//                                if (itemtype2.equals(TITLEZD)) {
                                sp2MAX1value = "";
                                sp2MAX2value = "";
                                tv_r2.setText("");
//                                }

                            }
                        }, 3000);
                        return;
                    }
                    if (i == 3) {//??????FDD
                        mHandler.postDelayed(new Runnable() {
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
//                                if (itemtype2.equals(TITLEZD)) {
                                sp2MAX1value = "";
                                sp2MAX2value = "";
                                tv_r2.setText("");
//                                }

                            }
                        }, 3000);
                        return;
                    }
                    if (i == 4) {//??????FDD
                        mHandler.postDelayed(new Runnable() {
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
//                                if (itemtype2.equals(TITLEZD)) {
                                sp2MAX1value = "";
                                sp2MAX2value = "";
                                tv_r2.setText("");
//                                }

                            }
                        }, 3000);
                        return;
                    }
                }
                if (tf2.equals("FDD")) {
                    if (i == 1) {//??????TDD
                        mHandler.postDelayed(new Runnable() {
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
//                                if (itemtype2.equals(TITLEZD)) {
                                sp2MAX1value = "";
                                sp2MAX2value = "";
                                tv_r2.setText("");
//                                }

                            }
                        }, 3000);
                        return;
                    }
                    if (i == 2) {//??????FDD
//                        ToastUtils.showToast("????????????FDD---");
                        sb2zhishiFlag = true;
//                                    MainUtils.start1SNF(IP1);
                        MainUtils.start1SNF(IP1, Constant.SNFFDD);

                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                saopinSend2(saopinBeanList, tf2, SAOPIN2);

//                                if (title.getText().toString().equals(getString(R.string.activity_saopintitle))) {//?????????????????????
//                                    sp2MAX1value = "";
//                                    sp2MAX2value = "";
//                                    tv_r2.setText("");
//                                }
//                                if (itemtype2.equals(TITLEZD)) {
                                sp2MAX1value = "";
                                sp2MAX2value = "";
                                tv_r2.setText("");
//                                }

                            }
                        }, 3000);
                        return;
                    }
                    if (i == 3) {//??????FDD
//                        ToastUtils.showToast("????????????FDD---");
                        sb2zhishiFlag = true;
//                                    MainUtils.start1SNF(IP1);
                        MainUtils.start1SNF(IP1, Constant.SNFFDD);

                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                saopinSend2(saopinBeanList, tf2, SAOPIN2);

//                                if (title.getText().toString().equals(getString(R.string.activity_saopintitle))) {//?????????????????????
//                                    sp2MAX1value = "";
//                                    sp2MAX2value = "";
//                                    tv_r2.setText("");
//                                }
//                                if (itemtype2.equals(TITLEZD)) {
                                sp2MAX1value = "";
                                sp2MAX2value = "";
                                tv_r2.setText("");
//                                }

                            }
                        }, 3000);
                        return;
                    }
                    if (i == 4) {//??????FDD
//                        ToastUtils.showToast("????????????FDD---");
                        sb2zhishiFlag = true;
//                                    MainUtils.start1SNF(IP1);
                        MainUtils.start1SNF(IP1, Constant.SNFFDD);

                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                saopinSend2(saopinBeanList, tf2, SAOPIN2);

//                                if (title.getText().toString().equals(getString(R.string.activity_saopintitle))) {//?????????????????????
//                                    sp2MAX1value = "";
//                                    sp2MAX2value = "";
//                                    tv_r2.setText("");
//                                }
//                                if (itemtype2.equals(TITLEZD)) {
                                sp2MAX1value = "";
                                sp2MAX2value = "";
                                tv_r2.setText("");
//                                }

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
//                        if (bts_start1.getText().equals(TITLEZDZM)) {//????????????
//                            MainUtils.sbzmLocation(IP1, mContext);  //??????????????????
//                        } else {//????????????
//                        sb1clear();
//                        }
//                        sb1Locations();// ??????????????????
                        sendBlackList();
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
//                        if (bts_start1.getText().equals(TITLEZDZM)) {//????????????
//                            MainUtils.sbzmLocation(IP1, mContext);  //??????????????????
//                        } else {//????????????
//                        sb1clear();
//                        }
//                        sb1Locations();// ??????????????????
                        sendBlackList();

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
//                        if (bts_start1.getText().equals(TITLEZDZM)) {//????????????
//                            MainUtils.sbzmLocation(IP1, mContext);  //??????????????????
//                        } else {//????????????
//                        sb1clear();
//                        sb1Locations();// ??????????????????
//                        }
                        sendBlackList();
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
//                        if (bts_start1.getText().equals(TITLEZDZM)) {//????????????
//                            MainUtils.sbzmLocation(IP1, mContext);  //??????????????????
//                        } else {//????????????
//                        sb1clear();
//                        sb1Locations();// ??????????????????
//                        }
                        sendBlackList();
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
//                        if (bts_start2.getText().equals(TITLEZDZM)) {//????????????
//                            MainUtils.sbzmLocation(IP2, mContext);  //??????????????????
//                        } else {//????????????
                        sb2clear();
//                        }
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
//                        if (bts_start2.getText().equals(TITLEZDZM)) {//????????????
//                            MainUtils.sbzmLocation(IP2, mContext);  //??????????????????
//                        } else {//????????????
                        sb2clear();
//                        }
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
//                        if (bts_start2.getText().equals(TITLEZDZM)) {//????????????
//                            MainUtils.sbzmLocation(IP2, mContext);  //??????????????????
//                        } else {//????????????
                        sb2clear();
//                        }
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
//                        if (bts_start2.getText().equals(TITLEZDZM)) {//????????????
//                            MainUtils.sbzmLocation(IP2, mContext);  //??????????????????
//                        } else {//????????????
                        sb2clear();
//                        }
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
                Intent intent = new Intent(mContext, SaoPinActivity.class);
                intent.putExtra("type", "1");
//                SPBEANLIST1.add(spBean);
                startActivity(intent);
            } else {
                Intent intent = new Intent(mContext, SaoPinActivity.class);
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

    //????????????1
    private void EstablishVillageSS(final String sp1MAX1value) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<PinConfigBean> pinConfigBeans = null;
                try {

                    if (TextUtils.isEmpty(sp1MAX1value)) {
                        ToastUtils.showToast("????????????????????????");
                        return;
                    }
                    pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(sp1MAX1value)); //?????????????????????

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Conmmunit01Bean forid = null;
                try {
                    DBManager01 dbManager01 = new DBManager01(mContext);
                    forid = dbManager01.forid(1);  //????????????1

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (forid == null) {
//                    ToastUtils.showToast("??????1????????????");
                    SetStatusBar("??????1????????????", 1);
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
                    dbManagerA = new DBManagerPinConfig(mContext);
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
                                                dbManagerPinConfig = new DBManagerPinConfig(mContext);
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
                                                        mHandler.sendMessage(message);
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
                                        dbManagerPinConfig = new DBManagerPinConfig(mContext);
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
                                                mHandler.sendMessage(message);
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
                                            dbManagerPinConfig = new DBManagerPinConfig(mContext);
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
                                                    mHandler.sendMessage(message);
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
                                    dbManagerPinConfig = new DBManagerPinConfig(mContext);
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
                                            mHandler.sendMessage(message);
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
                    dbManagerA = new DBManagerPinConfig(mContext);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                try {
                    DBManager01 dbManager01 = new DBManager01(mContext);
                    forid = dbManager01.forid(2);  //????????????2

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (forid == null) {
//                    ToastUtils.showToast("??????2????????????");
                    SetStatusBar("??????1????????????", 1);
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
                                                dbManagerPinConfig = new DBManagerPinConfig(mContext);
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
                                                        mHandler.sendMessage(message);
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
                                        dbManagerPinConfig = new DBManagerPinConfig(mContext);
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
                                                mHandler.sendMessage(message);
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
                                            dbManagerPinConfig = new DBManagerPinConfig(mContext);
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
                                                    mHandler.sendMessage(message);
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
                                    dbManagerPinConfig = new DBManagerPinConfig(mContext);
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
                                            mHandler.sendMessage(message);
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

    public void saopinList() { //????????????
        if (sb1.equals("?????????")) {
//            ToastUtils.showToast("??????????????????????????????");
            start1sp(false);//??????????????????
        } else {
            SaoPinB1 = false;
            start1sp(false);//??????????????????
        }

    }

    public void saopinList2() { //????????????
        if (sb2.equals("?????????")) {
//            ToastUtils.showToast("??????????????????????????????");
            start2sp(false);//??????????????????

        } else {
            SaoPinB2 = false;
            start2sp(false);//??????????????????
        }

//
    }

    //??????1??????
    public void start1sp(boolean phonesp) {//?????????????????????

        if (phonesp) {//??????????????????
            if ("??????".equals(sb1) || "?????????...".equals(sb1) || "?????????".equals(sb1) || "????????????".equals(sb1) || "?????????????????????".equals(sb1) || "???????????????".equals(sb1) || "?????????".equals(sb1)) {
//
                DialogUtils.SaoPinDialog(1, mContext, inflate, saoPinCallback, tf1, true);
                SaoPinB1 = true;
            } else {
                ToastUtils.showToast("???????????????");
            }
        } else {//????????????
            if ("??????".equals(sb1) || "?????????...".equals(sb1) || "?????????".equals(sb1) || "????????????".equals(sb1) || "?????????????????????".equals(sb1) || "???????????????".equals(sb1) || "?????????".equals(sb1)) {
//
                DialogUtils.SaoPinDialog2(1, mContext, inflate, saoPinCallback, tf1, false, sb1);
            } else {
                ToastUtils.showToast("???????????????");
            }
        }
    }

    //??????2??????
    public void start2sp(boolean phonesp) {
        if (phonesp) {

            if ("??????".equals(sb2) || "?????????...".equals(sb2) || "?????????".equals(sb2) || "????????????".equals(sb2) || "?????????????????????".equals(sb2) || "???????????????".equals(sb2) || "?????????".equals(sb2)) {
//
                DialogUtils.SaoPinDialog(2, mContext, inflate, saoPinCallback, tf2, true);
            } else {
                ToastUtils.showToast("???????????????");
            }
        } else {

            if ("??????".equals(sb2) || "?????????...".equals(sb2) || "?????????".equals(sb2) || "????????????".equals(sb2) || "?????????????????????".equals(sb2) || "???????????????".equals(sb2) || "?????????".equals(sb2)) {
//
                DialogUtils.SaoPinDialog2(2, mContext, inflate, saoPinCallback, tf2, false, sb2);
            } else {
                ToastUtils.showToast("???????????????");
            }
        }

    }

    public void saopinList(View view) { //????????????
        if (sb1.equals("?????????")) {
            ToastUtils.showToast("??????????????????????????????");
//            start1sp(false);//??????????????????
        } else {
            SaoPinB1 = false;
            start1sp(false);//??????????????????
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

    public void lishi1() {//????????????1
        if (SPBEANLIST1Fragment.size() > 0) {
//      Set1StatusBar("????????????????????????");
            Intent intent = new Intent(mContext, SaoPinActivity.class);
            intent.putExtra("type", "1");
            startActivity(intent);
        } else {
//            Set1StatusBar("???????????????????????????");
            ToastUtils.showToast("?????????????????????");
        }
    }

    public void lishi2() {//????????????2
        if (SPBEANLIST2Fragment.size() > 0) {
//            Set1StatusBar("????????????????????????");
            Intent intent = new Intent(mContext, SaoPinActivity.class);
            intent.putExtra("type", "2");
            startActivity(intent);
        } else {
//            Set2StatusBar("???????????????????????????");
            ToastUtils.showToast("?????????????????????");
        }
    }
}
