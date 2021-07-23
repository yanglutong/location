package com.sm.android.locations.location.Activity.Device;

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

import com.sm.android.locations.location.Base.BaseFragment;
import com.sm.android.locations.location.R;
import com.sm.android.locations.location.Utils.MainUtils.DeviceUtils;
import com.sm.android.locations.location.initData.CommandUtils;
import com.sm.android.locations.location.initData.PLMN;
import com.sm.android.locations.location.sos.SOSActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.sm.android.locations.location.Constant.Constant.DEVICENUMBER1;
import static com.sm.android.locations.location.Constant.Constant.HARDWAREVERSION1;
import static com.sm.android.locations.location.Constant.Constant.SOFTWAREVERSION1;
import static com.sm.android.locations.location.Constant.Constant.SNNUMBER1;
import static com.sm.android.locations.location.Constant.Constant.MACADDRESS1;
import static com.sm.android.locations.location.Constant.Constant.UBOOTVERSION1;
import static com.sm.android.locations.location.Constant.Constant.BOARDTEMPERATURE1;

public class TextFragment0 extends BaseFragment {
    private String SN1="";//采集设备编号  字符最大长度 16
    private String MAC1="";//采集设备MAC地址  17
    private String FW1="";//软件版本号  10
    private boolean isHidde=false;
    private String BAND1="";//Band
    private String PLMN1="";//PLMN

    private String RF1="";//射频状态
    private String GPS1="";//GPS状态
    private String CELL1="";//小区状态  小区是否建立成功：0：未建立 1:建立成功
    private String TMP1="";//板卡温度
    private  String CNM1="";// 空口同步状态
    private  String SYNC1="";// 同步状态
    private  String RIP1="";// 干扰测量
    private static String TAG = "TextFragment0";
    DecimalFormat df2;
    View view;
    TextView tv_SN, tv_MAC, tv_FW, SYNC, RIP, RF, CNM,TMP;
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
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.deviceinfragment0, null);
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
    public void initData() {
//        df2 = new DecimalFormat("####.00");
        findViews();
//        clear();
//        getData();
//
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
    public void onStart() {
        super.onStart();
        clear();
    }

    @Override
    public void onPause() {
        super.onPause();
        clear();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResumeA: " + "执行了4");
        clear();
//        getData();
    }

    private void getData() {
        tv_SN.setText(SN1);
        tv_MAC.setText(MAC1);
        tv_FW.setText(FW1);
        CNM.setText(CNM1);
        SYNC.setText(SYNC1);
        RF.setText(RF1);
        RIP.setText(RIP1);
        TMP.setText(TMP1);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isHidde=true;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void findViews() {

        tv_SN = view.findViewById(R.id.tv_SN);
        tv_MAC = view.findViewById(R.id.tv_MAC);
        tv_FW = view.findViewById(R.id.tv_FW);
        CNM = view.findViewById(R.id.CNM);
        SYNC = view.findViewById(R.id.SYNC);
        RF = view.findViewById(R.id.RF);
        RIP = view.findViewById(R.id.RIP);//测量干扰值
        TMP = view.findViewById(R.id.TMP);


        tv_SN.setText("");
        tv_MAC.setText("");
        tv_FW.setText("");
        RIP.setText("");
        RF.setText("");
        SYNC.setText("");
        CNM.setText("");
        TMP.setText("");

        //获取设备号
        view.findViewById(R.id.bt_SN).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CommandUtils.type.equals("")){
                    return;
                }
                tv_SN.setText(CommandUtils.SN);
                SN1=tv_SN.getText().toString();
            }
        });
        //获取硬件版本号
        view.findViewById(R.id.bt_MAC).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(CommandUtils.type.equals("")){
                    return;
                }
                tv_MAC.setText(CommandUtils.MAC);
                MAC1=tv_MAC.getText().toString();
            }
        });

        //获取软件版本号
        view.findViewById(R.id.bt_FW).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CommandUtils.type.equals("")){
                    return;
                }
                tv_FW.setText(CommandUtils.FW);
                FW1=tv_FW.getText().toString();
            }
        });
        //同步状态
