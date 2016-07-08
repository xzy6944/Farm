package com.example.xzy.farm;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

/**
 * Created by xzy on 2016/5/31.
 */
public class TemperatureActivity extends Activity {
    private TextView mTextView;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temperature);

        mHandler = new Handler();
        mTextView = (TextView) findViewById(R.id.showTemperature);

        mHandler.post(new Runnable() {
            @Override
            public void run(){
                mTextView.setText(String.valueOf((int)(Math.random() * 10) + 15) + "℃");
                mHandler.postDelayed(this, 2000);
            }
        });


    }

}

