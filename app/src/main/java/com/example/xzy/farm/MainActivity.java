package com.example.xzy.farm;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class MainActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        final String farmID = intent.getStringExtra("farmID");
        final String userID = intent.getStringExtra("userID");

        //TODO test
        Intent startIntent = new Intent(this, ReceiveService.class);
        startService(startIntent);

        ImageView image_to_MMS = (ImageView) findViewById(R.id.toMMS);
        image_to_MMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MYScheduleActivity.class);
                intent.putExtra("farmID", farmID);
                startActivity(intent);
            }
        });

        ImageView image_to_BJS = (ImageView) findViewById(R.id.toBJS);
        image_to_BJS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BJScheduleActivity.class);
                intent.putExtra("farmID", farmID);
                startActivity(intent);
            }
        });

        ImageView image_to_XDS = (ImageView) findViewById(R.id.toXDS);
        image_to_XDS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, XDScheduleActivity.class);
                intent.putExtra("farmID", farmID);
                startActivity(intent);
            }
        });

        Button button_temperature = (Button) findViewById(R.id.button);
        button_temperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TemperatureActivity.class);
                intent.putExtra("farmID", farmID);
                startActivity(intent);
            }
        });

        Button button_humidity = (Button) findViewById(R.id.button2);
        button_humidity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HumidityActivity.class);
                intent.putExtra("farmID", farmID);
                startActivity(intent);
            }
        });

        Button button_co2 = (Button) findViewById(R.id.button3);
        button_co2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CO2Activity.class);
                intent.putExtra("farmID", farmID);
                startActivity(intent);
            }
        });

        Button button_light = (Button) findViewById(R.id.button4);
        button_light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LightActivity.class);
                intent.putExtra("farmID", farmID);
                startActivity(intent);
            }
        });

        Button button_farm = (Button) findViewById(R.id.button5);
        button_farm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FarmSecurityActivity.class);
                intent.putExtra("farmID", farmID);
                startActivity(intent);
            }
        });

        Button button_individual = (Button) findViewById(R.id.button6);
        button_individual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, IndividualSecurityActivity.class);
                intent.putExtra("farmID", farmID);
                startActivity(intent);
            }
        });

        // attach to current activity;
        final ResideMenu resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.color.light_background);
        resideMenu.attachToActivity(this);

        ResideMenuItem itemChart = new ResideMenuItem(this, R.drawable.menu_chart, "市场走势");
        itemChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SomeActivity.class);
                intent.putExtra("userID", userID);
                intent.putExtra("farmID", farmID);
                startActivity(intent);
                resideMenu.closeMenu();
            }
        });
        resideMenu.addMenuItem(itemChart,  ResideMenu.DIRECTION_RIGHT); // or  ResideMenu.DIRECTION_RIGHT

        ResideMenuItem itemUser = new ResideMenuItem(this, R.drawable.menu_user, "用户");
        itemUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SettingsActivity.class);
                intent.putExtra("farmID", farmID);
                intent.putExtra("userID", userID);
                startActivity(intent);
                resideMenu.closeMenu();
            }
        });
        resideMenu.addMenuItem(itemUser,  ResideMenu.DIRECTION_RIGHT); // or  ResideMenu.DIRECTION_RIGHT

        findViewById(R.id.menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
            }
        });
    }

}
