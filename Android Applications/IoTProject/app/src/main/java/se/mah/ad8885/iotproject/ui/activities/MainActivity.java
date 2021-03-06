package ad8885.mah.se.iotproject.ui.activities;

//import ad1107.mah.se.iotandpeopleproject.R;
//import ad1107.mah.se.iotandpeopleproject.bluetooth.BluetoothManager;
//import ad1107.mah.se.iotandpeopleproject.configuration.ConfigurationActivity;
import se.mah.ad8885.iotproject.bluetooth.BluetoothManager;
import se.mah.ad8885.iotproject.R;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
  private static final String TAG = "MainActivity";
//  private ad8885.mah.se.iotproject.bluetooth.BluetoothManager btManager;
  private btManager;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Handler mHandler = new Handler() {
      @Override public void handleMessage(Message msg) {
        byte[] writeBuf = (byte[]) msg.obj;
        int begin = (int) msg.arg1;
        int end = (int) msg.arg2;

        switch (msg.what) {
          case 1 :
            String writeMessage = new String(writeBuf);
            writeMessage.substring(begin, end);
            Log.d(TAG, "handleMessage: " + writeMessage);
            break;
        }
      }
    };

    // TODO Remove this code only here to test.
    btManager = new BluetoothManager(this, this, mHandler);
    btManager.init();

    // TODO Remove this code only here to start configure activity the lazy way.
    // Intent intent = new Intent(this, ConfigurationActivity.class);
    // startActivity(intent);
  }
}
