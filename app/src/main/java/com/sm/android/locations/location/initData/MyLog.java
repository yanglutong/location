package com.sm.android.locations.location.initData;

import android.util.Log;

public class MyLog {
    public static void i(String tag, String msg){
        Log.i(tag, "  "+msg);
    }
    public static void e(String tag, String msg){
        Log.e(tag, "  "+msg);
    }

    public static void send(String type,String socketHeader) {
        Log.e(type, "发送的类型:"+type+"   内容:"+socketHeader);
    }
    public static void accept(String type,String socketHeader,String string) {
        Log.e(type, "接收的类型:"+type+"  二进制内容:"+socketHeader+"  ascii内容:"+string);
    }
}
