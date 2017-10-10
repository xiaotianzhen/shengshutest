package com.rongda.shengshutest;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.rongda.shengshutest.ui.fragment.AccountFragment;
import com.rongda.shengshutest.ui.fragment.CarFragment;
import com.rongda.shengshutest.ui.fragment.FoodFragment;
import com.rongda.shengshutest.ui.fragment.HomeFragment;
import com.rongda.shengshutest.ui.fragment.TypeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.ll_home)
    LinearLayout mLlHome;
    @BindView(R.id.ll_classify)
    LinearLayout mLlClassify;
    @BindView(R.id.ll_menu)
    LinearLayout mLlMenu;
    @BindView(R.id.rl_car)
    RelativeLayout mRlCar;
    @BindView(R.id.ll_person)
    LinearLayout mLlPerson;
    private HomeFragment homeFragment;
    private TypeFragment typeFragment;
    private CarFragment carFragment;
    private FoodFragment foodFragment;
    private AccountFragment accountFragment;
    private FragmentManager fManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fManager=getSupportFragmentManager();
        mLlHome.performClick();



    }

    @OnClick({R.id.ll_home, R.id.ll_classify, R.id.ll_menu, R.id.rl_car, R.id.ll_person})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.ll_home:
                selectTab(1);
                break;
            case R.id.ll_classify:
                selectTab(2);
                break;
            case R.id.ll_menu:
                selectTab(3);
                break;
            case R.id.rl_car:
                selectTab(4);
                break;
            case R.id.ll_person:
                selectTab(5);
                break;
        }


    }

    private void selectTab(int i) {

        FragmentTransaction fragmentTransaction = fManager.beginTransaction();
        hideAllFragment(fragmentTransaction);
        clearAllSelect();

        switch (i) {

            case 1:
                mLlHome.setSelected(true);
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    fragmentTransaction.add(R.id.fl_content, homeFragment);
                } else {
                    fragmentTransaction.show(homeFragment);
                }

                break;
            case 2:
                mLlClassify.setSelected(true);
                if (typeFragment == null) {
                    typeFragment = new TypeFragment();
                    fragmentTransaction.add(R.id.fl_content, typeFragment);
                }else {
                    fragmentTransaction.show(typeFragment);
                }
                break;
            case 3:
                mLlMenu.setSelected(true);
                if (foodFragment == null) {
                    foodFragment = new FoodFragment();
                    fragmentTransaction.add(R.id.fl_content, foodFragment);
                }else {
                    fragmentTransaction.show(foodFragment);
                }
                break;
            case 4:
                mRlCar.setSelected(true);
                if (carFragment == null) {
                    carFragment = new CarFragment();
                    fragmentTransaction.add(R.id.fl_content, carFragment);
                }else {
                    fragmentTransaction.show(carFragment);
                }
                break;
            case 5:
                mLlPerson.setSelected(true);
                if (accountFragment == null) {
                    accountFragment = new AccountFragment();
                    fragmentTransaction.add(R.id.fl_content, accountFragment);
                }else {
                    fragmentTransaction.show(accountFragment);
                }
                break;

        }

        fragmentTransaction.commitAllowingStateLoss();
    }

    private void clearAllSelect() {
        mLlHome.setSelected(false);
        mLlClassify.setSelected(false);
        mLlMenu.setSelected(false);
        mRlCar.setSelected(false);
        mLlPerson.setSelected(false);
    }

    private void hideAllFragment(FragmentTransaction fragmentTransaction) {

        if (homeFragment != null)
            fragmentTransaction.hide(homeFragment);
        if (typeFragment != null)
            fragmentTransaction.hide(typeFragment);
        if (carFragment != null)
            fragmentTransaction.hide(carFragment);
        if (foodFragment != null)
            fragmentTransaction.hide(foodFragment);
        if (accountFragment != null)
            fragmentTransaction.hide(accountFragment);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(this).unbind();
    }
}
