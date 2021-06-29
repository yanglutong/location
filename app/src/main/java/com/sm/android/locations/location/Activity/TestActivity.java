package com.sm.android.locations.location.Activity;

import android.app.Activity;

import android.os.Bundle;

import com.sm.android.locations.location.R;
import com.sm.android.locations.location.Utils.View.MySpinner;

public class TestActivity extends Activity {
    MySpinner spinner;
    public static String TAG = "TestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
//        spinner = findViewById(R.id.sp);
//        List<String> list = new ArrayList<>();
//
//        list.add("你好1");
//        list.add("你好2");
//        list.add("你好3");
//        list.add("你好4");
//        list.add("你好5");
//
//        spinner.setSpData(list, TestActivity.this);
//        spinner.getItem(2);
//        Log.d("nzq", "onCreate: " + spinner.getItem(2));

    }
}
