package com.sm.android.locations.location.initData;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.sm.android.locations.location.Utils.MainUtils.MainUtilsThread;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import static com.sm.android.locations.location.initData.CommandUtils.SN;

public class TCPServer extends Thread {
    public static boolean isRun=true;
    Socket socket;
    ServerSocket serverSocket;
    InputStream inputStream;
    OutputStream outputStream;
    Message message;
    Handler handler;
    public Runnable runnable;
    public Runnable runnable2;
    public static String str="";
    private String substring;
    private String body;
    private String header;
    private String magic;
    private String id;
    private String data_size;
    private String enctypt_length;
    private String crc;
    private String device_name;
    private String timestamp;
    private String reserve;
    private String s;

    public TCPServer(Handler handler) {
        this.handler = handler;
        MyLog.i("TAG", "创建线程socket");
    }

    //定時器
    private int DELYED= 20000;
    Bundle bundle = new Bundle();
    private void accept() throws IOException {
        while (isRun) {//循环读取
            byte [] b=new byte[1024];
            while ((inputStream.read(b))!=-1){//读取一条
                str = toHexString1(b);

                substring = str.substring(96);
                body = StringToHex.convertHexToString(substring);

                header = str.substring(0, 96);


                magic = header.substring(0, 8);
                id = header.substring(8, 16);
                data_size = header.substring(16, 24);
                enctypt_length = header.substring(24, 32);
                crc = header.substring(32, 40);
                device_name = header.substring(40, 72);
                timestamp = header.substring(72, 80);
                reserve = header.substring(80, 96);


                //收到的参数类型
                    s = body.substring(0, 6);
                    Log.e("type", "type: "+ s);
                    Log.i("ylt", "消息内容之消息类型: "+ s +"  "+TimeStampExample.getDataParseTimestamp(TimeStampExample.getTimestamp10()));
                    Log.i("ylt", "Magic: "+ magic);
                    Log.i("ylt", "id(自增): "+ParseSystemUtil.hex16To10(id));
                    Log.i("ylt", "Data_size(消息内容的真实长度): "+ParseSystemUtil.hex16To10(data_size));
                    Log.i("ylt", "enctypt_length(密文长度): "+ enctypt_length);
                    Log.i("ylt", "CRC(校检码): "+ crc);
                    Log.i("ylt", "device_name(采集设备编号信息): "+StringToHex.convertHexToString(device_name));
                    Log.i("ylt", "timestamp(时间戳): "+TimeStampExample.getDataParseTimestamp(ParseSystemUtil.hex16To10(timestamp)));
                    Log.i("ylt", "Reserve(默认全0): "+ reserve);
                    Log.i("ylt", "body: "+ body);
                    message=Message.obtain();
                    message.what = 1;//接收
                    message.obj = StringToHex.convertHexToString(str);
                    bundle.putString("type", s);
                    message.setData(bundle);
                    handler.sendMessage(message);

                    if(s.contains("0301")){//心跳报文
                        MyLog.accept("0301", str,StringToHex.convertHexToString(str));
                        String[] split = body.split("\t");
                        for (String s1 : split) {
                            Log.i("0301ylt", "accept: "+s1);
                            if(s1.contains("SN")){
                                String[] strings = s1.split(":");
                                SN=strings[1];
                            }if(s1.contains("MAC")){
                                String[] strings = s1.split(":");
                                CommandUtils.MAC=strings[1];
                            }if(s1.contains("FW")){
                                String[] strings = s1.split(":");
                                CommandUtils.FW=strings[1];
                            }if(s1.contains("CELL")){
                                String[] strings = s1.split(":");
                                CommandUtils. CELL=strings[1];
                            }if(s1.contains("GPS")){
                                String[] strings = s1.split(":");
                                CommandUtils.GPS=strings[1];
                            }if(s1.contains("TMP")){
                                String[] strings = s1.split(":");
                                CommandUtils. TMP=strings[1];
                            }
                            if(s1.contains("RF:")){
                                String[] strings = s1.split(":");
                                CommandUtils.RF=strings[1];
                            }if(s1.contains("CNM:")){
                                String[] strings = s1.split(":");
                                CommandUtils.CNM=strings[1];
                            }if(s1.contains("SYNC:")){
                                String[] strings = s1.split(":");
                                CommandUtils.SYNC=strings[1];
                            }if(s1.contains("RIP:")){
                                String[] strings = s1.split(":");
                                CommandUtils.RIP=strings[1];
                            }
                        }
                        MyLog.i("0301ylt", "accept: "+CommandUtils.SN+"\r\n");
                        MyLog.i("0301ylt", "accept: "+CommandUtils.MAC+"\r\n");
                        MyLog.i("0301ylt", "accept: "+CommandUtils.FW+"\r\n");
                        MyLog.i("0301ylt", "accept: "+CommandUtils.BAND+"\r\n");
                        MyLog.i("0301ylt", "accept: "+CommandUtils.PLMN+"\r\n");
                        MyLog.i("0301ylt", "accept: "+CommandUtils.CELL+"\r\n");
                        MyLog.i("0301ylt", "accept: "+CommandUtils.RF+"\r\n");
                        MyLog.i("0301ylt", "accept: "+CommandUtils.GPS+"\r\n");
                        MyLog.i("0301ylt", "accept: "+CommandUtils.TMP+"\r\n");
                    }
                if(s.contains("0303")){//被定位的imsi上报
                    message=Message.obtain();
                    message.what = 0303;
                    message.obj = StringToHex.convertHexToString(str);
                    bundle.putString("type", s);
                    message.setData(bundle);
                    handler.sendMessage(message);
                    MyLog.accept("定位0303", str,StringToHex.convertHexToString(str));
                    MyLog.e("定位中",body.substring(0,24));
                }
                    if(s.contains("0208")){//获取公网参数回应
                    message=Message.obtain();
                    message.what = 0207;//0208
                    message.obj = StringToHex.convertHexToString(str);
                    bundle.putString("type", s);
                    message.setData(bundle);
                    handler.sendMessage(message);
                    MyLog.accept("0208", str,StringToHex.convertHexToString(str));


                        String[] split = body.split("\t");
                        for (String s1 : split) {
                            MyLog.i("0208YLT", s1);
                              if(s1.contains("DLARFCN")){
                                  MyLog.i("020202",s1);
                                  String[] strings = s1.split(":");
                                  if(strings.length>1){
                                      CommandUtils.DLARFCN=strings[1];
                                      MyLog.i("020202", CommandUtils.DLARFCN);
                                  }

                              }
                           if(s1.contains("ULARFCN")){
                                String[] strings = s1.split(":");
//                                if(!TextUtils.isEmpty(split1[1])){
                               if(strings.length>1){
                                    CommandUtils.ULARFCN=strings[1];
                                    MyLog.i("0208YLT",""+CommandUtils.ULARFCN+"\r\n");
                                }

//                                }
                            }
                            if(s1.contains("BAND")){
                                String[] strings = s1.split(":");
                                CommandUtils. BAND=strings[1];
                            }
                            if(s1.contains("PLMN")){
                                String[] strings = s1.split(":");
                                CommandUtils. PLMN=strings[1].substring(0,5);
                            }
                            if(s1.contains("PERIOD")){
                                String[] split1 = s1.split(":");
//                                if(!TextUtils.isEmpty(split1[1])){
                                if(split1.length>1){
                                    CommandUtils.PERIOD=split1[1];
                                    MyLog.i("0208YLT",""+CommandUtils.PERIOD+"\r\n");
                                }

//                                }
                            }
                            if(s1.contains("PMAX")){
                                String[] split1 = s1.split(":");
//                                if(!TextUtils.isEmpty(split1[1])){
                                if(split1.length>1){
                                        CommandUtils.PMAX=split1[1];
                                        MyLog.i("0208YLT",""+CommandUtils.PMAX+"\r\n");
                                    }
//                                }
                            }
                            if(s1.contains("PA")){
                                String[] split1 = s1.split(":");
//                                if(!TextUtils.isEmpty(split1[1])){
                                if(split1.length>1){
                                        CommandUtils.PA=split1[1];
                                        MyLog.i("0208YLT",""+CommandUtils.PA+"\r\n");
                                    }
//                                }
                            }
                            if(s1.contains("CAP")){
                                String[] split1 = s1.split(":");
//                                if(!TextUtils.isEmpty(split1[1])){
                                if(split1.length>1){
                                        CommandUtils.CAP=split1[1];
                                        MyLog.i("0208YLT",""+CommandUtils.CAP+"\r\n");
                                    }
//                                }
                            }
                          if(s1.contains("PCI:")){
//                              MyLog.i("0208YLTPCI",""+s1+"\r\n");
                              if(!s1.contains("CNM")){
//                                  MyLog.i("0208YLTPCI",""+s1+"\r\n");

                                  String[] split1 = s1.split(":");

                                  if(split1.length>1) {
                                      CommandUtils.PCI = split1[1];
                                      MyLog.i("0208YLTPCI", "" + CommandUtils.PCI + "\r\n");
                                  }
                              }

//
////                                }
                          }
                            if(s1.contains("TAC")){
                                String[] split1 = s1.split(":");
//                                if(!TextUtils.isEmpty(split1[1])){
                                if(split1.length>1){
                                        CommandUtils.TAC=split1[1];
                                        MyLog.i("0208YLT",""+CommandUtils.TAC+"\r\n");
                                    }
//                                }
                            }
                            if(s1.contains("CI")){
                                if(s1.startsWith("CI")){
                                    String[] split1 = s1.split(":");
                                    if(split1.length>1){
                                        CommandUtils.CI=split1[1];
                                        MyLog.i("0208YLT",""+CommandUtils.CI+"\r\n");
                                    }
                                }
                            }if(s1.contains("PMAX")){

                                    String[] split1 = s1.split(":");
                                    if(split1.length>1){
                                        CommandUtils.PMAX=split1[1];
                                        MyLog.i("0208YLT",""+CommandUtils.PMAX+"\r\n");
                                    }
                            }
                            if(s1.contains("GAIN")){

                                    String[] split1 = s1.split(":");
                                    if(split1.length>1){
                                        CommandUtils.GAIN=split1[1];
                                        MyLog.i("0208YLT",""+CommandUtils.GAIN+"\r\n");
                                    }
                            }
                            if(s1.contains("MODE")){
                                    String[] split1 = s1.split(":");
                                    if(split1.length>1){
                                        CommandUtils.MODE=split1[1];
                                        MyLog.i("0208YLT",""+CommandUtils.MODE+"\r\n");
                                    }
                            }
                            if(s1.contains("RSTP")){
                                    String[] split1 = s1.split(":");
                                    if(split1.length>1){
                                        CommandUtils.RSTP=split1[1];
                                        MyLog.i("0208YLT",""+CommandUtils.RSTP+"\r\n");
                                    }
                            }
                        }


                    }

                    if(s.contains("0302")){//小区工作状态中 被采集的imsi上报
                        message=Message.obtain();
                        message.what = 0302;
                        message.obj = StringToHex.convertHexToString(str);
                        bundle.putString("type", s);
                        message.setData(bundle);
                        handler.sendMessage(message);
                        MyLog.accept("0302", str,StringToHex.convertHexToString(str));
                        MyLog.e("采集中",body.substring(0,24));
                    }
                    if(s.contains("0202")){//基站向控制端发送设置小区工作参数的回应
                        message=Message.obtain();
                        message.what = 0202;
                        message.obj = StringToHex.convertHexToString(str);
                        bundle.putString("type", s);
                        message.setData(bundle);
                        handler.sendMessage(message);
                        MyLog.accept("0202", str,StringToHex.convertHexToString(str));
                    }

                    if(s.contains("0204")){//射频回应
                        message=Message.obtain();
                        message.what = 0204;//0208
                        message.obj = StringToHex.convertHexToString(str);
                        bundle.putString("type", s);
                        message.setData(bundle);
                        handler.sendMessage(message);
                        MyLog.accept("0204", str,StringToHex.convertHexToString(str));
                    }
                    if(s.contains("0210")){//黑名单回复
                        message=Message.obtain();
                        message.what = 0210;//0208
                        message.obj = StringToHex.convertHexToString(str);
                        bundle.putString("type", s);
                        message.setData(bundle);
                        handler.sendMessage(message);
                        MyLog.accept("0210", str,StringToHex.convertHexToString(str));
                    }
                    if(s.contains("0226")){//切换定位模式回复
                        message=Message.obtain();
                        message.what = 0226;
                        message.obj = StringToHex.convertHexToString(str);
                        bundle.putString("type", s);
                        message.setData(bundle);
                        handler.sendMessage(message);
                        MyLog.accept("0226", str,StringToHex.convertHexToString(str));
                    }
                    if(s.contains("0236")){//回复定位目标黑名单设置 UE
                        message=Message.obtain();
                        message.what = 0236;
                        message.obj = StringToHex.convertHexToString(str);
                        bundle.putString("type", s);
                        message.setData(bundle);
                        handler.sendMessage(message);
                        MyLog.accept("0236", str,StringToHex.convertHexToString(str));
                    }
                    if(s.contains("0201")){//扫频列表的回复 0305 0201
                        message=Message.obtain();
                        message.what = 0201;
                        message.obj = StringToHex.convertHexToString(str);
                        bundle.putString("type", s);
                        message.setData(bundle);
                        handler.sendMessage(message);
                        MyLog.accept("0201", str,StringToHex.convertHexToString(str));
                    } if(s.contains("0305")){//扫频列表的回复 0305 0201
                        message=Message.obtain();
                        message.what = 0305;
                        message.obj = StringToHex.convertHexToString(str);
                        bundle.putString("type", s);
                        message.setData(bundle);
                        handler.sendMessage(message);
                        MyLog.accept("0305", str,StringToHex.convertHexToString(str));
                    }
                    MyLog.e("type", message.getData().getString("type"));
            }
        }
    }

