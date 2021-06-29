package com.sm.android.locations.location.initData;
 
import java.sql.Timestamp;

/*时间戳工具类*/
public class TimeStampExample {
   /*获取时间戳10位*/
    public static long getTimestamp10(){
        //获取时间戳的三种方式
//         long time2 = Calendar.getInstance().getTimeInMillis() / 1000;
//         long time3 = new Date().getTime() / 1000;
        return System.currentTimeMillis() / 1000;
    }
    /*获取时间戳13位*/
    public static long getTimestamp13(){
        //获取时间戳的三种方式
//         long time2 = Calendar.getInstance().getTimeInMillis();
//         long time3 = new Date().getTime();
        return System.currentTimeMillis();
    }

    /*日期转译时间戳*/
    public static Timestamp getDataParseTimestamp(long l){
        Timestamp timestamp = new Timestamp(l);
        return timestamp;
    }

}