//        基站是否同步成功, 0：同步失败 1：同步成功
        view.findViewById(R.id.bt_SYNC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CommandUtils.type.equals("")){
                    return;
                }
                if(CommandUtils.SYNC.equals("")){
                    SYNC.setText(CommandUtils.SYNC);
                }else{
                    if(Integer.parseInt(CommandUtils.SYNC)==0){
                        SYNC.setText("同步成功");
                    }
                    if(Integer.parseInt(CommandUtils.SYNC)==1){
                        SYNC.setText("同步失败");
                    }
                }
                SYNC1=SYNC.getText().toString();
            }
        });
        //干扰测量值
        //-1 ：小区未建
        //<-140: 接收故障
        //-140< RIP<-90:干扰值正常
        view.findViewById(R.id.bt_RIP).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CommandUtils.type.equals("")){
                    return;
                }
                if(CommandUtils.RIP.equals("")){
                    RIP.setText(CommandUtils.RIP);
                }else{
                    if(Integer.parseInt(CommandUtils.RIP)==-1){
                        RIP.setText("小区未建立"+CommandUtils.RIP);
                    }
                    if(Integer.parseInt(CommandUtils.RIP)<-140){
                        RIP.setText("接收故障"+CommandUtils.RIP);
                    }if(Integer.parseInt(CommandUtils.RIP)>=-140&&Integer.parseInt(CommandUtils.RIP)<=-90){
                        RIP.setText("干扰值正常("+CommandUtils.RIP+")");
                    }
                }
                RIP1=RIP.getText().toString();
            }
        });
        //获取射频状态
        view.findViewById(R.id.bt_RF).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CommandUtils.type.equals("")){
                    return;
                }
                if(CommandUtils.RF.equals("")){
                    RF.setText(CommandUtils.RF);
                }else{
                    if(Integer.parseInt(CommandUtils.RF)==0){//0 关射频  1开启
                        RF.setText("关闭");
                    }
                    if(Integer.parseInt(CommandUtils.RF)==1){//0 关射频  1开启
                        RF.setText("开启");
                    }
                }
                RF1=RF.getText().toString();
            }
        });
        //空口同步状态

        view.findViewById(R.id.bt_CNM).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CommandUtils.type.equals("")){
                    return;
                }
                if(CommandUtils.CNM.equals("")){
                    CNM.setText(CommandUtils.CNM);
                }else{
//                    0：未启用
//                    1：成功
//                    -1：失败

                    if(Integer.parseInt(CommandUtils.CNM)==0){
                        CNM.setText("未启用");
                    }
                    if(Integer.parseInt(CommandUtils.CNM)==1){
                        CNM.setText("成功");
                    }
                    if(Integer.parseInt(CommandUtils.CNM)==-1){
                        CNM.setText("失败");
                    }
                }
                CNM1=CNM.getText().toString();
            }
        });
        view.findViewById(R.id.bt_TMP).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CommandUtils.type.equals("")){
                    return;
                }
                TMP.setText(CommandUtils.TMP);
                TMP1=TMP.getText().toString();
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.d(TAG, "onHiddenChanged: " + hidden);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d(TAG, "setUserVisibleHint: " + "执行了1"+isVisibleToUser);
        if(isVisibleToUser&&isHidde){
            clear();
            SN1=tv_SN.getText().toString();
            MAC1=tv_MAC.getText().toString();
            FW1=tv_FW.getText().toString();
            RIP1=RIP.getText().toString();
            CNM1=CNM.getText().toString();
            RF1=RF.getText().toString();
            SYNC1=SYNC.getText().toString();
            TMP1=TMP.getText().toString();
        }
        if (isVisibleToUser) {
//            clear();
//            // 相当于onResume()方法
////            tv_devicenumber.setText("");
////            tv_hardware.setText("");
////            tv_software.setText("");
////            tv_sn.setText("");
////            tv_mac.setText("");
////            tvuboot.setText("");
////            initData();
////            clear();
////            initView();
//
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    clear();
//                    getData();
//                }
//            }, 100);
//        } else {
////            clear();
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    clear();
//                    getData();
//                }
//            }, 100);
////            getData();
//            Log.d(TAG, "onResumeA: " + "执行了2");
        }
    }

    @Override
    public boolean getUserVisibleHint() {
        boolean userVisibleHint = super.getUserVisibleHint();
        Log.d(TAG, "getUserVisibleHint: " + "执行了" + userVisibleHint);
        return super.getUserVisibleHint();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onResumeA: " + "执行了3");
        clear();
    }

    private void clear() {
        tv_SN.setText("");
        tv_MAC.setText("");
        tv_FW.setText("");
        RIP.setText("");
        CNM.setText("");
        RF.setText("");
        SYNC.setText("");
        TMP.setText("");
    }

//    private void getData() {
//
//                tv_devicenumber.setText(DEVICENUMBER1);
//                tv_hardware.setText(HARDWAREVERSION1);
//                tv_software.setText(SOFTWAREVERSION1);
//                tv_sn.setText(SNNUMBER1);
//                tv_mac.setText(MACADDRESS1);
//                if (!TextUtils.isEmpty(BOARDTEMPERATURE1)) {
//                    Double i = Double.parseDouble(BOARDTEMPERATURE1);
//
//                    tvboard.setText(df2.format(i));
//                }
//                tvuboot.setText(UBOOTVERSION1);
//    }
}
