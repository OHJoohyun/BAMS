package com.example.oh011798.bams;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        Button btn04 = (Button)findViewById(R.id.Button04);
        btn04.setOnClickListener(this);

    }

    public void onClick(View v) {
        Intent intent = new Intent(this, FirstActivity.class);

        switch(v.getId()) {
            case R.id.Button04:
                startActivity(intent);
        }
    }

}
