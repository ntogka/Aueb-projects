package com.example.georgiosmoschovis.aceindemo.services;

import java.util.List;

/**
 * This class implements Reservations' previews.
 */
public class ECustomPreview {
	com.example.georgiosmoschovis.aceindemo.domain.ECustomer customer;
	List<com.example.georgiosmoschovis.aceindemo.domain.EReservation> reservations;
	ECustomReservationItem element;
	
	public ECustomPreview(String clientID, com.example.georgiosmoschovis.aceindemo.dao.CustomersDAO dao) {
		List<com.example.georgiosmoschovis.aceindemo.domain.ECustomer> data = dao.findAll();
		for(com.example.georgiosmoschovis.aceindemo.domain.ECustomer c: data) {
			if(c.getID() == clientID)customer = c;
		}
	}
	
	public ECustomPreview submit(String ID, com.example.georgiosmoschovis.aceindemo.dao.CustomersDAO dao) {
		ECustomPreview e = new ECustomPreview(ID, dao);
		return e;
	}
	
	public ECustomPreview confirm(com.example.georgiosmoschovis.aceindemo.domain.EReservation reservationSelected) {
		this.element = new ECustomReservationItem(reservationSelected, customer, reservationSelected.getInsurance());
		return this;
	}
	
	public List<com.example.georgiosmoschovis.aceindemo.domain.EReservation> getReservations() {
		this.reservations = customer.getReservations();
		return this.reservations;
	}
	
	public ECustomReservationItem getElement() {
		return element;
	}
}
