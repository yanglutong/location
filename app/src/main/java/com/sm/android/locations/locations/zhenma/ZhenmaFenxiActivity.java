package com.sm.android.locations.locations.zhenma;

import android.annotation.SuppressLint;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;

import com.sm.android.locations.locations.Base.BaseActivity;
import com.sm.android.locations.locations.R;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.ZmBeanBsDATAlbenan;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.ZmBeanPdDATAlbenan;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.ZmBeanPdlbenan;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.ZmBeanbslbenan;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.ZmBeanlinshi;
import com.sm.android.locations.locations.Utils.OrmSqlLite.DBManagerZMlinshi;
import com.sm.android.locations.locations.Utils.OrmSqlLite.DBManagerZmBSList;
import com.sm.android.locations.locations.Utils.OrmSqlLite.DBManagerZmDATABsList;
import com.sm.android.locations.locations.Utils.OrmSqlLite.DBManagerZmDATAPdList;
import com.sm.android.locations.locations.Utils.OrmSqlLite.DBManagerZmPdList;
import com.sm.android.locations.locations.Utils.ToastUtils;
import com.sm.android.locations.locations.Utils.UtilsView;
import com.sm.android.locations.locations.zhenma.adapter.ZhenmaFenxBsiAdapter;
import com.sm.android.locations.locations.zhenma.adapter.ZhenmaFenxPziAdapter;
import com.sm.android.locations.locations.zhenma.adapter.ZhenmaFenxiAdapter;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.sm.android.locations.locations.Utils.MyUtils.removeD;

