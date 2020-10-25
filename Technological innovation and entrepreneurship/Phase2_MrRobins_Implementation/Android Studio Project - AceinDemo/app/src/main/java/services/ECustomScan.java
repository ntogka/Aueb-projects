package com.example.georgiosmoschovis.aceindemo.services;

import java.util.Date;

import com.example.georgiosmoschovis.aceindemo.dao.CustomersDAO;
import com.example.georgiosmoschovis.aceindemo.domain.Barcode;
import com.example.georgiosmoschovis.aceindemo.ui.DAO_UI_Adapter;

/**
 * This class implements RFID Scans using an RFID Scanner Service.
 */
public class ECustomScan {

    Barcode barcode;
    CustomersDAO custDAO;

    public ECustomScan() {}

    public ECustomScan(Barcode b, CustomersDAO cust) {
        this.barcode = b;
        this.custDAO = cust;
    }

    public ECustomScan submit(String transponder, String EPC, CustomersDAO cust, RFIDScannerService service){
        Barcode b = new Barcode(transponder, EPC);
        ECustomScan scan = new ECustomScan(b, cust);
        scan.enableChipScan(service);
        return scan;
    }

    public void enableChipScan(RFIDScannerService service) {
        service.scan(barcode);
    }

    public void validate(int duration){
        Date d = new Date();
        d.setHours(0);
        Date d_last = new Date();
        d_last.setMinutes(duration);
        DAO_UI_Adapter.getRFID().addTimestamp(d, d_last);
    }
}
