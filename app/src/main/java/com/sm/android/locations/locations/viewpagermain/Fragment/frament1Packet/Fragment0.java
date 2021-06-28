package com.sm.android.locations.locations.viewpagermain.Fragment.frament1Packet;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.sm.android.locations.locations.Activity.Main.Adapter.RyImsiAdapter;
import com.sm.android.locations.locations.Activity.Main.Adapter.RyZmAdapter;
import com.sm.android.locations.locations.Activity.Main.IT.SaoPinCallback;
import com.sm.android.locations.locations.Activity.Main.MainActivity;
import com.sm.android.locations.locations.Activity.Main.Objects.States;
import com.sm.android.locations.locations.Activity.Main.View.SlideButton;
import com.sm.android.locations.locations.Activity.SaoPin.SaoPinActivity.SaoPinActivity;
import com.sm.android.locations.locations.App.App;
import com.sm.android.locations.locations.Base.BaseFragment2;
import com.sm.android.locations.locations.Constant.Constant;
import com.sm.android.locations.locations.R;
import com.sm.android.locations.locations.Test.setxq;
import com.sm.android.locations.locations.Utils.DialogUtils;
import com.sm.android.locations.locations.Utils.LoginUtils.LoginUtils;
import com.sm.android.locations.locations.Utils.MainUtils.AddMenuUtils;
import com.sm.android.locations.locations.Utils.MainUtils.DbUtils;
import com.sm.android.locations.locations.Utils.MainUtils.MainUtils;
import com.sm.android.locations.locations.Utils.MainUtils.MainUtils2;
import com.sm.android.locations.locations.Utils.MainUtils.MainUtilsThread;
import com.sm.android.locations.locations.Utils.MyUtils;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.AddPararBean;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.Conmmunit01Bean;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.LogBean;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.PararBean;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.PinConfigBean;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.SaopinBean;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.SpBean;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.ZmBean;
import com.sm.android.locations.locations.Utils.OrmSqlLite.DBManager01;
import com.sm.android.locations.locations.Utils.OrmSqlLite.DBManagerAddParam;
import com.sm.android.locations.locations.Utils.OrmSqlLite.DBManagerLog;
import com.sm.android.locations.locations.Utils.OrmSqlLite.DBManagerPinConfig;
import com.sm.android.locations.locations.Utils.OrmSqlLite.DBManagerZM;
import com.sm.android.locations.locations.Utils.OrmSqlLite.DBManagerZY;
import com.sm.android.locations.locations.Utils.OrmSqlLite.DBManagersaopin;
import com.sm.android.locations.locations.Utils.ToastUtils;
import com.sm.android.locations.locations.Utils.View.LineChartView;
import com.sm.android.locations.locations.Utils.pop.DLPopItem;
import com.sm.android.locations.locations.Utils.pop.DLPopupWindow;
import com.sm.android.locations.locations.viewpagermain.Fragment.SendUtils;
import com.sm.android.locations.locations.viewpagermain.Fragment.frament1Packet.ViewSaoPinCallback.ViewSaoPinCallback;

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

import static android.graphics.Color.parseColor;
import static com.blankj.utilcode.util.Utils.runOnUiThread;
import static com.sm.android.locations.locations.App.App.context;
import static com.sm.android.locations.locations.Constant.Constant.BLACKLOCATION1;
import static com.sm.android.locations.locations.Constant.Constant.BLACKTIME1;
import static com.sm.android.locations.locations.Constant.Constant.BLACKTIME2;
import static com.sm.android.locations.locations.Constant.Constant.BLACKTIMESET1;
import static com.sm.android.locations.locations.Constant.Constant.BOARDTEMPERATURE1;
import static com.sm.android.locations.locations.Constant.Constant.CALLBLACKFLAG1;
import static com.sm.android.locations.locations.Constant.Constant.CALLBLACKFLAG2;
import static com.sm.android.locations.locations.Constant.Constant.CALLBLACKFLAGSET1;
import static com.sm.android.locations.locations.Constant.Constant.CALLBLACKLOCATION1;
import static com.sm.android.locations.locations.Constant.Constant.CALLBLACKOPEN1;
import static com.sm.android.locations.locations.Constant.Constant.CALLBLACKOPEN2;
import static com.sm.android.locations.locations.Constant.Constant.CLEAR;
import static com.sm.android.locations.locations.Constant.Constant.DOWNPIN1;
import static com.sm.android.locations.locations.Constant.Constant.DOWNPIN2;
import static com.sm.android.locations.locations.Constant.Constant.GFFLAG1;
import static com.sm.android.locations.locations.Constant.Constant.GFFLAG2;
import static com.sm.android.locations.locations.Constant.Constant.IP1;
import static com.sm.android.locations.locations.Constant.Constant.IP2;
import static com.sm.android.locations.locations.Constant.Constant.SPBEANLIST1Fragment;
import static com.sm.android.locations.locations.Constant.Constant.SPBEANLIST2Fragment;
import static com.sm.android.locations.locations.Constant.Constant.TITLESD;
import static com.sm.android.locations.locations.Constant.Constant.TITLEZD;
import static com.sm.android.locations.locations.Constant.Constant.TITLEZDZM;
import static com.sm.android.locations.locations.Utils.MainUtils.MainUtils.DS;
import static com.sm.android.locations.locations.Utils.MainUtils.MainUtils.i;
import static com.sm.android.locations.locations.Utils.MainUtils.MainUtils.location;
import static com.sm.android.locations.locations.Utils.MainUtils.MainUtils2.toIMSI;
import static com.sm.android.locations.locations.Utils.MyUtils.removeDuplicate;
import static com.sm.android.locations.locations.viewpagermain.Fragment.SendUtils.setzy;
import static com.sm.android.locations.locations.viewpagermain.ViewPagerMainActivity.TYPE_VIEWPAGERMAIN;


@SuppressLint("ValidFragment")
public class Fragment0 extends BaseFragment2 implements FragmentView0.View {
    Thread thread;
    public int blackNUM1 = 1;
    public int blackNUM2 = 1;
    private boolean sb1pd = false;   //初次就进入设备查询下行频点
    private boolean sb2pd = false;   //初次就进入设备查询下行频点
    protected Context mContext;
    private String TAG = "Fragment0";
    FragmentView0.FragmentPresenter1 presenter1;
    //     Handler mHandler ;
    private static Timer timer1, timer2, timer_wendu, timerwifi;
    private Timer timer = null;//五秒一次imsi列表更新
    private View rootView;//缓存Fragment view
    boolean runThread = false;
    List<SaopinBean> saopinBeanList = null;
    Message message;
    Bundle bundle;
    DatagramPacket dp;
    byte[] buf;
    private DLPopupWindow popupWindow;//侧边按钮
    private List<DLPopItem> mList = new ArrayList<>();
    ImageView imageView, iv_fengshan;
    TextView tv_wendu;
    ImageView iv_wendu;//温度图标
    ImageButton ib_zhedie, ib_zhedie_zhenma;//设备折叠 侦码记录折叠
    private LineChartView lineChartView;
    List<Integer> list1quxian, list2quxian, list3quxian, list4quxian;
    SlideButton slideButton1, slideButton2;//滑动按钮    是否自动模式

    private boolean slideButton1Flag = true;
    public static boolean LABAFLAG1 = true;
    public static boolean LABAFLAG2 = true;
    private RecyclerView ryIMSI, ry_zhenma;
    private ScrollView scrollView1, scrollView2;//状态栏显示在底部文字
    public static boolean FENGSHANFLAG = true;//风扇变量 默认风扇打开
    public static boolean qxzhedieFlag = false;//曲线图默认折叠
    public static boolean zmFlag = false;//侦码记录默认折叠
    ImageView laba1, laba2;//喇叭声音开关
    TextView tv_imsi1, tv_imsi2;//中标的IMSI
    CheckBox cbzt1_d, cbzt1_z, cbzt1_g, cbzt2_d, cbzt2_z, cbzt2_g;// 增益值设置 按钮
    private boolean sb1zy1 = false;//初次就进入设备增益查询一次
    private boolean sb1zy2 = false;//初次就进入设备增益查询一次
    TextView tv_r1, tv_r2, b1_jl, tv_searchNum;
    TextView tv1_td, tv2_td;
    TextView tv1_wifi, tv2_wifi;
    private TextView textViews1;//状态栏1
    private TextView textViews2;//状态栏1
    private TextView Tv_type1, Tv_type2;
    EditText et_zhenmasearch;
    private int wifitrue = 0;
    private int wififalse = 0;
    String tf1 = "";
    String tf2 = "";
    public String sb1 = "";
    public String sb2 = "";
    Spinner mysp1, mysp2;
    private String spinnerS1 = "", spinnerS2 = "";//下拉框选中的数据
    List<String> listsSp = new ArrayList<>();//   下拉框频点数据
    DBManagerPinConfig dbManagerPinConfig = null;//查询频点
    DBManagersaopin dbManagersaopin = null;
    private DBManagerAddParam dbManagerAddParam = null;//imsi
    List<PinConfigBean> pinConfigBeans = null;
    ArrayAdapter<String> adapter1, adapter2;
    Button bts_start1, bts_start2, btsstop1, btsstop2;
    List<AddPararBean> listImsiListTrue = null;//装载已经被选中的imsi
    List<AddPararBean> pararBeansList1 = new ArrayList<>();
    List<AddPararBean> dataAll = null;//首页IMSI列表的数据
    RyImsiAdapter ryImsiAdapter;
    RyZmAdapter ryZmAdapter;
    Dialog dialog;
    View inflate;
    private int SAOPIN = 1;//扫频的type设备1
    private int SAOPIN2 = 1;//扫频的type设备1
    TextView sb1_jl, sb1_jl_pj1;
    private TextToSpeech textToSpeech = null;//创建自带语音对象
    private boolean sb1locationgFlag1 = false;
    private boolean LocationFlag1 = true;//定位
    private boolean sb1locationgFlag = false;
    private boolean sb2locationgFlag = false;
    private boolean LocationFlag2 = true;//定位
    private boolean timerRestartFlag1 = false;  //是否重启完成1  若重启完是
    private boolean ClearFlag1 = true;//清空
    private boolean ClearFlags1 = true;//确认清空
    private boolean ClearFlag2 = true;//清空
    private boolean ClearFlags2 = true;//确认清空
    private boolean sb1FirstFlag = false;
    private boolean sb2FirstFlag = false;
    public static long startTime1 = 0;
    public static long startTime2 = 0;
    private boolean BlackFlag1 = true;//黑名单
    private boolean BlackFlags1 = true;//确认黑名单
    private boolean LocationFlags1 = true;//确认定位
    private boolean SaoPinB1 = false;
    private boolean SaoPinB2 = false;
    boolean sb1zhishiFlag = false;
    boolean sb2zhishiFlag = false;
    List<AddPararBean> sendListBlack = null;
    List<AddPararBean> sendListBlack2 = null;
    private boolean xiaoquFlag1 = true;//小区

