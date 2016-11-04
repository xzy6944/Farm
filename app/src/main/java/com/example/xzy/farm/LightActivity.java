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
 * Created by xzy on 2016/9/5.
 */
public class LightActivity extends Activity {
    private TextView mTextView;
    Update mUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.light);
        Intent intent = getIntent();
        String farmID = intent.getStringExtra("farmID");
        TextView range = (TextView) findViewById(R.id.light_range);

        ConnectDatabase connect = new ConnectDatabase();
        ArrayList<Farm> rs = connect.queryFarm("select * from farm where farmID = '" + farmID + "'");
        range.setText(4 + "Lx~" + 6 + "Lx");

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
}
