package com.sm.android.locations.locations.Utils.OrmSqlLite.Bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by admin on 2020/4/8.
 */
@DatabaseTable(tableName="zmbeanlinshi")
public class ZmBeanlinshi {
    @DatabaseField
    private String imsi;

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getSb() {
        return sb;
    }

    public String getStartdatatime() {
        return Startdatatime;
    }

    public void setStartdatatime(String startdatatime) {
        Startdatatime = startdatatime;
    }

    public void setSb(String sb) {
        this.sb = sb;
    }

    public String getZb() {
        return zb;
    }

    public void setZb(String zb) {
        this.zb = zb;
    }

    public String getDatatime() {
        return datatime;
    }

    public void setDatatime(String datatime) {
        this.datatime = datatime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDown() {
        return down;
    }

    public void setDown(String down) {
        this.down = down;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @DatabaseField
    private String sb;

    public String getStoptime() {
        return Stoptime;
    }

    public void setStoptime(String stoptime) {
        Stoptime = stoptime;
    }

    @DatabaseField
    private String zb;
    @DatabaseField
    private String datatime;
    @DatabaseField
    private String Startdatatime;

    @Override
    public String toString() {
        return "ZmBeanlinshi{" +
                "imsi='" + imsi + '\'' +
                ", sb='" + sb + '\'' +
                ", zb='" + zb + '\'' +
                ", datatime='" + datatime + '\'' +
                ", Startdatatime='" + Startdatatime + '\'' +
                ", Stoptime='" + Stoptime + '\'' +
                ", time='" + time + '\'' +
                ", down='" + down + '\'' +
                ", check='" + check + '\'' +
                ", id=" + id +
                '}';
    }

    @DatabaseField
    private String Stoptime;

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    @DatabaseField
    private String time;
    @DatabaseField
    private String down;
    @DatabaseField
    private String check;
    @DatabaseField(generatedId = true) //generatedId = true 表示自增长的主键
    private int id;
}
