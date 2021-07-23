package com.sm.android.locations.location.initData.bean;

/**
 * @author: 小杨同志
 * @date: 2021/7/21
 */
public class YXSaoPBean {
    private int  PRI;
    private int RSRP;
    private String EARFCN;
    public YXSaoPBean(int PRI, int RSRP,String e) {
        this.PRI = PRI;
        this.RSRP = RSRP;
        this.EARFCN=e;
    }

    @Override
    public String toString() {
        return "YXSaoPBean{" +
                "PRI=" + PRI +
                ", RSRP=" + RSRP +
                ", EARFCN='" + EARFCN + '\'' +
                '}';
    }

    public String getEARFCN() {
        return EARFCN;
    }

    public void setEARFCN(String EARFCN) {
        this.EARFCN = EARFCN;
    }

    public int getPRI() {
        return PRI;
    }

    public void setPRI(int PRI) {
        this.PRI = PRI;
    }

    public int getRSRP() {
        return RSRP;
    }

    public void setRSRP(int RSRP) {
        this.RSRP = RSRP;
    }
}
