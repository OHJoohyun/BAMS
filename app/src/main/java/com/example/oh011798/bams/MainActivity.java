package com.example.oh011798.bams;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private CheckDuplicatioinId myAsyncTask;
    public String check = "Error";
    public String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myAsyncTask = new CheckDuplicatioinId();
    }
    public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.login_button: {
                myAsyncTask.execute("80", "90", "100", "110");
                break;
            }
            case R.id.signup_button: {
                Intent intent = new Intent(this, SignupActivity.class);
                startActivity(intent);
                finish();
                break;
            }
        }
    }

    public void success()
    {
        Intent intent = new Intent(this, StudentActivity.class);
        Log.e("TEST", check);
        if(check.equals("200"))
        {
            startActivity(intent);
            finish();
        }
        else
        {
            Toast.makeText(MainActivity.this, "실패", Toast.LENGTH_SHORT).show();
        }
    }

    /* HTTP 통신을 위한 CLASS */
    private class CheckDuplicatioinId extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... sId) {

            String sResult = "Error";

            try {
                URL url = new URL("http://jam0929.lul.lu/bams/api/users/signin");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("POST");

                StringBuffer buffer = new StringBuffer();
                buffer.append("username").append("=").append("jam0929");
                buffer.append("&password").append("=").append("rlawoans2");

                OutputStreamWriter outStream = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
                PrintWriter writer = new PrintWriter(outStream);

                writer.write(buffer.toString());
                writer.flush();

                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuilder builder = new StringBuilder();
                String str;

                while ((str = reader.readLine()) != null) {
                    builder.append(str);
                }
                sResult = builder.toString();
                Log.e("BAMS", sResult);

                JSONObject jsonObject = new JSONObject(sResult);
                check = jsonObject.getString("response").toString();
                Log.e("BAMS", jsonObject.getString("response"));
                Log.e("BAMS", check);
                success();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {

            }

            return sResult;
        }
    }

}
