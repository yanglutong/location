package com.sm.android.locations.locations.Activity.Device;

import android.os.Build;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.AppUtils;
import com.lzy.widget.AlphaIndicator;
import com.sm.android.locations.locations.Base.BaseActivity;
import com.sm.android.locations.locations.Base.BaseFragment;
import com.sm.android.locations.locations.R;
import com.sm.android.locations.locations.Utils.UtilsView;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static com.sm.android.locations.locations.Constant.Constant.BAND1;
import static com.sm.android.locations.locations.Constant.Constant.BLACKLOCATION1;
import static com.sm.android.locations.locations.Constant.Constant.BLACKLOCATION2;
import static com.sm.android.locations.locations.Constant.Constant.BLACKOPENSET1;
import static com.sm.android.locations.locations.Constant.Constant.BLACKOPENSET2;
import static com.sm.android.locations.locations.Constant.Constant.BLACKTIME1;
import static com.sm.android.locations.locations.Activity.Main.MainActivity.SPBEANLIST1;
import static com.sm.android.locations.locations.Activity.Main.MainActivity.SPBEANLIST2;
import static com.sm.android.locations.locations.Constant.Constant.BLACKTIME2;
import static com.sm.android.locations.locations.Constant.Constant.BLACKTIMESET1;
import static com.sm.android.locations.locations.Constant.Constant.BLACKTIMESET2;
import static com.sm.android.locations.locations.Constant.Constant.CALLBLACKFLAG1;
import static com.sm.android.locations.locations.Constant.Constant.CALLBLACKFLAG2;
import static com.sm.android.locations.locations.Constant.Constant.CALLBLACKFLAGSET1;
import static com.sm.android.locations.locations.Constant.Constant.CALLBLACKFLAGSET2;
import static com.sm.android.locations.locations.Constant.Constant.CALLBLACKLOCATION1;
import static com.sm.android.locations.locations.Constant.Constant.CALLBLACKLOCATION2;
import static com.sm.android.locations.locations.Constant.Constant.CALLBLACKOPEN1;
import static com.sm.android.locations.locations.Constant.Constant.CALLBLACKOPEN2;
import static com.sm.android.locations.locations.Constant.Constant.CELLID1;
import static com.sm.android.locations.locations.Constant.Constant.DBM1;
import static com.sm.android.locations.locations.Constant.Constant.DK1;
import static com.sm.android.locations.locations.Constant.Constant.GZMS1;
import static com.sm.android.locations.locations.Constant.Constant.SHUAIJIAN1;
import static com.sm.android.locations.locations.Constant.Constant.ZENGYI1;
import static com.sm.android.locations.locations.Constant.Constant.SHUAIJIAN2;
import static com.sm.android.locations.locations.Constant.Constant.ZENGYI2;
import static com.sm.android.locations.locations.Constant.Constant.ZHZQ1;
import static com.sm.android.locations.locations.Constant.Constant.SBZQ1;
import static com.sm.android.locations.locations.Constant.Constant.TYPE1;
import static com.sm.android.locations.locations.Constant.Constant.DWON1;
import static com.sm.android.locations.locations.Constant.Constant.IP1;
import static com.sm.android.locations.locations.Constant.Constant.IP2;
import static com.sm.android.locations.locations.Constant.Constant.PCI1;
import static com.sm.android.locations.locations.Constant.Constant.PLMN1;
import static com.sm.android.locations.locations.Constant.Constant.TAC1;
import static com.sm.android.locations.locations.Constant.Constant.UEIMSI;
import static com.sm.android.locations.locations.Constant.Constant.UEMAX1;
import static com.sm.android.locations.locations.Constant.Constant.UEMAXOF1;
import static com.sm.android.locations.locations.Constant.Constant.UP1;
import static com.sm.android.locations.locations.Constant.Constant.CELLID2;
import static com.sm.android.locations.locations.Constant.Constant.DBM2;
import static com.sm.android.locations.locations.Constant.Constant.DK2;
import static com.sm.android.locations.locations.Constant.Constant.TYPE2;
import static com.sm.android.locations.locations.Constant.Constant.DWON2;

