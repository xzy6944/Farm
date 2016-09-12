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

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;

import cn.qqtheme.framework.picker.AddressPicker;

/**
 * Created by mx on 2016/7/13.
 */
public class AdreesssettingActivity extends Activity {
        TextView provinceText,cityText,countyText;
    private void dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(AdreesssettingActivity.this);
        builder.setMessage("确认取消吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                //Code.this.finish();
                Intent intent = new Intent(AdreesssettingActivity.this, UsersettingActivity.class);
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
           setContentView(R.layout.addresssetting);
           Button button  = (Button)findViewById(R.id.adressbutton);
           button.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   onAddressPicker(view);
               }
           });
           provinceText = (TextView)findViewById(R.id.addressedit1);
           cityText = (TextView)findViewById(R.id.addressedit2);
           countyText = (TextView)findViewById(R.id.addressedit3);
           Button button1 = (Button)findViewById(R.id.addressbutton1);
           Button button2 = (Button)findViewById(R.id.addressbutton2);
           button2.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   if(provinceText.getText().toString().length()==0||cityText.getText().toString().length()==0||countyText.getText().toString().length()==0){
                       showToast("请设置地址");
                   }
                   else {
                       Intent intent = getIntent();
                       String farmID = intent.getStringExtra("farmID");
                       ConnectDatabase connectdatabase = new ConnectDatabase();
                       String provinceName = provinceText.getText().toString();
                       String cityName = cityText.getText().toString();
                       String countyName = countyText.getText().toString();
                       connectdatabase.update("update farm set location = "+provinceName+""+cityName+""+countyName+""+"where farmID ="+farmID);

                       intent = new Intent(AdreesssettingActivity.this, UsersettingActivity.class);
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
    public void onAddressPicker(View view) {

        ArrayList<AddressPicker.Province> data = new ArrayList<AddressPicker.Province>();
        String json = AssetsUtils.readText(this, "city.json");
        data.addAll(JSON.parseArray(json, AddressPicker.Province.class));
        AddressPicker picker = new AddressPicker(this,data);
        picker.setSelectedItem("贵州", "贵阳", "花溪");
        //picker.setHideProvince(true);//加上此句举将只显示地级及县级
        //picker.setHideCounty(true);//加上此句举将只显示省级及地级
        picker.setOnAddressPickListener(new AddressPicker.OnAddressPickListener() {
            @Override
            public void onAddressPicked(AddressPicker.Province province, AddressPicker.City city, AddressPicker.County county) {
                //showToast(province.toString() + city.toString() + county.toString());
                provinceText.setText(province.getAreaName().toCharArray(),0,province.getAreaName().length());
                cityText.setText(city.getAreaName().toCharArray(),0,city.getAreaName().length());
                countyText.setText(county.getAreaName().toCharArray(),0,county.getAreaName().length());
            }
        });
        picker.show();
    }
}
