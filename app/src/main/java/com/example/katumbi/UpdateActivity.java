package com.example.katumbi;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    DBHelper dbHelper;

    Button btnChoose;
    long count;
    long newCount = ++count;
    String person;

    private RadioGroup radioGroup;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        btnChoose = (Button) findViewById(R.id.btn_choose);

        dbHelper = new DBHelper(this);

        Cursor cursor = dbHelper.viewData();
        while (cursor.moveToNext()) {
            //id = cursor.getString(0);
            count = cursor.getInt(5);
        }

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                person = radioButton.getText().toString();

                dbHelper.updateData(person, newCount);

                if(dbHelper.updateData(person, newCount)==true){
                    Toast.makeText(UpdateActivity.this, "Count added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UpdateActivity.this, "Error adding count", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}