import static com.sm.android.locations.locations.Constant.Constant.PCI2;
import static com.sm.android.locations.locations.Constant.Constant.PLMN2;
import static com.sm.android.locations.locations.Constant.Constant.TAC2;
import static com.sm.android.locations.locations.Constant.Constant.UP2;
import static com.sm.android.locations.locations.Constant.Constant.BAND2;
import static com.sm.android.locations.locations.Test.ReceiveTask.StringPin;
import static com.sm.android.locations.locations.Utils.MainUtils.MainUtils2.toIMSI;
import static com.sm.android.locations.locations.Constant.Constant.DEVICENUMBER1;
import static com.sm.android.locations.locations.Constant.Constant.DEVICENUMBER2;
import static com.sm.android.locations.locations.Constant.Constant.HARDWAREVERSION1;
import static com.sm.android.locations.locations.Constant.Constant.HARDWAREVERSION2;
import static com.sm.android.locations.locations.Constant.Constant.SOFTWAREVERSION1;
import static com.sm.android.locations.locations.Constant.Constant.SOFTWAREVERSION2;
import static com.sm.android.locations.locations.Constant.Constant.SNNUMBER1;
import static com.sm.android.locations.locations.Constant.Constant.SNNUMBER2;
import static com.sm.android.locations.locations.Constant.Constant.MACADDRESS1;
import static com.sm.android.locations.locations.Constant.Constant.MACADDRESS2;
import static com.sm.android.locations.locations.Constant.Constant.UBOOTVERSION1;
import static com.sm.android.locations.locations.Constant.Constant.UBOOTVERSION2;
import static com.sm.android.locations.locations.Constant.Constant.BOARDTEMPERATURE1;
import static com.sm.android.locations.locations.Constant.Constant.BOARDTEMPERATURE2;
import static com.sm.android.locations.locations.Constant.Constant.GZMS2;
import static com.sm.android.locations.locations.Constant.Constant.ZHZQ2;
import static com.sm.android.locations.locations.Constant.Constant.UEIMS2;
import static com.sm.android.locations.locations.Constant.Constant.SBZQ2;
import static com.sm.android.locations.locations.Constant.Constant.UEMAXOF2;
import static com.sm.android.locations.locations.Constant.Constant.UEMAX2;

/**
 * 设备信息
 */
public class DeviceInfoActivity extends BaseActivity implements View.OnClickListener {
    LinearLayout layout;
    TextView title;
    ImageView iv_finish, iv_add, imageView;
    Button button0, button1, button2, button3;
    ViewPager viewpage;
    List<Button> buttonList = new ArrayList<>();

    @Override
    protected void initQx() {

    }

    @Override
    protected void init() throws UnsupportedEncodingException {
//        setStatBar();//去导航栏
        UtilsView.setViewVisibility(this, layout, title, imageView, getResources().getString(R.string.deviceinfo), false, iv_finish, true);
    }

    /**
     * findview
     * 按钮监听
     */
    @Override
    protected void findViews() {
        layout = findViewById(R.id.ll_tab);
        title = findViewById(R.id.titles);
        imageView = findViewById(R.id.iv_menu);
        iv_finish = findViewById(R.id.iv_finish);
        iv_finish.setOnClickListener(new View.OnClickListener() {//关闭
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        button0 = findViewById(R.id.bt_0);
        button1 = findViewById(R.id.bt_1);
        button2 = findViewById(R.id.bt_2);
        button3 = findViewById(R.id.bt_3);
        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        buttonList.add(button0);
        buttonList.add(button1);
//        buttonList.add(button2);
//        buttonList.add(button3);
        viewpage = findViewById(R.id.viewpager);
        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        myFragmentPagerAdapter.getItem(0);
        viewpage.setAdapter(myFragmentPagerAdapter);
        viewpage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onPageSelected(int i) {
                Log.d("viewpagercount", "findViews: " + i);
                if (i == 0) {
                    setBackground(buttonList, 0);
                    return;
                }
                if (i == 1) {
                    setBackground(buttonList, 1);
                    return;

                }
//                if (i == 2) {
//                    setBackground(buttonList, 2);
//                    return;
//
//                }
//                if (i == 3) {
//                    setBackground(buttonList, 3);
//                    return;
//
//                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                Log.d("nzq", "onPageScrollStateChanged: "+i);
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_0:
                viewpage.setCurrentItem(0);
                break;
            case R.id.bt_1:
                viewpage.setCurrentItem(1);
                break;
//            case R.id.bt_2:
//                viewpage.setCurrentItem(2);
//                break;
//            case R.id.bt_3:
//                viewpage.setCurrentItem(3);
//                break;
        }
    }

    /**
     * 轮播图适配器
     */
    public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        private List<BaseFragment> fragments = new ArrayList<>();
        private String[] titles = {//
                "第一页\n\n重点看下面的的图标是渐变色，随着滑动距离的增加，颜色逐渐过度",//
                "第二页\n\n重点看下面的的图标是渐变色，随着滑动距离的增加，颜色逐渐过度",//
                "第三页\n\n重点看下面的的图标是渐变色，随着滑动距离的增加，颜色逐渐过度", //
                "第四页\n\n重点看下面的的图标是渐变色，随着滑动距离的增加，颜色逐渐过度"};

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
            fragments.add(new TextFragment0());
            fragments.add(new TextFragment1());
//            fragments.add(new TextFragment2());
//            fragments.add(new TextFragment3());
        }


        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

    }

