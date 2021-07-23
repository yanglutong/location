package com.sm.android.locations.location.Activity.Communit.Fragment;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sm.android.locations.location.Base.BaseFragment;
import com.sm.android.locations.location.R;

import com.sm.android.locations.location.Test.ReceiveTask;
import com.sm.android.locations.location.Utils.MainUtils.DeviceUtils;
import com.sm.android.locations.location.Utils.MainUtils.MainUtils;
import com.sm.android.locations.location.Utils.MainUtils.StringConvertUtils;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.Conmmunit01Bean;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.TDDFDDzyBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManager01;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerZY;
import com.sm.android.locations.location.Utils.ToastUtils;
import com.sm.android.locations.location.initData.CommandUtils;
import com.sm.android.locations.location.initData.MyLog;
import com.sm.android.locations.location.initData.TCPServer;
import com.sm.android.locations.location.initData.dao.DBManagerDevice;
import com.sm.android.locations.location.initData.dao.DeviceBean;
import com.sm.android.locations.location.sos.SOSActivity;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.sm.android.locations.location.Constant.Constant.IP1;
import static com.sm.android.locations.location.Constant.Constant.SHUAIJIAN1;
import static com.sm.android.locations.location.Constant.Constant.TBTYPE1;

public class CommunitFragment1 extends BaseFragment implements View.OnClickListener{

    private static String TAG = "TextFragment0";
    View view;
    //生成的ID
    private TextView tv1edit;
    private EditText  et_cycle, et_gl, et_zhenmalunxun,et_PCI,et_CI,et_TAC,et_Zyd,et_Zyg,et_Zyz;
    private Spinner sp_type;
    private Button mBt_save, bt_cancel;
    private CheckBox cb_location, cb_zm;
    private int spItem = 0;


    ImageView finsh, image_jiahao;
    private EditText et_range;
    private EditText et_start;
    private EditText et_zy;

