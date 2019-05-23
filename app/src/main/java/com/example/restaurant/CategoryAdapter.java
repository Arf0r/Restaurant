package com.example.restaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoryAdapter extends android.widget.ArrayAdapter {

    ArrayList<String> categories;

    public CategoryAdapter (Context context, int resource, ArrayList<String> category) {
        super(context, resource, category);
        categories = category;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.categories_layout, parent, false);
        }

        TextView text = convertView.findViewById(R.id.textView);
        String category = categories.get(position);
        text.setText(category);

        return convertView;
    }
}
