package com.example.georgiosmoschovis.aceindemo.services;

import android.util.Log;

/**
 * This class illustrates a Standard Configuration modification summary.
 */
public class PModificationSummary {
	com.example.georgiosmoschovis.aceindemo.dao.InsuranceDAO dao;
	com.example.georgiosmoschovis.aceindemo.domain.EInsurance insurance;
	
	public PModificationSummary(com.example.georgiosmoschovis.aceindemo.dao.InsuranceDAO dao, com.example.georgiosmoschovis.aceindemo.domain.EInsurance insurance) {
		this.dao = dao;
		this.insurance = insurance;
	}
	
	public void informDataWarehouse() {
		dao.save(insurance);
	}
	
	public void emailReservationDetails(EmailProviderService mailServer, EmailMessage message) {
		Log.e("MAIL_MESSAGE", (message!=null)+"");
		Log.e("MAIL_RECEIP", message.getTo().getAddress());
		Log.e("MAIL_SUBJECT", message.getSubject());
		Log.e("MAIL_BODY", message.getBody());
		Log.e("MAIL_SERVICE", "Ready to send mail");
		mailServer.sendEmail(message);
	}
	
	public com.example.georgiosmoschovis.aceindemo.services.PModificationSummary notifier(){
		return this;
	}
	
	public String toString() {
		return "Your modification for " + insurance.getName() + " successfully sumbited.";
	}
}
