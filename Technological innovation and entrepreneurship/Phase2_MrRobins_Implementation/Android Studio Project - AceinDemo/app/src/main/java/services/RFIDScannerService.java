package com.example.georgiosmoschovis.aceindemo.services;

import com.example.georgiosmoschovis.aceindemo.domain.Barcode;

/**
 * This class illustrates an interface for an RFID Scanner.
 */
public interface RFIDScannerService {
    public void scan(Barcode barcode);
}
