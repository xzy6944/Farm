package com.example.xzy.farm;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cn.qqtheme.framework.picker.NumberPicker;
import cn.qqtheme.framework.picker.OptionPicker;


/**
 * Created by mx on 2016/7/12.
 */
public class TemperaturesettingActivity extends Activity {

    TextView mintpText,maxtpText;
    private void dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(TemperaturesettingActivity.this);
        builder.setMessage("确认取消吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                //Code.this.finish();
                Intent intent = new Intent(TemperaturesettingActivity.this, FarmSettingActivity.class);
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
        setContentView(R.layout.temperaturesetting);
        maxtpText = (TextView)findViewById(R.id.tpmaxsettingtext);
        mintpText = (TextView)findViewById(R.id.tpminsettingtext);
        Button button1 = (Button)findViewById(R.id.tpsettingbutton1);
        Button button2 = (Button)findViewById(R.id.tpsettingbutton2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mintpdata = Integer.parseInt(mintpText.getText().toString());
                int maxtpdata = Integer.parseInt(maxtpText.getText().toString());
                if(maxtpText.getText().toString().length()==0||mintpText.getText().toString().length()==0||mintpdata>maxtpdata)
                    Toast.makeText(TemperaturesettingActivity.this,"请正确设置温度",Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = getIntent();
                    String farmID = intent.getStringExtra("farmID");
                    ConnectDatabase connectDatabase = new ConnectDatabase();
                    connectDatabase.setIOT(3, "update farm set temperature_min ='"+mintpdata+",temperature_max ='"+maxtpdata+"' where farmID ='"+farmID + "'", mintpdata + "", maxtpdata + "");
                    intent = new Intent(TemperaturesettingActivity.this, FarmSettingActivity.class);
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
    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void onMinNumberPicker(View view){

        NumberPicker picker = new NumberPicker(this);
        picker.setOffset(1);//偏移量
        picker.setRange(-5, 45);//数字范围
        picker.setSelectedItem(28);
        picker.setLabel("度");
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int position, String option) {
               mintpText.setText(option.toCharArray(),0,option.length());
                showToast(option);
            }
        });

        picker.show();
    }
    public void onMaxNumberPicker(View view){

        NumberPicker picker = new NumberPicker(this);
        picker.setOffset(1);//偏移量
        picker.setRange(-5, 45);//数字范围
        picker.setSelectedItem(28);
        picker.setLabel("度");
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int position, String option) {
                maxtpText.setText(option.toCharArray(),0,option.length());
                showToast(option);
            }
        });
        picker.show();
    }
}
