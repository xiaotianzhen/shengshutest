package com.rongda.shengshutest.util;

import android.os.Build;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by Buster on 2017/10/12.
 */

public class GalleryTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View view, float position) {
        float scale = 0.5f;
        float scaleValue = 1 - Math.abs(position) * scale;
        view.setScaleX(scaleValue);
        view.setScaleY(scaleValue);
        if(position > -0.25 && position < 0.25){
            view.setAlpha(0);
        }else {
            view.setAlpha(scaleValue);
        }

        view.setAlpha(scaleValue);
        view.setPivotX(view.getWidth() * (1 - position - (position > 0 ? 1 : -1) * 0.75f) * scale);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setElevation(position > -0.25 && position < 0.25 ? 1 : 0);
        }
    }
}