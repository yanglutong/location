package com.sm.android.locations.location.Activity.SaoPin.SaoPinActivity;

import android.content.Intent;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sm.android.locations.location.Activity.Main.Adapter.SaopinListAdapter;
import com.sm.android.locations.location.Activity.Main.MainActivity;
import com.sm.android.locations.location.Base.BaseActivity;
import com.sm.android.locations.location.R;
import com.sm.android.locations.location.Utils.ToastUtils;
import com.sm.android.locations.location.Utils.UtilsView;
import com.sm.android.locations.location.initData.MyLog;
import com.sm.android.locations.location.sos.SOSActivity;

import java.io.UnsupportedEncodingException;

import static com.sm.android.locations.location.Activity.Main.MainActivity.SPBEANLIST1;
import static com.sm.android.locations.location.Activity.Main.MainActivity.SPBEANLIST2;

public class SaoPinActivity extends BaseActivity implements View.OnClickListener {
    Button bt_add;
    ImageView iv_no, image_jiahao;
    TextView nullData;
    TextView tv_no;
    ImageView iv_finish, iv_add;
    RecyclerView ry;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sao_pin);
//    }

    @Override
    protected void initQx() {

    }

    @Override
    protected void init() throws UnsupportedEncodingException {
//        setStatBar();
        iv_add = findViewById(R.id.iv_add);
        iv_add.setVisibility(View.VISIBLE);
//        iv_add.setOnClickListener(this);
        nullData = findViewById(R.id.nullData);
        LinearLayout layout = findViewById(R.id.ll_tab);
        TextView title = findViewById(R.id.titles);
        ImageView imageView = findViewById(R.id.iv_menu);
        iv_finish = findViewById(R.id.iv_finish);
        iv_finish.setOnClickListener(this);
        UtilsView.setViewVisibility(this, layout, title, imageView, getResources().getString(R.string.xiaoqusaopin), false, iv_finish, true);
        iv_add.setVisibility(View.GONE);
        ry = findViewById(R.id.ry);//扫频列表集合
        ry.setLayoutManager(new LinearLayoutManager(SaoPinActivity.this, LinearLayoutManager.VERTICAL, false));
        Log.d("SaoPinCallbackList", "init: " + SPBEANLIST1.toString());
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");

        MyLog.e("SaoPin", type);
        MyLog.e("SaoPin", "xqck"+SOSActivity.xqck.toString());

        if(type.equals("3")){
            if(SOSActivity.xqck.size()>0){
                UtilsView.setViewVisibility(this, layout, title, imageView, "设备小区信息", false, iv_finish, true);
                SaopinListAdapter saopinListAdapter = new SaopinListAdapter(SaoPinActivity.this,SOSActivity.xqck);
                ry.setAdapter(saopinListAdapter);
            }

        }
        if (type.equals("1")) {
            UtilsView.setViewVisibility(this, layout, title, imageView, "设备小区信息", false, iv_finish, true);

//            for (int i = 0; i <20 ; i++) {
//                SpBean spBean=new SpBean();
//                spBean.setDown("500");
//                spBean.setBand(4+"");
//                spBean.setTac(1233);
//                spBean.setPlmn("46000");spBean.setBand(4+"");
//                spBean.setPci(999);
//                spBean.setTac(999);
//                spBean.setRsrp(0);
//                spBean.setCid("3333");
//
//                SPBEANLIST1.add(spBean);
//            }

            SaopinListAdapter saopinListAdapter = new SaopinListAdapter(SaoPinActivity.this, SPBEANLIST1);
            ry.setAdapter(saopinListAdapter);
        }
        if (type.equals("2")) {
            UtilsView.setViewVisibility(this, layout, title, imageView, "设备2小区信息", false, iv_finish, true);

            SaopinListAdapter saopinListAdapter = new SaopinListAdapter(SaoPinActivity.this, SPBEANLIST2);
            ry.setAdapter(saopinListAdapter);
        }
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected int getView() {
        return R.layout.activity_sao_pin;
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
