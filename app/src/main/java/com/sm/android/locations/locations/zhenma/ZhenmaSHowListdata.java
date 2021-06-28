package com.sm.android.locations.locations.zhenma;

import android.content.Intent;

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

import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.ZmBeanlinshi;
import com.sm.android.locations.locations.Utils.OrmSqlLite.DBManagerZMlinshi;
import com.sm.android.locations.locations.Utils.UtilsView;
import com.sm.android.locations.locations.zhenma.adapter.ZhenmafenxiImsiListAdapter;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

public class ZhenmaSHowListdata extends BaseActivity {

    LinearLayout layout;
    TextView title;
    ImageView iv_finish, iv_add, imageView, iv_fenxi;
    int MainLayout = R.layout.activity_zhenma_fenxi;//主界面布局
    Button bt_pd;
    RecyclerView ry;

    @Override
    protected void initQx() {

    }

    @Override
    protected void init() throws UnsupportedEncodingException {

//        setStatBar();//去导航栏
        iv_finish.setVisibility(View.VISIBLE);
        UtilsView.setViewVisibility(this, layout, title, imageView, getResources().getString(R.string.zhenmafenxiimsilist), false, iv_finish, true);
    }

    @Override
    protected void findViews() {
        layout = findViewById(R.id.ll_tab);
        title = findViewById(R.id.titles);
        ry = findViewById(R.id.ry);
        ry.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        imageView = findViewById(R.id.iv_menu);
        iv_fenxi = findViewById(R.id.iv_fenxi);
        iv_finish = findViewById(R.id.iv_finish);
        iv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();

        String starttime = intent.getStringExtra("starttime");
        String stoptime = intent.getStringExtra("stoptime");
        Log.d("ZhenmaSHowListdatanzq", "init: " + starttime + "==" + stoptime);
        try {
            DBManagerZMlinshi dbManagerZMlinshi = new DBManagerZMlinshi(ZhenmaSHowListdata.this);
            List<ZmBeanlinshi> list = dbManagerZMlinshi.queryGuanyu2(starttime, stoptime);
            if (list.size() > 0) {
                ZhenmafenxiImsiListAdapter ZhenmafenxiImsiListAdapter = new ZhenmafenxiImsiListAdapter(this, list);
                ry.setAdapter(ZhenmafenxiImsiListAdapter);
            }
            Log.d("nzqZmBeanlinshi", "init: " + list.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected int getView() {
        return R.layout.activity_zhenma_s_how_listdata;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}