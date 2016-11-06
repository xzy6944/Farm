package com.example.xzy.farm;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by xzy on 2016/9/5.
 */
public class LightActivity extends Activity {
    private TextView mTextView;
    private TextView range;
    String farmID;
    Update mUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.light);
        Intent intent = getIntent();
        farmID = intent.getStringExtra("farmID");;

        Button detail = (Button) findViewById(R.id.display_detail);
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopwindow();
            }
        });

        mTextView = (TextView) findViewById(R.id.showLight);

        IntentFilter filter = new IntentFilter();
        filter.addAction("GZ");
        mUpdate = new Update();
        registerReceiver(mUpdate, filter);
        final  Handler mHandler ;
        mHandler = new Handler();
        mHandler.post(new Runnable() {
            @Override
           public void run(){
               mTextView.setText(String.valueOf((int) (Math.random() * 2) + 5.0) + "Lx");
               mHandler.postDelayed(this, 2000);
           }
        });
    }

    private class Update extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            mTextView.setText(intent.getStringExtra("GZ") + "Lx");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        unregisterReceiver(mUpdate);
    }

    private void showPopwindow() {
        // 利用layoutInflater获得View
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.popwindow_without_button_layout, null);

        range = (TextView)view.findViewById(R.id.range);
        range.setText(4 + "Lx~" + 6 + "Lx");

        ((TextView)view.findViewById(R.id.info_ps)).setText("*光照在4Lx~6Lx最宜，\n光照强度低时我们会自动补光。");

        // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()
        PopupWindow window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);

        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setFocusable(true);


        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        window.setBackgroundDrawable(dw);


        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        // 在底部显示
        window.showAtLocation(LightActivity.this.findViewById(R.id.display_detail),
                Gravity.BOTTOM, 0, 0);


        //popWindow消失监听方法
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                System.out.println("popWindow消失");
            }
        });

    }
}
