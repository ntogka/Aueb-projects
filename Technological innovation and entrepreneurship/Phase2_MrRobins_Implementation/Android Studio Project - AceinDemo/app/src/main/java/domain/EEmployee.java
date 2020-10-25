package com.example.georgiosmoschovis.aceindemo.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * This class illustrates an Employee.
 */
public class EEmployee extends User{
	/* Domain Model: Instance variables */
    private String firstName;
    private String lastName;
    private String AMKA;
    
    /* Domain Model - Suite: Entity's Relations */
    private Set<EReservation> reservations;
    private Set<ECustomer> contacts;

    /* Overloaded Constructor */
    public EEmployee(String username, String password, String firstName, String lastName, String AMKA) {
    	super(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.AMKA = AMKA;
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
    
    public  boolean insertContact(ECustomer customer) {
    	if(customer != null) {
    		if(contacts == null )
    			contacts = new HashSet<ECustomer>();
    		if (!contacts.contains(customer)) {
    				this.contacts.add(customer);
    				return true;
    		}
    		else
    			System.err.println("Contact with this customer already registered!");
    	}
    	else 
    		System.err.println("System trying to handle null values. Insertion failed!");
    	return false;
    }
    
    public void addReservation(EReservation reservation) {
    	if(this.insertReservation(reservation))
    		reservation.setEmployee(this);
    }
    
    public void addContact(ECustomer customer) {
    	if(this.insertContact(customer))
    		customer.setEmployee(this);
    }
    
    /* Getters */
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAMKA() {
        return AMKA;
    }
    
    public Set<EReservation> getReservations(){
    	return reservations;
    }
    
    public Set<ECustomer> getContacts(){
    	return contacts;
    }
}
