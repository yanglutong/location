package com.sm.android.locations.location.initData;

import java.util.ArrayList;

/**
 * @author: 小杨同志
 * @date: 2021/6/23
 */
public class PLMN {
    public static ArrayList<PLMN> plmns=new ArrayList<>();
    private String plmn;
    private String down;
    private String up;
    private String tac;
    private String pci;
    private String band;
    private String cid;
    public PLMN(){}
    public PLMN(String plmn, String down, String up, String tac, String pci, String band, String cid) {
        this.plmn = plmn;
        this.down = down;
        this.up = up;
        this.tac = tac;
        this.pci = pci;
        this.band = band;
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "PLMN{" +
                "plmn='" + plmn + '\'' +
                ", down='" + down + '\'' +
                ", up='" + up + '\'' +
                ", tac='" + tac + '\'' +
                ", pci='" + pci + '\'' +
                ", band='" + band + '\'' +
                ", cid='" + cid + '\'' +
                '}';
    }

    public String getPlmn() {
        return plmn;
    }

    public void setPlmn(String plmn) {
        this.plmn = plmn;
    }

    public String getDown() {
        return down;
    }

    public void setDown(String down) {
        this.down = down;
    }

    public String getUp() {
        return up;
    }

    public void setUp(String up) {
        this.up = up;
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

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
    public static void setPlmns(PLMN plmn){
        if(plmn!=null){
            plmns=new ArrayList<>();
            plmns.add(plmn);
        }
    }
    public static ArrayList<PLMN> getPlmns(){
        if(plmns!=null){
            return plmns;
        }else{
            return null;
        }
    }
}