    private boolean xiaoquFlags1 = true;//确认小区
    private boolean xiaoquFlag2 = true;//小区

    private boolean xiaoquFlags2 = true;//确认小区
    private boolean saopinshow1 = false;
    private boolean saopinshow2 = false;
    String imsi2 = "";
    String imsi1 = "";
    public String imsi11 = "";
    public String imsi12 = "";
    public String imsi21 = "";
    public String imsi22 = "";
    private String sp1MAX1value = "";//扫频1得到的1号频点
    private String sp1MAX2value = "";//扫频1得到的2号频点
    private String sp2MAX1value = "";//扫频2得到的1号频点
    private String sp2MAX2value = "";//扫频2得到的2号频点
    private boolean phone1sp = false;//判断设备1是不是手机扫描  默认不是
    private boolean phone2sp = false;//判断设备2是不是手机扫描
    List<States> listStates = new ArrayList<>();// 设备黑名单中标情况
    private SpBean spBean1 = new SpBean();
    private SpBean spBean2 = new SpBean();
    private SpBean spBean154 = new SpBean();
    private SpBean spBean254 = new SpBean();

    @SuppressLint("NewApi")
    DecimalFormat dfBaoshu = new DecimalFormat("###");
    private Timer timerLocation = null;//五秒一次imsi列表更新

    private void findViewS() {
        et_zhenmasearch = rootView.findViewById(R.id.et_zhenmasearch);
        tv_searchNum = rootView.findViewById(R.id.tv_searchNum);
        imageView = rootView.findViewById(R.id.iv_menu);//右上角菜单栏
        imageView.setVisibility(View.VISIBLE);
        tv_wendu = rootView.findViewById(R.id.tv_wendu);//温度
        iv_wendu = rootView.findViewById(R.id.iv_wendu);//温度图标
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
        lineChartView = (LineChartView) rootView.findViewById(R.id.line_chart_view);//曲线图
        scrollView1 = rootView.findViewById(R.id.scrollView1);//滑动
        scrollView2 = rootView.findViewById(R.id.scrollView2);//滑动
        ib_zhedie = rootView.findViewById(R.id.ib_zhedie);//曲线图折叠
        ib_zhedie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter1.setQxzhedie(mContext, ib_zhedie, lineChartView);

            }
        });
        ryIMSI = rootView.findViewById(R.id.ryIMSI);//IMSI列表
        ryIMSI.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        ry_zhenma = rootView.findViewById(R.id.ry_zhenma);//IMSI列表
        ll_zhenma_search = rootView.findViewById(R.id.ll_zhenma_search);//侦码  线性布局
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
        sb1_jl = rootView.findViewById(R.id.sb1_jl);//设备1距离
        sb1_jl_pj1 = rootView.findViewById(R.id.sb1_jl_pj1);//设备1平均距离
        laba1 = rootView.findViewById(R.id.laba1);//喇叭1
        laba1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter1.setVoice(mContext, 1, laba1, LABAFLAG1);
            }
        });
        laba2 = rootView.findViewById(R.id.laba2);//喇叭2
        laba2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter1.setVoice(mContext, 2, laba2, LABAFLAG2);
            }
        });
        tv_imsi1 = rootView.findViewById(R.id.tv_imsi1);//捕捉到的1 imsi
        tv_imsi2 = rootView.findViewById(R.id.tv_imsi2);//捕捉到的2 imsi
        cbzt1_d = rootView.findViewById(R.id.cbzt1_d);//增益值低
        cbzt1_z = rootView.findViewById(R.id.cbzt1_z);//增益值中
        cbzt1_g = rootView.findViewById(R.id.cbzt1_g);//增益值高
        cbzt2_d = rootView.findViewById(R.id.cbzt2_d);
        cbzt2_z = rootView.findViewById(R.id.cbzt2_z);
        cbzt2_g = rootView.findViewById(R.id.cbzt2_g);

        tv1_wifi = rootView.findViewById(R.id.tv1_wifi);//无线状态1
        tv2_wifi = rootView.findViewById(R.id.tv2_wifi);//无线状态1
        textViews1 = rootView.findViewById(R.id.textViews1);
        textViews2 = rootView.findViewById(R.id.textViews2);
        Tv_type1 = rootView.findViewById(R.id.tv1_type);
        Tv_type2 = rootView.findViewById(R.id.tv2_type);

        tv_r1 = rootView.findViewById(R.id.tv_r1);
        tv_r2 = rootView.findViewById(R.id.tv_r2);
        tv1_td = rootView.findViewById(R.id.tv1_td);
        tv2_td = rootView.findViewById(R.id.tv2_tf);
        mysp1 = rootView.findViewById(R.id.mysp1);//设备1下拉框
        mysp2 = rootView.findViewById(R.id.mysp2);//设备2下拉框
        mysp1.setVisibility(View.GONE);
        mysp2.setVisibility(View.GONE);
        bts_start1 = rootView.findViewById(R.id.bts_start1);
        bts_start1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (slideButton1Flag) {//自动
                    Start1ZDlocation();
                } else {//手动
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
//            //为了方便 查看，我们用Log打印出来
//            Log.e("nzqmRunnable", Thread.currentThread().getName() + "Thread run");
////            MainUtils.ReceiveMain0(mHandler, message, bundle, timer1, timer2, dp, buf, mContext, runThread);//开启线程监听
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
                        //检查设备状态的监听
                        Log.d(TAG, "handleMessage120: ");

//                        String callblackflag1 = msg.getData().getString("CALLBLACKFLAG1");//延迟为1
//                        if (CALLBLACKFLAG1 == true) {
//                            if (callblackflag1.equals("1")) {
////                            CALLBLACKFLAG1 = false;
//                                SetStatusBar("清空黑名单未响应", 1);
//                                if (blackNUM1 <= 1) {
//                                    SetStatusBar("清空黑名单重新执行", 1);
//                                    sb1clear();
//
//                                    BLACKTIME1 = System.currentTimeMillis();
//                                    CALLBLACKFLAG1 = false;
//                                    blackNUM1++;
//                                } else {
//                                    SetStatusBar("清空黑名单未响应请稍后尝试", 1);
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
                                tv_wendu.setText(df2.format(i) + "°C");
                                if (!FENGSHANFLAG) {
                                    FENGSHANFLAG = true;
                                    MainUtils.offOn(1);//开启风扇
                                    iv_fengshan.setBackground(getResources().getDrawable(R.mipmap.fengshan));
                                }
                            } else {
                                iv_wendu.setBackground(getResources().getDrawable(R.mipmap.wendu));
                                tv_wendu.setTextColor(getResources().getColor(R.color.white));
                                tv_wendu.setText(df2.format(i) + "°C");
                            }

                        }
//                        Log.d("Fragment0nzq", msg.obj.toString());
                        break;

                    case 30000:

//                if (bts_start1.getText().equals(TITLEZDZM)) {//自动侦码
                        //当前显示侦码列表
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
                        //此处显示侦码的imsi列表
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
                                ryZmAdapter = new RyZmAdapter(mContext, finalZmBeans3, listsize);//list是imsi列表选中的数据
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
                                        linearLayoutManager.setStackFromEnd(true);//recyview显示最底部一条
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
                                        linearLayoutManager.setStackFromEnd(false);//recyview显示最底部一条
                                        ry_zhenma.setLayoutManager(linearLayoutManager);
                                        tv_searchNum.setText("(" + finalZmBeans1.size() + ")");
                                    }
                                });

                            }
                            final List<ZmBean> finalZmBeans2 = zmBeans;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ryZmAdapter = new RyZmAdapter(mContext, finalZmBeans2, listsize);//list是imsi列表选中的数据
                                    ry_zhenma.setAdapter(ryZmAdapter);
                                }
                            });

                        }

//                        ry_zhenma.scrollToPosition(zmBeans.size());
//                        if (msg.getData().getString("zb").equals("1")) {
//                            //如果是中标的不插入
//                        }

//
//                } else {
                        sbImsiType(msg);
//                }

                        break;
                    case 300001:
//去重
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
                                if (sb1.equals("定位中")) {
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
                                            Log.e("nzqsendstart1", "run: sendsocket端口号" + outputPacket.getPort() + "Ip地址:" + outputPacket.getAddress().toString() + "数据:" + outputPacket.getData());
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
                                if (sb1.equals("定位中")) {
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
                                            InetAddress address = null;//你本机的ip地址
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
                                            Log.e("nzqsendstart1", "run: sendsocket端口号" + outputPacket.getPort() + "Ip地址:" + outputPacket.getAddress().toString() + "数据:" + outputPacket.getData());
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
                                ryImsiAdapter = new RyImsiAdapter(mContext, pararBeansList1, size, config, tv_imsi1, tv_imsi2);//list是imsi列表选中的数据
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
                        if (wifi.equals("true")) {//无线正确
                            if (wifitrue == 0) {
//                        LoadingFalse();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv1_wifi.setText("WIFI连接: 正常");
                                        tv2_wifi.setText("WIFI连接: 正常");
                                    }
                                });

//                                Log.d(TAG, "handleMessage: 走了true");
                                wifitrue = 1;
                                wififalse = 0;
                            } else if (wifitrue == 1) {

                            }


                        }
                        if (wifi.equals("false")) {//无线错误
                            if (wififalse == 0) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv1_wifi.setText("WIFI连接: 断开");
                                        tv2_wifi.setText("WIFI连接: 断开");
                                    }
                                });

//                        LoadingTrue("请检查无线状态");
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
                                    tv1_td.setText("双工模式: " + tf1);
                                }
                            });

