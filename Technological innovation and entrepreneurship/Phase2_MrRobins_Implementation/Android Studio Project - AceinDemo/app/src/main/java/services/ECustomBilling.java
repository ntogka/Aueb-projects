package com.example.georgiosmoschovis.aceindemo.services;

/**
 * This class implements Billing fan-out.
 */
public class ECustomBilling {

    com.example.georgiosmoschovis.aceindemo.domain.EReservation reservation;
    com.example.georgiosmoschovis.aceindemo.domain.EPayment payment;
    String report;

    public ECustomBilling() {}

    public ECustomBilling(com.example.georgiosmoschovis.aceindemo.domain.EReservation r, com.example.georgiosmoschovis.aceindemo.domain.EPayment p) {
        this.reservation = r;
        this.payment = p;
    }

    public ECustomBilling submit(com.example.georgiosmoschovis.aceindemo.domain.EReservation r, com.example.georgiosmoschovis.aceindemo.domain.EPayment p) {
        ECustomBilling b = new ECustomBilling(r, p);
        b = b.setReport();
        return b;
    }

    private ECustomBilling setReport() {
        report = ("Reservation: " + new ECustomReservationItem(reservation, reservation.getCustomer(), reservation.getInsurance()).getReservationInformation());
        report += "\nbeing paid as: No. " + payment;
        return this;
    }

    public String getReport() {
        return this.report;
    }
}
