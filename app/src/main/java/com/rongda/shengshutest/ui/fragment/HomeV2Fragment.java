package com.rongda.shengshutest.ui.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rongda.shengshutest.R;
import com.rongda.shengshutest.adapter.TestAdapter;
import com.rongda.shengshutest.ui.widget.AutoScrollTextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    @BindView(R.id.tv_showtime_title)
    TextView tvShowTimeTitle;
    @BindView(R.id.rv_test)
    RecyclerView mRecyclerView;
    private String[] array = new String[]{"阿佛伽伽就感觉", "奥确认要覆盖面", "情人节痛殴是否明白"};
    private CountDownTimer mCountDownTimer;
    private static final String ACTRUSH = "ACTRUSH";
    private static final String ACTFINISH = "ACTFINISH";
    private static String type = "";

    private List<String> mList = new ArrayList<String>();

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
        ButterKnife.bind(this, view);
        tvAuto.setList(Arrays.asList(array));
        judgeActivity("2017-07-25 00:00:00.0", "2017-10-31 11:24:00.0");
        initData();
        mRecyclerView.setAdapter(new TestAdapter(mList));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
       /* mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);

                if (parent.getChildPosition(view)==0) {
                    outRect.right = -100;
                }
            }
        });*/
    }

    public void judgeActivity(String start, String end) {

        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
        String state = "距离抢购时间";
        long countTime = 0;
        long currentTime = System.currentTimeMillis();
        try {
            long startTime = sdf.parse(start).getTime();
            final long endTime = sdf.parse(end).getTime();
            if (startTime > currentTime) {
                state = "距离抢购时间";
                countTime = startTime - currentTime;
                type = ACTRUSH;

            } else if (startTime < currentTime) {
                state = "距离结束时间";
                countTime = endTime - currentTime;
                type = ACTFINISH;
            }

            tvShowTimeTitle.setText(state);

            mCountDownTimer = new CountDownTimer(countTime, 1000) {

                int day, hour, min, seconds;
                long hourSeconds, minSeconds;
                String resDay, resHour, resMin, resSecond;

                @Override
                public void onTick(long l) {
                    day = (int) (l / (24 * 3600 * 1000));
                    hourSeconds = l % (24 * 3600 * 1000);
                    hour = (int) (hourSeconds / (3600 * 1000));
                    minSeconds = hourSeconds % (3600 * 1000);
                    min = (int) (minSeconds / (60 * 1000));
                    seconds = (int) (minSeconds % (60 * 1000) / 1000);
                    if (day / 10 == 0)
                        resDay = "0" + day;
                    else
                        resDay = day + "";
                    if (hour / 10 == 0)
                        resHour = "0" + hour;
                    else
                        resHour = hour + "";
                    if (min / 10 == 0)
                        resMin = "0" + min;
                    else
                        resMin = min + "";
                    if (seconds / 10 == 0)
                        resSecond = "0" + seconds;
                    else resSecond = seconds + "";

                    tvLimitDay.setText(resDay);
                    tvLimitHour.setText(resHour);
                    tvLimitMin.setText(resMin);
                    tvLimitSecond.setText(resSecond);
                }

                @Override
                public void onFinish() {
                    if (type.equals("ACTRUSH")) {
                        mCountDownTimer = new CountDownTimer(endTime - System.currentTimeMillis(), 1000) {

                            int day, hour, min, seconds;
                            long hourSeconds, minSeconds;
                            String resDay, resHour, resMin, resSecond;

                            @Override
                            public void onTick(long l) {
                                day = (int) (l / (24 * 3600 * 1000));
                                hourSeconds = l % (24 * 3600 * 1000);
                                hour = (int) (hourSeconds / (3600 * 1000));
                                minSeconds = hourSeconds % (3600 * 1000);
                                min = (int) (minSeconds / (60 * 1000));
                                seconds = (int) (minSeconds % (60 * 1000) / 1000);
                                if (day / 10 == 0)
                                    resDay = "0" + day;
                                else
                                    resDay = day + "";
                                if (hour / 10 == 0)
                                    resHour = "0" + hour;
                                else
                                    resHour = hour + "";
                                if (min / 10 == 0)
                                    resMin = "0" + min;
                                else
                                    resMin = min + "";
                                if (seconds / 10 == 0)
                                    resSecond = "0" + seconds;
                                else resSecond = seconds + "";

                                tvLimitDay.setText(resDay);
                                tvLimitHour.setText(resHour);
                                tvLimitMin.setText(resMin);
                                tvLimitSecond.setText(resSecond);
                            }

                            @Override
                            public void onFinish() {
                                Toast.makeText(getActivity(), "活动已结束", Toast.LENGTH_SHORT).show();
                            }
                        }.start();
                    } else if (type.equals("ACTFINISH")) {
                        Toast.makeText(getActivity(), "活动已结束", Toast.LENGTH_SHORT).show();
                    }
                }
            }.start();

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void initData() {

        for (int i = 0; i < 10; i++) {
            mList.add("我是第" + i + "选项卡");
        }
    }
}
