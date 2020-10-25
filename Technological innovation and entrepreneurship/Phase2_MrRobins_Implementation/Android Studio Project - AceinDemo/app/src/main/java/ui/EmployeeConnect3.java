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
 * This class implements an Employee Login Activity. v3
 */
public class EmployeeConnect3 extends AppCompatActivity {

    Button mNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_connect3);

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
                    Toast toast = Toast.makeText(EmployeeConnect3.this, "Employee verified!",Toast.LENGTH_LONG);
                    toast.show();

                    Intent intent = new Intent(EmployeeConnect3.this, BillingActivity.class);
                    finish();
                    startActivity(intent);
                } else {
                    Log.e("ERROR", "Wrong credentials.");
                    Toast toast = Toast.makeText(EmployeeConnect3.this, "Wrong credentials.",Toast.LENGTH_LONG);
                    toast.show();
                    finish();
                    startActivity(getIntent());
                }
            }
        });
    }
}
