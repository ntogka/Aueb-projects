package com.example.georgiosmoschovis.aceindemo.domain;

/**
 * This class illustrates a Cash Payment.
 */
public class CashPayment extends EPayment {
	/* Overloaded Constructor */
    public CashPayment(EPayment p) {
    	super(p.getPassword(), p.getOption(), p.getDate(), p.getPayment());
    }
}
