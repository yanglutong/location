package com.sm.android.locations.locations.Utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sm.android.locations.locations.App.App;
import com.sm.android.locations.locations.R;

public class ToastUtils {
    //    //居中 自定义布局Toast
    public static void showToast(String text) {

        Context contexts = App.getContexts();
        try {
            Toast toast = Toast.makeText(contexts, text, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, -250);

            View view = LayoutInflater.from(contexts).inflate(R.layout.toast_item, null);
            TextView tv = (TextView) view.findViewById(R.id.tv);
            tv.setText(text + "");
            toast.setView(view);
            toast.show();
        } catch (Exception e) {

        }
    }

    //    //居中 自定义布局Toast
    public static void showToastDown(String text) {

        Context contexts = App.getContexts();
        try {
            Toast toast = Toast.makeText(contexts, text, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM, 0, -250);
            View view = LayoutInflater.from(contexts).inflate(R.layout.toast_item, null);
            TextView tv = (TextView) view.findViewById(R.id.tv);
            tv.setText(text + "");
            toast.setView(view);
            toast.show();
        } catch (Exception e) {
        }
    }
}
