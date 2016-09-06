package com.example.xzy.farm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import cn.qqtheme.framework.picker.DatePicker;

/**
 * Created by xzy on 2016/7/12.
 */
public class AddScheduleActivity extends Activity implements View.OnClickListener {

    int setMonth;
    int setDay;
    int type = 1;
    Button myButton = null;
    Button bjButton = null;
    Button xdButton = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_schedule);
        final Intent intent = getIntent();
        final String farmID = intent.getStringExtra("farmID");

        final TextView date = (TextView) findViewById(R.id.set_schedule_date);
        final EditText schedule = (EditText) findViewById(R.id.edit_schedule_content);
        myButton = (Button) findViewById(R.id.kind_my);
        bjButton = (Button) findViewById(R.id.kind_bj);
        xdButton = (Button) findViewById(R.id.kind_xd);

        myButton.setOnClickListener(this);
        bjButton.setOnClickListener(this);
        xdButton.setOnClickListener(this);

        final Calendar cal = Calendar.getInstance();
        setMonth = cal.get(Calendar.MONTH) + 1;
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
        final Button confirm = (Button) findViewById(R.id.schedule_confirm);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (intent.getIntExtra("type", 1)){
                    case 1:
                        {Intent intent = new Intent(AddScheduleActivity.this, MYScheduleActivity.class);
                        intent.putExtra("farmID", farmID);
                        startActivity(intent);}
                        break;
                    case 2:
                        {Intent intent = new Intent(AddScheduleActivity.this, BJScheduleActivity.class);
                        intent.putExtra("farmID", farmID);
                        startActivity(intent);}
                        break;
                    case 3:
                        {Intent intent = new Intent(AddScheduleActivity.this, XDScheduleActivity.class);
                        intent.putExtra("farmID", farmID);
                        startActivity(intent);}
                        break;
                }
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(0 == schedule.getText().toString().length()){
                    Toast.makeText(AddScheduleActivity.this, "请输入待办行程", Toast.LENGTH_SHORT).show();
                }else{
                    ConnectDatabase connect = new ConnectDatabase();
                    connect.update("insert into daily_management(type, date, details, farm_farmID, completion) values('" + type + "', '" + cal.get(Calendar.YEAR) + "-" + setMonth + "-" + setDay + "', '" + schedule.getText().toString() + "', '" + farmID + "', 0)");
                    switch (intent.getIntExtra("type", 1)){
                        case 1:
                        {Intent intent = new Intent(AddScheduleActivity.this, MYScheduleActivity.class);
                            intent.putExtra("farmID", farmID);
                            startActivity(intent);}
                        break;
                        case 2:
                        {Intent intent = new Intent(AddScheduleActivity.this, BJScheduleActivity.class);
                            intent.putExtra("farmID", farmID);
                            startActivity(intent);}
                        break;
                        case 3:
                        {Intent intent = new Intent(AddScheduleActivity.this, XDScheduleActivity.class);
                            intent.putExtra("farmID", farmID);
                            startActivity(intent);}
                        break;
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.kind_my:
                type = 1;
                myButton.setBackgroundResource(R.drawable.circle_corner_button_selected);
                bjButton.setBackgroundResource(R.drawable.circle_corner_button_unselected);
                xdButton.setBackgroundResource(R.drawable.circle_corner_button_unselected);
                break;
            case R.id.kind_bj:
                type = 2;
                myButton.setBackgroundResource(R.drawable.circle_corner_button_unselected);
                bjButton.setBackgroundResource(R.drawable.circle_corner_button_selected);
                xdButton.setBackgroundResource(R.drawable.circle_corner_button_unselected);
                break;
            case R.id.kind_xd:
                type = 3;
                myButton.setBackgroundResource(R.drawable.circle_corner_button_unselected);
                bjButton.setBackgroundResource(R.drawable.circle_corner_button_unselected);
                xdButton.setBackgroundResource(R.drawable.circle_corner_button_selected);
                break;
            default:
                break;
        }
    }
}
