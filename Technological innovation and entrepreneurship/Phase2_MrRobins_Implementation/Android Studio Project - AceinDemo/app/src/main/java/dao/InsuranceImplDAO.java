package com.example.georgiosmoschovis.aceindemo.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class illustrates a sample data base object implementation for Insurances.
 */
public class InsuranceImplDAO implements InsuranceDAO, Serializable{
	protected static List<com.example.georgiosmoschovis.aceindemo.domain.EInsurance> entities = new ArrayList<com.example.georgiosmoschovis.aceindemo.domain.EInsurance>();
	protected static List<Integer> pictures = new ArrayList<Integer>();
    
    public void delete(com.example.georgiosmoschovis.aceindemo.domain.EInsurance entity) {
        entities.remove(entity);    
    }

    public List<com.example.georgiosmoschovis.aceindemo.domain.EInsurance> findAll() {
        return entities;
    }
    
    public List<Integer> findDetails() {
        return pictures;
    }

    public void save(com.example.georgiosmoschovis.aceindemo.domain.EInsurance entity) {
        if (! entities.contains(entity)) {
            entities.add(entity);    
        }        
    }
    public com.example.georgiosmoschovis.aceindemo.domain.EInsurance find(int itemNo) {
        for(com.example.georgiosmoschovis.aceindemo.domain.EInsurance item : entities) {
            if (item.ID == itemNo) {
                return item;
            }
        }
        return null;
    }
}
