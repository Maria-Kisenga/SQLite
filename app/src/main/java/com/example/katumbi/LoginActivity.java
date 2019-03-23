package com.example.katumbi;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Cursor cursor;
    Button btnLogin, btnReg;
    EditText txtEmail, txtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmail=(EditText)findViewById(R.id.txtEmail);
        txtPass=(EditText)findViewById(R.id.txtPass);
        btnLogin=(Button)findViewById(R.id.btnLogin);
        btnReg = (Button)findViewById(R.id.btnReg);

        openHelper=new DBHelper(this);
        db = openHelper.getReadableDatabase();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString();
                String pass = txtPass.getText().toString();

                cursor = db.rawQuery("SELECT *FROM " + DBHelper.TABLE_NAME + " WHERE " + DBHelper.COL_5 + "=? AND " + DBHelper.COL_4 + "=?", new String[]{email, pass});
                if (cursor != null) {
                    if (cursor.getCount() > 0) {
                        Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(LoginActivity.this, DisplayActivity.class);
                        startActivity(intent);

                        finish();

                    } else {
                        Toast.makeText(LoginActivity.this, "Login error", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}

