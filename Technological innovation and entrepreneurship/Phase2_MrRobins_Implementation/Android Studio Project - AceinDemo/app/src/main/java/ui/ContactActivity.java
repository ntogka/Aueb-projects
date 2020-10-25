package com.example.georgiosmoschovis.aceindemo.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.georgiosmoschovis.aceindemo.R;

/**
 * This class implements a Users' Contact Activity.
 */
public class ContactActivity extends AppCompatActivity implements UIDialogTemplate {

    com.example.georgiosmoschovis.aceindemo.services.ECustomConfiguration conf;
    com.example.georgiosmoschovis.aceindemo.services.ECustomReservation reserv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        conf = (com.example.georgiosmoschovis.aceindemo.services.ECustomConfiguration)getIntent().getSerializableExtra("EXTRA_CONFIGURATION");
        reserv = (com.example.georgiosmoschovis.aceindemo.services.ECustomReservation)getIntent().getSerializableExtra("EXTRA_RESERVATION");
        Log.e("INVOK.C_RESERVATION", reserv.getReservation().toString());
        TextView text1 = (TextView)findViewById(R.id.textView1);
        String s = conf.getname();
        text1.setText(s);
        ImageView image = (ImageView)findViewById(R.id.config_image);
        Drawable dr = getResources().getDrawable(conf.getSummary().getView());
        image.setBackground(dr);
        Button send = (Button)findViewById(R.id.button);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reserv.contact(ContactActivity.this);
                Toast.makeText(ContactActivity.this , "Message sent!" , Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public String[] getFurtherDetails() {
        String[] args = new String[1];
        TextView text2 = (TextView)findViewById(R.id.button);
        args[0] = text2.getText().toString();
        return args;
    }
}
