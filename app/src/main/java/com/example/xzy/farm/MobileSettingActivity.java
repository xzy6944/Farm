package com.example.xzy.farm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by mx on 2016/7/13.
 */
public class MobileSettingActivity extends Activity {
       @Override
     public void onCreate(Bundle savedInstanceState){
           super.onCreate(savedInstanceState);
           setContentView(R.layout.mobilesetting);
           Button button1 = (Button)findViewById(R.id.mobilebutton1);
           Button button2 = (Button)findViewById(R.id.mobilebutton2);
           button1.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent = new Intent(MobileSettingActivity.this,UsersettingActivity.class);
                   startActivity(intent);
               }
           });
           button2.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent = new Intent(MobileSettingActivity.this,UsersettingActivity.class);
                   startActivity(intent);
               }
           });
       }
}
