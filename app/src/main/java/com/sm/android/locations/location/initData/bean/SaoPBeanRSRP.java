package com.sm.android.locations.location.initData.bean;

/**
 * @author: 小杨同志
 * @date: 2021/7/21
 */
public class SaoPBeanRSRP {
    private String EARFCN;
    private String RSRP;
    private String tac;
    private String pci;
    private String cid;

    @Override
    public String toString() {
        return "SaoPBeanRSRP{" +
                "EARFCN='" + EARFCN + '\'' +
                ", RSRP='" + RSRP + '\'' +
                ", tac='" + tac + '\'' +
                ", pci='" + pci + '\'' +
                ", cid='" + cid + '\'' +
                '}';
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

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
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
