package com.sm.android.locations.location.viewpagermain.NewMainPager;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
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
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.sm.android.locations.location.Activity.AddParam.AddParamActivity;
import com.sm.android.locations.location.Activity.AddParam.ParamActivity;
import com.sm.android.locations.location.Activity.AddParam.ParamWhiteActivity;
import com.sm.android.locations.location.Activity.Communit.CommunitViewPagerActivity;
import com.sm.android.locations.location.Activity.Device.DeviceInfoActivity;
import com.sm.android.locations.location.Activity.Main.Adapter.RyImsiAdapter;
import com.sm.android.locations.location.Activity.Main.Adapter.RyZmAdapter;
import com.sm.android.locations.location.Activity.Main.Adapter.RyZmAdapterGk;
import com.sm.android.locations.location.Activity.Main.Adapter.RyZmAdapterdw;
import com.sm.android.locations.location.Activity.Main.IT.SaoPinCallback;
import com.sm.android.locations.location.Activity.Main.IT.ZmDataCallBack;
import com.sm.android.locations.location.Activity.Main.Objects.States;
import com.sm.android.locations.location.Activity.Main.View.SlideButton;
import com.sm.android.locations.location.Activity.PinConfig.PinConfigViewPagerActivity;
import com.sm.android.locations.location.Activity.SaoPin.SaoPinActivity.SaoPinActivity;
import com.sm.android.locations.location.Activity.SaoPin.SaopinList.SaoPinSetingActivity;
import com.sm.android.locations.location.Base.BaseActivity;
import com.sm.android.locations.location.Constant.Constant;
import com.sm.android.locations.location.Lunxun.SaopinList.LunxunSetingActivity;
import com.sm.android.locations.location.R;
import com.sm.android.locations.location.Utils.DialogUtils;
import com.sm.android.locations.location.Utils.LoginUtils.LoginUtils;
import com.sm.android.locations.location.Utils.MainUtils.AddMenuUtils;
import com.sm.android.locations.location.Utils.MainUtils.DbUtils;
import com.sm.android.locations.location.Utils.MainUtils.MainUtils;
import com.sm.android.locations.location.Utils.MainUtils.MainUtils2;
import com.sm.android.locations.location.Utils.MainUtils.MainUtilsThread;
import com.sm.android.locations.location.Utils.MyUtils;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.AddPararBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.AddPararBeanWhite;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.LogBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.PararBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.PinConfigBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.SaopinBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.SpBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.ZmBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.ZmBeanGK;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.ZmBeanGKTongji;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.ZmBeanlinshi;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerAddParam;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerAddParamWhite;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerLog;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerPinConfig;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerZM;

import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerZMGK;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerZMlinshi;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerZY;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagersaopin;
import com.sm.android.locations.location.Utils.ToastUtils;
import com.sm.android.locations.location.Utils.View.LineChartView;
import com.sm.android.locations.location.Utils.View.MyScrollView;
import com.sm.android.locations.location.Utils.pop.DLPopItem;
import com.sm.android.locations.location.Utils.pop.DLPopupWindow;
import com.sm.android.locations.location.viewpagermain.NewMainPager.update.dingwei.DwUpdate;
import com.sm.android.locations.location.viewpagermain.NewMainPager.update.dingwei.OpnUpdate;
import com.sm.android.locations.location.zhenma.ZhenmaFenxiActivity;

import java.io.UnsupportedEncodingException;
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
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import static android.graphics.Color.parseColor;
import static com.sm.android.locations.location.Activity.Main.MainActivity.SPBEANLIST1;
import static com.sm.android.locations.location.Activity.Main.MainActivity.SPBEANLIST2;
import static com.sm.android.locations.location.Constant.Constant.BOARDTEMPERATURE1;

import static com.sm.android.locations.location.Constant.Constant.DOWNPIN1;
import static com.sm.android.locations.location.Constant.Constant.DOWNPIN2;
import static com.sm.android.locations.location.Constant.Constant.IP1;
import static com.sm.android.locations.location.Constant.Constant.IP2;
import static com.sm.android.locations.location.Constant.Constant.SPBEANLIST1Fragment;
import static com.sm.android.locations.location.Constant.Constant.SPBEANLIST2Fragment;
import static com.sm.android.locations.location.Utils.MainUtils.MainUtils.DS;
import static com.sm.android.locations.location.Utils.MainUtils.MainUtils.i;
import static com.sm.android.locations.location.Utils.MainUtils.MainUtils.location;
import static com.sm.android.locations.location.Utils.MainUtils.MainUtils2.toIMSI;
import static com.sm.android.locations.location.Utils.MyUtils.removeDuplicate;
import static com.sm.android.locations.location.viewpagermain.Fragment.SendUtils.setzy;

public class NewMainActivity extends BaseActivity implements View.OnClickListener, NewView.View {
    @SuppressLint("NewApi")
    DBManagerLog dbManagerLog = null;
    SimpleDateFormat sdf;
    LogBean logBean;
    String format;
    private boolean saopinshow1 = false;  //???????????? ???????????????
    private boolean saopinshow2 = false;
    ImageButton ib_zhediedw, ib_zhedie_zhenmadw;//????????????
    private TextToSpeech textToSpeech = null;//????????????????????????
    ImageView laba1dw, laba2dw;
    private boolean laba1Flag = true;//??????????????????
    private boolean laba2Flag = true;
    LinearLayout layout, ll_zhenma_searchdw;
    @SuppressLint("NewApi")
    DecimalFormat dfBaoshu = new DecimalFormat("###");
    private Timer timerdw1 = null;//????????????imsi????????????
    private Timer timerdw2 = null;//????????????imsi????????????
    private Timer timer = null;//????????????imsi????????????
    private Timer timerLocation = null;//????????????imsi????????????
    private boolean sb1FirstFlag = false;
    private boolean sb2FirstFlag = false;
    RyImsiAdapter ryImsiAdapter;
    List<AddPararBean> pararBeansList1 = new ArrayList<>();
    private int SAOPIN = 1;//?????????type??????1
    private int SAOPIN2 = 1;//?????????type??????1
    private String sp1MAX1value = "";//??????1?????????1?????????
    private String sp1MAX2value = "";//??????1?????????2?????????
    private String sp2MAX1value = "";//??????2?????????1?????????
    private String sp2MAX2value = "";//??????2?????????2?????????
    private String TAG = "NewMainActivity";
    NewView.MainPresenter presenter;
    private Context context;
    private int Mylayout = R.layout.activity_new_main;
    private int MainType = 0;
    List<Integer> list1quxian = null;//??????1???????????????
    List<Integer> list2quxian = null;//??????2???????????????
    List<String> listsSp = new ArrayList<>();
    private LinearLayout ll_0, ll_1, ll_2;
    private ScrollView rl0, rl2;
    private RelativeLayout rl1;
    private ImageButton bt_0, bt_1, bt_2;
    private ImageView iv_wendu, iv_fengshan, iv_menu;
    private TextView tv_pin1, tv_pin2, tv_titles, tv_wendu, tv1_wifi_dw, tv2_wifi_dw, tv1_type_dw, tv2_type_dw, tv1_td_dw, tv2_td_dw, tv_imsi1_dw, tv_imsi2_dw, tv_r1dw, tv_r2dw, sb1_jl, sb2_jl;//?????? ??????
    CheckBox cbzt1_d, cbzt1_z, cbzt1_g, cbzt2_d, cbzt2_z, cbzt2_g;// ??????????????? ??????
    private boolean SB1ZY = false;//???????????????????????????????????????
    private boolean SB2ZY = false;//???????????????????????????????????????
    EditText et_zhenmasearchdw;
    TextView tv_searchNumdw;
    private StringBuffer stringBuffer1dw = null;
    private StringBuffer stringBuffer2dw = null;
    LineChartView lineChartViewdw;
    ScrollView scrollViewdw;//??????????????????????????????
    TextView textViewsdw;//?????????1
    ScrollView scrollView2dw;//??????????????????????????????2
    TextView text2Viewsdw;//?????????2
    String tf1 = "";
    String tf2 = "";
    private boolean zdFlagdw = false;//???????????????
    private boolean zdSearchFlagdw = false;//???????????????
    public String sb1 = "";
    public String sb2 = "";
    public String sb1_zm = "";
    public String sb2_zm = "";
    public String sb1_gk = "";
    public String sb2_gk = "";
    public static boolean FENGSHANFLAG = true;
    Message message;
    Bundle bundle;
    private static Timer timer1, timer2, timer_wendu;
    DatagramPacket dp;
    byte[] buf;
    private boolean runThread = false;
    private int wifitrue = 0;
    private int wififalse = 0;
    private DLPopupWindow popupWindow;
    private List<DLPopItem> mList = new ArrayList<>();
    Dialog dialog;
    View inflate;
    RecyclerView ryIMSI_dw, ry_zhenma_dw;
    Spinner mysp1, mysp2;
    ArrayAdapter<String> adapter1, adapter2, adapter3;
    private String spinnerS1 = "", spinnerS2 = "";
    SlideButton slideButton1, slideButton2;//????????????    ??????????????????
    private boolean slideButton1Flag = true;
    private boolean slideButton2Flag = true;
    Button bt_start1dw, bt_start2dw, bt_stop1dw, bt_stop2dw;
    private boolean TIMERRESTARTFLAG1 = true;  //??????????????????1  ???????????? true
    private boolean TIMERRESTARTFLAG2 = true;  //??????????????????1  ???????????? true
    private boolean phoneFlag1 = true;// ??????????????????
    private boolean phoneFlag2 = true;// ??????????????????
    RyZmAdapter ryZmAdapter;
    RyZmAdapterdw ryZmAdapterdw;
    ////////////////////////////////////////////////////////////////////////////////////////////////////////  ??????????????????
    TextView tv1_type_zm, tv1_td_zm, tv1_wifi_zm, tv2_type_zm, tv2_td_zm, tv2_wifi_zm, tv_searchNum_zm;
    EditText et_zhenmasearch_zm;
    ImageButton ib_qc_zm, ib_dc_zm;
    RecyclerView ry_zhenma_zm;
    Button bts_start1_zm, bts_stop_zm;
    MyScrollView scrollView1zm, scrollView2zm;
    TextView textViews1zm, textViews2zm;
    private StringBuffer stringBuffer1zm = null;
    private StringBuffer stringBuffer2zm = null;
    List<String> listsucessDown1;
    List<String> listsucessDown2;
    private Timer timerSB1 = null;//????????????imsi????????????
    private Timer timerSB2 = null;//????????????imsi????????????
    private String Mydwon1 = "";//??????????????????
    private String Mydwon2 = "";//??????????????????
    private boolean saopinFalg1 = false;
    private boolean saopinFalg2 = false;

    private boolean saopinGKFalg1 = false;
    private boolean saopinGKFalg2 = false;
    public String startdatazm1;
    public String startdatazm2;
    // ?????????????????????
    public java.text.SimpleDateFormat aDateFormat = new java.text.SimpleDateFormat("" + "yyyy-MM-dd HH:mm:ss");
    public int lunxunNum1 = 1;
    public int lunxunNum2 = 1;
    private TextView tv_xunhuanNum1_zm, tv_xunhuanNum2_zm, tv_datashengyu1, tv_datashengyu2;
    CountDownTimer countDownTimer1;
    CountDownTimer countDownTimer2;
    private Timer timerzmjianli1 = null;//????????????imsi????????????
    private Timer timerzmjianli2 = null;//????????????imsi????????????
    private boolean ZMRRESTARTFLAG1 = true;  //??????????????????1  ???????????? true
    private boolean ZMRRESTARTFLAG2 = true;  //??????????????????1  ???????????? true
    //////////////////////////////////////////////////??????////////////////////////////
    private TextView tv1_type_gk, tv1_td_gk, tv1_wifi_gk, tv2_tf_gk, tv2_type_gk, tv2_wifi_gk, tv_pin1gk, tv_pin2gk, textViews2_gk, textViews_gk1;
    private RecyclerView ry_gk;
    private Button bts_start1_gk, bts_stop_gk;
    private StringBuffer stringBuffer1gk = null;
    private StringBuffer stringBuffer2gk = null;
    MyScrollView scrollView1_gk, scrollView2gk;
    CheckBox cb_gk1, cb_gk2;
    private int GuankongType = 1;
    String mydown1GK = "";
    String mydown11GK = "";
    String mydown2GK = "";
    String mydown22GK = "";
    RyZmAdapterGk ryZmAdapterGk;
    private boolean GKRRESTARTFLAG1 = true;  //??????????????????1  ???????????? true
    private boolean GKRRESTARTFLAG2 = true;  //??????????????????1  ???????????? true
    private long MymillisUntilFinished;
    private long MymillisUntilFinished2;
    private Timer timerZhenmaStop;

    @Override
    protected void findViews() {
        new NewPersent(this);
        context = this;
        //?????????
        iv_wendu = findViewById(R.id.iv_wendu);
        iv_fengshan = findViewById(R.id.iv_fengshan);
        iv_fengshan.setOnClickListener(this);
        iv_menu = findViewById(R.id.iv_menu);
        iv_menu.setOnClickListener(this);
        tv_titles = findViewById(R.id.titles);
        tv_titles.setText("??????");
        tv_wendu = findViewById(R.id.tv_wendu);
        //?????????   (??????)
        ll_0 = findViewById(R.id.ll_0);
        ll_1 = findViewById(R.id.ll_1);
        ll_2 = findViewById(R.id.ll_2);
        rl1 = findViewById(R.id.rl1);
        rl0 = findViewById(R.id.rl0);
        rl2 = findViewById(R.id.rl2);
        bt_0 = findViewById(R.id.bt_0);
        bt_1 = findViewById(R.id.bt_1);
        bt_2 = findViewById(R.id.bt_2);
        ll_0.setOnClickListener(this);//??????????????????
        ll_1.setOnClickListener(this);//??????????????????
        ll_2.setOnClickListener(this);//??????????????????????????????
        bt_0.setOnClickListener(this);
        bt_1.setOnClickListener(this);
        bt_2.setOnClickListener(this);
        mList.clear();
        AddITemMenu(0);//?????????????????????
        // ????????????

        laba1dw = findViewById(R.id.laba1dw);
        laba1dw.setOnClickListener(this);
        laba2dw = findViewById(R.id.laba2dw);
        laba2dw.setOnClickListener(this);

        ib_zhediedw = findViewById(R.id.ib_zhedie);
        ib_zhedie_zhenmadw = findViewById(R.id.ib_zhedie_zhenma);
        ib_zhediedw.setOnClickListener(this);
        ib_zhedie_zhenmadw.setOnClickListener(this);
        ll_zhenma_searchdw = findViewById(R.id.ll_zhenma_search);
        tv1_wifi_dw = findViewById(R.id.tv1_wifi_dw);
        tv2_wifi_dw = findViewById(R.id.tv2_wifi_dw);
        tv_imsi1_dw = findViewById(R.id.tv_imsi1_dw);
        tv_imsi2_dw = findViewById(R.id.tv_imsi2_dw);
        tv_r1dw = findViewById(R.id.tv_r1dw);
        tv_r2dw = findViewById(R.id.tv_r2dw);
        cbzt1_d = findViewById(R.id.cbzt1_d);
        cbzt1_z = findViewById(R.id.cbzt1_z);
        cbzt1_g = findViewById(R.id.cbzt1_g);
        cbzt2_d = findViewById(R.id.cbzt2_d);
        cbzt2_z = findViewById(R.id.cbzt2_z);
        cbzt2_g = findViewById(R.id.cbzt2_g);
        tv1_type_dw = findViewById(R.id.tv1_type_dw);
        tv2_type_dw = findViewById(R.id.tv2_type_dw);
        tv1_td_dw = findViewById(R.id.tv1_td_dw);
        tv2_td_dw = findViewById(R.id.tv2_td_dw);
        lineChartViewdw = (LineChartView) findViewById(R.id.line_chart_viewdw);
        scrollViewdw = findViewById(R.id.scrollView1dw);
        scrollViewdw.fullScroll(ScrollView.FOCUS_DOWN);//???????????????
        textViewsdw = findViewById(R.id.textViews1dw);

        scrollView2dw = findViewById(R.id.scrollView2dw);
        scrollView2dw.fullScroll(ScrollView.FOCUS_DOWN);//???????????????
        text2Viewsdw = findViewById(R.id.textViews2dw);
        sb1_jl = findViewById(R.id.tv_sb1_jl_dw);
        sb2_jl = findViewById(R.id.sb1_j2_dw);
        ryIMSI_dw = findViewById(R.id.ryIMSI_dw);//?????? IMSI ????????????
        ryIMSI_dw.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ry_zhenma_dw = findViewById(R.id.ry_zhenma_dw);//??????????????????????????????
        et_zhenmasearchdw = findViewById(R.id.et_zhenmasearchdw);
        setEt_search();//?????????????????????

        tv_searchNumdw = findViewById(R.id.tv_searchNumdw);
        mysp1 = findViewById(R.id.mysp1);//??????1?????????
        mysp2 = findViewById(R.id.mysp2);
        slideButton1 = findViewById(R.id.slideButton1);
        slideButton2 = findViewById(R.id.slideButton2);
        bt_start1dw = findViewById(R.id.bt_start1dw);
        bt_start2dw = findViewById(R.id.bt_start2dw);
        bt_stop1dw = findViewById(R.id.bt_stop1dw);
        bt_stop2dw = findViewById(R.id.bt_stop2dw);
        bt_start1dw.setOnClickListener(this);
        bt_start2dw.setOnClickListener(this);
        bt_stop1dw.setOnClickListener(this);
        bt_stop2dw.setOnClickListener(this);

        ///////////////////////////////////////////////////////////////////////////////????????????id
        tv1_type_zm = findViewById(R.id.tv1_type_zm);  //????????????
        tv2_type_zm = findViewById(R.id.tv2_type_zm);
        tv1_td_zm = findViewById(R.id.tv1_td_zm);//  TDD fdd
        tv2_td_zm = findViewById(R.id.tv2_tf_zm);
        tv1_wifi_zm = findViewById(R.id.tv1_wifi_zm);  //wifi
        tv2_wifi_zm = findViewById(R.id.tv2_wifi_zm);
        tv_searchNum_zm = findViewById(R.id.tv_searchNum_zm);//????????????
        et_zhenmasearch_zm = findViewById(R.id.et_zhenmasearch_zm);//??????????????????
        setEt_searchZM();
        ib_qc_zm = findViewById(R.id.ib_qc_zm);//????????????
        ib_qc_zm.setOnClickListener(this);
        ib_dc_zm = findViewById(R.id.ib_dc_zm);//????????????
        ib_dc_zm.setOnClickListener(this);
        ry_zhenma_zm = findViewById(R.id.ry_zhenma_zm);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
//
        ry_zhenma_zm.setLayoutManager(linearLayoutManager);
        bts_start1_zm = findViewById(R.id.bts_start1_zm);
        bts_stop_zm = findViewById(R.id.bts_stop_zm);
        bts_start1_zm.setOnClickListener(this);//????????????
        bts_stop_zm.setOnClickListener(this);//????????????

        scrollView1zm = findViewById(R.id.scrollView1zm);
        textViews1zm = findViewById(R.id.textViews1zm);
        scrollView2zm = findViewById(R.id.scrollView2zm);
        textViews2zm = findViewById(R.id.textViews2zm);
        tv_xunhuanNum1_zm = findViewById(R.id.tv_xunhuanNum1_zm);
        tv_xunhuanNum2_zm = findViewById(R.id.tv_xunhuanNum2_zm);
        tv_datashengyu1 = findViewById(R.id.tv_datashengyu1);
        tv_datashengyu2 = findViewById(R.id.tv_datashengyu2);
        tv_pin1 = findViewById(R.id.tv_pin1);
        tv_pin2 = findViewById(R.id.tv_pin2);
        ////////////////////////////////////////////////////??????ID////////////////////////////////////
        tv1_type_gk = findViewById(R.id.tv1_type_gk);
        tv1_td_gk = findViewById(R.id.tv1_td_gk);
        tv1_wifi_gk = findViewById(R.id.tv1_wifi_gk);
        tv2_type_gk = findViewById(R.id.tv2_type_gk);
        tv2_tf_gk = findViewById(R.id.tv2_tf_gk);
        tv2_wifi_gk = findViewById(R.id.tv2_wifi_gk);
        tv_pin1gk = findViewById(R.id.tv_pin1gk);
        tv_pin2gk = findViewById(R.id.tv_pin2gk);
        scrollView1_gk = findViewById(R.id.scrollView1_gk);
        scrollView2gk = findViewById(R.id.scrollView2gk);
        textViews2_gk = findViewById(R.id.textViews2_gk);
        textViews_gk1 = findViewById(R.id.textViews_gk1);
        ry_gk = findViewById(R.id.ry_gk);
        ry_gk.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        bts_start1_gk = findViewById(R.id.bts_start1_gk);//???????????????
        bts_start1_gk.setOnClickListener(this);
        bts_stop_gk = findViewById(R.id.bts_stop_gk);//????????????
        bts_stop_gk.setOnClickListener(this);
        cb_gk1 = findViewById(R.id.cb_gk1);//?????? ??????   ??????
        cb_gk2 = findViewById(R.id.cb_gk2);//????????????   ?????? ?????????

    }

