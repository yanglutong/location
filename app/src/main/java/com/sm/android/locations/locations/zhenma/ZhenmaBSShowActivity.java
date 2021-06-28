package com.sm.android.locations.locations.zhenma;

import android.content.Intent;
import android.os.Environment;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sm.android.locations.locations.Base.BaseActivity;
import com.sm.android.locations.locations.R;
import com.sm.android.locations.locations.Utils.ExcelUtils;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.ZmBean;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.ZmBeanBsDATAlbenan;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.ZmBeanlinshi;
import com.sm.android.locations.locations.Utils.OrmSqlLite.DBManagerZMlinshi;
import com.sm.android.locations.locations.Utils.OrmSqlLite.DBManagerZmDATABsList;
import com.sm.android.locations.locations.Utils.ToastUtils;
import com.sm.android.locations.locations.Utils.UtilsView;
import com.sm.android.locations.locations.zhenma.adapter.ZhenmaBsShowAdapter;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.sm.android.locations.locations.Utils.DialogUtils.makeDir;

public class ZhenmaBSShowActivity extends BaseActivity {
    //    ZMBEANLINSHILIST
    LinearLayout layout;
    TextView title, tv1content;
    ImageView iv_finish, iv_add, imageView, iv_fenxi;
    int MainLayout = R.layout.activity_zhenma_fenxi;//主界面布局
    Button bt_pd;
    RecyclerView ry;
    ZhenmaBsShowAdapter zhenmaBsShowAdapter;
    com.sm.android.locations.locations.zhenma.adapter.ZhenmaFenxPziAdapter ZhenmaFenxPziAdapter;
    DBManagerZMlinshi dbManagerZMlinshi;
    List<List<ZmBeanlinshi>> btlists = new ArrayList<>();


    @Override
    protected void initQx() {

    }

    @Override
    protected void init() throws UnsupportedEncodingException {
//        setStatBar();//去导航栏
        iv_finish.setVisibility(View.VISIBLE);
        UtilsView.setViewVisibility(this, layout, title, imageView, getResources().getString(R.string.zhengmafenxiBSshow), false, iv_finish, true);
    }

    @Override
    protected void findViews() {
        tv1content = findViewById(R.id.tv1content);
        layout = findViewById(R.id.ll_tab);
        title = findViewById(R.id.titles);
        ry = findViewById(R.id.ry);
        ry.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        ry_pd = findViewById(R.id.ry_pd);
//        ry_pd.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        imageView = findViewById(R.id.iv_menu);
        iv_fenxi = findViewById(R.id.iv_fenxi);
        iv_finish = findViewById(R.id.iv_finish);
        iv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        bt_pd = findViewById(R.id.bt_pds);
        bt_pd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ToastUtils.showToast("点击了保存");
                Intent intent = getIntent();
                String idtime = intent.getStringExtra("id");
                Log.d("idtime", "getdata: " + idtime);
                try {
                    DBManagerZmDATABsList dbManagerZmDATAPdList = new DBManagerZmDATABsList(ZhenmaBSShowActivity.this);
                    List<ZmBeanBsDATAlbenan> zmBeanbsDATAlbenans = dbManagerZmDATAPdList.queryTime(idtime);
                    if (zmBeanbsDATAlbenans.size() > 0) {
                        Log.d("ListdataAllListdataAll", "onClick: " + zmBeanbsDATAlbenans.size());
                        List<ZmBean> zmBeans;
                        ArrayList<ArrayList<String>> recordList;
                        String[] title = {"序号", "时间", "IMSI", "出现次数", "", ""};
//                    private static String[] title = { "编号","姓名","性别","年龄","班级","数学","英语","语文" };

                        recordList = new ArrayList<>();
                        for (int i = 0; i < zmBeanbsDATAlbenans.size(); i++) {
                            ZmBeanBsDATAlbenan zmBean = zmBeanbsDATAlbenans.get(i);
                            ArrayList<String> beanList = new ArrayList<String>();
                            beanList.add(i + 1 + "");
                            beanList.add(zmBean.getTime());
                            beanList.add(zmBean.getImsi());
                            beanList.add(zmBean.getSum());
//                        beanList.add(zmBean.getSb());
                            recordList.add(beanList);
                        }
                        File file = new File(getSDPath() + "/locationzm");
//                        java.text.SimpleDateFormat simpleDateFormat = new SimpleDateFormat("" + "yyyy-MM-dd HH:mm:ss ");
////                        Date date = new Date();
////                        String format = simpleDateFormat.format(date);
                        makeDir(file);
                        ExcelUtils.initExcel(file.toString() + "/" + zmBeanbsDATAlbenans.get(0).getTime() + "(伴随分析)" + ".xls", title);

                        String fileName = getSDPath() + "/locationzm/" + zmBeanbsDATAlbenans.get(0).getTime() + "(伴随分析)" + ".xls";
                        ExcelUtils.writeObjListToExcel(recordList, fileName, ZhenmaBSShowActivity.this);
                        tv1content.setText("" + fileName);
                    } else {
                        ToastUtils.showToast("无伴随分析数据");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        iv_fenxi.setVisibility(View.GONE);
        getdata();
    }

    public static String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();
        }
        String dir = sdDir.toString();
        return dir;
    }

    private void getdata() {
        Intent intent = getIntent();
        String idtime = intent.getStringExtra("id");
        Log.d("idtimeQ", "getdata: " + idtime);
        try {
            DBManagerZmDATABsList dbManagerZmDATABsList = new DBManagerZmDATABsList(ZhenmaBSShowActivity.this);
            List<ZmBeanBsDATAlbenan> dataAll = dbManagerZmDATABsList.getDataAll();
            Log.d("BSdataAll",dataAll.toString());
            List<ZmBeanBsDATAlbenan> zmBeanPdDATAlbenans = dbManagerZmDATABsList.queryTime(idtime);
            Log.d("TAGans", "getdata: " + zmBeanPdDATAlbenans.toString());
            zhenmaBsShowAdapter = new ZhenmaBsShowAdapter(this, zmBeanPdDATAlbenans);
            ry.setAdapter(zhenmaBsShowAdapter);
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d("idtimeQe.", "getdata: " + e.getMessage());
        }

    }

    @Override
    protected int getView() {
        return R.layout.activity_zhenma_p_z_show;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}