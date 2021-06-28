package com.sm.android.locations.locations.Activity.Communit;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.sm.android.locations.locations.Base.BaseActivity;
import com.sm.android.locations.locations.R;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.Conmmunit01Bean;
import com.sm.android.locations.locations.Utils.OrmSqlLite.DBManager01;
import com.sm.android.locations.locations.Utils.UtilsView;

import java.sql.SQLException;
import java.util.List;

public class Community01Activity extends BaseActivity implements View.OnClickListener {

    /**
     * 小区01配置
     */
    Button bt_add;
    ImageView finsh, image_jiahao;
    DBManager01 dbManager01;
    List<Conmmunit01Bean> conmmunit01Beans;
    List<Conmmunit01Bean> conmmunit01Beans2;
    //生成的ID
    private ImageView mFinsh;
    private TextView mMytitle;
    private ImageView mImage_jiahao;
    private EditText mEt_tac, mEt_tac2;
    private EditText et_cid, et_cid2;
    private EditText et_pci, et_pci2;
    private EditText mEt_uepmax, mEt_uepmax2;
    private EditText mEt_enodebpmax, mEt_enodebpmax2;
    private Button mBt_save, bt_cancel;


    protected void initData() {
//        setStatBar();

        bindViews();
        findview();
        try {
            dbManager01 = new DBManager01(this);

            /**
             * 插入数据
             */

            conmmunit01Beans = dbManager01.getConmmunit01Beans();
            Log.e("conmmunit01Beans", "initData: " + conmmunit01Beans);
            Conmmunit01Bean forid = dbManager01.forid(1);
            if (forid == null) {
                Log.e("nzqforid", "等于nullinitData: " + forid);
                Conmmunit01Bean conmmunit01Bean = new Conmmunit01Bean();
                conmmunit01Bean.setCid("");
                conmmunit01Bean.setEnodebpmax("");
                conmmunit01Bean.setUepmax("");
                conmmunit01Bean.setTac("");
                conmmunit01Bean.setPci("");
                conmmunit01Bean.setCid("");
                dbManager01.insertConmmunit01Bean(conmmunit01Bean);
            } else {
                Log.e("nzqforid", "不等于nullinitData: " + forid);
                et_cid.setText(forid.getCid() + "");
                et_pci.setText(forid.getPci() + "");
                mEt_tac.setText(forid.getTac() + "");
                mEt_enodebpmax.setText(forid.getEnodebpmax() + "");
                mEt_uepmax.setText(forid.getUepmax() + "");
            }
            Log.e("nzq", "initData: " + forid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (conmmunit01Beans.size() == 0) {
            //新添加的时候
        } else {

        }
        //小区2配置
        try {
            dbManager01 = new DBManager01(this);

            /**
             * 插入数据
             */

            conmmunit01Beans2 = dbManager01.getConmmunit01Beans();
            Log.e("conmmunit01Beans", "initData: " + conmmunit01Beans2);
            Conmmunit01Bean forid2 = dbManager01.forid(2);
            if (forid2 == null) {
                Log.e("nzqforid", "等于nullinitData: " + forid2);
                Conmmunit01Bean conmmunit01Bean = new Conmmunit01Bean();
                conmmunit01Bean.setCid("");
                conmmunit01Bean.setEnodebpmax("");
                conmmunit01Bean.setUepmax("");
                conmmunit01Bean.setTac("");
                conmmunit01Bean.setPci("");
                conmmunit01Bean.setCid("");
                dbManager01.insertConmmunit01Bean(conmmunit01Bean);
            } else {
                Log.e("nzqforid", "不等于nullinitData: " + forid2);
                et_cid2.setText(forid2.getCid() + "");
                et_pci2.setText(forid2.getPci() + "");
                mEt_tac2.setText(forid2.getTac() + "");
                mEt_enodebpmax2.setText(forid2.getEnodebpmax() + "");
                mEt_uepmax2.setText(forid2.getUepmax() + "");
            }
            Log.e("nzq", "initData: " + forid2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (conmmunit01Beans2.size() == 0) {
            //新添加的时候
        } else {

        }
    }


    private void findview() {
//        bt_add = findViewById(R.id.bt_add);
//        bt_add.setOnClickListener(this);
        finsh = findViewById(R.id.finsh);
        finsh.setOnClickListener(this);
        image_jiahao = findViewById(R.id.image_jiahao);
        image_jiahao.setOnClickListener(this);
        bt_cancel = findViewById(R.id.bt_cancel);
        bt_cancel.setOnClickListener(this);
        LinearLayout layout = findViewById(R.id.ll_tab);
        TextView title = findViewById(R.id.titles);
        ImageView imageView = findViewById(R.id.iv_menu);
        ImageView iv_findish = findViewById(R.id.iv_finish);
        iv_findish.setOnClickListener(this);
        UtilsView.setViewVisibility(this, layout, title, imageView, getResources().getString(R.string.coummunity_activity), false, iv_findish, false);
    }

    private void bindViews() {
        mFinsh = (ImageView) findViewById(R.id.finsh);
        mMytitle = (TextView) findViewById(R.id.mytitle);
        mImage_jiahao = (ImageView) findViewById(R.id.image_jiahao);
        mEt_tac = (EditText) findViewById(R.id.et_tac);
        et_pci = (EditText) findViewById(R.id.et_pci);
        et_cid = (EditText) findViewById(R.id.et_cid);
        mEt_uepmax = (EditText) findViewById(R.id.et_uepmax);
        mEt_enodebpmax = (EditText) findViewById(R.id.et_enodebpmax);

        mEt_tac2 = (EditText) findViewById(R.id.et_tac2);
        et_pci2 = (EditText) findViewById(R.id.et_pci2);
        et_cid2 = (EditText) findViewById(R.id.et_cid2);
        mEt_uepmax2 = (EditText) findViewById(R.id.et_uepmax2);
        mEt_enodebpmax2 = (EditText) findViewById(R.id.et_enodebpmax2);

        mBt_save = (Button) findViewById(R.id.bt_save);
        mBt_save.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_jiahao:
                break;

            case R.id.finsh:
                finish();
                break;
            case R.id.bt_save:

                //判断书否新建或者修改
                sqldata1();
                sqldata2();
                finish();
                break;

            case R.id.iv_finish:
                finish();
                break;
            case R.id.bt_cancel:
                finish();
                break;
        }
    }

    private void sqldata2() {
        try {
            dbManager01 = new DBManager01(this);

            /**
             * 插入数据
             */

            conmmunit01Beans = dbManager01.getConmmunit01Beans();
            Log.e("conmmunit01Beans", "initData: " + conmmunit01Beans);
            Conmmunit01Bean forid1 = dbManager01.forid(2);
            if (forid1 == null) {
                Log.e("nzqforid", "等于null 添加initData: " + forid1);
                Conmmunit01Bean conmmunit01Bean = new Conmmunit01Bean();
                conmmunit01Bean.setCid(et_cid2.getText().toString());
                conmmunit01Bean.setEnodebpmax(mEt_enodebpmax2.getText().toString());
                conmmunit01Bean.setUepmax(mEt_uepmax2.getText().toString());
                conmmunit01Bean.setTac(mEt_tac2.getText().toString());
                conmmunit01Bean.setPci(et_pci2.getText().toString());
                dbManager01.insertConmmunit01Bean(conmmunit01Bean);
            } else {
                Log.e("nzqforid", "不等于null 保存: " + forid1);
                Log.e("et_cid.getText", "sqldata: " + et_cid.getText().toString());
                Conmmunit01Bean conmmunit01Bean1 = new Conmmunit01Bean();
                conmmunit01Bean1.setCid(et_cid2.getText().toString());
                conmmunit01Bean1.setEnodebpmax(mEt_enodebpmax2.getText().toString());
                conmmunit01Bean1.setUepmax(mEt_uepmax2.getText().toString());
                conmmunit01Bean1.setTac(mEt_tac2.getText().toString());
                conmmunit01Bean1.setPci(et_pci2.getText().toString());
                conmmunit01Bean1.setId(2);
                dbManager01.updateConmmunit01Bean(conmmunit01Bean1);
                Log.e("sql", "sqldata: " + conmmunit01Bean1);
                Toast.makeText(this, "保存成功", Toast.LENGTH_LONG).show();
//                finish();
            }
            Log.e("nzq", "initData: " + forid1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void sqldata1() {
        try {
            dbManager01 = new DBManager01(this);

            /**
             * 插入数据
             */

            conmmunit01Beans = dbManager01.getConmmunit01Beans();
            Log.e("conmmunit01Beans", "initData: " + conmmunit01Beans);
            Conmmunit01Bean forid1 = dbManager01.forid(1);
            if (forid1 == null) {
                Log.e("nzqforid", "等于null 添加initData: " + forid1);
                Conmmunit01Bean conmmunit01Bean = new Conmmunit01Bean();
                conmmunit01Bean.setCid(et_cid.getText().toString());
                conmmunit01Bean.setEnodebpmax(mEt_enodebpmax.getText().toString());
                conmmunit01Bean.setUepmax(mEt_uepmax.getText().toString());
                conmmunit01Bean.setTac(mEt_tac.getText().toString());
                conmmunit01Bean.setPci(et_pci.getText().toString());
                dbManager01.insertConmmunit01Bean(conmmunit01Bean);
            } else {
                Log.e("nzqforid", "不等于null 保存: " + forid1);
                Log.e("et_cid.getText", "sqldata: " + et_cid.getText().toString());
                Conmmunit01Bean conmmunit01Bean1 = new Conmmunit01Bean();
                conmmunit01Bean1.setCid(et_cid.getText().toString());
                conmmunit01Bean1.setEnodebpmax(mEt_enodebpmax.getText().toString());
                conmmunit01Bean1.setUepmax(mEt_uepmax.getText().toString());
                conmmunit01Bean1.setTac(mEt_tac.getText().toString());
                conmmunit01Bean1.setPci(et_pci.getText().toString());
                conmmunit01Bean1.setId(1);
                dbManager01.updateConmmunit01Bean(conmmunit01Bean1);
                Log.e("sql", "sqldata: " + conmmunit01Bean1);
                Toast.makeText(this, "保存成功", Toast.LENGTH_LONG).show();
//                finish();
            }
            Log.e("nzq", "initData: " + forid1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initQx() {

    }

    @Override
    protected void init() {
        initData();
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected int getView() {
        return R.layout.activity_community01;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
