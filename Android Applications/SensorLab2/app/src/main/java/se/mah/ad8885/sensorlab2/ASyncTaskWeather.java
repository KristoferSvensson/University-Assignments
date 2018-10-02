/*
package se.mah.ad8885.sensorlab2;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

*/
/**
 * Created by Kristofer Svensson on 2017-02-02.
 *//*


*/
/**
 * Gets weather data from the Open Weather Map API, using AsyncTask.
 *//*

public class ASyncTaskWeather extends AsyncTask<String, Void, String> {

    private MainActivity main;
    private String mURL = "http://api.openweathermap.org/data/2.5/weather?id=2692969&units=metric&appid=0d243700f1782441198dc27b81c5e518";

    public ASyncTaskWeather(MainActivity main) {
        this.main = main;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            URL url = new URL(mURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            } finally {
                urlConnection.disconnect();
            }
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    protected void onPreExecute() {
    }

    protected void onPostExecute(String response) {
        if (response == null) {
            response = "THERE WAS AN ERROR";
        }
        main.processAPIData(response);
    }
}*/
