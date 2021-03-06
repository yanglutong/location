package com.sm.android.locations.location.sos;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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
import android.view.KeyEvent;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sm.android.locations.location.Activity.AddParam.AddParamActivity;
import com.sm.android.locations.location.Activity.AddParam.ParamActivity;
import com.sm.android.locations.location.Activity.AddParam.ParamWhiteActivity;
import com.sm.android.locations.location.Activity.Communit.CommunitViewPagerActivity;
import com.sm.android.locations.location.Activity.Device.DeviceInfoActivity;
import com.sm.android.locations.location.Activity.Main.Adapter.RyImsiAdapter;
import com.sm.android.locations.location.Activity.Main.Adapter.RyZmAdapterGk;
import com.sm.android.locations.location.Activity.Main.Adapter.RyZmAdapterdw;
import com.sm.android.locations.location.Activity.Main.IT.SaoPinCallback;
import com.sm.android.locations.location.Activity.Main.MainActivity;
import com.sm.android.locations.location.Activity.Main.Objects.States;
import com.sm.android.locations.location.Activity.Main.View.SlideButton;
import com.sm.android.locations.location.Activity.PinConfig.PinConfigViewPagerActivity;
import com.sm.android.locations.location.Activity.SaoPin.SaoPinActivity.SaoPinActivity;
import com.sm.android.locations.location.Activity.SaoPin.SaopinList.SaoPinSetingActivity;
import com.sm.android.locations.location.Constant.Constant;
import com.sm.android.locations.location.Lunxun.SaopinList.LunxunSetingActivity;
import com.sm.android.locations.location.R;
import com.sm.android.locations.location.Utils.DialogUtils;
import com.sm.android.locations.location.Utils.LoginUtils.LoginUtils;
import com.sm.android.locations.location.Utils.MainUtils.AddMenuUtils;
import com.sm.android.locations.location.Utils.MainUtils.MainUtils;
import com.sm.android.locations.location.Utils.MainUtils.MainUtils2;
import com.sm.android.locations.location.Utils.MainUtils.MainUtilsThread;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.AddPararBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.AddPararBeanWhite;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.LogBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.PararBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.PinConfigBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.SpBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.ZmBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.ZmBeanGK;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.ZmBeanGKTongji;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.ZmBeanlinshi;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerAddParam;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerLog;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerPinConfig;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerZM;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerZMGK;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerZMlinshi;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerZY;
import com.sm.android.locations.location.Utils.ToastUtils;
import com.sm.android.locations.location.Utils.View.LineChartView;
import com.sm.android.locations.location.Utils.View.MyScrollView;
import com.sm.android.locations.location.Utils.pop.DLPopItem;
import com.sm.android.locations.location.Utils.pop.DLPopupWindow;
import com.sm.android.locations.location.initData.CallBackSetState;
import com.sm.android.locations.location.initData.CommandUtils;
import com.sm.android.locations.location.initData.ExecutorServiceUtils;
import com.sm.android.locations.location.initData.MyLog;
import com.sm.android.locations.location.initData.MyTTS;
import com.sm.android.locations.location.initData.MyUtils;
import com.sm.android.locations.location.initData.PLMN;
import com.sm.android.locations.location.initData.StringToHex;
import com.sm.android.locations.location.initData.TCPServer;
import com.sm.android.locations.location.initData.bean.SaoPBean;
import com.sm.android.locations.location.initData.bean.SaoPBeanRSRP;
import com.sm.android.locations.location.initData.bean.YXSaoPBean;
import com.sm.android.locations.location.initData.dao.DBManagerDevice;
import com.sm.android.locations.location.initData.dao.DeviceBean;
import com.sm.android.locations.location.viewpagermain.NewMainPager.update.dingwei.DwUpdate;
import com.sm.android.locations.location.viewpagermain.NewMainPager.update.dingwei.OpnUpdate;
import com.sm.android.locations.location.zhenma.ZhenmaFenxiActivity;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.graphics.Color.parseColor;
import static com.sm.android.locations.location.Activity.Main.MainActivity.SPBEANLIST1;
import static com.sm.android.locations.location.Activity.Main.MainActivity.SPBEANLIST2;
import static com.sm.android.locations.location.Constant.Constant.BOARDTEMPERATURE1;
import static com.sm.android.locations.location.Constant.Constant.DOWNPIN1;
import static com.sm.android.locations.location.Constant.Constant.GFFLAG1;
import static com.sm.android.locations.location.Constant.Constant.IP1;
import static com.sm.android.locations.location.Constant.Constant.IP2;
import static com.sm.android.locations.location.Constant.Constant.TITLESD;
import static com.sm.android.locations.location.Constant.Constant.TITLEZD;
import static com.sm.android.locations.location.Utils.MainUtils.MainUtils.DS;
import static com.sm.android.locations.location.Utils.MainUtils.MainUtils.i;
import static com.sm.android.locations.location.Utils.MainUtils.MainUtils.location;
import static com.sm.android.locations.location.Utils.MainUtils.MainUtils2.toIMSI;
import static com.sm.android.locations.location.initData.CommandUtils.gwCan;
import static com.sm.android.locations.location.initData.CommandUtils.imsilist;
import static com.sm.android.locations.location.initData.CommandUtils.sbZt;
import static com.sm.android.locations.location.initData.CommandUtils.type;
import static com.sm.android.locations.location.initData.CommandUtils.type0102;
import static com.sm.android.locations.location.sos.C.ContantSOS.soslayout;
import static com.sm.android.locations.location.sos.MubiaopSOS.ZMBEANGKTONGJILISTCALL;
import static com.sm.android.locations.location.viewpagermain.Fragment.SendUtils.setzy;

public class SOSActivity extends Activity implements View.OnClickListener, SOSVIEW.View, CallBackSetState {
    private boolean iswifiState=true;
    private String imsi="";//???????????????imsi
    private String shePin="";
    // TTS??????
    private TextToSpeech mTextToSpeech;
    String syns="";//???????????????????????????
    int len=0;
    List<Integer> listsize=new ArrayList<>();
    List<ZmBean> zmBeanscontains=new ArrayList<>();
    LinearLayoutManager linearLayoutManager = null;

