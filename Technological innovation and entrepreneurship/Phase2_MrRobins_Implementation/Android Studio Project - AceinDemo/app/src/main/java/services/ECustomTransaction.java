package com.example.georgiosmoschovis.aceindemo.services;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.example.georgiosmoschovis.aceindemo.domain.EReservation;
import com.example.georgiosmoschovis.aceindemo.ui.DAO_UI_Adapter;

/**
 * This class implements Transactions.
 */
public class ECustomTransaction implements Serializable {
	com.example.georgiosmoschovis.aceindemo.domain.EPayment payment;
	com.example.georgiosmoschovis.aceindemo.domain.EReservation reservation;
	com.example.georgiosmoschovis.aceindemo.domain.PaymentReceipt receipt;
	com.example.georgiosmoschovis.aceindemo.services.PaymentSummary summary;
	String report;
	
	public ECustomTransaction() {}
	
	public ECustomTransaction(com.example.georgiosmoschovis.aceindemo.domain.EPayment pay, com.example.georgiosmoschovis.aceindemo.domain.EReservation res, com.example.georgiosmoschovis.aceindemo.domain.PaymentReceipt rec) {
		this.payment = pay;
		this.reservation = res;
		this.receipt = rec;
	}
	
	public ECustomTransaction submit(com.example.georgiosmoschovis.aceindemo.domain.EPayment payment, Date date) {
		com.example.georgiosmoschovis.aceindemo.domain.EReservation res = payment.getReservation();
		com.example.georgiosmoschovis.aceindemo.domain.PaymentReceipt rec = null;
		if(res.getPaymentInfo().equals("Receipt")) {
			com.example.georgiosmoschovis.aceindemo.domain.MeansOfPayment m = com.example.georgiosmoschovis.aceindemo.domain.MeansOfPayment.receipt;
			rec = new com.example.georgiosmoschovis.aceindemo.domain.PaymentReceipt("REC"+payment.getPassword(), m, date, res.getInsurance().getPrice().getValue());
		}else if(res.getPaymentInfo().equals("Invoice")) {
			com.example.georgiosmoschovis.aceindemo.domain.MeansOfPayment m = com.example.georgiosmoschovis.aceindemo.domain.MeansOfPayment.invoice;
			rec = new com.example.georgiosmoschovis.aceindemo.domain.PaymentReceipt("REC"+payment.getPassword(), m, date, res.getInsurance().getPrice().getValue());
		}else if(res.getPaymentInfo().equals("Check")) {
			com.example.georgiosmoschovis.aceindemo.domain.MeansOfPayment m = com.example.georgiosmoschovis.aceindemo.domain.MeansOfPayment.check;
			rec = new com.example.georgiosmoschovis.aceindemo.domain.PaymentReceipt("REC"+payment.getPassword(), m, date, res.getInsurance().getPrice().getValue());
		}else if(res.getPaymentInfo().equals("Bitcoin")) {
			com.example.georgiosmoschovis.aceindemo.domain.MeansOfPayment m = com.example.georgiosmoschovis.aceindemo.domain.MeansOfPayment.bitcoin;
			rec = new com.example.georgiosmoschovis.aceindemo.domain.PaymentReceipt("REC"+payment.getPassword(), m, date, res.getInsurance().getPrice().getValue());
		}
		return new ECustomTransaction(payment, res, rec);
	}
	
	public ECustomTransaction getCurrentPayment(com.example.georgiosmoschovis.aceindemo.dao.WebBankingAPI API, CardReaderService POS, CreditCard card) {
		this.report = ("Reservation: " + new ECustomReservationItem(reservation, reservation.getCustomer(), reservation.getInsurance()).getReservationInformation());
		ECustomTransactionItem tran = new ECustomTransactionItem(API, POS);
		if(payment.getOption().equals("Card") && tran.enableSwipes()) {
			payment = tran.getTransactionDetails(card, payment, 0);
		}
		else if(payment.getOption().equals("Cash"))
			payment = tran.getTransactionDetails(null, payment, 1);
		else
			payment = tran.getTransactionDetails(null, payment, -1);
		report += "\nbeing paid as: No. " + payment;
		return this;
	}
	
	public String getReport() {
		return this.report;
	}
	
	public com.example.georgiosmoschovis.aceindemo.services.PaymentSummary getSummary() {
		return summary.notifier();
	}
	
	public String getNotification() {
		return (getSummary()).toString();
	}
	
	public ECustomTransaction setPaymentDetails(com.example.georgiosmoschovis.aceindemo.dao.PaymentsDAO dao) {
		payment.addReceipt(receipt);
		ECustomPayment daoPayment;
		List<EReservation> resDAO = DAO_UI_Adapter.getRES().getDAO().findAll();
		for(EReservation res: resDAO) {
			if(res.getID() == payment.getReservation().getID())
				res.getPayment().addReceipt(receipt);
		}
		summary = new PaymentSummary(dao, payment) ;
		return this;
	}
	
}
