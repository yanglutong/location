package com.sm.android.locations.location.Activity.PinConfig;

import android.app.Dialog;
import android.database.SQLException;
import android.os.Build;

import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.sm.android.locations.location.Activity.PinConfig.fragment.PinConfigFragment0;
import com.sm.android.locations.location.Activity.PinConfig.fragment.PinConfigFragment1;
import com.sm.android.locations.location.Activity.PinConfig.fragment.PinConfigFragment2;
import com.sm.android.locations.location.Activity.PinConfig.fragment.PinConfigFragment3;
import com.sm.android.locations.location.Base.BaseActivity;
import com.sm.android.locations.location.Base.BaseFragment;
import com.sm.android.locations.location.R;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.PinConfigBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerPinConfig;
import com.sm.android.locations.location.Utils.ToastUtils;
import com.sm.android.locations.location.Utils.UtilsView;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static com.sm.android.locations.location.App.App.context;

public class PinConfigViewPagerActivity extends BaseActivity implements View.OnClickListener {
    LinearLayout layout;
    TextView title;
    ImageView iv_finish, iv_add, imageView;
    ViewPager viewpage;
    Button button0, button1, button2, button3;
    List<Button> buttonList = new ArrayList<>();
    private int type = 0;
    //?????????????????????
    private Dialog dialog;
    private View inflate;
    private Button bt_adddilao, bt_canel;
    private ImageView iv_show_finish;
    private EditText et_up_ping, et_down_ping, et_plmn, et_band;
    private String spinnerYY = "";
    private String spinnerTF = "";
    private Spinner sp_yy, sp_tf;
    private TextView titles;
    int sizeinit;//?????????????????????????????????
    private DBManagerPinConfig dbManager;

    @Override
    protected void initQx() {

    }

