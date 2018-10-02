package se.mah.ad8885.sensorlab4;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

/**
 * Created by Kristofer Svensson on 2017-02-21.
 */

/**
 * An activity which displays an animated compass and the amount of steps taken by the user since
 * logging in. Also displays the users amount of steps per second.
 */
public class CompassActivity extends AppCompatActivity implements SensorEventListener {

    private TextView tvUsername, tvStepsTaken, tvStepsPerSecond;
    private ImageView mCompass;
    private Button btnReset;
    private SensorManager mSensorManager;
    private Sensor mAccelerometerSensor, mMagnetometerSensor, mOrientationSensor;
    private boolean useOrientationAPI, mLastAccelerometerSet, mLastMagnetometerSet, isFirstValue;
    private long lastUpdateTime = 0, shakeThreshold = 5;
    private float mCurrentDegree = 0, x, last_x, y, last_y, z, last_z;
    private float[] mRotationMatrix, mLastAccelerometer, mLastMagnetometer, mOrientation;
    private MyDBHandler dbHandler;
    private String userName;
    private Intent stepsIntent;
    public StepsService mService;
    private MyServiceConnection mConnection;
    public boolean mBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);
        mConnection = new MyServiceConnection(this);
        stepsIntent = new Intent(this, StepsService.class);
        dbHandler = new MyDBHandler(this, null, null, 1);
        //GUI elements
        tvUsername = (TextView) findViewById(R.id.tvUserName);
        tvStepsTaken = (TextView) findViewById(R.id.tvNbrOfSteps);
        tvStepsPerSecond = (TextView) findViewById(R.id.tvStepsPerSecond);
        mCompass = (ImageView) findViewById(R.id.ivCompass);
        btnReset = (Button) findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHandler.resetUserSteps(userName);
                mService.resetStartTime();
                updateSteps(0);
            }
        });
        //Sensors
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
                mAccelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            } else {
            Toast.makeText(this, "Accelerometer Sensor not available!", Toast.LENGTH_SHORT).show();
        }
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)!=null){
            mMagnetometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        } else {
            Toast.makeText(this, "Magnetic Field Sensor not available!", Toast.LENGTH_SHORT).show();
        }
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION)!=null) {
            mOrientationSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        } else {
            Toast.makeText(this, "Orientation Sensor not available!", Toast.LENGTH_SHORT).show();
        }
        //Misc
        mLastAccelerometer = new float[3];
        mLastMagnetometer = new float[3];
        mOrientation = new float[9];
        mRotationMatrix = new float[9];
        useOrientationAPI = true;
        //Information from previous activity
        Bundle loginData = getIntent().getExtras();
        userName = loginData.getString("Username");
        tvUsername.setText(userName);
        //
        bindService(stepsIntent, mConnection, Context.BIND_AUTO_CREATE);
        Log.v("Pedometer", "service bound!");
        Toast.makeText(this, "Step Detector Service bound!", Toast.LENGTH_SHORT).show();
    }

    protected void onResume() {
        super.onResume();
        tvStepsTaken.setText("" + dbHandler.getUserSteps(userName));
        if (useOrientationAPI) {
            mSensorManager.registerListener(this, mAccelerometerSensor, SensorManager.SENSOR_DELAY_UI);
            mSensorManager.registerListener(this, mMagnetometerSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            mSensorManager.registerListener(this, mOrientationSensor, SensorManager.SENSOR_DELAY_UI);
        }
    }


    protected void onPause() {
        super.onPause();
        if (useOrientationAPI) {
            mSensorManager.unregisterListener(this, mAccelerometerSensor);
            mSensorManager.unregisterListener(this, mMagnetometerSensor);
        } else {
            mSensorManager.unregisterListener(this, mOrientationSensor);
        }
    }

    @Override
    protected void onDestroy() {
        mAccelerometerSensor = null;
        mMagnetometerSensor = null;
        mOrientationSensor = null;
        mSensorManager = null;
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
        Log.v("Pedometer", "service unbound");
        Toast.makeText(this, "Step Detector Service unbound!", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    public void updateSteps(double timestamp) {
        if (timestamp == 0) {
            tvStepsTaken.setText("0");
            tvStepsPerSecond.setText("0");
        } else {
            tvStepsTaken.setText("" + dbHandler.getUserSteps(userName));
            tvStepsPerSecond.setText(String.format("%.2f", calculateStepsPerSecond(timestamp)));
        }
    }

    private double calculateStepsPerSecond(double timestamp) {
        double timeActive = timestamp - dbHandler.getUserStartTime(userName);
        if (timeActive != 0) {
            double stepsPerSecond = dbHandler.getUserSteps(userName) / timeActive;
            return stepsPerSecond;
        } else {
            return 0;
        }
    }

    public String getUsername() {
        return userName;
    }

    public void rotateUsingOrientationSensor(SensorEvent event) {
        //only 4 times in 1 second
        if (System.currentTimeMillis() - lastUpdateTime > 250) {
            float angleInDegress = event.values[0];
            RotateAnimation mRotateAnimation = new RotateAnimation(
                    mCurrentDegree, -angleInDegress,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            //250 milliseconds
            mRotateAnimation.setDuration(250);
            mRotateAnimation.setFillAfter(true);
            mCompass.startAnimation(mRotateAnimation);
            mCurrentDegree = -angleInDegress;
            lastUpdateTime = System.currentTimeMillis();
        }
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (useOrientationAPI) {
            rotateUsingOrientationAPI(event);
        } else {
            rotateUsingOrientationSensor(event);
        }
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            x = event.values[0];
            y = event.values[1];
            z = event.values[2];
            if (isFirstValue) {
                float deltaX = Math.abs(last_x - x);
                float deltaY = Math.abs(last_y - y);
                float deltaZ = Math.abs(last_z - z);
                if ((deltaX > shakeThreshold && deltaY > shakeThreshold) || (deltaX > shakeThreshold && deltaZ > shakeThreshold) || (deltaY > shakeThreshold && deltaZ > shakeThreshold)) {
                    //Gör random degree och spela upp animationen
                    Random rand = new Random();
                    int azimuthInDegrees = rand.nextInt(1080) - 720;
                    RotateAnimation mRotateAnimation = new RotateAnimation(
                            mCurrentDegree, -azimuthInDegrees,
                            Animation.RELATIVE_TO_SELF, 0.5f,
                            Animation.RELATIVE_TO_SELF, 0.5f);
                    mRotateAnimation.setDuration(250);
                    mRotateAnimation.setFillAfter(true);
                    mCompass.startAnimation(mRotateAnimation);
                }
            }
            last_x = x;
            last_y = y;
            last_z = z;
            isFirstValue = true;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void rotateUsingOrientationAPI(SensorEvent event) {
        if (event.sensor == mAccelerometerSensor) {
            System.arraycopy(event.values, 0, mLastAccelerometer, 0, event.values.length);
            mLastAccelerometerSet = true;
        } else if (event.sensor == mMagnetometerSensor) {
            System.arraycopy(event.values, 0, mLastMagnetometer, 0, event.values.length);
            mLastMagnetometerSet = true;
        }
        //only 4 times in 1 second
        if (mLastAccelerometerSet && mLastMagnetometerSet && System.currentTimeMillis() - lastUpdateTime > 250) {
            SensorManager.getRotationMatrix(mRotationMatrix, null, mLastAccelerometer, mLastMagnetometer);
            SensorManager.getOrientation(mRotationMatrix, mOrientation);
            float azimuthInRadians = mOrientation[0];
            float azimuthInDegress = (float) (Math.toDegrees(azimuthInRadians) + 360) % 360;
            RotateAnimation mRotateAnimation = new RotateAnimation(mCurrentDegree, -azimuthInDegress, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            mRotateAnimation.setDuration(250);
            mRotateAnimation.setFillAfter(true);
            mCompass.startAnimation(mRotateAnimation);
            mCurrentDegree = -azimuthInDegress;
            lastUpdateTime = System.currentTimeMillis();
        }
    }
}
