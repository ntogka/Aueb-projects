package com.example.georgiosmoschovis.aceindemo.dao;

import java.util.List;

/**
* This class illustrates a sample data base object interface for Customers.
*/
public interface CustomersDAO extends UsersDAO {
    com.example.georgiosmoschovis.aceindemo.domain.ECustomer find(String visitorNo);
    void save(com.example.georgiosmoschovis.aceindemo.domain.ECustomer entity);
    void delete(com.example.georgiosmoschovis.aceindemo.domain.ECustomer entity);
    List<com.example.georgiosmoschovis.aceindemo.domain.ECustomer> findAll();
}
