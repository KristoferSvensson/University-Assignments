package se.mah.ad8885.sensorlab1extended;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Displays information about the sensor chosen from the list in the SensorList-fragment.
 */
public class SensorInfoFragment extends Fragment {

    private TextView tvSensorName, tvValue1, tvValue2, tvValue3, tvTimestamp, tvAccuracy, tvPower, tvDescription;
    private Button btnStart, btnStop;
    private MainActivity main;
    private int sensorType;
    public static final int TYPE_ACCELEROMETER = 1;
    public static final int TYPE_MAGNETIC_FIELD = 2;
    public static final int TYPE_ORIENTATION = 3;
    public static final int TYPE_GYROSCOPE = 4;
    public static final int TYPE_LIGHT = 5;
    public static final int TYPE_PRESSURE = 6;
    public static final int TYPE_TEMPERATURE = 7;
    public static final int TYPE_PROXIMITY = 8;
    public static final int TYPE_GRAVITY = 9;
    public static final int TYPE_LINEAR_ACCELERATION = 10;
    public static final int TYPE_ROTATION_VECTOR = 11;
    public static final int TYPE_RELATIVE_HUMIDITY = 12;
    public static final int TYPE_AMBIENT_TEMPERATURE = 13;
    public static final int TYPE_MAGNETIC_FIELD_UNCALIBRATED = 14;
    public static final int TYPE_GAME_ROTATION_VECTOR = 15;
    public static final int TYPE_GYROSCOPE_UNCALIBRATED = 16;
    public static final int TYPE_SIGNIFICANT_MOTION = 17;
    public static final int TYPE_STEP_DETECTOR = 18;
    public static final int TYPE_STEP_COUNTER = 19;
    public static final int TYPE_GEOMAGNETIC_ROTATION_VECTOR = 20;

