package com.example.oh011798.bams;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by oh011 on 2015-11-03.
 */
public class SignupActivity extends Activity{

    final Context context = this;
    private EditText name;
    private EditText student_no;
    private EditText password;
    private EditText email;
    private EditText phone;
    private Button major;

    /*
    name = (EditText)findViewById(R.id.signup_name);
    id = (EditText)findViewById(R.id.signup_id);
    password = (EditText)findViewById(R.id.signup_password);
    email = (EditText)findViewById(R.id.signup_email);
    phone = (EditText)findViewById(R.id.signup_phone);
    major = (Button)findViewById(R.id.signup_button);
    */

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = (EditText)findViewById(R.id.signup_name);
        student_no = (EditText)findViewById(R.id.signup_id);
        password = (EditText)findViewById(R.id.signup_password);
        email = (EditText)findViewById(R.id.signup_email);
        phone = (EditText)findViewById(R.id.signup_phone);
        major = (Button)findViewById(R.id.signup_button);
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
                final CharSequence[] items = { "기계공학과", "산업공학과", "화학공학과", "신소재공학과", "응용화학생명공학과", "환경공학과", "건설시스템공학과",
                "교통시스템공학과", "건축학과", "전자공학과", "정보컴퓨터공학과", "소프트웨어융합학과", "미디어학과", "국방디지털융합학과", "사이버보안학과",
                "수학과", "물리학과", "화학과", "생명과학과", "경영학과", "e-비즈니스학과", "금융공학과", "국어국문학과", "영어영문학과", "불어불문학과", "사학과",
                "문화콘텐츠학과", "경제학과", "행정학과", "심리학과", "사회학과", "정치외교학과", "스포츠레저학과", "의학과", "간호학과"};
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
