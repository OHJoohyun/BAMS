package com.example.oh011798.bams;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);

        Button list01=(Button)findViewById(R.id.List01);
        list01.setOnClickListener(this);
        Button btn01=(Button)findViewById(R.id.Button01);
        btn01.setOnClickListener(this);
        Button btn02=(Button)findViewById(R.id.Button02);
        btn02.setOnClickListener(this);

        //서버에서 값 읽어 오기
        
    }

    public void onClick(View v) {
        Intent intent = new Intent(this, SecondActivity.class);

        switch(v.getId()) {
            case R.id.Button01:
                startActivity(intent);
        }
    }


}
