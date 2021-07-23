package com.sm.android.locations.location.initData.dao;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "devicebean")
public class DeviceBean {
    @DatabaseField(generatedId = true) //generatedId = true 表示自增长的主键
    private int id;

    @DatabaseField
    private String et_cycle;
    @DatabaseField
    private String et_gl;
    @DatabaseField
    private String et_Zy;
    @DatabaseField
    private String et_START;
    @DatabaseField
    private String et_RANGE;
    @DatabaseField
    private String cycle;//抓号周期@DatabaseField
    @DatabaseField
    private String tac;//抓号周期@DatabaseField
    @DatabaseField
    private String pci;//抓号周期@DatabaseField
    @DatabaseField
    private String ci;//抓号周期@DatabaseField
    @DatabaseField
    private String et_Zyg;//抓号周期
     @DatabaseField
    private String et_Zyz;//抓号周期
     @DatabaseField
    private String et_Zyd;//抓号周期

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEt_cycle() {
        return et_cycle;
    }

    public void setEt_cycle(String et_cycle) {
        this.et_cycle = et_cycle;
    }

    public String getEt_gl() {
        return et_gl;
    }

    public void setEt_gl(String et_gl) {
        this.et_gl = et_gl;
    }

    public String getEt_Zy() {
        return et_Zy;
    }

    public void setEt_Zy(String et_Zy) {
        this.et_Zy = et_Zy;
    }

    public String getEt_START() {
        return et_START;
    }

    public void setEt_START(String et_START) {
        this.et_START = et_START;
    }

    public String getEt_RANGE() {
        return et_RANGE;
    }

    public void setEt_RANGE(String et_RANGE) {
        this.et_RANGE = et_RANGE;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getTac() {
        return tac;
    }

    public void setTac(String tac) {
        this.tac = tac;
    }

    public String getPci() {
        return pci;
    }

    public void setPci(String pci) {
        this.pci = pci;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getEt_Zyg() {
        return et_Zyg;
    }

    public void setEt_Zyg(String et_Zyg) {
        this.et_Zyg = et_Zyg;
    }

    public String getEt_Zyz() {
        return et_Zyz;
    }

    public void setEt_Zyz(String et_Zyz) {
        this.et_Zyz = et_Zyz;
    }

    public String getEt_Zyd() {
        return et_Zyd;
    }

    public void setEt_Zyd(String et_Zyd) {
        this.et_Zyd = et_Zyd;
    }

    @Override
    public String toString() {
        return "DeviceBean{" +
                "id=" + id +
                ", et_cycle='" + et_cycle + '\'' +
                ", et_gl='" + et_gl + '\'' +
                ", et_Zy='" + et_Zy + '\'' +
                ", et_START='" + et_START + '\'' +
                ", et_RANGE='" + et_RANGE + '\'' +
                ", cycle='" + cycle + '\'' +
                ", tac='" + tac + '\'' +
                ", pci='" + pci + '\'' +
                ", ci='" + ci + '\'' +
                ", et_Zyg='" + et_Zyg + '\'' +
                ", et_Zyz='" + et_Zyz + '\'' +
                ", et_Zyd='" + et_Zyd + '\'' +
                '}';
    }
}
