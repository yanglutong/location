package com.sm.android.locations.location.Utils.OrmSqlLite.Bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "zcbean")
public class ZcBean {
    @DatabaseField(generatedId = true) //generatedId = true 表示自增长的主键
    private int id;

    @DatabaseField
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCheck() {
        return check;
    }

    @Override
    public String toString() {
        return "ZcBean{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", check='" + check + '\'' +
                '}';
    }

    public void setCheck(String check) {
        this.check = check;
    }

    @DatabaseField
    private String check;
}
