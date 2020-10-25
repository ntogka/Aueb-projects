package com.example.georgiosmoschovis.aceindemo.dao;

import com.example.georgiosmoschovis.aceindemo.domain.EInsurance;

import java.util.List;

/**
 * This class illustrates a sample data base object interface for Insurances.
 */
public interface InsuranceDAO {
    EInsurance find(int itemNo);
    void save(EInsurance entity);
    void delete(EInsurance entity);
    List<EInsurance> findAll();
    List<Integer> findDetails();
}
