package com.cy.keyboardmaster;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import com.cy.cyrvadapter.adapter.RVAdapter;
import com.cy.cyrvadapter.recyclerview.VerticalRecyclerView;
import com.cy.keyboard.KeyboardMockActivity;

import java.util.ArrayList;
import java.util.List;

public class RVEdittextActivity extends KeyboardMockActivity {
    private RVAdapter<String> rvAdapter;

    private View view_keybord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rvedittext);

        List<String> list = new ArrayList<>();
        list.add("dojij");
        list.add("dojij");
        list.add("dojij");
        list.add("dojij");
        list.add("dojij");
        list.add("dojij");
        list.add("dojij");
        list.add("dojij");
        list.add("dojij");
        list.add("dojij");
        list.add("dojij");
        list.add("dojij");
        list.add("dojij");
        list.add("dojij");
        list.add("dojij");
        list.add("dojij");
        list.add("dojij");
        list.add("dojij");
        list.add("dojij");
        list.add("dojij");
        list.add("dojij");
        list.add("dojij");
        list.add("dojij");
        list.add("dojij");
        list.add("dojij");
        rvAdapter = new RVAdapter<String>(list) {
            @Override
            public void bindDataToView(RVViewHolder holder, int position, String bean, boolean isSelected) {

            }

            @Override
            public int getItemLayoutID(int position, String bean) {
                return R.layout.item_price_modify;
            }

            @Override
            public void onItemClick(int position, String bean) {

            }
        };
        //万能适配器：https://github.com/AnJiaoDe/RecyclerViewAdapter
        ((VerticalRecyclerView) findViewById(R.id.vrv)).setAdapter(rvAdapter);
        //设置监听器
        setOnCYGlobalLayoutListener(view_keybord, new OnCYGlobalLayoutListener() {
            //键盘刚显示
            @Override
            public void onKeyboardShowedNow(Rect rect) {

            }

            //键盘刚隐藏,可以对输入的内容进行修改
            @Override
            public void onKeyboardHideNow() {


            }

            //假键盘高度
            @Override
            public int getViewKeyboardHeight(Rect rect) {
                return screenHight-rect.bottom;//screenHight-rect.bottom就是键盘高度
            }
        });
    }
}
