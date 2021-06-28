package com.sm.android.locations.locations.viewpagermain.Fragment.frament1Packet.ViewSaoPinCallback;

import com.sm.android.locations.locations.Utils.OrmSqlLite.Bean.SpBean;

public interface ViewSaoPinCallback {
    void sucess(int sb, int i);//成功 设备扫描

    void sucessphone(int sb, String down, SpBean spBean, boolean phonesp);// 手机扫描
    void sucessphoneShow(int sb, String down, SpBean spBean,boolean phonesp,boolean showFlag);
    void error();//失败
}
