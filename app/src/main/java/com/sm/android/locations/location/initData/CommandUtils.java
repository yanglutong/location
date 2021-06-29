package com.sm.android.locations.location.initData;

import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.AddPararBean;

import java.util.ArrayList;
import java.util.List;

/**给基带板发送指令
 * @author: 小杨同志
 * @date: 2021/6/17
 */
public class CommandUtils {

    public static String xqType="0102";//小区设置参数的类型
    /**建立小区设置工作参数  0202  0成功 1失败
     * @description
     * @param
     * @return 
     * @author lutong
     * @time 2021/6/17 16:42
     */
    public static StringBuffer setXq(String PLMN,String TAC,String DLARFCN,String ULARFCN,String PCI,String BAND,String CI){
        /*设置小区参数*/
        StringBuffer sf = new StringBuffer();
        sf.append("PLMN:").append(PLMN).append("\t");
        sf.append("TAC:").append(TAC).append("\t");
        sf.append("DLARFCN:").append(DLARFCN).append("\t");
        sf.append("ULARFCN:").append(ULARFCN).append("\t");
        sf.append("PCI:").append(PCI).append("\t");
        sf.append("BAND:").append(BAND).append("\t");
        sf.append("CI:").append(CI).append("\r\n");
        MyLog.send("0102",sf.toString());
        return sf;
    }



    public static String setBlackList="";//设置黑名单的值
    public static String blackType="0110";//小区设置参数的类型
    public static List<String> imsilist;//存放黑名单列表
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


}
