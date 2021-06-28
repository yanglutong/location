package com.sm.android.locations.locations.App;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.sm.android.locations.locations.Activity.AddParam.AddParamActivity;
import com.sm.android.locations.locations.Activity.AddParam.ParamActivity;
import com.sm.android.locations.locations.Activity.Login.LoginActivity;
import com.sm.android.locations.locations.Activity.Main.MainActivity;
import com.sm.android.locations.locations.Activity.PinConfig.PinConfigActivity;
import com.sm.android.locations.locations.Receiver.ConnectionChangeReceiver;
import com.sm.android.locations.locations.Test.ReceiveTask;
import com.sm.android.locations.locations.Utils.MainUtils.DbUtils;
import com.sm.android.locations.locations.Utils.MainUtils.MainUtils;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.SpBean;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.ZmBean;
import com.sm.android.locations.locations.Utils.OrmSqlLite.DBManagerZM;
import com.sm.android.locations.locations.Utils.ToastUtils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import me.jessyan.autosize.AutoSize;
import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.external.ExternalAdaptInfo;
import me.jessyan.autosize.external.ExternalAdaptManager;
import me.jessyan.autosize.internal.CustomAdapt;
import me.jessyan.autosize.onAdaptListener;
import me.jessyan.autosize.utils.LogUtils;

import static com.sm.android.locations.locations.Activity.Main.MainActivity.SPBEANLIST1;
import static com.sm.android.locations.locations.Activity.Main.MainActivity.SPBEANLIST2;
import static com.sm.android.locations.locations.Constant.Constant.BAND1;
import static com.sm.android.locations.locations.Constant.Constant.BAND2;
import static com.sm.android.locations.locations.Constant.Constant.BLACKLISTSB1;
import static com.sm.android.locations.locations.Constant.Constant.BLACKLISTSB2;
import static com.sm.android.locations.locations.Constant.Constant.BOARDTEMPERATURE1;
import static com.sm.android.locations.locations.Constant.Constant.BOARDTEMPERATURE2;
import static com.sm.android.locations.locations.Constant.Constant.CELLID1;
import static com.sm.android.locations.locations.Constant.Constant.CELLID2;
import static com.sm.android.locations.locations.Constant.Constant.DBM1;
import static com.sm.android.locations.locations.Constant.Constant.DBM2;
import static com.sm.android.locations.locations.Constant.Constant.DEVICENUMBER1;
import static com.sm.android.locations.locations.Constant.Constant.DEVICENUMBER2;
import static com.sm.android.locations.locations.Constant.Constant.DK1;
import static com.sm.android.locations.locations.Constant.Constant.DK2;
import static com.sm.android.locations.locations.Constant.Constant.DOWNPIN1;
import static com.sm.android.locations.locations.Constant.Constant.DOWNPIN2;
import static com.sm.android.locations.locations.Constant.Constant.DWON1;
import static com.sm.android.locations.locations.Constant.Constant.DWON2;
import static com.sm.android.locations.locations.Constant.Constant.ENDNUM1;
import static com.sm.android.locations.locations.Constant.Constant.ENDNUM2;
import static com.sm.android.locations.locations.Constant.Constant.GZMS1;
import static com.sm.android.locations.locations.Constant.Constant.GZMS2;
import static com.sm.android.locations.locations.Constant.Constant.HARDWAREVERSION1;
import static com.sm.android.locations.locations.Constant.Constant.HARDWAREVERSION2;
import static com.sm.android.locations.locations.Constant.Constant.IMSINUM1;
import static com.sm.android.locations.locations.Constant.Constant.IMSINUM2;
import static com.sm.android.locations.locations.Constant.Constant.IP1;
import static com.sm.android.locations.locations.Constant.Constant.IP2;
import static com.sm.android.locations.locations.Constant.Constant.MACADDRESS1;
import static com.sm.android.locations.locations.Constant.Constant.MACADDRESS2;
import static com.sm.android.locations.locations.Constant.Constant.PCI1;
import static com.sm.android.locations.locations.Constant.Constant.PCI2;
import static com.sm.android.locations.locations.Constant.Constant.PLMN1;
import static com.sm.android.locations.locations.Constant.Constant.PLMN2;
import static com.sm.android.locations.locations.Constant.Constant.REQUEST1;
import static com.sm.android.locations.locations.Constant.Constant.REQUEST2;
import static com.sm.android.locations.locations.Constant.Constant.SBZQ1;
import static com.sm.android.locations.locations.Constant.Constant.SBZQ2;
import static com.sm.android.locations.locations.Constant.Constant.SHUAIJIAN1;
import static com.sm.android.locations.locations.Constant.Constant.SHUAIJIAN2;
import static com.sm.android.locations.locations.Constant.Constant.SNNUMBER1;
import static com.sm.android.locations.locations.Constant.Constant.SNNUMBER2;
import static com.sm.android.locations.locations.Constant.Constant.SOFTWAREVERSION1;
import static com.sm.android.locations.locations.Constant.Constant.SOFTWAREVERSION2;
import static com.sm.android.locations.locations.Constant.Constant.TAC1;
import static com.sm.android.locations.locations.Constant.Constant.TAC2;
import static com.sm.android.locations.locations.Constant.Constant.TBTYPE1;
import static com.sm.android.locations.locations.Constant.Constant.TBTYPE2;
import static com.sm.android.locations.locations.Constant.Constant.TBZT1;
import static com.sm.android.locations.locations.Constant.Constant.TBZT2;
import static com.sm.android.locations.locations.Constant.Constant.TYPE1;
import static com.sm.android.locations.locations.Constant.Constant.TYPE2;
import static com.sm.android.locations.locations.Constant.Constant.UBOOTVERSION1;
import static com.sm.android.locations.locations.Constant.Constant.UBOOTVERSION2;
import static com.sm.android.locations.locations.Constant.Constant.UEIMS2;
import static com.sm.android.locations.locations.Constant.Constant.UEIMSI;
import static com.sm.android.locations.locations.Constant.Constant.UEMAX1;
import static com.sm.android.locations.locations.Constant.Constant.UEMAX2;
import static com.sm.android.locations.locations.Constant.Constant.UEMAXOF1;
import static com.sm.android.locations.locations.Constant.Constant.UEMAXOF2;
import static com.sm.android.locations.locations.Constant.Constant.UP1;
import static com.sm.android.locations.locations.Constant.Constant.UP2;
import static com.sm.android.locations.locations.Constant.Constant.ZENGYI1;
import static com.sm.android.locations.locations.Constant.Constant.ZENGYI2;
import static com.sm.android.locations.locations.Constant.Constant.ZHZQ1;
import static com.sm.android.locations.locations.Constant.Constant.ZHZQ2;
import static com.sm.android.locations.locations.Utils.MainUtils.MainUtils.StringAd;
import static com.sm.android.locations.locations.Utils.MainUtils.MainUtils.StringPin;
import static com.sm.android.locations.locations.Utils.MainUtils.MainUtils.StringTOIMEI;
import static com.sm.android.locations.locations.Utils.MainUtils.MainUtils.hexStringToString;
import static com.sm.android.locations.locations.Utils.MainUtils.MainUtils.mPressedTime1;

public class App extends Application {
    private static DBManagerZM dbManagerZM;
    Message message;
    Bundle bundle;
    DatagramPacket dp;
    byte[] buf;
    Timer timer1, timer2;
    boolean runThread = false;
    public static Context context;//需要使用的上下文对象;application实例

    //Application.class
    private static Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
//            mListener.heandleMessage(msg);
            System.out.println("mListener 地址值==== " + mListener);
        }
    };

    private static HandlerListener mListener;

    public static void setOnHandlerListener(HandlerListener listener) {
        mListener = listener;
    }

    public static HandlerListener getListener() {
        return mListener;
    }

    public interface HandlerListener {
        void heandleMessage(Message msg);
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        wifi状态的监听
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        ConnectionChangeReceiver myReceiver = new ConnectionChangeReceiver();
        this.registerReceiver(myReceiver, filter);

        context = getApplicationContext();
        isFirstStart(context);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        message = new Message();
        bundle = new Bundle();
        timer1 = new Timer();
        timer2 = new Timer();
        buf = new byte[1024];
        dp = new DatagramPacket(buf, buf.length);
        try {
            dbManagerZM = new DBManagerZM(context);


        } catch (SQLException e) {
            e.printStackTrace();
        }
//        MainUtils.ReceiveMain0(mHandler, message, bundle, timer1, timer2, dp, buf, context, runThread);//开启线程监听
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
//                for (int i = 0; i < 1005; i++) {
//                    try {
//                        sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    mHandler.sendEmptyMessage(i);
//                }
//            }
//        }.start();

//        ReceiveMain(mHandler, message, bundle, timer1, timer2, dp, buf, context, runThread);
        //当 App 中出现多进程, 并且您需要适配所有的进程, 就需要在 App 初始化时调用 initCompatMultiProcess()
        //在 Demo 中跳转的三方库中的 DefaultErrorActivity 就是在另外一个进程中, 所以要想适配这个 Activity 就需要调用 initCompatMultiProcess()
        AutoSize.initCompatMultiProcess(this);
        /**
         * 以下是 AndroidAutoSize 可以自定义的参数, {@link AutoSizeConfig} 的每个方法的注释都写的很详细
         * 使用前请一定记得跳进源码，查看方法的注释, 下面的注释只是简单描述!!!
         */
        AutoSizeConfig.getInstance()

                //是否让框架支持自定义 Fragment 的适配参数, 由于这个需求是比较少见的, 所以须要使用者手动开启
                //如果没有这个需求建议不开启
                .setCustomFragment(true)

                //是否屏蔽系统字体大小对 AndroidAutoSize 的影响, 如果为 true, App 内的字体的大小将不会跟随系统设置中字体大小的改变
                //如果为 false, 则会跟随系统设置中字体大小的改变, 默认为 false
//                .setExcludeFontScale(true)
                .setUseDeviceSize(true)
                //屏幕适配监听器
                .setOnAdaptListener(new onAdaptListener() {
                    @Override
                    public void onAdaptBefore(Object target, Activity activity) {
                        //使用以下代码, 可支持 Android 的分屏或缩放模式, 但前提是在分屏或缩放模式下当用户改变您 App 的窗口大小时
                        //系统会重绘当前的页面, 经测试在某些机型, 某些情况下系统不会重绘当前页面, ScreenUtils.getScreenSize(activity) 的参数一定要不要传 Application!!!
//                        AutoSizeConfig.getInstance().setScreenWidth(ScreenUtils.getScreenSize(activity)[0]);
//                        AutoSizeConfig.getInstance().setScreenHeight(ScreenUtils.getScreenSize(activity)[1]);
                        LogUtils.d(String.format(Locale.ENGLISH, "%s onAdaptBefore!", target.getClass().getName()));
                    }

                    @Override
                    public void onAdaptAfter(Object target, Activity activity) {
                        LogUtils.d(String.format(Locale.ENGLISH, "%s onAdaptAfter!", target.getClass().getName()));
                    }
                });

        //是否打印 AutoSize 的内部日志, 默认为 true, 如果您不想 AutoSize 打印日志, 则请设置为 false
//                .setLog(false)

        //是否使用设备的实际尺寸做适配, 默认为 false, 如果设置为 false, 在以屏幕高度为基准进行适配时
        //AutoSize 会将屏幕总高度减去状态栏高度来做适配
        //设置为 true 则使用设备的实际屏幕高度, 不会减去状态栏高度
//                .setUseDeviceSize(true)

        //是否全局按照宽度进行等比例适配, 默认为 true, 如果设置为 false, AutoSize 会全局按照高度进行适配
//                .setBaseOnWidth(false)

        //设置屏幕适配逻辑策略类, 一般不用设置, 使用框架默认的就好
//                .setAutoAdaptStrategy(new AutoAdaptStrategy())

        customAdaptForExternal();

    }

    //判断是否是第一次安装如果是第一次安装 则插入自带的系统频点
    public boolean isFirstStart(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(
                "SHARE_APP_TAG", 0);
        Boolean isFirst = preferences.getBoolean("FIRSTStart", true);
        if (isFirst) {// 第一次
            preferences.edit().putBoolean("FIRSTStart", false).commit();
//            Log.i("GFA", "一次");
//            int band, int down, String plmn, int type, int up, String tf, String yy
            //以下是插入频点设置 列表
            //移动TDD
            DbUtils.insertDB(context, 38, 37900, "46000", 0, 37900, "TDD", "移动");
            DbUtils.insertDB(context, 38, 38098, "46000", 0, 38098, "TDD", "移动");
            DbUtils.insertDB(context, 39, 38400, "46000", 0, 38400, "TDD", "移动");
            DbUtils.insertDB(context, 39, 38544, "46000", 0, 38544, "TDD", "移动");
            DbUtils.insertDB(context, 40, 38950, "46000", 0, 38950, "TDD", "移动");
            DbUtils.insertDB(context, 41, 40936, "46000", 0, 40936, "TDD", "移动");
            //移动FDD
            DbUtils.insertDB(context, 3, 1300, "46000", 0, 19300, "FDD", "移动");
            DbUtils.insertDB(context, 8, 3683, "46000", 0, 21683, "FDD", "移动");
            //联通FDD
            DbUtils.insertDB(context, 1, 375, "46001", 0, 18375, "FDD", "联通");
            DbUtils.insertDB(context, 3, 1650, "46001", 0, 19650, "FDD", "联通");
            //电信
            DbUtils.insertDB(context, 1, 100, "46011", 0, 18100, "FDD", "电信");
            DbUtils.insertDB(context, 3, 1825, "46011", 0, 19825, "FDD", "电信");
            DbUtils.insertDB(context, 3, 1850, "46011", 0, 19850, "FDD", "电信");
            DbUtils.insertDB(context, 5, 2452, "46011", 0, 20452, "FDD", "电信");
            //以下是插入扫频列表
            //移动TDD
            DbUtils.insertDBSaopin(context, 37900, 37900, 1);
            DbUtils.insertDBSaopin(context, 38098, 38098, 1);
            DbUtils.insertDBSaopin(context, 38400, 38400, 1);
            DbUtils.insertDBSaopin(context, 38544, 38544, 1);
            DbUtils.insertDBSaopin(context, 38950, 38950, 1);
            DbUtils.insertDBSaopin(context, 40936, 40936, 1);
            //移动FDD
            DbUtils.insertDBSaopin(context, 1300, 19300, 2);
            DbUtils.insertDBSaopin(context, 3683, 21683, 2);
            //联通FDD
            DbUtils.insertDBSaopin(context, 375, 18375, 3);
            DbUtils.insertDBSaopin(context, 1650, 19650, 3);
            //电信FDD
            DbUtils.insertDBSaopin(context, 100, 18100, 4);
            DbUtils.insertDBSaopin(context, 1825, 19825, 4);
            DbUtils.insertDBSaopin(context, 1850, 19850, 4);
            DbUtils.insertDBSaopin(context, 2452, 20452, 4);

            //以下是插入轮循设置列表
            //移动TDD
            DbUtils.insertDBLunxun(context, 37900, 37900, 1);
            DbUtils.insertDBLunxun(context, 38098, 38098, 1);
            DbUtils.insertDBLunxun(context, 38400, 38400, 1);
            DbUtils.insertDBLunxun(context, 38544, 38544, 1);
            DbUtils.insertDBLunxun(context, 38950, 38950, 1);
            DbUtils.insertDBLunxun(context, 40936, 40936, 1);
            //移动FDD
            DbUtils.insertDBLunxun(context, 1300, 19300, 2);
            DbUtils.insertDBLunxun(context, 3683, 21683, 2);
            //联通FDD
            DbUtils.insertDBLunxun(context, 375, 18375, 3);
            DbUtils.insertDBLunxun(context, 1650, 19650, 3);
            //电信FDD
            DbUtils.insertDBLunxun(context, 100, 18100, 4);
            DbUtils.insertDBLunxun(context, 1825, 19825, 4);
            DbUtils.insertDBLunxun(context, 1850, 19850, 4);
            DbUtils.insertDBLunxun(context, 2452, 20452, 4);
            //以下是插入小区配置
            DbUtils.xiaoqu(context);
            //插入增益初始化数据
            DbUtils.ZY(context, "20", "52", "72", 1, "20", "40", "72");//TDD
            DbUtils.ZY(context, "20", "52", "72", 2, "20", "40", "72");//FDD
//            以下是插入临时测试imsi
//            460005192822841
//            DbUtils.TestIMSI(context, "460002360893648", "王", "", "移动", false);
//            DbUtils.TestIMSI(context, "460005192822841", "南移动", "", "移动", false);
//            DbUtils.TestIMSI(context, "460010619201205", "范", "", "联通", false);
//            DbUtils.TestIMSI(context, "460011641414031", "南联通", "", "联通", false);
//
//
//            DbUtils.TestIMSIWhite(context, "460002360893648", "南玻万", "", "移动", true);
//            DbUtils.TestIMSIWhite(context, "460005192822841", "南移动", "", "移动", false);
//            DbUtils.TestIMSIWhite(context, "460010619201205", "范", "", "联通", false);
//            DbUtils.TestIMSIWhite(context, "460011641414031", "南联通", "", "联通", false);
            //注册码
            DbUtils.zc(context);
            return true;
        } else {
//            startActivity(new Intent(context, MainActivity.class));
//            finish();
//            Log.i("GFA", "N次");
//            Toast.makeText(getApplicationContext(),"N次",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    /**
     * 给外部的三方库 {@link Activity} 自定义适配参数, 因为三方库的 {@link Activity} 并不能通过实现
     * {@link CustomAdapt} 接口的方式来提供自定义适配参数 (因为远程依赖改不了源码)
     * 所以使用 {@link ExternalAdaptManager} 来替代实现接口的方式, 来提供自定义适配参数
     */
    private void customAdaptForExternal() {
        /**
         * {@link ExternalAdaptManager} 是一个管理外部三方库的适配信息和状态的管理类, 详细介绍请看 {@link ExternalAdaptManager} 的类注释
         */
        AutoSizeConfig.getInstance().getExternalAdaptManager()
                //加入的 Activity 将会放弃屏幕适配, 一般用于三方库的 Activity, 详情请看方法注释
                //如果不想放弃三方库页面的适配, 请用 addExternalAdaptInfoOfActivity 方法, 建议对三方库页面进行适配, 让自己的 App 更完美一点
//                .addCancelAdaptOfActivity(DefaultErrorActivity.class)

                //为指定的 Activity 提供自定义适配参数, AndroidAutoSize 将会按照提供的适配参数进行适配, 详情请看方法注释
                //一般用于三方库的 Activity, 因为三方库的设计图尺寸可能和项目自身的设计图尺寸不一致, 所以要想完美适配三方库的页面
                //就需要提供三方库的设计图尺寸, 以及适配的方向 (以宽为基准还是高为基准?)
                //三方库页面的设计图尺寸可能无法获知, 所以如果想让三方库的适配效果达到最好, 只有靠不断的尝试
                //由于 AndroidAutoSize 可以让布局在所有设备上都等比例缩放, 所以只要您在一个设备上测试出了一个最完美的设计图尺寸
                //那这个三方库页面在其他设备上也会呈现出同样的适配效果, 等比例缩放, 所以也就完成了三方库页面的屏幕适配
                //即使在不改三方库源码的情况下也可以完美适配三方库的页面, 这就是 AndroidAutoSize 的优势
                //但前提是三方库页面的布局使用的是 dp 和 sp, 如果布局全部使用的 px, 那 AndroidAutoSize 也将无能为力
                //经过测试 DefaultErrorActivity 的设计图宽度在 380dp - 400dp 显示效果都是比较舒服的
                //主界面
                .addExternalAdaptInfoOfActivity(MainActivity.class, new ExternalAdaptInfo(true, 400));
        //登陆页面
        AutoSizeConfig.getInstance().getExternalAdaptManager()
                .addExternalAdaptInfoOfActivity(LoginActivity.class, new ExternalAdaptInfo(true, 400));
        AutoSizeConfig.getInstance().getExternalAdaptManager()
                //添加参数
                .addExternalAdaptInfoOfActivity(AddParamActivity.class, new ExternalAdaptInfo(true, 400));
        AutoSizeConfig.getInstance().getExternalAdaptManager()
                //参数列表
                .addExternalAdaptInfoOfActivity(ParamActivity.class, new ExternalAdaptInfo(true, 400));
        AutoSizeConfig.getInstance().getExternalAdaptManager()
                //配置列表
                .addExternalAdaptInfoOfActivity(PinConfigActivity.class, new ExternalAdaptInfo(true, 400));
    }

    public static String str3 = "";
    public static String str4 = "";

    public static Context getContexts() {
        return context;
    }

    private static long ZTLONG = 8000; //连接中
    private static long ZTLONGS = 180000;//连接失败
    public static long mPressedTime2 = 0;
    private static boolean ThreadFlag = true;
    // 指定格式化格式
    public static SimpleDateFormat ReceiveMainDateFormat1 = new SimpleDateFormat("" + "yyyy-MM-dd HH:mm:ss ");
    public static SimpleDateFormat ReceiveMainDateFormat2 = new SimpleDateFormat("" + "HH:mm:ss ");

    public static void ReceiveMain(final Handler handler, Message messages, final Bundle bundle, final Timer timer1, final Timer timer2, final DatagramPacket dp, final byte[] buf, final Context context, final boolean runThread) {


        mPressedTime1 = System.currentTimeMillis();
        mPressedTime2 = System.currentTimeMillis();
        Timer timers = new Timer();
        timers.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                long mNowTime = System.currentTimeMillis();//获取第一次按键时间
                if ((mNowTime - mPressedTime1) > ZTLONG) {//超过八秒就是未启动
//                    ToastUtils.showToast("再按一次退出程序");
//                        mPressedTime = mNowTime;


                    if ((mNowTime - mPressedTime1) > ZTLONGS) {
                        Message message = new Message();
                        Bundle bundle = new Bundle();
                        bundle.putString("zt1", "1");
                        message.setData(bundle);
                        handler.sendMessage(message);
                        message.what = 100120;
                        mListener.heandleMessage(message);
                    } else {
//                            Log.d(TAG, "mPressedTimerun1: ");
                        //设备1状态设定
                        Message message = new Message();
                        Bundle bundle = new Bundle();
                        bundle.putString("zt1", "0");
                        message.setData(bundle);
                        handler.sendMessage(message);
                        message.what = 100120;
                        mListener.heandleMessage(message);
                    }
                } else {//代表启动
//                        Log.d(TAG, "mPressedTimerun2: ");
//                        System.exit(0);
                }
                if ((mNowTime - mPressedTime2) > ZTLONG) {//超过八秒就是未启动
//                    ToastUtils.showToast("再按一次退出程序");
//                        mPressedTime = mNowTime;
//                        Log.d(TAG, "mPressedTimerun1: ");

//                        //设备2状态设定
//                        Message message = new Message();
//                        bundle.putString("zt2", "0");
//                        message.setData(bundle);
//                        handler.sendMessage(message);
//                        message.what = 200120;
                    if ((mNowTime - mPressedTime2) > ZTLONGS) {
                        Message message = new Message();
                        Bundle bundle = new Bundle();
                        bundle.putString("zt2", "1");
                        message.setData(bundle);
                        handler.sendMessage(message);
                        message.what = 200120;
                        mListener.heandleMessage(message);
                    } else {
                        Message message = new Message();
                        Bundle bundle = new Bundle();
                        bundle.putString("zt2", "0");
                        message.setData(bundle);
                        handler.sendMessage(message);
                        message.what = 200120;
                        mListener.heandleMessage(message);
                    }

                } else {//代表启动
//                        Log.d(TAG, "mPressedTimerun2: ");
//                        System.exit(0);
                }
            }
        }, 0, 5000);