    private void setEt_searchZM() {//???????????????
        et_zhenmasearch_zm.addTextChangedListener(new TextWatcher() {
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
                    Log.d("et_zhenmasearch_zm", "afterTextChanged:1 ");
                    DBManagerZM dbManagerZM = null;
                    try {
                        dbManagerZM = new DBManagerZM(context);
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
                            if (zmBeans.get(i).getImsi().contains(str) && zmBeans.get(i).getMaintype().equals("1")) {
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
                        tv_searchNum_zm.setText("(" + "0" + ")");
                    } else {
                        if (zmBeanscontains.size() > 10) {
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                            linearLayoutManager.setStackFromEnd(true);//recyview?????????????????????
                            ry_zhenma_zm.setLayoutManager(linearLayoutManager);
                            tv_searchNum_zm.setText("(" + listsize.size() + ")");
                        } else {
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                            linearLayoutManager.setStackFromEnd(false);//recyview?????????????????????
                            ry_zhenma_zm.setLayoutManager(linearLayoutManager);
                            tv_searchNum_zm.setText("(" + listsize.size() + ")");
                        }
                        ryZmAdapter = new RyZmAdapter(Basecontext, zmBeanscontains, listsize);//list???imsi?????????????????????
                        ry_zhenma_zm.setAdapter(ryZmAdapter);
                    }
                } else {
                    Log.d("et_zhenmasearch_zm", "afterTextChanged:2 ");
                    DBManagerZM dbManagerZM = null;
                    try {
                        dbManagerZM = new DBManagerZM(context);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    //?????????????????????imsi??????
                    List<ZmBean> zmBeans = new ArrayList<>();
                    List<ZmBean> zmBeanss = new ArrayList<>();
                    try {
                        zmBeanss = dbManagerZM.getDataAll();
                        for (int i = 0; i < zmBeanss.size(); i++) {
                            if (zmBeanss.get(i).getMaintype().equals("1")) {
                                zmBeans.add(zmBeanss.get(i));
                            }

                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    List<Integer> listsize = new ArrayList<>();
                    for (int i = 0; i < zmBeans.size(); i++) {
                        listsize.add(i + 1);

                    }
                    Log.d("dbManagerZM", "handleMessage: " + i);
                    Log.d("dbManagerZMaa", "handleMessage:size " + zmBeans.toString());
                    ryZmAdapter = new RyZmAdapter(Basecontext, zmBeans, listsize);//list???imsi?????????????????????
                    if (et_zhenmasearch_zm.getText().length() == 0) {


//                        ry_zhenma.setAdapter(ryZmAdapter);
                        if (zmBeans.size() > 10) {
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                            linearLayoutManager.setStackFromEnd(true);//recyview?????????????????????
                            ry_zhenma_zm.setLayoutManager(linearLayoutManager);
                            tv_searchNum_zm.setText("(" + zmBeans.size() + ")");
                        } else {
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                            linearLayoutManager.setStackFromEnd(false);//recyview?????????????????????
                            ry_zhenma_zm.setLayoutManager(linearLayoutManager);
                            tv_searchNum_zm.setText("(" + zmBeans.size() + ")");
                        }
                        //?????????????????????imsi??????
                        zmBeans = new ArrayList<>();
                        zmBeanss = new ArrayList<>();
                        try {
                            zmBeanss = dbManagerZM.getDataAll();
                            for (int i = 0; i < zmBeanss.size(); i++) {
                                if (zmBeanss.get(i).getMaintype().equals("1")) {
                                    zmBeans.add(zmBeanss.get(i));
                                }

                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        listsize = new ArrayList<>();
                        for (int i = 0; i < zmBeans.size(); i++) {
                            listsize.add(i + 1);

                        }
                        ryZmAdapter = new RyZmAdapter(Basecontext, zmBeans, listsize);//list???imsi?????????????????????
                        ry_zhenma_zm.setAdapter(ryZmAdapter);
                    }
                }

            }
        });
    }

    private void setEt_search() {
        et_zhenmasearchdw.addTextChangedListener(new TextWatcher() {
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
                        dbManagerZM = new DBManagerZM(context);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    //?????????????????????imsi??????
                    List<ZmBean> zmBeanss = new ArrayList<>();
                    List<ZmBean> zmBeans = new ArrayList<>();
                    try {
                        zmBeanss = dbManagerZM.getDataAll();
                        for (int i = 0; i < zmBeanss.size(); i++) {
                            if (zmBeanss.get(i).getMaintype().equals("00")) {
                                zmBeans.add(zmBeanss.get(i));
                            }
                        }
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
                        tv_searchNumdw.setText("(" + "0" + ")");
                    } else {
                        if (zmBeanscontains.size() > 6) {
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                            linearLayoutManager.setStackFromEnd(true);//recyview?????????????????????
                            ry_zhenma_dw.setLayoutManager(linearLayoutManager);
                            tv_searchNumdw.setText("(" + listsize.size() + ")");
                        } else {
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                            linearLayoutManager.setStackFromEnd(false);//recyview?????????????????????
                            ry_zhenma_dw.setLayoutManager(linearLayoutManager);
                            tv_searchNumdw.setText("(" + listsize.size() + ")");
                        }
                        ryZmAdapterdw = new RyZmAdapterdw(Basecontext, zmBeanscontains, listsize);//list???imsi?????????????????????
                        ry_zhenma_dw.setAdapter(ryZmAdapterdw);
                    }
                } else {

                }

            }
        });
    }

    private void setSlidubutton() {
        slideButton1.setChecked(true);
        slideButton2.setChecked(true);
        mysp1.setVisibility(View.GONE);

        mysp2.setVisibility(View.GONE);
        slideButton1.setSmallCircleModel(parseColor("#10A146"), parseColor("#00000000"), parseColor("#10A146"), parseColor("#cccccc"));
        slideButton1.setOnCheckedListener(new SlideButton.SlideButtonOnCheckedListener() {
            @Override
            public void onCheckedChangeListener(boolean isChecked) {
//                ToastUtils.showToast(isChecked + "");
                slideButton1Flag = isChecked;
                if (isChecked) {
                    slideButton1.setChecked(true);
//                    slideButton2.setChecked(true);
                    mysp1.setVisibility(View.GONE);
//                    mysp2.setVisibility(View.GONE);
                    mysp1.setEnabled(false);
//                    mysp2.setEnabled(false);
                } else {
                    slideButton1.setChecked(false);
//                    slideButton2.setChecked(false);
                    mysp1.setVisibility(View.VISIBLE);
//                    mysp2.setVisibility(View.VISIBLE);
                    mysp1.setEnabled(true);
//                    mysp2.setEnabled(true);
                }
            }
        });
//        slideButton1.set
        slideButton2.setSmallCircleModel(parseColor("#FF6367"), parseColor("#00000000"), parseColor("#FF6367"), parseColor("#cccccc"));
        slideButton2.setOnCheckedListener(new SlideButton.SlideButtonOnCheckedListener() {
            @Override
            public void onCheckedChangeListener(boolean isChecked) {
//                ToastUtils.showToast(isChecked + "");
                slideButton2Flag = isChecked;
                if (isChecked) {
//                    slideButton1.setChecked(true);
                    slideButton2.setChecked(true);
//                    mysp1.setVisibility(View.GONE);
                    mysp2.setVisibility(View.GONE);
//                    mysp1.setEnabled(false);
                    mysp2.setEnabled(false);
                } else {
//                    slideButton1.setChecked(false);
                    slideButton2.setChecked(false);
//                    mysp1.setVisibility(View.VISIBLE);
                    mysp2.setVisibility(View.VISIBLE);

//                    mysp1.setEnabled(true);
                    mysp2.setEnabled(true);
                }
            }
        });
    }

    private void AddITemMenu(int i) {
        popupWindow = new DLPopupWindow(context, mList, DLPopupWindow.STYLE_WEIXIN);
        if (i == 0) {
            mList.clear();
            AddMenuUtils.addmenu(this, popupWindow, mList);
        }
        if (i == 1) {
            mList.clear();
            AddMenuUtils.addmenuZhenma(this, popupWindow, mList);
        }
        if (i == 2) {
            mList.clear();
            AddMenuUtils.addmenuguankong(this, popupWindow, mList);
        }
        popupWindow.setOnItemClickListener(new DLPopupWindow.OnItemClickListener() {
            @Override
            public void OnClick(int position) {
//                ToastUtils.showToast(mList.get(position).getText());
                if (mList.get(position).getText().equals("??????IMSI")) {
                    startActivity(new Intent(context, AddParamActivity.class));
                }
                if (mList.get(position).getText().equals("??????IMSI")) {
                    startActivity(new Intent(context, ParamActivity.class));
//                    startActivity(new Intent(context, Param2Activity.class));
                }
                if (mList.get(position).getText().equals("??????IMSI")) {
                    startActivity(new Intent(context, ParamWhiteActivity.class));
//                    startActivity(new Intent(context, Param2Activity.class));
                }
                if (mList.get(position).getText().equals("????????????")) {
                    startActivity(new Intent(context, SaoPinSetingActivity.class));
                }

                if (mList.get(position).getText().equals("????????????")) {
//                    startActivity(new Intent(context, PinConfigActivity.class));//?????????
                    startActivity(new Intent(context, PinConfigViewPagerActivity.class));
                }
                if (mList.get(position).getText().equals("????????????")) {
//                    startActivity(new Intent(context, Community01Activity.class));
                    startActivity(new Intent(context, CommunitViewPagerActivity.class));
                }

                if (mList.get(position).getText().equals("????????????")) {
                    startActivity(new Intent(context, DeviceInfoActivity.class));
                }
                if (mList.get(position).getText().equals("????????????")) {
//                    startActivity(new Intent(context, DeviceInfoActivity.class));
//                    ToastUtils.showToast("????????????");
                    DialogUtils.DataExport(context, inflate, zmDataCallBack);
                }
                if (mList.get(position).getText().equals("????????????")) {
                    startActivity(new Intent(context, LunxunSetingActivity.class));
                }
                if (mList.get(position).getText().equals("????????????")) {
                    context.startActivity(new Intent(context, ZhenmaFenxiActivity.class));
                }

            }
        });
    }

    ZmDataCallBack zmDataCallBack = new ZmDataCallBack() {
        @Override
        public void sucess(String dir, String datetime) {

        }

        @Override
        public void deleall() {
            DBManagerZM dbManagerZM = null;
            try {
                dbManagerZM = new DBManagerZM(context);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //?????????????????????imsi??????
            List<ZmBean> zmBeanss = new ArrayList<>();
            List<ZmBean> zmBeans = new ArrayList<>();
            try {
                zmBeanss = dbManagerZM.getDataAll();
                for (int i = 0; i < zmBeanss.size(); i++) {
                    if (zmBeanss.get(i).getMaintype().equals("1")) {
                        zmBeans.add(zmBeanss.get(i));
                    }

                }
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
                ry_zhenma_zm.setAdapter(ryZmAdapter);
                tv_searchNum_zm.setText("(" + "0" + ")");
            } else {
                ryZmAdapter = new RyZmAdapter(Basecontext, zmBeans, listsize);//list???imsi?????????????????????
                ry_zhenma_zm.setAdapter(ryZmAdapter);
                tv_searchNum_zm.setText("(" + listsize.size() + ")");
            }
        }
    };

    @Override
    protected void initQx() {
        getPermissions();
    }

    //  public   String id="1";
    @Override
    protected void init() throws UnsupportedEncodingException {
//        SharedPreferences userSettings = getSharedPreferences("setting", 0);
//        SharedPreferences.Editor editor = userSettings.edit();
//
//        editor.putString("id", "1");
//        Log.e("myid", "Logins: "+adminBean.getId());
//        editor.commit();
        message = new Message();
        bundle = new Bundle();
        timer1 = new Timer();
        timer2 = new Timer();
        timer_wendu = new Timer();
        buf = new byte[1024];
        dp = new DatagramPacket(buf, buf.length);
        MainUtils.ReceiveMainWD(handler, message, bundle, timer_wendu);
        MainUtils.ReceiveMain(handler, message, bundle, timer1, timer2, dp, buf, context, runThread);//??????????????????
        MainUtils.WifiMain(handler, message, bundle, timer1, timer2, dp, buf, context);//??????wifi??????
        stringBuffer1dw = new StringBuffer();
        stringBuffer2dw = new StringBuffer();
        stringBuffer1zm = new StringBuffer();
        stringBuffer2zm = new StringBuffer();
        stringBuffer1gk = new StringBuffer();
        stringBuffer2gk = new StringBuffer();
        CheckBoxOnclickSet();
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
        setqxData(list1quxian, list2quxian);
        listsSp.clear();
        try {//????????? ???????????????????????????
            DBManagerZM dbManagerZM = new DBManagerZM(this);
            List<ZmBean> zmBeans = dbManagerZM.getDataAll();
            for (int i = 0; i < zmBeans.size(); i++) {
                if (zmBeans.get(i).getMaintype().equals("0")) {
                    dbManagerZM.deleteAddPararBean(zmBeans.get(i));
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBManagerZMGK dbManagergk = null;
        try {//????????? ???????????????  ??????????????????
            dbManagergk = new DBManagerZMGK(this);

            dbManagergk.deleteall();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //??????gkIMSI????????????
        //?????????????????????imsi??????
        List<ZmBeanGK> zmBeanssA = null;
        try {
            zmBeanssA = dbManagergk.getDataAll();
            Log.d("TAGzmBeanss", "init: " + zmBeanssA.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        List<ZmBeanGKTongji>list=new ArrayList<>();
//        ZmBeanGKTongji zmBeanGKTongji=null;
//        for (int i = 0; i < zmBeanssA.size(); i++) {
//            zmBeanGKTongji=new ZmBeanGKTongji();
//            zmBeanGKTongji.setSb(zmBeanssA.get(i).getSb());
//            zmBeanGKTongji.setDatatime(zmBeanssA.get(i).getDatatime());
//            zmBeanGKTongji.setNum("111");
//            zmBeanGKTongji.setTime(zmBeanssA.get(i).getTime());
//            zmBeanGKTongji.setDown(zmBeanssA.get(i).getDown());
//            zmBeanGKTongji.setMaintype(zmBeanssA.get(i).getMaintype());
//            zmBeanGKTongji.setImsi(zmBeanssA.get(i).getImsi());
//            list.add(zmBeanGKTongji);
//        }
//        List<Integer> listsize = new ArrayList<>();
//        for (int i = 0; i < zmBeanssA.size(); i++) {
//            listsize.add(i + 1);
//
//        }
//        Log.d("dbManagerZM", "handleMessage: " + i);
//        Log.d("dbManagerZM", "handleMessage:size " + zmBeanssA.toString());
//        ryZmAdapterGk = new RyZmAdapterGk(context, list, listsize);//list???imsi?????????????????????
////            if (et_zhenmasearchdw.getText().length() == 0) {
////                        ry_zhenma.setAdapter(ryZmAdapter);
//        if (zmBeanssA.size() > 6) {
//            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
//            linearLayoutManager.setStackFromEnd(true);//recyview?????????????????????
//            ry_gk.setLayoutManager(linearLayoutManager);
////                tv_searchNumdw.setText("(" + zmBeans.size() + ")");
//        } else {
//            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
//            linearLayoutManager.setStackFromEnd(false);//recyview?????????????????????
//            ry_gk.setLayoutManager(linearLayoutManager);
////                tv_searchNumdw.setText("(" + zmBeans.size() + ")");
//        }
//        ryZmAdapterGk = new RyZmAdapterGk(context, list, listsize);//list???imsi?????????????????????
//        ry_gk.setAdapter(ryZmAdapterGk);

//?????????????????????imsi??????
        List<ZmBeanGK> zmBeanss = null;
        try {
            zmBeanss = dbManagergk.getDataAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        ry_gk.setAdapter(ryZmAdapterGk);

        if (list2.size() > 10) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setStackFromEnd(true);//recyview?????????????????????
            ry_gk.setLayoutManager(linearLayoutManager);
//                tv_searchNumdw.setText("(" + zmBeans.size() + ")");
        } else {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setStackFromEnd(false);//recyview?????????????????????
            ry_gk.setLayoutManager(linearLayoutManager);
//                tv_searchNumdw.setText("(" + zmBeans.size() + ")");
        }
        try {
            DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
            List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
            Log.d(TAG, "AdataAllinit: " + dataAll.toString());
            for (int i = 0; i < dataAll.size(); i++) {
                AddPararBean addPararBean = dataAll.get(i);
                addPararBean.setCheckbox(false);
                dbManagerAddParam.updateCheck(addPararBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setPinnerdata();//??????????????????
        setSlidubutton();//??????????????????
        ImslList();
        initTTS();
        DwUpdate.ryIMSIUp(ryIMSI_dw, context, config, tv_imsi1_dw, tv_imsi2_dw);

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
        iv_menu.setVisibility(View.VISIBLE);
        iv_wendu.setVisibility(View.VISIBLE);
        iv_fengshan.setVisibility(View.VISIBLE);


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
        zhenmaShow();//????????????????????????
    }

    private void zhenmaShow() {
        DBManagerZM dbManagerZM = null;
        try {
            dbManagerZM = new DBManagerZM(context);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //?????????????????????imsi??????
        List<ZmBean> zmBeans = new ArrayList<>();
        List<ZmBean> zmBeanss = new ArrayList<>();
        try {
            zmBeanss = dbManagerZM.getDataAll();
            for (int i = 0; i < zmBeanss.size(); i++) {
                if (zmBeanss.get(i).getMaintype().equals("1")) {
                    zmBeans.add(zmBeanss.get(i));
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Integer> listsize = new ArrayList<>();
        for (int i = 0; i < zmBeans.size(); i++) {
            listsize.add(i + 1);

        }
        Log.d("dbManagerZM", "handleMessage: " + i);
        Log.d("dbManagerZMaa", "handleMessage:size " + zmBeans.toString());
        ryZmAdapter = new RyZmAdapter(Basecontext, zmBeans, listsize);//list???imsi?????????????????????
        if (et_zhenmasearch_zm.getText().length() == 0) {


//                        ry_zhenma.setAdapter(ryZmAdapter);
            if (zmBeans.size() > 10) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                linearLayoutManager.setStackFromEnd(true);//recyview?????????????????????
                ry_zhenma_zm.setLayoutManager(linearLayoutManager);
                tv_searchNum_zm.setText("(" + zmBeans.size() + ")");
            } else {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                linearLayoutManager.setStackFromEnd(false);//recyview?????????????????????
                ry_zhenma_zm.setLayoutManager(linearLayoutManager);
                tv_searchNum_zm.setText("(" + zmBeans.size() + ")");
            }
            ryZmAdapter = new RyZmAdapter(Basecontext, zmBeans, listsize);//list???imsi?????????????????????
            ry_zhenma_zm.setAdapter(ryZmAdapter);
        }
    }

    private void setPinnerdata() {
        listsSp.clear();
        DBManagerPinConfig dbManagerPinConfig = null;
        List<PinConfigBean> pinConfigBeans = null;
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
        try {
            DBManagerZMlinshi dbManagerZMlinshi = new DBManagerZMlinshi(context);
            List<ZmBeanlinshi> dataAll = dbManagerZMlinshi.getDataAll();
            Log.d("uuudataAll", "setPinnerdata: " + dataAll);
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
                    Log.d(TAG, "2onItemSelected: " + spinnerS2);

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }

    }

    /**
     * ?????????????????????
     *
     * @param list1quxian
     * @param list2quxian
     */
    private void setqxData(List<Integer> list1quxian, List<Integer> list2quxian) {
        lineChartViewdw.setShowTable(true);
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
        lineChartViewdw.setData(datas, datas2);
        lineChartViewdw.setBezierLine(true);
        lineChartViewdw.setRulerYSpace(200);
        lineChartViewdw.setStepSpace(30);
        scrollViewdw.fullScroll(ScrollView.FOCUS_DOWN);//???????????????
        scrollViewdw.post(new Runnable() {
            @Override
            public void run() {
                //???????????????
                scrollViewdw.fullScroll(ScrollView.FOCUS_DOWN);
                //???????????????
                //scrollview.fullScroll(ScrollView.FOCUS_UP);
            }
        });
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
                        DBManagerZY dbManagerZY = new DBManagerZY(context);
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
                        DBManagerZY dbManagerZY = new DBManagerZY(context);
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
                        DBManagerZY dbManagerZY = new DBManagerZY(context);
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
                        DBManagerZY dbManagerZY = new DBManagerZY(context);
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
                        DBManagerZY dbManagerZY = new DBManagerZY(context);
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
                        DBManagerZY dbManagerZY = new DBManagerZY(context);
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
        //????????????
        cb_gk1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cb_gk2.setChecked(false);
                    GuankongType = 1;
                } else {
                    cb_gk2.setChecked(true);
                    GuankongType = 2;
                }
            }
        });
        cb_gk2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cb_gk1.setChecked(false);
                    GuankongType = 2;
                } else {
                    cb_gk1.setChecked(true);
                    GuankongType = 1;
                }
            }
        });
    }

    RyImsiAdapter.IDialogPinConfig config = new RyImsiAdapter.IDialogPinConfig() {
        @Override
        public void call(final String imsi, String sb) {
            if (sb.equals("1")) {
//
                dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
                inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
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
                                byte[] outputData = MainUtilsThread.hexStringToByteArray(location(imsi, "04", sa, context, 1));
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
//                                    sb1locationgFlag = true;
                                    //                                    socket.send(outputPacket);
                                    DS.send(outputPacket);
//
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
//                                LocationFlag1 = true;
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

                dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
                inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
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
                                byte[] outputData = MainUtilsThread.hexStringToByteArray(location(imsi, "04", sa, context, 2));
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
//                                    sb2locationgFlag = true;
                                    //                                    socket.send(outputPacket);
                                    DS.send(outputPacket);
//
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
//                                LocationFlag2 = true;
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
    private Handler handler = new Handler() {
        @SuppressLint({"NewApi", "HandlerLeak"})
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 8153://?????????????????????????????????53 ??????????????????
                    DecimalFormat df2;
                    df2 = new DecimalFormat("####");
                    if (TextUtils.isEmpty(BOARDTEMPERATURE1)) {
                        break;
                    }
                    OpnUpdate.upwendu(tv_wendu, iv_wendu, BOARDTEMPERATURE1, df2, context, iv_fengshan, FENGSHANFLAG);
                    break;
//                case 7001://?????????????????????????????????53 ??????????????????
//                    if (sb1.equals("?????????") || sb1.equals("????????????????????????") || sb1.equals("???????????????") || sb1.equals("??????????????????")) {
//                        timerdw1.cancel();
//
//                    } else if (sb1.equals("??????")) {
//                        presenter.saopinjianlixiaoqu(context, 1, tf1, sp1MAX1value);
//                    }
//                    break;
//                case 7002://?????????????????????????????????53 ??????????????????
//                    if (sb2.equals("?????????") || sb2.equals("????????????????????????") || sb2.equals("???????????????") || sb2.equals("??????????????????")) {
//                        timerdw2.cancel();
//
//                    } else if (sb2.equals("??????")) {
//                        presenter.saopinjianlixiaoqu(context, 2, tf2, sp2MAX1value);
//                    }
//                    break;
                case 4445:
                    if (!sb1_zm.equals("??????")) {
                        MainUtils.StopLocation(IP1);
                    }

                    if (!sb2_zm.equals("??????")) {
                        MainUtils.StopLocation(IP2);
                    }

                    if (sb1_zm.equals("??????") && sb2_zm.equals("??????")) {
                        if (timerZhenmaStop != null) {
                            timerZhenmaStop.cancel();
                        }
                    }
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
                    break;
                case 100001:
                    if (MainType == 0) {
                        DwUpdate.upwifi(msg, tv1_wifi_dw, tv2_wifi_dw,null,null);
                    }
                    if (MainType == 1) {
                        DwUpdate.upwifi(msg, tv1_wifi_zm, tv2_wifi_zm,null,null);
                    }
                    if (MainType == 2) {
                        DwUpdate.upwifi(msg, tv1_wifi_gk, tv2_wifi_gk,null,null);
                    }
                    break;
                case 9001:
                    if (tf1.equals("TDD")) {
                        if (sb1_zm.equals("?????????")) {
                            Log.d("9001TAG", "????????????handleMessage: ");

                            if (Mydwon1.equals(listsucessDown1.get(0))) {
                                listsucessDown1.remove(0);
                                listsucessDown1.add(Mydwon1);
                                Mydwon1 = listsucessDown1.get(0);
                                presenter.setLunxunJianLixiaoqu(context, 1, Mydwon1, sb1_zm);
                                Log.d("9001TAG?????????", "???????????????handleMessage: " + Mydwon1);
                            } else {
                                Mydwon1 = listsucessDown1.get(0);
                                presenter.setLunxunJianLixiaoqu(context, 1, Mydwon1, sb1_zm);
                            }
                            break;
                        } else {
                            MainUtils.sbzmLocation(IP1, context);  //??????????????????aaaaaaaaa
//                        Mydwon1 = listsucessDown1.get(0);
                            Log.d("9001TAG", "???????????????handleMessage: " + Mydwon1);
                        }
                    }


                    break;

                case 9002:
                    if (tf2.equals("FDD")) {
                        if (sb2_zm.equals("?????????")) {
                            Log.d("9001TAG", "????????????handleMessage: ");

                            if (Mydwon2.equals(listsucessDown2.get(0))) {
                                listsucessDown2.remove(0);
                                listsucessDown2.add(Mydwon2);
                                Mydwon2 = listsucessDown2.get(0);
                                presenter.setLunxunJianLixiaoqu(context, 2, Mydwon2, sb2_zm);
                                Log.d("9002TAG?????????", "???????????????handleMessage: " + Mydwon2);

                            } else {
                                Mydwon2 = listsucessDown2.get(0);
                                presenter.setLunxunJianLixiaoqu(context, 2, Mydwon2, sb2_zm);
                            }


                            break;
                        } else {
                            MainUtils.sbzmLocation(IP2, context);  //??????????????????aaaaaaaaa
//                        Mydwon1 = listsucessDown1.get(0);
                            Log.d("9002TAG", "???????????????handleMessage: " + Mydwon2);

                        }
                    }
                    break;
                case 30000://
                    if (MainType == 2) {  //????????????
                        String imsi = msg.getData().getString("imsi");
                        Log.d("TAGMainType", "handleMessage: " + imsi);
                        presenter.setryGK(context, ry_gk, mydown1GK, mydown2GK, ryZmAdapterGk, msg);
                        break;
                    }
                    if (MainType == 0) {//????????????
                        presenter.setRyimsidw(context, ry_zhenma_dw, msg, ryZmAdapterdw, et_zhenmasearchdw, tv_searchNumdw);
//                        TableUtils.createTable(connectionSource, ZmBeanlinshi.class);//????????????

//
                        sbImsiType(msg);
                        break;
                    }
                    if (MainType == 1) {  //????????????
                        DBManagerZM dbManagerZM = null;
                        String imsi = msg.getData().getString("imsi");
                        Log.d("MainType==1", "handleMessage: " + imsi);

                        try {
                            dbManagerZM = new DBManagerZM(context);
                            ZmBean zmBean = new ZmBean();
                            zmBean.setImsi(imsi);
                            if (msg.getData().getString("sb").equals("1")) {
                                if (!TextUtils.isEmpty(DOWNPIN1)) {
                                    zmBean.setDown(DOWNPIN1);
                                    zmBean.setMaintype("1");

                                    zmBean.setSb(msg.getData().getString("sb"));
                                    zmBean.setTime(msg.getData().getString("time"));
                                    zmBean.setDatatime(msg.getData().getString("datetime"));
                                    int i = dbManagerZM.insertAddZmBean(zmBean);
                                    //?????????????????????????????????
                                    ZmBeanlinshi zmBeanlinshi = new ZmBeanlinshi();
                                    DBManagerZMlinshi dbManagerZMlinshi = new DBManagerZMlinshi(context);
                                    zmBeanlinshi.setImsi(imsi);
                                    zmBeanlinshi.setDown(DOWNPIN1);
                                    zmBeanlinshi.setTime(msg.getData().getString("time"));
                                    zmBeanlinshi.setSb(msg.getData().getString("sb"));
                                    zmBeanlinshi.setCheck("0");
                                    if (!TextUtils.isEmpty(startdatazm1)) {
                                        zmBeanlinshi.setStartdatatime(startdatazm1);
                                    } else {
                                        startdatazm1 = aDateFormat.format(new Date());
                                        zmBeanlinshi.setStartdatatime(startdatazm1);
                                    }

                                    zmBeanlinshi.setDatatime(msg.getData().getString("datetime"));
                                    int i2 = dbManagerZMlinshi.insertAddZmBeanlinshi(zmBeanlinshi);
                                } else {

                                }

                            }
                            if (msg.getData().getString("sb").equals("2")) {

                                if (!TextUtils.isEmpty(DOWNPIN2)) {
                                    zmBean.setDown(DOWNPIN2);
                                    zmBean.setMaintype("1");
                                    zmBean.setSb(msg.getData().getString("sb"));
                                    zmBean.setTime(msg.getData().getString("time"));
                                    zmBean.setDatatime(msg.getData().getString("datetime"));
                                    int i = dbManagerZM.insertAddZmBean(zmBean);
                                    //?????????????????????????????????
                                    ZmBeanlinshi zmBeanlinshi = new ZmBeanlinshi();
                                    DBManagerZMlinshi dbManagerZMlinshi = new DBManagerZMlinshi(context);
                                    zmBeanlinshi.setImsi(imsi);
                                    zmBeanlinshi.setDown(DOWNPIN2);
                                    zmBeanlinshi.setCheck("0");
                                    zmBeanlinshi.setTime(msg.getData().getString("time"));
                                    zmBeanlinshi.setSb(msg.getData().getString("sb"));
                                    if (!TextUtils.isEmpty(startdatazm1)) {
                                        zmBeanlinshi.setStartdatatime(startdatazm1);
                                    } else {
                                        startdatazm2 = aDateFormat.format(new Date());
                                        zmBeanlinshi.setStartdatatime(startdatazm1);
                                    }

                                    zmBeanlinshi.setDatatime(msg.getData().getString("datetime"));
                                    int i2 = dbManagerZMlinshi.insertAddZmBeanlinshi(zmBeanlinshi);
                                }

                            }
                            //?????????????????????imsi??????
                            List<ZmBean> zmBeanss = dbManagerZM.getDataAll();

                            List<ZmBean> zmBeans = new ArrayList<>();
                            for (int i = 0; i < zmBeanss.size(); i++) {
                                if (zmBeanss.get(i).getMaintype().equals("1")) {
                                    zmBeans.add(zmBeanss.get(i));
                                }
                            }
                            List<Integer> listsize = new ArrayList<>();
                            for (int i = 0; i < zmBeans.size(); i++) {
                                listsize.add(i + 1);

                            }
                            Log.d("dbManagerZM", "handleMessage: " + i);
                            Log.d("dbManagerZMbb", "handleMessage:size " + zmBeans.toString());
                            ryZmAdapter = new RyZmAdapter(Basecontext, zmBeans, listsize);//list???imsi?????????????????????
                            if (et_zhenmasearch_zm.getText().length() == 0) {


//                        ry_zhenma.setAdapter(ryZmAdapter);
                                if (zmBeans.size() > 12) {
                                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                                    linearLayoutManager.setStackFromEnd(true);//recyview?????????????????????
                                    ry_zhenma_zm.setLayoutManager(linearLayoutManager);
                                    tv_searchNum_zm.setText("(" + zmBeans.size() + ")");
                                } else {
                                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                                    linearLayoutManager.setStackFromEnd(false);//recyview?????????????????????
                                    ry_zhenma_zm.setLayoutManager(linearLayoutManager);
                                    tv_searchNum_zm.setText("(" + zmBeans.size() + ")");
                                }
                                ryZmAdapter = new RyZmAdapter(Basecontext, zmBeans, listsize);//list???imsi?????????????????????
                                ry_zhenma_zm.setAdapter(ryZmAdapter);
                            }


                        } catch (SQLException e) {
                            e.printStackTrace();
                            Log.d("300001SQLException", "handleMessage: ." + e.getMessage());
                        }

                    }
                    break;
                case 300002:
                    tv_imsi1_dw.setText("");
                    sb1_jl.setText("");
                    tv_imsi2_dw.setText("");
                    sb2_jl.setText("");
                    break;
                case 300001:
                    if (MainType == 0) {
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

                                                //                                    socket.send(outputPacket);
                                                DS.send(outputPacket);
//
                                                sb1FirstFlag = false;
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            listFirstIMSI1.clear();


                                        }
                                    }).start();
                                }

                            }
                        }
                        if (listFirstIMSI2.size() > 0) {
                            if (sb2FirstFlag == true) {
                                if (sb2.equals("?????????")) {
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

                                                //                                    socket.send(outputPacket);
                                                DS.send(outputPacket);
                                                sb2FirstFlag = false;
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            listFirstIMSI2.clear();

                                        }
                                    }).start();
                                }

                            }

                        }
                        List<AddPararBean> pararBeansListA = new ArrayList<>();
                        Log.d(TAG, "handleMessage: " + pararBeansList1);

                        ryImsiAdapter = new RyImsiAdapter(Basecontext, pararBeansList1, size, config, tv_imsi1_dw, tv_imsi2_dw);//list???imsi?????????????????????
                        ryIMSI_dw.setAdapter(ryImsiAdapter);
                        listStates.clear();
                    }


                    break;

                case 100920:
                    if (listsucessDown1.size() == 1) {//?????????????????????1

                    } else if (listsucessDown1.size() > 0) {
                        int size = listsucessDown1.size();
                        long time = size * DbUtils.getTime(context, 1);
                        setShengyu1(time);
                        countDownTimer1.start();
                    }
                    break;
                case 200920:
                    if (listsucessDown2.size() == 1) {//?????????????????????2

                    } else if (listsucessDown2.size() > 0) {
                        int size = listsucessDown2.size();
                        long time = size * DbUtils.getTime(context, 1);
                        setShengyu2(time);
                        countDownTimer2.start();
                    }
                    break;
                case 100110:
                    if (MainType == 1) {//????????????
                        tf1 = msg.getData().getString("tf1");
                        tv1_td_zm.setText("????????????:" + tf1);
                    }
                    if (MainType == 0) {//????????????
                        tf1 = msg.getData().getString("tf1");
                        tv1_td_dw.setText("????????????:" + tf1);
                    }
                    if (MainType == 2) {//????????????
                        tf1 = msg.getData().getString("tf1");
                        tv1_td_gk.setText("????????????:" + tf1);
                    }
                    break;
                case 100191:
                    if (MainType == 2) {//????????????
                        String jieguo = msg.getData().getString("100191");
                        if (jieguo.equals("1")) {
//                            Set1StatusBarGK("?????????????????????");
                            if (GuankongType == 1) {
                                //??????
                                presenter.spbuilsGK(context, 1, 1, tf1, tf2);//??????????????????
                            }
                            if (GuankongType == 2) {
                                //??????
                                presenter.spbuilsGK(context, 1, 3, tf1, tf2);//??????????????????
                            }
                        } else {
//                            Set1StatusBarGK("?????????????????????");
                            if (GuankongType == 1) {
                                //??????
                                presenter.spbuilsGK(context, 1, 1, tf1, tf2);//??????????????????
                            }
                            if (GuankongType == 2) {
                                //??????
                                presenter.spbuilsGK(context, 1, 3, tf1, tf2);//??????????????????
                            }
                        }

                    }
                    break;
                case 200191:
                    if (MainType == 2) {//????????????
                        String jieguo = msg.getData().getString("200191");
                        if (jieguo.equals("1")) {
//                            Set2StatusBarGK("?????????????????????");
//                       //??????
                            if (GuankongType == 1) {
                                //??????
                                presenter.spbuilsGK(context, 2, 2, tf1, tf2);//??????????????????
                            }
                            if (GuankongType == 2) {
                                //??????
                                presenter.spbuilsGK(context, 2, 4, tf1, tf2);//??????????????????
                            }
                        } else {
//                            Set2StatusBarGK("?????????????????????");
                            if (GuankongType == 1) {
                                //??????
                                presenter.spbuilsGK(context, 2, 2, tf1, tf2);//??????????????????
                            }
                            if (GuankongType == 2) {
                                //??????
                                presenter.spbuilsGK(context, 2, 4, tf1, tf2);//??????????????????
                            }
                        }

                    }
                    break;
                case 100120: //??????1?????????????????????
                    if (MainType == 2) {//????????????
                        DwUpdate.type(context, MainType, msg, tv1_type_gk, tv1_td_gk, tf1);
                        String zt1 = msg.getData().getString("zt1");
                        if (!TextUtils.isEmpty(zt1)) {
                            if (zt1.equals("2")) {
                                sb1_gk = "??????";
                                tv1_type_gk.setText("????????????:??????");
                                Set1StatusBarGK("??????");
                                tv_pin1gk.setText("??????1??????: ");


                                if (GKRRESTARTFLAG1 == false) {
                                    Log.d("AGKRRESTARTFLAG1", "handleMessage:1 ");
                                    GKRRESTARTFLAG1 = true;
                                    if (GuankongType == 1) {//???????????????TDD
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

                                                    List<AddPararBeanWhite> sendwhitelist = new ArrayList<>();
                                                    for (int j = 0; j < listImsiListTrue.size(); j++) {
                                                        if (listImsiListTrue.get(j).getYy().equals("??????")) {
                                                            sendwhitelist.add(listImsiListTrue.get(j));
                                                        }
                                                    }
                                                    Log.d("AGKRRESTARTFLAG1", "handleMessage:2 ");
                                                    sendwhiteListRun(1, sendwhitelist, tf1, tf2, context, listImsiListTrue);//???????????????  2???
                                                    Log.d("AGKRRESTARTFLAG1", "handleMessage:3 ");

                                                } else {
//                                                    ToastUtils.showToast("?????????????????????IMSI");
                                                    List<AddPararBeanWhite> sendwhitelist = new ArrayList<>();
                                                    sendwhiteListRun(1, sendwhitelist, tf1, tf2, context, listImsiListTrue);//???????????????  2???
                                                    Log.d("AGKRRESTARTFLAG1", "handleMessage:4 ");
                                                }
                                            }
                                            Log.d("setGKtartaddPararBeans", "?????????init: " + listImsiListTrue.toString());
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                    } else {//??????FDD
                                        try {
                                            Log.d("AGKRRESTARTFLAG1", "handleMessage:5 ");
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

                                                    List<AddPararBeanWhite> sendwhitelist = new ArrayList<>();
                                                    for (int j = 0; j < listImsiListTrue.size(); j++) {
                                                        if (listImsiListTrue.get(j).getYy().equals("??????")) {
                                                            sendwhitelist.add(listImsiListTrue.get(j));
                                                        }
                                                    }
                                                    Log.d("AGKRRESTARTFLAG1", "handleMessage:6 ");
                                                    sendwhiteListRun(1, sendwhitelist, tf1, tf2, context, listImsiListTrue);//???????????????  2???


                                                } else {
//                                                    ToastUtils.showToast("?????????????????????IMSI");
                                                    List<AddPararBeanWhite> sendwhitelist = new ArrayList<>();
                                                    sendwhiteListRun(1, sendwhitelist, tf1, tf2, context, listImsiListTrue);//???????????????  2???
                                                    Log.d("AGKRRESTARTFLAG1", "handleMessage:7 ");
                                                }
                                            }
                                            Log.d("AGKRRESTARTFLAG1", "handleMessage:8 ");
                                            Log.d("setGKtartaddPararBeans", "?????????init: " + listImsiListTrue.toString());
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                } else {
                                    Log.d("AGKRRESTARTFLAG1", "handleMessage:9 ");
                                }
                            }
                            if (zt1.equals("3")) {
//                            tv1_type.setText("????????????: " + "?????????????????????");//????????????
                                sb1_gk = "?????????????????????";
                                Set1StatusBarGK("?????????????????????");
//                                tv1_type_zm.setText("????????????:?????????????????????");
                                break;
                            }
                            if (zt1.equals("4")) {
                                sb1_gk = "???????????????";
                                Set1StatusBarGK("???????????????");
                                break;
                            }
                            if (zt1.equals("5")) {
                                sb1_gk = "?????????";
                                Set1StatusBarGK("?????????");
                                tv1_type_gk.setText("????????????:?????????");
                                tv_pin1gk.setText("??????1??????:" + mydown1GK);
                            }
                        }
                    }
                    if (MainType == 1) {//????????????
                        DwUpdate.type(context, MainType, msg, tv1_type_zm, tv1_td_zm, tf1);
                        String zt1 = msg.getData().getString("zt1");
                        if (!TextUtils.isEmpty(zt1)) {
                            if (zt1.equals("2")) {
                                sb1_zm = "??????";
                                tv1_type_zm.setText("????????????:??????");
                                Set1StatusBarZM("??????");
                                tv_pin1.setText("??????1??????: ");

                                if (ZMRRESTARTFLAG1 == false) {
                                    Log.d("AGKRRESTARTFLAG1", "handleMessage:1 ");
                                    ZMRRESTARTFLAG1 = true;

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

                                    if (listDown1.size() == 0) {
                                        ToastUtils.showToast("??????1????????????0???");
                                        return;
                                    }
                                    Log.d("aaasdqwe", "setzmlistStart1: ");
                                    zmlunxunlist1(listDown1, listDown2);


                                }
                            }
                            if (zt1.equals("3")) {
//                            tv1_type.setText("????????????: " + "?????????????????????");//????????????
                                sb1_zm = "?????????????????????";
                                Set1StatusBarZM("?????????????????????");
//                                tv1_type_zm.setText("????????????:?????????????????????");
                                break;
                            }
                            if (zt1.equals("4")) {
                                sb1_zm = "???????????????";
                                Set1StatusBarZM("???????????????");
                                break;
                            }
                            if (zt1.equals("5")) {
                                sb1_zm = "?????????";
                                Set1StatusBarZM("?????????");
                                tv1_type_zm.setText("????????????:?????????");
                                tv_pin1.setText("??????1??????: " + Mydwon1);
                            }
                        }
                    }
                    if (MainType == 0) {  //????????????
                        DwUpdate.type(context, MainType, msg, tv1_type_dw, tv1_td_dw, tf1);
                        String zt1 = msg.getData().getString("zt1");
                        if (!TextUtils.isEmpty(zt1)) {
                            if (zt1.equals("2")) {
                                Set1StatusBar("??????");
                                tv_imsi1_dw.setText("");
                                sb1_jl.setText("");
                                tv1_type_dw.setText("????????????:??????");
                                if (slideButton1Flag) {
                                    mysp1.setVisibility(View.GONE);
                                } else {
                                    mysp1.setVisibility(View.VISIBLE);
                                }

                                tv_r1dw.setText("??????:");
                                mysp1.setEnabled(true);
                                if (slideButton1Flag) {
                                    if (phoneFlag1 == true) {
                                        if (TIMERRESTARTFLAG1 == false) {
                                            phoneFlag1 = false;
                                            presenter.setStart(1, slideButton1Flag, MainType, sb1, sb2, spinnerS1, spinnerS2, context, tf1, tf2, 0, false);
                                            TIMERRESTARTFLAG1 = true;
                                        }
                                    } else {
                                        if (TIMERRESTARTFLAG1 == false) {
                                            presenter.sendsaopin(context, 1, SAOPIN);
                                            TIMERRESTARTFLAG1 = true;
                                        }
                                    }

                                } else {//????????????????????????

                                    if (TIMERRESTARTFLAG1 == false) {
                                        presenter.setStart(1, slideButton1Flag, MainType, sb1, sb2, spinnerS1, spinnerS2, context, tf1, tf2, 0, false);

                                        TIMERRESTARTFLAG1 = true;
                                    }

                                }

                                sb1 = "??????";
                                if (SB1ZY == false) {
                                    Log.d(TAG, "handleMessage: .?????????????????????");

                                    DBManagerZY dbManagerZY = null;
                                    try {
                                        dbManagerZY = new DBManagerZY(context);
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
                                        SB1ZY = true;
                                    }
                                    if (tf1.equals("FDD")) {
                                        int data = 0;
                                        try {
                                            data = dbManagerZY.foriddata(1, 2, 3);
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                        setzy(data, 1);
                                        SB1ZY = true;
                                    }
                                }
                            }
                            if (zt1.equals("3")) {
//                            tv1_type.setText("????????????: " + "?????????????????????");//????????????
                                sb1 = "?????????????????????";
                                Set1StatusBar("?????????????????????");
//                                tv1_type_dw.setText("????????????:?????????????????????");
                                break;
                            }
                            if (zt1.equals("4")) {
                                sb1 = "???????????????";
                                Set1StatusBar("???????????????");
                                break;
                            }
                            if (zt1.equals("5")) {
                                sb1 = "?????????";
                                Set1StatusBar("?????????");
                                tv1_type_dw.setText("????????????:" + "?????????");//????????????
                                mysp1.setEnabled(false);
                                mysp1.setVisibility(View.GONE);
                                tv_r1dw.setText("??????:" + DOWNPIN1);
                                if (SB1ZY == false) {
                                    Log.d(TAG, "handleMessage: .?????????????????????");

                                    DBManagerZY dbManagerZY = null;
                                    try {
                                        dbManagerZY = new DBManagerZY(context);
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
                                        SB1ZY = true;
                                    }
                                    if (tf1.equals("FDD")) {
                                        int data = 0;
                                        try {
                                            data = dbManagerZY.foriddata(1, 2, 3);
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                        setzy(data, 1);
                                        SB1ZY = true;
                                    }
                                }
                            }
                        }
                    }

                    break;
                case 1001200:
                    if (MainType == 2) {
                        String test = msg.getData().getString("test");
                        if (!TextUtils.isEmpty(test)) {
                            if (test.equals("1")) {
                                Set1StatusBarGK("??????????????????");
                                break;
                            }
                            if (test.equals("2")) {
                                Set1StatusBarGK("??????????????????");
//                                listsucessDown1.remove(0);
//                                listsucessDown1.add(Mydwon1);
//                                Mydwon1 = listsucessDown1.get(0);
//                                presenter.setLunxunJianLixiaoqu(context, 1, Mydwon1, sb1_zm);
//                                Log.d("lunxunNum1", "handleMessage: " + lunxunNum1);
                                break;
                            }
                            if (test.equals("3")) {
                                Set1StatusBarGK("GPS????????????");
                                break;
                            }
                            if (test.equals("4")) {
                                Set1StatusBarGK("GPS????????????");
                                break;
                            }
                            if (test.equals("5")) {
                                Set1StatusBarGK("????????????");


                                break;
                            }
                            if (test.equals("6")) {
                                Set1StatusBarGK("????????????");
                                break;
                            }
                            if (test.equals("7")) {
                                Set1StatusBarGK("??????????????????");
                                break;
                            }
                            if (test.equals("8")) {
                                Set1StatusBarGK("??????????????????");
                                break;
                            }
                            if (test.equals("9")) {
                                Set1StatusBarGK("???????????????");

                                break;
                            }
                            if (test.equals("10")) {
                                Set1StatusBarGK("???????????????");
                                break;
                            }
                            if (test.equals("11")) {
                                Set1StatusBarGK("GPS?????????");
                                break;
                            }
                            if (test.equals("12")) {
                                Set1StatusBarGK("?????????");
                                tv1_type_gk.setText("????????????:?????????");
                                break;
                            }
                            if (test.equals("13")) {
                                Set1StatusBarGK("???????????????");

                                break;
                            }
                            if (test.equals("14")) {
                                Set1StatusBarGK("??????????????????");
                                break;
                            }
                        }
                    }
                    if (MainType == 1) {
                        String test = msg.getData().getString("test");
                        if (!TextUtils.isEmpty(test)) {
                            if (test.equals("1")) {
                                Set1StatusBarZM("??????????????????");
                                break;
                            }
                            if (test.equals("2")) {
                                Set1StatusBarZM("??????????????????");
                                listsucessDown1.remove(0);
                                listsucessDown1.add(Mydwon1);
                                Mydwon1 = listsucessDown1.get(0);
                                presenter.setLunxunJianLixiaoqu(context, 1, Mydwon1, sb1_zm);
                                Log.d("lunxunNum1", "handleMessage: " + lunxunNum1);


                                if (listsucessDown1.size() == 1) {//?????????????????????1

                                } else if (listsucessDown1.size() > 0) {
                                    int size = listsucessDown1.size();
                                    long time = size * 60;
//                                    time=listsucessDown1.size()-1*60;
                                    if (MymillisUntilFinished / 1000 > 60) {
                                        Log.d("adsadq", "handleMessage: " + MymillisUntilFinished / 1000);
                                        long iaa = DbUtils.getTime(context, 1) * 1000;

                                        time = MymillisUntilFinished - iaa;
                                        Log.e("shijianaa", "handleMessage: " + MymillisUntilFinished + "\n" + iaa + "\n" + time);
                                        if (countDownTimer1 != null) {
                                            countDownTimer1.cancel();
                                            time = time / 1000;
                                            setShengyu1(time);
                                            countDownTimer1.start();
                                        }
                                    } else if (MymillisUntilFinished / 1000 < 60) {

                                        Log.d("uopmo", "handleMessage1: " + MymillisUntilFinished / 1000);
                                        if (countDownTimer1 != null) {
                                            countDownTimer1.cancel();
                                            setShengyu1(1);
                                            countDownTimer1.start();
                                            Log.d("uopmo", "handleMessage2: ");
                                        }
                                    }


                                }
                                break;
                            }
                            if (test.equals("3")) {
                                Set1StatusBarZM("GPS????????????");
                                break;
                            }
                            if (test.equals("4")) {
                                Set1StatusBarZM("GPS????????????");
                                break;
                            }
                            if (test.equals("5")) {
                                Set1StatusBarZM("????????????");


                                break;
                            }
                            if (test.equals("6")) {
                                Set1StatusBarZM("????????????");
                                break;
                            }
                            if (test.equals("7")) {
                                Set1StatusBarZM("??????????????????");
                                break;
                            }
                            if (test.equals("8")) {
                                Set1StatusBarZM("??????????????????");
                                break;
                            }
                            if (test.equals("9")) {
                                Set1StatusBarZM("???????????????");

                                break;
                            }
                            if (test.equals("10")) {
                                Set1StatusBarZM("???????????????");
                                break;
                            }
                            if (test.equals("11")) {
                                Set1StatusBarZM("GPS?????????");
                                break;
                            }
                            if (test.equals("12")) {
                                Set1StatusBarZM("?????????");
                                tv1_type_zm.setText("????????????:?????????");
                                break;
                            }
                            if (test.equals("13")) {
                                Set1StatusBarZM("???????????????");

                                break;
                            }
                            if (test.equals("14")) {
                                Set1StatusBar("??????????????????");
                                break;
                            }
                        }
                    }
                    if (MainType == 0) {
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
                                tv1_type_dw.setText("????????????:?????????");
                                break;
                            }
                            if (test.equals("13")) {
                                Set1StatusBar("???????????????");

                                break;
                            }
                            if (test.equals("14")) {
                                Set1StatusBar("??????????????????");
                                break;
                            }
                        }
                    }

