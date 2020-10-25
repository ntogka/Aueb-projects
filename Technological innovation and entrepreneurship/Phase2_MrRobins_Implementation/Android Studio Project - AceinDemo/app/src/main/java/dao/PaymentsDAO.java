package com.example.georgiosmoschovis.aceindemo.dao;

import java.util.List;

/**
 * This class illustrates a sample data base object interface for Payments.
 */
public interface PaymentsDAO {
	com.example.georgiosmoschovis.aceindemo.domain.EPayment find(String itemNo);
	    void save(com.example.georgiosmoschovis.aceindemo.domain.EPayment entity);
	    void delete(com.example.georgiosmoschovis.aceindemo.domain.EPayment entity);
	    List<com.example.georgiosmoschovis.aceindemo.domain.EPayment> findAll();
}
