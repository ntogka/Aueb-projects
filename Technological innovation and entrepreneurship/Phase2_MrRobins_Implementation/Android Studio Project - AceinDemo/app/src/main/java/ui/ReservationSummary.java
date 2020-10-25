package com.example.georgiosmoschovis.aceindemo.ui;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.georgiosmoschovis.aceindemo.R;
import com.example.georgiosmoschovis.aceindemo.services.EmailAddress;
import com.example.georgiosmoschovis.aceindemo.services.EmailMessage;
import com.example.georgiosmoschovis.aceindemo.services.EmailProviderService;

import java.io.Serializable;

/**
 * This class implements a Reservation Summarizing Activity.
 */
public class ReservationSummary extends AppCompatActivity {

    com.example.georgiosmoschovis.aceindemo.services.ECustomConfiguration conf;
    com.example.georgiosmoschovis.aceindemo.services.ECustomReservation reserv;
    String s1;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_summary);
        ActionBar actionBar = getActionBar();
        conf = (com.example.georgiosmoschovis.aceindemo.services.ECustomConfiguration)getIntent().getSerializableExtra("EXTRA_CONFIGURATION");
        reserv = (com.example.georgiosmoschovis.aceindemo.services.ECustomReservation)getIntent().getSerializableExtra("EXTRA_RESERVATION");
        Log.e("INVOK_RESERVATION", reserv.getReservation().toString());
        s1 = getIntent().getStringExtra("EXTRA_USERNAME");
        TextView text1 = (TextView)findViewById(R.id.textView1);
        String s = conf.getname();
        text1.setText(s);
        ImageView image = (ImageView)findViewById(R.id.config_image);
        Drawable dr = getResources().getDrawable(conf.getSummary().getView());
        image.setBackground(dr);
        TextView text2 = (TextView)findViewById(R.id.textView);
        String s2 = reserv.getReport();
        text2.setText(s2);
        Button send = (Button)findViewById(R.id.button);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText text3 = (EditText)findViewById(R.id.editText2);
                String mail = text3.getText().toString();
                Intent intent = new Intent(ReservationSummary.this, ReservationConfirmation.class);
                intent.putExtra("EXTRA_RESERVATION_DOMAIN", reserv);
                intent.putExtra("EXTRA_CONFIGURATION", conf);
                intent.putExtra("EXTRA_USERNAME", s1);
                intent.putExtra("EXTRA_MAIL", mail);
                startActivity(intent);
                //reserv = reserv.confirm(DAO_UI_Adapter.getRES().getDAO(), ReservationSummary.this, new EmailAddress(mail));
                //Toast.makeText(ReservationSummary.this , "Success!" , Toast.LENGTH_LONG).show();
            }
        });
        Button reload = (Button)findViewById(R.id.button2);
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReservationSummary.this, ReservationActivity.class);
                intent.putExtra("EXTRA_CONFIGURATION", conf);
                intent.putExtra("EXTRA_USERNAME", s1);
                startActivity(intent);
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_contact: {
                Intent intent = new Intent(ReservationSummary.this, ContactActivity.class);
                intent.putExtra("EXTRA_CONFIGURATION", conf);
                intent.putExtra("EXTRA_RESERVATION", reserv);
                startActivity(intent);
                break;
            }
        }
        return false;
    }
}
