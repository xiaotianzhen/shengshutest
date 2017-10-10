package com.rongda.shengshutest.ui.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rongda.shengshutest.R;
import com.rongda.shengshutest.ui.widget.AutoScrollTextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeV2Fragment extends Fragment {

    @BindView(R.id.tv_auto)
    AutoScrollTextView tvAuto;
    @BindView(R.id.tv_limit_day)
    TextView tvLimitDay;
    @BindView(R.id.tv_limit_hour)
    TextView tvLimitHour;
    @BindView(R.id.tv_limit_min)
    TextView tvLimitMin;
    @BindView(R.id.tv_limit_second)
    TextView tvLimitSecond;

    private String[] array=new String[]{"阿佛伽伽就感觉","奥确认要覆盖面","情人节痛殴是否明白"};
    private CountDownTimer mCountDownTimer;

    public HomeV2Fragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_v2, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        tvAuto.setList(Arrays.asList(array));
        judgeActivity("2017-07-25 00:00:00.0","2017-10-31 11:24:00.0");
    }

    public void judgeActivity(String start,String end) {

        if (mCountDownTimer!=null){
            mCountDownTimer.cancel();
        }

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
        final String state;
        long countTime;
        long currentTime= System.currentTimeMillis();
        try {
            long startTime=sdf.parse(start).getTime();
            long endTime=sdf.parse(end).getTime();
            if(startTime>currentTime){
                state="距离抢购时间";
                countTime=startTime-currentTime;

            }else if(startTime<currentTime){
                state="距离结束时间";
                countTime=endTime-currentTime;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
