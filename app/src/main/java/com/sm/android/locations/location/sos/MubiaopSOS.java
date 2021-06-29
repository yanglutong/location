package com.sm.android.locations.location.sos;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sm.android.locations.location.Activity.Main.Adapter.RyZmAdapterGkMUBIAO;
import com.sm.android.locations.location.Base.BaseActivity;
import com.sm.android.locations.location.R;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.AddPararBeanWhite;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.ZmBeanGK;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.ZmBeanGKTongji;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerAddParamWhite;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerZMGK;
import com.sm.android.locations.location.Utils.ToastUtils;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.sm.android.locations.location.Utils.MainUtils.MainUtils.i;

public class MubiaopSOS extends BaseActivity {
    RecyclerView ry;
    TextView titles;
    ImageView iv_finish;
    Button bt_confirm, bt_cancel;

    @Override
    protected void initQx() {

    }

    @Override
    protected void init() throws UnsupportedEncodingException {

    }

    public static List<ZmBeanGKTongji> ZMBEANGKTONGJILISTCALL = new ArrayList<>();
    XuanzeCallback callback = new XuanzeCallback() {
        @Override
        public void Call(List<ZmBeanGKTongji> zmBeanGKTongjiList) {
            Log.d("XuanzeCallback", "Call: " + zmBeanGKTongjiList.toString());
            ZMBEANGKTONGJILISTCALL = zmBeanGKTongjiList;
        }
    };
    public interface XuanzeCallback {
        void Call(List<ZmBeanGKTongji> zmBeanGKTongjiList);
    }
    @Override
    protected void findViews() {
        ry = findViewById(R.id.ry_sos_mubiao);
        titles = findViewById(R.id.titles);
        titles.setText("选择定位目标");
        iv_finish = findViewById(R.id.iv_finish);
        iv_finish.setVisibility(View.VISIBLE);
        bt_confirm = findViewById(R.id.bt_confirm);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//                linearLayoutManager.setStackFromEnd(true);//recyview显示最底部一条
        ry.setLayoutManager(linearLayoutManager);

        DBManagerZMGK dbManagerZM = null;
        try {
            dbManagerZM = new DBManagerZMGK(this);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        List<ZmBeanGK> zmBeanss = null;
        try {
            zmBeanss = dbManagerZM.getDataAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        List<ZmBeanGK> zmBeans = new ArrayList<>();
        for (int j = 0; j < zmBeanss.size(); j++) {
            if (zmBeanss.get(j).getMaintype().equals("0")) {
                zmBeans.add(zmBeanss.get(j));
            }
        }

        Log.d("dbManagerZM", "handleMessage: " + i);
        Log.d("dbManagerZM", "handleMessage:size " + zmBeans.toString());
//            ryZmAdapterGk = new RyZmAdapterGk(context, zmBeans, listsize);//list是imsi列表选中的数据
//            if (et_zhenmasearchdw.getText().length() == 0) {
//                        ry_zhenma.setAdapter(ryZmAdapter);

//            ryZmAdapterGk = new RyZmAdapterGk(context, zmBeans, listsize);//list是imsi列表选中的数据
//            recyclerView.setAdapter(ryZmAdapterGk);

        //通过 split 方法拆分数据并放入数组中
        List<String> list = new ArrayList<>();
        for (int i = 0; i < zmBeanss.size(); i++) {
            list.add(zmBeanss.get(i).getImsi());
        }
        Log.e("inittongjiIMsi", "init: " + list.toString());
        List<Map<String, Integer>> list1 = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            Integer integer = map.get(list.get(i));
            map.put(list.get(i), integer == null ? 1 : integer + 1);
        }
        // 方式一
        Set<String> res = map.keySet();
        for (String im : res) {
            System.out.println("a数据：" + im + "共有" + map.get(im));
        }
        List<ZmBeanGKTongji> list2 = new ArrayList<>();
        ZmBeanGKTongji zmBeanGKTongji;
        Set<Map.Entry<String, Integer>> set = map.entrySet();
        for (Map.Entry<String, Integer> entry : set) {
            System.out.println("输出结果重复" + entry.getKey() + "---" + entry.getValue());
            if (entry.getValue() > 0) {
                zmBeanGKTongji = new ZmBeanGKTongji();
                zmBeanGKTongji.setImsi(entry.getKey());
                zmBeanGKTongji.setNum(entry.getValue() + "");
//                zmBeanGKTongji.setTime(time);
                list2.add(zmBeanGKTongji);

            }


        }
        List<AddPararBeanWhite> dataAllawhiite=null;
        DBManagerAddParamWhite dbManagerAddParamA = null;
        try {
            dbManagerAddParamA = new DBManagerAddParamWhite(this);
           dataAllawhiite = dbManagerAddParamA.getDataAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Log.d("TAGlisttongji", "init: " + list2.toString());
        for (int i = 0; i < zmBeanss.size(); i++) {
            for (int j = 0; j < list2.size(); j++) {
                if (zmBeanss.get(i).getImsi().equals(list2.get(j).getImsi())) {
                    list2.get(j).setDown(zmBeanss.get(i).getDown());
                    list2.get(j).setSb(zmBeanss.get(i).getSb());
                    list2.get(j).setTime(zmBeanss.get(i).getTime());
                    list2.get(j).setDatatime(zmBeanss.get(i).getDatatime());
                }
            }
        }
        Log.d("sosutilsTAGlistton", "init: " + list2.toString());
//        白名单不显示
        for (int j = 0; j <dataAllawhiite.size() ; j++) {
            for (int k = 0; k < list2.size(); k++) {
                if (list2.get(k).getImsi().equals(dataAllawhiite.get(j).getImsi())&&dataAllawhiite.get(j).isCheckbox()){
                    list2.remove(list2.get(k));
                }

            }


        }

        List<Integer> listsize = new ArrayList<>();
        for (int i = 0; i < list2.size(); i++) {
            listsize.add(i + 1);

        }


        RyZmAdapterGkMUBIAO ryZmAdapterGk = new RyZmAdapterGkMUBIAO(this, list2, listsize, callback);//list是imsi列表选中的数据
        ry.setAdapter(ryZmAdapterGk);
        LinearLayoutManager linearLayoutManagera;
        if (list2.size() > 10) {
            linearLayoutManagera = new LinearLayoutManager(this);
//                linearLayoutManager.setStackFromEnd(true);//recyview显示最底部一条
            ry.setLayoutManager(linearLayoutManagera);
//                tv_searchNumdw.setText("(" + zmBeans.size() + ")");
        } else {
            linearLayoutManagera = new LinearLayoutManager(this);
//                linearLayoutManager.setStackFromEnd(false);//recyview显示最底部一条
            ry.setLayoutManager(linearLayoutManagera);
//                tv_searchNumdw.setText("(" + zmBeans.size() + ")");
        }
        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<ZmBeanGKTongji> list1 = new ArrayList<>();
                List<ZmBeanGKTongji> list2 = new ArrayList<>();
                for (int j = 0; j < ZMBEANGKTONGJILISTCALL.size(); j++) {
                    if (ZMBEANGKTONGJILISTCALL.get(j).getSb().equals("1")&&ZMBEANGKTONGJILISTCALL.get(j).isCheck()) {
                        list1.add(ZMBEANGKTONGJILISTCALL.get(j));
                    }
                    if (ZMBEANGKTONGJILISTCALL.get(j).getSb().equals("2")&&ZMBEANGKTONGJILISTCALL.get(j).isCheck()) {
                        list2.add(ZMBEANGKTONGJILISTCALL.get(j));
                    }
                }
                if (list1.size() > 10) {
                    ToastUtils.showToast("设备1选择目标不得大于10条");
                    return;
                }
                if (list1.size() > 10) {
                    ToastUtils.showToast("设备2选择目标不得大于10条");
                    return;
                }
                if (list1.size() == 0 && list2.size() == 0) {
                    ToastUtils.showToast("没有选择的目标");
                    return;
                }
                Intent intent = new Intent();
                setResult(1001, intent);
                finish();
            }
        });
        bt_cancel = findViewById(R.id.bt_cancel);
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        iv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //可传递返回参数
//                Intent intent = new Intent();
//                setResult(1001, intent);
                finish();
            }
        });

    }

    @Override
    protected int getView() {
        return R.layout.sos_mubiaobiaoxuanze;
    }

    @Override
    public void onBackPressed() {

        //可传递返回参数
        Intent intent = new Intent();
        setResult(1001, intent);
        finish();
    }
}
