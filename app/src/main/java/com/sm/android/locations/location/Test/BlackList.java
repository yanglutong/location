package com.sm.android.locations.location.Test;


import java.util.ArrayList;
import java.util.List;

public class BlackList {

	
	public static void main(String[] args) {
		
		//清空黑名单
		String str2="aa aa 55 55 53 f0 64 01 00 00 00 ff 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 01 00 00";
		
		
		int s1 = 20;
		Integer.toString(s1, 16);
		System.out.println(blacklistCount(Integer.toString(s1, 16)));
		System.out.println(toIMSI("123"));
		
		int total=1;
		String str = "460010619201205";
		String s2 = "460010619201205";
		String a="";String b="";String c="";String d="";String e="";String f="";String g="";
		String h="";String i="";String j="";String k="";String l="";String m="";String n="";
		String o="";String p="";String q="";String r="";String s="";String t="";
		String strs = addIMSI(total, str, a, s2, c, d, f, e, g, h, i, j, k, l, m, n, o, p, q, r, s,"000000");
		System.out.println(strs);
		
	}
	
	/**
	 * 黑名单设置
	 * @param total    设置数量
	 * @param imsi1    imsi号
	 * @param imsi2
	 * @param imsi3
	 * @param imsi4
	 * @param imsi5
	 * @param imsi6
	 * @param imsi7
	 * @param imsi8
	 * @param imsi9
	 * @param imsi10
	 * @param imsi11
	 * @param imsi12
	 * @param imsi13
	 * @param imsi14
	 * @param imsi15
	 * @param imsi16
	 * @param imsi17
	 * @param imsi18
	 * @param imsi19
	 * @param imsi20
	 * @param clearList 000000表示保留当前所有配置；010000:清空之前配置的IMSI配置列表。
	 * @return
	 */
	public static String addIMSI(int total,String imsi1,String imsi2,String imsi3,String imsi4,String imsi5,
			String imsi6,String imsi7,String imsi8,String imsi9,String imsi10,
			String imsi11,String imsi12,String imsi13,String imsi14,String imsi15,String imsi16,
			String imsi17,String imsi18,String imsi19,String imsi20,String clearList){
		
		List<String> list = new ArrayList<>();
		list.add(imsi1);
		list.add(imsi2);
		list.add(imsi3);
		list.add(imsi4);
		list.add(imsi5);	
		list.add(imsi6);
		list.add(imsi7);
		list.add(imsi8);
		list.add(imsi9);
		list.add(imsi10);
		list.add(imsi11);
		list.add(imsi12);
		list.add(imsi13);
		list.add(imsi14);
		list.add(imsi15);
		list.add(imsi16);
		list.add(imsi17);
		list.add(imsi18);
		list.add(imsi19);
		list.add(imsi20);
		
		//消息头
		StringBuffer str = new StringBuffer("aaaa555553f06401000000ff");
		//黑名单数量
		str.append(StringPin(blacklistCount(Integer.toString(total, 16))));
		List<String>list2=new ArrayList<>();
		for(int i=0;i<list.size();i++){
			if(!list.get(i).equals("")&&list.get(i)!=null){
				StringBuffer imsi = toIMSI(list.get(i));
				str.append(imsi).append("0000");
				list2.add(list.get(i));
				
			}
		}
		
		for (int i = 0; i < 20-list2.size(); i++) {
			str.append("0000000000000000000000000000000000");
		}
		
		str.append(clearList);
		return str.toString();
	}
	//手机上的IMSI号转换成指令中的IMSI号
	public static StringBuffer toIMSI(String str){
		
		char[] chars = "0123456789ABCDEF".toCharArray();
    	StringBuffer sb = new StringBuffer("");
    	byte[] bs = str.getBytes();
    	int bit;
    	for (int i = 0; i < bs.length; i++) {
    		bit = (bs[i] & 0x0f0) >> 4;
    		sb.append(chars[bit]);
    		bit = bs[i] & 0x0f;
    		sb.append(chars[bit]);
    		
    	}
    	return sb;
	}
	//IMSI黑名单数量设置
	public static StringBuffer blacklistCount(String str){
  		
  		StringBuffer buffer = new StringBuffer(str);	
  		for(int i = buffer.length(); i<4; i++){
  			buffer.insert(0,"0");
  		}
  		return buffer;	
  	}
	
	//字符串分割成两个字符一组，倒序拼接到一起
    public static StringBuffer StringPin(StringBuffer str){
    	
    	String [] bands = new String[str.length()/2]; 
		for(int i=0;i<str.length();i+=2){
			bands[i/2] = str.substring(i,i+2);
		}
		
		StringBuffer str1 = new StringBuffer();
		for(int i=bands.length-1;i>=0;i--){
			str1.append(bands[i]);
		}
		
    	return str1;
    }
	
}
