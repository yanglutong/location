package com.sm.android.locations.location.Activity.Device;

import android.annotation.SuppressLint;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sm.android.locations.location.Base.BaseFragment;
import com.sm.android.locations.location.R;
import com.sm.android.locations.location.Utils.MainUtils.DeviceUtils;
import com.sm.android.locations.location.initData.CommandUtils;
import com.sm.android.locations.location.sos.SOSActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.ContentValues.TAG;
import static com.sm.android.locations.location.Activity.Device.TextFragment3.ridRepeat4;
import static com.sm.android.locations.location.Constant.Constant.BAND1;
import static com.sm.android.locations.location.Constant.Constant.BLACKLISTSB1;
import static com.sm.android.locations.location.Constant.Constant.CELLID1;
import static com.sm.android.locations.location.Constant.Constant.DBM1;
import static com.sm.android.locations.location.Constant.Constant.DK1;
import static com.sm.android.locations.location.Constant.Constant.DWON1;
import static com.sm.android.locations.location.Constant.Constant.ENDNUM1;
import static com.sm.android.locations.location.Constant.Constant.GZMS1;
import static com.sm.android.locations.location.Constant.Constant.IMSINUM1;
import static com.sm.android.locations.location.Constant.Constant.PCI1;
import static com.sm.android.locations.location.Constant.Constant.PLMN1;
import static com.sm.android.locations.location.Constant.Constant.REQUEST1;
import static com.sm.android.locations.location.Constant.Constant.SBZQ1;
import static com.sm.android.locations.location.Constant.Constant.SHUAIJIAN1;
import static com.sm.android.locations.location.Constant.Constant.TAC1;
import static com.sm.android.locations.location.Constant.Constant.TBTYPE1;
import static com.sm.android.locations.location.Constant.Constant.TBZT1;
import static com.sm.android.locations.location.Constant.Constant.TYPE1;
import static com.sm.android.locations.location.Constant.Constant.UEIMSI;
import static com.sm.android.locations.location.Constant.Constant.UEMAX1;
import static com.sm.android.locations.location.Constant.Constant.UEMAXOF1;
import static com.sm.android.locations.location.Constant.Constant.UP1;
import static com.sm.android.locations.location.Constant.Constant.ZENGYI1;
import static com.sm.android.locations.location.Constant.Constant.ZHZQ1;

public class TextFragment1 extends BaseFragment {
    boolean isHide;
    RecyclerView recyclerView;
    RyImsiAdapter ryImsiAdapter;
    View view;
    TextView tv_plmn, tv_up, tv_down, tv_band, tv_dk, tv_tac, tv_pci, tv_cid, tv_max, tv_type;//????????????
    TextView tv_ms, tv_zq, tv_ueimsi, tv_sbzq, tv_glkg, tvmax;//????????????
    TextView tv_rrcnum, tv_rrcsucess, tv_rrcsucessnum, tv_imsiNum, tv_imsiNumsucess;
    TextView tv_zy, tv_sj;
    TextView tv_tbtype, tv_tbzt;
    private Timer timer;
    //    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            if (msg.what == 100001) {//????????????
//            }
//        }
//    };

    @Override
    public View initView() {
//        view = inflater.inflate(R.layout.deviceinfragment0, container, false);
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.deviceinfragment1, null);
            initData();
        }
//        view = inflater.inflate(R.layout.deviceinfragment0, container, false);
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
            initData();
        }
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser&&isHide) {
            clear();
        }
