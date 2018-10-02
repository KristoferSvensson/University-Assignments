package se.mah.ad8885.sensorlab4;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Kristofer Svensson on 2017-02-21.
 */

/**
 * A service utilizing the step detector to count the users steps even while the screen is turned off.
 */
public class StepsService extends Service implements SensorEventListener {

    private LocalBinder mBinder;
    private CompassActivity mListener;
    private SensorManager mSensorManager;
    private Sensor mStepDetectorSensor;
    private MyDBHandler dbHandler;
    private boolean startTimeSet;


    public StepsService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBinder = new LocalBinder();
        mSensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR) != null) {
            mStepDetectorSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
            Log.v("Step detector sensor", "Registered!");
        } else {
            Toast.makeText(this, "Step Detector Sensor not available!", Toast.LENGTH_SHORT).show();
        }
        dbHandler = new MyDBHandler(this, null, null, 1);
    }

    public void setListenerActivity(CompassActivity main) {
        this.mListener = main;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        double timestampSeconds = event.timestamp / 1000000000;
        if (!startTimeSet) {
            dbHandler.setStartTime(mListener.getUsername(), timestampSeconds);
            startTimeSet = true;
        } else {
            Log.v("Step", "Step registered");
            dbHandler.addUserSteps(mListener.getUsername());
            mListener.updateSteps(timestampSeconds);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void resetStartTime() {
        startTimeSet = false;
    }

    @Override
    public IBinder onBind(Intent intent) {
        mSensorManager.registerListener(this, mStepDetectorSensor, SensorManager.SENSOR_DELAY_NORMAL);
        return mBinder;
    }

    public class LocalBinder extends Binder {
        StepsService getService() {
            return StepsService.this;
        }
    }
}