                case 100131:
//                    Set1StatusBar("??????");
                    break;
                case 55661:
                    if (!sb1_zm.equals("?????????")) {
                        if (Mydwon1.equals(listsucessDown1.get(0))) {
                            listsucessDown1.remove(0);
                            listsucessDown1.add(Mydwon1);
                            Mydwon1 = listsucessDown1.get(0);
                            presenter.setLunxunJianLixiaoqu(context, 1, Mydwon1, sb1_zm);
                            if (timerzmjianli1 != null) {
                                timerzmjianli1.cancel();
                            }
                        } else {
                            Mydwon1 = listsucessDown1.get(0);
                            listsucessDown1.remove(0);
                            listsucessDown1.add(Mydwon1);
                            presenter.setLunxunJianLixiaoqu(context, 1, Mydwon1, sb1_zm);
                            if (timerzmjianli1 != null) {
                                timerzmjianli1.cancel();
                            }
                        }
                    } else {
                        if (timerzmjianli1 != null) {
                            timerzmjianli1.cancel();
                        }
                    }
                    break;
                case 55662:
                    if (!sb2_zm.equals("?????????")) {

                        if (Mydwon2.equals(listsucessDown2.get(0))) {
                            listsucessDown2.remove(0);
                            listsucessDown2.add(Mydwon2);
                            Mydwon2 = listsucessDown2.get(0);
                            presenter.setLunxunJianLixiaoqu(context, 2, Mydwon2, sb2_zm);
                            if (timerzmjianli2 != null) {
                                timerzmjianli2.cancel();
                            }
                        } else {
                            Mydwon2 = listsucessDown2.get(0);
                            listsucessDown2.remove(0);
                            listsucessDown2.add(Mydwon2);
                            presenter.setLunxunJianLixiaoqu(context, 2, Mydwon2, sb2_zm);
                            if (timerzmjianli2 != null) {
                                timerzmjianli2.cancel();
                            }

                        }

                    } else {
                        if (timerzmjianli2 != null) {
                            timerzmjianli2.cancel();
                        }
                    }
                    break;
                case 100136:
                    if (MainType == 2) {
                        Set1StatusBarGK("????????????????????????");
//                        presenter.spbuils(context, 1, 1, tf1, tf2);//??????????????????
                        presenter.buildSD(mydown1GK, 1, sb1, context);//????????????
                    }
                    if (MainType == 0) {
                        Set1StatusBar("????????????????????????");
                        if (sb1.equals("?????????")) {
                            return;
                        }
                        if (slideButton1Flag) {
                            sb1FirstFlag = true;
                            presenter.buildSD(sp1MAX1value, 1, sb1, context);

                        } else {
                            sb1FirstFlag = true;
                            if (sb1.equals("?????????")) {
                                break;
                            }
                            presenter.buildSD(spinnerS1, 1, sb1, context);
                        }
                        break;
                    }
                    if (MainType == 1) {
                        Set1StatusBarZM("????????????????????????");
                        if (timerzmjianli1 == null) {
                            timerzmjianli1 = new Timer();
                            timerzmjianli1.schedule(new TimerTask() {

                                //run?????????????????????????????????????????????
                                @Override
                                public void run() {

                                    Message message = new Message();
                                    handler.sendMessage(message);
                                    message.what = 55661;
                                    Log.d(TAG, "handlerrun: " + 1);
                                    Log.d(TAG, "handlerrun: " + 1);
                                }
                            }, 0, 6000);//3??????????????????????????????
                        }

                        if (Mydwon1.equals(listsucessDown1.get(0))) {
                            listsucessDown1.remove(0);
                            listsucessDown1.add(Mydwon1);
                            Mydwon1 = listsucessDown1.get(0);
                            presenter.setLunxunJianLixiaoqu(context, 1, Mydwon1, sb1_zm);
                        } else {
                            Mydwon1 = listsucessDown1.get(0);
                            listsucessDown1.remove(0);
                            listsucessDown1.add(Mydwon1);
                            presenter.setLunxunJianLixiaoqu(context, 1, Mydwon1, sb1_zm);
                        }

                    }
                    break;
                case 100137:

