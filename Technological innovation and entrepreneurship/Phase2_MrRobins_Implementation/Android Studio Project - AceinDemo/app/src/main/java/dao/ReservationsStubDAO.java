package com.example.georgiosmoschovis.aceindemo.dao;

/**
 * This class illustrates a sample data base object container.
 */
public class ReservationsStubDAO {
	ReservationsImplDAO stub = new ReservationsImplDAO();

	public void initialize() {}
	
	public ReservationsDAO getDAO() {
		return stub;
	}
}
