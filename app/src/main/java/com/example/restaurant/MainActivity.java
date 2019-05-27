package com.example.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CategoriesRequest.Callback {

    // Initialize variables
    ListView listView;

    // Show main layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the categories from the server
        CategoriesRequest x = new CategoriesRequest(this);
        x.getCategories(this);

        // Activate the listeners
        listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(new checkItemClick());

    }

    // Put the information from the server in the listview according to the category adapter
    @Override
    public void gotCategories(ArrayList<String> categories) {
        Toast.makeText(this, categories.get(0), Toast.LENGTH_LONG).show();
        CategoryAdapter adapter = new CategoryAdapter(MainActivity.this, R.layout.categories_layout, categories);
        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
    }

    // If an error is returned by the server, print message in Toast
    @Override
    public void gotCategoriesError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    // Set listview item click listener
    private class checkItemClick implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // Get the position of the clicked item
            String clickedCategory = (String) parent.getItemAtPosition(position);

            // Start the menu activity, and give if the clicked category
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            intent.putExtra("category" , clickedCategory);
            startActivity(intent);
            finish();
        }
    }
}
