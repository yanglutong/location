package com.sm.android.locations.location.App;

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

import com.sm.android.locations.location.Activity.AddParam.AddParamActivity;
import com.sm.android.locations.location.Activity.AddParam.ParamActivity;
import com.sm.android.locations.location.Activity.Login.LoginActivity;
import com.sm.android.locations.location.Activity.Main.MainActivity;
import com.sm.android.locations.location.Activity.PinConfig.PinConfigActivity;
import com.sm.android.locations.location.Receiver.ConnectionChangeReceiver;
import com.sm.android.locations.location.Test.ReceiveTask;
import com.sm.android.locations.location.Utils.MainUtils.DbUtils;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.SpBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.ZmBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerZM;
import com.sm.android.locations.location.Utils.ToastUtils;

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

import static com.sm.android.locations.location.Activity.Main.MainActivity.SPBEANLIST1;
import static com.sm.android.locations.location.Activity.Main.MainActivity.SPBEANLIST2;
import static com.sm.android.locations.location.Constant.Constant.BAND1;
import static com.sm.android.locations.location.Constant.Constant.BAND2;
import static com.sm.android.locations.location.Constant.Constant.BLACKLISTSB1;
import static com.sm.android.locations.location.Constant.Constant.BLACKLISTSB2;
import static com.sm.android.locations.location.Constant.Constant.BOARDTEMPERATURE1;
import static com.sm.android.locations.location.Constant.Constant.BOARDTEMPERATURE2;
import static com.sm.android.locations.location.Constant.Constant.CELLID1;
import static com.sm.android.locations.location.Constant.Constant.CELLID2;
import static com.sm.android.locations.location.Constant.Constant.DBM1;
import static com.sm.android.locations.location.Constant.Constant.DBM2;
import static com.sm.android.locations.location.Constant.Constant.DEVICENUMBER1;
import static com.sm.android.locations.location.Constant.Constant.DEVICENUMBER2;
import static com.sm.android.locations.location.Constant.Constant.DK1;
import static com.sm.android.locations.location.Constant.Constant.DK2;
import static com.sm.android.locations.location.Constant.Constant.DOWNPIN1;
import static com.sm.android.locations.location.Constant.Constant.DOWNPIN2;
import static com.sm.android.locations.location.Constant.Constant.DWON1;
import static com.sm.android.locations.location.Constant.Constant.DWON2;
import static com.sm.android.locations.location.Constant.Constant.ENDNUM1;
import static com.sm.android.locations.location.Constant.Constant.ENDNUM2;
import static com.sm.android.locations.location.Constant.Constant.GZMS1;
import static com.sm.android.locations.location.Constant.Constant.GZMS2;
import static com.sm.android.locations.location.Constant.Constant.HARDWAREVERSION1;
import static com.sm.android.locations.location.Constant.Constant.HARDWAREVERSION2;
import static com.sm.android.locations.location.Constant.Constant.IMSINUM1;
import static com.sm.android.locations.location.Constant.Constant.IMSINUM2;
import static com.sm.android.locations.location.Constant.Constant.IP1;
import static com.sm.android.locations.location.Constant.Constant.IP2;
import static com.sm.android.locations.location.Constant.Constant.MACADDRESS1;
import static com.sm.android.locations.location.Constant.Constant.MACADDRESS2;
import static com.sm.android.locations.location.Constant.Constant.PCI1;
import static com.sm.android.locations.location.Constant.Constant.PCI2;
import static com.sm.android.locations.location.Constant.Constant.PLMN1;
import static com.sm.android.locations.location.Constant.Constant.PLMN2;
import static com.sm.android.locations.location.Constant.Constant.REQUEST1;
import static com.sm.android.locations.location.Constant.Constant.REQUEST2;
import static com.sm.android.locations.location.Constant.Constant.SBZQ1;
import static com.sm.android.locations.location.Constant.Constant.SBZQ2;
import static com.sm.android.locations.location.Constant.Constant.SHUAIJIAN1;
import static com.sm.android.locations.location.Constant.Constant.SHUAIJIAN2;
import static com.sm.android.locations.location.Constant.Constant.SNNUMBER1;
import static com.sm.android.locations.location.Constant.Constant.SNNUMBER2;
import static com.sm.android.locations.location.Constant.Constant.SOFTWAREVERSION1;
import static com.sm.android.locations.location.Constant.Constant.SOFTWAREVERSION2;
import static com.sm.android.locations.location.Constant.Constant.TAC1;
import static com.sm.android.locations.location.Constant.Constant.TAC2;
import static com.sm.android.locations.location.Constant.Constant.TBTYPE1;
import static com.sm.android.locations.location.Constant.Constant.TBTYPE2;
import static com.sm.android.locations.location.Constant.Constant.TBZT1;
import static com.sm.android.locations.location.Constant.Constant.TBZT2;
import static com.sm.android.locations.location.Constant.Constant.TYPE1;
import static com.sm.android.locations.location.Constant.Constant.TYPE2;
import static com.sm.android.locations.location.Constant.Constant.UBOOTVERSION1;
import static com.sm.android.locations.location.Constant.Constant.UBOOTVERSION2;
import static com.sm.android.locations.location.Constant.Constant.UEIMS2;
import static com.sm.android.locations.location.Constant.Constant.UEIMSI;
import static com.sm.android.locations.location.Constant.Constant.UEMAX1;
import static com.sm.android.locations.location.Constant.Constant.UEMAX2;
import static com.sm.android.locations.location.Constant.Constant.UEMAXOF1;
import static com.sm.android.locations.location.Constant.Constant.UEMAXOF2;
import static com.sm.android.locations.location.Constant.Constant.UP1;
import static com.sm.android.locations.location.Constant.Constant.UP2;
import static com.sm.android.locations.location.Constant.Constant.ZENGYI1;
import static com.sm.android.locations.location.Constant.Constant.ZENGYI2;
import static com.sm.android.locations.location.Constant.Constant.ZHZQ1;
import static com.sm.android.locations.location.Constant.Constant.ZHZQ2;
import static com.sm.android.locations.location.Utils.MainUtils.MainUtils.StringAd;
import static com.sm.android.locations.location.Utils.MainUtils.MainUtils.StringPin;
import static com.sm.android.locations.location.Utils.MainUtils.MainUtils.StringTOIMEI;
import static com.sm.android.locations.location.Utils.MainUtils.MainUtils.hexStringToString;
import static com.sm.android.locations.location.Utils.MainUtils.MainUtils.mPressedTime1;

public class App extends Application {
    private static DBManagerZM dbManagerZM;
    Message message;
    Bundle bundle;
    DatagramPacket dp;
    byte[] buf;
    Timer timer1, timer2;
    boolean runThread = false;
    public static Context context;//??????????????????????????????;application??????

    //Application.class
    private static Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
//            mListener.heandleMessage(msg);
            System.out.println("mListener ?????????==== " + mListener);
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
//        wifi???????????????
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
//        MainUtils.ReceiveMain0(mHandler, message, bundle, timer1, timer2, dp, buf, context, runThread);//??????????????????
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
        //??? App ??????????????????, ????????????????????????????????????, ???????????? App ?????????????????? initCompatMultiProcess()
        //??? Demo ??????????????????????????? DefaultErrorActivity ??????????????????????????????, ???????????????????????? Activity ??????????????? initCompatMultiProcess()
        AutoSize.initCompatMultiProcess(this);
        /**
         * ????????? AndroidAutoSize ????????????????????????, {@link AutoSizeConfig} ??????????????????????????????????????????
         * ????????????????????????????????????????????????????????????, ?????????????????????????????????!!!
         */
        AutoSizeConfig.getInstance()

                //?????????????????????????????? Fragment ???????????????, ????????????????????????????????????, ?????????????????????????????????
                //???????????????????????????????????????
                .setCustomFragment(true)

                //????????????????????????????????? AndroidAutoSize ?????????, ????????? true, App ????????????????????????????????????????????????????????????????????????
                //????????? false, ????????????????????????????????????????????????, ????????? false
//                .setExcludeFontScale(true)
                .setUseDeviceSize(true)
                //?????????????????????
                .setOnAdaptListener(new onAdaptListener() {
                    @Override
                    public void onAdaptBefore(Object target, Activity activity) {
                        //??????????????????, ????????? Android ????????????????????????, ????????????????????????????????????????????????????????? App ??????????????????
                        //??????????????????????????????, ????????????????????????, ?????????????????????????????????????????????, ScreenUtils.getScreenSize(activity) ??????????????????????????? Application!!!
//                        AutoSizeConfig.getInstance().setScreenWidth(ScreenUtils.getScreenSize(activity)[0]);
//                        AutoSizeConfig.getInstance().setScreenHeight(ScreenUtils.getScreenSize(activity)[1]);
                        LogUtils.d(String.format(Locale.ENGLISH, "%s onAdaptBefore!", target.getClass().getName()));
                    }

                    @Override
                    public void onAdaptAfter(Object target, Activity activity) {
                        LogUtils.d(String.format(Locale.ENGLISH, "%s onAdaptAfter!", target.getClass().getName()));
                    }
                });

        //???????????? AutoSize ???????????????, ????????? true, ??????????????? AutoSize ????????????, ??????????????? false
//                .setLog(false)

        //??????????????????????????????????????????, ????????? false, ??????????????? false, ??????????????????????????????????????????
        //AutoSize ??????????????????????????????????????????????????????
        //????????? true ????????????????????????????????????, ???????????????????????????
//                .setUseDeviceSize(true)

        //?????????????????????????????????????????????, ????????? true, ??????????????? false, AutoSize ?????????????????????????????????
//                .setBaseOnWidth(false)

        //?????????????????????????????????, ??????????????????, ???????????????????????????
//                .setAutoAdaptStrategy(new AutoAdaptStrategy())

        customAdaptForExternal();

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
            DbUtils.insertDB(context, 38, 37900, "46000", 0, 37900, "TDD", "??????");
            DbUtils.insertDB(context, 38, 38098, "46000", 0, 38098, "TDD", "??????");
            DbUtils.insertDB(context, 39, 38400, "46000", 0, 38400, "TDD", "??????");
            DbUtils.insertDB(context, 39, 38544, "46000", 0, 38544, "TDD", "??????");
            DbUtils.insertDB(context, 40, 38950, "46000", 0, 38950, "TDD", "??????");
            DbUtils.insertDB(context, 41, 40936, "46000", 0, 40936, "TDD", "??????");
            //??????FDD
            DbUtils.insertDB(context, 3, 1300, "46000", 0, 19300, "FDD", "??????");
            DbUtils.insertDB(context, 8, 3683, "46000", 0, 21683, "FDD", "??????");
            //??????FDD
            DbUtils.insertDB(context, 1, 375, "46001", 0, 18375, "FDD", "??????");
            DbUtils.insertDB(context, 3, 1650, "46001", 0, 19650, "FDD", "??????");
            //??????
            DbUtils.insertDB(context, 1, 100, "46011", 0, 18100, "FDD", "??????");
            DbUtils.insertDB(context, 3, 1825, "46011", 0, 19825, "FDD", "??????");
            DbUtils.insertDB(context, 3, 1850, "46011", 0, 19850, "FDD", "??????");
            DbUtils.insertDB(context, 5, 2452, "46011", 0, 20452, "FDD", "??????");
            //???????????????????????????
            //??????TDD
            DbUtils.insertDBSaopin(context, 37900, 37900, 1);
            DbUtils.insertDBSaopin(context, 38098, 38098, 1);
            DbUtils.insertDBSaopin(context, 38400, 38400, 1);
            DbUtils.insertDBSaopin(context, 38544, 38544, 1);
            DbUtils.insertDBSaopin(context, 38950, 38950, 1);
            DbUtils.insertDBSaopin(context, 40936, 40936, 1);
            //??????FDD
            DbUtils.insertDBSaopin(context, 1300, 19300, 2);
            DbUtils.insertDBSaopin(context, 3683, 21683, 2);
            //??????FDD
            DbUtils.insertDBSaopin(context, 375, 18375, 3);
            DbUtils.insertDBSaopin(context, 1650, 19650, 3);
            //??????FDD
            DbUtils.insertDBSaopin(context, 100, 18100, 4);
            DbUtils.insertDBSaopin(context, 1825, 19825, 4);
            DbUtils.insertDBSaopin(context, 1850, 19850, 4);
            DbUtils.insertDBSaopin(context, 2452, 20452, 4);

