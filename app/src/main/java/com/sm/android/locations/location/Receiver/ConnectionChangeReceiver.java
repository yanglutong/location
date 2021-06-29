package com.sm.android.locations.location.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class ConnectionChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifiNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

//        if (!mobNetInfo.isConnected() && !wifiNetInfo.isConnected()) {
////            BSToast.showLong(context, "网络不可以用");
//            Log.d("nzq", "onReceive:网络不可以用 ");
//            //改变背景或者 处理网络的全局变量
//        } else {
//            //改变背景或者 处理网络的全局变量
//            Log.d(TAG, "onReceive: 网络可用");
//
//        }
    }
}
