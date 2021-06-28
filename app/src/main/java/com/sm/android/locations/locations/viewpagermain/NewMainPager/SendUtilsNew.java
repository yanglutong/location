package com.sm.android.locations.locations.viewpagermain.NewMainPager;

import android.util.Log;

import com.sm.android.locations.locations.Constant.Constant;
import com.sm.android.locations.locations.Utils.MainUtils.MainUtilsThread;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import static com.sm.android.locations.locations.Constant.Constant.IP1;
import static com.sm.android.locations.locations.Utils.MainUtils.MainUtils.DS;

public class SendUtilsNew {
    public static void fs(final boolean b) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                DatagramSocket socket = null;
                InetAddress address = null;//你本机的ip地址
                byte[] outputData = null;
                if (b) {
                    outputData = MainUtilsThread.hexStringToByteArray(Constant.CLOSEGF);
                } else {
                    outputData = MainUtilsThread.hexStringToByteArray(Constant.OPENGF);
                }
                try {
                    socket = new DatagramSocket();

                    address = InetAddress.getByName(IP1);

                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (SocketException e) {
                    e.printStackTrace();
                }
                ;
                DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, address, 3345);
//                        DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, reIP, Integer.parseInt(et_port.getText().toString()));
                Log.e("nzqsendstart1Black", "run: sendsocket端口号" + outputPacket.getPort() + "Ip地址:" + outputPacket.getAddress().toString() + "数据:" + outputPacket.getData());

                try {
                    //                                    socket.send(outputPacket);
                    DS.send(outputPacket);
//                    interrupted();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
