package com.example.georgiosmoschovis.aceindemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import com.example.georgiosmoschovis.aceindemo.R;
import com.example.georgiosmoschovis.aceindemo.domain.ECustomer;
import com.example.georgiosmoschovis.aceindemo.domain.EReservation;
import com.example.georgiosmoschovis.aceindemo.services.ECustomReservationItem;
import com.example.georgiosmoschovis.aceindemo.services.ECustomScan;
import com.example.georgiosmoschovis.aceindemo.services.RFIDStubScannerService;

/**
 * This class implements an RFID Scanner Activity.
 */
public class ScannerActivity extends AppCompatActivity {

    Button activate, enable;
    EReservation reservation;
    String customerID, messDB1;
    ECustomer customer;
    ECustomScan scan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        customerID = getIntent().getStringExtra("EXTRA_ID");
        EditText serv = (EditText)findViewById(R.id.editText10);
        serv.setEnabled(false);
        searchDataBase0();
        EditText t4 = (EditText)findViewById(R.id.editText4);
        t4.setEnabled(false);
        EditText t5 = (EditText)findViewById(R.id.editText5);
        t5.setEnabled(false);
        activate = (Button)findViewById(R.id.button2);
        enable = (Button)findViewById(R.id.button);
        enable.setEnabled(false);
        activate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText t2 = (EditText)findViewById(R.id.editText2);
                String transponder = t2.getText().toString();
                EditText t3 = (EditText)findViewById(R.id.editText3);
                String EPC = t3.getText().toString();
                if(searchDataBase1(transponder, EPC)) {
                    scan = new ECustomScan();
                    scan = scan.submit(transponder, EPC, DAO_UI_Adapter.getCUST().getDAO(), new RFIDStubScannerService());
                    enable.setEnabled(true);
                    activate.setEnabled(false);
                    t2.setEnabled(false);
                    t3.setEnabled(false);
                    EditText t4 = (EditText)findViewById(R.id.editText4);
                    t4.setEnabled(true);
                    EditText t5 = (EditText)findViewById(R.id.editText5);
                    t5.setText(customerID);
                } else {
                    Toast toast = Toast.makeText(ScannerActivity.this, messDB1, Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
        enable.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  EditText t4 = (EditText)findViewById(R.id.editText4);
                  int resID = Integer.parseInt(t4.getText().toString());
                  boolean db = searchDataBase2(resID);
                  if(db) {
                    scan.validate(reservation.getInsurance().getDuration());
                      enable.setEnabled(false);
                      ECustomReservationItem text = new ECustomReservationItem(reservation);
                      EditText t6 = (EditText)findViewById(R.id.editText6);
                      t6.setText(text.getReservationInformation());
                  } else {
                      Toast toast = Toast.makeText(ScannerActivity.this, "Invalid reservation data!", Toast.LENGTH_LONG);
                      toast.show();
                  }
              }
      });
    }

    public void searchDataBase0() {
        List<ECustomer> customers = DAO_UI_Adapter.getCUST().getDAO().findAll();
        for(ECustomer c: customers) {
            if(customerID.equals(c.getID())) {
                customer = c;
                EditText t2 = (EditText)findViewById(R.id.editText2);
                t2.setText(c.getRFID().getTransponderData());
                EditText t3 = (EditText)findViewById(R.id.editText3);
                t3.setText(c.getRFID().getEPC());
            }
        }
    }

    public boolean searchDataBase1(String x1, String x2) {
        List<ECustomer> customers = DAO_UI_Adapter.getCUST().getDAO().findAll();
        for(ECustomer c: customers) {
            if(customerID.equals(c.getID())) {
                if(x1.equals(c.getRFID().getTransponderData()) && x2.equals(c.getRFID().getEPC()))
                    return true;
                else {
                    messDB1 = "Invalid RFID data!";
                    return false;
                }
            }
        }
        messDB1 = "Access denied!";
        return false;
    }

    public boolean searchDataBase2(int x){
        List<EReservation> reserv = customer.getReservations();
        if(reserv != null) {
            for (EReservation r : reserv) {
                if (r.getID() == x) {
                    reservation = r;
                    return true;
                }
            }
        }
        return false;
    }
}
