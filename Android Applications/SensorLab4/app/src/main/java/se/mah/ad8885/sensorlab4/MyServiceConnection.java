package se.mah.ad8885.sensorlab4;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * Created by Kristofer Svensson on 2017-02-21.
 */

public class MyServiceConnection implements ServiceConnection {

    private final CompassActivity mActivity;

    public MyServiceConnection(CompassActivity a) {
        this.mActivity = a;
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        StepsService.LocalBinder binder = (StepsService.LocalBinder) service;
        mActivity.mService = binder.getService();
        mActivity.mBound = true;
        mActivity.mService.setListenerActivity(mActivity);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        mActivity.mBound = false;
    }
}
