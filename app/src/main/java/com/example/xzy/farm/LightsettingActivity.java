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
public class LightsettingActivity extends Activity {
    private void dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(LightsettingActivity.this);
        builder.setMessage("确认取消吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                //Code.this.finish();
                Intent intent = new Intent(LightsettingActivity.this, FarmSettingActivity.class);
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
    TextView minlightText,maxlightText;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lightsetting);
        minlightText = (TextView) findViewById(R.id.lightmaxsettingtext);
        maxlightText = (TextView) findViewById(R.id.lightminsettingtext);
        Button button1 = (Button)findViewById(R.id.lightsettingbutton1);
        Button button2 = (Button)findViewById(R.id.lightsettingbutton2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float minlightdata = Float.parseFloat(minlightText.getText().toString());
                float maxlightdata = Float.parseFloat(maxlightText.getText().toString());
                if(minlightText.getText().toString().length()==0||maxlightText.getText().toString().length()==0||minlightdata>maxlightdata){
                    showToast("请正确设置光照");
                }
                else {
                    Intent intent = getIntent();
                    String farmID = intent.getStringExtra("farmID");
                    ConnectDatabase connectDatabase = new ConnectDatabase();
                    connectDatabase.update("update farm set light_min="+minlightdata+",light_max ="+maxlightdata+"where farmID = "+farmID);
                    intent = new Intent(LightsettingActivity.this, FarmSettingActivity.class);
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
        picker.setOffset(2);//偏移量
        picker.setRange(100, 5500);//数字范围
        picker.setSelectedItem(28);
        picker.setLabel("lx");
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int position, String option) {
                minlightText.setText(option.toCharArray(),0,option.length());
                showToast(option);
            }
        });

        picker.show();
    }
    public void onMaxNumberPicker(View view){

        NumberPicker picker = new NumberPicker(this);
        picker.setOffset(2);//偏移量
        picker.setRange(100, 550);//数字范围
        picker.setSelectedItem(28);
        picker.setLabel("lx");
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int position, String option) {
                maxlightText.setText(option.toCharArray(),0,option.length());
                showToast(option);
            }
        });
        picker.show();
    }
}
