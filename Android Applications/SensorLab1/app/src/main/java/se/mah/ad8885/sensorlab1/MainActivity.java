package se.mah.ad8885.sensorlab1;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensorGyro;
    private TextView tvGyroStatus, tvGyroAccuracy, tvGyroTimestamp, tvGyroEventValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
        tvGyroStatus = (TextView)findViewById(R.id.tvGyro);
        tvGyroAccuracy = (TextView)findViewById(R.id.tvGyroAccuracy);
        tvGyroTimestamp = (TextView)findViewById(R.id.tvGyroTimestamp);
        tvGyroEventValue = (TextView)findViewById(R.id.tvGyroEventValue);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)!=null){
            sensorGyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            tvGyroStatus.setText("Gyroscope is available in your device!");
        } else {
            tvGyroStatus.setText("Gyroscope is not available in your device!");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorManager = null;
        sensorGyro = null;
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
        Toast.makeText(this, "Sensor unregistered", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorGyro, SensorManager.SENSOR_DELAY_NORMAL);
        Toast.makeText(this, "Sensor registered", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        tvGyroAccuracy.setText(String.valueOf(event.accuracy));
        tvGyroTimestamp.setText(String.valueOf(event.timestamp));
        tvGyroEventValue.setText("X: " + String.valueOf(event.values[0]) + " Y: " + String.valueOf(event.values[1]) +
        " Z: " + String.valueOf(event.values[2]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        sensorGyro.getStringType();
        sensorGyro.getPower();
    }
}
