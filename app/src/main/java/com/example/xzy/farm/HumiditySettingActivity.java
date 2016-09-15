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
public class HumiditySettingActivity extends Activity {
    private void dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(HumiditySettingActivity.this);
        builder.setMessage("确认取消吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                //Code.this.finish();
                Intent intent = new Intent(HumiditySettingActivity.this, FarmSettingActivity.class);
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
    TextView minhumidityText,maxhumidityText;
    @Override

       public void onCreate(Bundle savedInstanceState){
           super.onCreate(savedInstanceState);
           setContentView(R.layout.humiditysetting);
           minhumidityText = (TextView) findViewById(R.id.hdminsettingtext);
           maxhumidityText = (TextView) findViewById(R.id.hdmaxsettingtext);
           Button button1 = (Button)findViewById(R.id.hdsettingbutton1);
           Button button2 = (Button)findViewById(R.id.hdsettingbutton2);
           button2.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   int minhumifitydata = Integer.parseInt(minhumidityText.getText().toString());
                   int maxhumiditydata = Integer.parseInt(maxhumidityText.getText().toString());
                   if(minhumidityText.getText().toString().length()==0||maxhumidityText.getText().toString().length()==0||minhumifitydata>maxhumiditydata)
                       showToast("请正确设置湿度");
                   else {
                        ConnectDatabase connectDatabase = new ConnectDatabase();
                        Intent intent = getIntent();
                        String farmID = intent.getStringExtra("farmID");
                        connectDatabase.setIOT(4, "update farm set humidity_min="+minhumifitydata+",humidity_max ="+maxhumiditydata+"where farmID = "+farmID, minhumifitydata + "", maxhumiditydata + "");
                        intent = new Intent(HumiditySettingActivity.this, FarmSettingActivity.class);
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
        picker.setRange(0, 100);//数字范围
        picker.setSelectedItem(45);
        picker.setLabel("%");
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int position, String option) {
                minhumidityText.setText(option.toCharArray(),0,option.length());
                showToast(option);
            }
        });

        picker.show();
    }
    public void onMaxNumberPicker(View view){

        NumberPicker picker = new NumberPicker(this);
        picker.setOffset(1);//偏移量
        picker.setRange(0, 100);//数字范围
        picker.setSelectedItem(45);
        picker.setLabel("%");
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int position, String option) {
                maxhumidityText.setText(option.toCharArray(),0,option.length());
                showToast(option);
            }
        });
        picker.show();
    }
}
