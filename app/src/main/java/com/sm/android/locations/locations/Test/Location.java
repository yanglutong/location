package com.sm.android.locations.locations.Test;
/**
 * 定位模式：设置定位的IMSI号
 * @author Administrator
 *
 */
public class Location {

	public static void main(String[] args) {
		
		System.out.println(location("460010619201205","04","00"));
	}
	String str="aa aa 55 55 06 f0 28 00 00 00 00 ff 02 04 01 00 34 36 30 30 31 30 36 31 39 32 30 31 32 30 36 00 00 00 00 17 00 00 00 00 00 00 00 00";
	/**
	 * 定位模式
	 * @param imsi imsi号
	 * @param sbzq  上报周期时间   00：120ms 01：240ms 02：480ms 3：640ms 04：1024ms 05：2048ms
	 * @param power 功率  00：enable-FDD(最大功率) 01：disable-TDD(最小功率)
	 * @return
	 */
	public static String location(String imsi, String sbzq,String power){
		
		StringBuffer buffer = new StringBuffer("aaaa555506f02800000000ff02040100");
		StringBuffer buffer1 = buffer.append(toIMSI(imsi)).append("0000").append(sbzq).append(power).append("170000000000000000");
				
		return buffer1.toString();
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
	
	
}
