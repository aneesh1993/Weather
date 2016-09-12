package com.example.aneesh.weather;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Aneesh on 9/11/2016.
 */
public class jsonParser {

    public JSONObject getObject(String tagName, JSONObject jobj)  {
        JSONObject subObj = null;
        try {
            subObj = jobj.getJSONObject(tagName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return subObj;
    }

    public int getInt(String tagName, JSONObject jobj) {
        int subint = 0;
        try {
            subint = jobj.getInt(tagName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return subint;
    }

    public float getFloat(String tagName, JSONObject jobj) {
        float subfloat = 0;
        try {
            subfloat = (float) jobj.getDouble(tagName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return subfloat;
    }
}
