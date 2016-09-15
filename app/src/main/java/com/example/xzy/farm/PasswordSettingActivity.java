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

import java.util.ArrayList;

/**
 * Created by mx on 2016/7/13.
 */
public class PasswordSettingActivity extends Activity {
    EditText formerpasswordText,newpasswordText;
    private void dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(PasswordSettingActivity.this);
        builder.setMessage("确认取消吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                //Code.this.finish();
                Intent intent = new Intent(PasswordSettingActivity.this, UsersettingActivity.class);
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
          setContentView(R.layout.passwordsetting);
          Button button1 = (Button)findViewById(R.id.passwordbutton1);
          Button button2 = (Button)findViewById(R.id.passwordbutton2);
          formerpasswordText = (EditText) findViewById(R.id.type_old);
          newpasswordText = (EditText) findViewById(R.id.type_new);
          button2.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent dataintent = getIntent();
                  String userID = dataintent.getStringExtra("userID");
                  ConnectDatabase connectDatabase = new ConnectDatabase();
                  ArrayList<User> array = connectDatabase.queryUser("select * from user where ID ="+userID);
                  String formerpassword = array.get(0).getPassword();
                  if(formerpasswordText.getText().toString().length()==0||newpasswordText.getText().toString().length()==0){
                      Toast.makeText(PasswordSettingActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                  }
                  if(!(formerpasswordText.getText().toString().equals(formerpassword)))
                      Toast.makeText(PasswordSettingActivity.this,"输入密码不一致",Toast.LENGTH_SHORT).show();
                  else{
                      connectDatabase.update("update User set password = "+newpasswordText.getText().toString()+"where ID = "+userID);
                      Intent intent = new Intent(PasswordSettingActivity.this,UsersettingActivity.class);
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
