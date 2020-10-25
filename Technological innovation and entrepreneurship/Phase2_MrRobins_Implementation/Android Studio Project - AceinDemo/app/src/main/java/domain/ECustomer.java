package com.example.georgiosmoschovis.aceindemo.domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class illustrates a Customer.
 */
public class ECustomer extends User implements Serializable{
	/* Domain Model: Instance variables */
    private String firstName;
    private String lastName;
    private String ID;
    private Barcode RFID;
    
    /* Domain Model - Suite: Entity's Relations */
    private List<EReservation> reservations;
    private EEmployee employee;

    public ECustomer() {}

    /* Overloaded Constructor */
    public ECustomer(String username, String password, String firstName, String lastName, String ID, Barcode RFID, List<EReservation> reservations) {
        super(username, password);
    	this.firstName = firstName;
        this.lastName = lastName;
        this.ID = ID;
        this.RFID = RFID;
        this.reservations = reservations;
    }
    
    /* Setters */
    public void setEmployee(EEmployee employee) {
    	this.employee = employee;
    }
    
    /* Domain building methods */
   public boolean insertReservation(EReservation reservation) {
    	if (reservation != null) {
    		if(reservations == null)
        		reservations = new ArrayList<EReservation>();
    		if (!reservations.contains(reservation)) {
    				this.reservations.add(reservation);
    				return true;
    		}
    		//else
    		//	System.err.println("This reservation is already registered!");
    	}
    	else 
    		System.err.println("System trying to handle null values. Insertion failed!");
    	return false;
    }
    
    public void addReservation(EReservation reservation) {
    	if(this.insertReservation(reservation))
    		reservation.setCustomer(this);
    }

    /* Getters */
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getID() {
        return ID;
    }

    public Barcode getRFID() {
        return RFID;
    }
    
    public List<EReservation> getReservations() {
    	return reservations;
    }
    
    public EEmployee getEmployee() {
    	return employee;
    }
    
    public String toString() {
    	return "Customer details: \n" + firstName + " " +  lastName + "\nID: " + ID + "\n RFID:" + RFID;
    }
}
