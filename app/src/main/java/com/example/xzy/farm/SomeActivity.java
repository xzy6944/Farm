package com.example.xzy.farm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class SomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailinfo);
        View view = new AverageTemperatureChart().executed(this);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.id1);
        layout.addView(view);
        Button todetail = (Button)findViewById(R.id.button2);
        todetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SomeActivity.this,DetailsActivity.class);
                startActivity(intent);
            }
        });
    }
}
