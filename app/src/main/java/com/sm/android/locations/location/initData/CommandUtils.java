package com.sm.android.locations.location.initData;

import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.AddPararBean;

import java.util.ArrayList;
import java.util.List;

/**给基带板发送指令
 * @author: 小杨同志
 * @date: 2021/6/17
 */
public class CommandUtils {
    public static String dwei="";
    public static boolean spbuilsshow=false;
    public static String gwCan="";
    public static int syType=0;
    public static String zyType="";
    public static String type="";//连接成功返回的报文标识
    public static String type0303="";//定位标识
    public static String type0102="";
    public static String sbZt="";//设备状态

    public static String xqType="0102";//小区设置参数的类型
    public static List<Integer> list=new ArrayList<>();

    /**建立小区设置工作参数  0202  0成功 1失败
     * @description
     * @param
     * @return 
     * @author lutong
     * @time 2021/6/17 16:42
     */
    public static StringBuffer setXq(String PLMN,String TAC,String DLARFCN,String ULARFCN,String PCI,String BAND,String RXGAIN,String CI){
        /*设置小区参数*/
        StringBuffer sf = new StringBuffer();
        sf.append("PLMN:").append(PLMN).append("\t");
        sf.append("TAC:").append(TAC).append("\t");
        sf.append("DLARFCN:").append(DLARFCN).append("\t");
        sf.append("ULARFCN:").append(ULARFCN).append("\t");
        sf.append("PCI:").append(PCI).append("\t");
        sf.append("BAND:").append(BAND).append("\t");
        sf.append("RXGAIN:").append(RXGAIN).append("\t");
        sf.append("CI:").append(CI).append("\r\n");
        MyLog.send("0102",sf.toString());
        return sf;
    }

    /**设置接收增益
     * @description
     * @param
     * @return
     * @author lutong
     * @time 2021/7/12 16:19
     */

    public static String setZy(String RXGAIN){
        StringBuffer sf = new StringBuffer();
        sf.append("TAC:").append("111").append("\t");
        sf.append("RXGAIN:").append(RXGAIN).append("\r\n");
        MyLog.send("0102", sf.toString());
        String s = MyUtils.getSocketHeader(MyUtils.getToHexString("0102", sf.toString()));
        MyLog.send("0102", s);
        return s;
    }


    public static String setBlackList="";//设置黑名单的值
    public static String blackType="0110";//小区设置参数的类型
    public static List<String> imsilist=new ArrayList<>();//存放黑名单列表
    /**下发黑名单  0110
     * @description
     * @param
     * @param sendListBlack
     * @return
     * @author lutong
     * @time 2021/6/18 9:33
     */
    public static StringBuffer setBlackList(List<AddPararBean> sendListBlack){
        MyLog.e("0110", "黑名單集合：  "+sendListBlack.toString());
        StringBuffer bf = new StringBuffer();
        bf.append("BLACKLIST:");
        if(sendListBlack.size()>0){//不管选中几个都默认第一条
            imsilist=new ArrayList<>();
            for (int i = 0; i <sendListBlack.size() ; i++) {
                imsilist.add(sendListBlack.get(i).getImsi());
                bf.append(sendListBlack.get(i).getImsi()).append(",");
            }
        }
        bf.deleteCharAt(bf.length()-1);
        bf.append("\r\n");
        MyLog.send("0110","下发的黑名单:   "+bf.toString());
        return bf;
    }

    /**获取黑名单列表
     * @description
     * @param
     * @param
     * @return
     * @author lutong
     * @time 2021/6/18 9:33
     */
    public static String getBlackList(){
        if(!setBlackList.equals("")){
            MyLog.send("0110","黑名单列表:   "+setBlackList);
            return setBlackList;
        }else{
            return null;
        }
    }


    /**清空黑名单
     * @description
     * @param
     * @param
     * @return
     * @author lutong
     * @time 2021/6/18 9:33
     */
    public static String ClearBlackList(){
        if(!setBlackList.equals("")){
            MyLog.send("0110","黑名单列表:   "+setBlackList);
            return setBlackList;
        }else{
            return null;
        }
    }


    /**定位目标黑名单设置 0136   仅支持设置一个IMSI且必须是IMSI
     * @description
     * @param
     * @param
     * @return
     * @author lutong
     * @time 2021/6/18 9:33
     */
    public static String setLocationImsI(String ImsI){
        String header = MyUtils.getSocketHeader(MyUtils.getToHexString("0136", ImsI));
        MyLog.send("0136", header);
        return header;
    }

    private static String RF_OPEN="";
    public static int RF_STATE=0;
    /**射频  0关闭  1打开
     * @description
     * @param l
     * @return
     * @author lutong
     * @time 2021/6/18 14:49
     */
    public static String getRF(int l){
        if(l==0){//关闭射频
            RF_STATE=l;
            RF_OPEN="01040";
        }
        if(l==1){//开启射频
            RF_STATE=l;
            RF_OPEN="01041";
        }
        String header = MyUtils.getSocketHeader(MyUtils.getToHexString(RF_OPEN, ""));
        MyLog.send(RF_OPEN,header);
        return header;
    }
    
