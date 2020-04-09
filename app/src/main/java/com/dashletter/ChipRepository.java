package com.dashletter;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ChipRepository {
    private Application application;

    public ChipRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<ArrayList<ChipInfo>> getData() {
        final MutableLiveData<ArrayList<ChipInfo>> mutableLiveData = new MutableLiveData<>();
        final ArrayList<ChipInfo> arrayList = new ArrayList<>();

        String json_url = "https://dashletter-backend.herokuapp.com/blog/?category=allcategory";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, json_url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {

                    int i;
                    for (i=0 ; i<response.length() ; i++) {
                        JSONObject jsonObject = (JSONObject) response.get(i);
                        String name = jsonObject.getString("name");
                        String url = jsonObject.getString("url");
                        ChipInfo chipInfo = new ChipInfo(name, url);
                        arrayList.add(chipInfo);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
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
