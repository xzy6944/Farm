package com.example.xzy.farm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import cn.qqtheme.framework.picker.DatePicker;

/**
 * Created by xzy on 2016/7/12.
 */
public class AddScheduleActivity extends Activity implements View.OnClickListener {

    int setMonth;
    int setDay;
    Button myButton = null;
    Button bjButton = null;
    Button xdButton = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_schedule);
        final TextView date = (TextView) findViewById(R.id.set_schedule_date);
        myButton = (Button) findViewById(R.id.kind_my);
        bjButton = (Button) findViewById(R.id.kind_bj);
        xdButton = (Button) findViewById(R.id.kind_xd);

        myButton.setOnClickListener(this);
        bjButton.setOnClickListener(this);
        xdButton.setOnClickListener(this);

        Calendar cal = Calendar.getInstance();
        setMonth = cal.get(Calendar.MONTH);
        setDay = cal.get(Calendar.DAY_OF_MONTH);
        date.setText(setMonth + " 月 " + setDay + " 日");
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePicker picker = new DatePicker(AddScheduleActivity.this, DatePicker.MONTH_DAY);
                picker.setOnDatePickListener(new cn.qqtheme.framework.picker.DatePicker.OnMonthDayPickListener() {
                    @Override
                    public void onDatePicked(String month, String day) {
                        setMonth = Integer.parseInt(month);
                        setDay = Integer.parseInt(day);
                        date.setText(setMonth + " 月 " + setDay + " 日");
                    }
                });
                picker.show();

            }
        });

        Button cancel = (Button) findViewById(R.id.schedule_cancel);
        Button confirm = (Button) findViewById(R.id.schedule_confirm);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddScheduleActivity.this, MYScheduleActivity.class);
                startActivity(intent);
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddScheduleActivity.this, MYScheduleActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.kind_my:
                myButton.setBackgroundResource(R.drawable.circle_corner_button_selected);
                bjButton.setBackgroundResource(R.drawable.circle_corner_button_unselected);
                xdButton.setBackgroundResource(R.drawable.circle_corner_button_unselected);
                break;
            case R.id.kind_bj:
                myButton.setBackgroundResource(R.drawable.circle_corner_button_unselected);
                bjButton.setBackgroundResource(R.drawable.circle_corner_button_selected);
                xdButton.setBackgroundResource(R.drawable.circle_corner_button_unselected);
                break;
            case R.id.kind_xd:
                myButton.setBackgroundResource(R.drawable.circle_corner_button_unselected);
                bjButton.setBackgroundResource(R.drawable.circle_corner_button_unselected);
                xdButton.setBackgroundResource(R.drawable.circle_corner_button_selected);
                break;
            default:
                break;
        }
    }
}
