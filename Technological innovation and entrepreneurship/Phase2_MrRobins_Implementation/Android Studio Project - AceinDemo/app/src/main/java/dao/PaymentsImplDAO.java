package com.example.georgiosmoschovis.aceindemo.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * This class illustrates a sample data base object implementation for Payments.
 */
public class PaymentsImplDAO implements PaymentsDAO {
protected static List<com.example.georgiosmoschovis.aceindemo.domain.EPayment> entities = new ArrayList<com.example.georgiosmoschovis.aceindemo.domain.EPayment>();
    
    public void delete(com.example.georgiosmoschovis.aceindemo.domain.EPayment entity) {
        entities.remove(entity);    
    }

    public List<com.example.georgiosmoschovis.aceindemo.domain.EPayment> findAll() {
        return entities;
    }

    public void save(com.example.georgiosmoschovis.aceindemo.domain.EPayment entity) {
        if (! entities.contains(entity)) {
            entities.add(entity);    
        }        
    }
    
    public com.example.georgiosmoschovis.aceindemo.domain.EPayment find(String itemNo) {
        for(com.example.georgiosmoschovis.aceindemo.domain.EPayment item : entities) {
            if (item.getPassword() .equals( itemNo)) {
                return item;
            }
        }
        return null;
    }
}
