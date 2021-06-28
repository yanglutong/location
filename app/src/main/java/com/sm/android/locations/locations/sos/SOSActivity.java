package com.sm.android.locations.locations.sos;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.icu.text.DecimalFormat;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sm.android.locations.locations.Activity.AddParam.AddParamActivity;
import com.sm.android.locations.locations.Activity.AddParam.ParamActivity;
import com.sm.android.locations.locations.Activity.AddParam.ParamWhiteActivity;
import com.sm.android.locations.locations.Activity.Communit.CommunitViewPagerActivity;
import com.sm.android.locations.locations.Activity.Device.DeviceInfoActivity;
import com.sm.android.locations.locations.Activity.Main.Adapter.RyImsiAdapter;
import com.sm.android.locations.locations.Activity.Main.Adapter.RyZmAdapterGk;
import com.sm.android.locations.locations.Activity.Main.Adapter.RyZmAdapterdw;
import com.sm.android.locations.locations.Activity.Main.IT.SaoPinCallback;
import com.sm.android.locations.locations.Activity.Main.Objects.States;
import com.sm.android.locations.locations.Activity.Main.View.SlideButton;
import com.sm.android.locations.locations.Activity.PinConfig.PinConfigViewPagerActivity;
import com.sm.android.locations.locations.Activity.SaoPin.SaoPinActivity.SaoPinActivity;
import com.sm.android.locations.locations.Activity.SaoPin.SaopinList.SaoPinSetingActivity;
import com.sm.android.locations.locations.Constant.Constant;
import com.sm.android.locations.locations.Lunxun.SaopinList.LunxunSetingActivity;
import com.sm.android.locations.locations.R;
import com.sm.android.locations.locations.Utils.DialogUtils;
import com.sm.android.locations.locations.Utils.MainUtils.AddMenuUtils;
import com.sm.android.locations.locations.Utils.MainUtils.MainUtils;
import com.sm.android.locations.locations.Utils.MainUtils.MainUtils2;
import com.sm.android.locations.locations.Utils.MainUtils.MainUtilsThread;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.AddPararBean;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.AddPararBeanWhite;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.PararBean;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.PinConfigBean;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.SpBean;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.ZmBean;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.ZmBeanGK;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.ZmBeanGKTongji;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.ZmBeanlinshi;
import com.sm.android.locations.locations.Utils.OrmSqlLite.DBManagerAddParam;
import com.sm.android.locations.locations.Utils.OrmSqlLite.DBManagerPinConfig;
import com.sm.android.locations.locations.Utils.OrmSqlLite.DBManagerZM;
import com.sm.android.locations.locations.Utils.OrmSqlLite.DBManagerZMGK;
import com.sm.android.locations.locations.Utils.OrmSqlLite.DBManagerZMlinshi;
import com.sm.android.locations.locations.Utils.OrmSqlLite.DBManagerZY;
import com.sm.android.locations.locations.Utils.ToastUtils;
import com.sm.android.locations.locations.Utils.View.LineChartView;
import com.sm.android.locations.locations.Utils.View.MyScrollView;
import com.sm.android.locations.locations.Utils.pop.DLPopItem;
import com.sm.android.locations.locations.Utils.pop.DLPopupWindow;
import com.sm.android.locations.locations.initData.CommandUtils;
import com.sm.android.locations.locations.initData.ExecutorServiceUtils;
import com.sm.android.locations.locations.initData.MyLog;
import com.sm.android.locations.locations.initData.MyUtils;
import com.sm.android.locations.locations.initData.PLMN;
import com.sm.android.locations.locations.initData.StringToHex;
import com.sm.android.locations.locations.initData.TCPServer;
import com.sm.android.locations.locations.viewpagermain.NewMainPager.update.dingwei.DwUpdate;
import com.sm.android.locations.locations.viewpagermain.NewMainPager.update.dingwei.OpnUpdate;
import com.sm.android.locations.locations.viewpagermain.ViewPagerMainActivity;
import com.sm.android.locations.locations.zhenma.ZhenmaFenxiActivity;

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
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.graphics.Color.parseColor;
import static com.sm.android.locations.locations.Activity.Main.MainActivity.SPBEANLIST1;
import static com.sm.android.locations.locations.Activity.Main.MainActivity.SPBEANLIST2;
import static com.sm.android.locations.locations.Constant.Constant.BOARDTEMPERATURE1;
import static com.sm.android.locations.locations.Constant.Constant.IP1;
import static com.sm.android.locations.locations.Constant.Constant.IP2;
import static com.sm.android.locations.locations.Constant.Constant.SHUAIJIAN1;
import static com.sm.android.locations.locations.Utils.MainUtils.MainUtils.DS;
import static com.sm.android.locations.locations.Utils.MainUtils.MainUtils.i;
import static com.sm.android.locations.locations.Utils.MainUtils.MainUtils.location;
import static com.sm.android.locations.locations.Utils.MainUtils.MainUtils2.toIMSI;
import static com.sm.android.locations.locations.sos.C.ContantSOS.soslayout;
import static com.sm.android.locations.locations.sos.MubiaopSOS.ZMBEANGKTONGJILISTCALL;
import static com.sm.android.locations.locations.viewpagermain.Fragment.SendUtils.setzy;

