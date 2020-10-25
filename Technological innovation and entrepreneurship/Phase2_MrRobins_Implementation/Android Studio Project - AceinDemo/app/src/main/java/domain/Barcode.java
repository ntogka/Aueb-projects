package com.example.georgiosmoschovis.aceindemo.domain;

import java.io.Serializable;

/**
 * This class illustrates a Barcode.
 */
public class Barcode implements Serializable {

    	/* Domain Model: Instance variables */
        private String TransponderData;
        private String EPC;

        public Barcode(String transponderData, String EPC) {
            TransponderData = transponderData;
            this.EPC = EPC;
        }

        /* Getters */
        public String getTransponderData() {
        	return TransponderData;
        }
        
        public String getEPC() {
        	return EPC;
        }
    }