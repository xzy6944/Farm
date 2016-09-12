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
public class WindsettingActivity extends Activity {
    private void dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(WindsettingActivity.this);
        builder.setMessage("确认取消吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                //Code.this.finish();
                Intent intent = new Intent(WindsettingActivity.this, FarmSettingActivity.class);
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
    TextView minwindText,maxwindText;
      @Override
    public void onCreate(Bundle  savedInstanceState){
          super.onCreate(savedInstanceState);
          setContentView(R.layout.windsetting);
          maxwindText = (TextView)findViewById(R.id.windmaxsettingtext);
          minwindText = (TextView)findViewById(R.id.windminsettingtext);
          Button button1 = (Button)findViewById(R.id.windsettingbutton1);
          Button button2 = (Button)findViewById(R.id.windsettingbutton2);
          button2.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  float minwinddata = Float.parseFloat(minwindText.getText().toString());
                  float maxwinddata = Float.parseFloat(maxwindText.getText().toString());
                  if(minwindText.getText().toString().length()==0||maxwindText.getText().toString().length()==0||minwinddata>maxwinddata)
                      Toast.makeText(WindsettingActivity.this, "请正确设置co2浓度", Toast.LENGTH_SHORT).show();
                  else {
                      Intent intent = getIntent();
                      String farmID = intent.getStringExtra("farmID");
                      ConnectDatabase connectdatabase = new ConnectDatabase();
                      connectdatabase.update("update farm set wind_min="+minwinddata+",wind_max ="+maxwinddata+"where farmID = "+farmID);
                      intent = new Intent(WindsettingActivity.this, FarmSettingActivity.class);
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
        picker.setRange(0, 100);//数字范围
        picker.setSelectedItem(28);
        picker.setLabel("%");
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int position, String option) {
                minwindText.setText(option.toCharArray(),0,option.length());
                showToast(option);
            }
        });

        picker.show();
    }
    public void onMaxNumberPicker(View view){

        NumberPicker picker = new NumberPicker(this);
        picker.setOffset(2);//偏移量
        picker.setRange(0, 100);//数字范围
        picker.setSelectedItem(28);
        picker.setLabel("%");
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int position, String option) {
                maxwindText.setText(option.toCharArray(),0,option.length());
                showToast(option);
            }
        });
        picker.show();
    }
}