//            // ?????????onResume()??????
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    clear();
//                    getData();
//                }
//            }, 100);
//        } else {
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    clear();
//                    getData();
//                }
//            }, 100);
//
//        }
    }

    @Override
    public void initData() {
        findViews();
//        getData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isHide=true;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void findViews() {
//        TextView tv_plmn, tv_up, tv_down, tv_band, tv_dk, tv_tac, tv_pci, tv_cid, tv_max, tv_type;//????????????
//        TextView tv_ms, tv_zq, tv_ueimsi, tv_sbzq, tv_glkg, tvmax;//????????????
//        TextView tv_zy, tv_sj;
        tv_plmn = view.findViewById(R.id.tv_plmn);
        tv_up = view.findViewById(R.id.tv_up);
        tv_down = view.findViewById(R.id.tv_down);
        tv_band = view.findViewById(R.id.tv_band);
        tv_dk = view.findViewById(R.id.tv_dk);
        tv_tac = view.findViewById(R.id.tv_tac);
        tv_pci = view.findViewById(R.id.tv_pci);
        tv_cid = view.findViewById(R.id.tv_cid);
        tv_max = view.findViewById(R.id.tv_max);
        tv_type = view.findViewById(R.id.tv_type);

//        recyclerView = view.findViewById(R.id.ryimsi);
//        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        //????????????
        tv_ms = view.findViewById(R.id.tv_ms);
        tv_zq = view.findViewById(R.id.tv_zq);
        tv_ueimsi = view.findViewById(R.id.tv_ueimsi);
        tv_sbzq = view.findViewById(R.id.tv_sbzq);
        tv_glkg = view.findViewById(R.id.tv_glkg);
        tvmax = view.findViewById(R.id.tvmax);
//        //??????
//        tv_zy = view.findViewById(R.id.tv_zy);
//        tv_sj = view.findViewById(R.id.tv_sj);


//        tv_rrcnum,tv_rrcsucess,tv_rrcsucessnum,tv_imsiNum,tv_imsiNumsucess
//        tv_rrcnum = view.findViewById(R.id.tv_rrcnum);
//        tv_rrcsucess = view.findViewById(R.id.tv_rrcsucess);
//        tv_rrcsucessnum = view.findViewById(R.id.tv_rrcsucessnum);
//        tv_imsiNum = view.findViewById(R.id.tv_imsiNum);
//        tv_imsiNumsucess = view.findViewById(R.id.tv_imsiNumsucess);
//
//        tv_tbtype = view.findViewById(R.id.tv_tbtype);
//        tv_tbzt = view.findViewById(R.id.tv_tbzt);
        /////////////
        tv_plmn.setText("");
        tv_up.setText("");
        tv_down.setText("");
        tv_band.setText("");
//        if (!TextUtils.isEmpty(DK1)) {
//            int i = Integer.parseInt(DK1);
            tv_dk.setText("");
//            tv_dk.setText(i / 5 + "");
//        }
        tv_tac.setText("");
        tv_pci.setText("");
        tv_cid.setText("");
        tv_max.setText("");
        tv_type.setText("");
        //
        tv_ms.setText("");
        tv_zq.setText("");
        tv_ueimsi.setText("");
        tv_sbzq.setText("");
        tv_glkg.setText("");
        tvmax.setText("");
        //??????????????????
        view.findViewById(R.id.bt_getxiaoqu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!CommandUtils.type.equals("")){
                    if(SOSActivity.socketTcpServer!=null){

//
//                        CommandUtils. BAND="";//Band
//                        CommandUtils. PLMN="";//PLMN
//
//                        CommandUtils. GPS="";//GPS??????
////                        CommandUtils. CELL="";//????????????  ???????????????????????????0???????????? 1:????????????
//
//
//                        CommandUtils. DLARFCN="";//??????
//                        CommandUtils. ULARFCN="";//????????????
//                        CommandUtils. PERIOD="";//????????????
//                        CommandUtils. PMAX="";//????????????
//                        CommandUtils. PA="";//??????????????????
//                        CommandUtils. CAP="";//????????????
//                        CommandUtils. PCI="";
//                        CommandUtils. TAC="";
//                        CommandUtils. CI="";
//                        CommandUtils. GAIN="";// ????????????
//                        CommandUtils. MODE="";// ????????????
//                        CommandUtils. RSTP="";// ??????????????????
//
//
//
//


                        CommandUtils.gwCan="????????????";
                        SOSActivity.socketTcpServer.sendPost(CommandUtils.getPublicParameters());



                        timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv_plmn.setText(CommandUtils.PLMN);
                                        tv_up.setText(CommandUtils.ULARFCN);
                                        tv_down.setText(CommandUtils.DLARFCN);
                                        tv_band.setText(CommandUtils.BAND);
//        if (!TextUtils.isEmpty(DK1)) {
//            int i = Integer.parseInt(DK1);
                                        tv_dk.setText("");
//            tv_dk.setText(i / 5 + "");
//        }
                                        tv_tac.setText(CommandUtils.TAC);
                                        tv_pci.setText(CommandUtils.PCI);
                                        tv_cid.setText(CommandUtils.CI);
                                        tv_max.setText(CommandUtils.PMAX);
                                        if(CommandUtils.GPS.equals("")){
                                            tv_dk.setText(CommandUtils.GPS);
                                        }else{
                                            if(!CommandUtils.GPS.equals("Not Fixed")){
                                                if(Math.abs(Integer.parseInt(CommandUtils.GPS))==1){
                                                    tv_dk.setText("??????");
                                                }
                                                if(Math.abs(Integer.parseInt(CommandUtils.GPS))==0){
                                                    tv_dk.setText("??????");
                                                }
                                            }
                                        }
                                    }
                                });
                            }
                        },1000);
                    }
                }