                    Set1StatusBarGK("????????????????????????");
                    break;
                case 200137:

                    Set2StatusBarGK("????????????????????????");
                    break;
                case 100138: //??????????????????
//                    ToastUtils.showToast("??????????????????");
                    Log.d(TAG, "handleMessagea: " + "??????????????????");
                    if (MainType == 0) {
                        Set1StatusBar("?????????...");

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("??????????????????", "run:788888888888");
//                                saopinSend1(saopinBeanList, tf1, SAOPIN);
                                TIMERRESTARTFLAG1 = false;
                            }
                        }, 20000);


                        break;
                    }
                    if (MainType == 1) {
                        Set1StatusBar("?????????...");

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("??????????????????", "run:788888888888");
//                                saopinSend1(saopinBeanList, tf1, SAOPIN);
                                ZMRRESTARTFLAG1 = false;
                            }
                        }, 20000);


                        break;
                    }
                    if (MainType == 2) {
                        Set1StatusBarGK("?????????...");

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("??????????????????", "run:788888888888");
//                                saopinSend1(saopinBeanList, tf1, SAOPIN);
                                GKRRESTARTFLAG1 = false;
                            }
                        }, 30000);


                        break;
                    }
                case 100140:
//                    ToastUtils.showToast("??????????????????");
                    if (MainType == 0) {
                        Set1StatusBar("??????????????????");
                        MainUtils.Restart();
                    }
                    if (MainType == 1) {
                        Set1StatusBarZM("??????????????????");
                        MainUtils.Restart();
                    }
                    if (MainType == 2) {
                        Set1StatusBarGK("??????????????????");
                        MainUtils.Restart();
                    }
                case 100147://  ???????????????

                    presenter.setIMSInengliangzhi(context, 1, tf1, dfBaoshu, sb1_jl, laba1Flag, msg, textToSpeech);

                    break;
                case 100148: //????????????imsi ??????
                    presenter.setIMSIshow(msg, tv_imsi1_dw);
                    break;
                case 100152:
                    if (MainType == 2) {
                        Log.d("????????????100152", "handleMessage: " + SPBEANLIST1Fragment.toString());
                        if (saopinGKFalg1) {

                            break;
                        }
                        if (SPBEANLIST1Fragment.size() > 0) {
                            if (SPBEANLIST1Fragment.size() > 1) {
                                mydown1GK = SPBEANLIST1Fragment.get(0).getDown();
                                saopinGKFalg1 = true;
                                mydown11GK = SPBEANLIST1Fragment.get(1).getDown();

                                presenter.chongdingxiang1(mydown11GK);
                                presenter.chongdingxiangSET1();

                                Log.d("tagnumge2ge", "handleMessage: " + mydown1GK + "---" + mydown11GK);
                            } else if (SPBEANLIST1Fragment.size() == 1) {
                                saopinGKFalg1 = true;
                                mydown1GK = SPBEANLIST1Fragment.get(0).getDown();
                                mydown11GK = SPBEANLIST1Fragment.get(0).getDown();
                                presenter.chongdingxiang1(mydown11GK);
                                presenter.chongdingxiangSET1();
                                Log.d("tagnumge1ge", "handleMessage: " + mydown1GK + "---" + mydown11GK);
                            }

                        } else {
                            Set1StatusBarGK("???????????????????????????");
                        }
                    }
                    if (MainType == 1) {
                        String dwon1s = msg.getData().getString("sp1MAX1value");//???????????????
                        Log.d("nzqh1dwon1szm", "handleMessage: " + dwon1s);
                        Log.d("????????????100152??????", "handleMessage: " + SPBEANLIST1Fragment.toString());
                        if (saopinFalg1) {

                            break;
                        }
                        if (SPBEANLIST1Fragment.size() > 0) {
                            listsucessDown1 = MyUtils.removeDuplicateSpBean(SPBEANLIST1Fragment);
                            Log.d("????????????1001522", "handleMessage: " + listsucessDown1.toString());
                            if (listsucessDown1.size() == 0) {

                                ToastUtils.showToast("??????1?????????????????????");
                                return;
                            }
                            if (listsucessDown1.size() == 1) {
                                LogUtils.a("2???????????? ??????1???????????????");
                                MainUtils.sbzmLocation(IP1, context);  //??????????????????aaaaaaaaaa                                return;
                            }
                            if (listsucessDown1.size() == 1) {
                                LogUtils.a("3???????????? ??????1???????????????");
                                startdatazm1 = aDateFormat.format(new Date());
                                return;
                            }
                            if (listsucessDown1.size() > 1) {
                                startdatazm1 = aDateFormat.format(new Date());
                                LogUtils.a("4???????????? ??????1???????????????");
                                if (timerSB1 == null) {
                                    int size = listsucessDown1.size();

                                    final long tme = size * DbUtils.getTime(context, 1);
                                    /** ?????????60????????????1??? */
                                    setShengyu1(tme);
                                    countDownTimer1.start();
                                    timerSB1 = new Timer();

                                    timerSB1.schedule(new TimerTask() {
                                        //run?????????????????????????????????????????????
                                        @Override
                                        public void run() {
                                            saopinFalg1 = true;
                                            Message message = new Message();
                                            handler.sendMessage(message);
                                            message.what = 9001;
                                            Log.d(TAG, "Ahandlerrunaaaa: " + 1);
                                        }

                                    }, 0, DbUtils.getTime(context, 1) * 1000);
//                                    }, 0, 20 * 1000);
                                } else {
                                    int size = listsucessDown1.size();
                                    final long tme = size * DbUtils.getTime(context, 1);
                                    /** ?????????60????????????1??? */
                                    setShengyu1(tme);
                                    countDownTimer1.start();
                                    timerSB1 = new Timer();
                                    timerSB1.schedule(new TimerTask() {
                                        //run?????????????????????????????????????????????
                                        @Override
                                        public void run() {
                                            saopinFalg1 = true;
                                            Message message = new Message();
                                            handler.sendMessage(message);
                                            message.what = 9001;
                                            Log.d(TAG, "handlerrunaaaa: " + 1);
                                        }

                                    }, 0, DbUtils.getTime(context, 1) * 1000);
//                                    }, 0, 20 * 1000);
                                }
                                return;

                            }
                        } else {
                            Set1StatusBar("???????????????????????????");
                        }
                        break;
                    }
                    if (MainType == 0) {
                        String dwon1s = msg.getData().getString("sp1MAX1value");//???????????????
                        sp1MAX2value = msg.getData().getString("sp1MAX2value");//???????????????
                        Log.d("nzqh1dwon1s", "handleMessage: " + dwon1s);
                        if (!TextUtils.isEmpty(dwon1s)) {
                            if (!TextUtils.isEmpty(sp1MAX1value)) {
                                if (!sp1MAX1value.equals(dwon1s)) {
                                    Log.d("tagggg", "handleMessage: " + 1);
//                                    sp1MAX2value = dwon1s;
                                    break;
                                } else {
                                    //????????????
                                    Log.d("tagggg", "handleMessage: " + 2);
                                    break;
                                }

                            } else {
                                if (saopinshow1 == false) {
                                    sp1MAX1value = dwon1s;
                                    if (sb1.equals("???????????????") || sb1.equals("?????????")) {
                                        Log.d("???????????????????????????", "handleMessage: " + sp1MAX1value);
                                    } else {
                                        Log.d("????????????????????????", "handleMessage: " + sp1MAX1value);
                                        if (sp1MAX1value.equals(sp2MAX1value) || sb2.equals("?????????")) {
                                            if (!TextUtils.isEmpty(sp1MAX2value)) {

                                                presenter.saopinjianlixiaoqu(context, 1, tf1, sp1MAX2value);
                                                if (timerdw1 != null) {
                                                    timerdw1.cancel();
                                                }
//                                                timerdw1 = new Timer();
//                                                timerdw1.schedule(new TimerTask() {
//                                                    //run?????????????????????????????????????????????
//                                                    @SuppressLint("HandlerLeak")
//                                                    @Override
//                                                    public void run() {
//
//                                                        Message message = new Message();
//                                                        handler.sendMessage(message);
//                                                        message.what = 7001;
//                                                        Log.d(TAG, "Ahandlerrunaaaa1: " + 7001);
//                                                    }
//
//                                                }, 0, 6 * 1000);
                                                Log.e("aaaaq", "4");
                                            } else {
                                                Log.e("aaaaq", "3");
//                                                ToastUtils.showToast("?????????????????????2?????? ????????????");
                                            }
                                            Log.e("aaaaq", "2");
                                        } else {
                                            Log.e("aaaaq", "1");

                                            presenter.saopinjianlixiaoqu(context, 1, tf1, sp1MAX1value);
                                            if (timerdw1 != null) {
                                                timerdw1.cancel();
                                            }
//                                            timerdw1 = new Timer();
//                                            timerdw1.schedule(new TimerTask() {
//                                                //run?????????????????????????????????????????????
//                                                @Override
//                                                public void run() {
//
//                                                    Message message = new Message();
//                                                    handler.sendMessage(message);
//                                                    message.what = 7001;
//                                                    Log.d(TAG, "Ahandlerrunaaaa2: " + 7001);
//                                                }
//
//                                            }, 0, 6 * 1000);
                                        }

                                    }
                                } else {
                                    if (SPBEANLIST1.size() > 0) {

//                                Set1StatusBar("????????????????????????");
                                        Intent intent = new Intent(context, SaoPinActivity.class);
                                        intent.putExtra("type", "1");
                                        startActivity(intent);
                                    } else {
                                        Set1StatusBar("???????????????????????????");
                                    }
                                }

                            }
                        }

                    }

                    break;

                /////  ??????2


                case 200110:


                    if (MainType == 1) {//????????????
                        tf2 = msg.getData().getString("tf2");
                        tv2_td_zm.setText("????????????:" + tf2);
                    }
                    if (MainType == 0) {//????????????
                        tf2 = msg.getData().getString("tf2");
                        tv2_td_dw.setText("????????????:" + tf2);
                    }
                    if (MainType == 2) {//????????????
                        tf2 = msg.getData().getString("tf2");
                        tv2_tf_gk.setText("????????????:" + tf2);
                    }
                    break;
                case 200120: //??????2?????????????????????
                    if (MainType == 2) {
                        DwUpdate.type2(context, MainType, msg, tv2_type_gk, tv2_tf_gk, tf2);
                        String zt2 = msg.getData().getString("zt2");
                        if (!TextUtils.isEmpty(zt2)) {
                            if (zt2.equals("2")) {
                                sb2_gk = "??????";
                                tv2_type_gk.setText("????????????:??????");
                                Set2StatusBarGK("??????");
                                tv_pin2gk.setText("??????2??????: ");

                                if (GKRRESTARTFLAG2 == false) {
                                    GKRRESTARTFLAG2 = true;
                                    if (GuankongType == 1) {//???????????????TDD
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

                                                    List<AddPararBeanWhite> sendwhitelist = new ArrayList<>();
                                                    for (int j = 0; j < listImsiListTrue.size(); j++) {
                                                        if (listImsiListTrue.get(j).getYy().equals("??????")) {
                                                            sendwhitelist.add(listImsiListTrue.get(j));
                                                        }
                                                    }
                                                    sendwhiteListRun(2, sendwhitelist, tf1, tf2, context, listImsiListTrue);//???????????????  2???
                                                } else {
//                                                    ToastUtils.showToast("?????????????????????IMSI");
                                                    List<AddPararBeanWhite> sendwhitelist = new ArrayList<>();
                                                    sendwhiteListRun(2, sendwhitelist, tf1, tf2, context, listImsiListTrue);//???????????????  2???
                                                }
                                            }
                                            Log.d("setGKtartaddPararBeans", "?????????init: " + listImsiListTrue.toString());
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                    } else {//??????FDD
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

                                                    List<AddPararBeanWhite> sendwhitelist = new ArrayList<>();
                                                    for (int j = 0; j < listImsiListTrue.size(); j++) {
                                                        if (listImsiListTrue.get(j).getYy().equals("??????")) {
                                                            sendwhitelist.add(listImsiListTrue.get(j));
                                                        }
                                                    }
                                                    sendwhiteListRun(2, sendwhitelist, tf1, tf2, context, listImsiListTrue);//???????????????  2???
                                                } else {
//                                                    ToastUtils.showToast("?????????????????????IMSI");
                                                    List<AddPararBeanWhite> sendwhitelist = new ArrayList<>();
                                                    sendwhiteListRun(2, sendwhitelist, tf1, tf2, context, listImsiListTrue);//???????????????  2???

                                                }
                                            }
                                            Log.d("setGKtartaddPararBeans", "?????????init: " + listImsiListTrue.toString());
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                            if (zt2.equals("3")) {
//                            tv1_type.setText("????????????: " + "?????????????????????");//????????????
                                sb2_gk = "?????????????????????";
                                Set2StatusBarGK("?????????????????????");
//                                tv2_type_zm.setText("????????????:?????????????????????");
                                break;
                            }
                            if (zt2.equals("4")) {
                                sb2_gk = "???????????????";
                                Set2StatusBarGK("???????????????");
                                break;
                            }
                            if (zt2.equals("5")) {
                                sb2_gk = "?????????";
                                Set2StatusBarGK("?????????");
                                tv2_type_gk.setText("????????????:?????????");
                                tv_pin2gk.setText("??????2??????:" + mydown2GK);
                            }
                        }
                    }
                    if (MainType == 1) {
                        DwUpdate.type2(context, MainType, msg, tv2_type_zm, tv2_td_zm, tf2);
                        String zt2 = msg.getData().getString("zt2");
                        if (!TextUtils.isEmpty(zt2)) {
                            if (zt2.equals("2")) {
                                sb2_zm = "??????";
                                tv2_type_zm.setText("????????????:??????");
                                Set2StatusBarZM("??????");
                                tv_pin2.setText("??????2??????: ");

                                if (ZMRRESTARTFLAG2 == false) {
                                    Log.d("AGKRRESTARTFLAG1", "handleMessage:1 ");
                                    ZMRRESTARTFLAG2 = true;

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

                                    if (listDown2.size() > 10) {
                                        ToastUtils.showToast("??????2????????????????????????10???");
                                        return;
                                    }

                                    if (listDown2.size() == 0) {
                                        ToastUtils.showToast("??????2????????????0???");
                                        return;
                                    }

                                    Log.d("aaasdqwe", "setzmlistStart1: ");
                                    zmlunxunlist2(listDown1, listDown2);


                                }
                            }
                            if (zt2.equals("3")) {
//                            tv1_type.setText("????????????: " + "?????????????????????");//????????????
                                sb2_zm = "?????????????????????";
                                Set2StatusBarZM("?????????????????????");
//                                tv2_type_zm.setText("????????????:?????????????????????");
                                break;
                            }
                            if (zt2.equals("4")) {
                                sb2_zm = "???????????????";
                                Set2StatusBarZM("???????????????");
                                break;
                            }
                            if (zt2.equals("5")) {
                                sb2_zm = "?????????";
                                Set2StatusBarZM("?????????");
                                tv2_type_zm.setText("????????????:?????????");
                                tv_pin2.setText("??????2??????: " + Mydwon2);
                            }
                        }
                    }
                    if (MainType == 0) {  //????????????
                        DwUpdate.type2(context, MainType, msg, tv2_type_dw, tv2_td_dw, tf2);
                        String zt2 = msg.getData().getString("zt2");
                        if (!TextUtils.isEmpty(zt2)) {
                            if (zt2.equals("2")) {
                                Set2StatusBar("??????");
                                tv_imsi2_dw.setText("");
                                sb2_jl.setText("");
                                tv2_type_dw.setText("????????????:??????");
                                if (slideButton2Flag) {
                                    mysp2.setVisibility(View.GONE);
                                } else {
                                    mysp2.setVisibility(View.VISIBLE);
                                }

                                tv_r2dw.setText("??????:");
                                mysp2.setEnabled(true);
                                if (slideButton2Flag) {
                                    if (phoneFlag2 == true) {
                                        if (TIMERRESTARTFLAG2 == false) {
                                            phoneFlag2 = false;
                                            presenter.setStart(2, slideButton1Flag, MainType, sb1, sb2, spinnerS1, spinnerS2, context, tf1, tf2, 0, false);
                                            TIMERRESTARTFLAG2 = true;
                                            Log.e("qweqaeee", "handleMessage: " + "1");
                                        }
                                    } else {
                                        if (TIMERRESTARTFLAG2 == false) {
                                            presenter.sendsaopin(context, 2, SAOPIN2);
                                            TIMERRESTARTFLAG2 = true;
                                            Log.e("qweqaeee", "handleMessage: " + "2");
                                        }
                                    }

                                } else {//????????????????????????
                                    if (TIMERRESTARTFLAG2 == false) {

                                        TIMERRESTARTFLAG2 = true;
                                        presenter.setStart(2, slideButton1Flag, MainType, sb1, sb2, spinnerS1, spinnerS2, context, tf1, tf2, 0, false);
                                        Log.e("qweqaeee", "handleMessage: " + "3");
                                    }


                                }

                                sb2 = "??????";
                                if (SB2ZY == false) {
                                    Log.d(TAG, "handleMessage: .?????????????????????");

                                    DBManagerZY dbManagerZY = null;
                                    try {
                                        dbManagerZY = new DBManagerZY(context);
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
                                        SB2ZY = true;
                                    }
                                    if (tf2.equals("FDD")) {
                                        int data = 0;
                                        try {
                                            data = dbManagerZY.foriddata(2, 2, 3);
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                        setzy(data, 2);
                                        SB2ZY = true;
                                    }
                                }
                            }
                            if (zt2.equals("3")) {
//                            tv1_type.setText("????????????: " + "?????????????????????");//????????????
                                sb2 = "?????????????????????";
                                Set2StatusBar("?????????????????????");
//                                tv2_type_dw.setText("????????????:?????????????????????");
                                break;
                            }
                            if (zt2.equals("4")) {
                                sb2 = "???????????????";
                                Set2StatusBar("???????????????");
                                break;
                            }
                            if (zt2.equals("5")) {
                                sb2 = "?????????";
                                Set2StatusBar("?????????");
                                tv2_type_dw.setText("????????????:" + "?????????");//????????????
                                mysp2.setEnabled(false);
                                mysp2.setVisibility(View.GONE);
                                tv_r2dw.setText("??????:" + DOWNPIN2);
                                if (SB2ZY == false) {
                                    Log.d(TAG, "handleMessage: .?????????????????????");

                                    DBManagerZY dbManagerZY = null;
                                    try {
                                        dbManagerZY = new DBManagerZY(context);
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
                                        SB2ZY = true;
                                    }
                                    if (tf2.equals("FDD")) {
                                        int data = 0;
                                        try {
                                            data = dbManagerZY.foriddata(2, 2, 3);
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                        setzy(data, 2);
                                        SB2ZY = true;
                                    }
                                }
                            }
                        }
                    }

                    break;
                case 2001200:
                    if (MainType == 2) {
                        String test = msg.getData().getString("test");
                        if (!TextUtils.isEmpty(test)) {
                            if (test.equals("1")) {
                                Set2StatusBarGK("??????????????????");
                                break;
                            }
                            if (test.equals("2")) {
                                Set2StatusBarZM("??????????????????");
//                                listsucessDown2.remove(0);
//                                listsucessDown2.add(Mydwon2);
//                                Mydwon2 = listsucessDown2.get(0);
//                                presenter.setLunxunJianLixiaoqu(context, 2, Mydwon2, sb2_zm);
                                break;
                            }
                            if (test.equals("3")) {
                                Set2StatusBarGK("GPS????????????");
                                break;
                            }
                            if (test.equals("4")) {
                                Set2StatusBarGK("GPS????????????");
                                break;
                            }
                            if (test.equals("5")) {
                                Set2StatusBarGK("????????????");


                                break;
                            }
                            if (test.equals("6")) {
                                Set2StatusBarGK("????????????");
                                break;
                            }
                            if (test.equals("7")) {
                                Set2StatusBarGK("??????????????????");
                                break;
                            }
                            if (test.equals("8")) {
                                Set2StatusBarGK("??????????????????");
                                break;
                            }
                            if (test.equals("9")) {
                                Set2StatusBarGK("???????????????");

                                break;
                            }
                            if (test.equals("10")) {
                                Set2StatusBarGK("???????????????");
                                break;
                            }
                            if (test.equals("11")) {
                                Set2StatusBarGK("GPS?????????");
                                break;
                            }
                            if (test.equals("12")) {
                                Set2StatusBarGK("?????????");
                                tv2_type_gk.setText("????????????:?????????");
                                break;
                            }
                            if (test.equals("13")) {
                                Set2StatusBarGK("???????????????");

                                break;
                            }
                            if (test.equals("14")) {
                                Set2StatusBarGK("??????????????????");
                                break;
                            }
                        }
                    }
                    if (MainType == 1) {
                        String test = msg.getData().getString("test");
                        if (!TextUtils.isEmpty(test)) {
                            if (test.equals("1")) {
                                Set2StatusBarZM("??????????????????");
                                break;
                            }
                            if (test.equals("2")) {
                                Set2StatusBarZM("??????????????????");
                                listsucessDown2.remove(0);
                                listsucessDown2.add(Mydwon2);
                                Mydwon2 = listsucessDown2.get(0);
                                presenter.setLunxunJianLixiaoqu(context, 2, Mydwon2, sb2_zm);
                                if (listsucessDown2.size() == 1) {//?????????????????????1

                                } else if (listsucessDown2.size() > 0) {
                                    int size = listsucessDown2.size();
                                    long time = size * 60;
//                                    time=listsucessDown1.size()-1*60;
                                    if (MymillisUntilFinished2 / 1000 > 60) {
                                        Log.d("adsadq", "handleMessage: " + MymillisUntilFinished2 / 1000);
                                        long iaa = DbUtils.getTime(context, 1) * 1000;

                                        time = MymillisUntilFinished2 - iaa;
                                        Log.e("shijianaa", "handleMessage: " + MymillisUntilFinished2 + "\n" + iaa + "\n" + time);
                                        if (countDownTimer2 != null) {
                                            countDownTimer2.cancel();
                                            time = time / 1000;
                                            setShengyu2(time);
                                            countDownTimer2.start();
                                        }
                                    } else if (MymillisUntilFinished2 / 1000 < 60) {

                                        Log.d("uopmo", "handleMessage1: " + MymillisUntilFinished2 / 1000);
                                        if (countDownTimer2 != null) {
                                            countDownTimer2.cancel();
                                            setShengyu2(1);
                                            countDownTimer2.start();
                                            Log.d("uopmo", "handleMessage2: ");
                                        }
                                    }


                                }

                                break;
                            }
                            if (test.equals("3")) {
                                Set2StatusBarZM("GPS????????????");
                                break;
                            }
                            if (test.equals("4")) {
                                Set2StatusBarZM("GPS????????????");
                                break;
                            }
                            if (test.equals("5")) {
                                Set2StatusBarZM("????????????");


                                break;
                            }
                            if (test.equals("6")) {
                                Set2StatusBarZM("????????????");
                                break;
                            }
                            if (test.equals("7")) {
                                Set2StatusBarZM("??????????????????");
                                break;
                            }
                            if (test.equals("8")) {
                                Set2StatusBarZM("??????????????????");
                                break;
                            }
                            if (test.equals("9")) {
                                Set2StatusBarZM("???????????????");

                                break;
                            }
                            if (test.equals("10")) {
                                Set2StatusBarZM("???????????????");
                                break;
                            }
                            if (test.equals("11")) {
                                Set2StatusBarZM("GPS?????????");
                                break;
                            }
                            if (test.equals("12")) {
                                Set2StatusBarZM("?????????");
                                tv2_type_zm.setText("????????????:?????????");
                                break;
                            }
                            if (test.equals("13")) {
                                Set2StatusBarZM("???????????????");

                                break;
                            }
                            if (test.equals("14")) {
                                Set2StatusBarZM("??????????????????");
                                break;
                            }
                        }
                    }
                    if (MainType == 0) {
                        String test = msg.getData().getString("test");
                        if (!TextUtils.isEmpty(test)) {
                            if (test.equals("1")) {
                                Set2StatusBar("??????????????????");
                                break;
                            }
                            if (test.equals("2")) {
                                Set2StatusBar("??????????????????");
                                break;
                            }
                            if (test.equals("3")) {
                                Set2StatusBar("GPS????????????");
                                break;
                            }
                            if (test.equals("4")) {
                                Set2StatusBar("GPS????????????");
                                break;
                            }
                            if (test.equals("5")) {
                                Set2StatusBar("????????????");
                                break;
                            }
                            if (test.equals("6")) {
                                Set2StatusBar("????????????");
                                break;
                            }
                            if (test.equals("7")) {
                                Set2StatusBar("??????????????????");
                                break;
                            }
                            if (test.equals("8")) {
                                Set2StatusBar("??????????????????");
                                break;
                            }
                            if (test.equals("9")) {
                                Set2StatusBar("???????????????");

                                break;
                            }
                            if (test.equals("10")) {
                                Set2StatusBar("???????????????");
                                break;
                            }
                            if (test.equals("11")) {
                                Set2StatusBar("GPS?????????");
                                break;
                            }
                            if (test.equals("12")) {
                                Set2StatusBar("?????????");
                                tv2_type_dw.setText("????????????:?????????");
                                break;
                            }
                            if (test.equals("13")) {
                                Set2StatusBar("???????????????");

                                break;
                            }
                            if (test.equals("14")) {
                                Set2StatusBar("??????????????????");
                                break;
                            }
                        }
                    }

                case 200131:
//                    Set1StatusBar("??????");
                    break;
                case 200136:
                    if (MainType == 2) {
                        Set2StatusBarGK("????????????????????????");
                        presenter.buildSD(mydown2GK, 2, sb2, context);//????????????
                    }
                    if (MainType == 0) {
                        Set2StatusBar("????????????????????????");
//                        Set1StatusBar("???????????????????????????????????????");
                        if (sb2.equals("?????????")) {
                            return;
                        }
                        if (slideButton2Flag) {
                            sb2FirstFlag = true;
                            presenter.buildSD(sp2MAX1value, 2, sb2, context);

                        } else {
                            sb2FirstFlag = true;
                            Log.d("spinnerS2", "handleMessage: " + spinnerS2);
                            if (sb2.equals("?????????")) {
                                break;
                            }
                            presenter.buildSD(spinnerS2, 2, sb2, context);
                        }

                    }
                    if (MainType == 1) {
                        Set2StatusBarZM("????????????????????????");
                        if (timerzmjianli2 == null) {
                            timerzmjianli2 = new Timer();
                            timerzmjianli2.schedule(new TimerTask() {

                                //run?????????????????????????????????????????????
                                @Override
                                public void run() {

                                    Message message = new Message();
                                    handler.sendMessage(message);
                                    message.what = 55662;
                                    Log.d(TAG, "handlerrun: " + 1);
                                    Log.d(TAG, "handlerrun: " + 1);
                                }
                            }, 0, 6000);//3??????????????????????????????
                        }
                        if (Mydwon2.equals(listsucessDown2.get(0))) {
                            listsucessDown2.remove(0);
                            listsucessDown2.add(Mydwon2);
                            Mydwon2 = listsucessDown2.get(0);
                            presenter.setLunxunJianLixiaoqu(context, 2, Mydwon2, sb2_zm);

                        } else {
                            Mydwon2 = listsucessDown2.get(0);
                            listsucessDown2.remove(0);
                            listsucessDown2.add(Mydwon2);
                            presenter.setLunxunJianLixiaoqu(context, 2, Mydwon2, sb2_zm);

                        }

                    }
                    break;
                case 200138: //??????????????????
//                    ToastUtils.showToast("??????????????????");
                    Log.d(TAG, "handleMessagea: " + "??????????????????");
                    if (MainType == 0) {
                        Set2StatusBar("?????????...");

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("??????????????????", "run:788888888888");
//                                saopinSend1(saopinBeanList, tf1, SAOPIN);
                                TIMERRESTARTFLAG2 = false;
                            }
                        }, 20000);


                        break;
                    }
                    if (MainType == 1) {
                        Set2StatusBar("?????????...");

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("??????????????????", "run:788888888888");
//                                saopinSend1(saopinBeanList, tf1, SAOPIN);
                                ZMRRESTARTFLAG2 = false;
                            }
                        }, 20000);


                        break;
                    }
                    if (MainType == 2) {
                        Set2StatusBarGK("?????????...");

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("??????????????????", "run:788888888888");
//                                saopinSend1(saopinBeanList, tf1, SAOPIN);
                                GKRRESTARTFLAG2 = false;
                            }
                        }, 30000);


                        break;
                    }

                    break;
                case 200140:
