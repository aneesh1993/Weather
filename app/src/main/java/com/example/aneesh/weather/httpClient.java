package com.example.aneesh.weather;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Aneesh on 9/11/2016.
 */
public class httpClient {
    private static String base_url = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static String api_key = "2e94f426ee13c1b9005eff2a9a09537a";
    private static String final_url;

    public JSONObject getWeather(String location){
        HttpURLConnection con = null;
        InputStream is = null;
        final_url = base_url + location + "&appid=" + api_key;

        try{
            con = (HttpURLConnection) new URL(final_url).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();

            // Reading the response
            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ( (line = br.readLine()) != null )
                buffer.append(line + "rn");

            is.close();
            con.disconnect();
            JSONObject jsonObj = new JSONObject(buffer.toString());
            return jsonObj;

        }catch (Throwable t) {
            t.printStackTrace();
        }finally {
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }
        return null;
    }
}
