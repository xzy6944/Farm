package com.example.xzy.farm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
                Intent intent = new Intent(FarmSettingActivity.this,TemperaturesettingActivity.class);
                startActivity(intent);
            }
        });
        View tohdsetting = findViewById(R.id.tohumiditysetting);
        tohdsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FarmSettingActivity.this,HumiditySettingActivity.class);
                startActivity(intent);
            }
        });
        View tolightsetting = findViewById(R.id.tolightsetting);
        tolightsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FarmSettingActivity.this,LightsettingActivity.class);
                startActivity(intent);
            }
        });
        View towindsetting = findViewById(R.id.towindsetting);
        towindsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FarmSettingActivity.this,WindsettingActivity.class);
                startActivity(intent);
            }
        });
        View tobtpsetting = findViewById(R.id.tobtpsetting);
        tobtpsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FarmSettingActivity.this,BodyTemperaturesettingActivity.class);
                startActivity(intent);
            }
        });
    }



}
