package com.example.georgiosmoschovis.aceindemo.dao;

import java.util.List;

/**
 * This class illustrates a sample data base object interface for Employees.
 */
public interface EmployeesDAO extends UsersDAO {
    com.example.georgiosmoschovis.aceindemo.domain.EEmployee find(String employeeSSN);
    void save(com.example.georgiosmoschovis.aceindemo.domain.EEmployee entity);
    void delete(com.example.georgiosmoschovis.aceindemo.domain.EEmployee entity);
    List<com.example.georgiosmoschovis.aceindemo.domain.EEmployee> findAll();
}