//                            Log.d(TAG, "handleMessage双工模式1: " + tf1);
                        } else {


                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tv1_td.setText("双工模式: ");
                                }
                            });
                        }
                        break;
                    case 100120:
                        Log.d("Fragmentnzq0", "状态" + msg.getData().getString("zt1"));
                        String zt1 = msg.getData().getString("zt1");
                        if (!TextUtils.isEmpty(zt1)) {
                            if (zt1.equals("0")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Tv_type1.setText("当前状态:连接中");
                                    }
                                });

//
                            }
                            if (zt1.equals("1")) {

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Tv_type1.setText("连接失败");
                                    }
                                });
                            }
                            if (zt1.equals("2")) {//就绪
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
//                                        Toast.makeText(getApplicationContext(), "FourActivity2 click button", Toast.LENGTH_SHORT).show();
                                        Tv_type1.setText("当前状态: 就绪");
//                                SetStatusBar("就绪", 1);
                                        tv_r1.setText("频点:");
                                        sb1 = "就绪";
                                        //查询设备1增益值初次就绪状态查询一次
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
//                                zy(1);//增益查询方法
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
                            if (zt1.equals("3")) {//扫频同步进行中
//                            Tv_type1.setText("连接失败");

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                SetStatusBar("扫频同步进行中", 1);
                                                sb1 = "扫频同步进行中";
                                            }
                                        });
                                    }
                                });
                            }
                            if (zt1.equals("4")) {//小区激活中

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Tv_type1.setText("当前状态:连接失败");
                                        SetStatusBar("小区激活中", 1);
                                    }
                                });
                            }
                            if (zt1.equals("5")) {//侦码中 /定位中  手动 自动.
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Tv_type1.setText("当前状态:定位中");
                                        SetStatusBar("定位中", 1);
                                        tv_r1.setText("频点:" + DOWNPIN1);
                                        sb1 = "定位中";
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
                                        SetStatusBar("空口同步成功", 1);
                                    }
                                });

                                break;
                            }
                            if (test.equals("2")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        SetStatusBar("空口同步失败", 1);
                                    }
                                });

                                break;
                            }
                            if (test.equals("3")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        SetStatusBar("GPS同步成功", 1);
                                    }
                                });

                                break;
                            }
                            if (test.equals("4")) {

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        SetStatusBar("GPS同步失败", 1);
                                    }
                                });
                                break;
                            }
                            if (test.equals("5")) {
//                                SetStatusBar("扫频成功", 1);
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
                                        SetStatusBar("扫频失败", 1);
                                    }
                                });

                                break;
                            }
                            if (test.equals("7")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        SetStatusBar("小区激活成功", 1);
                                    }
                                });

                                break;
                            }
                            if (test.equals("8")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        SetStatusBar("小区激活失败", 1);

                                    }
                                });
                                break;
                            }
                            if (test.equals("9")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        SetStatusBar("小区去激活", 1);
                                    }
                                });

//                                if (itemtype1.equals(TITLEZD)) {//如果是双频模式
//                                    mysp1.setVisibility(View.GONE);
//                                    tv_r1.setText("下行频点");
//
//                                } else {
//                                    mysp1.setVisibility(View.VISIBLE);
//                                    tv_r1.setText("下行频点");
//                                }
                                break;
                            }
                            if (test.equals("10")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        SetStatusBar("空口同步中", 1);
                                    }
                                });

                                break;
                            }
                            if (test.equals("11")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        SetStatusBar("GPS同步中", 1);
                                    }
                                });

                                break;
                            }
                            if (test.equals("12")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        SetStatusBar("扫频中", 1);
                                        Tv_type1.setText("当前状态:扫频中");
                                    }
                                });


                                break;
                            }
                            if (test.equals("13")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        SetStatusBar("小区激活中", 1);
                                        sb1 = "小区激活中";
                                        sb1 = "小区激活中";
                                    }
                                });

                                break;
                            }
                            if (test.equals("14")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        SetStatusBar("小区去激活中", 1);
                                        sb1 = "小区去激活中";
                                    }
                                });

                                break;
                            }
                        }
                        break;
                    case 100130://判断清空指令是否下发成功
//                        SetStatusBar("清空黑名单成功", 1);
//                        if (slideButton1Flag) {//自动
//
//                        } else {//手中
////                            Log.d(TAG, "handleMessage2清空成功: ");
//                            ClearFlag1 = false;
//                            ClearFlags1 = false;
//
////                            Set1StatusBar("清空黑名单成功");
////                            sb1type = "清空黑名单成功";
//                            CALLBLACKFLAG1 = false;
//                            BLACKTIMESET1 = System.currentTimeMillis();
//                            CALLBLACKFLAGSET1 = true;
//                            sendBlackList();
//                            Log.d("nzqsendstart1Clearset", "设置黑名单列表");
//                        }
                        break;

                    case 100131:
//                        SetStatusBar("设置黑名单列表成功", 1);
//                        if (slideButton1Flag) {//自动
//                            BlackFlags1 = false;
////                            sb1type = "设置黑名单列表成功";
//                            sb1LocationsSP();//设备1定位模式
////                            Set1StatusBar("设置黑名单列表成功");
//                            CALLBLACKFLAGSET1 = false;
//                            BLACKLOCATION1 = System.currentTimeMillis();
//                            CALLBLACKLOCATION1 = true;
//                            CALLBLACKFLAG1 = false;
//                        } else {//手中
//                            BlackFlags1 = false;
////                            sb1type = "设置黑名单列表成功";
//                            sb1Locations();//设备1定位模式
////                            Set1StatusBar("设置黑名单列表成功");
//                            CALLBLACKFLAGSET1 = false;
//                            BLACKLOCATION1 = System.currentTimeMillis();
//                            CALLBLACKLOCATION1 = true;
//                            CALLBLACKFLAG1 = false;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//                                SetStatusBar("设置黑名单列表成功", 1);
                            }
                        });
//                        }

                        break;


                    case 100136: //设置基站定位成功
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                SetStatusBar("设置定位模式成功", 1);
                                if (slideButton1Flag) {//自动
                                    EstablishVillageSS(sp1MAX1value);
                                } else {//手动

                                    EstablishVillage();
                                }
                            }
                        });


                        break;
                    case 100138: //重启设置成功
//                    ToastUtils.showToast("重启设置成功");
//                        Log.d(TAG, "handleMessagea: " + "重启设置成功");

//                    if (itemtype1.equals(TITLEZD)) {//如果是扫频模式
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                SetStatusBar("重启中...", 1);
                            }
                        });
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("重启完成执行", "run:788888888888");
//                                saopinSend1(saopinBeanList, tf1, SAOPIN);
                                timerRestartFlag1 = true;
                            }
                        }, 30000);
//                    }
                        break;
                    case 1001388: //重启设置成功
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                SetStatusBar("重启设置完成", 1);
                                if (slideButton1Flag) {//自动
//                            sb1clear();
                                    saopinSend1(saopinBeanList, tf1, SAOPIN);
                                } else {//手动
//                            Log.d(TAG, "sendBlackList:移动 " + spinnerS1);
//                            sb1Locations();
                                    sendBlackList();
                                }
                            }
                        });

                        break;

                    case 100140:
//                    ToastUtils.showToast("切换制式成功");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                SetStatusBar("切换制式成功", 1);
                                MainUtils.Restart();
                            }
                        });

                        break;
                    case 100141:
//                    ToastUtils.showToast("切换制式失败");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                SetStatusBar("切换制式失败", 1);
                            }
                        });

                        break;
                    case 100147:  //能量值

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
                                //退出日志
//                            try {
//                                dbManagerLog = new DBManagerLog(mContext);
//                            } catch (SQLException e) {
//                                e.printStackTrace();
//                            }
//                            LogBean logBean = new LogBean();
//                            logBean.setAssociated(LoginUtils.getId(mContext) + "");//关联ID
//                            logBean.setEvent(LoginUtils.setBase64("定位"));//登录事件
//                            logBean.setPd(LoginUtils.setBase64(DOWNPIN1));
//                            logBean.setSb(LoginUtils.setBase64(1 + ""));
//                            logBean.setSucessIMSI(LoginUtils.setBase64(imsi11));
//                            String formatD = sdf.format(new Date());//登录时间
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
////                        if (itemtype1.equals(TITLEZD)) {//如果是扫频模式
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                            }
                        });
                        if (SaoPinB1 == false) {//不可建立小区  可查看
                            if (sb1.equals("定位中")) {
                                break;
                            }

                            if (SPBEANLIST1Fragment.size() > 0) {
//                                Set1StatusBar("当前扫频列表可用");
                                Intent intent = new Intent(mContext, SaoPinActivity.class);
                                intent.putExtra("type", "1");
                                startActivity(intent);
//
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        SetStatusBar("当前扫频列表不可用", 1);
                                    }
                                });

                            }

                        } else {
                            if (sb1.equals("定位中")) {
                                break;
                            }
                            //以下是扫频定位

                            String dwon1s = msg.getData().getString("sp1MAX1value");//第一个频点
                            Log.d(TAG, "dwon1shan: " + dwon1s);
//                        ArrayList<SpBean> list = msg.getData().getParcelableArrayList("List");
//                        Log.d("listHand", "handleMessage: " + list);

                            String sp1up = msg.getData().getString("sp1up");
                            String sp1pci = msg.getData().getString("sp1pci");
                            String sp1plmn = msg.getData().getString("sp1plmn");
                            String sp1band = msg.getData().getString("sp1band");
                            String sp1tac = msg.getData().getString("sp1tac");

                            sp1MAX2value = msg.getData().getString("sp1MAX2value");//第二个频点
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

                            if (sb2.equals("定位中")) {//如果设备2 在定位中
                                if (tf1.equals(tf2)) {
                                    if (sp1MAX1value.equals(sp2MAX1value)) {
//                                    Set1StatusBar("制式一样100152 扫频1最优与扫频2最优相同");
                                        if (!TextUtils.isEmpty(sp1MAX2value)) {
                                            SetStatusBar("sp1MAX2value不为空", 1);
                                            spBean1 = spBean2;
//                                        ToastUtils.showToast("设备1可用" + "" + sp1MAX1value);
//
                                            if (sb1locationgFlag1 == false) {
                                                if (!sp1MAX2value.equals("0") && !TextUtils.isEmpty(sp1MAX2value)) {

//                                                sb1Clear();//设备1清空名单


//                                                if (bts_start1.getText().equals(TITLEZDZM)) {//自动侦码
//                                                    MainUtils.sbzmLocation(IP1, mContext);
//                                                } else {
//                                                sb1clear();//设备1清空名单
//                                                sb1Locations();// 手动定位模式
                                                    sendBlackList();
//                                                }
                                                    Log.d("100152spBean1aaa", "100152handleMessage: " + spBean1);
//                                aaaaaa
//                                                tv_r1.setText("下行频点" + sp1MAX2value + "");
//                                                        SetStatusBar("当前频点" + sp1MAX2value, 1);

                                                } else {
//                                                ToastUtils.showToast("不可用0或者空");

                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            SetStatusBar("当前频点不可用", 1);
                                                        }
                                                    });
                                                }

                                            } else {
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        ToastUtils.showToast("请先停止定位");
                                                    }
                                                });

                                            }
