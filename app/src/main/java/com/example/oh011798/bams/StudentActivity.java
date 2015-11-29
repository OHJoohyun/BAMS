package com.example.oh011798.bams;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by oh011 on 2015-11-06.
 */
public class StudentActivity extends AppCompatActivity {

    private   DrawerLayout drawerLayout;
    private View drawerView;
    private BackPressCloseHandler backPressCloseHandler;
    private String username = "Error";
    private String student_no = "Error";
    private String e_mail = "Error";
    private TextView name;
    private TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        Intent intent = getIntent();
        username = intent.getStringExtra("name");
        student_no = intent.getStringExtra("student_no");
        e_mail = intent.getStringExtra("email");

        name = (TextView) findViewById(R.id.printName);
        email = (TextView) findViewById(R.id.printMail);
        name.setText(username + " / " + student_no);
        email.setText(e_mail);
        /*
        String username = intent.getStringExtra("username");
        String student_no = intent.getStringExtra("student_no");
        String email = intent.getStringExtra("email");

        TextView name = (TextView) findViewById(R.id.printName);
        TextView mail = (TextView) findViewById(R.id.printMail);
        name.setText(username);
        mail.setText(email);
        */

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerView = (View) findViewById(R.id.layoutDrawer);
        backPressCloseHandler = new BackPressCloseHandler(this);
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
                Toast.makeText(StudentActivity.this, "시간표", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(drawerView);
                break;
            }
            case R.id.state_button: {
                Intent intent = new Intent(this, StateActivity.class);
                startActivity(intent);
                finish();
                break;
            }
            case R.id.option_button: {
                Toast.makeText(StudentActivity.this, "설정", Toast.LENGTH_SHORT).show();
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

    public class BackPressCloseHandler {
        private long backKeyPressedTime = 0;
        private Toast toast;

        private Activity activity;


        public BackPressCloseHandler(Activity context) {
            this.activity = context;
        }

        public void onBackPressed() {
            if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
                backKeyPressedTime = System.currentTimeMillis();
                showGuide();
                return;
            }
            if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
                activity.finish();
                toast.cancel();
            }
        }

        private void showGuide() {
            toast = Toast.makeText(activity, "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        backPressCloseHandler.onBackPressed();
    }
}
