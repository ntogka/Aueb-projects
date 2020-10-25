package com.example.georgiosmoschovis.aceindemo.dao;

/**
 * This class illustrates a sample data base object container.
 */
public class EmployeesStubDAO {
    EmployeesImplDAO stub = new EmployeesImplDAO();

    public void initialize() {
        com.example.georgiosmoschovis.aceindemo.domain.EEmployee e1 = new com.example.georgiosmoschovis.aceindemo.domain.EEmployee("userE1","1234","Georgios","Moschovis","029152320");
        com.example.georgiosmoschovis.aceindemo.domain.EEmployee e2 = new com.example.georgiosmoschovis.aceindemo.domain.EEmployee("userE2","5678","Mitsos","Mpoukas","029475698");
        com.example.georgiosmoschovis.aceindemo.domain.EEmployee e3 = new com.example.georgiosmoschovis.aceindemo.domain.EEmployee("userE3","7654","Professor Theodore","Apostolopoulos","025259887");
        stub.save(e1);
        stub.save(e2);
        stub.save(e3);
    }

    public EmployeesImplDAO getDAO() {
        return stub;
    }
}
