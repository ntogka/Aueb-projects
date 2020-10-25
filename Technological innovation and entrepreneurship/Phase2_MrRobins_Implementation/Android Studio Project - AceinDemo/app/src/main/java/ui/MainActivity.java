package com.example.georgiosmoschovis.aceindemo.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.georgiosmoschovis.aceindemo.R;

/**
 * This class implements a Customer Login Activity and start for statistics computation.
 */
public class MainActivity extends AppCompatActivity {


    Button mNextButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNextButton = findViewById(R.id.button_connect);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username= (EditText) findViewById(R.id.Username);
                EditText password= (EditText) findViewById(R.id.Password);
                final String username_Str= username.getText().toString();
                final String password_Str= password.getText().toString();
                DAO_UI_Adapter.initialize();
                com.example.georgiosmoschovis.aceindemo.services.ECustomComputation comp = new com.example.georgiosmoschovis.aceindemo.services.ECustomComputation();
                comp.execute();
                com.example.georgiosmoschovis.aceindemo.services.ECustomConnection conn = new  com.example.georgiosmoschovis.aceindemo.services.ECustomConnection();
                conn = conn.submit(username_Str, password_Str, DAO_UI_Adapter.getCUST().getDAO());
                if(conn.getConnectionResult()) {
                    com.example.georgiosmoschovis.aceindemo.domain.Credentials creds = conn.getConnectionData();
                    Log.e("SUCCESS", creds.getUsername());
                    Toast toast = Toast.makeText(MainActivity.this, "Welcome!",Toast.LENGTH_LONG);
                    toast.show();

                    Intent intent = new Intent(MainActivity.this, InsurancePreview.class);
                    intent.putExtra("EXTRA_USERNAME", creds.getUsername());
                    finish();
                    startActivity(intent);
                } else {
                    Log.e("ERROR", "Wrong credentials.");
                    Toast toast = Toast.makeText(MainActivity.this, "Wrong credentials.",Toast.LENGTH_LONG);
                    toast.show();
                    finish();
                    startActivity(getIntent());
                }
            }
        });



    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Exit?")
                .setMessage("Are you sure you want to exit app?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }


}
