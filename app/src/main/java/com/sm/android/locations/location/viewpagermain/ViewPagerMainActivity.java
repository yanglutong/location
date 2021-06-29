package com.sm.android.locations.location.viewpagermain;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;


import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.sm.android.locations.location.Base.BaseActivity;
import com.sm.android.locations.location.Base.BaseFragment2;
import com.sm.android.locations.location.R;
import com.sm.android.locations.location.Utils.MainUtils.DbUtils;
import com.sm.android.locations.location.viewpagermain.Fragment.frament1Packet.Fragment0;
import com.sm.android.locations.location.viewpagermain.Fragment.Fragment1;
import com.sm.android.locations.location.viewpagermain.Fragment.Fragment2;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class ViewPagerMainActivity extends BaseActivity implements View.OnClickListener {
    ImageButton button0, button1, button2;
    NoScrollViewPager viewpage;
    int bujuLayout = R.layout.activity_view_pager_main;
    public static int TYPE_VIEWPAGERMAIN = 0;
    public static boolean FRAGMENTFLAG0 = true;
    public static boolean FRAGMENTFLAG1 = false;
    public static boolean FRAGMENTFLAG2 = false;


    //public Handler handler =new Handler();
    @Override
    protected void initQx() {
        getPermissions();
    }

    @Override
    protected void init() throws UnsupportedEncodingException {
        isFirstStart(ViewPagerMainActivity.this);
    }

    //判断是否是第一次安装如果是第一次安装 则插入自带的系统频点
    public boolean isFirstStart(Context context) {
        Log.e("GFA", "一次");
        SharedPreferences preferences = context.getSharedPreferences(
                "SHARE_APP_TAG", 0);
        Boolean isFirst = preferences.getBoolean("FIRSTStart", true);
        if (isFirst) {// 第一次
            preferences.edit().putBoolean("FIRSTStart", false).commit();
            Log.e("GFA", "一次");
//            int band, int down, String plmn, int type, int up, String tf, String yy
            //以下是插入频点设置 列表
            //移动TDD
            DbUtils.insertDB(context, 38, 37900, "46000", 0, 37900, "TDD", "移动");
            DbUtils.insertDB(context, 38, 38098, "46000", 0, 38098, "TDD", "移动");
            DbUtils.insertDB(context, 39, 38400, "46000", 0, 38400, "TDD", "移动");
            DbUtils.insertDB(context, 39, 38544, "46000", 0, 38544, "TDD", "移动");
            DbUtils.insertDB(context, 40, 38950, "46000", 0, 38950, "TDD", "移动");
            DbUtils.insertDB(context, 41, 40936, "46000", 0, 40936, "TDD", "移动");
            //移动FDD
            DbUtils.insertDB(context, 3, 1300, "46000", 0, 19300, "FDD", "移动");
            DbUtils.insertDB(context, 8, 3683, "46000", 0, 21683, "FDD", "移动");
            //联通FDD
            DbUtils.insertDB(context, 1, 375, "46001", 0, 18375, "FDD", "联通");
            DbUtils.insertDB(context, 3, 1650, "46001", 0, 19650, "FDD", "联通");
            //电信
            DbUtils.insertDB(context, 1, 100, "46011", 0, 18100, "FDD", "电信");
            DbUtils.insertDB(context, 3, 1825, "46011", 0, 19825, "FDD", "电信");
            DbUtils.insertDB(context, 3, 1850, "46011", 0, 19850, "FDD", "电信");
            DbUtils.insertDB(context, 5, 2452, "46011", 0, 20452, "FDD", "电信");
            //以下是插入扫频列表
            //移动TDD
            DbUtils.insertDBSaopin(context, 37900, 37900, 1);
            DbUtils.insertDBSaopin(context, 38098, 38098, 1);
            DbUtils.insertDBSaopin(context, 38400, 38400, 1);
            DbUtils.insertDBSaopin(context, 38544, 38544, 1);
            DbUtils.insertDBSaopin(context, 38950, 38950, 1);
            DbUtils.insertDBSaopin(context, 40936, 40936, 1);
            //移动FDD
            DbUtils.insertDBSaopin(context, 1300, 19300, 2);
            DbUtils.insertDBSaopin(context, 3683, 21683, 2);
            //联通FDD
            DbUtils.insertDBSaopin(context, 375, 18375, 3);
            DbUtils.insertDBSaopin(context, 1650, 19650, 3);
            //电信FDD
            DbUtils.insertDBSaopin(context, 100, 18100, 4);
            DbUtils.insertDBSaopin(context, 1825, 19825, 4);
            DbUtils.insertDBSaopin(context, 1850, 19850, 4);
            DbUtils.insertDBSaopin(context, 2452, 20452, 4);
            //以下是插入小区配置
            DbUtils.xiaoqu(context);
            //插入增益初始化数据
            DbUtils.ZY(context, "20", "52", "72", 1, "20", "40", "72");//TDD
            DbUtils.ZY(context, "20", "52", "72", 2, "20", "40", "72");//FDD
//            以下是插入临时测试imsi
//            DbUtils.TestIMSI(context, "460002360893648", "王", "", "移动", true);
//            DbUtils.TestIMSI(context, "460005192822841", "南移动", "", "移动", true);
//            DbUtils.TestIMSI(context, "460010619201205", "范", "", "联通", true);
//            DbUtils.TestIMSI(context, "460027131000306", "杨路通", "", "移动", true);
//            DbUtils.TestIMSI(context, "460110851349783", "杨路通", "", "电信", true);
            //注册码
            DbUtils.zc(context);
            return true;
        } else {
//            startActivity(new Intent(context, MainActivity.class));
//            finish();
            Log.i("GFA", "N次");
//            Toast.makeText(getApplicationContext(),"N次",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    protected void findViews() {
//        getHandler();
        button0 = findViewById(R.id.bt_0);//定位
        button1 = findViewById(R.id.bt_1);//侦码
        button2 = findViewById(R.id.bt_2);//管控
        viewpage = findViewById(R.id.viewpager);
        viewpage.setNoScroll(false);
        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        myFragmentPagerAdapter.getItem(0);
        viewpage.setAdapter(myFragmentPagerAdapter);
//        viewpage.onTouchEvent()

        viewpage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onPageSelected(int i) {
                Log.d("viewpagercount", "findViews: " + i);
                if (i == 0) {
//                    setBackground(buttonList, 0);
                    TYPE_VIEWPAGERMAIN = 0;

                    FRAGMENTFLAG1 = false;
                    FRAGMENTFLAG2 = false;
                    FRAGMENTFLAG0 = true;
                    button0.setBackground(getResources().getDrawable(R.mipmap.dw_bl));
//
//                buttonList.get(j).setTextColor(getResources().getColor(R.color.black));
                    button1.setBackground(getResources().getDrawable(R.mipmap.zm_gr));
                    button2.setBackground(getResources().getDrawable(R.mipmap.gk_gr));

                    return;
                }
                if (i == 1) {
//                    setBackground(buttonList, 1);
                    TYPE_VIEWPAGERMAIN = 1;
                    FRAGMENTFLAG0 = false;
                    FRAGMENTFLAG2 = false;
                    FRAGMENTFLAG1 = true;


                    button1.setBackground(getResources().getDrawable(R.mipmap.zm_bl));
//                button1.setTextColor(getResources().getColor(R.color.white));


//                buttonList.get(j).setTextColor(getResources().getColor(R.color.black));
                    button0.setBackground(getResources().getDrawable(R.mipmap.dw_gr));
                    button2.setBackground(getResources().getDrawable(R.mipmap.gk_gr));
                    return;

                }
                if (i == 2) {
//                    setBackground(buttonList, 2);
                    TYPE_VIEWPAGERMAIN = 2;
                    FRAGMENTFLAG0 = false;
                    FRAGMENTFLAG1 = false;
                    FRAGMENTFLAG2 = true;
                    button2.setBackground(getResources().getDrawable(R.mipmap.gk_bl));
//                button2.setTextColor(getResources().getColor(R.color.white));


//                buttonList.get(j).setTextColor(getResources().getColor(R.color.black));
                    button0.setBackground(getResources().getDrawable(R.mipmap.dw_gr));
                    button1.setBackground(getResources().getDrawable(R.mipmap.zm_gr));

                    return;

                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
//        setHandler(handlers);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_0:
//                TYPE_VIEWPAGERMAIN = 0;
//                FRAGMENTFLAG1 = false;
//                FRAGMENTFLAG2 = false;
//                FRAGMENTFLAG0= true;

                viewpage.setCurrentItem(0);

                break;
            case R.id.bt_1:
//                TYPE_VIEWPAGERMAIN = 1;

//                FRAGMENTFLAG0 = false;
//                FRAGMENTFLAG2 = false;
//                FRAGMENTFLAG1 = true;
                viewpage.setCurrentItem(1);
                break;
            case R.id.bt_2:
//                FRAGMENTFLAG0 = false;
//
//                FRAGMENTFLAG1 = true;
//                FRAGMENTFLAG2 = true;
//                TYPE_VIEWPAGERMAIN = 2;
                viewpage.setCurrentItem(2);
                break;
        }
    }
    
    /**
     * 轮播图适配器
     */
    public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        private List<BaseFragment2> fragments = new ArrayList<>();
        private String[] titles = {//
                "第一页\n\n重点看下面的的图标是渐变色，随着滑动距离的增加，颜色逐渐过度",//
                "第二页\n\n重点看下面的的图标是渐变色，随着滑动距离的增加，颜色逐渐过度",//
                "第三页\n\n重点看下面的的图标是渐变色，随着滑动距离的增加，颜色逐渐过度", //
                "第四页\n\n重点看下面的的图标是渐变色，随着滑动距离的增加，颜色逐渐过度"};

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);

            fragments.add(new Fragment0());
            fragments.add(new Fragment1());
            fragments.add(new Fragment2());
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
        return bujuLayout;
    }

    //    public Handler getHandler() {
////        this.handler = handler;
//
//        return handler;
//    }
//    public void setHandler(Handler handler) {
//        this.handlers = handler;
//    }

    // 内部接口
    public interface IDialogViewPager {
        public void Position(int position);
    }
}