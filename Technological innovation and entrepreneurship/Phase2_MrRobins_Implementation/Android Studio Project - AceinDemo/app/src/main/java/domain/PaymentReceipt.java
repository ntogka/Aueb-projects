package com.example.georgiosmoschovis.aceindemo.domain;
import java.io.Serializable;
import java.util.Date;

/**
 * This class illustrates a Payment Receipt.
 */
public class PaymentReceipt implements Serializable {
	/* Domain Model: Instance variables */
    private String code;
    private MeansOfPayment type;
    private Date date;
    private float amount;
    
    /* Domain Model - Suite: Entity's Relations */
    private EPayment payment;

    /* Overloaded Constructor */
    public PaymentReceipt(String code, MeansOfPayment type, Date date, float amount) {
        this.code = code;
        this.type = type;
        this.date = date;
        this.amount = amount;
    }
    
    /* Setters */
    public void setPayment(EPayment payment) {
    	this.payment = payment;
    }

    /* Getters */
    public String getCode() {
        return code;
    }

    public MeansOfPayment getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    public float getAmount() { return amount; }
    
    public EPayment getPayment() {
    	return payment;
    }
}
