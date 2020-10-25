package com.example.georgiosmoschovis.aceindemo.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * This class illustrates a generic Insurance.
 */
public abstract class EInsurance implements Serializable {
	/* Domain Model: Instance variables */
    private String name;
    private String category;
    private int duration;
    private int capacity;
    public static int ID;
public int CurrID;
    
    /* Domain Model - Suite: Entity's Relations */
    private Set<EReservation> reservations;

    /* Overloaded Constructor */
    public EInsurance(String name, String difficulty, int duration, int capacity) {
    	this.name = name;
    	this.category = difficulty;
    	this.duration = duration;
    	this.capacity = capacity;
    	this.CurrID = ID++;
    }
    /* Domain building methods */
    public boolean insertReservation(EReservation reservation) {
    	if (reservation != null) {
    		if(reservations == null)
        		reservations = new HashSet<EReservation>();
    		if (!reservations.contains(reservation)) {
    				this.reservations.add(reservation);
    				return true;
    		}
    		else 
    			System.err.println("This reservation is already registered!");
    	}
    	else 
    		System.err.println("System trying to handle null values. Insertion failed!");
    	return false;
    }
    
    public void addReservation(EReservation reservation) {
    	if(this.insertReservation(reservation))
    		reservation.setInsurance(this);
    }

    /* Getters */
    public String getName() {
    	return this.name;
    }
    
    public String getDifficulty() {
        return category;
    }

    public int getDuration() {
        return duration;
    }

    public int getCapacity() {
        return capacity;
    }
    
    public abstract Money getPrice() ;

    public String getInsuranceName() {
        return name;
    }
    
    public Set<EReservation> getReservations() {
    	return reservations;
    }

    public String getInsuranceDescription() {
        return ("Category: "+this.getDifficulty() +"\nDuration (in years): "+ this.getDuration() + "\nMax. insured : " + this.getCapacity() );
    }

    public String toString(){
        return ("Insurance Details: \nName: "+this.getInsuranceName()+"\nCategory: "+this.getDifficulty() +"\nDuration : "+ this.getDuration() + " years\nMax. insured : " + this.getCapacity() );
    }

}
