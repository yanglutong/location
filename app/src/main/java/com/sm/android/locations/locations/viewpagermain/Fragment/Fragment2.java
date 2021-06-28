package com.sm.android.locations.locations.viewpagermain.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

import android.os.Message;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sm.android.locations.locations.Base.BaseFragment;
import com.sm.android.locations.locations.Base.BaseFragment2;
import com.sm.android.locations.locations.R;
import com.sm.android.locations.locations.Utils.MainUtils.MainUtils;
import com.sm.android.locations.locations.viewpagermain.ViewPagerMainActivity;

import java.net.DatagramPacket;
import java.util.Timer;

import static com.sm.android.locations.locations.viewpagermain.ViewPagerMainActivity.TYPE_VIEWPAGERMAIN;


@SuppressLint("ValidFragment")
public class Fragment2 extends BaseFragment2 {
    protected Context mContext;
    private View view;
    private String name;
    //     Handler mHandler ;
    private static Timer timer1, timer2, timer_wendu;
    private View rootView;//缓存Fragment view
    boolean runThread = false;
    Message message;
    Bundle bundle;
    DatagramPacket dp;
    byte[] buf;
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String s=  bundle.getString("hehe");
//            Log.d("TAGhehe1", "handleMessage: ."+s+"TYPE_VIEWPAGERMAIN"+TYPE_VIEWPAGERMAIN);
            if (TYPE_VIEWPAGERMAIN==2){
                switch (msg.what) {
                    case 1:
//                    tv_522.setText((String) msg.obj);//这里把传过来的值显示在textview上
                        String zt1 = msg.getData().getString("zt1");
//                    Log.d("Fragment0nzq",zt1);
                        Log.d("Fragment0nzq",msg.obj.toString());
                        break;
                    case 100120:
                        Log.d("Fragmentnzq2","状态"+msg.getData().getString("zt1"));
                        break;
                    default:
                        break;
                }
            }

        };
    };

//    @Override
//    public View initView() {
//        mContext = getActivity();
//        view = LayoutInflater.from(mContext).inflate(R.layout.activity_main, null);
//        return view;
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        if(rootView==null){
            rootView=LayoutInflater.from(mContext).inflate(R.layout.activity_main, null);
            buf = new byte[1024];
            dp = new DatagramPacket(buf, buf.length);
            message = new Message();
            bundle = new Bundle();
            timer1 = new Timer();
            timer2 = new Timer();
            timer_wendu = new Timer();
            buf = new byte[1024];
            dp = new DatagramPacket(buf, buf.length);
//        MainUtils.ReceiveMain2(mHandler, message, bundle, timer1, timer2, dp, buf, mContext, runThread);//开启线程监听
        }
        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }

    @Override
    public void initData() {
        super.initData();
//        onAttach(mContext);


//        int bujuLayout = R.layout.activity_view_pager_main;
//        buf = new byte[1024];
//        dp = new DatagramPacket(buf, buf.length);
//        message = new Message();
//        bundle = new Bundle();
//        timer1 = new Timer();
//        timer2 = new Timer();
//        timer_wendu = new Timer();
//        buf = new byte[1024];
//        dp = new DatagramPacket(buf, buf.length);
////        MainUtils.ReceiveMain2(mHandler, message, bundle, timer1, timer2, dp, buf, mContext, runThread);//开启线程监听
        Log.d("tagFragment2", "initData: .");
    }
    private ViewPagerMainActivity mActivity;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
//        mActivity = (ViewPagerMainActivity) context;
//        mActivity.setHandler(mHandler);
    }

}
