package com.sm.android.locations.location.sos;

import android.content.Context;
import android.icu.text.DecimalFormat;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sm.android.locations.location.Activity.Main.Adapter.RyZmAdapterGk;
import com.sm.android.locations.location.Activity.Main.Adapter.RyZmAdapterdw;
import com.sm.android.locations.location.Base.BasePresenter;
import com.sm.android.locations.location.Base.BaseView;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.AddPararBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.ZmBeanGKTongji;
import com.sm.android.locations.location.initData.CallBackSetState;
import com.sm.android.locations.location.initData.TCPServer;

import java.util.List;

public class SOSVIEW {
    interface View extends BaseView<MainPresenter> {//回调更新

        void fengshanUp(boolean b);

        void stopdwup(int i);

        void zhishiqiehuan(int device, String tf);

        void quxian(String data, int device);

        void zidongsaopinjianlixiaoqu(int device);//自动扫频建立小区

        void labaup(int device, boolean bs);

        void gkqiehuan(String tf, int device);

        void gkstop();

        void gkList(List<ZmBeanGKTongji> list);//回调管控的数据便于选择定位目标
        void zhuanhuandw(int device);
    }

    interface MainPresenter extends BasePresenter {//使用

        void fengshanSet(boolean b, ImageView imageView, Context context);//风扇开关

        /**
         * @param device      设备编号
         * @param tf          制式
         * @param context     上下文
         * @param spinnerDown //选择的频点
         * @param sbzhuangtai // 设备的状态
         */
        void startSD(int device, String tf, Context context, String spinnerDown, String sbzhuangtai, String tv, TCPServer tcpServer);//手动定位
        void startSaoPin(int device, String tf, Context context, String spinnerDown, String sbzhuangtai, String tv, TCPServer tcpServer);//手动定位
        void setZy(TCPServer tcpServer,String zy);//设置增益


        void buildSD(final String spinnerS1, final int i, final String sb1, final Context context,TCPServer tcpServer,List<AddPararBean> sendListBlack);//手动建立小区
        void buildSDSaoPin(final String spinnerS1, final int i, final String sb1, final Context context,TCPServer tcpServer,List<AddPararBean> sendListBlack);//手动建立小区

        void stopdw(final int i, final Context context, String sbzhuangtai,TCPServer tcpServer);//停止定位

        void setRyimsidw(Context context, RecyclerView ry_zhenma, Message msg, RyZmAdapterdw ryZmAdapter, EditText et_zhenmasearchdw, TextView tv_searchNumdw);

        void setIMSInengliangzhi(Context context, int device, String tf, DecimalFormat dfBaoshu, TextView sb1_jl, boolean laba, Message msg, TextToSpeech textToSpeech);

        void setIMSIshow(Message msg, TextView tv_imsi1_dw);

        void setIMSIshow2(Message msg, TextView tv_imsi1_dw);

        void spbuilsshow(Context context, int device, int yy, String tf1, String tf2, CallBackSetState c);

        void spbuils(Context context, int device, int yy, String tf1, String tf2,CallBackSetState c);//扫频

        void setStart(int device, final boolean b, final int maintype, final String sb1, String sb2, final String sp1, final String sp2, final Context context, final String tf1, final String tf2, int restart, boolean phoneFalg);
        void setStartYy(TCPServer tcpServer,int device, final boolean b, String sb, final String sp, final Context context,  final String tf, boolean phoneFalg);

        void saopinjianlixiaoqu(Context context, int device, String tf, String down);

        void setlaba(Context context, ImageView imageView, int device, boolean laba);

        void setsosStart(Context context, String tf1, String tf2, String sb1, String sb2, int type);

        void chongdingxiang1(String down);

        void chongdingxiang2(String down);

        void chongdingxiangSET1();

        void chongdingxiangSET2();

        void setryGK(Context context, RecyclerView recyclerView, String down1, String down2, RyZmAdapterGk ryZmAdapterdw, Message msg,TextView sos_tv_searchNum_zm,RyZmAdapterGk.GKCallBack gkCallBack);

        void spbuilsGK(Context context, int device, int yy, String tf1, String tf2);//扫频  弹出窗.

        void stopGK(Context context);
    }
}
