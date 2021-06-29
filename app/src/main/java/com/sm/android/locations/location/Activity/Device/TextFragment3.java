package com.sm.android.locations.location.Activity.Device;

import android.annotation.SuppressLint;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.os.Handler;
import android.os.Message;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sm.android.locations.location.Base.BaseFragment;
import com.sm.android.locations.location.R;
import com.sm.android.locations.location.Utils.MainUtils.DeviceUtils;
import com.sm.android.locations.location.Utils.MainUtils.MainUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static com.sm.android.locations.location.Constant.Constant.BAND2;
import static com.sm.android.locations.location.Constant.Constant.BLACKLISTSB2;
import static com.sm.android.locations.location.Constant.Constant.CELLID2;
import static com.sm.android.locations.location.Constant.Constant.DBM2;
import static com.sm.android.locations.location.Constant.Constant.DK2;
import static com.sm.android.locations.location.Constant.Constant.DWON2;
import static com.sm.android.locations.location.Constant.Constant.ENDNUM2;
import static com.sm.android.locations.location.Constant.Constant.GZMS2;
import static com.sm.android.locations.location.Constant.Constant.IMSINUM2;
import static com.sm.android.locations.location.Constant.Constant.IP2;
import static com.sm.android.locations.location.Constant.Constant.PCI2;
import static com.sm.android.locations.location.Constant.Constant.PLMN2;
import static com.sm.android.locations.location.Constant.Constant.REQUEST2;
import static com.sm.android.locations.location.Constant.Constant.SBZQ2;
import static com.sm.android.locations.location.Constant.Constant.SHUAIJIAN2;
import static com.sm.android.locations.location.Constant.Constant.TAC2;
import static com.sm.android.locations.location.Constant.Constant.TBTYPE2;
import static com.sm.android.locations.location.Constant.Constant.TBZT2;
import static com.sm.android.locations.location.Constant.Constant.TYPE2;
import static com.sm.android.locations.location.Constant.Constant.UEIMS2;
import static com.sm.android.locations.location.Constant.Constant.UEMAX2;
import static com.sm.android.locations.location.Constant.Constant.UEMAXOF2;
import static com.sm.android.locations.location.Constant.Constant.UP2;
import static com.sm.android.locations.location.Constant.Constant.ZENGYI2;
import static com.sm.android.locations.location.Constant.Constant.ZHZQ2;

public class TextFragment3 extends BaseFragment {
    RecyclerView recyclerView;
    RyImsiAdapter ryImsiAdapter;
    View view;
    TextView tv_plmn, tv_up, tv_down, tv_band, tv_dk, tv_tac, tv_pci, tv_cid, tv_max, tv_type;//小区信息
    TextView tv_ms, tv_zq, tv_ueimsi, tv_sbzq, tv_glkg, tvmax;//定位模式
    TextView tv_zy, tv_sj;
    TextView tv_rrcnum, tv_rrcsucess, tv_rrcsucessnum, tv_imsiNum, tv_imsiNumsucess;
    TextView tv_tbtype,tv_tbzt;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100001) {//无线正确

            }
        }
    };

    @Override
    public View initView() {
//        view = inflater.inflate(R.layout.deviceinfragment0, container, false);
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.deviceinfragment1, null);
            initData();
        }
