package com.sm.android.locations.locations.Utils.OrmSqlLite;

/**
 * Created by Administrator on 2018/3/22 0022.
 */


import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.LunxunBean;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2016/10/14.
 * 封装各种操作数据库的方法
 */
public class DBManagerLunxun {
    private static DBManagerLunxun dbManagerLunxun;
    private Dao dao;

    private DBManagerLunxun() {


    }


    public static DBManagerLunxun getInstance() {

        if (dbManagerLunxun == null) {

            dbManagerLunxun = new DBManagerLunxun();

        }

        return dbManagerLunxun;

    }



    /**
     * 在构造中获取数据库的操作类
     *
     * @param context
     * @throws SQLException
     */

    public DBManagerLunxun(Context context) throws SQLException {

        OrmHelper ormHelper = new OrmHelper(context);
        //        SQLiteDatabase readableDatabase = ormHelper.getReadableDatabase(); //可以获取一个原生的数据库
        //Dao相当于原生的SQLiteDatabase，可以操作数据库,一个Dao只能操作一张表
        dao = ormHelper.getDao(LunxunBean.class);
    }

    /**
     * 插入数据
     *
     * @param
     * @thro
     */
    public int insertStudent(LunxunBean LunxunBean) throws SQLException {
        //在数据库中创建一条记录，作用与SQLiteDatabase.insert一样
        int i = dao.create(LunxunBean);
        return i;
    }

    /**
     * 批量插入
     * 不能使用循环一个一个的插入，因为这样会一直打开数据库、插入数据、
     * 关闭数据库
     *
     * @param
     * @throws SQLException
     */
    public void batchInsert(List<LunxunBean> LunxunBeans) throws SQLException {
        dao.create(LunxunBeans);
    }

    /**
     * 查询数据
     *
     * @return
     * @throws SQLException
     */
    public List<LunxunBean> getStudent() throws SQLException {
        List<LunxunBean> list = dao.queryForAll();
        return list;
    }

    public LunxunBean forid(int id) throws SQLException {
        LunxunBean LunxunBean = (com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.LunxunBean) dao.queryForId(id);
        return LunxunBean;
    }

    /**
     * 查询某个数据
     *
     * @return
     * @throws SQLException
     */
    public List<LunxunBean> queryGuanyu() throws SQLException {
        //Eq是equals的缩写
        //方法1
        //List<Student> list = dao.queryForEq("name", "张飞");

        //方法2
        QueryBuilder queryBuilder = dao.queryBuilder();
//        queryBuilder.offset(); //偏移量
//        queryBuilder.limit(8l); //最多几行  offset + limit 做分页
//        queryBuilder.orderBy("age",true);
        queryBuilder.where().eq("name", "关羽"); //多条件查询
        List<LunxunBean> query = queryBuilder.query();//此方法相当于build，提交设置
        return query;
    }

    /**
     * 查询下旬频点对应的数据
     *
     * @param s
     * @return
     * @throws SQLException
     */
    public List<LunxunBean> queryData(int s) throws SQLException {
        List list = dao.queryBuilder().where().eq("down", s).query();
        return list;
    }

    /**
     * 删除数据
     *
     * @param LunxunBean
     * @throws SQLException
     */
    public int deleteStudent(LunxunBean LunxunBean) throws SQLException {
        //只看id
        int delete = dao.delete(LunxunBean);
        return delete;
    }

    /**
     * 删除指定数据
     *
     * @throws SQLException
     */
    public void deleteGuanyu() throws SQLException {
        DeleteBuilder deleteBuilder = dao.deleteBuilder();
        deleteBuilder.where().eq("name", "关羽");
        deleteBuilder.delete();
    }

    /**
     * 修改数据
     *
     * @param LunxunBean
     * @throws SQLException
     */
    public void updateStudentfalse(LunxunBean LunxunBean) throws SQLException {
        LunxunBean.setType(0);
        dao.update(LunxunBean);
    }

    public void updateStudenttrue(LunxunBean LunxunBean) throws SQLException {
        LunxunBean.setType(1);
        dao.update(LunxunBean);
    }

    //checkBox选中事件
    public int updatecheckBox(LunxunBean LunxunBean) throws SQLException {
        LunxunBean.setType(1);
        int update = dao.update(LunxunBean);
        return update;
    }

    //checkBox选中事件
    public int updatecheckFlaseBox(LunxunBean LunxunBean) throws SQLException {
        LunxunBean.setType(0);
        int update = dao.update(LunxunBean);
        return update;
    }

    //checkBox选中事件
    public int updateData(LunxunBean LunxunBean) throws SQLException {
        int update = dao.update(LunxunBean);
        return update;
    }

    /**
     * 修改指定数据
     *
     * @throws SQLException
     */
    public void updateGuanyu() throws SQLException {
        UpdateBuilder updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq("name", "关羽");
        updateBuilder.updateColumnValue("sex", "女");
        updateBuilder.update();
    }
}
