package com.example.georgiosmoschovis.aceindemo.services;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.io.Serializable;
import java.util.Date;

import com.example.georgiosmoschovis.aceindemo.ui.DAO_UI_Adapter;

/**
 * This class implements Escape Rooms Reservations.
 */
public class ECustomReservation implements Serializable {
	com.example.georgiosmoschovis.aceindemo.domain.EReservation reservation;
	com.example.georgiosmoschovis.aceindemo.domain.ECustomer customer;
	com.example.georgiosmoschovis.aceindemo.domain.EInsurance insurance;
	com.example.georgiosmoschovis.aceindemo.dao.CustomersDAO custDAO;
	com.example.georgiosmoschovis.aceindemo.dao.EmployeesDAO empDAO;
	String informationReport;
	ECustomReservationItem element;
	ECustomContactItem contactManager;
	PReservationSummary summary;
	
	public ECustomReservation() {}
	
	public ECustomReservation(com.example.georgiosmoschovis.aceindemo.domain.EReservation reservation, com.example.georgiosmoschovis.aceindemo.domain.ECustomer customer,com.example.georgiosmoschovis.aceindemo.domain.EInsurance insurance, com.example.georgiosmoschovis.aceindemo.dao.CustomersDAO dao2, com.example.georgiosmoschovis.aceindemo.dao.EmployeesDAO dao) {
		this.reservation = reservation;
		this.customer = customer;
		this.insurance = insurance;
		this.custDAO = dao2;
		this.empDAO = dao;
	}
	
	public ECustomReservation submit(String CustomerUser, com.example.georgiosmoschovis.aceindemo.domain.EInsurance ins, Date date, String paymentInfo, int numberOfPeople, com.example.georgiosmoschovis.aceindemo.dao.CustomersDAO dao2, com.example.georgiosmoschovis.aceindemo.dao.EmployeesDAO dao) {
		com.example.georgiosmoschovis.aceindemo.domain.EReservation resDetails = new com.example.georgiosmoschovis.aceindemo.domain.EReservation(date, paymentInfo, numberOfPeople);
		this.element = new ECustomReservationItem(resDetails, ins, CustomerUser, dao2);
		ECustomReservation r = new ECustomReservation(resDetails, element.getCustomer(), element.getInsurance(), dao2, dao);
		Log.e("ECUSTRES_CUSTOMER", element.getCustomer().getID() + " " + element.getCustomer().getFirstName());
		r.element = this.element;
		r.informationReport = element.getReservationInformation();
		return r;
	}
	
	public String getReport() {
		return informationReport;
	}
	
	public ECustomReservation confirm(com.example.georgiosmoschovis.aceindemo.dao.ReservationsDAO dao, EmailProviderService mailServer, EmailAddress to) {
		insurance.addReservation(reservation);
		//DAO_UI_Adapter.getESC().getDAO().find(escapeRoom.CurrID).addReservation(reservation);
		customer.addReservation(reservation);
		DAO_UI_Adapter.getCUST().getDAO().find(customer.getID()).addReservation(reservation);
		Log.e("ECUSTRES_VALIDCUSTOMER", customer.getID() + " " + reservation.getCustomer().getID() + reservation.getInsurance());
		summary = new PReservationSummary(dao, reservation);
		summary.informDataWarehouse();
		if(to != null) {
			EmailMessage message = new EmailMessage(to, "Your Insurance reservation details!", informationReport);
			summary.emailReservationDetails(mailServer, message);
		}
		return this;
	}
	
	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	public ECustomReservation contact(com.example.georgiosmoschovis.aceindemo.ui.UIDialogTemplate dialog) {
		contactManager = new ECustomContactItem(empDAO, reservation, dialog.getFurtherDetails()[0]);
		contactManager.assignToEmployee();
		return this;
	}
	
	public com.example.georgiosmoschovis.aceindemo.services.PReservationSummary summarize() {
		return summary.notifier();
	}

	public ECustomReservationItem getElement() {
		return element;
	}
	
	public ECustomContactItem getManager() {
		return contactManager;
	}

	public com.example.georgiosmoschovis.aceindemo.domain.EReservation getReservation() {
		return reservation;
	}
	
}