    @Override
    protected int getView() {
        int activity_device_info = R.layout.activity_device_info;
        return activity_device_info;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setBackground(List<Button> buttonList, int i) {
        Log.d("buttons", "setBackground: "+buttonList.size()+"---"+i);
        for (int j = 0; j < buttonList.size(); j++) {
            if (i == 0) {
                button0.setBackground(getResources().getDrawable(R.mipmap.duan));
                button0.setTextColor(getResources().getColor(R.color.white));
            } else {
                buttonList.get(j).setBackground(null);
                buttonList.get(j).setTextColor(getResources().getColor(R.color.black));
            }
            if (i == 1) {
//                button1.setBackground(getResources().getDrawable(R.mipmap.duan));
//                button1.setTextColor(getResources().getColor(R.color.white));
                button1.setBackground(getResources().getDrawable(R.mipmap.duan));
                button1.setTextColor(getResources().getColor(R.color.white));
//                button1.setText("aaa");
            } else {
                buttonList.get(j).setBackground(null);
                buttonList.get(j).setTextColor(getResources().getColor(R.color.black));
            }
//            if (i == 2) {
//                button2.setBackground(getResources().getDrawable(R.mipmap.duan));
//                button2.setTextColor(getResources().getColor(R.color.white));
//            } else {
//                buttonList.get(j).setBackground(null);
//                buttonList.get(j).setTextColor(getResources().getColor(R.color.black));
//            }
//            if (i == 3) {
//                button3.setBackground(getResources().getDrawable(R.mipmap.duan));
//                button3.setTextColor(getResources().getColor(R.color.white));
//            } else {
//                buttonList.get(j).setBackground(null);
//                buttonList.get(j).setTextColor(getResources().getColor(R.color.black));
//            }
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //设备信息的变量------
        //设备号
        DEVICENUMBER1 = "";
        DEVICENUMBER2 = "";
        //硬件版本号
        HARDWAREVERSION1 = "";
        HARDWAREVERSION2 = "";
        //软件版本
        SOFTWAREVERSION1 = "";
        SOFTWAREVERSION2 = "";
        //SN号
        SNNUMBER1 = "";
        SNNUMBER2 = "";
        //MAC地址
        MACADDRESS1 = "";
        MACADDRESS2 = "";
        //UBOOT 版本号
        UBOOTVERSION1 = "";
        UBOOTVERSION2 = "";
        //板卡温度
        BOARDTEMPERATURE1 = "";
        BOARDTEMPERATURE2 = "";
        //当前小区信息状态响应
        PLMN1 = "";
        PLMN2 = "";
        UP1 = "";
        UP2 = "";
        DWON1 = "";
        DWON2 = "";
        BAND1 = "";
        BAND2 = "";
        DK1 = "";
        DK2 = "";
        TAC1 = "";
        TAC2 = "";
        PCI1 = "";
        PCI2 = "";
        CELLID1 = "";
        CELLID2 = "";
        DBM1 = "";
        DBM2 = "";
        TYPE1 = "";
        TYPE2 = "";
        //定位模式
        GZMS1 = "";//工作模式
        ZHZQ1 = "";//抓号周期
        UEIMSI = "";//UEIMSO
        SBZQ1 = "";//测量值上报周期
        UEMAXOF1 = "";//ue最大功率发设开关
        UEMAX1 = "";//ue最大发设功率

        GZMS2 = "";//工作模式
        ZHZQ2 = "";//抓号周期
        UEIMS2 = "";//UEIMSO
        SBZQ2 = "";//测量值上报周期
        UEMAXOF2 = "";//ue最大功率发设开关
        UEMAX2 = "";//ue最大发设功率

        //接受增益和衰减
        ZENGYI1 = "";
        SHUAIJIAN1 = "";
        ZENGYI2 = "";
        SHUAIJIAN2 = "";
    }
}
