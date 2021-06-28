package com.sm.android.locations.locations.Activity.Tail;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.sm.android.locations.locations.Activity.Login.LoginActivity;
import com.sm.android.locations.locations.Base.BaseActivity;
import com.sm.android.locations.locations.R;
import com.sm.android.locations.locations.Utils.MyUtils;

import java.util.List;

import static com.sm.android.locations.locations.App.App.context;

public class TailActivity extends BaseActivity {
    ImageView iv;
    Animation animation = null;

    @Override
    protected void initQx() {

    }

    @Override
    protected void init() {

//        WifiManager wm = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//        WifiInfo info = wm.getConnectionInfo();
//        //获取wifi名称
//        String ssid = info.getSSID().replace("\"", "");
//        //获取mac地址
//        String MacAddr = info.getMacAddress();
//        //获取当前连接速度
//        int linkSpeed = info.getLinkSpeed();
//        //获取ip地址
//        int ipAddr = info.getIpAddress();
//        Log.d("ooop", "init: "+     info.getSSID().replace("\"", ""));
//
//        ConnectivityManager ctm = (ConnectivityManager) context.
//                getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = ctm.getActiveNetworkInfo();
//        String ssids = networkInfo.getExtraInfo();
//        Log.d("0000a", "init: "+ssids);
//
//


//
//
//
//
//
        //执行动画
        animation = AnimationUtils.loadAnimation(this,
                R.anim.anim_set);
        iv.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(TailActivity.this, LoginActivity.class));
                finish();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }

    @SuppressLint("NewApi")
    @Override
    protected void findViews() {
        iv = findViewById(R.id.iv);
        getPermissions();
        isIgnoringBatteryOptimizations();
        requestIgnoreBatteryOptimizations();
    }

    @Override
    protected int getView() {
        return R.layout.activity_tail;
    }
}
