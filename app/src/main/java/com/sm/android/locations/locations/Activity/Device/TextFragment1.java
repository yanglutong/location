package com.sm.android.locations.locations.Activity.Device;

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

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sm.android.locations.locations.Base.BaseFragment;
import com.sm.android.locations.locations.R;
import com.sm.android.locations.locations.Utils.MainUtils.DeviceUtils;
import com.sm.android.locations.locations.Utils.MainUtils.MainUtils;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.sm.android.locations.locations.Activity.Device.TextFragment3.ridRepeat4;
import static com.sm.android.locations.locations.Constant.Constant.BAND1;
import static com.sm.android.locations.locations.Constant.Constant.BLACKLISTSB1;
import static com.sm.android.locations.locations.Constant.Constant.BLACKLISTSB2;
import static com.sm.android.locations.locations.Constant.Constant.BOARDTEMPERATURE1;
import static com.sm.android.locations.locations.Constant.Constant.CELLID1;
import static com.sm.android.locations.locations.Constant.Constant.DBM1;
import static com.sm.android.locations.locations.Constant.Constant.DEVICENUMBER1;
import static com.sm.android.locations.locations.Constant.Constant.DK1;
import static com.sm.android.locations.locations.Constant.Constant.DK2;
import static com.sm.android.locations.locations.Constant.Constant.DWON1;
import static com.sm.android.locations.locations.Constant.Constant.ENDNUM1;
import static com.sm.android.locations.locations.Constant.Constant.GZMS1;
import static com.sm.android.locations.locations.Constant.Constant.HARDWAREVERSION1;
import static com.sm.android.locations.locations.Constant.Constant.IMSINUM1;
import static com.sm.android.locations.locations.Constant.Constant.IP1;
import static com.sm.android.locations.locations.Constant.Constant.MACADDRESS1;
import static com.sm.android.locations.locations.Constant.Constant.PCI1;
import static com.sm.android.locations.locations.Constant.Constant.PLMN1;
import static com.sm.android.locations.locations.Constant.Constant.REQUEST1;
import static com.sm.android.locations.locations.Constant.Constant.SBZQ1;
import static com.sm.android.locations.locations.Constant.Constant.SHUAIJIAN1;
import static com.sm.android.locations.locations.Constant.Constant.SNNUMBER1;
import static com.sm.android.locations.locations.Constant.Constant.SOFTWAREVERSION1;
import static com.sm.android.locations.locations.Constant.Constant.TAC1;
import static com.sm.android.locations.locations.Constant.Constant.TBTYPE1;
import static com.sm.android.locations.locations.Constant.Constant.TBZT1;
import static com.sm.android.locations.locations.Constant.Constant.TYPE1;
import static com.sm.android.locations.locations.Constant.Constant.UBOOTVERSION1;
import static com.sm.android.locations.locations.Constant.Constant.UEIMSI;
import static com.sm.android.locations.locations.Constant.Constant.UEMAX1;
import static com.sm.android.locations.locations.Constant.Constant.UEMAXOF1;
import static com.sm.android.locations.locations.Constant.Constant.UEMAXOF2;
import static com.sm.android.locations.locations.Constant.Constant.UP1;
import static com.sm.android.locations.locations.Constant.Constant.ZENGYI1;
import static com.sm.android.locations.locations.Constant.Constant.ZHZQ1;

