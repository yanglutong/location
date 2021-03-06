package com.sm.android.locations.location.viewpagermain.Fragment.frament1Packet;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sm.android.locations.location.Activity.AddParam.AddParamActivity;
import com.sm.android.locations.location.Activity.AddParam.ParamActivity;
import com.sm.android.locations.location.Activity.Communit.CommunitViewPagerActivity;
import com.sm.android.locations.location.Activity.Device.DeviceInfoActivity;
import com.sm.android.locations.location.Activity.Main.Adapter.RyImsiAdapter;
import com.sm.android.locations.location.Activity.Main.IT.SaoPinCallback;
import com.sm.android.locations.location.Activity.PinConfig.PinConfigViewPagerActivity;
import com.sm.android.locations.location.Activity.SaoPin.SaopinList.SaoPinSetingActivity;
import com.sm.android.locations.location.Constant.Constant;
import com.sm.android.locations.location.R;
import com.sm.android.locations.location.Utils.MainUtils.MainUtils;
import com.sm.android.locations.location.Utils.MainUtils.MainUtilsThread;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.AddPararBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.PinConfigBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerAddParam;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerLog;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerPinConfig;
import com.sm.android.locations.location.Utils.ToastUtils;
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
import java.util.List;
import java.util.Timer;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;

import static com.sm.android.locations.location.Constant.Constant.BLACKTIME1;
import static com.sm.android.locations.location.Constant.Constant.CALLBLACKFLAG1;
import static com.sm.android.locations.location.Constant.Constant.IP1;
import static com.sm.android.locations.location.Constant.Constant.IP2;

import static com.sm.android.locations.location.Utils.MainUtils.MainUtils.DS;
import static com.sm.android.locations.location.Utils.MainUtils.MainUtils.location;
import static com.sm.android.locations.location.viewpagermain.Fragment.frament1Packet.Fragment0.qxzhedieFlag;
import static com.sm.android.locations.location.viewpagermain.Fragment.frament1Packet.Fragment0.zmFlag;

public class FragmentPersent0 implements FragmentView0.FragmentPresenter1 {
    private FragmentView0.View view1;
    public static boolean voice1 = true;//??????1??????
    public static boolean voice2 = true;//??????2??????
    public static boolean LABAFLAG1 = true;
    public static boolean LABAFLAG2 = true;
    Dialog dialog;
    View inflate;

    public FragmentPersent0(@NonNull FragmentView0.View view) {
//        this.models = models;
        this.view1 = view;
        view.setPresenter(this);
    }

