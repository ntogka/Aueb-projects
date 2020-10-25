package com.example.georgiosmoschovis.aceindemo.services;

/**
 * This class implements computations on Transactions.
 */
public class ECustomTransactionItem {
	com.example.georgiosmoschovis.aceindemo.dao.WebBankingAPI API;
	CardReaderService POS;
	
	public ECustomTransactionItem(com.example.georgiosmoschovis.aceindemo.dao.WebBankingAPI API, CardReaderService POS) {
		this.API = API;
		this.POS = POS;
	}
	
	public boolean enableSwipes() {
		return (API.getEligibility());
	}
	
	public com.example.georgiosmoschovis.aceindemo.domain.EPayment getTransactionDetails(CreditCard card, com.example.georgiosmoschovis.aceindemo.domain.EPayment payment, int mode) {
		com.example.georgiosmoschovis.aceindemo.domain.EPayment newPayment;
		if(mode == 0) {
			POS.readCard(card);
			newPayment = this.registerPayment(card, payment);
		}else if(mode == 1) {
			newPayment = new com.example.georgiosmoschovis.aceindemo.domain.CashPayment(payment);
		}else {
			newPayment = payment;
		}
		return newPayment;
	}
	
	public com.example.georgiosmoschovis.aceindemo.domain.EPayment registerPayment(CreditCard card, com.example.georgiosmoschovis.aceindemo.domain.EPayment payment){
		return new com.example.georgiosmoschovis.aceindemo.domain.CardPayment(payment, card.getCardCode(), card.getCardName(), card.getExpirationDate());
	}
}
