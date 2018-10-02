/*
package se.mah.ad8885.sensorlab2;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

*/
/**
 * Created by Kristofer Svensson on 2017-02-02.
 *//*


*/
/**
 *The main activity makes sure the sensors are present, instantiated, registered, unregistered, etc.
 * Also receives data from the API-classes and sends it to the fragment containing the GUI.
 *//*

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensorPressure, sensorTemperature, sensorAmbientTemperature, sensorHumidity;
    private Front_frag frontFrag;
    private ASyncTaskWeather astClass;
    private VolleyClass volleyClass;
    private FragmentManager fragManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY) != null) {
            sensorHumidity = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        } else {
            Toast.makeText(this, "Humidity sensor not available!", Toast.LENGTH_SHORT).show();
        }
        if (sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE) != null) {
            sensorPressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        } else {
            Toast.makeText(this, "Pressure sensor not available!", Toast.LENGTH_SHORT).show();
        }
        if (sensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE) != null) {
            sensorTemperature = sensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE);
        } else {
            Toast.makeText(this, "Temperature sensor not available!", Toast.LENGTH_SHORT).show();
        }
        if (sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null) {
            sensorAmbientTemperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        } else {
            Toast.makeText(this, "Temperature sensor not available!", Toast.LENGTH_SHORT).show();
        }
        frontFrag = new Front_frag();
        fragManager = getFragmentManager();
        swapFragment(frontFrag, true, "Front");
    }

    private void swapFragment(Fragment frag, boolean addToBackstack, String fragmentTag) {
        FragmentTransaction trans = fragManager.beginTransaction();
        trans.replace(R.id.frameLayout, frag, fragmentTag);
        if (addToBackstack) {
            trans.addToBackStack(fragmentTag);
        }
        trans.commit();
        fragManager.executePendingTransactions();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorManager = null;
    }

    public void startAST() {
        astClass = new ASyncTaskWeather(this);
        astClass.execute();
    }

    public void startVolley() {
        volleyClass = new VolleyClass(this);
        volleyClass.volleyRequest();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    private void registerSensors() {
        if (sensorTemperature != null) {
            sensorManager.registerListener(this, sensorTemperature, SensorManager.SENSOR_DELAY_NORMAL);
            Toast.makeText(this, "Temperature sensor registered", Toast.LENGTH_SHORT).show();
        }
        if (sensorAmbientTemperature != null) {
            sensorManager.registerListener(this, sensorTemperature, SensorManager.SENSOR_DELAY_NORMAL);
            Toast.makeText(this, "Ambient temperature sensor registered", Toast.LENGTH_SHORT).show();
        }
        if (sensorPressure != null) {
            sensorManager.registerListener(this, sensorPressure, SensorManager.SENSOR_DELAY_NORMAL);
            Toast.makeText(this, "Pressure sensor registered", Toast.LENGTH_SHORT).show();
        }
        if (sensorHumidity != null) {
            sensorManager.registerListener(this, sensorHumidity, SensorManager.SENSOR_DELAY_NORMAL);
            Toast.makeText(this, "Humidity sensor registered", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerSensors();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_TEMPERATURE || event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            frontFrag.setText(event.sensor.getStringType(), event.values[0], event.accuracy, event.timestamp);
        }
        if (event.sensor.getType() == Sensor.TYPE_PRESSURE) {
            frontFrag.setText(event.sensor.getStringType(), event.values[0], event.accuracy, event.timestamp);
        }
        if (event.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY) {
            frontFrag.setText(event.sensor.getStringType(), event.values[0], event.accuracy, event.timestamp);
        }
    }

    public void processAPIData(String apiData) {
        String temperature, pressure, humidity;
        if (apiData != null) {
            try {
                JSONObject jsonObject = new JSONObject(apiData);
                JSONObject jsonObject1 = jsonObject.getJSONObject("main");
                temperature = jsonObject1.getString("temp");
                pressure = jsonObject1.getString("pressure");
                humidity = jsonObject1.getString("humidity");
//                float altitude = SensorManager.getAltitude(SensorManager.PRESSURE_STANDARD_ATMOSPHERE, Float.parseFloat(pressure));
                float altitude = SensorManager.getAltitude(Float.parseFloat(pressure), SensorManager.PRESSURE_STANDARD_ATMOSPHERE);
                frontFrag.setAPIText(pressure, humidity, temperature, altitude);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
*/
