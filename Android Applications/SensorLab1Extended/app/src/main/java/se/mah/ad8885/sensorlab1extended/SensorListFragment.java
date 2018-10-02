package se.mah.ad8885.sensorlab1extended;

import android.hardware.Sensor;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

/**
 * Displays a list of all the sensors present in the device running the application.
 */

public class SensorListFragment extends Fragment {

    private ListView list;
    private List<Sensor> sensorList;
    private MainActivity main;

    public SensorListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sensor_list, container, false);
        main = (MainActivity) getActivity();
        sensorList = main.getSensorList();
        list = (ListView) view.findViewById(R.id.LVSensor);
        list.setAdapter(new SensorAdapter(getActivity(), sensorList));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                main.setSensor(position);
            }
        });
        return view;
    }

}
