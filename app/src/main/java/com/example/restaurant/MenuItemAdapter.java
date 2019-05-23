package com.example.restaurant;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MenuItemAdapter extends ArrayAdapter<MenuItem> {

    ArrayList<MenuItem> menuItems;

    public MenuItemAdapter(Context context, int resource, ArrayList<MenuItem> menuItems) {
        super(context, resource, menuItems);
        this.menuItems = menuItems;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_items_layout, parent, false);
        }

        MenuItem menuItem = menuItems.get(position);

        TextView dishName= convertView.findViewById(R.id.dishName);
        dishName.setText(menuItem.getName());

        TextView dishPrice= convertView.findViewById(R.id.dishPrice);
        dishPrice.setText(String.valueOf(menuItem.getPrice()));

        ImageView dishImage = convertView.findViewById(R.id.dishImage);
        String url = String.valueOf(menuItem.getImageUrl());
        Log.d("tag", url);

        Picasso.with(getContext()).load(url).into(dishImage);


        return convertView;
    }
}