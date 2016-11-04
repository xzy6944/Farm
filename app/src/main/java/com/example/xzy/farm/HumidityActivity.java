package com.example.xzy.farm;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by xzy on 2016/6/2.
 */
public class HumidityActivity extends Activity{
    private TextView mTextView;
    Update mUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.humidity);
        Intent intent = getIntent();
        String farmID = intent.getStringExtra("farmID");
        TextView range = (TextView) findViewById(R.id.humidity_range);

        ConnectDatabase connect = new ConnectDatabase();
        ArrayList<Farm> rs = connect.queryFarm("select * from farm where farmID = '" + farmID + "'");
        range.setText(60 + "%~" + 70 + "%");

        mTextView = (TextView) findViewById(R.id.showHumidity);

        IntentFilter filter = new IntentFilter();
        filter.addAction("SD");
        mUpdate = new Update();
        registerReceiver(mUpdate, filter);
        final  Handler mHandler;
        mHandler = new Handler();
        mHandler.post(new Runnable() {
           @Override
            public void run(){
                mTextView.setText(String.valueOf((int)(Math.random() * 5) + 50) + "%");
                mHandler.postDelayed(this, 2000);
            }
        });
    }

    private class Update extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            mTextView.setText(intent.getStringExtra("SD") + "%");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        unregisterReceiver(mUpdate);
    }
}
