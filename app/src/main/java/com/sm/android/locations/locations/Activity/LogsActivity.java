package com.sm.android.locations.locations.Activity;

import android.content.Intent;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sm.android.locations.locations.Activity.AddParam.Adapter.TestBaseAdapter2;
import com.sm.android.locations.locations.Activity.AddParam.Adapter.TestBaseAdapter3;
import com.sm.android.locations.locations.Activity.AddParam.Adapter.TestData2;
import com.sm.android.locations.locations.Activity.AddParam.Adapter.TestData3;
import com.sm.android.locations.locations.Base.BaseActivity;
import com.sm.android.locations.locations.R;
import com.sm.android.locations.locations.Utils.LoginUtils.LoginUtils;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.LogBean;
import com.sm.android.locations.locations.Utils.OrmSqlLite.DBManagerLog;
import com.sm.android.locations.locations.Utils.UtilsView;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LogsActivity extends BaseActivity implements View.OnClickListener {
    String id = "";
    String name = "";
    ImageView iv_findish;
    private RecyclerView recyclerView;
    private List<TestData3> data = new ArrayList<>();
    TextView nullData;
    private TestBaseAdapter3 mAdapter;

    @Override
    protected void initQx() {

    }

    TestBaseAdapter3.ITTestBaseAdapterCallBack3 callBack3 = new TestBaseAdapter3.ITTestBaseAdapterCallBack3() {
        @Override
        public void Call(int position, boolean checkFlag, CheckBox checkBox) {

        }

        @Override
        public void CallDele(int id, int position) {

        }
    };

    @Override
    protected void init() throws UnsupportedEncodingException {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        name= intent.getStringExtra("name");
        try {
            DBManagerLog dbManagerLog = new DBManagerLog(LogsActivity.this);
            List<LogBean> zcBean1Beans = dbManagerLog.getZcBean1Beans();
            Log.e("zcBean1Beans", "onCreate: " + id + "-------" + zcBean1Beans.toString());
            List<LogBean> zcBean1BeansID = new ArrayList<>();
            if (zcBean1Beans.size() > 0 && zcBean1Beans != null) {
                for (int i = 0; i < zcBean1Beans.size(); i++) {
                    if (zcBean1Beans.get(i).getAssociated().equals(id)) {
                        zcBean1BeansID.add(zcBean1Beans.get(i));
                    }

                }
            } else {//全部数据没有

            }
            if (zcBean1BeansID.size() > 0 && zcBean1BeansID != null) {
                Log.e("tagzcBean1BeansID", "onCreate: " + zcBean1BeansID.toString());

                for (int i = 0; i < zcBean1BeansID.size(); i++) {
                    TestData3 testData = new TestData3();
//                    testData.id= i + 1 + "";
                    testData.xh = i + 1 ;
                    testData.id = zcBean1BeansID.get(i).getId();
                    testData.associated = zcBean1BeansID.get(i).getAssociated()+"";
                    testData.datetime = zcBean1BeansID.get(i).getDatetime()+"";
                    testData.event = zcBean1BeansID.get(i).getEvent();
                    testData.pd = zcBean1BeansID.get(i).getPd()+"";
                    testData.sb = zcBean1BeansID.get(i).getSb() + "";
                    testData.sucessIMSI = zcBean1BeansID.get(i).getSucessIMSI() + "";
                    testData.person = name + "";
                    data.add(testData);
                }
                mAdapter = new TestBaseAdapter3(LogsActivity.this, callBack3);
                mAdapter.setDataList(data);
                mAdapter.setHasMoreData(true);
                recyclerView.setAdapter(mAdapter);
            } else {
                //没有数据
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void findViews() {
        LinearLayout layout = findViewById(R.id.ll_tab);
        TextView title = findViewById(R.id.titles);
        ImageView imageView = findViewById(R.id.iv_menu);
        iv_findish = findViewById(R.id.iv_finish);
        iv_findish.setOnClickListener(this);
        UtilsView.setViewVisibility(this, layout, title, imageView, "日志管理", false, iv_findish, true);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        GridLayoutManager mLayoutManager = new GridLayoutManager(LogsActivity.this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    protected int getView() {
        return R.layout.activity_logs;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_finish:
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
