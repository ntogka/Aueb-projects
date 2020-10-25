package com.example.georgiosmoschovis.aceindemo.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class illustrates a sample data base object implementation for Customers.
 */
public class CustomersImplDAO implements CustomersDAO, Serializable {
	protected static List<com.example.georgiosmoschovis.aceindemo.domain.ECustomer> entities = new ArrayList<com.example.georgiosmoschovis.aceindemo.domain.ECustomer>();
    
    public void delete(com.example.georgiosmoschovis.aceindemo.domain.ECustomer entity) {
        entities.remove(entity);    
    }

    public List<com.example.georgiosmoschovis.aceindemo.domain.ECustomer> findAll() {
        return entities;
    }

    public void save(com.example.georgiosmoschovis.aceindemo.domain.ECustomer entity) {
        if (! entities.contains(entity)) {
            entities.add(entity);    
        }        
    }
    
    public com.example.georgiosmoschovis.aceindemo.domain.ECustomer find(String itemNo) {
        for(com.example.georgiosmoschovis.aceindemo.domain.ECustomer item : entities) {
            if (item.getID() .equals( itemNo)) {
                return item;
            }
        }
        return null;
    }
    
    public com.example.georgiosmoschovis.aceindemo.domain.User verify(String username, String password){
    	for(com.example.georgiosmoschovis.aceindemo.domain.ECustomer item: entities) {
    		if(item.getLoginData().getUsername().equals(username)) {
    			if(item.getLoginData().getPassword().equals(password)) return item;
    		}
    	}
    	return null;
    }
}
