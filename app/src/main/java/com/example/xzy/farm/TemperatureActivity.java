package com.example.xzy.farm;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by xzy on 2016/5/31.
 */
public class TemperatureActivity extends Activity {
    private TextView mTextView;
    private Button increase;
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
        String farmID = intent.getStringExtra("farmID");
        TextView range = (TextView) findViewById(R.id.temperature_range);

        ConnectDatabase connect = new ConnectDatabase();
        ArrayList<Farm> rs = connect.queryFarm("select * from farm where farmID = '" + farmID + "'");
        range.setText(28 + "℃~" + 32 + "℃");

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
}

