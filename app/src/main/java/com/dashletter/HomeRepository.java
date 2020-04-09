package com.dashletter;

import android.app.Application;
import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeRepository {
    private Application application;

    public HomeRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<ArrayList<HomeInfo>> getData() {

        final MutableLiveData<ArrayList<HomeInfo>> mutableLiveData = new MutableLiveData<>();
        final ArrayList<HomeInfo> arrayList = new ArrayList<>();

        String json_url = PreferenceManager.getDefaultSharedPreferences(application).getString("CATEGORY_URL", "https://dashletter-backend.herokuapp.com/blog/?category=all").trim();
        //String json_url = "https://dashletter-backend.herokuapp.com/blog/?category=all";


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, json_url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                int i;
                for (i=0 ; i<response.length() ; i++) {
                    try {
                        JSONObject jsonObject = (JSONObject) response.get(i);
                        JSONObject source = jsonObject.getJSONObject("source");
                        String source2 = source.getString("name");
                        String title = jsonObject.getString("title");
                        String thumbnail = jsonObject.getString("urlToImage");
                        String description = jsonObject.getString("description");
                        //int last = content.lastIndexOf("[+");
//                        String content2 = content.substring(0, last);
                        String url = jsonObject.getString("url");
                        if (thumbnail.equals("")) {
                            thumbnail = "hahaha";
                        }
                        HomeInfo homeInfo = new HomeInfo(title, thumbnail, source2, description, url);
                        arrayList.add(homeInfo);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                mutableLiveData.postValue(arrayList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(application).addToRequestQueue(jsonArrayRequest);
        return mutableLiveData;
    }
}
