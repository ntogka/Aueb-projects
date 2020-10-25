package com.example.georgiosmoschovis.aceindemo.services;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class implements calculations on Users' contact.
 */
public class ECustomContactItem {
	com.example.georgiosmoschovis.aceindemo.dao.EmployeesDAO dao;
	com.example.georgiosmoschovis.aceindemo.domain.EReservation reservation;
	com.example.georgiosmoschovis.aceindemo.domain.EEmployee employee;
	String message;
	
	public ECustomContactItem(com.example.georgiosmoschovis.aceindemo.dao.EmployeesDAO dao, com.example.georgiosmoschovis.aceindemo.domain.EReservation reservation, String message) {
		this.dao = dao;
		this.reservation = reservation;
		this.message = message;
	}
	
	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	public void assignToEmployee() {
		List<com.example.georgiosmoschovis.aceindemo.domain.EEmployee> employees = dao.findAll();
		int empID =  ThreadLocalRandom.current().nextInt(1, employees.size());
		employee = employees.get(empID);
		employee.addReservation(reservation);
	}
	
	public String toString() {
		return "Employee " + employee.getFirstName() + " " + employee.getLastName() + " will help you with your reservation!";
	}
}
