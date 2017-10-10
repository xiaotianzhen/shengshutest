package com.rongda.shengshutest.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rongda.shengshutest.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeFragment extends Fragment {

    @BindView(R.id.tab_classify)
    TabLayout tabClassify;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    private HomeV2Fragment mHomeV2Fragment;
    private HomeOtherFragment mHomeOtherFragment;
    private String[] otherType=new String[]{"大白菜","青菜","白萝卜","鸡肉","鱼"};
    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        mHomeV2Fragment=new HomeV2Fragment();
        mHomeOtherFragment=new HomeOtherFragment();
        tabClassify.setupWithViewPager(mViewPager);
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {

                if(position==0){
                    return mHomeV2Fragment;
                }
                mHomeOtherFragment=HomeOtherFragment.newInstance(otherType[position]);
                return mHomeOtherFragment;
            }

            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return otherType[position].toString();
            }
        });
        mViewPager.setCurrentItem(0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(getActivity()).unbind();

    }
}
