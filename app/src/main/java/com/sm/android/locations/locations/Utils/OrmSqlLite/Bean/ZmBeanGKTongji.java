package com.sm.android.locations.locations.Utils.OrmSqlLite.Bean;

import android.widget.CheckBox;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by admin on 2020/4/8.
 */

public class ZmBeanGKTongji {
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


    private String sb;

    public String getMaintype() {
        return maintype;
    }

    public void setMaintype(String maintype) {
        this.maintype = maintype;
    }


    private String zb;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    private String datatime;

    @Override
    public String toString() {
        return "ZmBeanGKTongji{" +
                "imsi='" + imsi + '\'' +
                ", sb='" + sb + '\'' +
                ", zb='" + zb + '\'' +
                ", datatime='" + datatime + '\'' +
                ", time='" + time + '\'' +
                ", down='" + down + '\'' +
                ", maintype='" + maintype + '\'' +
                ", id=" + id +
                ", num='" + num + '\'' +
                ", check=" + check +
                '}';
    }

    private String time;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    private String down;

    private String maintype;

    private int id;

    private String num;
    private boolean check=false;

}
