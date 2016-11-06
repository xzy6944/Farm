package com.example.xzy.farm;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by xzy on 2016/5/31.
 */
public class TemperatureActivity extends Activity {
    private TextView mTextView;
    private Button increase;
    private TextView range;
    String farmID;
    Update mUpdate;
    Handler m = new Handler();
//    Runnable runnable = new Runnable() {
//        @Override
//        public void run(){
//            mTextView.setText(String.valueOf(new DecimalFormat("##0.0").format(Math.random() * 2 + 28)) + "℃");
//            m.postDelayed(this, 2000);
//        }
//    };
//    Handler mHandler =  new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what){
//                case 1:
//                    m.post(runnable);
//                    break;
//                case 2:
//                    m.post(new Runnable() {
//                        @Override
//                        public void run(){
//                            mTextView.setText(String.valueOf(new DecimalFormat("##0.0").format(Math.random() * 2 + 29)) + "℃");
//                            m.postDelayed(this, 2000);
//                        }
//                    });
//                    break;
//                case 3:
//                    m.removeCallbacks(runnable);
//                    mTextView.setText(String.valueOf(new DecimalFormat("##0.0").format(Math.random() * 2 + 50)) + "℃");
//                    break;
//            }
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temperature);
        Intent intent = getIntent();
        farmID = intent.getStringExtra("farmID");;

        Button detail = (Button) findViewById(R.id.display_detail);
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopwindow();
            }
        });

//        increase = (Button)findViewById(R.id.increasetemperature);
        mTextView = (TextView) findViewById(R.id.showTemperature);


//        increase.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final Message message = new Message();
//                message.what = 2;
//                mHandler.sendMessage(message);
//            }
//        });
        IntentFilter filter = new IntentFilter();
        filter.addAction("WD");
        mUpdate = new Update();
        registerReceiver(mUpdate, filter);
        final  Handler mHandler ;
        mHandler = new Handler();
        mHandler.post(new Runnable() {
            @Override
            public void run(){
                mTextView.setText(String.valueOf(new DecimalFormat("##0.0").format(Math.random() * 2 + 28)) + "℃");
                mHandler.postDelayed(this, 2000);
            }
        });

//        final Message message = new Message();
//        message.what = 1;
//        mHandler.sendMessage(message);
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                final Message message = new Message();
//                message.what = 3;
//                mHandler.sendMessage(message);
//            }
//        }).start();

    }

    private class Update extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            mTextView.setText(intent.getStringExtra("WD") + "℃");
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
        View view = inflater.inflate(R.layout.popwindow_with_button_layout, null);

        range = (TextView)view.findViewById(R.id.range);
        ConnectDatabase connect = new ConnectDatabase();
        ArrayList<Farm> rs = connect.queryFarm("select * from farm where farmID = '" + farmID + "'");
        range.setText(rs.get(0).getTemperature_min() + "℃~" + rs.get(0).getTemperature_max() + "℃");

        ((TextView) view.findViewById(R.id.down)).setText("降温");
        ((TextView) view.findViewById(R.id.up)).setText("升温");

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
        window.showAtLocation(TemperatureActivity.this.findViewById(R.id.display_detail),
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

