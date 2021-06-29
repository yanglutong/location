package com.sm.android.locations.location.Utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import android.util.Log;
import android.view.View;


import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.blankj.utilcode.util.DeviceUtils;
import com.mylhyl.circledialog.CircleDialog;
import com.sm.android.locations.location.Activity.Main.Objects.States;
import com.sm.android.locations.location.Utils.MainUtils.MainUtils;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.SpBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.ZmBeanlinshi;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


import static android.content.Context.WIFI_SERVICE;
import static com.sm.android.locations.location.Constant.Constant.IP1;
import static com.sm.android.locations.location.Constant.Constant.IP2;

public class MyUtils {
    private static Context context;
    private static boolean typeAppup = false;//是否强制更新

    public static void getPermissions(Activity mainActivity) {
        mPermissionList.clear();
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(mainActivity, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permissions[i]);
            }
        }
        if (mPermissionList.isEmpty()) {//未授予的权限为空，表示都授予了
//            Toast.makeText(LoginActivity.this,"已经授权",Toast.LENGTH_LONG).show();
        } else {//请求权限方法
            String[] permissions = mPermissionList.toArray(new String[mPermissionList.size()]);//将List转为数组
            ActivityCompat.requestPermissions(mainActivity, permissions, MY_PERMISSIONS_REQUEST_CALL_CAMERA);
        }
    }


    //权限
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private static final int MY_PERMISSIONS_REQUEST_CALL_CAMERA = 2;
    // 声明一个集合，在后面的代码中用来存储用户拒绝授权的权
    public static List<String> mPermissionList = new ArrayList<>();
    public static String[] permissions = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.CHANGE_WIFI_STATE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.FOREGROUND_SERVICE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.ACCESS_NETWORK_STATE,
    };//申请的权限

    public static String getWifiName(Activity activity) {
        WifiManager my_wifiManager = ((WifiManager) activity.getApplicationContext().getSystemService(WIFI_SERVICE));
        assert my_wifiManager != null;
        WifiInfo wifiInfo = my_wifiManager.getConnectionInfo();
        String ssid = wifiInfo.getSSID();
        int networkId = wifiInfo.getNetworkId();
        @SuppressLint("MissingPermission") List<WifiConfiguration> configuredNetworks = my_wifiManager.getConfiguredNetworks();
        for (WifiConfiguration wifiConfiguration : configuredNetworks) {
            if (wifiConfiguration.networkId == networkId) {
                ssid = wifiConfiguration.SSID.replace("\"", "");
                Log.d("pppppa", "init: " + ssid);
                return ssid;

            }
        }
        return ssid;
    }

    public static List<ZmBeanlinshi> removeD(List<ZmBeanlinshi> list) {
// 从list中索引为0开始往后遍历
        for (int i = 0; i < list.size() - 1; i++) {
            // 从list中索引为 list.size()-1 开始往前遍历
            for (int j = list.size() - 1; j > i; j--) {
                // 进行比较
                if (list.get(j).getImsi().equals(list.get(i).getImsi())) {
                    // 去重
                    list.remove(j);
                }
            }
        }
        return list;
    }

    /**
     * 去重
     *
     * @param list
     * @return
     */
    public static List<States> removeDuplicate(List<States> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(j).getImsi().equals(list.get(i).getImsi())) {
                    list.remove(j);
                }
            }
        }
        return list;
    }

    /**
     * 去重
     *
     * @param list
     * @return
     */
    public static List<String> removeDuplicateSpBean(List<SpBean> list) {
        List<String> list1 = new ArrayList<>();
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(j).getDown().equals(list.get(i).getDown())) {
                    list.remove(j);
                }
            }
        }
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                list1.add(list.get(i).getDown());
            }
        }
        return list1;
    }

    public static List<States> removeDuplicatelinshi(List<States> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(j).getImsi().equals(list.get(i).getImsi())) {
                    list.remove(j);
                }
            }
        }
        return list;
    }

    //停止定位
    public static void stopLocations(Context context, int ip) {
        if (ip == 1) {
            new CircleDialog.Builder((FragmentActivity) context)
                    .setTitle("")
                    .setText("确定要停止定位1吗?")
                    .setTitleColor(Color.parseColor("#00acff"))
                    .setNegative("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            MainUtils.StopLocation(IP1);
                        }
                    })
                    .setPositive("取消", null)
                    .show();
        }
        if (ip == 2) {
            new CircleDialog.Builder((FragmentActivity) context)
                    .setTitle("")
                    .setText("确定要停止定位2吗?")
                    .setTitleColor(Color.parseColor("#00acff"))
                    .setNegative("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            MainUtils.StopLocation(IP2);
                        }
                    })
                    .setPositive("取消", null)
                    .show();
        }

    }

    public static String YYname(int i) {
        String name = "移动TDD";
        if (i == 1) {
            name = "移动TDD";
            return name;
        }
        if (i == 2) {
            name = "移动FDD";
            return name;
        }
        if (i == 3) {
            name = "联通";
            return name;
        }
        if (i == 4) {
            name = "电信";
            return name;
        }
        return name;
    }

    public static String PLMN(int i) {
        String plmn = "移动TDD";
        if (i == 1) {
            plmn = "46000";
            return plmn;
        }
        if (i == 2) {
            plmn = "46000";
            return plmn;
        }
        if (i == 3) {
            plmn = "46001";
            return plmn;
        }
        if (i == 4) {
            plmn = "46003";
            return plmn;
        }
        return plmn;
    }

    public static String getZC() {
        @SuppressLint("MissingPermission") String macAddress = DeviceUtils.getMacAddress();//获取mac
        String replace = macAddress.replace(":", "");
        String substring1 = replace.substring(0, 2);
        String substring2 = replace.substring(2, 4);
        String substring3 = replace.substring(4, 6);
        String substring4 = replace.substring(6, 8);
        String substring5 = replace.substring(8, 10);
        String substring6 = replace.substring(10, 12);
        BigInteger bigint1 = new BigInteger(substring1, 16);
        int numb1 = bigint1.intValue();
        BigInteger bigint2 = new BigInteger(substring2, 16);
        int numb2 = bigint2.intValue();
        BigInteger bigint3 = new BigInteger(substring3, 16);
        int numb3 = bigint3.intValue();
        BigInteger bigint4 = new BigInteger(substring4, 16);
        int numb4 = bigint4.intValue();
        BigInteger bigint5 = new BigInteger(substring5, 16);
        int numb5 = bigint5.intValue();
        BigInteger bigint6 = new BigInteger(substring6, 16);
        int numb6 = bigint6.intValue();
        Log.d("10进制", "setUser_pwd: " + numb1 + "--" + numb2 + "--" + numb3 + "--" + numb4 + "--" + numb5 + "--" + numb6);
        int el = numb1 + numb2 + numb3 + numb4 + numb5;//和
        int d1 = (el - numb1) % 10;
        int d2 = (el - numb2) % 10;
        int d3 = (el - numb3) % 10;
        int d4 = (el - numb4) % 10;
        int d5 = (el - numb5) % 10;
        int d6 = (el - numb6) % 10;
        Log.d("10进制加前五位", "setUser_pwd: " + d1 + "" + d2 + "" + d3 + "" + d4 + "" + d5 + "" + d6);
        return String.valueOf(d1) + String.valueOf(d2) + String.valueOf(d3) + String.valueOf(d4) + String.valueOf(d5) + String.valueOf(d6);
//        ed_zc.setText(bjzcm);
    }
}
