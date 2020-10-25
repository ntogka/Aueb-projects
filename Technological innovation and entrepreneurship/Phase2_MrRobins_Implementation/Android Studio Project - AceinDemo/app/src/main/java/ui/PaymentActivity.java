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

import java.util.Date;
import java.util.List;

import com.example.georgiosmoschovis.aceindemo.R;
import com.example.georgiosmoschovis.aceindemo.domain.ECustomer;
import com.example.georgiosmoschovis.aceindemo.domain.EReservation;

/**
 * This class implements a Payment Activity.
 */
public class PaymentActivity extends AppCompatActivity {

    com.example.georgiosmoschovis.aceindemo.services.ECustomPayment payment;
    EReservation sampleRes = null;
    String s, mes;
    Button search, send, confirm;
    Date date;
    TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        s = getIntent().getStringExtra("EXTRA_ID");
        search = (Button)findViewById(R.id.button3);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean action = searchDataBase();
                if(!action)
                    Toast.makeText(PaymentActivity.this, mes, Toast.LENGTH_SHORT).show();
                else {
                    search.setEnabled(false);
                    payment = new com.example.georgiosmoschovis.aceindemo.services.ECustomPayment();
                    TextView label = (TextView)findViewById(R.id.textView8);
                    label.setVisibility(View.VISIBLE);
                    TextView option = (TextView)findViewById(R.id.textView3);
                    option.setVisibility(View.VISIBLE);
                    EditText optfield = (EditText)findViewById(R.id.editText3);
                    optfield.setVisibility(View.VISIBLE);
                    send = (Button)findViewById(R.id.button5);
                    send.setVisibility(View.VISIBLE);
                    send.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            send.setEnabled(false);
                            date = new Date();
                            date.setHours(0);
                            payment = payment.submit(new String(), date, sampleRes);
                            TextView label = (TextView)findViewById(R.id.textView9);
                            label.setVisibility(View.VISIBLE);
                            message = (TextView)findViewById(R.id.textView7);
                            message.setVisibility(View.VISIBLE);
                            message.setText(payment.getConfRequest());
                            message.setMovementMethod(new ScrollingMovementMethod());
                            confirm = (Button)findViewById(R.id.button6);
                            confirm.setVisibility(View.VISIBLE);
                            confirm.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Log.e("SUCCESS", "Reached final listener.");
                                    date.setHours(0);
                                    payment = payment.submit(date);
                                    Intent intent = new Intent(PaymentActivity.this, EmployeeConnect.class);
                                    intent.putExtra("EXTRA_PAYMENT", payment);
                                    startActivity(intent);
                                    String result = message.getText().toString() + "\n";
                                    result += payment.getReport();
                                    message.setText(result);
                                    confirm.setEnabled(false);
                                }
                            });
                        }
                    });
                }
            }
        });
    }

    public boolean searchDataBase() {
        List<ECustomer> customers = DAO_UI_Adapter.getCUST().getDAO().findAll();
        EditText text = (EditText)findViewById(R.id.editText2);
        String rT = text.getText().toString();
        mes = "";
        for(ECustomer c: customers) {
            if(s.equals(c.getID())) {
                List<EReservation> rel = c.getReservations();
                if(rel == null) {
                    mes = "You're unauthorized to pay!";
                    return false;
                } else {
                    for (EReservation r : rel) {
                        if(r.getID()== Integer.parseInt(rT)) {
                            if(r.getPayment() == null) {
                                sampleRes = r; return true;
                            } else {
                                mes = "This reservation has already been paid!";
                                return false;
                            }
                        }
                    }
                }
                if(mes.equals("")) {
                    mes = "You're unauthorized to pay!"; return false;
                }
            }
        }
        return false;
    }
}
