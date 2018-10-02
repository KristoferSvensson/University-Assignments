package se.mah.ad8885.sensorlab4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Created by Kristofer Svensson on 2017-02-21.
 */

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startLogInActivity();
    }

    private void startLogInActivity() {
        Intent i = new Intent(this, LogInActivity.class);
        startActivity(i);
    }
}
