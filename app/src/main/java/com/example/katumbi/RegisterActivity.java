package com.example.katumbi;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button btnreg, btnlogin;
    EditText txtfname, txtlname, txtpass, txtemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        openHelper = new DBHelper(this);
        txtfname = (EditText) findViewById(R.id.txtfname);
        txtlname = (EditText) findViewById(R.id.txtlname);
        txtpass = (EditText) findViewById(R.id.txtpass);
        txtemail = (EditText) findViewById(R.id.txtemail);

        btnlogin = (Button) findViewById(R.id.btnlogin);
        btnreg = (Button) findViewById(R.id.btnreg);

        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = openHelper.getWritableDatabase();

                String fname = txtfname.getText().toString();
                String lname = txtlname.getText().toString();
                String pass = txtpass.getText().toString();
                String email = txtemail.getText().toString();

                if(fname.isEmpty() || lname.isEmpty() || pass.isEmpty() || email.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Fill in all the fields", Toast.LENGTH_LONG).show();
                }else{
                    insertdata(fname, lname, pass, email);
                    Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void insertdata(String fname, String lname, String pass, String email) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.COL_2, fname);
        contentValues.put(DBHelper.COL_3, lname);
        contentValues.put(DBHelper.COL_4, pass);
        contentValues.put(DBHelper.COL_5, email);

        long id = db.insert(DBHelper.TABLE_NAME, null, contentValues);

        contentValues.put(DBHelper.COL_1, id);
    }
}




