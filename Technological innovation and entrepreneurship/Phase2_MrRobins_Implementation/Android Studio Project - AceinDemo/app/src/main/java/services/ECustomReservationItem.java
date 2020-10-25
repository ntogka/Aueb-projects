package com.example.georgiosmoschovis.aceindemo.services;

import java.io.Serializable;
import java.util.List;

/**
 * This class implements calculations on Escape Rooms Reservations.
 */
public class ECustomReservationItem implements Serializable {
	com.example.georgiosmoschovis.aceindemo.domain.EReservation reservation;
	com.example.georgiosmoschovis.aceindemo.domain.ECustomer customer;
	com.example.georgiosmoschovis.aceindemo.domain.EInsurance insurance;
	
	public ECustomReservationItem(com.example.georgiosmoschovis.aceindemo.domain.EReservation res, com.example.georgiosmoschovis.aceindemo.domain.EInsurance insurance, String custUserName, com.example.georgiosmoschovis.aceindemo.dao.CustomersDAO dao2) {
		this.reservation = res;
		this.insurance = insurance;
		List<com.example.georgiosmoschovis.aceindemo.domain.ECustomer> data2 = dao2.findAll();
		for(com.example.georgiosmoschovis.aceindemo.domain.ECustomer c: data2) {
			if(c.getLoginData().getUsername().equals(custUserName))
				this.customer = c;
		}
	}
	
	public ECustomReservationItem(com.example.georgiosmoschovis.aceindemo.domain.EReservation res, com.example.georgiosmoschovis.aceindemo.domain.ECustomer cust, com.example.georgiosmoschovis.aceindemo.domain.EInsurance ins) {
		this.reservation = res;
		this.customer = cust;
		this.insurance = ins;
	}

	public ECustomReservationItem(com.example.georgiosmoschovis.aceindemo.domain.EReservation res) {
		this.reservation = res;
		this.customer = res.getCustomer();
		this.insurance = res.getInsurance();
	}
	
	public com.example.georgiosmoschovis.aceindemo.domain.ECustomer getCustomer(){
		return this.customer;
	}
	
	public com.example.georgiosmoschovis.aceindemo.domain.EInsurance getInsurance(){
		return this.insurance;
	}
	
	public String getReservationInformation() {
		return this.reservation + "\n" + this.customer + "\n" + this.insurance;
	}
}
