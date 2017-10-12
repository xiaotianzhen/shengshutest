package com.rongda.shengshutest.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rongda.shengshutest.R;
import com.rongda.shengshutest.adapter.GoodsPageAdapter;
import com.rongda.shengshutest.ui.widget.CarouselViewPager;
import com.rongda.shengshutest.util.GalleryTransformer;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TypeFragment extends Fragment {

    @BindView(R.id.id_viewpager)
    CarouselViewPager idViewpager;

    public TypeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_type, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        GoodsPageAdapter adapter = new GoodsPageAdapter(getActivity(), idViewpager);
        idViewpager.setOffscreenPageLimit(3);
        idViewpager.setAdapter(adapter);
        // 设置轮播时间
        idViewpager.setTimeOut(5);
        // 设置3d效果
        idViewpager.setPageTransformer(true, new GalleryTransformer());
        // 设置已经有数据了，可以进行轮播，一般轮播的图片等数据是来源于网络，网络数据来了后才设置此值，此处因为是demo，所以直接赋值了
        idViewpager.setHasData(true);
        // 开启轮播
        idViewpager.startTimer();
    }
}
