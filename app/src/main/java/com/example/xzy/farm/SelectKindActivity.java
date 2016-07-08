package com.example.xzy.farm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by xzy on 2016/5/28.
 */
public class SelectKindActivity extends Activity {
    private String[] kind1 = new String[]{"鸡","鸭","鹅"};
    private String[][] kind2 = new String[][]{
            {"乌鸡","芦花鸡","柴鸡","贵妃鸡","三黄鸡","杏花鸡"},
            {"北京鸭","绍鸭","高邮鸭","金定鸭","樱桃谷鸭","狄高鸭"},
            {"四川白鹅","马岗鹅","狮头鹅"},
    };

    Spinner kind1Spinner = null;
    Spinner kind2Spinner = null;
    ArrayAdapter<String> kind1Adapter = null;
    ArrayAdapter<String> kind2Adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectkind);

        kind1Spinner = (Spinner) findViewById(R.id.spin_kind_1);
        kind2Spinner = (Spinner) findViewById(R.id.spin_kind_2);
        kind1Adapter = new ArrayAdapter<String>(SelectKindActivity.this,android.R.layout.simple_spinner_item,kind1);
        kind1Spinner.setAdapter(kind1Adapter);
        kind1Spinner.setSelection(0,true);
        kind2Adapter = new ArrayAdapter<String>(SelectKindActivity.this,android.R.layout.simple_spinner_item,kind2[0]);
        kind2Spinner.setAdapter(kind2Adapter);
        kind2Spinner.setSelection(0,true);

        kind1Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                kind2Adapter = new ArrayAdapter<String>(SelectKindActivity.this,android.R.layout.simple_spinner_item,kind2[position]);
                kind2Spinner.setAdapter(kind2Adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
