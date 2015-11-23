package com.example.oh011798.bams;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by oh011 on 2015-11-03.
 */
public class SignupActivity extends Activity{

    final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.ok:{
                Toast.makeText(SignupActivity.this, "회원가입 성공!!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            }
            case R.id.signup_major: {
                final Button btn_major = (Button)findViewById(R.id.signup_major);
                final CharSequence[] items = { "정보컴퓨터공학과", "소프트웨어학과", "미디어학과", "전자공학과", "기계공학과", "화학공학과", "경영학과",
                "산업공학과", "건축학과", "소프트웨어보안학과", "금융공학과", "물리학과", "수학과", "화학과", "국어국문학과"};
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // 제목셋팅
                alertDialogBuilder.setTitle("학과");
                alertDialogBuilder.setItems(items,new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int id) {
                        //Toast.makeText(getApplicationContext(),items[id] + " 선택했습니다.",Toast.LENGTH_SHORT).show();
                        btn_major.setText(items[id]);
                        dialog.dismiss();
                        }
                });

                // 다이얼로그 생성
                AlertDialog alertDialog = alertDialogBuilder.create();

                // 다이얼로그 보여주기
                alertDialog.show();
            }
        }
    }
}
