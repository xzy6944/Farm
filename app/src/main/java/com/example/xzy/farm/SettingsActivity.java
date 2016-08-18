package com.example.xzy.farm;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        View tousersetting = findViewById(R.id.settinglayout1);
        tousersetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this,UsersettingActivity.class);
                startActivity(intent);
            }
        });
        View tofarmsetting = findViewById(R.id.settinglayout2);
        tofarmsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this,FarmSettingActivity.class);
                startActivity(intent);
            }
        });
        View totatalsetting = findViewById(R.id.settinglayout3);
        totatalsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this,TotalsettingActivity.class);
                startActivity(intent);
            }
        });
//        ImageView ima = (ImageView)findViewById(R.id.settingimage);
//        ima.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(SettingsActivity.this,MainActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}
