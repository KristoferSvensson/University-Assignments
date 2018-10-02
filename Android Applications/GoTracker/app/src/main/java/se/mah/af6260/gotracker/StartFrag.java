package se.mah.af6260.gotracker;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class StartFrag extends Fragment {

    private Button btnStartRun;
    private ImageView ivRun, ivWalk, ivBicycle;
    private TextView tvGps, tvStepDetecter;
    private ImageView ivGps, ivStepdetector;




    public StartFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start, container, false);
        btnStartRun = (Button)view.findViewById(R.id.btnStartRun);
        ivGps = (ImageView)view.findViewById(R.id.ivGps);
        ivStepdetector = (ImageView)view.findViewById(R.id.ivStepdetector);

        btnStartRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).setRunFrag();
            }
        });
        ivRun = (ImageView) view.findViewById(R.id.ivRun);
        ivWalk = (ImageView)view.findViewById(R.id.ivWalk);
        ivBicycle = (ImageView)view.findViewById(R.id.ivBicycle);

        ivRun.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                ivRun.setImageResource(R.drawable.selectedrunning);
                ivWalk.setImageResource(R.drawable.walking);
                ivBicycle.setImageResource(R.drawable.bicycling);
                ((MainActivity)getActivity()).setRunning(true);
                ((MainActivity)getActivity()).setWalking(false);
                ((MainActivity)getActivity()).setCycling(false);
            }
        });
        ivWalk.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                ivWalk.setImageResource(R.drawable.selectedwalking);
                ivRun.setImageResource(R.drawable.running);
                ivBicycle.setImageResource(R.drawable.bicycling);
                ((MainActivity)getActivity()).setRunning(false);
                ((MainActivity)getActivity()).setWalking(true);
                ((MainActivity)getActivity()).setCycling(false);
            }
        });
        ivBicycle.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                ivBicycle.setImageResource(R.drawable.selectedbicycle);
                ivRun.setImageResource(R.drawable.running);
                ivWalk.setImageResource(R.drawable.walking);
                ((MainActivity)getActivity()).setRunning(false);
                ((MainActivity)getActivity()).setWalking(false);
                ((MainActivity)getActivity()).setCycling(true);
            }
        });
        tvGps = (TextView)view.findViewById(R.id.tvGps);
        tvStepDetecter = (TextView)view.findViewById(R.id.tvStepDetecter);
        sensorStatus(((MainActivity)getActivity()).isGpsSensorPresent(), ((MainActivity)getActivity()).isStepSensorPresent());
        return view;
    }

    public void sensorStatus(boolean gps, boolean stepDetector){
        if(gps){
            ivGps.setImageResource(R.drawable.success);
        }else{
            ivGps.setImageResource(R.drawable.error);
        }
        if(stepDetector){
            ivStepdetector.setImageResource(R.drawable.success);
        }else{
            ivStepdetector.setImageResource(R.drawable.error);
        }
    }

}
