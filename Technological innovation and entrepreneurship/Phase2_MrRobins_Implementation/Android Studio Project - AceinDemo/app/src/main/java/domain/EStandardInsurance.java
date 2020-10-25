package com.example.georgiosmoschovis.aceindemo.domain;

/**
 * This class illustrates a generic Standard Configured Insurance.
 */
public class EStandardInsurance extends EInsurance {
	/* Domain Model: Instance variables */
	private Money price;

	/* Overloaded Constructor */
    public EStandardInsurance(String name, String difficulty, int duration, int capacity, Money price) {
    	super(name, difficulty, duration, capacity);
        this.price = price;
    }

    /* Getters */
    public Money getPrice() {
        return price;
    }
}
