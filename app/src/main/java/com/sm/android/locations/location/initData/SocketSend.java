package com.sm.android.locations.location.initData;

import com.sm.android.locations.location.sos.SOSActivity;

import java.util.ArrayList;
import java.util.List;

/**发送tcp  双重检查懒汉式
 * @author: 小杨同志
 * @date: 2021/7/21
 */
public class SocketSend {
        private static volatile SocketSend instance;

        private SocketSend() {}

        public static SocketSend getInstance() {
            if (instance == null) {
                synchronized (SocketSend.class) {
                    if (instance == null) {
                        instance = new SocketSend();
                    }
                }
            }
            return instance;
        }
        //发送扫频列表
        public void setSaoPin(List<Integer> list,CallBackSetState callBackSetState){
            if(SOSActivity.socketTcpServer!=null){
                if(!CommandUtils.type.equals("")){
                    MyLog.e("Socket", CommandUtils.saoPin(list));

                    MyLog.e("0101", "下行频点"+list);
                    SOSActivity.socketTcpServer.sendPost(CommandUtils.saoPin(list));//发送扫频列表获取公网参数
                    //发送扫频时状态改为扫频中
                    CommandUtils.sbZt="扫频中";
                    callBackSetState.showStateBar("扫频中");
                }
            }
        }
}