    /**0126获取定位模式    0260  0成功  1失败
     *      “0”拒绝定位
     *      * “2”吸附定位
     *      * “3”多目标定位   需要哪个模式传入相应的值 0 2 3
     * @description
     *
     * @param str
     * @return 
     * @author lutong
     * @time 2021/6/22 15:50
     */
    public static String getLocateMode(int str){
        String header = MyUtils.getSocketHeader(MyUtils.getToHexString("0126"+str, ""));
        MyLog.send("0126",header);
        return header;
    }


    /**获取公网参数环境 0108   回复 0208
     * @description
     * @param
     * @return
     * @author lutong
     * @time 2021/6/22 16:02
     */
    public static String getPublicParameters(){
        String socketHeader = MyUtils.getSocketHeader(MyUtils.getToHexString("0108", "\r\n"));//获取公网参数
        MyLog.send("0108",socketHeader);
        return socketHeader;
    }


    /**设备设置参数  //1.采集周期  2.射频功率 3.RXGAIN 接收增益  4.START起始值  5.RANGE变更承受
     * @description
     * @param
     * @return
     * @author lutong
     * @time 2021/7/8 15:11
     */
    public static String setDeviceParameters(String PERIOD,String START,String RANGE,String TAC,String CI,String PCI){
        /*设置设备参数*/
        StringBuffer sf = new StringBuffer();
        sf.append("PERIOD:").append(PERIOD).append("\t");
//        sf.append("RSTP:").append(RSTP).append("\t");
//        sf.append("RXGAIN:").append(RXGAIN).append("\t");
        sf.append("START:").append(START).append("\t");
        sf.append("TAC:").append(TAC).append("\t");
        sf.append("CI:").append(CI).append("\t");
        sf.append("PCI:").append(PCI).append("\t");
        sf.append("RANGE:").append(RANGE).append("\r\n");

        String header = MyUtils.getSocketHeader(MyUtils.getToHexString(("0102"), sf.toString()));
        MyLog.send("0102",header);
        return header;
    }


    /**扫频
     * @description
     * @param
     * @return
     * @author lutong
     * @time 2021/7/21 8:34
     */
    public static String saoPin(List<Integer> list){
        StringBuffer buffer = new StringBuffer();
        for (Integer integer : list) {
            buffer.append(integer+"").append(",");
        }
        buffer.deleteCharAt(buffer.length()-1);


        StringBuffer sf = new StringBuffer();
        sf.append("AUTOREM:0").append("\t");
        sf.append("LTEREM:1").append("\t");
        sf.append("EARFCN:").append(buffer.toString()).append("\t");
        sf.append("GSMREM:0:").append("\t");
        sf.append("WCDMAREM:0").append("\t");
        sf.append("ARFCN:50,619").append("\t");
        sf.append("REMPRD:0").append("\t");
        sf.append("AUTOCFG:0").append("\r\n");
        String s = MyUtils.getSocketHeader(MyUtils.getToHexString("0101", sf.toString()));
        MyLog.send("0101", sf.toString());
        MyLog.send("0101", s);
        return s;
//        final String string2 = "AUTOREM:0\tLTEREM:1\tEARFCN:37900,38400,39150\tGSMREM:0\tWCDMAREM:0\tARFCN:50,619\tREMPRD:30\tAUTOCFG:0\r\n";
//        AUTOREM:0	LTEREM:1	EARFCN:37900,38400,39150	GSMREM:0:	WCDMAREM:0	ARFCN:50,619	REMPRD:30	AUTOCFG:0
    }










    public static String SN="";//采集设备编号  字符最大长度 16
    public static String MAC="";//采集设备MAC地址  17
    public static String FW="";//软件版本号  10

    public static String BAND="";//Band
    public static String PLMN="";//PLMN

    public static String RF="";//射频状态
    public static String GPS="";//GPS状态
    public static String CELL="";//小区状态  小区是否建立成功：0：未建立 1:建立成功
    public static String TMP="";//板卡温度


    public static String DLARFCN="";//下行
    public static String ULARFCN="";//上行频点
    public static String PERIOD="";//采集周期
    public static String PMAX="";//发射功率
    public static String PA="";//整机输出功率
    public static String CAP="";//采集模式
    public static String PCI="";
    public static String TAC="";
    public static String CI="";
    public static String GAIN="";// 功放增益
    public static String MODE="";// 工作模式
    public static String RSTP="";// 信号发射功率



    public static String CNM="";// 空口同步状态
    public static String SYNC="";// 同步状态
    public static String RIP="";// 干扰测量




}