    @Override
    public View initView() {
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.activity_community03, null);
            initData();
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
            initData();
        }
        return view;
    }

    @Override
    public void initData() {
        findViews();

        setLiShi();//保存上次的历史记录
    }

    private void setLiShi() {
        try {
            DBManagerDevice managerDevice = new DBManagerDevice(mContext);
            List<DeviceBean> list = managerDevice.getDeviceBeans();
            if(list.size()>0){
                et_range.setText(""+list.get(list.size()-1).getEt_RANGE());
                et_start.setText(""+list.get(list.size()-1).getEt_START());
                et_cycle.setText(""+list.get(list.size()-1).getCycle());
                et_CI.setText(""+list.get(list.size()-1).getCi());
                et_PCI.setText(""+list.get(list.size()-1).getPci());
                et_Zyd.setText(""+list.get(list.size()-1).getEt_Zyd());
                et_Zyz.setText(""+list.get(list.size()-1).getEt_Zyz());
                et_Zyg.setText(""+list.get(list.size()-1).getEt_Zyg());
                et_TAC.setText(""+list.get(list.size()-1).getTac());
            }else{

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onStart() {
        super.onStart();
        clear();
    }

    @Override
    public void onPause() {
        super.onPause();
        clear();
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d(TAG, "onResumeA: " + "执行了4");
    }

    private void findViews() {
        et_range = view.findViewById(R.id.et_RANGE);
        et_start = view.findViewById(R.id.et_START);

        et_cycle = view.findViewById(R.id.et_cycle);
        et_PCI = view.findViewById(R.id.et_PCI);
        et_CI = view.findViewById(R.id.et_CI);
        et_TAC = view.findViewById(R.id.et_TAC);
        et_Zyd = view.findViewById(R.id.et_Zyd);
        et_Zyz = view.findViewById(R.id.et_Zyz);
        et_Zyg = view.findViewById(R.id.et_Zyg);

        tv1edit = view.findViewById(R.id.tv1edit);


        tv1edit.setText("请输入设备1参数");
        mBt_save = (Button) view.findViewById(R.id.bt_save);
        mBt_save.setOnClickListener(this);
        bt_cancel = view.findViewById(R.id.bt_cancel);
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }


    private void clear() {

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_jiahao:
                break;

            case R.id.finsh:
                break;
            case R.id.bt_save:
                //将参数都获取到并且下达发送命令
                    sendStting();
                break;

            case R.id.iv_finish:


                break;

            case R.id.bt_cancel:

        }
    }

    private void sendStting() {
        String et_range1 = et_range.getText().toString();
        String et_start1 = et_start.getText().toString();
        String et_cycle1 = et_cycle.getText().toString();
        String et_CI1 = et_CI.getText().toString();
        String et_PCI1 = et_PCI.getText().toString();
        String et_Zyd1 = et_Zyd.getText().toString();
        String et_Zyz1 = et_Zyz.getText().toString();
        String et_Zyg1 = et_Zyg.getText().toString();
        String et_TAC1 = et_TAC.getText().toString();

        MyLog.i("sendStting",et_cycle1);
        if(et_cycle1.equals("")){
            ToastUtils.showToast("请输入抓号周期");
            return;
        }

//        if(et_Zyz1.equals("")||et_Zyg1.equals("")||et_Zyd1.equals("")){
//            ToastUtils.showToast("请输入低中高增益值");
//            return;
//        }
        if(et_start1.equals("")){
            ToastUtils.showToast("请输入TAC起始值");
            return;
        }if(et_range1.equals("")){
            ToastUtils.showToast("请输入TAC变更个数");
            return;
        }if(et_CI1.equals("")){
            ToastUtils.showToast("请输入CI值");
            return;
        }if(et_TAC1.equals("")){
            ToastUtils.showToast("请输入TAC值");
            return;
        }if(et_PCI1.equals("")){
            ToastUtils.showToast("请输入PCI值");
            return;
        }

        if(Integer.parseInt(et_cycle1)>=0&&Integer.parseInt(et_cycle1)<=1440){
        }else{
            ToastUtils.showToast("输入的抓号周期需在0-1440");
            return;
        }
        if(Integer.parseInt(et_TAC1)>0&&Integer.parseInt(et_TAC1)<=65535){
        }else{
            ToastUtils.showToast("输入的范围应在1-65535");
            return;
        }if(Integer.parseInt(et_CI1)>=0&&Integer.parseInt(et_CI1)<=255){
        }else{
            ToastUtils.showToast("输入的范围应在0-255");
            return;
        }if(Integer.parseInt(et_PCI1)>0&&Integer.parseInt(et_PCI1)<=503){
        }else{
            ToastUtils.showToast("输入的范围应在0-503");
            return;
        }
//        if(Integer.parseInt(et_Zyd1)>=0&&Integer.parseInt(et_Zyd1)<=20){
//        }else{
//            ToastUtils.showToast("最低增益范围应在0-20");
//            return;
//        }if(Integer.parseInt(et_Zyz1)>=21&&Integer.parseInt(et_Zyz1)<=50){
//        }else{
//            ToastUtils.showToast("中档增益范围应在21-50");
//            return;
//        }if(Integer.parseInt(et_Zyg1)>=51&&Integer.parseInt(et_Zyg1)<=70){
//        }else{
//            ToastUtils.showToast("最高增益范围应在51-70");
//            return;
//        }
        if(Integer.parseInt(et_start1)>0&&Integer.parseInt(et_start1)<=65535){
            if(Integer.parseInt(et_range1)>0&&Integer.parseInt(et_range1)<=1000){
                if(!CommandUtils.type.equals("")){
                }else{
                    ToastUtils.showToast("离线状态无法设置");
                    return;
                }
                DBManagerDevice dbManagerDevice=null;
                try {
                    dbManagerDevice = new DBManagerDevice(mContext);
                    List<DeviceBean> list = dbManagerDevice.getDeviceBeans();
                    if(list.size()>0){


                        if(list.get(list.size()-1).getCycle().equals(et_cycle1)
                                &&list.get(list.size()-1).getEt_RANGE().equals(et_range1)
                                &&list.get(list.size()-1).getEt_START().equals(et_start1)
                                &&list.get(list.size()-1).getTac().equals(et_TAC1)
                                &&list.get(list.size()-1).getCi().equals(et_CI1)
                                &&list.get(list.size()-1).getPci().equals(et_PCI1)
                                &&list.get(list.size()-1).getEt_Zyd().equals(et_Zyd1)
                                &&list.get(list.size()-1).getEt_Zyz().equals(et_Zyz1)
                                &&list.get(list.size()-1).getEt_Zyg().equals(et_Zyg1)){
                                ToastUtils.showToast("已是当前配置无需重复保存");
                        }else{
                            DeviceBean deviceBean = new DeviceBean();
                            deviceBean.setCycle(et_cycle1);
                            deviceBean.setEt_Zyd(et_Zyd1);
                            deviceBean.setEt_Zyz(et_Zyz1);
                            deviceBean.setEt_Zyg(et_Zyg1);
                            deviceBean.setEt_RANGE(et_range1);
                            deviceBean.setEt_START(et_start1);
                            deviceBean.setTac(et_TAC1);
                            deviceBean.setPci(et_PCI1);
                            deviceBean.setCi(et_CI1);
                            dbManagerDevice.insertDeviceBean(deviceBean);

                            for (DeviceBean bean : list) {
                                MyLog.e("sendStting", bean.toString());
                            }
                            MyLog.i("sendStting", "sendStting: "+list.size());

                            //进行保存数据和请求网络
                            if(SOSActivity.socketTcpServer!=null){
                                CommandUtils.type0102="设备设置";
                                SOSActivity.socketTcpServer.sendPost(CommandUtils.setDeviceParameters(et_cycle1,et_start1, et_range1,et_TAC1,et_CI1,et_PCI1));
                                getActivity().finish();
                            }
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }




            }else{
                ToastUtils.showToast("输入的个数必须在区间范围");
            }
        }else{
            ToastUtils.showToast("输入的起始值必须在区间范围");
        }



    }
}
