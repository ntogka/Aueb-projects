package com.example.georgiosmoschovis.aceindemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.georgiosmoschovis.aceindemo.R;

/**
 * This class implements an Employee Login Activity. v4
 */
public class EmployeeConnect4 extends AppCompatActivity {

    Button mNextButton;
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_connect4);

        s = getIntent().getStringExtra("EXTRA_ID");
        mNextButton = findViewById(R.id.button_connect);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username= (EditText) findViewById(R.id.Username);
                EditText password= (EditText) findViewById(R.id.Password);
                final String username_Str= username.getText().toString();
                final String password_Str= password.getText().toString();
                com.example.georgiosmoschovis.aceindemo.services.ECustomConnection conn = new  com.example.georgiosmoschovis.aceindemo.services.ECustomConnection();
                conn = conn.submit(username_Str, password_Str, DAO_UI_Adapter.getEMPL().getDAO());
                if(conn.getConnectionResult()) {
                    com.example.georgiosmoschovis.aceindemo.domain.Credentials creds = conn.getConnectionData();
                    Log.e("SUCCESS", creds.getUsername());
                    Toast toast = Toast.makeText(EmployeeConnect4.this, "Employee verified!",Toast.LENGTH_LONG);
                    toast.show();

                    Intent intent = new Intent(EmployeeConnect4.this, ScannerActivity.class);
                    intent.putExtra("EXTRA_ID", s);
                    finish();
                    startActivity(intent);
                } else {
                    Log.e("ERROR", "Wrong credentials.");
                    Toast toast = Toast.makeText(EmployeeConnect4.this, "Wrong credentials.",Toast.LENGTH_LONG);
                    toast.show();
                    finish();
                    startActivity(getIntent());
                }
            }
        });
    }
}