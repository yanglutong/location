package com.sm.android.locations.location.Utils.OrmSqlLite;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.AddPararBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.AddPararBeanWhite;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.AdminBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.Conmmunit01Bean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.GuijiViewBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.GuijiViewBeanjizhan;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.LogBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.LunxunBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.PinConfigBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.SaopinBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.TDDFDDzyBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.ZcBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.ZmBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.ZmBeanBsDATAlbenan;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.ZmBeanGK;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.ZmBeanPdDATAlbenan;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.ZmBeanPdlbenan;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.ZmBeanbslbenan;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.Nzqzmbeandw;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.ZmBeanlinshi;
import com.sm.android.locations.location.initData.dao.DeviceBean;

/**
 * Created by Administrator on 2018/3/22 0022.
 */

public class OrmHelper extends OrmLiteSqliteOpenHelper {
    public static final String DB_NAME = "16078.db";
    private static final int DB_VERSION = 1;
 public static    ConnectionSource connectionSources;
    public static  SQLiteDatabase databases;
    //????????????????????????DbHelper??????
    private static OrmHelper helper;

    public static OrmHelper getHelper(Context context) {
        if (helper == null) {
            helper = new OrmHelper(context);
        }
        return helper;
    }
    public OrmHelper(Context context) {

        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        connectionSources=connectionSource;
        databases=database;
        //??????,???Gson?????????????????????????????????????????????
        try {
            try {
                TableUtils.createTable(connectionSource, LunxunBean.class);//????????????


                TableUtils.createTable(connectionSource, ZmBean.class);//????????????
                TableUtils.createTable(connectionSource, ZmBeanGK.class);//???????????? ??????


                TableUtils.createTable(connectionSource, ZmBeanlinshi.class);//????????????
                TableUtils.createTable(connectionSource, ZmBeanPdlbenan.class);//????????????????????????
                TableUtils.createTable(connectionSource, ZmBeanPdDATAlbenan.class);//??????????????????????????????

                TableUtils.createTable(connectionSource, ZmBeanbslbenan.class);//????????????????????????
                TableUtils.createTable(connectionSource, ZmBeanBsDATAlbenan.class);//??????????????????????????????



                TableUtils.createTable(connectionSource, PinConfigBean.class);
                TableUtils.createTable(connectionSource, Conmmunit01Bean.class);
                TableUtils.createTable(connectionSource, GuijiViewBeanjizhan.class);
                TableUtils.createTable(connectionSource, GuijiViewBean.class);
                TableUtils.createTable(connectionSource, AddPararBean.class);
                TableUtils.createTable(connectionSource, AddPararBeanWhite.class);
                TableUtils.createTable(connectionSource, SaopinBean.class);//????????????
                TableUtils.createTable(connectionSource, Nzqzmbeandw.class);//??????????????????

                TableUtils.createTable(connectionSource, TDDFDDzyBean.class);//TDD FDD?????????
                TableUtils.createTable(connectionSource, ZcBean.class);//?????????
                TableUtils.createTable(connectionSource, AdminBean.class);//????????????
                TableUtils.createTable(connectionSource, LogBean.class);//??????
                TableUtils.createTable(connectionSource, DeviceBean.class);//??????


            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }
}
