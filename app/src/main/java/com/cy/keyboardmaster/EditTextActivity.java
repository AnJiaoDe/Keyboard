package com.cy.keyboardmaster;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import com.cy.keyboard.KeyboardActivity;

public class EditTextActivity extends KeyboardActivity {
    private View layout_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        layout_content=findViewById(R.id.layout_content);
        //设置监听器
        setOnCYGlobalLayoutListener(new OnCYGlobalLayoutListener() {
            //键盘刚显示
            @Override
            public void onKeyboardShowedNow(Rect rect) {
                //rect 可视区域
                //screenHight-rect.bottom就是键盘高度
                layout_content.scrollTo(0,screenHight-rect.bottom);

            }
            //键盘刚隐藏,可以对输入的内容进行修改


            @Override
            public void onKeyboardHideNow() {
                layout_content.scrollTo(0,0);


            }
        });
    }
}
