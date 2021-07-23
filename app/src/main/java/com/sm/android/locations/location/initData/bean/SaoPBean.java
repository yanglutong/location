package com.sm.android.locations.location.initData.bean;

/**
 * @author: 小杨同志
 * @date: 2021/7/21
 */
public class SaoPBean {
    private String EARFCN;
    private String PRI;

    public SaoPBean() {
    }

    public SaoPBean(String EARFCN, String PRI) {
        this.EARFCN = EARFCN;
        this.PRI = PRI;
    }

    @Override
    public String toString() {
        return "SaoPBean{" +
                "EARFCN='" + EARFCN + '\'' +
                ", PRI='" + PRI + '\'' +
                '}';
    }

    public String getEARFCN() {
        return EARFCN;
    }

    public void setEARFCN(String EARFCN) {
        this.EARFCN = EARFCN;
    }

    public String getPRI() {
        return PRI;
    }

    public void setPRI(String PRI) {
        this.PRI = PRI;
    }
}
