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

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CategoriesRequest x = new CategoriesRequest(this);
        x.getCategories(this);
        Toast.makeText(this, "started", Toast.LENGTH_SHORT).show();

        listView = (ListView) findViewById(R.id.list);

        // Activate the listeners
        listView.setOnItemClickListener(new checkItemClick());

    }

    @Override
    public void gotCategories(ArrayList<String> categories) {
        Toast.makeText(this, categories.get(0), Toast.LENGTH_LONG).show();
        CategoryAdapter adapter = new CategoryAdapter(MainActivity.this, R.layout.categories_layout, categories);
        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
    }

    @Override
    public void gotCategoriesError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private class checkItemClick implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            String clickedCategory = (String) parent.getItemAtPosition(position);

            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            intent.putExtra("category" , clickedCategory);
            startActivity(intent);
            finish();
        }
    }
}
