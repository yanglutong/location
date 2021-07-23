package com.sm.android.locations.location.initData;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Test02 {

    /**
     * 把长报文拆分成多个子报文
     * 拆分规则：以begin开始，以end结尾
     * @param info 待拆分的长报文
     * @param begin 开始字符
     * @param end 结尾字符
     * @return 符合规则的子报文集合
     */
    public static List<String> getChildMsgByParent(String info, String begin, String end){
        //通过起始字符拆分成数组
        String[] split = info.split(begin);
        List<String> list = new ArrayList<>();
        //遍历，从第二个元素开始取值（第一个元素为无效元素）
        for (int i = 1; i < split.length; i++) {
            String str = split[i].substring(0,split[i].lastIndexOf(end)+end.length());
            if (str.length() > end.length()) {
                list.add(begin+str);
            }
        }
        return list;
    }

    public static String deleteCharString0(String sourceString, char chElemData) {
        String deleteString = "";
        StringBuffer sf = new StringBuffer();
        for (int i = 0; i < sourceString.length(); i++) {
            if (sourceString.charAt(i) != chElemData) {
                deleteString += sourceString.charAt(i);
            }
        }
        return deleteString;
    }
    public static void main(String[] args) {
        String info = "00007e0552000102ed8101b13600000146000004fe0000015e000027100000017f0000057d0000314600000a500000314700000017a00000000000000f146000000000000f147000000000000f14e0000000032106e0100000000000000010000002c000000000000000000000000000000000000007e05530001023182001f06000002220900000316000000031700000003180000000319000000038f00025c6e0000";
        List<String> list = getChildMsgByParent(info, "7e", "6e");
        for (String s : list) {
            System.out.println(s);
        }
    }
    public static String startToEnd(String  body,String startWith,String endWith){
        List<String> list = Test02.getChildMsgByParent(body, startWith, endWith);
        StringBuffer buffer = new StringBuffer();
        for (String s1 : list) {
            buffer.append(s1);
        }
        String im=buffer.toString();
        char[] toCharArray = endWith.toCharArray();
        for (char c : toCharArray) {
            im=Test02.deleteCharString0(im, c);
        }
        return im;
    }
}
