package com.example.georgiosmoschovis.aceindemo.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class illustrates a sample data base object implementation for Reservations.
 */
public class ReservationsImplDAO implements ReservationsDAO, Serializable {
		protected static List<com.example.georgiosmoschovis.aceindemo.domain.EReservation> entities = new ArrayList<com.example.georgiosmoschovis.aceindemo.domain.EReservation>();
	    
	    public void delete(com.example.georgiosmoschovis.aceindemo.domain.EReservation entity) {
	        entities.remove(entity);    
	    }

	    public List<com.example.georgiosmoschovis.aceindemo.domain.EReservation> findAll() {
	        return entities;
	    }

	    public void save(com.example.georgiosmoschovis.aceindemo.domain.EReservation entity) {
	        if (! entities.contains(entity)) {
	            entities.add(entity);    
	        }        
	    }
	    
}
