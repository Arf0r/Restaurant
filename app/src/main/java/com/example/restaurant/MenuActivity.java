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

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent intent = getIntent();

        String category = (String) intent.getSerializableExtra("category");

        MenuRequest x = new MenuRequest(MenuActivity.this);
        x.getMenu( MenuActivity.this, category);

        // Activate the listeners
        list = findViewById(R.id.menuList);
        list.setOnItemClickListener(new checkItemClick());
    }

    @Override
    public void gotMenu(ArrayList<MenuItem> menuItems) {
        menuAdapter = new MenuItemAdapter(MenuActivity.this, R.layout.menu_items_layout, menuItems);
        list = (ListView) findViewById(R.id.menuList);
        list.setAdapter(menuAdapter);
    }

    @Override
    public void gotMenuError(String message) {

    }

    private class checkItemClick implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MenuItem clickedDish = (MenuItem) parent.getItemAtPosition(position);

            Intent intent = new Intent(MenuActivity.this,  MenuItemActivity.class);
            intent.putExtra("clickedDish",clickedDish);

            startActivity(intent);
        }
    }
}
