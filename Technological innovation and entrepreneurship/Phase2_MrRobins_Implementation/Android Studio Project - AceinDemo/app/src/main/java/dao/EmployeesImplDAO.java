package com.example.georgiosmoschovis.aceindemo.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class illustrates a sample data base object implementation for Employees.
 */
public class EmployeesImplDAO implements EmployeesDAO, Serializable {
	protected static List<com.example.georgiosmoschovis.aceindemo.domain.EEmployee> entities = new ArrayList<com.example.georgiosmoschovis.aceindemo.domain.EEmployee>();

	public com.example.georgiosmoschovis.aceindemo.domain.EEmployee find(String employeeSSN) {
		for(com.example.georgiosmoschovis.aceindemo.domain.EEmployee item : entities) {
            if (item.getAMKA() .equals( employeeSSN)) {
                return item;
            }
        }
        return null;
	}

	public void save(com.example.georgiosmoschovis.aceindemo.domain.EEmployee entity) {
		 if (! entities.contains(entity)) {
	            entities.add(entity);    
	        } 
	}

	public void delete(com.example.georgiosmoschovis.aceindemo.domain.EEmployee entity) {
		 entities.remove(entity);
	}

	public List<com.example.georgiosmoschovis.aceindemo.domain.EEmployee> findAll() {
		return entities;
	}
	
	public com.example.georgiosmoschovis.aceindemo.domain.User verify(String username, String password){
    	for(com.example.georgiosmoschovis.aceindemo.domain.EEmployee item: entities) {
    		if(item.getLoginData().getUsername().equals(username)) {
    			if(item.getLoginData().getPassword().equals(password)) return item;
    		}
    	}
    	return null;
    }

}
