package com.example.xzy.farm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by xzy on 2016/6/2.
 */
public class CO2Activity extends Activity {
    private TextView mTextView;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.co2);
        Intent intent = getIntent();
        String farmID = intent.getStringExtra("farmID");
        TextView range = (TextView) findViewById(R.id.co2_range);

        ConnectDatabase connect = new ConnectDatabase();
        ArrayList<Farm> rs = connect.queryFarm("select * from farm where farmID = " + farmID);
        range.setText(rs.get(0).getWind_max() + "%以下");

        mHandler = new Handler();
        mTextView = (TextView) findViewById(R.id.showCO2);

        mHandler.post(new Runnable() {
            @Override
            public void run(){
                mTextView.setText("0." + String.valueOf((int)(Math.random() * 7) + 5) + "%");
                mHandler.postDelayed(this, 2000);
            }
        });
    }
}