public class ZhenmaFenxiActivity extends BaseActivity {
    LinearLayout layout;
    TextView title;
    ImageView iv_finish, iv_add, imageView, iv_fenxi;
    int MainLayout = R.layout.activity_zhenma_fenxi;//主界面布局
    Button bt_pd, bt_bs;
    RecyclerView ry;
    RecyclerView ry_pd;
    RecyclerView ry_bs;
    ZhenmaFenxiAdapter zhenmaFenxiAdapter;
    com.sm.android.locations.locations.zhenma.adapter.ZhenmaFenxPziAdapter ZhenmaFenxPziAdapter;
    ZhenmaFenxBsiAdapter zhenmaFenxBsiAdapter;
    DBManagerZMlinshi dbManagerZMlinshi;
    List<ZmBeanlinshi> btlists = new ArrayList<>();
    EditText ed_s, ed_imsi;
    ZhenmaFenxiAdapter.ZhenmaCallBack callBack = new ZhenmaFenxiAdapter.ZhenmaCallBack() {
        @Override
        public void call(List<ZmBeanlinshi> lists) {
            Log.d("chengg", lists.toString());
            btlists = lists;
        }

        @Override
        public void callID(int i) {
////            ToastUtils.showToast("i" + i);
//            getdata();
////            removeD()''
//            zhenmaFenxiAdapter.notifyItemRemoved(i);
//            zhenmaFenxiAdapter.notifyItemRemoved(i);
        }

        @Override
        public void callStartTime(String i, String i2, int postion) {
            Log.d("uinzq1", "on" + i + "--" + i2);
            try {
                DBManagerZMlinshi dbManagerZMlinshi = new DBManagerZMlinshi(ZhenmaFenxiActivity.this);
                List<ZmBeanlinshi> list = dbManagerZMlinshi.queryGuanyu2(i, i2);
                Log.d("uinzq2", "on" + i + "--" + i2 + "--" + list.size() + "--" + list.toString());
                for (int j = 0; j < list.size(); j++) {
//                    ZmBeanlinshi zmBeanlinshi=new ZmBeanlinshi();
//                    zmBeanlinshi.setId(list.get(j).getId());
                    dbManagerZMlinshi.deleteGuanyu(list.get(j).getStartdatatime());
                    Log.d("uinzq3", "on" + i + "--" + i2);
                }
                Log.d("uinzq4", "on" + i + "--" + i2);
//                zhenmaFenxiAdapter.notifyItemRemoved(postion);
//                ry.notify();
                getdata();
                Log.d("uinzq5", "on" + i + "--" + i2);
                getdata2();

                Log.d("uinzq6", "on" + i + "--" + i2);
            } catch (SQLException e) {
                e.printStackTrace();
                Log.d("uinzq7", "on" + i + "--" + i2 + e.getMessage());
            }
        }
    };
    ZhenmaFenxPziAdapter.ZhenmaPdCallBack PdcallBack = new ZhenmaFenxPziAdapter.ZhenmaPdCallBack() {


        @Override
        public void callDele(int id) {
            try {
                DBManagerZmPdList dbManagerZmPdList = new DBManagerZmPdList(ZhenmaFenxiActivity.this);

                ZmBeanPdlbenan zmBeanPdlbenan = dbManagerZmPdList.forid(id);
                dbManagerZmPdList.deleteGuanyu(id);
                if (zmBeanPdlbenan != null) {
                    DBManagerZmDATAPdList dbManagerZmDATAPdList = new DBManagerZmDATAPdList(ZhenmaFenxiActivity.this);
                    List<ZmBeanPdDATAlbenan> zmBeanPdDATAlbenans = dbManagerZmDATAPdList.queryTime(zmBeanPdlbenan.getTime());
                    for (int i = 0; i < zmBeanPdDATAlbenans.size(); i++) {
                        dbManagerZmDATAPdList.deleteAddPararBean(zmBeanPdDATAlbenans.get(i));
                        Log.d("uiii", "111");
                    }
                } else {
                    Log.d("uiii", "222");
                }

                getdata2();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void callID(int i) {
//            ToastUtils.showToast("i" + i);
        }
    };

    ZhenmaFenxBsiAdapter.ZhenmaBsCallBack zhenmaBsCallBack = new ZhenmaFenxBsiAdapter.ZhenmaBsCallBack() {
        @Override
        public void callDele(int id) {
            try {
                DBManagerZmBSList dbManagerZmPdList = new DBManagerZmBSList(ZhenmaFenxiActivity.this);

                ZmBeanbslbenan zmBeanPdlbenan = dbManagerZmPdList.forid(id);
                dbManagerZmPdList.deleteGuanyu(id);
                if (zmBeanPdlbenan != null) {
                    DBManagerZmDATABsList dbManagerZmDATAPdList = new DBManagerZmDATABsList(ZhenmaFenxiActivity.this);
                    List<ZmBeanBsDATAlbenan> zmBeanBsDATAlbenans = dbManagerZmDATAPdList.queryTime(zmBeanPdlbenan.getTime());
                    for (int i = 0; i < zmBeanBsDATAlbenans.size(); i++) {
                        dbManagerZmDATAPdList.deleteAddPararBean(zmBeanBsDATAlbenans.get(i));
                        Log.d("uiii", "111");
                    }
                } else {
                    Log.d("uiii", "222");
                }

                getdata3();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void callID(int i) {

        }
    };

    @Override
    protected void initQx() {

    }

    @Override
    protected void init() throws UnsupportedEncodingException {

//        setStatBar();//去导航栏
        iv_finish.setVisibility(View.VISIBLE);
        UtilsView.setViewVisibility(this, layout, title, imageView, getResources().getString(R.string.zhenmafenxi), false, iv_finish, true);
    }

    @Override
    protected void findViews() {
        layout = findViewById(R.id.ll_tab);
        title = findViewById(R.id.titles);
        ry = findViewById(R.id.ry);
        ry.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ry_pd = findViewById(R.id.ry_pd);
        ry_pd.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ry_bs = findViewById(R.id.ry_bs);
        ry_bs.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        imageView = findViewById(R.id.iv_menu);
        iv_fenxi = findViewById(R.id.iv_fenxi);
        iv_finish = findViewById(R.id.iv_finish);
        iv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getdata();
        getdata2();
        getdata3();
        ed_imsi = findViewById(R.id.ed_imsi);//获取IMSI
        ed_s = findViewById(R.id.ed_s);//获取秒
        bt_bs = findViewById(R.id.bt_bs);//获取秒
        ed_imsi.setText("");
        ed_s.setText("");
//        final String etimsi = ed_imsi.getText().toString();
        bt_bs.setOnClickListener(new View.OnClickListener() {//伴随分析
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(ed_imsi.getText().toString())) {
                    ToastUtils.showToast("IMSI输入不能为空");
                    return;
                }
//                if (ed_imsi.getText().toString().length() != 15) {
//                    ToastUtils.showToast("IMSI输入长度应该为15位");
//                    return;
//                }
                if (TextUtils.isEmpty(ed_s.getText().toString())) {
                    ToastUtils.showToast("伴随时间不能为空");
                    return;
                }
                if (Integer.parseInt(ed_s.getText().toString()) == 0) {
                    ToastUtils.showToast("伴随时间不能为0");
                    return;
                }


                boolean imsiFlag = false;
                if (btlists.size() > 0) {//判断  是否存在当前IMSI
                    List<String> listsum = new ArrayList<>();
                    for (int i = 0; i < btlists.size(); i++) {//选中的总数
                        if (btlists.get(i).getCheck().equals("1")) {
                            Log.d("btlists", "onClick: " + btlists.get(i).toString());
                            try {
                                dbManagerZMlinshi = new DBManagerZMlinshi(ZhenmaFenxiActivity.this);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<ZmBeanlinshi> list = dbManagerZMlinshi.queryGuanyu2(btlists.get(i).getStartdatatime(), btlists.get(i).getStoptime());
//                                Log.d("r2222", list.toString());
                                if (list.size() > 0) {
                                    List<ZmBeanlinshi> list1 = removeD(list);
                                    Log.d("list1", list.toString());
                                    if (list.size() > 0) {
                                        for (int j = 0; j < list.size(); j++) {
                                            listsum.add(list.get(j).getImsi());
                                            if (ed_imsi.getText().toString().equals(list.get(j).getImsi())) {
                                                imsiFlag = true;
                                                break;
                                            }
                                        }
                                    }
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
//                            listsum.add(btlists.get(i).getImsi() + "");
                            Log.d("listsum", "添加" + btlists.get(i).getImsi());
                        }
                    }

                    Log.d("IMMSIflag", "onClick: " + imsiFlag);
                    if (imsiFlag) {//true
                        Log.d("IMMSIflag", "onClick: " + imsiFlag);
                    } else {//false
                        Log.d("IMMSIflag", "onClick: " + imsiFlag);
                        ToastUtils.showToast("没有查询到IMSI");
                        return;
                    }
                } else {
                    ToastUtils.showToast("没有选中IMSI");
                    return;
                }

                //去比对


//                4600253312122221223
                ///////////////////////////一下处理

                List<ZmBeanlinshi> listData = null;
                List<List<ZmBeanlinshi>> listDataSUm2 = new ArrayList<>();//选中IMSI
                if (btlists.size() > 0) {

                    for (int i = 0; i < btlists.size(); i++) {//选中的总数
                        if (btlists.get(i).getCheck().equals("1")) {
                            try {
                                dbManagerZMlinshi = new DBManagerZMlinshi(ZhenmaFenxiActivity.this);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                listData = dbManagerZMlinshi.queryGuanyu2(btlists.get(i).getStartdatatime(), btlists.get(i).getStoptime());
                                Log.d("r22221", listData.toString());
                                listDataSUm2.add(listData);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
//                    if (listsum.size() > 0) {
//
//                    } else {
//                        ToastUtils.showToast("没有选中IMSI11");
//                    }
                } else {
                    ToastUtils.showToast("没有选中IMSI");

                }

                List<ZmBeanlinshi> imsiList = new ArrayList();//输入的IMSI
                if (listDataSUm2.size() > 0) {
                    for (int i = 0; i < listDataSUm2.size(); i++) {
                        for (int j = 0; j < listDataSUm2.get(i).size(); j++) {
                            if (ed_imsi.getText().toString().equals(listDataSUm2.get(i).get(j).getImsi())) {
                                imsiList.add(listDataSUm2.get(i).get(j));
                                Log.d("imsiList get(i),", "onClick: " + listDataSUm2.get(i).get(j).getImsi());
                            }
                        }

                    }
                    Log.d("imsiListB", imsiList.toString());
                }

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                List<List<ZmBeanlinshi>> listDataSUm = new ArrayList<>();//判断后的放的高级分组集合
                List<ZmBeanlinshi> beanlinshis = null;
                if (imsiList.size() > 0) {//符合条件的 几个IMSI

                    for (int m = 0; m < imsiList.size(); m++) {
                        for (int i = 0; i < listDataSUm2.size(); i++) {
                            beanlinshis = new ArrayList<>();
                            for (int j = 0; j < listDataSUm2.get(i).size(); j++) {

                                String datatimeJ = listDataSUm2.get(i).get(j).getDatatime();
                                String datatime1M = imsiList.get(m).getDatatime();


                                Log.d("DDDDDa", "datatimeJ" + datatimeJ + "---datatime1M" + datatime1M);
                                try {
                                    Date date1 = sdf.parse(datatimeJ);
                                    Date date21 = sdf.parse(datatime1M);
                                    Date date22 = sdf.parse(datatime1M);
                                    date21.setTime(date21.getTime() - Integer.parseInt(ed_s.getText().toString()) * 1000);//时间范围 开始
                                    date22.setTime(date22.getTime() + Integer.parseInt(ed_s.getText().toString()) * 1000);//时间范围 结束

                                    Log.d("date2", sdf.format(date21));
                                    if (isEffectiveDate(date1, date21, date22) && !listDataSUm2.get(i).get(j).getImsi().equals(imsiList.get(m).getImsi())) {

//                                        if (isEffectiveDate(date1, date21, date22) ) {
                                        Log.d("nzq相等时间", "===" + sdf.format(date1) + "开始范围--" + sdf.format(date21) + "--结束范围" + sdf.format(date22));
                                        beanlinshis.add(listDataSUm2.get(i).get(j));
                                    } else {
                                        Log.d("nzq不相等时间", "===");
                                    }
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                            }
                            if (beanlinshis.size() == 0) {
                                Log.d("beanlinshiss++", "2");
                            } else {
                                Log.d("beanlinshiss++", "1");
                                listDataSUm.add(beanlinshis);
                            }

                            Log.d("beanlinshis", "onClick: " + beanlinshis.size() + "--" + beanlinshis.toString());
                        }
                    }

                }
                if (listDataSUm.size() > 0) {
                    Log.d("TAGbeanlinshis之前", "onClic" + listDataSUm.toString());
                    for (int i = 0; i < listDataSUm.size(); i++) {
                        Log.d("TAG", "onClick: .");
                        List<ZmBeanlinshi> beanlinshis1 = removeD(listDataSUm.get(i));
                        listDataSUm.set(i, beanlinshis1);
                    }
                    Log.d("TAGbeanlinshis之后", "onClic" + listDataSUm.toString());
                    List<String> listsum = new ArrayList<>();
                    for (int i = 0; i < listDataSUm.size(); i++) {
                        for (int j = 0; j < listDataSUm.get(i).size(); j++) {
                            listsum.add(listDataSUm.get(i).get(j).getImsi());

                        }

                    }
                    if (listsum.size() > 0) {
                        Log.d("listsum", listsum.toString());
                        Map<String, Integer> map = new HashMap<>();
                        for (int i = 0; i < listsum.size(); i++) {
                            Integer integer = map.get(listsum.get(i));
                            map.put(listsum.get(i), integer == null ? 1 : integer + 1);
                        }
                        Set<Map.Entry<String, Integer>> set = map.entrySet();
                        ZmBeanbslbenan zmBeanPdlbenan;
                        DBManagerZmDATABsList dbManagerZmDATAPdList;
                        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String time = simpleDateFormat.format(new Date());
                        DBManagerZmBSList dbManagerZmbsList = null;
//                        try {
//                            dbManagerZmPdList = new DBManagerZmPdList(ZhenmaFenxiActivity.this);
//                        } catch (SQLException e) {
//                            e.printStackTrace();
//                        }
                        Log.d("mapTAG", "onClick: ." + map.toString());
                        if (set.size() == 0) {
                            ToastUtils.showToast("无分析记录");
                            return;
                        }

                        List<ZmBeanBsDATAlbenan> zmBeanbsDATAlbenans = new ArrayList<>();
                        for (Map.Entry<String, Integer> entry : set) {
                            System.out.println("输出结果重复BS" + entry.getKey() + "---" + entry.getValue());
                            if (entry.getValue() > 0) {
                                ZmBeanBsDATAlbenan zmBeanPdDATAlbenan = new ZmBeanBsDATAlbenan();
                                zmBeanPdDATAlbenan.setImsi(entry.getKey());
                                zmBeanPdDATAlbenan.setSum(entry.getValue() + "");
                                zmBeanPdDATAlbenan.setTime(time);
                                zmBeanbsDATAlbenans.add(zmBeanPdDATAlbenan);
                                Log.d("nzqaddA", "");
                                try {

                                    dbManagerZmDATAPdList = new DBManagerZmDATABsList(ZhenmaFenxiActivity.this);
                                    Log.d("nzqaddb", "");
                                    dbManagerZmDATAPdList.insertAddZmBeanlinshi(zmBeanPdDATAlbenan);
                                    Log.d("nzqaddc", "");
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        zmBeanPdlbenan = new ZmBeanbslbenan();
                        zmBeanPdlbenan.setType("伴随分析");
                        zmBeanPdlbenan.setTime(time);
                        try {
                            dbManagerZmbsList = new DBManagerZmBSList(ZhenmaFenxiActivity.this);
                            dbManagerZmbsList.insertAddZmBeanlinshi(zmBeanPdlbenan);
                            getdata3();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }

                } else {
                    ToastUtils.showToast("没有符合条件的结果");
                }

                ////////////////////////
//                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                Date date = null;
//                String s = ed_s.getText().toString();//输入时的时间
//                int i = Integer.parseInt(s);
//                int longtime = 1000;
//                int sunlong = i * longtime;
//                try {
//                    date = df.parse("2020-09-10 19:34:51");
//                    date.setTime(date.getTime() + 10000);
//                    Log.d("TAG", "onClick: " + df.format(date));
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
            }
        });

        bt_pd = findViewById(R.id.bt_pds);
        bt_pd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//碰撞分析


                if (btlists.size() > 0) {
                    List<String> listsum = new ArrayList<>();
                    for (int i = 0; i < btlists.size(); i++) {//选中的总数
                        if (btlists.get(i).getCheck().equals("1")) {
                            try {
                                dbManagerZMlinshi = new DBManagerZMlinshi(ZhenmaFenxiActivity.this);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                List<ZmBeanlinshi> list = dbManagerZMlinshi.queryGuanyu2(btlists.get(i).getStartdatatime(), btlists.get(i).getStoptime());
                                Log.d("r2222", list.toString());
                                if (list.size() > 0) {
                                    List<ZmBeanlinshi> list1 = removeD(list);
                                    Log.d("list1", list.toString());
                                    if (list1.size() > 0) {
                                        for (int j = 0; j < list1.size(); j++) {
                                            listsum.add(list1.get(j).getImsi());

                                        }
                                    }
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
//                            listsum.add(btlists.get(i).getImsi() + "");
                            Log.d("listsum", "添加" + btlists.get(i).getImsi());
                        }
                    }
                    if (listsum.size() > 0) {
                        Log.d("listsum", listsum.toString());
                        Map<String, Integer> map = new HashMap<>();
                        for (int i = 0; i < listsum.size(); i++) {
                            Integer integer = map.get(listsum.get(i));
                            map.put(listsum.get(i), integer == null ? 1 : integer + 1);
                        }
                        Set<Map.Entry<String, Integer>> set = map.entrySet();
                        ZmBeanPdlbenan zmBeanPdlbenan;
                        DBManagerZmDATAPdList dbManagerZmDATAPdList;
                        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String time = simpleDateFormat.format(new Date());
                        DBManagerZmPdList dbManagerZmPdList = null;
                        try {
                            dbManagerZmPdList = new DBManagerZmPdList(ZhenmaFenxiActivity.this);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
//                        if (map.size() == 0) {
//                            ToastUtils.showToast("无碰撞记录");
//                            return;
//                        }
                        List<ZmBeanPdDATAlbenan> zmBeanPdDATAlbenans = new ArrayList<>();
                        for (Map.Entry<String, Integer> entry : set) {
                            System.out.println("输出结果重复a" + entry.getKey() + "---" + entry.getValue());
                            if (entry.getValue() > 1) {
                                ZmBeanPdDATAlbenan zmBeanPdDATAlbenan = new ZmBeanPdDATAlbenan();
                                zmBeanPdDATAlbenan.setImsi(entry.getKey());
                                zmBeanPdDATAlbenan.setSum(entry.getValue() + "");
                                zmBeanPdDATAlbenan.setTime(time);
                                zmBeanPdDATAlbenans.add(zmBeanPdDATAlbenan);
                                try {

                                    dbManagerZmDATAPdList = new DBManagerZmDATAPdList(ZhenmaFenxiActivity.this);

                                    dbManagerZmDATAPdList.insertAddZmBeanlinshi(zmBeanPdDATAlbenan);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }


                        }
                        Log.d("TAGzmBeanPdDATAlbenans", "onClick: " + zmBeanPdDATAlbenans.toString());
                        if (zmBeanPdDATAlbenans.size() > 0) {
                            zmBeanPdlbenan = new ZmBeanPdlbenan();
                            zmBeanPdlbenan.setType("碰撞分析");
                            zmBeanPdlbenan.setTime(time);
                            try {
                                dbManagerZmPdList.insertAddZmBeanlinshi(zmBeanPdlbenan);
                                getdata2();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else {
                            ToastUtils.showToast("无碰撞记录");
                        }

                    } else {
                        ToastUtils.showToast("没有选中IMSI");
                    }
                } else {
                    ToastUtils.showToast("没有选中IMSI");

                }


            }
        });
        iv_fenxi.setVisibility(View.GONE);

    }

    /**
     * @param nowTime   当前时间
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     * @author sunran   判断当前时间在时间区间内
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    private void getdata2() {
        //下面是碰撞列表
        try {
            DBManagerZmPdList dbManagerZmPdList = new DBManagerZmPdList(ZhenmaFenxiActivity.this);

            //分析列表数据
            List<ZmBeanPdlbenan> dataAll = dbManagerZmPdList.getDataAll();
            Log.d("", "getdata2: " + dataAll.toString());
            ZhenmaFenxPziAdapter = new ZhenmaFenxPziAdapter(ZhenmaFenxiActivity.this, dataAll, PdcallBack);
            ry_pd.setAdapter(ZhenmaFenxPziAdapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ZhenmaFenxiActivity.this);
            linearLayoutManager.setStackFromEnd(true);//recyview显示最底部一条
            ry_pd.setLayoutManager(linearLayoutManager);
            if (dataAll.size() > 0) {
                if (dataAll.size() > 6) {
                    linearLayoutManager = new LinearLayoutManager(ZhenmaFenxiActivity.this);
                    linearLayoutManager.setStackFromEnd(true);//recyview显示最底部一条
                    ry_pd.setLayoutManager(linearLayoutManager);
                } else {
                    linearLayoutManager = new LinearLayoutManager(ZhenmaFenxiActivity.this);
                    linearLayoutManager.setStackFromEnd(false);//recyview显示最底部一条
                    ry_pd.setLayoutManager(linearLayoutManager);
                }
            }
//            dbManagerZmPdList.deleteall();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getdata3() {
        //下面是碰撞列表
        try {
            DBManagerZmBSList dbManagerZmPdList = new DBManagerZmBSList(ZhenmaFenxiActivity.this);
            //分析列表数据
            List<ZmBeanbslbenan> dataAll = dbManagerZmPdList.getDataAll();
            zhenmaFenxBsiAdapter = new ZhenmaFenxBsiAdapter(ZhenmaFenxiActivity.this, dataAll, zhenmaBsCallBack);
            ry_bs.setAdapter(zhenmaFenxBsiAdapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ZhenmaFenxiActivity.this);
            linearLayoutManager.setStackFromEnd(true);//recyview显示最底部一条
            ry_bs.setLayoutManager(linearLayoutManager);
            if (dataAll.size() > 0) {
                if (dataAll.size() > 5) {
                    linearLayoutManager = new LinearLayoutManager(ZhenmaFenxiActivity.this);
                    linearLayoutManager.setStackFromEnd(true);//recyview显示最底部一条
                    ry_bs.setLayoutManager(linearLayoutManager);
                } else {
                    linearLayoutManager = new LinearLayoutManager(ZhenmaFenxiActivity.this);
                    linearLayoutManager.setStackFromEnd(false);//recyview显示最底部一条
                    ry_bs.setLayoutManager(linearLayoutManager);
                }
            }
//            dbManagerZmPdList.deleteall();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getdata() {
        ZmBeanlinshi zmBeanlinshi = new ZmBeanlinshi();
        try {
            dbManagerZMlinshi = new DBManagerZMlinshi(ZhenmaFenxiActivity.this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        for (int i = 0; i < ; i++) {
//
//        }
        zmBeanlinshi.setImsi("4600253312122221205");
        zmBeanlinshi.setDown("38950");
        zmBeanlinshi.setSb("1");
        zmBeanlinshi.setCheck("0");
        zmBeanlinshi.setStartdatatime("2020-09-10 19:10:01");
        zmBeanlinshi.setStoptime("2020-09-10 20:20:01");
        zmBeanlinshi.setDatatime("2020-09-10 19:20:02");
//        try {
//            dbManagerZMlinshi.insertAddZmBeanlinshi(zmBeanlinshi);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


        try {
            dbManagerZMlinshi = new DBManagerZMlinshi(ZhenmaFenxiActivity.this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<List<ZmBeanlinshi>> listdata = new ArrayList<>();
        List<ZmBeanlinshi> list = null;

        try {
            List<ZmBeanlinshi> dataAllA = null;
            try {
                dataAllA = dbManagerZMlinshi.getDataAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            LogUtils.a("临时的侦码记录" + dataAllA.toString());
            if (dataAllA.size() > 0) {

                // 从list中索引为0开始往后遍历
                for (int i = 0; i < dataAllA.size() - 1; i++) {
                    // 从list中索引为 list.size()-1 开始往前遍历
                    for (int j = dataAllA.size() - 1; j > i; j--) {
                        // 进行比较
                        if (dataAllA.get(j).getStartdatatime().equals(dataAllA.get(i).getStartdatatime())) {
                            // 去重
                            dataAllA.remove(j);
                        }
                    }
                }
                LogUtils.a("临时的侦码记录处理后" + dataAllA.toString());
                zhenmaFenxiAdapter = new ZhenmaFenxiAdapter(this, dataAllA, callBack);
                ry.setAdapter(zhenmaFenxiAdapter);
                Log.e("sadsad", "getdata:2 ");
            } else {
                zhenmaFenxiAdapter = new ZhenmaFenxiAdapter(this, dataAllA, callBack);
                ry.setAdapter(zhenmaFenxiAdapter);
                Log.e("sadsad", "getdata:1 ");
            }
        } catch (Exception e) {
            Log.e("qqq", "getdata: " + e.getMessage());
        }

    }

    @Override
    protected int getView() {
        return MainLayout;
    }

    @Override
    public void onBackPressed() {

        finish();
    }
}