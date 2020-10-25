package com.example.georgiosmoschovis.aceindemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import com.example.georgiosmoschovis.aceindemo.R;
import com.example.georgiosmoschovis.aceindemo.domain.ECustomer;
import com.example.georgiosmoschovis.aceindemo.domain.EReservation;
import com.example.georgiosmoschovis.aceindemo.services.ECustomReservationItem;
import com.example.georgiosmoschovis.aceindemo.services.EmailMessage;
import com.example.georgiosmoschovis.aceindemo.services.EmailProviderService;

/**
 * This class implements a Secretariat Informative Buzzer Activity.
 */
public class BuzzerActivity extends AppCompatActivity implements EmailProviderService {

    Button search;
    String mes, emp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buzzer);
        emp = getIntent().getStringExtra("EXTRA_EMPLOYEENAME");
        search = (Button)findViewById(R.id.button3);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean action = searchDataBase();
                if(action) {
                    TextView header = (TextView)findViewById(R.id.textView8);
                    header.setVisibility(View.VISIBLE);
                    TextView text = (TextView) findViewById(R.id.textView7);
                    text.setVisibility(View.VISIBLE);
                    text.setMovementMethod(new ScrollingMovementMethod());
                    text.setText(mes);
                    Button share = (Button)findViewById(R.id.button4);
                    share.setVisibility(View.VISIBLE);
                }
            }
        });
        Button share = (Button)findViewById(R.id.button4);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView res = (TextView)findViewById(R.id.textView7);
                String myResRep = "Employee " + emp + " informs:\n";
                myResRep += res.getText().toString();
                EmailMessage message = new EmailMessage("New Insurance Reservations!", myResRep);
                sendEmail(message);
            }
        });
    }

    public boolean searchDataBase() {
        List<ECustomer> customers = DAO_UI_Adapter.getCUST().getDAO().findAll();
        EditText text = (EditText)findViewById(R.id.editText2);
        String rT = text.getText().toString();
        mes = "";
        for(ECustomer c: customers) {
            List<EReservation> rel = c.getReservations();
            if(rel != null) {
                for (EReservation r : rel) {
                    if(r.getID()== Integer.parseInt(rT)) {
                        ECustomReservationItem item = new ECustomReservationItem(r);
                        mes = item.getReservationInformation();
                        return true;
                    }
                }
            }
            if(mes.equals("")) {
                mes = "This reservation ID does not exist!"; return false;
            }
        }
        return false;
    }

    public void sendEmail(EmailMessage message) {
        Log.i("Send email", "");
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
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
