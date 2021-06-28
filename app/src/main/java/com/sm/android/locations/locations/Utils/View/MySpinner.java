package com.sm.android.locations.locations.Utils.View;

import android.content.Context;

import android.location.LocationManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.sm.android.locations.locations.Activity.Main.IT.SpinnerCallback;
import com.sm.android.locations.locations.R;

import java.util.List;

import static com.sm.android.locations.locations.Activity.TestActivity.TAG;


public class MySpinner extends LinearLayout {
    Spinner sp;
    Context context;
    View view;
    ArrayAdapter<String> adapter;
    String item = "";

    public MySpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        LayoutInflater lInflater = LayoutInflater.from(context);
        view = lInflater.inflate(R.layout.view_spinner, this);
        sp = view.findViewById(R.id.Mysp);
        sp.setGravity(Gravity.CENTER_HORIZONTAL);
    }

    public void setSpData(List<String> StringList, final int flag, final SpinnerCallback spinnerCallback, Context context) {
        if (StringList.size() > 0 && StringList != null) {
            adapter = new ArrayAdapter<String>(context, R.layout.spinner_item
                    , StringList);
            sp.setAdapter(adapter);
            sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    item = adapter.getItem(i);
                    if (flag == 1) {
                        spinnerCallback.CallItemSp1(item);
                    }
                    if (flag == 2) {
                        spinnerCallback.CallItemSp2(item);
                    }
                    Log.d(TAG, "onItemSelected: " + item);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
    }

    public String getItem() {
        return item;


    }
}
