package com.example.oh011798.bams;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by oh011 on 2015-11-03.
 */
public class SplashActivity extends Activity {
    @Override
    protected  void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        try
        {
            Thread.sleep(3000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}
