package com.example.katumbi;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {
    DBHelper dbHelper;
    ArrayList<Object> listItem;
    ArrayAdapter adapter;

    ListView usersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        dbHelper = new DBHelper(this);
        listItem = new ArrayList<>();

        usersList = (ListView) findViewById(R.id.users_list);

        viewData();
        usersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text  = usersList.getItemAtPosition(1).toString();
                Toast.makeText(DisplayActivity.this, "" +text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void viewData() {
        Cursor cursor = dbHelper.viewData();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "No Registered Users", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                listItem.add(cursor.getInt(0));
                listItem.add(cursor.getString(1));
                listItem.add(cursor.getString(2));
                listItem.add(cursor.getString(3));
                listItem.add(cursor.getString(4));
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);
            usersList.setAdapter(adapter);
        }
    }

}
