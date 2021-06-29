package com.sm.android.locations.location.initData;
/**
 * @author: 小杨同志
 * @date: 2021/6/1
 */
public class JK {


    /**
     * 16进制直接转换成为字符串 包含\r\n \t

     * @explain

//     * @param hexStr 16进制字符串

     * @return String (字符集：UTF-8)

     */

    public static String fromHexString(String hexString) throws Exception {
// 用于接收转换结果

        String result = "";

// 转大写

        hexString = hexString.toUpperCase();

// 16进制字符

        String hexDigital = "0123456789ABCDEF";

// 将16进制字符串转换成char数组

        char[] hexs = hexString.toCharArray();

// 能被16整除，肯定可以被2整除

        byte[] bytes = new byte[hexString.length() / 2];

        int n;

        for (int i = 0; i < bytes.length; i++) {
            n = hexDigital.indexOf(hexs[2 * i]) * 16 + hexDigital.indexOf(hexs[2 * i + 1]);

            bytes[i] = (byte) (n & 0xff);

        }

// byte[]-->String

        result = new String(bytes, "UTF-8");

        return result;

    }

    /**
     包含\r\n \t
     * 字符串转换成为16进制字符串(大写)

     * @explain 因为java转义字符串在java中有着特殊的意义，

     * 所以当字符串中包含转义字符串，并将其转换成16进制后，16进制再转成String时，会出问题：

     * java会将其当做转义字符串所代表的含义解析出来

     * @param str 字符串(去除java转义字符)

     * @return 16进制字符串

     * @throws Exception

     */

    public static String toHexString(String str) {
// 用于接收转换结果

        String hexString = "";

// 1.校验是否包含特殊字符内容

// java特殊转义符

// String[] escapeArray = {"\b","\t","\n","\f","\r","\'","\"","\\"};

        String[] escapeArray = {"\b","\t","\n","\f","\r"};

// 用于校验参数是否包含特殊转义符
//        boolean flag = false;----1

// 迭代

        for (String esacapeStr : escapeArray) {
// 一真则真

            if (str.contains(esacapeStr)) {
                //如果有包含的就转成自己想要的
                //\t  09  \r\n 0d 0a



//                flag = true;----2
//                break;// 终止循环----3
            }

        }

// 包含特殊字符

//        if (flag) throw new Exception("参数字符串不能包含转义字符！"); ----4

// 16进制字符

        char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

        StringBuilder sb = new StringBuilder();

// String-->byte[]

        byte[] bs = str.getBytes();
        int bit;

        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;

            sb.append(hexArray[bit]);

            bit = bs[i] & 0x0f;

            sb.append(hexArray[bit]);

        }

        hexString = sb.toString();

        return hexString;

    }

//3.测试
//
//    /***
//
//     * @explain
//
//     *@paramargs
//
//     *@throwsException*/
//
//    public static void main(String[] args) throwsException {//其中，\'\"\\ 实际字符串代表：'"\
//
//        String str = "张三&$李四((、，//\'\"\\‘’“”+-*/！!~.。=——？?;；";
//
//        String hexStr=toHexString(str);
//
//        System.out.println(hexStr);
//
//        System.out.println(fromHexString(hexStr));
//
//    }

/*4.说明

    要想将特殊转义符当作字符串来正常解析，需要自己再手动进行转义，比如：*/
    public static void  parse() throws Exception {
        //输出\\

        String str = "PLMN:46000\tTAC:1111\tDLARFCN:37900\tULARFCN:37900\tPCI:111\tBAND:38\tCI:11\r\n";

        String hexStr=toHexString(str);

        System.out.println("1"+hexStr);

        System.out.println("2"+fromHexString(hexStr));//输出\n



//        str = "\r\n";
//
//        hexStr=toHexString(str);
//
//        System.out.println("3"+hexStr);
//
//        System.out.println("4"+fromHexString(hexStr));

//        toHexString()方法中，调用的contains(param)方法，不需要转义，而是将param当作纯字符串来解析，所以能校验通过！
    }

}
