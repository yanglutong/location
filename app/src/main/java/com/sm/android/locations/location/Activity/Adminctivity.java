package com.sm.android.locations.location.Activity;

import android.content.Intent;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sm.android.locations.location.Activity.AddParam.Adapter.TestBaseAdapter2;
import com.sm.android.locations.location.Activity.AddParam.Adapter.TestData2;
import com.sm.android.locations.location.Base.BaseActivity;
import com.sm.android.locations.location.R;
import com.sm.android.locations.location.UpdateAdminActivity;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.AdminBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerAdmin;
import com.sm.android.locations.location.Utils.UtilsView;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Adminctivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = Adminctivity.ACTIVITY_SERVICE;
    ImageView iv_findish;
    private RecyclerView recyclerView;
    private List<TestData2> data = new ArrayList<>();
    private TestBaseAdapter2 mAdapter;
    TextView nullData;
    TestBaseAdapter2.ITTestBaseAdapterCallBack2 itTestBaseAdapterCallBack = new TestBaseAdapter2.ITTestBaseAdapterCallBack2() {
        @Override
        public void Call(int position, boolean checkFlag, CheckBox checkBox) {
            try {
                DBManagerAdmin dbManagerAdmin = new DBManagerAdmin(Adminctivity.this);
                AdminBean forid = dbManagerAdmin.forid(position);
                if (checkFlag == true) {
                    forid.setType(1);
                } else {
                    forid.setType(0);
                }
                dbManagerAdmin.updateConmmunit01Bean(forid);
                setadapter();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void CallDele(int id, int position) {
            if (data.size() > 2) {
                data.remove(position);
//            setadapter();
                recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                GridLayoutManager mLayoutManager = new GridLayoutManager(Adminctivity.this, 1);
                recyclerView.setLayoutManager(mLayoutManager);
//            recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, 12, false));
                mAdapter = new TestBaseAdapter2(Adminctivity.this, itTestBaseAdapterCallBack);
                mAdapter.setDataList(data);
                mAdapter.setHasMoreData(true);
                recyclerView.setAdapter(mAdapter);
                if (data.size() > 0) {
                    nullData.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                } else {
                    nullData.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
            } else {
                setadapter();
            }

        }
    };

    @Override
    protected void initQx() {

    }

    @Override
    protected void init() throws UnsupportedEncodingException {

    }

    @Override
    protected void findViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        ImageView iv_add = findViewById(R.id.iv_add);
        iv_add.setVisibility(View.VISIBLE);
        iv_add.setOnClickListener(this);
        nullData = findViewById(R.id.nullData);
        LinearLayout layout = findViewById(R.id.ll_tab);
        TextView title = findViewById(R.id.titles);
        ImageView imageView = findViewById(R.id.iv_menu);
        iv_findish = findViewById(R.id.iv_finish);
        iv_findish.setOnClickListener(this);
        UtilsView.setViewVisibility(this, layout, title, imageView, getResources().getString(R.string.personnelConfig), false, iv_findish, true);
        setadapter();
    }

    private void setadapter() {
        DBManagerAdmin dbManagerAdmin = null;
        List<AdminBean> listBeans = null;
        try {
            dbManagerAdmin = new DBManagerAdmin(Adminctivity.this);
            AdminBean adminBean = new AdminBean();
            adminBean.setCheckbox(true);
            adminBean.setName("小王");
            adminBean.setPwd("121314");
            adminBean.setNote("去甘肃执行任务,马上就去了阿旺旦达");
            adminBean.setType(1);

//            dbManagerAdmin.insertConmmunit01Bean(adminBean);
            listBeans = dbManagerAdmin.getListBeans();
            Log.d(TAG, "setadapter: " + listBeans.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (listBeans != null && listBeans.size() > 0) {//有数据
            data.clear();
            nullData.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            for (int i = 0; i < listBeans.size(); i++) {
                TestData2 testData = new TestData2();
                testData.xh = i + 1 + "";
                testData.name = listBeans.get(i).getName();
                testData.pwd = listBeans.get(i).getPwd();
                testData.id = listBeans.get(i).getId();
                testData.note = listBeans.get(i).getNote();
                testData.check = listBeans.get(i).isCheckbox();
                testData.type = listBeans.get(i).getType() + "";
                data.add(testData);
                Log.d("nzqqqqq", "setadapter: " + data);
            }
        } else {//无数据
            nullData.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
        mAdapter = new TestBaseAdapter2(Adminctivity.this, itTestBaseAdapterCallBack);
        mAdapter.setDataList(data);
        mAdapter.setHasMoreData(true);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected int getView() {
        return R.layout.activity_adminctivity;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setadapter();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_finish://关闭
                finish();
                break;
            case R.id.iv_add://添加
                startActivity(new Intent(Adminctivity.this, UpdateAdminActivity.class));
                break;
        }
    }
}
