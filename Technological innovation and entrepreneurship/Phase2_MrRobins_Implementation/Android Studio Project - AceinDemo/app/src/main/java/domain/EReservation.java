package com.example.georgiosmoschovis.aceindemo.domain;
import java.io.Serializable;
import java.util.Date;

/**
 * This class illustrates a Reservation.
 */
public class EReservation implements Serializable {
	/* Domain Model: Instance variables */
    private Date date;
    private String paymentInfo;
    private int numberOfPeople;
    public static int ID;
    public int CurrID;
    
    /* Domain Model - Suite: Entity's Relations */
    private EEmployee employee;
    private ECustomer customer;
    private EPayment payment;
    private EInsurance insurance;

    /* Overloaded Constructor */
    public EReservation(Date date, String paymentInfo, int numberOfPeople) {
        this.date = date;
        this.paymentInfo = paymentInfo;
        this.numberOfPeople = numberOfPeople;
        this.CurrID = ID++;
    }
    
    /* Setters */
    public void setEmployee(EEmployee employee) {
    	this.employee = employee;
    }

    public void setCustomer(ECustomer customer) {
    	this.customer = customer;
    }

    public void setPayment (EPayment payment) {
    	this.payment = payment;
    }

    public void setInsurance (EInsurance insurance) { this.insurance = insurance; }

    public int getID() { return this.CurrID; }

    /* Getters */
    public Date getDate() {
        return date;
    }

    public String getPaymentInfo() {
        return paymentInfo;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }
    
    public EEmployee getEmployee() {
    	return employee;
    }
    
    public ECustomer getCustomer() {
    	return customer;
    }
    
    public EPayment getPayment () {
    	return payment;
    }
    
    public EInsurance getInsurance () {
    	return insurance;
    }
    
    public String toString() {
    	return "Reservation with ID: " + this.getID() +"  information:\n" + getDate() + " paid by " + getPaymentInfo()  + ", capacity: " + getNumberOfPeople();
    }
}
