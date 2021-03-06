package com.sm.android.locations.location.Activity.AddParam;

import android.app.Dialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.sm.android.locations.location.Base.BaseActivity;
import com.sm.android.locations.location.R;
import com.sm.android.locations.location.Utils.MainUtils.MainUtils2;

import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.AddPararBeanWhite;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerAddParamWhite;
import com.sm.android.locations.location.Utils.ToastUtils;
import com.sm.android.locations.location.Utils.UtilsView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.sm.android.locations.location.App.App.context;

public class AddParamWhiteActivity extends BaseActivity implements View.OnClickListener {
    ImageView iv_findish;
    EditText ed_phone, ed_yy, ed_name, ed_imsi;
    Button button, bt_cancel;
    DBManagerAddParamWhite dbManagerAddParam = null;
    Spinner sp1;
    ArrayAdapter adapter1;
    private String spinnerS1 = "";

    @Override
    protected void initQx() {

    }

    @Override
    protected void init() {
        try {
            dbManagerAddParam = new DBManagerAddParamWhite(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void findViews() {
        bt_cancel = findViewById(R.id.bt_cancel);
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        sp1 = findViewById(R.id.sp1);
        List<String> list = new ArrayList<>();
        list.add("??????");
        list.add("??????");
        list.add("??????");
        adapter1 = new ArrayAdapter<String>(context, R.layout.spinner_item
                , list);
        sp1.setAdapter(adapter1);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerS1 = (String) adapter1.getItem(i);//?????????1???????????????

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        LinearLayout layout = findViewById(R.id.ll_tab);
        TextView title = findViewById(R.id.titles);
        ImageView imageView = findViewById(R.id.iv_menu);
        iv_findish = findViewById(R.id.iv_finish);
        iv_findish.setOnClickListener(this);
        UtilsView.setViewVisibility(this, layout, title, imageView, getResources().getString(R.string.addparam_activity), false, iv_findish, true);
        //???????????????View
        ed_phone = findViewById(R.id.ed_phone);
        ed_imsi = findViewById(R.id.ed_imsi);
        ed_name = findViewById(R.id.ed_name);
        ed_yy = findViewById(R.id.ed_yy);
        button = findViewById(R.id.button);
        button.setOnClickListener(this);
        //????????????????????????????????????
        ed_imsi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(ed_imsi.getText().toString())) {
                    String yy = MainUtils2.YY(ed_imsi.getText().toString());
                    ed_yy.setText(yy);
                }

            }
        });
    }

    @Override
    protected int getView() {
        return R.layout.activity_add_param;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_finish:
                finish();
                break;
            case R.id.button:
                IsImpty();
                break;
        }
    }

    private void IsImpty() {
        List<AddPararBeanWhite> addPararBeans = null;
        try {
            addPararBeans = dbManagerAddParam.getDataAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Log.d("AddPararBean", "insertData: " + addPararBeans);
//        if (TextUtils.isEmpty(ed_name.getText().toString())) {
//            ToastUtils.showToast("??????????????????");
//            return;
//        }
//        if (TextUtils.isEmpty(ed_phone.getText().toString())) {
//            ToastUtils.showToast("??????????????????");
//            return;
//        }
//        if (!RegexUtils.isMobileExact(ed_phone.getText().toString())) {
//            ToastUtils.showToast("???????????????????????????");
//            return;
//        }

        if (TextUtils.isEmpty(ed_imsi.getText().toString())) {
            ToastUtils.showToast("IMSI????????????");
            return;
        }
        if (ed_imsi.getText().length() != 15) {
            ToastUtils.showToast("?????????15???IMSI???");
            return;
        }
//        if (TextUtils.isEmpty(ed_yy.getText().toString())) {
//            ToastUtils.showToast("?????????????????????");
//            return;
//        }
//        new CircleDialog.Builder()
//                .setTitle("")
//                .setText("???????????????IMSI?????????")
//                .setTitleColor(Color.parseColor("#00acff"))
//                .setNegative("??????", new Positiv())
//                .setPositive("??????", null)
//                .show(getSupportFragmentManager());
        final Dialog dialog = new Dialog(AddParamWhiteActivity.this, R.style.menuDialogStyleDialogStyle);
        View inflate = LayoutInflater.from(AddParamWhiteActivity.this).inflate(R.layout.dialog_item_title, null);
        TextView tv_title = inflate.findViewById(R.id.tv_title);
        tv_title.setText("?????????????????????IMSI????");
        Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
        bt_confirm.setOnClickListener(new Positiv(dialog));
        Button bt_cancel = inflate.findViewById(R.id.bt_cancel);
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                dialog.cancel();
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(inflate);
        //????????????Activity???????????????
        Window dialogWindow = dialog.getWindow();
        //??????Dialog?????????????????????
        dialogWindow.setGravity(Gravity.CENTER);
        //?????????????????????
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//                           lp.y = 20;//??????Dialog?????????????????????
//                          ????????????????????????
        dialogWindow.setAttributes(lp);
        dialog.show();//???????????????
    }

    private void insertData() throws SQLException {
        AddPararBeanWhite addPararBean = new AddPararBeanWhite();
        addPararBean.setId(0);
        addPararBean.setImsi(ed_imsi.getText().toString() + "");
        addPararBean.setName(ed_name.getText().toString() + "");
        addPararBean.setPhone(ed_phone.getText().toString() + "");
        addPararBean.setYy(spinnerS1 + "");
        addPararBean.setCheckbox(true);
        int i = dbManagerAddParam.insertAddPararBean(addPararBean);

        if (i == 1) {
            ToastUtils.showToast("????????????IMSI??????");
            finish();
        } else {
            ToastUtils.showToast("????????????IMSI??????");

        }
        List<AddPararBeanWhite> addPararBeans = dbManagerAddParam.getDataAll();
        Log.d("AddPararBean", "insertData: " + addPararBeans);

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    class Positiv implements View.OnClickListener {
        Dialog dialog;

        public Positiv(Dialog dialog) {
            this.dialog = dialog;
        }

        @Override
        public void onClick(View view) {
            try {
                insertData();
                dialog.dismiss();
                dialog.cancel();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