//        view = inflater.inflate(R.layout.deviceinfragment0, container, false);
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
            initData();
        }
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // 相当于onResume()方法
            Log.d("nzq", "AsetUserVisibleHint: 1");
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    clear();
                    getData();
                }
            }, 100);
        } else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    clear();
                    getData();
                }
            }, 100);
            Log.d("nzq", "AsetUserVisibleHint: 2");
        }
    }

    @Override
    public void initData() {
        findViews();
        getData();
    }

    private void findViews() {
//        TextView tv_plmn, tv_up, tv_down, tv_band, tv_dk, tv_tac, tv_pci, tv_cid, tv_max, tv_type;//小区信息
//        TextView tv_ms, tv_zq, tv_ueimsi, tv_sbzq, tv_glkg, tvmax;//定位模式
//        TextView tv_zy, tv_sj;
        tv_plmn = view.findViewById(R.id.tv_plmn);
        tv_up = view.findViewById(R.id.tv_up);
        tv_down = view.findViewById(R.id.tv_down);
        tv_band = view.findViewById(R.id.tv_band);
        tv_dk = view.findViewById(R.id.tv_dk);


        recyclerView = view.findViewById(R.id.ryimsi);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        if (!TextUtils.isEmpty(DK2)) {
            int i = Integer.parseInt(DK2);
            tv_dk.setText(i / 5 + "");
        }
        tv_tac = view.findViewById(R.id.tv_tac);
        tv_pci = view.findViewById(R.id.tv_pci);
        tv_cid = view.findViewById(R.id.tv_cid);
        tv_max = view.findViewById(R.id.tv_max);
        tv_type = view.findViewById(R.id.tv_type);
        //定位模式
        tv_ms = view.findViewById(R.id.tv_ms);
        tv_zq = view.findViewById(R.id.tv_zq);
        tv_ueimsi = view.findViewById(R.id.tv_ueimsi);
        tv_sbzq = view.findViewById(R.id.tv_sbzq);
        tv_glkg = view.findViewById(R.id.tv_glkg);
        tvmax = view.findViewById(R.id.tvmax);
        //增益
        tv_zy = view.findViewById(R.id.tv_zy);
        tv_sj = view.findViewById(R.id.tv_sj);
        tv_rrcnum = view.findViewById(R.id.tv_rrcnum);
        tv_rrcsucess = view.findViewById(R.id.tv_rrcsucess);
        tv_rrcsucessnum = view.findViewById(R.id.tv_rrcsucessnum);
        tv_imsiNum = view.findViewById(R.id.tv_imsiNum);
        tv_imsiNumsucess = view.findViewById(R.id.tv_imsiNumsucess);

        tv_tbtype = view.findViewById(R.id.tv_tbtype);
        tv_tbzt = view.findViewById(R.id.tv_tbzt);
        /////////////
        tv_plmn.setText(PLMN2);
        tv_up.setText(UP2);
        tv_down.setText(DWON2);
        tv_band.setText(BAND2);
        tv_dk.setText(DK2);
        tv_tac.setText(TAC2);
        tv_pci.setText(PCI2);
        tv_cid.setText(CELLID2);
        tv_max.setText(DBM2);
        tv_type.setText(TYPE2);
        //
        tv_ms.setText(GZMS2);
        tv_zq.setText(ZHZQ2);
        tv_ueimsi.setText(UEIMS2);
        tv_sbzq.setText(SBZQ2);
        tv_glkg.setText(UEMAXOF2);
        tvmax.setText(UEMAX2);
        tv_rrcnum.setText(REQUEST2);
        tv_rrcsucess.setText(ENDNUM2);
        if (!TextUtils.isEmpty(REQUEST2) && !TextUtils.isEmpty(ENDNUM2) && !REQUEST2.equals("0") && !ENDNUM2.equals("0")) {
            int i = Integer.parseInt(REQUEST2);
            int i2 = Integer.parseInt(ENDNUM2);
            int i3 = i2 / i * 100;
            tv_rrcsucessnum.setText(i3 + "%");
        } else {
            tv_rrcsucessnum.setText("0%");
        }
        tv_imsiNum.setText(IMSINUM2);
        if (!TextUtils.isEmpty(IMSINUM2) && !TextUtils.isEmpty(ENDNUM2) && !REQUEST2.equals("0") && !IMSINUM2.equals("0")) {
            int i = Integer.parseInt(IMSINUM2);
            int i2 = Integer.parseInt(ENDNUM2);
            int i3 = i2 / i * 100;
            tv_imsiNumsucess.setText(i3 + "%");
        } else {
            tv_imsiNumsucess.setText("0%");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        clear();
        getData();
    }

    @Override
    public void onPause() {
        super.onPause();
        clear();
    }

    @Override
    public void onStart() {
        super.onStart();
        clear();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        clear();
    }

    @Override
    public void onStop() {
        super.onStop();
        clear();
    }

    public void clear() {
        tv_plmn.setText("");
        tv_up.setText("");
        tv_down.setText("");
        tv_band.setText("");
//        if (!TextUtils.isEmpty(DK1)) {
//            int i = Integer.parseInt(DK1);
        tv_dk.setText("");
//        }
        tv_tac.setText("");
        tv_pci.setText("");
        tv_cid.setText("");
        tv_max.setText("");
        tv_type.setText("");
        //
        BLACKLISTSB2.clear();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < BLACKLISTSB2.size(); i++) {
            list.add(i);

        }
        //定位模式黑名单
        ryImsiAdapter = new RyImsiAdapter(mContext, list, BLACKLISTSB2);
        recyclerView.setAdapter(ryImsiAdapter);

        tv_ms.setText("");
        tv_zq.setText("");
        tv_ueimsi.setText("");
        tv_sbzq.setText("");
        tv_glkg.setText("");
        tvmax.setText("");

        tv_zy.setText("");
        tv_sj.setText("");
        tv_rrcnum.setText("");
        tv_rrcsucess.setText("");
        tv_rrcsucessnum.setText("");
        tv_imsiNum.setText("");
        tv_imsiNumsucess.setText("");
        tv_tbzt.setText("");
        tv_tbtype.setText("");
    }

    private void getData() {
        DeviceUtils.SelectQury(1, 2);
        DeviceUtils.SelectQury(2, 2);
        DeviceUtils.SelectQury(3, 2);
        DeviceUtils.SelectQury(4, 2);
        DeviceUtils.SelectQury(5, 2);
        DeviceUtils.SelectQury(6, 2);
        MainUtils.getType(IP2);//查询状态

        handler.postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
//                tv_devicenumber.setText(DEVICENUMBER1);
//                tv_hardware.setText(HARDWAREVERSION1);
//                tv_software.setText(SOFTWAREVERSION1);
//                tv_sn.setText(SNNUMBER1);
//                tv_mac.setText(MACADDRESS1);
//                tvuboot.setText(UBOOTVERSION1);
//                tvboard.setText(BOARDTEMPERATURE1);
//定位模式
                tv_ms = view.findViewById(R.id.tv_ms);
                tv_zq = view.findViewById(R.id.tv_zq);
                tv_ueimsi = view.findViewById(R.id.tv_ueimsi);
                tv_sbzq = view.findViewById(R.id.tv_sbzq);
                tv_glkg = view.findViewById(R.id.tv_glkg);
                tvmax = view.findViewById(R.id.tvmax);
                //增益
                tv_zy = view.findViewById(R.id.tv_zy);
                tv_sj = view.findViewById(R.id.tv_sj);

                /////////////
                if (BLACKLISTSB2.size()>0){
                    List<String> list1S = ridRepeat4(BLACKLISTSB2);
                    List<Integer> list = new ArrayList<>();
                    for (int i = 0; i < list1S.size(); i++) {
                        list.add(i);
                    }
                    //定位模式黑名单

                    ryImsiAdapter = new RyImsiAdapter(mContext, list, list1S);
                    recyclerView.setAdapter(ryImsiAdapter);
                }


                tv_plmn.setText(PLMN2);
                tv_up.setText(UP2);
                tv_down.setText(DWON2);
                tv_band.setText(BAND2);
                if (!TextUtils.isEmpty(DK2)) {
                    int i = Integer.parseInt(DK2);
                    tv_dk.setText(i / 5 + "");
                }


                tv_tac.setText(TAC2);
                tv_pci.setText(PCI2);
                tv_cid.setText(CELLID2);
                tv_max.setText(DBM2);
                tv_type.setText(TYPE2);
                //
                tv_ms.setText(GZMS2);
                tv_zq.setText(ZHZQ2);
                tv_ueimsi.setText(UEIMS2);
                tv_sbzq.setText(SBZQ2);
                tv_glkg.setText(UEMAXOF2);
                tvmax.setText(UEMAX2);

                tv_zy.setText(ZENGYI2);
                tv_sj.setText(SHUAIJIAN2);

                tv_rrcnum.setText(REQUEST2);
                tv_rrcsucess.setText(ENDNUM2);
                if (!TextUtils.isEmpty(REQUEST2) && !TextUtils.isEmpty(ENDNUM2) && !REQUEST2.equals("0") && !ENDNUM2.equals("0")) {
                    Double i = Double.parseDouble(REQUEST2);
                    Double i2 = Double.parseDouble(ENDNUM2);
                    Double i3 = i2 / i * 100;
                    @SuppressLint({"NewApi", "LocalSuppress"}) DecimalFormat df2=new DecimalFormat("#.#");
                    tv_rrcsucessnum.setText(df2.format(i3) + "%");
                } else {
                    tv_rrcsucessnum.setText("0%");
                }
                tv_imsiNum.setText(IMSINUM2);
                if (!TextUtils.isEmpty(IMSINUM2) && !TextUtils.isEmpty(ENDNUM2) && !REQUEST2.equals("0") && !IMSINUM2.equals("0")) {
                    int i = Integer.parseInt(IMSINUM2);
                    int i2 = Integer.parseInt(ENDNUM2);
                    int i3 = i2 / i * 100;
                    @SuppressLint({"NewApi", "LocalSuppress"}) DecimalFormat df2=new DecimalFormat("#.#");
                    tv_imsiNumsucess.setText(i3 + "%");
                } else {
                    tv_imsiNumsucess.setText("0%");
                }
                if (!TextUtils.isEmpty(TBTYPE2)){
                    if (TBTYPE2.equals("0")){
                        tv_tbtype.setText("空口同步");
                    }else {
                        tv_tbtype.setText("GPS同步");
                    }
                }
                if (!TextUtils.isEmpty(TBZT2)){
                    if (TBZT2.equals("0")){
                        tv_tbzt.setText("GPS同步成功");
                    }
                    if (TBZT2.equals("1")){
                        tv_tbzt.setText("空口同步成功");
                    }
                    if (TBZT2.equals("2")){
                        tv_tbzt.setText("未同步");
                    }
                    if (TBZT2.equals("3")){
                        tv_tbzt.setText("GPS失步");

                    }
                    if (TBZT2.equals("4")){
                        tv_tbzt.setText("空口失步");
                    }
                }
            }
        }, 5000);
    }

    // Set通过HashSet去重（将ridRepeat3方法缩减为一行） 无序
    public static List<String> ridRepeat4(List<String> list) {
        System.out.println("list = [" + list + "]");
        List<String> listNew = new ArrayList<String>(new HashSet(list));
        System.out.println("listNew = [" + listNew + "]");
        return listNew;
    }
}