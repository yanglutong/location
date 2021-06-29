package com.sm.android.locations.location.Utils.MainUtils;

import android.content.Context;

import com.sm.android.locations.location.R;
import com.sm.android.locations.location.Utils.pop.DLPopItem;
import com.sm.android.locations.location.Utils.pop.DLPopupWindow;

import java.util.List;

public class AddMenuUtils {
    public static void addmenu(final Context context, DLPopupWindow popupWindow, final List<DLPopItem> mList) {
//        DLPopItem item = new DLPopItem(R.mipmap.add_nlue, "添加参数", 0xffffff);//添加参数按钮
//        mList.add(item);
//        DLPopItem item = new DLPopItem(R.mipmap.saop, "定位方式", 0xffffff);//选择参数按钮
//        mList.add(item);
        DLPopItem item = new DLPopItem(R.mipmap.chose, "目标IMSI", 0xffffff);//选择参数按钮
        mList.add(item);
        item = new DLPopItem(R.mipmap.shebeipeizhi, "设备设置", 0xffffff);//选择参数按钮
        mList.add(item);
        item = new DLPopItem(R.mipmap.peizhi, "频点设置", 0xffffff);//选择参数按钮
        mList.add(item);
        item = new DLPopItem(R.mipmap.locationmenu, "扫频设置", 0xffffff);//选择参数按钮
        mList.add(item);
//        item = new DLPopItem(R.mipmap.sjgl, "数据管理", 0xffffff);//选择参数按钮
//        mList.add(item);
        item = new DLPopItem(R.mipmap.banben, "设备信息", 0xffffff);//选择参数按钮
        mList.add(item);
//    设备设置   频点设置  扫频设置 轮循设置. 设备信息   //侦码模式
//        item = new DLPopItem(R.mipmap.qu2, "设备2配置", 0xffffff);//选择参数按钮
//        mList.add(item);
        popupWindow = new DLPopupWindow(context, mList, DLPopupWindow.STYLE_WEIXIN);
//        popupWindow.setOnItemClickListener(new DLPopupWindow.OnItemClickListener() {
//            @Override
//            public void OnClick(int position) {
//                Toast.makeText(context, mList.get(position).getText(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    public static void addmenuZhenma(final Context context, DLPopupWindow popupWindow, final List<DLPopItem> mList) {
//        DLPopItem item = new DLPopItem(R.mipmap.add_nlue, "添加参数", 0xffffff);//添加参数按钮
//        mList.add(item);
//        DLPopItem item = new DLPopItem(R.mipmap.saop, "定位方式", 0xffffff);//选择参数按钮
//        mList.add(item);
//        DLPopItem    item = new DLPopItem(R.mipmap.chose, "目标IMSI", 0xffffff);//选择参数按钮
//        mList.add(item);
        DLPopItem item = new DLPopItem(R.mipmap.shebeipeizhi, "设备设置", 0xffffff);//选择参数按钮
        mList.add(item);
        item = new DLPopItem(R.mipmap.peizhi, "频点设置", 0xffffff);//选择参数按钮
        mList.add(item);
        item = new DLPopItem(R.mipmap.locationmenu, "扫频设置", 0xffffff);//选择参数按钮
        mList.add(item);
//        item = new DLPopItem(R.mipmap.locationmenu, "轮循设置", 0xffffff);//选择参数按钮
//        mList.add(item);
//        item = new DLPopItem(R.mipmap.sjgl, "数据管理", 0xffffff);//选择参数按钮
//        mList.add(item);
        item = new DLPopItem(R.mipmap.sjgl, "数据分析", 0xffffff);//选择参数按钮
        mList.add(item);
        item = new DLPopItem(R.mipmap.banben, "设备信息", 0xffffff);//选择参数按钮
        mList.add(item);
//    设备设置   频点设置  扫频设置 轮循设置. 设备信息   //侦码模式
//        item = new DLPopItem(R.mipmap.qu2, "设备2配置", 0xffffff);//选择参数按钮
//        mList.add(item);+
        popupWindow = new DLPopupWindow(context, mList, DLPopupWindow.STYLE_WEIXIN);
//        popupWindow.setOnItemClickListener(new DLPopupWindow.OnItemClickListener() {
//            @Override
//            public void OnClick(int position) {
//                Toast.makeText(context, mList.get(position).getText(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    public static void addmenuguankong(final Context context, DLPopupWindow popupWindow, final List<DLPopItem> mList) {


        DLPopItem item = new DLPopItem(R.mipmap.chose, "非控IMSI", 0xffffff);//选择参数按钮
        mList.add(item);
        item = new DLPopItem(R.mipmap.shebeipeizhi, "设备设置", 0xffffff);//选择参数按钮
        mList.add(item);
        item = new DLPopItem(R.mipmap.peizhi, "频点设置", 0xffffff);//选择参数按钮
        mList.add(item);
        item = new DLPopItem(R.mipmap.locationmenu, "扫频设置", 0xffffff);//选择参数按钮
        mList.add(item);

        item = new DLPopItem(R.mipmap.banben, "设备信息", 0xffffff);//选择参数按钮
        mList.add(item);
//
    }

}