//                tv_type.setText("");
//                //
//                tv_ms.setText("");
//                tv_zq.setText("");
//                tv_ueimsi.setText("");
//                tv_sbzq.setText("");
//                tv_glkg.setText("");
//                tvmax.setText("");
//                DeviceUtils.SelectQury(1, 1);
//        DeviceUtils.SelectQury(2, 1);
//        DeviceUtils.SelectQury(3, 1);
//        DeviceUtils.SelectQury(4, 1);//????????????
//        DeviceUtils.SelectQury(5, 1);
//        DeviceUtils.SelectQury(6, 1);
//        MainUtils.getType(IP1);//????????????
            }
        });

        //??????????????????
        view.findViewById(R.id.bt_zhuangtai).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!CommandUtils.type.equals("")){
                    if(SOSActivity.socketTcpServer!=null){
                                        if(!CommandUtils.PLMN.equals("")){
                                            if (CommandUtils.CELL.equals("")) {
                                                tv_type.setText(CommandUtils.CELL);
                                            } else {
                                                if (Math.abs(Integer.parseInt(CommandUtils.CELL)) == 1) {
                                                    tv_type.setText("?????????");
                                                }
                                                if (Math.abs(Integer.parseInt(CommandUtils.CELL)) == 0) {
                                                    tv_type.setText("?????????");
                                                }
                                            }
                                        }
                    }
                }
            }
        });


        //??????????????????
        view.findViewById(R.id.bt_location_type).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                CommandUtils. BAND="";//Band
//                CommandUtils. PLMN="";//PLMN
//
//                CommandUtils. GPS="";//GPS??????
////                CommandUtils. CELL="";//????????????  ???????????????????????????0???????????? 1:????????????
//
//
//                CommandUtils. DLARFCN="";//??????
//                CommandUtils. ULARFCN="";//????????????
//                CommandUtils. PERIOD="";//????????????
//                CommandUtils. PMAX="";//????????????
//                CommandUtils. PA="";//??????????????????
//                CommandUtils. CAP="";//????????????
//                CommandUtils. PCI="";
//                CommandUtils. TAC="";
//                CommandUtils. CI="";
//                CommandUtils. GAIN="";// ????????????
//                CommandUtils. MODE="";// ????????????
//                CommandUtils. RSTP="";// ??????????????????
                if(!CommandUtils.type.equals("")){
                    if(SOSActivity.socketTcpServer!=null){
                        CommandUtils.gwCan="????????????";
                        SOSActivity.socketTcpServer.sendPost(CommandUtils.getPublicParameters());

                        timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if(CommandUtils.CAP.equals("")){//0?????? 1??????
                                            tv_ms.setText(CommandUtils.CAP);
                                        }else{
                                            if(Integer.parseInt(CommandUtils.CAP)==0){
                                                tv_ms.setText("??????");

                                            }
                                            if(Integer.parseInt(CommandUtils.CAP)==1){
                                                tv_ms.setText("??????");
                                            }
                                        }
                                        tv_zq.setText(CommandUtils.PERIOD);


                                        if(!CommandUtils.GAIN.equals("")){
                                            tv_ueimsi.setText("1W 30(dbm)");//??????????????????
                                            tv_sbzq.setText("35");//????????????
                                        }
//                                        if(Integer.parseInt(CommandUtils.GAIN)==0){
//                                            tv_sbzq.setText("????????????");//????????????
//                                        }else{
//                                            tv_sbzq.setText(CommandUtils.GAIN);//????????????
//                                        }

                                        if(CommandUtils.MODE.equals("")){
                                            tv_glkg.setText(CommandUtils.MODE);//????????????
                                        }else{
                                            if(Integer.parseInt(CommandUtils.MODE)==1){
                                                tv_glkg.setText("TDD");

                                            }
                                            if(Integer.parseInt(CommandUtils.MODE)==2){
                                                tv_glkg.setText("FDD");
                                            }
                                        }
                                        tvmax.setText(CommandUtils.RSTP);//??????????????????
                                    }
                                });
                            }
                        }, 1000);


                    }
                }


//                DeviceUtils.SelectQury(1, 1);//????????????
//                DeviceUtils.SelectQury(2, 1);//??????????????????
//        DeviceUtils.SelectQury(3, 1);
//        DeviceUtils.SelectQury(4, 1);
//        DeviceUtils.SelectQury(5, 1);
//        DeviceUtils.SelectQury(6, 1);
//        MainUtils.getType(IP1);//????????????
            }
        });
