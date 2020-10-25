package com.example.georgiosmoschovis.aceindemo.dao;

import java.util.List;

/**
 * This class illustrates a sample data base object interface for Reservations.
 */
public interface ReservationsDAO {
	void save(com.example.georgiosmoschovis.aceindemo.domain.EReservation entity);
    void delete(com.example.georgiosmoschovis.aceindemo.domain.EReservation entity);
    List<com.example.georgiosmoschovis.aceindemo.domain.EReservation> findAll();
}
