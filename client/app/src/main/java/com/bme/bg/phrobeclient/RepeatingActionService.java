package com.bme.bg.phrobeclient;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.bme.bg.phrobeclient.entities.PhrobeDataObject;
import com.bme.bg.phrobeclient.util.SharedPreferencesUtil;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

public class RepeatingActionService extends Service {
    public RepeatingActionService() {
    }

    // http://www.mopri.de/2010/timertask-bad-do-it-the-android-way-use-a-handler/
    private Handler handler = new Handler();
    private PhrobeDataObject phrobeDataObject = new PhrobeDataObject();

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            /* do what you need to do */
            new HttpRequestTask().execute();
            /* and here comes the "trick" */
            handler.postDelayed(this, interval);

        }
    };

    private int mId = 18433153; //Any unique number for this notification
    private int interval;
    String url = PhrobeMainActivity.PHRROBE_BACKEND_URL_DEFAULT;
    String apiKey = "";

    @Override
    public void onCreate() {

    }

    TelephonyManager telephonyManager;
    LocationManager locationManager;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();

        interval = intent.getIntExtra("interval", 10000);

        final SharedPreferences sharedPreferences = getSharedPreferences(PhrobeMainActivity.class.getSimpleName(), Context.MODE_PRIVATE);
        url = sharedPreferences.getString(PhrobeMainActivity.PHROBE_BACKEND_URL_PROPERTY_REG_ID, PhrobeMainActivity.PHRROBE_BACKEND_URL_DEFAULT) + "/phones/addMetrics";
        apiKey = sharedPreferences.getString(PhrobeMainActivity.PROPERTY_REG_ID, "");

        showNotification();
        handler.postDelayed(runnable, 0);

        // register network strength listener
        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(new GetSignalStrength(), PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 4000, 0, new GetGpsStrength());

        // If we get killed, after returning from here, restart
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // We don't provide binding, so return null
        return null;
    }

    @Override
    public void onDestroy() {
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancel(mId);
        handler.removeCallbacks(runnable);
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
    }


    private void showNotification() {
        Notification.Builder mBuilder =
                new Notification.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("Service in background with " + interval + "ms interval")
                        .setContentText("URL: " + url)
                        .setOngoing(true);

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(mId, mBuilder.build());
    }


    private class HttpRequestTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            try {
                // for example value of first element
                phrobeDataObject.timestamp = new Date().getTime();
                phrobeDataObject.interval = interval;
                phrobeDataObject.apiKey = apiKey;

                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                String result = restTemplate.postForObject(url, phrobeDataObject, String.class);
                return result;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * SignalStrength listener
     */
    private class GetSignalStrength extends PhoneStateListener {
        @Override
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);
            phrobeDataObject.signalStrength.dbm = signalStrength.getCdmaDbm();
            phrobeDataObject.signalStrength.signalStrength = signalStrength.getGsmSignalStrength();
        }
    }

    private class GetGpsStrength implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            phrobeDataObject.location.accuracy = location.getAccuracy();
            phrobeDataObject.location.altitude = location.getAltitude();
            phrobeDataObject.location.bearing = location.getBearing();
            phrobeDataObject.location.elapsedRealtimeNanos = location.getElapsedRealtimeNanos();
            phrobeDataObject.location.latitude = location.getLatitude();
            phrobeDataObject.location.longitude = location.getLongitude();
            phrobeDataObject.location.accuracy = location.getAccuracy();
            phrobeDataObject.location.provider = location.getProvider();
            phrobeDataObject.location.speed = location.getSpeed();
            phrobeDataObject.location.time = location.getTime();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }
    }


}
