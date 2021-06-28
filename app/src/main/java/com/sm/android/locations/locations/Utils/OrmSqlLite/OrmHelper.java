package com.sm.android.locations.locations.Utils.OrmSqlLite;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.AddPararBean;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.AddPararBeanWhite;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.AdminBean;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.Conmmunit01Bean;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.GuijiViewBean;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.GuijiViewBeanjizhan;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.LogBean;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.LunxunBean;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.PinConfigBean;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.SaopinBean;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.TDDFDDzyBean;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.ZcBean;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.ZmBean;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.ZmBeanBsDATAlbenan;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.ZmBeanGK;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.ZmBeanPdDATAlbenan;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.ZmBeanPdlbenan;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.ZmBeanbslbenan;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.Nzqzmbeandw;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.ZmBeanlinshi;

/**
 * Created by Administrator on 2018/3/22 0022.
 */

public class OrmHelper extends OrmLiteSqliteOpenHelper {
    public static final String DB_NAME = "16078.db";
    private static final int DB_VERSION = 1;
 public static    ConnectionSource connectionSources;
    public static  SQLiteDatabase databases;
    //实现一个单例返回DbHelper实例
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
        //建表,和Gson类似，第二个参数即是业务实体类
        try {
            try {
                TableUtils.createTable(connectionSource, LunxunBean.class);//轮循列表


                TableUtils.createTable(connectionSource, ZmBean.class);//侦码列表
                TableUtils.createTable(connectionSource, ZmBeanGK.class);//侦码列表 管控


                TableUtils.createTable(connectionSource, ZmBeanlinshi.class);//侦码列表
                TableUtils.createTable(connectionSource, ZmBeanPdlbenan.class);//侦码碰撞分析列表
                TableUtils.createTable(connectionSource, ZmBeanPdDATAlbenan.class);//侦码碰撞结果分析列表

                TableUtils.createTable(connectionSource, ZmBeanbslbenan.class);//侦码碰撞分析列表
                TableUtils.createTable(connectionSource, ZmBeanBsDATAlbenan.class);//侦码碰撞结果分析列表



                TableUtils.createTable(connectionSource, PinConfigBean.class);
                TableUtils.createTable(connectionSource, Conmmunit01Bean.class);
                TableUtils.createTable(connectionSource, GuijiViewBeanjizhan.class);
                TableUtils.createTable(connectionSource, GuijiViewBean.class);
                TableUtils.createTable(connectionSource, AddPararBean.class);
                TableUtils.createTable(connectionSource, AddPararBeanWhite.class);
                TableUtils.createTable(connectionSource, SaopinBean.class);//扫频列表
                TableUtils.createTable(connectionSource, Nzqzmbeandw.class);//定位侦码列表

                TableUtils.createTable(connectionSource, TDDFDDzyBean.class);//TDD FDD增益值
                TableUtils.createTable(connectionSource, ZcBean.class);//注册码
                TableUtils.createTable(connectionSource, AdminBean.class);//人员管理
                TableUtils.createTable(connectionSource, LogBean.class);//日志


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
