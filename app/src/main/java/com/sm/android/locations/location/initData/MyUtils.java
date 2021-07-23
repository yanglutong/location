package com.sm.android.locations.location.initData;

import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.AddPararBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.ZmBean;

import java.util.List;

/**
 * 报文工具类
 * @description
 * @param
 * @return
 * @author lutong
 * @time 2021/5/28 18:17
 */

public class MyUtils {
    /**获取时间戳
     * @description
     * @param
     * @return
     * @author lutong
     * @time 2021/5/28 18:16
     */
    public static String getTimeTimeStamp(){
        long time = TimeStampExample.getDataParseTimestamp(TimeStampExample.getTimestamp10()).getTime();
        int i = new Long(time).intValue();
        String s = ParseSystemUtil.hex10To16(i);
        return s;
    }

    /**
     * 获取相关报文协议的请求头
     * @description
     * @param
     * @return
     * @author lutong
     * @time 2021/6/3 15:11
     */
    public static String getSocketHeader(String content){
        return "00ffff0000000000"+ParseSystemUtil.hex10To16(content.length()/2)+"000000000000000000000000000000000000000000000000"+MyUtils.getTimeTimeStamp()+"0000000000000000"+content;
    }


    /**
     * 将报文类型和内容转为16进制的字符表现方式
     * @description
     * @param
     * @return
     * @author lutong
     * @time 2021/6/3 15:15
     */

    public static String getToHexString(String type, String body) {
        return JK.toHexString(type+body);//将Utf-8转为16进制字符
    }



    /**
     * @description 是否为数字
//     * @param 是否为数字
     * @return
     * @author lutong
     * @time 2021/6/15 15:50
     */
    public static String getNumber(String str){
        char[] toCharArray = str.toCharArray();
        StringBuffer sf = new StringBuffer();
        for (int i = 0; i <toCharArray.length ; i++) {
            if(Character.isDigit(toCharArray[i])){
                sf.append(toCharArray[i]);
            }
        }
        return sf.toString();
    }
    /**
     * 去重
     *
     * @param list
     * @return
     */
    public static List<ZmBean> removeDuplicate(List<ZmBean> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(j).getImsi().equals(list.get(i).getImsi())) {
                    list.remove(j);
                }
            }
        }
        return list;
    }

    public static List<AddPararBean> removeDuplicate2(List<AddPararBean> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(j).getImsi().equals(list.get(i).getImsi())) {
                    list.remove(j);
                }
            }
        }
        return list;
    } public static List<String> removeDuplicate3(List<String> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(j).equals(list.get(i))) {
                    list.remove(j);
                }
            }
        }
        return list;
    }

    //方法一：用JAVA自带的函数
    public static boolean isNumeric(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
