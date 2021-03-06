package com.sm.android.locations.location.viewpagermain.Fragment;

import android.util.Log;

import com.sm.android.locations.location.Test.Setzy;
import com.sm.android.locations.location.Utils.MainUtils.MainUtilsThread;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import static com.sm.android.locations.location.Constant.Constant.IP1;
import static com.sm.android.locations.location.Constant.Constant.IP2;
import static com.sm.android.locations.location.Utils.MainUtils.MainUtils.DS;

public class SendUtils {
    public static void setzy(final int i, final int sb) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String ip = "";
                if (sb == 1) {
                    ip = IP1;
                }
                if (sb == 2) {
                    ip = IP2;
                }
                DatagramSocket socket = null;
                InetAddress address = null;//你本机的ip地址
                Log.e("znzq", "run: nzqsend" + i);
                String s = Setzy.acceptGain(i);
//                Log.d(TAG, "run: " + s);
                byte[] outputData = MainUtilsThread.hexStringToByteArray(Setzy.acceptGain(i));
                try {
                    socket = new DatagramSocket();
                    address = InetAddress.getByName(ip);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (SocketException e) {
                    e.printStackTrace();
                }
                ;
                DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, address, 3345);
                Log.e("startLocation1s", "run: sendsocket端口号" + outputPacket.getPort() + "Ip地址:" + outputPacket.getAddress().toString() + "数据:" + outputPacket.getData());
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


    public static void setzySeek(final int i, final int sb, String string) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String ip = "";
                if (sb == 1) {
                    ip = IP1;
                }
                if (sb == 2) {
                    ip = IP2;
                }
                DatagramSocket socket = null;
                InetAddress address = null;//你本机的ip地址
//                Log.e("znzq", "run: nzqsend" + i);
                String s = Setzy.acceptGain(i);
//                Log.d(TAG, "run: " + s);
                byte[] outputData = MainUtilsThread.hexStringToByteArray(string);
                try {
                    socket = new DatagramSocket();
                    address = InetAddress.getByName(ip);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (SocketException e) {
                    e.printStackTrace();
                }
                ;
                DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, address, 3345);
                Log.e("setzySeek", "run: sendsocket端口号" + outputPacket.getPort() + "Ip地址:" + outputPacket.getAddress().toString() + "数据:" + outputPacket.getData());
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

    /**
     * 不同步建立TDD 小区
     *
     * @param i
     * @param sb
     * @param string
     */
    public static void setbutongbu(final int i, final int sb, String string) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String ip = "";
                if (sb == 1) {
                    ip = IP1;
                }
                if (sb == 2) {
                    ip = IP2;
                }
                DatagramSocket socket = null;
                InetAddress address = null;//你本机的ip地址
//                Log.e("znzq", "run: nzqsend" + i);
                String s = Setzy.acceptGain(i);
//                Log.d(TAG, "run: " + s);
                byte[] outputData = MainUtilsThread.hexStringToByteArray(string);
                try {
                    socket = new DatagramSocket();
                    address = InetAddress.getByName(ip);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (SocketException e) {
                    e.printStackTrace();
                }
                ;
                DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, address, 3345);
                Log.e("setzySeek", "run: sendsocket端口号" + outputPacket.getPort() + "Ip地址:" + outputPacket.getAddress().toString() + "数据:" + outputPacket.getData());
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
