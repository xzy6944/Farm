package com.example.xzy.farm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by mx on 2016/7/9.
 */
public class UsersettingActivity extends Activity {
         @Override
         public void onCreate(Bundle savedInstanceState){
             super.onCreate(savedInstanceState);
             setContentView(R.layout.usersettting);
             Button button = (Button)findViewById(R.id.logout);
             button.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     Intent intent = new Intent(UsersettingActivity.this,LoginActivity.class);
                     startActivity(intent);
                 }
             });
            View toaddresssetting = findViewById(R.id.toaddresssetting);
             toaddresssetting.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     Intent intent = getIntent();
                     String farmID = intent.getStringExtra("farmID");
                     String userID = intent.getStringExtra("userID");
                     intent.putExtra("userID",userID);
                     intent.putExtra("farmID",farmID);
                     intent = new Intent(UsersettingActivity.this,AdreesssettingActivity.class);
                     startActivity(intent);
                 }
             });
             View topasswordsetting = findViewById(R.id.topasswordsetting);
             topasswordsetting.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     Intent dataintent = getIntent();
                     String userID = dataintent.getStringExtra("userID");
                     Intent intent = new Intent(UsersettingActivity.this,PasswordSettingActivity.class);
                     intent.putExtra("userID",userID);
                     startActivity(intent);
                 }
             });
             View tomobilesetting = findViewById(R.id.tomobilesetting);
             tomobilesetting.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     Intent dataintent = getIntent();
                     String farmID = dataintent.getStringExtra("farmID");
                     String userID = dataintent.getStringExtra("userID");
                     Intent intent = new Intent(UsersettingActivity.this,MobileSettingActivity.class);
                     intent.putExtra("farmID",farmID);
                     intent.putExtra("userID",userID);
                     startActivity(intent);
                 }
             });
         }

}
