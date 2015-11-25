package com.example.oh011798.bams;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.Toast;

/**
 * Created by oh011 on 2015-11-16.
 */
public class StartActivity extends Activity {


    private   DrawerLayout drawerLayout;
    private View drawerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerView = (View) findViewById(R.id.layoutDrawer);
    }

    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.menu_button:{
                drawerLayout.openDrawer(drawerView);
                break;
            }
            case R.id.schedule_button: {
                Toast.makeText(StartActivity.this, "시간표", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(drawerView);
                break;
            }
            case R.id.state_button: {
                Toast.makeText(StartActivity.this, "현재상태", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(drawerView);
                break;
            }
            case R.id.option_button: {
                Toast.makeText(StartActivity.this, "설정", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(drawerView);
                break;
            }
            case R.id.logout_button: {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            }
        }
    }

}
