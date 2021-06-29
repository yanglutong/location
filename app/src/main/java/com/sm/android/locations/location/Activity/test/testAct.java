package com.sm.android.locations.location.Activity.test;

import android.view.View;
import android.widget.ImageView;

import com.sm.android.locations.location.Base.BaseActivity;
import com.sm.android.locations.location.R;
import com.sm.android.locations.location.Utils.ToastUtils;

import java.io.UnsupportedEncodingException;

public class testAct  extends BaseActivity {
    ImageView iv_menusss;

    @Override
    protected void initQx() {

    }

    @Override
    protected void init() throws UnsupportedEncodingException {
        iv_menusss=findViewById(R.id.iv_menusss);
        iv_menusss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showToast("chenggong");
            }
        });
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected int getView() {
        return R.layout.act;
    }
}
