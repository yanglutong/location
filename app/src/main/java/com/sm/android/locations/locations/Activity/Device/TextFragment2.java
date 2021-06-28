package com.sm.android.locations.locations.Activity.Device;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sm.android.locations.locations.Base.BaseFragment;
import com.sm.android.locations.locations.R;
import com.sm.android.locations.locations.Utils.MainUtils.DeviceUtils;
import com.sm.android.locations.locations.Utils.MainUtils.MainUtils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import static com.sm.android.locations.locations.Constant.Constant.DEVICENUMBER1;
import static com.sm.android.locations.locations.Constant.Constant.DEVICENUMBER2;
import static com.sm.android.locations.locations.Constant.Constant.GFFLAG1;
import static com.sm.android.locations.locations.Constant.Constant.HARDWAREVERSION1;
import static com.sm.android.locations.locations.Constant.Constant.HARDWAREVERSION2;
import static com.sm.android.locations.locations.Constant.Constant.SOFTWAREVERSION1;
import static com.sm.android.locations.locations.Constant.Constant.SOFTWAREVERSION2;
import static com.sm.android.locations.locations.Constant.Constant.SNNUMBER1;
import static com.sm.android.locations.locations.Constant.Constant.SNNUMBER2;
import static com.sm.android.locations.locations.Constant.Constant.MACADDRESS1;
import static com.sm.android.locations.locations.Constant.Constant.MACADDRESS2;
import static com.sm.android.locations.locations.Constant.Constant.UBOOTVERSION1;
import static com.sm.android.locations.locations.Constant.Constant.UBOOTVERSION2;
import static com.sm.android.locations.locations.Constant.Constant.BOARDTEMPERATURE1;
import static com.sm.android.locations.locations.Constant.Constant.BOARDTEMPERATURE2;

public class TextFragment2 extends BaseFragment {
    private static String TAG = "TextFragment0";
    DecimalFormat df2;
    //            NumberFormat.getInstance(Locale.GERMAN);
    View view;
    TextView tv_devicenumber, tv_hardware, tv_software, tv_sn, tv_mac, tvuboot, tvboard;
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
            view = LayoutInflater.from(mContext).inflate(R.layout.deviceinfragment2, null);
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
        df2 = new DecimalFormat("####.00");
        findViews();
        clear();
        getData();
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

    private void findViews() {
        tv_devicenumber = view.findViewById(R.id.tv_devicenumber);
        tv_hardware = view.findViewById(R.id.tv_hardware);
        tv_software = view.findViewById(R.id.tv_software);
        tv_sn = view.findViewById(R.id.tv_sn);
        tv_mac = view.findViewById(R.id.tv_mac);
        tvuboot = view.findViewById(R.id.tvuboot);
        tvboard = view.findViewById(R.id.tvboard);

        tv_devicenumber.setText(DEVICENUMBER2);
        tv_hardware.setText(HARDWAREVERSION2);
        tv_software.setText(SOFTWAREVERSION2);
        tv_sn.setText(SNNUMBER2);
        tv_mac.setText(MACADDRESS2);
        tvuboot.setText(UBOOTVERSION2);
        tvboard.setText(BOARDTEMPERATURE2);
        if (!TextUtils.isEmpty(BOARDTEMPERATURE2)) {
            Double i = Double.parseDouble(BOARDTEMPERATURE2);

            tvboard.setText(df2.format(i));
        }
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

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // 相当于onResume()方法
            Log.d(TAG, "onResume: " + "执行了1");
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

    private void clear() {
        tv_devicenumber.setText("");
        tv_hardware.setText("");
        tv_software.setText("");
        tv_sn.setText("");
        tv_mac.setText("");
        tvuboot.setText("");
        tvboard.setText("");
    }

    private void getData() {
        Log.d(TAG, "onResume: " + "执行了2");
        // 相当于onpause()方法
        DeviceUtils.Select(0, 2);//设备号
        DeviceUtils.Select(1, 2);//硬件版本
        DeviceUtils.Select(2, 2);//软件版本
        DeviceUtils.Select(3, 2);//SN号
        DeviceUtils.Select(4, 2);//MAC地址
        DeviceUtils.Select(5, 2);//uboot版本号
        DeviceUtils.Select(6, 2);//板卡温度


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv_devicenumber.setText(DEVICENUMBER2);
                tv_hardware.setText(HARDWAREVERSION2);
                tv_software.setText(SOFTWAREVERSION2);
                tv_sn.setText(SNNUMBER2);
                tv_mac.setText(MACADDRESS2);
                tvuboot.setText(UBOOTVERSION2);
                tvboard.setText(BOARDTEMPERATURE2);
                if (!TextUtils.isEmpty(BOARDTEMPERATURE2)) {
                    Double i = Double.parseDouble(BOARDTEMPERATURE2);

                    tvboard.setText(df2.format(i));
                }

            }
        }, 5000);
    }
}
