package com.sm.android.locations.location.zhenma;

public class LinshiBenjieguo {
    String IMSI;
    String num;

    @Override
    public String toString() {
        return "LinshiBenjieguo{" +
                "IMSI='" + IMSI + '\'' +
                ", num='" + num + '\'' +
                '}';
    }

    public String getIMSI() {
        return IMSI;
    }

    public void setIMSI(String IMSI) {
        this.IMSI = IMSI;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
