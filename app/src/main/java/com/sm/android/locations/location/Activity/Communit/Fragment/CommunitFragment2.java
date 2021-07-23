//package com.sm.android.locations.location.Activity.Communit.Fragment;
//
//import android.os.Handler;
//import android.os.Message;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.CompoundButton;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.sm.android.locations.location.Base.BaseFragment;
//import com.sm.android.locations.location.R;
//
//import com.sm.android.locations.location.Test.ReceiveTask;
//import com.sm.android.locations.location.Utils.MainUtils.DeviceUtils;
//import com.sm.android.locations.location.Utils.MainUtils.MainUtils;
//import com.sm.android.locations.location.Utils.MainUtils.StringConvertUtils;
//import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.Conmmunit01Bean;
//import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.TDDFDDzyBean;
//import com.sm.android.locations.location.Utils.OrmSqlLite.DBManager01;
//import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerZY;
//import com.sm.android.locations.location.Utils.ToastUtils;
//
//import java.math.BigDecimal;
//import java.sql.SQLException;
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Timer;
//import java.util.TimerTask;
//
//import static com.sm.android.locations.location.Constant.Constant.IP2;
//import static com.sm.android.locations.location.Constant.Constant.SHUAIJIAN1;
//import static com.sm.android.locations.location.Constant.Constant.SHUAIJIAN2;
//import static com.sm.android.locations.location.Constant.Constant.TBTYPE1;
//import static com.sm.android.locations.location.Constant.Constant.TBTYPE2;
//
//public class CommunitFragment2 extends BaseFragment implements View.OnClickListener {
//    private static String TAG = "TextFragment0";
//    DBManager01 dbManager01;
//    List<Conmmunit01Bean> conmmunit01Beans;
//    DecimalFormat df = new DecimalFormat("0.00");
//    View view;
//    //生成的ID
//    private TextView tv1edit;
//    private ImageView mFinsh;
//    private TextView mMytitle;
//    private ImageView mImage_jiahao;
//    private EditText mEt_tac;
//    private EditText et_cid;
//    private EditText et_pci;
//    private EditText mEt_uepmax;
//    private EditText mEt_enodebpmax;
//    private EditText et_tddd, et_tddz, et_tddg, et_fddd, et_fddz, et_fddg,et_cycle,et_gl,et_zhenmalunxun;
//    private Spinner sp_type;
//    private Button mBt_save, bt_cancel;
//    private CheckBox cb_location, cb_zm;
//    private int spItem = 0;
//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            if (msg.what == 100) {//
////                setSpData();
//                timerLocation.cancel();
//                Log.d("setSpData", "handleMessage: " + TBTYPE1);
//
//
//                Log.d("SHUAIJIAN1", "handleMessage: " + SHUAIJIAN1);
//                if (!TextUtils.isEmpty(SHUAIJIAN2)) {
//                    Double XS=Double.parseDouble(SHUAIJIAN2);
////                    int XS = 12;
//                    double v = (33 - (XS * 0.25)) * 0.1;
//                    double pow = Math.pow(10, v);
//                    double v1 = pow / 1000;
//                    Log.d("nzqtag", "run: " + v1);
//                    et_gl.setText(df.format(v1));
////
//
//
//                }
//            }
//        }
//    };
//
//    private void setSpData() {
//        if (TBTYPE2.equals("0")) {
//            sp_type.setSelection(0);//空口同步
//
//        } else {
//            sp_type.setSelection(1);//GPS同步
//        }
//    }
//
//    private Timer timerLocation = null;//五秒一次imsi列表更新
//    ImageView finsh, image_jiahao;
//
//    @Override
//    public View initView() {
////        view = inflater.inflate(R.layout.deviceinfragment0, container, false);
//        if (view == null) {
//            view = LayoutInflater.from(mContext).inflate(R.layout.activity_community02, null);
//            initData();
//        }
//        ViewGroup parent = (ViewGroup) view.getParent();
//        if (parent != null) {
//            parent.removeView(view);
//            initData();
//        }
//        return view;
//    }
//
//    @Override
//    public void initData() {
//        findViews();
//        clear();
//        getData();
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        clear();
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        clear();
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        Log.d(TAG, "onResumeA: " + "执行了4");
////        clear();
////        getData();
//        timerLocation = new Timer();
//        //schedule方法是执行时间定时任务的方法
//        timerLocation.schedule(new TimerTask() {
//
//            //run方法就是具体需要定时执行的任务
//            @Override
//            public void run() {
//                Message message = new Message();
//                handler.sendMessage(message);
//                message.what = 100;
//                Log.d(TAG, "handlerrun: " + 1);
//            }
////            }, 0, 10000);
//        }, 0, 3000);
//    }
//
//    private void findViews() {
//
//        et_cycle=view.findViewById(R.id.et_cycle);
//        et_gl = view.findViewById(R.id.et_gl);
//        et_zhenmalunxun=view.findViewById(R.id.et_zhenmalunxun);
//        sp_type=view.findViewById(R.id.sp_type);
//        tv1edit = view.findViewById(R.id.tv1edit);
//        tv1edit.setText("请输入设备2参数");
//        mEt_tac = (EditText) view.findViewById(R.id.et_tac);
//        et_pci = (EditText) view.findViewById(R.id.et_pci);
//        et_cid = (EditText) view.findViewById(R.id.et_cid);
//        mEt_uepmax = (EditText) view.findViewById(R.id.et_uepmax);
//        mEt_enodebpmax = (EditText) view.findViewById(R.id.et_enodebpmax);
//        mBt_save = (Button) view.findViewById(R.id.bt_save);
//        mBt_save.setOnClickListener(this);
//        bt_cancel = view.findViewById(R.id.bt_cancel);
//        bt_cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getActivity().finish();
//            }
//        });
//        cb_location = view.findViewById(R.id.cb_location);
//        cb_zm = view.findViewById(R.id.cb_zm);
//        cb_location.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b == true) {
//                    cb_zm.setChecked(false);
////                    ACacheUtil.putMS1("0");
//                } else {
//                    cb_zm.setChecked(true);
////                    ACacheUtil.putMS1("1");
//
//                }
//            }
//        });
//        cb_zm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b == true) {
//                    cb_location.setChecked(false);
////                    ACacheUtil.putMS1("1");
//                } else {
//                    cb_location.setChecked(true);
////                    ACacheUtil.putMS1("0");
//                }
//            }
//        });
//    }
//
//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        Log.d(TAG, "onHiddenChanged: " + hidden);
//    }
//
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser) {
//            // 相当于onResume()方法
//            Log.d(TAG, "onResumeA: " + "执行了1");
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    clear();
////                    getData();
//                }
//            }, 100);
//        } else {
////            clear();
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    clear();
////                    getData();
//                }
//            }, 100);
////            getData();
//            Log.d(TAG, "onResumeA: " + "执行了2");
//        }
//    }
//
//    @Override
//    public boolean getUserVisibleHint() {
//        boolean userVisibleHint = super.getUserVisibleHint();
//        Log.d(TAG, "onResumeA: " + "执行了" + userVisibleHint);
//        return super.getUserVisibleHint();
//
//
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        Log.d(TAG, "onResumeA: " + "执行了3");
//        clear();
//    }
//
//    private void clear() {
//
//    }
//
//    private void getData() {
//        MainUtils.getType(IP2);
//        DeviceUtils.SelectQury(3, 2);
//        List<String> listspinner = new ArrayList<>();
//        listspinner.add("空口同步");
//        listspinner.add("GPS同步");
//        ArrayAdapter adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, listspinner);
//        sp_type.setAdapter(adapter);
//        sp_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Log.d("onItemSelectedCom", "onItemSelected: " + i);
//                spItem = i;
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
////        setStatBar();
////        findview();
////        bindViews();
//        try {
//            dbManager01 = new DBManager01(mContext);
//
//            /**
//             * 插入数据
//             */
//
//            conmmunit01Beans = dbManager01.getConmmunit01Beans();
//            Log.e("conmmunit01Beans", "initData: " + conmmunit01Beans);
//            Conmmunit01Bean forid = dbManager01.forid(2);
//            if (forid == null) {
//                Log.e("nzqforid", "等于nullinitData: " + forid);
//                Conmmunit01Bean conmmunit01Bean = new Conmmunit01Bean();
//                conmmunit01Bean.setCid("");
//                conmmunit01Bean.setEnodebpmax("");
//                conmmunit01Bean.setUepmax("");
//                conmmunit01Bean.setTac("");
//                conmmunit01Bean.setPci("");
//                conmmunit01Bean.setCid("");
//                conmmunit01Bean.setType("0");
//                conmmunit01Bean.setTime("60");
//                dbManager01.insertConmmunit01Bean(conmmunit01Bean);
////                ACacheUtil.putMS2("0");
//            } else {
//                DBManagerZY zy = new DBManagerZY(mContext);
//                TDDFDDzyBean forid1 = zy.forid(2);
////                et_tddd,et_tddz,et_tddg,et_fddd,et_fddz,et_fddg;
//                et_tddd.setText(forid1.getTDDzyd());
//                et_tddz.setText(forid1.getTDDzyz());
//                et_tddg.setText(forid1.getTDDzyg());
//
//                et_fddd.setText(forid1.getFDDzyd());
//                et_fddz.setText(forid1.getFDDzyz());
//                et_fddg.setText(forid1.getFDDzyg());
//                et_cycle.setText(forid.getCycle());
//                Log.e("nzqforid", "不等于nullinitData: " + forid);
//                et_cid.setText(forid.getCid() + "");
//                et_pci.setText(forid.getPci() + "");
//                mEt_tac.setText(forid.getTac() + "");
//                et_zhenmalunxun.setText(forid.getTime()+"");
//                mEt_enodebpmax.setText(forid.getEnodebpmax() + "");
//                mEt_uepmax.setText(forid.getUepmax() + "");
//                String type = forid.getType();
//                if (type.equals("0")) {
//                    cb_location.setChecked(true);
//                    cb_zm.setChecked(false);
//                } else if (forid.getType().equals("1")) {
//                    cb_location.setChecked(false);
//                    cb_zm.setChecked(true);
//                }
//            }
//            Log.e("nzq", "initData: " + forid);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        if (conmmunit01Beans.size() == 0) {
//            //新添加的时候
//        } else {
//
//        }
//
////        handler.postDelayed(new Runnable() {
////            @Override
////            public void run() {
////
////
////            }
////        }, 5000);
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        if (TBTYPE2.equals("0")) {
//            sp_type.setSelection(0);//空口同步
//
//        } else {
//            sp_type.setSelection(1);//GPS同步
//        }
//    }
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.image_jiahao:
//                break;
//
//            case R.id.finsh:
////                finish();
//                break;
//            case R.id.bt_save:
//
//                //判断书否新建或者修改
//                sqldata();
//                break;
//
//            case R.id.iv_finish:
//
////                finish();
//
//                break;
//
//            case R.id.bt_cancel:
//
////                finish();
//        }
//    }
//
//    private void sqldata() {
//        try {
//            dbManager01 = new DBManager01(mContext);
//
//            /**
//             * 插入数据
//             */
//
//            conmmunit01Beans = dbManager01.getConmmunit01Beans();
//            Log.e("conmmunit01Beans", "initData: " + conmmunit01Beans);
//            Conmmunit01Bean forid1 = dbManager01.forid(2);
//            if (forid1 == null) {
//                Log.e("nzqforid", "等于null 添加initData: " + forid1);
//                Conmmunit01Bean conmmunit01Bean = new Conmmunit01Bean();
//                conmmunit01Bean.setCid(et_cid.getText().toString());
//                conmmunit01Bean.setEnodebpmax(mEt_enodebpmax.getText().toString());
//                conmmunit01Bean.setUepmax(mEt_uepmax.getText().toString());
//                conmmunit01Bean.setTac(mEt_tac.getText().toString());
//                conmmunit01Bean.setPci(et_pci.getText().toString());
//                conmmunit01Bean.setTime(et_zhenmalunxun.getText().toString());
//
//                dbManager01.insertConmmunit01Bean(conmmunit01Bean);
////                ACacheUtil.putMS2("0");
//            } else {
//                Log.e("nzqforid", "不等于null 保存: " + forid1);
//                Log.e("et_cid.getText", "sqldata: " + et_cid.getText().toString());
//                if (isNulls()) return;
//                Conmmunit01Bean conmmunit01Bean1 = new Conmmunit01Bean();
//                conmmunit01Bean1.setCid(et_cid.getText().toString());
//                conmmunit01Bean1.setEnodebpmax(mEt_enodebpmax.getText().toString());
//                conmmunit01Bean1.setUepmax(mEt_uepmax.getText().toString());
//                conmmunit01Bean1.setTac(mEt_tac.getText().toString());
//                conmmunit01Bean1.setPci(et_pci.getText().toString());
//                conmmunit01Bean1.setCycle(et_cycle.getText().toString());
//                conmmunit01Bean1.setTime(et_zhenmalunxun.getText().toString());
//                conmmunit01Bean1.setId(2);
//                if (cb_location.isChecked() == true) {
//                    conmmunit01Bean1.setType("0");//定位模式
//                } else {
//                    conmmunit01Bean1.setType("1");//侦码模式
//                }
//                dbManager01.updateConmmunit01Bean(conmmunit01Bean1);
//                Log.e("sql", "sqldata: " + conmmunit01Bean1);
//                Toast.makeText(mContext, "保存成功", Toast.LENGTH_LONG).show();
////                finish();
//                //增益值保存
//
//
//                DBManagerZY dbManagerZY=new DBManagerZY(mContext);
//                TDDFDDzyBean tddfdDzyBean=new TDDFDDzyBean();
//                tddfdDzyBean.setId(2);
//                tddfdDzyBean.setTDDzyd(et_tddd.getText().toString());
//                tddfdDzyBean.setTDDzyz(et_tddz.getText().toString());
//                tddfdDzyBean.setTDDzyg(et_tddg.getText().toString());
//
//                tddfdDzyBean.setFDDzyd(et_fddd.getText().toString());
//                tddfdDzyBean.setFDDzyz(et_fddz.getText().toString());
//                tddfdDzyBean.setFDDzyg(et_fddg.getText().toString());
//
//                int i = dbManagerZY.updateCheck(tddfdDzyBean);//修改
//                if (spItem == 0) {//空口同步
//                    MainUtils.SetType(IP2,0);
//
//                } else {//GPS同步
//                    MainUtils.SetType(IP2,1);
//                }
////
//                //衰减功率设置
//                String s = et_gl.getText().toString();
//                Double i1 = Double.valueOf(s);
//                double v2 = i1 * 1000;
//                double log = Math.log10(v2);
//                double v3 = 33 - log * 10;
//                double v4 = v3 * 4;
//                String s1 = String.valueOf(v4);
//                BigDecimal bigDecimal = new BigDecimal(s1).setScale(0, BigDecimal.ROUND_HALF_UP);
//                String s2 = bigDecimal.toString();
//                Log.d("nzqtaglog", "run: " + s2);
////
//                int a = Integer.parseInt(s2);
//                byte[] bytes = StringConvertUtils.toHH(a);
//                String str = ReceiveTask.toHexString1(bytes);
//                String substring = str.substring(4);
//                String substring3 = substring.substring(2, 3);
//                String substring4 = substring.substring(3, 4);
//                String data=substring3+substring4;
//
//                Log.d("nzqtaglog", "runsubstring: " + data.toString());
//                MainUtils.GlSend(IP2,data);
//            }
//            Log.e("nzq", "initData: " + forid1);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private boolean isNulls() {
//        if (!TextUtils.isEmpty(et_cycle.getText().toString())){
//            String scid = et_cycle.getText().toString();
//            int i = Integer.parseInt(scid);
//            if (5 <= i && i <= 65535) {
//
//            } else {
//                ToastUtils.showToast("抓号周期应为5-65535之间");
//                return true;
//            }
//        }
//        if (!TextUtils.isEmpty(et_zhenmalunxun.getText().toString())) {
//            String scid = et_zhenmalunxun.getText().toString();
//            int i = Integer.parseInt(scid);
//            if (i == 0) {
//                ToastUtils.showToast("侦码时间不能为0");
//                return true ;
//
//            }
//        } else {
//            ToastUtils.showToast("侦码时间不能为空");
//            return true ;
//        }
//        if (!TextUtils.isEmpty(mEt_tac.getText().toString())) {
//            String scid = mEt_tac.getText().toString();
//            int i = Integer.parseInt(scid);
//            if (0 <= i && i <= 65535) {
//
//            } else {
//                ToastUtils.showToast("TAC应为0-65535之间");
//                return true;
//            }
//        } else {
//            ToastUtils.showToast("TAC不能为空");
//            return true;
//        }
//        if (!TextUtils.isEmpty(et_pci.getText().toString())) {
//            String scid = et_pci.getText().toString();
//            int i = Integer.parseInt(scid);
//            if (0 <= i && i <= 503) {
//
//            } else {
//                ToastUtils.showToast("PCI应为0-503之间");
//                return true;
//            }
//        } else {
//            ToastUtils.showToast("PCI不能为空");
//            return true;
//        }
//        if (!TextUtils.isEmpty(et_cid.getText().toString())) {
//            String scid = et_cid.getText().toString();
//            int i = Integer.parseInt(scid);
//            if (0 <= i && i <= 268435455) {
//
//            } else {
//                ToastUtils.showToast("CID应为0-268435455之间");
//
//                return true;
//            }
//        } else {
//            ToastUtils.showToast("CID不能为空");
//            return true;
//        }
//        //TDD
//        if (!TextUtils.isEmpty(et_tddd.getText().toString())){
//            String scid = et_tddd.getText().toString();
//            int i = Integer.parseInt(scid);
//            if (0 <= i && i <= 72) {
//
//            }else {
//                ToastUtils.showToast("TDD低档应为0-72之间");
//                return true;
//            }
//        }
//
//        if (!TextUtils.isEmpty(et_tddz.getText().toString())){
//            String scid = et_tddz.getText().toString();
//            int i = Integer.parseInt(scid);
//            if (0 <= i && i <= 72) {
//
//            }else {
//                ToastUtils.showToast("TDD中档应为0-72之间");
//                return true;
//            }
//        }
//        if (!TextUtils.isEmpty(et_tddg.getText().toString())){
//            String scid = et_tddg.getText().toString();
//            int i = Integer.parseInt(scid);
//            if (0 <= i && i <= 72) {
//
//            }else {
//                ToastUtils.showToast("TDD高档应为0-72之间");
//                return true;
//            }
//        }
//        //FDD
//        if (!TextUtils.isEmpty(et_fddd.getText().toString())){
//            String scid = et_fddd.getText().toString();
//            int i = Integer.parseInt(scid);
//            if (0 <= i && i <= 72) {
//
//            }else {
//                ToastUtils.showToast("FDD低档应为0-72之间");
//                return true;
//            }
//        }
//
//
//        if (!TextUtils.isEmpty(et_fddz.getText().toString())){
//            String scid = et_fddz.getText().toString();
//            int i = Integer.parseInt(scid);
//            if (0 <= i && i <= 72) {
//
//            }else {
//                ToastUtils.showToast("FDD中档应为0-72之间");
//                return true;
//            }
//        }
//        if (!TextUtils.isEmpty(et_fddg.getText().toString())){
//            String scid = et_fddg.getText().toString();
//            int i = Integer.parseInt(scid);
//            if (0 <= i && i <= 72) {
//
//            }else {
//                ToastUtils.showToast("FDD高档应为0-72之间");
//                return true;
//            }
//        }
//
//
//        return false;
//    }
//}
