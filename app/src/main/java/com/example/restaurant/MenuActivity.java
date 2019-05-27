package com.example.restaurant;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuRequest.Callback {
    private MenuItemAdapter menuAdapter;
    // Initialize variable
    ListView list;

    // Show the activity menu layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Save the intent from the last activity including the category that was clicked
        Intent intent = getIntent();
        String category = (String) intent.getSerializableExtra("category");

        // Make request from server for menu items in the clicked category
        MenuRequest x = new MenuRequest(MenuActivity.this);
        x.getMenu( MenuActivity.this, category);

        // Activate the listeners
        list = findViewById(R.id.menuList);
        list.setOnItemClickListener(new checkItemClick());
    }

    // Put the information from the server into a the listview
    @Override
    public void gotMenu(ArrayList<MenuItem> menuItems) {
        menuAdapter = new MenuItemAdapter(MenuActivity.this, R.layout.menu_items_layout, menuItems);
        list = (ListView) findViewById(R.id.menuList);
        list.setAdapter(menuAdapter);
    }

    // No further action when error is returned
@Override
    public void gotMenuError(String message) {

    }

    // Set a click listener for the listview items
    private class checkItemClick implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // Start Menu item activity, give it the clicked menu items as information
            MenuItem clickedDish = (MenuItem) parent.getItemAtPosition(position);
            Intent intent = new Intent(MenuActivity.this,  MenuItemActivity.class);
            intent.putExtra("clickedDish",clickedDish);

            startActivity(intent);
        }
    }
}
