package com.example.restaurant;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener  {
    private Context context;
    private Callback callback;

    // Constructor
    public CategoriesRequest(Context incomingContext) {
        this.context = incomingContext;
    }

    // If error is returned, get the message
    @Override
    public void onErrorResponse(VolleyError error) {
        callback.gotCategoriesError(error.getMessage());
    }

    // If server responds as expected...
    @Override
    public void onResponse(JSONObject response) {
        try {
            // Make a new arraylist, and save the array from server in JSONArray
            ArrayList arrayList = new ArrayList();
            JSONArray categoryList = response.getJSONArray("categories");

            // Loop over server information and save the strings into the new arrayList
            for (int i = 0; i < categoryList.length(); i++) {
                arrayList.add(categoryList.getString(i));
            }
            // Return the information via callback
            callback.gotCategories(arrayList);

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Parse information back to request page
    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    // Make the request from the server
    public  void getCategories(Callback activity) {
        this.callback = activity;
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/categories", null, this, this);
        queue.add(jsonObjectRequest);
    }
}
