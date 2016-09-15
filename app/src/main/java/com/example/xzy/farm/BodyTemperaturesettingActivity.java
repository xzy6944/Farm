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

import java.util.ArrayList;

import cn.qqtheme.framework.picker.NumberPicker;
import cn.qqtheme.framework.picker.OptionPicker;

/**
 * Created by mx on 2016/7/12.
 */
public class BodyTemperaturesettingActivity extends Activity {
    private void dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(BodyTemperaturesettingActivity.this);
        builder.setMessage("确认取消吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                //Code.this.finish();
                Intent intent = new Intent(BodyTemperaturesettingActivity.this, FarmSettingActivity.class);
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
    TextView minbodytpText = null;
    TextView maxbodytpText =null;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bodytpsetting);
        minbodytpText = (TextView)findViewById(R.id.btpmaxsettingtext);
        maxbodytpText = (TextView)findViewById(R.id.btpminsettingtext);
        Button button1 = (Button)findViewById(R.id.btpsettingbutton1);
        Button button2 = (Button)findViewById(R.id.btpsettingbutton2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mintpdata = Integer.parseInt(minbodytpText.getText().toString());
                int maxtpdata = Integer.parseInt(maxbodytpText.getText().toString());
                if(minbodytpText.getText().toString().length()==0||maxbodytpText.getText().toString().length()==0||mintpdata>maxtpdata)
                    Toast.makeText(BodyTemperaturesettingActivity.this,"请正确设置体温",Toast.LENGTH_SHORT).show();
                else {
                     ConnectDatabase connectdatabse = new ConnectDatabase();
                     Intent intent = getIntent();
                     String farmID = intent.getStringExtra("farmID");
                     ArrayList<Farm>array  = connectdatabse.queryFarm("select * from farm where farmID = "+farmID);
                     String categorydata = array.get(3).getCategory_breed();
                     connectdatabse.setIOT(1, "update category set suitable_body_temperature_min ="+mintpdata+",suitable_body_temperature_max"+maxtpdata+ "where category = "+categorydata, mintpdata + "", maxtpdata + "");
                     intent = new Intent(BodyTemperaturesettingActivity.this, FarmSettingActivity.class);
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
        picker.setRange(30, 45);//数字范围
        picker.setSelectedItem(35);
        picker.setLabel("度");
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int position, String option) {
                minbodytpText.setText(option.toCharArray(),0,option.length());
                showToast(option);
            }
        });

        picker.show();
    }
    public void onMaxNumberPicker(View view){

        NumberPicker picker = new NumberPicker(this);
        picker.setOffset(1);//偏移量
        picker.setRange(30, 45);//数字范围
        picker.setSelectedItem(35);
        picker.setLabel("度");
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int position, String option) {
                maxbodytpText.setText(option.toCharArray(),0,option.length());
                showToast(option);
            }
        });
        picker.show();
    }
}
