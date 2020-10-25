package com.example.georgiosmoschovis.aceindemo.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Date;

import com.example.georgiosmoschovis.aceindemo.R;
import com.example.georgiosmoschovis.aceindemo.services.ECustomReservation;

/**
 * This class implements a Reservation Activity.
 */
public class ReservationActivity extends AppCompatActivity {

    com.example.georgiosmoschovis.aceindemo.services.ECustomConfiguration conf;
    com.example.georgiosmoschovis.aceindemo.services.ECustomReservation reserv;
    String s1;
    EditText text2;
    RadioGroup sel;
    RadioButton rec, inv, chk, btc;
    final String[] newFieldValues = new String[2];

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        conf = (com.example.georgiosmoschovis.aceindemo.services.ECustomConfiguration)getIntent().getSerializableExtra("EXTRA_CONFIGURATION");
        sel = (RadioGroup)findViewById(R.id.groupaki);
        s1 = getIntent().getStringExtra("EXTRA_USERNAME");
        TextView text1 = (TextView)findViewById(R.id.textView1);
        String s = conf.getname();
        text1.setText(s);
        this.fillIn();
        ImageView image = (ImageView)findViewById(R.id.config_image);
        Drawable dr = getResources().getDrawable(conf.getSummary().getView());
        image.setBackground(dr);
        Button send = (Button)findViewById(R.id.button);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assignNewValues();
                Date date = new Date();
                date.setHours(0);
                reserv = new com.example.georgiosmoschovis.aceindemo.services.ECustomReservation();
                reserv = reserv.submit(s1, conf.getconfiguredInsurance(), date, newFieldValues[0], Integer.parseInt(newFieldValues[1]), DAO_UI_Adapter.getCUST().getDAO(), DAO_UI_Adapter.getEMPL().getDAO());
                Log.e("SUBM_RESERVATION", reserv.getReservation().toString());
                Intent intent = new Intent(ReservationActivity.this, ReservationSummary.class);
                intent.putExtra("EXTRA_CONFIGURATION", conf);
                intent.putExtra("EXTRA_RESERVATION", reserv);
                intent.putExtra("EXTRA_USERNAME", s1);
                startActivity(intent);
            }
        });
        Button reload = (Button)findViewById(R.id.button2);
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillIn();
            }
        });
    }

    public void fillIn() {
        rec = (RadioButton)findViewById(R.id.receipt);
        inv = (RadioButton)findViewById(R.id.invoice);
        chk = (RadioButton)findViewById(R.id.check);
        btc = (RadioButton)findViewById(R.id.bitcoin);
        rec.setChecked(true);
        text2 = (EditText)findViewById(R.id.editText2);
        text2.setText(conf.getconfiguredInsurance().getCapacity()+"");
    }

    public void assignNewValues() {
        rec = (RadioButton)findViewById(R.id.receipt);
        inv = (RadioButton)findViewById(R.id.invoice);
        chk = (RadioButton)findViewById(R.id.check);
        btc = (RadioButton)findViewById(R.id.bitcoin);
        if(rec.isChecked()) newFieldValues[0] = rec.getText().toString();
        else if(inv.isChecked()) newFieldValues[0] = inv.getText().toString();
        else if(chk.isChecked()) newFieldValues[0] = chk.getText().toString();
        else if(btc.isChecked()) newFieldValues[0] = btc.getText().toString();
        text2 = (EditText)findViewById(R.id.editText2);
        newFieldValues[1] = text2.getText().toString();
        for(int i = 0; i < 2; i++) Log.e("VALUE "+i, newFieldValues[i]);
    }
}