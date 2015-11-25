package com.example.oh011798.bams;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by 강동현 on 2015-11-25.
 */
public class StateActivity extends AppCompatActivity implements Runnable {

    ProgressBar progressBar;
    int progress = 0;
    Thread thread;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

    }

    public void start(View v) {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        progress = 0;
        while (progress < 100) {
            ++progress;
            progressBar.setProgress(progress);
            try {
                thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

