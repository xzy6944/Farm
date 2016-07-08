package com.example.xzy.farm;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

/**
 * Created by xzy on 2016/6/2.
 */
public class HumidityActivity extends Activity{
    private TextView mTextView;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.humidity);

        mHandler = new Handler();
        mTextView = (TextView) findViewById(R.id.showHumidity);

        mHandler.post(new Runnable() {
            @Override
            public void run(){
                mTextView.setText(String.valueOf((int)(Math.random() * 20) + 50) + "%");
                mHandler.postDelayed(this, 2000);
            }
        });
    }
}
