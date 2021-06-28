package com.sm.android.locations.locations.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 扫频频点配置
 * @author Administrator
 *
 */
public class Setpd {

	public static void main(String[] args) {
		
		/**
		 * 设置扫频频点配置之前需要扫频端口，目前支持 RX 口和SNIFFER 口扫频。
		 * TDD基站支持 RX 和 SINNIFER 2 个端口扫频，基站默认版本是 RX 口；
		 * FDD 模式只支持用 SNF 端口扫频，基站默认版本是 SNF口。
		 */		
		//设置成SNF端口扫频
		String str = "aaaa55557df01000000000ff01000000";
		//响应
		String str1 = "aa aa 55 55 7e f0 10 00 ff 00 00 7f 00 00 00 00";
		
		int wholeBandRem = 0;
		int sysEarfcnNum = 10;//传递的数量
		int arry[] = {38950,37900,38400,38544,39148,39292,38955,19650,19825,18100};
		List<Integer>list=new ArrayList<>();
		list.add(38950);
		System.out.println(setpd(wholeBandRem, sysEarfcnNum, list));
		
		
	}
	
	/**
	 * 
	 * @param wholeBandRem  是否开启全频段扫频  取值范围为：0：不开启；1：开启。
	 * @param sysEarfcnNum	扫频频点数目。取值范围为：1~10。
	 * @return
	 */
	public static String setpd(int wholeBandRem, int sysEarfcnNum, List<Integer> sysEarfcn ){
		
		StringBuffer buffer3 = new StringBuffer();
		if(sysEarfcn.size() != 10){
			
//			System.out.println("参数数量不对");
		}
		if(cheakIsRepeat(sysEarfcn)){
			for(int i=0;i<sysEarfcn.size();i++){
				buffer3.append(StringPin(StringAdd(Integer.toString(sysEarfcn.get(i), 16))));
			}
		}else{
			System.out.println("有重复值！");
		}
		//消息头
		StringBuffer buffer = new StringBuffer("aaaa555509f03c00000000ff");
		//是否开启全频段扫频  取值范围为：0：不开启；1：开启。
		StringBuffer buffer1 = StringPin(StringAdd(Integer.toString(wholeBandRem, 16)));
		//扫频频点数目。取值范围为：1~10
		StringBuffer buffer2 = StringPin(StringAdd(Integer.toString(sysEarfcnNum, 16)));
		
		String str = buffer.append(buffer1).append(buffer2).append(buffer3).toString();
		
		return str;
		
	}
	
	 /*
	  * 判断数组中是否有重复的值
	  */
	public static boolean cheakIsRepeat(List<Integer> array) {
		HashSet<Integer> hashSet = new HashSet<Integer>();
		for (int i = 0; i < array.size(); i++) {
			hashSet.add(array.get(i));
		}
		if (hashSet.size() == array.size()) {
			return true;
		} else {
			return false;
		}
	}
	
	//字符串前添加0，添加到字符串为8位为止
	public static StringBuffer StringAdd(String str){
		StringBuffer buffer = new StringBuffer(str);	
		for(int i=buffer.length();i<8;i++){
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
