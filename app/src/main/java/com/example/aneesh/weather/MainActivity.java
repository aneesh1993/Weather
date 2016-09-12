package com.example.aneesh.weather;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView city;
    TextView temp;
    TextView pressure;
    TextView humidity;
    //ImageView icon;
    String cityName;
    int press, humid;
    float temperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        city = (TextView)findViewById(R.id.city);
        temp = (TextView)findViewById(R.id.temp);
        pressure = (TextView)findViewById(R.id.pressure);
        humidity = (TextView)findViewById(R.id.humidity);
        city.setText("Rome,IT");
        //cityName = city.getText().toString();
    }

    public void onButtonClick(View view) throws JSONException {

        cityName = city.getText().toString();

        fetchWeatherTask task = new fetchWeatherTask();
        task.execute(new String[] {cityName});

    }

    private class fetchWeatherTask extends AsyncTask<String, Void, JSONObject>{

        @Override
        protected JSONObject doInBackground(String... params){
            JSONObject JsonResponse;
            httpClient client = new httpClient();
            JsonResponse = client.getWeather(params[0]);
            return JsonResponse;
        }

        @Override
        protected void onPostExecute(JSONObject JsonResponse){
            jsonParser jsonParserObj = new jsonParser();

            JSONObject main = jsonParserObj.getObject("main", JsonResponse);
            temperature = jsonParserObj.getFloat("temp", main);
            temp.setText(temperature + "");

            press = jsonParserObj.getInt("pressure", main);
            pressure.setText(press + "");

            humid = jsonParserObj.getInt("humidity", main);
            humidity.setText(humid + "");
        }
    }

}
