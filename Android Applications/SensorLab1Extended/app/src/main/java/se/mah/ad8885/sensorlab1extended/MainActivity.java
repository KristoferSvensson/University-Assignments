package se.mah.ad8885.sensorlab1extended;

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

import java.util.List;

/**
 * Created by Kristofer Svensson on 2017-01-23.
 */

/**
 * The main activity makes sure the sensors are present, instantiated, registered, unregistered, etc.
 */
public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor mSensor;
    private List<Sensor> sensorList;
    private SensorInfoFragment sensorInfoFragment;
    private SensorListFragment sensorListFragment;
    private FragmentManager fm;
    private boolean sensorActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
        sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        sensorInfoFragment = new SensorInfoFragment();
        sensorListFragment = new SensorListFragment();
        fm = getFragmentManager();
        swapFragment(sensorListFragment, false, "List");
    }

    public List<Sensor> getSensorList() {
        return sensorList;
    }

    private void swapFragment(Fragment frag, boolean addToBackstack, String fragmentTag) {
        FragmentTransaction trans = fm.beginTransaction();
        trans.replace(R.id.frameLayout, frag, fragmentTag);
        if (addToBackstack) {
            trans.addToBackStack(fragmentTag);
        }
        trans.commit();
        fm.executePendingTransactions();
    }

    protected void onDestroy() {
        super.onDestroy();
        sensorManager = null;
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
        Toast.makeText(this, "Sensor unregistered", Toast.LENGTH_SHORT).show();
        sensorActive = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mSensor != null) {
            sensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
            Toast.makeText(this, "Sensor registered", Toast.LENGTH_SHORT).show();
            sensorActive = true;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (sensorActive) {
            deregisterSensor();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.values.length>2) {
            sensorInfoFragment.setText(event.accuracy, event.timestamp, event.values[0], event.values[1], event.values[2]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    /**
     * When the user clicks a sensor in the list, that sensor gets flagged and will
     * be instantiated and registered when the "SensorInfo" fragment becomes active.
     *
     * @param position The position in the sensor-list/array.
     */
    public void setSensor(int position) {
        mSensor = sensorList.get(position);
        swapFragment(sensorInfoFragment, true, "Info");
    }

    public String getSensorName() {
        return mSensor.getName();
    }

    public int getSensorType() {
        return mSensor.getType();
    }

    public void registerSensor() {
        sensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
        Toast.makeText(this, "Sensor registered", Toast.LENGTH_SHORT).show();
        sensorActive = true;
    }

    public void deregisterSensor() {
        sensorManager.unregisterListener(this);
        sensorActive = false;
        Toast.makeText(this, "Sensor unregistered", Toast.LENGTH_SHORT).show();
    }
}