//                    ToastUtils.showToast("??????????????????");
                    if (MainType == 0) {
                        Set2StatusBar("??????????????????");
                        MainUtils.Restart2();
                    }
                    if (MainType == 1) {
                        Set2StatusBarZM("??????????????????");

                        MainUtils.Restart2();
                    }
                    if (MainType == 2) {
                        Set2StatusBarGK("??????????????????");
                        MainUtils.Restart2();
                    }
                    break;
                case 200147://  ???????????????

                    presenter.setIMSInengliangzhi(context, 2, tf2, dfBaoshu, sb2_jl, laba2Flag, msg, textToSpeech);

                    break;
                case 200148: //????????????imsi ??????
                    presenter.setIMSIshow2(msg, tv_imsi2_dw);
                    break;
                case 200152:
                    Log.d("200152????????????2", "handleMessage: " + SPBEANLIST2Fragment.toString());

                    if (MainType == 2) {
                        Log.d("????????????200152", "handleMessage: " + SPBEANLIST2Fragment.toString());
                        if (saopinGKFalg2) {

                            break;
                        }
                        if (SPBEANLIST2Fragment.size() > 0) {
                            if (SPBEANLIST2Fragment.size() > 1) {
                                saopinGKFalg2 = true;
                                mydown2GK = SPBEANLIST2Fragment.get(0).getDown();

                                mydown22GK = SPBEANLIST2Fragment.get(1).getDown();

                                presenter.chongdingxiang2(mydown22GK);
                                presenter.chongdingxiangSET2();

                                Log.d("Atagnumge2ge", "handleMessage: " + mydown2GK + "---" + mydown22GK);
                            } else if (SPBEANLIST2Fragment.size() == 1) {
                                saopinGKFalg2 = true;
                                mydown2GK = SPBEANLIST2Fragment.get(0).getDown();
                                mydown22GK = SPBEANLIST2Fragment.get(0).getDown();
                                presenter.chongdingxiang2(mydown22GK);
                                presenter.chongdingxiangSET2();
                                Log.d("Atagnumge1ge", "handleMessage: " + mydown2GK + "---" + mydown22GK);
                            }
                        } else {
                            Set1StatusBarGK("???????????????????????????");
                        }
                    }
                    if (MainType == 1) {
//                        String dwon1s = msg.getData().getString("sp1MAX1value");//???????????????
//                        Log.d("nzqh1dwon1szm", "handleMessage: " + dwon1s);
                        Log.d("????????????2", "handleMessage: " + SPBEANLIST2Fragment.toString());
                        if (saopinFalg2) {

                            break;
                        }
                        if (SPBEANLIST2Fragment.size() > 0) {
                            listsucessDown2 = MyUtils.removeDuplicateSpBean(SPBEANLIST2Fragment);
                            Log.d("????????????22", "handleMessage: " + listsucessDown2.toString());
                            if (listsucessDown2.size() == 0) {

                                ToastUtils.showToast("??????2?????????????????????");
                                return;
                            }
                            if (listsucessDown2.size() == 1) {
                                LogUtils.a("2???????????? ??????1???????????????");
                                MainUtils.sbzmLocation(IP2, context);  //??????????????????aaaaaaaaa

                                return;
                            }
                            if (listsucessDown2.size() == 1) {
                                LogUtils.a("3???????????? ??????1???????????????");
                                startdatazm2 = aDateFormat.format(new Date());
                                return;
                            }
                            if (listsucessDown2.size() > 1) {
                                startdatazm2 = aDateFormat.format(new Date());
                                LogUtils.a("4???????????? ??????1???????????????");
                                if (timerSB2 == null) {
                                    int size = listsucessDown2.size();

                                    final long tme = size * DbUtils.getTime(context, 2);
                                    /** ?????????60????????????1??? */
                                    setShengyu2(tme);
                                    countDownTimer2.start();

                                    timerSB2 = new Timer();
                                    timerSB2.schedule(new TimerTask() {
                                        //run?????????????????????????????????????????????
                                        @Override
                                        public void run() {
                                            saopinFalg2 = true;
                                            Message message = new Message();
                                            handler.sendMessage(message);
                                            message.what = 9002;
                                            Log.d(TAG, "9002handlerrunaaaa: " + 1);
                                        }

                                    }, 0, DbUtils.getTime(context, 2) * 1000);
//                                    }, 0, 20 * 1000);
                                } else {
                                    int size = listsucessDown2.size();

                                    final long tme = size * DbUtils.getTime(context, 2);
                                    /** ?????????60????????????1??? */
                                    setShengyu2(tme);
                                    countDownTimer2.start();
                                    timerSB2 = new Timer();
                                    timerSB2.schedule(new TimerTask() {
                                        //run?????????????????????????????????????????????
                                        @Override
                                        public void run() {
                                            saopinFalg2 = true;
                                            Message message = new Message();
                                            handler.sendMessage(message);
                                            message.what = 9002;
                                            Log.d(TAG, "handlerrunaaaa: " + 1);
                                        }

                                    }, 0, DbUtils.getTime(context, 2) * 1000);
//                                    }, 0, 20 * 1000);
                                }
                                return;

                            }
                        } else {
                            Set2StatusBar("???????????????????????????");
                        }
                        break;
                    }
                    if (MainType == 0) {
                        String dwon1s = msg.getData().getString("sp1MAX1value54");//???????????????
                        sp2MAX2value = msg.getData().getString("sp1MAX2value54");//???????????????
                        Log.d("nzqh1dwon12", "handleMessage: " + dwon1s);
                        if (!TextUtils.isEmpty(dwon1s)) {
                            if (!TextUtils.isEmpty(sp2MAX1value)) {
                                if (!sp2MAX1value.equals(dwon1s)) {
                                    Log.d("tagggg", "handleMessage: " + 1);
//                                    sp2MAX2value = dwon1s;
                                    break;
                                } else {
                                    //????????????
                                    Log.d("tagggg", "handleMessage: " + 2);
                                    break;
                                }

                            } else {
                                if (saopinshow2 == false) {
                                    sp2MAX1value = dwon1s;
                                    if (sb2.equals("???????????????") || sb2.equals("?????????")) {
                                        Log.d("???????????????????????????", "handleMessage: " + sp2MAX1value);
                                    } else {
                                        Log.d("????????????????????????", "handleMessage: " + sp2MAX1value);
                                        if (sp1MAX1value.equals(sp2MAX1value) || sb2.equals("?????????")) {
                                            if (!TextUtils.isEmpty(sp2MAX2value)) {
                                                presenter.saopinjianlixiaoqu(context, 2, tf2, sp2MAX2value);
                                                if (timerdw2 != null) {
                                                    timerdw2.cancel();
                                                }
//                                                timerdw2 = new Timer();
//                                                timerdw2.schedule(new TimerTask() {
//                                                    //run?????????????????????????????????????????????
//                                                    @Override
//                                                    public void run() {
//
//                                                        Message message = new Message();
//                                                        handler.sendMessage(message);
//                                                        message.what = 7002;
//                                                        Log.d(TAG, "Ahandlerrunaaaa1: " + 7002);
//                                                    }
//
//                                                }, 0, 6 * 1000);
                                            } else {
//                                                ToastUtils.showToast("?????????????????????2?????? ????????????");
                                            }
                                        } else {
                                            Log.d("nzqh1dwon12????????????", "handleMessage: " + dwon1s);
                                            presenter.saopinjianlixiaoqu(context, 2, tf2, sp2MAX1value);
                                            if (timerdw2 != null) {
                                                timerdw2.cancel();
                                            }
                                            timerdw2 = new Timer();
                                            timerdw2.schedule(new TimerTask() {
                                                //run?????????????????????????????????????????????
                                                @Override
                                                public void run() {
                                                    Message message = new Message();
                                                    handler.sendMessage(message);
                                                    message.what = 7002;
                                                    Log.d(TAG, "Ahandlerrunaaaa1: " + 7002);
                                                }

                                            }, 0, 6 * 1000);
                                        }

                                    }

                                } else {
                                    if (SPBEANLIST2.size() > 0) {

//                                Set1StatusBar("????????????????????????");
                                        Intent intent = new Intent(context, SaoPinActivity.class);
                                        intent.putExtra("type", "2");
                                        startActivity(intent);
                                    } else {
                                        Set1StatusBar("???????????????????????????");
                                    }
                                }

                            }
                        }

                    }

                    break;
            }

        }
    };

    private void setShengyu1(final long tme) {
        countDownTimer1 = new CountDownTimer(tme * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub
                MymillisUntilFinished = millisUntilFinished;
                tv_datashengyu1.setText("??????????????????:" + millisUntilFinished / 1000 + "???");
                tv_xunhuanNum1_zm.setText("??????????????????:" + lunxunNum1);
            }

            @Override
            public void onFinish() {
                tv_datashengyu1.setText("??????????????????:");
                lunxunNum1 += 1;
                tv_xunhuanNum1_zm.setText("??????????????????:" + lunxunNum1);
                Message message = new Message();
                Bundle bundle = new Bundle();
                message.setData(bundle);
                handler.sendMessage(message);
                message.what = 100920;

            }
        };
    }

    private void setShengyu2(final long tme) {
        countDownTimer2 = new CountDownTimer(tme * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                MymillisUntilFinished2 = millisUntilFinished;
                // TODO Auto-generated method stub
                tv_datashengyu2.setText("??????????????????:" + millisUntilFinished / 1000 + "???");
                tv_xunhuanNum2_zm.setText("??????????????????:" + lunxunNum2);
            }

            @Override
            public void onFinish() {
                tv_datashengyu2.setText("??????????????????:");
                lunxunNum2 += 1;
                tv_xunhuanNum2_zm.setText("??????????????????:" + lunxunNum2);
                Message message = new Message();
                Bundle bundle = new Bundle();
                message.setData(bundle);
                handler.sendMessage(message);
                message.what = 200920;
            }
        };
    }

    //???????????????1?????????
    public String upStr = "";

    //??????????????? ????????????
    private void Set1StatusBar(String str) {
        if (!upStr.equals(str)) {
            stringBuffer1dw.append(str + "" + "\n");
            textViewsdw.setText(stringBuffer1dw);
            upStr = str;
            scrollViewdw.fullScroll(ScrollView.FOCUS_DOWN);//???????????????
        }
    }

    //???????????????1?????????
    public String upStrzm1 = "";

    //??????????????? ????????????
    private void Set1StatusBarZM(String str) {
        if (!upStrzm1.equals(str)) {
            stringBuffer1zm.append(str + "" + "\n");
            textViews1zm.setText(stringBuffer1zm);
            upStrzm1 = str;
            scrollView1zm.fullScroll(ScrollView.FOCUS_DOWN);//???????????????
        }
    }

    //???????????????1?????????
    public String upStrgk1 = "";

    //??????????????? ????????????
    private void Set1StatusBarGK(String str) {
        if (!upStrgk1.equals(str)) {
            stringBuffer1gk.append(str + "" + "\n");
            textViews_gk1.setText(stringBuffer1gk);
            upStrgk1 = str;
            scrollView1_gk.fullScroll(ScrollView.FOCUS_DOWN);//???????????????
        }
    }

    //???????????????1?????????
    public String upStrgk2 = "";

    //??????????????? ????????????
    private void Set2StatusBarGK(String str) {
        if (!upStrgk2.equals(str)) {
            stringBuffer2gk.append(str + "" + "\n");
            textViews2_gk.setText(stringBuffer2gk);
            upStrgk2 = str;
            scrollView2gk.fullScroll(ScrollView.FOCUS_DOWN);//???????????????
        }
    }

    //???????????????1?????????
    public String upStrzm2 = "";

    //??????????????? ????????????
    private void Set2StatusBarZM(String str) {
        if (!upStrzm2.equals(str)) {
            stringBuffer2zm.append(str + "" + "\n");
            textViews2zm.setText(stringBuffer2zm);
            upStrzm2 = str;
            scrollView2zm.fullScroll(ScrollView.FOCUS_DOWN);//???????????????
        }
    }

    //???????????????1?????????
    public String upStr2 = "";

    //??????????????? ????????????
    private void Set2StatusBar(String str) {
        if (!upStr2.equals(str)) {
            stringBuffer2dw.append(str + "" + "\n");
            text2Viewsdw.setText(stringBuffer2dw);
            upStr2 = str;
            scrollView2dw.fullScroll(ScrollView.FOCUS_DOWN);//???????????????
        }
    }

    @Override
    protected int getView() {
        return Mylayout;
    }

    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_0:

                if (sb1_zm.equals("?????????")) {
                    ToastUtils.showToast("??????1?????????????????????");
                    break;
                }
                if (sb2_zm.equals("?????????")) {
                    ToastUtils.showToast("??????2?????????????????????");
                    break;
                }
                if (sb1_gk.equals("?????????")) {
                    ToastUtils.showToast("??????1?????????????????????");
                    break;
                }
                if (sb2_gk.equals("?????????")) {
                    ToastUtils.showToast("??????2?????????????????????");
                    break;
                }
                MainType = 0;
                tv_titles.setText("??????");
                rl0.setVisibility(View.VISIBLE);
                rl1.setVisibility(View.GONE);
                rl2.setVisibility(View.GONE);
                bt_0.setBackground(getResources().getDrawable(R.mipmap.dw_bl));
                bt_1.setBackground(getResources().getDrawable(R.mipmap.zm_gr));
                bt_2.setBackground(getResources().getDrawable(R.mipmap.gk_gr));
                AddITemMenu(0);//?????????????????????

                break;
            case R.id.bt_0:
                if (sb1_zm.equals("?????????")) {
                    ToastUtils.showToast("??????1?????????????????????");
                    break;
                }
                if (sb2_zm.equals("?????????")) {
                    ToastUtils.showToast("??????2?????????????????????");
                    break;
                }
                if (sb1_gk.equals("?????????")) {
                    ToastUtils.showToast("??????1?????????????????????");
                    break;
                }
                if (sb2_gk.equals("?????????")) {
                    ToastUtils.showToast("??????2?????????????????????");
                    break;
                }
                MainType = 0;
                tv_titles.setText("??????");
                rl0.setVisibility(View.VISIBLE);
                rl1.setVisibility(View.GONE);
                rl2.setVisibility(View.GONE);
                bt_0.setBackground(getResources().getDrawable(R.mipmap.dw_bl));
                bt_1.setBackground(getResources().getDrawable(R.mipmap.zm_gr));
                bt_2.setBackground(getResources().getDrawable(R.mipmap.gk_gr));
                AddITemMenu(0);//?????????????????????

                break;
            case R.id.ll_1:
                if (sb1.equals("?????????")) {
                    ToastUtils.showToast("??????1?????????????????????");

                    break;
                }
                if (sb2.equals("?????????")) {
                    ToastUtils.showToast("??????2?????????????????????");

                    break;
                }
                if (sb1_gk.equals("?????????")) {
                    ToastUtils.showToast("??????1?????????????????????");
                    break;
                }
                if (sb2_gk.equals("?????????")) {
                    ToastUtils.showToast("??????2?????????????????????");
                    break;
                }
                MainType = 1;
                tv_titles.setText("??????");
                rl0.setVisibility(View.GONE);
                rl2.setVisibility(View.GONE);
                rl1.setVisibility(View.VISIBLE);
                bt_1.setBackground(getResources().getDrawable(R.mipmap.zm_bl));
                bt_0.setBackground(getResources().getDrawable(R.mipmap.dw_gr));
                bt_2.setBackground(getResources().getDrawable(R.mipmap.gk_gr));
                AddITemMenu(1);//?????????????????????
                break;
            case R.id.bt_1:
                if (sb1.equals("?????????")) {
                    ToastUtils.showToast("??????1?????????????????????");

                    break;
                }
                if (sb2.equals("?????????")) {
                    ToastUtils.showToast("??????2?????????????????????");

                    break;
                }
                if (sb1_gk.equals("?????????")) {
                    ToastUtils.showToast("??????1?????????????????????");
                    break;
                }
                if (sb2_gk.equals("?????????")) {
                    ToastUtils.showToast("??????2?????????????????????");
                    break;
                }
                MainType = 1;
                tv_titles.setText("??????");
                rl0.setVisibility(View.GONE);
                rl2.setVisibility(View.GONE);
                rl1.setVisibility(View.VISIBLE);

                bt_1.setBackground(getResources().getDrawable(R.mipmap.zm_bl));
                bt_0.setBackground(getResources().getDrawable(R.mipmap.dw_gr));
                bt_2.setBackground(getResources().getDrawable(R.mipmap.gk_gr));
                AddITemMenu(1);//?????????????????????
                break;
            case R.id.ll_2:
                if (sb1.equals("?????????")) {
                    ToastUtils.showToast("??????1?????????????????????");

                    break;
                }
                if (sb2.equals("?????????")) {
                    ToastUtils.showToast("??????2?????????????????????");

                    break;
                }

                if (sb1_zm.equals("?????????")) {
                    ToastUtils.showToast("??????1?????????????????????");
                    break;
                }
                if (sb2_zm.equals("?????????")) {
                    ToastUtils.showToast("??????2?????????????????????");
                    break;
                }
                MainType = 2;
                tv_titles.setText("??????");
                rl0.setVisibility(View.GONE);
                rl1.setVisibility(View.GONE);
                rl2.setVisibility(View.VISIBLE);

                bt_2.setBackground(getResources().getDrawable(R.mipmap.gk_bl));
                bt_0.setBackground(getResources().getDrawable(R.mipmap.dw_gr));
                bt_1.setBackground(getResources().getDrawable(R.mipmap.zm_gr));
                AddITemMenu(2);//?????????????????????
                break;
            case R.id.bt_2:
                if (sb1.equals("?????????")) {
                    ToastUtils.showToast("??????1?????????????????????");

                    break;
                }
                if (sb2.equals("?????????")) {
                    ToastUtils.showToast("??????2?????????????????????");

                    break;
                }
                if (sb1_zm.equals("?????????")) {
                    ToastUtils.showToast("??????1?????????????????????");
                    break;
                }
                if (sb2_zm.equals("?????????")) {
                    ToastUtils.showToast("??????2?????????????????????");
                    break;
                }
                MainType = 2;
                tv_titles.setText("??????");
                rl0.setVisibility(View.GONE);
                rl1.setVisibility(View.GONE);
                rl2.setVisibility(View.VISIBLE);

                bt_2.setBackground(getResources().getDrawable(R.mipmap.gk_bl));
                bt_0.setBackground(getResources().getDrawable(R.mipmap.dw_gr));
                bt_1.setBackground(getResources().getDrawable(R.mipmap.zm_gr));
                AddITemMenu(2);//?????????????????????
                break;
            //  ????????????????????? ??????
            case R.id.iv_fengshan:

                presenter.setFS(FENGSHANFLAG, iv_fengshan, context);
                break;
            case R.id.iv_menu:
                popupWindow.showAsDropDown(view, 0, 0);

                break;

            case R.id.bt_start1dw:
                presenter.setStart(1, slideButton1Flag, MainType, sb1, sb2, spinnerS1, spinnerS2, context, tf1, tf2, 1, false);//restart   1???????????? ?????????,,, 0????????????????????????