    public void send(final String ss) {
        ExecutorServiceUtils.cachedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    if(outputStream!=null){

                        outputStream.write(MainUtilsThread.hexStringToByteArray(ss));
                        if(!ss.contains("30343031")){//如果是控制端向基站发送的心跳报文就不接收了
                            message=Message.obtain();
                            message.what = 2;//发送
                            message.obj = ss;
                            handler.sendMessage(message);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    /*
     * btye[]字节数组转换成16进制的字符串
     */
    public static String toHexString1(byte[] b) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < b.length; ++i) {
            buffer.append(toHexString1(b[i]));
        }
        return buffer.toString();
    }

    public static String toHexString1(byte b) {
        String s = Integer.toHexString(b & 0xFF);
        if (s.length() == 1) {
            return "0" + s;
        } else {
            return s;
        }
    }

    @Override
    public void run() {
        try {
            Log.i("ylt", "?????????????: ");
                serverSocket = new ServerSocket(6969);
                socket = serverSocket.accept();
                if (socket != null){
                    //连接状态每10秒发送一次报文
                    Log.i("ylt", "连接成功: ");
                    System.out.println("连接成功");
                    inputStream = socket.getInputStream();
                    outputStream = socket.getOutputStream();
                    message=Message.obtain();
                    message.obj="连接成功";
                    message.what = 3;//连接成功
                    handler.sendMessage(message);
                    runnable = new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.i("ttttt", "连接成功每十秒向基带板发送一次报文: ");
                                long time = TimeStampExample.getDataParseTimestamp(TimeStampExample.getTimestamp10()).getTime();
                                int i = new Long(time).intValue();
                                String s = ParseSystemUtil.hex10To16(i);
                                String str22="00ffff000000000000000005000000000000000000000000000000000000000000000000"+s+"00000000"+"00000000"+"30343031";
                                send(str22);
                                handler.postDelayed(this, DELYED);
                            } catch (Exception e) {
                                Log.e("ylt"+"->runnable定时器", e.toString());
                            }
                        }
                    };
                    handler.postDelayed(runnable, DELYED);
                    accept();//如果板卡不接收数据就重置为空
                }

        } catch (IOException e) {
            e.printStackTrace();
        }
        MyLog.e("TCPSERVER", "accept2");
    }

    /**
     * 关闭连接  1234567890
     */
    public void close() {
        try {
            if (socket != null) {
                if(runnable!=null){
                    handler.removeCallbacks(runnable);
                    if(runnable==null){
                        MyLog.i("TAG", "close runnable");
                    }
                }
                MyLog.i("TAG", "close in");
                inputStream.close();
                MyLog.i("TAG", "close out");
                outputStream.close();
                MyLog.i("TAG", "close client");
                socket.close();
                if(serverSocket!=null){
                    serverSocket.close();
                }
            }
        } catch (Exception e) {
            MyLog.i("TAG", "close err");
            e.printStackTrace();
        }

    }

    public void sendPost(String header) {
        ExecutorServiceUtils.cachedThreadPool.execute(() -> {
            try {
                if(outputStream!=null){
                    outputStream.flush();
                    outputStream.write(MainUtilsThread.hexStringToByteArray(header));
                    message=Message.obtain();
                    message.what = 2;//发送
                    message.obj = header;
                    handler.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
