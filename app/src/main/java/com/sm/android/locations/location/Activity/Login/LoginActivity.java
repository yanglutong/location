package com.sm.android.locations.location.Activity.Login;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;

import android.text.InputType;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import com.blankj.utilcode.util.AppUtils;
import com.sm.android.locations.location.Activity.Adminctivity;
import com.sm.android.locations.location.Activity.ConfigsActivity;
import com.sm.android.locations.location.Base.BaseActivity;
import com.sm.android.locations.location.R;
import com.sm.android.locations.location.Utils.GPS.GpsUtil;
import com.sm.android.locations.location.Utils.GPSLocationUtil;
import com.sm.android.locations.location.Utils.LoginUtils.LoginUtils;
import com.sm.android.locations.location.Utils.MyUtils;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.AdminBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.LogBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.ZcBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerAdmin;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerLog;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerZX;
import com.sm.android.locations.location.Utils.ToastUtils;
import com.sm.android.locations.location.sos.SOSActivity;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.sm.android.locations.location.App.App.context;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText et_user, et_pwd;
    private ImageView iv_show;
    private Button bt_login;
    private boolean showaBoolean = false;
    private TextView tv_version;
    private String sb1 = "";
    boolean dateFlag = false;
    GPSLocationUtil gUtil;
    LocationManager locationManager;
    Location location;
