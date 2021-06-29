package com.sm.android.locations.location.viewpagermain.Fragment.frament1Packet;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sm.android.locations.location.Activity.Main.Adapter.RyImsiAdapter;
import com.sm.android.locations.location.Activity.Main.IT.SaoPinCallback;
import com.sm.android.locations.location.Base.BasePresenter;
import com.sm.android.locations.location.Base.BaseView;
import com.sm.android.locations.location.Utils.View.LineChartView;
import com.sm.android.locations.location.Utils.pop.DLPopItem;
import com.sm.android.locations.location.Utils.pop.DLPopupWindow;

import java.util.List;
import java.util.Timer;

public class FragmentView0 {
    interface View extends BaseView<FragmentPresenter1> {//更新

        void WifiUp(ImageView imageView, Drawable drawable);//wifi状态

        void fsup(Drawable drawable, boolean fs);//风扇更新

        void qxzhedieUp(Drawable drawable);//曲线图折叠

        void ZmzhedieUp(Drawable drawable);//侦码记录折叠

        void VoiceUp(ImageView imageView, Drawable drawable, boolean laba, int device);//声音开关

        void MenuUp(Activity activity);//侧边按钮 注:无更新

        void SpinnerUp(int devcice, String spStr, String tf);

        void UpEstablish(int device, String str);

        /**
         * 手动定位
         *
         * @param device
         * @param down
         */
        void StartupSDlocation(int device, String down);

        /**
         * 手动侦码
         *
         * @param device
         * @param down
         */
        void StartupSDZM(int device, String down);
//        /**
//         *
//         * @param device  设备编号
//         * @param context
//         * @param YYtype 扫频运营商
//         * @param down  下行频点
//         */
//        void StartUp(int device, Context context, int YYtype, String down);

        /**
         * @param s  状态
         * @param i  设备
         * @param ip IP发送的IP
         */
        void StopUp(String s, int i, String ip);//停止定位/侦码 回调

        /**
         * //         * @param 设备1手动模式轮循
         */
        void Start1SD();

        /**
         * //         * @param 设备2手动模式轮循
         */
        void Start2SD();

        /**
         * 设备1自动轮循模式
         */
        void Start1ZDlocation();
    }

    interface FragmentPresenter1 extends BasePresenter {//使用

        void startlocation(Context context,String type, int device, boolean zd, String tf,String down,String down2);

        void setWIFI(Context context, ImageView tv, String type);

        void setFS(Context context, ImageView tv, boolean fengshan);//设置风扇

        void setIMSIAdapter(Context context, RecyclerView recyclerView, RyImsiAdapter ryImsiAdapter);//无回调

        void setQxzhedie(Context context, ImageButton iv, LineChartView lineChartView);//曲线折叠

        void setZmzhedie(Context context, ImageButton iv, LinearLayout linearLayout, RecyclerView recyclerView);//设置侦码折叠

        void setVoice(Context context, int i, ImageView imageView, boolean b);//设置喇叭

        void setmenu(Context context, DLPopupWindow popupWindow, List<DLPopItem> mList);//侧边按钮点击事件

        void SetSpinner(Context context, Spinner spinner, int device);

        void SetStatusBar(Context context, ScrollView myScrollView);

        void setStop(Context context, int device, Button button);//停止定位/停止侦码


//        void start1(Context context, String device, int type, Timer timer);//开启定位

        void start1(Context context, boolean ms, TextView tvtype1, Timer timer, SaoPinCallback saoPinCallback);

        void start2(Context context, boolean ms, TextView tvtype1, Timer timer, SaoPinCallback saoPinCallback);

        /**
         * 发送黑名单列表
         *
         * @param context
         * @param device  设备
         * @param button  启动设备的按钮
         * @param strDown 下行频点
         */
        void sendBlack(Context context, int device, Button button, String strDown);

        /**
         * 定位模式设置
         *
         * @param device       设备
         * @param locationType 设备定位模式
         * @param tf           设备制式 TDD FDD
         */
        void setLocation(int device, int locationType, Context context, String tf, String type, String down);

        /**
         * 手动建立小区
         *
         * @param context
         * @param device  设备编号
         * @param down    建立的下行频点
         * @param type    设备状态
         */
        void setEstablish(Context context, int device, String down, String type);

        void qxListda(List<Integer> list1, List<Integer> list2, List<Integer> list3, List<Integer> list4);//数据初始化 不更新
    }
}