//        //????????????
//        view.findViewById(R.id.bt_gonglv).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                DeviceUtils.SelectQury(1, 1);//????????????
////                DeviceUtils.SelectQury(2, 1);//??????????????????
//        DeviceUtils.SelectQury(3, 1);//??????
////        DeviceUtils.SelectQury(4, 1);
////        DeviceUtils.SelectQury(5, 1);
////        DeviceUtils.SelectQury(6, 1);
////        MainUtils.getType(IP1);//????????????
//            }
//        });



//        //????????????
//        view.findViewById(R.id.tv_tbtype).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                DeviceUtils.SelectQury(1, 1);//????????????
////                DeviceUtils.SelectQury(2, 1);//??????????????????
////                DeviceUtils.SelectQury(3, 1);//??????
////        DeviceUtils.SelectQury(4, 1);
////        DeviceUtils.SelectQury(5, 1);
//        DeviceUtils.SelectQury(7, 1);
////        MainUtils.getType(IP1);//????????????
//            }
//        });
//        //????????????
//        view.findViewById(R.id.bt_sjjieru).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                DeviceUtils.SelectQury(1, 1);//????????????
////                DeviceUtils.SelectQury(2, 1);//??????????????????
////                DeviceUtils.SelectQury(3, 1);//??????
////        DeviceUtils.SelectQury(4, 1);
////        DeviceUtils.SelectQury(5, 1);
//                DeviceUtils.SelectQury(6, 1);//????????????
////        MainUtils.getType(IP1);//????????????
//            }
//        });


