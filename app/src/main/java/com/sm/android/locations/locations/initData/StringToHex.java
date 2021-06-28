package com.sm.android.locations.locations.initData;
/*ascii码转十六进制 十六进制转ascii码*/
public class StringToHex {
      /** 不包含特殊字符 \r\n \t
       * 字符串转十六进制字符串
       *
       * @author 静心事成
       * @param str 源字符串
       * @return 十六进制字符串
       * */
      public static String encodeHex(String str) {
            // 定义变量保存结果
            final StringBuffer hexStr = new StringBuffer();
            // 遍历字符串，直接使用Integer.toHexString返回每个字符的十六进制字符串
            for (int i = 0; i < str.length(); i++)
                  hexStr.append(Integer.toHexString(str.charAt(i)));
            // 返回结果
            return hexStr.toString();
      }




      public static String convertStringToHex(String str){
 
      char[] chars = str.toCharArray();
 
      StringBuffer hex = new StringBuffer();
      for(int i = 0; i < chars.length; i++){
        hex.append(Integer.toHexString((int)chars[i]));
      }
 
      return hex.toString();
      }
 
      public static String convertHexToString(String hex){
 
      StringBuilder sb = new StringBuilder();
      StringBuilder temp = new StringBuilder();
 
      //49204c6f7665204a617661 split into two characters 49, 20, 4c...
      for( int i=0; i<hex.length()-1; i+=2){
          //grab the hex in pairs
          String output = hex.substring(i, (i + 2));
          //convert hex to decimal
          int decimal = Integer.parseInt(output, 16);
          //convert the decimal to character
          sb.append((char)decimal);
 
          temp.append(decimal);
      }
 
      return sb.toString();
      }
 
      //504F533838383834  POS88884
      public static void main(String[] args) {
 
      StringToHex strToHex = new StringToHex();
      System.out.println("\n-----ASCII码转换为16进制 -----");
      String str = "POS88884";
      System.out.println("字符串: " + str);
      String hex = strToHex.convertStringToHex(str);
      System.out.println("转换为16进制 : " + hex);
 
      System.out.println("\n***** 16进制转换为ASCII *****");
      System.out.println("Hex : " + hex);
      System.out.println("ASCII : " + strToHex.convertHexToString(hex));
      }
    }