    private String log="";
    /*1 ???????????????????????????  2 ????????????????????????????????? 3 ????????????????????????*///??????tcpServer???????????????????????????
    @SuppressLint("HandlerLeak")
    final Handler handlerMsg = new Handler() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @SuppressLint({"HandlerLeak", "SetTextI18n", "NewApi"})
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1: {//???????????????????????????????????????(?????????????????????utf-8)
                    type = msg.obj.toString();
                    break;
                }
                case 2: {
                    MyLog.e("msg2", "????????????????????????-----" + msg.obj.toString() + "?????????");//??????????????????????????????
                    break;
                }
                case 3: {
                    if (msg.obj.toString().equals("????????????")) {
                        type = msg.obj.toString(); //??????????????????
//                        Toast.makeText(context, "????????????", Toast.LENGTH_SHORT).show();
                        Set1StatusBar("????????????");


                        if (!type.equals("")) {
                            CommandUtils.sbZt = "??????";//???????????????????????????
                        }

                            gwCan="????????????";
                            socketTcpServer.sendPost(CommandUtils.getPublicParameters());//????????????????????????

                            //????????????????????????
                            socketTcpServer.sendPost(CommandUtils.getRF(0));
                    }
                    break;
                }
                case 0202: {//??????????????????
                    if (msg.obj.toString() != null);
                    syns = msg.obj.toString();
                    if (syns.contains("02020")) {
                        //????????????????????????
                        if (type0102.equals("????????????")) {//??????????????????????????? ?????????
                            ToastUtils.showToast("????????????");
                            Set1StatusBar("????????????????????????");
                            type0102 = "";
                            return;
                        } else {
                            Set1StatusBar("????????????????????????");
                            Set1StatusBar("??????????????????");
                            Set1StatusBar("??????????????????");
                            Set1StatusBar("???????????????");
                            CommandUtils.sbZt = "?????????";//????????????



                            //???????????????????????????????????????
                            socketTcpServer.sendPost(CommandUtils.getLocateMode(3));//??????????????????
                        }
                    }else if (syns.contains("02021")) {
                        CommandUtils.sbZt="??????";
                        Set1StatusBar("????????????????????????");
                        ToastUtils.showToast("????????????????????????");
                    }
                    break;
                }
                case 0207: {//0208  ????????????????????????????????????????????????
                    if (msg.obj.toString() != null) ;
                    syns = msg.obj.toString();
                    if(!CommandUtils.MODE.equals("")){//???????????????
                        if(Integer.parseInt(CommandUtils.MODE)==1){
                            tf1="TDD";
                        }
                        if(Integer.parseInt(CommandUtils.MODE)==2){
                            tf1="FDD";
                        }
                    }
                    if (gwCan.equals("????????????")) {
                        MyLog.e("??????Bug", "????????????");
                        gwCan = "";
                        return;
                    }else if (gwCan.equals("????????????")) {
                        MyLog.e("??????Bug", "???????????????????????????????????????");
                        gwCan="";
                        return;
                    }
                    break;
                }
                case 0226: {//5.93	???????????????????????? ???????????????
                    if (msg.obj.toString() != null) ;
                    syns = msg.obj.toString();
                    if (syns.contains("02260")) {//??????????????????????????????
//                        ToastUtils.showToast("????????????????????????");
                        Set1StatusBar("????????????????????????");

                        socketTcpServer.sendPost(CommandUtils.getBlackList());//???????????????
                        MyLog.e("??????Bug", StringToHex.convertHexToString(CommandUtils.getBlackList()));
                        Set1StatusBar("???????????????");
                    } else {
                        CommandUtils.sbZt="??????";
                        Set1StatusBar("????????????????????????");
                        ToastUtils.showToast("????????????????????????");
                    }
                    break;
                }
                case 0210: {//5.23	??????IMSI??????????????????????????????
                    if (msg.obj.toString() != null) ;
                    syns = msg.obj.toString();
                    if (syns.contains("02100")) {//???????????????????????????
                        Set1StatusBar("?????????????????????");
                        socketTcpServer.sendPost(CommandUtils.setLocationImsI(imsilist.get(0)));//???????????????
                        MyLog.e("??????Bug", StringToHex.convertHexToString(CommandUtils.setLocationImsI(imsilist.get(0))));
                        Set1StatusBar("????????????IMSI");
                    } else {
                        Set1StatusBar("?????????????????????");
                        ToastUtils.showToast("?????????????????????");
                        CommandUtils.sbZt="??????";
                    }
                    break;
                }
                case 0236: {//?????????????????????????????????
                    if (msg.obj.toString() != null) ;
                    syns = msg.obj.toString();
                    if (syns.contains("02360")) {//???????????????????????????
                        if (!imsi.equals("")) {
                            Set1StatusBar("????????????IMSI" + imsi+"??????");
                        } else {
                            if (imsilist.size() > 0) {
                                String s = imsilist.get(0);
                                Set1StatusBar("????????????IMSI" + s+"??????");
                            } else {
                                Set1StatusBar("????????????IMSI??????");
                            }
                        }
                        Set1StatusBar("??????????????????");
                        socketTcpServer.sendPost(CommandUtils.getRF(1));//?????????
                        MyLog.e("??????Bug", CommandUtils.getRF(1));
                    } else {
                        CommandUtils.sbZt="??????";
                        Set1StatusBar("????????????IMSI??????");
                    }
                    break;
                }
                case 0204: {//??????
                    if (msg.obj.toString() != null) ;
                    syns = msg.obj.toString();
                    if (syns.contains("02040")) {//??????????????????????????????
                        if(socketTcpServer!=null){
                            if(!type.equals("")){
                                new Timer().schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        socketTcpServer.sendPost(CommandUtils.getPublicParameters());
                                    }
                                }, 5000);
//                                new Timer().schedule(new TimerTask() {
//                                    @Override
//                                    public void run() {
//                                        runOnUiThread(new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                if(!CommandUtils.DLARFCN.equals("")){
//                                                    mysp1.setVisibility(View.GONE);
//                                                    tv_r1dw.setText("??????: "+CommandUtils.DLARFCN);
//                                                }
//                                            }
//                                        });
//                                    }
//                                }, 6000);
                            }
                        }


                        if (CommandUtils.RF_STATE == 0) {//?????????????????????


                            CommandUtils.dwei="????????????";//??????????????????
                            shePin = "??????";
                            Set1StatusBar("???????????????");
//                            ToastUtils.showToast("????????????");
                            //?????????????????????????????????imsi????????????????????????????????????
                            ImslList();
                            //?????????imsi?????????
                            tv_imsi1_dw.setText("");
                            //???????????????
                            tv_sb1_jl_dw.setText("");


                            CommandUtils.sbZt = "??????";//???????????????????????????



                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            DBManagerLog dbManagerLog = null;
                            String string = "";

                            string = CommandUtils.dwei;

                            //????????????
                            try {
                                dbManagerLog = new DBManagerLog(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            LogBean logBean = new LogBean();
                            logBean.setAssociated(LoginUtils.getId(context) + "");//??????ID
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
                        if (CommandUtils.RF_STATE == 1) {

                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            DBManagerLog dbManagerLog = null;
                            String string = "";

                            string = CommandUtils.dwei;

                            //????????????
                            try {
                                dbManagerLog = new DBManagerLog(context);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            LogBean logBean = new LogBean();
                            logBean.setAssociated(LoginUtils.getId(context) + "");//??????ID
                            logBean.setEvent(LoginUtils.setBase64(string));//????????????
                            if(!DOWNPIN1.equals("")){
                                logBean.setPd(LoginUtils.setBase64(DOWNPIN1));
                            }
                            Log.i("yltimsilist", "handleMessage: "+DOWNPIN1);

                            Log.i("yltimsilist", "handleMessage: "+imsilist.get(0));
                            if(!imsilist.get(0).equals("")){
                                logBean.setSucessIMSI(LoginUtils.setBase64(imsilist.get(0)));
                            }
                            logBean.setSb(LoginUtils.setBase64("1"));

                            String format = sdf.format(new Date());//????????????
                            logBean.setDatetime(LoginUtils.setBase64(format));
                            try {

                                MyLog.e("BII12",imsilist.get(0));
                                MyLog.e("BII12",CommandUtils.DLARFCN);
                                MyLog.e("BII12",logBean.toString());
                                dbManagerLog.insertConmmunit01Bean(logBean);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                            //?????????????????????????????????
                            CommandUtils.sbZt = "?????????";//????????????????????????

                            shePin = "??????";
                            Set1StatusBar("???????????????");
                            Set1StatusBar("?????????");
                        }
                    } else if (syns.contains("02041")) {
                        Set1StatusBar("??????????????????????????????");
                        CommandUtils.sbZt="??????";
                    }
                    break;
                }
                case 0303: {//????????????imsi
                    if (msg.obj.toString() != null)
                        if(!shePin.equals("??????")){//???????????????????????????????????????
                            CommandUtils.type0303 = msg.obj.toString();
                        }
                    break;
                }
                case 0302: {//???????????????IMSI
//                        //??????????????????imsi???
                    //????????????????????????
                    zmListsize.add(msg.obj.toString());



                    tv_searchNumdw.setText("(" + zmListsize.size() + ")");
                    String s = msg.obj.toString();
                    String f = s.substring(48);//????????? ????????????

                    MyLog.e("ttt", f + "\r\n");
                    String[] imsi = f.split("IMSI:");
                    String ims = imsi[1].substring(0, 15);
                    Log.e("ttt", "IMSI??????: " + ims);
                    listIMSI.add(ims);
                    if (linearLayoutManager == null) {
                        linearLayoutManager = new LinearLayoutManager(context);
                    }
//                    linearLayoutManager.setStackFromEnd(true);//???????????????

                    ry_zhenma_dw.setLayoutManager(linearLayoutManager);

                    zmBeanscontains.add(new ZmBean(ims, "1", "123", new SimpleDateFormat("HH:mm:ss").format(new Date()) + "", new SimpleDateFormat("HH:mm:ss").format(new Date()) + "", CommandUtils.DLARFCN, "123", len++));
//                        zmBeanscontains=com.sm.android.locations.location.initData.MyUtils.removeDuplicate(zmBeanscontains);

                    //1 2 3 4 5  5 4 3 2 1


                    listsize.clear();
                    for (int j = 1; j < zmBeanscontains.size() + 1; j++) {
                        listsize.add(j);
                    }
                    ryZmAdapterdw = new RyZmAdapterdw(SOSActivity.this, zmBeanscontains, listsize);//list???imsi?????????????????????
                    ry_zhenma_dw.setAdapter(ryZmAdapterdw);
                    linearLayoutManager.scrollToPositionWithOffset(ryZmAdapterdw.getItemCount() - 1, Integer.MIN_VALUE);//??????????????????????????? ???????????????????????????????????????????????????????????????

                    DBManagerZM dbManagerZM = null;
                    try {
                        dbManagerZM = new DBManagerZM(SOSActivity.this);
                        dbManagerZM.deleteall();
                        if (zmBeanscontains.size() > 0) {
                            for (int i = 0; i < zmBeanscontains.size(); i++) {
                                dbManagerZM.insertAddZmBean(zmBeanscontains.get(i));
                            }
                        }
                        List<ZmBean> zmBeanList = dbManagerZM.getDataAll();
                        Log.i("???????????????", "handleMessage: " + zmBeanList.toString());










                        SimpleDateFormat sdf =null;
                                sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        DBManagerLog dbManagerLog = null;
                        String string = "";

                        string = "??????";

                        //????????????
                        try {
                            dbManagerLog = new DBManagerLog(context);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        LogBean logBean = new LogBean();
                        logBean.setAssociated(LoginUtils.getId(context) + "");//??????ID
                        logBean.setEvent(LoginUtils.setBase64(string));//????????????
                        logBean.setPd(LoginUtils.setBase64(CommandUtils.DLARFCN));
                        logBean.setSb(LoginUtils.setBase64("1"));
                        String format = sdf.format(new Date());//????????????
                        logBean.setDatetime(LoginUtils.setBase64(format));
                        try {
                            dbManagerLog.insertConmmunit01Bean(logBean);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case 0201:
                    if(msg.obj.toString()!=null){//????????????
                            if(msg.getData().getString("type").contains("0201")){
                                list0201=new ArrayList<>();
                                SaoPBeanRSRP saoPBeanRSRP;
                                String[] strings = msg.obj.toString().split("\r\n");
                                for (String string : strings) {//??????
                                    saoPBeanRSRP= new SaoPBeanRSRP();
                                    MyLog.e("0201", string);
                                    MyLog.e("0201", "-------------------");
                                    String[] split = string.split("\t");
                                    for (String s : split) {
                                        MyLog.e("0201E", s);
                                        if(s.contains("EARFCN:")){
                                            if(s.contains("0201EARFCN")){
                                                String[] split1 = s.split("0201");
                                                saoPBeanRSRP.setEARFCN(split1[1].split(":")[1]);
                                            }else{
                                                saoPBeanRSRP.setEARFCN(s.split(":")[1]);
                                            }
                                        }
                                        if(s.contains("RSRP:")){
                                            saoPBeanRSRP.setRSRP(s.split(":")[1]);
                                        }if(s.contains("CI:")){
                                            saoPBeanRSRP.setCid(s.split(":")[1]);
                                        }if(s.contains("TAC:")){
                                            saoPBeanRSRP.setTac(s.split(":")[1]);
                                        }if(s.contains("PCI:")){
                                            saoPBeanRSRP.setPci(s.split(":")[1]);
                                        }
                                    }
                                    if(saoPBeanRSRP.getEARFCN()!=null
                                            &&saoPBeanRSRP.getRSRP()!=null
                                            &&saoPBeanRSRP.getTac()!=null&&saoPBeanRSRP.getPci()!=null
                                            &&saoPBeanRSRP.getCid()!=null){
                                        list0201.add(saoPBeanRSRP);
                                    }
                                }
                                Log.i("0201handleMessage", "list0201.toString():?????????"+list0201.toString()+"\r\n");


                                if(list0201.size()>0){
                                    if(CommandUtils.spbuilsshow){//????????????
                                        CommandUtils.sbZt="??????";
                                        Set1StatusBar("????????????");
                                        xqck.clear();
                                        SpBean spBean;
                                        for (SaoPBeanRSRP yxSaoPBean : list0201) {
                                            spBean = new SpBean();
                                            if(yxSaoPBean.getEARFCN().equals("37900")){
                                                spBean.setBand("38");
                                                spBean.setPlmn("46000");
                                                spBean.setRsrp(Integer.parseInt(yxSaoPBean.getRSRP()));
                                                spBean.setDown(yxSaoPBean.getEARFCN());
                                                spBean.setCid(yxSaoPBean.getCid());
                                                spBean.setPci(Integer.parseInt(yxSaoPBean.getPci()));
                                                spBean.setPriority(0);
                                                spBean.setRsrq(0);
                                                spBean.setTac(Integer.parseInt(yxSaoPBean.getTac()));
                                                spBean.setUp(yxSaoPBean.getEARFCN());
                                            }if(yxSaoPBean.getEARFCN().equals("38098")){
                                                spBean.setBand("38");
                                                spBean.setPlmn("46000");
                                                spBean.setRsrp(Integer.parseInt(yxSaoPBean.getRSRP()));
                                                spBean.setDown(yxSaoPBean.getEARFCN());
                                                spBean.setCid(yxSaoPBean.getCid());
                                                spBean.setPci(Integer.parseInt(yxSaoPBean.getPci()));
                                                spBean.setPriority(0);
                                                spBean.setRsrq(0);
                                                spBean.setTac(Integer.parseInt(yxSaoPBean.getTac()));
                                                spBean.setUp(yxSaoPBean.getEARFCN());
                                            }if(yxSaoPBean.getEARFCN().equals("38400")){
                                                spBean.setBand("39");
                                                spBean.setPlmn("46000");
                                                spBean.setRsrp(Integer.parseInt(yxSaoPBean.getRSRP()));
                                                spBean.setDown(yxSaoPBean.getEARFCN());
                                                spBean.setCid(yxSaoPBean.getCid());
                                                spBean.setPci(Integer.parseInt(yxSaoPBean.getPci()));
                                                spBean.setPriority(0);
                                                spBean.setRsrq(0);
                                                spBean.setTac(Integer.parseInt(yxSaoPBean.getTac()));
                                                spBean.setUp(yxSaoPBean.getEARFCN());
                                            }if(yxSaoPBean.getEARFCN().equals("38544")){
                                                spBean.setBand("39");
                                                spBean.setPlmn("46000");
                                                spBean.setRsrp(Integer.parseInt(yxSaoPBean.getRSRP()));
                                                spBean.setDown(yxSaoPBean.getEARFCN());
                                                spBean.setCid(yxSaoPBean.getCid());
                                                spBean.setPci(Integer.parseInt(yxSaoPBean.getPci()));
                                                spBean.setPriority(0);
                                                spBean.setRsrq(0);
                                                spBean.setTac(Integer.parseInt(yxSaoPBean.getTac()));
                                                spBean.setUp(yxSaoPBean.getEARFCN());
                                            }if(yxSaoPBean.getEARFCN().equals("38950")){
                                                spBean.setBand("40");
                                                spBean.setPlmn("46000");
                                                spBean.setRsrp(Integer.parseInt(yxSaoPBean.getRSRP()));
                                                spBean.setDown(yxSaoPBean.getEARFCN());
                                                spBean.setCid(yxSaoPBean.getCid());
                                                spBean.setPci(Integer.parseInt(yxSaoPBean.getPci()));
                                                spBean.setPriority(0);
                                                spBean.setRsrq(0);
                                                spBean.setTac(Integer.parseInt(yxSaoPBean.getTac()));
                                                spBean.setUp(yxSaoPBean.getEARFCN());
                                            }if(yxSaoPBean.getEARFCN().equals("40936")){
                                                spBean.setBand("41");
                                                spBean.setPlmn("46000");
                                                spBean.setRsrp(Integer.parseInt(yxSaoPBean.getRSRP()));
                                                spBean.setDown(yxSaoPBean.getEARFCN());
                                                spBean.setCid(yxSaoPBean.getCid());
                                                spBean.setPci(Integer.parseInt(yxSaoPBean.getPci()));
                                                spBean.setPriority(0);
                                                spBean.setRsrq(0);
                                                spBean.setTac(Integer.parseInt(yxSaoPBean.getTac()));
                                                spBean.setUp(yxSaoPBean.getEARFCN());
                                            }if(yxSaoPBean.getEARFCN().equals("1300")){
                                                spBean.setBand("3");
                                                spBean.setPlmn("46000");
                                                spBean.setRsrp(Integer.parseInt(yxSaoPBean.getRSRP()));
                                                spBean.setDown(yxSaoPBean.getEARFCN());
                                                spBean.setCid(yxSaoPBean.getCid());
                                                spBean.setPci(Integer.parseInt(yxSaoPBean.getPci()));
                                                spBean.setPriority(0);
                                                spBean.setRsrq(0);
                                                spBean.setTac(Integer.parseInt(yxSaoPBean.getTac()));
                                                spBean.setUp(yxSaoPBean.getEARFCN());
                                            }if(yxSaoPBean.getEARFCN().equals("3683")){
                                                spBean.setBand("8");
                                                spBean.setPlmn("46000");
                                                spBean.setRsrp(Integer.parseInt(yxSaoPBean.getRSRP()));
                                                spBean.setDown(yxSaoPBean.getEARFCN());
                                                spBean.setCid(yxSaoPBean.getCid());
                                                spBean.setPci(Integer.parseInt(yxSaoPBean.getPci()));
                                                spBean.setPriority(0);
                                                spBean.setRsrq(0);
                                                spBean.setTac(Integer.parseInt(yxSaoPBean.getTac()));
                                                spBean.setUp(yxSaoPBean.getEARFCN());
                                            }if(yxSaoPBean.getEARFCN().equals("375")){
                                                spBean.setBand("1");
                                                spBean.setPlmn("46001");
                                                spBean.setRsrp(Integer.parseInt(yxSaoPBean.getRSRP()));
                                                spBean.setDown(yxSaoPBean.getEARFCN());
                                                spBean.setCid(yxSaoPBean.getCid());
                                                spBean.setPci(Integer.parseInt(yxSaoPBean.getPci()));
                                                spBean.setPriority(0);
                                                spBean.setRsrq(0);
                                                spBean.setTac(Integer.parseInt(yxSaoPBean.getTac()));
                                                spBean.setUp(yxSaoPBean.getEARFCN());
                                            }if(yxSaoPBean.getEARFCN().equals("1650")){
                                                spBean.setBand("3");
                                                spBean.setPlmn("46001");
                                                spBean.setRsrp(Integer.parseInt(yxSaoPBean.getRSRP()));
                                                spBean.setDown(yxSaoPBean.getEARFCN());
                                                spBean.setCid(yxSaoPBean.getCid());
                                                spBean.setPci(Integer.parseInt(yxSaoPBean.getPci()));
                                                spBean.setPriority(0);
                                                spBean.setRsrq(0);
                                                spBean.setTac(Integer.parseInt(yxSaoPBean.getTac()));
                                                spBean.setUp(yxSaoPBean.getEARFCN());
                                            }if(yxSaoPBean.getEARFCN().equals("100")){
                                                spBean.setBand("1");
                                                spBean.setPlmn("46011");
                                                spBean.setRsrp(Integer.parseInt(yxSaoPBean.getRSRP()));
                                                spBean.setDown(yxSaoPBean.getEARFCN());
                                                spBean.setCid(yxSaoPBean.getCid());
                                                spBean.setPci(Integer.parseInt(yxSaoPBean.getPci()));
                                                spBean.setPriority(0);
                                                spBean.setRsrq(0);
                                                spBean.setTac(Integer.parseInt(yxSaoPBean.getTac()));
                                                spBean.setUp(yxSaoPBean.getEARFCN());
                                            }if(yxSaoPBean.getEARFCN().equals("1825")){
                                                spBean.setBand("3");
                                                spBean.setPlmn("46011");
                                                spBean.setRsrp(Integer.parseInt(yxSaoPBean.getRSRP()));
                                                spBean.setDown(yxSaoPBean.getEARFCN());
                                                spBean.setCid("");
                                                spBean.setPci(0);
                                                spBean.setPriority(0);
                                                spBean.setRsrq(0);
                                                spBean.setTac(Integer.parseInt(yxSaoPBean.getTac()));
                                                spBean.setUp(yxSaoPBean.getEARFCN());
                                            }if(yxSaoPBean.getEARFCN().equals("1850")){
                                                spBean.setBand("3");
                                                spBean.setPlmn("46011");
                                                spBean.setRsrp(Integer.parseInt(yxSaoPBean.getRSRP()));
                                                spBean.setDown(yxSaoPBean.getEARFCN());
                                                spBean.setCid(yxSaoPBean.getCid());
                                                spBean.setPci(Integer.parseInt(yxSaoPBean.getPci()));
                                                spBean.setPriority(0);
                                                spBean.setRsrq(0);
                                                spBean.setTac(Integer.parseInt(yxSaoPBean.getTac()));
                                                spBean.setUp(yxSaoPBean.getEARFCN());
                                            }if(yxSaoPBean.getEARFCN().equals("2452")){
                                                spBean.setBand("5");
                                                spBean.setPlmn("46011");
                                                spBean.setRsrp(Integer.parseInt(yxSaoPBean.getRSRP()));
                                                spBean.setDown(yxSaoPBean.getEARFCN());
                                                spBean.setCid(yxSaoPBean.getCid());
                                                spBean.setPci(Integer.parseInt(yxSaoPBean.getPci()));
                                                spBean.setPriority(0);
                                                spBean.setRsrq(0);
                                                spBean.setTac(Integer.parseInt(yxSaoPBean.getTac()));
                                                spBean.setUp(yxSaoPBean.getEARFCN());
                                            }
                                            if(spBean.getDown()!=null&&spBean.getUp()!=null){
                                                xqck.add(spBean);
                                            }
                                        }
                                        MyLog.e("????????????0305", xqck.toString());
                                        xqck = com.sm.android.locations.location.Utils.MyUtils.removeDd(xqck);
                                        Intent intent = new Intent(context, SaoPinActivity.class);
                                        intent.putExtra("type", "3");
                                        startActivity(intent);
                                    }else{//????????????
                                        if(socketTcpServer!=null){
                                            if(!type.equals("")){
                                                if(CommandUtils.list.size()>0){//??????????????????
                                                    MyLog.e("SOSPresent", "SOSPresent0201"+CommandUtils.list+"");

                                                    MyLog.e("list0201", ""+list0201.get(0).getEARFCN().equals(""));

                                                    List<SaoPBeanRSRP> list;
                                                    list=new ArrayList<>();
                                                    for (SaoPBeanRSRP pBeanRSRP : list0201) {
                                                        for (Integer integer : CommandUtils.list) {
                                                            if(pBeanRSRP.getEARFCN().equals(integer+"")){
                                                                list.add(pBeanRSRP);
                                                            }
                                                        }
                                                    }
                                                    MyLog.e("SOSPresent", list.toString());


                                                    presenter.startSaoPin(1, type, context, list.get(0).getEARFCN(), SOSActivity.sbZhuangTai,tv1_wifi_dw.getText().toString(),socketTcpServer,setState);
                                                    Set1StatusBar("????????????");
                                                    Set1StatusBar("????????????");
                                                    CommandUtils.sbZt="?????????";
                                                    Set1StatusBar("???????????????");

                                                    //?????????????????????????????????????????????????????????
                                                    new Timer().schedule(new TimerTask() {
                                                        @Override
                                                        public void run() {
                                                            MyLog.e("????????????", ""+ sbZt);
                                                            runOnUiThread(new Runnable() {
                                                                @Override
                                                                public void run() {
                                                                    if(sbZt.equals("?????????")){
                                                                        Set1StatusBar("??????????????????");
                                                                        ToastUtils.showToast("??????????????????");
                                                                        sbZt="??????";
                                                                    }
                                                                }
                                                            });
                                                        }
                                                    }, 60000);
                                                }

                                            }
                                        }
                                    }
                                }else{
                                    ToastUtils.showToast("??????????????????");
                                    Set1StatusBar("??????????????????");
                                    CommandUtils.sbZt="??????";
                                }
                            }
                    }
                    break;
                case 0323:
                    //?????????????????????
                    if(list!=null&&list0201!=null){
                        if(list0201.size()>0&&list.size()>0){
                            if(yXList==null){
                                yXList=new ArrayList<>();
                            }else{
                                yXList.clear();
                            }
                            MyLog.e("aaalist0201", "0201"+list0201.toString());
                            MyLog.e("aaalist0201", "0305"+list.toString()+"\r\n\r\n");
                            for (int i1 = 0; i1 < list.size(); i1++) {//0305
                                SaoPBean bean = list.get(i1);
                                MyLog.e("aaalist", "list0305????????????"+bean.getEARFCN());
                                MyLog.e("aaalist", "-------------------");
                                for (int i2 = 0; i2 < list0201.size(); i2++) {//0201
                                    if(bean.getEARFCN().equals(list0201.get(i2).getEARFCN())){//03050201?????????????????????
                                        yXList.add(new YXSaoPBean(Integer.parseInt(bean.getPRI()), Integer.parseInt(list0201.get(i2).getRSRP()),bean.getEARFCN()));
                                    }else{
                                        MyLog.e("aaalist0201", "???????????????"+yXList.toString()+"\r\n\r\n");
                                        MyLog.e("aaalist0201", "???????????????"+"\r\n\r\n");
                                    }
                                }
                            }
                            if(yXList.size()>0){//??????????????????????????????????????? ?????????????????????????????? ????????????????????????
                                MyLog.e("aaalist", "yxlist:"+yXList.toString());
                                if(yXList.size()==1){
//                                        ?????????????????????????????????
//                                        ???????????????????????????
                                    if(socketTcpServer!=null){
                                        if(!type.equals("")){
                                            MyLog.e("??????????????????????????????","????????????????????? ??????"+yXList.get(0).getEARFCN()+"--"+yXList.toString());
                                            presenter.startSaoPin(1, type, context, yXList.get(0).getEARFCN(), SOSActivity.sbZhuangTai,tv1_wifi_dw.getText().toString(),socketTcpServer,setState);
                                            Set1StatusBar("????????????");
                                            CommandUtils.sbZt="?????????";
                                            Set1StatusBar("???????????????");
                                        }
                                    }
                                }else{
                                    //???????????????????????????????????????????????????
                                    ArrayList<YXSaoPBean> saoP=null;
                                    if(saoP==null){
                                        saoP=new ArrayList<>();
                                    }
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                        yXList.sort(Comparator.comparing(YXSaoPBean::getPRI).thenComparing(YXSaoPBean::getRSRP));//????????????????????????????????????
                                    }
                                    MyLog.e("aaalist","??????????????????????????????---"+yXList.toString());
                                    //???????????????????????????
                                    if(socketTcpServer!=null){
                                        if(!type.equals("")){
                                            MyLog.e("??????????????????????????????","????????????????????? ??????"+yXList.get(0).getEARFCN()+"--"+yXList.toString());
                                            presenter.startSaoPin(1, type, context, yXList.get(yXList.size()-1).getEARFCN(), SOSActivity.sbZhuangTai,tv1_wifi_dw.getText().toString(),socketTcpServer,setState);
                                            Set1StatusBar("????????????");
                                            CommandUtils.sbZt="?????????";
                                            Set1StatusBar("???????????????");
                                        }
                                    }
                                }
                            }else{//????????????????????????0305????????? ????????????????????????????????????
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                    list.sort(Comparator.comparing(SaoPBean::getPRI));
                                    //???????????????????????????
                                    if(socketTcpServer!=null){
                                        if(!type.equals("")){
                                            MyLog.e("??????????????????????????????","??????0305?????????"+list.toString());
                                            presenter.startSaoPin(1, type, context, list.get(list.size()-1).getEARFCN(), SOSActivity.sbZhuangTai,tv1_wifi_dw.getText().toString(),socketTcpServer,setState);
                                            Set1StatusBar("????????????");
                                            CommandUtils.sbZt="?????????";
                                            Set1StatusBar("???????????????");
                                        }
                                    }
                                }
                            }
                        }
                    }


                    if(list!=null&&list0201!=null){
                        if(list0201.size()>0&&list.size()>0){
                            if(yXList==null){
                                yXList=new ArrayList<>();
                            }else{
                                yXList.clear();
                            }
                            MyLog.e("aaalist0201", "0201"+list0201.toString());
                            MyLog.e("aaalist0201", "0305"+list.toString()+"\r\n\r\n");
                            for (int i1 = 0; i1 < list.size(); i1++) {//0305
                                SaoPBean bean = list.get(i1);
                                MyLog.e("aaalist", "list0305????????????"+bean.getEARFCN());
                                MyLog.e("aaalist", "-------------------");
                                for (int i2 = 0; i2 < list0201.size(); i2++) {//0201
                                    if(bean.getEARFCN().equals(list0201.get(i2).getEARFCN())){//03050201?????????????????????
                                        yXList.add(new YXSaoPBean(Integer.parseInt(bean.getPRI()), Integer.parseInt(list0201.get(i2).getRSRP()),bean.getEARFCN(),list0201.get(i2).getTac(),list0201.get(i2).getCid(),list0201.get(i2).getPci()));
                                    }else{
                                    }
                                }
                            }
                            MyLog.e("aaalist0201", "???????????????"+yXList.toString()+"\r\n\r\n");
                            if(yXList.size()>0){//????????????????????????????????????
                                CommandUtils.sbZt="??????";
                                Set1StatusBar("????????????");
                                xqck.clear();
                                SpBean spBean;
                                for (YXSaoPBean yxSaoPBean : yXList) {
                                    spBean = new SpBean();
                                    if(yxSaoPBean.getEARFCN().equals("37900")){
                                        spBean.setBand("38");
                                        spBean.setPlmn("46000");
                                        spBean.setRsrp(yxSaoPBean.getRSRP());
                                        spBean.setDown(yxSaoPBean.getEARFCN());
                                        spBean.setCid(yxSaoPBean.getCid());
                                        spBean.setPci(Integer.parseInt(yxSaoPBean.getPci()));
                                        spBean.setPriority(0);
                                        spBean.setRsrq(0);
                                        spBean.setTac(Integer.parseInt(yxSaoPBean.getTac()));
                                        spBean.setUp(yxSaoPBean.getEARFCN());
                                    }if(yxSaoPBean.getEARFCN().equals("38098")){
                                        spBean.setBand("38");
                                        spBean.setPlmn("46000");
                                        spBean.setRsrp(yxSaoPBean.getRSRP());
                                        spBean.setDown(yxSaoPBean.getEARFCN());
                                        spBean.setCid(yxSaoPBean.getCid());
                                        spBean.setPci(Integer.parseInt(yxSaoPBean.getPci()));
                                        spBean.setPriority(0);
                                        spBean.setRsrq(0);
                                        spBean.setTac(Integer.parseInt(yxSaoPBean.getTac()));
                                        spBean.setUp(yxSaoPBean.getEARFCN());
                                    }if(yxSaoPBean.getEARFCN().equals("38400")){
                                        spBean.setBand("39");
                                        spBean.setPlmn("46000");
                                        spBean.setRsrp(yxSaoPBean.getRSRP());
                                        spBean.setDown(yxSaoPBean.getEARFCN());
                                        spBean.setCid(yxSaoPBean.getCid());
                                        spBean.setPci(Integer.parseInt(yxSaoPBean.getPci()));
                                        spBean.setPriority(0);
                                        spBean.setRsrq(0);
                                        spBean.setTac(Integer.parseInt(yxSaoPBean.getTac()));
                                        spBean.setUp(yxSaoPBean.getEARFCN());
                                    }if(yxSaoPBean.getEARFCN().equals("38544")){
                                        spBean.setBand("39");
                                        spBean.setPlmn("46000");
                                        spBean.setRsrp(yxSaoPBean.getRSRP());
                                        spBean.setDown(yxSaoPBean.getEARFCN());
                                        spBean.setCid(yxSaoPBean.getCid());
                                        spBean.setPci(Integer.parseInt(yxSaoPBean.getPci()));
                                        spBean.setPriority(0);
                                        spBean.setRsrq(0);
                                        spBean.setTac(Integer.parseInt(yxSaoPBean.getTac()));
                                        spBean.setUp(yxSaoPBean.getEARFCN());
                                    }if(yxSaoPBean.getEARFCN().equals("38950")){
                                        spBean.setBand("40");
                                        spBean.setPlmn("46000");
                                        spBean.setRsrp(yxSaoPBean.getRSRP());
                                        spBean.setDown(yxSaoPBean.getEARFCN());
                                        spBean.setCid(yxSaoPBean.getCid());
                                        spBean.setPci(Integer.parseInt(yxSaoPBean.getPci()));
                                        spBean.setPriority(0);
                                        spBean.setRsrq(0);
                                        spBean.setTac(Integer.parseInt(yxSaoPBean.getTac()));
                                        spBean.setUp(yxSaoPBean.getEARFCN());
                                    }if(yxSaoPBean.getEARFCN().equals("40936")){
                                        spBean.setBand("41");
                                        spBean.setPlmn("46000");
                                        spBean.setRsrp(yxSaoPBean.getRSRP());
                                        spBean.setDown(yxSaoPBean.getEARFCN());
                                        spBean.setCid(yxSaoPBean.getCid());
                                        spBean.setPci(Integer.parseInt(yxSaoPBean.getPci()));
                                        spBean.setPriority(0);
                                        spBean.setRsrq(0);
                                        spBean.setTac(Integer.parseInt(yxSaoPBean.getTac()));
                                        spBean.setUp(yxSaoPBean.getEARFCN());
                                    }if(yxSaoPBean.getEARFCN().equals("1300")){
                                        spBean.setBand("3");
                                        spBean.setPlmn("46000");
                                        spBean.setRsrp(yxSaoPBean.getRSRP());
                                        spBean.setDown(yxSaoPBean.getEARFCN());
                                        spBean.setCid(yxSaoPBean.getCid());
                                        spBean.setPci(Integer.parseInt(yxSaoPBean.getPci()));
                                        spBean.setPriority(0);
                                        spBean.setRsrq(0);
                                        spBean.setTac(Integer.parseInt(yxSaoPBean.getTac()));
                                        spBean.setUp(yxSaoPBean.getEARFCN());
                                    }if(yxSaoPBean.getEARFCN().equals("3683")){
                                        spBean.setBand("8");
                                        spBean.setPlmn("46000");
                                        spBean.setRsrp(yxSaoPBean.getRSRP());
                                        spBean.setDown(yxSaoPBean.getEARFCN());
                                        spBean.setCid(yxSaoPBean.getCid());
                                        spBean.setPci(Integer.parseInt(yxSaoPBean.getPci()));
                                        spBean.setPriority(0);
                                        spBean.setRsrq(0);
                                        spBean.setTac(Integer.parseInt(yxSaoPBean.getTac()));
                                        spBean.setUp(yxSaoPBean.getEARFCN());
                                    }if(yxSaoPBean.getEARFCN().equals("375")){
                                        spBean.setBand("1");
                                        spBean.setPlmn("46001");
                                        spBean.setRsrp(yxSaoPBean.getRSRP());
                                        spBean.setDown(yxSaoPBean.getEARFCN());
                                        spBean.setCid(yxSaoPBean.getCid());
                                        spBean.setPci(Integer.parseInt(yxSaoPBean.getPci()));
                                        spBean.setPriority(0);
                                        spBean.setRsrq(0);
                                        spBean.setTac(Integer.parseInt(yxSaoPBean.getTac()));
                                        spBean.setUp(yxSaoPBean.getEARFCN());
                                    }if(yxSaoPBean.getEARFCN().equals("1650")){
                                        spBean.setBand("3");
                                        spBean.setPlmn("46001");
                                        spBean.setRsrp(yxSaoPBean.getRSRP());
                                        spBean.setDown(yxSaoPBean.getEARFCN());
                                        spBean.setCid(yxSaoPBean.getCid());
                                        spBean.setPci(Integer.parseInt(yxSaoPBean.getPci()));
                                        spBean.setPriority(0);
                                        spBean.setRsrq(0);
                                        spBean.setTac(Integer.parseInt(yxSaoPBean.getTac()));
                                        spBean.setUp(yxSaoPBean.getEARFCN());
                                    }if(yxSaoPBean.getEARFCN().equals("100")){
                                        spBean.setBand("1");
                                        spBean.setPlmn("46011");
                                        spBean.setRsrp(yxSaoPBean.getRSRP());
                                        spBean.setDown(yxSaoPBean.getEARFCN());
                                        spBean.setCid(yxSaoPBean.getCid());
                                        spBean.setPci(Integer.parseInt(yxSaoPBean.getPci()));
                                        spBean.setPriority(0);
                                        spBean.setRsrq(0);
                                        spBean.setTac(Integer.parseInt(yxSaoPBean.getTac()));
                                        spBean.setUp(yxSaoPBean.getEARFCN());
                                    }if(yxSaoPBean.getEARFCN().equals("1825")){
                                        spBean.setBand("3");
                                        spBean.setPlmn("46011");
                                        spBean.setRsrp(yxSaoPBean.getRSRP());
                                        spBean.setDown(yxSaoPBean.getEARFCN());
                                        spBean.setCid("");
                                        spBean.setPci(0);
                                        spBean.setPriority(0);
                                        spBean.setRsrq(0);
                                        spBean.setTac(Integer.parseInt(yxSaoPBean.getTac()));
                                        spBean.setUp(yxSaoPBean.getEARFCN());
                                    }if(yxSaoPBean.getEARFCN().equals("1850")){
                                        spBean.setBand("3");
                                        spBean.setPlmn("46011");
                                        spBean.setRsrp(yxSaoPBean.getRSRP());
                                        spBean.setDown(yxSaoPBean.getEARFCN());
                                        spBean.setCid(yxSaoPBean.getCid());
                                        spBean.setPci(Integer.parseInt(yxSaoPBean.getPci()));
                                        spBean.setPriority(0);
                                        spBean.setRsrq(0);
                                        spBean.setTac(Integer.parseInt(yxSaoPBean.getTac()));
                                        spBean.setUp(yxSaoPBean.getEARFCN());
                                    }if(yxSaoPBean.getEARFCN().equals("2452")){
                                        spBean.setBand("5");
                                        spBean.setPlmn("46011");
                                        spBean.setRsrp(yxSaoPBean.getRSRP());
                                        spBean.setDown(yxSaoPBean.getEARFCN());
                                        spBean.setCid(yxSaoPBean.getCid());
                                        spBean.setPci(Integer.parseInt(yxSaoPBean.getPci()));
                                        spBean.setPriority(0);
                                        spBean.setRsrq(0);
                                        spBean.setTac(Integer.parseInt(yxSaoPBean.getTac()));
                                        spBean.setUp(yxSaoPBean.getEARFCN());
                                    }
                                    if(spBean.getDown()!=null&&spBean.getUp()!=null){
                                        xqck.add(spBean);
                                    }
                                }
                                MyLog.e("????????????0305", xqck.toString());
                                xqck = com.sm.android.locations.location.Utils.MyUtils.removeDd(xqck);
                                Intent intent = new Intent(context, SaoPinActivity.class);
                                intent.putExtra("type", "3");
                                startActivity(intent);
                            }
                        }
                    }
                        break;
                case 0305:{//????????????????????????
                    if(msg.obj.toString()!=null){
                        if(CommandUtils.spbuilsshow) {//????????????
                            MyLog.e("??????Bug", "????????????");
                            if (msg.getData().getString("type").contains("0305")) {
                                list = new ArrayList<>();//?????????
                                SaoPBean bean;
                                String[] strings = msg.obj.toString().split("\r\n");
                                for (String string : strings) {//??????
                                    bean = new SaoPBean();
                                    MyLog.e("0305F", string);
                                    MyLog.e("0305F", "-------------------");

                                    String[] split = string.split("\t");
                                    for (String s : split) {
                                        MyLog.e("0305E", s);
                                        if (s.contains("EARFCN:")) {
                                            if (s.contains("0305EARFCN")) {
                                                String[] split1 = s.split("0305");
                                                bean.setEARFCN(split1[1].split(":")[1]);
                                            } else {
                                                bean.setEARFCN(s.split(":")[1]);
                                            }
                                        }
                                        if (s.contains("PRI:")) {
                                            bean.setPRI(s.split(":")[1]);
                                        }
                                    }
                                    if (bean.getEARFCN() != null && bean.getPRI() != null) {
                                        list.add(bean);
                                    }
                                }
                                Log.i("0201handleMessage", "list.toString(): " + list.toString());
                            }
                            MyLog.i("jkljkl", "123456");
                        }else{
                            MyLog.e("??????Bug", "????????????");
                            if(msg.getData().getString("type").contains("0305")){
                                list=new ArrayList<>();//?????????
                                SaoPBean bean;
                                String[] strings = msg.obj.toString().split("\r\n");
                                for (String string : strings) {//??????
                                    bean= new SaoPBean();
                                    MyLog.e("0305F", string);
                                    MyLog.e("0305F", "-------------------");

                                    String[] split = string.split("\t");
                                    for (String s : split) {
                                        MyLog.e("0305E", s);
                                        if(s.contains("EARFCN:")){
                                            if(s.contains("0305EARFCN")){
                                                String[] split1 = s.split("0305");
                                                bean.setEARFCN(split1[1].split(":")[1]);
                                            }else{
                                                bean.setEARFCN(s.split(":")[1]);
                                            }
                                        }
                                        if(s.contains("PRI:")){
                                            bean.setPRI(s.split(":")[1]);
                                        }
                                    }
                                    if(bean.getEARFCN()!=null&&bean.getPRI()!=null){
                                        list.add(bean);
                                    }
                                }
                                Log.i("0201handleMessage", "list.toString(): "+list.toString());
                            }
                        }

                    }
                    break;
                }
            }
        }
    };


    private String ims="";

    //?????????????????????
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
    //????????????????????????0305
    private List<SaoPBean> list;
    private List<SaoPBeanRSRP> list0201;
    public static List<SpBean> xqck = new ArrayList<>();
    //???????????????0305
    private List<YXSaoPBean> yXList;
    //?????????????????????????????????
    public static TCPServer socketTcpServer;
    //????????????????????????
    private ArrayList<String> zmListsize=new ArrayList<>();
    public static String str;
    private Timer timer0303;
    private Timer timerlistImsi;

    public void startSocket() {
        socketTcpServer.isRun = true;
        socketTcpServer = new TCPServer(handlerMsg);
        socketTcpServer.start();
    }
    private void stopSocket() {
        socketTcpServer.close();
        socketTcpServer = null;
        MyLog.i("", "Socket?????????");
    }



    public static int zy=0;
    private Context context;
    SOSVIEW.MainPresenter presenter;//????????????
    //??????flag
    private int ViewTYPE = 1;

    //?????????id
    @BindView(R.id.titles_sos)
    TextView titles_sos;//?????? ??????
    @BindView(R.id.iv_wendu)
    ImageView iv_wendu;//????????????
    @BindView(R.id.tv_wendu)
    TextView tv_wendu;//???????????? ??????
    @BindView(R.id.iv_fengshan)
    ImageView iv_fengshan;//??????
    @BindView(R.id.iv_menusss)
    ImageView iv_menusss;//???????????????
    private boolean fengshanFlag = true;
    String tf1 = "";
    String tf2 = "";
    //???????????????
    @BindView(R.id.ll_0)
    LinearLayout ll_0;
    /*@BindView(R.id.ll_1)
    LinearLayout ll_1;*/
    @BindView(R.id.bt_0)
    ImageButton bt_0;
   /* @BindView(R.id.bt_1)
    ImageButton bt_1;*/

    @BindView(R.id.r0)
    ScrollView r0;
  /*  @BindView(R.id.r1)
    ScrollView r1;*/
    //?????????????????????
    @BindView(R.id.ll_zhenma_search)
    LinearLayout ll_zhenma_search;
    @BindView(R.id.ryIMSI_dw)
    RecyclerView ryIMSI_dw;//????????????
    @BindView(R.id.ry_zhenma_dw)
    RecyclerView ry_zhenma_dw;//????????????
    @BindView(R.id.ib_zhedie)
    ImageButton ib_zhedie;//??????????????????????????????
    @BindView(R.id.ib_zhedie_zhenma)
    ImageButton ib_zhedie_zhenma;//??????- ???????????????
    //    @BindView(R.id.line_chart_view)
    LineChartView line_chart_viewdw;//???????????????
    @BindView(R.id.slideButton1)
    SlideButton slideButton1;//??????????????????1
    @BindView(R.id.laba1dw)
    ImageView laba1dw;//????????????1
    @BindView(R.id.slideButton2)//??????????????????2
            SlideButton slideButton2;
    @BindView(R.id.laba2dw)//????????????2
            ImageView laba2dw;
    @BindView(R.id.tv_r1dw)
    TextView tv_r1dw;
    @BindView(R.id.tv_r2dw)
    TextView tv_r2dw;
    @BindView(R.id.tv_imsi1_dw)//???????????????IMSI1
            TextView tv_imsi1_dw;
    @BindView(R.id.tv_imsi2_dw)//???????????????IMSI2
            TextView tv_imsi2_dw;
    @BindView(R.id.tv_sb1_jl_dw)//??????1???????????????
            TextView tv_sb1_jl_dw;
    @BindView(R.id.sb1_j2_dw)//??????2???????????????
            TextView tv_sb2_jl_dw;
    @BindView(R.id.cbzt1_d)//??????1???????????????
            CheckBox cbzt1_d;
    @BindView(R.id.cbzt1_z)
    CheckBox cbzt1_z;
    @BindView(R.id.cbzt1_g)
    CheckBox cbzt1_g;
    @BindView(R.id.cbzt2_d)//??????2???????????????
            CheckBox cbzt2_d;
    @BindView(R.id.cbzt2_z)
    CheckBox cbzt2_z;
    @BindView(R.id.cbzt2_g)
    CheckBox cbzt2_g;
    @BindView(R.id.mysp1)
    Spinner mysp1;//??????1?????????
    @BindView(R.id.mysp2)
    Spinner mysp2;//??????2?????????
    @BindView(R.id.scrollView1dw)
    MyScrollView scrollView1dw;//?????????1
    @BindView(R.id.scrollView2dw)
    MyScrollView scrollView2dw;//?????????2
    @BindView(R.id.textViews1dw)
    TextView textViews1dw;//???????????????1
    @BindView(R.id.textViews2dw)
    TextView textViews2dw;//???????????????2
    @BindView(R.id.tv1_type_dw)
    TextView tv1_type_dw;//??????????????????????????????1???
    @BindView(R.id.tv2_type_dw)
    TextView tv2_type_dw;//??????????????????????????????2???
    @BindView(R.id.tv1_td_dw)
    TextView tv1_td_dw;//??????1 ????????????
    @BindView(R.id.tv2_td_dw)
    TextView tv2_td_dw;//??????2 ????????????
    @BindView(R.id.tv1_wifi_dw)
    TextView tv1_wifi_dw;//??????1 wifi??????????????????
    @BindView(R.id.tv2_wifi_dw)
    TextView tv2_wifi_dw;//??????2 wifi??????????????????
    @BindView(R.id.bt_start1dw)
    Button bt_start1dw;//????????????1
    @BindView(R.id.bt_start2dw)
    Button bt_start2dw;//????????????2
    @BindView(R.id.bt_stop1dw)
    Button bt_stop1dw;//????????????1
    @BindView(R.id.bt_stop2dw)
    Button bt_stop2dw;//????????????2
    @BindView(R.id.et_zhenmasearchdw)
    EditText et_zhenmasearchdw;

    @BindView(R.id.tv_searchNumdw)
    TextView tv_searchNumdw;
    private TextToSpeech textToSpeech = null;//????????????????????????
    StringBuffer stringBuffer1dw = null;
    StringBuffer stringBuffer2dw = null;
    private boolean slideButton1Flag = false;
    private boolean slideButton2Flag = true;
    ArrayAdapter<String> adapter1, adapter2;
    List<String> listsSp = new ArrayList<>();
    private String spinnerS1 = "", spinnerS2 = "";
    Message message;
    Bundle bundle;
    private static Timer timer_wendu;
    DatagramPacket dp;
    byte[] buf;
    public static boolean FENGSHANFLAG = true;
    private boolean runThread = false;
    private DLPopupWindow popupWindow;
    private List<DLPopItem> mList = new ArrayList<>();
    Dialog dialog;
    View inflate;
    public static String sbZhuangTai="????????????";
    public String sb1 = "";
    public String sb2 = "";
    private boolean SB1ZY = false;//???????????????????????????????????????
    private boolean SB2ZY = false;//???????????????????????????????????????
    List<Integer> list1quxian = null;//??????1???????????????
    List<Integer> list2quxian = null;//??????2???????????????
    private String sp1MAX1value = "";//??????1?????????1?????????
    private String sp1MAX2value = "";//??????1?????????2?????????
    private String sp2MAX1value = "";//??????2?????????1?????????
    private String sp2MAX2value = "";//??????2?????????2?????????
    private boolean sb1FirstFlag = false;//??????????????????
    private boolean sb2FirstFlag = false;
    private boolean TIMERRESTARTFLAG1 = true;  //??????????????????1  ???????????? true
    private boolean TIMERRESTARTFLAG2 = true;  //??????????????????1  ???????????? true
    private Timer timerLocation = null;//????????????imsi????????????
    RyZmAdapterdw ryZmAdapterdw;
    @SuppressLint("NewApi")
    DecimalFormat dfBaoshu = new DecimalFormat("###");
    private boolean laba1Flag = true;//??????????????????
    private boolean laba2Flag = true;

    RyZmAdapterGk.GKCallBack gkCallBack = new RyZmAdapterGk.GKCallBack() {
        @Override
        public void call(String imsi, String sb) {
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
        @SuppressLint("NewApi")
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
//                case 100:
//                    if (!TextUtils.isEmpty(SHUAIJIAN1)) {
//                        Double XS = Double.parseDouble(SHUAIJIAN1);
////                    int XS = 12;
//                        double v = (33 - (XS * 0.25)) * 0.1;
//                        double pow = Math.pow(10, v);
//                        double v1 = pow / 1000;
//                        Log.d("nzqtag", "run: " + v1);
//                        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
//                    }
//                    break;
                case 8153://?????????????????????????????????53 ??????????????????
                    DecimalFormat df2;
                    df2 = new DecimalFormat("####");
                    if (TextUtils.isEmpty(BOARDTEMPERATURE1)) {
                        break;
                    }
                    OpnUpdate.upwendu(tv_wendu, iv_wendu, BOARDTEMPERATURE1, df2, context, iv_fengshan, FENGSHANFLAG);
                    break;
//                case 300002:
//                    tv_imsi1_dw.setText("");
//                    tv_sb1_jl_dw.setText("");
//                    tv_imsi2_dw.setText("");
//                    tv_sb2_jl_dw.setText("");
//                    tv_sb2_jl_dw.setText("");
//                    break;
                case 100001://wifi??????
                        //??????????????????

                    if(!type.equals("")) {//?????????
                        mode=CommandUtils.MODE;
                        if (!mode.equals("")) {
                            if (mode.equals("1")) {
                                tv1_td_dw.setText("???????????????TDD");
                            }
                            if (mode.equals("2")) {
                                tv1_td_dw.setText("???????????????FDD");
                            }
                        }
                        mode = "";//????????????
                    }
                    DwUpdate.upwifi(msg, tv1_wifi_dw, tv1_type_dw,tv1_td_dw,setState);
                    String wifi = msg.getData().getString("msgWifi");
//                        Log.d(TAG, "handleMessa4age: " + wifi);
                    if (wifi.equals("true")) {//????????????
                        if(CommandUtils.type.equals("")){//wifi????????????
//                            if(SOSActivity.socketTcpServer!=null){
////                                if(isSbState){
//                                    stopSocket();
//                                    startSocket();
////                                }
//                            }
                        }
                    }
                    break;
            }
        }
    };
    public static String mode="";//????????????
    RyImsiAdapter ryImsiAdapter;
    List<AddPararBean> pararBeansList1 = new ArrayList<>();
    List<String> listIMSI=new ArrayList<>();//?????????????????????imsi
    String mydown2GK = "";
    String mydown1GK = "";
    String mydown11GK = "";
    String mydown22GK = "";
    Timer timerLocationshepingonglv;
   /* @BindView(R.id.ib_dc_zm)//????????????????????????
            ImageButton ib_dc_zm;
    @BindView(R.id.sos_ib_qc_sos)//?????? ????????????
            ImageButton sos_ib_qc_sos;*/
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(soslayout);

        String s = "Hello world!";
        char c = s.charAt(4); // ?????????5?????????
        System.out.println("?????????5?????????" + c);
        s = s.substring(5);// ????????????5??????????????????
        System.out.println("????????????5??????????????????" + s);



        context = this;//???????????????
        new SOSPersent(this);//????????????

        ButterKnife.bind(this);//??????ID
        MyTTS.getInstance().init(this);
        startSocket();//??????????????????



        seekBarOnchangLister();//???????????????
        setEt_search();
        stringBuffer1dw = new StringBuffer();
        stringBuffer2dw = new StringBuffer();
        SOSUtils.getPermissions(this);//????????????
        SOSUtils.setTITle(titles_sos);//????????????

        ll_0.setOnClickListener(this);
        bt_0.setOnClickListener(this);
        textViews1dw.setOnClickListener(this);
//        bt_1.setOnClickListener(this);
        iv_menusss.setOnClickListener(this);
        line_chart_viewdw = findViewById(R.id.line_chart_viewdw);
        iv_fengshan.setOnClickListener(this);//????????????
        bt_start1dw.setOnClickListener(this);//??????1????????????
        bt_start2dw.setOnClickListener(this);//??????2????????????
        bt_stop1dw.setOnClickListener(this);//??????1????????????
        bt_stop2dw.setOnClickListener(this);//??????2????????????
        ib_zhedie.setOnClickListener(this);//????????????????????????
        ib_zhedie_zhenma.setOnClickListener(this);//???????????????
        laba1dw.setOnClickListener(this);//??????1??????
        laba2dw.setOnClickListener(this);//??????2??????
        ryIMSI_dw.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        setSlidubutton();//????????????????????????
        setPinnerdata();//?????????????????????
        CheckBoxOnclickSet();//????????????????????????
        ImslList();//?????????IMSI
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



        message = new Message();
        bundle = new Bundle();
        timer_wendu = new Timer();
        buf = new byte[1024];
        dp = new DatagramPacket(buf, buf.length);
//        MainUtils.ReceiveMainWD(handler, message, bundle, timer_wendu);
//        MainUtils.ReceiveMain(handler, message, bundle, timer1, timer2, dp, buf, context, runThread);//??????????????????
        MainUtils.WifiMain(handler, message, bundle, null, null, dp, buf, context);//??????wifi??????
        AddITemMenu(0);//?????????????????????  0  ??????
//
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

        if (list2.size() > 10) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setStackFromEnd(true);//recyview?????????????????????

//                tv_searchNumdw.setText("(" + zmBeans.size() + ")");
        } else {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setStackFromEnd(false);//recyview?????????????????????
//                tv_searchNumdw.setText("(" + zmBeans.size() + ")");
        }
        //????????????
        iv_fengshan.setVisibility(View.GONE);


        //imsi?????????
        tv_imsi1_dw.setText("");
        //imsi??????
        tv_sb1_jl_dw.setText("");





        timer0303 = new Timer();
        timer0303.schedule(new TimerTask() {
            @Override
            public void run() {
                //???????????????  ?????????????????????????????????
                    if(!CommandUtils.type0303.equals("")){
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if(shePin.equals("??????")||shePin.equals("")){
                                        sbZt="?????????";
                                        ImslList0303(CommandUtils.type0303);//?????????imsi??????????????????????????????
                                    String s = CommandUtils.type0303;
                                    String f = s.substring(48);//????????? ????????????

                                    MyLog.e("ttt", f + "\r\n");
                                    String[] imsi = f.split("IMSI:");
                                                ims = imsi[1].substring(0, 15);
                                    Log.e("ttt", "IMSI??????: " + ims);

                                    String[] time = f.split("TIME:");
                                    String tim = time[1].substring(0, 10);
                                    MyLog.e("ttt", tim);

                                    String[] RSSI = f.split("RSSI:");
                                    String rssi = RSSI[1].substring(0, 5);
                                    rssi = com.sm.android.locations.location.initData.MyUtils.getNumber(rssi);
                                    MyLog.e("ttt", rssi);





                                    //???????????????
                                    list1quxian.remove(0);
                                    list1quxian.add((130-Integer.parseInt(rssi))*1000/130);
                                    setqxData(list1quxian, list2quxian);





                                    MyLog.e("123456", laba1Flag+"");
//                        //??????????????????????????????imsi
                                    if(laba1Flag){
                                        MyTTS.getInstance().speak(((130-Integer.parseInt(rssi))*1000/130)+"");
                                    }

                                    //??????????????? ?????????????????? ???????????????????????????
                                    //???????????????
                                    tv_sb1_jl_dw.setText(((130-Integer.parseInt(rssi))*1000/130)+"");
                                    if(tv_imsi1_dw.getText().toString().equals("")||!tv_imsi1_dw.getText().toString().equals(rssi)){
                                        tv_imsi1_dw.setText(ims);
                                    }//

                                }
                                CommandUtils.type0303="";
                            }
                        });
                    }else{//?????????????????????
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //imsi?????????
                                tv_imsi1_dw.setText("");
                                //imsi??????
                                tv_sb1_jl_dw.setText("");
                                ImslList2();
                            }
                        });

                    }
            }
        }, 0,2000);
    }

    @SuppressLint("NewApi")
    private void seekBarOnchangLister() {
//        seekbarnum = seekbar_sos.getProgress();


//                double log = Math.log(seekbarnum);//???????????????????????????
//                double a=Math.pow(log,seekbarnum);
//                double Ant_Power = 27 * Math.pow(Math.log(10),seekbarnum)  - 75 + 38.45;
//                double Slider = 4 * (36 - Ant_Power);


        double log = Math.log(10);
        double log1 = Math.log(i);

        double Ant_Power = 27 * (log1 / log) - 75 + 38.45;

        double Slider = 4 * (36 - Ant_Power);

        if (Slider > 255) {
            Slider = 255;
        }

        double pow1 = Math.pow(10, Ant_Power / 10) / 2000;

//                //??????
//                int sum = seekbarnum;
//                int num = 255 - sum;
//
        long a = Math.round(Ant_Power);
        String hex = Integer.toHexString((int) a);
        //?????????
        StringBuffer buffer = new StringBuffer("aaaa555515f01400000000ff");
        buffer.append(hex);
        if (hex.length() == 1) {
            buffer.append("00000");
        }
        if (hex.length() == 2) {
            buffer.append("0000");
        }
        buffer.append("00000000");
//                Log.d("TAGdayin", "onProgressChanged: " + buffer.toString());
//        SendUtils.setzySeek(1, 1, buffer.toString());
//        SendUtils.setzySeek(1, 2, buffer.toString());
////                for (int j = 0; j < 255; j++) {
////                    String zhex = Integer.toHexString(j);
////                    Log.d("zhuanhjuanseekBarProgr" +
////                            "", "onProgressChanged: " + zhex.toString() + "---" + zhex.length());
////                }
//                if (TextUtils.isEmpty(SHUAIJIAN1)) {
////                    Double XS = Double.parseDouble(SHUAIJIAN1);
//////                    int XS = 12;
////                    double v = (33 - (XS * 0.25)) * 0.1;
////                    double pow = Math.pow(10, v);
////                    double v1 = pow / 1000;
////                    Log.d("nzqtag", "run: " + v1);
//                    java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
//                    double a = 36 - 0.25 * (255 - sum);
//                    double powmun = Math.pow(10, (a + 75 - 38.45) / 27);
//
//                    Log.d("seekBarProgr" +
//                            "", "onProgressChanged: " + hex.toString() + "---????????????+" + pow);
//                    tv_sos_juli.setText("?????????????????????" + Math.rint(powmun) + "?????????????????????(" + ")");
//                } else {
//                    Double XS = Double.parseDouble(SHUAIJIAN1);
////                    int XS = 12;
//                    double v = (33 - (XS * 0.25)) * 0.1;
//                    double pow = Math.pow(10, v);
//                    double v1 = pow / 1000;
//                    Log.d("nzqtag", "run: " + v1);
//                    java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
//                    double a = 36 - 0.25 * (255 - sum);
//                    double powmun = Math.pow(10, (a + 75 - 38.45) / 27);
//
//                    Log.d("seekBarProgr" +
//                            "", "onProgressChanged: " + hex.toString() + "---????????????+" + pow);
        @SuppressLint({"NewApi", "LocalSuppress"}) DecimalFormat df = new DecimalFormat("######0.000");
//                }
        Log.d("onStopTrackingTouch", "onStopTrackingTouch: " + Ant_Power + "------" + Slider + "---");
    }


    @Override
    protected void onStop() {
        super.onStop();
        // ????????????????????????TTS????????????
        if(mTextToSpeech!=null){
            mTextToSpeech.stop();
            // ?????????????????????
            mTextToSpeech.shutdown();
        }
    }

    /**
     * ?????????????????????
     *
     * @param list1quxian
     * @param list2quxian
     */
    private void setqxData(List<Integer> list1quxian, List<Integer> list2quxian) {
        line_chart_viewdw.setShowTable(true);
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
        line_chart_viewdw.setData(datas, datas2);
        line_chart_viewdw.setBezierLine(true);
        line_chart_viewdw.setRulerYSpace(200);
        line_chart_viewdw.setStepSpace(30);

        scrollView1dw.fullScroll(ScrollView.FOCUS_DOWN);//???????????????

        scrollView1dw.post(new Runnable() {
            @Override
            public void run() {
                //???????????????
                scrollView1dw.fullScroll(ScrollView.FOCUS_DOWN);
                //???????????????
//                scrollView1dw.fullScroll(ScrollView.FOCUS_UP);
            }
        });
    }

    /**
     * ?????????????????????
     *
     * @param list1quxian
     * @param list2quxian
     */
    private void setqxDataSOS(List<Integer> list1quxian, List<Integer> list2quxian) {
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
    }

    private void CheckBoxOnclickSet() {
        cbzt1_g.setChecked(true);//?????????????????????
        //??????1
        cbzt1_d.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    zy=1;
                    cbzt1_z.setChecked(false);
                    cbzt1_g.setChecked(false);

                    if(socketTcpServer!=null){
                        if(!CommandUtils.type.equals("")){//????????????
                            try {
                                DBManagerDevice device = new DBManagerDevice(context);
                                List<DeviceBean> deviceBeans = device.getDeviceBeans();
                                if(deviceBeans.size()>0){
                                    CommandUtils.syType=1;
                                    CommandUtils.zyType=deviceBeans.get(deviceBeans.size()-1).getEt_Zyd();
                                    presenter.setZy(socketTcpServer,deviceBeans.get(deviceBeans.size()-1).getEt_Zyd());
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
//                    try {
//                        DBManagerZY dbManagerZY = new DBManagerZY(context);
//                        if (tf1.equals("TDD")) {
//                            int data = dbManagerZY.foriddata(1, 1, 1);
//                            setzy(data, 1);
//                        }
//                        if (tf1.equals("FDD")) {
//                            int data = dbManagerZY.foriddata(1, 2, 1);
//                            setzy(data, 1);
//                            Log.d("zydata", "onCheckedChanged: " + data);
//                        }
//
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
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
                    zy=2;
                    cbzt1_d.setChecked(false);
                    cbzt1_g.setChecked(false);
                    if(socketTcpServer!=null){
                        if(!CommandUtils.type.equals("")){//????????????
                            try {
                                DBManagerDevice device = new DBManagerDevice(context);
                                List<DeviceBean> deviceBeans = device.getDeviceBeans();
                                if(deviceBeans.size()>0){
                                    CommandUtils.syType=2;
                                    CommandUtils.zyType=deviceBeans.get(deviceBeans.size()-1).getEt_Zyz();
                                    presenter.setZy(socketTcpServer,deviceBeans.get(deviceBeans.size()-1).getEt_Zyz());
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
//                    try {
//                        DBManagerZY dbManagerZY = new DBManagerZY(context);
//                        if (tf1.equals("TDD")) {
//                            int data = dbManagerZY.foriddata(1, 1, 2);
//                            setzy(data, 1);
//                        }
//                        if (tf1.equals("FDD")) {
//                            int data = dbManagerZY.foriddata(1, 2, 2);
//                            setzy(data, 1);
//                        }
//
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
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
                    zy=3;
                    cbzt1_z.setChecked(false);
                    cbzt1_d.setChecked(false);
                    if(socketTcpServer!=null){
                        if(!CommandUtils.type.equals("")){//????????????
                            try {
                                DBManagerDevice device = new DBManagerDevice(context);
                                List<DeviceBean> deviceBeans = device.getDeviceBeans();
                                if(deviceBeans.size()>0){
                                    CommandUtils.syType=3;
                                    CommandUtils.zyType=deviceBeans.get(deviceBeans.size()-1).getEt_Zyg();
                                    presenter.setZy(socketTcpServer,deviceBeans.get(deviceBeans.size()-1).getEt_Zyg());
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
//                    try {
//                        DBManagerZY dbManagerZY = new DBManagerZY(context);
//                        if (tf1.equals("TDD")) {
//                            int data = dbManagerZY.foriddata(1, 1, 3);
//                            setzy(data, 1);
//                        }
//                        if (tf1.equals("FDD")) {
//                            int data = dbManagerZY.foriddata(1, 2, 3);
//                            setzy(data, 1);
//                        }
//
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
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
//                if(CommandUtils.sbZt.equals("?????????...")){
//                    ToastUtils.showToast("??????????????????");
//                    return;
//                }
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
//                    DialogUtils.DataExport(context, inflate, zmDataCallBack);
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
//            Log.d(TAG, "pinConfigBeansinit: " + pinConfigBeans);

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
//                    Log.d(TAG, "2onItemSelected: " + spinnerS2);

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }

    }

    private void setSlidubutton() {
        slideButton1.setChecked(false);//?????????????????????????????????
        slideButton2.setChecked(true);
        mysp1.setVisibility(View.VISIBLE);
        mysp2.setVisibility(View.GONE);
        slideButton1.setSmallCircleModel(parseColor("#10A146"), parseColor("#00000000"), parseColor("#10A146"), parseColor("#cccccc"));
        slideButton1.setOnCheckedListener(new SlideButton.SlideButtonOnCheckedListener() {
            @Override
            public void onCheckedChangeListener(boolean isChecked) {
                slideButton1Flag = isChecked;
                if (isChecked) {


                    slideButton1.setChecked(true);
                    slideButton2.setChecked(true);
                    mysp1.setVisibility(View.GONE);
//                    mysp2.setVisibility(View.GONE);
//


                    mysp1.setEnabled(false);



//                    mysp2.setEnabled(false);
                } else {
                    slideButton1.setChecked(false);
//                    slideButton2.setChecked(false);


                    if(CommandUtils.sbZt.equals("?????????")){
                        mysp1.setVisibility(View.GONE);
                        mysp1.setEnabled(false);
                    }else{
                        mysp1.setVisibility(View.VISIBLE);
                        mysp1.setEnabled(true);
                    }
//                    mysp2.setVisibility(View.VISIBLE);
//                    mysp2.setEnabled(true);

                }
            }
        });

        slideButton2.setSmallCircleModel(parseColor("#FF6367"), parseColor("#00000000"), parseColor("#FF6367"), parseColor("#cccccc"));
        slideButton2.setOnCheckedListener(new SlideButton.SlideButtonOnCheckedListener() {
            @Override
            public void onCheckedChangeListener(boolean isChecked) {

                slideButton2Flag = isChecked;
                if (isChecked) {
                    slideButton2.setChecked(true);
//                    slideButton1.setChecked(true);
                    mysp2.setVisibility(View.GONE);
                    mysp2.setEnabled(false);
//                    mysp1.setVisibility(View.GONE);
//                    mysp1.setEnabled(false);
                } else {
                    slideButton2.setChecked(false);
//                    slideButton1.setChecked(false);
                    mysp2.setVisibility(View.VISIBLE);
                    mysp2.setEnabled(true);
                }
            }
        });
    }

    //???????????????1?????????
    public String upStr = "";
    //??????????????? ????????????
    public void Set1StatusBar(String str) {
        if (!upStr.equals(str)) {
            stringBuffer1dw.append(str + "" + "\n");
            textViews1dw.setText(stringBuffer1dw);
            upStr = str;
            scrollView1dw.fullScroll(ScrollView.FOCUS_DOWN);//???????????????
        }
    }

    //???????????????1?????????
    public String upStrsos = "";

    public String upStrsos2 = "";

   /* //??????????????? ????????????
    private void SetSOS2StatusBar(String str) {
        if (!upStrsos2.equals(str)) {
            stringBuffer2sos.append(str + "" + "\n");
            textViews_sos2.setText(stringBuffer2sos);
            upStrsos2 = str;
            scrollView2_sos.fullScroll(ScrollView.FOCUS_DOWN);//???????????????
        }
    }*/

    //???????????????1?????????
    public String upStr2 = "";

    //??????????????? ????????????
    private void Set2StatusBar(String str) {
        if (!upStr2.equals(str)) {
            stringBuffer2dw.append(str + "" + "\n");
            textViews2dw.setText(stringBuffer2dw);
            upStr2 = str;
            scrollView2dw.fullScroll(ScrollView.FOCUS_DOWN);//???????????????
        }
    }

    private boolean zdFlagdw = false;//???????????????
    private boolean zdSearchFlagdw = false;//???????????????
    private boolean zdSearchFlagsos = false;//???????????????

    @SuppressLint("NewApi")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sos_ib_qc_sos:
                Dialog dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
                inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
                TextView tv_title = inflate.findViewById(R.id.tv_title);
                Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
                tv_title.setText("???????????????????????????????");
                bt_confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DBManagerZMGK dbManagergk = null;
                        try {//????????? ???????????????  ??????????????????
                            dbManagergk = new DBManagerZMGK(SOSActivity.this);

                            dbManagergk.deleteall();
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
                break;
           /* case R.id.ib_dc_zm:
                dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
                inflate = LayoutInflater.from(context).inflate(R.layout.dataexport_item, null);*/
//        bt_export
//        bt_clear
//        tv_location

               /* final Button bt_clear = inflate.findViewById(R.id.bt_clear);//??????????????????
                bt_cancel = inflate.findViewById(R.id.bt_cancel);//????????????
                final TextView tv_location = inflate.findViewById(R.id.tv_location);
                bt_clear.setVisibility(View.GONE);

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
                Window dialogWindow2 = dialog.getWindow();
                //??????Dialog?????????????????????
                dialogWindow2.setGravity(Gravity.CENTER);

                //?????????????????????
                WindowManager.LayoutParams lp2 = dialogWindow2.getAttributes();

//                    lp.y = 20;//??????Dialog?????????????????????
//        lp.height=1200;
//        lp.width=400;
//       ????????????????????????
                dialogWindow2.setAttributes(lp2);
                dialog.show();//???????????????*/
//                break;
            case R.id.bts_sos_mubiao1:
//                SOSUtils.mubibiao(SOSActivity.this, "1", soSxuanzemubiao);
//                Intent intent = new Intent();
//                startActivityForResult();
//                start

                break;
            case R.id.bts_sos_mubiao2:
//                SOSUtils.mubibiao(SOSActivity.this, "2", soSxuanzemubiao);
                break;
            /*case R.id.ll_0:
                if (sb1_gk.equals("?????????")) {
                    ToastUtils.showToast("??????1?????????????????????");

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
                if (sb2_gk.equals("?????????")) {
                    ToastUtils.showToast("??????2?????????????????????");

                    break;
                }
                ViewTYPE = 1;
                r0.setVisibility(View.VISIBLE);
                r1.setVisibility(View.GONE);

                bt_0.setBackground(getResources().getDrawable(R.mipmap.dwsj_bl));
                bt_1.setBackground(getResources().getDrawable(R.mipmap.ypcj_gr));

                AddITemMenu(0);//?????????????????????

                break;*/
            case R.id.bt_0:
                if (sb1_gk.equals("?????????")) {
                    ToastUtils.showToast("??????1?????????????????????");
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
                if (sb2_gk.equals("?????????")) {
                    ToastUtils.showToast("??????2?????????????????????");

                    break;
                }
                ViewTYPE = 1;
                r0.setVisibility(View.VISIBLE);
//                r1.setVisibility(View.GONE);

                bt_0.setBackground(getResources().getDrawable(R.mipmap.dwsj_bl));
//                bt_1.setBackground(getResources().getDrawable(R.mipmap.ypcj_gr));

                AddITemMenu(0);//?????????????????????
                break;


           /* case R.id.ll_1:
                if (sb1.equals("?????????")) {
                    ToastUtils.showToast("??????1?????????????????????");

                    break;
                }
                if (sb2.equals("?????????")) {
                    ToastUtils.showToast("??????2?????????????????????");
??????imsi????????????????????????

                    break;
                }
                ViewTYPE = 2;

                r0.setVisibility(View.GONE);

                r1.setVisibility(View.VISIBLE);
                bt_1.setBackground(getResources().getDrawable(R.mipmap.ypsj_bl));
                bt_0.setBackground(getResources().getDrawable(R.mipmap.dwsj_gr));

                AddITemMenu(2);//?????????????????????
                break;*/
            /*case R.id.bt_1:
                if (sb1.equals("?????????")) {
                    ToastUtils.showToast("??????1?????????????????????");

                    break;
                }
                if (sb2.equals("?????????")) {
                    ToastUtils.showToast("??????2?????????????????????");

                    break;
                }
                ViewTYPE = 2;
                r0.setVisibility(View.GONE);
                r1.setVisibility(View.VISIBLE);
                bt_1.setBackground(getResources().getDrawable(R.mipmap.ypsj_bl));
                bt_0.setBackground(getResources().getDrawable(R.mipmap.dwsj_gr));

                AddITemMenu(2);//?????????????????????
                break;*/


            case R.id.iv_fengshan:
//                presenter.fengshanSet(fengshanFlag, iv_fengshan, context);
//                saopinshow1=true;
//                presenter.spbuilsshow(context, 1, 3, tf1, tf2);//??????????????????
//                SendUtils.setbutongbu(1, 1, TFFBUTONGBU);
//                presenter.saopinjianlixiaoqu(context, 1, tf1, "1650");
//                mydown1GK="1650";
//                presenter.chongdingxiang1("1650");
//                presenter.chongdingxiangSET1();
                break;
            case R.id.iv_menusss:
                popupWindow.showAsDropDown(view, 0, 0);
                break;
            case R.id.ib_zhedie:
                if (zdFlagdw == false) {
                    line_chart_viewdw.setVisibility(View.GONE);
                    ib_zhedie.setBackground(getResources().getDrawable(R.mipmap.zheide));
                    zdFlagdw = true;
                } else if (zdFlagdw == true) {
                    line_chart_viewdw.setVisibility(View.VISIBLE);
                    ib_zhedie.setBackground(getResources().getDrawable(R.mipmap.zheidedown));
                    zdFlagdw = false;
                }
                break;
            case R.id.ib_zhedie_zhenma:
                if (zdSearchFlagdw == false) {
                    ll_zhenma_search.setVisibility(View.VISIBLE);
                    ry_zhenma_dw.setVisibility(View.VISIBLE);
                    ib_zhedie_zhenma.setBackground(getResources().getDrawable(R.mipmap.zheidedown));
                    zdSearchFlagdw = true;

                } else if (zdSearchFlagdw == true) {
                    ll_zhenma_search.setVisibility(View.GONE);
                    ry_zhenma_dw.setVisibility(View.GONE);
                    ib_zhedie_zhenma.setBackground(getResources().getDrawable(R.mipmap.zheide));
                    zdSearchFlagdw = false;
                }
                break;
            case R.id.ib_zhedie_sos:

                break;
            case R.id.textViews1dw:
//                socketTcpServer.sendPost(MyUtils.getSocketHeader(MyUtils.getToHexString("0108", "")));//??????????????????
                break;

            case R.id.bt_start1dw:
//                if (slideButton1Flag) {//??????????????????
////                    ToastUtils.showToast("??????1??????????????????");

//                } else {//??????????????????
////                    ToastUtils.showToast("??????????????????");
//                    presenter.startSD(1, tf1, context, spinnerS1, sb1);
//                }
                if(type.equals("")){
                    ToastUtils.showToast("???????????????");
                    return;
                }
                if(!slideButton1Flag){//??????????????????
                    if(CommandUtils.sbZt.equals("")){
                        ToastUtils.showToast("???????????????");
                    }else{//?????????????????????????????????????????????
                        if(CommandUtils.sbZt.equals("?????????")){
                            ToastUtils.showToast("???????????????");
                            return;
                        }
                        if(CommandUtils.sbZt.equals("?????????")){
                            ToastUtils.showToast("???????????????");
                            return;
                        }
                        if(CommandUtils.sbZt.equals("?????????")){
                            ToastUtils.showToast("????????????????????????");
                            return;
                        }if(!CommandUtils.sbZt.equals("??????")){
                            return;
                        }
                        //????????????
                        presenter.startSD(1, CommandUtils.type, context, spinnerS1, SOSActivity.sbZhuangTai,tv1_wifi_dw.getText().toString(),socketTcpServer,setState);
                    }
                }else{//????????????
                    if(CommandUtils.sbZt.equals("")){
                        ToastUtils.showToast("???????????????");
                    }else{
//                        if(CommandUtils.RF.equals("")){
//                            return;
//                        }
//                        if(Integer.parseInt(CommandUtils.RF)!=0){
//                            return;
//                        }
                        if(CommandUtils.sbZt.equals("?????????")){
                            ToastUtils.showToast("???????????????");
                            return;
                        }
                        if(CommandUtils.sbZt.equals("?????????")){
                            ToastUtils.showToast("??????????????????");
                            return;
                        }
                        if(CommandUtils.sbZt.equals("?????????")){
                            ToastUtils.showToast("????????????????????????");
                            return;
                        }
                        if(!CommandUtils.MODE.equals("")){
                            if(Integer.parseInt(CommandUtils.MODE)==1){
                                tf1="TDD";

                            }
                            if(Integer.parseInt(CommandUtils.MODE)==2){
                                tf1="FDD";
                            }
                             zidongsaopinjianlixiaoqu(1);//????????????
                        }

                    }
                }
                break;
            case R.id.bt_start2dw:
//                if (slideButton2Flag) {//??????????????????
////                    ToastUtils.showToast("??????2??????????????????");
//                    zidongsaopinjianlixiaoqu(2);
//                } else {//??????????????????
//                    presenter.startSD(2, CommandUtils.type, context, spinnerS2, sb2,tv1_td_dw.getText().toString(),socketTcpServer);
//
//                }
//                break;

            case R.id.bts_start_sos:
//                ToastUtils.showToast("??????????????????");
                saopinGKFalg1 = false;
                saopinGKFalg2 = false;
//
                break;
            case R.id.bts_stop_sos:
                presenter.stopGK(context);
                break;
            case R.id.bt_stop1dw:
                if(type.equals("")){
                    ToastUtils.showToast("???????????????");
                    return;
                }
                if(CommandUtils.sbZt.equals("")){
                    ToastUtils.showToast("???????????????");
                }else{
                    if(CommandUtils.RF.equals("")){
                        if(CommandUtils.sbZt.equals("?????????")){
                            ToastUtils.showToast("???????????????");
                            return;
                        }
                        presenter.stopdw(1, context, sb1,socketTcpServer,mysp1,tv_r1dw,setState);
                    }else{
                        if(CommandUtils.RF.equals("1")){//??????
                            presenter.stopdw(1, context, sb1,socketTcpServer,mysp1,tv_r1dw,setState);
                        }else{
                            if(CommandUtils.sbZt.equals("?????????")){
                                ToastUtils.showToast("???????????????");
                                return;
                            }if(CommandUtils.sbZt.equals("?????????")){
                                ToastUtils.showToast("???????????????");
                                return;
                            }
                            ToastUtils.showToast("??????????????????");
                        }
                    }
                }
                break;
            case R.id.bt_stop2dw:
                sb1FirstFlag = false;
//                presenter.stopdw(2, context, sb2,null);
                break;
            case R.id.laba1dw:
                presenter.setlaba(context, laba1dw, 1, laba1Flag);
                break;
            case R.id.laba2dw:
                presenter.setlaba(context, laba2dw, 2, laba2Flag);
                break;

            case R.id.laba1_sos:
                break;
            case R.id.laba2_sos:

                break;
        }
    }
    private boolean saopinGKFalg1 = false;
    private boolean saopinGKFalg2 = false;
    private int SOSType = 1;
    public String sb1_gk = "";
    public String sb2_gk = "";

    @Override
    public void fengshanUp(boolean b) {
        fengshanFlag = b;
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

    @Override
    public void setPresenter(SOSVIEW.MainPresenter presenter) {
        this.presenter = presenter;
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
                //list???imsi?????????????????????
                if (listImsiListTrue.size() > 0) {
                    for (int i = 1; i < listImsiListTrue.size() + 1; i++) {
                        list1size.add(i);
                    }
                }
                pararBeansList1 = listImsiListTrue;
                ryImsiAdapter = new RyImsiAdapter(context, listImsiListTrue, list1size, config, tv_imsi1_dw, tv_imsi2_dw);//list???imsi?????????????????????
                ryIMSI_dw.setAdapter(ryImsiAdapter);
                ryImsiAdapter.notifyDataSetChanged();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //imsi??????
    private void ImslList2() {
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
                //list???imsi?????????????????????
                if (listImsiListTrue.size() > 0) {
                    for (int i = 1; i < listImsiListTrue.size() + 1; i++) {
                        list1size.add(i);
                    }
                }
                pararBeansList1 = listImsiListTrue;
                ryImsiAdapter = new RyImsiAdapter(context, listImsiListTrue, list1size, config, tv_imsi1_dw, tv_imsi2_dw);//list???imsi?????????????????????
                ryIMSI_dw.setAdapter(ryImsiAdapter);
                ryImsiAdapter.notifyDataSetChanged();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //imsi??????
    private void ImslList0303(String msg) {
        MyLog.i("?????????msg", msg+"\r\n");
        List<AddPararBean> dataAll = null;//??????IMSI???????????????
        List<AddPararBean> listImsiListTrue = null;
        try {
            DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(this);
            dataAll = dbManagerAddParam.getDataAll();
            listImsiListTrue = new ArrayList<>();
            if (dataAll.size() > 0) {
                for (int i = 0; i < dataAll.size(); i++) {
                    if (dataAll.get(i).isCheckbox() == true) {//?????????????????????
                        Log.i("?????????ImslList0303", "ImslList0303: "+dataAll.get(i).getImsi());

                        if(msg.contains(dataAll.get(i).getImsi())){
                            dataAll.get(i).setSb("1");
                        }else{
                            dataAll.get(i).setSb("");
                        }
                        listImsiListTrue.add(dataAll.get(i));
                    }
                }
                Log.d("aalistImsiListTrue", "ImslList: " + listImsiListTrue.toString());
                List<Integer> list1size = new ArrayList<>();
                //list???imsi?????????????????????
                if (listImsiListTrue.size() > 0) {
                    for (int i = 1; i < listImsiListTrue.size() + 1; i++) {
                        list1size.add(i);
                    }
                }
                pararBeansList1 = listImsiListTrue;
                ryImsiAdapter = new RyImsiAdapter(context, listImsiListTrue, list1size, config, tv_imsi1_dw, tv_imsi2_dw);//list???imsi?????????????????????
                ryIMSI_dw.setAdapter(ryImsiAdapter);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//imsi??????
    private void ImslListData(List<String> msg) {
        List<AddPararBean> dataAll = null;//??????IMSI???????????????
        List<AddPararBean> listImsiListTrue = null;
        try {
            DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(this);
            dataAll = dbManagerAddParam.getDataAll();
            listImsiListTrue = new ArrayList<>();
            if (dataAll.size() > 0) {
                for (int i = 0; i < dataAll.size(); i++) {//5
                    if (dataAll.get(i).isCheckbox() == true) {//?????????????????????
                        Log.i("??????8???", "dataAll.get(i).getImsi(): "+dataAll.get(i).getImsi());
                        for (String s : msg) {
                            Log.i("??????8???", "msg: "+s);
                        }
                        if(msg.contains(dataAll.get(i).getImsi())){
                            dataAll.get(i).setSb("1");
                        }else{
                            dataAll.get(i).setSb("");
                        }
                        listImsiListTrue.add(dataAll.get(i));
                    }
                }
                Log.d("??????8???", "listImsiListTrue: " + listImsiListTrue.toString());
                List<Integer> list1size = new ArrayList<>();
                //list???imsi?????????????????????
                if (listImsiListTrue.size() > 0) {
                    for (int i = 1; i < listImsiListTrue.size() + 1; i++) {
                        list1size.add(i);
                    }
                }
                pararBeansList1 = listImsiListTrue;
                ryImsiAdapter = new RyImsiAdapter(context, listImsiListTrue, list1size, config, tv_imsi1_dw, tv_imsi2_dw);//list???imsi?????????????????????
                ryIMSI_dw.setAdapter(ryImsiAdapter);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    RyImsiAdapter.IDialogPinConfig config = new RyImsiAdapter.IDialogPinConfig() {
        @Override
        public void call(final String imsi, String sb) {
//            if (sb.equals("1")) {
//
                //????????????????????????Imsi????????????IMsi????????????


                MyLog.e("configMMM", CommandUtils.sbZt);
                if(type.equals("")){
                    ToastUtils.showToast("???????????????");
                    return;
                }
                if(CommandUtils.sbZt.equals("")){
                    ToastUtils.showToast("???????????????");
                    return;
                }
                if(CommandUtils.sbZt.equals("?????????")){
                    ToastUtils.showToast("?????????");
                    return;
                }
                if(!CommandUtils.sbZt.equals("?????????")){
                    return;
                }

                String dingWei="";
                if(!ims.equals("")){//????????????????????????imsi??????

                    if(ims.startsWith("46000")||ims.startsWith("46002")){
                        dingWei="??????";
                    }else if(ims.startsWith("46001")){//??????
                        dingWei="??????";
                    }else if(ims.startsWith("46011")){//??????
                        dingWei="??????";
                    }else{
                        dingWei="";
                        //??????????????????????????????
                    }
                }
                String qieHuan="";
                if(!imsi.equals("")){//????????????????????????imsi??????
                    if(imsi.startsWith("46000")||imsi.startsWith("46002")){
                        qieHuan="??????";
                    }else if(imsi.startsWith("46001")){//??????
                        qieHuan="??????";
                    }else if(imsi.startsWith("46011")){//??????
                        qieHuan="??????";
                    }else{
                        qieHuan="";
                        //??????????????????????????????
                    }
                }

                SOSActivity.this.imsi=imsi;
                if(dingWei.equals(qieHuan)&&!dingWei.equals("")&&!qieHuan.equals("")){
                    //????????????
                }else{
                    //?????????
                    return;
                }
                dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
                inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
                TextView tv_title = inflate.findViewById(R.id.tv_title);
                tv_title.setText("???????????????" + imsi + "????");
                Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
                bt_confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Set1StatusBar("????????????IMSI"+imsi);
//                        socketTcpServer.sendPost(MyUtils.getSocketHeader(MyUtils.getToHexString("0110","BLACKLIST:000000000000000")));//???????????????
                        CommandUtils.dwei="????????????";
                        socketTcpServer.sendPost(CommandUtils.setLocationImsI(imsi));//0136??????????????????IMSI
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
//            }
//            } else if (sb.equals("2")) {
//
//                dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
//                inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
//                TextView tv_title = inflate.findViewById(R.id.tv_title);
//                tv_title.setText("???????????????" + imsi + "????");
//                Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
//                bt_confirm.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        new Thread(new Runnable() {
//                            @Override
//                            public void run() {
//                                String sa = "";
//                                if (tf2.equals("TDD")) {
//                                    sa = "01";
//                                }
//                                if (tf2.equals("FDD")) {
//                                    sa = "00";
//                                }
//                                DatagramSocket socket = null;
//                                InetAddress address = null;//????????????ip??????
////                                        Log.e("nzq", "run: nzqsend");
////                                        Log.d("nzqsendimsi", "run: " + sendListBlack.get(0).getImsi());
////                                        String location = location(sendListBlack.get(0).getImsi(), "04", sa);
////                                        Log.d(TAG, "run: " + location);
//                                byte[] outputData = MainUtilsThread.hexStringToByteArray(location(imsi, "04", sa, context, 2));
//                                try {
//                                    socket = new DatagramSocket();
//                                    address = InetAddress.getByName(IP2);
//                                } catch (UnknownHostException e) {
//                                    e.printStackTrace();
//                                } catch (SocketException e) {
//                                    e.printStackTrace();
//                                }
//                                ;
//                                DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, address, 3345);
////                        DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, reIP, Integer.parseInt(et_port.getText().toString()));
//                                Log.e("nzqsendstart1", "run: sendsocket?????????" + outputPacket.getPort() + "Ip??????:" + outputPacket.getAddress().toString() + "??????:" + outputPacket.getData());
//                                try {
////                                    sb2locationgFlag = true;
////                                    socket.send(outputPacket);
//                                    DS.send(outputPacket);
////
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
////                                LocationFlag2 = true;
//                                long l = System.currentTimeMillis();
////
//                            }
//                        }).start();
//                        dialog.dismiss();
//                        dialog.cancel();
//                    }
//                });
//                Button bt_cancel = inflate.findViewById(R.id.bt_cancel);
//                bt_cancel.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.dismiss();
//                        dialog.cancel();
//                    }
//                });
//                dialog.setCanceledOnTouchOutside(false);
//                dialog.setContentView(inflate);
//                //????????????Activity???????????????
//                Window dialogWindow = dialog.getWindow();
//                //??????Dialog?????????????????????
//                dialogWindow.setGravity(Gravity.CENTER);
//                //?????????????????????
//                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
////                           lp.y = 20;//??????Dialog?????????????????????
////                          ????????????????????????
//                dialogWindow.setAttributes(lp);
//                dialog.show();//???????????????
//
//
//            }
//            else {
//                ToastUtils.showToast("?????????");
//
//            }
        }
    };

    public static boolean isSbState=true;
    public static String sbState="";//???????????????????????????????????????
    @Override
    protected void onPause() {
        super.onPause();
//        //????????????????????????????????????
//        sbState=CommandUtils.sbZt;
        isSbState=false;

        //???????????????
        laba1Flag=false;
    }
    @Override
    protected void onResume() {
        super.onResume();
        isSbState=true;
        //????????????
        laba1Flag=true;
        laba1dw.setBackground(context.getResources().getDrawable(R.mipmap.laba_green));


        if(zmListsize.size()>0){//???????????????????????????
            tv_searchNumdw.setText("(" + zmListsize.size() + ")");
        }
        ImslList();
        setPinnerdata();//?????????????????????
    }

    Timer timerState;//??????????????????
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImslList();
        setPinnerdata();//?????????????????????
        if (requestCode == 1000 && resultCode == 1001) //&& resultCode == 1001
        {
            ToastUtils.showToast("ok");
            List<AddPararBean> list1 = new ArrayList<>();// ??????1
            List<AddPararBean> list2 = new ArrayList<>();// ??????2
            String sb1down = "";
            String sb2down = "";
            Log.d("aaaweqe", "onActivityResult: " + ZMBEANGKTONGJILISTCALL);
            for (int j = 0; j < ZMBEANGKTONGJILISTCALL.size(); j++) {
                if (ZMBEANGKTONGJILISTCALL.get(j).getSb().equals("1") && ZMBEANGKTONGJILISTCALL.get(j).isCheck() == true) {
                    AddPararBean addPararBean = new AddPararBean();
                    addPararBean.setImsi(ZMBEANGKTONGJILISTCALL.get(j).getImsi());
                    list1.add(addPararBean);
                    sb1down = ZMBEANGKTONGJILISTCALL.get(j).getDown();
                }
                if (ZMBEANGKTONGJILISTCALL.get(j).getSb().equals("2") && ZMBEANGKTONGJILISTCALL.get(j).isCheck() == true) {
                    AddPararBean addPararBean = new AddPararBean();
                    addPararBean.setImsi(ZMBEANGKTONGJILISTCALL.get(j).getImsi());
                    list2.add(addPararBean);
                    sb2down = ZMBEANGKTONGJILISTCALL.get(j).getDown();
                }
            }

        }
    }

    /**
     * ????????????imsi?????????
     *
     * @param msg
     */
    List<States> listStates = new ArrayList<>();// ???????????????????????????

    private void sbImsiType(Message msg) {
        String imsi = msg.getData().getString("imsi");
        String sb = msg.getData().getString("sb");
        String zb = msg.getData().getString("zb");
        String datetime = msg.getData().getString("datetime");
        String time = msg.getData().getString("time");
        Log.d("TAG", "handl?????????: " + imsi + "---" + sb + "zb--" + "datatime--" + datetime + "time--" + time);
        States states = new States();
        states.setImsi(imsi);
        states.setSb(sb);
        states.setZb(zb + "");
        states.setDatatime(datetime);
        states.setTime(time);
        listStates.add(states);

        Log.d("statessbImsiType", "statessbImsiType: " + states.toString());
        //?????????????????????
//        if (timer == null) {
//            timer = new Timer();
//            //schedule??????????????????????????????????????????
//            timer.schedule(new TimerTask() {
//
//                //run?????????????????????????????????????????????
//                @Override
//                public void run() {
//
//                    Message message = new Message();
//                    handler.sendMessage(message);
//                    message.what = 300001;
////                    Log.d(TAG, "handlerrun: " + 1);
////                    Log.d(TAG, "handlerrun: " + 1);
//                }
//            }, 0, 11000);//IMSI
//        } else {
//            Log.d(TAG, "ahandlerrun: " + 1);

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
//        }
    }

    private boolean saopinshow1 = false;  //???????????? ???????????????  ???????????????????????????
    private boolean saopinshow2 = false;

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

    private int SAOPIN = 1;//?????????type??????1
    private int SAOPIN2 = 1;//?????????type??????1
    private SpBean spBean1 = new SpBean();
    private SpBean spBean2 = new SpBean();
    private SpBean spBean154 = new SpBean();
    private SpBean spBean254 = new SpBean();
    private boolean phoneFlag1 = true;// ??????????????????
    private boolean phoneFlag2 = true;// ??????????????????

    CallBackSetState setState=new CallBackSetState() {
        @Override
        public void showStateBar(String title) {
                Set1StatusBar(title);
        }
    };
    SaoPinCallback saoPinCallback = new SaoPinCallback() {//??????????????????
        @Override
        public void sucess(int sb, int i) {
            Log.d("saoPinCallback", "sucess: ??????" + sb + "i===" + i+saopinshow1);
            SAOPIN = i;
            SAOPIN2 = i;
            if (sb == 1) {
                if (saopinshow1) {//????????????
                    presenter.spbuilsshow(context, sb, i, tf1, tf2,setState);//??????????????????
                } else {//?????????
                    sp1MAX1value = "";
                    sp1MAX2value = "";
                    presenter.spbuils(context, sb, i, tf1, tf2,setState);//??????????????????
                    phoneFlag1 = false;
                }

            }
            if (sb == 2) {
                if (saopinshow2) {//?????????????????????
                    presenter.spbuilsshow(context, sb, i, tf1, tf2,setState);//????????????

                } else {
                    sp2MAX1value = "";
                    sp2MAX2value = "";
                    presenter.spbuils(context, sb, i, tf1, tf2,setState);//??????????????????
                    phoneFlag2 = false;
                }

            }

        }

        @Override
        public void sucessphone(int sb, String down, SpBean spBean, boolean phonesp) {
            Log.d("baqsa", "sucessphone: " + sb + "--" + down);
            if (sb == 1) {
                if(socketTcpServer==null){
                    ToastUtils.showToast("??????????????????????????????");
                    return;
                }
//                phone1sp = phonesp;
                sp1MAX1value = down;
                spBean1 = spBean;

                if (MainUtils2.YY(spBean1.getPlmn()).equals("??????")) {
                    SAOPIN = 1;
                    Log.d("aaaplmnsucessphone", "sucessphone: +");
                    if (tf1.equals("TDD")) {
                        //1 ??????1  slideButton1Flag??????????????????  3.???????????? 4.????????????
                        presenter.setStartYy(socketTcpServer,1,slideButton1Flag,CommandUtils.sbZt,sp1MAX1value, context, tf1, true,setState);
                    } else {
//                        String titles = "";
//                        if (tf1.equals("TDD")) {
//                            titles = "FDD";
//                            MainUtils.start1SNF(IP1, Constant.SNFFDD);
//                        }
//                        if (tf1.equals("FDD")) {
//                            titles = "TDD";
//                            MainUtils.start1SNF(IP1, Constant.SNFTDD);
//                        }
//                        MainUtils.Qiehuanzs(titles, IP1);presenter.setStartYy(1,slideButton1Flag,CommandUtils.sbZt,sp1MAX1value, context, tf1, true);
                        presenter.setStartYy(socketTcpServer,1,slideButton1Flag,CommandUtils.sbZt,sp1MAX1value, context, tf1, true,setState);

                        phoneFlag1 = true;
                    }
                } else if (spBean.getBand().equals("1") || spBean.getBand().equals("3") || spBean.getBand().equals("5") || spBean.getBand().equals("8") || MainUtils2.YY(spBean1.getPlmn()).equals("??????")) {//??????FDD
                    SAOPIN = 2;
                    if (tf1.equals("FDD")) {
                        presenter.setStartYy(socketTcpServer,1,slideButton1Flag,CommandUtils.sbZt,sp1MAX1value, context, tf1, true,setState);
//
                    } else {

//                        String titles = "";
//                        if (tf1.equals("TDD")) {
//                            titles = "FDD";
//                            MainUtils.start1SNF(IP1, Constant.SNFFDD);
//                        }
//                        if (tf1.equals("FDD")) {
//                            titles = "TDD";
//                            MainUtils.start1SNF(IP1, Constant.SNFTDD);
//                        }
//                        MainUtils.Qiehuanzs(titles, IP1);presenter.setStartYy(1,slideButton1Flag,CommandUtils.sbZt,sp1MAX1value, context, tf1, true);
                        presenter.setStartYy(socketTcpServer,1,slideButton1Flag,CommandUtils.sbZt,sp1MAX1value, context, tf1, true,setState);

                        phoneFlag1 = true;
                    }
                } else if (MainUtils2.YY(spBean1.getDown()).equals("??????")) {
                    SAOPIN = 3;
                    if (tf1.equals("FDD")) {
                        presenter.setStartYy(socketTcpServer,1,slideButton1Flag,CommandUtils.sbZt,sp1MAX1value, context, tf1, true,setState);



                    } else {
//                        String titles = "";
//                        if (tf1.equals("TDD")) {
//                            titles = "FDD";
//                            MainUtils.start1SNF(IP1, Constant.SNFFDD);
//                        }
//                        if (tf1.equals("FDD")) {
//                            titles = "TDD";
//                            MainUtils.start1SNF(IP1, Constant.SNFTDD);
//                        }
//                        MainUtils.Qiehuanzs(titles, IP1);
                        presenter.setStartYy(socketTcpServer,1,slideButton1Flag,CommandUtils.sbZt,sp1MAX1value, context, tf1, true,setState);

                        phoneFlag1 = true;
                    }
                } else if (MainUtils2.YY(spBean1.getDown()).equals("??????")) {
                    SAOPIN = 4;
                    if (tf1.equals("FDD")) {
                        presenter.setStartYy(socketTcpServer,1,slideButton1Flag,CommandUtils.sbZt,sp1MAX1value, context, tf1, true,setState);



                    } else {

//                        String titles = "";
//                        if (tf1.equals("TDD")) {
//                            titles = "FDD";
//                            MainUtils.start1SNF(IP1, Constant.SNFFDD);
//                        }
//                        if (tf1.equals("FDD")) {
//                            titles = "TDD";
//                            MainUtils.start1SNF(IP1, Constant.SNFTDD);
//                        }
//                        MainUtils.Qiehuanzs(titles, IP1);
                        presenter.setStartYy(socketTcpServer,1,slideButton1Flag,CommandUtils.sbZt,sp1MAX1value, context, tf1, true,setState);
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

                        presenter.setStart(2, slideButton1Flag, 0, sb1, sb2, sp1MAX1value, sp2MAX1value, context, tf1, tf2, 1, true,setState);//restart   1???????????? ?????????,,, 0????????????????????????


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

                        presenter.setStart(2, slideButton1Flag, 0, sb1, sb2, sp1MAX1value, sp2MAX1value, context, tf1, tf2, 1, true,setState);//restart   1???????????? ?????????,,, 0????????????????????????


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

                        presenter.setStart(2, slideButton1Flag, 0, sb1, sb2, sp1MAX1value, sp2MAX1value, context, tf1, tf2, 1, true,setState);//restart   1???????????? ?????????,,, 0????????????????????????


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
                    SAOPIN2 = 4;
                    if (tf2.equals("FDD")) {

                        presenter.setStart(2, slideButton1Flag, 0, sb1, sb2, sp1MAX1value, sp2MAX1value, context, tf1, tf2, 1, true,setState);//restart   1???????????? ?????????,,, 0????????????????????????


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
//            Log.d(TAG, "sucessphoneShow: ");
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

    //    /**
//     * ????????????????????????
//     *
//     * @param device
//     */
//    @Override
//    public void zidongsaopinjianlixiaoqu(int device) {
//        if (device == 1) {
//            saopinshow1 = false;
//            DialogUtils.SaoPinDialog(device, context, inflate, saoPinCallback, tf1, true);
//        }
//        if (device == 2) {
//            saopinshow2 = false;
//            DialogUtils.SaoPinDialog(device, context, inflate, saoPinCallback, tf2, true);
//        }
//
//    }
    public void saopinList(View view) {
        if(type.equals("")){
            ToastUtils.showToast("???????????????");
            return;
        }
        if(CommandUtils.sbZt.equals("?????????")){
             ToastUtils.showToast("???????????????");
             return;
        }if(CommandUtils.sbZt.equals("?????????")){
             ToastUtils.showToast("???????????????");
             return;
        }if(CommandUtils.sbZt.equals("?????????")){
             ToastUtils.showToast("?????????");
             return;
        }
        if(!CommandUtils.sbZt.equals("??????")){
            ToastUtils.showToast("???????????????");
            return;
        }
        saopinshow1 = true;
        if(!CommandUtils.MODE.equals("")){
            if(Integer.parseInt(CommandUtils.MODE)==1){
                tf1="TDD";

            }
            if(Integer.parseInt(CommandUtils.MODE)==2){
                tf1="FDD";
            }
        }

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
                //??????????????????imsi????????????????????? ???????????????????????????????????????imsi????????????
                String str = editable.toString();
                if (!TextUtils.isEmpty(str) && str.length() > 0) {
                    DBManagerZM dbManagerZM = null;
                    try {
                        dbManagerZM = new DBManagerZM(context);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    //?????????????????????imsi??????
                    List<ZmBean> zmBeanss;
                    List<ZmBean> zmBeans = new ArrayList<>();
                    try {
                        zmBeanss = dbManagerZM.getDataAll();
                        if(zmBeanss.size()>0){
                            for (int i = 0; i < zmBeanss.size(); i++) {
//                                if (zmBeanss.get(i).getMaintype().equals("0")) {
                                    zmBeans.add(zmBeanss.get(i));
//                                }
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
                            ryZmAdapterdw = new RyZmAdapterdw(SOSActivity.this, zmBeanscontains, listsize);//list???imsi?????????????????????
                            ry_zhenma_dw.setAdapter(ryZmAdapterdw);
                        } else {
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                            linearLayoutManager.setStackFromEnd(false);//recyview?????????????????????
                            ry_zhenma_dw.setLayoutManager(linearLayoutManager);
                            tv_searchNumdw.setText("(" + listsize.size() + ")");
                            ryZmAdapterdw = new RyZmAdapterdw(SOSActivity.this, zmBeanscontains, listsize);//list???imsi?????????????????????
                            ry_zhenma_dw.setAdapter(ryZmAdapterdw);
                        }
                    }
                } else {

                }

            }
        });
    }


    private void setRySOS(String str) {
        DBManagerZMGK dbManagerZM = null;
        try {
            dbManagerZM = new DBManagerZMGK(SOSActivity.this);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        List<ZmBeanGK> zmBeanss = null;
        try {
            zmBeanss = dbManagerZM.getDataAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        List<ZmBeanGK> zmBeans = new ArrayList<>();
        for (int j = 0; j < zmBeanss.size(); j++) {
            if (zmBeanss.get(j).getMaintype().equals("0")) {
                zmBeans.add(zmBeanss.get(j));
            }
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
        //????????????

        List<ZmBeanGKTongji> zmBeanscontains = new ArrayList<>();
        if (zmBeans != null) {
            for (int i = 0; i < list2.size(); i++) {
                if (list2.get(i).getImsi().contains(str)) {
                    zmBeanscontains.add(list2.get(i));
                }

            }
        } else {
            return;
        }
        List<Integer> listsize = new ArrayList<>();
        for (int i = 0; i < zmBeanscontains.size(); i++) {
            listsize.add(i + 1);

        }
        if (zmBeanscontains.size() > 10) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setStackFromEnd(true);//recyview?????????????????????
        } else {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setStackFromEnd(false);//recyview?????????????????????
        }
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
    public void gkqiehuan(String tf, int device) {
        Log.d("?????????", "gkqiehuan: " + tf + "---" + device);
        if (device == 1) {
            String titles = "";
            if (tf.equals("TDD")) {
                titles = "FDD";
//                MainUtils.start1SNF(IP1, Constant.SNFFDD);
            }
            if (tf.equals("FDD")) {
                titles = "TDD";
//                MainUtils.start1SNF(IP1, Constant.SNFTDD);
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

    /**
     * ????????????
     */
    @Override
    public void gkstop() {
        saopinGKFalg1 = false;
        saopinGKFalg2 = false;
    }

    List<ZmBeanGKTongji> listgk = new ArrayList<>();//???????????????????????? ,???????????????????????????

    @Override
    public void gkList(List<ZmBeanGKTongji> list) {
        listgk = list;
    }

    @Override
    public void zhuanhuandw(int device) {
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
//                    socket.send(outputPacket);
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
//                    socket.send(outputPacket);
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

    @Override
    public void showStateBar(String title) {
        Set1StatusBar(title);
    }

    interface SOSxuanzemubiao {
        void Callback(List<ZmBeanGKTongji> list, String device);
    }

    SOSxuanzemubiao soSxuanzemubiao = new SOSxuanzemubiao() {
        @Override
        public void Callback(List<ZmBeanGKTongji> list, String device) {
            Log.d("soSxuanzemubiao", "?????????:" + device + "?????????Callback: " + list.toString());
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        MyLog.e("", "start Destroy~~~");
//        if(socketTcpServer!=null){
//            socketTcpServer.interrupt();
//        }
//        stopSocket();
//        timerCancel();
//        MyTTS.getInstance().release();
//        Log.i("SOSffff", "onDestroy: ");
    }




    private void timerCancel() {
        if(timerlistImsi!=null){
            timerlistImsi.cancel();
        }if(timer0303!=null){
            timer0303.cancel();
        }if(timer_wendu!=null){
            timer_wendu.cancel();
        }if(timerLocation!=null) {
            timerLocation.cancel();
        }
    }
}
