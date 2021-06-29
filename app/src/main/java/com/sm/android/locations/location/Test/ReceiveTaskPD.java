package com.sm.android.locations.location.Test;

//public class ReceiveTask implements Runnable{
public class ReceiveTaskPD {
//	static Device device = new Device();
//
//	public static void timerTest(){
//
//		//创建一个定时器
//		Timer timer = new Timer();
//		//schedule方法是执行时间定时任务的方法
//		TimerTask timerTask = new TimerTask(){
//
//		//run方法就是具体需要定时执行的任务
//			public void run() {
//
//				Date date = new Date();
//				System.out.println("现在时间："+date);
//				if("192.168.2.53".equals(device.getIP1())){
//
//					long second = (date.getTime()-device.getTime1().getTime())/1000;
//					System.out.println(second+"---IP:53-------------");
//					if(second>20){
//						System.out.println("53链接超时————————————————————————————————————————————");
//					}
//				}
//
//				if("192.168.2.54".equals(device.getIP2())){
//					long second = (date.getTime()-device.getTime2().getTime())/1000;
//					System.out.println(second+"---IP:54-------------");
//					if(second>20){
//						System.out.println("54链接超时————————————————————————————————————————————");
//					}
//				}
//			}
//		};
//
//		timer.schedule(timerTask, 20000,20000);
//
//	}
//
//
//	@Override
//	public void run(){
//
//		try {
//
//			DatagramSocket ds = new DatagramSocket(3345);
//			byte[] buf= new byte[1024];
//			DatagramPacket dp = new DatagramPacket(buf, buf.length);
//			timerTest();
//			device.setIP1("192.168.2.53");
//			device.setIP2("192.168.2.54");
//			device.setTime1(new Date());
//			device.setTime2(new Date());
//			while(true){
//				//通过udp的socket服务将数据包接收到，通过receive方法
//
//				ds.receive(dp);
//				System.out.println(dp.getAddress().getHostAddress());
//				byte[] buf1 = dp.getData();
//				//btye[]字节数组转换成16进制的字符串
//				String str = ReceiveTask.toHexString1(buf1);
//
//				//String str = new String(dp.getData(),0,dp.getLength());
//				System.out.println("收到"+dp.getAddress().getHostAddress()+"发送数据："+str);
//				if("192.168.2.53".equals(dp.getAddress().getHostAddress())){
//					device.setIP1("192.168.2.53");
//					device.setTime1(new Date());
//
//					//时间
//					Date d = new Date();
//			        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			        System.out.println("时间："+sdf.format(d));
//					//模式：FDD TDD
//					if("00ff".equals(str.substring(16, 20))){
//						//设置模式FDD
//						System.err.println("FDD");
//					}if("ff00".equals(str.substring(16, 20))){
//						//设置模式TDD
//						System.out.println("TDD");
//					}
//
//					//小区基本信息状态查询响应
//					if("2cf0".equals(str.substring(8, 12))){
//
//						if("00000000".equals(str.substring(24, 32))){
//							System.out.println("设备型号："+hexStringToString(str.substring(32, 46)));
//						}else if("01000000".equals(str.substring(24, 32))){
//							System.out.println("硬件版本："+hexStringToString(str.substring(32, 34)));
//						}else if("02000000".equals(str.substring(24, 32))){
//							System.out.println("软件版本："+hexStringToString(str.substring(32, 106)));
//						}else if("03000000".equals(str.substring(24, 32))){
//							System.out.println("序列号SN:"+hexStringToString(str.substring(32, 70)));
//						}else if("04000000".equals(str.substring(24, 32))){
//							System.out.println("MAC地址："+hexStringToString(str.substring(32, 66)));
//						}else if("05000000".equals(str.substring(24, 32))){
//							System.out.println("uboot版本号："+hexStringToString(str.substring(32, 47)));
//						}else if("06000000".equals(str.substring(24, 32))){
//							System.out.println("板卡温度："+hexStringToString(str.substring(32, 50)));
//						}
//
//					}
//
//					//黑名单中标
//					if("05f0".equals(str.substring(8, 12))){
//						System.out.println("IMSI号："+hexStringToString(str.substring(32, 62)));
//					}
//					//查询设备增益值
//					if("32f0".equals(str.substring(8, 12))){
//						//寄存器中的值，实际生效的值（FDD 模式下仅在建立完小区查询，该值有效）
//						System.out.println(Integer.parseInt(str.substring(24,26),16));
//						//数据库中的保存值，重启保留生效的值,
//						System.out.println(Integer.parseInt(str.substring(26,28),16));
//						//只在FDD模式下有效，寄存器中的值，实际生效的值,该值只有在扫频完成后，建立小区前查询有效
//						System.out.println(Integer.parseInt(str.substring(34,36),16));
//						//eeprom 中的保存值，重启保留生效的值
//						System.out.println(Integer.parseInt(str.substring(36,38),16));
//					}
//					//设置增益是否成功
//					if("14f0".equals(str.substring(8, 12))){
//						//成功0；不成功>0（16进制字符串转换成十进制）
//						int row = Integer.parseInt(str.substring(24,32),16);
//						if(row==0){
//							System.out.println("设置成功！");
//						}else{
//							System.err.println("设置失败！");
//						}
//					}
//					//动态修改小区的响应
//					if("81f0".equals(str.substring(8, 12))){
//						//成功0；不成功>0（16进制字符串转换成十进制）
//						int row = Integer.parseInt(str.substring(24,32),16);
//						if(row==0){
//							System.out.println("设置成功！");
//						}else{
//							System.err.println("设置失败！");
//						}
//					}
//					//扫频频点设置响应
//					if("04f0".equals(str.substring(8, 12))){
//						//成功0；不成功>0（16进制字符串转换成十进制）
//						int row = Integer.parseInt(str.substring(24,32),16);
//						if(row==0){
//							System.out.println("设置成功！");
//						}else{
//							System.err.println("设置失败！");
//						}
//					}
//					//扫频频点设置响应
//					if("0af0".equals(str.substring(8, 12))){
//						//采集的小区数目
//						int row;
//						if("ffff".equals(str.substring(24,28))){
//							row = 0;
//						}else{
//							row = Integer.parseInt(StringPin(str.substring(24,28)),16);
//							System.out.println("小区个数："+row);
//						}
//
//						int dlEarfcnBegin = 32,dlEarfcnEnd = 40;
//						int pciBegin = 40,pciEnd = 44;
//						int tacBegin = 44,tacEnd = 48;
//						int plmnBegin =48,plmnEnd = 52;
//						int celldBegin = 56,celldEnd = 64;
//						int priorityBegin = 64, priorityEnd = 72;
//						int rsrpBegin = 72,rsrpEnd = 74;
//						int rsrqBegin = 74, rsrqEnd = 76;
//						int bandWidthBegin = 76,bandWidthEnd = 78;
//						int tddSpecialSfBegin = 78 , tddSpecialSfEnd=80;
//						int interFreqLstNumBegin = 88, interFreqLstNumEnd = 96;
//						int interFreqNghNumBegin = 112 ,interFreqNghNumEnd = 120;
//						for(int i=0;i<row;i++){
//							//下行频点
//							System.out.println(str.substring(dlEarfcnBegin,dlEarfcnEnd));
//							if("ffffffff".equals(str.substring(dlEarfcnBegin,dlEarfcnEnd))){
//								System.out.println("null");
//							}else{
//								System.out.println("下行频点：------"+Integer.parseInt(StringPin(str.substring(dlEarfcnBegin,dlEarfcnEnd)),16));
//							}
//							//PCI
//							System.out.println("PCI：------"+Integer.parseInt(StringPin(str.substring(pciBegin,pciEnd)),16));
//
//							System.out.println(dlEarfcnBegin+"+"+dlEarfcnEnd);
//
//							//TAC
//							System.out.println("TAC：------"+Integer.parseInt(StringPin(str.substring(tacBegin,tacEnd)),16));
//							//PLMN
//							System.out.println(Integer.parseInt(StringPin(str.substring(plmnBegin,plmnEnd)),16)+"---PLMN：");
//							//CellId
//							System.out.println("ffffffff".equals(str.substring(celldBegin,celldEnd))?"null":Integer.parseInt(StringPin(str.substring(celldBegin,celldEnd)),16)+"------CellId：");
//							//Priority 本小区频点优先级
//							System.out.println(str.substring(64,72));
//							System.out.println("ffffffff".equals(str.substring(priorityBegin,priorityEnd))?"null":Integer.parseInt(StringPin(str.substring(priorityBegin,priorityEnd)),16)+"------Priority 本小区频点优先级：");
//							//RSRP
//							System.out.println("RSRP：------"+Integer.parseInt(StringPin(str.substring(rsrpBegin,rsrpEnd)),16));
//							//RSRQ
//							System.out.println("RSRQ：------"+Integer.parseInt(StringPin(str.substring(rsrqBegin,rsrqEnd)),16));
//							//Bandwidth小区工作带宽
//							System.out.println("Bandwidth：------"+Integer.parseInt(StringPin(str.substring(bandWidthBegin,bandWidthEnd)),16));
//							//TddSpecialSf Patterns TDD特殊子帧配置
//							System.out.println("TDD特殊子帧配置：------"+Integer.parseInt(StringPin(str.substring(tddSpecialSfBegin,tddSpecialSfEnd)),16));
//							//异频小区个数
//							int InterFreqLstNum;
//							if("ffffffff".equals(str.substring(interFreqLstNumBegin,interFreqLstNumEnd))){
//								InterFreqLstNum = 0;
//							}else{
//								InterFreqLstNum = Integer.parseInt(StringPin(str.substring(interFreqLstNumBegin,interFreqLstNumEnd)),16);
//							}
//							System.out.println(interFreqLstNumBegin+"---"+interFreqLstNumEnd);
//							System.out.println("异频小区个数：------"+InterFreqLstNum);
//
//							dlEarfcnBegin = dlEarfcnBegin+64; dlEarfcnEnd = dlEarfcnEnd+64;
//							pciBegin = pciBegin+64; pciEnd = pciEnd+64;
//							tacBegin = tacBegin+64; tacEnd = tacEnd+64;
//							plmnBegin =plmnBegin+64; plmnEnd = plmnEnd+64;
//							celldBegin = celldBegin+64; celldEnd = celldEnd+64;
//							priorityBegin = priorityBegin+64; priorityEnd = priorityEnd+64;
//							rsrpBegin = rsrpBegin+64; rsrpEnd = rsrpEnd+64;
//							rsrqBegin = rsrqBegin+64; rsrqEnd = rsrqEnd+64;
//							bandWidthBegin = bandWidthBegin+64; bandWidthEnd = bandWidthEnd+64;
//							tddSpecialSfBegin = tddSpecialSfBegin+64; tddSpecialSfEnd=tddSpecialSfEnd+64;
//							interFreqLstNumBegin = interFreqLstNumBegin+64;interFreqLstNumEnd = interFreqLstNumEnd+64;
//
//							System.out.println(interFreqNghNumBegin+"---"+interFreqNghNumEnd);
//							for(int r=0;r<InterFreqLstNum;r++){
//								System.out.println(interFreqNghNumBegin+"---"+interFreqNghNumEnd);
//								//异频小区的领区数目
//								System.out.println(str.substring(interFreqNghNumBegin,interFreqNghNumEnd));
//								System.out.println("pin:"+StringPin(str.substring(interFreqNghNumBegin,interFreqNghNumEnd)));
//								System.out.println(Integer.parseInt(StringPin(str.substring(interFreqNghNumBegin,interFreqNghNumEnd)),16));
//								int interFreqNghNum = Integer.parseInt(StringPin(str.substring(interFreqNghNumBegin,interFreqNghNumEnd)),16);
//								System.out.println("异频小区的邻区个数："+interFreqNghNum);
//
//								for(int n=0;n<interFreqNghNum;n++){
//									dlEarfcnBegin = dlEarfcnBegin+8; dlEarfcnEnd = dlEarfcnEnd+8;
//									pciBegin = pciBegin+8; pciEnd = pciEnd+8;
//									tacBegin = tacBegin+8; tacEnd = tacEnd+8;
//									plmnBegin = plmnBegin+8; plmnEnd = plmnEnd+8;
//									celldBegin = celldBegin+8; celldEnd = celldEnd+8;
//									priorityBegin = priorityBegin+8; priorityEnd = priorityEnd+8;
//									rsrpBegin = rsrpBegin+8; rsrpEnd = rsrpEnd+8;
//									rsrqBegin = rsrqBegin+8; rsrqEnd = rsrqEnd+8;
//									bandWidthBegin = bandWidthBegin+8; bandWidthEnd = bandWidthEnd+8;
//									tddSpecialSfBegin = tddSpecialSfBegin+8; tddSpecialSfEnd=tddSpecialSfEnd+8;
//									interFreqLstNumBegin = interFreqLstNumBegin+8; interFreqLstNumEnd = interFreqLstNumEnd+8;
//									interFreqNghNumBegin = interFreqNghNumBegin+8; interFreqNghNumEnd = interFreqNghNumEnd+8;
//								}
//
//								int number = InterFreqLstNum-r;
//								if(number!=1){
//									interFreqNghNumBegin = interFreqNghNumBegin+24; interFreqNghNumEnd = interFreqNghNumEnd+24;
//								}
//								dlEarfcnBegin = dlEarfcnBegin+24; dlEarfcnEnd = dlEarfcnEnd+24;
//								pciBegin = pciBegin+24; pciEnd = pciEnd+24;
//								tacBegin = tacBegin+24; tacEnd = tacEnd+24;
//								plmnBegin =plmnBegin+24; plmnEnd = plmnEnd+24;
//								celldBegin = celldBegin+24; celldEnd = celldEnd+24;
//								priorityBegin = priorityBegin+24; priorityEnd = priorityEnd+24;
//								rsrpBegin = rsrpBegin+24; rsrpEnd = rsrpEnd+24;
//								rsrqBegin = rsrqBegin+24; rsrqEnd = rsrqEnd+24;
//								bandWidthBegin = bandWidthBegin+24; bandWidthEnd = bandWidthEnd+24;
//								tddSpecialSfBegin = tddSpecialSfBegin+24; tddSpecialSfEnd=tddSpecialSfEnd+24;
//								interFreqLstNumBegin = interFreqLstNumBegin+24; interFreqLstNumEnd = interFreqLstNumEnd+24;
//
//							}
//							interFreqNghNumBegin = interFreqNghNumBegin+64; interFreqNghNumEnd = interFreqNghNumEnd+64;
//						}
//
//					}
//					//////////////nzq
//					//建立小区是否成功
//					if("04f0".equals(str.substring(8, 12))){
//						//成功0；不成功>0（16进制字符串转换成十进制）
//						int row = Integer.parseInt(str.substring(24,32),16);
//						if(row==0){
//							System.out.println("设置成功！开始建立小区！");
//						}else{
//							System.err.println("设置失败！");
//						}
//					}
//					//重启是否成功
//					if("0cf0".equals(str.substring(8, 12))){
//						//成功0；不成功>0（16进制字符串转换成十进制）
//						int row = Integer.parseInt(str.substring(24,32),16);
//						if(row==0){
//							System.out.println("重启设置成功！");
//						}else{
//							System.err.println("重启失败！");
//						}
//					}
//					//公放设置
//					if("a0f0".equals(str.substring(8, 12))){
//						//成功0；不成功>0（16进制字符串转换成十进制）
//						int row = Integer.parseInt(str.substring(24,32),16);
//						if(row==0){
//							System.out.println("公放设置成功！");
//						}else{
//							System.err.println("设置失败！");
//						}
//					}
//					//去激活小区是否成功
//					if("0ef0".equals(str.substring(8, 12))){
//						//成功0；不成功>0（16进制字符串转换成十进制）
//						int row = Integer.parseInt(str.substring(24,32),16);
//						if(row==0){
//							System.out.println("设置成功！去激活小区成功，停止定位！");
//						}else{
//							System.err.println("设置失败！");
//						}
//					}
//					//基站执行状态
//					if("19f0".equals(str.substring(8, 12))){
//						String state = str.substring(24,32);
//						System.out.println("state"+state);
//						if("00000000".equals(state)){
//							System.err.println("空口同步成功");
//						}else if("01000000".equals(state)){
//							System.err.println("空口同步失败");
//						}else if("02000000".equals(state)){
//							System.err.println("GPS同步成功");
//						}else if("03000000".equals(state)){
//							System.err.println("GPS同步失败");
//						}else if("04000000".equals(state)){
//							System.err.println("扫频成功");
//						}else if("05000000".equals(state)){
//							System.err.println("扫频失败");
//						}else if("06000000".equals(state)){
//							System.err.println("小区激活成功");
//						}else if("07000000".equals(state)){
//							System.err.println("小区激活失败");
//						}else if("08000000".equals(state)){
//							System.err.println("小区去激活");
//						}else if("09000000".equals(state)){
//							System.err.println("空口同步中");
//						}else if("0a000000".equals(state)){
//							System.err.println("GPS同步中");
//						}else if("0b000000".equals(state)){
//							System.err.println("扫频中");
//						}else if("0c000000".equals(state)){
//							System.err.println("小区激活中");
//						}else if("0d000000".equals(state)){
//							System.err.println("小区去激活中");
//						}
//
//					}
//					//设置定位模式的应答信息
//					if("07f0".equals(str.substring(8, 12))){
//						if("00000000".equals(str.substring(24, 32))){
//							System.out.println("设置基站ue定位成功");
//						}else{
//							System.out.println("设置基站ue定位失败");
//						}
//					}
//					//设置黑名单响应
//					if("54f0".equals(str.substring(8, 12))){
//						if("00000000".equals(str.substring(24, 32))){
//							System.out.println("设置基站黑名单成功");
//						}else{
//							System.out.println("设置基站黑名单失败");
//						}
//					}
//					//定位UE测量值上报
//					if("08f0".equals(str.substring(8, 12))){
//
//						//目标距离（16进制字符串转换成十进制）
//						Integer.parseInt(str.substring(24, 26),16);
//						System.out.println("距离："+Integer.parseInt(str.substring(24, 26),16));
//						//IMSI号
//						StringTOIMEI(str.substring(26, 56));
//						System.out.println("IMSI号："+hexStringToString(str.substring(26, 56)));
//
//					}
//					//心跳解析
//					if("10f0".equals(str.substring(8, 12))){
//
//						//查看小区是否建立成功（0：小区 IDLE态；1：扫频/同步进行中；2：小区激活中；3：小区激活态；4：小区去激活中）
//						if("0000".equals(str.substring(24, 28))){
//							System.out.println("0：小区 IDLE态");
//
//						}else if("0100".equals(str.substring(24, 28))){
//							System.out.println("1：扫频/同步进行中");
//						}else if("0200".equals(str.substring(24, 28))){
//							System.out.println("2：小区激活中");
//						}else if("0300".equals(str.substring(24, 28))){
//							System.out.println("3：小区激活态");
//
//							//Band号
//							Integer.parseInt(StringPin(str.substring(28,32)),16);
//							System.out.println("Band号："+Integer.parseInt(StringPin(str.substring(28,32)),16));
//							//上行频点
//							Integer.parseInt(StringPin(str.substring(32,40)),16);
//							System.out.println("上行频点："+Integer.parseInt(StringPin(str.substring(32,40)),16));
//							//下行频点
//							Integer.parseInt(StringPin(str.substring(40,48)),16);
//							System.out.println("下行频点："+Integer.parseInt(StringPin(str.substring(40,48)),16));
//							//移动联通电信
//							if("3436303030".equals(str.substring(48, 58))){
//								//设置中国移动
//							}if("3436303031".equals(str.substring(48, 58))){
//								//设置中国联通
//							}if("3436303033".equals(str.substring(48, 58))||"3436303131".equals(str.substring(48, 58))){
//								//设置中国电信
//							}
//
//							//PCI
//							Integer.parseInt(StringPin(str.substring(64,68)),16);
//							System.out.println("PCI:"+Integer.parseInt(StringPin(str.substring(64,68)),16));
//							//TAC
//							Integer.parseInt(StringPin(str.substring(68,72)),16);
//							System.out.println("TAC:"+Integer.parseInt(StringPin(str.substring(68,72)),16));
//
//							}else if("0400".equals(str.substring(24, 28))){
//								System.out.println("4：小区去激活中");
//							}
//
//					}
//
//					//温度告警
//					if("5bf0".equals(str.substring(8, 12))){
//						if("00000000".equals(str.substring(32,40))){
//							System.out.println("基带板温度超过70度");
//						}
//						if("01000000".equals(str.substring(32,40))){
//							System.out.println("基带板温度降低到70度以下了");
//						}
//					}
//
//				}
//				if("192.168.2.54".equals(dp.getAddress().getHostAddress())){
//					System.out.println("进入：192.168.2.54");
//
//					device.setIP2("192.168.2.54");
//					device.setTime2(new Date());
//
//					//时间
//					Date d = new Date();
//			        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			        System.out.println("时间："+sdf.format(d));
//					//设置模式：FDD TDD
//					if("00ff".equals(str.substring(16, 20))){
//						//设置模式FDD
//						System.err.println("FDD");
//					}if("ff00".equals(str.substring(16, 20))){
//						//设置模式TDD
//						System.out.println("TDD");
//					}
//					//查询设备增益值
//					if("32f0".equals(str.substring(8, 12))){
//						//寄存器中的值，实际生效的值（FDD 模式下仅在建立完小区查询，该值有效）
//						System.out.println(Integer.parseInt(str.substring(24,26),16));
//						//数据库中的保存值，重启保留生效的值,
//						System.out.println(Integer.parseInt(str.substring(26,28),16));
//						//只在FDD模式下有效，寄存器中的值，实际生效的值,该值只有在扫频完成后，建立小区前查询有效
//						System.out.println(Integer.parseInt(str.substring(34,36),16));
//						//eeprom 中的保存值，重启保留生效的值
//						System.out.println(Integer.parseInt(str.substring(36,38),16));
//					}
//					//设置增益是否成功
//					if("14f0".equals(str.substring(8, 12))){
//						//成功0；不成功>0（16进制字符串转换成十进制）
//						int row = Integer.parseInt(str.substring(24,32),16);
//						if(row==0){
//							System.out.println("设置成功！");
//						}else{
//							System.err.println("设置失败！");
//						}
//					}
//					//小区基本信息状态查询响应
//					if("2cf0".equals(str.substring(8, 12))){
//
//						if("00000000".equals(str.substring(24, 32))){
//							System.out.println("设备型号："+hexStringToString(str.substring(32, 46)));
//						}else if("01000000".equals(str.substring(24, 32))){
//							System.out.println("硬件版本："+hexStringToString(str.substring(32, 34)));
//						}else if("02000000".equals(str.substring(24, 32))){
//							System.out.println("软件版本："+hexStringToString(str.substring(32, 106)));
//						}else if("03000000".equals(str.substring(24, 32))){
//							System.out.println("序列号SN:"+hexStringToString(str.substring(32, 70)));
//						}else if("04000000".equals(str.substring(24, 32))){
//							System.out.println("MAC地址："+hexStringToString(str.substring(32, 66)));
//						}else if("05000000".equals(str.substring(24, 32))){
//							System.out.println("uboot版本号："+hexStringToString(str.substring(32, 47)));
//						}else if("06000000".equals(str.substring(24, 32))){
//							System.out.println("板卡温度："+hexStringToString(str.substring(32, 50)));
//						}
//
//					}
//
//
//					//扫频频点设置响应
//					if("0af0".equals(str.substring(8, 12))){
//						//采集的小区数目
//						int row;
//						if("ffff".equals(str.substring(24,28))){
//							row = 0;
//						}else{
//							row = Integer.parseInt(StringPin(str.substring(24,28)),16);
//							System.out.println("小区个数："+row);
//						}
//
//						int dlEarfcnBegin = 32,dlEarfcnEnd = 40;
//						int pciBegin = 40,pciEnd = 44;
//						int tacBegin = 44,tacEnd = 48;
//						int plmnBegin =48,plmnEnd = 52;
//						int celldBegin = 56,celldEnd = 64;
//						int priorityBegin = 64, priorityEnd = 72;
//						int rsrpBegin = 72,rsrpEnd = 74;
//						int rsrqBegin = 74, rsrqEnd = 76;
//						int bandWidthBegin = 76,bandWidthEnd = 78;
//						int tddSpecialSfBegin = 78 , tddSpecialSfEnd=80;
//						int interFreqLstNumBegin = 88, interFreqLstNumEnd = 96;
//						int interFreqNghNumBegin = 112 ,interFreqNghNumEnd = 120;
//						for(int i=0;i<row;i++){
//							//下行频点
//							System.out.println(str.substring(dlEarfcnBegin,dlEarfcnEnd));
//							if("ffffffff".equals(str.substring(dlEarfcnBegin,dlEarfcnEnd))){
//								System.out.println("null");
//							}else{
//								System.out.println("下行频点：------"+Integer.parseInt(StringPin(str.substring(dlEarfcnBegin,dlEarfcnEnd)),16));
//							}
//							//PCI
//							System.out.println("PCI：------"+Integer.parseInt(StringPin(str.substring(pciBegin,pciEnd)),16));
//
//							System.out.println(dlEarfcnBegin+"+"+dlEarfcnEnd);
//
//							//TAC
//							System.out.println("TAC：------"+Integer.parseInt(StringPin(str.substring(tacBegin,tacEnd)),16));
//							//PLMN
//							System.out.println(Integer.parseInt(StringPin(str.substring(plmnBegin,plmnEnd)),16)+"---PLMN：");
//							//CellId
//							System.out.println("ffffffff".equals(str.substring(celldBegin,celldEnd))?"null":Integer.parseInt(StringPin(str.substring(celldBegin,celldEnd)),16)+"------CellId：");
//							//Priority 本小区频点优先级
//							System.out.println(str.substring(64,72));
//							System.out.println("ffffffff".equals(str.substring(priorityBegin,priorityEnd))?"null":Integer.parseInt(StringPin(str.substring(priorityBegin,priorityEnd)),16)+"------Priority 本小区频点优先级：");
//							//RSRP
//							System.out.println("RSRP：------"+Integer.parseInt(StringPin(str.substring(rsrpBegin,rsrpEnd)),16));
//							//RSRQ
//							System.out.println("RSRQ：------"+Integer.parseInt(StringPin(str.substring(rsrqBegin,rsrqEnd)),16));
//							//Bandwidth小区工作带宽
//							System.out.println("Bandwidth：------"+Integer.parseInt(StringPin(str.substring(bandWidthBegin,bandWidthEnd)),16));
//							//TddSpecialSf Patterns TDD特殊子帧配置
//							System.out.println("TDD特殊子帧配置：------"+Integer.parseInt(StringPin(str.substring(tddSpecialSfBegin,tddSpecialSfEnd)),16));
//							//异频小区个数
//							int InterFreqLstNum;
//							if("ffffffff".equals(str.substring(interFreqLstNumBegin,interFreqLstNumEnd))){
//								InterFreqLstNum = 0;
//							}else{
//								InterFreqLstNum = Integer.parseInt(StringPin(str.substring(interFreqLstNumBegin,interFreqLstNumEnd)),16);
//							}
//							System.out.println(interFreqLstNumBegin+"---"+interFreqLstNumEnd);
//							System.out.println("异频小区个数：------"+InterFreqLstNum);
//
//							dlEarfcnBegin = dlEarfcnBegin+64; dlEarfcnEnd = dlEarfcnEnd+64;
//							pciBegin = pciBegin+64; pciEnd = pciEnd+64;
//							tacBegin = tacBegin+64; tacEnd = tacEnd+64;
//							plmnBegin =plmnBegin+64; plmnEnd = plmnEnd+64;
//							celldBegin = celldBegin+64; celldEnd = celldEnd+64;
//							priorityBegin = priorityBegin+64; priorityEnd = priorityEnd+64;
//							rsrpBegin = rsrpBegin+64; rsrpEnd = rsrpEnd+64;
//							rsrqBegin = rsrqBegin+64; rsrqEnd = rsrqEnd+64;
//							bandWidthBegin = bandWidthBegin+64; bandWidthEnd = bandWidthEnd+64;
//							tddSpecialSfBegin = tddSpecialSfBegin+64; tddSpecialSfEnd=tddSpecialSfEnd+64;
//							interFreqLstNumBegin = interFreqLstNumBegin+64;interFreqLstNumEnd = interFreqLstNumEnd+64;
//
//							System.out.println(interFreqNghNumBegin+"---"+interFreqNghNumEnd);
//							for(int r=0;r<InterFreqLstNum;r++){
//								System.out.println(interFreqNghNumBegin+"---"+interFreqNghNumEnd);
//								//异频小区的领区数目
//								System.out.println(str.substring(interFreqNghNumBegin,interFreqNghNumEnd));
//								System.out.println("pin:"+StringPin(str.substring(interFreqNghNumBegin,interFreqNghNumEnd)));
//								System.out.println(Integer.parseInt(StringPin(str.substring(interFreqNghNumBegin,interFreqNghNumEnd)),16));
//								int interFreqNghNum = Integer.parseInt(StringPin(str.substring(interFreqNghNumBegin,interFreqNghNumEnd)),16);
//								System.out.println("异频小区的邻区个数："+interFreqNghNum);
//
//								for(int n=0;n<interFreqNghNum;n++){
//									dlEarfcnBegin = dlEarfcnBegin+8; dlEarfcnEnd = dlEarfcnEnd+8;
//									pciBegin = pciBegin+8; pciEnd = pciEnd+8;
//									tacBegin = tacBegin+8; tacEnd = tacEnd+8;
//									plmnBegin = plmnBegin+8; plmnEnd = plmnEnd+8;
//									celldBegin = celldBegin+8; celldEnd = celldEnd+8;
//									priorityBegin = priorityBegin+8; priorityEnd = priorityEnd+8;
//									rsrpBegin = rsrpBegin+8; rsrpEnd = rsrpEnd+8;
//									rsrqBegin = rsrqBegin+8; rsrqEnd = rsrqEnd+8;
//									bandWidthBegin = bandWidthBegin+8; bandWidthEnd = bandWidthEnd+8;
//									tddSpecialSfBegin = tddSpecialSfBegin+8; tddSpecialSfEnd=tddSpecialSfEnd+8;
//									interFreqLstNumBegin = interFreqLstNumBegin+8; interFreqLstNumEnd = interFreqLstNumEnd+8;
//									interFreqNghNumBegin = interFreqNghNumBegin+8; interFreqNghNumEnd = interFreqNghNumEnd+8;
//								}
//
//								int number = InterFreqLstNum-r;
//								if(number!=1){
//									interFreqNghNumBegin = interFreqNghNumBegin+24; interFreqNghNumEnd = interFreqNghNumEnd+24;
//								}
//								dlEarfcnBegin = dlEarfcnBegin+24; dlEarfcnEnd = dlEarfcnEnd+24;
//								pciBegin = pciBegin+24; pciEnd = pciEnd+24;
//								tacBegin = tacBegin+24; tacEnd = tacEnd+24;
//								plmnBegin =plmnBegin+24; plmnEnd = plmnEnd+24;
//								celldBegin = celldBegin+24; celldEnd = celldEnd+24;
//								priorityBegin = priorityBegin+24; priorityEnd = priorityEnd+24;
//								rsrpBegin = rsrpBegin+24; rsrpEnd = rsrpEnd+24;
//								rsrqBegin = rsrqBegin+24; rsrqEnd = rsrqEnd+24;
//								bandWidthBegin = bandWidthBegin+24; bandWidthEnd = bandWidthEnd+24;
//								tddSpecialSfBegin = tddSpecialSfBegin+24; tddSpecialSfEnd=tddSpecialSfEnd+24;
//								interFreqLstNumBegin = interFreqLstNumBegin+24; interFreqLstNumEnd = interFreqLstNumEnd+24;
//
//							}
//							interFreqNghNumBegin = interFreqNghNumBegin+64; interFreqNghNumEnd = interFreqNghNumEnd+64;
//						}
//
//					}
//
//					if("08f0".equals(str.substring(8, 12))){
//
//						//目标距离（16进制字符串转换成十进制）
//						Integer.parseInt(str.substring(24, 26),16);
//						System.out.println("距离："+Integer.parseInt(str.substring(24, 26),16));
//						//IMSI号
//						StringTOIMEI(str.substring(26, 56));
//						System.out.println("IMSI号："+hexStringToString(str.substring(26, 56)));
//
//					}
//					if("10f0".equals(str.substring(8, 12))){
//						//心跳解析
//						//查看小区是否建立成功（0：小区 IDLE态；1：扫频/同步进行中；2：小区激活中；3：小区激活态；4：小区去激活中）
//						if("0000".equals(str.substring(24, 28))){
//							System.out.println("0：小区 IDLE态");
//
//						}else if("0100".equals(str.substring(24, 28))){
//							System.out.println("1：扫频/同步进行中");
//						}else if("0200".equals(str.substring(24, 28))){
//							System.out.println("2：小区激活中");
//						}else if("0300".equals(str.substring(24, 28))){
//							System.out.println("3：小区激活态");
//
//							//Band号
//							Integer.parseInt(StringPin(str.substring(28,32)),16);
//							System.out.println("Band号："+Integer.parseInt(StringPin(str.substring(28,32)),16));
//							//上行频点
//							Integer.parseInt(StringPin(str.substring(32,40)),16);
//							System.out.println("上行频点："+Integer.parseInt(StringPin(str.substring(32,40)),16));
//							//下行频点
//							Integer.parseInt(StringPin(str.substring(40,48)),16);
//							System.out.println("下行频点："+Integer.parseInt(StringPin(str.substring(40,48)),16));
//							//移动联通电信
//							if("3436303030".equals(str.substring(48, 58))){
//								//设置中国移动
//							}if("3436303031".equals(str.substring(48, 58))){
//								//设置中国联通
//							}if("3436303033".equals(str.substring(48, 58))||"3436303131".equals(str.substring(48, 58))){
//								//设置中国电信
//							}
//
//							//PCI
//							Integer.parseInt(StringPin(str.substring(64,68)),16);
//							System.out.println("PCI:"+Integer.parseInt(StringPin(str.substring(64,68)),16));
//							//TAC
//							Integer.parseInt(StringPin(str.substring(68,72)),16);
//							System.out.println("TAC:"+Integer.parseInt(StringPin(str.substring(68,72)),16));
//
//						}else if("0400".equals(str.substring(24, 28))){
//							System.out.println("4：小区去激活中");
//						}
//
//					}
//
//				}
//				/*System.out.println("截取字符串"+str.substring(8, 12));
//				if(str.substring(8, 12).equals("08f0")){
//
//					//目标距离（16进制字符串转换成十进制）
//					Integer.parseInt(str.substring(24, 26),16);
//					System.out.println("距离："+Integer.parseInt(str.substring(24, 26),16));
//					//IMSI号
//					StringTOIMEI(str.substring(26, 56));
//					System.out.println("IMSI号："+hexStringToString(str.substring(26, 56)));
//
//				}*//*if("10f0".equals(str.substring(8, 12))){
//					//心跳解析
//					//查看小区是否建立成功（0：小区 IDLE态；1：扫频/同步进行中；2：小区激活中；3：小区激活态；4：小区去激活中）
//					if("0000".equals(str.substring(24, 28))){
//						System.out.println("0：小区 IDLE态");
//					}if("0100".equals(str.substring(24, 28))){
//						System.out.println("1：扫频/同步进行中");
//					}if("0200".equals(str.substring(24, 28))){
//						System.out.println("2：小区激活中");
//					}if("0300".equals(str.substring(24, 28))){
//						System.out.println("3：小区激活态");
//					}if("0400".equals(str.substring(24, 28))){
//						System.out.println("4：小区去激活中");
//					}
//					//设置模式：FDD TDD
//					if("00ff".equals(str.substring(16, 20))){
//						//设置模式FDD
//						System.err.println("FDD");
//					}if("ff00".equals(str.substring(16, 20))){
//						//设置模式TDD
//						System.out.println("TDD");
//					}
//					//时间
//					Date d = new Date();
//			        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			        System.out.println("时间："+sdf.format(d));
//					//Band号
//					Integer.parseInt(StringPin(str.substring(28,32)),16);
//					System.out.println("Band号："+Integer.parseInt(StringPin(str.substring(28,32)),16));
//					//上行频点
//					Integer.parseInt(StringPin(str.substring(32,40)),16);
//					System.out.println("上行频点："+Integer.parseInt(StringPin(str.substring(32,40)),16));
//					//下行频点
//					Integer.parseInt(StringPin(str.substring(40,48)),16);
//					System.out.println("下行频点："+Integer.parseInt(StringPin(str.substring(40,48)),16));
//					//移动联通电信
//					if("3436303030".equals(str.substring(48, 58))){
//						//设置中国移动
//					}if("3436303031".equals(str.substring(48, 58))){
//						//设置中国联通
//					}if("3436303033".equals(str.substring(48, 58))||"3436303131".equals(str.substring(48, 58))){
//						//设置中国电信
//					}
//
//					//PCI
//					Integer.parseInt(StringPin(str.substring(64,68)),16);
//					System.out.println("PCI:"+Integer.parseInt(StringPin(str.substring(64,68)),16));
//					//TAC
//					Integer.parseInt(StringPin(str.substring(68,72)),16);
//					System.out.println("TAC:"+Integer.parseInt(StringPin(str.substring(68,72)),16));
//
//				}*/
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//
//		}
//
//	}
//
//	/*
//	 * btye[]字节数组转换成16进制的字符串
//	 */
//	public static String toHexString1(byte[] b){
//        StringBuffer buffer = new StringBuffer();
//        for (int i = 0; i < b.length; ++i){
//            buffer.append(toHexString1(b[i]));
//        }
//        return buffer.toString();
//    }
//
//	 public static String toHexString1(byte b){
//	        String s = Integer.toHexString(b & 0xFF);
//	        if (s.length() == 1){
//	            return "0" + s;
//	        }else{
//	            return s;
//	        }
//	 }
//
//	 /**
//	   * 指令中截取的IMSI号字符串转换成IMSI号字符串
//	   * @param s
//	   * @return
//	   */
//	public static String hexStringToString(String s) {
//	    if (s == null || s.equals("")) {
//	        return null;
//	    }
//	    s = s.replace(" ", "");
//	    byte[] baKeyword = new byte[s.length() / 2];
//	    for (int i = 0; i < baKeyword.length; i++) {
//	        try {
//	            baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
//	    }
//	    try {
//	        s = new String(baKeyword, "UTF-8");
//	        new String();
//	    } catch (Exception e1) {
//	        e1.printStackTrace();
//	    }
//	    return s;
//	}
//
//	//取字符串偶数位字符拼接到一起
//    public static String StringTOIMEI(String str){
//    	StringBuffer buffer = new StringBuffer();
//    	for(int i=1;i<=str.length();i+=2){
//    		if(i%2 != 0){
//    			buffer.append(str.charAt(i));
//    		}
//    	}
//    	return buffer.toString();
//    }
//
//    //字符串分割成两个字符一组，倒序拼接到一起
//    public static String StringPin(String str){
//
//    	String [] bands = new String[str.length()/2];
//		for(int i=0;i<str.length();i+=2){
//			bands[i/2] = str.substring(i,i+2);
//
//		}
//
//		String str1 = new String();
//		for(int i=bands.length-1;i>=0;i--){
//			str1 += bands[i];
//		}
//
//    	return str1;
//    }
    
    
}