    @Override
    public void startlocation(Context context, String type, int device, boolean zd, String tf, String down, String down2) {
        List<AddPararBean> listImsiListTrue = new ArrayList<>();
        if (!TextUtils.isEmpty(type)) {
            if (type.equals("??????")) {
//                ToastUtils.showToast("?????????");

                try {
                    DBManagerAddParam dbManagerAddParam = new DBManagerAddParam(context);
                    List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
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

                        }

                    }
                    Log.d("addPararBeans", "init: " + dataAll);
                } catch (SQLException e) {
                    e.printStackTrace();
                }


                List<AddPararBean> sendListBlack = new ArrayList<>();
                if (!TextUtils.isEmpty(down)) {
//            String sb1zhishi = "";
                    String yy = "";
                    DBManagerPinConfig dbManagerPinConfig = null;
                    try {
                        dbManagerPinConfig = new DBManagerPinConfig(context);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    try {
                        List<PinConfigBean> pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(down)); //?????????????????????
                        yy = pinConfigBeans.get(0).getYy();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    Log.d("TAG", "sendBlackList:?????? ");
                    if (listImsiListTrue.size() > 0 && listImsiListTrue != null) {
                        for (int i = 0; i < listImsiListTrue.size(); i++) {
                            if (listImsiListTrue.get(i).getYy().equals(yy)) {
                                sendListBlack.add(listImsiListTrue.get(i));
                            }
                        }
                    }

                    if (sendListBlack.size() > 0 && sendListBlack != null) {
                        Log.d("TAG", "sendBlackList: " + sendListBlack);
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

                if ("??????".equals(type) || "?????????...".equals(type) || "?????????".equals(type) || "????????????".equals(type) || "?????????????????????".equals(type) || "???????????????".equals(type)) {
                    String sb1zhishi = "";
                    if (!TextUtils.isEmpty(down)) {
                        DBManagerPinConfig dbManagerPinConfig = null;
                        try {
                            dbManagerPinConfig = new DBManagerPinConfig(context);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        try {
                            List<PinConfigBean> pinConfigBeans = dbManagerPinConfig.queryData(Integer.parseInt(down));//?????????????????????
                            sb1zhishi = pinConfigBeans.get(0).getTf();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        if (!TextUtils.isEmpty(tf)) {
                            if (sb1zhishi.equals(tf)) {//????????????
                                if (TextUtils.isEmpty(tf)) {
                                    ToastUtils.showToast("??????1??????????????????");
                                    return;
                                }
                                if (down.equals(down2)) {
                                    ToastUtils.showToast("??????1?????????2????????????????????????");
                                } else {
//                                    sb1zhishiFlag = true;
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
                                    dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
                                    inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
                                    TextView tv_title = inflate.findViewById(R.id.tv_title);
                                    tv_title.setText("?????????????????????1????");
                                    Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
                                    bt_confirm.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
//                                            sb1Clear();//??????1????????????
                                            view1.Start1SD();
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
                                if (TextUtils.isEmpty(down)) {
                                    ToastUtils.showToast("??????1??????????????????");
                                    return;
                                }
//                        if (TextUtils.isEmpty(spinnerS2)) {
//                            ToastUtils.showToast("??????2??????????????????");
//                            return;
//                        }
                                if (down.equals(down2)) {
                                    ToastUtils.showToast("??????1?????????2????????????????????????");
                                    return;
                                }

//                                sb1zhishiFlag = false;
//                        ToastUtils.showToast("???????????????");
//                        new CircleDialog.Builder((FragmentActivity) mContext)
//                                .setTitle("")
//                                .setText("??????????????????,????????????????????????1")
//                                .setTitleColor(Color.parseColor("#00acff"))
//                                .setNegative("??????", new MyOclck(sb1zhishi, 11))
//                                .setPositive("??????", null)
//                                .show();
                                dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
                                inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
                                TextView tv_title = inflate.findViewById(R.id.tv_title);
                                tv_title.setText("??????????????????,????????????????????????1");
                                Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
                                bt_confirm.setOnClickListener(new MyOclckFragment(sb1zhishi, 11, context));

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


                return;
            }
            if (type.equals("?????????")) {
                ToastUtils.showToast("??????????????????");
                return;
            }
        } else {
            ToastUtils.showToast("???????????????");
        }
    }

    @Override
    public void setWIFI(Context context, ImageView tv, String type) {

    }

    /**
     * @param context ?????????
     * @param tv      ????????? view
     * @param fs      //????????????
     */
    @Override
    public void setFS(Context context, ImageView tv, boolean fs) {
        if (fs) {
            fs = false;
            Drawable drawable = context.getResources().getDrawable(R.mipmap.fengshan_toming);
            view1.fsup(drawable, fs);
            MainUtils.offOn(2);//????????????
        } else {
            fs = true;
            Drawable drawable = context.getResources().getDrawable(R.mipmap.fengshan);
            view1.fsup(drawable, fs);
            MainUtils.offOn(1);//????????????
        }
//        ToastUtils.showToast("??????");
    }

    @Override
    public void setIMSIAdapter(Context context, RecyclerView recyclerView, RyImsiAdapter ryImsiAdapter) {

    }

    @Override
    public void setQxzhedie(Context context, ImageButton iv, LineChartView lineChartView) {
        if (qxzhedieFlag == false) {
            Drawable drawable = context.getResources().getDrawable(R.mipmap.zheidedown);
            qxzhedieFlag = true;
            view1.qxzhedieUp(drawable);
//            ToastUtils.showToast("222");
            lineChartView.setVisibility(View.VISIBLE);
        } else if (qxzhedieFlag == true) {
            Drawable drawable = context.getResources().getDrawable(R.mipmap.zheide);
            qxzhedieFlag = false;
            view1.qxzhedieUp(drawable);
            lineChartView.setVisibility(View.GONE);
        }
    }

    @Override
    public void setZmzhedie(Context context, ImageButton iv, LinearLayout linearLayout, RecyclerView recyclerView) {
        if (zmFlag == false) {
            Drawable drawable = context.getResources().getDrawable(R.mipmap.zheidedown);
            zmFlag = true;
            view1.ZmzhedieUp(drawable);
//            ToastUtils.showToast("222");
            linearLayout.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        } else if (zmFlag == true) {
            Drawable drawable = context.getResources().getDrawable(R.mipmap.zheide);
            zmFlag = false;
            view1.ZmzhedieUp(drawable);
            recyclerView.setVisibility(View.GONE);
            linearLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void setVoice(Context context, int i, ImageView imageView, boolean b) {
        if (i == 1) {
            if (voice1 == true) {
                Drawable drawable = context.getResources().getDrawable(R.mipmap.labaflase);
                voice1 = false;
                LABAFLAG1 = voice1;
                view1.VoiceUp(imageView, drawable, voice1, i);
            } else if (voice1 == false) {
                Drawable drawable = context.getResources().getDrawable(R.mipmap.laba_green);
                voice1 = true;
                LABAFLAG1 = voice1;
                view1.VoiceUp(imageView, drawable, voice1, i);
            }
        }
        if (i == 2) {
            if (voice2 == true) {
                Drawable drawable = context.getResources().getDrawable(R.mipmap.labaflase);
                voice2 = false;
                LABAFLAG2 = voice2;
                view1.VoiceUp(imageView, drawable, voice2, i);
            } else if (voice2 == false) {
                Drawable drawable = context.getResources().getDrawable(R.mipmap.laba_red);
                voice2 = true;
                LABAFLAG1 = voice2;
                view1.VoiceUp(imageView, drawable, voice2, i);
            }
        }
    }

    @Override
    public void setmenu(final Context context, DLPopupWindow popupWindow, final List<DLPopItem> mList) {
        popupWindow.setOnItemClickListener(new DLPopupWindow.OnItemClickListener() {
            @Override
            public void OnClick(int position) {

                if (mList.get(position).getText().equals("??????IMSI")) {
                    startActivity(new Intent(context, AddParamActivity.class));
                }
                if (mList.get(position).getText().equals("??????IMSI")) {
                    startActivity(new Intent(context, ParamActivity.class));
//                    startActivity(new Intent(MainActivity.this, Param2Activity.class));
                }
                if (mList.get(position).getText().equals("????????????")) {
                    startActivity(new Intent(context, SaoPinSetingActivity.class));
                }
//                if (mList.get(position).getText().equals("????????????")) {
//                    startActivity(new Intent(context, LunxunSetingActivity.class));
//                    ToastUtils.showToast("aaa");
//                }

                if (mList.get(position).getText().equals("????????????")) {
//                    startActivity(new Intent(MainActivity.this, PinConfigActivity.class));//?????????
                    startActivity(new Intent(context, PinConfigViewPagerActivity.class));
                }
                if (mList.get(position).getText().equals("????????????")) {
//                    startActivity(new Intent(MainActivity.this, Community01Activity.class));
                    startActivity(new Intent(context, CommunitViewPagerActivity.class));
                }

                if (mList.get(position).getText().equals("????????????")) {
                    startActivity(new Intent(context, DeviceInfoActivity.class));
                }
//                if (mList.get(position).getText().equals("????????????")) {
////                    startActivity(new Intent(MainActivity.this, DeviceInfoActivity.class));
////                    ToastUtils.showToast("????????????");
//                    DialogUtils.DataExport(context, inflate, zmDataCallBack);
//                }

            }
        });
    }


    @Override
    public void SetSpinner(Context context, Spinner spinner, int device) {

    }

    @Override
    public void SetStatusBar(Context context, ScrollView myScrollView) {

    }

    @Override
    public void setStop(final Context context, final int i, Button button) {

        String MyIp = "";
        String MyTitle = "";
        if (i == 1) {
            MyIp = IP1;
            MyTitle = "?????????????????????1????";
        } else if (i == 2) {
            MyIp = IP2;
            MyTitle = "?????????????????????2????";
        } else if (i == 3) {
//            MyIp = IP3;
//            MyTitle = "?????????????????????3????";
        } else if (i == 4) {
//            MyIp = IP4;
//            MyTitle = "?????????????????????4????";
        }

        dialog = new Dialog(context, R.style.menuDialogStyleDialogStyle);
        inflate = LayoutInflater.from(context).inflate(R.layout.dialog_item_title, null);
        TextView tv_title = inflate.findViewById(R.id.tv_title);
        tv_title.setText(MyTitle);
        Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
        final String finalMyIp = MyIp;
        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewS) {
                com.sm.android.locations.location.Utils.MainUtils.MainUtils.StopLocation(finalMyIp);
                dialog.dismiss();
                dialog.cancel();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                DBManagerLog dbManagerLog = null;
                String string = "";
//                if (button.getText().toString().equals("????????????")) {
                string = "????????????";
//                }
//                if (button.getText().toString().equals("????????????")) {
//                    string = "????????????";
//
//                }
                view1.StopUp(string, i, finalMyIp);
                //????????????
//                try {
//                    dbManagerLog = new DBManagerLog(context);
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//                LogBean logBean = new LogBean();
//                logBean.setAssociated(LoginUtils.getId(context) + "");//??????ID
//                logBean.setEvent(LoginUtils.setBase64(string));//????????????
//                logBean.setSb(LoginUtils.setBase64("1"));
//                String format = sdf.format(new Date());//????????????
//                logBean.setDatetime(LoginUtils.setBase64(format));
//                try {
//                    dbManagerLog.insertConmmunit01Bean(logBean);
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
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

    @Override
    public void start1(Context context, boolean ms, TextView tvtype1, Timer timer, SaoPinCallback saoPinCallback) {

    }

    @Override
    public void start2(Context context, boolean ms, TextView tvtype1, Timer timer, SaoPinCallback saoPinCallback) {

    }

    @Override
    public void sendBlack(Context context, int device, Button button, String strDown) {

    }

    @Override
    public void setLocation(int device, int locationType, Context context, String tf, String type, String down) {

    }

    @Override
    public void setEstablish(Context context, int device, String down, String type) {

    }

    /**
     * ?????????????????? ?????????
     *
     * @param list1quxian
     * @param list2quxian
     * @param list3quxian
     * @param list4quxian
     */
    @Override
    public void qxListda
    (List<Integer> list1quxian, List<Integer> list2quxian, List<Integer> list3quxian, List<Integer> list4quxian) {

        //????????????
        SetqxData(list1quxian, list2quxian, list3quxian, list4quxian);
        //clear????????????
//        clearQxData(list1quxian, list2quxian, list3quxian, list4quxian);

    }

    /**
     * ????????????????????????
     *
     * @param list1quxian
     * @param list2quxian
     * @param list3quxian
     * @param list4quxian
     */
    private void SetqxData
    (List<Integer> list1quxian, List<Integer> list2quxian, List<Integer> list3quxian, List<Integer> list4quxian) {
//        list1quxian.add(600);
//        list1quxian.add(150);
//        list1quxian.add(250);
//        list1quxian.add(350);
//        list1quxian.add(450);
//        list1quxian.add(550);
//        list1quxian.add(650);
//        list1quxian.add(750);
//        list1quxian.add(850);
//        list1quxian.add(950);
//
//        list2quxian.add(1100);
//        list2quxian.add(160);
//        list2quxian.add(240);
//        list2quxian.add(301);
//        list2quxian.add(400);
//        list2quxian.add(530);
//        list2quxian.add(730);
//        list2quxian.add(300);
//        list2quxian.add(850);
//        list2quxian.add(950);

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


//        list3quxian.add(800);
//        list3quxian.add(500);
//        list3quxian.add(900);
//        list3quxian.add(1100);
//        list3quxian.add(100);
//        list3quxian.add(890);
//        list3quxian.add(400);
//        list3quxian.add(321);
//        list3quxian.add(123);
//        list3quxian.add(800);
//
//        list4quxian.add(200);
//        list4quxian.add(660);
//        list4quxian.add(40);
//        list4quxian.add(452);
//        list4quxian.add(590);
//        list4quxian.add(880);
//        list4quxian.add(790);
//        list4quxian.add(350);
//        list4quxian.add(1100);
//        list4quxian.add(100);
    }

    private class MyOclckFragment implements View.OnClickListener {
        private int i;
        private String zs;
        private Context context;

        public MyOclckFragment(String zs, int i, Context context) {
            this.i = i;
            this.zs = zs;
            this.context = context;
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
                    outputData = MainUtilsThread.hexStringToByteArray(location("460010619201205", "04", Sa, context, 1));
                    if (zs.equals("FDD")) {
                        Sa = "00";
                    }
                    if (zs.equals("TDD")) {
                        Sa = "01";
                    }
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
//                        outputData = MainUtilsThread.hexStringToByteArray(location(sendListBlack.get(0).getImsi(), "04", Sa, MainActivity.this, 1));
//                    }
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
//                        mysp1.setEnabled(false);
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
}
