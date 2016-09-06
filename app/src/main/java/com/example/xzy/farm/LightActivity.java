package com.example.xzy.farm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by xzy on 2016/9/5.
 */
public class LightActivity extends Activity {
    private TextView mTextView;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.light);
        Intent intent = getIntent();
        String farmID = intent.getStringExtra("farmID");
        TextView range = (TextView) findViewById(R.id.light_range);

        ConnectDatabase connect = new ConnectDatabase();
        ArrayList<Farm> rs = connect.queryFarm("select * from farm where farmID = " + farmID);
        range.setText(rs.get(0).getLight_min() + "Lx~" + rs.get(0).getLight_max() + "Lx");

        mHandler = new Handler();
        mTextView = (TextView) findViewById(R.id.showLight);

        mHandler.post(new Runnable() {
            @Override
            public void run(){
                mTextView.setText(String.valueOf((int)(Math.random() * 2) + 4) + "Lx");
                mHandler.postDelayed(this, 2000);
            }
        });


    }
}
