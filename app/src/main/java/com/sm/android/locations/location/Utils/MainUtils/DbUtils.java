package com.sm.android.locations.location.Utils.MainUtils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.AddPararBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.AddPararBeanWhite;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.Conmmunit01Bean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.LunxunBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.PinConfigBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.SaopinBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.TDDFDDzyBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.ZcBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManager01;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerAddParam;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerAddParamWhite;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerLunxun;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerPinConfig;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerZX;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerZY;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagersaopin;
import com.sm.android.locations.location.Utils.ToastUtils;

import java.sql.SQLException;

/**
 * Created by admin on 2019/12/9.
 */

public class DbUtils {
    public static void insertDB(Context context, int band, int down, String plmn, int type, int up, String tf, String yy) {
        DBManagerPinConfig dbManager = null;
        try {
            dbManager = new DBManagerPinConfig(context);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PinConfigBean pinConfigBean = new PinConfigBean();
        pinConfigBean.setBand(band); //int
        pinConfigBean.setDown(down); //int
        pinConfigBean.setPlmn(plmn);//string
        pinConfigBean.setType(type);//int
        pinConfigBean.setUp(up); //int
        pinConfigBean.setTf(tf);//String
        pinConfigBean.setYy(yy);//String
        try {
            int i = dbManager.insertStudent(pinConfigBean);
            if (i == 1) {
                Log.d("nan保存的数据", "===" + pinConfigBean);
//                Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
//                ToastUtils.showToast("添加成功");
//                dialog.dismiss();
//                //查询所以数据
//                initData();
            } else {
//                Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();
//                ToastUtils.showToast("添加失败");
            }
//
        } catch (java.sql.SQLException e) {

            e.printStackTrace();
        }

    }
    /**
     * 轮循设置数据
     *
     * @param context
     * @param down
     * @param up
     * @param typeSP
     */
    public static void insertDBLunxun(Context context, int down, int up, int typeSP) {
        DBManagerLunxun dbManagersaopin = null;
        try {
            dbManagersaopin = new DBManagerLunxun(context);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LunxunBean saopinBean = new LunxunBean();
//        saopinBean.setBand(Integer.parseInt(et_band.getText().toString()));
        saopinBean.setDown(down);
//        saopinBean.setPlmn(et_plmn.getText().toString());
        saopinBean.setType(1);
        saopinBean.setUp(up);
//        saopinBean.setTf(spinnerTF);
        if (typeSP == 1) {
            saopinBean.setTf("TDD");
            saopinBean.setYy("移动TDD");
        }
        if (typeSP == 2) {
            saopinBean.setTf("FDD");
            saopinBean.setYy("移动FDD");
        }
        if (typeSP == 3) {
            saopinBean.setTf("FDD");
            saopinBean.setYy("联通");
        }
        if (typeSP == 4) {
            saopinBean.setTf("FDD");
            saopinBean.setYy("电信");
        }
        try {
            int i = dbManagersaopin.insertStudent(saopinBean);
            if (i == 1) {
                Log.d("nan保存的数据", "===" + saopinBean);
//                Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
//                ToastUtils.showToast("添加成功");

            } else {
//                Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();
                ToastUtils.showToast("添加失败");
            }
//
        } catch (java.sql.SQLException e) {

            e.printStackTrace();
        }

    }
    /**
     * 数据扫频数据
     *
     * @param context
     * @param down
     * @param up
     * @param typeSP
     */
    public static void insertDBSaopin(Context context, int down, int up, int typeSP) {
        DBManagersaopin dbManagersaopin = null;
        try {
            dbManagersaopin = new DBManagersaopin(context);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        SaopinBean saopinBean = new SaopinBean();
//        saopinBean.setBand(Integer.parseInt(et_band.getText().toString()));
        saopinBean.setDown(down);
//        saopinBean.setPlmn(et_plmn.getText().toString());
        saopinBean.setType(1);
        saopinBean.setUp(up);
//        saopinBean.setTf(spinnerTF);
        if (typeSP == 1) {
            saopinBean.setTf("TDD");
            saopinBean.setYy("移动TDD");
        }
        if (typeSP == 2) {
            saopinBean.setTf("FDD");
            saopinBean.setYy("移动FDD");
        }
        if (typeSP == 3) {
            saopinBean.setTf("FDD");
            saopinBean.setYy("联通");
        }
        if (typeSP == 4) {
            saopinBean.setTf("FDD");
            saopinBean.setYy("电信");
        }
        try {
            int i = dbManagersaopin.insertStudent(saopinBean);
            if (i == 1) {
                Log.d("nan保存的数据", "===" + saopinBean);
//                Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
//                ToastUtils.showToast("添加成功");

            } else {
//                Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();
                ToastUtils.showToast("添加失败");
            }
//
        } catch (java.sql.SQLException e) {

            e.printStackTrace();
        }

    }

    /**
     * 初始化小区数据
     *
     * @param context
     */
    public static void xiaoqu(Context context) {
        DBManager01 dbManager01;
        try {
            dbManager01 = new DBManager01(context);
            Conmmunit01Bean forid = dbManager01.forid(1);
            if (forid == null) {
                Log.e("nzqforid", "等于nullinitData: " + forid);
                Conmmunit01Bean conmmunit01Bean = new Conmmunit01Bean();
                conmmunit01Bean.setCid("");
                conmmunit01Bean.setEnodebpmax("");
                conmmunit01Bean.setUepmax("");
                conmmunit01Bean.setTac("1111");
                conmmunit01Bean.setPci("111");
                conmmunit01Bean.setCid("1111");
                conmmunit01Bean.setType("0");
                conmmunit01Bean.setCycle("5");
                conmmunit01Bean.setTime("60");
                dbManager01.insertConmmunit01Bean(conmmunit01Bean);
            }

            Conmmunit01Bean forid2 = dbManager01.forid(2);
            if (forid2 == null) {
                Log.e("nzqforid", "等于nullinitData: " + forid);
                Conmmunit01Bean conmmunit01Bean = new Conmmunit01Bean();
                conmmunit01Bean.setCid("1111");
                conmmunit01Bean.setEnodebpmax("");
                conmmunit01Bean.setUepmax("");
                conmmunit01Bean.setTac("2222");
                conmmunit01Bean.setPci("112");
                conmmunit01Bean.setCid("2222");
                conmmunit01Bean.setType("0");
                conmmunit01Bean.setCycle("5");
                conmmunit01Bean.setTime("60");
                dbManager01.insertConmmunit01Bean(conmmunit01Bean);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化APP安装  此为测试数据
     *
     * @param context
     * @param imsi
     * @param name
     * @param phone
     * @param yy
     * @param Checkboix
     */
    public static void TestIMSI(Context context, String imsi, String name, String phone, String yy, boolean Checkboix) {
        DBManagerAddParam dbManagerAddParam = null;
        try {
            dbManagerAddParam = new DBManagerAddParam(context);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        AddPararBean addPararBean = new AddPararBean();
//        addPararBean.setId(0);
        addPararBean.setImsi(imsi + "");
        addPararBean.setName(name + "");
        addPararBean.setPhone(phone + "");
        addPararBean.setYy(yy + "");
        addPararBean.setCheckbox(Checkboix);
        try {
            int i = dbManagerAddParam.insertAddPararBean(addPararBean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 初始化APP安装  此为测试数据
     *
     * @param context
     * @param imsi
     * @param name
     * @param phone
     * @param yy
     * @param Checkboix
     */
    public static void TestIMSIWhite(Context context, String imsi, String name, String phone, String yy, boolean Checkboix) {
        DBManagerAddParamWhite dbManagerAddParam = null;
        try {
            dbManagerAddParam = new DBManagerAddParamWhite(context);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        AddPararBeanWhite addPararBean = new AddPararBeanWhite();
//        addPararBean.setId(0);
        addPararBean.setImsi(imsi + "");
        addPararBean.setName(name + "");
        addPararBean.setPhone(phone + "");
        addPararBean.setYy(yy + "");
        addPararBean.setCheckbox(Checkboix);
        try {
            int i = dbManagerAddParam.insertAddPararBean(addPararBean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //增益设置设置查询
    public static void ZY(Context context, String TDDd, String TDDz, String TDDg, int id, String FDDd, String FDDz, String FDDg) {
        DBManagerZY zy = null;
        try {
            zy = new DBManagerZY(context);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        TDDFDDzyBean tddfdDzyBean = new TDDFDDzyBean();
        tddfdDzyBean.setId(id);
        tddfdDzyBean.setTDDzyd(TDDd);
        tddfdDzyBean.setTDDzyz(TDDz);
        tddfdDzyBean.setTDDzyg(TDDg);
        tddfdDzyBean.setFDDzyd(FDDd);
        tddfdDzyBean.setFDDzyz(FDDz);
        tddfdDzyBean.setFDDzyg(FDDg);
        try {
            zy.insertAddZmBean(tddfdDzyBean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //注册码
    public static void zc(Context context) {
        DBManagerZX dbManagerZX = null;
        try {
            dbManagerZX = new DBManagerZX(context);
            ZcBean zcBean = new ZcBean();
            zcBean.setId(1);
            zcBean.setDate("");
            dbManagerZX.insertConmmunit01Bean(zcBean);
            ZcBean zcBean2 = new ZcBean();
            zcBean2.setId(2);
            zcBean2.setDate("");
            dbManagerZX.insertConmmunit01Bean(zcBean2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static int getTime(Context context,int device){
        Conmmunit01Bean forid = null;
        DBManager01 dbManager01= null;
        try {
            dbManager01 = new DBManager01(context);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            forid = dbManager01.forid(device);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String time = forid.getTime();
        if (!TextUtils.isEmpty(time)){
            return Integer.parseInt(time);
        }else {
            return 60;
        }
    }
}