public class TextFragment1 extends BaseFragment {
    RecyclerView recyclerView;
    RyImsiAdapter ryImsiAdapter;
    View view;
    TextView tv_plmn, tv_up, tv_down, tv_band, tv_dk, tv_tac, tv_pci, tv_cid, tv_max, tv_type;//小区信息
    TextView tv_ms, tv_zq, tv_ueimsi, tv_sbzq, tv_glkg, tvmax;//定位模式
    TextView tv_rrcnum, tv_rrcsucess, tv_rrcsucessnum, tv_imsiNum, tv_imsiNumsucess;
    TextView tv_zy, tv_sj;
    TextView tv_tbtype, tv_tbzt;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100001) {//无线正确

            }
        }
    };

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
        if (isVisibleToUser) {
            // 相当于onResume()方法
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    clear();
                    getData();
                }
            }, 100);
        } else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    clear();
                    getData();
                }
            }, 100);

        }
    }

    @Override
    public void initData() {
        findViews();
        getData();
    }

    private void findViews() {
//        TextView tv_plmn, tv_up, tv_down, tv_band, tv_dk, tv_tac, tv_pci, tv_cid, tv_max, tv_type;//小区信息
//        TextView tv_ms, tv_zq, tv_ueimsi, tv_sbzq, tv_glkg, tvmax;//定位模式
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

        recyclerView = view.findViewById(R.id.ryimsi);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        //定位模式
        tv_ms = view.findViewById(R.id.tv_ms);
        tv_zq = view.findViewById(R.id.tv_zq);
        tv_ueimsi = view.findViewById(R.id.tv_ueimsi);
        tv_sbzq = view.findViewById(R.id.tv_sbzq);
        tv_glkg = view.findViewById(R.id.tv_glkg);
        tvmax = view.findViewById(R.id.tvmax);
        //增益
        tv_zy = view.findViewById(R.id.tv_zy);
        tv_sj = view.findViewById(R.id.tv_sj);


//        tv_rrcnum,tv_rrcsucess,tv_rrcsucessnum,tv_imsiNum,tv_imsiNumsucess
        tv_rrcnum = view.findViewById(R.id.tv_rrcnum);
        tv_rrcsucess = view.findViewById(R.id.tv_rrcsucess);
        tv_rrcsucessnum = view.findViewById(R.id.tv_rrcsucessnum);
        tv_imsiNum = view.findViewById(R.id.tv_imsiNum);
        tv_imsiNumsucess = view.findViewById(R.id.tv_imsiNumsucess);

        tv_tbtype = view.findViewById(R.id.tv_tbtype);
        tv_tbzt = view.findViewById(R.id.tv_tbzt);
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
        //
        tv_ms.setText(GZMS1);
        tv_zq.setText(ZHZQ1);
        tv_ueimsi.setText(UEIMSI);
        tv_sbzq.setText(SBZQ1);
        tv_glkg.setText(UEMAXOF1);
        tvmax.setText(UEMAX1);
        //获取小区信息
        view.findViewById(R.id.bt_getxiaoqu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeviceUtils.SelectQury(1, 1);
//        DeviceUtils.SelectQury(2, 1);
//        DeviceUtils.SelectQury(3, 1);
//        DeviceUtils.SelectQury(4, 1);//小区状态
//        DeviceUtils.SelectQury(5, 1);
//        DeviceUtils.SelectQury(6, 1);
//        MainUtils.getType(IP1);//查询状态
            }
        });
        //获取小区状态
        view.findViewById(R.id.bt_zhuangtai).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                DeviceUtils.SelectQury(1, 1);//获取小区
//                DeviceUtils.SelectQury(2, 1);//小区定位模式
//                DeviceUtils.SelectQury(3, 1);//功率
                DeviceUtils.SelectQury(4, 1);
//        DeviceUtils.SelectQury(5, 1);
//                DeviceUtils.SelectQury(7, 1);
//        MainUtils.getType(IP1);//查询状态
            }
        });
        //获取黑名单列表
        view.findViewById(R.id.bt_blacklist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                DeviceUtils.SelectQury(1, 1);//获取小区
//        DeviceUtils.SelectQury(2, 1);//小区定位模式
//        DeviceUtils.SelectQury(3, 1);
//        DeviceUtils.SelectQury(4, 1);
        DeviceUtils.SelectQury(5, 1);//查询黑名单
//        DeviceUtils.SelectQury(6, 1);
//        MainUtils.getType(IP1);//查询状态
            }
        });

        //获取定位模式
        view.findViewById(R.id.bt_location_type).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                DeviceUtils.SelectQury(1, 1);//获取小区
                DeviceUtils.SelectQury(2, 1);//小区定位模式
//        DeviceUtils.SelectQury(3, 1);
//        DeviceUtils.SelectQury(4, 1);
//        DeviceUtils.SelectQury(5, 1);
//        DeviceUtils.SelectQury(6, 1);
//        MainUtils.getType(IP1);//查询状态
            }
        });
        //获取功率
        view.findViewById(R.id.bt_gonglv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                DeviceUtils.SelectQury(1, 1);//获取小区
//                DeviceUtils.SelectQury(2, 1);//小区定位模式
        DeviceUtils.SelectQury(3, 1);//功率
//        DeviceUtils.SelectQury(4, 1);
//        DeviceUtils.SelectQury(5, 1);
//        DeviceUtils.SelectQury(6, 1);
//        MainUtils.getType(IP1);//查询状态
            }
        });



        //获取同步
        view.findViewById(R.id.tv_tbtype).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                DeviceUtils.SelectQury(1, 1);//获取小区
//                DeviceUtils.SelectQury(2, 1);//小区定位模式
//                DeviceUtils.SelectQury(3, 1);//功率
//        DeviceUtils.SelectQury(4, 1);
//        DeviceUtils.SelectQury(5, 1);
        DeviceUtils.SelectQury(7, 1);
//        MainUtils.getType(IP1);//查询状态
            }
        });
        //随机接入
        view.findViewById(R.id.bt_sjjieru).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                DeviceUtils.SelectQury(1, 1);//获取小区
