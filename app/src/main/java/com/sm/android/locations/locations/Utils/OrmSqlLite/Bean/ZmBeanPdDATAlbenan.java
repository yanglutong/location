package com.sm.android.locations.locations.Utils.OrmSqlLite.Bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by admin on 2020/4/8.
 */
@DatabaseTable(tableName = "zmbeanpddatalbenan")
public class ZmBeanPdDATAlbenan {
    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @DatabaseField
    private String time;
    @DatabaseField
    private String imsi;
    @DatabaseField
    private String sum;//分析类型
    @DatabaseField(generatedId = true) //generatedId = true 表示自增长的主键
    private int id;

    @Override
    public String toString() {
        return "ZmBeanPdDATAlbenan{" +
                "time='" + time + '\'' +
                ", imsi='" + imsi + '\'' +
                ", sum='" + sum + '\'' +
                ", id=" + id +
                '}';
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public List<LinshiBenjieguo> getList() {
//        return list;
//    }
//
//    public void setList(List<LinshiBenjieguo> list) {
//        this.list = list;
//    }
//
//    @DatabaseField
//    private List<LinshiBenjieguo> list;//分析类型
}
