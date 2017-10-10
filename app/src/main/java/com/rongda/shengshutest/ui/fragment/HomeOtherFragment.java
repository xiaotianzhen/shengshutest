package com.rongda.shengshutest.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.rongda.shengshutest.R;
import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeOtherFragment extends Fragment {

    @BindView(R.id.tv_show)
    TextView tvShow;

    public HomeOtherFragment() {
    }

    public static HomeOtherFragment newInstance(String str) {

        Bundle args = new Bundle();
        args.putString("type", str);
        HomeOtherFragment fragment = new HomeOtherFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_other, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        tvShow.setText(getArguments().getString("type"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
