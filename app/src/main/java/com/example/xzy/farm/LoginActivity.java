package com.example.xzy.farm;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);

        if(!Settings.canDrawOverlays(this)){
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, REQUEST_CODE);
        }

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

//                    //TODO test
//                    {Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                    intent.putExtra("farmID", "1");
//                    intent.putExtra("userID", "1");
//                    startActivity(intent);}

                    ConnectDatabase connect = new ConnectDatabase();
                    String s = connect.queryUser("select * from User where ID = " + u).get(0).getPassword();
                    if(!s.isEmpty() && p.equals(s)){
                        Toast.makeText(LoginActivity.this, "登陆成功！",Toast.LENGTH_SHORT).show();
                        String s1 = connect.queryFarm("select * from Farm where user_id = " + u).get(0).getFarmID();
                        String farmID = s1;
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("farmID", farmID);
                        intent.putExtra("userID", u);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (Settings.canDrawOverlays(this)) {
                Log.i("LOGTAG", "onActivityResult granted");
            }
        }
    }
}
