package com.example.xzy.farm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);

        Button button_Login = (Button) findViewById(R.id.login_button);
        final EditText username = (EditText) findViewById(R.id.username_edit);
        final EditText password = (EditText) findViewById(R.id.password_edit);
        button_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u = username.getText().toString();
                String p = password.getText().toString();
                if(u.isEmpty() || p.isEmpty()){
                    Toast.makeText(LoginActivity.this, "请输入账号和密码！",Toast.LENGTH_SHORT).show();
                }else{
                    ConnectDatabase connect = new ConnectDatabase();
                    ArrayList<User> rs = connect.queryUser("select * from User where ID = " + u);
                    if(p.equals(rs.get(0).getPassword())){
                        Toast.makeText(LoginActivity.this, "登陆成功！",Toast.LENGTH_SHORT).show();
                        ArrayList<Farm> rs1 = connect.queryFarm("select * from Farm where user_id = " + u);
                        String farmID = rs1.get(0).getFarmID();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("farmID", farmID);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "账号或密码错误！",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        final TextView text_Register = (TextView) findViewById(R.id.register_link);
        text_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,Code.class);
                startActivity(intent);
            }
        });

    }
}