            //?????????????????????????????????
            //??????TDD
            DbUtils.insertDBLunxun(context, 37900, 37900, 1);
            DbUtils.insertDBLunxun(context, 38098, 38098, 1);
            DbUtils.insertDBLunxun(context, 38400, 38400, 1);
            DbUtils.insertDBLunxun(context, 38544, 38544, 1);
            DbUtils.insertDBLunxun(context, 38950, 38950, 1);
            DbUtils.insertDBLunxun(context, 40936, 40936, 1);
            //??????FDD
            DbUtils.insertDBLunxun(context, 1300, 19300, 2);
            DbUtils.insertDBLunxun(context, 3683, 21683, 2);
            //??????FDD
            DbUtils.insertDBLunxun(context, 375, 18375, 3);
            DbUtils.insertDBLunxun(context, 1650, 19650, 3);
            //??????FDD
            DbUtils.insertDBLunxun(context, 100, 18100, 4);
            DbUtils.insertDBLunxun(context, 1825, 19825, 4);
            DbUtils.insertDBLunxun(context, 1850, 19850, 4);
            DbUtils.insertDBLunxun(context, 2452, 20452, 4);
            //???????????????????????????
            DbUtils.xiaoqu(context);
            //???????????????????????????
            DbUtils.ZY(context, "20", "52", "72", 1, "20", "40", "72");//TDD
            DbUtils.ZY(context, "20", "52", "72", 2, "20", "40", "72");//FDD
//            ???????????????????????????imsi
//            460005192822841
//            DbUtils.TestIMSI(context, "460002360893648", "???", "", "??????", false);
//            DbUtils.TestIMSI(context, "460005192822841", "?????????", "", "??????", false);
//            DbUtils.TestIMSI(context, "460010619201205", "???", "", "??????", false);
//            DbUtils.TestIMSI(context, "460011641414031", "?????????", "", "??????", false);
//
//
//            DbUtils.TestIMSIWhite(context, "460002360893648", "?????????", "", "??????", true);
//            DbUtils.TestIMSIWhite(context, "460005192822841", "?????????", "", "??????", false);
//            DbUtils.TestIMSIWhite(context, "460010619201205", "???", "", "??????", false);
//            DbUtils.TestIMSIWhite(context, "460011641414031", "?????????", "", "??????", false);
            //?????????
            DbUtils.zc(context);
            return true;
        } else {
//            startActivity(new Intent(context, MainActivity.class));
//            finish();
//            Log.i("GFA", "N???");
//            Toast.makeText(getApplicationContext(),"N???",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    /**
     * ????????????????????? {@link Activity} ?????????????????????, ?????????????????? {@link Activity} ?????????????????????
     * {@link CustomAdapt} ????????????????????????????????????????????? (?????????????????????????????????)
     * ???????????? {@link ExternalAdaptManager} ??????????????????????????????, ??????????????????????????????
     */
    private void customAdaptForExternal() {
        /**
         * {@link ExternalAdaptManager} ??????????????????????????????????????????????????????????????????, ?????????????????? {@link ExternalAdaptManager} ????????????
         */
        AutoSizeConfig.getInstance().getExternalAdaptManager()
                //????????? Activity ????????????????????????, ???????????????????????? Activity, ????????????????????????
                //??????????????????????????????????????????, ?????? addExternalAdaptInfoOfActivity ??????, ????????????????????????????????????, ???????????? App ???????????????
//                .addCancelAdaptOfActivity(DefaultErrorActivity.class)

                //???????????? Activity ???????????????????????????, AndroidAutoSize ?????????????????????????????????????????????, ????????????????????????
                //???????????????????????? Activity, ?????????????????????????????????????????????????????????????????????????????????, ??????????????????????????????????????????
                //??????????????????????????????????????????, ????????????????????? (??????????????????????????????????)
                //???????????????????????????????????????????????????, ??????????????????????????????????????????????????????, ????????????????????????
                //?????? AndroidAutoSize ???????????????????????????????????????????????????, ??????????????????????????????????????????????????????????????????????????????
                //??????????????????????????????????????????????????????????????????????????????, ???????????????, ???????????????????????????????????????????????????
                //?????????????????????????????????????????????????????????????????????????????????, ????????? AndroidAutoSize ?????????
                //???????????????????????????????????????????????? dp ??? sp, ??????????????????????????? px, ??? AndroidAutoSize ??????????????????
                //???????????? DefaultErrorActivity ????????????????????? 380dp - 400dp ?????????????????????????????????
                //?????????
                .addExternalAdaptInfoOfActivity(MainActivity.class, new ExternalAdaptInfo(true, 400));
        //????????????
        AutoSizeConfig.getInstance().getExternalAdaptManager()
                .addExternalAdaptInfoOfActivity(LoginActivity.class, new ExternalAdaptInfo(true, 400));
        AutoSizeConfig.getInstance().getExternalAdaptManager()
                //????????????
                .addExternalAdaptInfoOfActivity(AddParamActivity.class, new ExternalAdaptInfo(true, 400));
        AutoSizeConfig.getInstance().getExternalAdaptManager()
                //????????????
                .addExternalAdaptInfoOfActivity(ParamActivity.class, new ExternalAdaptInfo(true, 400));
        AutoSizeConfig.getInstance().getExternalAdaptManager()
                //????????????
                .addExternalAdaptInfoOfActivity(PinConfigActivity.class, new ExternalAdaptInfo(true, 400));
    }

    public static String str3 = "";
    public static String str4 = "";

    public static Context getContexts() {
        return context;
    }

    private static long ZTLONG = 8000; //?????????
    private static long ZTLONGS = 180000;//????????????
    public static long mPressedTime2 = 0;
    private static boolean ThreadFlag = true;
    // ?????????????????????
    public static SimpleDateFormat ReceiveMainDateFormat1 = new SimpleDateFormat("" + "yyyy-MM-dd HH:mm:ss ");
    public static SimpleDateFormat ReceiveMainDateFormat2 = new SimpleDateFormat("" + "HH:mm:ss ");

    public static void ReceiveMain(final Handler handler, Message messages, final Bundle bundle, final Timer timer1, final Timer timer2, final DatagramPacket dp, final byte[] buf, final Context context, final boolean runThread) {


        mPressedTime1 = System.currentTimeMillis();
        mPressedTime2 = System.currentTimeMillis();
        Timer timers = new Timer();
        timers.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                long mNowTime = System.currentTimeMillis();//???????????????????????????
                if ((mNowTime - mPressedTime1) > ZTLONG) {//???????????????????????????
//                    ToastUtils.showToast("????????????????????????");
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
                        //??????1????????????
                        Message message = new Message();
                        Bundle bundle = new Bundle();
                        bundle.putString("zt1", "0");
                        message.setData(bundle);
                        handler.sendMessage(message);
                        message.what = 100120;
                        mListener.heandleMessage(message);
                    }
                } else {//????????????
//                        Log.d(TAG, "mPressedTimerun2: ");
//                        System.exit(0);
                }
                if ((mNowTime - mPressedTime2) > ZTLONG) {//???????????????????????????
//                    ToastUtils.showToast("????????????????????????");
//                        mPressedTime = mNowTime;
//                        Log.d(TAG, "mPressedTimerun1: ");

//                        //??????2????????????
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

                } else {//????????????
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
//                    long mNowTime = System.currentTimeMillis();//???????????????????????????
//                    if ((mNowTime - mPressedTime1) > ZTLONG) {//???????????????????????????
////                    ToastUtils.showToast("????????????????????????");
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
//                            //??????1????????????
//                            Message message = new Message();
//                            Bundle bundle = new Bundle();
//                            bundle.putString("zt1", "0");
//                            message.setData(bundle);
//                            handler.sendMessage(message);
//                            message.what = 100120;
//                        }
//
//                    } else {//????????????
////                        Log.d(TAG, "mPressedTimerun2: ");
////                        System.exit(0);
//                    }
//                    if ((mNowTime - mPressedTime2) > ZTLONG) {//???????????????????????????
////                    ToastUtils.showToast("????????????????????????");
////                        mPressedTime = mNowTime;
////                        Log.d(TAG, "mPressedTimerun1: ");
//
////                        //??????2????????????
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
//                    } else {//????????????
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
                    //??????1????????????
                    message = new Message();
                    bundle.putString("zt1", "0");
                    message.setData(bundle);
                    handler.sendMessage(message);
                    message.what = 100120;
                    mListener.heandleMessage(message);
                    //??????2????????????
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
                    //??????udp???socket????????????????????????????????????receive??????
//                        ds.setSoTimeout((int) timerDate);//????????????
//                        if (wifiFlag==false){
//                            return;
//                        }
                    try {
                        ds.receive(dp);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    byte[] buf1 = dp.getData();
                    //btye[]?????????????????????16??????????????????
                    String str = ReceiveTask.toHexString1(buf1);
                    //String str = new String(dp.getData(),0,dp.getLength());
                    String ID1TF = "";
                    String ID2TF = "";
                    if (IP1.equals(dp.getAddress().getHostAddress())) {
                        mPressedTime1 = System.currentTimeMillis();
                        System.out.println("123456");
                        System.out.println("??????" + dp.getAddress().getHostAddress() + "???????????????" + str);

                        //??????
                        Date d = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        System.out.println("?????????" + sdf.format(d));
                        //????????????????????????????????????
                        if ("2cf0".equals(str.substring(8, 12))) {

                            if ("00000000".equals(str.substring(24, 32))) {
                                System.out.println("???????????????" + hexStringToString(str.substring(32, 46)));
                                DEVICENUMBER1 = hexStringToString(str.substring(32, 46));
                            } else if ("01000000".equals(str.substring(24, 32))) {
                                System.out.println("???????????????" + hexStringToString(str.substring(32, 34)));
                                HARDWAREVERSION1 = hexStringToString(str.substring(32, 34));
                            } else if ("02000000".equals(str.substring(24, 32))) {
                                System.out.println("???????????????" + hexStringToString(str.substring(32, 106)));
                                SOFTWAREVERSION1 = hexStringToString(str.substring(32, 106));
                            } else if ("03000000".equals(str.substring(24, 32))) {
                                System.out.println("?????????SN:" + hexStringToString(str.substring(32, 70)));
                                SNNUMBER1 = hexStringToString(str.substring(32, 70));
                            } else if ("04000000".equals(str.substring(24, 32))) {
                                System.out.println("MAC?????????" + hexStringToString(str.substring(32, 66)));
                                MACADDRESS1 = hexStringToString(str.substring(32, 66));
                            } else if ("05000000".equals(str.substring(24, 32))) {
                                UBOOTVERSION1 = hexStringToString(str.substring(32, 47));
                                System.out.println("uboot????????????" + hexStringToString(str.substring(32, 47)));
                            } else if ("06000000".equals(str.substring(24, 32))) {
                                System.out.println("???????????????" + hexStringToString(str.substring(32, 50)));
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
                            System.out.println("????????????" + str.toString());
                            String substring = str.substring(25, 26);
                            String substring2 = str.substring(29, 30);
                            TBTYPE1 = substring.toString();
                            TBZT1 = str.substring(29, 30);
                            Log.d("Asubstringzt1", "run: " + substring2.toString());
                            Log.d("Asubstringzt1", "run: " + str.toString());
                        }
//                        if (str.contains("2ef0")) {
//                            System.out.println("????????????--" + str.toString());
//                        }
//                        if ("f02d".equals(str.substring(13, 14))) {
//                            Log.d("nzq2df0",str.toString());
//                            System.out.println("????????????"+str.toString());
//                        }
                        //??????????????????????????????
                        if ("28f0".equals(str.substring(8, 12))) {
                            //PLMN
//                            System.out.println(StringTOIMEI(str.substring(40, 50)));
                            PLMN1 = StringTOIMEI(str.substring(40, 50));

                            //????????????
                            System.out.println("?????????????????????" + Integer.parseInt(StringPin(str.substring(24, 32)), 16));
                            UP1 = Integer.parseInt(StringPin(str.substring(24, 32)), 16) + "";
                            //????????????
                            System.out.println(Integer.parseInt(StringPin(str.substring(32, 40)), 16));
                            DWON1 = Integer.parseInt(StringPin(str.substring(32, 40)), 16) + "";
                            //Band
                            System.out.println(Integer.parseInt(StringPin(str.substring(56, 64)), 16));
                            BAND1 = Integer.parseInt(StringPin(str.substring(56, 64)), 16) + "";
                            //??????
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
                        //???????????????????????????????????????
                        if ("30f0".equals(str.substring(8, 12))) {
                            if ("00000000".equals(str.substring(24, 32))) {
                                System.out.println("???????????????");
                                TYPE1 = "???????????????";
                            } else if ("01000000".equals(str.substring(24, 32))) {
                                System.out.println("??????????????????");
                                TYPE1 = "??????????????????";
                            } else if ("02000000".equals(str.substring(24, 32))) {
                                System.out.println("???????????????");
                                TYPE1 = "???????????????";
                            } else if ("03000000".equals(str.substring(24, 32))) {
                                System.out.println("???????????????");
                                TYPE1 = "???????????????";
                            } else if ("04000000".equals(str.substring(24, 32))) {
                                System.out.println("??????????????????");
                                TYPE1 = "??????????????????";
                            }
                        }

                        //UE??????????????????????????????
                        if ("3ef0".equals(str.substring(8, 12))) {
                            //????????????
                            if ("00".equals(str.substring(24, 26))) {
                                System.out.println("??????????????????");
                                GZMS1 = "??????????????????";
                            } else if ("01".equals(str.substring(24, 26))) {
                                System.out.println("??????????????????");
                                //??????????????????????????????????????????IMSI ???????????????
                                System.out.println(Integer.parseInt(StringPin(str.substring(28, 32)), 16));
                                ZHZQ1 = "" + Integer.parseInt(StringPin(str.substring(28, 32)), 16);
                                GZMS1 = "??????????????????";
                            } else if ("02".equals(str.substring(24, 26))) {
                                System.out.println("????????????");
                                GZMS1 = "????????????";
                                //?????????????????????????????? IMSI
                                UEIMSI = StringTOIMEI(str.substring(32, 62));
                                System.out.println("imsi:" + StringTOIMEI(str.substring(32, 62)));
                                //?????????????????????????????????????????????,??????????????? 1024ms, 0???120ms,1???240ms,2???480ms,3???640ms,4???1024ms,5???2048ms

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
                                //??????????????????????????????????????????????????????????????????????????? 0,0???enable,1???disable
                                if ("00".equals(str.substring(68, 70))) {
                                    System.out.println("enable");
                                    UEMAXOF1 = "???";
                                } else if ("01".equals(str.substring(68, 70))) {
                                    System.out.println("disable");
                                    UEMAXOF1 = "???";
                                }
                                //???????????????UE ?????????????????????????????? ??? ??? ???wrFLLmtToEnbSysArfcnCfg ?????????UePMax?????????????????? 23dBm
                                UEMAX1 = Integer.parseInt(str.substring(70, 72), 16) + "";
                                System.out.println(Integer.parseInt(str.substring(70, 72), 16));

                                //???????????????????????????????????? SRS ????????????????????????????????????????????????disable??????????????????????????????????????????????????????0: disable,1: enable
                                if ("00".equals(str.substring(72, 74))) {
                                    System.out.println("disable");
                                } else if ("01".equals(str.substring(72, 74))) {
                                    System.out.println("enable");
                                }
                                //?????????????????????????????????????????????????????????????????????????????? 0???????????? 1 ??????????????????????????????????????????????????????????????????????????????1??????????????????????????????????????????, 0?????????????????????????????????
                                if ("00".equals(str.substring(76, 78))) {
                                    System.out.println("??????????????????????????????");
                                } else if ("01".equals(str.substring(76, 78))) {
                                    System.out.println("???????????????????????????????????????");
                                }
                                //??????????????????????????????????????????SRS ?????????
                                if ("00".equals(str.substring(78, 80))) {
                                    System.out.println("disable");
                                } else if ("01".equals(str.substring(78, 80))) {
                                    System.out.println("enable");
                                }
                            } else if ("03".equals(str.substring(24, 26))) {
                                System.out.println("????????????");
                                GZMS1 = "????????????";
                                //????????????????????????0????????????????????????1?????????????????????
                                if ("00".equals(str.substring(80, 82))) {
                                    System.out.println("??????????????????");
                                    GZMS1 = "??????????????????";
                                } else if ("01".equals(str.substring(80, 82))) {
                                    System.out.println("??????????????????");
                                    GZMS1 = "??????????????????";
                                }
                            } else if ("04".equals(str.substring(24, 26))) {
                                System.out.println("???????????????");
                                GZMS1 = "???????????????";
                            /*0: ??????????????????????????????????????????????????????????????????
                            1: ????????????????????????????????????????????????????????????
							2: ?????????????????????????????????????????????????????????????????????
							3: ?????????????????????????????????;???????????????????????????
							4: ?????????????????????*/
                                System.out.println(Integer.parseInt(str.substring(26, 28), 16));

                            }

                        }
//?????????????????????????????????
                        if ("56f0".equals(str.substring(8, 12))) {

                            //???????????????
                            Integer blacklistNum = Integer.parseInt(StringPin(str.substring(24, 28)), 16);
                            int begin = 28;
                            int end = 58;
                            for (int i = 0; i < blacklistNum; i++) {
                                System.out.println("???????????????" + StringTOIMEI(str.substring(begin, end)));
                                BLACKLISTSB1.add(StringTOIMEI(str.substring(begin, end)));
                                begin = begin + 34;
                                end = end + 34;
                            }

                        }
                        //??????????????????????????????????????????
                        if ("32f0".equals(str.substring(8, 12))) {
                            //??????
                            //??????????????????????????????????????????FDD ??????????????????????????????????????????????????????
                            System.out.println("?????????1" + Integer.parseInt(str.substring(24, 26), 16));
                            ZENGYI1 = Integer.parseInt(str.substring(24, 26), 16) + "";
                            //????????????  ??????
                            //??????????????????????????????????????????FDD ??????????????????????????????????????????????????????
                            System.out.println(Integer.parseInt(str.substring(28, 30), 16));
                            SHUAIJIAN1 = Integer.parseInt(str.substring(28, 30), 16) + "";
                            System.out.println("?????????1" + Integer.parseInt(str.substring(24, 26), 16));

//                            //???????????????????????????????????????????????????,
//                            System.out.println(Integer.parseInt(str.substring(26,28),16));
//
//                            //???????????????????????????????????????????????????
//                            System.out.println(Integer.parseInt(str.substring(30,32),16));
//                            //FDD AGC ??????
//                            if("00".equals(str.substring(30,32))){
//                                System.out.println("FDD AGC ????????????");
//                            }else if("01".equals(str.substring(30,32))){
//                                System.out.println("FDD AGC ????????????");
//                            }

                            //??????
                            //??????FDD?????????????????????????????????????????????????????????,????????????????????????????????????????????????????????????
//                            System.out.println(Integer.parseInt(str.substring(34,36),16));
//                            //eeprom ??????????????????????????????????????????
//                            System.out.println(Integer.parseInt(str.substring(36,38),16));
                        }
                        //????????????????????? ????????????
                        if ("3af0".equals(str.substring(8, 12))) {
                            if ("00000000".equals(str.substring(24, 32))) {
                                System.out.println("?????????????????????" + opionts1);
                            }

                        }
                        //????????????????????? ????????????
                        if ("54f0".equals(str.substring(8, 12))) {
                            if ("00000000".equals(str.substring(24, 32))) {
                                opionts1++;
                                System.out.println("???????????????????????????" + opionts1);
                                if (opionts1 % 2 == 0) {
                                    Log.d("jsgs", "run:??????");
                                    message = new Message();
                                    bundle.putString("100130", "FDD");
                                    message.setData(bundle);
                                    handler.sendMessage(message);
                                    message.what = 100131;
                                    mListener.heandleMessage(message);
                                } else {
                                    Log.d("jsgs", "run:??????");
                                    message = new Message();
                                    bundle.putString("100131", "FDD");
                                    message.setData(bundle);
                                    handler.sendMessage(message);
                                    message.what = 100130;
                                    mListener.heandleMessage(message);
                                }
                            } else {
                                System.out.println("???????????????????????????");

                                if (opionts2 % 2 == 0) {
                                    Log.d("jsgs", "run:??????");
                                    message = new Message();
                                    bundle.putString("100130", "FDD");
                                    message.setData(bundle);
                                    handler.sendMessage(message);
                                    message.what = 100135;
                                    mListener.heandleMessage(message);
                                } else {
                                    Log.d("jsgs", "run:??????");
                                    message = new Message();
                                    bundle.putString("100131", "FDD");
                                    message.setData(bundle);
                                    handler.sendMessage(message);
                                    message.what = 100134;
                                    mListener.heandleMessage(message);
                                }


                            }
                        }
                        //???????????????
                        if ("05f0".equals(str.substring(8, 12))) {
                            String down = "";
                            System.out.println("1???????????????IMSI??????" + hexStringToString(str.substring(32, 62)));
                            //??????????????????????????????
//                            if ("28f0".equals(str.substring(8, 12))) {
//                                down = Integer.parseInt(StringPin(str.substring(32, 40)), 16) + "";
//                            }
//                            down=Integer.parseInt(StringPin(str.substring(40, 48)), 16)+"";
                            Date now = new Date();
                            String imsi = hexStringToString(str.substring(32, 62));
                            Log.d("jsgs", "run:??????");
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
                            System.out.println("Imsi???????????????" + imsi + "down" + down);

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

                        //?????????????????????????????????
                        if ("07f0".equals(str.substring(8, 12))) {
                            if ("00000000".equals(str.substring(24, 32))) {
                                System.out.println("????????????ue????????????");

                                message = new Message();
                                bundle.putString("test", "");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 100136;
                                mListener.heandleMessage(message);
                            } else {
                                System.out.println("????????????ue????????????");


                                message = new Message();
                                bundle.putString("test", "");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 100137;
                                mListener.heandleMessage(message);
                            }
                        }
                        //??????????????????
                        if ("0cf0".equals(str.substring(8, 12))) {
                            //??????0????????????>0???16????????????????????????????????????
                            int row = Integer.parseInt(str.substring(24, 32), 16);
                            if (row == 0) {
                                System.out.println("?????????????????????");
                                message = new Message();
                                bundle.putString("test", "");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 100138;
                            } else {
                                System.err.println("???????????????");
                                message = new Message();
                                bundle.putString("test", "");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 100139;
                                mListener.heandleMessage(message);
                            }
                        }
                        //????????????????????????
                        if ("02f0".equals(str.substring(8, 12))) {
                            //??????0????????????>0???16????????????????????????????????????
                            int row = Integer.parseInt(str.substring(24, 32), 16);
                            if (row == 0) {
                                System.out.println("?????????????????????");
                                message = new Message();
                                bundle.putString("test", "");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 100140;
                                mListener.heandleMessage(message);
                            } else {
                                System.err.println("??????????????????");
                                message = new Message();
                                bundle.putString("test", "");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 100141;
                                mListener.heandleMessage(message);
                            }
                        }
                        //?????????FDD TDD
                        if ("00ff".equals(str.substring(16, 20))) {
                            //????????????FDD
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
                            //????????????TDD
                            ID1TF = "TDD";
                            System.out.println("TDD");
                            message = new Message();
                            bundle.putString("tf1", "TDD");
                            message.setData(bundle);
                            handler.sendMessage(message);
                            message.what = 100110;
                            mListener.heandleMessage(message);
                        }
                        //??????????????????????????????1
                        if ("04f0".equals(str.substring(8, 12))) {
                            //??????0????????????>0???16????????????????????????????????????
                            int row = Integer.parseInt(str.substring(24, 32), 16);
                            Log.d("wtto", "04f0run:0 ");
                            if (row == 0) {
                                message = new Message();
                                bundle.putString("test", "0");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 100145;
                                mListener.heandleMessage(message);
                                System.out.println("????????????????????????????????????");
                                Log.d("wtto", "04f0run:1 ");
                            } else {
                                System.err.println("???????????????");
                                message = new Message();
                                bundle.putString("test", "0");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 100146;
                                mListener.heandleMessage(message);
                                Log.d("wtto", "04f0run:2 ");
                            }
                        }
                        //????????????
//                        if ("a0f0".equals(str.substring(8, 12))) {
//                            //??????0????????????>0???16????????????????????????????????????
//                            int row = Integer.parseInt(str.substring(24, 32), 16);
//                            if (row == 0) {
//                                System.out.println("?????????????????????");
//                                message = new Message();
//                                bundle.putString("test", "0");
//                                message.setData(bundle);
//                                handler.sendMessage(message);
//                                message.what = 100142;
//                            } else {
//                                System.err.println("???????????????");
//                                message = new Message();
//                                bundle.putString("test", "0");
//                                message.setData(bundle);
//                                handler.sendMessage(message);
//                                message.what = 100143;
//                            }
//                        }
                        //???????????????????????????,?????????????????????
                        if ("a0f0".equals(str.substring(8, 12))) {
                            //??????0????????????>0???16????????????????????????????????????
                            int row = Integer.parseInt(str.substring(24, 32), 16);
                            if (row == 0) {
                                System.out.println("??????????????????");
                                message = new Message();
                                bundle.putString("test", "0");
                                message.setData(bundle);
                                handler.sendMessage(message);
//                                message.what = 100142;
                                mListener.heandleMessage(message);
                            } else {
                                System.err.println("??????????????????");
                                message = new Message();
                                bundle.putString("test", "0");
                                message.setData(bundle);
                                handler.sendMessage(message);
//                                message.what = 100143;

                                mListener.heandleMessage(message);
                            }
                        }
                        //???????????????????????????
                        if ("0ef0".equals(str.substring(8, 12))) {
                            //??????0????????????>0???16????????????????????????????????????
                            int row = Integer.parseInt(str.substring(24, 32), 16);
                            if (row == 0) {
                                System.out.println("??????????????????????????????????????????????????????");
                            } else {
                                System.err.println("???????????????");
                            }
                        }
                        //??????????????????
                        if ("19f0".equals(str.substring(8, 12))) {
                            String state = str.substring(24, 32);
                            System.out.println("state" + state);
                            Log.d("wtto", "staterun: " + state);
                            if ("00000000".equals(state)) {
                                System.err.println("??????????????????");
                                Log.d("wtto", "qqqrun:??????????????????");

                                message = new Message();
                                bundle.putString("test", "1");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 1001200;
                                mListener.heandleMessage(message);
                            } else if ("01000000".equals(state)) {
                                System.err.println("??????????????????");
                                Log.d("wtto", "qqqrun:??????????????????");
                                message = new Message();
                                bundle.putString("test", "2");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 1001200;
                                mListener.heandleMessage(message);
                            } else if ("02000000".equals(state)) {
                                System.err.println("GPS????????????");
                                Log.d("wtto", "qqqrun:GPS????????????");
                                message = new Message();
                                bundle.putString("test", "3");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 1001200;
                                mListener.heandleMessage(message);
                            } else if ("03000000".equals(state)) {
                                System.err.println("GPS????????????");
                                Log.d("wtto", "qqqrun:GPS????????????");
                                message = new Message();
                                bundle.putString("test", "4");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 1001200;
                                mListener.heandleMessage(message);
                            } else if ("04000000".equals(state)) {
                                System.err.println("????????????");
                                Log.d("wtto", "qqqrun:????????????");
                                message = new Message();
                                bundle.putString("test", "5");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 1001200;
                                mListener.heandleMessage(message);
                            } else if ("05000000".equals(state)) {
                                System.err.println("????????????");
                                Log.d("wtto", "qqqrun:????????????");
                                message = new Message();
                                bundle.putString("test", "6");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 1001200;
                                mListener.heandleMessage(message);
                            } else if ("06000000".equals(state)) {
                                System.err.println("??????????????????");
                                Log.d("wtto", "qqqrun:??????????????????");
                                message = new Message();
                                bundle.putString("test", "7");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 1001200;
                                mListener.heandleMessage(message);
                            } else if ("07000000".equals(state)) {
                                System.err.println("??????????????????");
                                Log.d("wtto", "qqqrun:??????????????????");
                                message = new Message();
                                bundle.putString("test", "8");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 1001200;
                                mListener.heandleMessage(message);
                            } else if ("08000000".equals(state)) {
                                System.err.println("???????????????");
                                Log.d("wtto", "qqqrun:???????????????");
                                message = new Message();
                                bundle.putString("test", "9");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 1001200;
                                mListener.heandleMessage(message);
                            } else if ("09000000".equals(state)) {
                                System.err.println("???????????????");
                                Log.d("wtto", "qqqrun:???????????????");
                                message = new Message();
                                bundle.putString("test", "10");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 1001200;
                                mListener.heandleMessage(message);
                            } else if ("0a000000".equals(state)) {
                                System.err.println("GPS?????????");
                                Log.d("wtto", "qqqrun:GPS?????????");
                                message = new Message();
                                bundle.putString("test", "11");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 1001200;
                                mListener.heandleMessage(message);
                            } else if ("0b000000".equals(state)) {
                                System.err.println("?????????");
                                Log.d("wtto", "qqqrun:?????????");
                                message = new Message();
                                bundle.putString("test", "12");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 1001200;
                                mListener.heandleMessage(message);
                            } else if ("0c000000".equals(state)) {
                                System.err.println("???????????????");
                                Log.d("wtto", "qqqrun:???????????????");
                                message = new Message();
                                bundle.putString("test", "13");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 1001200;
                                mListener.heandleMessage(message);
                            } else if ("0d000000".equals(state)) {
                                System.err.println("??????????????????");
                                Log.d("wtto", "qqqrun:??????????????????");
                                message = new Message();
                                bundle.putString("test", "14");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 1001200;
                                mListener.heandleMessage(message);
                            }

                        }
                        if ("08f0".equals(str.substring(8, 12))) {

                            //???????????????16????????????????????????????????????
                            Integer.parseInt(str.substring(24, 26), 16);
                            System.out.println("?????????" + Integer.parseInt(str.substring(24, 26), 16));
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
                            //IMSI???
                            StringTOIMEI(str.substring(26, 56));
//                            System.out.println("IMSI???1???" + hexStringToString(str.substring(26, 56)));

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
                            //????????????
                            //?????????????????????????????????0????????? IDLE??????1?????????/??????????????????2?????????????????????3?????????????????????4????????????????????????
                            message = new Message();
                            bundle.putString("zt1", "2");
                            message.setData(bundle);
                            handler.sendMessage(message);
                            message.what = 100120;
                            mListener.heandleMessage(message);
                            if ("0000".equals(str.substring(24, 28))) {
                                System.out.println("0????????? IDLE???");
                                message = new Message();
                                bundle.putString("zt1", "2");//IDLE???
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 100120;
                                mListener.heandleMessage(message);
                            } else if ("0100".equals(str.substring(24, 28))) {
                                System.out.println("1?????????/???????????????");
                                message = new Message();
                                bundle.putString("zt1", "3");//?????????????????????
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 100120;
                                mListener.heandleMessage(message);
                            } else if ("0200".equals(str.substring(24, 28))) {
                                System.out.println("2??????????????????");
                                message = new Message();
                                bundle.putString("zt1", "4");//???????????????
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 100120;
                                mListener.heandleMessage(message);
                            } else if ("0300".equals(str.substring(24, 28))) {
                                System.out.println("3????????????");
                                message = new Message();
                                bundle.putString("zt1", "5");//???????????????
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 100120;
                                mListener.heandleMessage(message);
                                //Band???
                                Integer.parseInt(StringPin(str.substring(28, 32)), 16);
                                System.out.println("Band??????" + Integer.parseInt(StringPin(str.substring(28, 32)), 16));
                                //????????????
                                Integer.parseInt(StringPin(str.substring(32, 40)), 16);
                                System.out.println("???????????????" + Integer.parseInt(StringPin(str.substring(32, 40)), 16));
                                //????????????
                                Integer.parseInt(StringPin(str.substring(40, 48)), 16);
                                System.out.println("???????????????" + Integer.parseInt(StringPin(str.substring(40, 48)), 16));
                                DOWNPIN1 = Integer.parseInt(StringPin(str.substring(40, 48)), 16) + "";
                                message = new Message();
                                bundle.putString("down", Integer.parseInt(StringPin(str.substring(40, 48)), 16) + "");//????????????
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 100151;
                                mListener.heandleMessage(message);
                                System.out.println("100151");
                                //??????????????????
                                if ("3436303030".equals(str.substring(48, 58))) {
                                    //??????????????????
                                }
                                if ("3436303031".equals(str.substring(48, 58))) {
                                    //??????????????????
                                }
                                if ("3436303033".equals(str.substring(48, 58)) || "3436303131".equals(str.substring(48, 58))) {
                                    //??????????????????
                                }

                                //PCI
                                Integer.parseInt(StringPin(str.substring(64, 68)), 16);
                                System.out.println("PCI:" + Integer.parseInt(StringPin(str.substring(64, 68)), 16));
                                //TAC
                                Integer.parseInt(StringPin(str.substring(68, 72)), 16);
                                System.out.println("TAC:" + Integer.parseInt(StringPin(str.substring(68, 72)), 16));

                            } else if ("0400".equals(str.substring(24, 28))) {
                                System.out.println("4?????????????????????");
                            }

                        }
                        //????????????
                        if ("5bf0".equals(str.substring(8, 12))) {
                            if ("00000000".equals(str.substring(32, 40))) {
                                System.out.println("?????????????????????70???");
                            }
                            if ("01000000".equals(str.substring(32, 40))) {
                                System.out.println("????????????????????????70????????????");
                            }

                        }
                        //
                        if ("32f0".equals(str.substring(8, 12))) {
                            message = new Message();
                            bundle.putString("zy1", Integer.parseInt(str.substring(24, 26), 16) + "");//????????????
                            message.setData(bundle);
                            handler.sendMessage(message);
                            message.what = 100149;
                            mListener.heandleMessage(message);
                            System.out.println("100149");
                        }
                        if ("14f0".equals(str.substring(8, 12))) {

                        }
                        //????????????????????????
                        if ("14f0".equals(str.substring(8, 12))) {
                            //??????0????????????>0???16????????????????????????????????????
                            int row = Integer.parseInt(str.substring(24, 32), 16);
                            if (row == 0) {
                                System.out.println("?????????????????????!");
                                message = new Message();
                                bundle.putString("zyset1", "?????????????????????");//???????????????
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 100150;
                                mListener.heandleMessage(message);
                                System.out.println("100150");

                            } else {
                                System.err.println("?????????????????????!");
                                message = new Message();
                                bundle.putString("zyset1", "?????????????????????");//???????????????
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 100150;
                                mListener.heandleMessage(message);
                                System.out.println("100150");
                            }
                        }

//                            aaaa
                        //????????????????????????
                        if ("04f0".equals(str.substring(8, 12))) {
                            //??????0????????????>0???16????????????????????????????????????
                            int row = Integer.parseInt(str.substring(24, 32), 16);
                            if (row == 0) {
//                                System.out.println("???????????????");
                            } else {
//                                System.err.println("???????????????");
                            }
                        }
                        //     ????????????????????????
                        if ("0af0".equals(str.substring(8, 12))) {
                            //?????????????????????
                            int row;
                            if ("ffff".equals(str.substring(24, 28))) {
                                row = 0;
                            } else {
                                row = Integer.parseInt(StringPin(str.substring(24, 28)), 16);
                                System.out.println("???????????????" + row);
                            }
                        }
                        if ("7ef0".equals(str.substring(8, 12))) {
                            int row = Integer.parseInt(str.substring(24, 32), 16);
                            if (row == 0) {
                                System.out.println("SNF???????????????????????????");

                                message = new Message();
                                bundle.putString("snf", "SNF????????????????????????");//???????????????
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 100154;
                                mListener.heandleMessage(message);
                                System.out.println("100154");

                            } else {
                                System.err.println("SNF???????????????????????????");
                                message = new Message();
                                bundle.putString("snf", "SNF????????????????????????");//???????????????
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
                            //???????????????
                            String length = str.substring(12, 16);
                            String len = StringPin(length);
                            Integer strlen = Integer.parseInt(len, 16);
                            //???????????????
                            Integer.parseInt(StringPin(str.substring(20, 24)), 16);
                            String code = Integer.toBinaryString(Integer.parseInt(StringPin(str.substring(20, 24)), 16));

                            code = StringAd(code);
                            System.out.println("?????????2F--" + str3);
                            System.err.println("code:" + code);
                            String s = str.substring(24, strlen * 2);
                            System.out.println("ss___" + s);
                            str3 = str3 + s;
                            System.err.println("s3___" + str3);
                            if ("0".equals(code.substring(0, 1))) {

                                str3 = "aaaa55550af0240000ff0000" + str3;
                                System.err.println("str3:+++++++++++++++" + str3);
                                //?????????????????????
                                int row;
                                if ("ffff".equals(str3.substring(24, 28))) {
                                    row = 0;
                                } else {
                                    row = Integer.parseInt(StringPin(str3.substring(24, 28)), 16);
                                    System.out.println("???????????????" + row);
                                }
                                System.out.println("?????????F--" + str3);
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
                                    //????????????
                                    SpBean spBean = new SpBean();
                                    System.out.println(str3.substring(dlEarfcnBegin, dlEarfcnEnd));
                                    if ("ffffffff".equals(str3.substring(dlEarfcnBegin, dlEarfcnEnd))) {
                                        System.out.println("null");
//                                            continue;
                                    } else {
                                        System.out.println("???????????????------" + Integer.parseInt(StringPin(str3.substring(dlEarfcnBegin, dlEarfcnEnd)), 16));
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
//                                    System.out.println("PCI???------" + Integer.parseInt(StringPin(str3.substring(pciBegin, pciEnd)), 16));
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
//                                    System.out.println("TAC???------" + Integer.parseInt(StringPin(str3.substring(tacBegin, tacEnd)), 16));
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
//                                    System.out.println(Integer.parseInt(StringPin(str3.substring(plmnBegin, plmnEnd)), 16) + "---PLMN???");

                                    if ("ffff".equals(str3.substring(plmnBegin, plmnEnd))) {
                                        Log.d("1nzqffffffff", "run:1 ");
                                        spBean.setPlmn(0 + "");
                                    } else {
                                        spBean.setPlmn(Integer.parseInt(StringPin(str3.substring(plmnBegin, plmnEnd)), 16) + "");
                                        Log.d("2nzqffffffff", "run:1 ");
                                    }


//                                    spBean.setPlmn("0");
                                    //CellId
                                    System.out.println("ffffffff".equals(str3.substring(celldBegin, celldEnd)) ? "null" : Integer.parseInt(StringPin(str3.substring(celldBegin, celldEnd)), 16) + "------CellId???");


                                    if ("ffffffff".equals(str3.substring(celldBegin, celldEnd))) {
                                        Log.d("1nzqffffffff", "run:1 ");
                                        spBean.setCid(0 + "");
                                    } else {
                                        spBean.setCid(Integer.parseInt(StringPin(str3.substring(celldBegin, celldEnd)), 16) + "");
                                        Log.d("2nzqffffffff", "run:1 ");
                                    }
                                    //Priority ????????????????????????
                                    System.out.println(str3.substring(64, 72));
                                    System.out.println("ffffffff".equals(str3.substring(priorityBegin, priorityEnd)) ? "Priority_null" : Integer.parseInt(StringPin(str3.substring(priorityBegin, priorityEnd)), 16) + "------Priority ???????????????????????????");
                                    if ("ffffffff".equals(str3.substring(priorityBegin, priorityEnd))) {
                                        Log.d("1nzqffffffff", "run:1 ");
                                        spBean.setPriority(0);
                                    } else {
                                        spBean.setPriority(Integer.parseInt(StringPin(str3.substring(priorityBegin, priorityEnd)), 16));
                                        Log.d("2nzqffffffff", "run:1 ");
                                    }
                                    //RSRP
//                                    System.out.println("RSRP???------" + Integer.parseInt(StringPin(str3.substring(rsrpBegin, rsrpEnd)), 16));

                                    if ("ffffffff".equals(str3.substring(rsrpBegin, rsrpEnd))) {
                                        Log.d("1nzqffffffff", "run:1 ");
                                        spBean.setRsrp(0);
                                    } else {
                                        spBean.setRsrp(Integer.parseInt(StringPin(str3.substring(rsrpBegin, rsrpEnd)), 16));
                                        Log.d("2nzqffffffff", "run:1 ");
                                    }
//                                    spBean.setRsrp(Integer.parseInt(StringPin(str3.substring(rsrpBegin, rsrpEnd)), 16));
                                    //RSRQ
                                    System.out.println("RSRQ???------" + Integer.parseInt(StringPin(str3.substring(rsrqBegin, rsrqEnd)), 16));
//                                    spBean.setRsrq(Integer.parseInt(StringPin(str3.substring(rsrqBegin, rsrqEnd)), 16));

                                    if ("ffffffff".equals(str3.substring(rsrqBegin, rsrqEnd))) {
                                        Log.d("1nzqffffffff", "run:1 ");
                                        spBean.setRsrq(0);
                                    } else {
                                        spBean.setRsrq(Integer.parseInt(StringPin(str3.substring(rsrqBegin, rsrqEnd)), 16));
                                        Log.d("2nzqffffffff", "run:1 ");
                                    }
                                    //Bandwidth??????????????????
                                    System.out.println("Bandwidth???------" + Integer.parseInt(StringPin(str3.substring(bandWidthBegin, bandWidthEnd)), 16));
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
                                        spBeanList.add(spBean);//????????????add
                                    }
                                    //TddSpecialSf Patterns TDD??????????????????
                                    System.out.println("TDD?????????????????????------" + Integer.parseInt(StringPin(str3.substring(tddSpecialSfBegin, tddSpecialSfEnd)), 16));
                                    //??????????????????
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
                                    System.out.println("?????????????????????------" + InterFreqLstNum);

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
                                        //???????????????????????????
//                                            System.out.println(str3.substring(interFreqNghNumBegin, interFreqNghNumEnd));
//                                            System.out.println("pin:" + StringPin(str3.substring(interFreqNghNumBegin, interFreqNghNumEnd)));
//                                            System.out.println(Integer.parseInt(StringPin(str3.substring(interFreqNghNumBegin, interFreqNghNumEnd)), 16));
//                                            int interFreqNghNum = Integer.parseInt(StringPin(str3.substring(interFreqNghNumBegin, interFreqNghNumEnd)), 16);
//                                            System.out.println("??????????????????????????????" + interFreqNghNum);
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
//                                                System.out.println("??????????????????????????????" + interFreqNghNum);
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
                                Log.d("nzqrun77", "nzqrun: " + "??????");
                                if (spBeanList.size() == 0) {
//                                    Log.d(TAG, "nzqrunrun: " + "??????0");
                                } else {
//                                    Log.d(TAG, "nzqrunrun: " + "??????0");
                                    SPBEANLIST1 = spBeanList;
                                    Log.d("nzqspBeanList1", "" + spBeanList);
//                                spBeanList.sort(Comparator.comparing(SpBean::getPriority));
                                    //??????????????????,???????????????????????????RSRP
                                    List<Integer> list = new ArrayList();
                                    String down1 = "";
                                    SpBean spBean1 = new SpBean();
                                    SpBean spBean2 = new SpBean();
                                    if (spBeanList.size() >= 2) {
                                        for (int i = 0; i < spBeanList.size(); i++) {
                                            list.add(spBeanList.get(i).getPriority());
                                        }
                                        Integer max = Collections.max(list);
                                        Log.d("Anzqmax", "??????2???run: " + max);
                                        list.remove(max);//???????????????

                                        for (int i = 0; i < spBeanList.size(); i++) {
                                            if (max.equals(spBeanList.get(i).getPriority())) {
                                                down1 = spBeanList.get(i).getDown();
                                                spBean1 = spBeanList.get(i);
                                            }
                                        }
                                        //???????????????
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

                                            if (!down1.equals(down2)) {//???????????????????????? ????????????????????????
                                                message = new Message();
                                                bundle.putString("sp1MAX1value", down1);//??????
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
                                                Log.d("Anzqmax", "??????2???run???????????????????????????????????????: " + max);
                                            } else {
                                                message = new Message();
                                                bundle.putString("sp1MAX1value", down1);//??????
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
                                                Log.d("Anzqmax", "??????2???run??????????????????????????????????????????: " + down1 + "--" + down2);
                                            }


                                        } else {//???????????????????????????  ,??????rsrp??????

                                            int rssp1;
                                            int rssp2;
                                            List<Integer> list1rsp = new ArrayList<>();
                                            for (int i = 0; i < spBeanList.size(); i++) {
                                                list1rsp.add(spBeanList.get(i).getRsrp());
                                            }
                                            //?????????rsrp
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
                                            //????????????rsrp
                                            rssp2 = Collections.max(list1rsp);
                                            for (int i = 0; i < spBeanList.size(); i++) {
                                                if (rssp2 == spBeanList.get(i).getRsrp()) {
                                                    down2 = spBeanList.get(i).getDown();
                                                    spBean2 = spBeanList.get(i);
                                                }
                                            }
                                            if (down1.equals(down2)) {
                                                message = new Message();
                                                bundle.putString("sp1MAX1value", down1);//??????
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
                                                Log.d("Anzqmax", "??????2???run????????????????????????RSRP??????????????????????????????????????????: " + down1 + "--" + down2);
                                            } else {
                                                message = new Message();
                                                bundle.putString("sp1MAX1value", down1);//??????
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
                                                Log.d("Anzqmax", "??????2???run????????????????????????RSRP????????????????????????: " + down1 + "--" + down2);
                                            }


//                                            ToastUtils.showToast("?????????????????????");
//                                        Log.d("Anzqmax", "??????2???run????????????????????????RSRP??????: "+down1+"--"+down2 );
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
//                                            ToastUtils.showToast("???????????????1");
                                            Log.d("Anzqmax", "???????????????1: ");
                                        } else {
                                            message = new Message();
                                            bundle.putString("sp1MAX1value", "");
                                            bundle.putString("sp1MAX2value", "");
                                            message.setData(bundle);
                                            handler.sendMessage(message);
                                            message.what = 100152;
                                            mListener.heandleMessage(message);
//                                            ToastUtils.showToast("???????????????0");
                                            Log.d("Anzqmax", "???????????????0: ");
                                        }
                                    }
                                }


                            }

                        }


//                            //???
//                            if ("0af0".equals(str.substring(8, 12))) {
//                                i++;
//                                System.out.println("???????????????"+str);
////                                List<SpBean> spBeanList = new ArrayList<>();
//                                //???????????????
//                                String length = str.substring(12, 16);
//                                String len = StringPin(length);
//                                Integer strlen = Integer.parseInt(len, 16);
//                                //???????????????
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
//                                    //?????????????????????
//                                   System.out.println("?????????F--"+str3);
//                                    int row;
//                                    if ("ffff".equals(str3.substring(24, 28))) {
//                                        row = 0;
//                                    } else {
//                                        row = Integer.parseInt(StringPin(str3.substring(24, 28)), 16);
//                                        System.out.println("???????????????" + row);
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
//                                        //????????????
//                                        System.out.println(str3.substring(dlEarfcnBegin, dlEarfcnEnd));
//                                        if ("ffffffff".equals(str3.substring(dlEarfcnBegin, dlEarfcnEnd))) {
//                                            System.out.println("null");
//                                        } else {
//                                            System.out.println("???????????????------" + Integer.parseInt(StringPin(str3.substring(dlEarfcnBegin, dlEarfcnEnd)), 16));
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
//                                        System.out.println("PCI???------" + Integer.parseInt(StringPin(str3.substring(pciBegin, pciEnd)), 16));
//                                        spBean.setPci(Integer.parseInt(StringPin(str3.substring(pciBegin, pciEnd)), 16));
//                                        System.out.println(dlEarfcnBegin + "+" + dlEarfcnEnd);
//
//                                        //TAC
//                                        System.out.println("TAC???------" + Integer.parseInt(StringPin(str3.substring(tacBegin, tacEnd)), 16));
//                                        spBean.setTac(Integer.parseInt(StringPin(str3.substring(tacBegin, tacEnd)), 16));
//                                        //PLMN
//                                        System.out.println(Integer.parseInt(StringPin(str3.substring(plmnBegin, plmnEnd)), 16) + "---PLMN???");
//                                        spBean.setPlmn(Integer.parseInt(StringPin(str3.substring(plmnBegin, plmnEnd)), 16) + "");
//                                        //CellId
//                                        System.out.println("ffffffff".equals(str3.substring(celldBegin, celldEnd)) ? "null" : Integer.parseInt(StringPin(str3.substring(celldBegin, celldEnd)), 16) + "------CellId???");
//                                        //Priority ????????????????????????
//                                        System.out.println(str3.substring(64, 72));
//                                        System.out.println("ffffffff".equals(str3.substring(priorityBegin, priorityEnd)) ? "Priority_null" : Integer.parseInt(StringPin(str3.substring(priorityBegin, priorityEnd)), 16) + "------Priority ???????????????????????????");
//                                        if ("ffffffff".equals(str3.substring(priorityBegin, priorityEnd))) {
//                                            Log.d("1nzqffffffff", "run:1 ");
//                                            spBean.setPriority(0);
//                                        } else {
//                                            spBean.setPriority(Integer.parseInt(StringPin(str3.substring(priorityBegin, priorityEnd)), 16));
//                                            Log.d("2nzqffffffff", "run:1 ");
//                                        }
//                                        //RSRP
//                                        System.out.println("RSRP???------" + Integer.parseInt(StringPin(str3.substring(rsrpBegin, rsrpEnd)), 16));
//                                        spBean.setRsrp(Integer.parseInt(StringPin(str3.substring(rsrqBegin, rsrqEnd)), 16));
//
//                                        //RSRQ
//                                        System.out.println("RSRQ???------" + Integer.parseInt(StringPin(str3.substring(rsrqBegin, rsrqEnd)), 16));
//                                        spBean.setRsrp(Integer.parseInt(StringPin(str.substring(rsrpBegin, rsrpEnd)), 16));
//                                        //Bandwidth??????????????????
//                                        System.out.println("Bandwidth???------" + Integer.parseInt(StringPin(str3.substring(bandWidthBegin, bandWidthEnd)), 16));
//                                        spBean.setBand(Integer.parseInt(StringPin(str3.substring(bandWidthBegin, bandWidthEnd)), 16) + "");
//                                        spBeanList.add(spBean);
//                                        //TddSpecialSf Patterns TDD??????????????????
//                                        System.out.println("TDD?????????????????????------" + Integer.parseInt(StringPin(str3.substring(tddSpecialSfBegin, tddSpecialSfEnd)), 16));
//                                        //??????????????????
//                                        int InterFreqLstNum;
//                                        if ("ffffffff".equals(str3.substring(interFreqLstNumBegin, interFreqLstNumEnd))) {
//                                            InterFreqLstNum = 0;
//                                        } else {
//                                            InterFreqLstNum = Integer.parseInt(StringPin(str3.substring(interFreqLstNumBegin, interFreqLstNumEnd)), 16);
//                                        }
//                                        System.out.println(interFreqLstNumBegin + "---" + interFreqLstNumEnd);
//                                        System.out.println("?????????????????????------" + InterFreqLstNum);
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
//                                            //???????????????????????????
//                                            System.out.println(str3.substring(interFreqNghNumBegin, interFreqNghNumEnd));
//                                            System.out.println("pin:" + StringPin(str3.substring(interFreqNghNumBegin, interFreqNghNumEnd)));
//                                            System.out.println(Integer.parseInt(StringPin(str3.substring(interFreqNghNumBegin, interFreqNghNumEnd)), 16));
//                                            int interFreqNghNum = Integer.parseInt(StringPin(str3.substring(interFreqNghNumBegin, interFreqNghNumEnd)), 16);
//                                            System.out.println("??????????????????????????????" + interFreqNghNum);
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
//                                        //??????????????????,???????????????????????????RSRP
//                                        List<Integer> list = new ArrayList();
//                                        String down1 = "";
//                                        SpBean spBean1 = new SpBean();
//                                        SpBean spBean2 = new SpBean();
//                                        if (spBeanList.size() >= 2) {
//                                            for (int i = 0; i < spBeanList.size(); i++) {
//                                                list.add(spBeanList.get(i).getPriority());
//                                            }
//                                            Integer max = Collections.max(list);
//                                            Log.d("Anzqmax", "??????2???run: " + max);
//                                            list.remove(max);//???????????????
//
//                                            for (int i = 0; i < spBeanList.size(); i++) {
//                                                if (max.equals(spBeanList.get(i).getPriority())) {
//                                                    down1 = spBeanList.get(i).getDown();
//                                                    spBean1 = spBeanList.get(i);
//                                                }
//                                            }
//                                            //???????????????
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
//                                                if (!down1.equals(down2)) {//???????????????????????? ????????????????????????
//                                                    message = new Message();
//                                                    bundle.putString("sp1MAX1value", down1);//??????
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
//                                                    Log.d("Anzqmax", "??????2???run???????????????????????????????????????: " + max);
//                                                } else {
//                                                    message = new Message();
//                                                    bundle.putString("sp1MAX1value", down1);//??????
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
//                                                    Log.d("Anzqmax", "??????2???run??????????????????????????????????????????: " + down1 + "--" + down2);
//                                                }
//
//
//                                            } else {//???????????????????????????  ,??????rsrp??????
//
//                                                int rssp1;
//                                                int rssp2;
//                                                List<Integer> list1rsp = new ArrayList<>();
//                                                for (int i = 0; i < spBeanList.size(); i++) {
//                                                    list1rsp.add(spBeanList.get(i).getRsrp());
//                                                }
//                                                //?????????rsrp
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
//                                                //????????????rsrp
//                                                rssp2 = Collections.max(list1rsp);
//                                                for (int i = 0; i < spBeanList.size(); i++) {
//                                                    if (rssp2 == spBeanList.get(i).getRsrp()) {
//                                                        down2 = spBeanList.get(i).getDown();
//                                                        spBean2 = spBeanList.get(i);
//                                                    }
//                                                }
//                                                if (down1.equals(down2)) {
//                                                    message = new Message();
//                                                    bundle.putString("sp1MAX1value", down1);//??????
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
//                                                    Log.d("Anzqmax", "??????2???run????????????????????????RSRP??????????????????????????????????????????: " + down1 + "--" + down2);
//                                                } else {
//                                                    message = new Message();
//                                                    bundle.putString("sp1MAX1value", down1);//??????
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
//                                                    Log.d("Anzqmax", "??????2???run????????????????????????RSRP????????????????????????: " + down1 + "--" + down2);
//                                                }
//
//
//                                                ToastUtils.showToast("?????????????????????");
////                                        Log.d("Anzqmax", "??????2???run????????????????????????RSRP??????: "+down1+"--"+down2 );
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
//                                                ToastUtils.showToast("???????????????1");
//                                                Log.d("Anzqmax", "???????????????1: ");
//                                            } else {
//                                                message = new Message();
//                                                bundle.putString("sp1MAX1value", "");
//                                                bundle.putString("sp1MAX2value", "");
//                                                message.setData(bundle);
//                                                handler.sendMessage(message);
//                                                message.what = 100152;
//                                                ToastUtils.showToast("???????????????0");
//                                                Log.d("Anzqmax", "???????????????0: ");
//                                            }
//                                        }
//                                    }
//
//                                }
//
//                            }


//                            //????????????????????????  ???
//                            if ("0af0".equals(str.substring(8, 12))) {
//                                //?????????????????????
//                                int row;
//                                if ("ffff".equals(str.substring(24, 28))) {
//                                    row = 0;
//                                } else {
//                                    row = Integer.parseInt(StringPin(str.substring(24, 28)), 16);
//                                    System.out.println("???????????????" + row);
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
//                                    //????????????
//                                    System.out.println(str.substring(dlEarfcnBegin, dlEarfcnEnd));
//
//                                    if ("ffffffff".equals(str.substring(dlEarfcnBegin, dlEarfcnEnd))) {
//                                        System.out.println("null");
//                                        spBean.setDown("null");
//                                    } else {
//                                        System.out.println("???????????????------" + Integer.parseInt(StringPin(str.substring(dlEarfcnBegin, dlEarfcnEnd)), 16));
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
//                                    System.out.println("PCI???------" + Integer.parseInt(StringPin(str.substring(pciBegin, pciEnd)), 16));
//                                    spBean.setPci(Integer.parseInt(StringPin(str.substring(pciBegin, pciEnd)), 16));
//                                    System.out.println(dlEarfcnBegin + "+" + dlEarfcnEnd);
//
//                                    //TAC
//                                    System.out.println("TAC???------" + Integer.parseInt(StringPin(str.substring(tacBegin, tacEnd)), 16));
//                                    spBean.setTac(Integer.parseInt(StringPin(str.substring(tacBegin, tacEnd)), 16));
//                                    //PLMN
//                                    System.out.println(Integer.parseInt(StringPin(str.substring(plmnBegin, plmnEnd)), 16) + "---PLMN???");
//
//                                    spBean.setPlmn(Integer.parseInt(StringPin(str.substring(plmnBegin, plmnEnd)), 16) + "");
//                                    //CellId
//                                    System.out.println("ffffffff".equals(str.substring(celldBegin, celldEnd)) ? "null" : Integer.parseInt(StringPin(str.substring(celldBegin, celldEnd)), 16) + "------CellId???");
//                                    //Priority ????????????????????????
//                                    System.out.println("?????????--" + str.substring(64, 72));
//                                    System.out.println("ffffffff".equals(str.substring(priorityBegin, priorityEnd)) ? "null" : Integer.parseInt(StringPin(str.substring(priorityBegin, priorityEnd)), 16) + "------Priority ???????????????????????????");
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
//                                    System.out.println("RSRP???------" + Integer.parseInt(StringPin(str.substring(rsrpBegin, rsrpEnd)), 16));
//                                    //RSRQ
//                                    System.out.println("RSRQ???------" + Integer.parseInt(StringPin(str.substring(rsrqBegin, rsrqEnd)), 16));
//                                    spBean.setRsrp(Integer.parseInt(StringPin(str.substring(rsrqBegin, rsrqEnd)), 16));
//
//                                    if (spBean.getDown().equals("0")) {
//
//                                    } else {
//                                        spBeanList.add(spBean);//????????????add
//                                    }
//
//                                    //Bandwidth??????????????????
//                                    System.out.println("Bandwidth???------" + Integer.parseInt(StringPin(str.substring(bandWidthBegin, bandWidthEnd)), 16));
//                                    spBean.setBand(Integer.parseInt(StringPin(str.substring(bandWidthBegin, bandWidthEnd)), 16) + "");
//                                    //TddSpecialSf Patterns TDD??????????????????
//                                    System.out.println("TDD?????????????????????------" + Integer.parseInt(StringPin(str.substring(tddSpecialSfBegin, tddSpecialSfEnd)), 16));
//                                    //??????????????????
//                                    int InterFreqLstNum;
//                                    if ("ffffffff".equals(str.substring(interFreqLstNumBegin, interFreqLstNumEnd))) {
//                                        InterFreqLstNum = 0;
//                                    } else {
//                                        InterFreqLstNum = Integer.parseInt(StringPin(str.substring(interFreqLstNumBegin, interFreqLstNumEnd)), 16);
//                                    }
//                                    System.out.println(interFreqLstNumBegin + "---" + interFreqLstNumEnd);
//                                    System.out.println("?????????????????????------" + InterFreqLstNum);
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
////                                        //???????????????????????????
////                                        System.out.println(str.substring(interFreqNghNumBegin, interFreqNghNumEnd));
////                                        System.out.println("pin:" + StringPin(str.substring(interFreqNghNumBegin, interFreqNghNumEnd)));
////                                        System.out.println(Integer.parseInt(StringPin(str.substring(interFreqNghNumBegin, interFreqNghNumEnd)), 16));
////                                        int interFreqNghNum = Integer.parseInt(StringPin(str.substring(interFreqNghNumBegin, interFreqNghNumEnd)), 16);
////                                        System.out.println("??????????????????????????????" + interFreqNghNum);
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
//                                //????????????37900
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
//                                    //??????????????????,???????????????????????????RSRP
//                                    List<Integer> list = new ArrayList();
//                                    String down1 = "";
//                                    SpBean spBean1 = new SpBean();
//                                    SpBean spBean2 = new SpBean();
//                                    if (spBeanList.size() >= 2) {
//                                        for (int i = 0; i < spBeanList.size(); i++) {
//                                            list.add(spBeanList.get(i).getPriority());
//                                        }
//                                        Integer max = Collections.max(list);
//                                        Log.d("Anzqmax", "??????2???run: " + max);
//                                        list.remove(max);//???????????????
//
//                                        for (int i = 0; i < spBeanList.size(); i++) {
//                                            if (max.equals(spBeanList.get(i).getPriority())) {
//                                                down1 = spBeanList.get(i).getDown();
//                                                spBean1 = spBeanList.get(i);
//                                            }
//                                        }
//                                        //???????????????
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
//                                            if (!down1.equals(down2)) {//???????????????????????? ????????????????????????
//                                                message = new Message();
//                                                bundle.putString("sp1MAX1value", down1);//??????
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
//                                                Log.d("Anzqmax", "??????2???run???????????????????????????????????????: " + max);
//                                            } else {
//                                                message = new Message();
//                                                bundle.putString("sp1MAX1value", down1);//??????
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
//                                                Log.d("Anzqmax", "??????2???run??????????????????????????????????????????: " + down1 + "--" + down2);
//                                            }
//
//
//                                        } else {//???????????????????????????  ,??????rsrp??????
//
//                                            int rssp1;
//                                            int rssp2;
//                                            List<Integer> list1rsp = new ArrayList<>();
//                                            for (int i = 0; i < spBeanList.size(); i++) {
//                                                list1rsp.add(spBeanList.get(i).getRsrp());
//                                            }
//                                            //?????????rsrp
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
//                                            //????????????rsrp
//                                            rssp2 = Collections.max(list1rsp);
//                                            for (int i = 0; i < spBeanList.size(); i++) {
//                                                if (rssp2 == spBeanList.get(i).getRsrp()) {
//                                                    down2 = spBeanList.get(i).getDown();
//                                                    spBean2 = spBeanList.get(i);
//                                                }
//                                            }
//                                            if (down1.equals(down2)) {
//                                                message = new Message();
//                                                bundle.putString("sp1MAX1value", down1);//??????
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
//                                                Log.d("Anzqmax", "??????2???run????????????????????????RSRP??????????????????????????????????????????: " + down1 + "--" + down2);
//                                            } else {
//                                                message = new Message();
//                                                bundle.putString("sp1MAX1value", down1);//??????
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
//                                                Log.d("Anzqmax", "??????2???run????????????????????????RSRP????????????????????????: " + down1 + "--" + down2);
//                                            }
//
//
//                                            ToastUtils.showToast("?????????????????????");
////                                        Log.d("Anzqmax", "??????2???run????????????????????????RSRP??????: "+down1+"--"+down2 );
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
//                                            ToastUtils.showToast("???????????????1");
//                                            Log.d("Anzqmax", "???????????????1: ");
//                                        } else {
//                                            message = new Message();
//                                            bundle.putString("sp1MAX1value", "");
//                                            bundle.putString("sp1MAX2value", "");
//                                            message.setData(bundle);
//                                            handler.sendMessage(message);
//                                            message.what = 100152;
//                                            ToastUtils.showToast("???????????????0");
//                                            Log.d("Anzqmax", "???????????????0: ");
//                                        }
//                                    }
//                                }
//
////                                } else if (spBeanList.size() > 0 && spBeanList.size() == 1) {//??????????????????
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
////                                        ToastUtils.showToast("???????????????1");
////                                        Log.d("Anzqmax", "???????????????1: ");
////                                    }
////                                } else { //???????????????1 ????????????2
////                                    message = new Message();
////                                    bundle.putString("sp1MAX1value", "");
////                                    bundle.putString("sp1MAX2value", "");
////                                    message.setData(bundle);
////                                    handler.sendMessage(message);
////                                    message.what = 100152;
////                                    ToastUtils.showToast("???????????????0");
////                                    Log.d("Anzqmax", "???????????????0: ");
////                                }
//                            }

                    }

                    //??????2
                    if (IP2.equals(dp.getAddress().getHostAddress())) {
                        mPressedTime2 = System.currentTimeMillis();
                        System.out.println("123456");
                        System.out.println("??????2A" + dp.getAddress().getHostAddress() + "???????????????" + str);
                        //??????
                        Date d = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        System.out.println("?????????" + sdf.format(d));

                        if ("2cf0".equals(str.substring(8, 12))) {

                            if ("00000000".equals(str.substring(24, 32))) {
                                System.out.println("???????????????" + hexStringToString(str.substring(32, 46)));
                                DEVICENUMBER2 = hexStringToString(str.substring(32, 46));
                            } else if ("01000000".equals(str.substring(24, 32))) {
                                System.out.println("???????????????" + hexStringToString(str.substring(32, 34)));
                                HARDWAREVERSION2 = hexStringToString(str.substring(32, 34));
                            } else if ("02000000".equals(str.substring(24, 32))) {
                                System.out.println("???????????????" + hexStringToString(str.substring(32, 106)));
                                SOFTWAREVERSION2 = hexStringToString(str.substring(32, 106));
                            } else if ("03000000".equals(str.substring(24, 32))) {
                                System.out.println("?????????SN:" + hexStringToString(str.substring(32, 70)));
                                SNNUMBER2 = hexStringToString(str.substring(32, 70));
                            } else if ("04000000".equals(str.substring(24, 32))) {
                                System.out.println("MAC?????????" + hexStringToString(str.substring(32, 66)));
                                MACADDRESS2 = hexStringToString(str.substring(32, 66));
                            } else if ("05000000".equals(str.substring(24, 32))) {
                                UBOOTVERSION2 = hexStringToString(str.substring(32, 47));
                                System.out.println("uboot????????????" + hexStringToString(str.substring(32, 47)));
                            } else if ("06000000".equals(str.substring(24, 32))) {
                                System.out.println("???????????????" + hexStringToString(str.substring(32, 50)));
                                BOARDTEMPERATURE2 = hexStringToString(str.substring(32, 50));


                            }
                        }
                        if ("2ef0".equals(str.substring(8, 12))) {
                            Log.d("nzq2df0", str.toString());
                            System.out.println("????????????" + str.toString());
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

                        //??????????????????????????????
                        if ("28f0".equals(str.substring(8, 12))) {
                            //PLMN
                            System.out.println(StringTOIMEI(str.substring(40, 50)));
                            PLMN2 = StringTOIMEI(str.substring(40, 50));
                            //????????????
                            System.out.println(Integer.parseInt(StringPin(str.substring(24, 32)), 16));
                            UP2 = Integer.parseInt(StringPin(str.substring(24, 32)), 16) + "";
                            //????????????
                            System.out.println(Integer.parseInt(StringPin(str.substring(32, 40)), 16));
                            DWON2 = Integer.parseInt(StringPin(str.substring(32, 40)), 16) + "";
                            //Band
                            System.out.println(Integer.parseInt(StringPin(str.substring(56, 64)), 16));
                            BAND2 = Integer.parseInt(StringPin(str.substring(56, 64)), 16) + "";
                            //??????
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
                        //???????????????????????????????????????
                        if ("30f0".equals(str.substring(8, 12))) {
                            if ("00000000".equals(str.substring(24, 32))) {
                                System.out.println("???????????????");
                                TYPE2 = "???????????????";
                            } else if ("01000000".equals(str.substring(24, 32))) {
                                System.out.println("??????????????????");
                                TYPE2 = "??????????????????";
                            } else if ("02000000".equals(str.substring(24, 32))) {
                                System.out.println("???????????????");
                                TYPE2 = "???????????????";
                            } else if ("03000000".equals(str.substring(24, 32))) {
                                System.out.println("???????????????");
                                TYPE2 = "???????????????";
                            } else if ("04000000".equals(str.substring(24, 32))) {
                                System.out.println("??????????????????");
                                TYPE2 = "??????????????????";
                            }
                        }
                        //UE??????????????????????????????
                        if ("3ef0".equals(str.substring(8, 12))) {
                            //????????????
                            if ("00".equals(str.substring(24, 26))) {
                                System.out.println("??????????????????");
                                GZMS2 = "??????????????????";
                            } else if ("01".equals(str.substring(24, 26))) {
                                System.out.println("??????????????????");
                                //??????????????????????????????????????????IMSI ???????????????
                                System.out.println(Integer.parseInt(StringPin(str.substring(28, 32)), 16));
                                ZHZQ2 = "" + Integer.parseInt(StringPin(str.substring(28, 32)), 16);
                                GZMS2 = "??????????????????";
                            } else if ("02".equals(str.substring(24, 26))) {
                                System.out.println("????????????");
                                GZMS2 = "????????????";
                                //?????????????????????????????? IMSI
                                UEIMS2 = StringTOIMEI(str.substring(32, 62));
                                System.out.println("imsi:" + StringTOIMEI(str.substring(32, 62)));
                                //?????????????????????????????????????????????,??????????????? 1024ms, 0???120ms,1???240ms,2???480ms,3???640ms,4???1024ms,5???2048ms

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
                                //??????????????????????????????????????????????????????????????????????????? 0,0???enable,1???disable
                                if ("00".equals(str.substring(68, 70))) {
                                    System.out.println("enable");
                                    UEMAXOF2 = "???";
                                } else if ("01".equals(str.substring(68, 70))) {
                                    System.out.println("disable");
                                    UEMAXOF2 = "???";
                                }
                                //???????????????UE ?????????????????????????????? ??? ??? ???wrFLLmtToEnbSysArfcnCfg ?????????UePMax?????????????????? 23dBm
                                UEMAX2 = Integer.parseInt(str.substring(70, 72), 16) + "";
                                System.out.println(Integer.parseInt(str.substring(70, 72), 16));

                                //???????????????????????????????????? SRS ????????????????????????????????????????????????disable??????????????????????????????????????????????????????0: disable,1: enable
                                if ("00".equals(str.substring(72, 74))) {
                                    System.out.println("disable");
                                } else if ("01".equals(str.substring(72, 74))) {
                                    System.out.println("enable");
                                }
                                //?????????????????????????????????????????????????????????????????????????????? 0???????????? 1 ??????????????????????????????????????????????????????????????????????????????1??????????????????????????????????????????, 0?????????????????????????????????
                                if ("00".equals(str.substring(76, 78))) {
                                    System.out.println("??????????????????????????????");
                                } else if ("01".equals(str.substring(76, 78))) {
                                    System.out.println("???????????????????????????????????????");
                                }
                                //??????????????????????????????????????????SRS ?????????
                                if ("00".equals(str.substring(78, 80))) {
                                    System.out.println("disable");
                                } else if ("01".equals(str.substring(78, 80))) {
                                    System.out.println("enable");
                                }
                            } else if ("03".equals(str.substring(24, 26))) {
                                System.out.println("????????????");
                                GZMS2 = "????????????";
                                //????????????????????????0????????????????????????1?????????????????????
                                if ("00".equals(str.substring(80, 82))) {
                                    System.out.println("??????????????????");
                                    GZMS2 = "??????????????????";
                                } else if ("01".equals(str.substring(80, 82))) {
                                    System.out.println("??????????????????");
                                    GZMS2 = "??????????????????";
                                }
                            } else if ("04".equals(str.substring(24, 26))) {
                                System.out.println("???????????????");
                                GZMS2 = "???????????????";
                            /*0: ??????????????????????????????????????????????????????????????????
                            1: ????????????????????????????????????????????????????????????
							2: ?????????????????????????????????????????????????????????????????????
							3: ?????????????????????????????????;???????????????????????????
							4: ?????????????????????*/
                                System.out.println(Integer.parseInt(str.substring(26, 28), 16));

                            }

                        }

                        if ("56f0".equals(str.substring(8, 12))) {

                            //???????????????
                            Integer blacklistNum = Integer.parseInt(StringPin(str.substring(24, 28)), 16);
                            int begin = 28;
                            int end = 58;
                            for (int i = 0; i < blacklistNum; i++) {
                                System.out.println("???????????????2" + StringTOIMEI(str.substring(begin, end)));
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
                        //??????????????????????????????????????????
                        if ("32f0".equals(str.substring(8, 12))) {
                            //??????
                            //??????????????????????????????????????????FDD ??????????????????????????????????????????????????????
                            System.out.println(Integer.parseInt(str.substring(24, 26), 16));
                            ZENGYI2 = Integer.parseInt(str.substring(24, 26), 16) + "";
                            //????????????  ??????
                            //??????????????????????????????????????????FDD ??????????????????????????????????????????????????????
                            System.out.println(Integer.parseInt(str.substring(28, 30), 16));
                            SHUAIJIAN2 = Integer.parseInt(str.substring(28, 30), 16) + "";

//                            //???????????????????????????????????????????????????,
//                            System.out.println(Integer.parseInt(str.substring(26,28),16));
//
//                            //???????????????????????????????????????????????????
//                            System.out.println(Integer.parseInt(str.substring(30,32),16));
//                            //FDD AGC ??????
//                            if("00".equals(str.substring(30,32))){
//                                System.out.println("FDD AGC ????????????");
//                            }else if("01".equals(str.substring(30,32))){
//                                System.out.println("FDD AGC ????????????");
//                            }

                            //??????
                            //??????FDD?????????????????????????????????????????????????????????,????????????????????????????????????????????????????????????
//                            System.out.println(Integer.parseInt(str.substring(34,36),16));
//                            //eeprom ??????????????????????????????????????????
//                            System.out.println(Integer.parseInt(str.substring(36,38),16));
                        }
                        //????????????????????? ????????????
                        if ("54f0".equals(str.substring(8, 12))) {
                            if ("00000000".equals(str.substring(24, 32))) {
                                opionts11++;
                                System.out.println("???????????????????????????" + opionts1);
                                if (opionts11 % 2 == 0) {
                                    Log.d("jsgs", "run:??????");
                                    message = new Message();
                                    bundle.putString("200130", "FDD");
                                    message.setData(bundle);
                                    handler.sendMessage(message);
                                    message.what = 200131;
                                    mListener.heandleMessage(message);
                                } else {
                                    Log.d("jsgs", "run:??????");
                                    message = new Message();
                                    bundle.putString("200131", "FDD");
                                    message.setData(bundle);
                                    handler.sendMessage(message);
                                    message.what = 200130;
                                    mListener.heandleMessage(message);
                                }
                            } else {
                                System.out.println("???????????????????????????");
                                if (opionts22 % 2 == 0) {
                                    Log.d("jsgs", "run:??????");
                                    message = new Message();
                                    bundle.putString("200130", "FDD");
                                    message.setData(bundle);
                                    handler.sendMessage(message);
                                    message.what = 200135;
                                    mListener.heandleMessage(message);
                                } else {
                                    Log.d("jsgs", "run:??????");
                                    message = new Message();
                                    bundle.putString("200131", "FDD");
                                    message.setData(bundle);
                                    handler.sendMessage(message);
                                    message.what = 200134;
                                    mListener.heandleMessage(message);
                                }
                            }
                        }

                        //???????????????
                        if ("05f0".equals(str.substring(8, 12))) {
                            System.out.println("2???????????????IMSI??????" + hexStringToString(str.substring(32, 62)));
                            Date now = new Date();
                            String imsi = hexStringToString(str.substring(32, 62));
                            Log.d("jsgs", "run:??????");
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

                        //?????????????????????????????????
                        if ("07f0".equals(str.substring(8, 12))) {
                            if ("00000000".equals(str.substring(24, 32))) {
                                System.out.println("????????????ue????????????");
                                message = new Message();
                                bundle.putString("test", "");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 200136;
                                mListener.heandleMessage(message);
                            } else {
                                System.out.println("????????????ue????????????");


                                message = new Message();
                                bundle.putString("test", "");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 200137;
                                mListener.heandleMessage(message);
                            }
                        }
                        //??????????????????
                        if ("0cf0".equals(str.substring(8, 12))) {
                            //??????0????????????>0???16????????????????????????????????????
                            int row = Integer.parseInt(str.substring(24, 32), 16);
                            if (row == 0) {
                                System.out.println("?????????????????????");
                                message = new Message();
                                bundle.putString("test", "");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 200138;
                                mListener.heandleMessage(message);
                            } else {
                                System.err.println("???????????????");
                                message = new Message();
                                bundle.putString("test", "");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 200139;
                                mListener.heandleMessage(message);
                            }
                        }
                        //????????????????????????
                        if ("02f0".equals(str.substring(8, 12))) {
                            //??????0????????????>0???16????????????????????????????????????
                            int row = Integer.parseInt(str.substring(24, 32), 16);
                            if (row == 0) {
                                System.out.println("?????????????????????");
                                message = new Message();
                                bundle.putString("test", "");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 200140;
                                mListener.heandleMessage(message);
                            } else {
                                System.err.println("??????????????????");
                                message = new Message();
                                bundle.putString("test", "");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 200141;
                                mListener.heandleMessage(message);
                            }
                        }
                        //?????????FDD TDD
                        if ("00ff".equals(str.substring(16, 20))) {
                            //????????????FDD
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
                            //????????????TDD
                            ID2TF = "TDD";
                            System.out.println("TDD");
                            message = new Message();
                            bundle.putString("tf2", "TDD");
                            message.setData(bundle);
                            handler.sendMessage(message);
                            message.what = 200110;
                            mListener.heandleMessage(message);
                        }
                        //????????????????????????
                        if ("04f0".equals(str.substring(8, 12))) {
                            //??????0????????????>0???16????????????????????????????????????
                            int row = Integer.parseInt(str.substring(24, 32), 16);
                            if (row == 0) {
                                message = new Message();
                                bundle.putString("test", "0");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 200145;
                                mListener.heandleMessage(message);
                                System.out.println("????????????????????????????????????");
                            } else {
                                System.err.println("???????????????");
                                message = new Message();
                                bundle.putString("test", "0");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 200146;
                                mListener.heandleMessage(message);
                            }
                        }
                        //????????????
                        if ("a0f0".equals(str.substring(8, 12))) {
                            //??????0????????????>0???16????????????????????????????????????
                            int row = Integer.parseInt(str.substring(24, 32), 16);
                            if (row == 0) {
                                System.out.println("?????????????????????");
                                message = new Message();
                                bundle.putString("test", "0");
                                message.setData(bundle);
                                handler.sendMessage(message);
//                                message.what = 200142;
                                mListener.heandleMessage(message);
                            } else {
                                System.err.println("???????????????");
                                message = new Message();
                                bundle.putString("test", "0");
                                message.setData(bundle);
                                handler.sendMessage(message);
//                                message.what = 200143;
                                mListener.heandleMessage(message);
                            }
                        }
                        //???????????????????????????
                        if ("0ef0".equals(str.substring(8, 12))) {
                            //??????0????????????>0???16????????????????????????????????????
                            int row = Integer.parseInt(str.substring(24, 32), 16);
                            if (row == 0) {
                                System.out.println("??????????????????????????????????????????????????????");
                            } else {
                                System.err.println("???????????????");
                            }
                        }
                        //??????????????????
                        //??????????????????
                        if ("19f0".equals(str.substring(8, 12))) {
                            String state = str.substring(24, 32);
                            System.out.println("state" + state);
                            Log.d("wtto", "staterun: " + state);
                            if ("00000000".equals(state)) {
                                System.err.println("??????????????????");
                                Log.d("wtto", "qqqrun:??????????????????");
                                message = new Message();
                                bundle.putString("test", "1");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 2001200;
                                mListener.heandleMessage(message);
                            } else if ("01000000".equals(state)) {
                                System.err.println("??????????????????");
                                Log.d("wtto", "qqqrun:??????????????????");
                                message = new Message();
                                bundle.putString("test", "2");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 2001200;
                                mListener.heandleMessage(message);
                            } else if ("02000000".equals(state)) {
                                System.err.println("GPS????????????");
                                Log.d("wtto", "qqqrun:GPS????????????");
                                message = new Message();
                                bundle.putString("test", "3");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 2001200;
                                mListener.heandleMessage(message);
                            } else if ("03000000".equals(state)) {
                                System.err.println("GPS????????????");
                                Log.d("wtto", "qqqrun:GPS????????????");
                                message = new Message();
                                bundle.putString("test", "4");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 2001200;
                                mListener.heandleMessage(message);
                            } else if ("04000000".equals(state)) {
                                System.err.println("????????????");
                                Log.d("wtto", "qqqrun:????????????");
                                message = new Message();
                                bundle.putString("test", "5");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 2001200;
                                mListener.heandleMessage(message);
                            } else if ("05000000".equals(state)) {
                                System.err.println("????????????");
                                Log.d("wtto", "qqqrun:????????????");
                                message = new Message();
                                bundle.putString("test", "6");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 2001200;
                                mListener.heandleMessage(message);
                            } else if ("06000000".equals(state)) {
                                System.err.println("??????????????????");
                                Log.d("wtto", "qqqrun:??????????????????");
                                message = new Message();
                                bundle.putString("test", "7");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 2001200;
                                mListener.heandleMessage(message);
                            } else if ("07000000".equals(state)) {
                                System.err.println("??????????????????");
                                Log.d("wtto", "qqqrun:??????????????????");
                                message = new Message();
                                bundle.putString("test", "8");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 2001200;
                                mListener.heandleMessage(message);
                            } else if ("08000000".equals(state)) {
                                System.err.println("???????????????");
                                Log.d("wtto", "qqqrun:???????????????");
                                message = new Message();
                                bundle.putString("test", "9");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 2001200;
                                mListener.heandleMessage(message);
                            } else if ("09000000".equals(state)) {
                                System.err.println("???????????????");
                                Log.d("wtto", "qqqrun:???????????????");
                                message = new Message();
                                bundle.putString("test", "10");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 2001200;
                                mListener.heandleMessage(message);
                            } else if ("0a000000".equals(state)) {
                                System.err.println("GPS?????????");
                                Log.d("wtto", "qqqrun:GPS?????????");
                                message = new Message();
                                bundle.putString("test", "11");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 2001200;
                                mListener.heandleMessage(message);
                            } else if ("0b000000".equals(state)) {
                                System.err.println("?????????");
                                Log.d("wtto", "qqqrun:?????????");
                                message = new Message();
                                bundle.putString("test", "12");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 2001200;
                                mListener.heandleMessage(message);
                            } else if ("0c000000".equals(state)) {
                                System.err.println("???????????????");
                                Log.d("wtto", "qqqrun:???????????????");
                                message = new Message();
                                bundle.putString("test", "13");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 2001200;
                                mListener.heandleMessage(message);
                            } else if ("0d000000".equals(state)) {
                                System.err.println("??????????????????");
                                Log.d("wtto", "qqqrun:??????????????????");
                                message = new Message();
                                bundle.putString("test", "14");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 2001200;
                                mListener.heandleMessage(message);
                            }
                        }
                        if ("08f0".equals(str.substring(8, 12))) {

                            //???????????????16????????????????????????????????????
                            Integer.parseInt(str.substring(24, 26), 16);
                            System.out.println("?????????" + Integer.parseInt(str.substring(24, 26), 16));
                            message = new Message();
                            bundle.putString("sb1j2", Integer.parseInt(str.substring(24, 26), 16) + "");
                            bundle.putString("imsi2", hexStringToString(str.substring(26, 56)) + "");
                            message.setData(bundle);
                            handler.sendMessage(message);
                            message.what = 200147;
                            mListener.heandleMessage(message);
                            //IMSI???
                            StringTOIMEI(str.substring(26, 56));
                            System.out.println("IMSI???zb???" + hexStringToString(str.substring(26, 56)));
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
                            //????????????
                            //?????????????????????????????????0????????? IDLE??????1?????????/??????????????????2?????????????????????3?????????????????????4????????????????????????
                            message = new Message();
                            bundle.putString("zt1", "2");
                            message.setData(bundle);
                            handler.sendMessage(message);
                            message.what = 200120;
                            mListener.heandleMessage(message);
                            if ("0000".equals(str.substring(24, 28))) {
                                System.out.println("0????????? IDLE???");
                                message = new Message();
                                bundle.putString("zt2", "2");//IDLE???
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 200120;
                                mListener.heandleMessage(message);
                            } else if ("0100".equals(str.substring(24, 28))) {
                                System.out.println("1?????????/???????????????");

                                message = new Message();
                                bundle.putString("zt2", "3");//?????????????????????
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 200120;
                                mListener.heandleMessage(message);
                            } else if ("0200".equals(str.substring(24, 28))) {
                                System.out.println("2??????????????????");
                                message = new Message();
                                bundle.putString("zt2", "4");//???????????????
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 100120;
                                mListener.heandleMessage(message);
                            } else if ("0300".equals(str.substring(24, 28))) {
                                System.out.println("3????????????");
                                message = new Message();
                                bundle.putString("zt2", "5");//???????????????
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 200120;
                                mListener.heandleMessage(message);
                                //Band???
                                Integer.parseInt(StringPin(str.substring(28, 32)), 16);
                                System.out.println("Band??????" + Integer.parseInt(StringPin(str.substring(28, 32)), 16));
                                //????????????
                                Integer.parseInt(StringPin(str.substring(32, 40)), 16);
                                System.out.println("???????????????" + Integer.parseInt(StringPin(str.substring(32, 40)), 16));
                                //????????????
                                Integer.parseInt(StringPin(str.substring(40, 48)), 16);
                                DOWNPIN2 = Integer.parseInt(StringPin(str.substring(40, 48)), 16) + "";
                                System.out.println("???????????????" + Integer.parseInt(StringPin(str.substring(40, 48)), 16));
                                message = new Message();
                                bundle.putString("down", Integer.parseInt(StringPin(str.substring(40, 48)), 16) + "");//????????????
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 200151;
                                mListener.heandleMessage(message);
                                System.out.println("200151");
                                //??????????????????
                                if ("3436303030".equals(str.substring(48, 58))) {
                                    //??????????????????
                                }
                                if ("3436303031".equals(str.substring(48, 58))) {
                                    //??????????????????
                                }
                                if ("3436303033".equals(str.substring(48, 58)) || "3436303131".equals(str.substring(48, 58))) {
                                    //??????????????????
                                }

                                //PCI
                                Integer.parseInt(StringPin(str.substring(64, 68)), 16);
                                System.out.println("PCI:" + Integer.parseInt(StringPin(str.substring(64, 68)), 16));
                                //TAC
                                Integer.parseInt(StringPin(str.substring(68, 72)), 16);
                                System.out.println("TAC:" + Integer.parseInt(StringPin(str.substring(68, 72)), 16));

                            } else if ("0400".equals(str.substring(24, 28))) {
                                System.out.println("4?????????????????????");
                            }

                        }
                        //????????????
                        if ("5bf0".equals(str.substring(8, 12))) {
                            if ("00000000".equals(str.substring(32, 40))) {
                                System.out.println("?????????????????????70???");
                            }
                            if ("01000000".equals(str.substring(32, 40))) {
                                System.out.println("????????????????????????70????????????");
                            }

                        }
                        if ("32f0".equals(str.substring(8, 12))) {
                            message = new Message();
                            int i = Integer.parseInt(str.substring(24, 26), 16);

//                            Log.d(TAG, "run: " + i);
                            bundle.putString("zy2", Integer.parseInt(str.substring(24, 26), 16) + "");//????????????
                            message.setData(bundle);
                            handler.sendMessage(message);
                            message.what = 200149;
                            System.out.println("200149");
                            mListener.heandleMessage(message);
                        }

                        //????????????????????????
                        if ("14f0".equals(str.substring(8, 12))) {
                            //??????0????????????>0???16????????????????????????????????????
                            int row = Integer.parseInt(str.substring(24, 32), 16);
                            if (row == 0) {
                                System.out.println("?????????????????????!");
                                message = new Message();
                                bundle.putString("zyset2", "?????????????????????");//???????????????
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 200150;
                                mListener.heandleMessage(message);
                                System.out.println("200150");

                            } else {
                                System.err.println("?????????????????????!");
                                message = new Message();
                                bundle.putString("zyset2", "?????????????????????");//???????????????
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
                                System.out.println("SNF???????????????????????????");

                                message = new Message();
                                bundle.putString("snf", "SNF????????????????????????");//???????????????
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 200154;
                                mListener.heandleMessage(message);
                                System.out.println("100154");

                            } else {
                                System.err.println("SNF???????????????????????????");
                                message = new Message();
                                bundle.putString("snf", "SNF????????????????????????");//???????????????
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 200154;
                                mListener.heandleMessage(message);
                                System.out.println("100154");
                            }
                        }
                        //??? 54

                        if ("0af0".equals(str.substring(8, 12))) {
                            //???????????????
                            List<SpBean> spBeanList = new ArrayList<>();
                            String length = str.substring(12, 16);
                            String len = StringPin(length);
                            Integer strlen = Integer.parseInt(len, 16);
                            //???????????????
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
                                //?????????????????????
                                int row;
                                if ("ffff".equals(str4.substring(24, 28))) {
                                    row = 0;
                                } else {
                                    row = Integer.parseInt(StringPin(str4.substring(24, 28)), 16);
                                    System.out.println("???????????????" + row);
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
                                    //????????????
                                    //????????????
                                    SpBean spBean = new SpBean();
                                    System.out.println(str4.substring(dlEarfcnBegin, dlEarfcnEnd));
                                    if ("ffffffff".equals(str4.substring(dlEarfcnBegin, dlEarfcnEnd))) {
                                        System.out.println("null");

                                    } else {
                                        System.out.println("???????????????------" + Integer.parseInt(StringPin(str4.substring(dlEarfcnBegin, dlEarfcnEnd)), 16));
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
//                                    System.out.println("PCI???------" + Integer.parseInt(StringPin(str4.substring(pciBegin, pciEnd)), 16));
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
//                                    System.out.println("TAC???------" + Integer.parseInt(StringPin(str4.substring(tacBegin, tacEnd)), 16));

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
//                                    System.out.println(Integer.parseInt(StringPin(str4.substring(plmnBegin, plmnEnd)), 16) + "---PLMN???");
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
//                                    System.out.println("ffffffff".equals(str4.substring(celldBegin, celldEnd)) ? "null" : Integer.parseInt(StringPin(str4.substring(celldBegin, celldEnd)), 16) + "------CellId???");
                                    if ("ffffffff".equals(str4.substring(celldBegin, celldEnd))) {
                                        Log.d("1nzqffffffff", "run:1 ");
                                        spBean.setCid(0 + "");
                                    } else {
                                        spBean.setCid(Integer.parseInt(StringPin(str4.substring(celldBegin, celldEnd)), 16) + "");
                                        Log.d("2nzqffffffff", "run:1 ");
                                    }
                                    //Priority ????????????????????????
//                                    System.out.println(str4.substring(64, 72));
                                    System.out.println("ffffffff".equals(str4.substring(priorityBegin, priorityEnd)) ? "Priority_null" : Integer.parseInt(StringPin(str4.substring(priorityBegin, priorityEnd)), 16) + "------Priority ???????????????????????????");
                                    if ("ffffffff".equals(str4.substring(priorityBegin, priorityEnd))) {
                                        Log.d("1nzqffffffff", "run:1 ");
                                        spBean.setPriority(0);
                                    } else {
                                        spBean.setPriority(Integer.parseInt(StringPin(str4.substring(priorityBegin, priorityEnd)), 16));
                                        Log.d("2nzqffffffff", "run:1 ");
                                    }
                                    //RSRP
//                                    System.out.println("RSRP???------" + Integer.parseInt(StringPin(str4.substring(rsrpBegin, rsrpEnd)), 16));
//                                    spBean.setRsrp(Integer.parseInt(StringPin(str4.substring(rsrpBegin, rsrpEnd)), 16));

                                    if ("ffffffff".equals(str4.substring(rsrpBegin, rsrpEnd))) {
                                        Log.d("1nzqffffffff", "run:1 ");
                                        spBean.setRsrp(0);
                                    } else {
                                        spBean.setRsrp(Integer.parseInt(StringPin(str4.substring(rsrpBegin, rsrpEnd)), 16));
                                        Log.d("2nzqffffffff", "run:1 ");
                                    }


                                    //RSRQ
//                                    System.out.println("RSRQ???------" + Integer.parseInt(StringPin(str4.substring(rsrqBegin, rsrqEnd)), 16));
//                                    spBean.setRsrq(Integer.parseInt(StringPin(str4.substring(rsrqBegin, rsrqEnd)), 16));

                                    if ("ffffffff".equals(str4.substring(rsrqBegin, rsrqEnd))) {
                                        Log.d("1nzqffffffff", "run:1 ");
                                        spBean.setRsrq(0);
                                    } else {
                                        spBean.setRsrq(Integer.parseInt(StringPin(str4.substring(rsrqBegin, rsrqEnd)), 16));
                                        Log.d("2nzqffffffff", "run:1 ");
                                    }
                                    //Bandwidth??????????????????
//                                    System.out.println("Bandwidth???------" + Integer.parseInt(StringPin(str4.substring(bandWidthBegin, bandWidthEnd)), 16));

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
                                        spBeanList.add(spBean);//????????????add
                                    }
                                    //TddSpecialSf Patterns TDD??????????????????
//                                        System.out.println("TDD?????????????????????------" + Integer.parseInt(StringPin(str4.substring(tddSpecialSfBegin, tddSpecialSfEnd)), 16));
                                    //??????????????????
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
//                                        System.out.println("?????????????????????------" + InterFreqLstNum);

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
                                        //???????????????????????????
//                                            System.out.println(str4.substring(interFreqNghNumBegin, interFreqNghNumEnd));
//                                            System.out.println("pin:" + StringPin(str4.substring(interFreqNghNumBegin, interFreqNghNumEnd)));
//                                            System.out.println(Integer.parseInt(StringPin(str4.substring(interFreqNghNumBegin, interFreqNghNumEnd)), 16));
//                                        int interFreqNghNum;
//                                        if ("ffffffff".equals(str4.substring(interFreqNghNumBegin, interFreqNghNumEnd))) {
//
//                                            continue;
//                                        } else {
//                                            interFreqNghNum = Integer.parseInt(StringPin(str4.substring(interFreqNghNumBegin, interFreqNghNumEnd)), 16);
////                                                System.out.println("??????????????????????????????" + interFreqNghNum);
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
//                                                System.out.println("??????????????????????????????" + interFreqNghNum);
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
                                    //??????????????????,???????????????????????????RSRP
                                    List<Integer> list = new ArrayList();
                                    String down1 = "";
                                    SpBean spBean1 = new SpBean();
                                    SpBean spBean2 = new SpBean();
                                    if (spBeanList.size() >= 2) {
                                        for (int i = 0; i < spBeanList.size(); i++) {
                                            list.add(spBeanList.get(i).getPriority());
                                        }
                                        Integer max = Collections.max(list);
                                        Log.d("Anzqmax", "??????2???run: " + max);
                                        list.remove(max);//???????????????

                                        for (int i = 0; i < spBeanList.size(); i++) {
                                            if (max.equals(spBeanList.get(i).getPriority())) {
                                                down1 = spBeanList.get(i).getDown();
                                                spBean1 = spBeanList.get(i);
                                                break;
                                            }
                                        }

                                        //???????????????
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

                                            if (!down1.equals(down2)) {//???????????????????????? ????????????????????????
                                                message = new Message();

                                                bundle.putString("sp1MAX1value54", down1);//??????
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
                                                Log.d("Anzqmax", "??????2???run???????????????????????????????????????: " + max);
                                            } else {
                                                message = new Message();
                                                bundle.putString("sp1MAX1value54", down1);//??????
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
                                                Log.d("Anzqmax", "??????2???run??????????????????????????????????????????: " + down1 + "--" + down2);
                                            }


                                        } else {//???????????????????????????  ,??????rsrp??????

                                            int rssp1;
                                            int rssp2;
                                            List<Integer> list1rsp = new ArrayList<>();
                                            for (int i = 0; i < spBeanList.size(); i++) {
                                                list1rsp.add(spBeanList.get(i).getRsrp());
                                            }
                                            //?????????rsrp
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
                                            //????????????rsrp
                                            rssp2 = Collections.max(list1rsp);
                                            for (int i = 0; i < spBeanList.size(); i++) {
                                                if (rssp2 == spBeanList.get(i).getRsrp()) {
                                                    down2 = spBeanList.get(i).getDown();
                                                    spBean2 = spBeanList.get(i);
                                                }
                                            }
                                            if (down1.equals(down2)) {
                                                message = new Message();
                                                bundle.putString("sp1MAX1value54", down1);//??????
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
                                                Log.d("Anzqmax", "??????2???run????????????????????????RSRP??????????????????????????????????????????: " + down1 + "--" + down2);
                                            } else {
                                                message = new Message();
                                                bundle.putString("sp1MAX1value54", down1);//??????
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
                                                Log.d("Anzqmax", "??????2???run????????????????????????RSRP????????????????????????: " + down1 + "--" + down2);
                                            }


                                            ToastUtils.showToast("?????????????????????");
//                                        Log.d("Anzqmax", "??????2???run????????????????????????RSRP??????: "+down1+"--"+down2 );
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
                                            ToastUtils.showToast("???????????????1");
                                            Log.d("Anzqmax", "???????????????1: ");
                                        } else {
                                            message = new Message();
                                            bundle.putString("sp1MAX1value54", "");
                                            bundle.putString("sp1MAX2value54", "");
                                            message.setData(bundle);
                                            handler.sendMessage(message);
                                            message.what = 200152;
                                            mListener.heandleMessage(message);
                                            ToastUtils.showToast("???????????????0");
                                            Log.d("Anzqmax", "???????????????0: ");
                                        }
                                    }
                                }

                            } else if (spBeanList.size() > 0 && spBeanList.size() == 1) {//??????????????????
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
                                    ToastUtils.showToast("???????????????1");
                                    Log.d("Anzqmax", "???????????????1: ");
                                }
                            } else { //???????????????1 ????????????2
                                message = new Message();
                                bundle.putString("sp1MAX1value", "");
                                bundle.putString("sp1MAX2value", "");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                message.what = 200152;

                                mListener.heandleMessage(message);
                                ToastUtils.showToast("???????????????0");
                                Log.d("Anzqmax", "???????????????0: ");
                            }
                        }

                    }

                    //????????????????????????
//                            if ("0af0".equals(str.substring(8, 12))) {
//                                //?????????????????????
//                                int row;
//                                if ("ffff".equals(str.substring(24, 28))) {
//                                    row = 0;
//                                } else {
//                                    row = Integer.parseInt(StringPin(str.substring(24, 28)), 16);
//                                    System.out.println("???????????????" + row);
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
//                                    //????????????
//                                    System.out.println(str.substring(dlEarfcnBegin, dlEarfcnEnd));
//
//                                    if ("ffffffff".equals(str.substring(dlEarfcnBegin, dlEarfcnEnd))) {
//                                        System.out.println("null");
//                                        spBean.setDown("0");
//                                    } else {
//                                        System.out.println("???????????????------" + Integer.parseInt(StringPin(str.substring(dlEarfcnBegin, dlEarfcnEnd)), 16));
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
//                                    System.out.println("PCI???------" + Integer.parseInt(StringPin(str.substring(pciBegin, pciEnd)), 16));
//                                    spBean.setPci(Integer.parseInt(StringPin(str.substring(pciBegin, pciEnd)), 16));
//                                    System.out.println(dlEarfcnBegin + "+" + dlEarfcnEnd);
//
//                                    //TAC
//                                    System.out.println("TAC???------" + Integer.parseInt(StringPin(str.substring(tacBegin, tacEnd)), 16));
//                                    spBean.setTac(Integer.parseInt(StringPin(str.substring(tacBegin, tacEnd)), 16));
//                                    //PLMN
//                                    System.out.println(Integer.parseInt(StringPin(str.substring(plmnBegin, plmnEnd)), 16) + "---PLMN???");
//
//                                    spBean.setPlmn(Integer.parseInt(StringPin(str.substring(plmnBegin, plmnEnd)), 16) + "");
//                                    //CellId
//                                    System.out.println("ffffffff".equals(str.substring(celldBegin, celldEnd)) ? "null" : Integer.parseInt(StringPin(str.substring(celldBegin, celldEnd)), 16) + "------CellId???");
//                                    //Priority ????????????????????????
//                                    System.out.println("?????????--" + str.substring(64, 72));
//                                    System.out.println("ffffffff".equals(str.substring(priorityBegin, priorityEnd)) ? "null" : Integer.parseInt(StringPin(str.substring(priorityBegin, priorityEnd)), 16) + "------Priority ???????????????????????????");
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
//                                    System.out.println("RSRP???------" + Integer.parseInt(StringPin(str.substring(rsrpBegin, rsrpEnd)), 16));
//                                    //RSRQ
//                                    System.out.println("RSRQ???------" + Integer.parseInt(StringPin(str.substring(rsrqBegin, rsrqEnd)), 16));
//                                    spBean.setRsrp(Integer.parseInt(StringPin(str.substring(rsrpBegin, rsrpEnd)), 16));
//                                    spBeanList.add(spBean);//????????????add
//                                    //Bandwidth??????????????????
//                                    System.out.println("Bandwidth???------" + Integer.parseInt(StringPin(str.substring(bandWidthBegin, bandWidthEnd)), 16));
//                                    spBean.setBand(Integer.parseInt(StringPin(str.substring(bandWidthBegin, bandWidthEnd)), 16) + "");
//                                    //TddSpecialSf Patterns TDD??????????????????
//                                    System.out.println("TDD?????????????????????------" + Integer.parseInt(StringPin(str.substring(tddSpecialSfBegin, tddSpecialSfEnd)), 16));
//                                    //??????????????????
//                                    int InterFreqLstNum;
//                                    if ("ffffffff".equals(str.substring(interFreqLstNumBegin, interFreqLstNumEnd))) {
//                                        InterFreqLstNum = 0;
//                                    } else {
//                                        InterFreqLstNum = Integer.parseInt(StringPin(str.substring(interFreqLstNumBegin, interFreqLstNumEnd)), 16);
//                                    }
//                                    System.out.println(interFreqLstNumBegin + "---" + interFreqLstNumEnd);
//                                    System.out.println("?????????????????????------" + InterFreqLstNum);
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
////                                        //???????????????????????????
////                                        System.out.println(str.substring(interFreqNghNumBegin, interFreqNghNumEnd));
////                                        System.out.println("pin:" + StringPin(str.substring(interFreqNghNumBegin, interFreqNghNumEnd)));
////                                        System.out.println(Integer.parseInt(StringPin(str.substring(interFreqNghNumBegin, interFreqNghNumEnd)), 16));
////                                        int interFreqNghNum = Integer.parseInt(StringPin(str.substring(interFreqNghNumBegin, interFreqNghNumEnd)), 16);
////                                        System.out.println("??????????????????????????????" + interFreqNghNum);
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
//                                //????????????37900
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
//                                    //??????????????????,???????????????????????????RSRP
//                                    List<Integer> list = new ArrayList();
//                                    String down1 = "";
//                                    SpBean spBean1 = new SpBean();
//                                    SpBean spBean2 = new SpBean();
//                                    if (spBeanList.size() >= 2) {
//                                        for (int i = 0; i < spBeanList.size(); i++) {
//                                            list.add(spBeanList.get(i).getPriority());
//                                        }
//                                        Integer max = Collections.max(list);
//                                        Log.d("Anzqmax", "??????2???run: " + max);
//                                        list.remove(max);//???????????????
//
//                                        for (int i = 0; i < spBeanList.size(); i++) {
//                                            if (max.equals(spBeanList.get(i).getPriority())) {
//                                                down1 = spBeanList.get(i).getDown();
//                                                spBean1 = spBeanList.get(i);
//                                                break;
//                                            }
//                                        }
//
//                                        //???????????????
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
//                                            if (!down1.equals(down2)) {//???????????????????????? ????????????????????????
//                                                message = new Message();
//                                                bundle.putString("sp1MAX1value54", down1);//??????
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
//                                                Log.d("Anzqmax", "??????2???run???????????????????????????????????????: " + max);
//                                            } else {
//                                                message = new Message();
//                                                bundle.putString("sp1MAX1value54", down1);//??????
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
//                                                Log.d("Anzqmax", "??????2???run??????????????????????????????????????????: " + down1 + "--" + down2);
//                                            }
//
//
//                                        } else {//???????????????????????????  ,??????rsrp??????
//
//                                            int rssp1;
//                                            int rssp2;
//                                            List<Integer> list1rsp = new ArrayList<>();
//                                            for (int i = 0; i < spBeanList.size(); i++) {
//                                                list1rsp.add(spBeanList.get(i).getRsrp());
//                                            }
//                                            //?????????rsrp
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
//                                            //????????????rsrp
//                                            rssp2 = Collections.max(list1rsp);
//                                            for (int i = 0; i < spBeanList.size(); i++) {
//                                                if (rssp2 == spBeanList.get(i).getRsrp()) {
//                                                    down2 = spBeanList.get(i).getDown();
//                                                    spBean2 = spBeanList.get(i);
//                                                }
//                                            }
//                                            if (down1.equals(down2)) {
//                                                message = new Message();
//                                                bundle.putString("sp1MAX1value54", down1);//??????
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
//                                                Log.d("Anzqmax", "??????2???run????????????????????????RSRP??????????????????????????????????????????: " + down1 + "--" + down2);
//                                            } else {
//                                                message = new Message();
//                                                bundle.putString("sp1MAX1value54", down1);//??????
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
//                                                Log.d("Anzqmax", "??????2???run????????????????????????RSRP????????????????????????: " + down1 + "--" + down2);
//                                            }
//
//
//                                            ToastUtils.showToast("?????????????????????");
////                                        Log.d("Anzqmax", "??????2???run????????????????????????RSRP??????: "+down1+"--"+down2 );
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
//                                            ToastUtils.showToast("???????????????1");
//                                            Log.d("Anzqmax", "???????????????1: ");
//                                        } else {
//                                            message = new Message();
//                                            bundle.putString("sp1MAX1value54", "");
//                                            bundle.putString("sp1MAX2value54", "");
//                                            message.setData(bundle);
//                                            handler.sendMessage(message);
//                                            message.what = 200152;
//                                            ToastUtils.showToast("???????????????0");
//                                            Log.d("Anzqmax", "???????????????0: ");
//                                        }
//                                    }
//                                }

//                                } else if (spBeanList.size() > 0 && spBeanList.size() == 1) {//??????????????????
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
//                                        ToastUtils.showToast("???????????????1");
//                                        Log.d("Anzqmax", "???????????????1: ");
//                                    }
//                                } else { //???????????????1 ????????????2
//                                    message = new Message();
//                                    bundle.putString("sp1MAX1value", "");
//                                    bundle.putString("sp1MAX2value", "");
//                                    message.setData(bundle);
//                                    handler.sendMessage(message);
//                                    message.what = 100152;
//                                    ToastUtils.showToast("???????????????0");
//                                    Log.d("Anzqmax", "???????????????0: ");
//                                }
                }

//                }

//                        if ("192.168.2.54".equals(dp.getAddress().getHostAddress())) {
////                            mPressedTime2 = System.currentTimeMillis();
////                            System.out.println("ABCD");
////                            System.out.println("??????" + dp.getAddress().getHostAddress() + "???????????????" + str);
////
////                            //??????
////                            Date d = new Date();
////                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////                            System.out.println("?????????" + sdf.format(d));
////                            //???????????????FDD TDD
////                            if ("00ff".equals(str.substring(16, 20))) {
////                                //????????????FDD
////                                System.err.println("FDD");
////                                System.err.println("FDD");
////                                message = new Message();
////                                bundle.putString("tf2", "FDD");
////                                message.setData(bundle);
////                                handler.sendMessage(message);
////                                message.what = 200110;
////                            }
////                            if ("ff00".equals(str.substring(16, 20))) {
////                                //????????????TDD
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
////                                //???????????????16????????????????????????????????????
////                                Integer.parseInt(str.substring(24, 26), 16);
////                                System.out.println("?????????" + Integer.parseInt(str.substring(24, 26), 16));
////                                //IMSI???
////                                StringTOIMEI(str.substring(26, 56));
////                                System.out.println("IMSI??????" + hexStringToString(str.substring(26, 56)));
////
////                            }
////                            if ("10f0".equals(str.substring(8, 12))) {
////                                message = new Message();
////                                bundle.putString("zt2", "2");
////                                message.setData(bundle);
////                                handler.sendMessage(message);
////                                message.what = 200120;
////                                //????????????
////                                //?????????????????????????????????0????????? IDLE??????1?????????/??????????????????2?????????????????????3?????????????????????4????????????????????????
////                                message = new Message();
////                                bundle.putString("zt2", "0");
////                                message.setData(bundle);
////                                handler.sendMessage(message);
////                                message.what = 200120;
////                                if ("0000".equals(str.substring(24, 28))) {
////                                    System.out.println("0????????? IDLE???");
////                                    message = new Message();
////                                    bundle.putString("zt2", "2");
////                                    message.setData(bundle);
////                                    handler.sendMessage(message);
////                                    message.what = 200120;
////                                } else if ("0100".equals(str.substring(24, 28))) {
////                                    System.out.println("1?????????/???????????????");
////                                    message = new Message();
////                                    bundle.putString("zt2", "3");
////                                    message.setData(bundle);
////                                    handler.sendMessage(message);
////                                    message.what = 200120;
////                                } else if ("0200".equals(str.substring(24, 28))) {
////                                    System.out.println("2??????????????????");
////                                    message = new Message();
////                                    bundle.putString("zt2", "4");
////                                    message.setData(bundle);
////                                    handler.sendMessage(message);
////                                    message.what = 200120;
////                                } else if ("0300".equals(str.substring(24, 28))) {
////                                    System.out.println("3??????????????????");
////                                    message = new Message();
////                                    bundle.putString("zt2", "5");
////                                    message.setData(bundle);
////                                    handler.sendMessage(message);
////                                    message.what = 200120;
////                                    //Band???
////                                    Integer.parseInt(StringPin(str.substring(28, 32)), 16);
////                                    System.out.println("Band??????" + Integer.parseInt(StringPin(str.substring(28, 32)), 16));
////                                    //????????????
////                                    Integer.parseInt(StringPin(str.substring(32, 40)), 16);
////                                    System.out.println("???????????????" + Integer.parseInt(StringPin(str.substring(32, 40)), 16));
////                                    //????????????
////                                    Integer.parseInt(StringPin(str.substring(40, 48)), 16);
////                                    System.out.println("???????????????" + Integer.parseInt(StringPin(str.substring(40, 48)), 16));
////                                    //??????????????????
////                                    if ("3436303030".equals(str.substring(48, 58))) {
////                                        //??????????????????
////                                    }
////                                    if ("3436303031".equals(str.substring(48, 58))) {
////                                        //??????????????????
////                                    }
////                                    if ("3436303033".equals(str.substring(48, 58)) || "3436303131".equals(str.substring(48, 58))) {
////                                        //??????????????????
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
////                                    System.out.println("4?????????????????????");
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
                /*System.out.println("???????????????"+str.substring(8, 12));
                if(str.substring(8, 12).equals("08f0")){

					//???????????????16????????????????????????????????????
					Integer.parseInt(str.substring(24, 26),16);
					System.out.println("?????????"+Integer.parseInt(str.substring(24, 26),16));
					//IMSI???
					StringTOIMEI(str.substring(26, 56));
					System.out.println("IMSI??????"+hexStringToString(str.substring(26, 56)));

				}*//*if("10f0".equals(str.substring(8, 12))){
                    //????????????
					//?????????????????????????????????0????????? IDLE??????1?????????/??????????????????2?????????????????????3?????????????????????4????????????????????????
					if("0000".equals(str.substring(24, 28))){
						System.out.println("0????????? IDLE???");
					}if("0100".equals(str.substring(24, 28))){
						System.out.println("1?????????/???????????????");
					}if("0200".equals(str.substring(24, 28))){
						System.out.println("2??????????????????");
					}if("0300".equals(str.substring(24, 28))){
						System.out.println("3??????????????????");
					}if("0400".equals(str.substring(24, 28))){
						System.out.println("4?????????????????????");
					}
					//???????????????FDD TDD
					if("00ff".equals(str.substring(16, 20))){
						//????????????FDD
						System.err.println("FDD");
					}if("ff00".equals(str.substring(16, 20))){
						//????????????TDD
						System.out.println("TDD");
					}
					//??????
					Date d = new Date();
			        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			        System.out.println("?????????"+sdf.format(d));
					//Band???
					Integer.parseInt(StringPin(str.substring(28,32)),16);
					System.out.println("Band??????"+Integer.parseInt(StringPin(str.substring(28,32)),16));
					//????????????
					Integer.parseInt(StringPin(str.substring(32,40)),16);
					System.out.println("???????????????"+Integer.parseInt(StringPin(str.substring(32,40)),16));
					//????????????
					Integer.parseInt(StringPin(str.substring(40,48)),16);
					System.out.println("???????????????"+Integer.parseInt(StringPin(str.substring(40,48)),16));
					//??????????????????
					if("3436303030".equals(str.substring(48, 58))){
						//??????????????????
					}if("3436303031".equals(str.substring(48, 58))){
						//??????????????????
					}if("3436303033".equals(str.substring(48, 58))||"3436303131".equals(str.substring(48, 58))){
						//??????????????????
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
//                    Log.d("TAG120111", "run: e.printStackTrace()" + "?????????" + e.getMessage() + "\n" + e.getLocalizedMessage() + "\n" + s);
////                    //????????????????????????
//                    bundle.putString("runThread", "true");
//                    message = new Message();
//                    message.setData(bundle);
//                    handler.sendMessage(message);
//                    message.what = 120;
//////
////                    //??????1?????? ?????????
////                    message = new Message();
////                    bundle.putString("zt1", "0");
////                    message.setData(bundle);
////                    handler.sendMessage(message);
////                    message.what = 100120;
////                    //??????2?????? ?????????
////                    message = new Message();
////                    bundle.putString("zt2", "0");
////                    message.setData(bundle);
////                    handler.sendMessage(message);
////                    message.what = 200120;
//////
//
////                    interrupted();//????????????
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
                /// ??????

            }
        }).

                start();

    }

}
