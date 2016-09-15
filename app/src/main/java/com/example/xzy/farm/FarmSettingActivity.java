package com.example.xzy.farm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

/**
 * Created by mx on 2016/7/10.
 */
public class FarmSettingActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farmsetting);
        View totpsetting = findViewById(R.id.totpsetting);
        totpsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                String farmID = intent.getStringExtra("farmID");
                intent = new Intent(FarmSettingActivity.this,TemperaturesettingActivity.class);
                intent.putExtra("farmID",farmID);
                startActivity(intent);
            }
        });
        View tohdsetting = findViewById(R.id.tohumiditysetting);
        tohdsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                String farmID = intent.getStringExtra("farmID");
                intent = new Intent(FarmSettingActivity.this,HumiditySettingActivity.class);
                intent.putExtra("farmID",farmID);
                startActivity(intent);
            }
        });
        View tolightsetting = findViewById(R.id.tolightsetting);
        tolightsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =getIntent();
                String farmID = intent.getStringExtra("farmID");
                intent = new Intent(FarmSettingActivity.this,LightsettingActivity.class);
                intent.putExtra("farmID",farmID);
                startActivity(intent);
            }
        });
        View tobtpsetting = findViewById(R.id.tobtpsetting);
        tobtpsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =getIntent();
                String farmID = intent.getStringExtra("farmID");
                intent = new Intent(FarmSettingActivity.this,BodyTemperaturesettingActivity.class);
                intent.putExtra("farmID",farmID);
                startActivity(intent);
            }
        });

        final ConnectDatabase connect = new ConnectDatabase();
        Switch warning = (Switch)findViewById(R.id.security_warning);
        warning.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    connect.setIOT(5, "安全提示开启", 1 + "", 0 + "");
                }else {
                    connect.setIOT(5, "安全提示关闭", 0 + "", 0 + "");

                }
            }
        });
    }
}
