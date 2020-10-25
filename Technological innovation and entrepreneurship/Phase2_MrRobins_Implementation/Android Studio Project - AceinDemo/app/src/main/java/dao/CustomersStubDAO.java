package com.example.georgiosmoschovis.aceindemo.dao;

/**
 * This class illustrates a sample data base object container.
 */
public class CustomersStubDAO {
	CustomersImplDAO stub = new CustomersImplDAO();

	public void initialize() {
		com.example.georgiosmoschovis.aceindemo.domain.ECustomer c1 = new com.example.georgiosmoschovis.aceindemo.domain.ECustomer("user1","1234","Georgios","Moschovis","USER021", new com.example.georgiosmoschovis.aceindemo.domain.Barcode("BCD102", "DSlso2%&js"),null);
		com.example.georgiosmoschovis.aceindemo.domain.ECustomer c2 = new com.example.georgiosmoschovis.aceindemo.domain.ECustomer("user2","5678","Mitsos","Mpoukas","USER052", new com.example.georgiosmoschovis.aceindemo.domain.Barcode("BCD425", "KSo3$*sw!"),null);
		com.example.georgiosmoschovis.aceindemo.domain.ECustomer c3 = new com.example.georgiosmoschovis.aceindemo.domain.ECustomer("user3","7654","Professor Theodore","Apostolopoulos","USER052", new com.example.georgiosmoschovis.aceindemo.domain.Barcode("BCD928", "KS92S#%l"),null);
		stub.save(c1);
		stub.save(c2);
		stub.save(c3);
	}
	
	public CustomersDAO getDAO() {
		return stub;
	}
}
