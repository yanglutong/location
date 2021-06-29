package com.sm.android.locations.location.Utils.OrmSqlLite.Bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by admin on 2020/4/8.
 */
@DatabaseTable(tableName = "zmbeanbslbenan")
public class ZmBeanbslbenan {
    @DatabaseField
    private String imsi;

    @Override
    public String toString() {
        return "ZmBeanPdlbenan{" +
                "imsi='" + imsi + '\'' +
                ", time='" + time + '\'' +
                ", type='" + type + '\'' +
                ", id=" + id +
                '}';
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
    private String type;//分析类型
    @DatabaseField(generatedId = true) //generatedId = true 表示自增长的主键
    private int id;

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