public class SOSActivity extends Activity implements View.OnClickListener, SOSVIEW.View {
    String syns="";//发送回来的报文内容
    String type="";//连接成功返回的报文
    int len=0;
    List<Integer> listsize=new ArrayList<>();
    List<ZmBean> zmBeanscontains=new ArrayList<>();
    LinearLayoutManager linearLayoutManager = null;
    /*1 代表接收基站的消息  2 代表我向基站发送的消息 3 代表当前连接状态*///接收tcpServer的每一次的消息内容
    Handler handlerMsg = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:{//接收基站发送给我的消息内容(已转换成字符串utf-8)
//设置语音播报
                    startAuto("123",true);
                    break;}
                case 2:{
                    MyLog.e("msg2", "向基带板发送命令-----"+msg.obj.toString()+"杨路通");//我向基带板发送的指令
                    break;}
                case 3:{
                    if(msg.obj.toString().equals("连接成功")){
                        type=msg.obj.toString(); //连接成功标识
                        Toast.makeText(context, "连接成功", Toast.LENGTH_SHORT).show();
                    }
                    break;}
                case 0202:{
                    if(msg.obj.toString()!=null);syns=msg.obj.toString();
                    if(syns.contains("02020")){//代表参数下发成功
                        ToastUtils.showToast("参数下发成功");
                        ExecutorServiceUtils.scheduledThreadPool.schedule(new Runnable() {
                            @Override
                            public void run() {
                                socketTcpServer.sendPost(CommandUtils.getPublicParameters());//验证并建立小区
                            }
                        }, 20, TimeUnit.SECONDS);
                    }
                    if(syns.contains("02021")){
                        ToastUtils.showToast("参数下发失败");
                    }
                    break;}
                case 0207:{//0208  获取公网参数
                    if(msg.obj.toString()!=null);
                    syns=msg.obj.toString();
                    if(syns.contains("0208")){//获取公网参数成功
                        MyLog.e("0208", "保存的设置参数"+PLMN.getPlmns().get(0).toString());
                        if(syns.contains(PLMN.getPlmns().get(0).getBand())&&syns.contains(PLMN.getPlmns().get(0).getPlmn())&&
                        syns.contains(PLMN.getPlmns().get(0).getPci())&&syns.contains(PLMN.getPlmns().get(0).getTac())&&
                        syns.contains(PLMN.getPlmns().get(0).getCid())&&syns.contains(PLMN.getPlmns().get(0).getUp())&&
                        syns.contains(PLMN.getPlmns().get(0).getDown())){
                            ToastUtils.showToast("小区建立成功了");
                            //小区建立成功后设置定位模式
                            socketTcpServer.sendPost(CommandUtils.getLocateMode(3));//设置定位模式
                        }
                    }
                break;}
                case 0226:{
                    if(msg.obj.toString()!=null);
                    syns=msg.obj.toString();
                    if(syns.contains("02260")){//代表切换定位模式成功
                        ToastUtils.showToast("设定定位模式成功");
                        socketTcpServer.sendPost(CommandUtils.getBlackList());//下发黑名单
                    }else{
                        ToastUtils.showToast("切换定位模式失败");
                    }
                break;}
                case 0210:{
                    if(msg.obj.toString()!=null);
                    syns=msg.obj.toString();
                    if(syns.contains("02100")){//代表下发黑名单成功
                        ToastUtils.showToast("下发黑名单成功");
                        socketTcpServer.sendPost(CommandUtils.setLocationImsI(CommandUtils.imsilist.get(0)));//设置报号值
                    }else {
                        ToastUtils.showToast("下发黑名单失败");
                    }
                break;}
                case 0236:{//报号值
                    if(msg.obj.toString()!=null);
                    syns=msg.obj.toString();
                    if(syns.contains("02360")){//代表设置报号值成功
                        ToastUtils.showToast("定位指定IMSI成功");
                        socketTcpServer.sendPost(CommandUtils.getRF(1));//开射频
                    }else{
                        ToastUtils.showToast("设置报号值失败");
                    }
                break;}
                case 0204:{//射频
                    if(msg.obj.toString()!=null);
                    syns=msg.obj.toString();
                    if(syns.contains("02040")){//代表下发射频命令成功
                        if(CommandUtils.RF_STATE==0){//代表是关闭操作
                            ToastUtils.showToast("射频关闭");
                            //射频将imsi列表重新设置
                            ImslList();
                        }
                        if(CommandUtils.RF_STATE==1){
                            sbZhuangTai="就绪状态";
                            ToastUtils.showToast("射频打开");
                        }
                    }else if(syns.contains("02041")){
                        ToastUtils.showToast("射频");
                    }
                break;}
                case 0303:{//被定位的imsi
                    if(msg.obj.toString()!=null)
                                ImslList0303(msg.obj.toString());//获取的imsi和数据库里对比是哪个
//                    if(msg.obj.toString().contains("0303")) {//定位中
                        String s = msg.obj.toString();
                        String f = s.substring(48);//获取的 消息内容

                        MyLog.e("ttt", f + "\r\n");
                        String[] imsi = f.split("IMSI:");
                        String ims = imsi[1].substring(0, 15);
                        Log.e("ttt", "IMSI号为: " + ims);

                        String[] time = f.split("TIME:");
                        String tim = time[1].substring(0, 10);
                        MyLog.e("ttt", tim);

                        String[] RSSI = f.split("RSSI:");
                        String rssi = RSSI[1].substring(0, 5);
                        rssi = com.sm.android.locations.locations.initData.MyUtils.getNumber(rssi);
                        MyLog.e("ttt", rssi);

                        //设置曲线图
                        list1quxian.remove(0);
                        list1quxian.add(Integer.parseInt(rssi));
                        setqxData(list1quxian, list2quxian);




//                    }
                break;}
                case 0302:{//被采集中的IMSI
//                        //将数据设置到imsi上
                    String s = msg.obj.toString();
                    String f = s.substring(48);//获取的 消息内容

                    MyLog.e("ttt", f + "\r\n");
                    String[] imsi = f.split("IMSI:");
                    String ims = imsi[1].substring(0, 15);
                    Log.e("ttt", "IMSI号为: " + ims);
                        if(linearLayoutManager==null){
                            linearLayoutManager = new LinearLayoutManager(context);
                        }
                        linearLayoutManager.setStackFromEnd(false);//recyview显示最底部一条
                        ry_zhenma_dw.setLayoutManager(linearLayoutManager);

                        zmBeanscontains.add(new ZmBean(ims, "1", "123", new SimpleDateFormat("HH:mm:ss").format(new Date())+"", new SimpleDateFormat("HH:mm:ss").format(new Date())+"", "下行", "123", len++));
                        zmBeanscontains=com.sm.android.locations.locations.initData.MyUtils.removeDuplicate(zmBeanscontains);

                        for (int j = 1; j <zmBeanscontains.size()+1 ; j++) {
                            listsize.add(j);
                        }
                        ryZmAdapterdw = new RyZmAdapterdw(SOSActivity.this, zmBeanscontains, listsize);//list是imsi列表选中的数据
                        ry_zhenma_dw.setAdapter(ryZmAdapterdw);
                    break;
                }
            }
        }
    };
    //用于建立基带板的长链接
    TCPServer socketTcpServer;
    public void startSocket() {
        socketTcpServer.isRun = true;
        socketTcpServer = new TCPServer(handlerMsg);
        socketTcpServer.start();
    }
    private void stopSocket() {
        socketTcpServer.isRun = false;
        socketTcpServer.close();
        socketTcpServer = null;
        MyLog.i("", "Socket已终止");
    }

    private Context context;
    SOSVIEW.MainPresenter presenter;//全局监听
    //功能flag
    private int ViewTYPE = 1;

    //标题栏id
    @BindView(R.id.titles_sos)
    TextView titles_sos;//标题 控件
    @BindView(R.id.iv_wendu)
    ImageView iv_wendu;//温度图标
    @BindView(R.id.tv_wendu)
    TextView tv_wendu;//温度文字 控件
    @BindView(R.id.iv_fengshan)
    ImageView iv_fengshan;//风扇
    @BindView(R.id.iv_menusss)
    ImageView iv_menusss;//侧边菜单栏
    private boolean fengshanFlag = true;
    String tf1 = "";
    String tf2 = "";
    //底部菜单栏
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
    //以下为定位界面
    @BindView(R.id.ll_zhenma_search)
    LinearLayout ll_zhenma_search;
    @BindView(R.id.ryIMSI_dw)
    RecyclerView ryIMSI_dw;//定位界面
    @BindView(R.id.ry_zhenma_dw)
    RecyclerView ry_zhenma_dw;//定位界面
    @BindView(R.id.ib_zhedie)
    ImageButton ib_zhedie;//定位能量曲线图折叠图
    @BindView(R.id.ib_zhedie_zhenma)
    ImageButton ib_zhedie_zhenma;//定位- 侦码折叠图
    //    @BindView(R.id.line_chart_view)
    LineChartView line_chart_viewdw;//能量曲线图
    @BindView(R.id.slideButton1)
    SlideButton slideButton1;//滑动按钮设备1
    @BindView(R.id.laba1dw)
    ImageView laba1dw;//喇叭设备1
    @BindView(R.id.slideButton2)//滑动按钮设备2
            SlideButton slideButton2;
    @BindView(R.id.laba2dw)//喇叭设备2
            ImageView laba2dw;
    @BindView(R.id.tv_r1dw)
    TextView tv_r1dw;
    @BindView(R.id.tv_r2dw)
    TextView tv_r2dw;
    @BindView(R.id.tv_imsi1_dw)//定位展示的IMSI1
            TextView tv_imsi1_dw;
    @BindView(R.id.tv_imsi2_dw)//定位展示的IMSI2
            TextView tv_imsi2_dw;
    @BindView(R.id.tv_sb1_jl_dw)//设备1能力值距离
            TextView tv_sb1_jl_dw;
    @BindView(R.id.sb1_j2_dw)//设备2能力值距离
            TextView tv_sb2_jl_dw;
    @BindView(R.id.cbzt1_d)//设备1增益低中高
            CheckBox cbzt1_d;
    @BindView(R.id.cbzt1_z)
    CheckBox cbzt1_z;
    @BindView(R.id.cbzt1_g)
    CheckBox cbzt1_g;
    @BindView(R.id.cbzt2_d)//设备2增益低中高
            CheckBox cbzt2_d;
    @BindView(R.id.cbzt2_z)
    CheckBox cbzt2_z;
    @BindView(R.id.cbzt2_g)
    CheckBox cbzt2_g;
    @BindView(R.id.mysp1)
    Spinner mysp1;//设备1下拉框
    @BindView(R.id.mysp2)
    Spinner mysp2;//设备2下拉框
    @BindView(R.id.scrollView1dw)
    MyScrollView scrollView1dw;//状态框1
    @BindView(R.id.scrollView2dw)
    MyScrollView scrollView2dw;//状态框2
    @BindView(R.id.textViews1dw)
    TextView textViews1dw;//状态框文字1
    @BindView(R.id.textViews2dw)
    TextView textViews2dw;//状态框文字2
    @BindView(R.id.tv1_type_dw)
    TextView tv1_type_dw;//当前设备连接状态设备1：
    @BindView(R.id.tv2_type_dw)
    TextView tv2_type_dw;//当前设备连接状态设备2：
    @BindView(R.id.tv1_td_dw)
    TextView tv1_td_dw;//设备1 双工模式
    @BindView(R.id.tv2_td_dw)
    TextView tv2_td_dw;//设备2 双工模式
    @BindView(R.id.tv1_wifi_dw)
    TextView tv1_wifi_dw;//设备1 wifi连接连接状态
    @BindView(R.id.tv2_wifi_dw)
    TextView tv2_wifi_dw;//设备2 wifi连接连接状态
    @BindView(R.id.bt_start1dw)
    Button bt_start1dw;//启动定位1
    @BindView(R.id.bt_start2dw)
    Button bt_start2dw;//启动定位2
    @BindView(R.id.bt_stop1dw)
    Button bt_stop1dw;//停止定位1
    @BindView(R.id.bt_stop2dw)
    Button bt_stop2dw;//停止定位2
    @BindView(R.id.et_zhenmasearchdw)
    EditText et_zhenmasearchdw;

    @BindView(R.id.tv_searchNumdw)
    TextView tv_searchNumdw;
    private TextToSpeech textToSpeech = null;//创建自带语音对象
    StringBuffer stringBuffer1dw = null;
    StringBuffer stringBuffer2dw = null;
    private boolean slideButton1Flag = true;
    private boolean slideButton2Flag = true;
    ArrayAdapter<String> adapter1, adapter2;
    List<String> listsSp = new ArrayList<>();
    private String spinnerS1 = "", spinnerS2 = "";
    Message message;
    Bundle bundle;
    private static Timer timer1, timer2, timer_wendu;
    DatagramPacket dp;
    byte[] buf;
    public static boolean FENGSHANFLAG = true;
    private boolean runThread = false;
    private DLPopupWindow popupWindow;
    private List<DLPopItem> mList = new ArrayList<>();
    Dialog dialog;
    View inflate;
    public static String sbZhuangTai="就绪状态";
    public String sb1 = "";
    public String sb2 = "";
    private boolean SB1ZY = false;//初次就进入设备增益查询一次
    private boolean SB2ZY = false;//初次就进入设备增益查询一次
    List<Integer> list1quxian = null;//设备1曲线图数据
    List<Integer> list2quxian = null;//设备2曲线图数据
    private String sp1MAX1value = "";//扫频1得到的1号频点
    private String sp1MAX2value = "";//扫频1得到的2号频点
    private String sp2MAX1value = "";//扫频2得到的1号频点
    private String sp2MAX2value = "";//扫频2得到的2号频点
    private boolean sb1FirstFlag = false;//第一次中标的
    private boolean sb2FirstFlag = false;
    private boolean TIMERRESTARTFLAG1 = true;  //是否重启完成1  若重启完 true
    private boolean TIMERRESTARTFLAG2 = true;  //是否重启完成1  若重启完 true
    private Timer timer = null;//11秒一次imsi列表更新
    private Timer timerLocation = null;//五秒一次imsi列表更新
    RyZmAdapterdw ryZmAdapterdw;
    @SuppressLint("NewApi")
    DecimalFormat dfBaoshu = new DecimalFormat("###");
    private boolean laba1Flag = true;//默认声音开启
    private boolean laba2Flag = true;

    RyZmAdapterGk.GKCallBack gkCallBack = new RyZmAdapterGk.GKCallBack() {
        @Override
        public void call(String imsi, String sb) {
            if (sb.equals("1")) {
//
                dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
                inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
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
                                Log.e("nzqsendstart1", "run: sendsocket端口号" + outputPacket.getPort() + "Ip地址:" + outputPacket.getAddress().toString() + "数据:" + outputPacket.getData());
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
                dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
                inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
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
                                Log.e("nzqsendstart1", "run: sendsocket端口号" + outputPacket.getPort() + "Ip地址:" + outputPacket.getAddress().toString() + "数据:" + outputPacket.getData());
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
    private Handler handler = new Handler() {
        @SuppressLint("NewApi")
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {

                case 100:
                    if (!TextUtils.isEmpty(SHUAIJIAN1)) {
                        Double XS = Double.parseDouble(SHUAIJIAN1);
//                    int XS = 12;
                        double v = (33 - (XS * 0.25)) * 0.1;
                        double pow = Math.pow(10, v);
                        double v1 = pow / 1000;
                        Log.d("nzqtag", "run: " + v1);
                        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
                    }
                    break;
                case 8153://接受板卡温度目前只接受53 板卡温度为准
                    DecimalFormat df2;
                    df2 = new DecimalFormat("####");
                    if (TextUtils.isEmpty(BOARDTEMPERATURE1)) {
                        break;
                    }
                    OpnUpdate.upwendu(tv_wendu, iv_wendu, BOARDTEMPERATURE1, df2, context, iv_fengshan, FENGSHANFLAG);
                    break;
                case 300002:
                    tv_imsi1_dw.setText("");
                    tv_sb1_jl_dw.setText("");
                    tv_imsi2_dw.setText("");
                    tv_sb2_jl_dw.setText("");
                    tv_sb2_jl_dw.setText("");
                    break;
                case 100001://wifi状态
//                    if (ViewTYPE == 1) {
                        DwUpdate.upwifi(msg, tv1_wifi_dw, tv2_wifi_dw);
//                    }
//                    if (ViewTYPE == 2) {//搜救模式下
//
//                    }
                    break;
                case 200148: //展示中标imsi 一个
                    if (ViewTYPE == 1) {
                        presenter.setIMSIshow2(msg, tv_imsi2_dw);
                    }
                break;
            }
        }
    };
    RyImsiAdapter ryImsiAdapter;
    List<AddPararBean> pararBeansList1 = new ArrayList<>();
    String mydown2GK = "";
    String mydown1GK = "";
    String mydown11GK = "";
    String mydown22GK = "";
    Timer timerLocationshepingonglv;
   /* @BindView(R.id.ib_dc_zm)//搜救侦码数据清除
            ImageButton ib_dc_zm;
    @BindView(R.id.sos_ib_qc_sos)//搜救 侦码清除
            ImageButton sos_ib_qc_sos;*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(soslayout);
//        new ViewPagerMainActivity().isFirstStart(this);

        String s = "Hello world!";
        char c = s.charAt(4); // 拿到第5个字符
        System.out.println("拿到第5个字符" + c);
        s = s.substring(5);// 删掉了第5位之前的内容
        System.out.println("删掉了第5位之前的内容" + s);



        context = this;//得到上下文
        new SOSPersent(this);//回调接听

        ButterKnife.bind(this);//绑定ID

        startSocket();//初始化基带板



     /*   ib_dc_zm.setOnClickListener(this);
        sos_ib_qc_sos.setOnClickListener(this);*/
        seekBarOnchangLister();//滑动条监听
        timerLocationshepingonglv = new Timer();
        //schedule方法是执行时间定时任务的方法
        timerLocationshepingonglv.schedule(new TimerTask() {

            //run方法就是具体需要定时执行的任务
            @Override
            public void run() {
                Message message = new Message();
                handler.sendMessage(message);
//                message.what = 100;
//                Log.d(TAG, "handlerrun: " + 1);
//                DeviceUtils.SelectQury(3, 1);
            }
        }, 0, 3000);


        initTTS();
        setEt_search();
        stringBuffer1dw = new StringBuffer();
        stringBuffer2dw = new StringBuffer();
        SOSUtils.getPermissions(this);//申请权限
        SOSUtils.setTITle(titles_sos);//设置标题

        ll_0.setOnClickListener(this);
        bt_0.setOnClickListener(this);
        textViews1dw.setOnClickListener(this);
//        bt_1.setOnClickListener(this);
        iv_menusss.setOnClickListener(this);
        line_chart_viewdw = findViewById(R.id.line_chart_viewdw);
        iv_fengshan.setOnClickListener(this);//风扇监听
        bt_start1dw.setOnClickListener(this);//设备1开始监听
        bt_start2dw.setOnClickListener(this);//设备2开始监听
        bt_stop1dw.setOnClickListener(this);//设备1停止监听
        bt_stop2dw.setOnClickListener(this);//设备2停止监听
        ib_zhedie.setOnClickListener(this);//折叠能量曲线监听
        ib_zhedie_zhenma.setOnClickListener(this);//侦码曲线图
        laba1dw.setOnClickListener(this);//喇叭1监听
        laba2dw.setOnClickListener(this);//喇叭2监听
        ryIMSI_dw.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        setSlidubutton();//设置滑动按钮监听
        setPinnerdata();//设置下拉框数据
        CheckBoxOnclickSet();//设置增益
        ImslList();//初始化IMSI
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
        timer1 = new Timer();
        timer2 = new Timer();
        timer_wendu = new Timer();
        buf = new byte[1024];
        dp = new DatagramPacket(buf, buf.length);
//        MainUtils.ReceiveMainWD(handler, message, bundle, timer_wendu);
        MainUtils.ReceiveMain(handler, message, bundle, timer1, timer2, dp, buf, context, runThread);//开启线程监听
        MainUtils.WifiMain(handler, message, bundle, timer1, timer2, dp, buf, context);//开启wifi监听
        AddITemMenu(0);//添加菜单的按钮  0  定位
        if (timerLocation == null) {
            timerLocation = new Timer();
            //schedule方法是执行时间定时任务的方法
            timerLocation.schedule(new TimerTask() {

                //run方法就是具体需要定时执行的任务
                @Override
                public void run() {
                    Message message = new Message();
                    handler.sendMessage(message);
                        message.what = 300002;
//                    Log.d(TAG, "handlerrun: " + 1);
                }
//            }, 0, 10000);
            }, 0, 8000);
        }
        try {//初始化 清除上次的侦码记录
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
        try {//初始化 清除上次的  管控侦码记录
            dbManagergk = new DBManagerZMGK(this);

            dbManagergk.deleteall();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //测试gkIMSI统计代码
        //此处显示侦码的imsi列表
        List<ZmBeanGK> zmBeanssA = null;
        try {
            zmBeanssA = dbManagergk.getDataAll();
            Log.d("TAGzmBeanss", "init: " + zmBeanssA.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
//此处显示侦码的imsi列表
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
            linearLayoutManager.setStackFromEnd(true);//recyview显示最底部一条

//                tv_searchNumdw.setText("(" + zmBeans.size() + ")");
        } else {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setStackFromEnd(false);//recyview显示最底部一条
//                tv_searchNumdw.setText("(" + zmBeans.size() + ")");
        }
        //风扇隐藏
        iv_fengshan.setVisibility(View.GONE);
    }

    @SuppressLint("NewApi")
    private void seekBarOnchangLister() {
//        seekbarnum = seekbar_sos.getProgress();


//                double log = Math.log(seekbarnum);//自然数底数的对数值
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

//                //停止
//                int sum = seekbarnum;
//                int num = 255 - sum;
//
        long a = Math.round(Ant_Power);
        String hex = Integer.toHexString((int) a);
        //消息头
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
//                            "", "onProgressChanged: " + hex.toString() + "---探测距离+" + pow);
//                    tv_sos_juli.setText("探测距离参考值" + Math.rint(powmun) + "米射频输出功率(" + ")");
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
//                            "", "onProgressChanged: " + hex.toString() + "---探测距离+" + pow);
        @SuppressLint({"NewApi", "LocalSuppress"}) DecimalFormat df = new DecimalFormat("######0.000");
//                }
        Log.d("onStopTrackingTouch", "onStopTrackingTouch: " + Ant_Power + "------" + Slider + "---");
    }


    /**
     * 设置曲线图数据
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

        scrollView1dw.fullScroll(ScrollView.FOCUS_DOWN);//滚动到底部

        scrollView1dw.post(new Runnable() {
            @Override
            public void run() {
                //滚动到底部
                scrollView1dw.fullScroll(ScrollView.FOCUS_DOWN);
                //滚动到顶部
//                scrollView1dw.fullScroll(ScrollView.FOCUS_UP);
            }
        });
    }

    /**
     * 设置曲线图数据
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

        //设备1
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

        //设备2
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
//                ToastUtils.showToast(mList.get(position).getText());
                if (mList.get(position).getText().equals("添加IMSI")) {
                    startActivity(new Intent(context, AddParamActivity.class));
                }
                if (mList.get(position).getText().equals("目标IMSI")) {
                    startActivity(new Intent(context, ParamActivity.class));
//                    startActivity(new Intent(context, Param2Activity.class));
                }
                if (mList.get(position).getText().equals("非控IMSI")) {
                    startActivity(new Intent(context, ParamWhiteActivity.class));
//                    startActivity(new Intent(context, Param2Activity.class));
                }
                if (mList.get(position).getText().equals("扫频设置")) {
                    startActivity(new Intent(context, SaoPinSetingActivity.class));
                }

                if (mList.get(position).getText().equals("频点设置")) {
//                    startActivity(new Intent(context, PinConfigActivity.class));//旧版本
                    startActivity(new Intent(context, PinConfigViewPagerActivity.class));
                }
                if (mList.get(position).getText().equals("设备设置")) {
//                    startActivity(new Intent(context, Community01Activity.class));
                    startActivity(new Intent(context, CommunitViewPagerActivity.class));
                }

                if (mList.get(position).getText().equals("设备信息")) {
                    startActivity(new Intent(context, DeviceInfoActivity.class));
                }
                if (mList.get(position).getText().equals("数据管理")) {
//                    startActivity(new Intent(context, DeviceInfoActivity.class));
//                    ToastUtils.showToast("数据管理");
//                    DialogUtils.DataExport(context, inflate, zmDataCallBack);
                }
                if (mList.get(position).getText().equals("轮循设置")) {
                    startActivity(new Intent(context, LunxunSetingActivity.class));
                }
                if (mList.get(position).getText().equals("数据分析")) {
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
        try {
            DBManagerZMlinshi dbManagerZMlinshi = new DBManagerZMlinshi(context);
            List<ZmBeanlinshi> dataAll = dbManagerZMlinshi.getDataAll();
            Log.d("uuudataAll", "setPinnerdata: " + dataAll);
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

    private void setSlidubutton() {
        slideButton1.setChecked(true);
        slideButton2.setChecked(true);
        mysp1.setVisibility(View.GONE);
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
                    mysp1.setVisibility(View.VISIBLE);
                    mysp1.setEnabled(true);
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
//                    mysp1.setVisibility(View.VISIBLE);
//                    mysp1.setEnabled(true);
                }
            }
        });
    }

    //设置状态栏1的数据
    public String upStr = "";

    //拼接状态栏 数据处理
    private void Set1StatusBar(String str) {
        if (!upStr.equals(str)) {
            stringBuffer1dw.append(str + "" + "\n");
            textViews1dw.setText(stringBuffer1dw);
            upStr = str;
            scrollView1dw.fullScroll(ScrollView.FOCUS_DOWN);//滚动到底部
        }
    }

    //设置状态栏1的数据
    public String upStrsos = "";

    public String upStrsos2 = "";

   /* //拼接状态栏 数据处理
    private void SetSOS2StatusBar(String str) {
        if (!upStrsos2.equals(str)) {
            stringBuffer2sos.append(str + "" + "\n");
            textViews_sos2.setText(stringBuffer2sos);
            upStrsos2 = str;
            scrollView2_sos.fullScroll(ScrollView.FOCUS_DOWN);//滚动到底部
        }
    }*/

    //设置状态栏1的数据
    public String upStr2 = "";

    //拼接状态栏 数据处理
    private void Set2StatusBar(String str) {
        if (!upStr2.equals(str)) {
            stringBuffer2dw.append(str + "" + "\n");
            textViews2dw.setText(stringBuffer2dw);
            upStr2 = str;
            scrollView2dw.fullScroll(ScrollView.FOCUS_DOWN);//滚动到底部
        }
    }

    private boolean zdFlagdw = false;//折线图开关
    private boolean zdSearchFlagdw = false;//折线图开关
    private boolean zdSearchFlagsos = false;//折线图开关

    @SuppressLint("NewApi")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sos_ib_qc_sos:
                Dialog dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
                inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
                TextView tv_title = inflate.findViewById(R.id.tv_title);
                Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
                tv_title.setText("确定要清除侦码记录吗?");
                bt_confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DBManagerZMGK dbManagergk = null;
                        try {//初始化 清除上次的  管控侦码记录
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
                break;
           /* case R.id.ib_dc_zm:
                dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
                inflate = LayoutInflater.from(context).inflate(R.layout.dataexport_item, null);*/
//        bt_export
//        bt_clear
//        tv_location

               /* final Button bt_clear = inflate.findViewById(R.id.bt_clear);//清除数据按钮
                bt_cancel = inflate.findViewById(R.id.bt_cancel);//关闭按钮
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
                //获取当前Activity所在的窗体
                Window dialogWindow2 = dialog.getWindow();
                //设置Dialog从窗体底部弹出
                dialogWindow2.setGravity(Gravity.CENTER);

                //获得窗体的属性
                WindowManager.LayoutParams lp2 = dialogWindow2.getAttributes();

//                    lp.y = 20;//设置Dialog距离底部的距离
//        lp.height=1200;
//        lp.width=400;
//       将属性设置给窗体
                dialogWindow2.setAttributes(lp2);
                dialog.show();//显示对话框*/
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
                if (sb1_gk.equals("搜索中")) {
                    ToastUtils.showToast("设备1搜索中请先停止");

                    break;
                }
                if (sb1_gk.equals("管控中")) {
                    ToastUtils.showToast("设备1管控中请先停止");

                    break;
                }
                if (sb2_gk.equals("搜索中")) {
                    ToastUtils.showToast("设备2搜索中请先停止");

                    break;
                }
                if (sb2_gk.equals("管控中")) {
                    ToastUtils.showToast("设备2管控中请先停止");

                    break;
                }
                ViewTYPE = 1;
                r0.setVisibility(View.VISIBLE);
                r1.setVisibility(View.GONE);

                bt_0.setBackground(getResources().getDrawable(R.mipmap.dwsj_bl));
                bt_1.setBackground(getResources().getDrawable(R.mipmap.ypcj_gr));

                AddITemMenu(0);//添加菜单的按钮

                break;*/
            case R.id.bt_0:
                if (sb1_gk.equals("搜索中")) {
                    ToastUtils.showToast("设备1搜索中请先停止");

                    break;
                }
                if (sb1_gk.equals("管控中")) {
                    ToastUtils.showToast("设备1管控中请先停止");

                    break;
                }
                if (sb2_gk.equals("搜索中")) {
                    ToastUtils.showToast("设备2搜索中请先停止");

                    break;
                }
                if (sb2_gk.equals("管控中")) {
                    ToastUtils.showToast("设备2管控中请先停止");

                    break;
                }
                ViewTYPE = 1;
                r0.setVisibility(View.VISIBLE);
//                r1.setVisibility(View.GONE);

                bt_0.setBackground(getResources().getDrawable(R.mipmap.dwsj_bl));
//                bt_1.setBackground(getResources().getDrawable(R.mipmap.ypcj_gr));

                AddITemMenu(0);//添加菜单的按钮
                break;


           /* case R.id.ll_1:
                if (sb1.equals("定位中")) {
                    ToastUtils.showToast("设备1定位中请先停止");

                    break;
                }
                if (sb2.equals("定位中")) {
                    ToastUtils.showToast("设备2定位中请先停止");

                    break;
                }
                ViewTYPE = 2;

                r0.setVisibility(View.GONE);

                r1.setVisibility(View.VISIBLE);
                bt_1.setBackground(getResources().getDrawable(R.mipmap.ypsj_bl));
                bt_0.setBackground(getResources().getDrawable(R.mipmap.dwsj_gr));

                AddITemMenu(2);//添加菜单的按钮
                break;*/
            /*case R.id.bt_1:
                if (sb1.equals("定位中")) {
                    ToastUtils.showToast("设备1定位中请先停止");

                    break;
                }
                if (sb2.equals("定位中")) {
                    ToastUtils.showToast("设备2定位中请先停止");

                    break;
                }
                ViewTYPE = 2;
                r0.setVisibility(View.GONE);
                r1.setVisibility(View.VISIBLE);
                bt_1.setBackground(getResources().getDrawable(R.mipmap.ypsj_bl));
                bt_0.setBackground(getResources().getDrawable(R.mipmap.dwsj_gr));

                AddITemMenu(2);//添加菜单的按钮
                break;*/


            case R.id.iv_fengshan:
//                presenter.fengshanSet(fengshanFlag, iv_fengshan, context);
//                saopinshow1=true;
//                presenter.spbuilsshow(context, 1, 3, tf1, tf2);//扫频建立小区
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
                socketTcpServer.sendPost(MyUtils.getSocketHeader(MyUtils.getToHexString("0108", "")));//获取公网参数
                break;

            case R.id.bt_start1dw:
//                if (slideButton1Flag) {//自动建立小区
////                    ToastUtils.showToast("设备1自动建立小区");
//                    zidongsaopinjianlixiaoqu(1);
//                } else {//手动建立小区
////                    ToastUtils.showToast("手动建立小区");
//                    presenter.startSD(1, tf1, context, spinnerS1, sb1);
//                }
                if(!slideButton1Flag){//手动建立小区
                    if(sbZhuangTai.equals("就绪状态")){
                        //设置工作参数
                        presenter.startSD(1, type, context, spinnerS1, SOSActivity.sbZhuangTai,tv1_wifi_dw.getText().toString(),socketTcpServer);
                    }else{
                        ToastUtils.showToast("正在启动设备");
                    }
                }else{
                    ToastUtils.showToast("自动扫频");
                }
                break;
            case R.id.bt_start2dw:
                if (slideButton2Flag) {//自动建立小区
//                    ToastUtils.showToast("设备2自动建立小区");
                    zidongsaopinjianlixiaoqu(2);
                } else {//手动建立小区
                    presenter.startSD(2, tf2, context, spinnerS2, sb2,tv1_td_dw.getText().toString(),socketTcpServer);

                }
                break;

            case R.id.bts_start_sos:
//                ToastUtils.showToast("开始人工搜救");
                saopinGKFalg1 = false;
                saopinGKFalg2 = false;
//
                break;
            case R.id.bts_stop_sos:

                presenter.stopGK(context);
                break;
            case R.id.bt_stop1dw:
//                sb1FirstFlag = false;
                presenter.stopdw(1, context, sb1,socketTcpServer);
                break;
            case R.id.bt_stop2dw:
                sb1FirstFlag = false;
                presenter.stopdw(2, context, sb2,null);
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
            sp1MAX1value = "";//扫频1得到的1号频点
            sp1MAX2value = "";//扫频1得到的2号频点

        } else {

            sp2MAX1value = "";//扫频2得到的1号频点
            sp2MAX2value = "";//扫频2得到的2号频点
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

    //imsi列表
    private void ImslList() {
        List<AddPararBean> dataAll = null;//首页IMSI列表的数据
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
                //list是imsi列表选中的数据
                if (listImsiListTrue.size() > 0) {
                    for (int i = 1; i < listImsiListTrue.size() + 1; i++) {
                        list1size.add(i);
                    }
                }
                pararBeansList1 = listImsiListTrue;
                ryImsiAdapter = new RyImsiAdapter(context, listImsiListTrue, list1size, config, tv_imsi1_dw, tv_imsi2_dw);//list是imsi列表选中的数据
                ryIMSI_dw.setAdapter(ryImsiAdapter);
                ryImsiAdapter.notifyDataSetChanged();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //imsi列表
    private void ImslList0303(String msg) {
        MyLog.i("杨路通msg", msg+"\r\n");
        List<AddPararBean> dataAll = null;//首页IMSI列表的数据
        List<AddPararBean> listImsiListTrue = null;
        try {
            DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(this);
            dataAll = dbManagerAddParam.getDataAll();
            listImsiListTrue = new ArrayList<>();
            if (dataAll.size() > 0) {
                for (int i = 0; i < dataAll.size(); i++) {
                    if (dataAll.get(i).isCheckbox() == true) {
                        Log.i("杨路通ImslList0303", "ImslList0303: "+dataAll.get(i).getImsi());
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
                //list是imsi列表选中的数据
                if (listImsiListTrue.size() > 0) {
                    for (int i = 1; i < listImsiListTrue.size() + 1; i++) {
                        list1size.add(i);
                    }
                }
                pararBeansList1 = listImsiListTrue;
                ryImsiAdapter = new RyImsiAdapter(context, listImsiListTrue, list1size, config, tv_imsi1_dw, tv_imsi2_dw);//list是imsi列表选中的数据
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
                dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
                inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
                TextView tv_title = inflate.findViewById(R.id.tv_title);
                tv_title.setText("确定要定位" + imsi + "吗?");
                Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
                bt_confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

//                        socketTcpServer.sendPost(MyUtils.getSocketHeader(MyUtils.getToHexString("0110","BLACKLIST:000000000000000")));//清空黑明单
                        socketTcpServer.sendPost(CommandUtils.setLocationImsI(imsi));
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
//            }
//            } else if (sb.equals("2")) {
//
//                dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
//                inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
//                TextView tv_title = inflate.findViewById(R.id.tv_title);
//                tv_title.setText("确定要定位" + imsi + "吗?");
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
//                                InetAddress address = null;//你本机的ip地址
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
//                                Log.e("nzqsendstart1", "run: sendsocket端口号" + outputPacket.getPort() + "Ip地址:" + outputPacket.getAddress().toString() + "数据:" + outputPacket.getData());
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
//                //获取当前Activity所在的窗体
//                Window dialogWindow = dialog.getWindow();
//                //设置Dialog从窗体底部弹出
//                dialogWindow.setGravity(Gravity.CENTER);
//                //获得窗体的属性
//                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
////                           lp.y = 20;//设置Dialog距离底部的距离
////                          将属性设置给窗体
//                dialogWindow.setAttributes(lp);
//                dialog.show();//显示对话框
//
//
//            }
//            else {
//                ToastUtils.showToast("未中标");
//
//            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        ImslList();
        setPinnerdata();//设置下拉框数据
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImslList();
        setPinnerdata();//设置下拉框数据
        if (requestCode == 1000 && resultCode == 1001) //&& resultCode == 1001
        {
            ToastUtils.showToast("ok");
            List<AddPararBean> list1 = new ArrayList<>();// 设备1
            List<AddPararBean> list2 = new ArrayList<>();// 设备2
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
     * 设备定位imsi的数据
     *
     * @param msg
     */
    List<States> listStates = new ArrayList<>();// 设备黑名单中标情况

    private void sbImsiType(Message msg) {
        String imsi = msg.getData().getString("imsi");
        String sb = msg.getData().getString("sb");
        String zb = msg.getData().getString("zb");
        String datetime = msg.getData().getString("datetime");
        String time = msg.getData().getString("time");
        Log.d("TAG", "handl黑名单: " + imsi + "---" + sb + "zb--" + "datatime--" + datetime + "time--" + time);
        States states = new States();
        states.setImsi(imsi);
        states.setSb(sb);
        states.setZb(zb + "");
        states.setDatatime(datetime);
        states.setTime(time);
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
                    handler.sendMessage(message);
                    message.what = 300001;
//                    Log.d(TAG, "handlerrun: " + 1);
//                    Log.d(TAG, "handlerrun: " + 1);
                }
            }, 0, 11000);//IMSI
        } else {
//            Log.d(TAG, "ahandlerrun: " + 1);

//            timer.schedule(new TimerTask() {
//
//                //run方法就是具体需要定时执行的任务
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
        textToSpeech = new TextToSpeech(SOSActivity.this, new TextToSpeech.OnInitListener() {
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

    private boolean saopinshow1 = false;  //小区查看 不建立小区
    private boolean saopinshow2 = false;

    /**
     * 自动扫频建立小区
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

    private int SAOPIN = 1;//扫频的type设备1
    private int SAOPIN2 = 1;//扫频的type设备1
    private SpBean spBean1 = new SpBean();
    private SpBean spBean2 = new SpBean();
    private SpBean spBean154 = new SpBean();
    private SpBean spBean254 = new SpBean();
    private boolean phoneFlag1 = true;// 自动手机扫频
    private boolean phoneFlag2 = true;// 自动手机扫频
    SaoPinCallback saoPinCallback = new SaoPinCallback() {
        @Override
        public void sucess(int sb, int i) {

            Log.d("saoPinCallback", "sucess: 设备" + sb + "i===" + i);
            SAOPIN = i;
            SAOPIN2 = i;
            if (sb == 1) {
                if (saopinshow1) {//如果是 小区查看
                    presenter.spbuilsshow(context, sb, i, tf1, tf2);//扫频建立小区
                } else {//如果是扫频并建立小区
                    sp1MAX1value = "";
                    sp1MAX2value = "";
                    presenter.spbuils(context, sb, i, tf1, tf2);//扫频建立小区

                    phoneFlag1 = false;
                }

            }
            if (sb == 2) {

                if (saopinshow2) {//如果是小区查看
                    presenter.spbuilsshow(context, sb, i, tf1, tf2);//扫频小区

                } else {
                    sp2MAX1value = "";
                    sp2MAX2value = "";
                    presenter.spbuils(context, sb, i, tf1, tf2);//扫频建立小区
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

                if (MainUtils2.YY(spBean1.getPlmn()).equals("移动")) {
                    SAOPIN = 1;
                    Log.d("aaaplmnsucessphone", "sucessphone: +");
                    if (tf1.equals("TDD")) {

                        presenter.setStart(1, slideButton1Flag, 0, sb1, sb2, sp1MAX1value, spinnerS2, context, tf1, tf2, 1, true);//restart   1表示重启 有弹窗,,, 0表示不重启没弹窗


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
                } else if (spBean.getBand().equals("1") || spBean.getBand().equals("3") || spBean.getBand().equals("5") || spBean.getBand().equals("8") || MainUtils2.YY(spBean1.getPlmn()).equals("移动")) {//移动FDD
                    SAOPIN = 2;
                    if (tf1.equals("FDD")) {
                        presenter.setStart(1, slideButton1Flag, 0, sb1, sb2, sp1MAX1value, spinnerS2, context, tf1, tf2, 1, true);//restart   1表示重启 有弹窗,,, 0表示不重启没弹窗
//
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
                } else if (MainUtils2.YY(spBean1.getDown()).equals("联通")) {
                    SAOPIN = 3;
                    if (tf1.equals("FDD")) {

                        presenter.setStart(1, slideButton1Flag, 0, sb1, sb2, sp1MAX1value, spinnerS2, context, tf1, tf2, 1, true);//restart   1表示重启 有弹窗,,, 0表示不重启没弹窗


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
                } else if (MainUtils2.YY(spBean1.getDown()).equals("电信")) {
                    SAOPIN = 4;
                    if (tf1.equals("FDD")) {

                        presenter.setStart(1, slideButton1Flag, 0, sb1, sb2, sp1MAX1value, spinnerS2, context, tf1, tf2, 1, true);//restart   1表示重启 有弹窗,,, 0表示不重启没弹窗


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
                if (MainUtils2.YY(spBean154.getPlmn()).equals("移动") && !spBean154.getBand().equals("1") && !spBean154.getBand().equals("3") && !spBean154.getBand().equals("5") && !spBean154.getBand().equals("8")) {
                    SAOPIN2 = 1;
                    if (tf2.equals("TDD")) {

                        presenter.setStart(2, slideButton1Flag, 0, sb1, sb2, sp1MAX1value, sp2MAX1value, context, tf1, tf2, 1, true);//restart   1表示重启 有弹窗,,, 0表示不重启没弹窗


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
                } else if (spBean154.getBand().equals("1") || spBean154.getBand().equals("3") || spBean154.getBand().equals("5") || spBean154.getBand().equals("8")) {//移动FDD
                    SAOPIN2 = 2;
                    if (tf2.equals("FDD")) {

                        presenter.setStart(2, slideButton1Flag, 0, sb1, sb2, sp1MAX1value, sp2MAX1value, context, tf1, tf2, 1, true);//restart   1表示重启 有弹窗,,, 0表示不重启没弹窗


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
                } else if (MainUtils2.YY(spBean154.getDown()).equals("联通")) {
                    SAOPIN2 = 3;
                    if (tf2.equals("FDD")) {

                        presenter.setStart(2, slideButton1Flag, 0, sb1, sb2, sp1MAX1value, sp2MAX1value, context, tf1, tf2, 1, true);//restart   1表示重启 有弹窗,,, 0表示不重启没弹窗


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
                } else if (MainUtils2.YY(spBean154.getDown()).equals("电信")) {
                    SAOPIN2 = 4;
                    if (tf2.equals("FDD")) {

                        presenter.setStart(2, slideButton1Flag, 0, sb1, sb2, sp1MAX1value, sp2MAX1value, context, tf1, tf2, 1, true);//restart   1表示重启 有弹窗,,, 0表示不重启没弹窗


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
//     * 自动扫频建立小区
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
        saopinshow1 = true;
        DialogUtils.SaoPinDialog2(1, context, inflate, saoPinCallback, tf1, false, sb1);
    }

    public void lishi11(View view) {

        if (SPBEANLIST1.size() > 0) {
            Intent intent = new Intent(context, SaoPinActivity.class);
            intent.putExtra("type", "1");
            startActivity(intent);
        } else {
            Set1StatusBar("当前扫频列表不可用");
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
            Set2StatusBar("当前扫频列表不可用");
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
                String str = editable.toString();
                if (!TextUtils.isEmpty(str) && str.length() > 0) {
                    DBManagerZM dbManagerZM = null;
                    try {
                        dbManagerZM = new DBManagerZM(context);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    //此处显示侦码的imsi列表
                    List<ZmBean> zmBeanss = new ArrayList<>();
                    List<ZmBean> zmBeans = new ArrayList<>();
                    try {
                        zmBeanss = dbManagerZM.getDataAll();
                        for (int i = 0; i < zmBeanss.size(); i++) {
                            if (zmBeanss.get(i).getMaintype().equals("0")) {
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
                            linearLayoutManager.setStackFromEnd(true);//recyview显示最底部一条
                            ry_zhenma_dw.setLayoutManager(linearLayoutManager);
                            tv_searchNumdw.setText("(" + listsize.size() + ")");
                            ryZmAdapterdw = new RyZmAdapterdw(SOSActivity.this, zmBeanscontains, listsize);//list是imsi列表选中的数据
                            ry_zhenma_dw.setAdapter(ryZmAdapterdw);
                        } else {
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                            linearLayoutManager.setStackFromEnd(false);//recyview显示最底部一条
                            ry_zhenma_dw.setLayoutManager(linearLayoutManager);
                            tv_searchNumdw.setText("(" + listsize.size() + ")");
                            ryZmAdapterdw = new RyZmAdapterdw(SOSActivity.this, zmBeanscontains, listsize);//list是imsi列表选中的数据
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
        //时间倒叙

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
            linearLayoutManager.setStackFromEnd(true);//recyview显示最底部一条
        } else {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setStackFromEnd(false);//recyview显示最底部一条
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
        Log.d("重启哦", "gkqiehuan: " + tf + "---" + device);
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
     * 停止管控
     */
    @Override
    public void gkstop() {
        saopinGKFalg1 = false;
        saopinGKFalg2 = false;
    }

    List<ZmBeanGKTongji> listgk = new ArrayList<>();//管控所有侦码数据 ,选择定位目标的使用

    @Override
    public void gkList(List<ZmBeanGKTongji> list) {
        listgk = list;
    }

    @Override
    public void zhuanhuandw(int device) {
    }


    //发送黑名单
    private void sendwhiteListRun(int type, List<AddPararBeanWhite> sendListBlack, final String tf1, final String spinnerS1, final Context context, List<AddPararBeanWhite> listImsiListTrue) {

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
            if (type == 1) {
                sendrunwhite(str, sendListBlack, tf1, spinnerS1, context, listImsiListTrue);//开始发送
            }
            if (type == 2) {

                sendrunwhite2(str, sendListBlack, tf1, spinnerS1, context, listImsiListTrue);//开始发送
            }
            if (type == 3) {
                sendrunwhite(str, sendListBlack, tf1, spinnerS1, context, listImsiListTrue);//开始发送
                sendrunwhite2(str, sendListBlack, tf1, spinnerS1, context, listImsiListTrue);//开始发送
            }


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
                Log.e("nzqsendstart1Black", "run: sendsocket端口号" + outputPacket.getPort() + "Ip地址:" + outputPacket.getAddress().toString() + "数据:" + outputPacket.getData());
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

    interface SOSxuanzemubiao {
        void Callback(List<ZmBeanGKTongji> list, String device);
    }

    SOSxuanzemubiao soSxuanzemubiao = new SOSxuanzemubiao() {
        @Override
        public void Callback(List<ZmBeanGKTongji> list, String device) {
            Log.d("soSxuanzemubiao", "设备是:" + device + "数据是Callback: " + list.toString());
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyLog.e("", "start Destroy~~~");
        if(socketTcpServer!=null){
            socketTcpServer.interrupt();
        }
        stopSocket();
    }
}
