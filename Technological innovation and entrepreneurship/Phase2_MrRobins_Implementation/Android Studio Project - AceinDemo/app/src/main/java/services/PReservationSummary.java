package com.example.georgiosmoschovis.aceindemo.services;

import android.util.Log;

import java.io.Serializable;

import com.example.georgiosmoschovis.aceindemo.ui.DAO_UI_Adapter;

/**
 * This class illustrates a Reservation Summary.
 */
public class PReservationSummary implements Serializable {
	com.example.georgiosmoschovis.aceindemo.dao.ReservationsDAO dao;
	com.example.georgiosmoschovis.aceindemo.domain.EReservation reservation;
	
	public PReservationSummary(com.example.georgiosmoschovis.aceindemo.dao.ReservationsDAO dao, com.example.georgiosmoschovis.aceindemo.domain.EReservation reservation) {
		this.dao = dao;
		this.reservation = reservation;
	}
	
	public void informDataWarehouse() {
		dao.save(reservation);
		Log.e("ECUSTRES_STOREDCUSTOMER", reservation.getCustomer().getID() + reservation.getInsurance());
		Log.e("ECUSTDAO_STOREDCUSTOMER", dao.findAll().get(0).getCustomer()+"");
		Log.e("ECOMMDAO_STOREDCUSTOMER", DAO_UI_Adapter.getRES().getDAO().findAll().get(0).getCustomer()+"");
	}
	
	public void emailReservationDetails(EmailProviderService mailServer, EmailMessage message) {
		mailServer.sendEmail(message);
	}
	
	public com.example.georgiosmoschovis.aceindemo.services.PReservationSummary notifier(){
		return this;
	}
	
	public String toString() {
		return "Your reservation for " + reservation.getDate() + " successfully sumbited.";
	}
}
