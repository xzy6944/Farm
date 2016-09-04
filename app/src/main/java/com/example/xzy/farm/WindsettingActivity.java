package com.example.xzy.farm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.qqtheme.framework.picker.NumberPicker;
import cn.qqtheme.framework.picker.OptionPicker;

/**
 * Created by mx on 2016/7/12.
 */
public class WindsettingActivity extends Activity {
    TextView editText1 = null;
    TextView editText2 =null;
      @Override
    public void onCreate(Bundle  savedInstanceState){
          super.onCreate(savedInstanceState);
          setContentView(R.layout.windsetting);
          editText1 = (TextView)findViewById(R.id.windmaxsettingtext);
          editText2 = (TextView)findViewById(R.id.windminsettingtext);
          Button button1 = (Button)findViewById(R.id.windsettingbutton1);
          Button button2 = (Button)findViewById(R.id.windsettingbutton2);
          button1.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent intent = new Intent(WindsettingActivity.this,FarmSettingActivity.class);
                  startActivity(intent);
              }
          });
          button2.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent intent = new Intent(WindsettingActivity.this,FarmSettingActivity.class);
                  startActivity(intent);
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
                editText2.setText(option.toCharArray(),0,option.length());
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
                editText1.setText(option.toCharArray(),0,option.length());
                showToast(option);
            }
        });
        picker.show();
    }
}
