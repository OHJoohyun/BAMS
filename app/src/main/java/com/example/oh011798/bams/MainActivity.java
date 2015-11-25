package com.example.oh011798.bams;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.perples.recosdk.RECOBeaconManager;
import com.perples.recosdk.RECOBeaconRegion;
import com.perples.recosdk.RECOServiceConnectListener;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String RECO_UUID = "24DDF411-8CF1-440C-87CD-E368DAF9C93E";
    /**
     * SCAN_RECO_ONLY:
     *
     * If true, the application scans RECO beacons only, otherwise it scans all beacons.
     * It will be used when the instance of RECOBeaconManager is created.
     *
     * true일 경우 레코 비콘만 스캔하며, false일 경우 모든 비콘을 스캔합니다.
     * RECOBeaconManager 객체 생성 시 사용합니다.
     */
    public static final boolean SCAN_RECO_ONLY = true;
    /**
     * ENABLE_BACKGROUND_RANGING_TIMEOUT:
     *
     * If true, the application stops to range beacons in the entered region automatically in 10 seconds (background),
     * otherwise it continues to range beacons. (It affects the battery consumption.)
     * It will be used when the instance of RECOBeaconManager is created.
     *
     * 백그라운드 ranging timeout을 설정합니다.
     * true일 경우, 백그라운드에서 입장한 region에서 ranging이 실행 되었을 때, 10초 후 자동으로 정지합니다.
     * false일 경우, 계속 ranging을 실행합니다. (배터리 소모율에 영향을 끼칩니다.)
     * RECOBeaconManager 객체 생성 시 사용합니다.
     */
    public static final boolean ENABLE_BACKGROUND_RANGING_TIMEOUT = true;
    /**
     * DISCONTINUOUS_SCAN:
     *
     * There is a known android bug that some android devices scan BLE devices only once.
     * (link: http://code.google.com/p/android/issues/detail?id=65863)
     * To resolve the bug in our SDK, you can use setDiscontinuousScan() method of the RECOBeaconManager.
     * This method is to set whether the device scans BLE devices continuously or discontinuously.
     * The default is set as FALSE. Please set TRUE only for specific devices.
     *
     * 일부 안드로이드 기기에서 BLE 장치들을 스캔할 때, 한 번만 스캔 후 스캔하지 않는 버그(참고: http://code.google.com/p/android/issues/detail?id=65863)가 있습니다.
     * 해당 버그를 SDK에서 해결하기 위해, RECOBeaconManager에 setDiscontinuousScan() 메소드를 이용할 수 있습니다.
     * 해당 메소드는 기기에서 BLE 장치들을 스캔할 때(즉, ranging 시에), 연속적으로 계속 스캔할 것인지, 불연속적으로 스캔할 것인지 설정하는 것입니다.
     * 기본 값은 FALSE로 설정되어 있으며, 특정 장치에 대해 TRUE로 설정하시길 권장합니다.
     */
    public static final boolean DISCONTINUOUS_SCAN = false;
    private static final int REQUEST_ENABLE_BT = 1;
    private static final int REQUEST_LOCATION = 10;

    private BluetoothManager mBluetoothManager;
    private BluetoothAdapter mBluetoothAdapter;

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
/*
    private abstract class RecoActivity extends Activity implements RECOServiceConnectListener {
        protected RECOBeaconManager mRecoManager;
        protected ArrayList<RECOBeaconRegion> mRegions;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            /**
             * RECOBeaconManager 인스턴스틀 생성합니다. (스캔 대상 및 백그라운드 ranging timeout 설정)
             * RECO만을 스캔하고, 백그라운드 ranging timeout을 설정하고 싶지 않으시다면, 다음과 같이 생성하시기 바랍니다.
             * 		mRecoManager = RECOBeaconManager.getInstance(getApplicationContext(), true, false);
             * 주의: enableRangingTimeout을 false로 설정 시, 배터리 소모량이 증가합니다.


            mRecoManager = RECOBeaconManager.getInstance(getApplicationContext(), MainActivity.SCAN_RECO_ONLY, MainActivity.ENABLE_BACKGROUND_RANGING_TIMEOUT);
            mRegions = this.generateBeaconRegion();
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
        }

        private ArrayList<RECOBeaconRegion> generateBeaconRegion() {
            ArrayList<RECOBeaconRegion> regions = new ArrayList<RECOBeaconRegion>();

            RECOBeaconRegion recoRegion;
            recoRegion = new RECOBeaconRegion(MainActivity.RECO_UUID, "RECO Sample Region");
            regions.add(recoRegion);

            return regions;
        }

        protected abstract void start(ArrayList<RECOBeaconRegion> regions);
        protected abstract void stop(ArrayList<RECOBeaconRegion> regions);
    }


*/
}