//
                                        } else {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    ToastUtils.showToast("不可用");
//                                        Set1StatusBar("sp1MAX2value为空不可用");
                                                    SetStatusBar("当前频点不可用", 1);
                                                }
                                            });

                                        }
                                    } else {
//                                    Set1StatusBar("sp1MAX1value与sp2MAX1value不同");
                                        if (!TextUtils.isEmpty(sp1MAX1value)) {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    SetStatusBar("sp1MAX1value不为空", 1);
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


//                                        ToastUtils.showToast("可用333" + "" + sp1MAX1value);
//
                                            if (sb1locationgFlag1 == false) {
                                                if (!sp1MAX1value.equals("0") && !TextUtils.isEmpty(sp1MAX1value)) {
                                                    if (!sp1MAX1value.equals(sp2MAX1value)) {
//                                                    sb1Clear();//设备1清空名单

//                                                    if (bts_start1.getText().equals(TITLEZDZM)) {//自动侦码
//                                                        MainUtils.sbzmLocation(IP1, MainActivity.this);
//                                                    } else {
//                                                    sb1clear();//设备1清空名单
//                                                    }
//                                                    sb1Locations();// 手动定位模式
                                                        runOnUiThread(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                sendBlackList();
                                                            }
                                                        });

                                                        Log.d("spBean1aaa", "handleMessage: " + spBean1);
//                                aaaaaa
//                                                    tv_r1.setText("下行频点" + sp1MAX1value + "");
//                                                            SetStatusBar("当前频点" + sp1MAX1value, 1);
                                                    } else {
                                                        runOnUiThread(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                SetStatusBar("当前频点不可用", 1);
                                                            }
                                                        });

                                                    }

                                                } else {
//                                                ToastUtils.showToast("不可用0或者空");

                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            SetStatusBar("当前频点不可用", 1);
                                                        }
                                                    });
                                                }

                                            } else {
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        ToastUtils.showToast("请先停止定位");
                                                    }
                                                });

                                            }
//
                                        } else {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    ToastUtils.showToast("不可用");
                                                    SetStatusBar("当前频点不可用", 1);
                                                }
                                            });

                                        }
                                    }
                                } else {
                                    if (!TextUtils.isEmpty(sp1MAX1value)) {
//                                    Set1StatusBar("制式不一致直接用sp1MAX1value");
//                              spBean1.setUp(sp1up);
                                        SpBean spBean = new SpBean();
                                        spBean.setUp(sp1up);
                                        spBean.setDown(sp1MAX1value);
                                        spBean.setBand(sp1band);
                                        spBean.setPlmn(sp1plmn);
                                        spBean.setTac(Integer.parseInt(sp1tac));
                                        spBean.setPci(Integer.parseInt(sp1pci));
                                        spBean1 = spBean;

//                                    ToastUtils.showToast("可用2221" + "" + sp1MAX1value);
//
                                        if (sb1locationgFlag1 == false) {
                                            if (!sp1MAX1value.equals("0") && !TextUtils.isEmpty(sp1MAX1value)) {
                                                if (!sp1MAX1value.equals(sp2MAX1value)) {
//                                                sb1Clear();//设备1清空名单
//                                                if (bts_start1.getText().equals(TITLEZDZM)) {//自动侦码
//                                                    MainUtils.sbzmLocation(IP1, mContext);
//                                                } else {
//                                                sb1clear();//设备1清空名单
//                                                sb1Locations();// 手动定位模式
//                                                }
                                                    sendBlackList();
                                                    Log.d("spBean1aaa", "handleMessage: " + spBean1);
//                                aaaaaa
//                                                tv_r1.setText("下行频点" + sp1MAX1value + "");
//                                                        SetStatusBar("当前频点" + sp1MAX1value, 1);
                                                } else {
                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            SetStatusBar("当前频点不可用", 1);

                                                        }
                                                    });

                                                }

                                            } else {
//                                            ToastUtils.showToast("不可用0或者空");

                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        SetStatusBar("当前频点不可用", 1);
                                                    }
                                                });
                                            }

                                        } else {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    ToastUtils.showToast("请先停止定位");
                                                }
                                            });

                                        }
//
                                    } else {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                ToastUtils.showToast("不可用");
                                                SetStatusBar("当前频点不可用", 1);
                                            }
                                        });

                                    }
                                }
                            } else {//如果没在定位中
                                if (!TextUtils.isEmpty(sp1MAX1value)) {
//                                Set1StatusBar("设备2没定位中直接中且sp1MAX1value不为空");
//                              spBean1.setUp(sp1up);
                                    SpBean spBean = new SpBean();
                                    spBean.setUp(sp1up);
                                    spBean.setDown(sp1MAX1value);
                                    spBean.setBand(sp1band);
                                    spBean.setPlmn(sp1plmn);
                                    spBean.setTac(Integer.parseInt(sp1tac));
                                    spBean.setPci(Integer.parseInt(sp1pci));
                                    spBean1 = spBean;


//                                ToastUtils.showToast("可用222" + "" + sp1MAX1value);
//
                                    if (sb1locationgFlag1 == false) {
                                        if (!sp1MAX1value.equals("0") && !TextUtils.isEmpty(sp1MAX1value)) {
                                            if (!sp1MAX1value.equals(sp2MAX1value)) {
//                                            if (bts_start1.getText().equals(TITLEZDZM)) {//自动侦码
//                                                MainUtils.sbzmLocation(IP1, mContext);
//                                            } else {
//                                            sb1clear();//设备1清空名单
//                                            sb1Locations();// 手动定位模式
//                                            }
                                                sendBlackList();
                                                Log.d("spBean1aaaA", "handleMessage: " + spBean1);
//                                aaaaaa
//                                            tv_r1.setText("下行频点" + sp1MAX1value + "");
//                                                    SetStatusBar("当前频点" + sp1MAX1value, 1);
                                            } else {
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        SetStatusBar("当前频点不可用", 1);
                                                    }
                                                });

                                            }

                                        } else {
//                                        ToastUtils.showToast("不可用0或者空");

                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    SetStatusBar("当前频点不可用", 1);
                                                }
                                            });
                                        }

                                    } else {

                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                ToastUtils.showToast("请先停止定位");
                                            }
                                        });
                                    }
//
                                } else {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            ToastUtils.showToast("不可用");
//                                Set1StatusBar("设备2没定位中直接中且sp1MAX1value为空");
                                            SetStatusBar("当前频点不可用", 1);
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
                pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(sp1MAX1value)); //查询对应的频点
                yy = pinConfigBeans.get(0).getYy();
            } catch (SQLException e) {
                e.printStackTrace();
            }
//                                Log.d(TAG, "sendBlackList:移动 ");
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
                    ToastUtils.showToast("符合条件的黑名单列表大于20");
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            sb1FirstFlag = true;
                            startTime1 = System.currentTimeMillis();
                            DatagramSocket socket = null;
                            InetAddress address = null;//你本机的ip地址
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
                            Log.e("nzqsendstart1Clear", "run: sendsocket端口号" + outputPacket.getPort() + "Ip地址:" + outputPacket.getAddress().toString() + "数据:" + outputPacket.getData());

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
                ToastUtils.showToast("没有符合条件的IMSI");
            }

        } else {
            List<AddPararBean> sendListBlack = null;
            sendListBlack = new ArrayList<>();
            if (!TextUtils.isEmpty(spinnerS1)) {
//            String sb1zhishi = "";
                String yy = "";
                try {
                    pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(spinnerS1)); //查询对应的频点
                    yy = pinConfigBeans.get(0).getYy();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
//                                Log.d(TAG, "sendBlackList:移动 ");
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
                        ToastUtils.showToast("符合条件的黑名单列表大于20");
                    } else {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                sb1FirstFlag = true;
                                startTime1 = System.currentTimeMillis();
                                DatagramSocket socket = null;
                                InetAddress address = null;//你本机的ip地址
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
                                Log.e("nzqsendstart1Clear", "run: sendsocket端口号" + outputPacket.getPort() + "Ip地址:" + outputPacket.getAddress().toString() + "数据:" + outputPacket.getData());

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
                    ToastUtils.showToast("没有符合条件的IMSI");
                }

            } else {
                ToastUtils.showToast("请先设置下行频点");
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
                pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(spinnerS2)); //查询对应的频点
                yy = pinConfigBeans.get(0).getYy();
            } catch (SQLException e) {
                e.printStackTrace();
            }
