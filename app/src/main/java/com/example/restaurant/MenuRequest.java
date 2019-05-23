package com.example.restaurant;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener  {
    private Context context;
    private Callback callback;

    public MenuRequest(Context incomingContext) {
        this.context = incomingContext;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        callback.gotMenuError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            ArrayList arrayList = new ArrayList();
            JSONArray menuList = response.getJSONArray("items");
            for (int i = 0; i < menuList.length(); i++) {
                JSONObject menuItem = menuList.getJSONObject(i);
                String name = menuItem.getString("name");
                String description = menuItem.getString("description");
                String image = menuItem.getString("image_url");
                int price = menuItem.getInt("price");
                String category = menuItem.getString("category");
                MenuItem inputMenuItem =  new MenuItem(name, description, image, price, category);
                arrayList.add(inputMenuItem);
            }
            Log.d("tag", String.valueOf(arrayList));
            callback.gotMenu(arrayList);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getMenu(Callback activity, String category) {
        this.callback = activity;
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/menu?category="+ category, null, this, this);
        queue.add(jsonObjectRequest);
    }

    public interface Callback {
        void gotMenu(ArrayList<MenuItem> menuItems);
        void gotMenuError(String message);
    }

}
