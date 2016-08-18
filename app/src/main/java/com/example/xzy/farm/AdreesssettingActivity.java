package com.example.xzy.farm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;

import cn.qqtheme.framework.picker.AddressPicker;

/**
 * Created by mx on 2016/7/13.
 */
public class AdreesssettingActivity extends Activity {
        EditText editText1,editText2,editText3;
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
           editText1 = (EditText)findViewById(R.id.addressedit1);
           editText2 = (EditText)findViewById(R.id.addressedit2);
           editText3 = (EditText)findViewById(R.id.addressedit3);
           Button button1 = (Button)findViewById(R.id.addressbutton1);
           Button button2 = (Button)findViewById(R.id.addressbutton2);
           button1.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent = new Intent(AdreesssettingActivity.this,UsersettingActivity.class);
                   startActivity(intent);
               }
           });
           button2.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent = new Intent(AdreesssettingActivity.this,UsersettingActivity.class);
                   startActivity(intent);
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
                editText1.setText(province.getAreaName().toCharArray(),0,province.getAreaName().length());
                editText2.setText(city.getAreaName().toCharArray(),0,city.getAreaName().length());
                editText3.setText(county.getAreaName().toCharArray(),0,county.getAreaName().length());
            }
        });
        picker.show();
    }
}