//                                Log.d(TAG, "sendBlackList:移动 ");
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
                    ToastUtils.showToast("符合条件的黑名单列表大于20");
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            sb1FirstFlag = true;
                            startTime1 = System.currentTimeMillis();
                            DatagramSocket socket = null;
                            InetAddress address = null;//你本机的ip地址
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
                            Log.e("nzqsendstart1Clear", "run: sendsocket端口号" + outputPacket.getPort() + "Ip地址:" + outputPacket.getAddress().toString() + "数据:" + outputPacket.getData());

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
                ToastUtils.showToast("没有符合条件的IMSI");
            }

        } else {
            ToastUtils.showToast("请先设置下行频点");
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
                tv_title.setText("确定要定位" + imsi + "吗?");
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
                                InetAddress address = null;//你本机的ip地址
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
                                Log.e("nzqsendstart1", "run: sendsocket端口号" + outputPacket.getPort() + "Ip地址:" + outputPacket.getAddress().toString() + "数据:" + outputPacket.getData());
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

            } else if (sb.equals("2")) {

                dialog = new Dialog(mContext, R.style.menuDialogStyleDialogStyle);
                inflate = LayoutInflater.from(mContext).inflate(R.layout.dialog_item_title, null);
                TextView tv_title = inflate.findViewById(R.id.tv_title);
                tv_title.setText("确定要定位" + imsi + "吗?");
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
                                InetAddress address = null;//你本机的ip地址
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
                                Log.e("nzqsendstart1", "run: sendsocket端口号" + outputPacket.getPort() + "Ip地址:" + outputPacket.getAddress().toString() + "数据:" + outputPacket.getData());
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


            } else {
                ToastUtils.showToast("未中标");

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
            MainUtils.ReceiveMainWD0(mHandler, message, bundle, timer_wendu);//温度查询
            MainUtils.TYPES(mHandler);
//            WifiMain(mHandler, message, bundle, timer1, timer2, dp, buf, mContext);//开启wifi监听
            findViewS();
            initData();
            AddITemMenu();
            new Thread(new Runnable() {
                @Override
                public void run() {
//                    MainUtils.getInstance().ReceiveMain0(mHandler, message, bundle, timer1, timer2, dp, buf, mContext, runThread);//开启线程监听
                }
            }).start();
            //通过Handler启动线程
//            mHandler.post(mRunnable);
//            App.setOnHandlerListener(new App.HandlerListener() {
//                @Override
//                public void heandleMessage(Message msg) {
//                    System.out.println("Fragment_msg0==== " + msg.what);
//                    System.out.println("这是1==== " + msg.what);
//                    mHandler.handleMessage(msg);
//                }
//            });
        }
        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
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
            MainUtils.ReceiveMainWD0(mHandler, message, bundle, timer_wendu);//温度查询
            MainUtils.TYPES(mHandler);
            WifiMain(mHandler, message, bundle, timer1, timer2, dp, buf, mContext);//开启wifi监听
//            MainUtils.ReceiveMain0(mHandler, message, bundle, timer1, timer2, dp, buf, mContext, runThread);//开启线程监听
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
        //曲线数据初始化
        list1quxian = new ArrayList<>();
        list2quxian = new ArrayList<>();
        setPresenter(presenter1);
        presenter1.qxListda(list1quxian, list2quxian, list3quxian, list4quxian);//初始化
        setqxData(list1quxian, list2quxian, list3quxian, list4quxian);
        CheckBoxOnclickSet();//增益设置
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
                    SpinnersSetData(1);//设置两个spinner下拉框数据
                    SpinnersSetData(2);//设置两个spinner下拉框数据

                } else {
//                    ToastUtils.showToast("无可用频点");
                }
            } else {
//                ToastUtils.showToast("无可用频点");
            }