//                DeviceUtils.SelectQury(2, 1);//小区定位模式
//                DeviceUtils.SelectQury(3, 1);//功率
//        DeviceUtils.SelectQury(4, 1);
//        DeviceUtils.SelectQury(5, 1);
                DeviceUtils.SelectQury(6, 1);//随机接入
//        MainUtils.getType(IP1);//查询状态
            }
        });


        handler.post(new Runnable() {
            @Override
            public void run() {
                Log.d("delayTAG", "---");
                getData();
                handler.postDelayed(this, 1000);
            }
        });
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

        tv_zy.setText("");
        tv_sj.setText("");
        tv_rrcnum.setText("");
        tv_rrcsucess.setText("");
        tv_rrcsucessnum.setText("");
        tv_imsiNum.setText("");
        tv_imsiNumsucess.setText("");
        tv_tbzt.setText("");
        tv_tbtype.setText("");
//        tv_rrcnum,tv_rrcsucess,tv_rrcsucessnum,tv_imsiNum,tv_imsiNumsucess
        BLACKLISTSB1.clear();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < BLACKLISTSB1.size(); i++) {
            list.add(i);

        }
        //定位模式黑名单
        ryImsiAdapter = new RyImsiAdapter(mContext, list, BLACKLISTSB1);
        recyclerView.setAdapter(ryImsiAdapter);

    }

    private void getData() {
//        DeviceUtils.SelectQury(1, 1);
//        DeviceUtils.SelectQury(2, 1);
//        DeviceUtils.SelectQury(3, 1);
//        DeviceUtils.SelectQury(4, 1);
//        DeviceUtils.SelectQury(5, 1);
//        DeviceUtils.SelectQury(6, 1);
//        MainUtils.getType(IP1);//查询状态
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
//定位模式
                tv_ms = view.findViewById(R.id.tv_ms);
                tv_zq = view.findViewById(R.id.tv_zq);
                tv_ueimsi = view.findViewById(R.id.tv_ueimsi);
                tv_sbzq = view.findViewById(R.id.tv_sbzq);
                tv_glkg = view.findViewById(R.id.tv_glkg);
                tvmax = view.findViewById(R.id.tvmax);
                //增益
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
                    //定位模式黑名单
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

                tv_zy.setText(ZENGYI1);
                tv_sj.setText(SHUAIJIAN1);
                //        tv_rrcnum,tv_rrcsucess,tv_rrcsucessnum,tv_imsiNum,tv_imsiNumsucess
                tv_rrcnum.setText(REQUEST1);
                tv_rrcsucess.setText(ENDNUM1);
                if (!TextUtils.isEmpty(REQUEST1) && !TextUtils.isEmpty(ENDNUM1) && !REQUEST1.equals("0") && !ENDNUM1.equals("0")) {
                    Double i = Double.parseDouble(REQUEST1);
                    Double i2 = Double.parseDouble(ENDNUM1);
                    Double i3 = i2 / i*100 ;
                    @SuppressLint({"NewApi", "LocalSuppress"}) DecimalFormat df2=new DecimalFormat("#.#");
                    Log.d("REQUEST1ENDNUM1", "runi: "+i+"   -i2="+i2+"--i3="+i3);

                    tv_rrcsucessnum.setText(df2.format(i3) + "%");
//                    tv_rrcsucessnum.setText("444");
                } else {
                    tv_rrcsucessnum.setText("0%");
                }
                tv_imsiNum.setText(IMSINUM1);
                if (!TextUtils.isEmpty(IMSINUM1) && !TextUtils.isEmpty(ENDNUM1) && !REQUEST1.equals("0") && !IMSINUM1.equals("0")) {
                    int i = Integer.parseInt(IMSINUM1);
                    int i2 = Integer.parseInt(ENDNUM1);
                    int i3 = i2 / i * 100;
                    @SuppressLint({"NewApi", "LocalSuppress"}) DecimalFormat df2=new DecimalFormat("#.#");

                    tv_imsiNumsucess.setText(i3+ "%");

                } else {
                    tv_imsiNumsucess.setText("0%");
                }
                if (!TextUtils.isEmpty(TBTYPE1)){
                    if (TBTYPE1.equals("0")){
                        tv_tbtype.setText("空口同步");
                    }else {
                        tv_tbtype.setText("GPS同步");
                    }
                }
                if (!TextUtils.isEmpty(TBZT1)){
                    if (TBZT1.equals("0")){
                        tv_tbzt.setText("GPS同步成功");
                    }
                    if (TBZT1.equals("1")){
                        tv_tbzt.setText("空口同步成功");
                    }
                    if (TBZT1.equals("2")){
                        tv_tbzt.setText("未同步");
                    }
                    if (TBZT1.equals("3")){
                        tv_tbzt.setText("GPS失步");
                    }
                    if (TBZT1.equals("4")){
                        tv_tbzt.setText("空口失步");
                    }
                }

//            }
//        }, 5000);
    }

}
