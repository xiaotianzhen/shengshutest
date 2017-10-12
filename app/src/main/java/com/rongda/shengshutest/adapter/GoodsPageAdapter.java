package com.rongda.shengshutest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rongda.shengshutest.R;
import com.rongda.shengshutest.ui.widget.CarouselViewPager;

/**
 * Created by Buster on 2017/10/12.
 */

public class GoodsPageAdapter extends CarouselPagerAdapter<CarouselViewPager> {

    private Context mContext;
    public GoodsPageAdapter(Context context, CarouselViewPager viewPager) {
        super(viewPager);
        this.mContext=context;
    }


    @Override
    public Object instantiateRealItem(ViewGroup container, int position) {

        View view= LayoutInflater.from(mContext).inflate(R.layout.item_home_limittime,container,false);
        container.addView(view);
        return view;
    }

    @Override
    public int getRealDataCount() {
        return 3;
    }
}