//                ToastUtils.showToast("?????????");
                try {
                    dbManagerLog = new DBManagerLog(context);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                logBean = new LogBean();
                logBean.setAssociated(LoginUtils.getId(context) + "");//??????ID
                logBean.setEvent(LoginUtils.setBase64("????????????"));//????????????
                logBean.setSb(LoginUtils.setBase64("1"));

                format = sdf.format(new Date());//????????????
                logBean.setDatetime(LoginUtils.setBase64(format));
                try {
                    dbManagerLog.insertConmmunit01Bean(logBean);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.bt_start2dw:
                presenter.setStart(2, slideButton2Flag, MainType, sb1, sb2, spinnerS1, spinnerS2, context, tf1, tf2, 1, false);//restart   1???????????? ?????????,,, 0????????????????????????
                try {
                    dbManagerLog = new DBManagerLog(context);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                logBean = new LogBean();
                logBean.setAssociated(LoginUtils.getId(context) + "");//??????ID
                logBean.setEvent(LoginUtils.setBase64("????????????"));//????????????
                logBean.setSb(LoginUtils.setBase64("2"));
                String format = sdf.format(new Date());//????????????
                logBean.setDatetime(LoginUtils.setBase64(format));
                try {
                    dbManagerLog.insertConmmunit01Bean(logBean);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
//                ToastUtils.showToast("?????????");
                break;
            case R.id.bt_stop1dw:
                sb1FirstFlag = false;
                presenter.stopdw(1, context);
//               ToastUtils.showToast("?????????");
                try {
                    dbManagerLog = new DBManagerLog(context);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                logBean = new LogBean();
                logBean.setAssociated(LoginUtils.getId(context) + "");//??????ID
                logBean.setEvent(LoginUtils.setBase64("????????????"));//????????????
                logBean.setSb(LoginUtils.setBase64("1"));
                format = sdf.format(new Date());//????????????
                logBean.setDatetime(LoginUtils.setBase64(format));
                try {
                    dbManagerLog.insertConmmunit01Bean(logBean);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.bt_stop2dw:
                sb2FirstFlag = false;
                presenter.stopdw(2, context);
//               ToastUtils.showToast("?????????");
                try {
                    dbManagerLog = new DBManagerLog(context);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                logBean = new LogBean();
                logBean.setAssociated(LoginUtils.getId(context) + "");//??????ID
                logBean.setEvent(LoginUtils.setBase64("????????????"));//????????????
                logBean.setSb(LoginUtils.setBase64("2"));
                format = sdf.format(new Date());//????????????
                logBean.setDatetime(LoginUtils.setBase64(format));
                try {
                    dbManagerLog.insertConmmunit01Bean(logBean);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.laba1dw:
                presenter.setlaba(context, laba1dw, 1, laba1Flag);
                break;
            case R.id.laba2dw:
                presenter.setlaba(context, laba2dw, 2, laba2Flag);
                break;

            case R.id.ib_zhedie:
                if (zdFlagdw == false) {
                    lineChartViewdw.setVisibility(View.GONE);
                    ib_zhediedw.setBackground(getResources().getDrawable(R.mipmap.zheide));
                    zdFlagdw = true;
                } else if (zdFlagdw == true) {
                    lineChartViewdw.setVisibility(View.VISIBLE);
                    ib_zhediedw.setBackground(getResources().getDrawable(R.mipmap.zheidedown));
                    zdFlagdw = false;
                }
                break;
            case R.id.ib_zhedie_zhenma:
                if (zdSearchFlagdw == false) {
                    ll_zhenma_searchdw.setVisibility(View.VISIBLE);
                    ry_zhenma_dw.setVisibility(View.VISIBLE);
                    ib_zhedie_zhenmadw.setBackground(getResources().getDrawable(R.mipmap.zheidedown));
                    zdSearchFlagdw = true;

                } else if (zdSearchFlagdw == true) {
                    ll_zhenma_searchdw.setVisibility(View.GONE);
                    ry_zhenma_dw.setVisibility(View.GONE);
                    ib_zhedie_zhenmadw.setBackground(getResources().getDrawable(R.mipmap.zheide));
                    zdSearchFlagdw = false;
                }
                break;

            ////////////////////////////////////////////??????????????????
            case R.id.bts_stop_zm:
//                ToastUtils.showToast("??????????????????");                                        00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000

                presenter.stopZM(1, context, timerSB1, timerSB2, countDownTimer1, countDownTimer2, tv_datashengyu1, tv_datashengyu2, tv_xunhuanNum1_zm, tv_xunhuanNum2_zm, lunxunNum1, lunxunNum2);
                //????????????

                try {
                    dbManagerLog = new DBManagerLog(context);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                logBean = new LogBean();
                logBean.setAssociated(LoginUtils.getId(context) + "");//??????ID
                logBean.setEvent(LoginUtils.setBase64("????????????"));//????????????
                logBean.setSb(LoginUtils.setBase64("1"));
                format = sdf.format(new Date());//????????????
                logBean.setDatetime(LoginUtils.setBase64(format));
                try {
                    dbManagerLog.insertConmmunit01Bean(logBean);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
//                presenter.stop(2, context);
//                tv_datashengyu1.setText("??????????????????:");
//                if (countDownTimer1 != null) {
//                    countDownTimer1.cancel();
//                }
//                if (countDownTimer2 != null) {
//                    countDownTimer2.cancel();
//                }
//                lunxunNum1 = 1;
//                lunxunNum2 = 1;
//                tv_datashengyu1.setText("??????????????????:");
//                tv_datashengyu2.setText("??????????????????:");
//                tv_xunhuanNum1_zm.setText("??????????????????:");
//                tv_xunhuanNum2_zm.setText("??????????????????:");

                break;
            case R.id.bts_start1_zm:
//                ToastUtils.showToast("??????????????????");
                startdatazm1 = aDateFormat.format(new Date());
                presenter.setZMStart(context, tf1, tf2, sb1_zm, sb2_zm, 1);//type ???0 ????????????, 1 ????????????


                try {
                    dbManagerLog = new DBManagerLog(context);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                logBean = new LogBean();
                logBean.setAssociated(LoginUtils.getId(context) + "");//??????ID
                logBean.setEvent(LoginUtils.setBase64("??????????????????"));//????????????
                logBean.setSb(LoginUtils.setBase64("1"));
                format = sdf.format(new Date());//????????????
                logBean.setDatetime(LoginUtils.setBase64(format));
                try {
                    dbManagerLog.insertConmmunit01Bean(logBean);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.ib_qc_zm:
//                ToastUtils.showToast("????????????????????????");
                DialogUtils.DataExportzm(context, inflate, zmDataCallBack, 1);
                try {
                    dbManagerLog = new DBManagerLog(context);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                logBean = new LogBean();
                logBean.setAssociated(LoginUtils.getId(context) + "");//??????ID
                logBean.setEvent(LoginUtils.setBase64("??????????????????"));//????????????
                logBean.setSb(LoginUtils.setBase64("1"));
                format = sdf.format(new Date());//????????????
                logBean.setDatetime(LoginUtils.setBase64(format));
                try {
                    dbManagerLog.insertConmmunit01Bean(logBean);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.ib_dc_zm:
//                ToastUtils.showToast("????????????????????????");
                DialogUtils.DataExportzm(context, inflate, zmDataCallBack, 2);
                //????????????

                try {
                    dbManagerLog = new DBManagerLog(context);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                logBean = new LogBean();
                logBean.setAssociated(LoginUtils.getId(context) + "");//??????ID
                logBean.setEvent(LoginUtils.setBase64("??????????????????"));//????????????
                logBean.setSb(LoginUtils.setBase64("1"));
                format = sdf.format(new Date());//????????????
                logBean.setDatetime(LoginUtils.setBase64(format));
                try {
                    dbManagerLog.insertConmmunit01Bean(logBean);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.bts_start1_gk:
                Log.d("bts_start1_gk", "onClick: " + GuankongType);
                saopinGKFalg1 = false;
                saopinGKFalg2 = false;
                presenter.setGKtart(context, tf1, tf2, sb1_gk, sb1_gk, GuankongType);

                break;
            case R.id.bts_stop_gk:
                presenter.stopGK(context);

                break;
        }
    }

    @Override
    public void setPresenter(NewView.MainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void Up(String str, String strs) {

    }

    /**
     * ???????????????
     *
     * @param view
     */
    public void open(View view) {
        popupWindow.showAsDropDown(view, 0, 0);
    }

    @Override
    public void FsUp(boolean b) {
        FENGSHANFLAG = b;
    }

    @Override
    public void buildSdError(String str, int i) {
        if (i == 1) {
            Set1StatusBar(str);
        }
//        if (i==2){
//            Set1StatusBar(str);
//        }
    }

    @Override
    public void zhishiqiehuan(int device, String tf) {
        if (device == 1) {
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
        if (device == 2) {
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
    }

    /**
     * ????????????????????????
     *
     * @param device
     */
    @Override
    public void zidongsaopinjianlixiaoqu(int device) {
        if (device == 1) {
            saopinshow1 = false;
            DialogUtils.SaoPinDialog(device, context, inflate, saoPinCallback, tf1, true);
        }
        if (device == 2) {
            saopinshow2 = false;
            DialogUtils.SaoPinDialog(device, context, inflate, saoPinCallback, tf2, true);
        }

    }

    @Override
    public void setpararBeansList1(List<AddPararBean> pararBeansList) {
        pararBeansList1 = pararBeansList;
    }

    @Override
    public void labaup(int device, boolean bs) {
        if (device == 1) {
            laba1Flag = bs;
        }
        if (device == 2) {
            laba2Flag = bs;
        }
    }

    @Override
    public void quxian(String data, int device) {
        if (device == 1) {
            if (list1quxian.size() > 0) {
                list1quxian.remove(0);
                list1quxian.add(Integer.parseInt(data));
                double total = 0;
                for (int i = 0; i < list1quxian.size(); i++) {
                    total += list1quxian.get(i);
                }
                double a = total / list1quxian.size();

                setqxData(list1quxian, list2quxian);
            }
        }
        if (device == 2) {
            if (list2quxian.size() > 0) {
                list2quxian.remove(0);
                list2quxian.add(Integer.parseInt(data));
                double total = 0;
                for (int i = 0; i < list2quxian.size(); i++) {
                    total += list2quxian.get(i);
                }
                double a = total / list2quxian.size();

                setqxData(list1quxian, list2quxian);
            }
        }
    }

    /**
     * ???????????????????????????
     *
     * @param device
     */
    @Override
    public void zhishiqiehuanZm(int device) {
        if (device == 1) {
            MainUtils.Qiehuanzs("TDD", IP1);
            MainUtils.start1SNF(IP1, Constant.SNFTDD);
        }
        if (device == 2) {
            MainUtils.Qiehuanzs("FDD", IP2);
            MainUtils.start1SNF(IP2, Constant.SNFFDD);
        }
        if (device == 3) {
            MainUtils.Qiehuanzs("TDD", IP1);
            MainUtils.start1SNF(IP1, Constant.SNFTDD);
            MainUtils.Qiehuanzs("FDD", IP2);
            MainUtils.start1SNF(IP2, Constant.SNFFDD);
        }
        Log.d(TAG, "zhishiqiehuanZm: " + device);

    }

    @Override
    public void MesageV(int i) {
        if (i == 1) {
            if (timerdw1 != null) {
                timerdw1.cancel();
            }

        }
        if (i == 2) {
            if (timerdw2 != null) {
                timerdw2.cancel();
            }

        }
    }

    /**
     * ????????????list
     *
     * @param list1
     * @param list2
     */
    @Override
    public void zmlunxunlist(List<Integer> list1, List<Integer> list2) {
        saopinFalg1 = false;
        saopinFalg2 = false;
        MainUtils.sendspSocket(list1, IP1);
        MainUtils.sendspSocket(list2, IP2);
        lunxunNum1 = 1;
        lunxunNum2 = 1;
    }

    /**
     * ????????????list
     *
     * @param list1
     * @param list2
     */

    public void zmlunxunlist1(List<Integer> list1, List<Integer> list2) {
        saopinFalg1 = false;
        MainUtils.sendspSocket(list1, IP1);
        lunxunNum1 = 1;

    }

    /**
     * ????????????list
     *
     * @param list1
     * @param list2
     */

    public void zmlunxunlist2(List<Integer> list1, List<Integer> list2) {
        saopinFalg2 = false;
        MainUtils.sendspSocket(list2, IP2);
        lunxunNum2 = 1;
    }

    @Override
    public void zmstop(int i, int i2) {
        lunxunNum1 = 1;
        lunxunNum2 = 1;
    }

    /**
     * ????????????
     */
    @Override
    public void gkstop() {
        saopinGKFalg1 = false;
        saopinGKFalg2 = false;
    }

    @Override
    public void gkqiehuan(String tf, int device) {
        if (device == 1) {
            String titles = "";
            if (tf.equals("TDD")) {
                titles = "FDD";
                MainUtils.start1SNF(IP1, Constant.SNFFDD);
            }
            if (tf.equals("FDD")) {
                titles = "TDD";
                MainUtils.start1SNF(IP1, Constant.SNFTDD);
            }
            MainUtils.Qiehuanzs(titles, IP1);

        }
        if (device == 2) {
            String titles = "";
            if (tf.equals("TDD")) {
                titles = "FDD";
                MainUtils.start1SNF(IP2, Constant.SNFFDD);
            }
            if (tf.equals("FDD")) {
                titles = "TDD";
                MainUtils.start1SNF(IP2, Constant.SNFTDD);
            }
            MainUtils.Qiehuanzs(titles, IP2);

        }
    }

    @Override
    public void stopZmV() {
        if (timerzmjianli1 != null) {
            timerzmjianli1.cancel();
        }
        if (timerzmjianli2 != null) {
            timerzmjianli2.cancel();
        }
        if (timerZhenmaStop != null) {
            timerZhenmaStop.cancel();
            timerZhenmaStop = new Timer();
            timerZhenmaStop.schedule(new TimerTask() {

                //run?????????????????????????????????????????????
                @Override
                public void run() {

                    Message message = new Message();
                    handler.sendMessage(message);
                    message.what = 4445;
                    Log.d(TAG, "handlerrun: " + 1);
                    Log.d(TAG, "handlerrun: " + 1);
                }
            }, 0, 3000);//3??????????????????????????????
        } else {
            timerZhenmaStop = new Timer();

            timerZhenmaStop.schedule(new TimerTask() {

                //run?????????????????????????????????????????????
                @Override
                public void run() {

                    Message message = new Message();
                    handler.sendMessage(message);
                    message.what = 4445;
                    Log.d(TAG, "handlerrun: " + 1);
                    Log.d(TAG, "handlerrun: " + 1);
                }
            }, 0, 3000);//3??????????????????????????????
        }
    }

    @Override
    public void stopdwup(int i) {
        if (i == 1) {
            sp1MAX1value = "";//??????1?????????1?????????
            sp1MAX2value = "";//??????1?????????2?????????

        } else {

            sp2MAX1value = "";//??????2?????????1?????????
            sp2MAX2value = "";//??????2?????????2?????????
        }
    }

    List<States> listStates = new ArrayList<>();// ???????????????????????????

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

    //imsi??????
    private void ImslList() {
        List<AddPararBean> dataAll = null;//??????IMSI???????????????
        List<AddPararBean> listImsiListTrue = null;
        try {
            DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(this);
            dataAll = dbManagerAddParam.getDataAll();
            listImsiListTrue = new ArrayList<>();
            if (dataAll.size() > 0) {
                for (int i = 0; i < dataAll.size(); i++) {
                    if (dataAll.get(i).isCheckbox() == true) {
                        dataAll.get(i).setSb("");
                        listImsiListTrue.add(dataAll.get(i));
                    }
                }
                Log.d("aalistImsiListTrue", "ImslList: " + listImsiListTrue.toString());
                List<Integer> list1size = new ArrayList<>();
                if (listImsiListTrue.size() > 0) {
                    for (int i = 1; i < listImsiListTrue.size() + 1; i++) {
                        list1size.add(i);
                    }
                    pararBeansList1 = listImsiListTrue;
                    ryImsiAdapter = new RyImsiAdapter(Basecontext, listImsiListTrue, list1size, config, tv_imsi1_dw, tv_imsi2_dw);//list???imsi?????????????????????
                    ryIMSI_dw.setAdapter(ryImsiAdapter);
                } else {
                    pararBeansList1 = listImsiListTrue;
                    ryImsiAdapter = new RyImsiAdapter(Basecontext, listImsiListTrue, list1size, config, tv_imsi1_dw, tv_imsi2_dw);//list???imsi?????????????????????
                    ryIMSI_dw.setAdapter(ryImsiAdapter);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    private SpBean spBean1 = new SpBean();
    private SpBean spBean2 = new SpBean();
    private SpBean spBean154 = new SpBean();
    private SpBean spBean254 = new SpBean();
    SaoPinCallback  saoPinCallback = new SaoPinCallback() {
        @Override
        public void sucess(int sb, int i) {

            Log.d("saoPinCallback", "sucess: ??????" + sb + "i===" + i);
            SAOPIN = i;
            SAOPIN2 = i;
            if (sb == 1) {
                if (saopinshow1) {//????????? ????????????
                    presenter.spbuilsshow(context, sb, i, tf1, tf2);//??????????????????
                } else {//??????????????????????????????
                    sp1MAX1value = "";
                    sp1MAX2value = "";
                    presenter.spbuils(context, sb, i, tf1, tf2);//??????????????????

                    phoneFlag1 = false;
                }

            }
            if (sb == 2) {
                if (saopinshow2) {//?????????????????????
                    presenter.spbuilsshow(context, sb, i, tf1, tf2);//??????????????????

                } else {
                    sp2MAX1value = "";
                    sp2MAX2value = "";
                    presenter.spbuils(context, sb, i, tf1, tf2);//??????????????????
                    phoneFlag2 = false;
                }

            }

        }

        @Override
        public void sucessphone(int sb, String down, SpBean spBean, boolean phonesp) {
            Log.d("baqsa", "sucessphone: " + sb + "--" + down);
            if (sb == 1) {

//                phone1sp = phonesp;
                sp1MAX1value = down;
                spBean1 = spBean;

                if (MainUtils2.YY(spBean1.getPlmn()).equals("??????")) {
                    SAOPIN = 1;
                    Log.d("aaaplmnsucessphone", "sucessphone: +");
                    if (tf1.equals("TDD")) {

                        presenter.setStart(1, slideButton1Flag, MainType, sb1, sb2, sp1MAX1value, spinnerS2, context, tf1, tf2, 1, true);//restart   1???????????? ?????????,,, 0????????????????????????


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
                        phoneFlag1 = true;
                    }
                } else if (spBean.getBand().equals("1") || spBean.getBand().equals("3") || spBean.getBand().equals("5") || spBean.getBand().equals("8") || MainUtils2.YY(spBean1.getPlmn()).equals("??????")) {//??????FDD
                    SAOPIN = 2;
                    if (tf1.equals("FDD")) {
                        presenter.setStart(1, slideButton1Flag, MainType, sb1, sb2, sp1MAX1value, spinnerS2, context, tf1, tf2, 1, true);//restart   1???????????? ?????????,,, 0????????????????????????

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
                        phoneFlag1 = true;
                    }
                } else if (MainUtils2.YY(spBean1.getDown()).equals("??????")) {
                    SAOPIN = 3;
                    if (tf1.equals("FDD")) {

                        presenter.setStart(1, slideButton1Flag, MainType, sb1, sb2, sp1MAX1value, spinnerS2, context, tf1, tf2, 1, true);//restart   1???????????? ?????????,,, 0????????????????????????


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
                        phoneFlag1 = true;
                    }
                } else if (MainUtils2.YY(spBean1.getDown()).equals("??????")) {
                    SAOPIN = 4;
                    if (tf1.equals("FDD")) {

                        presenter.setStart(1, slideButton1Flag, MainType, sb1, sb2, sp1MAX1value, spinnerS2, context, tf1, tf2, 1, true);//restart   1???????????? ?????????,,, 0????????????????????????


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
                        phoneFlag1 = true;
                    }
                }

            }
            if (sb == 2) {
                sp2MAX1value = down;
                spBean154 = spBean;
                Log.d("spBean154a", "sucessphone: " + spBean154.toString());
                if (MainUtils2.YY(spBean154.getPlmn()).equals("??????") && !spBean154.getBand().equals("1") && !spBean154.getBand().equals("3") && !spBean154.getBand().equals("5") && !spBean154.getBand().equals("8")) {
                    SAOPIN2 = 1;
                    if (tf2.equals("TDD")) {

                        presenter.setStart(2, slideButton1Flag, MainType, sb1, sb2, sp1MAX1value, sp2MAX1value, context, tf1, tf2, 1, true);//restart   1???????????? ?????????,,, 0????????????????????????


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
                        phoneFlag2 = true;
                    }
                } else if (spBean154.getBand().equals("1") || spBean154.getBand().equals("3") || spBean154.getBand().equals("5") || spBean154.getBand().equals("8")) {//??????FDD
                    SAOPIN2 = 2;
                    if (tf2.equals("FDD")) {

                        presenter.setStart(2, slideButton1Flag, MainType, sb1, sb2, sp1MAX1value, sp2MAX1value, context, tf1, tf2, 1, true);//restart   1???????????? ?????????,,, 0????????????????????????


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
                        phoneFlag2 = true;
                    }
                } else if (MainUtils2.YY(spBean154.getDown()).equals("??????")) {
                    SAOPIN2 = 3;
                    if (tf2.equals("FDD")) {

                        presenter.setStart(2, slideButton1Flag, MainType, sb1, sb2, sp1MAX1value, sp2MAX1value, context, tf1, tf2, 1, true);//restart   1???????????? ?????????,,, 0????????????????????????


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
                        phoneFlag2 = true;
                    }
                } else if (MainUtils2.YY(spBean154.getDown()).equals("??????")) {
                    SAOPIN2 = 4;
                    if (tf2.equals("FDD")) {

                        presenter.setStart(2, slideButton1Flag, MainType, sb1, sb2, sp1MAX1value, sp2MAX1value, context, tf1, tf2, 1, true);//restart   1???????????? ?????????,,, 0????????????????????????


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
                        phoneFlag2 = true;
                    }
                } else {
//                    ToastUtils.showToast("mmmmm");
                }
            }
        }

        @Override
        public void sucessphoneShow(int sb, String down, SpBean spBean, boolean phonesp, boolean showFlag) {
            Log.d(TAG, "sucessphoneShow: ");
            if (sb == 1) {
                Intent intent = new Intent(context, SaoPinActivity.class);
                intent.putExtra("type", "1");
//                SPBEANLIST1.add(spBean);
                startActivity(intent);
            } else {
                Intent intent = new Intent(context, SaoPinActivity.class);
                intent.putExtra("type", "2");
//                SPBEANLIST2.add(spBean);
                startActivity(intent);
            }
        }

        @Override
        public void error() {

        }
    };

    public void saopinList(View view) {
        saopinshow1 = true;
        DialogUtils.SaoPinDialog2(1, context, inflate, saoPinCallback, tf1, false, sb1);
    }

    public void lishi11(View view) {

        if (SPBEANLIST1.size() > 0) {
            Intent intent = new Intent(context, SaoPinActivity.class);
            intent.putExtra("type", "1");
            startActivity(intent);
        } else {
            Set1StatusBar("???????????????????????????");
        }
    }

    public void saopinList2(View view) {
        saopinshow2 = true;
        DialogUtils.SaoPinDialog2(2, context, inflate, saoPinCallback, tf2, false, sb2);
    }

    public void lishi12(View view) {
        if (SPBEANLIST2.size() > 0) {
            Intent intent = new Intent(context, SaoPinActivity.class);
            intent.putExtra("type", "2");
            startActivity(intent);
        } else {
            Set2StatusBar("???????????????????????????");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        ImslList();
        setPinnerdata();
    }


    //???????????????
    private void sendwhiteListRun(int type, List<AddPararBeanWhite> sendListBlack, final String tf1, final String spinnerS1, final Context context, List<AddPararBeanWhite> listImsiListTrue) {

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
            if (type == 1) {
                sendrunwhite(str, sendListBlack, tf1, spinnerS1, context, listImsiListTrue);//????????????
            }
            if (type == 2) {

                sendrunwhite2(str, sendListBlack, tf1, spinnerS1, context, listImsiListTrue);//????????????
            }
            if (type == 3) {
                sendrunwhite(str, sendListBlack, tf1, spinnerS1, context, listImsiListTrue);//????????????
                sendrunwhite2(str, sendListBlack, tf1, spinnerS1, context, listImsiListTrue);//????????????
            }


        }
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
                Log.e("strDatanzq", "run: nzqsend" + strData);
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
}