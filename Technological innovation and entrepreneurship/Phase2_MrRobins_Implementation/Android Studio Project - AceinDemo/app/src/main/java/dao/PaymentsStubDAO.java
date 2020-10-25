package com.example.georgiosmoschovis.aceindemo.dao;

/**
 * This class illustrates a sample data base object container.
 */
public class PaymentsStubDAO {
		PaymentsImplDAO stub = new PaymentsImplDAO();

		public void initialize() {
		}
		
		public PaymentsDAO getDAO() {
			return stub;
		}
}