//            Log.d(TAG, "pinConfigBeansinit: " + pinConfigBeans);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (timerLocation == null) {
            timerLocation = new Timer();
            //schedule方法是执行时间定时任务的方法
            timerLocation.schedule(new TimerTask() {

                //run方法就是具体需要定时执行的任务
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
        try {//初始化 清除上次的侦码记录
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
                    //此处显示侦码的imsi列表
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
                            linearLayoutManager.setStackFromEnd(true);//recyview显示最底部一条
                            linearLayoutManager.setStackFromEnd(true);//recyview显示最底部一条
                            ry_zhenma.setLayoutManager(linearLayoutManager);
                            tv_searchNum.setText("(" + listsize.size() + ")");
                        } else {
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                            linearLayoutManager.setStackFromEnd(false);//recyview显示最底部一条
                            ry_zhenma.setLayoutManager(linearLayoutManager);
                            tv_searchNum.setText("(" + listsize.size() + ")");
                        }
                        ryZmAdapter = new RyZmAdapter(mContext, zmBeanscontains, listsize);//list是imsi列表选中的数据
                        ry_zhenma.setAdapter(ryZmAdapter);
                    }
                } else {

                }

            }
        });
    }

    //imsi列表
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

                    ryImsiAdapter = new RyImsiAdapter(mContext, listImsiListTrue, list1size, config, tv_imsi1, tv_imsi2);//list是imsi列表选中的数据
                    ryIMSI.setAdapter(ryImsiAdapter);
                }

            }
            Log.d("addPararBeans", "init: " + dataAll);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //设置下拉框数据
    private void SpinnersSetData(int posion) {
//        mysp1.setClickable(true);
        if (posion == 1) {
            adapter1 = new ArrayAdapter<String>(context, R.layout.spinner_item
                    , listsSp);
            mysp1.setAdapter(adapter1);
            mysp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    spinnerS1 = adapter1.getItem(i);//下拉框1选择的数据
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
                    spinnerS2 = adapter2.getItem(i);//下拉框2选择的数据
//                    Log.d(TAG, "2onItemSelected: " + spinnerS2);

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }

    }

    /**
     * wifi状态的监听
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
     * 判断wifi连接是否正确
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
        //wifi状态的判断 设备1wifi状态判断
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
                    wifi = "正常";
//                        Log.d(TAG, "run:走了");
                    bundle.putString("msgWifi", "true");
                    Message message = new Message();
                    message.setData(bundle);
                    handler.sendMessage(message);
                    message.what = 100001;
                    Log.d("nzqmsgWifi", "run: true");
                } else {
//                        ToastUtils.showToast("连接wifi错误");
                    wifi = "错误";
                    wifiFlag = false;
//                    Log.d(TAG, "run:走了");
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
     * 增益初始化
     */
    private void CheckBoxOnclickSet() {
        //设备1
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

        //设备2
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
     * 添加菜单的按钮
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
//                    System.out.println("这是0==== " + msg.what);
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
            if (timer_wendu != null) {  //判断切换回来的温度
                timer_wendu.cancel();
                timer_wendu = new Timer();
                bundle = new Bundle();
                MainUtils.ReceiveMainWD0(mHandler, message, bundle, timer_wendu);//温度查询
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
//                MainUtils.ReceiveMainWD0(mHandler, message, bundle, timer_wendu);//温度查询
                MainUtils.TYPES(mHandler);
                WifiMain(mHandler, message, bundle, timer1, timer2, dp, buf, mContext);//开启wifi监听
                initData();
//                if (thread==null){
//                    thread = new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            /**要实现的方法*/
////                    Message message=new Message();
////                    message.what=1;
////                    mHandler.sendMessage(message);
//
////                            try {
////                                Thread.sleep(1000);
////                            } catch (InterruptedException e) {
////                                e.printStackTrace();
////                            }
//                            MainUtils.getInstance().ReceiveMain0(mHandler, message, bundle, timer1, timer2, dp, buf, mContext, runThread);//开启线程监听
//                            Log.d("qiehuanhuilai", "run: " + "切换自信了");
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
//                        MainUtils.getInstance().ReceiveMain0(mHandler, message, bundle, timer1, timer2, dp, buf, mContext, runThread);//开启线程监听
                    }
                }).start();

            } else {
            }
            //wifi
            if (timerwifi != null) {  //判断切换回来的温度
                timerwifi.cancel();
                timerwifi = new Timer();
                bundle = new Bundle();
//                WifiMain(mHandler, message, bundle, timer1, timer2, dp, buf, mContext);//开启wifi监听

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
     * 设置曲线图数据
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
     * 风扇回调
     * 注: 暂时弃用
     *
     * @param imageView
     * @param drawable
     */
    @Override
    public void WifiUp(ImageView imageView, Drawable drawable) {

    }

    /**
     * 风扇回调
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
     * 曲线图回调
     *
     * @param drawable
     */
    @SuppressLint("NewApi")
    @Override
    public void qxzhedieUp(Drawable drawable) {
        ib_zhedie.setBackground(drawable);
    }

    /**
     * 侦码折叠回调
     *
     * @param drawable
     */
    @SuppressLint("NewApi")
    @Override
    public void ZmzhedieUp(Drawable drawable) {
        ib_zhedie_zhenma.setBackground(drawable);
    }

    /**
     * 喇叭回调
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
//        sb1clear();//清空黑名单指令
        ClearFlag1 = false;
        ClearFlags1 = false;

//                            Set1StatusBar("清空黑名单成功");
//                            sb1type = "清空黑名单成功";
        CALLBLACKFLAG1 = false;
        BLACKTIMESET1 = System.currentTimeMillis();
        CALLBLACKFLAGSET1 = true;
        sendBlackList();
        Log.d("nzqsendstart1Clearset", "设置黑名单列表");
    }

    @Override
    public void Start2SD() {

    }

    //自动定位
    @Override
    public void Start1ZDlocation() {
        SaoPinB1 = true;
        DialogUtils.SaoPinDialog(1, mContext, inflate, saoPinCallback, tf1, true);
    }

    //设置状态栏1的数据
    public String upStr1 = "";
    //设置状态栏2的数据
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
                scrollView1.fullScroll(scrollView1.FOCUS_DOWN);//滚动到底部
            }
        }

        if (i == 2) {
            if (!upStr2.equals(str)) {
                stringBuffer2.append(str + "" + "\n");
                textViews2.setText(stringBuffer2);
                upStr2 = str;
                scrollView2.fullScroll(scrollView2.FOCUS_DOWN);//滚动到底部
            }
        }
//        if (i == 2) {
//            if (TextUtils.isEmpty(upStr2)){
//                stringBuffer2.append(str + "" + "\n");
//                textViews2.setText(stringBuffer2);
//                upStr2 = str;
//                scrollView2.fullScroll(scrollView2.FOCUS_DOWN);//滚动到底部
//            }else {
//                if (!upStr2.equals(str)) {
//                    stringBuffer2.append(str + "" + "\n");
//                    textViews2.setText(stringBuffer2);
//                    upStr2 = str;
//                    scrollView2.fullScroll(scrollView2.FOCUS_DOWN);//滚动到底部
//                }else {
//                    stringBuffer2.append(str + "" + "\n");
//                    textViews2.setText(stringBuffer2);
//                    upStr2 = str;
//                    scrollView2.fullScroll(scrollView2.FOCUS_DOWN);//滚动到底部
//                }
//            }
//
//        }
        if (i == 3) {
            if (!upStr3.equals(str)) {
//                stringBuffer3.append(str + "" + "\n");
//                textViews3.setText(stringBuffer3);
                upStr3 = str;
//                scrollView3.fullScroll(scrollView3.FOCUS_DOWN);//滚动到底部
            }
        }
        if (i == 4) {
            if (!upStr4.equals(str)) {
//                stringBuffer4.append(str + "" + "\n");
//                textViews4.setText(stringBuffer4);
                upStr4 = str;
//                scrollView4.fullScroll(scrollView4.FOCUS_DOWN);//滚动到底部
            }
        }

    }

    //重启执行的方法
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

    private void sendBlackList() {//发送黑名单列表  1.筛选符合条件的 imsi
//        Log.d(TAG, "sendBlackList:移动 " + spinnerS1);
        if (slideButton1Flag) {
            String yy = "";
            List<AddPararBean> sendListBlack = null;
            sendListBlack = new ArrayList<>();
            try {
                pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(sp1MAX1value)); //查询对应的频点
                yy = pinConfigBeans.get(0).getYy();
            } catch (SQLException e) {
                e.printStackTrace();
            }
//            Log.d(TAG, "sendBlackList:移动 ");
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
                    sendBlackListRun(sendListBlack);
                }
//
            } else {
                CALLBLACKFLAGSET1 = false;
                ToastUtils.showToast("没有符合条件的IMSI");
            }
        } else {
            List<AddPararBean> sendListBlack = null;
            sendListBlack = new ArrayList<>();
            if (!TextUtils.isEmpty(spinnerS1)) {
//            String sb1zhishi = "";
                String yy = "";
                try {
                    pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(spinnerS1)); //查询对应的频点
                    yy = pinConfigBeans.get(0).getYy();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
//            Log.d(TAG, "sendBlackList:移动 ");
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
                        sendBlackListRun(sendListBlack);
                    }
//
                } else {
                    CALLBLACKFLAGSET1 = false;
                    ToastUtils.showToast("没有符合条件的IMSI");
                }

            } else {
                ToastUtils.showToast("请先设置下行频点");
            }
        }


    }

    //发送黑名单
    private void sendBlackListRun(List<AddPararBean> sendListBlack) {
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
            sendrun(str);//开始发送
//            Log.d(TAG, "sendBlackListRun:开始发送 ");
        }
        Log.d("nzqsendrun", "sendrun: " + str.toString());
    }

    //设备1已开始发送
    private void sendrun(final StringBuffer strData) {

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
                Log.e("nzqsendrun", "run: sendsocket端口号" + outputPacket.getPort() + "Ip地址:" + outputPacket.getAddress().toString() + "数据:" + outputPacket.getData());
                LogUtils.a("QsendBlackListRun数据:" + outputPacket.getData());
                try {
                    //                                    socket.send(outputPacket);
                    DS.send(outputPacket);
                    if (!sb1.equals("定位中") || sb1.equals("扫频中") || sb1.equals("小区激活中") || sb1.equals("扫频同步进行中"))
                        if (slideButton1Flag) {//自动
                            BlackFlags1 = false;
//                            sb1type = "设置黑名单列表成功";
//                        sb1LocationsSP();//设备1定位模式
//                            Set1StatusBar("设置黑名单列表成功");
                            CALLBLACKFLAGSET1 = false;
                            BLACKLOCATION1 = System.currentTimeMillis();
                            CALLBLACKLOCATION1 = true;
                            CALLBLACKFLAG1 = false;
//                            mHandler.postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
                            Log.d("设置黑名单", "延时一秒后指定手动建立小区");

                            sb1LocationsSP();
//                                }
//                            }, 3000);
                        } else {//手中
                            BlackFlags1 = false;
//                            sb1type = "设置黑名单列表成功";
//                        sb1Locations();//设备1定位模式
//                            Set1StatusBar("设置黑名单列表成功");
                            CALLBLACKFLAGSET1 = false;
                            BLACKLOCATION1 = System.currentTimeMillis();
                            CALLBLACKLOCATION1 = true;
                            CALLBLACKFLAG1 = false;

//                            mHandler.postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
                            Log.d("设置黑名单", "延时一秒后指定手动建立小区");

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
//                            Log.d(TAG, "run:耗时了1 ");
//                        interrupted();
                            Message message = new Message();
                            bundle.putString("zt1", "1");
                            message.setData(bundle);
                            mHandler.sendMessage(message);
//                            message.what = 100133;
                            BlackFlag1 = false;
//                        ToastUtils.showToast("耗时了");
                        } else if (BlackFlags1 == false) {
                            BlackFlag1 = false;

                        }
                    }

                }

            }
        }).start();
    }

    //设备1定位模式 手动
    private void sb1Locations() {
        SaoPinB1 = true;
        sendListBlack = new ArrayList<>();
        if (!sb1.equals("定位中")) {
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
                        try {
                            pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(spinnerS1)); //查询对应的频点
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
                            Log.d(TAG, "sendBlackList: " + sendListBlack);
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

    //设备1定位模式 扫频
    private void sb1LocationsSP() {
//        sendListBlack = new ArrayList<>();
        if (!sb1.equals("定位中")) {
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
                        ToastUtils.showToast("请设置IMSI列表");

                        return;
                    }
                    if (sendListBlack.size() == 0) {
//                        Set1StatusBar("没有符合下行频点的IMSI");
//                        ToastUtils.showToast("没有符合下行频点的IMSI");
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
                        Log.e("nzqsendstart1设置定位模式", "run: sendsocket端口号" + outputPacket.getPort() + "Ip地址:" + outputPacket.getAddress().toString() + "数据:" + outputPacket.getData());
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

    //建立小区1
    private void EstablishVillage() {
        new Thread(new Runnable() {
            @SuppressLint("LongLogTag")
            @Override
            public void run() {
                List<PinConfigBean> pinConfigBeans = null;
                try {
                    pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(spinnerS1)); //查询对应的频点

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
                    forid = dbManager01.forid(1);  //查询小区1

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (pinConfigBeans == null) {
//                    ToastUtils.showToast("频点配置错误");
                    SetStatusBar("频点配置错误", 1);
                }
                if (forid == null) {
//                    ToastUtils.showToast("小区1配置错误");
                    SetStatusBar("小区1配置错误", 1);
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
        // 设置音调，值越大声音越尖（女生），值越小则变成男声,1.0是常规

        textToSpeech.setPitch(1.f);
        Log.d("wpnqq", "startAuto: " + b);

        // 设置语速
        textToSpeech.setSpeechRate(8.01f);
        textToSpeech.speak(data,//输入中文，若不支持的设备则不会读出来
                TextToSpeech.QUEUE_FLUSH, null);
    }

    private void initTTS() {
        //实例化自带语音对象
        textToSpeech = new TextToSpeech(mContext, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == textToSpeech.SUCCESS) {
                    // Toast.makeText(MainActivity.this,"成功输出语音",
                    // Toast.LENGTH_SHORT).show();
                    // Locale loc1=new Locale("us");
                    // Locale loc2=new Locale("china");

                    textToSpeech.setPitch(0.5f);//方法用来控制音调
                    textToSpeech.setSpeechRate(0.01f);//用来控制语速

                    //判断是否支持下面两种语言
                    int result1 = textToSpeech.setLanguage(Locale.US);
                    int result2 = textToSpeech.setLanguage(Locale.
                            SIMPLIFIED_CHINESE);
                    boolean a = (result1 == TextToSpeech.LANG_MISSING_DATA || result1 == TextToSpeech.LANG_NOT_SUPPORTED);
                    boolean b = (result2 == TextToSpeech.LANG_MISSING_DATA || result2 == TextToSpeech.LANG_NOT_SUPPORTED);

//                    Log.i("zhh_tts", "US支持否？--》" + a +
//                            "\nzh-CN支持否》--》" + b);
                } else {
//                    MyToast.showToast("数据丢失或不支持");
//                    Toast.makeText(MainActivity.this, "数据丢失或不支持", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    /**
     * 设备定位imsi的数据
     *
     * @param msg
     */
    private void sbImsiType(Message msg) {
        String imsi = msg.getData().getString("imsi");
        String sb = msg.getData().getString("sb");
        String zb = msg.getData().getString("zb");
        String datetime = msg.getData().getString("datetime");
        String time = msg.getData().getString("time");
        Log.d(TAG, "handl黑名单: " + imsi + "---" + sb + "zb--" + "datatime--" + datetime + "time--" + time);
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
        //创建一个定时器
        if (timer == null) {
            timer = new Timer();
            //schedule方法是执行时间定时任务的方法
            timer.schedule(new TimerTask() {

                //run方法就是具体需要定时执行的任务
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
            Log.d("saoPinCallback", "sucess: 设备" + sb + "i===" + i);
            if (sb == 1) {//设备1
                SAOPIN = i;
                if (tf1.equals("TDD")) {
                    if (i == 1) {//移动TDD
//                        ToastUtils.showToast("当前一致TDD");
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
                            saopinBeanList = dbManagersaopin.getStudent(); //查询对应的频点
                            if (saopinBeanList != null && saopinBeanList.size() > 0) {
//                                    Set1StatusBar("功放开启成功");
                                saopinSend1(saopinBeanList, tf1, SAOPIN);
//                                            MainUtils.start1SNF(IP1);
//                                            ToastUtils.showToast("snf发送了");
                                GFFLAG1 = 1;
                            } else {
                                ToastUtils.showToast("当前没有频点");
                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        return;
                    }
                    if (i == 2) {//移动FDD   需要切换
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
                    if (i == 3) {//联通FDD
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
                    if (i == 4) {//电信FDD
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
                    if (i == 1) {//移动TDD
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
                    if (i == 2) {//移动FDD

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
                    if (i == 3) {//联通FDD
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
                    if (i == 4) {//电信FDD

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
            //设备2
            if (sb == 2) {
                SAOPIN2 = i;
                if (tf2.equals("TDD")) {
                    if (i == 1) {//移动TDD
//                        ToastUtils.showToast("1当前一致TDD");
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
                                    saopinBeanList = dbManagersaopin.getStudent(); //查询对应的频点
                                    if (saopinBeanList != null && saopinBeanList.size() > 0) {
                                        saopinSend2(saopinBeanList, tf2, SAOPIN2);
//                                            MainUtils.start1SNF(IP1);
//                                            ToastUtils.showToast("snf发送了");
                                        GFFLAG2 = 1;
                                    } else {
                                        ToastUtils.showToast("当前没有频点");
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
                    if (i == 2) {//移动FDD   需要切换
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

//                                if (title.getText().toString().equals(getString(R.string.activity_saopintitle))) {//如果是扫频模式
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
                    if (i == 3) {//联通FDD
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

//                                if (title.getText().toString().equals(getString(R.string.activity_saopintitle))) {//如果是扫频模式
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
                    if (i == 4) {//电信FDD
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

//                                if (title.getText().toString().equals(getString(R.string.activity_saopintitle))) {//如果是扫频模式
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
                    if (i == 1) {//移动TDD
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

//                                if (title.getText().toString().equals(getString(R.string.activity_saopintitle))) {//如果是扫频模式
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
                    if (i == 2) {//移动FDD
//                        ToastUtils.showToast("当前一致FDD---");
                        sb2zhishiFlag = true;
//                                    MainUtils.start1SNF(IP1);
                        MainUtils.start1SNF(IP1, Constant.SNFFDD);

                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                saopinSend2(saopinBeanList, tf2, SAOPIN2);

//                                if (title.getText().toString().equals(getString(R.string.activity_saopintitle))) {//如果是扫频模式
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
                    if (i == 3) {//联通FDD
//                        ToastUtils.showToast("当前一致FDD---");
                        sb2zhishiFlag = true;
//                                    MainUtils.start1SNF(IP1);
                        MainUtils.start1SNF(IP1, Constant.SNFFDD);

                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                saopinSend2(saopinBeanList, tf2, SAOPIN2);

//                                if (title.getText().toString().equals(getString(R.string.activity_saopintitle))) {//如果是扫频模式
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
                    if (i == 4) {//电信FDD
//                        ToastUtils.showToast("当前一致FDD---");
                        sb2zhishiFlag = true;
//                                    MainUtils.start1SNF(IP1);
                        MainUtils.start1SNF(IP1, Constant.SNFFDD);

                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                saopinSend2(saopinBeanList, tf2, SAOPIN2);

//                                if (title.getText().toString().equals(getString(R.string.activity_saopintitle))) {//如果是扫频模式
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

                if (MainUtils2.YY(spBean1.getPlmn()).equals("移动")) {
                    SAOPIN = 1;
                    Log.d("aaaplmnsucessphone", "sucessphone: +");
                    if (tf1.equals("TDD")) {
//                        if (bts_start1.getText().equals(TITLEZDZM)) {//自动侦码
//                            MainUtils.sbzmLocation(IP1, mContext);  //侦码功能使用
//                        } else {//自动扫频
//                        sb1clear();
//                        }
//                        sb1Locations();// 手动定位模式
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
                } else if (spBean.getBand().equals("1") || spBean.getBand().equals("3") || spBean.getBand().equals("5") || spBean.getBand().equals("8") || MainUtils2.YY(spBean1.getPlmn()).equals("移动")) {//移动FDD
                    SAOPIN = 2;
                    if (tf1.equals("FDD")) {
//                        if (bts_start1.getText().equals(TITLEZDZM)) {//自动侦码
//                            MainUtils.sbzmLocation(IP1, mContext);  //侦码功能使用
//                        } else {//自动扫频
//                        sb1clear();
//                        }
//                        sb1Locations();// 手动定位模式
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
                } else if (MainUtils2.YY(spBean1.getDown()).equals("联通")) {
                    SAOPIN = 3;
                    if (tf1.equals("FDD")) {
//                        if (bts_start1.getText().equals(TITLEZDZM)) {//自动侦码
//                            MainUtils.sbzmLocation(IP1, mContext);  //侦码功能使用
//                        } else {//自动扫频
//                        sb1clear();
//                        sb1Locations();// 手动定位模式
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
                } else if (MainUtils2.YY(spBean1.getDown()).equals("电信")) {
                    SAOPIN = 4;
                    if (tf1.equals("FDD")) {
//                        if (bts_start1.getText().equals(TITLEZDZM)) {//自动侦码
//                            MainUtils.sbzmLocation(IP1, mContext);  //侦码功能使用
//                        } else {//自动扫频
//                        sb1clear();
//                        sb1Locations();// 手动定位模式
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
                if (MainUtils2.YY(spBean154.getPlmn()).equals("移动") && !spBean154.getBand().equals("1") && !spBean154.getBand().equals("3") && !spBean154.getBand().equals("5") && !spBean154.getBand().equals("8")) {
                    SAOPIN2 = 1;
                    if (tf2.equals("TDD")) {
//                        if (bts_start2.getText().equals(TITLEZDZM)) {//自动侦码
//                            MainUtils.sbzmLocation(IP2, mContext);  //侦码功能使用
//                        } else {//自动扫频
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
                } else if (spBean154.getBand().equals("1") || spBean154.getBand().equals("3") || spBean154.getBand().equals("5") || spBean154.getBand().equals("8")) {//移动FDD
                    SAOPIN2 = 2;
                    if (tf2.equals("FDD")) {
//                        if (bts_start2.getText().equals(TITLEZDZM)) {//自动侦码
//                            MainUtils.sbzmLocation(IP2, mContext);  //侦码功能使用
//                        } else {//自动扫频
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
                } else if (MainUtils2.YY(spBean154.getDown()).equals("联通")) {
                    SAOPIN2 = 3;
                    if (tf2.equals("FDD")) {
//                        if (bts_start2.getText().equals(TITLEZDZM)) {//自动侦码
//                            MainUtils.sbzmLocation(IP2, mContext);  //侦码功能使用
//                        } else {//自动扫频
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
                } else if (MainUtils2.YY(spBean154.getDown()).equals("电信")) {
                    SAOPIN2 = 4;
                    if (tf2.equals("FDD")) {
//                        if (bts_start2.getText().equals(TITLEZDZM)) {//自动侦码
//                            MainUtils.sbzmLocation(IP2, mContext);  //侦码功能使用
//                        } else {//自动扫频
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
            ToastUtils.showToast("请选择扫频运营商");
        }
    };

    /**
     * 当前频道的集合 zs 制式
     *
     * @param saopinBeanList
     */
    private void saopinSend1(List<SaopinBean> saopinBeanList, String zs, int SAOPIN) {
        Log.d(TAG, "listImsiListTruesad1: " + listImsiListTrue);
        List<Integer> list = new ArrayList<>();
        Log.d(TAG, "AsaopinSend1: " + saopinBeanList + "制式" + zs);
        try {
            saopinBeanList = dbManagersaopin.getStudent();
            Log.d(TAG, "AsaopinSend1: " + saopinBeanList + "制式" + zs);
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
                    ToastUtils.showToast("扫频列表大于10条");
                } else {
                    MainUtils.sendspSocket(list, IP1);
                    Log.d(TAG, "saopinSend1: " + list);
                }

            } else {
                ToastUtils.showToast("当前没有" + zs + "的制式");

            }

        } else {
            ToastUtils.showToast("当前没有频点");
        }

    }

    /**
     * 当前频道的集合 zs 制式
     *
     * @param saopinBeanList
     */
    private void saopinSend2(List<SaopinBean> saopinBeanList, String zs, int SAOPIN) {
        Log.d(TAG, "listImsiListTruesad1: " + listImsiListTrue);
        List<Integer> list = new ArrayList<>();
        Log.d(TAG, "AsaopinSend1: " + saopinBeanList + "制式" + zs);
        try {
            saopinBeanList = dbManagersaopin.getStudent();
            Log.d(TAG, "AsaopinSend1: " + saopinBeanList + "制式" + zs);
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
                    ToastUtils.showToast("扫频列表大于10条");
                } else {
                    MainUtils.sendspSocket(list, IP2);
                    Log.d(TAG, "saopinSend1: " + list);
                }

            } else {
                ToastUtils.showToast("当前没有" + zs + "的制式");

            }

        } else {
            ToastUtils.showToast("当前没有频点");
        }

    }

    //建立小区1
    private void EstablishVillageSS(final String sp1MAX1value) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<PinConfigBean> pinConfigBeans = null;
                try {

                    if (TextUtils.isEmpty(sp1MAX1value)) {
                        ToastUtils.showToast("下行频点不能为空");
                        return;
                    }
                    pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(sp1MAX1value)); //查询对应的频点

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Conmmunit01Bean forid = null;
                try {
                    DBManager01 dbManager01 = new DBManager01(mContext);
                    forid = dbManager01.forid(1);  //查询小区1

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (forid == null) {
//                    ToastUtils.showToast("小区1配置错误");
                    SetStatusBar("小区1配置错误", 1);
                    return;
                }
                if (TextUtils.isEmpty(forid.getTac())) {
                    ToastUtils.showToast("小区1设备配置TAC未设置");
                    return;
                }
                if (TextUtils.isEmpty(forid.getCid())) {
                    ToastUtils.showToast("小区1设备配置CID未设置");
                    return;
                }
                if (TextUtils.isEmpty(forid.getPci())) {
                    ToastUtils.showToast("小区1设备配置pci未设置");
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
                InetAddress address = null;//你本机的ip地址
                Log.e("znzq", "run: nzqsend");

//               Log.d(TAG, "run: " + s);
                byte[] outputData = null;
                String plmn = "";
                if (bts_start1.getText().equals(TITLEZDZM)) {//自动动侦码
                    plmn = spBean1.getPlmn();
                    DOWNPIN1 = spBean1.getDown();//用于显示小区侦码imsi 的下行频点
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
//                Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
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
//                                                    SpinnersSetData(1);//设置两个spinner下拉框数据
                                                        message = new Message();
                                                        Bundle bundle = new Bundle();
                                                        bundle.putString("SpinnersSetData", "1");
                                                        message.setData(bundle);
                                                        mHandler.sendMessage(message);
                                                        message.what = 8154;
                                                    } else {
                                                        ToastUtils.showToast("无可用频点");
                                                    }
                                                } else {
                                                    ToastUtils.showToast("无可用频点");
                                                }
                                                Log.d(TAG, "pinConfigBeansinit: " + pinConfigBeans);
                                            } catch (SQLException e) {
                                                e.printStackTrace();
                                            }
                                            //查询所以数据
                                        } else {
//                Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();

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
//                                            SpinnersSetData(1);//设置两个spinner下拉框数据
//

                                                message = new Message();
                                                Bundle bundle = new Bundle();
                                                bundle.putString("SpinnersSetData", "1");
                                                message.setData(bundle);
                                                mHandler.sendMessage(message);
                                                message.what = 8154;
                                            } else {
                                                ToastUtils.showToast("无可用频点");
                                            }
                                        } else {
                                            ToastUtils.showToast("无可用频点");
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
//                Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
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
//                                                    SpinnersSetData(1);//设置两个spinner下拉框数据
                                                    message = new Message();
                                                    Bundle bundle = new Bundle();
                                                    bundle.putString("SpinnersSetData", "1");
                                                    message.setData(bundle);
                                                    mHandler.sendMessage(message);
                                                    message.what = 8154;
                                                } else {
                                                    ToastUtils.showToast("无可用频点");
                                                }
                                            } else {
                                                ToastUtils.showToast("无可用频点");
                                            }
                                            Log.d(TAG, "pinConfigBeansinit: " + pinConfigBeans);
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                        //查询所以数据
                                    } else {
//                Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();

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
//                                            SpinnersSetData(1);//设置两个spinner下拉框数据
                                            message = new Message();
                                            Bundle bundle = new Bundle();
                                            bundle.putString("SpinnersSetData", "1");
                                            message.setData(bundle);
                                            mHandler.sendMessage(message);
                                            message.what = 8154;
                                        } else {
                                            ToastUtils.showToast("无可用频点");
                                        }
                                    } else {
                                        ToastUtils.showToast("无可用频点");
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
                Log.e("startLocation1s", "run: sendsocket端口号" + outputPacket.getPort() + "Ip地址:" + outputPacket.getAddress().toString() + "数据:" + outputPacket.getData());

                try {
                    if (sb1.equals("定位中")) {

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

    //建立小区2
    private void EstablishVillageSS2(final String sp2MAX1value) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<PinConfigBean> pinConfigBeans = null;
                try {
                    pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(sp2MAX1value)); //查询对应的频点

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
                    forid = dbManager01.forid(2);  //查询小区2

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (forid == null) {
//                    ToastUtils.showToast("小区2配置错误");
                    SetStatusBar("小区1配置错误", 1);
                    return;
                }

                if (TextUtils.isEmpty(forid.getTac())) {
                    ToastUtils.showToast("小区2设备配置TAC未设置");
                    return;
                }
                if (TextUtils.isEmpty(forid.getCid())) {
                    ToastUtils.showToast("小区2设备配置CID未设置");
                    return;
                }
                if (TextUtils.isEmpty(forid.getPci())) {
                    ToastUtils.showToast("小区2设备配置pci未设置");
                    return;
                }
                DatagramSocket socket = null;
                InetAddress address = null;//你本机的ip地址
                Log.e("znzq", "run: nzqsend");
//                String plmn = MyUtils.PLMN(SAOPIN2);

                String plmn = "";
                if (bts_start2.getText().equals(TITLEZDZM)) {//自动动侦码
                    plmn = spBean154.getPlmn();
                    DOWNPIN2 = spBean154.getDown();
                } else {
                    plmn = MyUtils.PLMN(SAOPIN2);
                }
                String band = MainUtils.getBand(Integer.parseInt(spBean154.getDown()));
                byte[] outputData = null;
                if (spBean154.getUp().equals("255")) {//如果是TDD
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
//                Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
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
//                                                    SpinnersSetData(2);//设置两个spinner下拉框数据
                                                        message = new Message();
                                                        Bundle bundle = new Bundle();
                                                        bundle.putString("SpinnersSetData", "2");
                                                        message.setData(bundle);
                                                        mHandler.sendMessage(message);
                                                        message.what = 8154;
                                                    } else {
                                                        ToastUtils.showToast("无可用频点");
                                                    }
                                                } else {
                                                    ToastUtils.showToast("无可用频点");
                                                }
                                                Log.d(TAG, "pinConfigBeansinit: " + pinConfigBeans);
                                            } catch (SQLException e) {
                                                e.printStackTrace();
                                            }
                                            //查询所以数据
                                        } else {
//                Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();

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
//                                            SpinnersSetData(2);//设置两个spinner下拉框数据
                                                message = new Message();
                                                Bundle bundle = new Bundle();
                                                bundle.putString("SpinnersSetData", "2");
                                                message.setData(bundle);
                                                mHandler.sendMessage(message);
                                                message.what = 8154;
                                            } else {
                                                ToastUtils.showToast("无可用频点");
                                            }
                                        } else {
                                            ToastUtils.showToast("无可用频点");
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
                } else {//如果是FDD
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
//                Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
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
//                                                    SpinnersSetData(2);//设置两个spinner下拉框数据


                                                    message = new Message();
                                                    Bundle bundle = new Bundle();
                                                    bundle.putString("SpinnersSetData", "2");
                                                    message.setData(bundle);
                                                    mHandler.sendMessage(message);
                                                    message.what = 8154;
//                                                    message = new Message();
//                                                    bundle.putString("SpinnersSetData", "1");//查询增益
//                                                    message.setData(bundle);
//                                                    handler.sendMessage(message);
//                                                    message.what = 200151;
                                                } else {
                                                    ToastUtils.showToast("无可用频点");
                                                }
                                            } else {
                                                ToastUtils.showToast("无可用频点");
                                            }
                                            Log.d(TAG, "pinConfigBeansinit: " + pinConfigBeans);
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                        //查询所以数据
                                    } else {
//                Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();

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
//                                            SpinnersSetData(2);//设置两个spinner下拉框数据
                                            message = new Message();
                                            Bundle bundle = new Bundle();
                                            bundle.putString("SpinnersSetData", "2");
                                            message.setData(bundle);
                                            mHandler.sendMessage(message);
                                            message.what = 8154;
                                        } else {
                                            ToastUtils.showToast("无可用频点");
                                        }
                                    } else {
                                        ToastUtils.showToast("无可用频点");
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
                Log.e("startLocation1s", "run: sendsocket端口号" + outputPacket.getPort() + "Ip地址:" + outputPacket.getAddress().toString() + "数据:" + outputPacket.getData());

                try {
                    if (sb2.equals("定位中")) {

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

    public void saopinList() { //扫频列表
        if (sb1.equals("定位中")) {
//            ToastUtils.showToast("定位中小区查看不可用");
            start1sp(false);//不可建立小区
        } else {
            SaoPinB1 = false;
            start1sp(false);//不可建立小区
        }

    }

    public void saopinList2() { //扫频列表
        if (sb2.equals("定位中")) {
//            ToastUtils.showToast("定位中小区查看不可用");
            start2sp(false);//不可建立小区

        } else {
            SaoPinB2 = false;
            start2sp(false);//不可建立小区
        }

//
    }

    //设备1扫频
    public void start1sp(boolean phonesp) {//扫频的点击事件

        if (phonesp) {//扫频建立小区
            if ("就绪".equals(sb1) || "连接中...".equals(sb1) || "定位中".equals(sb1) || "连接失败".equals(sb1) || "扫频同步进行中".equals(sb1) || "小区激活中".equals(sb1) || "侦码中".equals(sb1)) {
//
                DialogUtils.SaoPinDialog(1, mContext, inflate, saoPinCallback, tf1, true);
                SaoPinB1 = true;
            } else {
                ToastUtils.showToast("设备未连接");
            }
        } else {//小区查看
            if ("就绪".equals(sb1) || "连接中...".equals(sb1) || "定位中".equals(sb1) || "连接失败".equals(sb1) || "扫频同步进行中".equals(sb1) || "小区激活中".equals(sb1) || "侦码中".equals(sb1)) {
//
                DialogUtils.SaoPinDialog2(1, mContext, inflate, saoPinCallback, tf1, false, sb1);
            } else {
                ToastUtils.showToast("设备未连接");
            }
        }
    }

    //设备2扫频
    public void start2sp(boolean phonesp) {
        if (phonesp) {

            if ("就绪".equals(sb2) || "连接中...".equals(sb2) || "定位中".equals(sb2) || "连接失败".equals(sb2) || "扫频同步进行中".equals(sb2) || "小区激活中".equals(sb2) || "侦码中".equals(sb2)) {
//
                DialogUtils.SaoPinDialog(2, mContext, inflate, saoPinCallback, tf2, true);
            } else {
                ToastUtils.showToast("设备未连接");
            }
        } else {

            if ("就绪".equals(sb2) || "连接中...".equals(sb2) || "定位中".equals(sb2) || "连接失败".equals(sb2) || "扫频同步进行中".equals(sb2) || "小区激活中".equals(sb2) || "侦码中".equals(sb2)) {
//
                DialogUtils.SaoPinDialog2(2, mContext, inflate, saoPinCallback, tf2, false, sb2);
            } else {
                ToastUtils.showToast("设备未连接");
            }
        }

    }

    public void saopinList(View view) { //扫频列表
        if (sb1.equals("定位中")) {
            ToastUtils.showToast("定位中小区查看不可用");
//            start1sp(false);//不可建立小区
        } else {
            SaoPinB1 = false;
            start1sp(false);//不可建立小区
        }

    }

    public void saopinList2(View view) { //扫频列表
        if (sb2.equals("定位中")) {
//            ToastUtils.showToast("定位中小区查看不可用");
            start2sp(false);//不可建立小区

        } else {
            SaoPinB2 = false;
            start2sp(false);//不可建立小区
        }

//
    }

    public void lishi1() {//小区历史1
        if (SPBEANLIST1Fragment.size() > 0) {
//      Set1StatusBar("当前扫频列表可用");
            Intent intent = new Intent(mContext, SaoPinActivity.class);
            intent.putExtra("type", "1");
            startActivity(intent);
        } else {
//            Set1StatusBar("当前扫频列表不可用");
            ToastUtils.showToast("无小区历史记录");
        }
    }

    public void lishi2() {//小区历史2
        if (SPBEANLIST2Fragment.size() > 0) {
//            Set1StatusBar("当前扫频列表可用");
            Intent intent = new Intent(mContext, SaoPinActivity.class);
            intent.putExtra("type", "2");
            startActivity(intent);
        } else {
//            Set2StatusBar("当前扫频列表不可用");
            ToastUtils.showToast("无小区历史记录");
        }
    }
}
