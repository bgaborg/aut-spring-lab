package com.bme.bg.phrobeclient;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bme.bg.phrobeclient.util.SharedPreferencesUtil;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.concurrent.atomic.AtomicInteger;


public class PhrobeMainActivity extends Activity {

    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String TAG = PhrobeMainActivity.class.getName();
    private final SharedPreferencesUtil sharedPreferencesUtil;
    public static String PHROBE_BACKEND_URL_PROPERTY_REG_ID = "PHROBE_BACKEND_URL_PROPERTY";
    public static String PHRROBE_BACKEND_URL_DEFAULT = "http://192.168.1.105:8080";

    /**
     * Substitute you own sender ID here. This is the project number you got
     * from the API Console, as described in "Getting Started."
     */
    String SENDER_ID = "1007175536345";
    TextView mDisplay;

    String regid;
    GoogleCloudMessaging gcm;
    Context context;

    private SharedPreferences prefs = null;


    EditText ipAddrIpText = null;
    private String backendUrl = "";

    public PhrobeMainActivity() {
        sharedPreferencesUtil = new SharedPreferencesUtil(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrobe_main);

        mDisplay = (TextView) findViewById(R.id.display);
        ipAddrIpText = (EditText) findViewById(R.id.ip_address);


        context = getApplicationContext();
        prefs = sharedPreferencesUtil.getGcmPreferences(context);
        backendUrl = prefs.getString(PHROBE_BACKEND_URL_PROPERTY_REG_ID, PHRROBE_BACKEND_URL_DEFAULT);
        ipAddrIpText.setText(backendUrl, TextView.BufferType.EDITABLE);

        prefs.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);

        // Check device for Play Services APK. If check succeeds, proceed with GCM registration.
        if (checkPlayServices()) {
            gcm = GoogleCloudMessaging.getInstance(this);
            regid = sharedPreferencesUtil.getRegistrationId(context);

            if (regid.isEmpty()) {
                registerInBackground();
            }
        } else {
            Log.i(TAG, "No valid Google Play Services APK found.");
        }
    }

    public void onClick(View view) {
        // handle clicks by view id
        if(view.getId() == R.id.register_at_server){
            backendUrl = ipAddrIpText.getText().toString();
            prefs.edit().putString(PHROBE_BACKEND_URL_PROPERTY_REG_ID, backendUrl).commit();
            sendRegistrationIdToBackend(regid);
        }

        if(view.getId() == R.id.clear_messages){
            mDisplay.setText("");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.phrobe_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // You need to do the Play Services APK check here too.
    @Override
    protected void onResume() {
        super.onResume();
        checkPlayServices();
        Log.i(TAG, "onResume");
    }

    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }


    /**
     * Registers the application with GCM servers asynchronously.
     * <p/>
     * Stores the registration ID and the app versionCode in the application's
     * shared preferences.
     */
    private void registerInBackground() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(context);
                    }
                    regid = gcm.register(SENDER_ID);
                    msg = "Device registered, registration ID=" + regid;

                    // You should send the registration ID to your server over HTTP, so it
                    // can use GCM/HTTP or CCS to send messages to your app.
                    sendRegistrationIdToBackend(regid);

                    // For this demo: we don't need to send it because the device will send
                    // upstream messages to a server that echo back the message using the
                    // 'from' address in the message.

                    // Persist the regID - no need to register again.
                    sharedPreferencesUtil.storeRegistrationId(context, regid);
                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                    // If there is an error, don't just keep trying to register.
                    // Require the user to click a button again, or perform
                    // exponential back-off.
                }
                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {
                mDisplay.append(msg + "\n");
            }
        }.execute(null, null, null);
    }

    /**
     * Sends the registration ID to your server over HTTP, so it can use GCM/HTTP or CCS to send
     * messages to your app. Not needed for this demo since the device sends upstream messages
     * to a server that echoes back the message using the 'from' address in the message.
     */
    private void sendRegistrationIdToBackend(String rId) {
        String deviceNick = URLEncoder.encode(getPhoneName());
        String getUrl = backendUrl + "/phones/addphone?api=" + rId + "&nick=" + deviceNick;
        new RequestTask().execute(getUrl);
    }

    /**
     * Get phone name from BluetoothAdapter
     */
    public String getPhoneName() {
        BluetoothAdapter myDevice = BluetoothAdapter.getDefaultAdapter();
        String deviceName = myDevice.getName();
        return deviceName;
    }

    public void startService(View view) {
        Toast.makeText(getApplicationContext(), "Starting service", Toast.LENGTH_LONG).show();
        startService(new Intent(this, RepeatingActionService.class));
    }

    public void stopService(View view){
        Toast.makeText(getApplicationContext(), "Stopping service", Toast.LENGTH_LONG).show();
        stopService(new Intent(this, RepeatingActionService.class));
    }

    public SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if(key.equals(PHROBE_BACKEND_URL_PROPERTY_REG_ID)){
                Log.i(TAG, key);
                Log.i(TAG, sharedPreferences.getString(key, PHRROBE_BACKEND_URL_DEFAULT));
                ipAddrIpText.setText(sharedPreferences.getString(key, PHRROBE_BACKEND_URL_DEFAULT));
            }
        }
    };
}