    public SensorInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sensor_info, container, false);
        main = (MainActivity) getActivity();
        tvSensorName = (TextView) view.findViewById(R.id.tvSensorName);
        tvDescription = (TextView) view.findViewById(R.id.tvSensorDescription);
        tvAccuracy = (TextView) view.findViewById(R.id.tvAccuracy);
        tvTimestamp = (TextView) view.findViewById(R.id.tvTimestamp);
        tvValue1 = (TextView) view.findViewById(R.id.tvValue1);
        tvValue2 = (TextView) view.findViewById(R.id.tvValue2);
        tvValue3 = (TextView) view.findViewById(R.id.tvValue3);
        sensorType = main.getSensorType();
        initializeComponents(view, sensorType);
        btnStart = (Button) view.findViewById(R.id.btnStart);
        btnStop = (Button) view.findViewById(R.id.btnStop);
        btnStop.setEnabled(false);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.registerSensor();
                btnStart.setEnabled(false);
                btnStop.setEnabled(true);
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.deregisterSensor();
                btnStart.setEnabled(true);
                btnStop.setEnabled(false);
            }
        });
        return view;
    }

    private void initializeComponents(View view, int sensorType) {
        tvSensorName.setText(main.getSensorName());
        switch (sensorType) {
            case TYPE_ACCELEROMETER:
                tvDescription.setText(main.getString(R.string.accelerometer));
                break;
            case TYPE_MAGNETIC_FIELD:
                tvDescription.setText(main.getString(R.string.magneticField));
                break;
            case TYPE_ORIENTATION:
                tvDescription.setText(main.getString(R.string.orientationSensor));
                break;
            case TYPE_GYROSCOPE:
                tvDescription.setText(main.getString(R.string.gyroscope));
                break;
            case TYPE_LIGHT:
                tvDescription.setText(main.getString(R.string.lightSensor));
                break;
            case TYPE_PRESSURE:
                tvDescription.setText(main.getString(R.string.pressureSensor));
                break;
            case TYPE_TEMPERATURE:
                //Not implemented for this version
                break;
            case TYPE_PROXIMITY:
                tvDescription.setText(main.getString(R.string.proximitySensor));
                break;
            case TYPE_GRAVITY:
                tvDescription.setText(main.getString(R.string.gravitySensor));
                break;
            case TYPE_LINEAR_ACCELERATION:
                tvDescription.setText(main.getString(R.string.linearAcceleration));
                break;
            case TYPE_ROTATION_VECTOR:
                tvDescription.setText(main.getString(R.string.rotationSensor));
                break;
            case TYPE_RELATIVE_HUMIDITY:
                //Not implemented for this version
                break;
            case TYPE_AMBIENT_TEMPERATURE:
                //Not implemented for this version
                break;
            case TYPE_MAGNETIC_FIELD_UNCALIBRATED:
                tvDescription.setText(main.getString(R.string.magneticField));
                break;
            case TYPE_GAME_ROTATION_VECTOR:
                tvDescription.setText(main.getString(R.string.rotationSensorGame));
                break;
            case TYPE_GYROSCOPE_UNCALIBRATED:
                tvDescription.setText(main.getString(R.string.gyroscope));
                break;
            case TYPE_SIGNIFICANT_MOTION:
                //Not implemented for this version
                break;
            case TYPE_STEP_DETECTOR:
                //Not implemented for this version
                break;
            case TYPE_STEP_COUNTER:
                //Not implemented for this version
                break;
            case TYPE_GEOMAGNETIC_ROTATION_VECTOR:
                //Not implemented for this version
                break;
            default:
                break;
        }
    }

    public void setText(int accuracy, long timestamp, float value1, float value2, float value3) {
        tvAccuracy.setText(getString(R.string.accuracy) + String.valueOf(accuracy));
        tvTimestamp.setText(getString(R.string.timestamp) + String.valueOf(timestamp));
        switch (sensorType) {
            case TYPE_ACCELEROMETER:
                tvValue1.setText("X: " + value1 + " m/s2");
                tvValue2.setText("Y: " + value2 + " m/s2");
                tvValue3.setText("Z: " + value3 + " m/s2");
                break;
            case TYPE_MAGNETIC_FIELD:
                tvValue1.setText("X: " + value1 + " μT");
                tvValue2.setText("Y: " + value2 + " μT");
                tvValue3.setText("Z: " + value3 + " μT");
                break;
            case TYPE_ORIENTATION:
                tvValue1.setText("X: " + value1 + " degrees");
                tvValue2.setText("Y: " + value2 + " degrees");
                tvValue3.setText("Z: " + value3 + " degrees");
                break;
            case TYPE_GYROSCOPE:
                tvValue1.setText("X: " + value1 + " rad/s");
                tvValue2.setText("Y: " + value2 + " rad/s");
                tvValue3.setText("Z: " + value3 + " rad/s");
                break;
            case TYPE_LIGHT:
                tvValue1.setText("Light level: " + value1 + " lx");
                break;
            case TYPE_PRESSURE:
                tvValue1.setText("Air pressure: " + value1 + " hPa");
                break;
            case TYPE_TEMPERATURE:
                //Not implemented for this version
                break;
            case TYPE_PROXIMITY:
                tvValue1.setText("X: " + value1 + " cm");
                break;
            case TYPE_GRAVITY:
                tvValue1.setText("X: " + value1 + " m/s2");
                tvValue2.setText("Y: " + value2 + " m/s2");
                tvValue3.setText("Z: " + value3 + " m/s2");
                break;
            case TYPE_LINEAR_ACCELERATION:
                tvValue1.setText("X: " + value1 + " m/s2");
                tvValue2.setText("Y: " + value2 + " m/s2");
                tvValue3.setText("Z: " + value3 + " m/s2");
                break;
            case TYPE_ROTATION_VECTOR:
                tvValue1.setText("X: " + value1 + " degrees");
                tvValue2.setText("Y: " + value2 + " degrees");
                tvValue3.setText("Z: " + value3 + " degrees");
                break;
            case TYPE_RELATIVE_HUMIDITY:
                //Not implemented for this version
                break;
            case TYPE_AMBIENT_TEMPERATURE:
                //Not implemented for this version
                break;
            case TYPE_MAGNETIC_FIELD_UNCALIBRATED:
                tvValue1.setText("X: " + value1 + " μT");
                tvValue2.setText("Y: " + value2 + " μT");
                tvValue3.setText("Z: " + value3 + " μT");
                break;
            case TYPE_GAME_ROTATION_VECTOR:
                tvValue1.setText("X: " + value1 + " degrees");
                tvValue2.setText("Y: " + value2 + " degrees");
                tvValue3.setText("Z: " + value3 + " degrees");
                break;
            case TYPE_GYROSCOPE_UNCALIBRATED:
                tvValue1.setText("X: " + value1 + " rad/s");
                tvValue2.setText("Y: " + value2 + " rad/s");
                tvValue3.setText("Z: " + value3 + " rad/s");
                break;
            case TYPE_SIGNIFICANT_MOTION:
                //Not implemented for this version
                break;
            case TYPE_STEP_DETECTOR:
                //Not implemented for this version
                break;
            case TYPE_STEP_COUNTER:
                //Not implemented for this version
                break;
            case TYPE_GEOMAGNETIC_ROTATION_VECTOR:
                //Not implemented for this version
                break;
            default:
                break;
        }
    }
}
