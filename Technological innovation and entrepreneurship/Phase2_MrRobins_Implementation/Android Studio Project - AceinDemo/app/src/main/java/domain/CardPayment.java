package com.example.georgiosmoschovis.aceindemo.domain;
import java.util.Date;

/**
 * This class illustrates a Card Payment.
 */
public class CardPayment extends EPayment {
	/* Domain Model: Instance variables */
    private String code;
    private String name;
    private Date expDate;

    /* Overloaded Constructor */
    public CardPayment(EPayment p, String code, String name, Date expDate) {
    	super(p.getPassword(), p.getOption(), p.getDate(), p.getPayment());
    	this.code = code;
        this.name = name;
        this.expDate = expDate;
    }

    /* Getters */
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Date getExpDate() {
        return expDate;
    }
}
