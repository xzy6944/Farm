package com.example.xzy.farm;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

/**
 * Created by mx on 2016/7/13.
 */
public class PasswordSettingActivity extends Activity {
    EditText formerpasswordText, newpasswordText;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient mClient;

    private void dialog() {
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passwordsetting);
        Button button1 = (Button) findViewById(R.id.passwordbutton1);
        Button button2 = (Button) findViewById(R.id.passwordbutton2);
        formerpasswordText = (EditText) findViewById(R.id.type_old);
        newpasswordText = (EditText) findViewById(R.id.type_new);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dataintent = getIntent();
                String userID = dataintent.getStringExtra("userID");
                ConnectDatabase connectDatabase = new ConnectDatabase();
                ArrayList<User> array = connectDatabase.queryUser("select * from user where ID ='" + userID + "'");
                String formerpassword = array.get(0).getPassword();
                if (formerpasswordText.getText().toString().length() == 0 || newpasswordText.getText().toString().length() == 0) {
                    Toast.makeText(PasswordSettingActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                }
                else if(!(formerpasswordText.getText().toString().equals(formerpassword)))
                     Toast.makeText(PasswordSettingActivity.this,"输入密码不一致",Toast.LENGTH_SHORT).show();
                else {
                    connectDatabase.update("update User set password = '" + newpasswordText.getText().toString() + "'where ID = '" + userID + "'");
                    Intent intent = new Intent(PasswordSettingActivity.this, UsersettingActivity.class);
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
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "PasswordSetting Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.xzy.farm/http/host/path")
        );
        AppIndex.AppIndexApi.start(mClient, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "PasswordSetting Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.xzy.farm/http/host/path")
        );
        AppIndex.AppIndexApi.end(mClient, viewAction);
        mClient.disconnect();
    }
}
