package com.sm.android.locations.location.initData.bean;

/**
 * @author: 小杨同志
 * @date: 2021/7/21
 */
public class SaoPBeanRSRP {
    private String EARFCN;
    private String RSRP;

    @Override
    public String toString() {
        return "SaoPBeanRSRP{" +
                "EARFCN='" + EARFCN + '\'' +
                ", RSRP='" + RSRP + '\'' +
                '}';
    }

    public String getEARFCN() {
        return EARFCN;
    }

    public void setEARFCN(String EARFCN) {
        this.EARFCN = EARFCN;
    }

    public String getRSRP() {
        return RSRP;
    }

    public void setRSRP(String RSRP) {
        this.RSRP = RSRP;
    }

    public SaoPBeanRSRP(String EARFCN, String RSRP) {
        this.EARFCN = EARFCN;
        this.RSRP = RSRP;
    }

    public SaoPBeanRSRP() {
    }
}
