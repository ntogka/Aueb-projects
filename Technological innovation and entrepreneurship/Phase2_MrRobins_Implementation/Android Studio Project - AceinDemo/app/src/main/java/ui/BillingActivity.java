package com.example.georgiosmoschovis.aceindemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
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
import com.example.georgiosmoschovis.aceindemo.domain.EPayment;
import com.example.georgiosmoschovis.aceindemo.domain.EReservation;
import com.example.georgiosmoschovis.aceindemo.domain.PaymentReceipt;
import com.example.georgiosmoschovis.aceindemo.services.ECustomBilling;
import com.example.georgiosmoschovis.aceindemo.services.EmailMessage;
import com.example.georgiosmoschovis.aceindemo.services.EmailProviderService;

/**
 * This class implements a Billing Activity.
 */
public class BillingActivity extends AppCompatActivity implements EmailProviderService {

    Button search;
    String mes;
    EPayment payment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);
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
                } else {
                    Toast toast = Toast.makeText(BillingActivity.this, mes,Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
        Button share = (Button)findViewById(R.id.button4);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentReceipt receipt = payment.getReceipt();
                String code = receipt.getCode();
                String type = receipt.getType().toString();
                String date = receipt.getDate().toString();
                String amount = receipt.getAmount() + "";
                String currency = payment.getPayment().getCurrency().toString();
                String myRecSum = "<B>INSURANCE RESERVATION</B><br>";
                myRecSum += "<B>PAYMENT RECEIPT</B><br><br>";
                myRecSum += "<U>Code:</U> " + code + "<br>";
                myRecSum += "<U>Type:</U> " + type + "<br>";
                myRecSum += "<U>Date:</U> " + date + "<br>";
                myRecSum += "<font color = \"blue\"><U>AMOUNT:</U> " + amount + " " + currency + "</font><br> <br> <br>";
                myRecSum += "<font color = \"red\"><U><B>TOTAL:</U></B> " + amount + " " + currency + "</font><br>";
                EmailMessage message = new EmailMessage("New Insurance Reservations!", myRecSum);
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
                        if(r.getPayment() == null) {
                            mes = "This reservation hasn't been paid!";
                            return false;
                        } else {
                            payment = r.getPayment();
                            ECustomBilling item = new ECustomBilling();
                            item = item.submit(r, r.getPayment());
                            mes = item.getReport();
                            return true;
                        }
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
        String mes = new StringBuilder().append(message.getBody()).toString();
        emailIntent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(mes));
        emailIntent.setType("message/rfc822"); //message/rfc822

        try {
            startActivity(Intent.createChooser(emailIntent, "Select your preferred Mail Service."));
            //finish();
            Log.i("Finished", " sending email...");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}