package com.example.georgiosmoschovis.aceindemo.services;

import com.example.georgiosmoschovis.aceindemo.domain.EInsurance;

import java.io.Serializable;

import com.example.georgiosmoschovis.aceindemo.domain.EInsurance;

/**
 * This class illustrates a configuration summary.
 */
public class PConfigurationSummary implements Serializable {
	private String name, description;
	private Integer view;
	private com.example.georgiosmoschovis.aceindemo.domain.Money price;

	public PConfigurationSummary(Integer view) {
		this.view = view;
	}
	
	public PConfigurationSummary(String name, String description, Integer view, com.example.georgiosmoschovis.aceindemo.domain.Money price) {
		this.name = name;
		this.description = description;
		this.view = view;
		this.price = price;
	}
	
	public PConfigurationSummary(PConfigurationSummary basic, String description) {
		this.name = basic.name;
		this.description = basic.description + "\n" + description;
		this.view = basic.view;
		this.price = basic.price;
	}

	public PConfigurationSummary(EInsurance insurance, Integer view, String s) {
		this.name = insurance.getName();
		this.description = insurance.getInsuranceDescription() + "\nExtras: " + s;
		this.view = view;
		this.price = insurance.getPrice();
	}

	public Integer getView() {
		return view;
	}

	public String toString() {
		return "Name: " + this.name + " \nPrice: " + this.price.getValue() + " " + this.price.getCurrency().toString()  + " \n" + this.description;
	}
}