    @Override
    protected void init() throws UnsupportedEncodingException {
//        setStatBar();//????????????
        UtilsView.setViewVisibility(this, layout, title, imageView, getResources().getString(R.string.pin_activity2), false, iv_finish, true);
        setViewpagerData();
        //?????????DBManager
        try {
            dbManager = new DBManagerPinConfig(this);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    private void setViewpagerData() {
        PinConfigViewPagerActivity.MyFragmentPagerAdapter myFragmentPagerAdapter = new PinConfigViewPagerActivity.MyFragmentPagerAdapter(getSupportFragmentManager());
        myFragmentPagerAdapter.getItem(0);
        viewpage.setAdapter(myFragmentPagerAdapter);
        viewpage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onPageSelected(int i) {
                Log.d("viewpagercount", "findViews: " + i);
                if (i == 0) {
                    setBackground(buttonList, 0);
                    type = 0;
                    return;
                }
                if (i == 1) {
                    setBackground(buttonList, 1);
                    type = 1;
                    return;

                }
                if (i == 2) {
                    setBackground(buttonList, 2);
                    type = 2;
                    return;

                }
                if (i == 3) {
                    setBackground(buttonList, 3);
                    type = 3;
                    return;

                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setBackground(List<Button> buttonList, int i) {
        for (int j = 0; j < buttonList.size(); j++) {
            if (i == 0) {
                button0.setBackground(getResources().getDrawable(R.mipmap.duan));
                button0.setTextColor(getResources().getColor(R.color.white));
            } else {
                buttonList.get(j).setBackground(null);
                buttonList.get(j).setTextColor(getResources().getColor(R.color.black));
            }
            if (i == 1) {
                button1.setBackground(getResources().getDrawable(R.mipmap.duan));
                button1.setTextColor(getResources().getColor(R.color.white));
            } else {
                buttonList.get(j).setBackground(null);
                buttonList.get(j).setTextColor(getResources().getColor(R.color.black));
            }
            if (i == 2) {
                button2.setBackground(getResources().getDrawable(R.mipmap.duan));
                button2.setTextColor(getResources().getColor(R.color.white));
            } else {
                buttonList.get(j).setBackground(null);
                buttonList.get(j).setTextColor(getResources().getColor(R.color.black));
            }
            if (i == 3) {
                button3.setBackground(getResources().getDrawable(R.mipmap.duan));
                button3.setTextColor(getResources().getColor(R.color.white));
            } else {
                buttonList.get(j).setBackground(null);
                buttonList.get(j).setTextColor(getResources().getColor(R.color.black));
            }
        }

    }

    @Override
    protected void findViews() {
        iv_add = findViewById(R.id.iv_add);
        iv_add.setVisibility(View.VISIBLE);
        iv_add.setOnClickListener(this);
        title = findViewById(R.id.titles);
        imageView = findViewById(R.id.iv_menu);
        iv_finish = findViewById(R.id.iv_finish);
        iv_finish.setOnClickListener(this);
        layout = findViewById(R.id.ll_tab);
        viewpage = findViewById(R.id.viewpager);
        button0 = findViewById(R.id.bt_0);
        button1 = findViewById(R.id.bt_1);
        button2 = findViewById(R.id.bt_2);
        button3 = findViewById(R.id.bt_3);
        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        buttonList.add(button0);
        buttonList.add(button1);
        buttonList.add(button2);
        buttonList.add(button3);
    }

    @Override
    protected int getView() {
        return R.layout.activity_pin_config_viewpager;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    /**
     * ??????????????????
     */
    public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        private List<BaseFragment> fragments = new ArrayList<>();
        private String[] titles = {//
                "?????????\n\n??????????????????????????????????????????????????????????????????????????????????????????",//
                "?????????\n\n??????????????????????????????????????????????????????????????????????????????????????????",//
                "?????????\n\n??????????????????????????????????????????????????????????????????????????????????????????", //
                "?????????\n\n??????????????????????????????????????????????????????????????????????????????????????????"};

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
            fragments.add(new PinConfigFragment0("??????TDD"));
            fragments.add(new PinConfigFragment1("??????FDD"));
            fragments.add(new PinConfigFragment2("??????"));
            fragments.add(new PinConfigFragment3("??????"));
        }


        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_finish:
                finish();
                break;


            case R.id.iv_add:
                PinConfigBean pinConfigBean = new PinConfigBean();
                showDialog(false, pinConfigBean);
                break;

            case R.id.bt_adddilao:

                ViewisEmpty();
                break;
            case R.id.bt_canel:
                dialog.dismiss();
                break;

            case R.id.bt_0:
                viewpage.setCurrentItem(0);
                break;
            case R.id.bt_1:
                viewpage.setCurrentItem(1);
                break;
            case R.id.bt_2:
                viewpage.setCurrentItem(2);
                break;
            case R.id.bt_3:
                viewpage.setCurrentItem(3);
                break;


        }
    }

    /**
     * ???????????????
     */
    private void showDialog(boolean b, PinConfigBean pinConfigBean) {
        if (b) {
            dialog = new Dialog(this, R.style.ActionSheetDialogStyleDialog);
//            //????????????????????????
            inflate = LayoutInflater.from(this).inflate(R.layout.configlist_dibushow, null);
//            //???????????????
            bt_adddilao = inflate.findViewById(R.id.bt_adddilao);
            titles = inflate.findViewById(R.id.titles);
            titles.setText("????????????");
            bt_adddilao.setText("??????");
            bt_adddilao.setOnClickListener(new PinConfigViewPagerActivity.MySetUpdate(pinConfigBean, pinConfigBean.getId(), pinConfigBean.getType()));
            iv_show_finish = inflate.findViewById(R.id.iv_show_finish);
            iv_show_finish.setOnClickListener(this);
            et_up_ping = inflate.findViewById(R.id.et_up_ping);//??????
            et_up_ping.setText(pinConfigBean.getUp() + "");
            et_down_ping = inflate.findViewById(R.id.et_down_ping);//??????
            et_down_ping.setText(pinConfigBean.getDown() + "");
            sp_tf = inflate.findViewById(R.id.sp_tf);//TDD FDD
            et_plmn = inflate.findViewById(R.id.et_plmn);//plmn
            et_plmn.setText(pinConfigBean.getPlmn());
            et_band = inflate.findViewById(R.id.et_band);//??????
            et_band.setText(pinConfigBean.getBand() + "");
            sp_yy = inflate.findViewById(R.id.sp_yy);
            bt_canel = inflate.findViewById(R.id.bt_canel);
            bt_canel.setOnClickListener(this);
            String yy = pinConfigBean.getYy();
            Log.d("pinConfigBeanaa", "showDialog: " + yy);
            List<String> list1 = new ArrayList<>();
            list1.add("??????");
            list1.add("??????");
            list1.add("??????");
            final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.spinner_item
                    , list1);


            sp_yy.setAdapter(adapter1);
//            sp_yy.setSelection(2);
            if (!TextUtils.isEmpty(yy)) {
                if (yy.equals("??????")) {
                    sp_yy.setSelection(0);
                }
                if (yy.equals("??????")) {
                    sp_yy.setSelection(1);
                }
                if (yy.equals("??????")) {
                    sp_yy.setSelection(2);
                }
            }
            sp_yy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    spinnerYY = adapter1.getItem(i);//?????????1???????????????
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });


            String tf = pinConfigBean.getTf();
            Log.d("pinConfigBeanaa", "showDialog: " + tf);
            List<String> list2 = new ArrayList<>();
            list2.add("TDD");
            list2.add("FDD");
            final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.spinner_item
                    , list2);


            sp_tf.setAdapter(adapter2);
//            sp_yy.setSelection(2);
            if (!TextUtils.isEmpty(tf)) {
                if (yy.equals("TDD")) {
                    sp_tf.setSelection(0);
                }
                if (tf.equals("FDD")) {
                    sp_tf.setSelection(1);
                }
            }
            sp_tf.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    spinnerTF = adapter2.getItem(i);//?????????1???????????????
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            dialog.setCanceledOnTouchOutside(false);
////            et_yy.setText(pinConfigBean.getYy());
//            //??????????????????Dialog
            dialog.setContentView(inflate);
            //????????????Activity???????????????
            Window dialogWindow = dialog.getWindow();
            //??????Dialog?????????????????????
            dialogWindow.setGravity(Gravity.BOTTOM);
            dialogWindow.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            //?????????????????????
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//       ????????????????????????
            dialogWindow.setAttributes(lp);
            dialog.show();//???????????????
        } else {
            dialog = new Dialog(this, R.style.ActionSheetDialogStyleDialog);
            //????????????????????????
            inflate = LayoutInflater.from(this).inflate(R.layout.configlist_dibushow, null);
            //???????????????
            bt_adddilao = inflate.findViewById(R.id.bt_adddilao);
            bt_adddilao.setOnClickListener(this);
            iv_show_finish = inflate.findViewById(R.id.iv_show_finish);
            iv_show_finish.setOnClickListener(this);
            et_up_ping = inflate.findViewById(R.id.et_up_ping);//??????
            et_down_ping = inflate.findViewById(R.id.et_down_ping);//??????
            sp_tf = inflate.findViewById(R.id.sp_tf);//TDD FDD   ?????????
            et_plmn = inflate.findViewById(R.id.et_plmn);//plmn
            et_band = inflate.findViewById(R.id.et_band);//??????
            sp_yy = inflate.findViewById(R.id.sp_yy);//?????????   ?????????
            bt_canel = inflate.findViewById(R.id.bt_canel);
            bt_canel.setOnClickListener(this);
            List<String> list1 = new ArrayList<>();
            list1.add("??????");
            list1.add("??????");
            list1.add("??????");
            final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(context, R.layout.spinner_item
                    , list1);
            sp_yy.setAdapter(adapter1);
            sp_yy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    spinnerYY = adapter1.getItem(i);//?????????1???????????????
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });


            List<String> list2 = new ArrayList<>();
            list2.add("TDD");
            list2.add("FDD");
            final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context, R.layout.spinner_item
                    , list2);
            sp_tf.setAdapter(adapter2);
            sp_tf.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    spinnerTF = adapter2.getItem(i);//?????????1???????????????
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            dialog.setCanceledOnTouchOutside(false);
            //??????????????????Dialog
            dialog.setContentView(inflate);
            //????????????Activity???????????????
            Window dialogWindow = dialog.getWindow();
            //??????Dialog?????????????????????
            dialogWindow.setGravity(Gravity.BOTTOM);
            dialogWindow.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            //?????????????????????
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//       ????????????????????????
            dialogWindow.setAttributes(lp);
            dialog.show();//???????????????
        }

    }

    /**
     * Edit????????????
     */
    private void ViewisEmpty() {
        if (isnull()) return;
        //??????????????????????????? SQL
        Log.d("iiii", "ViewisEmpty: ");
        String s = et_down_ping.getText().toString();
        String sr = "";
        try {
            List<PinConfigBean> studentlist = dbManager.getStudent();
            if (studentlist.size() > 0 && studentlist != null) {
                for (int i = 0; i < studentlist.size(); i++) {
                    if (s.equals(studentlist.get(i).getDown() + "")) {
                        sr = "1";
                        break;
                    }

                }
                if (!TextUtils.isEmpty(sr)) {
                    ToastUtils.showToast("????????????");
                    return;
                } else {
                    exeSql();
                }
            } else {
//                exeSql();
            }

        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }


    }

    /**
     * ??????sql??????
     */
    private void exeSql() {

        savepin();
    }

    private void savepin() {
        PinConfigBean pinConfigBean = new PinConfigBean();
        pinConfigBean.setBand(Integer.parseInt(et_band.getText().toString()));
        pinConfigBean.setDown(Integer.parseInt(et_down_ping.getText().toString()));
        pinConfigBean.setPlmn(et_plmn.getText().toString());
        pinConfigBean.setType(0);
        pinConfigBean.setUp(Integer.parseInt(et_up_ping.getText().toString()));
        pinConfigBean.setTf(spinnerTF);
        pinConfigBean.setYy(spinnerYY);
        try {
            int i = dbManager.insertStudent(pinConfigBean);
            if (i == 1) {
                Log.d("nan???????????????", "===" + pinConfigBean);
//                Toast.makeText(this, "????????????", Toast.LENGTH_SHORT).show();
                ToastUtils.showToast("????????????");
                dialog.dismiss();
                //??????????????????
//                initData();
                setViewpagerData();
            } else {
//                Toast.makeText(this, "????????????", Toast.LENGTH_SHORT).show();
                ToastUtils.showToast("????????????");
            }
//
        } catch (java.sql.SQLException e) {

            e.printStackTrace();
        }
    }

    class MySetUpdate implements View.OnClickListener {
        private int type;
        private int id;
        PinConfigBean pinConfigBean;

        public MySetUpdate(PinConfigBean pinConfigBean, int id, int type) {
            this.pinConfigBean = pinConfigBean;
            this.id = id;
            this.type = type;
        }

        @Override
        public void onClick(View view) {
            if (isnull()) return;
            PinConfigBean pinConfigBeanss = new PinConfigBean();
            pinConfigBeanss.setUp(Integer.parseInt(et_up_ping.getText().toString()));
            pinConfigBeanss.setDown(Integer.parseInt(et_down_ping.getText().toString()));
            pinConfigBeanss.setTf(spinnerTF);
            pinConfigBeanss.setPlmn(et_plmn.getText().toString());
            pinConfigBeanss.setBand(Integer.parseInt(et_band.getText().toString()));
            pinConfigBeanss.setId(id);
            pinConfigBeanss.setType(type);
            pinConfigBeanss.setYy(spinnerYY);
            try {
                int i = dbManager.updateData(pinConfigBeanss);
                Log.d("nzq", "updateDataonClick: " + i);
//                queryStudent();
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
            dialog.dismiss();
        }
    }

    private boolean isnull() {
        if (TextUtils.isEmpty(et_up_ping.getText().toString())) {
            Toast.makeText(this, "????????????????????????", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (TextUtils.isEmpty(et_down_ping.getText().toString())) {
            Toast.makeText(this, "????????????????????????", Toast.LENGTH_SHORT).show();
            return true;
        }

//        if (TextUtils.isEmpty(et_tf.getText().toString())) {
//            Toast.makeText(this, "TDD/FDD????????????", Toast.LENGTH_SHORT).show();
//            return true;
//        }
        if (TextUtils.isEmpty(et_band.getText().toString())) {
            Toast.makeText(this, "??????????????????", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (TextUtils.isEmpty(et_plmn.getText().toString())) {
            Toast.makeText(this, "PLMN????????????", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}
