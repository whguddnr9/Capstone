package com.caps.exear;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class DeviceActivity extends AppCompatActivity {

    private Button btn_connLocation1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);

        btn_connLocation1=findViewById(R.id.btn_connLocation1);

        btn_connLocation1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(DeviceActivity.this, LocationActivity.class);

                startActivity(intent1);
            }

        });
    }
}