//
//        mPressedTime1 = System.currentTimeMillis();
//        mPressedTime2 = System.currentTimeMillis();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    long mNowTime = System.currentTimeMillis();//获取第一次按键时间
//                    if ((mNowTime - mPressedTime1) > ZTLONG) {//超过八秒就是未启动
////                    ToastUtils.showToast("再按一次退出程序");
////                        mPressedTime = mNowTime;
//
//
//                        if ((mNowTime - mPressedTime1) > ZTLONGS) {
//                            Message message = new Message();
//                            Bundle bundle = new Bundle();
//                            bundle.putString("zt1", "1");
//                            message.setData(bundle);
//                            handler.sendMessage(message);
//                            message.what = 100120;
//                        } else {
////                            Log.d(TAG, "mPressedTimerun1: ");
//                            //设备1状态设定
//                            Message message = new Message();
//                            Bundle bundle = new Bundle();
//                            bundle.putString("zt1", "0");
//                            message.setData(bundle);
//                            handler.sendMessage(message);
//                            message.what = 100120;
//                        }
//
//                    } else {//代表启动
////                        Log.d(TAG, "mPressedTimerun2: ");
////                        System.exit(0);
//                    }
//                    if ((mNowTime - mPressedTime2) > ZTLONG) {//超过八秒就是未启动
////                    ToastUtils.showToast("再按一次退出程序");
////                        mPressedTime = mNowTime;
////                        Log.d(TAG, "mPressedTimerun1: ");
//
////                        //设备2状态设定
////                        Message message = new Message();
////                        bundle.putString("zt2", "0");
////                        message.setData(bundle);
////                        handler.sendMessage(message);
////                        message.what = 200120;
//                        if ((mNowTime - mPressedTime2) > ZTLONGS) {
//                            Message message = new Message();
//                            Bundle bundle = new Bundle();
//                            bundle.putString("zt2", "1");
//                            message.setData(bundle);
//                            handler.sendMessage(message);
//                            message.what = 200120;
//                        } else {
//                            Message message = new Message();
//                            Bundle bundle = new Bundle();
//                            bundle.putString("zt2", "0");
//                            message.setData(bundle);
//                            handler.sendMessage(message);
//                            message.what = 200120;
//                        }
//
//                    } else {//代表启动
////                        Log.d(TAG, "mPressedTimerun2: ");
////                        System.exit(0);
//                    }
//                }
//
//            }
//        });
        new Thread(new Runnable() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                DatagramSocket ds = null;
                int opionts1 = 0;
                int opionts2 = 0;

                int opionts11 = 0;
                int opionts22 = 0;
                ThreadFlag = true;
                Log.d("wppn", "whiletry ");
                bundle.putString("msgWifi", 222 + "");
                Message message = new Message();
                message.setData(bundle);
                handler.sendMessage(message);
                message.what = 100;
//                mListener.heandleMessage(message);
                try {
                    ds = new DatagramSocket(3345);

                    if (ds == null) {
                        ds = new DatagramSocket(null);
                        ds.setReuseAddress(true);
                        ds.bind(new InetSocketAddress(3345));
                    }
                    //设备1状态设定
                    message = new Message();
                    bundle.putString("zt1", "0");
                    message.setData(bundle);
                    handler.sendMessage(message);
                    message.what = 100120;
                    mListener.heandleMessage(message);
                    //设备2状态设定
                    message = new Message();
                    bundle.putString("zt2", "0");
                    message.setData(bundle);
                    handler.sendMessage(message);
                    message.what = 200120;
                    mListener.heandleMessage(message);
                    byte[] buf = new byte[1024];
//                     dp = new DatagramPacket(buf, buf.length);
//                    mPressedTime1 = System.currentTimeMillis();
//                    mPressedTime2 = System.currentTimeMillis();

                } catch (Exception e) {

                }
                while (true) {
//                        bundle.putString("msgWifi", 222 + "");
//                        message = new Message();
//                        message.setData(bundle);
//                        handler.sendMessage(message);
//                        message.what = 100;
                    //通过udp的socket服务将数据包接收到，通过receive方法
//                        ds.setSoTimeout((int) timerDate);//延时操作
//                        if (wifiFlag==false){
//                            return;
//                        }
                    try {
                        ds.receive(dp);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    byte[] buf1 = dp.getData();
                    //btye[]字节数组转换成16进制的字符串
                    String str = ReceiveTask.toHexString1(buf1);
                    //String str = new String(dp.getData(),0,dp.getLength());
                    String ID1TF = "";
                    String ID2TF = "";
                    if (IP1.equals(dp.getAddress().getHostAddress())) {
                        mPressedTime1 = System.currentTimeMillis();
                        System.out.println("123456");
                        System.out.println("收到" + dp.getAddress().getHostAddress() + "发送数据：" + str);

                        //时间
                        Date d = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        System.out.println("时间：" + sdf.format(d));
                        //小区基本信息状态查询响应
                        if ("2cf0".equals(str.substring(8, 12))) {

                            if ("00000000".equals(str.substring(24, 32))) {
                                System.out.println("设备型号：" + hexStringToString(str.substring(32, 46)));
                                DEVICENUMBER1 = hexStringToString(str.substring(32, 46));
                            } else if ("01000000".equals(str.substring(24, 32))) {
                                System.out.println("硬件版本：" + hexStringToString(str.substring(32, 34)));
                                HARDWAREVERSION1 = hexStringToString(str.substring(32, 34));
                            } else if ("02000000".equals(str.substring(24, 32))) {
                                System.out.println("软件版本：" + hexStringToString(str.substring(32, 106)));
                                SOFTWAREVERSION1 = hexStringToString(str.substring(32, 106));
                            } else if ("03000000".equals(str.substring(24, 32))) {
                                System.out.println("序列号SN:" + hexStringToString(str.substring(32, 70)));
                                SNNUMBER1 = hexStringToString(str.substring(32, 70));
                            } else if ("04000000".equals(str.substring(24, 32))) {
                                System.out.println("MAC地址：" + hexStringToString(str.substring(32, 66)));
                                MACADDRESS1 = hexStringToString(str.substring(32, 66));
                            } else if ("05000000".equals(str.substring(24, 32))) {
                                UBOOTVERSION1 = hexStringToString(str.substring(32, 47));
                                System.out.println("uboot版本号：" + hexStringToString(str.substring(32, 47)));
                            } else if ("06000000".equals(str.substring(24, 32))) {
                                System.out.println("板卡温度：" + hexStringToString(str.substring(32, 50)));
                                BOARDTEMPERATURE1 = hexStringToString(str.substring(32, 50));
                            }
                        }

                        if ("66f0".equals(str.substring(8, 12))) {
                            REQUEST1 = Integer.parseInt(StringPin(str.substring(24, 32)), 16) + "";
                            ENDNUM1 = Integer.parseInt(StringPin(str.substring(32, 40)), 16) + "";
                            IMSINUM1 = Integer.parseInt(StringPin(str.substring(56, 64)), 16) + "";
//                            Log.d("Asubstring66f0", "--run: " + Integer.parseInt(str.substring(24, 26), 16)+"/n"+ENDNUM1+"/n"+IMSINUM1);
                            String substring = str.substring(24, 32);
                            String substring1 = str.substring(32, 40);
                            String substring2 = str.substring(56, 64);
//                            Log.d("Asubstring66f0REQUEST1", "run: "+substring.toString());
//                            Log.d("Asubstring66f0ENDNUM1", "run: "+substring1.toString());
//                            Log.d("Asubstring66f0IMSINUM1", "run: "+substring2.toString());
//                            Log.d("Asubstring66f0", "run: "+str.toString());

                            Log.d("Asubstring66f0REQUEST1", "run: " + REQUEST1);
                            Log.d("Asubstring66f0ENDNUM1", "run: " + ENDNUM1);
                            Log.d("Asubstring66f0IMSINUM1", "run: " + IMSINUM1);
                            Log.d("Asubstring66f0", "run: " + str.toString());
                        }
                        if ("2ef0".equals(str.substring(8, 12))) {
                            Log.d("nzq2df0", str.toString());
                            System.out.println("同步类型" + str.toString());
                            String substring = str.substring(25, 26);
                            String substring2 = str.substring(29, 30);
                            TBTYPE1 = substring.toString();
                            TBZT1 = str.substring(29, 30);
                            Log.d("Asubstringzt1", "run: " + substring2.toString());
                            Log.d("Asubstringzt1", "run: " + str.toString());
                        }
//                        if (str.contains("2ef0")) {
//                            System.out.println("同步类型--" + str.toString());
//                        }
//                        if ("f02d".equals(str.substring(13, 14))) {
//                            Log.d("nzq2df0",str.toString());
//                            System.out.println("同步类型"+str.toString());
//                        }
                        //当前小区信息状态响应
                        if ("28f0".equals(str.substring(8, 12))) {
                            //PLMN
//                            System.out.println(StringTOIMEI(str.substring(40, 50)));
                            PLMN1 = StringTOIMEI(str.substring(40, 50));

                            //上行频点
                            System.out.println("我上行上行频点" + Integer.parseInt(StringPin(str.substring(24, 32)), 16));
                            UP1 = Integer.parseInt(StringPin(str.substring(24, 32)), 16) + "";
                            //下行频点
                            System.out.println(Integer.parseInt(StringPin(str.substring(32, 40)), 16));
                            DWON1 = Integer.parseInt(StringPin(str.substring(32, 40)), 16) + "";
                            //Band
                            System.out.println(Integer.parseInt(StringPin(str.substring(56, 64)), 16));
                            BAND1 = Integer.parseInt(StringPin(str.substring(56, 64)), 16) + "";
                            //带宽
                            System.out.println(Integer.parseInt(str.substring(54, 56), 16));
                            DK1 = Integer.parseInt(str.substring(54, 56), 16) + "";
                            //TAC
                            System.out.println(Integer.parseInt(StringPin(str.substring(68, 72)), 16));
                            TAC1 = Integer.parseInt(StringPin(str.substring(68, 72)), 16) + "";
                            //PCI
                            System.out.println(Integer.parseInt(StringPin(str.substring(64, 68)), 16));
                            PCI1 = Integer.parseInt(StringPin(str.substring(64, 68)), 16) + "";
                            //Cellld
                            System.out.println(Integer.parseInt(StringPin(str.substring(72, 80)), 16));
                            CELLID1 = Integer.parseInt(StringPin(str.substring(72, 80)), 16) + "";
                            //UePMax
                            System.out.println(Integer.parseInt(StringPin(str.substring(80, 84)), 16));
                            DBM1 = Integer.parseInt(StringPin(str.substring(80, 84)), 16) + "";
                            //EnodeBPMax
                            System.out.println(Integer.parseInt(StringPin(str.substring(84, 88)), 16));
//                            TYPE1 = Integer.parseInt(StringPin(str.substring(84, 88)), 16) + "";

                        }
                        //小区状态信息查询指令的响应
                        if ("30f0".equals(str.substring(8, 12))) {
                            if ("00000000".equals(str.substring(24, 32))) {
                                System.out.println("小区空闲态");
                                TYPE1 = "小区空闲态";
                            } else if ("01000000".equals(str.substring(24, 32))) {
                                System.out.println("同步或扫频中");
                                TYPE1 = "同步或扫频中";
                            } else if ("02000000".equals(str.substring(24, 32))) {
                                System.out.println("小区激活中");
                                TYPE1 = "小区激活中";
                            } else if ("03000000".equals(str.substring(24, 32))) {
                                System.out.println("小区已激活");
                                TYPE1 = "小区已激活";
                            } else if ("04000000".equals(str.substring(24, 32))) {
                                System.out.println("小区去激活中");
                                TYPE1 = "小区去激活中";
                            }
                        }

                        //UE测量配置查询响应信息
                        if ("3ef0".equals(str.substring(8, 12))) {
                            //工作模式
                            if ("00".equals(str.substring(24, 26))) {
                                System.out.println("持续侦码模式");
                                GZMS1 = "持续侦码模式";
                            } else if ("01".equals(str.substring(24, 26))) {
                                System.out.println("周期侦码模式");
                                //周期模式参数，指示针对同一个IMSI 的抓号周期
                                System.out.println(Integer.parseInt(StringPin(str.substring(28, 32)), 16));
                                ZHZQ1 = "" + Integer.parseInt(StringPin(str.substring(28, 32)), 16);
                                GZMS1 = "周期侦码模式";
                            } else if ("02".equals(str.substring(24, 26))) {
                                System.out.println("定位模式");
                                GZMS1 = "定位模式";
                                //定位模式，定位的终端 IMSI
                                UEIMSI = StringTOIMEI(str.substring(32, 62));
                                System.out.println("imsi:" + StringTOIMEI(str.substring(32, 62)));
                                //定位模式，终端测量值的上报周期,建议设置为 1024ms, 0：120ms,1：240ms,2：480ms,3：640ms,4：1024ms,5：2048ms

                                if ("00".equals(str.substring(66, 68))) {
                                    System.out.println("120ms");
                                    SBZQ1 = "120ms";
                                } else if ("01".equals(str.substring(66, 68))) {
                                    System.out.println("240ms");
                                    SBZQ1 = "240ms";
                                } else if ("02".equals(str.substring(66, 68))) {
                                    System.out.println("480ms");
                                    SBZQ1 = "480ms";
                                } else if ("03".equals(str.substring(66, 68))) {
                                    System.out.println("640ms");
                                    SBZQ1 = "640ms";
                                } else if ("04".equals(str.substring(66, 68))) {
                                    System.out.println("1024ms");
                                    SBZQ1 = "1024ms";
                                } else if ("05".equals(str.substring(66, 68))) {
                                    System.out.println("2048ms");
                                    SBZQ1 = "2048ms";
                                }
                                //定位模式，调度定位终端最大功率发射开关，需要设置为 0,0：enable,1：disable
                                if ("00".equals(str.substring(68, 70))) {
                                    System.out.println("enable");
                                    UEMAXOF1 = "开";
                                } else if ("01".equals(str.substring(68, 70))) {
                                    System.out.println("disable");
                                    UEMAXOF1 = "关";
                                }
                                //定位模式，UE 最大发射功率，最大值 不 超 过wrFLLmtToEnbSysArfcnCfg 配置的UePMax，建议设置为 23dBm
                                UEMAX1 = Integer.parseInt(str.substring(70, 72), 16) + "";
                                System.out.println(Integer.parseInt(str.substring(70, 72), 16));

                                //定位模式，由于目前都采用 SRS 方案配合单兵，因此此值需要设置为disable，否则大用户量时有定位终端掉线概率。0: disable,1: enable
                                if ("00".equals(str.substring(72, 74))) {
                                    System.out.println("disable");
                                } else if ("01".equals(str.substring(72, 74))) {
                                    System.out.println("enable");
                                }
                                //定位模式，是否把非定位终端踢回公网的配置，建议设置为 0。设置为 1 的方式是为了发布版本时保留用户反复接入基站测试使用。1：非定位终端继续被本小区吸附, 0：把非定位终端踢回公网
                                if ("00".equals(str.substring(76, 78))) {
                                    System.out.println("把非定位终端踢回公网");
                                } else if ("01".equals(str.substring(76, 78))) {
                                    System.out.println("非定位终端继续被本小区吸附");
                                }
                                //定位模式，是否对定位终端建立SRS 配置。
                                if ("00".equals(str.substring(78, 80))) {
                                    System.out.println("disable");
                                } else if ("01".equals(str.substring(78, 80))) {
                                    System.out.println("enable");
                                }
                            } else if ("03".equals(str.substring(24, 26))) {
                                System.out.println("管控模式");
                                GZMS1 = "管控模式";
                                //管控模式的子模式0：黑名单子模式；1：白名单子模式
                                if ("00".equals(str.substring(80, 82))) {
                                    System.out.println("黑名单子模式");
                                    GZMS1 = "黑名单子模式";
                                } else if ("01".equals(str.substring(80, 82))) {
                                    System.out.println("白名单子模式");
                                    GZMS1 = "白名单子模式";
                                }
                            } else if ("04".equals(str.substring(24, 26))) {
                                System.out.println("重定向模式");
                                GZMS1 = "重定向模式";
                            /*0: 名单中的用户执行重定向；名单外的全部踢回公网
                            1: 名单中的用户踢回公网；名单外的全部重定向
							2: 名单中的用户执行重定向；名单外的全部吸附在本站
							3: 名单中的用户吸附在本站;名单外的全部重定向
							4: 所有目标重定向*/
                                System.out.println(Integer.parseInt(str.substring(26, 28), 16));

                            }

                        }
//定位模式黑名单查询响应
                        if ("56f0".equals(str.substring(8, 12))) {

                            //黑名单数量
                            Integer blacklistNum = Integer.parseInt(StringPin(str.substring(24, 28)), 16);
                            int begin = 28;
                            int end = 58;
                            for (int i = 0; i < blacklistNum; i++) {
                                System.out.println("黑名单打印" + StringTOIMEI(str.substring(begin, end)));
                                BLACKLISTSB1.add(StringTOIMEI(str.substring(begin, end)));
                                begin = begin + 34;
                                end = end + 34;
                            }

                        }
                        //接受增益和发射功率查询的响应
                        if ("32f0".equals(str.substring(8, 12))) {
                            //增益
                            //寄存器中的值，实际生效的值（FDD 模式下仅在建立完小区查询，该值有效）
                            System.out.println("我增益1" + Integer.parseInt(str.substring(24, 26), 16));
                            ZENGYI1 = Integer.parseInt(str.substring(24, 26), 16) + "";
                            //发射功率  衰减
                            //寄存器中的值，实际生效的值（FDD 模式下仅在建立完小区查询，该值有效）
                            System.out.println(Integer.parseInt(str.substring(28, 30), 16));
                            SHUAIJIAN1 = Integer.parseInt(str.substring(28, 30), 16) + "";
                            System.out.println("我衰减1" + Integer.parseInt(str.substring(24, 26), 16));

//                            //数据库中的保存值，重启保留生效的值,
//                            System.out.println(Integer.parseInt(str.substring(26,28),16));
//
//                            //数据库中的保存值，重启保留生效的值
//                            System.out.println(Integer.parseInt(str.substring(30,32),16));
//                            //FDD AGC 开关
//                            if("00".equals(str.substring(30,32))){
//                                System.out.println("FDD AGC 开关关闭");
//                            }else if("01".equals(str.substring(30,32))){
//                                System.out.println("FDD AGC 开关打开");
//                            }

                            //增益
                            //只在FDD模式下有效，寄存器中的值，实际生效的值,该值只有在扫频完成后，建立小区前查询有效
//                            System.out.println(Integer.parseInt(str.substring(34,36),16));
//                            //eeprom 中的保存值，重启保留生效的值
//                            System.out.println(Integer.parseInt(str.substring(36,38),16));
                        }
                        //设置黑名单响应 清空响应
                        if ("3af0".equals(str.substring(8, 12))) {
                            if ("00000000".equals(str.substring(24, 32))) {
                                System.out.println("设置基站白名单" + opionts1);
                            }

                        }
                        //设置黑名单响应 清空响应
                        if ("54f0".equals(str.substring(8, 12))) {
                            if ("00000000".equals(str.substring(24, 32))) {
                                opionts1++;
                                System.out.println("设置基站黑名单成功" + opionts1);
                                if (opionts1 % 2 == 0) {
                                    Log.d("jsgs", "run:偶数");
                                    message = new Message();
                                    bundle.putString("100130", "FDD");
                                    message.setData(bundle);
                                    handler.sendMessage(message);
                                    message.what = 100131;
                                    mListener.heandleMessage(message);
                                } else {
                                    Log.d("jsgs", "run:基数");
                                    message = new Message();
                                    bundle.putString("100131", "FDD");
                                    message.setData(bundle);
                                    handler.sendMessage(message);
                                    message.what = 100130;
                                    mListener.heandleMessage(message);
                                }
                            } else {
                                System.out.println("设置基站黑名单失败");

                                if (opionts2 % 2 == 0) {
                                    Log.d("jsgs", "run:偶数");
                                    message = new Message();
                                    bundle.putString("100130", "FDD");
                                    message.setData(bundle);
                                    handler.sendMessage(message);
                                    message.what = 100135;
                                    mListener.heandleMessage(message);
                                } else {
                                    Log.d("jsgs", "run:基数");
                                    message = new Message();
                                    bundle.putString("100131", "FDD");
                                    message.setData(bundle);
                                    handler.sendMessage(message);
                                    message.what = 100134;
                                    mListener.heandleMessage(message);
                                }


                            }
                        }
                        //黑名单中标
                        if ("05f0".equals(str.substring(8, 12))) {
                            String down = "";
                            System.out.println("1黑名单中标IMSI号：" + hexStringToString(str.substring(32, 62)));
                            //当前小区信息状态响应
//                            if ("28f0".equals(str.substring(8, 12))) {
//                                down = Integer.parseInt(StringPin(str.substring(32, 40)), 16) + "";
//                            }
//                            down=Integer.parseInt(StringPin(str.substring(40, 48)), 16)+"";
                            Date now = new Date();
                            String imsi = hexStringToString(str.substring(32, 62));
                            Log.d("jsgs", "run:基数");
                            message = new Message();
                            bundle.putString("imsi", imsi);
                            bundle.putString("sb", "1");
                            bundle.putString("zb", "");
                            bundle.putString("datetime", "" + ReceiveMainDateFormat1.format(now));
                            bundle.putString("time", "" + ReceiveMainDateFormat2.format(now));
//                            bundle.putString("zmdown", "" + down);
                            message.setData(bundle);
                            handler.sendMessage(message);
                            message.what = 30000;
                            mListener.heandleMessage(message);
                            if (!TextUtils.isEmpty(DOWNPIN1)) {
                                ZmBean zmBean = new ZmBean();
                                zmBean.setDown(DOWNPIN1);
                                zmBean.setSb("1");
                                zmBean.setImsi(imsi);
                                zmBean.setTime(ReceiveMainDateFormat1.format(now));
                                zmBean.setDatatime(ReceiveMainDateFormat1.format(now));
                                try {
                                    int i = dbManagerZM.insertAddZmBean(zmBean);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }

                            Log.d("Aaimsi", "imsirun: " + imsi);
                            System.out.println("Imsi黑名单中标" + imsi + "down" + down);

//
//                            bundle.putString("imsi", imsi);
//                            bundle.putString("sb", "1");
//                            bundle.putString("zb", "");
//                            bundle.putString("datetime", "" + ReceiveMainDateFormat1.format(now));
//                            bundle.putString("time", "" + ReceiveMainDateFormat2.format(now));

//                            message.setData(bundle);
//                            handler.sendMessage(message);
//                            message.what = 3000000;

                        }

                        //设置定位模式的应答信息
                        if ("07f0".equals(str.substring(8, 12))) {
                            if ("00000000".equals(str.substring(24, 32))) {
                                System.out.println("设置基站ue定位成功");

                                message = new Message();
                                bundle.putString("test", "");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 100136;
                                mListener.heandleMessage(message);
                            } else {
                                System.out.println("设置基站ue定位失败");


                                message = new Message();
                                bundle.putString("test", "");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 100137;
                                mListener.heandleMessage(message);
                            }
                        }
                        //重启是否成功
                        if ("0cf0".equals(str.substring(8, 12))) {
                            //成功0；不成功>0（16进制字符串转换成十进制）
                            int row = Integer.parseInt(str.substring(24, 32), 16);
                            if (row == 0) {
                                System.out.println("重启设置成功！");
                                message = new Message();
                                bundle.putString("test", "");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 100138;
                            } else {
                                System.err.println("重启失败！");
                                message = new Message();
                                bundle.putString("test", "");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 100139;
                                mListener.heandleMessage(message);
                            }
                        }
                        //制式切换是否成功
                        if ("02f0".equals(str.substring(8, 12))) {
                            //成功0；不成功>0（16进制字符串转换成十进制）
                            int row = Integer.parseInt(str.substring(24, 32), 16);
                            if (row == 0) {
                                System.out.println("制式切换成功！");
                                message = new Message();
                                bundle.putString("test", "");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 100140;
                                mListener.heandleMessage(message);
                            } else {
                                System.err.println("制式切换失败");
                                message = new Message();
                                bundle.putString("test", "");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 100141;
                                mListener.heandleMessage(message);
                            }
                        }
                        //模式：FDD TDD
                        if ("00ff".equals(str.substring(16, 20))) {
                            //设置模式FDD
                            ID1TF = "FDD";
                            System.err.println("FDD");
                            message = new Message();
                            bundle.putString("tf1", "FDD");
                            message.setData(bundle);
                            handler.sendMessage(message);
                            message.what = 100110;
                            mListener.heandleMessage(message);
                        }
                        if ("ff00".equals(str.substring(16, 20))) {
                            //设置模式TDD
                            ID1TF = "TDD";
                            System.out.println("TDD");
                            message = new Message();
                            bundle.putString("tf1", "TDD");
                            message.setData(bundle);
                            handler.sendMessage(message);
                            message.what = 100110;
                            mListener.heandleMessage(message);
                        }
                        //建立小区是发送否成功1
                        if ("04f0".equals(str.substring(8, 12))) {
                            //成功0；不成功>0（16进制字符串转换成十进制）
                            int row = Integer.parseInt(str.substring(24, 32), 16);
                            Log.d("wtto", "04f0run:0 ");
                            if (row == 0) {
                                message = new Message();
                                bundle.putString("test", "0");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 100145;
                                mListener.heandleMessage(message);
                                System.out.println("设置成功！开始建立小区！");
                                Log.d("wtto", "04f0run:1 ");
                            } else {
                                System.err.println("设置失败！");
                                message = new Message();
                                bundle.putString("test", "0");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 100146;
                                mListener.heandleMessage(message);
                                Log.d("wtto", "04f0run:2 ");
                            }
                        }
                        //公放设置
//                        if ("a0f0".equals(str.substring(8, 12))) {
//                            //成功0；不成功>0（16进制字符串转换成十进制）
//                            int row = Integer.parseInt(str.substring(24, 32), 16);
//                            if (row == 0) {
//                                System.out.println("公放设置成功！");
//                                message = new Message();
//                                bundle.putString("test", "0");
//                                message.setData(bundle);
//                                handler.sendMessage(message);
//                                message.what = 100142;
//                            } else {
//                                System.err.println("设置失败！");
//                                message = new Message();
//                                bundle.putString("test", "0");
//                                message.setData(bundle);
//                                handler.sendMessage(message);
//                                message.what = 100143;
//                            }
//                        }
                        //原来的功放设置响应,现改为风扇开关
                        if ("a0f0".equals(str.substring(8, 12))) {
                            //成功0；不成功>0（16进制字符串转换成十进制）
                            int row = Integer.parseInt(str.substring(24, 32), 16);
                            if (row == 0) {
                                System.out.println("风扇开启成功");
                                message = new Message();
                                bundle.putString("test", "0");
                                message.setData(bundle);
                                handler.sendMessage(message);
//                                message.what = 100142;
                                mListener.heandleMessage(message);
                            } else {
                                System.err.println("风扇开启失败");
                                message = new Message();
                                bundle.putString("test", "0");
                                message.setData(bundle);
                                handler.sendMessage(message);
//                                message.what = 100143;

                                mListener.heandleMessage(message);
                            }
                        }
                        //去激活小区是否成功
                        if ("0ef0".equals(str.substring(8, 12))) {
                            //成功0；不成功>0（16进制字符串转换成十进制）
                            int row = Integer.parseInt(str.substring(24, 32), 16);
                            if (row == 0) {
                                System.out.println("设置成功！去激活小区成功，停止定位！");
                            } else {
                                System.err.println("设置失败！");
                            }
                        }
                        //基站执行状态
                        if ("19f0".equals(str.substring(8, 12))) {
                            String state = str.substring(24, 32);
                            System.out.println("state" + state);
                            Log.d("wtto", "staterun: " + state);
                            if ("00000000".equals(state)) {
                                System.err.println("空口同步成功");
                                Log.d("wtto", "qqqrun:空口同步成功");

                                message = new Message();
                                bundle.putString("test", "1");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 1001200;
                                mListener.heandleMessage(message);
                            } else if ("01000000".equals(state)) {
                                System.err.println("空口同步失败");
                                Log.d("wtto", "qqqrun:空口同步失败");
                                message = new Message();
                                bundle.putString("test", "2");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 1001200;
                                mListener.heandleMessage(message);
                            } else if ("02000000".equals(state)) {
                                System.err.println("GPS同步成功");
                                Log.d("wtto", "qqqrun:GPS同步成功");
                                message = new Message();
                                bundle.putString("test", "3");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 1001200;
                                mListener.heandleMessage(message);
                            } else if ("03000000".equals(state)) {
                                System.err.println("GPS同步失败");
                                Log.d("wtto", "qqqrun:GPS同步失败");
                                message = new Message();
                                bundle.putString("test", "4");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 1001200;
                                mListener.heandleMessage(message);
                            } else if ("04000000".equals(state)) {
                                System.err.println("扫频成功");
                                Log.d("wtto", "qqqrun:扫频成功");
                                message = new Message();
                                bundle.putString("test", "5");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 1001200;
                                mListener.heandleMessage(message);
                            } else if ("05000000".equals(state)) {
                                System.err.println("扫频失败");
                                Log.d("wtto", "qqqrun:扫频失败");
                                message = new Message();
                                bundle.putString("test", "6");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 1001200;
                                mListener.heandleMessage(message);
                            } else if ("06000000".equals(state)) {
                                System.err.println("小区激活成功");
                                Log.d("wtto", "qqqrun:小区激活成功");
                                message = new Message();
                                bundle.putString("test", "7");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 1001200;
                                mListener.heandleMessage(message);
                            } else if ("07000000".equals(state)) {
                                System.err.println("小区激活失败");
                                Log.d("wtto", "qqqrun:小区激活失败");
                                message = new Message();
                                bundle.putString("test", "8");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 1001200;
                                mListener.heandleMessage(message);
                            } else if ("08000000".equals(state)) {
                                System.err.println("小区去激活");
                                Log.d("wtto", "qqqrun:小区去激活");
                                message = new Message();
                                bundle.putString("test", "9");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 1001200;
                                mListener.heandleMessage(message);
                            } else if ("09000000".equals(state)) {
                                System.err.println("空口同步中");
                                Log.d("wtto", "qqqrun:空口同步中");
                                message = new Message();
                                bundle.putString("test", "10");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 1001200;
                                mListener.heandleMessage(message);
                            } else if ("0a000000".equals(state)) {
                                System.err.println("GPS同步中");
                                Log.d("wtto", "qqqrun:GPS同步中");
                                message = new Message();
                                bundle.putString("test", "11");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 1001200;
                                mListener.heandleMessage(message);
                            } else if ("0b000000".equals(state)) {
                                System.err.println("扫频中");
                                Log.d("wtto", "qqqrun:扫频中");
                                message = new Message();
                                bundle.putString("test", "12");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 1001200;
                                mListener.heandleMessage(message);
                            } else if ("0c000000".equals(state)) {
                                System.err.println("小区激活中");
                                Log.d("wtto", "qqqrun:小区激活中");
                                message = new Message();
                                bundle.putString("test", "13");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 1001200;
                                mListener.heandleMessage(message);
                            } else if ("0d000000".equals(state)) {
                                System.err.println("小区去激活中");
                                Log.d("wtto", "qqqrun:小区去激活中");
                                message = new Message();
                                bundle.putString("test", "14");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 1001200;
                                mListener.heandleMessage(message);
                            }

                        }
                        if ("08f0".equals(str.substring(8, 12))) {

                            //目标距离（16进制字符串转换成十进制）
                            Integer.parseInt(str.substring(24, 26), 16);
                            System.out.println("距离：" + Integer.parseInt(str.substring(24, 26), 16));
                            message = new Message();
                            bundle.putString("sb1jl", Integer.parseInt(str.substring(24, 26), 16) + "");
//                            bundle.putString("sb1jl", Integer.parseInt(str.substring(24, 26), 16) + "");
                            bundle.putString("imsi1", hexStringToString(str.substring(26, 56)));
                            message.setData(bundle);
                            handler.sendMessage(message);
                            message.what = 100147;
                            mListener.heandleMessage(message);
                            Date now = new Date();
                            String imsi = hexStringToString(str.substring(26, 56));
                            message = new Message();
                            bundle.putString("imsi", imsi);
                            bundle.putString("sb", "1");
                            bundle.putString("zb", "1");
                            bundle.putString("datetime", "" + ReceiveMainDateFormat1.format(now));
                            bundle.putString("time", "" + ReceiveMainDateFormat2.format(now));
                            message.setData(bundle);
                            handler.sendMessage(message);
                            message.what = 30000;
                            mListener.heandleMessage(message);
                            //IMSI号
                            StringTOIMEI(str.substring(26, 56));
//                            System.out.println("IMSI号1：" + hexStringToString(str.substring(26, 56)));

                            if (!TextUtils.isEmpty(DOWNPIN1)) {
                                ZmBean zmBean = new ZmBean();
                                zmBean.setDown(DOWNPIN1);
                                zmBean.setSb("1");
                                zmBean.setImsi(imsi);
                                zmBean.setTime(ReceiveMainDateFormat1.format(now));
                                zmBean.setDatatime(ReceiveMainDateFormat1.format(now));
                                try {
                                    int i = dbManagerZM.insertAddZmBean(zmBean);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
//                            message = new Message();
//                            bundle.putString("imsi1", imsi);
//                            message.setData(bundle);
//                            handler.sendMessage(message);
//                            message.what = 100148;
//                            mListener.heandleMessage(message);
//                            Log.e(TAG, "loggnzqrun: " + hexStringToString(str.substring(26, 56)));
                        }

                        if ("10f0".equals(str.substring(8, 12))) {
                            //心跳解析
                            //查看小区是否建立成功（0：小区 IDLE态；1：扫频/同步进行中；2：小区激活中；3：小区激活态；4：小区去激活中）
                            message = new Message();
                            bundle.putString("zt1", "2");
                            message.setData(bundle);
                            handler.sendMessage(message);
                            message.what = 100120;
                            mListener.heandleMessage(message);
                            if ("0000".equals(str.substring(24, 28))) {
                                System.out.println("0：小区 IDLE态");
                                message = new Message();
                                bundle.putString("zt1", "2");//IDLE态
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 100120;
                                mListener.heandleMessage(message);
                            } else if ("0100".equals(str.substring(24, 28))) {
                                System.out.println("1：扫频/同步进行中");
                                message = new Message();
                                bundle.putString("zt1", "3");//扫频同步进行中
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 100120;
                                mListener.heandleMessage(message);
                            } else if ("0200".equals(str.substring(24, 28))) {
                                System.out.println("2：小区激活中");
                                message = new Message();
                                bundle.putString("zt1", "4");//小区激活中
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 100120;
                                mListener.heandleMessage(message);
                            } else if ("0300".equals(str.substring(24, 28))) {
                                System.out.println("3：定位中");
                                message = new Message();
                                bundle.putString("zt1", "5");//小区激活态
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 100120;
                                mListener.heandleMessage(message);
                                //Band号
                                Integer.parseInt(StringPin(str.substring(28, 32)), 16);
                                System.out.println("Band号：" + Integer.parseInt(StringPin(str.substring(28, 32)), 16));
                                //上行频点
                                Integer.parseInt(StringPin(str.substring(32, 40)), 16);
                                System.out.println("上行频点：" + Integer.parseInt(StringPin(str.substring(32, 40)), 16));
                                //下行频点
                                Integer.parseInt(StringPin(str.substring(40, 48)), 16);
                                System.out.println("下行频点：" + Integer.parseInt(StringPin(str.substring(40, 48)), 16));
                                DOWNPIN1 = Integer.parseInt(StringPin(str.substring(40, 48)), 16) + "";
                                message = new Message();
                                bundle.putString("down", Integer.parseInt(StringPin(str.substring(40, 48)), 16) + "");//查询增益
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 100151;
                                mListener.heandleMessage(message);
                                System.out.println("100151");
                                //移动联通电信
                                if ("3436303030".equals(str.substring(48, 58))) {
                                    //设置中国移动
                                }
                                if ("3436303031".equals(str.substring(48, 58))) {
                                    //设置中国联通
                                }
                                if ("3436303033".equals(str.substring(48, 58)) || "3436303131".equals(str.substring(48, 58))) {
                                    //设置中国电信
                                }

                                //PCI
                                Integer.parseInt(StringPin(str.substring(64, 68)), 16);
                                System.out.println("PCI:" + Integer.parseInt(StringPin(str.substring(64, 68)), 16));
                                //TAC
                                Integer.parseInt(StringPin(str.substring(68, 72)), 16);
                                System.out.println("TAC:" + Integer.parseInt(StringPin(str.substring(68, 72)), 16));

                            } else if ("0400".equals(str.substring(24, 28))) {
                                System.out.println("4：小区去激活中");
                            }

                        }
                        //温度告警
                        if ("5bf0".equals(str.substring(8, 12))) {
                            if ("00000000".equals(str.substring(32, 40))) {
                                System.out.println("基带板温度超过70度");
                            }
                            if ("01000000".equals(str.substring(32, 40))) {
                                System.out.println("基带板温度降低到70度以下了");
                            }

                        }
                        //
                        if ("32f0".equals(str.substring(8, 12))) {
                            message = new Message();
                            bundle.putString("zy1", Integer.parseInt(str.substring(24, 26), 16) + "");//查询增益
                            message.setData(bundle);
                            handler.sendMessage(message);
                            message.what = 100149;
                            mListener.heandleMessage(message);
                            System.out.println("100149");
                        }
                        if ("14f0".equals(str.substring(8, 12))) {

                        }
                        //设置增益是否成功
                        if ("14f0".equals(str.substring(8, 12))) {
                            //成功0；不成功>0（16进制字符串转换成十进制）
                            int row = Integer.parseInt(str.substring(24, 32), 16);
                            if (row == 0) {
                                System.out.println("增益值设置成功!");
                                message = new Message();
                                bundle.putString("zyset1", "增益值设置成功");//小区激活中
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 100150;
                                mListener.heandleMessage(message);
                                System.out.println("100150");

                            } else {
                                System.err.println("增益值设置失败!");
                                message = new Message();
                                bundle.putString("zyset1", "增益值设置失败");//小区激活中
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 100150;
                                mListener.heandleMessage(message);
                                System.out.println("100150");
                            }
                        }

//                            aaaa
                        //扫频频点设置响应
                        if ("04f0".equals(str.substring(8, 12))) {
                            //成功0；不成功>0（16进制字符串转换成十进制）
                            int row = Integer.parseInt(str.substring(24, 32), 16);
                            if (row == 0) {
//                                System.out.println("设置成功！");
                            } else {
//                                System.err.println("设置失败！");
                            }
                        }
                        //     扫频频点设置响应
                        if ("0af0".equals(str.substring(8, 12))) {
                            //采集的小区数目
                            int row;
                            if ("ffff".equals(str.substring(24, 28))) {
                                row = 0;
                            } else {
                                row = Integer.parseInt(StringPin(str.substring(24, 28)), 16);
                                System.out.println("小区个数：" + row);
                            }
                        }
                        if ("7ef0".equals(str.substring(8, 12))) {
                            int row = Integer.parseInt(str.substring(24, 32), 16);
                            if (row == 0) {
                                System.out.println("SNF端口扫频设置成功！");

                                message = new Message();
                                bundle.putString("snf", "SNF端口扫频设置成功");//小区激活中
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 100154;
                                mListener.heandleMessage(message);
                                System.out.println("100154");

                            } else {
                                System.err.println("SNF端口扫频设置失败！");
                                message = new Message();
                                bundle.putString("snf", "SNF端口扫频设置失败");//小区激活中
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 100154;
                                mListener.heandleMessage(message);
                                System.out.println("100154");
                            }
                        }
//                            F

                        if ("0af0".equals(str.substring(8, 12))) {
                            List<SpBean> spBeanList = new ArrayList<>();
                            //消息头长度
                            String length = str.substring(12, 16);
                            String len = StringPin(length);
                            Integer strlen = Integer.parseInt(len, 16);
                            //是否发送完
                            Integer.parseInt(StringPin(str.substring(20, 24)), 16);
                            String code = Integer.toBinaryString(Integer.parseInt(StringPin(str.substring(20, 24)), 16));

                            code = StringAd(code);
                            System.out.println("南志强2F--" + str3);
                            System.err.println("code:" + code);
                            String s = str.substring(24, strlen * 2);
                            System.out.println("ss___" + s);
                            str3 = str3 + s;
                            System.err.println("s3___" + str3);
                            if ("0".equals(code.substring(0, 1))) {

                                str3 = "aaaa55550af0240000ff0000" + str3;
                                System.err.println("str3:+++++++++++++++" + str3);
                                //采集的小区数目
                                int row;
                                if ("ffff".equals(str3.substring(24, 28))) {
                                    row = 0;
                                } else {
                                    row = Integer.parseInt(StringPin(str3.substring(24, 28)), 16);
                                    System.out.println("小区个数：" + row);
                                }
                                System.out.println("南志强F--" + str3);
                                int dlEarfcnBegin = 32, dlEarfcnEnd = 40;
                                int pciBegin = 40, pciEnd = 44;
                                int tacBegin = 44, tacEnd = 48;
                                int plmnBegin = 48, plmnEnd = 52;
                                int celldBegin = 56, celldEnd = 64;
                                int priorityBegin = 64, priorityEnd = 72;
                                int rsrpBegin = 72, rsrpEnd = 74;
                                int rsrqBegin = 74, rsrqEnd = 76;
                                int bandWidthBegin = 76, bandWidthEnd = 78;
                                int tddSpecialSfBegin = 78, tddSpecialSfEnd = 80;
                                int interFreqLstNumBegin = 88, interFreqLstNumEnd = 96;
                                int interFreqNghNumBegin = 0, interFreqNghNumEnd = 0;
                                for (int i = 0; i < row; i++) {
                                    //下行频点
                                    SpBean spBean = new SpBean();
                                    System.out.println(str3.substring(dlEarfcnBegin, dlEarfcnEnd));
                                    if ("ffffffff".equals(str3.substring(dlEarfcnBegin, dlEarfcnEnd))) {
                                        System.out.println("null");
//                                            continue;
                                    } else {
                                        System.out.println("下行频点：------" + Integer.parseInt(StringPin(str3.substring(dlEarfcnBegin, dlEarfcnEnd)), 16));
                                        spBean.setDown(Integer.parseInt(StringPin(str3.substring(dlEarfcnBegin, dlEarfcnEnd)), 16) + "");
                                    }
                                    if (ID1TF.equals("TDD")) {
                                        spBean.setUp(255 + "");
                                    } else {
                                        if (!TextUtils.isEmpty(spBean.getDown())) {
                                            int i1 = Integer.parseInt(spBean.getDown()) + 18000;
                                            spBean.setUp(i1 + "");
                                        }
                                    }
                                    //PCI
//                                    System.out.println("PCI：------" + Integer.parseInt(StringPin(str3.substring(pciBegin, pciEnd)), 16));
//                                    spBean.setPci(Integer.parseInt(StringPin(str3.substring(pciBegin, pciEnd)), 16));
//                                    spBean.setPci(0);
//                                    System.out.println(dlEarfcnBegin + "+" + dlEarfcnEnd);


                                    try {
                                        str3.substring(pciBegin, pciEnd);
                                        spBean.setPci(Integer.parseInt(StringPin(str3.substring(pciBegin, pciEnd)), 16));
                                    } catch (StringIndexOutOfBoundsException e) {
                                        spBean.setPci(0);
                                    }
                                    //TAC
//                                    System.out.println("TAC：------" + Integer.parseInt(StringPin(str3.substring(tacBegin, tacEnd)), 16));
//                                    spBean.setTac(Integer.parseInt(StringPin(str3.substring(tacBegin, tacEnd)), 16));

                                    try {
                                        str3.substring(tacBegin, tacEnd);
                                        spBean.setTac(Integer.parseInt(StringPin(str3.substring(tacBegin, tacEnd)), 16));
                                    } catch (StringIndexOutOfBoundsException e) {
                                        spBean.setTac(0);
                                    }


//                                    Log.d(TAG, "spBeanTAcrun: " + spBean.getTac());
//                                    spBean.setTac(0);
                                    //PLMN
//                                    System.out.println(Integer.parseInt(StringPin(str3.substring(plmnBegin, plmnEnd)), 16) + "---PLMN：");

                                    if ("ffff".equals(str3.substring(plmnBegin, plmnEnd))) {
                                        Log.d("1nzqffffffff", "run:1 ");
                                        spBean.setPlmn(0 + "");
                                    } else {
                                        spBean.setPlmn(Integer.parseInt(StringPin(str3.substring(plmnBegin, plmnEnd)), 16) + "");
                                        Log.d("2nzqffffffff", "run:1 ");
                                    }


//                                    spBean.setPlmn("0");
                                    //CellId
                                    System.out.println("ffffffff".equals(str3.substring(celldBegin, celldEnd)) ? "null" : Integer.parseInt(StringPin(str3.substring(celldBegin, celldEnd)), 16) + "------CellId：");


                                    if ("ffffffff".equals(str3.substring(celldBegin, celldEnd))) {
                                        Log.d("1nzqffffffff", "run:1 ");
                                        spBean.setCid(0 + "");
                                    } else {
                                        spBean.setCid(Integer.parseInt(StringPin(str3.substring(celldBegin, celldEnd)), 16) + "");
                                        Log.d("2nzqffffffff", "run:1 ");
                                    }
                                    //Priority 本小区频点优先级
                                    System.out.println(str3.substring(64, 72));
                                    System.out.println("ffffffff".equals(str3.substring(priorityBegin, priorityEnd)) ? "Priority_null" : Integer.parseInt(StringPin(str3.substring(priorityBegin, priorityEnd)), 16) + "------Priority 本小区频点优先级：");
                                    if ("ffffffff".equals(str3.substring(priorityBegin, priorityEnd))) {
                                        Log.d("1nzqffffffff", "run:1 ");
                                        spBean.setPriority(0);
                                    } else {
                                        spBean.setPriority(Integer.parseInt(StringPin(str3.substring(priorityBegin, priorityEnd)), 16));
                                        Log.d("2nzqffffffff", "run:1 ");
                                    }
                                    //RSRP
//                                    System.out.println("RSRP：------" + Integer.parseInt(StringPin(str3.substring(rsrpBegin, rsrpEnd)), 16));

                                    if ("ffffffff".equals(str3.substring(rsrpBegin, rsrpEnd))) {
                                        Log.d("1nzqffffffff", "run:1 ");
                                        spBean.setRsrp(0);
                                    } else {
                                        spBean.setRsrp(Integer.parseInt(StringPin(str3.substring(rsrpBegin, rsrpEnd)), 16));
                                        Log.d("2nzqffffffff", "run:1 ");
                                    }
//                                    spBean.setRsrp(Integer.parseInt(StringPin(str3.substring(rsrpBegin, rsrpEnd)), 16));
                                    //RSRQ
                                    System.out.println("RSRQ：------" + Integer.parseInt(StringPin(str3.substring(rsrqBegin, rsrqEnd)), 16));
//                                    spBean.setRsrq(Integer.parseInt(StringPin(str3.substring(rsrqBegin, rsrqEnd)), 16));

                                    if ("ffffffff".equals(str3.substring(rsrqBegin, rsrqEnd))) {
                                        Log.d("1nzqffffffff", "run:1 ");
                                        spBean.setRsrq(0);
                                    } else {
                                        spBean.setRsrq(Integer.parseInt(StringPin(str3.substring(rsrqBegin, rsrqEnd)), 16));
                                        Log.d("2nzqffffffff", "run:1 ");
                                    }
                                    //Bandwidth小区工作带宽
                                    System.out.println("Bandwidth：------" + Integer.parseInt(StringPin(str3.substring(bandWidthBegin, bandWidthEnd)), 16));
//                                    spBean.setBand(Integer.parseInt(StringPin(str3.substring(bandWidthBegin, bandWidthEnd)), 16) + "");

                                    if ("ffffffff".equals(str3.substring(bandWidthBegin, bandWidthEnd))) {
                                        Log.d("1nzqffffffff", "run:1 ");
                                        spBean.setBand("");
                                    } else {
                                        spBean.setBand(Integer.parseInt(StringPin(str3.substring(bandWidthBegin, bandWidthEnd)), 16) + "");
                                        Log.d("2nzqffffffff", "run:1 ");
                                    }

                                    if (spBean.getDown().equals("0")) {

                                    } else {
                                        spBeanList.add(spBean);//测试代码add
                                    }
                                    //TddSpecialSf Patterns TDD特殊子帧配置
                                    System.out.println("TDD特殊子帧配置：------" + Integer.parseInt(StringPin(str3.substring(tddSpecialSfBegin, tddSpecialSfEnd)), 16));
                                    //异频小区个数
                                    int InterFreqLstNum;
                                    try {
                                        if ("ffffffff".equals(str3.substring(interFreqLstNumBegin, interFreqLstNumEnd))) {
                                            InterFreqLstNum = 0;
                                        } else {
                                            try {
                                                InterFreqLstNum = Integer.parseInt(StringPin(str3.substring(interFreqLstNumBegin, interFreqLstNumEnd)), 16);
                                            } catch (Exception e) {
                                                InterFreqLstNum = 0;
                                                Log.d("nzqexce1", "run: " + e.getMessage());
                                            }
//                                        InterFreqLstNum = Integer.parseInt(StringPin(str3.substring(interFreqLstNumBegin, interFreqLstNumEnd)), 16);
                                        }
                                    } catch (Exception e) {
                                        InterFreqLstNum = 0;
                                        Log.d("nzqexce3", "run: " + e.getMessage());
                                    }


                                    System.out.println(interFreqLstNumBegin + "---" + interFreqLstNumEnd);
                                    System.out.println("异频小区个数：------" + InterFreqLstNum);

                                    dlEarfcnBegin = dlEarfcnBegin + 64;
                                    dlEarfcnEnd = dlEarfcnEnd + 64;
                                    pciBegin = pciBegin + 64;
                                    pciEnd = pciEnd + 64;
                                    tacBegin = tacBegin + 64;
                                    tacEnd = tacEnd + 64;
                                    plmnBegin = plmnBegin + 64;
                                    plmnEnd = plmnEnd + 64;
                                    celldBegin = celldBegin + 64;
                                    celldEnd = celldEnd + 64;
                                    priorityBegin = priorityBegin + 64;
                                    priorityEnd = priorityEnd + 64;
                                    rsrpBegin = rsrpBegin + 64;
                                    rsrpEnd = rsrpEnd + 64;
                                    rsrqBegin = rsrqBegin + 64;
                                    rsrqEnd = rsrqEnd + 64;
                                    bandWidthBegin = bandWidthBegin + 64;
                                    bandWidthEnd = bandWidthEnd + 64;
                                    tddSpecialSfBegin = tddSpecialSfBegin + 64;
                                    tddSpecialSfEnd = tddSpecialSfEnd + 64;
                                    //interFreqLstNumBegin = interFreqLstNumBegin+64;interFreqLstNumEnd = interFreqLstNumEnd+64;

                                    System.out.println(interFreqNghNumBegin + "---" + interFreqNghNumEnd);
                                    for (int r = 0; r < InterFreqLstNum; r++) {
                                        System.out.println(interFreqNghNumBegin + "---" + interFreqNghNumEnd);
                                        interFreqNghNumBegin = interFreqLstNumBegin + 24;
                                        interFreqNghNumEnd = interFreqLstNumEnd + 24;
                                        //异频小区的领区数目
//                                            System.out.println(str3.substring(interFreqNghNumBegin, interFreqNghNumEnd));
//                                            System.out.println("pin:" + StringPin(str3.substring(interFreqNghNumBegin, interFreqNghNumEnd)));
//                                            System.out.println(Integer.parseInt(StringPin(str3.substring(interFreqNghNumBegin, interFreqNghNumEnd)), 16));
//                                            int interFreqNghNum = Integer.parseInt(StringPin(str3.substring(interFreqNghNumBegin, interFreqNghNumEnd)), 16);
//                                            System.out.println("异频小区的邻区个数：" + interFreqNghNum);
                                        int interFreqNghNum;
//                                        try {
//                                        if (str4.length() < interFreqNghNumEnd) {
//                                            continue;
//                                        }
                                        try {
                                            if (!TextUtils.isEmpty(str3)) {

                                            } else {
                                                continue;
                                            }
                                            if ("ffffffff".equals(str3.substring(interFreqNghNumBegin, interFreqNghNumEnd))) {

                                                interFreqNghNum = 0;
                                            } else {
                                                interFreqNghNum = Integer.parseInt(StringPin(str3.substring(interFreqNghNumBegin, interFreqNghNumEnd)), 16);
//                                                System.out.println("异频小区的邻区个数：" + interFreqNghNum);
                                            }
                                        } catch (Exception e) {
                                            interFreqNghNum = 0;
//                                            Log.d("nzqexce2", "run: " + e.getMessage());
                                        }

//                                        } catch (Exception e) {
//                                            continue;
//                                        }

                                        for (int n = 0; n < interFreqNghNum; n++) {
                                            dlEarfcnBegin = dlEarfcnBegin + 8;
                                            dlEarfcnEnd = dlEarfcnEnd + 8;
                                            pciBegin = pciBegin + 8;
                                            pciEnd = pciEnd + 8;
                                            tacBegin = tacBegin + 8;
                                            tacEnd = tacEnd + 8;
                                            plmnBegin = plmnBegin + 8;
                                            plmnEnd = plmnEnd + 8;
                                            celldBegin = celldBegin + 8;
                                            celldEnd = celldEnd + 8;
                                            priorityBegin = priorityBegin + 8;
                                            priorityEnd = priorityEnd + 8;
                                            rsrpBegin = rsrpBegin + 8;
                                            rsrpEnd = rsrpEnd + 8;
                                            rsrqBegin = rsrqBegin + 8;
                                            rsrqEnd = rsrqEnd + 8;
                                            bandWidthBegin = bandWidthBegin + 8;
                                            bandWidthEnd = bandWidthEnd + 8;
                                            tddSpecialSfBegin = tddSpecialSfBegin + 8;
                                            tddSpecialSfEnd = tddSpecialSfEnd + 8;
                                            interFreqLstNumBegin = interFreqLstNumBegin + 8;
                                            interFreqLstNumEnd = interFreqLstNumEnd + 8;
                                            interFreqNghNumBegin = interFreqNghNumBegin + 8;
                                            interFreqNghNumEnd = interFreqNghNumEnd + 8;
                                        }

									/*int number = InterFreqLstNum-r;
                                    if(number!=1){
										interFreqNghNumBegin = interFreqNghNumBegin+24; interFreqNghNumEnd = interFreqNghNumEnd+24;
									}*/
                                        dlEarfcnBegin = dlEarfcnBegin + 24;
                                        dlEarfcnEnd = dlEarfcnEnd + 24;
                                        pciBegin = pciBegin + 24;
                                        pciEnd = pciEnd + 24;
                                        tacBegin = tacBegin + 24;
                                        tacEnd = tacEnd + 24;
                                        plmnBegin = plmnBegin + 24;
                                        plmnEnd = plmnEnd + 24;
                                        celldBegin = celldBegin + 24;
                                        celldEnd = celldEnd + 24;
                                        priorityBegin = priorityBegin + 24;
                                        priorityEnd = priorityEnd + 24;
                                        rsrpBegin = rsrpBegin + 24;
                                        rsrpEnd = rsrpEnd + 24;
                                        rsrqBegin = rsrqBegin + 24;
                                        rsrqEnd = rsrqEnd + 24;
                                        bandWidthBegin = bandWidthBegin + 24;
                                        bandWidthEnd = bandWidthEnd + 24;
                                        tddSpecialSfBegin = tddSpecialSfBegin + 24;
                                        tddSpecialSfEnd = tddSpecialSfEnd + 24;
                                        interFreqLstNumBegin = interFreqLstNumBegin + 24;
                                        interFreqLstNumEnd = interFreqLstNumEnd + 24;

                                    }
                                    interFreqLstNumBegin = interFreqLstNumBegin + 64;
                                    interFreqLstNumEnd = interFreqLstNumEnd + 64;
                                    interFreqNghNumBegin = interFreqNghNumBegin + 64;
                                    interFreqNghNumEnd = interFreqNghNumEnd + 64;
                                }
                                str3 = "";
                                Log.d("nzqrun77", "nzqrun: " + "执行");
                                if (spBeanList.size() == 0) {
//                                    Log.d(TAG, "nzqrunrun: " + "等于0");
                                } else {
//                                    Log.d(TAG, "nzqrunrun: " + "大于0");
                                    SPBEANLIST1 = spBeanList;
                                    Log.d("nzqspBeanList1", "" + spBeanList);
//                                spBeanList.sort(Comparator.comparing(SpBean::getPriority));
                                    //先根据优先级,如果优先级一样根据RSRP
                                    List<Integer> list = new ArrayList();
                                    String down1 = "";
                                    SpBean spBean1 = new SpBean();
                                    SpBean spBean2 = new SpBean();
                                    if (spBeanList.size() >= 2) {
                                        for (int i = 0; i < spBeanList.size(); i++) {
                                            list.add(spBeanList.get(i).getPriority());
                                        }
                                        Integer max = Collections.max(list);
                                        Log.d("Anzqmax", "大于2条run: " + max);
                                        list.remove(max);//最大的优先

                                        for (int i = 0; i < spBeanList.size(); i++) {
                                            if (max.equals(spBeanList.get(i).getPriority())) {
                                                down1 = spBeanList.get(i).getDown();
                                                spBean1 = spBeanList.get(i);
                                            }
                                        }
                                        //第二个优先
                                        String down2 = "";
                                        Integer max2 = Collections.max(list);
//                                    Log.d("Anzqmax2", "run: " + max);
                                        for (int i = 0; i < spBeanList.size(); i++) {
                                            if (max2.equals(spBeanList.get(i).getPriority())) {

                                                down2 = spBeanList.get(i).getDown();
                                                spBean2 = spBeanList.get(i);
                                            }
                                        }
                                        Log.d("down2a", "run: " + down2);
                                        if (max != max2) {

                                            if (!down1.equals(down2)) {//优先级的频点一致 比较频点是否一致
                                                message = new Message();
                                                bundle.putString("sp1MAX1value", down1);//下行
                                                bundle.putString("sp1up", spBean1.getUp() + "");
                                                bundle.putString("sp1pci", spBean1.getPci() + "");
                                                bundle.putString("sp1plmn", spBean1.getPlmn() + "");
                                                bundle.putString("sp1band", spBean1.getBand() + "");
                                                bundle.putString("sp1tac", spBean1.getTac() + "");

                                                bundle.putString("sp1MAX2value", down2);
                                                bundle.putString("sp2up", spBean2.getUp() + "");
                                                bundle.putString("sp2pci", spBean2.getPci() + "");
                                                bundle.putString("sp2plmn", spBean2.getPlmn() + "");
                                                bundle.putString("sp2band", spBean2.getBand() + "");
                                                bundle.putString("sp2tac", spBean2.getTac() + "");
                                                message.setData(bundle);
                                                handler.sendMessage(message);
                                                message.what = 100152;
                                                mListener.heandleMessage(message);
                                                Log.d("Anzqmax", "大于2条run且优先级有区别但是频点一致: " + max);
                                            } else {
                                                message = new Message();
                                                bundle.putString("sp1MAX1value", down1);//下行
                                                bundle.putString("sp1up", spBean1.getUp() + "");
                                                bundle.putString("sp1pci", spBean1.getPci() + "");
                                                bundle.putString("sp1plmn", spBean1.getPlmn() + "");
                                                bundle.putString("sp1band", spBean1.getBand() + "");
                                                bundle.putString("sp1tac", spBean1.getTac() + "");

                                                for (int i = 0; i < spBeanList.size(); i++) {
                                                    if (!down1.equals(spBeanList.get(i).getDown())) {
                                                        down2 = spBeanList.get(i).getDown();
                                                        spBean2 = spBeanList.get(i);
                                                        break;
                                                    }
                                                }


                                                bundle.putString("sp1MAX2value", down2);
                                                bundle.putString("sp2up", spBean2.getUp() + "");
                                                bundle.putString("sp2pci", spBean2.getPci() + "");
                                                bundle.putString("sp2plmn", spBean2.getPlmn() + "");
                                                bundle.putString("sp2band", spBean2.getBand() + "");
                                                bundle.putString("sp2tac", spBean2.getTac() + "");
                                                message.setData(bundle);
                                                handler.sendMessage(message);
                                                message.what = 100152;
                                                mListener.heandleMessage(message);
                                                Log.d("Anzqmax", "大于2条run且优先级有区别但是频点不一致: " + down1 + "--" + down2);
                                            }


                                        } else {//根据优先级比较一致  ,通过rsrp比较

                                            int rssp1;
                                            int rssp2;
                                            List<Integer> list1rsp = new ArrayList<>();
                                            for (int i = 0; i < spBeanList.size(); i++) {
                                                list1rsp.add(spBeanList.get(i).getRsrp());
                                            }
                                            //最大的rsrp
                                            rssp1 = Collections.max(list1rsp);
                                            for (int i = 0; i < spBeanList.size(); i++) {
                                                if (rssp1 == spBeanList.get(i).getRsrp()) {
                                                    down1 = spBeanList.get(i).getDown();
                                                    spBean1 = spBeanList.get(i);
                                                }
                                            }
                                            for (int i = 0; i < list1rsp.size(); i++) {
                                                if (list1rsp.get(i).equals(rssp1)) {
                                                    list1rsp.remove(i);
                                                }
                                            }
                                            //求第二个rsrp
                                            rssp2 = Collections.max(list1rsp);
                                            for (int i = 0; i < spBeanList.size(); i++) {
                                                if (rssp2 == spBeanList.get(i).getRsrp()) {
                                                    down2 = spBeanList.get(i).getDown();
                                                    spBean2 = spBeanList.get(i);
                                                }
                                            }
                                            if (down1.equals(down2)) {
                                                message = new Message();
                                                bundle.putString("sp1MAX1value", down1);//下行
                                                bundle.putString("sp1up", spBean1.getUp() + "");
                                                bundle.putString("sp1pci", spBean1.getPci() + "");
                                                bundle.putString("sp1plmn", spBean1.getPlmn() + "");
                                                bundle.putString("sp1band", spBean1.getBand() + "");
                                                bundle.putString("sp1tac", spBean1.getTac() + "");
                                                for (int i = 0; i < spBeanList.size(); i++) {
                                                    if (!down1.equals(spBeanList.get(i).getDown())) {
                                                        down2 = spBeanList.get(i).getDown();
                                                        spBean2 = spBeanList.get(i);
                                                        break;
                                                    }

                                                }
                                                bundle.putString("sp1MAX2value", "");
                                                bundle.putString("sp2up", spBean2.getUp() + "");
                                                bundle.putString("sp2pci", spBean2.getPci() + "");
                                                bundle.putString("sp2plmn", spBean2.getPlmn() + "");
                                                bundle.putString("sp2band", spBean2.getBand() + "");
                                                bundle.putString("sp2tac", spBean2.getTac() + "");
                                                message.setData(bundle);
                                                handler.sendMessage(message);
                                                message.what = 100152;
                                                mListener.heandleMessage(message);
                                                Log.d("Anzqmax", "大于2条run且优先级没区别用RSRP比较且下频一致取第二个不应的: " + down1 + "--" + down2);
                                            } else {
                                                message = new Message();
                                                bundle.putString("sp1MAX1value", down1);//下行
                                                bundle.putString("sp1up", spBean1.getUp() + "");
                                                bundle.putString("sp1pci", spBean1.getPci() + "");
                                                bundle.putString("sp1plmn", spBean1.getPlmn() + "");
                                                bundle.putString("sp1band", spBean1.getBand() + "");
                                                bundle.putString("sp1tac", spBean1.getTac() + "");

                                                bundle.putString("sp1MAX2value", down2);
                                                bundle.putString("sp2up", spBean2.getUp() + "");
                                                bundle.putString("sp2pci", spBean2.getPci() + "");
                                                bundle.putString("sp2plmn", spBean2.getPlmn() + "");
                                                bundle.putString("sp2band", spBean2.getBand() + "");
                                                bundle.putString("sp2tac", spBean2.getTac() + "");
                                                message.setData(bundle);
                                                handler.sendMessage(message);
                                                message.what = 100152;
                                                mListener.heandleMessage(message);
                                                Log.d("Anzqmax", "大于2条run且优先级没区别用RSRP比较且下频不一致: " + down1 + "--" + down2);
                                            }


//                                            ToastUtils.showToast("当前条数为多条");
//                                        Log.d("Anzqmax", "大于2条run且优先级没区别用RSRP比较: "+down1+"--"+down2 );
                                        }

                                    } else {
                                        //

                                        if (spBeanList.size() > 0 && spBeanList.size() == 1) {
                                            down1 = spBeanList.get(0).getDown();
//                                            spBean2 = spBeanList.get(0);
                                            message = new Message();
                                            bundle.putString("sp1MAX1value", down1);
                                            bundle.putString("sp1up", spBeanList.get(0).getUp() + "");
                                            bundle.putString("sp1pci", spBeanList.get(0).getPci() + "");
                                            bundle.putString("sp1plmn", spBeanList.get(0).getPlmn() + "");
                                            bundle.putString("sp1band", spBeanList.get(0).getBand() + "");
                                            bundle.putString("sp1tac", spBeanList.get(0).getTac() + "");

                                            bundle.putString("sp1MAX2value", "");
                                            bundle.putString("sp2up", "");
                                            bundle.putString("sp2pci", "");
                                            bundle.putString("sp2plmn", "");
                                            bundle.putString("sp2band", "");
                                            bundle.putString("sp2tac", "");


                                            message.setData(bundle);
                                            handler.sendMessage(message);
                                            message.what = 100152;
                                            mListener.heandleMessage(message);
//                                            ToastUtils.showToast("当前条数为1");
                                            Log.d("Anzqmax", "当前条数为1: ");
                                        } else {
                                            message = new Message();
                                            bundle.putString("sp1MAX1value", "");
                                            bundle.putString("sp1MAX2value", "");
                                            message.setData(bundle);
                                            handler.sendMessage(message);
                                            message.what = 100152;
                                            mListener.heandleMessage(message);
//                                            ToastUtils.showToast("当前条数为0");
                                            Log.d("Anzqmax", "当前条数为0: ");
                                        }
                                    }
                                }


                            }

                        }


//                            //新
//                            if ("0af0".equals(str.substring(8, 12))) {
//                                i++;
//                                System.out.println("南志强加加"+str);
////                                List<SpBean> spBeanList = new ArrayList<>();
//                                //消息头长度
//                                String length = str.substring(12, 16);
//                                String len = StringPin(length);
//                                Integer strlen = Integer.parseInt(len, 16);
//                                //是否发送完
//                                Integer.parseInt(StringPin(str.substring(20, 24)), 16);
//                                String code = Integer.toBinaryString(Integer.parseInt(StringPin(str.substring(20, 24)), 16));
//
//                                code = StringAd(code);
//
////                                System.err.println("code:" + code);
//                                String s = str.substring(24, strlen * 2);
////                                System.out.println("ss___" + s);
//                                str3 = str3 + s;
////                                System.err.println("s3___" + str3);
//
//                                if ("0".equals(code.substring(0, 1))) {
//                                    i++;
//
//                                    List<SpBean> spBeanList = new ArrayList<>();
//                                    str3 = "aaaa55550af0240000ff0000" + str3;
////                                    System.err.println("str3:+++++++++++++++" + str3);
//                                    //采集的小区数目
//                                   System.out.println("南志强F--"+str3);
//                                    int row;
//                                    if ("ffff".equals(str3.substring(24, 28))) {
//                                        row = 0;
//                                    } else {
//                                        row = Integer.parseInt(StringPin(str3.substring(24, 28)), 16);
//                                        System.out.println("小区个数：" + row);
//                                    }
//
//                                    int dlEarfcnBegin = 32, dlEarfcnEnd = 40;
//                                    int pciBegin = 40, pciEnd = 44;
//                                    int tacBegin = 44, tacEnd = 48;
//                                    int plmnBegin = 48, plmnEnd = 52;
//                                    int celldBegin = 56, celldEnd = 64;
//                                    int priorityBegin = 64, priorityEnd = 72;
//                                    int rsrpBegin = 72, rsrpEnd = 74;
//                                    int rsrqBegin = 74, rsrqEnd = 76;
//                                    int bandWidthBegin = 76, bandWidthEnd = 78;
//                                    int tddSpecialSfBegin = 78, tddSpecialSfEnd = 80;
//                                    int interFreqLstNumBegin = 88, interFreqLstNumEnd = 96;
//                                    int interFreqNghNumBegin = 0, interFreqNghNumEnd = 0;
//                                    for (int i = 0; i < row; i++) {
//                                        SpBean spBean = new SpBean();
//                                        //下行频点
//                                        System.out.println(str3.substring(dlEarfcnBegin, dlEarfcnEnd));
//                                        if ("ffffffff".equals(str3.substring(dlEarfcnBegin, dlEarfcnEnd))) {
//                                            System.out.println("null");
//                                        } else {
//                                            System.out.println("下行频点：------" + Integer.parseInt(StringPin(str3.substring(dlEarfcnBegin, dlEarfcnEnd)), 16));
//                                            spBean.setDown(Integer.parseInt(StringPin(str3.substring(dlEarfcnBegin, dlEarfcnEnd)), 16) + "");
//                                        }
//                                        if (ID1TF.equals("TDD")) {
//                                            spBean.setUp(255 + "");
//                                        } else {
//                                            if (!TextUtils.isEmpty(spBean.getDown())) {
//                                                int i1 = Integer.parseInt(spBean.getDown() + 18000);
//                                                spBean.setUp(i1 + "");
//                                            }
//                                        }
//                                        //PCI
//                                        System.out.println("PCI：------" + Integer.parseInt(StringPin(str3.substring(pciBegin, pciEnd)), 16));
//                                        spBean.setPci(Integer.parseInt(StringPin(str3.substring(pciBegin, pciEnd)), 16));
//                                        System.out.println(dlEarfcnBegin + "+" + dlEarfcnEnd);
//
//                                        //TAC
//                                        System.out.println("TAC：------" + Integer.parseInt(StringPin(str3.substring(tacBegin, tacEnd)), 16));
//                                        spBean.setTac(Integer.parseInt(StringPin(str3.substring(tacBegin, tacEnd)), 16));
//                                        //PLMN
//                                        System.out.println(Integer.parseInt(StringPin(str3.substring(plmnBegin, plmnEnd)), 16) + "---PLMN：");
//                                        spBean.setPlmn(Integer.parseInt(StringPin(str3.substring(plmnBegin, plmnEnd)), 16) + "");
//                                        //CellId
//                                        System.out.println("ffffffff".equals(str3.substring(celldBegin, celldEnd)) ? "null" : Integer.parseInt(StringPin(str3.substring(celldBegin, celldEnd)), 16) + "------CellId：");
//                                        //Priority 本小区频点优先级
//                                        System.out.println(str3.substring(64, 72));
//                                        System.out.println("ffffffff".equals(str3.substring(priorityBegin, priorityEnd)) ? "Priority_null" : Integer.parseInt(StringPin(str3.substring(priorityBegin, priorityEnd)), 16) + "------Priority 本小区频点优先级：");
//                                        if ("ffffffff".equals(str3.substring(priorityBegin, priorityEnd))) {
//                                            Log.d("1nzqffffffff", "run:1 ");
//                                            spBean.setPriority(0);
//                                        } else {
//                                            spBean.setPriority(Integer.parseInt(StringPin(str3.substring(priorityBegin, priorityEnd)), 16));
//                                            Log.d("2nzqffffffff", "run:1 ");
//                                        }
//                                        //RSRP
//                                        System.out.println("RSRP：------" + Integer.parseInt(StringPin(str3.substring(rsrpBegin, rsrpEnd)), 16));
//                                        spBean.setRsrp(Integer.parseInt(StringPin(str3.substring(rsrqBegin, rsrqEnd)), 16));
//
//                                        //RSRQ
//                                        System.out.println("RSRQ：------" + Integer.parseInt(StringPin(str3.substring(rsrqBegin, rsrqEnd)), 16));
//                                        spBean.setRsrp(Integer.parseInt(StringPin(str.substring(rsrpBegin, rsrpEnd)), 16));
//                                        //Bandwidth小区工作带宽
//                                        System.out.println("Bandwidth：------" + Integer.parseInt(StringPin(str3.substring(bandWidthBegin, bandWidthEnd)), 16));
//                                        spBean.setBand(Integer.parseInt(StringPin(str3.substring(bandWidthBegin, bandWidthEnd)), 16) + "");
//                                        spBeanList.add(spBean);
//                                        //TddSpecialSf Patterns TDD特殊子帧配置
//                                        System.out.println("TDD特殊子帧配置：------" + Integer.parseInt(StringPin(str3.substring(tddSpecialSfBegin, tddSpecialSfEnd)), 16));
//                                        //异频小区个数
//                                        int InterFreqLstNum;
//                                        if ("ffffffff".equals(str3.substring(interFreqLstNumBegin, interFreqLstNumEnd))) {
//                                            InterFreqLstNum = 0;
//                                        } else {
//                                            InterFreqLstNum = Integer.parseInt(StringPin(str3.substring(interFreqLstNumBegin, interFreqLstNumEnd)), 16);
//                                        }
//                                        System.out.println(interFreqLstNumBegin + "---" + interFreqLstNumEnd);
//                                        System.out.println("异频小区个数：------" + InterFreqLstNum);
//
//                                        dlEarfcnBegin = dlEarfcnBegin + 64;
//                                        dlEarfcnEnd = dlEarfcnEnd + 64;
//                                        pciBegin = pciBegin + 64;
//                                        pciEnd = pciEnd + 64;
//                                        tacBegin = tacBegin + 64;
//                                        tacEnd = tacEnd + 64;
//                                        plmnBegin = plmnBegin + 64;
//                                        plmnEnd = plmnEnd + 64;
//                                        celldBegin = celldBegin + 64;
//                                        celldEnd = celldEnd + 64;
//                                        priorityBegin = priorityBegin + 64;
//                                        priorityEnd = priorityEnd + 64;
//                                        rsrpBegin = rsrpBegin + 64;
//                                        rsrpEnd = rsrpEnd + 64;
//                                        rsrqBegin = rsrqBegin + 64;
//                                        rsrqEnd = rsrqEnd + 64;
//                                        bandWidthBegin = bandWidthBegin + 64;
//                                        bandWidthEnd = bandWidthEnd + 64;
//                                        tddSpecialSfBegin = tddSpecialSfBegin + 64;
//                                        tddSpecialSfEnd = tddSpecialSfEnd + 64;
//                                        //interFreqLstNumBegin = interFreqLstNumBegin+64;interFreqLstNumEnd = interFreqLstNumEnd+64;
//
//                                        System.out.println(interFreqNghNumBegin + "---" + interFreqNghNumEnd);
//                                        for (int r = 0; r < InterFreqLstNum; r++) {
//                                            System.out.println(interFreqNghNumBegin + "---" + interFreqNghNumEnd);
//                                            interFreqNghNumBegin = interFreqLstNumBegin + 24;
//                                            interFreqNghNumEnd = interFreqLstNumEnd + 24;
//                                            //异频小区的领区数目
//                                            System.out.println(str3.substring(interFreqNghNumBegin, interFreqNghNumEnd));
//                                            System.out.println("pin:" + StringPin(str3.substring(interFreqNghNumBegin, interFreqNghNumEnd)));
//                                            System.out.println(Integer.parseInt(StringPin(str3.substring(interFreqNghNumBegin, interFreqNghNumEnd)), 16));
//                                            int interFreqNghNum = Integer.parseInt(StringPin(str3.substring(interFreqNghNumBegin, interFreqNghNumEnd)), 16);
//                                            System.out.println("异频小区的邻区个数：" + interFreqNghNum);
//
//                                            for (int n = 0; n < interFreqNghNum; n++) {
//                                                dlEarfcnBegin = dlEarfcnBegin + 8;
//                                                dlEarfcnEnd = dlEarfcnEnd + 8;
//                                                pciBegin = pciBegin + 8;
//                                                pciEnd = pciEnd + 8;
//                                                tacBegin = tacBegin + 8;
//                                                tacEnd = tacEnd + 8;
//                                                plmnBegin = plmnBegin + 8;
//                                                plmnEnd = plmnEnd + 8;
//                                                celldBegin = celldBegin + 8;
//                                                celldEnd = celldEnd + 8;
//                                                priorityBegin = priorityBegin + 8;
//                                                priorityEnd = priorityEnd + 8;
//                                                rsrpBegin = rsrpBegin + 8;
//                                                rsrpEnd = rsrpEnd + 8;
//                                                rsrqBegin = rsrqBegin + 8;
//                                                rsrqEnd = rsrqEnd + 8;
//                                                bandWidthBegin = bandWidthBegin + 8;
//                                                bandWidthEnd = bandWidthEnd + 8;
//                                                tddSpecialSfBegin = tddSpecialSfBegin + 8;
//                                                tddSpecialSfEnd = tddSpecialSfEnd + 8;
//                                                interFreqLstNumBegin = interFreqLstNumBegin + 8;
//                                                interFreqLstNumEnd = interFreqLstNumEnd + 8;
//                                                interFreqNghNumBegin = interFreqNghNumBegin + 8;
//                                                interFreqNghNumEnd = interFreqNghNumEnd + 8;
//                                            }
//
//									/*int number = InterFreqLstNum-r;
//									if(number!=1){
//										interFreqNghNumBegin = interFreqNghNumBegin+24; interFreqNghNumEnd = interFreqNghNumEnd+24;
//									}*/
//                                            dlEarfcnBegin = dlEarfcnBegin + 24;
//                                            dlEarfcnEnd = dlEarfcnEnd + 24;
//                                            pciBegin = pciBegin + 24;
//                                            pciEnd = pciEnd + 24;
//                                            tacBegin = tacBegin + 24;
//                                            tacEnd = tacEnd + 24;
//                                            plmnBegin = plmnBegin + 24;
//                                            plmnEnd = plmnEnd + 24;
//                                            celldBegin = celldBegin + 24;
//                                            celldEnd = celldEnd + 24;
//                                            priorityBegin = priorityBegin + 24;
//                                            priorityEnd = priorityEnd + 24;
//                                            rsrpBegin = rsrpBegin + 24;
//                                            rsrpEnd = rsrpEnd + 24;
//                                            rsrqBegin = rsrqBegin + 24;
//                                            rsrqEnd = rsrqEnd + 24;
//                                            bandWidthBegin = bandWidthBegin + 24;
//                                            bandWidthEnd = bandWidthEnd + 24;
//                                            tddSpecialSfBegin = tddSpecialSfBegin + 24;
//                                            tddSpecialSfEnd = tddSpecialSfEnd + 24;
//                                            interFreqLstNumBegin = interFreqLstNumBegin + 24;
//                                            interFreqLstNumEnd = interFreqLstNumEnd + 24;
//
//                                        }
//                                        interFreqLstNumBegin = interFreqLstNumBegin + 64;
//                                        interFreqLstNumEnd = interFreqLstNumEnd + 64;
//                                        interFreqNghNumBegin = interFreqNghNumBegin + 64;
//                                        interFreqNghNumEnd = interFreqNghNumEnd + 64;
//                                    }
//                                    str3 = "";
//                                    if (spBeanList.size() == 0) {
//
//                                    } else {
//                                        Log.d("nzqspBeanList1", "" + spBeanList);
////                                spBeanList.sort(Comparator.comparing(SpBean::getPriority));
//                                        //先根据优先级,如果优先级一样根据RSRP
//                                        List<Integer> list = new ArrayList();
//                                        String down1 = "";
//                                        SpBean spBean1 = new SpBean();
//                                        SpBean spBean2 = new SpBean();
//                                        if (spBeanList.size() >= 2) {
//                                            for (int i = 0; i < spBeanList.size(); i++) {
//                                                list.add(spBeanList.get(i).getPriority());
//                                            }
//                                            Integer max = Collections.max(list);
//                                            Log.d("Anzqmax", "大于2条run: " + max);
//                                            list.remove(max);//最大的优先
//
//                                            for (int i = 0; i < spBeanList.size(); i++) {
//                                                if (max.equals(spBeanList.get(i).getPriority())) {
//                                                    down1 = spBeanList.get(i).getDown();
//                                                    spBean1 = spBeanList.get(i);
//                                                }
//                                            }
//                                            //第二个优先
//                                            String down2 = "";
//                                            Integer max2 = Collections.max(list);
////                                    Log.d("Anzqmax2", "run: " + max);
//                                            for (int i = 0; i < spBeanList.size(); i++) {
//                                                if (max2.equals(spBeanList.get(i).getPriority())) {
//
//                                                    down2 = spBeanList.get(i).getDown();
//                                                    spBean2 = spBeanList.get(i);
//                                                }
//                                            }
//                                            Log.d("down2a", "run: " + down2);
//                                            if (max != max2) {
//
//                                                if (!down1.equals(down2)) {//优先级的频点一致 比较频点是否一致
//                                                    message = new Message();
//                                                    bundle.putString("sp1MAX1value", down1);//下行
//                                                    bundle.putString("sp1up", spBean1.getUp() + "");
//                                                    bundle.putString("sp1pci", spBean1.getPci() + "");
//                                                    bundle.putString("sp1plmn", spBean1.getPlmn() + "");
//                                                    bundle.putString("sp1band", spBean1.getBand() + "");
//                                                    bundle.putString("sp1tac", spBean1.getTac() + "");
//
//                                                    bundle.putString("sp1MAX2value", down2);
//                                                    bundle.putString("sp2up", spBean2.getUp() + "");
//                                                    bundle.putString("sp2pci", spBean2.getPci() + "");
//                                                    bundle.putString("sp2plmn", spBean2.getPlmn() + "");
//                                                    bundle.putString("sp2band", spBean2.getBand() + "");
//                                                    bundle.putString("sp2tac", spBean2.getTac() + "");
//                                                    message.setData(bundle);
//                                                    handler.sendMessage(message);
//                                                    message.what = 100152;
//                                                    Log.d("Anzqmax", "大于2条run且优先级有区别但是频点一致: " + max);
//                                                } else {
//                                                    message = new Message();
//                                                    bundle.putString("sp1MAX1value", down1);//下行
//                                                    bundle.putString("sp1up", spBean1.getUp() + "");
//                                                    bundle.putString("sp1pci", spBean1.getPci() + "");
//                                                    bundle.putString("sp1plmn", spBean1.getPlmn() + "");
//                                                    bundle.putString("sp1band", spBean1.getBand() + "");
//                                                    bundle.putString("sp1tac", spBean1.getTac() + "");
//
//                                                    for (int i = 0; i < spBeanList.size(); i++) {
//                                                        if (!down1.equals(spBeanList.get(i).getDown())) {
//                                                            down2 = spBeanList.get(i).getDown();
//                                                            spBean2 = spBeanList.get(i);
//                                                            break;
//                                                        }
//                                                    }
//
//
//                                                    bundle.putString("sp1MAX2value", down2);
//                                                    bundle.putString("sp2up", spBean2.getUp() + "");
//                                                    bundle.putString("sp2pci", spBean2.getPci() + "");
//                                                    bundle.putString("sp2plmn", spBean2.getPlmn() + "");
//                                                    bundle.putString("sp2band", spBean2.getBand() + "");
//                                                    bundle.putString("sp2tac", spBean2.getTac() + "");
//                                                    message.setData(bundle);
//                                                    handler.sendMessage(message);
//                                                    message.what = 100152;
//                                                    Log.d("Anzqmax", "大于2条run且优先级有区别但是频点不一致: " + down1 + "--" + down2);
//                                                }
//
//
//                                            } else {//根据优先级比较一致  ,通过rsrp比较
//
//                                                int rssp1;
//                                                int rssp2;
//                                                List<Integer> list1rsp = new ArrayList<>();
//                                                for (int i = 0; i < spBeanList.size(); i++) {
//                                                    list1rsp.add(spBeanList.get(i).getRsrp());
//                                                }
//                                                //最大的rsrp
//                                                rssp1 = Collections.max(list1rsp);
//                                                for (int i = 0; i < spBeanList.size(); i++) {
//                                                    if (rssp1 == spBeanList.get(i).getRsrp()) {
//                                                        down1 = spBeanList.get(i).getDown();
//                                                        spBean1 = spBeanList.get(i);
//                                                    }
//                                                }
//                                                for (int i = 0; i < list1rsp.size(); i++) {
//                                                    if (list1rsp.get(i).equals(rssp1)) {
//                                                        list1rsp.remove(i);
//                                                    }
//                                                }
//                                                //求第二个rsrp
//                                                rssp2 = Collections.max(list1rsp);
//                                                for (int i = 0; i < spBeanList.size(); i++) {
//                                                    if (rssp2 == spBeanList.get(i).getRsrp()) {
//                                                        down2 = spBeanList.get(i).getDown();
//                                                        spBean2 = spBeanList.get(i);
//                                                    }
//                                                }
//                                                if (down1.equals(down2)) {
//                                                    message = new Message();
//                                                    bundle.putString("sp1MAX1value", down1);//下行
//                                                    bundle.putString("sp1up", spBean1.getUp() + "");
//                                                    bundle.putString("sp1pci", spBean1.getPci() + "");
//                                                    bundle.putString("sp1plmn", spBean1.getPlmn() + "");
//                                                    bundle.putString("sp1band", spBean1.getBand() + "");
//                                                    bundle.putString("sp1tac", spBean1.getTac() + "");
//                                                    for (int i = 0; i < spBeanList.size(); i++) {
//                                                        if (!down1.equals(spBeanList.get(i).getDown())) {
//                                                            down2 = spBeanList.get(i).getDown();
//                                                            spBean2 = spBeanList.get(i);
//                                                            break;
//                                                        }
//
//                                                    }
//                                                    bundle.putString("sp1MAX2value", "");
//                                                    bundle.putString("sp2up", spBean2.getUp() + "");
//                                                    bundle.putString("sp2pci", spBean2.getPci() + "");
//                                                    bundle.putString("sp2plmn", spBean2.getPlmn() + "");
//                                                    bundle.putString("sp2band", spBean2.getBand() + "");
//                                                    bundle.putString("sp2tac", spBean2.getTac() + "");
//                                                    message.setData(bundle);
//                                                    handler.sendMessage(message);
//                                                    message.what = 100152;
//                                                    Log.d("Anzqmax", "大于2条run且优先级没区别用RSRP比较且下频一致取第二个不应的: " + down1 + "--" + down2);
//                                                } else {
//                                                    message = new Message();
//                                                    bundle.putString("sp1MAX1value", down1);//下行
//                                                    bundle.putString("sp1up", spBean1.getUp() + "");
//                                                    bundle.putString("sp1pci", spBean1.getPci() + "");
//                                                    bundle.putString("sp1plmn", spBean1.getPlmn() + "");
//                                                    bundle.putString("sp1band", spBean1.getBand() + "");
//                                                    bundle.putString("sp1tac", spBean1.getTac() + "");
//
//                                                    bundle.putString("sp1MAX2value", down2);
//                                                    bundle.putString("sp2up", spBean2.getUp() + "");
//                                                    bundle.putString("sp2pci", spBean2.getPci() + "");
//                                                    bundle.putString("sp2plmn", spBean2.getPlmn() + "");
//                                                    bundle.putString("sp2band", spBean2.getBand() + "");
//                                                    bundle.putString("sp2tac", spBean2.getTac() + "");
//                                                    message.setData(bundle);
//                                                    handler.sendMessage(message);
//                                                    message.what = 100152;
//                                                    Log.d("Anzqmax", "大于2条run且优先级没区别用RSRP比较且下频不一致: " + down1 + "--" + down2);
//                                                }
//
//
//                                                ToastUtils.showToast("当前条数为多条");
////                                        Log.d("Anzqmax", "大于2条run且优先级没区别用RSRP比较: "+down1+"--"+down2 );
//                                            }
//
//                                        } else {
//                                            //
//
//                                            if (spBeanList.size() > 0 && spBeanList.size() == 1) {
//                                                down1 = spBeanList.get(0).getDown();
////                                            spBean2 = spBeanList.get(0);
//                                                message = new Message();
//                                                bundle.putString("sp1MAX1value", down1);
//                                                bundle.putString("sp1up", spBeanList.get(0).getUp() + "");
//                                                bundle.putString("sp1pci", spBeanList.get(0).getPci() + "");
//                                                bundle.putString("sp1plmn", spBeanList.get(0).getPlmn() + "");
//                                                bundle.putString("sp1band", spBeanList.get(0).getBand() + "");
//                                                bundle.putString("sp1tac", spBeanList.get(0).getTac() + "");
//
//                                                bundle.putString("sp1MAX2value", "");
//                                                bundle.putString("sp2up", "");
//                                                bundle.putString("sp2pci", "");
//                                                bundle.putString("sp2plmn", "");
//                                                bundle.putString("sp2band", "");
//                                                bundle.putString("sp2tac", "");
//
//
//                                                message.setData(bundle);
//                                                handler.sendMessage(message);
//                                                message.what = 100152;
//                                                ToastUtils.showToast("当前条数为1");
//                                                Log.d("Anzqmax", "当前条数为1: ");
//                                            } else {
//                                                message = new Message();
//                                                bundle.putString("sp1MAX1value", "");
//                                                bundle.putString("sp1MAX2value", "");
//                                                message.setData(bundle);
//                                                handler.sendMessage(message);
//                                                message.what = 100152;
//                                                ToastUtils.showToast("当前条数为0");
//                                                Log.d("Anzqmax", "当前条数为0: ");
//                                            }
//                                        }
//                                    }
//
//                                }
//
//                            }


//                            //扫频频点设置响应  旧
//                            if ("0af0".equals(str.substring(8, 12))) {
//                                //采集的小区数目
//                                int row;
//                                if ("ffff".equals(str.substring(24, 28))) {
//                                    row = 0;
//                                } else {
//                                    row = Integer.parseInt(StringPin(str.substring(24, 28)), 16);
//                                    System.out.println("小区个数：" + row);
//                                }
//                                List<SpBean> spBeanList = new ArrayList<>();
//                                int dlEarfcnBegin = 32, dlEarfcnEnd = 40;
//                                int pciBegin = 40, pciEnd = 44;
//                                int tacBegin = 44, tacEnd = 48;
//                                int plmnBegin = 48, plmnEnd = 52;
//                                int celldBegin = 56, celldEnd = 64;
//                                int priorityBegin = 64, priorityEnd = 72;
//                                int rsrpBegin = 72, rsrpEnd = 74;
//                                int rsrqBegin = 74, rsrqEnd = 76;
//                                int bandWidthBegin = 76, bandWidthEnd = 78;
//                                int tddSpecialSfBegin = 78, tddSpecialSfEnd = 80;
//                                int interFreqLstNumBegin = 88, interFreqLstNumEnd = 96;
//                                int interFreqNghNumBegin = 112, interFreqNghNumEnd = 120;
//
//                                for (int i = 0; i < row; i++) {
//                                    SpBean spBean = new SpBean();
//                                    //下行频点
//                                    System.out.println(str.substring(dlEarfcnBegin, dlEarfcnEnd));
//
//                                    if ("ffffffff".equals(str.substring(dlEarfcnBegin, dlEarfcnEnd))) {
//                                        System.out.println("null");
//                                        spBean.setDown("null");
//                                    } else {
//                                        System.out.println("下行频点：------" + Integer.parseInt(StringPin(str.substring(dlEarfcnBegin, dlEarfcnEnd)), 16));
//                                        spBean.setDown(Integer.parseInt(StringPin(str.substring(dlEarfcnBegin, dlEarfcnEnd)), 16) + "");
//                                    }
//                                    if (ID1TF.equals("TDD")) {
//                                        spBean.setUp(255 + "");
//                                    } else {
//                                        if (!TextUtils.isEmpty(spBean.getDown())) {
//                                            int i1 = Integer.parseInt(spBean.getDown() + 18000);
//                                            spBean.setUp(i1 + "");
//                                        }
//                                    }
//                                    Log.d("spBeanup", "run: " + spBean.getUp());
//                                    //PCI
//                                    System.out.println("PCI：------" + Integer.parseInt(StringPin(str.substring(pciBegin, pciEnd)), 16));
//                                    spBean.setPci(Integer.parseInt(StringPin(str.substring(pciBegin, pciEnd)), 16));
//                                    System.out.println(dlEarfcnBegin + "+" + dlEarfcnEnd);
//
//                                    //TAC
//                                    System.out.println("TAC：------" + Integer.parseInt(StringPin(str.substring(tacBegin, tacEnd)), 16));
//                                    spBean.setTac(Integer.parseInt(StringPin(str.substring(tacBegin, tacEnd)), 16));
//                                    //PLMN
//                                    System.out.println(Integer.parseInt(StringPin(str.substring(plmnBegin, plmnEnd)), 16) + "---PLMN：");
//
//                                    spBean.setPlmn(Integer.parseInt(StringPin(str.substring(plmnBegin, plmnEnd)), 16) + "");
//                                    //CellId
//                                    System.out.println("ffffffff".equals(str.substring(celldBegin, celldEnd)) ? "null" : Integer.parseInt(StringPin(str.substring(celldBegin, celldEnd)), 16) + "------CellId：");
//                                    //Priority 本小区频点优先级
//                                    System.out.println("优先级--" + str.substring(64, 72));
//                                    System.out.println("ffffffff".equals(str.substring(priorityBegin, priorityEnd)) ? "null" : Integer.parseInt(StringPin(str.substring(priorityBegin, priorityEnd)), 16) + "------Priority 本小区频点优先级：");
//                                    Log.d("nzqffffffff", "ffffffff".equals(str.substring(priorityBegin, priorityEnd)) ? "0" : Integer.parseInt(StringPin(str.substring(priorityBegin, priorityEnd)), 16) + "");
//                                    if ("ffffffff".equals(str.substring(priorityBegin, priorityEnd))) {
//                                        Log.d("1nzqffffffff", "run:1 ");
//                                        spBean.setPriority(0);
//                                    } else {
//                                        spBean.setPriority(Integer.parseInt(StringPin(str.substring(priorityBegin, priorityEnd)), 16));
//                                        Log.d("2nzqffffffff", "run:1 ");
//                                    }
////
////
////                                    spBean.setPriority(Integer.parseInt(StringPin(str.substring(priorityBegin, priorityEnd)), 16)+"");
//                                    //RSRP
//                                    System.out.println("RSRP：------" + Integer.parseInt(StringPin(str.substring(rsrpBegin, rsrpEnd)), 16));
//                                    //RSRQ
//                                    System.out.println("RSRQ：------" + Integer.parseInt(StringPin(str.substring(rsrqBegin, rsrqEnd)), 16));
//                                    spBean.setRsrp(Integer.parseInt(StringPin(str.substring(rsrqBegin, rsrqEnd)), 16));
//
//                                    if (spBean.getDown().equals("0")) {
//
//                                    } else {
//                                        spBeanList.add(spBean);//测试代码add
//                                    }
//
//                                    //Bandwidth小区工作带宽
//                                    System.out.println("Bandwidth：------" + Integer.parseInt(StringPin(str.substring(bandWidthBegin, bandWidthEnd)), 16));
//                                    spBean.setBand(Integer.parseInt(StringPin(str.substring(bandWidthBegin, bandWidthEnd)), 16) + "");
//                                    //TddSpecialSf Patterns TDD特殊子帧配置
//                                    System.out.println("TDD特殊子帧配置：------" + Integer.parseInt(StringPin(str.substring(tddSpecialSfBegin, tddSpecialSfEnd)), 16));
//                                    //异频小区个数
//                                    int InterFreqLstNum;
//                                    if ("ffffffff".equals(str.substring(interFreqLstNumBegin, interFreqLstNumEnd))) {
//                                        InterFreqLstNum = 0;
//                                    } else {
//                                        InterFreqLstNum = Integer.parseInt(StringPin(str.substring(interFreqLstNumBegin, interFreqLstNumEnd)), 16);
//                                    }
//                                    System.out.println(interFreqLstNumBegin + "---" + interFreqLstNumEnd);
//                                    System.out.println("异频小区个数：------" + InterFreqLstNum);
//
//                                    dlEarfcnBegin = dlEarfcnBegin + 64;
//                                    dlEarfcnEnd = dlEarfcnEnd + 64;
//                                    pciBegin = pciBegin + 64;
//                                    pciEnd = pciEnd + 64;
//                                    tacBegin = tacBegin + 64;
//                                    tacEnd = tacEnd + 64;
//                                    plmnBegin = plmnBegin + 64;
//                                    plmnEnd = plmnEnd + 64;
//                                    celldBegin = celldBegin + 64;
//                                    celldEnd = celldEnd + 64;
//                                    priorityBegin = priorityBegin + 64;
//                                    priorityEnd = priorityEnd + 64;
//                                    rsrpBegin = rsrpBegin + 64;
//                                    rsrpEnd = rsrpEnd + 64;
//                                    rsrqBegin = rsrqBegin + 64;
//                                    rsrqEnd = rsrqEnd + 64;
//                                    bandWidthBegin = bandWidthBegin + 64;
//                                    bandWidthEnd = bandWidthEnd + 64;
//                                    tddSpecialSfBegin = tddSpecialSfBegin + 64;
//                                    tddSpecialSfEnd = tddSpecialSfEnd + 64;
//                                    interFreqLstNumBegin = interFreqLstNumBegin + 64;
//                                    interFreqLstNumEnd = interFreqLstNumEnd + 64;
//
//                                    System.out.println(interFreqNghNumBegin + "---" + interFreqNghNumEnd);
////                                    for (int r = 0; r < InterFreqLstNum; r++) {
////                                        System.out.println(interFreqNghNumBegin + "---" + interFreqNghNumEnd);
////                                        //异频小区的领区数目
////                                        System.out.println(str.substring(interFreqNghNumBegin, interFreqNghNumEnd));
////                                        System.out.println("pin:" + StringPin(str.substring(interFreqNghNumBegin, interFreqNghNumEnd)));
////                                        System.out.println(Integer.parseInt(StringPin(str.substring(interFreqNghNumBegin, interFreqNghNumEnd)), 16));
////                                        int interFreqNghNum = Integer.parseInt(StringPin(str.substring(interFreqNghNumBegin, interFreqNghNumEnd)), 16);
////                                        System.out.println("异频小区的邻区个数：" + interFreqNghNum);
////
////                                        for (int n = 0; n < interFreqNghNum; n++) {
////                                            dlEarfcnBegin = dlEarfcnBegin + 8;
////                                            dlEarfcnEnd = dlEarfcnEnd + 8;
////                                            pciBegin = pciBegin + 8;
////                                            pciEnd = pciEnd + 8;
////                                            tacBegin = tacBegin + 8;
////                                            tacEnd = tacEnd + 8;
////                                            plmnBegin = plmnBegin + 8;
////                                            plmnEnd = plmnEnd + 8;
////                                            celldBegin = celldBegin + 8;
////                                            celldEnd = celldEnd + 8;
////                                            priorityBegin = priorityBegin + 8;
////                                            priorityEnd = priorityEnd + 8;
////                                            rsrpBegin = rsrpBegin + 8;
////                                            rsrpEnd = rsrpEnd + 8;
////                                            rsrqBegin = rsrqBegin + 8;
////                                            rsrqEnd = rsrqEnd + 8;
////                                            bandWidthBegin = bandWidthBegin + 8;
////                                            bandWidthEnd = bandWidthEnd + 8;
////                                            tddSpecialSfBegin = tddSpecialSfBegin + 8;
////                                            tddSpecialSfEnd = tddSpecialSfEnd + 8;
////                                            interFreqLstNumBegin = interFreqLstNumBegin + 8;
////                                            interFreqLstNumEnd = interFreqLstNumEnd + 8;
////                                            interFreqNghNumBegin = interFreqNghNumBegin + 8;
////                                            interFreqNghNumEnd = interFreqNghNumEnd + 8;
////                                        }
////
////                                        int number = InterFreqLstNum - r;
////                                        if (number != 1) {
////                                            interFreqNghNumBegin = interFreqNghNumBegin + 24;
////                                            interFreqNghNumEnd = interFreqNghNumEnd + 24;
////                                        }
////                                        dlEarfcnBegin = dlEarfcnBegin + 24;
////                                        dlEarfcnEnd = dlEarfcnEnd + 24;
////                                        pciBegin = pciBegin + 24;
////                                        pciEnd = pciEnd + 24;
////                                        tacBegin = tacBegin + 24;
////                                        tacEnd = tacEnd + 24;
////                                        plmnBegin = plmnBegin + 24;
////                                        plmnEnd = plmnEnd + 24;
////                                        celldBegin = celldBegin + 24;
////                                        celldEnd = celldEnd + 24;
////                                        priorityBegin = priorityBegin + 24;
////                                        priorityEnd = priorityEnd + 24;
////                                        rsrpBegin = rsrpBegin + 24;
////                                        rsrpEnd = rsrpEnd + 24;
////                                        rsrqBegin = rsrqBegin + 24;
////                                        rsrqEnd = rsrqEnd + 24;
////                                        bandWidthBegin = bandWidthBegin + 24;
////                                        bandWidthEnd = bandWidthEnd + 24;
////                                        tddSpecialSfBegin = tddSpecialSfBegin + 24;
////                                        tddSpecialSfEnd = tddSpecialSfEnd + 24;
////                                        interFreqLstNumBegin = interFreqLstNumBegin + 24;
////                                        interFreqLstNumEnd = interFreqLstNumEnd + 24;
////
////                                    }
//                                    interFreqNghNumBegin = interFreqNghNumBegin + 64;
//                                    interFreqNghNumEnd = interFreqNghNumEnd + 64;
//                                }
//                                //测试代码37900
////                                SpBean spBean = new SpBean();
////                                spBean.setRsrp(0);
////                                spBean.setPriority(20);
////                                spBean.setDown("37900");
////                                spBean.setPci(1234);
////                                spBean.setTac(987654);
////                                spBean.setPlmn("46000");
////                                spBean.setBand(38+"");
////                                spBeanList.add(spBean);
////                                spBeanList.add(spBean);
////
////                                SpBean spBean22 = new SpBean();
////                                spBean22.setRsrp(0);
////                                spBean22.setPriority(0);
////                                spBean22.setDown("37200");
////                                spBeanList.add(spBean22);
//                                if (spBeanList.size() == 0) {
//
//                                } else {
//                                    Log.d("nzqspBeanList1", "" + spBeanList);
////                                spBeanList.sort(Comparator.comparing(SpBean::getPriority));
//                                    //先根据优先级,如果优先级一样根据RSRP
//                                    List<Integer> list = new ArrayList();
//                                    String down1 = "";
//                                    SpBean spBean1 = new SpBean();
//                                    SpBean spBean2 = new SpBean();
//                                    if (spBeanList.size() >= 2) {
//                                        for (int i = 0; i < spBeanList.size(); i++) {
//                                            list.add(spBeanList.get(i).getPriority());
//                                        }
//                                        Integer max = Collections.max(list);
//                                        Log.d("Anzqmax", "大于2条run: " + max);
//                                        list.remove(max);//最大的优先
//
//                                        for (int i = 0; i < spBeanList.size(); i++) {
//                                            if (max.equals(spBeanList.get(i).getPriority())) {
//                                                down1 = spBeanList.get(i).getDown();
//                                                spBean1 = spBeanList.get(i);
//                                            }
//                                        }
//                                        //第二个优先
//                                        String down2 = "";
//                                        Integer max2 = Collections.max(list);
////                                    Log.d("Anzqmax2", "run: " + max);
//                                        for (int i = 0; i < spBeanList.size(); i++) {
//                                            if (max2.equals(spBeanList.get(i).getPriority())) {
//
//                                                down2 = spBeanList.get(i).getDown();
//                                                spBean2 = spBeanList.get(i);
//                                            }
//                                        }
//                                        Log.d("down2a", "run: " + down2);
//                                        if (max != max2) {
//
//                                            if (!down1.equals(down2)) {//优先级的频点一致 比较频点是否一致
//                                                message = new Message();
//                                                bundle.putString("sp1MAX1value", down1);//下行
//                                                bundle.putString("sp1up", spBean1.getUp() + "");
//                                                bundle.putString("sp1pci", spBean1.getPci() + "");
//                                                bundle.putString("sp1plmn", spBean1.getPlmn() + "");
//                                                bundle.putString("sp1band", spBean1.getBand() + "");
//                                                bundle.putString("sp1tac", spBean1.getTac() + "");
//
//                                                bundle.putString("sp1MAX2value", down2);
//                                                bundle.putString("sp2up", spBean2.getUp() + "");
//                                                bundle.putString("sp2pci", spBean2.getPci() + "");
//                                                bundle.putString("sp2plmn", spBean2.getPlmn() + "");
//                                                bundle.putString("sp2band", spBean2.getBand() + "");
//                                                bundle.putString("sp2tac", spBean2.getTac() + "");
//                                                message.setData(bundle);
//                                                handler.sendMessage(message);
//                                                message.what = 100152;
//                                                Log.d("Anzqmax", "大于2条run且优先级有区别但是频点一致: " + max);
//                                            } else {
//                                                message = new Message();
//                                                bundle.putString("sp1MAX1value", down1);//下行
//                                                bundle.putString("sp1up", spBean1.getUp() + "");
//                                                bundle.putString("sp1pci", spBean1.getPci() + "");
//                                                bundle.putString("sp1plmn", spBean1.getPlmn() + "");
//                                                bundle.putString("sp1band", spBean1.getBand() + "");
//                                                bundle.putString("sp1tac", spBean1.getTac() + "");
//
//                                                for (int i = 0; i < spBeanList.size(); i++) {
//                                                    if (!down1.equals(spBeanList.get(i).getDown())) {
//                                                        down2 = spBeanList.get(i).getDown();
//                                                        spBean2 = spBeanList.get(i);
//                                                        break;
//                                                    }
//                                                }
//
//
//                                                bundle.putString("sp1MAX2value", down2);
//                                                bundle.putString("sp2up", spBean2.getUp() + "");
//                                                bundle.putString("sp2pci", spBean2.getPci() + "");
//                                                bundle.putString("sp2plmn", spBean2.getPlmn() + "");
//                                                bundle.putString("sp2band", spBean2.getBand() + "");
//                                                bundle.putString("sp2tac", spBean2.getTac() + "");
//                                                message.setData(bundle);
//                                                handler.sendMessage(message);
//                                                message.what = 100152;
//                                                Log.d("Anzqmax", "大于2条run且优先级有区别但是频点不一致: " + down1 + "--" + down2);
//                                            }
//
//
//                                        } else {//根据优先级比较一致  ,通过rsrp比较
//
//                                            int rssp1;
//                                            int rssp2;
//                                            List<Integer> list1rsp = new ArrayList<>();
//                                            for (int i = 0; i < spBeanList.size(); i++) {
//                                                list1rsp.add(spBeanList.get(i).getRsrp());
//                                            }
//                                            //最大的rsrp
//                                            rssp1 = Collections.max(list1rsp);
//                                            for (int i = 0; i < spBeanList.size(); i++) {
//                                                if (rssp1 == spBeanList.get(i).getRsrp()) {
//                                                    down1 = spBeanList.get(i).getDown();
//                                                    spBean1 = spBeanList.get(i);
//                                                }
//                                            }
//                                            for (int i = 0; i < list1rsp.size(); i++) {
//                                                if (list1rsp.get(i).equals(rssp1)) {
//                                                    list1rsp.remove(i);
//                                                }
//                                            }
//                                            //求第二个rsrp
//                                            rssp2 = Collections.max(list1rsp);
//                                            for (int i = 0; i < spBeanList.size(); i++) {
//                                                if (rssp2 == spBeanList.get(i).getRsrp()) {
//                                                    down2 = spBeanList.get(i).getDown();
//                                                    spBean2 = spBeanList.get(i);
//                                                }
//                                            }
//                                            if (down1.equals(down2)) {
//                                                message = new Message();
//                                                bundle.putString("sp1MAX1value", down1);//下行
//                                                bundle.putString("sp1up", spBean1.getUp() + "");
//                                                bundle.putString("sp1pci", spBean1.getPci() + "");
//                                                bundle.putString("sp1plmn", spBean1.getPlmn() + "");
//                                                bundle.putString("sp1band", spBean1.getBand() + "");
//                                                bundle.putString("sp1tac", spBean1.getTac() + "");
//                                                for (int i = 0; i < spBeanList.size(); i++) {
//                                                    if (!down1.equals(spBeanList.get(i).getDown())) {
//                                                        down2 = spBeanList.get(i).getDown();
//                                                        spBean2 = spBeanList.get(i);
//                                                        break;
//                                                    }
//
//                                                }
//                                                bundle.putString("sp1MAX2value", "");
//                                                bundle.putString("sp2up", spBean2.getUp() + "");
//                                                bundle.putString("sp2pci", spBean2.getPci() + "");
//                                                bundle.putString("sp2plmn", spBean2.getPlmn() + "");
//                                                bundle.putString("sp2band", spBean2.getBand() + "");
//                                                bundle.putString("sp2tac", spBean2.getTac() + "");
//                                                message.setData(bundle);
//                                                handler.sendMessage(message);
//                                                message.what = 100152;
//                                                Log.d("Anzqmax", "大于2条run且优先级没区别用RSRP比较且下频一致取第二个不应的: " + down1 + "--" + down2);
//                                            } else {
//                                                message = new Message();
//                                                bundle.putString("sp1MAX1value", down1);//下行
//                                                bundle.putString("sp1up", spBean1.getUp() + "");
//                                                bundle.putString("sp1pci", spBean1.getPci() + "");
//                                                bundle.putString("sp1plmn", spBean1.getPlmn() + "");
//                                                bundle.putString("sp1band", spBean1.getBand() + "");
//                                                bundle.putString("sp1tac", spBean1.getTac() + "");
//
//                                                bundle.putString("sp1MAX2value", down2);
//                                                bundle.putString("sp2up", spBean2.getUp() + "");
//                                                bundle.putString("sp2pci", spBean2.getPci() + "");
//                                                bundle.putString("sp2plmn", spBean2.getPlmn() + "");
//                                                bundle.putString("sp2band", spBean2.getBand() + "");
//                                                bundle.putString("sp2tac", spBean2.getTac() + "");
//                                                message.setData(bundle);
//                                                handler.sendMessage(message);
//                                                message.what = 100152;
//                                                Log.d("Anzqmax", "大于2条run且优先级没区别用RSRP比较且下频不一致: " + down1 + "--" + down2);
//                                            }
//
//
//                                            ToastUtils.showToast("当前条数为多条");
////                                        Log.d("Anzqmax", "大于2条run且优先级没区别用RSRP比较: "+down1+"--"+down2 );
//                                        }
//
//                                    } else {
//                                        //
//
//                                        if (spBeanList.size() > 0 && spBeanList.size() == 1) {
//                                            down1 = spBeanList.get(0).getDown();
////                                            spBean2 = spBeanList.get(0);
//                                            message = new Message();
//                                            bundle.putString("sp1MAX1value", down1);
//                                            bundle.putString("sp1up", spBeanList.get(0).getUp() + "");
//                                            bundle.putString("sp1pci", spBeanList.get(0).getPci() + "");
//                                            bundle.putString("sp1plmn", spBeanList.get(0).getPlmn() + "");
//                                            bundle.putString("sp1band", spBeanList.get(0).getBand() + "");
//                                            bundle.putString("sp1tac", spBeanList.get(0).getTac() + "");
//
//                                            bundle.putString("sp1MAX2value", "");
//                                            bundle.putString("sp2up", "");
//                                            bundle.putString("sp2pci", "");
//                                            bundle.putString("sp2plmn", "");
//                                            bundle.putString("sp2band", "");
//                                            bundle.putString("sp2tac", "");
//
//
//                                            message.setData(bundle);
//                                            handler.sendMessage(message);
//                                            message.what = 100152;
//                                            ToastUtils.showToast("当前条数为1");
//                                            Log.d("Anzqmax", "当前条数为1: ");
//                                        } else {
//                                            message = new Message();
//                                            bundle.putString("sp1MAX1value", "");
//                                            bundle.putString("sp1MAX2value", "");
//                                            message.setData(bundle);
//                                            handler.sendMessage(message);
//                                            message.what = 100152;
//                                            ToastUtils.showToast("当前条数为0");
//                                            Log.d("Anzqmax", "当前条数为0: ");
//                                        }
//                                    }
//                                }
//
////                                } else if (spBeanList.size() > 0 && spBeanList.size() == 1) {//如果只有一条
////                                    if (!spBeanList.get(0).getDown().equals("0")) {
////                                        down1 = spBeanList.get(0).getDown();
////                                        spBean2 = spBeanList.get(0);
////                                        message = new Message();
////                                        bundle.putString("sp1MAX1value", down1);
////                                        bundle.putString("sp1up", spBeanList.get(0).getUp() + "");
////                                        bundle.putString("sp1pci", spBeanList.get(0).getPci() + "");
////                                        bundle.putString("sp1plmn", spBeanList.get(0).getPlmn() + "");
////                                        bundle.putString("sp1band", spBeanList.get(0).getBand() + "");
////                                        bundle.putString("sp1tac", spBeanList.get(0).getTac() + "");
////
////                                        bundle.putString("sp1MAX2value", "");
////                                        message.setData(bundle);
////                                        handler.sendMessage(message);
////                                        message.what = 100152;
////                                        ToastUtils.showToast("当前条数为1");
////                                        Log.d("Anzqmax", "当前条数为1: ");
////                                    }
////                                } else { //当没有大于1 或者大于2
////                                    message = new Message();
////                                    bundle.putString("sp1MAX1value", "");
////                                    bundle.putString("sp1MAX2value", "");
////                                    message.setData(bundle);
////                                    handler.sendMessage(message);
////                                    message.what = 100152;
////                                    ToastUtils.showToast("当前条数为0");
////                                    Log.d("Anzqmax", "当前条数为0: ");
////                                }
//                            }

                    }

                    //设备2
                    if (IP2.equals(dp.getAddress().getHostAddress())) {
                        mPressedTime2 = System.currentTimeMillis();
                        System.out.println("123456");
                        System.out.println("收到2A" + dp.getAddress().getHostAddress() + "发送数据：" + str);
                        //时间
                        Date d = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        System.out.println("时间：" + sdf.format(d));

                        if ("2cf0".equals(str.substring(8, 12))) {

                            if ("00000000".equals(str.substring(24, 32))) {
                                System.out.println("设备型号：" + hexStringToString(str.substring(32, 46)));
                                DEVICENUMBER2 = hexStringToString(str.substring(32, 46));
                            } else if ("01000000".equals(str.substring(24, 32))) {
                                System.out.println("硬件版本：" + hexStringToString(str.substring(32, 34)));
                                HARDWAREVERSION2 = hexStringToString(str.substring(32, 34));
                            } else if ("02000000".equals(str.substring(24, 32))) {
                                System.out.println("软件版本：" + hexStringToString(str.substring(32, 106)));
                                SOFTWAREVERSION2 = hexStringToString(str.substring(32, 106));
                            } else if ("03000000".equals(str.substring(24, 32))) {
                                System.out.println("序列号SN:" + hexStringToString(str.substring(32, 70)));
                                SNNUMBER2 = hexStringToString(str.substring(32, 70));
                            } else if ("04000000".equals(str.substring(24, 32))) {
                                System.out.println("MAC地址：" + hexStringToString(str.substring(32, 66)));
                                MACADDRESS2 = hexStringToString(str.substring(32, 66));
                            } else if ("05000000".equals(str.substring(24, 32))) {
                                UBOOTVERSION2 = hexStringToString(str.substring(32, 47));
                                System.out.println("uboot版本号：" + hexStringToString(str.substring(32, 47)));
                            } else if ("06000000".equals(str.substring(24, 32))) {
                                System.out.println("板卡温度：" + hexStringToString(str.substring(32, 50)));
                                BOARDTEMPERATURE2 = hexStringToString(str.substring(32, 50));


                            }
                        }
                        if ("2ef0".equals(str.substring(8, 12))) {
                            Log.d("nzq2df0", str.toString());
                            System.out.println("同步类型" + str.toString());
                            String substring = str.substring(25, 26);
                            TBTYPE2 = substring.toString();
                            TBZT2 = str.substring(29, 30);
                            Log.d("Asubstring", "run: " + substring.toString());
                        }
                        if ("66f0".equals(str.substring(8, 12))) {
                            REQUEST2 = Integer.parseInt(StringPin(str.substring(24, 32)), 16) + "";
                            ENDNUM2 = Integer.parseInt(StringPin(str.substring(32, 40)), 16) + "";
                            IMSINUM2 = Integer.parseInt(StringPin(str.substring(56, 64)), 16) + "";
//                            Log.d("Asubstring66f0", "--run: " + Integer.parseInt(str.substring(24, 26), 16)+"/n"+ENDNUM1+"/n"+IMSINUM1);
                            String substring = str.substring(24, 32);
                            String substring1 = str.substring(32, 40);
                            String substring2 = str.substring(56, 64);
//                            Log.d("Asubstring66f0REQUEST1", "run: "+substring.toString());
//                            Log.d("Asubstring66f0ENDNUM1", "run: "+substring1.toString());
//                            Log.d("Asubstring66f0IMSINUM1", "run: "+substring2.toString());
//                            Log.d("Asubstring66f0", "run: "+str.toString());
//                            REQUEST2=      Integer.parseInt(StringPin(str.substring(24, 32)), 16)+"";
                            Log.d("2Asubstring66f0REQUEST1", "run: " + REQUEST2);
                            Log.d("2Asubstring66f0REQUEST1", "run: " + ENDNUM2);
                            Log.d("2Asubstring66f0REQUEST1", "run: " + IMSINUM2);
                            Log.d("2Asubstring66f0REQUEST1", "run: " + str.toString());
                        }
//                        if ("66f0".equals(str.substring(8, 12))) {
//                            REQUEST2 = Integer.parseInt(str.substring(24, 32), 16) + "";
//                            ENDNUM2 = Integer.parseInt(str.substring(32, 40), 16) + "";
//                            IMSINUM2 = Integer.parseInt(str.substring(48, 56), 16) + "";
//                            Log.d("Asubstring66f0", "--run: " + Integer.parseInt(str.substring(24, 26), 16)+"/n"+ENDNUM2+"/n"+IMSINUM2);
//                        }

                        //当前小区信息状态响应
                        if ("28f0".equals(str.substring(8, 12))) {
                            //PLMN
                            System.out.println(StringTOIMEI(str.substring(40, 50)));
                            PLMN2 = StringTOIMEI(str.substring(40, 50));
                            //上行频点
                            System.out.println(Integer.parseInt(StringPin(str.substring(24, 32)), 16));
                            UP2 = Integer.parseInt(StringPin(str.substring(24, 32)), 16) + "";
                            //下行频点
                            System.out.println(Integer.parseInt(StringPin(str.substring(32, 40)), 16));
                            DWON2 = Integer.parseInt(StringPin(str.substring(32, 40)), 16) + "";
                            //Band
                            System.out.println(Integer.parseInt(StringPin(str.substring(56, 64)), 16));
                            BAND2 = Integer.parseInt(StringPin(str.substring(56, 64)), 16) + "";
                            //带宽
                            System.out.println(Integer.parseInt(str.substring(54, 56), 16));
                            DK2 = Integer.parseInt(str.substring(54, 56), 16) + "";
                            //TAC
                            System.out.println(Integer.parseInt(StringPin(str.substring(68, 72)), 16));
                            TAC2 = Integer.parseInt(StringPin(str.substring(68, 72)), 16) + "";
                            //PCI
                            System.out.println(Integer.parseInt(StringPin(str.substring(64, 68)), 16));
                            PCI2 = Integer.parseInt(StringPin(str.substring(64, 68)), 16) + "";
                            //Cellld
                            System.out.println(Integer.parseInt(StringPin(str.substring(72, 80)), 16));
                            CELLID2 = Integer.parseInt(StringPin(str.substring(72, 80)), 16) + "";
                            //UePMax
                            System.out.println(Integer.parseInt(StringPin(str.substring(80, 84)), 16));
                            DBM2 = Integer.parseInt(StringPin(str.substring(80, 84)), 16) + "";
                            //EnodeBPMax
                            System.out.println(Integer.parseInt(StringPin(str.substring(84, 88)), 16));
//                            TYPE2 = Integer.parseInt(StringPin(str.substring(84, 88)), 16) + "";

                        }
                        //小区状态信息查询指令的响应
                        if ("30f0".equals(str.substring(8, 12))) {
                            if ("00000000".equals(str.substring(24, 32))) {
                                System.out.println("小区空闲态");
                                TYPE2 = "小区空闲态";
                            } else if ("01000000".equals(str.substring(24, 32))) {
                                System.out.println("同步或扫频中");
                                TYPE2 = "同步或扫频中";
                            } else if ("02000000".equals(str.substring(24, 32))) {
                                System.out.println("小区激活中");
                                TYPE2 = "小区激活中";
                            } else if ("03000000".equals(str.substring(24, 32))) {
                                System.out.println("小区已激活");
                                TYPE2 = "小区已激活";
                            } else if ("04000000".equals(str.substring(24, 32))) {
                                System.out.println("小区去激活中");
                                TYPE2 = "小区去激活中";
                            }
                        }
                        //UE测量配置查询响应信息
                        if ("3ef0".equals(str.substring(8, 12))) {
                            //工作模式
                            if ("00".equals(str.substring(24, 26))) {
                                System.out.println("持续侦码模式");
                                GZMS2 = "持续侦码模式";
                            } else if ("01".equals(str.substring(24, 26))) {
                                System.out.println("周期侦码模式");
                                //周期模式参数，指示针对同一个IMSI 的抓号周期
                                System.out.println(Integer.parseInt(StringPin(str.substring(28, 32)), 16));
                                ZHZQ2 = "" + Integer.parseInt(StringPin(str.substring(28, 32)), 16);
                                GZMS2 = "周期侦码模式";
                            } else if ("02".equals(str.substring(24, 26))) {
                                System.out.println("定位模式");
                                GZMS2 = "定位模式";
                                //定位模式，定位的终端 IMSI
                                UEIMS2 = StringTOIMEI(str.substring(32, 62));
                                System.out.println("imsi:" + StringTOIMEI(str.substring(32, 62)));
                                //定位模式，终端测量值的上报周期,建议设置为 1024ms, 0：120ms,1：240ms,2：480ms,3：640ms,4：1024ms,5：2048ms

                                if ("00".equals(str.substring(66, 68))) {
                                    System.out.println("120ms");
                                    SBZQ2 = "120ms";
                                } else if ("01".equals(str.substring(66, 68))) {
                                    System.out.println("240ms");
                                    SBZQ2 = "240ms";
                                } else if ("02".equals(str.substring(66, 68))) {
                                    System.out.println("480ms");
                                    SBZQ2 = "480ms";
                                } else if ("03".equals(str.substring(66, 68))) {
                                    System.out.println("640ms");
                                    SBZQ2 = "640ms";
                                } else if ("04".equals(str.substring(66, 68))) {
                                    System.out.println("1024ms");
                                    SBZQ2 = "1024ms";
                                } else if ("05".equals(str.substring(66, 68))) {
                                    System.out.println("2048ms");
                                    SBZQ2 = "2048ms";
                                }
                                //定位模式，调度定位终端最大功率发射开关，需要设置为 0,0：enable,1：disable
                                if ("00".equals(str.substring(68, 70))) {
                                    System.out.println("enable");
                                    UEMAXOF2 = "开";
                                } else if ("01".equals(str.substring(68, 70))) {
                                    System.out.println("disable");
                                    UEMAXOF2 = "关";
                                }
                                //定位模式，UE 最大发射功率，最大值 不 超 过wrFLLmtToEnbSysArfcnCfg 配置的UePMax，建议设置为 23dBm
                                UEMAX2 = Integer.parseInt(str.substring(70, 72), 16) + "";
                                System.out.println(Integer.parseInt(str.substring(70, 72), 16));

                                //定位模式，由于目前都采用 SRS 方案配合单兵，因此此值需要设置为disable，否则大用户量时有定位终端掉线概率。0: disable,1: enable
                                if ("00".equals(str.substring(72, 74))) {
                                    System.out.println("disable");
                                } else if ("01".equals(str.substring(72, 74))) {
                                    System.out.println("enable");
                                }
                                //定位模式，是否把非定位终端踢回公网的配置，建议设置为 0。设置为 1 的方式是为了发布版本时保留用户反复接入基站测试使用。1：非定位终端继续被本小区吸附, 0：把非定位终端踢回公网
                                if ("00".equals(str.substring(76, 78))) {
                                    System.out.println("把非定位终端踢回公网");
                                } else if ("01".equals(str.substring(76, 78))) {
                                    System.out.println("非定位终端继续被本小区吸附");
                                }
                                //定位模式，是否对定位终端建立SRS 配置。
                                if ("00".equals(str.substring(78, 80))) {
                                    System.out.println("disable");
                                } else if ("01".equals(str.substring(78, 80))) {
                                    System.out.println("enable");
                                }
                            } else if ("03".equals(str.substring(24, 26))) {
                                System.out.println("管控模式");
                                GZMS2 = "管控模式";
                                //管控模式的子模式0：黑名单子模式；1：白名单子模式
                                if ("00".equals(str.substring(80, 82))) {
                                    System.out.println("黑名单子模式");
                                    GZMS2 = "黑名单子模式";
                                } else if ("01".equals(str.substring(80, 82))) {
                                    System.out.println("白名单子模式");
                                    GZMS2 = "白名单子模式";
                                }
                            } else if ("04".equals(str.substring(24, 26))) {
                                System.out.println("重定向模式");
                                GZMS2 = "重定向模式";
                            /*0: 名单中的用户执行重定向；名单外的全部踢回公网
                            1: 名单中的用户踢回公网；名单外的全部重定向
							2: 名单中的用户执行重定向；名单外的全部吸附在本站
							3: 名单中的用户吸附在本站;名单外的全部重定向
							4: 所有目标重定向*/
                                System.out.println(Integer.parseInt(str.substring(26, 28), 16));

                            }

                        }

                        if ("56f0".equals(str.substring(8, 12))) {

                            //黑名单数量
                            Integer blacklistNum = Integer.parseInt(StringPin(str.substring(24, 28)), 16);
                            int begin = 28;
                            int end = 58;
                            for (int i = 0; i < blacklistNum; i++) {
                                System.out.println("黑名单打印2" + StringTOIMEI(str.substring(begin, end)));
//                                for (int j = 0; j < BLACKLISTSB2.size(); j++) {
//                                    if (BLACKLISTSB2.get(i).equals(StringTOIMEI(str.substring(begin, end)))) {
//
//                                    }
//                                }
                                BLACKLISTSB2.add(StringTOIMEI(str.substring(begin, end)));
                                begin = begin + 34;
                                end = end + 34;
                            }

                        }
                        //接受增益和发射功率查询的响应
                        if ("32f0".equals(str.substring(8, 12))) {
                            //增益
                            //寄存器中的值，实际生效的值（FDD 模式下仅在建立完小区查询，该值有效）
                            System.out.println(Integer.parseInt(str.substring(24, 26), 16));
                            ZENGYI2 = Integer.parseInt(str.substring(24, 26), 16) + "";
                            //发射功率  衰减
                            //寄存器中的值，实际生效的值（FDD 模式下仅在建立完小区查询，该值有效）
                            System.out.println(Integer.parseInt(str.substring(28, 30), 16));
                            SHUAIJIAN2 = Integer.parseInt(str.substring(28, 30), 16) + "";

//                            //数据库中的保存值，重启保留生效的值,
//                            System.out.println(Integer.parseInt(str.substring(26,28),16));
//
//                            //数据库中的保存值，重启保留生效的值
//                            System.out.println(Integer.parseInt(str.substring(30,32),16));
//                            //FDD AGC 开关
//                            if("00".equals(str.substring(30,32))){
//                                System.out.println("FDD AGC 开关关闭");
//                            }else if("01".equals(str.substring(30,32))){
//                                System.out.println("FDD AGC 开关打开");
//                            }

                            //增益
                            //只在FDD模式下有效，寄存器中的值，实际生效的值,该值只有在扫频完成后，建立小区前查询有效
//                            System.out.println(Integer.parseInt(str.substring(34,36),16));
//                            //eeprom 中的保存值，重启保留生效的值
//                            System.out.println(Integer.parseInt(str.substring(36,38),16));
                        }
                        //设置黑名单响应 清空响应
                        if ("54f0".equals(str.substring(8, 12))) {
                            if ("00000000".equals(str.substring(24, 32))) {
                                opionts11++;
                                System.out.println("设置基站黑名单成功" + opionts1);
                                if (opionts11 % 2 == 0) {
                                    Log.d("jsgs", "run:偶数");
                                    message = new Message();
                                    bundle.putString("200130", "FDD");
                                    message.setData(bundle);
                                    handler.sendMessage(message);
                                    message.what = 200131;
                                    mListener.heandleMessage(message);
                                } else {
                                    Log.d("jsgs", "run:基数");
                                    message = new Message();
                                    bundle.putString("200131", "FDD");
                                    message.setData(bundle);
                                    handler.sendMessage(message);
                                    message.what = 200130;
                                    mListener.heandleMessage(message);
                                }
                            } else {
                                System.out.println("设置基站黑名单失败");
                                if (opionts22 % 2 == 0) {
                                    Log.d("jsgs", "run:偶数");
                                    message = new Message();
                                    bundle.putString("200130", "FDD");
                                    message.setData(bundle);
                                    handler.sendMessage(message);
                                    message.what = 200135;
                                    mListener.heandleMessage(message);
                                } else {
                                    Log.d("jsgs", "run:基数");
                                    message = new Message();
                                    bundle.putString("200131", "FDD");
                                    message.setData(bundle);
                                    handler.sendMessage(message);
                                    message.what = 200134;
                                    mListener.heandleMessage(message);
                                }
                            }
                        }

                        //黑名单中标
                        if ("05f0".equals(str.substring(8, 12))) {
                            System.out.println("2黑名单中标IMSI号：" + hexStringToString(str.substring(32, 62)));
                            Date now = new Date();
                            String imsi = hexStringToString(str.substring(32, 62));
                            Log.d("jsgs", "run:基数");
                            message = new Message();
                            bundle.putString("imsi", imsi);
                            bundle.putString("sb", "2");
                            bundle.putString("zb", "");
                            bundle.putString("datetime", "" + ReceiveMainDateFormat1.format(now));
                            bundle.putString("time", "" + ReceiveMainDateFormat2.format(now));
                            message.setData(bundle);
                            handler.sendMessage(message);
                            message.what = 30000;
                            mListener.heandleMessage(message);
                        }

                        //设置定位模式的应答信息
                        if ("07f0".equals(str.substring(8, 12))) {
                            if ("00000000".equals(str.substring(24, 32))) {
                                System.out.println("设置基站ue定位成功");
                                message = new Message();
                                bundle.putString("test", "");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 200136;
                                mListener.heandleMessage(message);
                            } else {
                                System.out.println("设置基站ue定位失败");


                                message = new Message();
                                bundle.putString("test", "");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 200137;
                                mListener.heandleMessage(message);
                            }
                        }
                        //重启是否成功
                        if ("0cf0".equals(str.substring(8, 12))) {
                            //成功0；不成功>0（16进制字符串转换成十进制）
                            int row = Integer.parseInt(str.substring(24, 32), 16);
                            if (row == 0) {
                                System.out.println("重启设置成功！");
                                message = new Message();
                                bundle.putString("test", "");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 200138;
                                mListener.heandleMessage(message);
                            } else {
                                System.err.println("重启失败！");
                                message = new Message();
                                bundle.putString("test", "");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 200139;
                                mListener.heandleMessage(message);
                            }
                        }
                        //制式切换是否成功
                        if ("02f0".equals(str.substring(8, 12))) {
                            //成功0；不成功>0（16进制字符串转换成十进制）
                            int row = Integer.parseInt(str.substring(24, 32), 16);
                            if (row == 0) {
                                System.out.println("制式切换成功！");
                                message = new Message();
                                bundle.putString("test", "");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 200140;
                                mListener.heandleMessage(message);
                            } else {
                                System.err.println("制式切换失败");
                                message = new Message();
                                bundle.putString("test", "");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 200141;
                                mListener.heandleMessage(message);
                            }
                        }
                        //模式：FDD TDD
                        if ("00ff".equals(str.substring(16, 20))) {
                            //设置模式FDD
                            ID2TF = "FDD";
                            System.err.println("FDD");
                            message = new Message();
                            bundle.putString("tf2", "FDD");
                            message.setData(bundle);
                            handler.sendMessage(message);
                            message.what = 200110;
                            mListener.heandleMessage(message);
                        }
                        if ("ff00".equals(str.substring(16, 20))) {
                            //设置模式TDD
                            ID2TF = "TDD";
                            System.out.println("TDD");
                            message = new Message();
                            bundle.putString("tf2", "TDD");
                            message.setData(bundle);
                            handler.sendMessage(message);
                            message.what = 200110;
                            mListener.heandleMessage(message);
                        }
                        //建立小区是否成功
                        if ("04f0".equals(str.substring(8, 12))) {
                            //成功0；不成功>0（16进制字符串转换成十进制）
                            int row = Integer.parseInt(str.substring(24, 32), 16);
                            if (row == 0) {
                                message = new Message();
                                bundle.putString("test", "0");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 200145;
                                mListener.heandleMessage(message);
                                System.out.println("设置成功！开始建立小区！");
                            } else {
                                System.err.println("设置失败！");
                                message = new Message();
                                bundle.putString("test", "0");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 200146;
                                mListener.heandleMessage(message);
                            }
                        }
                        //公放设置
                        if ("a0f0".equals(str.substring(8, 12))) {
                            //成功0；不成功>0（16进制字符串转换成十进制）
                            int row = Integer.parseInt(str.substring(24, 32), 16);
                            if (row == 0) {
                                System.out.println("公放设置成功！");
                                message = new Message();
                                bundle.putString("test", "0");
                                message.setData(bundle);
                                handler.sendMessage(message);
//                                message.what = 200142;
                                mListener.heandleMessage(message);
                            } else {
                                System.err.println("设置失败！");
                                message = new Message();
                                bundle.putString("test", "0");
                                message.setData(bundle);
                                handler.sendMessage(message);
//                                message.what = 200143;
                                mListener.heandleMessage(message);
                            }
                        }
                        //去激活小区是否成功
                        if ("0ef0".equals(str.substring(8, 12))) {
                            //成功0；不成功>0（16进制字符串转换成十进制）
                            int row = Integer.parseInt(str.substring(24, 32), 16);
                            if (row == 0) {
                                System.out.println("设置成功！去激活小区成功，停止定位！");
                            } else {
                                System.err.println("设置失败！");
                            }
                        }
                        //基站执行状态
                        //基站执行状态
                        if ("19f0".equals(str.substring(8, 12))) {
                            String state = str.substring(24, 32);
                            System.out.println("state" + state);
                            Log.d("wtto", "staterun: " + state);
                            if ("00000000".equals(state)) {
                                System.err.println("空口同步成功");
                                Log.d("wtto", "qqqrun:空口同步成功");
                                message = new Message();
                                bundle.putString("test", "1");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 2001200;
                                mListener.heandleMessage(message);
                            } else if ("01000000".equals(state)) {
                                System.err.println("空口同步失败");
                                Log.d("wtto", "qqqrun:空口同步失败");
                                message = new Message();
                                bundle.putString("test", "2");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 2001200;
                                mListener.heandleMessage(message);
                            } else if ("02000000".equals(state)) {
                                System.err.println("GPS同步成功");
                                Log.d("wtto", "qqqrun:GPS同步成功");
                                message = new Message();
                                bundle.putString("test", "3");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 2001200;
                                mListener.heandleMessage(message);
                            } else if ("03000000".equals(state)) {
                                System.err.println("GPS同步失败");
                                Log.d("wtto", "qqqrun:GPS同步失败");
                                message = new Message();
                                bundle.putString("test", "4");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 2001200;
                                mListener.heandleMessage(message);
                            } else if ("04000000".equals(state)) {
                                System.err.println("扫频成功");
                                Log.d("wtto", "qqqrun:扫频成功");
                                message = new Message();
                                bundle.putString("test", "5");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 2001200;
                                mListener.heandleMessage(message);
                            } else if ("05000000".equals(state)) {
                                System.err.println("扫频失败");
                                Log.d("wtto", "qqqrun:扫频失败");
                                message = new Message();
                                bundle.putString("test", "6");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 2001200;
                                mListener.heandleMessage(message);
                            } else if ("06000000".equals(state)) {
                                System.err.println("小区激活成功");
                                Log.d("wtto", "qqqrun:小区激活成功");
                                message = new Message();
                                bundle.putString("test", "7");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 2001200;
                                mListener.heandleMessage(message);
                            } else if ("07000000".equals(state)) {
                                System.err.println("小区激活失败");
                                Log.d("wtto", "qqqrun:小区激活失败");
                                message = new Message();
                                bundle.putString("test", "8");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 2001200;
                                mListener.heandleMessage(message);
                            } else if ("08000000".equals(state)) {
                                System.err.println("小区去激活");
                                Log.d("wtto", "qqqrun:小区去激活");
                                message = new Message();
                                bundle.putString("test", "9");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 2001200;
                                mListener.heandleMessage(message);
                            } else if ("09000000".equals(state)) {
                                System.err.println("空口同步中");
                                Log.d("wtto", "qqqrun:空口同步中");
                                message = new Message();
                                bundle.putString("test", "10");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 2001200;
                                mListener.heandleMessage(message);
                            } else if ("0a000000".equals(state)) {
                                System.err.println("GPS同步中");
                                Log.d("wtto", "qqqrun:GPS同步中");
                                message = new Message();
                                bundle.putString("test", "11");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 2001200;
                                mListener.heandleMessage(message);
                            } else if ("0b000000".equals(state)) {
                                System.err.println("扫频中");
                                Log.d("wtto", "qqqrun:扫频中");
                                message = new Message();
                                bundle.putString("test", "12");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 2001200;
                                mListener.heandleMessage(message);
                            } else if ("0c000000".equals(state)) {
                                System.err.println("小区激活中");
                                Log.d("wtto", "qqqrun:小区激活中");
                                message = new Message();
                                bundle.putString("test", "13");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 2001200;
                                mListener.heandleMessage(message);
                            } else if ("0d000000".equals(state)) {
                                System.err.println("小区去激活中");
                                Log.d("wtto", "qqqrun:小区去激活中");
                                message = new Message();
                                bundle.putString("test", "14");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 2001200;
                                mListener.heandleMessage(message);
                            }
                        }
                        if ("08f0".equals(str.substring(8, 12))) {

                            //目标距离（16进制字符串转换成十进制）
                            Integer.parseInt(str.substring(24, 26), 16);
                            System.out.println("距离：" + Integer.parseInt(str.substring(24, 26), 16));
                            message = new Message();
                            bundle.putString("sb1j2", Integer.parseInt(str.substring(24, 26), 16) + "");
                            bundle.putString("imsi2", hexStringToString(str.substring(26, 56)) + "");
                            message.setData(bundle);
                            handler.sendMessage(message);
                            message.what = 200147;
                            mListener.heandleMessage(message);
                            //IMSI号
                            StringTOIMEI(str.substring(26, 56));
                            System.out.println("IMSI号zb：" + hexStringToString(str.substring(26, 56)));
                            Date now = new Date();
                            String imsi = hexStringToString(str.substring(26, 56));
                            message = new Message();
                            bundle.putString("imsi", imsi);
                            bundle.putString("sb", "2");
                            bundle.putString("zb", "2");
                            bundle.putString("datetime", "" + ReceiveMainDateFormat1.format(now));
                            bundle.putString("time", "" + ReceiveMainDateFormat2.format(now));
                            message.setData(bundle);
                            handler.sendMessage(message);
                            message.what = 30000;
                            mListener.heandleMessage(message);

                            message = new Message();
                            bundle.putString("imsi2", imsi);
                            message.setData(bundle);
                            handler.sendMessage(message);
                            message.what = 200148;
                            mListener.heandleMessage(message);
                        }

                        if ("10f0".equals(str.substring(8, 12))) {
                            //心跳解析
                            //查看小区是否建立成功（0：小区 IDLE态；1：扫频/同步进行中；2：小区激活中；3：小区激活态；4：小区去激活中）
                            message = new Message();
                            bundle.putString("zt1", "2");
                            message.setData(bundle);
                            handler.sendMessage(message);
                            message.what = 200120;
                            mListener.heandleMessage(message);
                            if ("0000".equals(str.substring(24, 28))) {
                                System.out.println("0：小区 IDLE态");
                                message = new Message();
                                bundle.putString("zt2", "2");//IDLE态
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 200120;
                                mListener.heandleMessage(message);
                            } else if ("0100".equals(str.substring(24, 28))) {
                                System.out.println("1：扫频/同步进行中");

                                message = new Message();
                                bundle.putString("zt2", "3");//扫频同步进行中
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 200120;
                                mListener.heandleMessage(message);
                            } else if ("0200".equals(str.substring(24, 28))) {
                                System.out.println("2：小区激活中");
                                message = new Message();
                                bundle.putString("zt2", "4");//小区激活中
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 100120;
                                mListener.heandleMessage(message);
                            } else if ("0300".equals(str.substring(24, 28))) {
                                System.out.println("3：定位中");
                                message = new Message();
                                bundle.putString("zt2", "5");//小区激活态
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 200120;
                                mListener.heandleMessage(message);
                                //Band号
                                Integer.parseInt(StringPin(str.substring(28, 32)), 16);
                                System.out.println("Band号：" + Integer.parseInt(StringPin(str.substring(28, 32)), 16));
                                //上行频点
                                Integer.parseInt(StringPin(str.substring(32, 40)), 16);
                                System.out.println("上行频点：" + Integer.parseInt(StringPin(str.substring(32, 40)), 16));
                                //下行频点
                                Integer.parseInt(StringPin(str.substring(40, 48)), 16);
                                DOWNPIN2 = Integer.parseInt(StringPin(str.substring(40, 48)), 16) + "";
                                System.out.println("下行频点：" + Integer.parseInt(StringPin(str.substring(40, 48)), 16));
                                message = new Message();
                                bundle.putString("down", Integer.parseInt(StringPin(str.substring(40, 48)), 16) + "");//查询增益
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 200151;
                                mListener.heandleMessage(message);
                                System.out.println("200151");
                                //移动联通电信
                                if ("3436303030".equals(str.substring(48, 58))) {
                                    //设置中国移动
                                }
                                if ("3436303031".equals(str.substring(48, 58))) {
                                    //设置中国联通
                                }
                                if ("3436303033".equals(str.substring(48, 58)) || "3436303131".equals(str.substring(48, 58))) {
                                    //设置中国电信
                                }

                                //PCI
                                Integer.parseInt(StringPin(str.substring(64, 68)), 16);
                                System.out.println("PCI:" + Integer.parseInt(StringPin(str.substring(64, 68)), 16));
                                //TAC
                                Integer.parseInt(StringPin(str.substring(68, 72)), 16);
                                System.out.println("TAC:" + Integer.parseInt(StringPin(str.substring(68, 72)), 16));

                            } else if ("0400".equals(str.substring(24, 28))) {
                                System.out.println("4：小区去激活中");
                            }

                        }
                        //温度告警
                        if ("5bf0".equals(str.substring(8, 12))) {
                            if ("00000000".equals(str.substring(32, 40))) {
                                System.out.println("基带板温度超过70度");
                            }
                            if ("01000000".equals(str.substring(32, 40))) {
                                System.out.println("基带板温度降低到70度以下了");
                            }

                        }
                        if ("32f0".equals(str.substring(8, 12))) {
                            message = new Message();
                            int i = Integer.parseInt(str.substring(24, 26), 16);

//                            Log.d(TAG, "run: " + i);
                            bundle.putString("zy2", Integer.parseInt(str.substring(24, 26), 16) + "");//查询增益
                            message.setData(bundle);
                            handler.sendMessage(message);
                            message.what = 200149;
                            System.out.println("200149");
                            mListener.heandleMessage(message);
                        }

                        //设置增益是否成功
                        if ("14f0".equals(str.substring(8, 12))) {
                            //成功0；不成功>0（16进制字符串转换成十进制）
                            int row = Integer.parseInt(str.substring(24, 32), 16);
                            if (row == 0) {
                                System.out.println("增益值设置成功!");
                                message = new Message();
                                bundle.putString("zyset2", "增益值设置成功");//小区激活中
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 200150;
                                mListener.heandleMessage(message);
                                System.out.println("200150");

                            } else {
                                System.err.println("增益值设置失败!");
                                message = new Message();
                                bundle.putString("zyset2", "增益值设置失败");//小区激活中
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 200150;
                                mListener.heandleMessage(message);
                                System.out.println("200150");
                            }
                        }
                        if ("7ef0".equals(str.substring(8, 12))) {
                            int row = Integer.parseInt(str.substring(24, 32), 16);
                            if (row == 0) {
                                System.out.println("SNF端口扫频设置成功！");

                                message = new Message();
                                bundle.putString("snf", "SNF端口扫频设置成功");//小区激活中
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 200154;
                                mListener.heandleMessage(message);
                                System.out.println("100154");

                            } else {
                                System.err.println("SNF端口扫频设置失败！");
                                message = new Message();
                                bundle.putString("snf", "SNF端口扫频设置失败");//小区激活中
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 200154;
                                mListener.heandleMessage(message);
                                System.out.println("100154");
                            }
                        }
                        //新 54

                        if ("0af0".equals(str.substring(8, 12))) {
                            //消息头长度
                            List<SpBean> spBeanList = new ArrayList<>();
                            String length = str.substring(12, 16);
                            String len = StringPin(length);
                            Integer strlen = Integer.parseInt(len, 16);
                            //是否发送完
                            Integer.parseInt(StringPin(str.substring(20, 24)), 16);
                            String code = Integer.toBinaryString(Integer.parseInt(StringPin(str.substring(20, 24)), 16));

                            code = StringAd(code);

                            System.err.println("code:" + code);
                            String s = str.substring(24, strlen * 2);
                            System.out.println("ss___" + s);
                            str4 = str4 + s;
                            System.err.println("s3___" + str4);
                            if ("0".equals(code.substring(0, 1))) {

                                str4 = "aaaa55550af0240000ff0000" + str4;
                                System.err.println("str3:+++++++++++++++" + str4);
                                //采集的小区数目
                                int row;
                                if ("ffff".equals(str4.substring(24, 28))) {
                                    row = 0;
                                } else {
                                    row = Integer.parseInt(StringPin(str4.substring(24, 28)), 16);
                                    System.out.println("小区个数：" + row);
                                }

                                int dlEarfcnBegin = 32, dlEarfcnEnd = 40;
                                int pciBegin = 40, pciEnd = 44;
                                int tacBegin = 44, tacEnd = 48;
                                int plmnBegin = 48, plmnEnd = 52;
                                int celldBegin = 56, celldEnd = 64;
                                int priorityBegin = 64, priorityEnd = 72;
                                int rsrpBegin = 72, rsrpEnd = 74;
                                int rsrqBegin = 74, rsrqEnd = 76;
                                int bandWidthBegin = 76, bandWidthEnd = 78;
                                int tddSpecialSfBegin = 78, tddSpecialSfEnd = 80;
                                int interFreqLstNumBegin = 88, interFreqLstNumEnd = 96;
                                int interFreqNghNumBegin = 0, interFreqNghNumEnd = 0;
                                for (int i = 0; i < row; i++) {
                                    //下行频点
                                    //下行频点
                                    SpBean spBean = new SpBean();
                                    System.out.println(str4.substring(dlEarfcnBegin, dlEarfcnEnd));
                                    if ("ffffffff".equals(str4.substring(dlEarfcnBegin, dlEarfcnEnd))) {
                                        System.out.println("null");

                                    } else {
                                        System.out.println("下行频点：------" + Integer.parseInt(StringPin(str4.substring(dlEarfcnBegin, dlEarfcnEnd)), 16));
                                        spBean.setDown(Integer.parseInt(StringPin(str4.substring(dlEarfcnBegin, dlEarfcnEnd)), 16) + "");
                                    }
                                    if (ID2TF.equals("TDD")) {
                                        spBean.setUp(255 + "");
                                    } else {
                                        if (!TextUtils.isEmpty(spBean.getDown())) {
                                            int i1 = Integer.parseInt(spBean.getDown()) + 18000;
                                            spBean.setUp(i1 + "");
                                        }
                                    }
                                    //PCI
//                                    System.out.println("PCI：------" + Integer.parseInt(StringPin(str4.substring(pciBegin, pciEnd)), 16));
                                    if ("ffff".equals(str4.substring(pciBegin, pciEnd))) {
                                        Log.d("1nzqffffffff", "run:1 ");
                                        spBean.setPci(0);
                                    } else {
                                        spBean.setPci(Integer.parseInt(StringPin(str4.substring(pciBegin, pciEnd)), 16));
                                        Log.d("2nzqffffffff", "run:1 ");
                                    }

//                                    spBean.setPci(Integer.parseInt(StringPin(str4.substring(pciBegin, pciEnd)), 16));
//                                    System.out.println(dlEarfcnBegin + "+" + dlEarfcnEnd);
//                                    spBean.setPci(0);
//                                    TAC
                                    try {
                                        str4.substring(tacBegin, tacEnd);
                                        spBean.setTac(Integer.parseInt(StringPin(str4.substring(tacBegin, tacEnd)), 16));
                                    } catch (StringIndexOutOfBoundsException e) {
                                        spBean.setTac(0);
                                    }
//                                    System.out.println("TAC：------" + Integer.parseInt(StringPin(str4.substring(tacBegin, tacEnd)), 16));

//                                    spBean.setTac(Integer.parseInt(StringPin(str4.substring(tacBegin, tacEnd)), 16));
//                                    if (str4.length() > tacEnd) {
//                                        if ("ffffffff".equals(str4.substring(tacBegin, tacEnd))) {
////
//                                            spBean.setTac(0);
//                                        } else {
//                                            spBean.setTac(Integer.parseInt(StringPin(str4.substring(tacBegin, tacEnd)), 16));
////
//                                        }
//                                    } else {
//                                        spBean.setTac(0);
//                                    }
//                                    spBean.setTac(0);
                                    //PLMN
//                                    System.out.println(Integer.parseInt(StringPin(str4.substring(plmnBegin, plmnEnd)), 16) + "---PLMN：");
//                                    spBean.setPlmn(Integer.parseInt(StringPin(str4.substring(plmnBegin, plmnEnd)), 16) + "");
//                                    spBean.setPlmn("");

                                    if ("ffff".equals(str4.substring(plmnBegin, plmnEnd))) {
                                        Log.d("1nzqffffffff", "run:1 ");
                                        spBean.setPlmn(0 + "");
                                    } else {
                                        spBean.setPlmn(Integer.parseInt(StringPin(str4.substring(plmnBegin, plmnEnd)), 16) + "");
                                        Log.d("2nzqffffffff", "run:1 ");
                                    }
                                    //CellId
//                                    System.out.println("ffffffff".equals(str4.substring(celldBegin, celldEnd)) ? "null" : Integer.parseInt(StringPin(str4.substring(celldBegin, celldEnd)), 16) + "------CellId：");
                                    if ("ffffffff".equals(str4.substring(celldBegin, celldEnd))) {
                                        Log.d("1nzqffffffff", "run:1 ");
                                        spBean.setCid(0 + "");
                                    } else {
                                        spBean.setCid(Integer.parseInt(StringPin(str4.substring(celldBegin, celldEnd)), 16) + "");
                                        Log.d("2nzqffffffff", "run:1 ");
                                    }
                                    //Priority 本小区频点优先级
//                                    System.out.println(str4.substring(64, 72));
                                    System.out.println("ffffffff".equals(str4.substring(priorityBegin, priorityEnd)) ? "Priority_null" : Integer.parseInt(StringPin(str4.substring(priorityBegin, priorityEnd)), 16) + "------Priority 本小区频点优先级：");
                                    if ("ffffffff".equals(str4.substring(priorityBegin, priorityEnd))) {
                                        Log.d("1nzqffffffff", "run:1 ");
                                        spBean.setPriority(0);
                                    } else {
                                        spBean.setPriority(Integer.parseInt(StringPin(str4.substring(priorityBegin, priorityEnd)), 16));
                                        Log.d("2nzqffffffff", "run:1 ");
                                    }
                                    //RSRP
//                                    System.out.println("RSRP：------" + Integer.parseInt(StringPin(str4.substring(rsrpBegin, rsrpEnd)), 16));
//                                    spBean.setRsrp(Integer.parseInt(StringPin(str4.substring(rsrpBegin, rsrpEnd)), 16));

                                    if ("ffffffff".equals(str4.substring(rsrpBegin, rsrpEnd))) {
                                        Log.d("1nzqffffffff", "run:1 ");
                                        spBean.setRsrp(0);
                                    } else {
                                        spBean.setRsrp(Integer.parseInt(StringPin(str4.substring(rsrpBegin, rsrpEnd)), 16));
                                        Log.d("2nzqffffffff", "run:1 ");
                                    }


                                    //RSRQ
//                                    System.out.println("RSRQ：------" + Integer.parseInt(StringPin(str4.substring(rsrqBegin, rsrqEnd)), 16));
//                                    spBean.setRsrq(Integer.parseInt(StringPin(str4.substring(rsrqBegin, rsrqEnd)), 16));

                                    if ("ffffffff".equals(str4.substring(rsrqBegin, rsrqEnd))) {
                                        Log.d("1nzqffffffff", "run:1 ");
                                        spBean.setRsrq(0);
                                    } else {
                                        spBean.setRsrq(Integer.parseInt(StringPin(str4.substring(rsrqBegin, rsrqEnd)), 16));
                                        Log.d("2nzqffffffff", "run:1 ");
                                    }
                                    //Bandwidth小区工作带宽
//                                    System.out.println("Bandwidth：------" + Integer.parseInt(StringPin(str4.substring(bandWidthBegin, bandWidthEnd)), 16));

                                    if ("ffffffff".equals(str4.substring(bandWidthBegin, bandWidthEnd))) {
                                        Log.d("1nzqffffffff", "run:1 ");
                                        spBean.setBand("");
                                    } else {
                                        spBean.setBand(Integer.parseInt(StringPin(str4.substring(bandWidthBegin, bandWidthEnd)), 16) + "");
                                        Log.d("2nzqffffffff", "run:1 ");
                                    }
//                                    spBean.setBand(Integer.parseInt(StringPin(str4.substring(bandWidthBegin, bandWidthEnd)), 16) + "");
                                    if (spBean.getDown().equals("0")) {

                                    } else {
                                        spBeanList.add(spBean);//测试代码add
                                    }
                                    //TddSpecialSf Patterns TDD特殊子帧配置
//                                        System.out.println("TDD特殊子帧配置：------" + Integer.parseInt(StringPin(str4.substring(tddSpecialSfBegin, tddSpecialSfEnd)), 16));
                                    //异频小区个数
//                                    int InterFreqLstNum;
//                                    if ("ffffffff".equals(str4.substring(interFreqLstNumBegin, interFreqLstNumEnd))) {
//                                        InterFreqLstNum = 0;
//                                    } else {
//                                        InterFreqLstNum = Integer.parseInt(StringPin(str4.substring(interFreqLstNumBegin, interFreqLstNumEnd)), 16);
//                                    }
                                    int InterFreqLstNum;
                                    try {
                                        if ("ffffffff".equals(str4.substring(interFreqLstNumBegin, interFreqLstNumEnd))) {
                                            InterFreqLstNum = 0;
                                        } else {
                                            try {
                                                InterFreqLstNum = Integer.parseInt(StringPin(str4.substring(interFreqLstNumBegin, interFreqLstNumEnd)), 16);
                                            } catch (Exception e) {
                                                InterFreqLstNum = 0;
                                                Log.d("nzqexce1", "run: " + e.getMessage());
                                            }
//                                        InterFreqLstNum = Integer.parseInt(StringPin(str3.substring(interFreqLstNumBegin, interFreqLstNumEnd)), 16);
                                        }
                                    } catch (Exception e) {
                                        InterFreqLstNum = 0;
                                        Log.d("nzqexce3", "run: " + e.getMessage());
                                    }
//                                        System.out.println(interFreqLstNumBegin + "---" + interFreqLstNumEnd);
//                                        System.out.println("异频小区个数：------" + InterFreqLstNum);

                                    dlEarfcnBegin = dlEarfcnBegin + 64;
                                    dlEarfcnEnd = dlEarfcnEnd + 64;
                                    pciBegin = pciBegin + 64;
                                    pciEnd = pciEnd + 64;
                                    tacBegin = tacBegin + 64;
                                    tacEnd = tacEnd + 64;
                                    plmnBegin = plmnBegin + 64;
                                    plmnEnd = plmnEnd + 64;
                                    celldBegin = celldBegin + 64;
                                    celldEnd = celldEnd + 64;
                                    priorityBegin = priorityBegin + 64;
                                    priorityEnd = priorityEnd + 64;
                                    rsrpBegin = rsrpBegin + 64;
                                    rsrpEnd = rsrpEnd + 64;
                                    rsrqBegin = rsrqBegin + 64;
                                    rsrqEnd = rsrqEnd + 64;
                                    bandWidthBegin = bandWidthBegin + 64;
                                    bandWidthEnd = bandWidthEnd + 64;
                                    tddSpecialSfBegin = tddSpecialSfBegin + 64;
                                    tddSpecialSfEnd = tddSpecialSfEnd + 64;
                                    //interFreqLstNumBegin = interFreqLstNumBegin+64;interFreqLstNumEnd = interFreqLstNumEnd+64;

                                    System.out.println(interFreqNghNumBegin + "---" + interFreqNghNumEnd);
                                    for (int r = 0; r < InterFreqLstNum; r++) {
//                                            System.out.println(interFreqNghNumBegin + "---" + interFreqNghNumEnd);
                                        interFreqNghNumBegin = interFreqLstNumBegin + 24;
                                        interFreqNghNumEnd = interFreqLstNumEnd + 24;
                                        //异频小区的领区数目
//                                            System.out.println(str4.substring(interFreqNghNumBegin, interFreqNghNumEnd));
//                                            System.out.println("pin:" + StringPin(str4.substring(interFreqNghNumBegin, interFreqNghNumEnd)));
//                                            System.out.println(Integer.parseInt(StringPin(str4.substring(interFreqNghNumBegin, interFreqNghNumEnd)), 16));
//                                        int interFreqNghNum;
//                                        if ("ffffffff".equals(str4.substring(interFreqNghNumBegin, interFreqNghNumEnd))) {
//
//                                            continue;
//                                        } else {
//                                            interFreqNghNum = Integer.parseInt(StringPin(str4.substring(interFreqNghNumBegin, interFreqNghNumEnd)), 16);
////                                                System.out.println("异频小区的邻区个数：" + interFreqNghNum);
//                                        }
                                        int interFreqNghNum;
//                                        try {
//                                        if (str4.length() < interFreqNghNumEnd) {
//                                            continue;
//                                        }
                                        try {
                                            if (!TextUtils.isEmpty(str4)) {

                                            } else {
                                                continue;
                                            }
                                            if ("ffffffff".equals(str4.substring(interFreqNghNumBegin, interFreqNghNumEnd))) {

                                                interFreqNghNum = 0;
                                            } else {
                                                interFreqNghNum = Integer.parseInt(StringPin(str4.substring(interFreqNghNumBegin, interFreqNghNumEnd)), 16);
//                                                System.out.println("异频小区的邻区个数：" + interFreqNghNum);
                                            }
                                        } catch (Exception e) {
                                            interFreqNghNum = 0;
//                                            Log.d("nzqexce2", "run: " + e.getMessage());
                                        }

                                        for (int n = 0; n < interFreqNghNum; n++) {
                                            dlEarfcnBegin = dlEarfcnBegin + 8;
                                            dlEarfcnEnd = dlEarfcnEnd + 8;
                                            pciBegin = pciBegin + 8;
                                            pciEnd = pciEnd + 8;
                                            tacBegin = tacBegin + 8;
                                            tacEnd = tacEnd + 8;
                                            plmnBegin = plmnBegin + 8;
                                            plmnEnd = plmnEnd + 8;
                                            celldBegin = celldBegin + 8;
                                            celldEnd = celldEnd + 8;
                                            priorityBegin = priorityBegin + 8;
                                            priorityEnd = priorityEnd + 8;
                                            rsrpBegin = rsrpBegin + 8;
                                            rsrpEnd = rsrpEnd + 8;
                                            rsrqBegin = rsrqBegin + 8;
                                            rsrqEnd = rsrqEnd + 8;
                                            bandWidthBegin = bandWidthBegin + 8;
                                            bandWidthEnd = bandWidthEnd + 8;
                                            tddSpecialSfBegin = tddSpecialSfBegin + 8;
                                            tddSpecialSfEnd = tddSpecialSfEnd + 8;
                                            interFreqLstNumBegin = interFreqLstNumBegin + 8;
                                            interFreqLstNumEnd = interFreqLstNumEnd + 8;
                                            interFreqNghNumBegin = interFreqNghNumBegin + 8;
                                            interFreqNghNumEnd = interFreqNghNumEnd + 8;
                                        }

									/*int number = InterFreqLstNum-r;
                                    if(number!=1){
										interFreqNghNumBegin = interFreqNghNumBegin+24; interFreqNghNumEnd = interFreqNghNumEnd+24;
									}*/
                                        dlEarfcnBegin = dlEarfcnBegin + 24;
                                        dlEarfcnEnd = dlEarfcnEnd + 24;
                                        pciBegin = pciBegin + 24;
                                        pciEnd = pciEnd + 24;
                                        tacBegin = tacBegin + 24;
                                        tacEnd =
                                                +24;
                                        plmnBegin = plmnBegin + 24;
                                        plmnEnd = plmnEnd + 24;
                                        celldBegin = celldBegin + 24;
                                        celldEnd = celldEnd + 24;
                                        priorityBegin = priorityBegin + 24;
                                        priorityEnd = priorityEnd + 24;
                                        rsrpBegin = rsrpBegin + 24;
                                        rsrpEnd = rsrpEnd + 24;
                                        rsrqBegin = rsrqBegin + 24;
                                        rsrqEnd = rsrqEnd + 24;
                                        bandWidthBegin = bandWidthBegin + 24;
                                        bandWidthEnd = bandWidthEnd + 24;
                                        tddSpecialSfBegin = tddSpecialSfBegin + 24;
                                        tddSpecialSfEnd = tddSpecialSfEnd + 24;
                                        interFreqLstNumBegin = interFreqLstNumBegin + 24;
                                        interFreqLstNumEnd = interFreqLstNumEnd + 24;
                                    }
                                    interFreqLstNumBegin = interFreqLstNumBegin + 64;
                                    interFreqLstNumEnd = interFreqLstNumEnd + 64;
                                    interFreqNghNumBegin = interFreqNghNumBegin + 64;
                                    interFreqNghNumEnd = interFreqNghNumEnd + 64;
                                }
                                str4 = "";


                                if (spBeanList.size() == 0) {
//
                                } else {
//                                    Log.d("nzqspBeanList2", "" + spBeanList);
//                                    bundle.putParcelableArrayList("List,", (ArrayList<? extends Parcelable>) spBeanList);
//                                    message.setData(bundle);
//                                    handler.sendMessage(message);
//                                    message.what = 200152;

                                    SPBEANLIST2 = spBeanList;
//                                spBeanList.sort(Comparator.comparing(SpBean::getPriority));
                                    //先根据优先级,如果优先级一样根据RSRP
                                    List<Integer> list = new ArrayList();
                                    String down1 = "";
                                    SpBean spBean1 = new SpBean();
                                    SpBean spBean2 = new SpBean();
                                    if (spBeanList.size() >= 2) {
                                        for (int i = 0; i < spBeanList.size(); i++) {
                                            list.add(spBeanList.get(i).getPriority());
                                        }
                                        Integer max = Collections.max(list);
                                        Log.d("Anzqmax", "大于2条run: " + max);
                                        list.remove(max);//最大的优先

                                        for (int i = 0; i < spBeanList.size(); i++) {
                                            if (max.equals(spBeanList.get(i).getPriority())) {
                                                down1 = spBeanList.get(i).getDown();
                                                spBean1 = spBeanList.get(i);
                                                break;
                                            }
                                        }

                                        //第二个优先
                                        String down2 = "";
                                        Integer max2 = Collections.max(list);
//                                    Log.d("Anzqmax2", "run: " + max);
                                        for (int i = 0; i < spBeanList.size(); i++) {
                                            if (max2.equals(spBeanList.get(i).getPriority())) {

                                                down2 = spBeanList.get(i).getDown();
                                                spBean2 = spBeanList.get(i);
                                            }
                                        }
                                        Log.d("down2a", "run: " + down2);
                                        if (max != max2) {

                                            if (!down1.equals(down2)) {//优先级的频点一致 比较频点是否一致
                                                message = new Message();

                                                bundle.putString("sp1MAX1value54", down1);//下行
                                                bundle.putString("sp1up54", spBean1.getUp() + "");
                                                bundle.putString("sp1pci54", spBean1.getPci() + "");
                                                bundle.putString("sp1plmn54", spBean1.getPlmn() + "");
                                                bundle.putString("sp1band54", spBean1.getBand() + "");
                                                bundle.putString("sp1tac54", spBean1.getTac() + "");

                                                bundle.putString("sp1MAX2value54", down2);
                                                bundle.putString("sp2up54", spBean2.getUp() + "");
                                                bundle.putString("sp2pci54", spBean2.getPci() + "");
                                                bundle.putString("sp2plmn54", spBean2.getPlmn() + "");
                                                bundle.putString("sp2band54", spBean2.getBand() + "");
                                                bundle.putString("sp2tac54", spBean2.getTac() + "");
                                                message.setData(bundle);
                                                handler.sendMessage(message);
                                                message.what = 200152;
                                                mListener.heandleMessage(message);
                                                Log.d("Anzqmax", "大于2条run且优先级有区别但是频点一致: " + max);
                                            } else {
                                                message = new Message();
                                                bundle.putString("sp1MAX1value54", down1);//下行
                                                bundle.putString("sp1up54", spBean1.getUp() + "");
                                                bundle.putString("sp1pci54", spBean1.getPci() + "");
                                                bundle.putString("sp1plmn54", spBean1.getPlmn() + "");
                                                bundle.putString("sp1band54", spBean1.getBand() + "");
                                                bundle.putString("sp1tac54", spBean1.getTac() + "");

                                                for (int i = 0; i < spBeanList.size(); i++) {
                                                    if (!down1.equals(spBeanList.get(i).getDown())) {
                                                        down2 = spBeanList.get(i).getDown();
                                                        spBean2 = spBeanList.get(i);
                                                        break;
                                                    }
                                                }


                                                bundle.putString("sp1MAX2value54", down2);
                                                bundle.putString("sp2up54", spBean2.getUp() + "");
                                                bundle.putString("sp2pci54", spBean2.getPci() + "");
                                                bundle.putString("sp2plmn54", spBean2.getPlmn() + "");
                                                bundle.putString("sp2band54", spBean2.getBand() + "");
                                                bundle.putString("sp2tac54", spBean2.getTac() + "");
                                                message.setData(bundle);
                                                handler.sendMessage(message);
                                                message.what = 200152;
                                                mListener.heandleMessage(message);
                                                Log.d("Anzqmax", "大于2条run且优先级有区别但是频点不一致: " + down1 + "--" + down2);
                                            }


                                        } else {//根据优先级比较一致  ,通过rsrp比较

                                            int rssp1;
                                            int rssp2;
                                            List<Integer> list1rsp = new ArrayList<>();
                                            for (int i = 0; i < spBeanList.size(); i++) {
                                                list1rsp.add(spBeanList.get(i).getRsrp());
                                            }
                                            //最大的rsrp
                                            rssp1 = Collections.max(list1rsp);
                                            for (int i = 0; i < spBeanList.size(); i++) {
                                                if (rssp1 == spBeanList.get(i).getRsrp()) {
                                                    down1 = spBeanList.get(i).getDown();
                                                    spBean1 = spBeanList.get(i);
                                                }
                                            }
                                            for (int i = 0; i < list1rsp.size(); i++) {
                                                if (list1rsp.get(i).equals(rssp1)) {
                                                    list1rsp.remove(i);
                                                }
                                            }
                                            //求第二个rsrp
                                            rssp2 = Collections.max(list1rsp);
                                            for (int i = 0; i < spBeanList.size(); i++) {
                                                if (rssp2 == spBeanList.get(i).getRsrp()) {
                                                    down2 = spBeanList.get(i).getDown();
                                                    spBean2 = spBeanList.get(i);
                                                }
                                            }
                                            if (down1.equals(down2)) {
                                                message = new Message();
                                                bundle.putString("sp1MAX1value54", down1);//下行
                                                bundle.putString("sp1up54", spBean1.getUp() + "");
                                                bundle.putString("sp1pci54", spBean1.getPci() + "");
                                                bundle.putString("sp1plmn54", spBean1.getPlmn() + "");
                                                bundle.putString("sp1band54", spBean1.getBand() + "");
                                                bundle.putString("sp1tac54", spBean1.getTac() + "");
                                                for (int i = 0; i < spBeanList.size(); i++) {
                                                    if (!down1.equals(spBeanList.get(i).getDown())) {
                                                        down2 = spBeanList.get(i).getDown();
                                                        spBean2 = spBeanList.get(i);
                                                        break;
                                                    }

                                                }
                                                bundle.putString("sp1MAX2value54", "");
                                                bundle.putString("sp2up54", spBean2.getUp() + "");
                                                bundle.putString("sp2pci54", spBean2.getPci() + "");
                                                bundle.putString("sp2plmn54", spBean2.getPlmn() + "");
                                                bundle.putString("sp2band54", spBean2.getBand() + "");
                                                bundle.putString("sp2tac54", spBean2.getTac() + "");
                                                message.setData(bundle);
                                                handler.sendMessage(message);
                                                message.what = 200152;
                                                mListener.heandleMessage(message);
                                                Log.d("Anzqmax", "大于2条run且优先级没区别用RSRP比较且下频一致取第二个不应的: " + down1 + "--" + down2);
                                            } else {
                                                message = new Message();
                                                bundle.putString("sp1MAX1value54", down1);//下行
                                                bundle.putString("sp1up54", spBean1.getUp() + "");
                                                bundle.putString("sp1pci54", spBean1.getPci() + "");
                                                bundle.putString("sp1plmn54", spBean1.getPlmn() + "");
                                                bundle.putString("sp1band54", spBean1.getBand() + "");
                                                bundle.putString("sp1tac54", spBean1.getTac() + "");

                                                bundle.putString("sp1MAX2value54", down2);
                                                bundle.putString("sp2up54", spBean2.getUp() + "");
                                                bundle.putString("sp2pci54", spBean2.getPci() + "");
                                                bundle.putString("sp2plmn54", spBean2.getPlmn() + "");
                                                bundle.putString("sp2band54", spBean2.getBand() + "");
                                                bundle.putString("sp2tac54", spBean2.getTac() + "");
                                                message.setData(bundle);
                                                handler.sendMessage(message);
                                                message.what = 200152;
                                                mListener.heandleMessage(message);
                                                Log.d("Anzqmax", "大于2条run且优先级没区别用RSRP比较且下频不一致: " + down1 + "--" + down2);
                                            }


                                            ToastUtils.showToast("当前条数为多条");
//                                        Log.d("Anzqmax", "大于2条run且优先级没区别用RSRP比较: "+down1+"--"+down2 );
                                        }

                                    } else {

                                        if (spBeanList.size() > 0 && spBeanList.size() == 1) {
                                            down1 = spBeanList.get(0).getDown();
//                                            spBean2 = spBeanList.get(0);
                                            message = new Message();
                                            bundle.putString("sp1MAX1value54", down1);
                                            bundle.putString("sp1up54", spBeanList.get(0).getUp() + "");
                                            bundle.putString("sp1pci54", spBeanList.get(0).getPci() + "");
                                            bundle.putString("sp1plmn54", spBeanList.get(0).getPlmn() + "");
                                            bundle.putString("sp1band54", spBeanList.get(0).getBand() + "");
                                            bundle.putString("sp1tac54", spBeanList.get(0).getTac() + "");

                                            bundle.putString("sp1MAX2value54", "");
                                            bundle.putString("sp2up54", spBean2.getUp() + "");
                                            bundle.putString("sp2pci54", spBean2.getPci() + "");
                                            bundle.putString("sp2plmn54", spBean2.getPlmn() + "");
                                            bundle.putString("sp2band54", spBean2.getBand() + "");
                                            bundle.putString("sp2tac54", spBean2.getTac() + "");
                                            message.setData(bundle);
                                            handler.sendMessage(message);
                                            message.what = 200152;
                                            mListener.heandleMessage(message);
                                            ToastUtils.showToast("当前条数为1");
                                            Log.d("Anzqmax", "当前条数为1: ");
                                        } else {
                                            message = new Message();
                                            bundle.putString("sp1MAX1value54", "");
                                            bundle.putString("sp1MAX2value54", "");
                                            message.setData(bundle);
                                            handler.sendMessage(message);
                                            message.what = 200152;
                                            mListener.heandleMessage(message);
                                            ToastUtils.showToast("当前条数为0");
                                            Log.d("Anzqmax", "当前条数为0: ");
                                        }
                                    }
                                }

                            } else if (spBeanList.size() > 0 && spBeanList.size() == 1) {//如果只有一条
                                if (!spBeanList.get(0).getDown().equals("0")) {
                                    String down1 = spBeanList.get(0).getDown();
                                    SpBean spBean2 = spBeanList.get(0);
                                    message = new Message();
                                    bundle.putString("sp1MAX1value", down1);
                                    bundle.putString("sp1up", spBeanList.get(0).getUp() + "");
                                    bundle.putString("sp1pci", spBeanList.get(0).getPci() + "");
                                    bundle.putString("sp1plmn", spBeanList.get(0).getPlmn() + "");
                                    bundle.putString("sp1band", spBeanList.get(0).getBand() + "");
                                    bundle.putString("sp1tac", spBeanList.get(0).getTac() + "");

                                    bundle.putString("sp1MAX2value", "");
                                    message.setData(bundle);
                                    handler.sendMessage(message);
                                    message.what = 200152;
                                    mListener.heandleMessage(message);
                                    ToastUtils.showToast("当前条数为1");
                                    Log.d("Anzqmax", "当前条数为1: ");
                                }
                            } else { //当没有大于1 或者大于2
                                message = new Message();
                                bundle.putString("sp1MAX1value", "");
                                bundle.putString("sp1MAX2value", "");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 200152;

                                mListener.heandleMessage(message);
                                ToastUtils.showToast("当前条数为0");
                                Log.d("Anzqmax", "当前条数为0: ");
                            }
                        }

                    }

                    //扫频频点设置响应
//                            if ("0af0".equals(str.substring(8, 12))) {
//                                //采集的小区数目
//                                int row;
//                                if ("ffff".equals(str.substring(24, 28))) {
//                                    row = 0;
//                                } else {
//                                    row = Integer.parseInt(StringPin(str.substring(24, 28)), 16);
//                                    System.out.println("小区个数：" + row);
//                                }
//                                List<SpBean> spBeanList = new ArrayList<>();
//                                int dlEarfcnBegin = 32, dlEarfcnEnd = 40;
//                                int pciBegin = 40, pciEnd = 44;
//                                int tacBegin = 44, tacEnd = 48;
//                                int plmnBegin = 48, plmnEnd = 52;
//                                int celldBegin = 56, celldEnd = 64;
//                                int priorityBegin = 64, priorityEnd = 72;
//                                int rsrpBegin = 72, rsrpEnd = 74;
//                                int rsrqBegin = 74, rsrqEnd = 76;
//                                int bandWidthBegin = 76, bandWidthEnd = 78;
//                                int tddSpecialSfBegin = 78, tddSpecialSfEnd = 80;
//                                int interFreqLstNumBegin = 88, interFreqLstNumEnd = 96;
//                                int interFreqNghNumBegin = 112, interFreqNghNumEnd = 120;
//
//                                for (int i = 0; i < row; i++) {
//                                    SpBean spBean = new SpBean();
//                                    //下行频点
//                                    System.out.println(str.substring(dlEarfcnBegin, dlEarfcnEnd));
//
//                                    if ("ffffffff".equals(str.substring(dlEarfcnBegin, dlEarfcnEnd))) {
//                                        System.out.println("null");
//                                        spBean.setDown("0");
//                                    } else {
//                                        System.out.println("下行频点：------" + Integer.parseInt(StringPin(str.substring(dlEarfcnBegin, dlEarfcnEnd)), 16));
//                                        spBean.setDown(Integer.parseInt(StringPin(str.substring(dlEarfcnBegin, dlEarfcnEnd)), 16) + "");
//                                    }
//                                    if (ID2TF.equals("TDD")) {
//                                        spBean.setUp(255 + "");
//                                    } else {
//                                        if (!TextUtils.isEmpty(spBean.getDown())) {
//                                            int i2 = Integer.parseInt(spBean.getDown());
//                                            int i1 = i2 + 18000;
//                                            spBean.setUp(i1 + "");
//
//                                        }
//                                    }
//                                    Log.d("spBeanup", "run: " + spBean.getUp());
//                                    //PCI
//                                    System.out.println("PCI：------" + Integer.parseInt(StringPin(str.substring(pciBegin, pciEnd)), 16));
//                                    spBean.setPci(Integer.parseInt(StringPin(str.substring(pciBegin, pciEnd)), 16));
//                                    System.out.println(dlEarfcnBegin + "+" + dlEarfcnEnd);
//
//                                    //TAC
//                                    System.out.println("TAC：------" + Integer.parseInt(StringPin(str.substring(tacBegin, tacEnd)), 16));
//                                    spBean.setTac(Integer.parseInt(StringPin(str.substring(tacBegin, tacEnd)), 16));
//                                    //PLMN
//                                    System.out.println(Integer.parseInt(StringPin(str.substring(plmnBegin, plmnEnd)), 16) + "---PLMN：");
//
//                                    spBean.setPlmn(Integer.parseInt(StringPin(str.substring(plmnBegin, plmnEnd)), 16) + "");
//                                    //CellId
//                                    System.out.println("ffffffff".equals(str.substring(celldBegin, celldEnd)) ? "null" : Integer.parseInt(StringPin(str.substring(celldBegin, celldEnd)), 16) + "------CellId：");
//                                    //Priority 本小区频点优先级
//                                    System.out.println("优先级--" + str.substring(64, 72));
//                                    System.out.println("ffffffff".equals(str.substring(priorityBegin, priorityEnd)) ? "null" : Integer.parseInt(StringPin(str.substring(priorityBegin, priorityEnd)), 16) + "------Priority 本小区频点优先级：");
//                                    Log.d("nzqffffffff", "ffffffff".equals(str.substring(priorityBegin, priorityEnd)) ? "0" : Integer.parseInt(StringPin(str.substring(priorityBegin, priorityEnd)), 16) + "");
//                                    if ("ffffffff".equals(str.substring(priorityBegin, priorityEnd))) {
//                                        Log.d("1nzqffffffff", "run:1 ");
//                                        spBean.setPriority(0);
//                                    } else {
//                                        spBean.setPriority(Integer.parseInt(StringPin(str.substring(priorityBegin, priorityEnd)), 16));
//                                        Log.d("2nzqffffffff", "run:1 ");
//                                    }
////
////
////                                    spBean.setPriority(Integer.parseInt(StringPin(str.substring(priorityBegin, priorityEnd)), 16)+"");
//                                    //RSRP
//                                    System.out.println("RSRP：------" + Integer.parseInt(StringPin(str.substring(rsrpBegin, rsrpEnd)), 16));
//                                    //RSRQ
//                                    System.out.println("RSRQ：------" + Integer.parseInt(StringPin(str.substring(rsrqBegin, rsrqEnd)), 16));
//                                    spBean.setRsrp(Integer.parseInt(StringPin(str.substring(rsrpBegin, rsrpEnd)), 16));
//                                    spBeanList.add(spBean);//测试代码add
//                                    //Bandwidth小区工作带宽
//                                    System.out.println("Bandwidth：------" + Integer.parseInt(StringPin(str.substring(bandWidthBegin, bandWidthEnd)), 16));
//                                    spBean.setBand(Integer.parseInt(StringPin(str.substring(bandWidthBegin, bandWidthEnd)), 16) + "");
//                                    //TddSpecialSf Patterns TDD特殊子帧配置
//                                    System.out.println("TDD特殊子帧配置：------" + Integer.parseInt(StringPin(str.substring(tddSpecialSfBegin, tddSpecialSfEnd)), 16));
//                                    //异频小区个数
//                                    int InterFreqLstNum;
//                                    if ("ffffffff".equals(str.substring(interFreqLstNumBegin, interFreqLstNumEnd))) {
//                                        InterFreqLstNum = 0;
//                                    } else {
//                                        InterFreqLstNum = Integer.parseInt(StringPin(str.substring(interFreqLstNumBegin, interFreqLstNumEnd)), 16);
//                                    }
//                                    System.out.println(interFreqLstNumBegin + "---" + interFreqLstNumEnd);
//                                    System.out.println("异频小区个数：------" + InterFreqLstNum);
//
////                                    dlEarfcnBegin = dlEarfcnBegin + 64;
////                                    dlEarfcnEnd = dlEarfcnEnd + 64;
////                                    pciBegin = pciBegin + 64;
////                                    pciEnd = pciEnd + 64;
////                                    tacBegin = tacBegin + 64;
////                                    tacEnd = tacEnd + 64;
////                                    plmnBegin = plmnBegin + 64;
////                                    plmnEnd = plmnEnd + 64;
////                                    celldBegin = celldBegin + 64;
////                                    celldEnd = celldEnd + 64;
////                                    priorityBegin = priorityBegin + 64;
////                                    priorityEnd = priorityEnd + 64;
////                                    rsrpBegin = rsrpBegin + 64;
////                                    rsrpEnd = rsrpEnd + 64;
////                                    rsrqBegin = rsrqBegin + 64;
////                                    rsrqEnd = rsrqEnd + 64;
////                                    bandWidthBegin = bandWidthBegin + 64;
////                                    bandWidthEnd = bandWidthEnd + 64;
////                                    tddSpecialSfBegin = tddSpecialSfBegin + 64;
////                                    tddSpecialSfEnd = tddSpecialSfEnd + 64;
////                                    interFreqLstNumBegin = interFreqLstNumBegin + 64;
////                                    interFreqLstNumEnd = interFreqLstNumEnd + 64;
////
////                                    System.out.println(interFreqNghNumBegin + "---" + interFreqNghNumEnd);
////                                    for (int r = 0; r < InterFreqLstNum; r++) {
////                                        System.out.println(interFreqNghNumBegin + "---" + interFreqNghNumEnd);
////                                        //异频小区的领区数目
////                                        System.out.println(str.substring(interFreqNghNumBegin, interFreqNghNumEnd));
////                                        System.out.println("pin:" + StringPin(str.substring(interFreqNghNumBegin, interFreqNghNumEnd)));
////                                        System.out.println(Integer.parseInt(StringPin(str.substring(interFreqNghNumBegin, interFreqNghNumEnd)), 16));
////                                        int interFreqNghNum = Integer.parseInt(StringPin(str.substring(interFreqNghNumBegin, interFreqNghNumEnd)), 16);
////                                        System.out.println("异频小区的邻区个数：" + interFreqNghNum);
////
////                                        for (int n = 0; n < interFreqNghNum; n++) {
////                                            dlEarfcnBegin = dlEarfcnBegin + 8;
////                                            dlEarfcnEnd = dlEarfcnEnd + 8;
////                                            pciBegin = pciBegin + 8;
////                                            pciEnd = pciEnd + 8;
////                                            tacBegin = tacBegin + 8;
////                                            tacEnd = tacEnd + 8;
////                                            plmnBegin = plmnBegin + 8;
////                                            plmnEnd = plmnEnd + 8;
////                                            celldBegin = celldBegin + 8;
////                                            celldEnd = celldEnd + 8;
////                                            priorityBegin = priorityBegin + 8;
////                                            priorityEnd = priorityEnd + 8;
////                                            rsrpBegin = rsrpBegin + 8;
////                                            rsrpEnd = rsrpEnd + 8;
////                                            rsrqBegin = rsrqBegin + 8;
////                                            rsrqEnd = rsrqEnd + 8;
////                                            bandWidthBegin = bandWidthBegin + 8;
////                                            bandWidthEnd = bandWidthEnd + 8;
////                                            tddSpecialSfBegin = tddSpecialSfBegin + 8;
////                                            tddSpecialSfEnd = tddSpecialSfEnd + 8;
////                                            interFreqLstNumBegin = interFreqLstNumBegin + 8;
////                                            interFreqLstNumEnd = interFreqLstNumEnd + 8;
////                                            interFreqNghNumBegin = interFreqNghNumBegin + 8;
////                                            interFreqNghNumEnd = interFreqNghNumEnd + 8;
////                                        }
////
////                                        int number = InterFreqLstNum - r;
////                                        if (number != 1) {
////                                            interFreqNghNumBegin = interFreqNghNumBegin + 24;
////                                            interFreqNghNumEnd = interFreqNghNumEnd + 24;
////                                        }
////                                        dlEarfcnBegin = dlEarfcnBegin + 24;
////                                        dlEarfcnEnd = dlEarfcnEnd + 24;
////                                        pciBegin = pciBegin + 24;
////                                        pciEnd = pciEnd + 24;
////                                        tacBegin = tacBegin + 24;
////                                        tacEnd = tacEnd + 24;
////                                        plmnBegin = plmnBegin + 24;
////                                        plmnEnd = plmnEnd + 24;
////                                        celldBegin = celldBegin + 24;
////                                        celldEnd = celldEnd + 24;
////                                        priorityBegin = priorityBegin + 24;
////                                        priorityEnd = priorityEnd + 24;
////                                        rsrpBegin = rsrpBegin + 24;
////                                        rsrpEnd = rsrpEnd + 24;
////                                        rsrqBegin = rsrqBegin + 24;
////                                        rsrqEnd = rsrqEnd + 24;
////                                        bandWidthBegin = bandWidthBegin + 24;
////                                        bandWidthEnd = bandWidthEnd + 24;
////                                        tddSpecialSfBegin = tddSpecialSfBegin + 24;
////                                        tddSpecialSfEnd = tddSpecialSfEnd + 24;
////                                        interFreqLstNumBegin = interFreqLstNumBegin + 24;
////                                        interFreqLstNumEnd = interFreqLstNumEnd + 24;
////
////                                    }
//                                    interFreqNghNumBegin = interFreqNghNumBegin + 64;
//                                    interFreqNghNumEnd = interFreqNghNumEnd + 64;
//                                }
//                                //测试代码37900
////                                SpBean spBean = new SpBean();
////                                spBean.setRsrp(0);
////                                spBean.setPriority(20);
////                                spBean.setDown("37900");
////                                spBean.setPci(1234);
////                                spBean.setTac(987654);
////                                spBean.setPlmn("46000");
////                                spBean.setBand(38+"");
////                                spBeanList.add(spBean);
////                                spBeanList.add(spBean);
////
////                                SpBean spBean22 = new SpBean();
////                                spBean22.setRsrp(0);
////                                spBean22.setPriority(0);
////                                spBean22.setDown("37200");
////                                spBeanList.add(spBean22);
//                                if (spBeanList.size() == 0) {
//
//                                } else {
//                                    Log.d("nzqspBeanList", "" + spBeanList);
////                                spBeanList.sort(Comparator.comparing(SpBean::getPriority));
//                                    //先根据优先级,如果优先级一样根据RSRP
//                                    List<Integer> list = new ArrayList();
//                                    String down1 = "";
//                                    SpBean spBean1 = new SpBean();
//                                    SpBean spBean2 = new SpBean();
//                                    if (spBeanList.size() >= 2) {
//                                        for (int i = 0; i < spBeanList.size(); i++) {
//                                            list.add(spBeanList.get(i).getPriority());
//                                        }
//                                        Integer max = Collections.max(list);
//                                        Log.d("Anzqmax", "大于2条run: " + max);
//                                        list.remove(max);//最大的优先
//
//                                        for (int i = 0; i < spBeanList.size(); i++) {
//                                            if (max.equals(spBeanList.get(i).getPriority())) {
//                                                down1 = spBeanList.get(i).getDown();
//                                                spBean1 = spBeanList.get(i);
//                                                break;
//                                            }
//                                        }
//
//                                        //第二个优先
//                                        String down2 = "";
//                                        Integer max2 = Collections.max(list);
////                                    Log.d("Anzqmax2", "run: " + max);
//                                        for (int i = 0; i < spBeanList.size(); i++) {
//                                            if (max2.equals(spBeanList.get(i).getPriority())) {
//
//                                                down2 = spBeanList.get(i).getDown();
//                                                spBean2 = spBeanList.get(i);
//                                            }
//                                        }
//                                        Log.d("down2a", "run: " + down2);
//                                        if (max != max2) {
//
//                                            if (!down1.equals(down2)) {//优先级的频点一致 比较频点是否一致
//                                                message = new Message();
//                                                bundle.putString("sp1MAX1value54", down1);//下行
//                                                bundle.putString("sp1up54", spBean1.getUp() + "");
//                                                bundle.putString("sp1pci54", spBean1.getPci() + "");
//                                                bundle.putString("sp1plmn54", spBean1.getPlmn() + "");
//                                                bundle.putString("sp1band54", spBean1.getBand() + "");
//                                                bundle.putString("sp1tac54", spBean1.getTac() + "");
//
//                                                bundle.putString("sp1MAX2value54", down2);
//                                                bundle.putString("sp2up54", spBean2.getUp() + "");
//                                                bundle.putString("sp2pci54", spBean2.getPci() + "");
//                                                bundle.putString("sp2plmn54", spBean2.getPlmn() + "");
//                                                bundle.putString("sp2band54", spBean2.getBand() + "");
//                                                bundle.putString("sp2tac54", spBean2.getTac() + "");
//                                                message.setData(bundle);
//                                                handler.sendMessage(message);
//                                                message.what = 200152;
//                                                Log.d("Anzqmax", "大于2条run且优先级有区别但是频点一致: " + max);
//                                            } else {
//                                                message = new Message();
//                                                bundle.putString("sp1MAX1value54", down1);//下行
//                                                bundle.putString("sp1up54", spBean1.getUp() + "");
//                                                bundle.putString("sp1pci54", spBean1.getPci() + "");
//                                                bundle.putString("sp1plmn54", spBean1.getPlmn() + "");
//                                                bundle.putString("sp1band54", spBean1.getBand() + "");
//                                                bundle.putString("sp1tac54", spBean1.getTac() + "");
//
//                                                for (int i = 0; i < spBeanList.size(); i++) {
//                                                    if (!down1.equals(spBeanList.get(i).getDown())) {
//                                                        down2 = spBeanList.get(i).getDown();
//                                                        spBean2 = spBeanList.get(i);
//                                                        break;
//                                                    }
//                                                }
//
//
//                                                bundle.putString("sp1MAX2value54", down2);
//                                                bundle.putString("sp2up54", spBean2.getUp() + "");
//                                                bundle.putString("sp2pci54", spBean2.getPci() + "");
//                                                bundle.putString("sp2plmn54", spBean2.getPlmn() + "");
//                                                bundle.putString("sp2band54", spBean2.getBand() + "");
//                                                bundle.putString("sp2tac54", spBean2.getTac() + "");
//                                                message.setData(bundle);
//                                                handler.sendMessage(message);
//                                                message.what = 200152;
//                                                Log.d("Anzqmax", "大于2条run且优先级有区别但是频点不一致: " + down1 + "--" + down2);
//                                            }
//
//
//                                        } else {//根据优先级比较一致  ,通过rsrp比较
//
//                                            int rssp1;
//                                            int rssp2;
//                                            List<Integer> list1rsp = new ArrayList<>();
//                                            for (int i = 0; i < spBeanList.size(); i++) {
//                                                list1rsp.add(spBeanList.get(i).getRsrp());
//                                            }
//                                            //最大的rsrp
//                                            rssp1 = Collections.max(list1rsp);
//                                            for (int i = 0; i < spBeanList.size(); i++) {
//                                                if (rssp1 == spBeanList.get(i).getRsrp()) {
//                                                    down1 = spBeanList.get(i).getDown();
//                                                    spBean1 = spBeanList.get(i);
//                                                }
//                                            }
//                                            for (int i = 0; i < list1rsp.size(); i++) {
//                                                if (list1rsp.get(i).equals(rssp1)) {
//                                                    list1rsp.remove(i);
//                                                }
//                                            }
//                                            //求第二个rsrp
//                                            rssp2 = Collections.max(list1rsp);
//                                            for (int i = 0; i < spBeanList.size(); i++) {
//                                                if (rssp2 == spBeanList.get(i).getRsrp()) {
//                                                    down2 = spBeanList.get(i).getDown();
//                                                    spBean2 = spBeanList.get(i);
//                                                }
//                                            }
//                                            if (down1.equals(down2)) {
//                                                message = new Message();
//                                                bundle.putString("sp1MAX1value54", down1);//下行
//                                                bundle.putString("sp1up54", spBean1.getUp() + "");
//                                                bundle.putString("sp1pci54", spBean1.getPci() + "");
//                                                bundle.putString("sp1plmn54", spBean1.getPlmn() + "");
//                                                bundle.putString("sp1band54", spBean1.getBand() + "");
//                                                bundle.putString("sp1tac54", spBean1.getTac() + "");
//                                                for (int i = 0; i < spBeanList.size(); i++) {
//                                                    if (!down1.equals(spBeanList.get(i).getDown())) {
//                                                        down2 = spBeanList.get(i).getDown();
//                                                        spBean2 = spBeanList.get(i);
//                                                        break;
//                                                    }
//
//                                                }
//                                                bundle.putString("sp1MAX2value54", "");
//                                                bundle.putString("sp2up54", spBean2.getUp() + "");
//                                                bundle.putString("sp2pci54", spBean2.getPci() + "");
//                                                bundle.putString("sp2plmn54", spBean2.getPlmn() + "");
//                                                bundle.putString("sp2band54", spBean2.getBand() + "");
//                                                bundle.putString("sp2tac54", spBean2.getTac() + "");
//                                                message.setData(bundle);
//                                                handler.sendMessage(message);
//                                                message.what = 200152;
//                                                Log.d("Anzqmax", "大于2条run且优先级没区别用RSRP比较且下频一致取第二个不应的: " + down1 + "--" + down2);
//                                            } else {
//                                                message = new Message();
//                                                bundle.putString("sp1MAX1value54", down1);//下行
//                                                bundle.putString("sp1up54", spBean1.getUp() + "");
//                                                bundle.putString("sp1pci54", spBean1.getPci() + "");
//                                                bundle.putString("sp1plmn54", spBean1.getPlmn() + "");
//                                                bundle.putString("sp1band54", spBean1.getBand() + "");
//                                                bundle.putString("sp1tac54", spBean1.getTac() + "");
//
//                                                bundle.putString("sp1MAX2value54", down2);
//                                                bundle.putString("sp2up54", spBean2.getUp() + "");
//                                                bundle.putString("sp2pci54", spBean2.getPci() + "");
//                                                bundle.putString("sp2plmn54", spBean2.getPlmn() + "");
//                                                bundle.putString("sp2band54", spBean2.getBand() + "");
//                                                bundle.putString("sp2tac54", spBean2.getTac() + "");
//                                                message.setData(bundle);
//                                                handler.sendMessage(message);
//                                                message.what = 200152;
//                                                Log.d("Anzqmax", "大于2条run且优先级没区别用RSRP比较且下频不一致: " + down1 + "--" + down2);
//                                            }
//
//
//                                            ToastUtils.showToast("当前条数为多条");
////                                        Log.d("Anzqmax", "大于2条run且优先级没区别用RSRP比较: "+down1+"--"+down2 );
//                                        }
//
//                                    } else {
//
//                                        if (spBeanList.size() > 0 && spBeanList.size() == 1) {
//                                            down1 = spBeanList.get(0).getDown();
////                                            spBean2 = spBeanList.get(0);
//                                            message = new Message();
//                                            bundle.putString("sp1MAX1value54", down1);
//                                            bundle.putString("sp1up54", spBeanList.get(0).getUp() + "");
//                                            bundle.putString("sp1pci54", spBeanList.get(0).getPci() + "");
//                                            bundle.putString("sp1plmn54", spBeanList.get(0).getPlmn() + "");
//                                            bundle.putString("sp1band54", spBeanList.get(0).getBand() + "");
//                                            bundle.putString("sp1tac54", spBeanList.get(0).getTac() + "");
//
//                                            bundle.putString("sp1MAX2value54", "");
//                                            bundle.putString("sp2up54", spBean2.getUp() + "");
//                                            bundle.putString("sp2pci54", spBean2.getPci() + "");
//                                            bundle.putString("sp2plmn54", spBean2.getPlmn() + "");
//                                            bundle.putString("sp2band54", spBean2.getBand() + "");
//                                            bundle.putString("sp2tac54", spBean2.getTac() + "");
//                                            message.setData(bundle);
//                                            handler.sendMessage(message);
//                                            message.what = 200152;
//                                            ToastUtils.showToast("当前条数为1");
//                                            Log.d("Anzqmax", "当前条数为1: ");
//                                        } else {
//                                            message = new Message();
//                                            bundle.putString("sp1MAX1value54", "");
//                                            bundle.putString("sp1MAX2value54", "");
//                                            message.setData(bundle);
//                                            handler.sendMessage(message);
//                                            message.what = 200152;
//                                            ToastUtils.showToast("当前条数为0");
//                                            Log.d("Anzqmax", "当前条数为0: ");
//                                        }
//                                    }
//                                }

//                                } else if (spBeanList.size() > 0 && spBeanList.size() == 1) {//如果只有一条
//                                    if (!spBeanList.get(0).getDown().equals("0")) {
//                                        down1 = spBeanList.get(0).getDown();
//                                        spBean2 = spBeanList.get(0);
//                                        message = new Message();
//                                        bundle.putString("sp1MAX1value", down1);
//                                        bundle.putString("sp1up", spBeanList.get(0).getUp() + "");
//                                        bundle.putString("sp1pci", spBeanList.get(0).getPci() + "");
//                                        bundle.putString("sp1plmn", spBeanList.get(0).getPlmn() + "");
//                                        bundle.putString("sp1band", spBeanList.get(0).getBand() + "");
//                                        bundle.putString("sp1tac", spBeanList.get(0).getTac() + "");
//
//                                        bundle.putString("sp1MAX2value", "");
//                                        message.setData(bundle);
//                                        handler.sendMessage(message);
//                                        message.what = 100152;
//                                        ToastUtils.showToast("当前条数为1");
//                                        Log.d("Anzqmax", "当前条数为1: ");
//                                    }
//                                } else { //当没有大于1 或者大于2
//                                    message = new Message();
//                                    bundle.putString("sp1MAX1value", "");
//                                    bundle.putString("sp1MAX2value", "");
//                                    message.setData(bundle);
//                                    handler.sendMessage(message);
//                                    message.what = 100152;
//                                    ToastUtils.showToast("当前条数为0");
//                                    Log.d("Anzqmax", "当前条数为0: ");
//                                }
                }

//                }

//                        if ("192.168.2.54".equals(dp.getAddress().getHostAddress())) {
////                            mPressedTime2 = System.currentTimeMillis();
////                            System.out.println("ABCD");
////                            System.out.println("收到" + dp.getAddress().getHostAddress() + "发送数据：" + str);
////
////                            //时间
////                            Date d = new Date();
////                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////                            System.out.println("时间：" + sdf.format(d));
////                            //设置模式：FDD TDD
////                            if ("00ff".equals(str.substring(16, 20))) {
////                                //设置模式FDD
////                                System.err.println("FDD");
////                                System.err.println("FDD");
////                                message = new Message();
////                                bundle.putString("tf2", "FDD");
////                                message.setData(bundle);
////                                handler.sendMessage(message);
////                                message.what = 200110;
////                            }
////                            if ("ff00".equals(str.substring(16, 20))) {
////                                //设置模式TDD
////                                System.out.println("TDD");
////                                message = new Message();
////                                bundle.putString("tf2", "TDD");
////                                message.setData(bundle);
////                                handler.sendMessage(message);
////                                message.what = 200110;
////                            }
////
////                            if ("08f0".equals(str.substring(8, 12))) {
////
////                                //目标距离（16进制字符串转换成十进制）
////                                Integer.parseInt(str.substring(24, 26), 16);
////                                System.out.println("距离：" + Integer.parseInt(str.substring(24, 26), 16));
////                                //IMSI号
////                                StringTOIMEI(str.substring(26, 56));
////                                System.out.println("IMSI号：" + hexStringToString(str.substring(26, 56)));
////
////                            }
////                            if ("10f0".equals(str.substring(8, 12))) {
////                                message = new Message();
////                                bundle.putString("zt2", "2");
////                                message.setData(bundle);
////                                handler.sendMessage(message);
////                                message.what = 200120;
////                                //心跳解析
////                                //查看小区是否建立成功（0：小区 IDLE态；1：扫频/同步进行中；2：小区激活中；3：小区激活态；4：小区去激活中）
////                                message = new Message();
////                                bundle.putString("zt2", "0");
////                                message.setData(bundle);
////                                handler.sendMessage(message);
////                                message.what = 200120;
////                                if ("0000".equals(str.substring(24, 28))) {
////                                    System.out.println("0：小区 IDLE态");
////                                    message = new Message();
////                                    bundle.putString("zt2", "2");
////                                    message.setData(bundle);
////                                    handler.sendMessage(message);
////                                    message.what = 200120;
////                                } else if ("0100".equals(str.substring(24, 28))) {
////                                    System.out.println("1：扫频/同步进行中");
////                                    message = new Message();
////                                    bundle.putString("zt2", "3");
////                                    message.setData(bundle);
////                                    handler.sendMessage(message);
////                                    message.what = 200120;
////                                } else if ("0200".equals(str.substring(24, 28))) {
////                                    System.out.println("2：小区激活中");
////                                    message = new Message();
////                                    bundle.putString("zt2", "4");
////                                    message.setData(bundle);
////                                    handler.sendMessage(message);
////                                    message.what = 200120;
////                                } else if ("0300".equals(str.substring(24, 28))) {
////                                    System.out.println("3：小区激活态");
////                                    message = new Message();
////                                    bundle.putString("zt2", "5");
////                                    message.setData(bundle);
////                                    handler.sendMessage(message);
////                                    message.what = 200120;
////                                    //Band号
////                                    Integer.parseInt(StringPin(str.substring(28, 32)), 16);
////                                    System.out.println("Band号：" + Integer.parseInt(StringPin(str.substring(28, 32)), 16));
////                                    //上行频点
////                                    Integer.parseInt(StringPin(str.substring(32, 40)), 16);
////                                    System.out.println("上行频点：" + Integer.parseInt(StringPin(str.substring(32, 40)), 16));
////                                    //下行频点
////                                    Integer.parseInt(StringPin(str.substring(40, 48)), 16);
////                                    System.out.println("下行频点：" + Integer.parseInt(StringPin(str.substring(40, 48)), 16));
////                                    //移动联通电信
////                                    if ("3436303030".equals(str.substring(48, 58))) {
////                                        //设置中国移动
////                                    }
////                                    if ("3436303031".equals(str.substring(48, 58))) {
////                                        //设置中国联通
////                                    }
////                                    if ("3436303033".equals(str.substring(48, 58)) || "3436303131".equals(str.substring(48, 58))) {
////                                        //设置中国电信
////                                    }
////
////                                    //PCI
////                                    Integer.parseInt(StringPin(str.substring(64, 68)), 16);
////                                    System.out.println("PCI:" + Integer.parseInt(StringPin(str.substring(64, 68)), 16));
////                                    //TAC
////                                    Integer.parseInt(StringPin(str.substring(68, 72)), 16);
////                                    System.out.println("TAC:" + Integer.parseInt(StringPin(str.substring(68, 72)), 16));
////
////                                } else if ("0400".equals(str.substring(24, 28))) {
////                                    System.out.println("4：小区去激活中");
////                                }
////
////                            }
////
////                        } else {
//////                            message = new Message();
//////                            bundle.putString("zt2", "0");
//////                            message.setData(bundle);
//////                            handler.sendMessage(message);
//////                            message.what = 200120;
////                        }
                /*System.out.println("截取字符串"+str.substring(8, 12));
                if(str.substring(8, 12).equals("08f0")){

					//目标距离（16进制字符串转换成十进制）
					Integer.parseInt(str.substring(24, 26),16);
					System.out.println("距离："+Integer.parseInt(str.substring(24, 26),16));
					//IMSI号
					StringTOIMEI(str.substring(26, 56));
					System.out.println("IMSI号："+hexStringToString(str.substring(26, 56)));

				}*//*if("10f0".equals(str.substring(8, 12))){
                    //心跳解析
					//查看小区是否建立成功（0：小区 IDLE态；1：扫频/同步进行中；2：小区激活中；3：小区激活态；4：小区去激活中）
					if("0000".equals(str.substring(24, 28))){
						System.out.println("0：小区 IDLE态");
					}if("0100".equals(str.substring(24, 28))){
						System.out.println("1：扫频/同步进行中");
					}if("0200".equals(str.substring(24, 28))){
						System.out.println("2：小区激活中");
					}if("0300".equals(str.substring(24, 28))){
						System.out.println("3：小区激活态");
					}if("0400".equals(str.substring(24, 28))){
						System.out.println("4：小区去激活中");
					}
					//设置模式：FDD TDD
					if("00ff".equals(str.substring(16, 20))){
						//设置模式FDD
						System.err.println("FDD");
					}if("ff00".equals(str.substring(16, 20))){
						//设置模式TDD
						System.out.println("TDD");
					}
					//时间
					Date d = new Date();
			        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			        System.out.println("时间："+sdf.format(d));
					//Band号
					Integer.parseInt(StringPin(str.substring(28,32)),16);
					System.out.println("Band号："+Integer.parseInt(StringPin(str.substring(28,32)),16));
					//上行频点
					Integer.parseInt(StringPin(str.substring(32,40)),16);
					System.out.println("上行频点："+Integer.parseInt(StringPin(str.substring(32,40)),16));
					//下行频点
					Integer.parseInt(StringPin(str.substring(40,48)),16);
					System.out.println("下行频点："+Integer.parseInt(StringPin(str.substring(40,48)),16));
					//移动联通电信
					if("3436303030".equals(str.substring(48, 58))){
						//设置中国移动
					}if("3436303031".equals(str.substring(48, 58))){
						//设置中国联通
					}if("3436303033".equals(str.substring(48, 58))||"3436303131".equals(str.substring(48, 58))){
						//设置中国电信
					}

					//PCI
					Integer.parseInt(StringPin(str.substring(64,68)),16);
					System.out.println("PCI:"+Integer.parseInt(StringPin(str.substring(64,68)),16));
					//TAC
					Integer.parseInt(StringPin(str.substring(68,72)),16);
					System.out.println("TAC:"+Integer.parseInt(StringPin(str.substring(68,72)),16));

				}*/
//                    }
//                } catch (
//                        Exception e) {
//                    e.printStackTrace();
//                    String s = e.toString();
//                    Log.d("TAG120111", "run: e.printStackTrace()" + "执行了" + e.getMessage() + "\n" + e.getLocalizedMessage() + "\n" + s);
////                    //发送设备连接异常
//                    bundle.putString("runThread", "true");
//                    message = new Message();
//                    message.setData(bundle);
//                    handler.sendMessage(message);
//                    message.what = 120;
//////
////                    //设备1状态 未启动
////                    message = new Message();
////                    bundle.putString("zt1", "0");
////                    message.setData(bundle);
////                    handler.sendMessage(message);
////                    message.what = 100120;
////                    //设备2状态 未启动
////                    message = new Message();
////                    bundle.putString("zt2", "0");
////                    message.setData(bundle);
////                    handler.sendMessage(message);
////                    message.what = 200120;
//////
//
////                    interrupted();//中断线程
////                    ThreadFlag = false;
//////                    System.exit(0);
//
////
///
/// .scheduleAtFixedRate(new TimerTask() {
////            @Override
////            public void run() {
////
////            }
////        }, 0, 1000);
//
//                }
                /// 外面

            }
        }).

                start();

    }

}
