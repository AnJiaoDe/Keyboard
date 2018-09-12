package com.cy.keyboard;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

/**
 * Created by cy on 2018/9/6.,假键盘，用在列表中的edittext
 */

public abstract class KeyboardMockActivity extends AppCompatActivity implements ViewTreeObserver.OnGlobalLayoutListener {

    private View view_keyboard;

    public int screenHight = 0;
    private OnCYGlobalLayoutListener onCYGlobalLayoutListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenHight = ScreenUtils.getScreenHeight(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
        addGlobal();
    }

    @Override
    protected void onStop() {
        super.onStop();
        removeGlobal();
    }

    private void addGlobal() {
        if (view_keyboard == null) return;
        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(this);

    }

    private void removeGlobal() {
        getWindow().getDecorView().getViewTreeObserver().removeOnGlobalLayoutListener(this);

    }

    @Override
    public void onGlobalLayout() {

        if (view_keyboard == null) {
            removeGlobal();
            return;
        }
//        CYLogUtils.log("布局变化", "---------------------------------------");
        Rect r = new Rect();
        //获取当前界面可视部分
        getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
        //如果屏幕高度和Window可见区域高度差值大于整个屏幕高度的1/3，则表示软键盘显示中，否则软键盘为隐藏状态。
//        int heightDifference = screenHeight - (r.bottom - r.top);
        boolean isKeyboardShowing = r.bottom < ScreenUtils.getScreenHeight(getApplicationContext());

        if (isKeyboardShowing) {
            if (view_keyboard.getVisibility() == View.VISIBLE) return;
//            Toast.makeText(this, "键盘显示", Toast.LENGTH_SHORT).show();
            removeGlobal();


            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view_keyboard.getLayoutParams();

            layoutParams.height = onCYGlobalLayoutListener.getViewKeyboardHeight(r);

            view_keyboard.setVisibility(View.VISIBLE);


            onCYGlobalLayoutListener.onKeyboardShowedNow(r);

            //会导致布局不断刷新，无限制回调当前方法,所以做延迟回调处理

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    addGlobal();
                }
            }, 100);
        } else {
//            Toast.makeText(this, "键盘隐藏", Toast.LENGTH_SHORT).show();
            if (view_keyboard.getVisibility() == View.GONE) return;

            removeGlobal();
            view_keyboard.setVisibility(View.GONE);

            onCYGlobalLayoutListener.onKeyboardHideNow();
            //会导致布局不断刷新，无限制回调当前方法,所以做延迟回调处理

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    addGlobal();
                }
            }, 100);


        }

    }

    public int getScreenHight() {
        return screenHight;
    }

    /**
     * @param view_keyboard            假键盘布局，使用时必须自行设置其高度，以满足 需要
     * @param onCYGlobalLayoutListener
     */
    public void setOnCYGlobalLayoutListener(View view_keyboard, OnCYGlobalLayoutListener onCYGlobalLayoutListener) {
        this.view_keyboard = view_keyboard;
        this.onCYGlobalLayoutListener = onCYGlobalLayoutListener;

        addGlobal();
    }

    public interface OnCYGlobalLayoutListener {
        public void onKeyboardShowedNow(Rect rect);//键盘刚显示

        public void onKeyboardHideNow();//键盘刚隐藏

        public int getViewKeyboardHeight(Rect rect);//返回假键盘的高度,rect 可视区域
    }
}
