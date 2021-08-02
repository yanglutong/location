package com.sm.android.locations.location.initData.bean;

/**
 * @author: 小杨同志
 * @date: 2021/7/21
 */
public class YXSaoPBean {
    private int  PRI;
    private int RSRP;
    private String EARFCN;
    private String tac;
    private String cid;
    private String pci;
    public YXSaoPBean(int PRI, int RSRP,String e) {
        this.PRI = PRI;
        this.RSRP = RSRP;
        this.EARFCN=e;
    }

    public YXSaoPBean(int PRI, int RSRP, String EARFCN, String tac, String cid, String pci) {
        this.PRI = PRI;
        this.RSRP = RSRP;
        this.EARFCN = EARFCN;
        this.tac = tac;
        this.cid = cid;
        this.pci = pci;
    }

    @Override
    public String toString() {
        return "YXSaoPBean{" +
                "PRI=" + PRI +
                ", RSRP=" + RSRP +
                ", EARFCN='" + EARFCN + '\'' +
                ", tac='" + tac + '\'' +
                ", cid='" + cid + '\'' +
                ", pci='" + pci + '\'' +
                '}';
    }

    public String getTac() {
        return tac;
    }

    public void setTac(String tac) {
        this.tac = tac;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getPci() {
        return pci;
    }

    public void setPci(String pci) {
        this.pci = pci;
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
