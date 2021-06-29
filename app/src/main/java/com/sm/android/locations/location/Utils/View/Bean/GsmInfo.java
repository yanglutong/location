package com.sm.android.locations.location.Utils.View.Bean;

/**
 * Created by admin on 2019/12/24.
 */

public class GsmInfo {
    private int Mcc;
    private int Mnc;
    private int Lac;
    private int Cid;
    private String plmn;
    private String pci;

    @Override
    public String toString() {
        return "GsmInfo{" +
                "Mcc=" + Mcc +
                ", Mnc=" + Mnc +
                ", Lac=" + Lac +
                ", Cid=" + Cid +
                ", plmn='" + plmn + '\'' +
                ", pci='" + pci + '\'' +
                ", Rssi=" + Rssi +
                ", Rsrp=" + Rsrp +
                ", Rsrq=" + Rsrq +
                ", ear=" + ear +
                '}';
    }

    public String getPci() {
        return pci;
    }

    public void setPci(String pci) {
        this.pci = pci;
    }

    public String getPlmn() {
        return plmn;
    }

    public void setPlmn(String plmn) {
        this.plmn = plmn;
    }

    public int getRsrp() {
        return Rsrp;
    }

    public void setRsrp(int rsrp) {
        Rsrp = rsrp;
    }

    public int getRsrq() {
        return Rsrq;
    }

    public void setRsrq(int rsrq) {
        Rsrq = rsrq;
    }

    private int Rssi;
    private int Rsrp;
    private int Rsrq;

    private int ear;

    public int getMcc() {
        return Mcc;
    }

    public void setMcc(int mcc) {
        Mcc = mcc;
    }

    public int getMnc() {
        return Mnc;
    }

    public void setMnc(int mnc) {
        Mnc = mnc;
    }

    public int getLac() {
        return Lac;
    }

    public void setLac(int lac) {
        Lac = lac;
    }

    public int getCid() {
        return Cid;
    }

    public void setCid(int cid) {
        Cid = cid;
    }

    public int getRssi() {
        return Rssi;
    }

    public void setRssi(int rssi) {
        Rssi = rssi;
    }

    public int getEar() {
        return ear;
    }

    public void setEar(int ear) {
        this.ear = ear;
    }
}
