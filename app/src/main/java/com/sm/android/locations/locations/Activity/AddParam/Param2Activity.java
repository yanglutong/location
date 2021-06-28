package com.sm.android.locations.locations.Activity.AddParam;

import android.content.Intent;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.diycoder.library.decoration.GridSpacingItemDecoration;
import com.sm.android.locations.locations.Activity.AddParam.Adapter.TestBaseAdapter;
import com.sm.android.locations.locations.Activity.AddParam.Adapter.TestData;
import com.sm.android.locations.locations.Activity.Main.Adapter.Param2RyImsiAdapter;
import com.sm.android.locations.locations.Base.BaseActivity;
import com.sm.android.locations.locations.R;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.AddPararBean;
import com.sm.android.locations.locations.Utils.OrmSqlLite.DBManagerAddParam;
import com.sm.android.locations.locations.Utils.ToastUtils;
import com.sm.android.locations.locations.Utils.UtilsView;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;

public class Param2Activity extends BaseActivity implements View.OnClickListener {
    Param2RyImsiAdapter.ITParamAdapterCallBack callBack = new Param2RyImsiAdapter.ITParamAdapterCallBack() {
        @Override
        public void Call(int id, boolean checkFlag, CheckBox checkBox, int positon) {
            Log.d("opjiohiho", "Call: " + id + "boolean---" + checkFlag);
            try {
                AddPararBean forid = dbManagerAddParam.forid(id);
                forid.setCheckbox(checkFlag);
                int i1 = dbManagerAddParam.updateCheck(forid);
//                if (i1 == 1) {
//                    try {
//                        init();
//                    } catch (UnsupportedEncodingException e) {
//                        e.printStackTrace();
//                    }
//                } else {
//                    Log.d("nzqq", "Call: "+55);
//                }
                Log.d("nzqqqqqqqq", "Call: " + i1);
                List<AddPararBean> dataAlla = dbManagerAddParam.getDataAll();
                List<AddPararBean> list = new ArrayList<>();
                if (dataAlla.size() > 0 && dataAlla != null) {
                    for (int i = 0; i < dataAlla.size(); i++) {
                        if (dataAlla.get(i).isCheckbox() == true) {
                            list.add(dataAlla.get(i));
                        }
                    }
                    if (list.size() > 0 && list != null) {
                        if (list.size()> 20) {
                            ToastUtils.showToast("大于20了");
                            forid.setCheckbox(checkFlag);
                            dbManagerAddParam.updateCheck(forid);
                            checkBox.setChecked(false);
//                            dataAlla.get(positon).setCheckbox(false);
////////                            setadapter();
                        }
                    } else {
                        Log.d("nzqcheck", "Call:3 ");
//
                    }
                } else {
                    Log.d("nzqcheck", "Call:4 ");
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void CallDele(int id, int position) {

        }
    };

    private DBManagerAddParam dbManagerAddParam;
    ImageView iv_findish;
    private RecyclerView recyclerView;
    TextView nullData;
    List<AddPararBean> dataAll = null;
    Param2RyImsiAdapter mAdapter = null;

    @Override
    protected void initQx() {

    }

    @Override
    protected void init() throws UnsupportedEncodingException {
        try {
            dbManagerAddParam = new DBManagerAddParam(Param2Activity.this);
//            dbManagerAddParam.deleteall();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ImageView iv_add = findViewById(R.id.iv_add);
        iv_add.setVisibility(View.VISIBLE);
        iv_add.setOnClickListener(this);
        nullData = findViewById(R.id.nullData);
        LinearLayout layout = findViewById(R.id.ll_tab);
        TextView title = findViewById(R.id.titles);
        ImageView imageView = findViewById(R.id.iv_menu);
        iv_findish = findViewById(R.id.iv_finish);
        iv_findish.setOnClickListener(this);
        UtilsView.setViewVisibility(this, layout, title, imageView, getResources().getString(R.string.param_activity), false, iv_findish, true);

        setadapter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            init();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void setadapter() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        try {

            dataAll = dbManagerAddParam.getDataAll();
            Log.d("nzq", "findViews: " + dataAll);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (dataAll.size() > 0 && dataAll != null) {

            nullData.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

        } else {
            nullData.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }

        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, 12, false));
        mAdapter = new Param2RyImsiAdapter(Param2Activity.this, dataAll, callBack);
//        mAdapter.setDataList(data);
//        mAdapter.setHasMoreData(true);
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void findViews() {

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected int getView() {
        return R.layout.activity_param2;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_finish:
                finish();
                break;

            case R.id.iv_add:
                startActivity(new Intent(Param2Activity.this, AddParamActivity.class));
                break;
        }
    }
}
