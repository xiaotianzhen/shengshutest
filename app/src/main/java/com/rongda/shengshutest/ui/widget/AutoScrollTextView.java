package com.rongda.shengshutest.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.rongda.shengshutest.R;
import com.rongda.shengshutest.util.ThreadPool;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Buster on 2017/7/5.
 */

public class AutoScrollTextView extends TextSwitcher implements ViewSwitcher.ViewFactory {
    private Context mContext;
    private float textSize;
    private int textColor;
    private long mAnimTime, mStayTime;
    private List<String> mDatas;

    private boolean loop = true, isRefresh = false;
    private final int LOOP = 100, REFRESH = 200;

    public AutoScrollTextView(Context context) {
        this(context, null);
    }

    public AutoScrollTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public void init(Context mContext, AttributeSet attrs) {
        this.mContext = mContext;
        mDatas = new LinkedList<>();
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.AutoScrollTextView);
        textSize = typedArray.getDimension(R.styleable.AutoScrollTextView_textSize, 12);
        textColor = typedArray.getColor(R.styleable.AutoScrollTextView_textColor, Color.BLACK);
        mAnimTime = typedArray.getInteger(R.styleable.AutoScrollTextView_animationDuration, 1000);
        mStayTime = typedArray.getInteger(R.styleable.AutoScrollTextView_stayDuration, 2500);
        typedArray.recycle();

        setFactory(this);
      /*setInAnimation(new TurnAnimation(true, true));
        setOutAnimation(new TurnAnimation(false, true));*/
        Animation outAnimation=AnimationUtils.loadAnimation(mContext,R.anim.anim_top_out);
        outAnimation.setInterpolator(new AccelerateInterpolator());
        Animation inAnimation=AnimationUtils.loadAnimation(mContext,R.anim.anim_bottom_in);
        inAnimation.setInterpolator(new AccelerateInterpolator());
        setOutAnimation(outAnimation);
        setInAnimation(inAnimation);
        ThreadPool.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                while (loop) {
                    SystemClock.sleep(mStayTime);
                    if (isRefresh)
                        mHandler.sendEmptyMessage(REFRESH);
                }
            }
        });
    }

    public void setList(List<String> datas) {
        mDatas.clear();
        mDatas.addAll(datas);
        isRefresh = true;
    }

    public void setLoop(boolean isLoop) {
        this.loop = isLoop;
    }

    @Override
    public View makeView() {
        TextView mTextView = new TextView(mContext);
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        mTextView.setTextColor(textColor);
        mTextView.setSingleLine(true);
        mTextView.setEllipsize(TextUtils.TruncateAt.END);
        mTextView.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
        mTextView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return mTextView;
    }

/*    private class TurnAnimation extends Animation {
        private Camera mCamera;
        private float mCenterX, mCenterY;
        private final boolean mTurnIn, mTurnUp;

        public TurnAnimation(boolean mTurnIn, boolean mTurnUp) {
            this.mTurnIn = mTurnIn;
            this.mTurnUp = mTurnUp;
        }

        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
            mCamera = new Camera();
            mCenterX = getWidth();
            mCenterY = getHeight();
            setDuration(mAnimTime);
            setFillAfter(false);  //不保持在结束状态
            setInterpolator(new AccelerateInterpolator()); //设置匀加速器
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            final int direction = mTurnUp ? 1 : -1;
            final Matrix matrix = t.getMatrix();

            mCamera.save();
            if (mTurnIn) {
                mCamera.translate(0, direction * mCenterY * (interpolatedTime - 1.0f), 0);
            } else {
                mCamera.translate(0, direction * mCenterY * (interpolatedTime), 0);
            }
            mCamera.getMatrix(matrix);
            mCamera.restore();
        }
    }*/

    private Handler mHandler = new Handler() {
        int number = 0;

        @Override
        public void handleMessage(Message msg) {

            if (msg.what == REFRESH) {
                if (mDatas.size() > 0 && number < mDatas.size()) {
                    setText(mDatas.get(number));
                    number++;
                    if (number == mDatas.size()) {
                        number = 0;
                    }
                }
            }
        }
    };
}