//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                Log.d("delayTAG", "---");
//                getData();
//                handler.postDelayed(this, 1000);
//            }
//        });
    }

    @Override
    public void onResume() {
        super.onResume();
        clear();
        getData();
    }

    @Override
    public void onPause() {
        super.onPause();
        clear();
    }

    @Override
    public void onStart() {
        super.onStart();
        clear();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        clear();
    }

    @Override
    public void onStop() {
        super.onStop();
        clear();
    }

    public void clear() {
        tv_plmn.setText("");
        tv_up.setText("");
        tv_down.setText("");
        tv_band.setText("");
//        if (!TextUtils.isEmpty(DK1)) {
//            int i = Integer.parseInt(DK1);
        tv_dk.setText("");
//        }
        tv_tac.setText("");
        tv_pci.setText("");
        tv_cid.setText("");
        tv_max.setText("");
        tv_type.setText("");
        //
        tv_ms.setText("");
        tv_zq.setText("");
        tv_ueimsi.setText("");
        tv_sbzq.setText("");
        tv_glkg.setText("");
        tvmax.setText("");
        if(timer!=null){
            timer.cancel();
        }
//        tv_zy.setText("");
//        tv_sj.setText("");
//        tv_rrcnum.setText("");
//        tv_rrcsucess.setText("");
//        tv_rrcsucessnum.setText("");
//        tv_imsiNum.setText("");
//        tv_imsiNumsucess.setText("");
//        tv_tbzt.setText("");
//        tv_tbtype.setText("");
//        tv_rrcnum,tv_rrcsucess,tv_rrcsucessnum,tv_imsiNum,tv_imsiNumsucess
//        BLACKLISTSB1.clear();
//        List<Integer> list = new ArrayList<>();
//        for (int i = 0; i < BLACKLISTSB1.size(); i++) {
//            list.add(i);
//
//        }
        //?????????????????????
//        ryImsiAdapter = new RyImsiAdapter(mContext, list, BLACKLISTSB1);
//        recyclerView.setAdapter(ryImsiAdapter);

    }

    private void getData() {
//        DeviceUtils.SelectQury(1, 1);
//        DeviceUtils.SelectQury(2, 1);
//        DeviceUtils.SelectQury(3, 1);
//        DeviceUtils.SelectQury(4, 1);
//        DeviceUtils.SelectQury(5, 1);
//        DeviceUtils.SelectQury(6, 1);
//        MainUtils.getType(IP1);//????????????
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                tv_devicenumber.setText(DEVICENUMBER1);
//                tv_hardware.setText(HARDWAREVERSION1);
//                tv_software.setText(SOFTWAREVERSION1);
//                tv_sn.setText(SNNUMBER1);
//                tv_mac.setText(MACADDRESS1);
//                tvuboot.setText(UBOOTVERSION1);
//                tvboard.setText(BOARDTEMPERATURE1);
//????????????
                tv_ms = view.findViewById(R.id.tv_ms);
                tv_zq = view.findViewById(R.id.tv_zq);
                tv_ueimsi = view.findViewById(R.id.tv_ueimsi);
                tv_sbzq = view.findViewById(R.id.tv_sbzq);
                tv_glkg = view.findViewById(R.id.tv_glkg);
                tvmax = view.findViewById(R.id.tvmax);
                //??????
                tv_zy = view.findViewById(R.id.tv_zy);
                tv_sj = view.findViewById(R.id.tv_sj);

                /////////////
                tv_plmn.setText(PLMN1);
                tv_up.setText(UP1);
                tv_down.setText(DWON1);
                tv_band.setText(BAND1);
                if (!TextUtils.isEmpty(DK1)) {
                    int i = Integer.parseInt(DK1);
                    tv_dk.setText(i / 5 + "");
                }
                tv_tac.setText(TAC1);
                tv_pci.setText(PCI1);
                tv_cid.setText(CELLID1);
                tv_max.setText(DBM1);
                tv_type.setText(TYPE1);
        Log.d(TAG, "BLACKLISTSB1getData: "+BLACKLISTSB1.toString());
                if (BLACKLISTSB1.size() > 0) {
                    List<String> list1 = ridRepeat4(BLACKLISTSB1);
                    List<Integer> list = new ArrayList<>();
                    for (int i = 0; i < list1.size(); i++) {
                        list.add(i);
                    }
                    //?????????????????????
                    Log.d("zlist1", "run: " + list1.toString());
                    ryImsiAdapter = new RyImsiAdapter(mContext, list, list1);
                    recyclerView.setAdapter(ryImsiAdapter);
                }
                //
                tv_ms.setText(GZMS1);
                tv_zq.setText(ZHZQ1);
                tv_ueimsi.setText(UEIMSI);
                tv_sbzq.setText(SBZQ1);
                tv_glkg.setText(UEMAXOF1);
                tvmax.setText(UEMAX1);

//                tv_zy.setText(ZENGYI1);
//                tv_sj.setText(SHUAIJIAN1);
//                //        tv_rrcnum,tv_rrcsucess,tv_rrcsucessnum,tv_imsiNum,tv_imsiNumsucess
//                tv_rrcnum.setText(REQUEST1);
//                tv_rrcsucess.setText(ENDNUM1);
                if (!TextUtils.isEmpty(REQUEST1) && !TextUtils.isEmpty(ENDNUM1) && !REQUEST1.equals("0") && !ENDNUM1.equals("0")) {
                    Double i = Double.parseDouble(REQUEST1);
                    Double i2 = Double.parseDouble(ENDNUM1);
                    Double i3 = i2 / i*100 ;
                    @SuppressLint({"NewApi", "LocalSuppress"}) DecimalFormat df2=new DecimalFormat("#.#");
                    Log.d("REQUEST1ENDNUM1", "runi: "+i+"   -i2="+i2+"--i3="+i3);

//                    tv_rrcsucessnum.setText(df2.format(i3) + "%");
//                    tv_rrcsucessnum.setText("444");
                } else {
//                    tv_rrcsucessnum.setText("0%");
                }
//                tv_imsiNum.setText(IMSINUM1);
                if (!TextUtils.isEmpty(IMSINUM1) && !TextUtils.isEmpty(ENDNUM1) && !REQUEST1.equals("0") && !IMSINUM1.equals("0")) {
                    int i = Integer.parseInt(IMSINUM1);
                    int i2 = Integer.parseInt(ENDNUM1);
                    int i3 = i2 / i * 100;
                    @SuppressLint({"NewApi", "LocalSuppress"}) DecimalFormat df2=new DecimalFormat("#.#");

//                    tv_imsiNumsucess.setText(i3+ "%");

                } else {
//                    tv_imsiNumsucess.setText("0%");
                }
                if (!TextUtils.isEmpty(TBTYPE1)){
                    if (TBTYPE1.equals("0")){
                        tv_tbtype.setText("????????????");
                    }else {
                        tv_tbtype.setText("GPS??????");
                    }
                }
                if (!TextUtils.isEmpty(TBZT1)){
                    if (TBZT1.equals("0")){
                        tv_tbzt.setText("GPS????????????");
                    }
                    if (TBZT1.equals("1")){
                        tv_tbzt.setText("??????????????????");
                    }
                    if (TBZT1.equals("2")){
                        tv_tbzt.setText("?????????");
                    }
                    if (TBZT1.equals("3")){
                        tv_tbzt.setText("GPS??????");
                    }
                    if (TBZT1.equals("4")){
                        tv_tbzt.setText("????????????");
                    }
                }

//            }
//        }, 5000);
    }

}
