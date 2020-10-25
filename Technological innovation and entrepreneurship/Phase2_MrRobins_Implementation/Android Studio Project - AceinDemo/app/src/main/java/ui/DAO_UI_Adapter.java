package com.example.georgiosmoschovis.aceindemo.ui;

import com.example.georgiosmoschovis.aceindemo.dao.RFID_Manager;

/**
 * This class illustrates a multiple data base objects container; such as a database library.
 */
public class DAO_UI_Adapter {

    private static com.example.georgiosmoschovis.aceindemo.dao.CustomersStubDAO CUST;
    private static com.example.georgiosmoschovis.aceindemo.dao.EmployeesStubDAO EMPL;
    private static com.example.georgiosmoschovis.aceindemo.dao.ReservationsStubDAO RES;
    private static com.example.georgiosmoschovis.aceindemo.dao.InsuranceStubDAO ESC;
    private static com.example.georgiosmoschovis.aceindemo.dao.PaymentsStubDAO PAY;
    private static com.example.georgiosmoschovis.aceindemo.dao.RFID_Manager RFID;

    public static void initialize() {
        com.example.georgiosmoschovis.aceindemo.dao.InsuranceStubDAO dao = new com.example.georgiosmoschovis.aceindemo.dao.InsuranceStubDAO();
        dao.initialize();
        ESC = dao;
        com.example.georgiosmoschovis.aceindemo.dao.CustomersStubDAO dao1 = new com.example.georgiosmoschovis.aceindemo.dao.CustomersStubDAO();
        dao1.initialize();
        CUST = dao1;
        com.example.georgiosmoschovis.aceindemo.dao.EmployeesStubDAO dao2 = new com.example.georgiosmoschovis.aceindemo.dao.EmployeesStubDAO();
        dao2.initialize();
        EMPL = dao2;
        com.example.georgiosmoschovis.aceindemo.dao.ReservationsStubDAO dao3 = new com.example.georgiosmoschovis.aceindemo.dao.ReservationsStubDAO();
        dao3.initialize();
        RES = dao3;
        com.example.georgiosmoschovis.aceindemo.dao.PaymentsStubDAO dao4 = new com.example.georgiosmoschovis.aceindemo.dao.PaymentsStubDAO();
        dao4.initialize();
        PAY = dao4;
        com.example.georgiosmoschovis.aceindemo.dao.RFID_Manager rfid_manager = new com.example.georgiosmoschovis.aceindemo.dao.RFID_Manager();
        RFID = rfid_manager;
    }

    public static com.example.georgiosmoschovis.aceindemo.dao.CustomersStubDAO getCUST() {
        return CUST;
    }

    public static com.example.georgiosmoschovis.aceindemo.dao.EmployeesStubDAO getEMPL() {
        return EMPL;
    }

    public static com.example.georgiosmoschovis.aceindemo.dao.ReservationsStubDAO getRES() {
        return RES;
    }

    public static com.example.georgiosmoschovis.aceindemo.dao.InsuranceStubDAO getESC() {
        return ESC;
    }

    public static com.example.georgiosmoschovis.aceindemo.dao.PaymentsStubDAO getPAY() { return PAY; }

    public static com.example.georgiosmoschovis.aceindemo.dao.RFID_Manager getRFID() { return RFID;}
}
