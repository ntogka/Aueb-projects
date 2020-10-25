package com.example.georgiosmoschovis.aceindemo.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.georgiosmoschovis.aceindemo.R;
import com.example.georgiosmoschovis.aceindemo.services.EmailAddress;
import com.example.georgiosmoschovis.aceindemo.services.EmailMessage;
import com.example.georgiosmoschovis.aceindemo.services.EmailProviderService;

public class ReservationConfirmation extends AppCompatActivity implements EmailProviderService{

    com.example.georgiosmoschovis.aceindemo.services.ECustomConfiguration conf;
    com.example.georgiosmoschovis.aceindemo.services.ECustomReservation reserv;
    String s1, s2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_confirmation);
        conf = (com.example.georgiosmoschovis.aceindemo.services.ECustomConfiguration)getIntent().getSerializableExtra("EXTRA_CONFIGURATION");
        reserv = (com.example.georgiosmoschovis.aceindemo.services.ECustomReservation)getIntent().getSerializableExtra("EXTRA_RESERVATION_DOMAIN");
        s2 = (String)getIntent().getSerializableExtra("EXTRA_MAIL");
        s1 = getIntent().getStringExtra("EXTRA_USERNAME");
        Button send = (Button)findViewById(R.id.button);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reserv = reserv.confirm(DAO_UI_Adapter.getRES().getDAO(), ReservationConfirmation.this, new EmailAddress(s2));
                Toast.makeText(ReservationConfirmation.this , "Success!" , Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ReservationConfirmation.this, ReservationCompletion.class);
                startActivity(intent);
            }
        });
        Button reload = (Button)findViewById(R.id.button2);
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReservationConfirmation.this, ReservationActivity.class);
                intent.putExtra("EXTRA_CONFIGURATION", conf);
                intent.putExtra("EXTRA_USERNAME", s1);
                startActivity(intent);
                finish();
            }
        });
    }

    public void sendEmail(EmailMessage message) {
        Log.i("Send email", "");
        String s1 = message.getTo().getAddress();
        String[] TO = {""}; TO[0] = s1;
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, message.getSubject());
        emailIntent.putExtra(Intent.EXTRA_TEXT, message.getBody());
        emailIntent.setType("message/rfc822");

        try {
            startActivity(Intent.createChooser(emailIntent, "Select your preferred Mail Service."));
            //finish();
            Log.i("Finished", " sending email...");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