//    @SuppressLint("HandlerLeak")
//    private Handler handler = new Handler() {
//        @RequiresApi(api = Build.VERSION_CODES.N)
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what) {
//                case 100120: //??????1?????????????????????
//                    String zt1 = msg.getData().getString("zt1");
//                    if (!TextUtils.isEmpty(zt1)) {
//                        if (zt1.equals("0")) {
////                        tv1_type.setText("????????????: " + "?????????...");
//
//                            sb1 = "?????????...";
//                            break;
//                        }
//                        if (zt1.equals("1")) {
//
//                            sb1 = "????????????";
//
//                            break;
//                        }
//                        if (zt1.equals("2")) {
//                            //
////                            ??????1??????
//                            sb1 = "??????";
////
//                            System.out.println("??????");
//                            if (dateFlag == false) {
//                                getDate(IP1);
//                                System.out.println("?????????");
//                                dateFlag = true;
//                            }
//                            break;
//                        }
//                        if (zt1.equals("3")) {
////                            tv1_type.setText("????????????: " + "?????????????????????");//????????????
//                            sb1 = "?????????????????????";
//                            break;
//                        }
//                        if (zt1.equals("4")) {
////                            tv1_type.setText("????????????: " + "???????????????");//????????????
//                            sb1 = "???????????????";
//                            break;
//                        }
//                        if (zt1.equals("5")) {
//                            sb1 = "?????????";
//                            break;
//                        }
//                    }
//            }
//        }
//    };

    @Override
    protected void initQx() {
        getPermissions();
    }

    @Override
    protected void init() {
//        getPermissions();
//        setUser_pwd();
        gUtil = GPSLocationUtil.getInstance(LoginActivity.this);
//        ifWifi();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//        updateView(location);
//        String time = getTime(location);
        try {
            DBManagerZX dbManagerZX = new DBManagerZX(LoginActivity.this);
            ZcBean forid = dbManagerZX.forid(1);
            if (!TextUtils.isEmpty(forid.getDate())) {
                tv_version.setText("????????????:" + forid.getDate());
            } else {
                tv_version.setText("????????????:");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 0, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                updateView(location);
//                String time = getTime(location);
//                Log.d("nzqonLocationChanged", "onLocationChanged: " + time);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        });


    }

    private void openGPS() {
        new AlertDialog.Builder(LoginActivity.this)
                .setIcon(android.R.drawable.ic_dialog_info)
                .setTitle("?????????GPS")
                .setMessage("?????????")
                .setNegativeButton("??????", null)
                .setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivityForResult(intent, 887);
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 887:
                //??????GPS???????????????????????????
//                startLocation();
                String time = getTime(location);
                ToastUtils.showToast(time);
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void updateView(Location location) {
        if (location != null) {
            Log.d("TAGlocation", "updateView: " + "??????");
            StringBuffer stringBuffer = new StringBuffer();
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                stringBuffer.append(simpleDateFormat.format(new Date(location.getTime())));
                stringBuffer.append("\n");

            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d("TAGlocation", "updateView: " + "??????" + stringBuffer.toString());
//            tv_version.setText(stringBuffer.toString());
        } else {
            Log.d("TAGlocation", "updateView: " + "???");
        }
    }

    //??????????????????
    private String getTime(Location location) {
        if (location != null) {
            StringBuffer stringBuffer = new StringBuffer();
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                stringBuffer.append(simpleDateFormat.format(new Date(location.getTime())));
//                stringBuffer.append("\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return stringBuffer.toString();
        } else {
            return "";
        }
//        return "";
    }


    @Override
    protected void findViews() {
        et_user = findViewById(R.id.et_user);
        et_pwd = findViewById(R.id.et_pwd);
        iv_show = findViewById(R.id.iv_show);
        iv_show.setOnClickListener(this);
        bt_login = findViewById(R.id.bt_login);
        bt_login.setOnClickListener(this);
        tv_version = findViewById(R.id.tv_version);
    }

    @Override
    protected int getView() {
        return R.layout.activity_login;
    }

    private void setUser_pwd() {
        SharedPreferences userSettings = getSharedPreferences("setting", 0);
        String namesp = userSettings.getString("name", "");
        String pswsp = userSettings.getString("pwd", "");
//        et_user.setText(namesp);
//        et_pwd.setText(pswsp);
        String appVersionName = AppUtils.getAppVersionName();
//        tv_version.setText("???????????????:" + appVersionName + "");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_show:
//                Toast.makeText(LoginActivity.this, "??????????????????????????????", Toast.LENGTH_LONG).show();
                if (showaBoolean == false) {
                    showaBoolean = true;
                    et_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    et_pwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    Log.e("showaBoolean", "onClick: " + showaBoolean);
                } else if (showaBoolean == true) {
                    showaBoolean = false;
                    et_pwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    Log.e("showaBoolean", "onClick: " + showaBoolean);
                }

                break;
            case R.id.bt_login:
//


                Logins();
//
                break;
        }
    }

    @SuppressLint("MissingPermission")
    private void Logins() {
//        KeyboardUtils.hideSoftInput(LoginActivity.this);
        final String number = et_user.getText().toString();
        final String pwd = et_pwd.getText().toString();
        if(number.equals("")){
            ToastUtils.showToast("??????????????????");
            return;
        }if(pwd.equals("")){
            ToastUtils.showToast("???????????????");
            return;
        }
        if (!TextUtils.isEmpty(number) && !TextUtils.isEmpty(pwd)) {
            if (number.equals("smsbgly") && pwd.equals("smsbgly")) {//????????????
                startActivity(new Intent(LoginActivity.this, ConfigsActivity.class));
//                finish();
            } else if (number.equals("admin") && pwd.equals("admin")) {//????????????
//                LoadingTrue("????????????");

                //????????????????????????
                final SharedPreferences preferences = context.getSharedPreferences(
                        "SHARE_APP_TAGZC", 0);
                Boolean isFirst = preferences.getBoolean("FIRSTStartzc", true);
                if (isFirst) {//?????????
                    final Dialog dialog = new Dialog(LoginActivity.this, R.style.menuDialogStyleDialogStyle);
                    View inflate = LayoutInflater.from(LoginActivity.this).inflate(R.layout.dialog_item_title_zc, null);
                    TextView tv_title = inflate.findViewById(R.id.tv_title);
                    tv_title.setText("???????????????");
                    final EditText et_jh = inflate.findViewById(R.id.et_jh);

                    Button bt_confirm = inflate.findViewById(R.id.bt_confirm);
                    bt_confirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (et_jh.getText().toString().equals(MyUtils.getZC())) {
                                preferences.edit().putBoolean("FIRSTStartzc", false).commit();

                            SharedPreferences userSettings = getSharedPreferences("setting", 0);
                                SharedPreferences.Editor editor = userSettings.edit();
                                editor.putString("name", number);
                                editor.putString("pwd", pwd);
                                editor.commit();
                                startActivity(new Intent(LoginActivity.this, Adminctivity.class));
                                LoadingFalse();

                                dialog.dismiss();
                                dialog.cancel();

                            } else {
                                ToastUtils.showToast("???????????????");
                            }

                        }
                    });
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
                } else {
                    SharedPreferences userSettings = getSharedPreferences("setting", 0);
                    SharedPreferences.Editor editor = userSettings.edit();
                    editor.putString("name", number);
                    editor.putString("pwd", pwd);
                    editor.commit();
                    startActivity(new Intent(LoginActivity.this, Adminctivity.class));
                    LoadingFalse();
                }


//                finish();
            } else {//???????????????
                LoadingFalse();
                if (GpsUtil.isOPen(LoginActivity.this)) {//GPS????????????
                    try {
                        DBManagerAdmin dbManagerAdmin = new DBManagerAdmin(LoginActivity.this);
                        List<AdminBean> adminBeans = dbManagerAdmin.foridName(number);
                        if (adminBeans.size() > 0) {
                            AdminBean adminBean = adminBeans.get(0);
                            Log.d("nzqTAG", "Logins: " + adminBean);
                            if (adminBean.getPwd().equals(pwd)) {//?????? ????????????
                                if (adminBean.getType() == 1) {
//                                LoadingTrue("????????????");
//
                                    //????????????

                                    DBManagerZX zx = new DBManagerZX(LoginActivity.this);
                                    ZcBean forid = zx.forid(1);
                                    if (!TextUtils.isEmpty(forid.getDate())) {
                                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                        String lastdate = forid.getDate();//????????????
                                        ZcBean forid2 = zx.forid(2);
                                        String nowDate = forid2.getDate();
                                        if (TextUtils.isEmpty(nowDate)) {
                                            ToastUtils.showToast("??????????????????");
                                            return;
                                        }
                                        String nowDateS = simpleDateFormat.format(new Date());//??????????????????
                                        Date date1 = null;
                                        Date date2 = null;
                                        Date date3 = null;
                                        try {
                                            date1 = simpleDateFormat.parse(lastdate);
                                            date2 = simpleDateFormat.parse(nowDate);
                                            date3 = simpleDateFormat.parse(nowDateS);
                                        } catch (Exception e) {

                                        }
                                        if (date1.getTime() >= date2.getTime() && date1.getTime() >= date3.getTime()) {

                                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                                            if (!TextUtils.isEmpty(getTime(location))) {

                                                String time = getTime(location);
                                                try {
                                                    date2 = simpleDateFormat.parse(time);
                                                } catch (ParseException e) {
                                                    e.printStackTrace();
                                                }
                                                if (date1.getTime() >= date2.getTime()) {
                                                    ZcBean zcBean = new ZcBean();
                                                    zcBean.setId(2);
                                                    zcBean.setDate(time);
                                                    zx.updateConmmunit01Bean(zcBean);

//                                                    ToastUtils.showToast("GPS????????????");
                                                    SharedPreferences userSettings = getSharedPreferences("setting", 0);
                                                    SharedPreferences.Editor editor = userSettings.edit();
                                                    editor.putString("name", number);
                                                    editor.putString("pwd", pwd);
                                                    int id = adminBean.getId();
                                                    editor.putString("id", id + "");
                                                    Log.e("myid", "Logins: " + adminBean.getId());
                                                    editor.commit();

                                                    //????????????
                                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                                    DBManagerLog dbManagerLog = new DBManagerLog(LoginActivity.this);
                                                    LogBean logBean = new LogBean();
                                                    logBean.setAssociated(id + "");//??????ID
                                                    logBean.setEvent(LoginUtils.setBase64("??????"));//????????????
                                                    String format = sdf.format(new Date());//????????????
                                                    logBean.setDatetime(LoginUtils.setBase64(format));
                                                    dbManagerLog.insertConmmunit01Bean(logBean);
                                                    //?????????
//                                                startActivity(new Intent(LoginActivity.this, NewMainActivity.class));
                                                    //??????
                                                    startActivity(new Intent(LoginActivity.this, SOSActivity.class));
                                                    finish();
//                                                    ToastUtils.showToast("11");
                                                } else {
                                                    ToastUtils.showToast("???????????????");
                                                }
                                            } else {
                                                SharedPreferences userSettings = getSharedPreferences("setting", 0);
                                                SharedPreferences.Editor editor = userSettings.edit();
                                                editor.putString("name", number);
                                                editor.putString("pwd", pwd);
                                                int id = adminBean.getId();
                                                editor.putString("id", id + "");
                                                editor.commit();
                                                //????????????
                                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                                DBManagerLog dbManagerLog = new DBManagerLog(LoginActivity.this);
                                                LogBean logBean = new LogBean();

                                                logBean.setAssociated(id + "");//??????ID
                                                logBean.setEvent(LoginUtils.setBase64("??????"));//????????????
                                                String format = sdf.format(new Date());//????????????
                                                logBean.setDatetime(LoginUtils.setBase64(format));
                                                dbManagerLog.insertConmmunit01Bean(logBean);
                                                //?????????
//                                                startActivity(new Intent(LoginActivity.this, NewMainActivity.class));
//                                                ??????
                                                startActivity(new Intent(LoginActivity.this, SOSActivity.class));

                                                finish();

                                                //
                                            }


//                                            ToastUtils.showToast("????????????");
                                        } else {
                                            ToastUtils.showToast("???????????????");
                                        }

                                    } else {

                                        ToastUtils.showToast("??????????????????");
                                    }

                                } else {
                                    ToastUtils.showToast("???????????????");
//                                LoadingTrue("????????????");
                                    SharedPreferences userSettings = getSharedPreferences("setting", 0);
                                    SharedPreferences.Editor editor = userSettings.edit();
                                    editor.putString("name", number);
                                    editor.putString("pwd", pwd);
                                    editor.commit();
                                }
                            } else {
                                ToastUtils.showToast("????????????????????????");
//                            LoadingTrue("????????????");
                                SharedPreferences userSettings = getSharedPreferences("setting", 0);
                                SharedPreferences.Editor editor = userSettings.edit();
                                editor.putString("name", number);
                                editor.putString("pwd", pwd);
                                editor.commit();
                            }
                        } else {
                            ToastUtils.showToast("???????????????");
//                        LoadingTrue("????????????");
                            SharedPreferences userSettings = getSharedPreferences("setting", 0);
                            SharedPreferences.Editor editor = userSettings.edit();
                            editor.putString("name", number);
                            editor.putString("pwd", pwd);
                            editor.commit();
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    ToastUtils.showToast("????????????GPS");
                }
            }
//
        } else {//???????????????????????????

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }
}
