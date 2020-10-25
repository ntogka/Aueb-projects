package com.example.georgiosmoschovis.aceindemo.services;

/**
 * This class illustrates a payment summary.
 */
public class PaymentSummary {
	com.example.georgiosmoschovis.aceindemo.dao.PaymentsDAO dao;
	com.example.georgiosmoschovis.aceindemo.domain.EPayment payment;
	
	public PaymentSummary(com.example.georgiosmoschovis.aceindemo.dao.PaymentsDAO dao, com.example.georgiosmoschovis.aceindemo.domain.EPayment payment) {
		this.dao = dao;
		this.payment = payment;
	}
	
	public void informDataWarehouse() {
		dao.save(payment);
	}
	
	public com.example.georgiosmoschovis.aceindemo.services.PaymentSummary notifier(){
		informDataWarehouse();
		return this;
	}
	
	public String toString() {
		return "Your payment, No. " + payment.getPassword() + " was successfully submitted.";
	}
}
