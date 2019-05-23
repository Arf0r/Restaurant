package com.example.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MenuItemActivity extends AppCompatActivity {
    TextView titleDish;
    TextView priceDish;
    TextView descriptionDish;
    ImageView url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        Intent intent = getIntent();
        MenuItem menuItem = (MenuItem) intent.getSerializableExtra("clickedDish");

        titleDish = findViewById(R.id.titleDish);
        priceDish = findViewById(R.id.priceDish);
        descriptionDish = findViewById(R.id.descriptionDish);
        url = findViewById(R.id.imageDish);

        titleDish.setText(menuItem.getName());
        priceDish.setText(String.valueOf(menuItem.getPrice()));
        descriptionDish.setText(menuItem.getDescription());
        Picasso.with(this).load(menuItem.getImageUrl()).into(url);


    }
}
