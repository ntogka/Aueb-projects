package com.example.georgiosmoschovis.aceindemo.domain;
import java.io.Serializable;
import java.util.Date;

/**
 * This class illustrates a Payment.
 */
public class EPayment implements Serializable {
	/* Domain Model: Instance variables */
    private String password;
    private String option;
    private Date date;
    private Money amount;
    
    /* Domain Model - Suite: Entity's Relations */
    private EReservation reservation;
    private PaymentReceipt receipt;

    /* Overloaded Constructor */
    public EPayment(String password, String option, Date date, Money value) {
        this.password = password;
        this.option = option;
        this.date = date;
        this.amount = value;
    }
    
    /* Setters */
    public void setReservation(EReservation reservation) {
    	this.reservation = reservation;
    }
    
    public void setReceipt(PaymentReceipt receipt) {
    	this.receipt = receipt;
    }
    
    /* Domain building methods */
    public void addReservation(EReservation reservation) {
    	this.setReservation(reservation);
    	reservation.setPayment(this);
    }
    
    public void addReceipt(PaymentReceipt receipt) {
    	this.setReceipt(receipt);
    	receipt.setPayment(this);
    }

    /* Getters */
    public String getPassword() {
        return password;
    }

    public String getOption() {
        return option;
    }

    public Date getDate() {
        return date;
    }
    
    public EReservation getReservation() {
    	return reservation;
    }
    
    public PaymentReceipt getReceipt() {
    	return receipt;
    }
    
    public Money getPayment() {
    	return amount;
    }
    
    public String toString() {
    	return this.password + ", by " + this.getOption() + " priced at: " + this.amount + ", " + this.date;
    }
}
