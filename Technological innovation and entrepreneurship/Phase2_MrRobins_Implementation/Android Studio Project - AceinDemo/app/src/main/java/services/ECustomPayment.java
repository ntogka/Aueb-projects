package com.example.georgiosmoschovis.aceindemo.services;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.example.georgiosmoschovis.aceindemo.domain.EReservation;
import com.example.georgiosmoschovis.aceindemo.ui.DAO_UI_Adapter;

/**
 * This class implements payments.
 */
public class ECustomPayment implements Serializable {
	int ID;
	static int rawID = 1;
	ECustomTransaction enabled;
	ECustomReservationItem synopsis;
	com.example.georgiosmoschovis.aceindemo.domain.EReservation reservation;
	com.example.georgiosmoschovis.aceindemo.domain.EPayment payment;
	String meansOfPayment;

	public ECustomPayment() {}
	
	public ECustomPayment(com.example.georgiosmoschovis.aceindemo.domain.EPayment payment, String info, com.example.georgiosmoschovis.aceindemo.domain.EReservation reservation, ECustomReservationItem synopsis) {
		this.payment = payment;
		this.meansOfPayment = info;
		this.reservation = reservation;
		this.synopsis = synopsis;
	}
	
	public ECustomPayment submit(String option, Date date, com.example.georgiosmoschovis.aceindemo.domain.EReservation reservation) {
		this.ID = rawID++;
		com.example.georgiosmoschovis.aceindemo.domain.EPayment payment = new com.example.georgiosmoschovis.aceindemo.domain.EPayment("ConfPay #"+ID, option, date, reservation.getInsurance().getPrice());
		ECustomReservationItem item = new ECustomReservationItem(reservation, reservation.getCustomer(), reservation.getInsurance());
		ECustomPayment p = new ECustomPayment(payment, reservation.getPaymentInfo(), reservation, item);
		return p;
	}
	
	public String getConfRequest() {
		String s = "Confirm " + payment.toString() + "?";
		s += "\n\n Pending payment details: \n" + this.synopsis.getReservationInformation();
		return s;
	}
	
	public ECustomPayment submit(Date date) {
		payment.addReservation(reservation);
		int id = reservation.getID();
		List<EReservation> daoRes = DAO_UI_Adapter.getRES().getDAO().findAll();
		for(EReservation r: daoRes) {
			if (reservation.getID() == r.getID()) payment.addReservation(r);
		}
		enabled = (new ECustomTransaction()).submit(payment, date);
		return this;
	}
	
	public String getReport() {
		return "Your payment " + payment.getPassword() + " was successfully sumbited.";
	}
	
	public ECustomTransaction getTransaction() {
		return this.enabled;
	}

	public EReservation getReservation() {
		return reservation;
	}
}
