package com.sm.android.locations.locations.viewpagermain.NewMainPager.update.dingwei;

import android.annotation.SuppressLint;
import android.content.Context;
import android.icu.text.DecimalFormat;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.sm.android.locations.locations.R;
import com.sm.android.locations.locations.viewpagermain.NewMainPager.SendUtilsNew;

import static com.sm.android.locations.locations.Constant.Constant.BOARDTEMPERATURE1;

public class OpnUpdate {
    @SuppressLint("NewApi")
    public static void upwendu(TextView tv_wendu, ImageView iv_wendu, String BOARDTEMPERATURE1, DecimalFormat df2, Context context, ImageView iv_fengshan, boolean FENGSHANFLAG) {
        if (!TextUtils.isEmpty(BOARDTEMPERATURE1)) {
            try {
                Double i = Double.parseDouble(BOARDTEMPERATURE1);
                df2 = new DecimalFormat("####");
                if (i >= 60) {
                    iv_wendu.setBackground(context.getResources().getDrawable(R.mipmap.wendu_hong));
                    tv_wendu.setTextColor(context.getResources().getColor(R.color.redTextwendu));
                    tv_wendu.setText(df2.format(i) + "°C");
                    if (!FENGSHANFLAG) {
                        FENGSHANFLAG = true;
                        SendUtilsNew.fs(true);
                        iv_fengshan.setBackground(context.getResources().getDrawable(R.mipmap.fengshan));
                    }
                } else {
                    iv_wendu.setBackground(context.getResources().getDrawable(R.mipmap.wendu));
                    tv_wendu.setTextColor(context.getResources().getColor(R.color.white));
                    tv_wendu.setText(df2.format(i) + "°C");
                }
            }catch (NumberFormatException e){
                Log.d("NumberFormatException", "upwendu: "+e.getMessage());
            }


        }
    }
}
