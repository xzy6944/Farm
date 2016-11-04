package com.example.xzy.farm;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by mx on 2016/7/13.
 */
public class MobileSettingActivity extends Activity {
    private void dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MobileSettingActivity.this);
        builder.setMessage("确认取消吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                //Code.this.finish();
                Intent intent = new Intent(MobileSettingActivity.this, UsersettingActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }
       @Override
     public void onCreate(Bundle savedInstanceState){
           super.onCreate(savedInstanceState);
           setContentView(R.layout.mobilesetting);
           final EditText mobileText = (EditText)findViewById( R.id.mobiletext) ;
           Button button1 = (Button)findViewById(R.id.mobilebutton1);
           Button button2 = (Button)findViewById(R.id.mobilebutton2);
           button2.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   if(mobileText.getText().toString().length()==0)
                       Toast.makeText(MobileSettingActivity.this,"请输入手机号",Toast.LENGTH_SHORT).show();
                   else {
                       Intent dataintent = getIntent();
                       String farmID = dataintent.getStringExtra("farmID");
                       String userID = dataintent.getStringExtra("userID");
                       ConnectDatabase connectDatabase = new ConnectDatabase();
                       connectDatabase.update("update user set mobile =" + mobileText.getText().toString() + "where ID = '" + userID + "'");
                       Intent intent = new Intent(MobileSettingActivity.this, UsersettingActivity.class);
                       startActivity(intent);
                   }
               }

           });
           button1.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   dialog();
               }
           });
       }
}
