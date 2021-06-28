package com.sm.android.locations.locations.Activity.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.sm.android.locations.locations.Base.BaseActivity;
import com.sm.android.locations.locations.R;
import com.sm.android.locations.locations.Utils.ToastUtils;

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
