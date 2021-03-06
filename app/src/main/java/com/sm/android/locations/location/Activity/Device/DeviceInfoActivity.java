package com.sm.android.locations.location.Activity.Device;

import android.os.Build;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.sm.android.locations.location.Base.BaseActivity;
import com.sm.android.locations.location.Base.BaseFragment;
import com.sm.android.locations.location.R;
import com.sm.android.locations.location.Utils.UtilsView;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static com.sm.android.locations.location.Constant.Constant.BAND1;
import static com.sm.android.locations.location.Constant.Constant.CELLID1;
import static com.sm.android.locations.location.Constant.Constant.DBM1;
import static com.sm.android.locations.location.Constant.Constant.DK1;
import static com.sm.android.locations.location.Constant.Constant.GZMS1;
import static com.sm.android.locations.location.Constant.Constant.SHUAIJIAN1;
import static com.sm.android.locations.location.Constant.Constant.ZENGYI1;
import static com.sm.android.locations.location.Constant.Constant.SHUAIJIAN2;
import static com.sm.android.locations.location.Constant.Constant.ZENGYI2;
import static com.sm.android.locations.location.Constant.Constant.ZHZQ1;
import static com.sm.android.locations.location.Constant.Constant.SBZQ1;
import static com.sm.android.locations.location.Constant.Constant.TYPE1;
import static com.sm.android.locations.location.Constant.Constant.DWON1;
import static com.sm.android.locations.location.Constant.Constant.PCI1;
import static com.sm.android.locations.location.Constant.Constant.PLMN1;
import static com.sm.android.locations.location.Constant.Constant.TAC1;
import static com.sm.android.locations.location.Constant.Constant.UEIMSI;
import static com.sm.android.locations.location.Constant.Constant.UEMAX1;
import static com.sm.android.locations.location.Constant.Constant.UEMAXOF1;
import static com.sm.android.locations.location.Constant.Constant.UP1;
import static com.sm.android.locations.location.Constant.Constant.CELLID2;
import static com.sm.android.locations.location.Constant.Constant.DBM2;
import static com.sm.android.locations.location.Constant.Constant.DK2;
import static com.sm.android.locations.location.Constant.Constant.TYPE2;
import static com.sm.android.locations.location.Constant.Constant.DWON2;

import static com.sm.android.locations.location.Constant.Constant.PCI2;
import static com.sm.android.locations.location.Constant.Constant.PLMN2;
import static com.sm.android.locations.location.Constant.Constant.TAC2;
import static com.sm.android.locations.location.Constant.Constant.UP2;
import static com.sm.android.locations.location.Constant.Constant.BAND2;
import static com.sm.android.locations.location.Constant.Constant.DEVICENUMBER1;
import static com.sm.android.locations.location.Constant.Constant.DEVICENUMBER2;
import static com.sm.android.locations.location.Constant.Constant.HARDWAREVERSION1;
import static com.sm.android.locations.location.Constant.Constant.HARDWAREVERSION2;
import static com.sm.android.locations.location.Constant.Constant.SOFTWAREVERSION1;
import static com.sm.android.locations.location.Constant.Constant.SOFTWAREVERSION2;
import static com.sm.android.locations.location.Constant.Constant.SNNUMBER1;
import static com.sm.android.locations.location.Constant.Constant.SNNUMBER2;
import static com.sm.android.locations.location.Constant.Constant.MACADDRESS1;
import static com.sm.android.locations.location.Constant.Constant.MACADDRESS2;
import static com.sm.android.locations.location.Constant.Constant.UBOOTVERSION1;
import static com.sm.android.locations.location.Constant.Constant.UBOOTVERSION2;
import static com.sm.android.locations.location.Constant.Constant.BOARDTEMPERATURE1;
import static com.sm.android.locations.location.Constant.Constant.BOARDTEMPERATURE2;
import static com.sm.android.locations.location.Constant.Constant.GZMS2;
import static com.sm.android.locations.location.Constant.Constant.ZHZQ2;
import static com.sm.android.locations.location.Constant.Constant.UEIMS2;
import static com.sm.android.locations.location.Constant.Constant.SBZQ2;
import static com.sm.android.locations.location.Constant.Constant.UEMAXOF2;
import static com.sm.android.locations.location.Constant.Constant.UEMAX2;

/**
 * ????????????
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
//        setStatBar();//????????????
        UtilsView.setViewVisibility(this, layout, title, imageView, getResources().getString(R.string.deviceinfo), false, iv_finish, true);
    }

    /**
     * findview
     * ????????????
     */
    @Override
    protected void findViews() {
        layout = findViewById(R.id.ll_tab);
        title = findViewById(R.id.titles);
        imageView = findViewById(R.id.iv_menu);
        iv_finish = findViewById(R.id.iv_finish);
        iv_finish.setOnClickListener(new View.OnClickListener() {//??????
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
     * ??????????????????
     */
    public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        private List<BaseFragment> fragments = new ArrayList<>();
        private String[] titles = {//
                "?????????\n\n??????????????????????????????????????????????????????????????????????????????????????????",//
                "?????????\n\n??????????????????????????????????????????????????????????????????????????????????????????",//
                "?????????\n\n??????????????????????????????????????????????????????????????????????????????????????????", //
                "?????????\n\n??????????????????????????????????????????????????????????????????????????????????????????"};

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
        //?????????????????????------
        //?????????
        DEVICENUMBER1 = "";
        DEVICENUMBER2 = "";
        //???????????????
        HARDWAREVERSION1 = "";
        HARDWAREVERSION2 = "";
        //????????????
        SOFTWAREVERSION1 = "";
        SOFTWAREVERSION2 = "";
        //SN???
        SNNUMBER1 = "";
        SNNUMBER2 = "";
        //MAC??????
        MACADDRESS1 = "";
        MACADDRESS2 = "";
        //UBOOT ?????????
        UBOOTVERSION1 = "";
        UBOOTVERSION2 = "";
        //????????????
        BOARDTEMPERATURE1 = "";
        BOARDTEMPERATURE2 = "";
        //??????????????????????????????
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
        //????????????
        GZMS1 = "";//????????????
        ZHZQ1 = "";//????????????
        UEIMSI = "";//UEIMSO
        SBZQ1 = "";//?????????????????????
        UEMAXOF1 = "";//ue????????????????????????
        UEMAX1 = "";//ue??????????????????

        GZMS2 = "";//????????????
        ZHZQ2 = "";//????????????
        UEIMS2 = "";//UEIMSO
        SBZQ2 = "";//?????????????????????
        UEMAXOF2 = "";//ue????????????????????????
        UEMAX2 = "";//ue??????????????????

        //?????????????????????
        ZENGYI1 = "";
        SHUAIJIAN1 = "";
        ZENGYI2 = "";
        SHUAIJIAN2 = "";
    }
}
